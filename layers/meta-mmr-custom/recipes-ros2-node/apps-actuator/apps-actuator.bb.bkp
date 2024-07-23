inherit ros_distro_${ROS_DISTRO}
inherit ros_superflore_generated

DESCRIPTION = "Apps Actuator for Kria KR260 - MMR"
AUTHOR = "Edoardo Torrini <edoardo.torrini@gmail.com>"
ROS_AUTHOR = "Edoardo Torrini <edoardo.torrini@gmail.com>"
HOMEPAGE = "https://wiki.ros.org"
SECTION = "devel"

LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

ROS_CN = "demos"
ROS_BPN = "apps_actuator"

ROS_BUILD_DEPENDS = " \
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

# matches with: https://github.com/ros2-gbp/demos-release/archive/release/humble/demo_nodes_cpp/0.33.2-1.tar.gz
ROS_BRANCH ?= "branch=master"
SRC_URI = "git://git@github.com/EdoardoTorrini/apps_actuator.git;${ROS_BRANCH};protocol=ssh"
SRCREV = "c35676fc601f68c4838ac729028335714b1d7d1b"
S = "${WORKDIR}/git/apps_actuator"

ROS_BUILD_TYPE = "ament_cmake"

inherit ros_${ROS_BUILD_TYPE}