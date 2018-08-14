package com.google.android.gms.internal.ads;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

final class zzbk {
    private static boolean zzhy = false;
    private static MessageDigest zzhz = null;
    private static final Object zzia = new Object();
    private static final Object zzib = new Object();
    static CountDownLatch zzic = new CountDownLatch(1);

    static String zza(zzba com_google_android_gms_internal_ads_zzba, String str) throws GeneralSecurityException, UnsupportedEncodingException {
        byte[] zza;
        byte[] zzb = zzbfi.zzb(com_google_android_gms_internal_ads_zzba);
        if (((Boolean) zzkb.zzik().zzd(zznk.zzbay)).booleanValue()) {
            Vector zza2 = zza(zzb, 255);
            if (zza2 == null || zza2.size() == 0) {
                zza = zza(zzbfi.zzb(zzc(PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM)), str, true);
            } else {
                zzbfi com_google_android_gms_internal_ads_zzbg = new zzbg();
                com_google_android_gms_internal_ads_zzbg.zzgv = new byte[zza2.size()][];
                Iterator it = zza2.iterator();
                int i = 0;
                while (it.hasNext()) {
                    int i2 = i + 1;
                    com_google_android_gms_internal_ads_zzbg.zzgv[i] = zza((byte[]) it.next(), str, false);
                    i = i2;
                }
                com_google_android_gms_internal_ads_zzbg.zzgq = zzb(zzb);
                zza = zzbfi.zzb(com_google_android_gms_internal_ads_zzbg);
            }
        } else if (zzde.zzso == null) {
            throw new GeneralSecurityException();
        } else {
            zza = zzde.zzso.zzc(zzb, str != null ? str.getBytes() : new byte[0]);
            zzbfi com_google_android_gms_internal_ads_zzbg2 = new zzbg();
            com_google_android_gms_internal_ads_zzbg2.zzgv = new byte[][]{zza};
            com_google_android_gms_internal_ads_zzbg2.zzfe = Integer.valueOf(2);
            zza = zzbfi.zzb(com_google_android_gms_internal_ads_zzbg2);
        }
        return zzbi.zza(zza, true);
    }

    private static Vector<byte[]> zza(byte[] bArr, int i) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        int length = ((bArr.length + 255) - 1) / 255;
        Vector<byte[]> vector = new Vector();
        int i2 = 0;
        while (i2 < length) {
            int i3 = i2 * 255;
            try {
                vector.add(Arrays.copyOfRange(bArr, i3, bArr.length - i3 > 255 ? i3 + 255 : bArr.length));
                i2++;
            } catch (IndexOutOfBoundsException e) {
                return null;
            }
        }
        return vector;
    }

    private static byte[] zza(byte[] bArr, String str, boolean z) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] array;
        int i = z ? 239 : 255;
        if (bArr.length > i) {
            bArr = zzbfi.zzb(zzc(PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM));
        }
        if (bArr.length < i) {
            byte[] bArr2 = new byte[(i - bArr.length)];
            new SecureRandom().nextBytes(bArr2);
            array = ByteBuffer.allocate(i + 1).put((byte) bArr.length).put(bArr).put(bArr2).array();
        } else {
            array = ByteBuffer.allocate(i + 1).put((byte) bArr.length).put(bArr).array();
        }
        if (z) {
            array = ByteBuffer.allocate(256).put(zzb(array)).put(array).array();
        }
        byte[] bArr3 = new byte[256];
        for (zzbp zza : new zzbn().zzpq) {
            zza.zza(array, bArr3);
        }
        if (str != null && str.length() > 0) {
            if (str.length() > 32) {
                str = str.substring(0, 32);
            }
            new zzazx(str.getBytes("UTF-8")).zzn(bArr3);
        }
        return bArr3;
    }

    public static byte[] zzb(byte[] bArr) throws NoSuchAlgorithmException {
        byte[] digest;
        synchronized (zzia) {
            MessageDigest zzw = zzw();
            if (zzw == null) {
                throw new NoSuchAlgorithmException("Cannot compute hash");
            }
            zzw.reset();
            zzw.update(bArr);
            digest = zzhz.digest();
        }
        return digest;
    }

    private static zzba zzc(long j) {
        zzba com_google_android_gms_internal_ads_zzba = new zzba();
        com_google_android_gms_internal_ads_zzba.zzdu = Long.valueOf(PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM);
        return com_google_android_gms_internal_ads_zzba;
    }

    static void zzv() {
        synchronized (zzib) {
            if (!zzhy) {
                zzhy = true;
                new Thread(new zzbm()).start();
            }
        }
    }

    private static MessageDigest zzw() {
        zzv();
        boolean z = false;
        try {
            z = zzic.await(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
        }
        return (z && zzhz != null) ? zzhz : null;
    }
}
