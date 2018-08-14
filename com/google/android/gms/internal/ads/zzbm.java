package com.google.android.gms.internal.ads;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

final class zzbm implements Runnable {
    private zzbm() {
    }

    public final void run() {
        try {
            zzbk.zzhz = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
        } finally {
            zzbk.zzic.countDown();
        }
    }
}
