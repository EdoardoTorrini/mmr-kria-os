SUMMARY = "apps-initializer"
LICENSE = "CLOSED"

DEPENDS = ""
SRC_URI = "file://CMakeLists.txt \
    file://src/ \
    file://include/ \
    file://config/ \
    "
 
S = "${WORKDIR}"

inherit cmake

EXTRA_OECMAKE = ""


do_install(){
    install -d ${D}${bindir}
    install -m 755 apps_initializer ${D}${bindir}
}
