LICENSE="CLOSED"

SRC_URI = "file://apps_initializer.sh \
           file://apps_initializer.service"

inherit systemd


RDEPENDS:${PN} += "bash"

SYSTEMD_SERVICE:${PN} = "apps_initializer.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"


do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/apps_initializer.sh ${D}${bindir}/apps_initializer.sh

    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/apps_initializer.service ${D}${systemd_unitdir}/system/apps_initializer.service
}

# Automatic enable

FILES:${PN} += "${bindir}/apps_initializer.sh"
FILES:${PN} += "${systemd_system_unitdir}/apps_initializer.service"
