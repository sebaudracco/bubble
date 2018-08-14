package com.mopub.common.util;

class ManifestUtils$FlagCheckUtil {
    ManifestUtils$FlagCheckUtil() {
    }

    public boolean hasFlag(Class clazz, int bitMask, int flag) {
        return Utils.bitMaskContainsFlag(bitMask, flag);
    }
}
