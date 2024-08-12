LICENSE="CLOSED"

SRC_URI = "file://mmr_boot.sh \
           file://mmr_boot.service"

inherit systemd


RDEPENDS:${PN} += "bash"

SYSTEMD_SERVICE:${PN} = "mmr_boot.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"


do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/mmr_boot.sh ${D}${bindir}/mmr_boot.sh

    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/mmr_boot.service ${D}${systemd_unitdir}/system/mmr_boot.service
}

# Autmatic enable

FILES:${PN} += "${bindir}/mmr_boot.sh"
FILES:${PN} += "${systemd_system_unitdir}/mmr_boot.service"
