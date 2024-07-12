# This recipe enables userland spidev in kernel's defconfig

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

KBUILD_DEFCONFIG:zynqmp := "custom_defconfig"
RT_PATCH = "001-linux-rt-kernel.patch"

SRC_URI += " \
  file://${KBUILD_DEFCONFIG} \
  file://${RT_PATCH};apply=no \
"

do_kernel_metadata:prepend () {
  cp ${WORKDIR}/${KBUILD_DEFCONFIG} ${S}/arch/${ARCH}/configs/${KBUILD_DEFCONFIG}
}

do_rt_patch () {
  cd ${STAGING_KERNEL_DIR}
  git apply --reject /home/francesco/Documents/mmr-kria-os/layers/meta-mmr-custom/recipes-kernel/linux/files/001-linux-rt-kernel.patch || :
}

addtask do_rt_patch before do_patch after do_kernel_metadata