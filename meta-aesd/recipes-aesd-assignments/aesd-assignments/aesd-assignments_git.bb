# See https://git.yoctoproject.org/poky/tree/meta/files/common-licenses
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit update-rc.d
SRC_URI = "git://git@github.com/cu-ecen-aeld/assignments-3-and-later-rafaelcalai.git;protocol=ssh;branch=master"


PV = "1.0+git${SRCPV}"
SRCREV = "c5fa3c1eae10f6ddfcf42eed9f16f6a41d0b6518"


INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME:${PN} = "aesdsocket-start-stop"

S = "${WORKDIR}/git/server"


FILES:${PN} += "/etc/init.d/*"


TARGET_LDFLAGS += "-pthread -lrt -lm"

do_configure () {
	:
}

do_compile () {
	oe_runmake
}

do_install () {
	install -d ${D}${bindir}
	install -m 0755 ${S}/aesdsocket ${D}${bindir}/
	install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${S}/aesdsocket-start-stop ${D}${sysconfdir}/init.d
}



