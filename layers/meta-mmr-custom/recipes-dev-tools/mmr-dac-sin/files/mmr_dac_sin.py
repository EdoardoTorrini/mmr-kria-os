#!/usr/bin/python3

import ctypes, struct
import numpy as np
import signal
import argparse
from fcntl import ioctl
from time import sleep
from struct import pack
from functools import partial

# spi.h defs porting
SPI_IOC_MAGIC   = ord("k")

_IOC_NRBITS = 8
_IOC_TYPEBITS = 8

_IOC_SIZEBITS = 14
_IOC_DIRBITS = 2

_IOC_NRMASK = (1 << _IOC_NRBITS) - 1
_IOC_TYPEMASK = (1 << _IOC_TYPEBITS) - 1
_IOC_SIZEMASK = (1 << _IOC_SIZEBITS) - 1
_IOC_DIRMASK = (1 << _IOC_DIRBITS) - 1

_IOC_NRSHIFT = 0
_IOC_TYPESHIFT = _IOC_NRSHIFT + _IOC_NRBITS
_IOC_SIZESHIFT = _IOC_TYPESHIFT + _IOC_TYPEBITS
_IOC_DIRSHIFT = _IOC_SIZESHIFT + _IOC_SIZEBITS

_IOC_WRITE = 1

def _IOC(dir, type, nr, size):
    if isinstance(size, str) or isinstance(size, unicode):
        size = struct.calcsize(size)
    return dir  << _IOC_DIRSHIFT  | \
           type << _IOC_TYPESHIFT | \
           nr   << _IOC_NRSHIFT   | \
           size << _IOC_SIZESHIFT

def _IOW(type, nr, size): return _IOC(_IOC_WRITE, type, nr, size)

SPI_MODE_0	= (0|0)
SPI_IOC_WR_MODE          = _IOW(SPI_IOC_MAGIC, 1, "=B")
SPI_IOC_WR_BITS_PER_WORD = _IOW(SPI_IOC_MAGIC, 3, "=B")
SPI_IOC_WR_MAX_SPEED_HZ  = _IOW(SPI_IOC_MAGIC, 4, "=I")

def SPI_IOC_MESSAGE(size):
    return _IOW(SPI_IOC_MAGIC, 0, "=" + ("QQIIHBBI" * size))

class SPI_DAC:
    def __init__(self, filename: str):
        self.fd = open(filename, "w")
        
    def set_mode(self, mode: int):
        m = pack("=B", mode)
        ioctl(self.fd, SPI_IOC_WR_MODE, m)

    def set_bits_per_word(self, bpw: int):
        b = pack("=B", bpw)
        ioctl(self.fd, SPI_IOC_WR_BITS_PER_WORD, b)
        self.bpw = bpw

    def set_speed(self, speed: int):
        s = pack("=I", speed)
        ioctl(self.fd, SPI_IOC_WR_MAX_SPEED_HZ, s)
        self.speed = speed

    def write(self, data: int, bytes_num: int):
        # content = ctypes.create_strinag_buffer(content)
        content = (ctypes.c_char*bytes_num)(*data.to_bytes(bytes_num))
        
        # Building spi_ioc_transfer struct
        transfer = pack(
            "=QQIIHBBI",
            ctypes.addressof(content),
            0,
            len(content),
            self.speed,
            0,
            self.bpw,
            0,
            0
        )
        
        ioctl(self.fd, SPI_IOC_MESSAGE(1), transfer)


class MCP4921(SPI_DAC):
    def __init__(self, filename: str):
        super().__init__(filename)
        self.set_mode(0)
        self.set_bits_per_word(8)
        self.set_speed(500000)
    
    def send(self, config: int, dac_points: int):
        self.write((config << 12) | dac_points, 2)

def scale(val: float, fsr: int) -> int:
    return int(fsr*val)

def create_scale(fsr: int): return lambda x : scale(x, fsr)

def main(device: str, offset: float, amplitude: float, frequency: float):
    FSR = 0x0FFF
    CMD = 0x03
    
    if (offset - amplitude < 0 or offset + amplitude > 1): print("Warning: cutting wave")
    
    scale_12_bit = create_scale(FSR)
    amplitude = scale_12_bit(amplitude)
    offset = scale_12_bit(offset)
    
    mcp4921 = MCP4921(device)
    signal.signal(signal.SIGINT, lambda sig, frame: mcp4921.send(CMD, 0) or exit(0))

    sequence = np.sin(np.linspace(0, 1, 100, False) * 2 * np.pi)
    sequence = sequence.tolist()
    scale_amplitude = create_scale(amplitude)

    while True:
        for val in sequence:
            mcp4921.send(CMD, scale_amplitude(val)+offset)
            sleep(1/(len(sequence)*frequency))

if __name__ == "__main__":
    parser = argparse.ArgumentParser(
            prog="DAC sin wave generator",
            description="Test tool to move the APS valve following a sin wave"
    )
    
    parser.add_argument("-d", "--device", default="/dev/spidev1.0")
    parser.add_argument("-o", "--offset", default=0.1, type=float)
    parser.add_argument("-a", "--amplitude", default=0.1, type=float)
    parser.add_argument("-f", "--frequency", default=1.0, type=float)
    args = parser.parse_args()

    main(args.device, args.offset, args.amplitude, args.frequency)
