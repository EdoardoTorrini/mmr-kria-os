#include <mcp4921.hpp>

#include <sys/ioctl.h>
#include <linux/spi/spidev.h>
#include <fcntl.h>
#include <linux/spi/spi.h>
#include <unistd.h>
#include <system_error>
#include <memory.h>

MCP4921::MCP4921(const std::string& interface, uint32_t frequency)
{
  constexpr uint8_t SPI_MODE = SPI_MODE_0;
  constexpr uint8_t BITS_PER_WORD = 8;

  int dev = open(interface.c_str(), O_RDWR);

  if (dev < 0)
    throw std::system_error(errno, std::generic_category(), "While opening " + interface);

  if (ioctl(dev, SPI_IOC_WR_MODE, &SPI_MODE) < 0)
    throw std::system_error(errno, std::generic_category(), "While setting SPI mode of " + interface);

  if (ioctl(dev, SPI_IOC_WR_BITS_PER_WORD, &BITS_PER_WORD) < 0)
    throw std::system_error(errno, std::generic_category(), "While setting BPW of " + interface);

  if (ioctl(dev, SPI_IOC_WR_MAX_SPEED_HZ, &frequency) < 0)
    throw std::system_error(errno, std::generic_category(), "While setting frequency of " + interface);

  m_io_device = dev;

  write(0);
}

int MCP4921::write(uint16_t value) {
  uint16_t input_data = std::clamp<uint16_t>(value, 0, MAX_VALUE);
  uint16_t command = (CONFIG << 12) | input_data;

  uint8_t buf[] = { static_cast<uint8_t>(command >> 8), static_cast<uint8_t>(command) };

  struct spi_ioc_transfer tr;
  memset(&tr, 0, sizeof(spi_ioc_transfer));
  tr.tx_buf = (uintptr_t)buf;
  tr.len = sizeof(command);

  return ioctl(m_io_device, SPI_IOC_MESSAGE(1), &tr);
}

MCP4921::~MCP4921() {
  write(0);
  close(m_io_device);
}