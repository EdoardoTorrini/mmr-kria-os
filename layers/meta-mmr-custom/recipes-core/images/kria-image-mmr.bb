DESCRIPTION = "A fully featured console-only image for Kria SOM."

inherit core-image
IMAGE_CLASSES += "kria-image"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:kria = "${MACHINE}"

IMAGE_FEATURES += "splash ssh-server-openssh hwcodecs package-management"
EXTRA_IMAGE_FEATURES = "tools-sdk tools-debug"

MMR_ROS_NODE = " \
    login-shell-profile \
    mmr-boot \
    mmr-kria-msgs \
    mmr-edf \
    mmr-canbus-bridge \
    mmr-canopen-bridge \
    mmr-pure-pursuit \
"

IMAGE_INSTALL = " \
    packagegroup-core-boot \
    packagegroup-petalinux-kria \
    kernel-modules \
    nfs-utils \
    nfs-utils-client \
    u-boot-tools \
    linux-xlnx-udev-rules \
    ${CORE_IMAGE_EXTRA_INSTALL} \
    udev-extraconf \
    spidev-test \
    mmr-dac-tester \
    tmux \
    rt-tests \
    ros-core \
    rosidl-adapter \
    ${MMR_ROS_NODE} \
"
