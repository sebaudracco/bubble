package com.google.android.gms.internal.ads;

import android.graphics.Color;
import cz.msebera.android.httpclient.HttpStatus;
import java.util.ArrayList;
import java.util.List;

@zzadh
public final class zzoj extends zzpt {
    private static final int zzbha = Color.rgb(12, 174, HttpStatus.SC_PARTIAL_CONTENT);
    private static final int zzbhb;
    private static final int zzbhc;
    private static final int zzbhd = zzbha;
    private final int mTextColor;
    private final String zzbhe;
    private final List<zzon> zzbhf = new ArrayList();
    private final List<zzpw> zzbhg = new ArrayList();
    private final int zzbhh;
    private final int zzbhi;
    private final int zzbhj;
    private final int zzbhk;
    private final boolean zzbhl;

    static {
        int rgb = Color.rgb(HttpStatus.SC_NO_CONTENT, HttpStatus.SC_NO_CONTENT, HttpStatus.SC_NO_CONTENT);
        zzbhb = rgb;
        zzbhc = rgb;
    }

    public zzoj(String str, List<zzon> list, Integer num, Integer num2, Integer num3, int i, int i2, boolean z) {
        this.zzbhe = str;
        if (list != null) {
            for (int i3 = 0; i3 < list.size(); i3++) {
                zzon com_google_android_gms_internal_ads_zzon = (zzon) list.get(i3);
                this.zzbhf.add(com_google_android_gms_internal_ads_zzon);
                this.zzbhg.add(com_google_android_gms_internal_ads_zzon);
            }
        }
        this.zzbhh = num != null ? num.intValue() : zzbhc;
        this.mTextColor = num2 != null ? num2.intValue() : zzbhd;
        this.zzbhi = num3 != null ? num3.intValue() : 12;
        this.zzbhj = i;
        this.zzbhk = i2;
        this.zzbhl = z;
    }

    public final int getBackgroundColor() {
        return this.zzbhh;
    }

    public final String getText() {
        return this.zzbhe;
    }

    public final int getTextColor() {
        return this.mTextColor;
    }

    public final int getTextSize() {
        return this.zzbhi;
    }

    public final List<zzpw> zzjr() {
        return this.zzbhg;
    }

    public final List<zzon> zzjs() {
        return this.zzbhf;
    }

    public final int zzjt() {
        return this.zzbhj;
    }

    public final int zzju() {
        return this.zzbhk;
    }

    public final boolean zzjv() {
        return this.zzbhl;
    }
}
