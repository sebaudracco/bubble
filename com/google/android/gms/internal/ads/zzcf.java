package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class zzcf implements zzce {
    protected static volatile zzcz zzps = null;
    protected MotionEvent zzpy;
    protected LinkedList<MotionEvent> zzpz = new LinkedList();
    protected long zzqa = 0;
    protected long zzqb = 0;
    protected long zzqc = 0;
    protected long zzqd = 0;
    protected long zzqe = 0;
    protected long zzqf = 0;
    protected long zzqg = 0;
    protected double zzqh;
    private double zzqi;
    private double zzqj;
    protected float zzqk;
    protected float zzql;
    protected float zzqm;
    protected float zzqn;
    private boolean zzqo = false;
    protected boolean zzqp = false;
    protected DisplayMetrics zzqq;

    protected zzcf(Context context) {
        try {
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbay)).booleanValue()) {
                zzbk.zzv();
            } else {
                zzde.zzb(zzps);
            }
            this.zzqq = context.getResources().getDisplayMetrics();
        } catch (Throwable th) {
        }
    }

    private final String zza(Context context, String str, boolean z, View view, Activity activity, byte[] bArr) {
        zzba zza;
        if (z) {
            try {
                zza = zza(context, view, activity);
                this.zzqo = true;
            } catch (GeneralSecurityException e) {
                return Integer.toString(7);
            } catch (UnsupportedEncodingException e2) {
                return Integer.toString(7);
            } catch (Throwable th) {
                return Integer.toString(3);
            }
        }
        zza = zza(context, null);
        return (zza == null || zza.zzacw() == 0) ? Integer.toString(5) : zzbk.zza(zza, str);
    }

    protected abstract long zza(StackTraceElement[] stackTraceElementArr) throws zzcw;

    protected abstract zzba zza(Context context, View view, Activity activity);

    protected abstract zzba zza(Context context, zzax com_google_android_gms_internal_ads_zzax);

    public final String zza(Context context) {
        if (zzdg.isMainThread()) {
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbba)).booleanValue()) {
                throw new IllegalStateException("The caller must not be called from the UI thread.");
            }
        }
        return zza(context, null, false, null, null, null);
    }

    public final String zza(Context context, String str, View view) {
        return zza(context, str, view, null);
    }

    public final String zza(Context context, String str, View view, Activity activity) {
        return zza(context, str, true, view, activity, null);
    }

    public final void zza(int i, int i2, int i3) {
        if (this.zzpy != null) {
            this.zzpy.recycle();
        }
        if (this.zzqq != null) {
            this.zzpy = MotionEvent.obtain(0, (long) i3, 1, ((float) i) * this.zzqq.density, ((float) i2) * this.zzqq.density, 0.0f, 0.0f, 0, 0.0f, 0.0f, 0, 0);
        } else {
            this.zzpy = null;
        }
        this.zzqp = false;
    }

    public final void zza(MotionEvent motionEvent) {
        if (this.zzqo) {
            this.zzqd = 0;
            this.zzqc = 0;
            this.zzqb = 0;
            this.zzqa = 0;
            this.zzqe = 0;
            this.zzqg = 0;
            this.zzqf = 0;
            Iterator it = this.zzpz.iterator();
            while (it.hasNext()) {
                ((MotionEvent) it.next()).recycle();
            }
            this.zzpz.clear();
            this.zzpy = null;
            this.zzqo = false;
        }
        switch (motionEvent.getAction()) {
            case 0:
                this.zzqh = 0.0d;
                this.zzqi = (double) motionEvent.getRawX();
                this.zzqj = (double) motionEvent.getRawY();
                break;
            case 1:
            case 2:
                double rawX = (double) motionEvent.getRawX();
                double rawY = (double) motionEvent.getRawY();
                double d = rawX - this.zzqi;
                double d2 = rawY - this.zzqj;
                this.zzqh = Math.sqrt((d * d) + (d2 * d2)) + this.zzqh;
                this.zzqi = rawX;
                this.zzqj = rawY;
                break;
        }
        switch (motionEvent.getAction()) {
            case 0:
                this.zzqk = motionEvent.getX();
                this.zzql = motionEvent.getY();
                this.zzqm = motionEvent.getRawX();
                this.zzqn = motionEvent.getRawY();
                this.zzqa++;
                break;
            case 1:
                this.zzpy = MotionEvent.obtain(motionEvent);
                this.zzpz.add(this.zzpy);
                if (this.zzpz.size() > 6) {
                    ((MotionEvent) this.zzpz.remove()).recycle();
                }
                this.zzqc++;
                try {
                    this.zzqe = zza(new Throwable().getStackTrace());
                    break;
                } catch (zzcw e) {
                    break;
                }
            case 2:
                this.zzqb += (long) (motionEvent.getHistorySize() + 1);
                try {
                    zzdf zzb = zzb(motionEvent);
                    Object obj = (zzb == null || zzb.zzfr == null || zzb.zzst == null) ? null : 1;
                    if (obj != null) {
                        this.zzqf += zzb.zzfr.longValue() + zzb.zzst.longValue();
                    }
                    obj = (this.zzqq == null || zzb == null || zzb.zzfp == null || zzb.zzsu == null) ? null : 1;
                    if (obj != null) {
                        this.zzqg = (zzb.zzsu.longValue() + zzb.zzfp.longValue()) + this.zzqg;
                        break;
                    }
                } catch (zzcw e2) {
                    break;
                }
                break;
            case 3:
                this.zzqd++;
                break;
        }
        this.zzqp = true;
    }

    protected abstract zzdf zzb(MotionEvent motionEvent) throws zzcw;

    public void zzb(View view) {
    }
}
