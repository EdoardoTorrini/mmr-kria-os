#!/bin/bash
cd /home/cristian/Desktop/formula/mmr-kria-os/build/tmp/deploy/images/k26-smk-kr 

sudo umount /dev/sda 2>/dev/null
sudo umount /dev/sda1 2>/dev/null
sudo umount /dev/sda2 2>/dev/null

sudo parted -s /dev/sda mklabel gpt
sudo parted -s /dev/sda mkpart primary fat32 1MiB 513MiB
sudo parted -s /dev/sda set 1 boot on
sudo parted -s /dev/sda mkpart primary ext4 513MiB 100%
sudo mkfs.fat -F32 /dev/sda1
sudo mkfs.ext4 /dev/sda2

sudo mount /dev/sda1 /mnt
sudo cp boot.bin /mnt/BOOT.bin
sudo cp boot.scr /mnt/
sudo cp -r devicetree/ /mnt/
sudo cp Image /mnt/
sudo cp Image--* /mnt/
sudo cp petalinux-initramfs-image-k26-smk-kr.rootfs.cpio.gz.u-boot /mnt/   
sudo cp -r pxeboot/ /mnt/
sudo cp -r pxelinux.cfg/ /mnt  
sudo cp  system.dtb /mnt
sudo cp u-boot-xlnx-scr-* /mnt/    
sudo umount /dev/sda1
sudo mount /dev/sda2 /mnt
sudo tar -xvf kria-image-mmr-k26-smk-kr.rootfs-*.tar.gz -C /mnt 
sudo umount /dev/sda2
