SUMMARY = "mmr-dac-sin"
DESCRIPTION = "Test APS DAC by sending a sin wave"
LICENSE = "CLOSED"

SRC_URI = "file://mmr_dac_sin.py"

RDEPENDS:${PN} = "python3-core"

do_install(){
    install -d ${D}${bindir}
    install -m 755 ${WORKDIR}/mmr_dac_sin.py ${D}${bindir}/mmr_dac_sin
}

FILES:${PN} = "${bindir}/mmr_dac_sin"