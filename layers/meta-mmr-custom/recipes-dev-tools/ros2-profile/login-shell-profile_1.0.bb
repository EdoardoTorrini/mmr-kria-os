LICENSE="CLOSED"
DESCRIPTION="Source ROS2 environment in login shell"

SRC_URI = "file://.profile"

do_install() {
    install -d ${D}${ROOT_HOME}
    install -m 0420 ${WORKDIR}/.profile ${D}${ROOT_HOME}/.profile
}

FILES:${PN} += "${ROOT_HOME}/.profile"