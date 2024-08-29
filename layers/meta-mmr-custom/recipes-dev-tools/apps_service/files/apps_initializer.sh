#!/bin/bash

while true
do
    A=$(pgrep control_node)
    if [ -n "$A" ]; then
        break
    else
        bash -c "/usr/bin/apps_initializer" > /tmp/log.txt
    fi
done