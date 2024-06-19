hsi open_hw_design /home/francesco/Documents/mmr-kria-os/platform/kria_hw.xsa
hsi set_repo_path /home/francesco/Documents/mmr-kria-os/layers/meta-kria/recipes-firmware/device-tree-xlnx/
hsi create_sw_design device-tree -os device_tree -proc psu_cortexa53_0
hsi set_property CONFIG.dt_overlay true [hsi::get_os]
# hsi set_property CONFIG.dt_zocl true [hsi get_os]
hsi generate_target -dir /home/francesco/Documents/mmr-kria-os/layers/meta-kria/recipes-firmware/mmr-firmware/mmr-firmware.dtsi