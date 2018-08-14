package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.InputDeviceCompat;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.internal.Asserts;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.ParametersAreNonnullByDefault;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

@zzadh
@ParametersAreNonnullByDefault
public final class zzapi extends FrameLayout implements zzapf {
    private final zzapw zzcxm;
    private final FrameLayout zzcxn;
    private final zznx zzcxo;
    private final zzapy zzcxp;
    private final long zzcxq;
    @Nullable
    private zzapg zzcxr;
    private boolean zzcxs;
    private boolean zzcxt;
    private boolean zzcxu;
    private boolean zzcxv;
    private long zzcxw;
    private long zzcxx;
    private String zzcxy;
    private Bitmap zzcxz;
    private ImageView zzcya;
    private boolean zzcyb;

    public zzapi(Context context, zzapw com_google_android_gms_internal_ads_zzapw, int i, boolean z, zznx com_google_android_gms_internal_ads_zznx, zzapv com_google_android_gms_internal_ads_zzapv) {
        super(context);
        this.zzcxm = com_google_android_gms_internal_ads_zzapw;
        this.zzcxo = com_google_android_gms_internal_ads_zznx;
        this.zzcxn = new FrameLayout(context);
        addView(this.zzcxn, new LayoutParams(-1, -1));
        Asserts.checkNotNull(com_google_android_gms_internal_ads_zzapw.zzbi());
        this.zzcxr = com_google_android_gms_internal_ads_zzapw.zzbi().zzwz.zza(context, com_google_android_gms_internal_ads_zzapw, i, z, com_google_android_gms_internal_ads_zznx, com_google_android_gms_internal_ads_zzapv);
        if (this.zzcxr != null) {
            this.zzcxn.addView(this.zzcxr, new LayoutParams(-1, -1, 17));
            if (((Boolean) zzkb.zzik().zzd(zznk.zzavg)).booleanValue()) {
                zztd();
            }
        }
        this.zzcya = new ImageView(context);
        this.zzcxq = ((Long) zzkb.zzik().zzd(zznk.zzavk)).longValue();
        this.zzcxv = ((Boolean) zzkb.zzik().zzd(zznk.zzavi)).booleanValue();
        if (this.zzcxo != null) {
            this.zzcxo.zze("spinner_used", this.zzcxv ? SchemaSymbols.ATTVAL_TRUE_1 : SchemaSymbols.ATTVAL_FALSE_0);
        }
        this.zzcxp = new zzapy(this);
        if (this.zzcxr != null) {
            this.zzcxr.zza(this);
        }
        if (this.zzcxr == null) {
            zzg("AdVideoUnderlay Error", "Allocating player failed.");
        }
    }

    public static void zza(zzapw com_google_android_gms_internal_ads_zzapw) {
        Map hashMap = new HashMap();
        hashMap.put(NotificationCompat.CATEGORY_EVENT, "no_video_view");
        com_google_android_gms_internal_ads_zzapw.zza("onVideoEvent", hashMap);
    }

    public static void zza(zzapw com_google_android_gms_internal_ads_zzapw, String str) {
        Map hashMap = new HashMap();
        hashMap.put(NotificationCompat.CATEGORY_EVENT, "decoderProps");
        hashMap.put("error", str);
        com_google_android_gms_internal_ads_zzapw.zza("onVideoEvent", hashMap);
    }

    public static void zza(zzapw com_google_android_gms_internal_ads_zzapw, Map<String, List<Map<String, Object>>> map) {
        Map hashMap = new HashMap();
        hashMap.put(NotificationCompat.CATEGORY_EVENT, "decoderProps");
        hashMap.put("mimeTypes", map);
        com_google_android_gms_internal_ads_zzapw.zza("onVideoEvent", hashMap);
    }

    private final void zza(String str, String... strArr) {
        Map hashMap = new HashMap();
        hashMap.put(NotificationCompat.CATEGORY_EVENT, str);
        int length = strArr.length;
        int i = 0;
        Object obj = null;
        while (i < length) {
            Object obj2 = strArr[i];
            if (obj != null) {
                hashMap.put(obj, obj2);
                obj2 = null;
            }
            i++;
            obj = obj2;
        }
        this.zzcxm.zza("onVideoEvent", hashMap);
    }

    private final boolean zztf() {
        return this.zzcya.getParent() != null;
    }

    private final void zztg() {
        if (this.zzcxm.zzto() != null && this.zzcxt && !this.zzcxu) {
            this.zzcxm.zzto().getWindow().clearFlags(128);
            this.zzcxt = false;
        }
    }

    public final void destroy() {
        this.zzcxp.pause();
        if (this.zzcxr != null) {
            this.zzcxr.stop();
        }
        zztg();
    }

    public final void finalize() throws Throwable {
        try {
            this.zzcxp.pause();
            if (this.zzcxr != null) {
                zzapg com_google_android_gms_internal_ads_zzapg = this.zzcxr;
                Executor executor = zzaoe.zzcvy;
                com_google_android_gms_internal_ads_zzapg.getClass();
                executor.execute(zzapj.zza(com_google_android_gms_internal_ads_zzapg));
            }
            super.finalize();
        } catch (Throwable th) {
            super.finalize();
        }
    }

    public final void onPaused() {
        zza("pause", new String[0]);
        zztg();
        this.zzcxs = false;
    }

    public final void onWindowVisibilityChanged(int i) {
        boolean z;
        if (i == 0) {
            this.zzcxp.resume();
            z = true;
        } else {
            this.zzcxp.pause();
            this.zzcxx = this.zzcxw;
            z = false;
        }
        zzakk.zzcrm.post(new zzapm(this, z));
    }

    public final void pause() {
        if (this.zzcxr != null) {
            this.zzcxr.pause();
        }
    }

    public final void play() {
        if (this.zzcxr != null) {
            this.zzcxr.play();
        }
    }

    public final void seekTo(int i) {
        if (this.zzcxr != null) {
            this.zzcxr.seekTo(i);
        }
    }

    public final void setVolume(float f) {
        if (this.zzcxr != null) {
            zzapg com_google_android_gms_internal_ads_zzapg = this.zzcxr;
            com_google_android_gms_internal_ads_zzapg.zzcxl.setVolume(f);
            com_google_android_gms_internal_ads_zzapg.zzst();
        }
    }

    public final void zza(float f, float f2) {
        if (this.zzcxr != null) {
            this.zzcxr.zza(f, f2);
        }
    }

    public final void zzd(int i, int i2, int i3, int i4) {
        if (i3 != 0 && i4 != 0) {
            ViewGroup.LayoutParams layoutParams = new LayoutParams(i3, i4);
            layoutParams.setMargins(i, i2, 0, 0);
            this.zzcxn.setLayoutParams(layoutParams);
            requestLayout();
        }
    }

    public final void zzdn(String str) {
        this.zzcxy = str;
    }

    public final void zzf(int i, int i2) {
        if (this.zzcxv) {
            int max = Math.max(i / ((Integer) zzkb.zzik().zzd(zznk.zzavj)).intValue(), 1);
            int max2 = Math.max(i2 / ((Integer) zzkb.zzik().zzd(zznk.zzavj)).intValue(), 1);
            if (this.zzcxz == null || this.zzcxz.getWidth() != max || this.zzcxz.getHeight() != max2) {
                this.zzcxz = Bitmap.createBitmap(max, max2, Config.ARGB_8888);
                this.zzcyb = false;
            }
        }
    }

    @TargetApi(14)
    public final void zzf(MotionEvent motionEvent) {
        if (this.zzcxr != null) {
            this.zzcxr.dispatchTouchEvent(motionEvent);
        }
    }

    public final void zzg(String str, @Nullable String str2) {
        zza("error", "what", str, "extra", str2);
    }

    public final void zzsu() {
        this.zzcxp.resume();
        zzakk.zzcrm.post(new zzapk(this));
    }

    public final void zzsv() {
        if (this.zzcxr != null && this.zzcxx == 0) {
            float duration = ((float) this.zzcxr.getDuration()) / 1000.0f;
            int videoWidth = this.zzcxr.getVideoWidth();
            int videoHeight = this.zzcxr.getVideoHeight();
            zza("canplaythrough", "duration", String.valueOf(duration), "videoWidth", String.valueOf(videoWidth), "videoHeight", String.valueOf(videoHeight));
        }
    }

    public final void zzsw() {
        if (!(this.zzcxm.zzto() == null || this.zzcxt)) {
            this.zzcxu = (this.zzcxm.zzto().getWindow().getAttributes().flags & 128) != 0;
            if (!this.zzcxu) {
                this.zzcxm.zzto().getWindow().addFlags(128);
                this.zzcxt = true;
            }
        }
        this.zzcxs = true;
    }

    public final void zzsx() {
        zza("ended", new String[0]);
        zztg();
    }

    public final void zzsy() {
        if (!(!this.zzcyb || this.zzcxz == null || zztf())) {
            this.zzcya.setImageBitmap(this.zzcxz);
            this.zzcya.invalidate();
            this.zzcxn.addView(this.zzcya, new LayoutParams(-1, -1));
            this.zzcxn.bringChildToFront(this.zzcya);
        }
        this.zzcxp.pause();
        this.zzcxx = this.zzcxw;
        zzakk.zzcrm.post(new zzapl(this));
    }

    public final void zzsz() {
        if (this.zzcxs && zztf()) {
            this.zzcxn.removeView(this.zzcya);
        }
        if (this.zzcxz != null) {
            long elapsedRealtime = zzbv.zzer().elapsedRealtime();
            if (this.zzcxr.getBitmap(this.zzcxz) != null) {
                this.zzcyb = true;
            }
            elapsedRealtime = zzbv.zzer().elapsedRealtime() - elapsedRealtime;
            if (zzakb.zzqp()) {
                zzakb.m3428v("Spinner frame grab took " + elapsedRealtime + "ms");
            }
            if (elapsedRealtime > this.zzcxq) {
                zzane.zzdk("Spinner frame grab crossed jank threshold! Suspending spinner.");
                this.zzcxv = false;
                this.zzcxz = null;
                if (this.zzcxo != null) {
                    this.zzcxo.zze("spinner_jank", Long.toString(elapsedRealtime));
                }
            }
        }
    }

    public final void zzta() {
        if (this.zzcxr != null) {
            if (TextUtils.isEmpty(this.zzcxy)) {
                zza("no_src", new String[0]);
            } else {
                this.zzcxr.setVideoPath(this.zzcxy);
            }
        }
    }

    public final void zztb() {
        if (this.zzcxr != null) {
            zzapg com_google_android_gms_internal_ads_zzapg = this.zzcxr;
            com_google_android_gms_internal_ads_zzapg.zzcxl.setMuted(true);
            com_google_android_gms_internal_ads_zzapg.zzst();
        }
    }

    public final void zztc() {
        if (this.zzcxr != null) {
            zzapg com_google_android_gms_internal_ads_zzapg = this.zzcxr;
            com_google_android_gms_internal_ads_zzapg.zzcxl.setMuted(false);
            com_google_android_gms_internal_ads_zzapg.zzst();
        }
    }

    @TargetApi(14)
    public final void zztd() {
        if (this.zzcxr != null) {
            View textView = new TextView(this.zzcxr.getContext());
            String str = "AdMob - ";
            String valueOf = String.valueOf(this.zzcxr.zzsp());
            textView.setText(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            textView.setTextColor(SupportMenu.CATEGORY_MASK);
            textView.setBackgroundColor(InputDeviceCompat.SOURCE_ANY);
            this.zzcxn.addView(textView, new LayoutParams(-2, -2, 17));
            this.zzcxn.bringChildToFront(textView);
        }
    }

    final void zzte() {
        if (this.zzcxr != null) {
            long currentPosition = (long) this.zzcxr.getCurrentPosition();
            if (this.zzcxw != currentPosition && currentPosition > 0) {
                float f = ((float) currentPosition) / 1000.0f;
                zza("timeupdate", "time", String.valueOf(f));
                this.zzcxw = currentPosition;
            }
        }
    }
}
