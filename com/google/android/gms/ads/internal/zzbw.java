package com.google.android.gms.ads.internal;

import android.content.Context;
import android.graphics.Rect;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.util.SimpleArrayMap;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzagx;
import com.google.android.gms.internal.ads.zzahe;
import com.google.android.gms.internal.ads.zzajh;
import com.google.android.gms.internal.ads.zzaji;
import com.google.android.gms.internal.ads.zzajj;
import com.google.android.gms.internal.ads.zzaju;
import com.google.android.gms.internal.ads.zzajx;
import com.google.android.gms.internal.ads.zzalc;
import com.google.android.gms.internal.ads.zzamj;
import com.google.android.gms.internal.ads.zzamu;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzasc;
import com.google.android.gms.internal.ads.zzce;
import com.google.android.gms.internal.ads.zzci;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zzke;
import com.google.android.gms.internal.ads.zzkh;
import com.google.android.gms.internal.ads.zzkx;
import com.google.android.gms.internal.ads.zzla;
import com.google.android.gms.internal.ads.zzlg;
import com.google.android.gms.internal.ads.zzlu;
import com.google.android.gms.internal.ads.zzmu;
import com.google.android.gms.internal.ads.zznk;
import com.google.android.gms.internal.ads.zzod;
import com.google.android.gms.internal.ads.zzpl;
import com.google.android.gms.internal.ads.zzqw;
import com.google.android.gms.internal.ads.zzqz;
import com.google.android.gms.internal.ads.zzrc;
import com.google.android.gms.internal.ads.zzrf;
import com.google.android.gms.internal.ads.zzri;
import com.google.android.gms.internal.ads.zzrl;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzbw implements OnGlobalLayoutListener, OnScrollChangedListener {
    final String zzaco;
    public String zzacp;
    final zzci zzacq;
    public final zzang zzacr;
    @Nullable
    zzbx zzacs;
    @Nullable
    public zzajx zzact;
    @Nullable
    public zzalc zzacu;
    public zzjn zzacv;
    @Nullable
    public zzajh zzacw;
    public zzaji zzacx;
    @Nullable
    public zzajj zzacy;
    @Nullable
    zzke zzacz;
    @Nullable
    zzkh zzada;
    @Nullable
    zzla zzadb;
    @Nullable
    zzkx zzadc;
    @Nullable
    zzlg zzadd;
    @Nullable
    zzqw zzade;
    @Nullable
    zzqz zzadf;
    @Nullable
    zzrl zzadg;
    SimpleArrayMap<String, zzrc> zzadh;
    SimpleArrayMap<String, zzrf> zzadi;
    zzpl zzadj;
    @Nullable
    zzmu zzadk;
    @Nullable
    zzlu zzadl;
    @Nullable
    zzri zzadm;
    @Nullable
    List<Integer> zzadn;
    @Nullable
    zzod zzado;
    @Nullable
    zzahe zzadp;
    @Nullable
    zzagx zzadq;
    @Nullable
    public String zzadr;
    @Nullable
    List<String> zzads;
    @Nullable
    public zzaju zzadt;
    @Nullable
    View zzadu;
    public int zzadv;
    private HashSet<zzajj> zzadw;
    private int zzadx;
    private int zzady;
    private zzamj zzadz;
    private boolean zzaea;
    private boolean zzaeb;
    private boolean zzaec;
    public final Context zzrt;
    boolean zzze;

    public zzbw(Context context, zzjn com_google_android_gms_internal_ads_zzjn, String str, zzang com_google_android_gms_internal_ads_zzang) {
        this(context, com_google_android_gms_internal_ads_zzjn, str, com_google_android_gms_internal_ads_zzang, null);
    }

    private zzbw(Context context, zzjn com_google_android_gms_internal_ads_zzjn, String str, zzang com_google_android_gms_internal_ads_zzang, zzci com_google_android_gms_internal_ads_zzci) {
        this.zzadt = null;
        this.zzadu = null;
        this.zzadv = 0;
        this.zzze = false;
        this.zzadw = null;
        this.zzadx = -1;
        this.zzady = -1;
        this.zzaea = true;
        this.zzaeb = true;
        this.zzaec = false;
        zznk.initialize(context);
        if (zzbv.zzeo().zzpy() != null) {
            List zzjc = zznk.zzjc();
            if (com_google_android_gms_internal_ads_zzang.zzcve != 0) {
                zzjc.add(Integer.toString(com_google_android_gms_internal_ads_zzang.zzcve));
            }
            zzbv.zzeo().zzpy().zzg(zzjc);
        }
        this.zzaco = UUID.randomUUID().toString();
        if (com_google_android_gms_internal_ads_zzjn.zzarc || com_google_android_gms_internal_ads_zzjn.zzare) {
            this.zzacs = null;
        } else {
            this.zzacs = new zzbx(context, str, com_google_android_gms_internal_ads_zzang.zzcw, this, this);
            this.zzacs.setMinimumWidth(com_google_android_gms_internal_ads_zzjn.widthPixels);
            this.zzacs.setMinimumHeight(com_google_android_gms_internal_ads_zzjn.heightPixels);
            this.zzacs.setVisibility(4);
        }
        this.zzacv = com_google_android_gms_internal_ads_zzjn;
        this.zzacp = str;
        this.zzrt = context;
        this.zzacr = com_google_android_gms_internal_ads_zzang;
        this.zzacq = new zzci(new zzag(this));
        this.zzadz = new zzamj(200);
        this.zzadi = new SimpleArrayMap();
    }

    private final void zzf(boolean z) {
        boolean z2 = true;
        if (this.zzacs != null && this.zzacw != null && this.zzacw.zzbyo != null && this.zzacw.zzbyo.zzuf() != null) {
            if (!z || this.zzadz.tryAcquire()) {
                if (this.zzacw.zzbyo.zzuf().zzfz()) {
                    int[] iArr = new int[2];
                    this.zzacs.getLocationOnScreen(iArr);
                    zzkb.zzif();
                    int zzb = zzamu.zzb(this.zzrt, iArr[0]);
                    zzkb.zzif();
                    int zzb2 = zzamu.zzb(this.zzrt, iArr[1]);
                    if (!(zzb == this.zzadx && zzb2 == this.zzady)) {
                        this.zzadx = zzb;
                        this.zzady = zzb2;
                        zzasc zzuf = this.zzacw.zzbyo.zzuf();
                        zzb = this.zzadx;
                        int i = this.zzady;
                        if (z) {
                            z2 = false;
                        }
                        zzuf.zza(zzb, i, z2);
                    }
                }
                if (this.zzacs != null) {
                    View findViewById = this.zzacs.getRootView().findViewById(16908290);
                    if (findViewById != null) {
                        Rect rect = new Rect();
                        Rect rect2 = new Rect();
                        this.zzacs.getGlobalVisibleRect(rect);
                        findViewById.getGlobalVisibleRect(rect2);
                        if (rect.top != rect2.top) {
                            this.zzaea = false;
                        }
                        if (rect.bottom != rect2.bottom) {
                            this.zzaeb = false;
                        }
                    }
                }
            }
        }
    }

    public final void onGlobalLayout() {
        zzf(false);
    }

    public final void onScrollChanged() {
        zzf(true);
        this.zzaec = true;
    }

    public final void zza(HashSet<zzajj> hashSet) {
        this.zzadw = hashSet;
    }

    public final HashSet<zzajj> zzfl() {
        return this.zzadw;
    }

    public final void zzfm() {
        if (this.zzacw != null && this.zzacw.zzbyo != null) {
            this.zzacw.zzbyo.destroy();
        }
    }

    public final void zzfn() {
        if (this.zzacw != null && this.zzacw.zzbtx != null) {
            try {
                this.zzacw.zzbtx.destroy();
            } catch (RemoteException e) {
                zzane.zzdk("Could not destroy mediation adapter.");
            }
        }
    }

    public final boolean zzfo() {
        return this.zzadv == 0;
    }

    public final boolean zzfp() {
        return this.zzadv == 1;
    }

    public final String zzfq() {
        return (this.zzaea && this.zzaeb) ? "" : this.zzaea ? this.zzaec ? "top-scrollable" : "top-locked" : this.zzaeb ? this.zzaec ? "bottom-scrollable" : "bottom-locked" : "";
    }

    public final void zzg(boolean z) {
        if (!(this.zzadv != 0 || this.zzacw == null || this.zzacw.zzbyo == null)) {
            this.zzacw.zzbyo.stopLoading();
        }
        if (this.zzact != null) {
            this.zzact.cancel();
        }
        if (this.zzacu != null) {
            this.zzacu.cancel();
        }
        if (z) {
            this.zzacw = null;
        }
    }

    final void zzj(View view) {
        if (((Boolean) zzkb.zzik().zzd(zznk.zzbat)).booleanValue()) {
            zzce zzaa = this.zzacq.zzaa();
            if (zzaa != null) {
                zzaa.zzb(view);
            }
        }
    }
}
