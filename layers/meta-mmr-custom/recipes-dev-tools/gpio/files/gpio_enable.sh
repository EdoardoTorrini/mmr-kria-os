#!/bin/bash

$(echo 489 | sudo tee /sys/class/gpio/export) 2>/dev/null	# BUZZER; PIN 11
$(echo out | sudo tee /sys/class/gpio/gpio489/direction) 2>/dev/null


$(echo 490 | sudo tee /sys/class/gpio/export) 2>/dev/null	 # WATCHDOG; PIN 13 
$(echo out | sudo tee /sys/class/gpio/gpio490/direction) 2>/dev/null

$(echo 491 | sudo tee /sys/class/gpio/export) 2>/dev/null	 # ASSIB; PIN 15
$(echo out | sudo tee /sys/class/gpio/gpio491/direction) 2>/dev/null 

$(echo 492 | sudo tee /sys/class/gpio/export) 2>/dev/null	# ASSIY; PIN 29
$(echo out | sudo tee /sys/class/gpio/gpio492/direction) 2>/dev/null

$(echo 493 | sudo tee /sys/class/gpio/export) 2>/dev/null 	# EBS_1; PIN 31
$(echo out | sudo tee /sys/class/gpio/gpio493/direction) 2>/dev/null

$(echo 494 | sudo tee /sys/class/gpio/export) 2>/dev/null 	# EBS_2; PIN 37
$(echo out | sudo tee /sys/class/gpio/gpio494/direction) 2>/dev/null

$(echo 495 | sudo tee /sys/class/gpio/export) 2>/dev/null 	# SDC_CTRL; PIN 22
$(echo out | sudo tee /sys/class/gpio/gpio495/direction) 2>/dev/null

$(echo 496 | sudo tee /sys/class/gpio/export) 2>/dev/null	# ASMS; PIN 16
$(echo in | sudo tee /sys/class/gpio/gpio496/direction) 2>/dev/null

$(echo 497 | sudo tee /sys/class/gpio/export) 2>/dev/null	# SDC_SENS; PIN 18 
$(echo in | sudo tee /sys/class/gpio/gpio497/direction) 2>/dev/null


