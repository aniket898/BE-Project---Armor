VERSION = 2
PATCHLEVEL = 6
SUBLEVEL = 29
EXTRAVERSION =
obj-m += hijack.o

KDIR = /home/aniket/beproject/htconev/android/goldfish
PWD := $(shell pwd)
all:
	make -C $(KDIR) ARCH=arm CROSS_COMPILE=/usr/local/Codesourcery/bin/arm-none-linux-gnueabi- SUBDIRS=$(PWD) modules
clean:
	make -C $(KDIR) ARCH=arm CROSS_COMPILE=/usr/local/Codesourcery/bin/arm-none-linux-gnueabi- SUBDIRS=$(PWD) clean
