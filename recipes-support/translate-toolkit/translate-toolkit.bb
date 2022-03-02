SUMMARY = "Tools to assist with translation and software localization"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=98efdc0d236de427ac1ff87d5108bd36"
HOMEPAGE = "http://toolkit.translatehouse.org/"

inherit setuptools3 pypi
PV = "3.6.0"
SRC_URI[sha256sum] = "dfdb19383920948e5bc1dafacb994ee07f8d6ecc053cd6e2b4c545ce0430ddff"

do_install:append() {
    # ${D}${PYTHON_SITEPACKAGES_DIR}${STAGING_DIR_HOST}${PYTHON_SITEPACKAGES_DIR} -> ${D}${PYTHON_SITEPACKAGES_DIR}
    # this is a bad hack aligning installed locations - but have not found a better way
    if [ -d ${D}${PYTHON_SITEPACKAGES_DIR}${STAGING_DIR_HOST}${PYTHON_SITEPACKAGES_DIR} ] ; then
        echo
        echo "Copy files from ${D}${PYTHON_SITEPACKAGES_DIR}${STAGING_DIR_HOST}${PYTHON_SITEPACKAGES_DIR} -> ${D}${PYTHON_SITEPACKAGES_DIR}"
        cp -r ${D}${PYTHON_SITEPACKAGES_DIR}${STAGING_DIR_HOST}/* ${D}
        staging_base=`echo ${STAGING_DIR_HOST} | cut -d "/" -f2`
        echo "Delete leftover ${PYTHON_SITEPACKAGES_DIR}/$staging_base"
        rm -r ${D}/${PYTHON_SITEPACKAGES_DIR}/$staging_base
    fi
}

RDEPENDS:${PN} += "bash python3-core"
