require mmr-kria-minimal.bb

TEST_PACKETS = " \
    rt-tests \
    spidev-test \
    mmr-dac-tester \
    mmr-dac-sin \
    mmr-wdg-gen \
    lmsensors \ 
"

IMAGE_INSTALL:append = " \
    ${TEST_PACKETS} \
"