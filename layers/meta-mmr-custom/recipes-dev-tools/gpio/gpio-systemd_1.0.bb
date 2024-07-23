LICENSE="CLOSED"

FILEEXTRAPATHS:prepend = "${THISDIR}/files:"

SCR_URI = "file://gpio_enable.sh \
            file://gpio.service"

inherit update-rc.d systemd

SYSTEMD_PACKAGES = "${PN}"
INITSCRIPT_PACKAGES = "${PN}"

SYSTEMD_SERVICE_${PN} = "gpio.service"


do_install(){
    install -d ${D}${libexecdir}
    install -m 0755 ${WORKDIR}/gpio_enable.sh ${D}{libexecdir}/gpio_enable.sh
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/gpio.service ${D}${systemd_system_unitdir}
    
}

FILES_${PN} += "${libexecdir}/gpio_enable.sh"
FILES_${PN} += "${systemd_system_unitdir}/gpio.service"

