# mmr-kria-os
`mmr-kria-os` is an OpenEmbedded-based operating system which addresses the challenges of packaging a Linux distribution for an asymmetric MP platform, such as Kria KR260, in the automotive environment.

## Setup
 1. Download this repo with related submodules: `git clone --recurse-submodules -j12 https://github.com/EdoardoTorrini/mmr-kria-os`
 2. Edit `DL_DIR`, `SSTATE_DIR`, `TMPDIR`, `HDF_PATH` in _local.conf_
 3. Source BitBake environment: `source setupsdk`
 4. Build as much recipes as possible with: `bitbake -k kria-image-full-cmdline`
 5. Fix failed recipes
