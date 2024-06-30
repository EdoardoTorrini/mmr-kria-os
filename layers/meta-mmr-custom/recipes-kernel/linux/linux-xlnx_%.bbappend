# This recipe enables userland spidev in kernel's defconfig

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://custom_defconfig"

KBUILD_DEFCONFIG:zynqmp := "custom_defconfig"

do_kernel_metadata:prepend () {
    cp ${WORKDIR}/${KBUILD_DEFCONFIG} ${S}/arch/${ARCH}/configs/${KBUILD_DEFCONFIG}
}