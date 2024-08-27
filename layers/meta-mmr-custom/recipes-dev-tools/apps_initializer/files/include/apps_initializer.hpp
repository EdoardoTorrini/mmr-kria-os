#include "SCL.hpp"
#include <sys/ioctl.h>
#include <linux/spi/spidev.h>
#include <fcntl.h>
#include <linux/spi/spi.h>
#include <unistd.h>
#include <system_error>
#include <memory.h>
#include <algorithm>
#include <cstdint>
#include <iostream>

class AppsInitializer {     

  public:             
    AppsInitializer();
    constexpr static uint16_t MAX_VALUE = 0xFFF;
  private:
    // The device is active
    // FLAG_ACTIVE = 0x1,

    // The device has unitary gain (2x otherwise)
    // FLAG_UNITARY_GAIN = 0x2,

    // // VRef is buffered
    // FLAG_BUFFERED = 0x4

    uint8_t flag = 0b0011;
    float vMin, vMax, vRef, perc; 
    int spiDevice;
    char* spiInterface = "/dev/spidev1.0";
    int spiFrequency = 50000;
    bool debug;

    void readParameters();
    void sendMCP();
    void initializeMCP();

    uint16_t voltageToValue(double v);
    int writeMCP(uint16_t value, uint8_t flags);    

};