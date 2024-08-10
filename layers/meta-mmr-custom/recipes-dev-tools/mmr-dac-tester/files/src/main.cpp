#include <iostream>
#include <mcp4921.hpp>

static inline uint16_t voltage_to_value(double v, uint16_t max_value, double vref) {
  v = std::clamp(v, 0.0, vref);
  double value_f = max_value * v / vref;
  int value = std::clamp<int>(static_cast<int>(value_f), 0, max_value);
  return static_cast<uint16_t>(value);
}

int main() {
  MCP4921 device("/dev/spidev1.0", 50000);

  while (true) {
    double ratio = 0.0;
    std::cout << "Percentage (0-1) of full voltage range: " << std::flush;
    std::cin >> ratio;
    ratio = std::clamp(ratio, 0.0, 1.0);

    uint16_t dac = voltage_to_value(ratio, MCP4921::MAX_VALUE, 1.0);
    std::cout << "DAC " << dac << std::endl;

    device.write(dac);
  }
}