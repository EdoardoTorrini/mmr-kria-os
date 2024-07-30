inherit ros_distro_humble
inherit ros_superflore_generated

DESCRIPTION = "Build mmr-kria-base of mmr-driverless/mmr-kria-drive "
AUTHOR = "Cirillo Cristian <cirillocristianpio02@gmail.com>"
ROS_AUTHOR = "Cirillo Cristian <cirillocristianpio02@gmail.com>"
HOMEPAGE = "https://wiki.ros.org"
SECTION = "devel"

LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

ROS_CN = "0_common"
ROS_BPN = "mmr_edf"

ROS_BUILD_DEPENDS = " \
    rcl \
    rcl-interfaces \
    rclcpp \
    rcpputils \
    std-msgs \
    mmr-kria-msgs \
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
"

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS:${PN} += "${ROS_EXEC_DEPENDS}"

ROS_BRANCH ?= "branch=dev_yocto"
SRC_URI = "git://git@github.com/mmr-driverless/mmr-kria-drive.git;${ROS_BRANCH};protocol=ssh"
SRCREV = "2569a174d15b47ecb0f78006702c4bb9fbf2ff79"
S = "${WORKDIR}/git/src/0_common/mmr_edf"

ROS_BUILD_TYPE = "ament_cmake"

inherit ros_${ROS_BUILD_TYPE}
