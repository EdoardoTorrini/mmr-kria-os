#!/usr/bin/bash

while [[ 1 ]]; do
  sleep 0.1;
  echo 0 > /sys/class/gpio/gpio490/value;
  sleep 0.1;
  echo 1 > /sys/class/gpio/gpio490/value;
done
