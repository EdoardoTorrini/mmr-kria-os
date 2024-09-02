#!/bin/bash

usage() {
    echo "Usage: $0 <patition_name> <partition_number>"
    echo "Example $0 /dev/sda 2"
    exit 1
}

if [ "$#" -ne 2 ]; then
    echo "Errore: numero di parametri errato."
    usage
fi

if ! type "growpart" > /dev/null; then
  echo "[INFO] growpart not found, proceding to instsall"
  sudo apt install cloud-guest-utils -y 
fi

sudo growpart $1 $2
