package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.util.Base64;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@zzadh
final class zzuc {
    final zzjj zzaao;
    final int zzbop;
    final String zzye;

    @VisibleForTesting
    private zzuc(zzjj com_google_android_gms_internal_ads_zzjj, String str, int i) {
        this.zzaao = com_google_android_gms_internal_ads_zzjj;
        this.zzye = str;
        this.zzbop = i;
    }

    zzuc(zzty com_google_android_gms_internal_ads_zzty) {
        this(com_google_android_gms_internal_ads_zzty.zzlf(), com_google_android_gms_internal_ads_zzty.getAdUnitId(), com_google_android_gms_internal_ads_zzty.getNetworkType());
    }

    static zzuc zzba(String str) throws IOException {
        Throwable e;
        String[] split = str.split("\u0000");
        if (split.length != 3) {
            throw new IOException("Incorrect field count for QueueSeed.");
        }
        Parcel obtain = Parcel.obtain();
        try {
            String str2 = new String(Base64.decode(split[0], 0), "UTF-8");
            int parseInt = Integer.parseInt(split[1]);
            byte[] decode = Base64.decode(split[2], 0);
            obtain.unmarshall(decode, 0, decode.length);
            obtain.setDataPosition(0);
            zzuc com_google_android_gms_internal_ads_zzuc = new zzuc((zzjj) zzjj.CREATOR.createFromParcel(obtain), str2, parseInt);
            obtain.recycle();
            return com_google_android_gms_internal_ads_zzuc;
        } catch (IllegalStateException e2) {
            e = e2;
            try {
                zzbv.zzeo().zza(e, "QueueSeed.decode");
                throw new IOException("Malformed QueueSeed encoding.", e);
            } catch (Throwable th) {
                obtain.recycle();
            }
        } catch (IllegalArgumentException e3) {
            e = e3;
            zzbv.zzeo().zza(e, "QueueSeed.decode");
            throw new IOException("Malformed QueueSeed encoding.", e);
        } catch (ParseException e4) {
            e = e4;
            zzbv.zzeo().zza(e, "QueueSeed.decode");
            throw new IOException("Malformed QueueSeed encoding.", e);
        }
    }

    final String zzlu() {
        String encodeToString;
        Parcel obtain = Parcel.obtain();
        try {
            encodeToString = Base64.encodeToString(this.zzye.getBytes("UTF-8"), 0);
            String num = Integer.toString(this.zzbop);
            this.zzaao.writeToParcel(obtain, 0);
            String encodeToString2 = Base64.encodeToString(obtain.marshall(), 0);
            encodeToString = new StringBuilder(((String.valueOf(encodeToString).length() + 2) + String.valueOf(num).length()) + String.valueOf(encodeToString2).length()).append(encodeToString).append("\u0000").append(num).append("\u0000").append(encodeToString2).toString();
            return encodeToString;
        } catch (UnsupportedEncodingException e) {
            encodeToString = "QueueSeed encode failed because UTF-8 is not available.";
            zzane.m3427e(encodeToString);
            return "";
        } finally {
            obtain.recycle();
        }
    }
}
