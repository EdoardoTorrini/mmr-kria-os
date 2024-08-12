LICENSE = "CLOSED"
SRC_URI = "file://can_enable.sh \
           file://can_enable.service"

inherit systemd

RDEPENDS:${PN} += "bash"

SYSTEMD_SERVICE:${PN} = "can_enable.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/can_enable.sh ${D}${bindir}/can_enable.sh
    
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/can_enable.service ${D}${systemd_system_unitdir}/can_enable.service
}

ROOTFS_POSTPROCESS_COMMAND = "systemctl enable gpio.service"

# Include all installed files in the package
FILES:${PN} += "${bindir}/can_enable.sh"
FILES:${PN} += "${systemd_system_unitdir}/can_enable.service"
