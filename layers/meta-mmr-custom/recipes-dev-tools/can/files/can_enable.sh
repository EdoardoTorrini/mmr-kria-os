#!/bin/bash

ip link set can0 up type can bitrate 1000000
# ip link set can0 txqueuelen 1000

ip link set can1 up type can bitrate 1000000 
# ip link set can1 txqueuelen 1000

