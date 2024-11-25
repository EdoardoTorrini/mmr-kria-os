require mmr-kria-minimal.bb

RACE_PACKETS = " \
    mmr-boot \
    mmr-data-logger \
    mmr-as-manager \
    apps-initializer \
    apps-systemd \
"

TEST_PACKETS = " \
    rt-tests \
    spidev-test \
    mmr-dac-tester \
    mmr-dac-sin \
    mmr-wdg-gen \
    lmsensors \ 
"

IMAGE_INSTALL:append = " \
    ${RACE_PACKETS} \
    ${TEST_PACKETS} \
"