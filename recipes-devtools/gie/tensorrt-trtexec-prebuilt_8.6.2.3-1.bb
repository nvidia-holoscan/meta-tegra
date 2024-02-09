DESCRIPTION = "NVIDIA TensorRT Prebuilt Plugins for deep learning"
LICENSE = "Proprietary"

inherit l4t_deb_pkgfeed features_check

HOMEPAGE = "http://developer.nvidia.com/tensorrt"

L4T_DEB_GROUP = "tensorrt"

SRC_COMMON_DEBS = "\
    libnvinfer-bin_${PV}%2Bcuda12.2_arm64.deb;downloadfilename=libnvinfer-bin_${PV}+cuda12.2_arm64.deb;name=bin;subdir=tensorrt \
"

BINSHA256SUM = "b3f13e81d91315cf2904624ebec45870dccb779a6c1678aa64252507b83f6e5e"

SRC_URI[bin.sha256sum] = "${BINSHA256SUM}"

COMPATIBLE_MACHINE = "(tegra)"

REQUIRED_DISTRO_FEATURES = "opengl"

LIC_FILES_CHKSUM = "file://usr/share/doc/libnvinfer-bin/copyright;md5=32ccc6a9bbc79616807b9bc252844b2f"

S = "${WORKDIR}/tensorrt"

DEPENDS = "cuda-cudart cudnn tensorrt-core tensorrt-plugins libcublas"

do_configure() {
    :
}

do_compile() {
    :
}

do_install() {
    install -d ${D}${prefix}/src
    cp --preserve=mode,timestamps --recursive ${S}/usr/src/tensorrt ${D}${prefix}/src/
}

FILES:${PN} = "${prefix}/src/tensorrt/bin"

PROVIDES = "tensorrt-trtexec"
RPROVIDES:${PN} = "tensorrt-trtexec"
RCONFLICTS:${PN} = "tensorrt-trtexec"
RPROVIDES:${PN}-dev = "tensorrt-trtexec-dev"
RCONFLICTS:${PN}-dev = "tensorrt-trtexec-dev"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_SYSROOT_STRIP = "1"

PACKAGE_ARCH = "${TEGRA_PKGARCH}"
