inherit ros_distro_humble
inherit ros_superflore_generated

DESCRIPTION = "Build mmr-kria-base of mmr-driverless/mmr-kria-drive "
AUTHOR = "Cirillo Cristian <cirillocristianpio02@gmail.com>"
ROS_AUTHOR = "Cirillo Cristian <cirillocristianpio02@gmail.com>"
HOMEPAGE = "https://wiki.ros.org"
SECTION = "devel"

LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

ROS_CN = "mmr-kria-drive"
ROS_BPN = "mmr-kria-drive"

ROS_BUILD_DEPENDS = " \
    rosidl-default-generators\
    rcl \
    rcl-interfaces \
    rclcpp \
    rcpputils \
    std-msgs \
"

ROS_BUILDTOOL_DEPENDS = " \
    ament-cmake-native \
"

ROS_EXPORT_DEPENDS = ""

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    ament-cmake-ros \
    rosidl-default-runtime \
    rcl \
    rcl-interfaces \
    rclcpp \
    rcpputils \
    std-msgs \
"

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS:${PN} += "${ROS_EXEC_DEPENDS}"

ROS_BRANCH ?= "branch=master"
SRC_URI = "git://git@github.com/mmr-driverless/mmr-kria-drive.git;${ROS_BRANCH};protocol=ssh"
SRCREV = "96e8e023360d3263e640d6bdfaf396ae7b3d8d15"
S = "${WORKDIR}/git/src/0_common/mmr_kria_base"

ROS_BUILD_TYPE = "ament_cmake"

inherit ros_${ROS_BUILD_TYPE}

# do_configure:prepend() {
#     export PYTHONPATH="/home/cristian/.local/lib/python3.10/site-packages:/home/cristian/.local/bin:/home/cristian/Desktop/formula/mmr-kria-os/repos/poky/bitbake/lib:/opt/ros/humble/lib/python3.10/site-packages:/opt/ros/humble/local/lib/python3.10/dist-packages:/home/cristian/Desktop/formula/mmr-kria-os/repos/poky/bitbake/lib:/opt/ros/humble/lib/python3.10/site-packages:/opt/ros/humble/local/lib/python3.10/dist-packages"
# }
