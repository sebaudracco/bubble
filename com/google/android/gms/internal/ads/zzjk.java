package com.google.android.gms.internal.ads;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzjk {
    private Bundle mExtras;
    private long zzaqm;
    private int zzaqn;
    private List<String> zzaqo;
    private boolean zzaqp;
    private int zzaqq;
    private String zzaqr;
    private zzmq zzaqs;
    private String zzaqt;
    private Bundle zzaqu;
    private Bundle zzaqv;
    private List<String> zzaqw;
    private String zzaqx;
    private String zzaqy;
    private boolean zzaqz;
    private Location zzhp;
    private boolean zzvm;

    public zzjk() {
        this.zzaqm = -1;
        this.mExtras = new Bundle();
        this.zzaqn = -1;
        this.zzaqo = new ArrayList();
        this.zzaqp = false;
        this.zzaqq = -1;
        this.zzvm = false;
        this.zzaqr = null;
        this.zzaqs = null;
        this.zzhp = null;
        this.zzaqt = null;
        this.zzaqu = new Bundle();
        this.zzaqv = new Bundle();
        this.zzaqw = new ArrayList();
        this.zzaqx = null;
        this.zzaqy = null;
        this.zzaqz = false;
    }

    public zzjk(zzjj com_google_android_gms_internal_ads_zzjj) {
        this.zzaqm = com_google_android_gms_internal_ads_zzjj.zzapw;
        this.mExtras = com_google_android_gms_internal_ads_zzjj.extras;
        this.zzaqn = com_google_android_gms_internal_ads_zzjj.zzapx;
        this.zzaqo = com_google_android_gms_internal_ads_zzjj.zzapy;
        this.zzaqp = com_google_android_gms_internal_ads_zzjj.zzapz;
        this.zzaqq = com_google_android_gms_internal_ads_zzjj.zzaqa;
        this.zzvm = com_google_android_gms_internal_ads_zzjj.zzaqb;
        this.zzaqr = com_google_android_gms_internal_ads_zzjj.zzaqc;
        this.zzaqs = com_google_android_gms_internal_ads_zzjj.zzaqd;
        this.zzhp = com_google_android_gms_internal_ads_zzjj.zzaqe;
        this.zzaqt = com_google_android_gms_internal_ads_zzjj.zzaqf;
        this.zzaqu = com_google_android_gms_internal_ads_zzjj.zzaqg;
        this.zzaqv = com_google_android_gms_internal_ads_zzjj.zzaqh;
        this.zzaqw = com_google_android_gms_internal_ads_zzjj.zzaqi;
        this.zzaqx = com_google_android_gms_internal_ads_zzjj.zzaqj;
        this.zzaqy = com_google_android_gms_internal_ads_zzjj.zzaqk;
    }

    public final zzjk zza(@Nullable Location location) {
        this.zzhp = null;
        return this;
    }

    public final zzjj zzhw() {
        return new zzjj(7, this.zzaqm, this.mExtras, this.zzaqn, this.zzaqo, this.zzaqp, this.zzaqq, this.zzvm, this.zzaqr, this.zzaqs, this.zzhp, this.zzaqt, this.zzaqu, this.zzaqv, this.zzaqw, this.zzaqx, this.zzaqy, false);
    }
}
