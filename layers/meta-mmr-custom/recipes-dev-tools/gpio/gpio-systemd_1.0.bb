LICENSE="CLOSED"

SRC_URI = "file://gpio_enable.sh \
           file://gpio.service"

inherit systemd


RDEPENDS:${PN} += "bash"

SYSTEMD_SERVICE_${PN} = "gpio.service"
SYSTEMD_AUTO_ENABLE = "enable"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/gpio_enable.sh ${D}${bindir}/gpio_enable.sh

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/gpio.service ${D}${systemd_system_unitdir}/gpio.service
}

FILES:${PN} += "${bindir}/gpio_enable.sh"
FILES:${PN} += "${systemd_system_unitdir}/gpio.service"
