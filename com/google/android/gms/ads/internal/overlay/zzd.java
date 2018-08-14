package com.google.android.gms.ads.internal.overlay;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.view.InputDeviceCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.Window;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.facebook.ads.AudienceNetworkActivity;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.GmsVersion;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzaal;
import com.google.android.gms.internal.ads.zzaaq;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zzakq;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zzarc;
import com.google.android.gms.internal.ads.zzasc;
import com.google.android.gms.internal.ads.zzhs;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zznk;
import java.util.Collections;

@zzadh
public class zzd extends zzaaq implements zzw {
    @VisibleForTesting
    private static final int zzbxm = Color.argb(0, 0, 0, 0);
    protected final Activity mActivity;
    @VisibleForTesting
    zzaqw zzbnd;
    @VisibleForTesting
    AdOverlayInfoParcel zzbxn;
    @VisibleForTesting
    private zzi zzbxo;
    @VisibleForTesting
    private zzo zzbxp;
    @VisibleForTesting
    private boolean zzbxq = false;
    @VisibleForTesting
    private FrameLayout zzbxr;
    @VisibleForTesting
    private CustomViewCallback zzbxs;
    @VisibleForTesting
    private boolean zzbxt = false;
    @VisibleForTesting
    private boolean zzbxu = false;
    @VisibleForTesting
    private zzh zzbxv;
    @VisibleForTesting
    private boolean zzbxw = false;
    @VisibleForTesting
    int zzbxx = 0;
    private final Object zzbxy = new Object();
    private Runnable zzbxz;
    private boolean zzbya;
    private boolean zzbyb;
    private boolean zzbyc = false;
    private boolean zzbyd = false;
    private boolean zzbye = true;

    public zzd(Activity activity) {
        this.mActivity = activity;
    }

    private final void zznl() {
        if (this.mActivity.isFinishing() && !this.zzbyc) {
            this.zzbyc = true;
            if (this.zzbnd != null) {
                this.zzbnd.zzai(this.zzbxx);
                synchronized (this.zzbxy) {
                    if (!this.zzbya && this.zzbnd.zzun()) {
                        this.zzbxz = new zzf(this);
                        zzakk.zzcrm.postDelayed(this.zzbxz, ((Long) zzkb.zzik().zzd(zznk.zzayq)).longValue());
                        return;
                    }
                }
            }
            zznm();
        }
    }

    private final void zzno() {
        this.zzbnd.zzno();
    }

    private final void zzs(boolean z) {
        int intValue = ((Integer) zzkb.zzik().zzd(zznk.zzben)).intValue();
        zzp com_google_android_gms_ads_internal_overlay_zzp = new zzp();
        com_google_android_gms_ads_internal_overlay_zzp.size = 50;
        com_google_android_gms_ads_internal_overlay_zzp.paddingLeft = z ? intValue : 0;
        com_google_android_gms_ads_internal_overlay_zzp.paddingRight = z ? 0 : intValue;
        com_google_android_gms_ads_internal_overlay_zzp.paddingTop = 0;
        com_google_android_gms_ads_internal_overlay_zzp.paddingBottom = intValue;
        this.zzbxp = new zzo(this.mActivity, com_google_android_gms_ads_internal_overlay_zzp, this);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(10);
        layoutParams.addRule(z ? 11 : 9);
        zza(z, this.zzbxn.zzbyr);
        this.zzbxv.addView(this.zzbxp, layoutParams);
    }

    private final void zzt(boolean z) throws zzg {
        if (!this.zzbyb) {
            this.mActivity.requestWindowFeature(1);
        }
        Window window = this.mActivity.getWindow();
        if (window == null) {
            throw new zzg("Invalid activity, no window available.");
        }
        boolean zza;
        boolean zzfz;
        ViewParent parent;
        if (PlatformVersion.isAtLeastN()) {
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbel)).booleanValue()) {
                zzbv.zzek();
                zza = zzakk.zza(this.mActivity, this.mActivity.getResources().getConfiguration());
                Object obj = (this.zzbxn.zzbyw == null && this.zzbxn.zzbyw.zzzf) ? 1 : null;
                if (!(this.zzbxu && obj == null) && r1) {
                    window.setFlags(1024, 1024);
                    if (((Boolean) zzkb.zzik().zzd(zznk.zzayr)).booleanValue() && PlatformVersion.isAtLeastKitKat() && this.zzbxn.zzbyw != null && this.zzbxn.zzbyw.zzzk) {
                        window.getDecorView().setSystemUiVisibility(InputDeviceCompat.SOURCE_TOUCHSCREEN);
                    }
                }
                zzasc zzuf = this.zzbxn.zzbyo == null ? this.zzbxn.zzbyo.zzuf() : null;
                zzfz = zzuf == null ? zzuf.zzfz() : false;
                this.zzbxw = false;
                if (zzfz) {
                    if (this.zzbxn.orientation == zzbv.zzem().zzrl()) {
                        this.zzbxw = this.mActivity.getResources().getConfiguration().orientation != 1;
                    } else if (this.zzbxn.orientation == zzbv.zzem().zzrm()) {
                        this.zzbxw = this.mActivity.getResources().getConfiguration().orientation != 2;
                    }
                }
                zzane.zzck("Delay onShow to next orientation change: " + this.zzbxw);
                setRequestedOrientation(this.zzbxn.orientation);
                if (zzbv.zzem().zza(window)) {
                    zzane.zzck("Hardware acceleration on the AdActivity window enabled.");
                }
                if (this.zzbxu) {
                    this.zzbxv.setBackgroundColor(-16777216);
                } else {
                    this.zzbxv.setBackgroundColor(zzbxm);
                }
                this.mActivity.setContentView(this.zzbxv);
                this.zzbyb = true;
                if (z) {
                    this.zzbnd = this.zzbxn.zzbyo;
                    this.zzbnd.zzbm(this.mActivity);
                } else {
                    try {
                        zzbv.zzel();
                        this.zzbnd = zzarc.zza(this.mActivity, this.zzbxn.zzbyo == null ? this.zzbxn.zzbyo.zzud() : null, this.zzbxn.zzbyo == null ? this.zzbxn.zzbyo.zzue() : null, true, zzfz, null, this.zzbxn.zzacr, null, null, this.zzbxn.zzbyo == null ? this.zzbxn.zzbyo.zzbi() : null, zzhs.zzhm());
                        this.zzbnd.zzuf().zza(null, this.zzbxn.zzbyx, null, this.zzbxn.zzbyp, this.zzbxn.zzbyt, true, null, this.zzbxn.zzbyo == null ? this.zzbxn.zzbyo.zzuf().zzut() : null, null, null);
                        this.zzbnd.zzuf().zza(new zze(this));
                        if (this.zzbxn.url != null) {
                            this.zzbnd.loadUrl(this.zzbxn.url);
                        } else if (this.zzbxn.zzbys == null) {
                            this.zzbnd.loadDataWithBaseURL(this.zzbxn.zzbyq, this.zzbxn.zzbys, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, "UTF-8", null);
                        } else {
                            throw new zzg("No URL or HTML to display in ad overlay.");
                        }
                        if (this.zzbxn.zzbyo != null) {
                            this.zzbxn.zzbyo.zzb(this);
                        }
                    } catch (Throwable e) {
                        zzane.zzb("Error obtaining webview.", e);
                        throw new zzg("Could not obtain webview for the overlay.");
                    }
                }
                this.zzbnd.zza(this);
                parent = this.zzbnd.getParent();
                if (parent != null && (parent instanceof ViewGroup)) {
                    ((ViewGroup) parent).removeView(this.zzbnd.getView());
                }
                if (this.zzbxu) {
                    this.zzbnd.zzur();
                }
                this.zzbxv.addView(this.zzbnd.getView(), -1, -1);
                if (!(z || this.zzbxw)) {
                    zzno();
                }
                zzs(zzfz);
                if (this.zzbnd.zzuh()) {
                    zza(zzfz, true);
                }
            }
        }
        zza = true;
        if (this.zzbxn.zzbyw == null) {
        }
        window.setFlags(1024, 1024);
        window.getDecorView().setSystemUiVisibility(InputDeviceCompat.SOURCE_TOUCHSCREEN);
        if (this.zzbxn.zzbyo == null) {
        }
        if (zzuf == null) {
        }
        this.zzbxw = false;
        if (zzfz) {
            if (this.zzbxn.orientation == zzbv.zzem().zzrl()) {
                if (this.mActivity.getResources().getConfiguration().orientation != 1) {
                }
                this.zzbxw = this.mActivity.getResources().getConfiguration().orientation != 1;
            } else if (this.zzbxn.orientation == zzbv.zzem().zzrm()) {
                if (this.mActivity.getResources().getConfiguration().orientation != 2) {
                }
                this.zzbxw = this.mActivity.getResources().getConfiguration().orientation != 2;
            }
        }
        zzane.zzck("Delay onShow to next orientation change: " + this.zzbxw);
        setRequestedOrientation(this.zzbxn.orientation);
        if (zzbv.zzem().zza(window)) {
            zzane.zzck("Hardware acceleration on the AdActivity window enabled.");
        }
        if (this.zzbxu) {
            this.zzbxv.setBackgroundColor(zzbxm);
        } else {
            this.zzbxv.setBackgroundColor(-16777216);
        }
        this.mActivity.setContentView(this.zzbxv);
        this.zzbyb = true;
        if (z) {
            this.zzbnd = this.zzbxn.zzbyo;
            this.zzbnd.zzbm(this.mActivity);
        } else {
            zzbv.zzel();
            if (this.zzbxn.zzbyo == null) {
            }
            if (this.zzbxn.zzbyo == null) {
            }
            if (this.zzbxn.zzbyo == null) {
            }
            this.zzbnd = zzarc.zza(this.mActivity, this.zzbxn.zzbyo == null ? this.zzbxn.zzbyo.zzud() : null, this.zzbxn.zzbyo == null ? this.zzbxn.zzbyo.zzue() : null, true, zzfz, null, this.zzbxn.zzacr, null, null, this.zzbxn.zzbyo == null ? this.zzbxn.zzbyo.zzbi() : null, zzhs.zzhm());
            if (this.zzbxn.zzbyo == null) {
            }
            this.zzbnd.zzuf().zza(null, this.zzbxn.zzbyx, null, this.zzbxn.zzbyp, this.zzbxn.zzbyt, true, null, this.zzbxn.zzbyo == null ? this.zzbxn.zzbyo.zzuf().zzut() : null, null, null);
            this.zzbnd.zzuf().zza(new zze(this));
            if (this.zzbxn.url != null) {
                this.zzbnd.loadUrl(this.zzbxn.url);
            } else if (this.zzbxn.zzbys == null) {
                throw new zzg("No URL or HTML to display in ad overlay.");
            } else {
                this.zzbnd.loadDataWithBaseURL(this.zzbxn.zzbyq, this.zzbxn.zzbys, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, "UTF-8", null);
            }
            if (this.zzbxn.zzbyo != null) {
                this.zzbxn.zzbyo.zzb(this);
            }
        }
        this.zzbnd.zza(this);
        parent = this.zzbnd.getParent();
        ((ViewGroup) parent).removeView(this.zzbnd.getView());
        if (this.zzbxu) {
            this.zzbnd.zzur();
        }
        this.zzbxv.addView(this.zzbnd.getView(), -1, -1);
        zzno();
        zzs(zzfz);
        if (this.zzbnd.zzuh()) {
            zza(zzfz, true);
        }
    }

    public final void close() {
        this.zzbxx = 2;
        this.mActivity.finish();
    }

    public final void onActivityResult(int i, int i2, Intent intent) {
    }

    public final void onBackPressed() {
        this.zzbxx = 0;
    }

    public void onCreate(Bundle bundle) {
        boolean z = false;
        this.mActivity.requestWindowFeature(1);
        if (bundle != null) {
            z = bundle.getBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", false);
        }
        this.zzbxt = z;
        try {
            this.zzbxn = AdOverlayInfoParcel.zzc(this.mActivity.getIntent());
            if (this.zzbxn == null) {
                throw new zzg("Could not get info for ad overlay.");
            }
            if (this.zzbxn.zzacr.zzcvf > GmsVersion.VERSION_QUESO) {
                this.zzbxx = 3;
            }
            if (this.mActivity.getIntent() != null) {
                this.zzbye = this.mActivity.getIntent().getBooleanExtra("shouldCallOnOverlayOpened", true);
            }
            if (this.zzbxn.zzbyw != null) {
                this.zzbxu = this.zzbxn.zzbyw.zzze;
            } else {
                this.zzbxu = false;
            }
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbbg)).booleanValue() && this.zzbxu && this.zzbxn.zzbyw.zzzj != -1) {
                new zzj().zzqo();
            }
            if (bundle == null) {
                if (this.zzbxn.zzbyn != null && this.zzbye) {
                    this.zzbxn.zzbyn.zzcc();
                }
                if (!(this.zzbxn.zzbyu == 1 || this.zzbxn.zzbym == null)) {
                    this.zzbxn.zzbym.onAdClicked();
                }
            }
            this.zzbxv = new zzh(this.mActivity, this.zzbxn.zzbyv, this.zzbxn.zzacr.zzcw);
            this.zzbxv.setId(1000);
            switch (this.zzbxn.zzbyu) {
                case 1:
                    zzt(false);
                    return;
                case 2:
                    this.zzbxo = new zzi(this.zzbxn.zzbyo);
                    zzt(false);
                    return;
                case 3:
                    zzt(true);
                    return;
                default:
                    throw new zzg("Could not determine ad overlay type.");
            }
        } catch (zzg e) {
            zzane.zzdk(e.getMessage());
            this.zzbxx = 3;
            this.mActivity.finish();
        }
    }

    public final void onDestroy() {
        if (this.zzbnd != null) {
            this.zzbxv.removeView(this.zzbnd.getView());
        }
        zznl();
    }

    public final void onPause() {
        zznh();
        if (this.zzbxn.zzbyn != null) {
            this.zzbxn.zzbyn.onPause();
        }
        if (!(((Boolean) zzkb.zzik().zzd(zznk.zzbem)).booleanValue() || this.zzbnd == null || (this.mActivity.isFinishing() && this.zzbxo != null))) {
            zzbv.zzem();
            zzakq.zzi(this.zzbnd);
        }
        zznl();
    }

    public final void onRestart() {
    }

    public final void onResume() {
        if (this.zzbxn.zzbyn != null) {
            this.zzbxn.zzbyn.onResume();
        }
        if (!((Boolean) zzkb.zzik().zzd(zznk.zzbem)).booleanValue()) {
            if (this.zzbnd == null || this.zzbnd.isDestroyed()) {
                zzane.zzdk("The webview does not exist. Ignoring action.");
                return;
            }
            zzbv.zzem();
            zzakq.zzj(this.zzbnd);
        }
    }

    public final void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", this.zzbxt);
    }

    public final void onStart() {
        if (!((Boolean) zzkb.zzik().zzd(zznk.zzbem)).booleanValue()) {
            return;
        }
        if (this.zzbnd == null || this.zzbnd.isDestroyed()) {
            zzane.zzdk("The webview does not exist. Ignoring action.");
            return;
        }
        zzbv.zzem();
        zzakq.zzj(this.zzbnd);
    }

    public final void onStop() {
        if (((Boolean) zzkb.zzik().zzd(zznk.zzbem)).booleanValue() && this.zzbnd != null && (!this.mActivity.isFinishing() || this.zzbxo == null)) {
            zzbv.zzem();
            zzakq.zzi(this.zzbnd);
        }
        zznl();
    }

    public final void setRequestedOrientation(int i) {
        if (this.mActivity.getApplicationInfo().targetSdkVersion >= ((Integer) zzkb.zzik().zzd(zznk.zzbfs)).intValue()) {
            if (this.mActivity.getApplicationInfo().targetSdkVersion <= ((Integer) zzkb.zzik().zzd(zznk.zzbft)).intValue()) {
                if (VERSION.SDK_INT >= ((Integer) zzkb.zzik().zzd(zznk.zzbfu)).intValue()) {
                    if (VERSION.SDK_INT <= ((Integer) zzkb.zzik().zzd(zznk.zzbfv)).intValue()) {
                        return;
                    }
                }
            }
        }
        this.mActivity.setRequestedOrientation(i);
    }

    public final void zza(View view, CustomViewCallback customViewCallback) {
        this.zzbxr = new FrameLayout(this.mActivity);
        this.zzbxr.setBackgroundColor(-16777216);
        this.zzbxr.addView(view, -1, -1);
        this.mActivity.setContentView(this.zzbxr);
        this.zzbyb = true;
        this.zzbxs = customViewCallback;
        this.zzbxq = true;
    }

    public final void zza(boolean z, boolean z2) {
        boolean z3 = false;
        boolean z4 = ((Boolean) zzkb.zzik().zzd(zznk.zzays)).booleanValue() && this.zzbxn != null && this.zzbxn.zzbyw != null && this.zzbxn.zzbyw.zzzl;
        boolean z5 = ((Boolean) zzkb.zzik().zzd(zznk.zzayt)).booleanValue() && this.zzbxn != null && this.zzbxn.zzbyw != null && this.zzbxn.zzbyw.zzzm;
        if (z && z2 && z4 && !z5) {
            new zzaal(this.zzbnd, "useCustomClose").zzbw("Custom close has been disabled for interstitial ads in this ad slot.");
        }
        if (this.zzbxp != null) {
            zzo com_google_android_gms_ads_internal_overlay_zzo = this.zzbxp;
            if (z5 || (z2 && !z4)) {
                z3 = true;
            }
            com_google_android_gms_ads_internal_overlay_zzo.zzu(z3);
        }
    }

    public final void zzax() {
        this.zzbyb = true;
    }

    public final void zznh() {
        if (this.zzbxn != null && this.zzbxq) {
            setRequestedOrientation(this.zzbxn.orientation);
        }
        if (this.zzbxr != null) {
            this.mActivity.setContentView(this.zzbxv);
            this.zzbyb = true;
            this.zzbxr.removeAllViews();
            this.zzbxr = null;
        }
        if (this.zzbxs != null) {
            this.zzbxs.onCustomViewHidden();
            this.zzbxs = null;
        }
        this.zzbxq = false;
    }

    public final void zzni() {
        this.zzbxx = 1;
        this.mActivity.finish();
    }

    public final boolean zznj() {
        this.zzbxx = 0;
        if (this.zzbnd == null) {
            return true;
        }
        boolean zzul = this.zzbnd.zzul();
        if (zzul) {
            return zzul;
        }
        this.zzbnd.zza("onbackblocked", Collections.emptyMap());
        return zzul;
    }

    public final void zznk() {
        this.zzbxv.removeView(this.zzbxp);
        zzs(true);
    }

    @VisibleForTesting
    final void zznm() {
        if (!this.zzbyd) {
            this.zzbyd = true;
            if (this.zzbnd != null) {
                this.zzbxv.removeView(this.zzbnd.getView());
                if (this.zzbxo != null) {
                    this.zzbnd.zzbm(this.zzbxo.zzrt);
                    this.zzbnd.zzai(false);
                    this.zzbxo.parent.addView(this.zzbnd.getView(), this.zzbxo.index, this.zzbxo.zzbyi);
                    this.zzbxo = null;
                } else if (this.mActivity.getApplicationContext() != null) {
                    this.zzbnd.zzbm(this.mActivity.getApplicationContext());
                }
                this.zzbnd = null;
            }
            if (this.zzbxn != null && this.zzbxn.zzbyn != null) {
                this.zzbxn.zzbyn.zzcb();
            }
        }
    }

    public final void zznn() {
        if (this.zzbxw) {
            this.zzbxw = false;
            zzno();
        }
    }

    public final void zznp() {
        this.zzbxv.zzbyh = true;
    }

    public final void zznq() {
        synchronized (this.zzbxy) {
            this.zzbya = true;
            if (this.zzbxz != null) {
                zzakk.zzcrm.removeCallbacks(this.zzbxz);
                zzakk.zzcrm.post(this.zzbxz);
            }
        }
    }

    public final void zzo(IObjectWrapper iObjectWrapper) {
        if (((Boolean) zzkb.zzik().zzd(zznk.zzbel)).booleanValue() && PlatformVersion.isAtLeastN()) {
            Configuration configuration = (Configuration) ObjectWrapper.unwrap(iObjectWrapper);
            zzbv.zzek();
            if (zzakk.zza(this.mActivity, configuration)) {
                this.mActivity.getWindow().addFlags(1024);
                this.mActivity.getWindow().clearFlags(2048);
                return;
            }
            this.mActivity.getWindow().addFlags(2048);
            this.mActivity.getWindow().clearFlags(1024);
        }
    }
}
