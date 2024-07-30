inherit ros_distro_humble
inherit ros_superflore_generated

DESCRIPTION = "A package containing some standard message definitions."
AUTHOR = "Edoardo Torrini <geoff@openrobotics.org>"
ROS_AUTHOR = "Dirk Thomas <dthomas@osrfoundation.org>"
HOMEPAGE = "https://wiki.ros.org"
SECTION = "devel"

LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

ROS_CN = "0_common"
ROS_BPN = "mmr_kria_base"

ROS_BUILD_DEPENDS = " \
    builtin-interfaces \
    std-msgs \
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
"

ROS_TEST_DEPENDS = " \
    ament-lint-common \
"

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS:${PN} += "${ROS_EXEC_DEPENDS}"

ROS_BRANCH ?= "branch=dev_yocto"
SRC_URI = "git://git@github.com/mmr-driverless/mmr-kria-drive.git;${ROS_BRANCH};protocol=ssh"
SRCREV = "2569a174d15b47ecb0f78006702c4bb9fbf2ff79"
S = "${WORKDIR}/git/src/0_common/mmr_kria_base"

ROS_BUILD_TYPE = "ament_cmake"

inherit ros_${ROS_BUILD_TYPE}
