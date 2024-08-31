#include "../include/apps_initializer.hpp"
#include <bitset>

AppsInitializer::AppsInitializer()
{
    readParameters();
    initializeMCP();
    sendMCP();
}

void AppsInitializer::readParameters()
{

    if(this->debug)
        std::cout << "[DEBUG] Reading parameters " << std::endl;

    //scl::config_file configFile("/usr/share/apps_initializer/apps_intitializer.conf", scl::config_file::READ);
    this->vMax = 2.484;
    this->vMin = 0.942;
    this->vRef = 5.0;
    this->perc = 15/100;
    this->debug = true;
    //configFile.close();

    if (this->debug){
        std::cout << "[DEBUG] Succesfully read config file" << std::endl;
        std::cout << "[DEBUG] VMax: " << this->vMax << std::endl;
        std::cout << "[DEBUG] VMin: " << this->vMin << std::endl;
        std::cout << "[DEBUG] VRef: " << this->vRef << std::endl;
        std::cout << "[DEBUG] Perc: " << this->perc << std::endl;
    }
    
}

void AppsInitializer::sendMCP()
{
    if(this->debug)
        std::cout << "[DEBUG] Sending on MCP " << std::endl;

    float vRange = this->vMax - this->vMin;
    double throttle = this->perc;
    double voltage = this->vMin + (throttle * vRange);

    if(this->debug){
        std::cout << "[DEBUG] VRange " << vRange << std::endl;
        std::cout << "[DEBUG] throttle " << throttle << std::endl;
        std::cout << "[DEBUG] Voltage " << voltage << std::endl;
    }

    uint16_t data = voltageToValue(voltage);
    if (writeMCP(data, this->flag) < 0)
        throw std::system_error(errno, std::generic_category(), "[ERROR] Cannot write on SPI");

}

void AppsInitializer::initializeMCP(){
    if (this->debug)
        std::cout << "[DEBUG] Initializing SPI " << std::endl;

    
    constexpr uint8_t SPI_MODE = SPI_MODE_0;
    constexpr uint8_t BITS_PER_WORD = 8;
    this->spiDevice = 0;
    int dev = open("/dev/spidev1.0", O_RDWR);
    
    if (dev < 0)
        throw std::system_error(errno, std::generic_category(), "[ERROR] Cannot open interface");

    if (ioctl(dev, SPI_IOC_WR_MODE, &SPI_MODE) < 0)
        throw std::system_error(errno, std::generic_category(), "[ERROR] Cannot set spi mode");

    if (ioctl(dev, SPI_IOC_WR_BITS_PER_WORD, &BITS_PER_WORD) < 0)
        throw std::system_error(errno, std::generic_category(), "[ERROR] Cannot set BPW");

    if (ioctl(dev, SPI_IOC_WR_MAX_SPEED_HZ, &this->spiFrequency) < 0)
        throw std::system_error(errno, std::generic_category(), "[ERROR] Cannot set frequency");

    this->spiDevice = dev;

    if(this->debug)
        std::cout << "[DEBUG] SPI Initialized" << std::endl;
    
}

uint16_t AppsInitializer::voltageToValue(double v) {
  v = std::clamp<float>(v, 0.0, this->vRef);
  double value_f = AppsInitializer::MAX_VALUE * v / this->vRef;
  int value = std::clamp<int>(static_cast<int>(value_f), 0, AppsInitializer::MAX_VALUE);
  return static_cast<uint16_t>(value);
}

int AppsInitializer::writeMCP(uint16_t value, uint8_t flags) {
    
    if(this->debug)
        std::cout << "[DEBUG] Writing on MCP "  << std::endl;
    
    uint16_t input_data = std::clamp<uint16_t>(value, 0, AppsInitializer::MAX_VALUE);
    uint16_t command = ((uint8_t)flags << 12) | input_data;

    uint8_t buf[] = { static_cast<uint8_t>(command >> 8), static_cast<uint8_t>(command) };
    
    struct spi_ioc_transfer tr;
    memset(&tr, 0, sizeof(spi_ioc_transfer));
    tr.tx_buf = (uintptr_t)buf;
    tr.len = sizeof(command);

    if(this->debug)
        std::cout << "[DEBUG]  Message sended over SPI at MCP "  << std::endl;
    
    
    return ioctl(this->spiDevice, SPI_IOC_MESSAGE(1), &tr);
}

int main(){
    AppsInitializer();
}