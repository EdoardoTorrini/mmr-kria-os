require mmr-kria-minimal.bb

RACE_PACKETS = " \
    mmr-boot \
    mmr-data-logger \
    mmr-as-manager \
    apps-initializer \
    apps-systemd \
"

IMAGE_INSTALL:append = " \
    ${RACE_PACKETS} \
"