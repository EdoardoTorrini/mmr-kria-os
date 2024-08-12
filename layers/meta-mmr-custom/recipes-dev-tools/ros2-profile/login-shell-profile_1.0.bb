LICENSE="CLOSED"
DESCRIPTION="Source ROS2 environment in login shell"

do_install() {
    install -d ${D}${ROOT_HOME}
    echo "source /etc/profile.d/ros/setup.bash" >> ${D}${ROOT_HOME}/.profile
}

FILES:${PN} += "${ROOT_HOME}/.profile"