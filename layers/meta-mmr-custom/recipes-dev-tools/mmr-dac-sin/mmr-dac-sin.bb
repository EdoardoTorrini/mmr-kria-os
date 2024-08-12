SUMMARY = "mmr-dac-sin"
DESCRIPTION = "Test APS DAC by sending a sin wave"
LICENSE = "CLOSED"

DEPENDS = ""
SRC_URI = "file://mmr_dac_sin.py"
 
S = "${WORKDIR}"

inherit cmake

EXTRA_OECMAKE = ""

do_install(){
    install -d ${D}${bindir}
    install -m 755 mmr_dac_sin.py ${D}${bindir}/mmr_dac_sin
}