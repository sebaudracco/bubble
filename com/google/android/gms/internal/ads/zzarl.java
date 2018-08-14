package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.appnext.base.p023b.C1042c;
import com.google.android.gms.common.util.CollectionUtils;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

@zzadh
public final class zzarl extends zzlp {
    private final Object lock = new Object();
    @GuardedBy("lock")
    private boolean zzato = true;
    @GuardedBy("lock")
    private boolean zzatp;
    @GuardedBy("lock")
    private boolean zzatq;
    @GuardedBy("lock")
    private zzlr zzbuq;
    private final zzapw zzcyg;
    private final boolean zzded;
    private final boolean zzdee;
    private final float zzdef;
    @GuardedBy("lock")
    private int zzdeg;
    @GuardedBy("lock")
    private boolean zzdeh;
    @GuardedBy("lock")
    private boolean zzdei = true;
    @GuardedBy("lock")
    private float zzdej;
    @GuardedBy("lock")
    private float zzdek;

    public zzarl(zzapw com_google_android_gms_internal_ads_zzapw, float f, boolean z, boolean z2) {
        this.zzcyg = com_google_android_gms_internal_ads_zzapw;
        this.zzdef = f;
        this.zzded = z;
        this.zzdee = z2;
    }

    private final void zzf(String str, @Nullable Map<String, String> map) {
        Map hashMap = map == null ? new HashMap() : new HashMap(map);
        hashMap.put(C1042c.jL, str);
        zzaoe.zzcvy.execute(new zzarm(this, hashMap));
    }

    public final float getAspectRatio() {
        float f;
        synchronized (this.lock) {
            f = this.zzdek;
        }
        return f;
    }

    public final int getPlaybackState() {
        int i;
        synchronized (this.lock) {
            i = this.zzdeg;
        }
        return i;
    }

    public final boolean isClickToExpandEnabled() {
        boolean isCustomControlsEnabled = isCustomControlsEnabled();
        synchronized (this.lock) {
            if (!isCustomControlsEnabled) {
                if (this.zzatq && this.zzdee) {
                    isCustomControlsEnabled = true;
                }
            }
            isCustomControlsEnabled = false;
        }
        return isCustomControlsEnabled;
    }

    public final boolean isCustomControlsEnabled() {
        boolean z;
        synchronized (this.lock) {
            z = this.zzded && this.zzatp;
        }
        return z;
    }

    public final boolean isMuted() {
        boolean z;
        synchronized (this.lock) {
            z = this.zzdei;
        }
        return z;
    }

    public final void mute(boolean z) {
        zzf(z ? "mute" : "unmute", null);
    }

    public final void pause() {
        zzf("pause", null);
    }

    public final void play() {
        zzf("play", null);
    }

    public final void zza(float f, int i, boolean z, float f2) {
        boolean z2;
        int i2;
        synchronized (this.lock) {
            this.zzdej = f;
            z2 = this.zzdei;
            this.zzdei = z;
            i2 = this.zzdeg;
            this.zzdeg = i;
            float f3 = this.zzdek;
            this.zzdek = f2;
            if (Math.abs(this.zzdek - f3) > 1.0E-4f) {
                this.zzcyg.getView().invalidate();
            }
        }
        zzaoe.zzcvy.execute(new zzarn(this, i2, i, z2, z));
    }

    final /* synthetic */ void zza(int i, int i2, boolean z, boolean z2) {
        boolean z3 = false;
        synchronized (this.lock) {
            boolean z4 = i != i2;
            boolean z5 = !this.zzdeh && i2 == 1;
            boolean z6 = z4 && i2 == 1;
            boolean z7 = z4 && i2 == 2;
            boolean z8 = z4 && i2 == 3;
            z4 = z != z2;
            if (this.zzdeh || z5) {
                z3 = true;
            }
            this.zzdeh = z3;
            if (this.zzbuq == null) {
                return;
            }
            if (z5) {
                try {
                    this.zzbuq.onVideoStart();
                } catch (Throwable e) {
                    zzane.zzc("Unable to call onVideoStart()", e);
                }
            }
            if (z6) {
                try {
                    this.zzbuq.onVideoPlay();
                } catch (Throwable e2) {
                    zzane.zzc("Unable to call onVideoPlay()", e2);
                }
            }
            if (z7) {
                try {
                    this.zzbuq.onVideoPause();
                } catch (Throwable e22) {
                    zzane.zzc("Unable to call onVideoPause()", e22);
                }
            }
            if (z8) {
                try {
                    this.zzbuq.onVideoEnd();
                } catch (Throwable e222) {
                    zzane.zzc("Unable to call onVideoEnd()", e222);
                }
            }
            if (z4) {
                try {
                    this.zzbuq.onVideoMute(z2);
                } catch (Throwable e2222) {
                    zzane.zzc("Unable to call onVideoMute()", e2222);
                }
            }
        }
    }

    public final void zza(zzlr com_google_android_gms_internal_ads_zzlr) {
        synchronized (this.lock) {
            this.zzbuq = com_google_android_gms_internal_ads_zzlr;
        }
    }

    public final void zzb(zzmu com_google_android_gms_internal_ads_zzmu) {
        synchronized (this.lock) {
            this.zzato = com_google_android_gms_internal_ads_zzmu.zzato;
            this.zzatp = com_google_android_gms_internal_ads_zzmu.zzatp;
            this.zzatq = com_google_android_gms_internal_ads_zzmu.zzatq;
        }
        zzf("initialState", CollectionUtils.mapOf("muteStart", com_google_android_gms_internal_ads_zzmu.zzato ? SchemaSymbols.ATTVAL_TRUE_1 : SchemaSymbols.ATTVAL_FALSE_0, "customControlsRequested", com_google_android_gms_internal_ads_zzmu.zzatp ? SchemaSymbols.ATTVAL_TRUE_1 : SchemaSymbols.ATTVAL_FALSE_0, "clickToExpandRequested", com_google_android_gms_internal_ads_zzmu.zzatq ? SchemaSymbols.ATTVAL_TRUE_1 : SchemaSymbols.ATTVAL_FALSE_0));
    }

    public final float zzim() {
        return this.zzdef;
    }

    public final float zzin() {
        float f;
        synchronized (this.lock) {
            f = this.zzdej;
        }
        return f;
    }

    public final zzlr zzio() throws RemoteException {
        zzlr com_google_android_gms_internal_ads_zzlr;
        synchronized (this.lock) {
            com_google_android_gms_internal_ads_zzlr = this.zzbuq;
        }
        return com_google_android_gms_internal_ads_zzlr;
    }

    final /* synthetic */ void zzo(Map map) {
        this.zzcyg.zza("pubVideoCmd", map);
    }
}
