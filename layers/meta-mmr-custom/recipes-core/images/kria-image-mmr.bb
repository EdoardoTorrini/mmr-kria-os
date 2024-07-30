DESCRIPTION = "A full featured console-only image for Kria SOM."

inherit core-image
IMAGE_CLASSES += "kria-image"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:kria = "${MACHINE}"

IMAGE_FEATURES += "splash ssh-server-openssh hwcodecs package-management"
EXTRA_IMAGE_FEATURES = "tools-sdk tools-debug"

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
    tmux \
    rt-tests \
    ros-core \
    rosidl-adapter \
    mmr-kria-drive \
"
