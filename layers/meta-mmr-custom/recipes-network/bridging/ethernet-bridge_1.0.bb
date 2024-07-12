LICENSE="CLOSED"
sdf
FILEEXTRAPATHS:prepend = "${THISDIR}/files:"
SRC_URI = "file://networkd/"

do_install:append () {
  install -d /etc/systemd/network
  install ${WORKDIR}/networkd/* /etc/systemd/network
}
