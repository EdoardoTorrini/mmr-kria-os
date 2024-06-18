# mmr-kria-os
`mmr-kria-os` is an OpenEmbedded-based operating system which addresses the challenges of packaging a Linux distribution for an asymmetric MP platform, such as Kria KR260, in the automotive environment.

## Setup
 1. Install host dependencies: [https://docs.yoctoproject.org/ref-manual/system-requirements.html#required-packages-for-the-build-host](https://docs.yoctoproject.org/ref-manual/system-requirements.html#required-packages-for-the-build-host)
 2. Download this repo with related submodules: `git clone --recurse-submodules -j12 https://github.com/EdoardoTorrini/mmr-kria-os`
 3. Edit `DL_DIR`, `SSTATE_DIR`, `TMPDIR`, `HDF_PATH` in _local.conf_
 4. Source BitBake environment: `source setupsdk`
 5. Build as much recipes as possible with: `bitbake -k kria-image-full-cmdline`
 6. Fix failed recipes
