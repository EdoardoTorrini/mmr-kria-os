SUMMARY = "mmr-dac-tester"
DESCRIPTION = "Test application made for dac"
LICENSE = "CLOSED"

DEPENDS = ""
SRC_URI = "file://CMakeLists.txt \
    file://src/ \
    file://include/"
 
S = "${WORKDIR}"

inherit cmake

EXTRA_OECMAKE = ""

do_install(){
    install -d ${D}${bindir}
    install -m 755 dac_test ${D}${bindir}
}