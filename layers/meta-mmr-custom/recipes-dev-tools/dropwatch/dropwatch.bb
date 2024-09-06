SUMMARY = "dropwatch"
DESCRIPTION = "Tool needed to get some metrics about dropped packages"
LICENSE = "CLOSED"

SRC_URI="git://github.com/nhorman/dropwatch.git;branch=master;protocol=https"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

DEPENDS = "binutils libnl libpcap readline"

inherit pkgconfig autotools