DESCRIPTION = "Build mmr-kria-base package from mmr-kria-drive"
SECTION = "devel"
LICENSE = "CLOSED"


DEPENDS = "rosidl-default-generators \
    ament-cmake \
    geometry-msgs \
    std-msgs \
    ament-package \
    ament-cmake-libraries \
"
RDEPENDS:{PN} = "rosidl-default-runtime ament-package"

BRANCH = "branch=dev_canopen"
SRC_URI = "git://git@github.com/EdoardoTorrini/mmr-kria-drive.git;${BRANCH};protocol=ssh"
SRCREV = "2108f7ac1f62540521860da56fe93030f06dc026"

S = "${WORKDIR}/git/src/0_common/mmr_kria_base"

inherit ros_ament_cmake