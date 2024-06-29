FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SPIDEV_DTSI ?= "spidev.dtsi"
 
SRC_URI += "file://${SPIDEV_DTSI}"
 
do_configure:append() {
        cp ${WORKDIR}/${SPIDEV_DTSI} ${B}/device-tree
        echo "/include/ \"${SPIDEV_DTSI}\"" >> ${B}/device-tree/system-top.dts
}