package com.google.android.gms.internal.ads;

import java.io.UnsupportedEncodingException;

public class zzav extends zzr<String> {
    private final Object mLock = new Object();
    private zzz<String> zzck;

    public zzav(int i, String str, zzz<String> com_google_android_gms_internal_ads_zzz_java_lang_String, zzy com_google_android_gms_internal_ads_zzy) {
        super(i, str, com_google_android_gms_internal_ads_zzy);
        this.zzck = com_google_android_gms_internal_ads_zzz_java_lang_String;
    }

    protected final zzx<String> zza(zzp com_google_android_gms_internal_ads_zzp) {
        Object str;
        try {
            byte[] bArr = com_google_android_gms_internal_ads_zzp.data;
            String str2 = "ISO-8859-1";
            String str3 = (String) com_google_android_gms_internal_ads_zzp.zzab.get("Content-Type");
            if (str3 != null) {
                String[] split = str3.split(";");
                for (int i = 1; i < split.length; i++) {
                    String[] split2 = split[i].trim().split("=");
                    if (split2.length == 2 && split2[0].equals("charset")) {
                        str3 = split2[1];
                        break;
                    }
                }
            }
            str3 = str2;
            str = new String(bArr, str3);
        } catch (UnsupportedEncodingException e) {
            str = new String(com_google_android_gms_internal_ads_zzp.data);
        }
        return zzx.zza(str, zzap.zzb(com_google_android_gms_internal_ads_zzp));
    }

    protected /* synthetic */ void zza(Object obj) {
        zzh((String) obj);
    }

    protected void zzh(String str) {
        synchronized (this.mLock) {
            zzz com_google_android_gms_internal_ads_zzz = this.zzck;
        }
        if (com_google_android_gms_internal_ads_zzz != null) {
            com_google_android_gms_internal_ads_zzz.zzb(str);
        }
    }
}
