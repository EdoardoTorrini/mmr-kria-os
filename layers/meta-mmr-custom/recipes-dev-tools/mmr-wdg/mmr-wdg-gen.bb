SUMMARY = "mmr-wdg"
DESCRIPTION = "Watchdog generator that keeps supervisor's WDO alive (see SDC control circuit)"
LICENSE = "CLOSED"

SRC_URI = "file://mmr_wdg_gen.sh"

RDEPENDS:${PN} = "bash"

do_install() {
    install -d ${D}${bindir}
    install -m 755 ${WORKDIR}/mmr_wdg_gen.sh ${D}${bindir}/mmr_wdg_gen
}

FILES:${PN} = "${bindir}/mmr_wdg_gen"