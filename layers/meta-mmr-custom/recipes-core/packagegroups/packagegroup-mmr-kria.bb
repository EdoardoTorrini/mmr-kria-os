DESCRIPTION = "Kria SOM related packages"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:kria = "${MACHINE}"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

KRIA_PACKAGES = " \
        packagegroup-core-full-cmdline \
        packagegroup-core-ssh-openssh \
        packagegroup-petalinux-networking-stack \
        packagegroup-petalinux-tpm \
        packagegroup-petalinux-utils \
        packagegroup-petalinux \
        archconfig \
        image-update \
        ldd \
        ntp \
        resize-part \
        tree \
        less \
        vim \
        spidev-test \
        tzdata \
        xmutil \
        kria-dashboard \
        lmsensors-fancontrol \
        ${KRIA_ARCH_PACKAGES} \
"

KRIA_ARCH_PACKAGES = ""
KRIA_ARCH_PACKAGES:k26-smk = "k26-starter-kits"
KRIA_ARCH_PACKAGES:k24-smk = "k24-starter-kits"

RDEPENDS:${PN} = "${KRIA_PACKAGES}"
