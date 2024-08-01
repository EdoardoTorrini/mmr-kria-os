inherit ros_distro_humble
inherit ros_superflore_generated

DESCRIPTION = "Build mmr-kria-base of mmr-driverless/mmr-kria-drive"
AUTHOR = "Edoardo Torrini <edoardo.torrini@gmail.com>"
ROS_AUTHOR = "Edoardo Torrini <edoardo.torrini@gmail.com>"

SECTION = "devel"

LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

ROS_CN = "0_common"
ROS_BPN = "mmr_kria_base"

ROS_BUILD_DEPENDS = " \
    builtin-interfaces \
    std-msgs \
    geometry-msgs \
    sensor-msgs \
"

ROS_BUILDTOOL_DEPENDS = " \
    ament-cmake-native \
    rosidl-default-generators-native \
"

ROS_EXPORT_DEPENDS = " \
    builtin-interfaces \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    builtin-interfaces \
    rosidl-default-runtime \
    std-msgs \
    geometry-msgs \
    sensor-msgs \
"

ROS_TEST_DEPENDS = " \
    ament-lint-common \
"

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS:${PN} += "${ROS_EXEC_DEPENDS}"

ROS_BRANCH ?= "branch=dev_clutch"
SRC_URI = "git://git@github.com/mmr-driverless/mmr-kria-drive.git;${ROS_BRANCH};protocol=ssh"
SRCREV = "${AUTOREV}"
S = "${WORKDIR}/git/src/0_common/mmr_kria_base"

ROS_BUILD_TYPE = "ament_cmake"

inherit ros_${ROS_BUILD_TYPE}
