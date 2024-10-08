# mmr-kria-os
`mmr-kria-os` is an OpenEmbedded-based operating system which addresses the challenges of packaging a Linux distribution for an asymmetric MP platform, such as Kria KR260, in the automotive environment.

## Setup
 1. Install host dependencies: [https://docs.yoctoproject.org/ref-manual/system-requirements.html#required-packages-for-the-build-host](https://docs.yoctoproject.org/ref-manual/system-requirements.html#required-packages-for-the-build-host)
 2. Download this repo with related submodules: `git clone --recurse-submodules -j12 https://github.com/EdoardoTorrini/mmr-kria-os`
 3. Install extra dependencies by running: `sudo apt install < apt_dependencies.txt`
 4. Edit `DL_DIR`, `SSTATE_DIR`, `TMPDIR`, `HDF_PATH` in _local.conf_ if necessary. These parameters will allow you to place _downloads_, _sstate_ and _tmp_ dirs in a folder different from _build_ (the default one)
 5. Source BitBake environment: `source setupsdk`
 6. Build as much recipes as possible with: `bitbake -k kria-image-mmr`
 7. Fix failed recipes

## Convention-over-configuration
The default location of hardware description file, in the form of XSA file, is _platform/platform.xsa_, according to the default configuration of _local.conf_. Replace this file to change hardware description.

You may need to run `bitbake -c cleanall pmu-firmware fsbl-firmware` after it.

## Build Process
`bitbake -k kria-image-mmr` is the suggested command to build kria image.

### Important notes
Run `bitbake -c cleanall pmu-firmware fsbl-firmware device-tree` whenever _platform.xsa_ changes.

## Common Issues
> **My image runs well on QEMU, but doesn't work on the real board.**
> QEMU is good at validating images up to the platform description (userspace is ok, u-boot and device tree too) but cannot simulate boundary blocks such as PMU and FPGA. So, if your design describes peripherals such as CAN controller or GPIO, make sure to upload the boot firmware (_BOOT.bin_) on PMU through `xmutil bootfw_update -i BOOT.bin`, otherwise power domain errors will arise.

> **My new platform boots on QEMU, but doesn't boot on the devboard**
> There can be several reasons. The most likely one is that you are running an image that expects a FPGA bitstream coherent with its device tree.
> Is there a bitstream (_.bit_) in your BOOT.bin? check it with `bootgen -read BOOT.bin`
> Have you uploaded the boot firmware to the PM Unit? Run `xmutil bootfw_update -i BOOT.bin`. If the current image doesn't boot, run another image, flash BOOT.bin and then try again.
