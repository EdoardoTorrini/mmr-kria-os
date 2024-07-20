LICENSE="CLOSED"

FILEEXTRAPATHS:prepend = "${THISDIR}/files:"
SRC_URI = "file://networkd"

do_install() {
  install -d ${D}${sysconfdir}/systemd/network
  install ${WORKDIR}/networkd/* ${D}${sysconfdir}/systemd/network
}

FILES:${PN} += "${sysconfdir}/systemd/network"
