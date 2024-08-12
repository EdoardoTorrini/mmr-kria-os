inherit ros_distro_humble
inherit ros_superflore_generated

DESCRIPTION = "Build canopen_bridge of mmr-driverless/mmr-kria-drive "
AUTHOR = "Edoardo Torrini <edoardo.torrini@gmail.com>"
ROS_AUTHOR = "Edoardo Torrini <edoardo.torrini@gmail.com>"
HOMEPAGE = "https://wiki.ros.org"
SECTION = "devel"

LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

ROS_CN = "1_actuation"
ROS_BPN = "canopen_bridge"

ROS_BUILD_DEPENDS = " \
    rcl \
    rcl-interfaces \
    rclcpp \
    rcpputils \
    std-msgs \
    mmr-kria-msgs \
    can-msgs \
    mmr-edf \
"

ROS_BUILDTOOL_DEPENDS = " \
    ament-cmake-native \
"

ROS_EXPORT_DEPENDS = ""

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    rcl \
    rcl-interfaces \
    rclcpp \
    rcpputils \
    std-msgs \
    mmr-kria-msgs \
    can-msgs \
    mmr-edf \
"

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS:${PN} += "${ROS_EXEC_DEPENDS}"

ROS_BRANCH ?= "branch=master"
SRC_URI = "git://git@github.com/mmr-driverless/mmr-kria-drive.git;${ROS_BRANCH};protocol=ssh"
SRCREV = "${AUTOREV}"
S = "${WORKDIR}/git/src/1_actuation/canopen_bridge"

ROS_BUILD_TYPE = "ament_cmake"

inherit ros_${ROS_BUILD_TYPE}