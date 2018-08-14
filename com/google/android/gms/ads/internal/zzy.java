package com.google.android.gms.ads.internal;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.webkit.WebView;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzait;
import com.google.android.gms.internal.ads.zzaix;
import com.google.android.gms.internal.ads.zzajh;
import com.google.android.gms.internal.ads.zzaji;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zzarg;
import com.google.android.gms.internal.ads.zzasi;
import com.google.android.gms.internal.ads.zzfp;
import com.google.android.gms.internal.ads.zzjj;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zzlo;
import com.google.android.gms.internal.ads.zzxn;
import com.google.android.gms.internal.ads.zzxz;
import com.google.android.gms.internal.ads.zzyc;
import java.lang.ref.WeakReference;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

@zzadh
@ParametersAreNonnullByDefault
public final class zzy extends zzi implements OnGlobalLayoutListener, OnScrollChangedListener {
    private boolean zzvm;
    private boolean zzxf;
    private WeakReference<Object> zzxg = new WeakReference(null);

    public zzy(Context context, zzjn com_google_android_gms_internal_ads_zzjn, String str, zzxn com_google_android_gms_internal_ads_zzxn, zzang com_google_android_gms_internal_ads_zzang, zzw com_google_android_gms_ads_internal_zzw) {
        super(context, com_google_android_gms_internal_ads_zzjn, str, com_google_android_gms_internal_ads_zzxn, com_google_android_gms_internal_ads_zzang, com_google_android_gms_ads_internal_zzw);
    }

    private final void zzc(zzaqw com_google_android_gms_internal_ads_zzaqw) {
        if (zzcp()) {
            WebView webView = com_google_android_gms_internal_ads_zzaqw.getWebView();
            if (webView != null) {
                View view = com_google_android_gms_internal_ads_zzaqw.getView();
                if (view != null && zzbv.zzfa().zzi(this.zzvw.zzrt)) {
                    int i = this.zzvw.zzacr.zzcve;
                    this.zzwb = zzbv.zzfa().zza(i + "." + this.zzvw.zzacr.zzcvf, webView, "", "javascript", zzbz());
                    if (this.zzwb != null) {
                        zzbv.zzfa().zza(this.zzwb, view);
                        zzbv.zzfa().zzm(this.zzwb);
                        this.zzxf = true;
                    }
                }
            }
        }
    }

    private final boolean zzd(@Nullable zzajh com_google_android_gms_internal_ads_zzajh, zzajh com_google_android_gms_internal_ads_zzajh2) {
        if (com_google_android_gms_internal_ads_zzajh2.zzceq) {
            View zze = zzas.zze(com_google_android_gms_internal_ads_zzajh2);
            if (zze == null) {
                zzane.zzdk("Could not get mediation view");
                return false;
            }
            View nextView = this.zzvw.zzacs.getNextView();
            if (nextView != null) {
                if (nextView instanceof zzaqw) {
                    ((zzaqw) nextView).destroy();
                }
                this.zzvw.zzacs.removeView(nextView);
            }
            if (!zzas.zzf(com_google_android_gms_internal_ads_zzajh2)) {
                try {
                    if (zzbv.zzfh().zzt(this.zzvw.zzrt)) {
                        new zzfp(this.zzvw.zzrt, zze).zza(new zzaix(this.zzvw.zzrt, this.zzvw.zzacp));
                    }
                    if (com_google_android_gms_internal_ads_zzajh2.zzcof != null) {
                        this.zzvw.zzacs.setMinimumWidth(com_google_android_gms_internal_ads_zzajh2.zzcof.widthPixels);
                        this.zzvw.zzacs.setMinimumHeight(com_google_android_gms_internal_ads_zzajh2.zzcof.heightPixels);
                    }
                    zzg(zze);
                } catch (Throwable e) {
                    zzbv.zzeo().zza(e, "BannerAdManager.swapViews");
                    zzane.zzc("Could not add mediation view to view hierarchy.", e);
                    return false;
                }
            }
        } else if (!(com_google_android_gms_internal_ads_zzajh2.zzcof == null || com_google_android_gms_internal_ads_zzajh2.zzbyo == null)) {
            com_google_android_gms_internal_ads_zzajh2.zzbyo.zza(zzasi.zzb(com_google_android_gms_internal_ads_zzajh2.zzcof));
            this.zzvw.zzacs.removeAllViews();
            this.zzvw.zzacs.setMinimumWidth(com_google_android_gms_internal_ads_zzajh2.zzcof.widthPixels);
            this.zzvw.zzacs.setMinimumHeight(com_google_android_gms_internal_ads_zzajh2.zzcof.heightPixels);
            zzg(com_google_android_gms_internal_ads_zzajh2.zzbyo.getView());
        }
        if (this.zzvw.zzacs.getChildCount() > 1) {
            this.zzvw.zzacs.showNext();
        }
        if (com_google_android_gms_internal_ads_zzajh != null) {
            View nextView2 = this.zzvw.zzacs.getNextView();
            if (nextView2 instanceof zzaqw) {
                ((zzaqw) nextView2).destroy();
            } else if (nextView2 != null) {
                this.zzvw.zzacs.removeView(nextView2);
            }
            this.zzvw.zzfn();
        }
        this.zzvw.zzacs.setVisibility(0);
        return true;
    }

    @Nullable
    public final zzlo getVideoController() {
        Preconditions.checkMainThread("getVideoController must be called from the main thread.");
        return (this.zzvw.zzacw == null || this.zzvw.zzacw.zzbyo == null) ? null : this.zzvw.zzacw.zzbyo.zztm();
    }

    public final void onGlobalLayout() {
        zzd(this.zzvw.zzacw);
    }

    public final void onScrollChanged() {
        zzd(this.zzvw.zzacw);
    }

    public final void setManualImpressionsEnabled(boolean z) {
        Preconditions.checkMainThread("setManualImpressionsEnabled must be called from the main thread.");
        this.zzvm = z;
    }

    public final void showInterstitial() {
        throw new IllegalStateException("Interstitial is NOT supported by BannerAdManager.");
    }

    protected final zzaqw zza(zzaji com_google_android_gms_internal_ads_zzaji, @Nullable zzx com_google_android_gms_ads_internal_zzx, @Nullable zzait com_google_android_gms_internal_ads_zzait) throws zzarg {
        if (this.zzvw.zzacv.zzard == null && this.zzvw.zzacv.zzarf) {
            zzjn com_google_android_gms_internal_ads_zzjn;
            zzbw com_google_android_gms_ads_internal_zzbw = this.zzvw;
            if (com_google_android_gms_internal_ads_zzaji.zzcos.zzarf) {
                com_google_android_gms_internal_ads_zzjn = this.zzvw.zzacv;
            } else {
                AdSize adSize;
                String str = com_google_android_gms_internal_ads_zzaji.zzcos.zzcet;
                if (str != null) {
                    String[] split = str.split("[xX]");
                    split[0] = split[0].trim();
                    split[1] = split[1].trim();
                    adSize = new AdSize(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                } else {
                    adSize = this.zzvw.zzacv.zzhy();
                }
                com_google_android_gms_internal_ads_zzjn = new zzjn(this.zzvw.zzrt, adSize);
            }
            com_google_android_gms_ads_internal_zzbw.zzacv = com_google_android_gms_internal_ads_zzjn;
        }
        return super.zza(com_google_android_gms_internal_ads_zzaji, com_google_android_gms_ads_internal_zzx, com_google_android_gms_internal_ads_zzait);
    }

    protected final void zza(@Nullable zzajh com_google_android_gms_internal_ads_zzajh, boolean z) {
        zzyc com_google_android_gms_internal_ads_zzyc = null;
        if (zzcp()) {
            zzaqw com_google_android_gms_internal_ads_zzaqw = com_google_android_gms_internal_ads_zzajh != null ? com_google_android_gms_internal_ads_zzajh.zzbyo : null;
            if (com_google_android_gms_internal_ads_zzaqw != null) {
                if (!this.zzxf) {
                    zzc(com_google_android_gms_internal_ads_zzaqw);
                }
                if (this.zzwb != null) {
                    com_google_android_gms_internal_ads_zzaqw.zza("onSdkImpression", new ArrayMap());
                }
            }
        }
        super.zza(com_google_android_gms_internal_ads_zzajh, z);
        if (zzas.zzf(com_google_android_gms_internal_ads_zzajh)) {
            zzac com_google_android_gms_ads_internal_zzac = new zzac(this);
            if (com_google_android_gms_internal_ads_zzajh != null && zzas.zzf(com_google_android_gms_internal_ads_zzajh)) {
                zzaqw com_google_android_gms_internal_ads_zzaqw2 = com_google_android_gms_internal_ads_zzajh.zzbyo;
                Object view = com_google_android_gms_internal_ads_zzaqw2 != null ? com_google_android_gms_internal_ads_zzaqw2.getView() : null;
                if (view == null) {
                    zzane.zzdk("AdWebView is null");
                    return;
                }
                try {
                    List list = com_google_android_gms_internal_ads_zzajh.zzbtw != null ? com_google_android_gms_internal_ads_zzajh.zzbtw.zzbsi : null;
                    if (list == null || list.isEmpty()) {
                        zzane.zzdk("No template ids present in mediation response");
                        return;
                    }
                    zzxz zzmo = com_google_android_gms_internal_ads_zzajh.zzbtx != null ? com_google_android_gms_internal_ads_zzajh.zzbtx.zzmo() : null;
                    if (com_google_android_gms_internal_ads_zzajh.zzbtx != null) {
                        com_google_android_gms_internal_ads_zzyc = com_google_android_gms_internal_ads_zzajh.zzbtx.zzmp();
                    }
                    if (list.contains("2") && zzmo != null) {
                        zzmo.zzk(ObjectWrapper.wrap(view));
                        if (!zzmo.getOverrideImpressionRecording()) {
                            zzmo.recordImpression();
                        }
                        com_google_android_gms_internal_ads_zzaqw2.zza("/nativeExpressViewClicked", zzas.zza(zzmo, null, com_google_android_gms_ads_internal_zzac));
                    } else if (!list.contains(SchemaSymbols.ATTVAL_TRUE_1) || com_google_android_gms_internal_ads_zzyc == null) {
                        zzane.zzdk("No matching template id and mapper");
                    } else {
                        com_google_android_gms_internal_ads_zzyc.zzk(ObjectWrapper.wrap(view));
                        if (!com_google_android_gms_internal_ads_zzyc.getOverrideImpressionRecording()) {
                            com_google_android_gms_internal_ads_zzyc.recordImpression();
                        }
                        com_google_android_gms_internal_ads_zzaqw2.zza("/nativeExpressViewClicked", zzas.zza(null, com_google_android_gms_internal_ads_zzyc, com_google_android_gms_ads_internal_zzac));
                    }
                } catch (Throwable e) {
                    zzane.zzc("Error occurred while recording impression and registering for clicks", e);
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zza(@android.support.annotation.Nullable com.google.android.gms.internal.ads.zzajh r6, com.google.android.gms.internal.ads.zzajh r7) {
        /*
        r5 = this;
        r1 = 0;
        r2 = 0;
        r0 = super.zza(r6, r7);
        if (r0 != 0) goto L_0x000a;
    L_0x0008:
        r0 = r2;
    L_0x0009:
        return r0;
    L_0x000a:
        r0 = r5.zzvw;
        r0 = r0.zzfo();
        if (r0 == 0) goto L_0x0028;
    L_0x0012:
        r0 = r5.zzd(r6, r7);
        if (r0 != 0) goto L_0x0028;
    L_0x0018:
        r0 = r7.zzcoq;
        if (r0 == 0) goto L_0x0023;
    L_0x001c:
        r0 = r7.zzcoq;
        r1 = com.google.android.gms.internal.ads.zzhu.zza.zzb.zzakl;
        r0.zza(r1);
    L_0x0023:
        r5.zzi(r2);
        r0 = r2;
        goto L_0x0009;
    L_0x0028:
        r5.zzb(r7, r2);
        r0 = r7.zzcfh;
        if (r0 == 0) goto L_0x00ff;
    L_0x002f:
        r5.zzd(r7);
        com.google.android.gms.ads.internal.zzbv.zzfg();
        r0 = r5.zzvw;
        r0 = r0.zzacs;
        com.google.android.gms.internal.ads.zzaor.zza(r0, r5);
        com.google.android.gms.ads.internal.zzbv.zzfg();
        r0 = r5.zzvw;
        r0 = r0.zzacs;
        com.google.android.gms.internal.ads.zzaor.zza(r0, r5);
        r0 = r7.zzcoc;
        if (r0 != 0) goto L_0x0063;
    L_0x004a:
        r2 = new com.google.android.gms.ads.internal.zzab;
        r2.<init>(r5);
        r0 = r7.zzbyo;
        if (r0 == 0) goto L_0x00fc;
    L_0x0053:
        r0 = r7.zzbyo;
        r0 = r0.zzuf();
    L_0x0059:
        if (r0 == 0) goto L_0x0063;
    L_0x005b:
        r3 = new com.google.android.gms.ads.internal.zzz;
        r3.<init>(r7, r2);
        r0.zza(r3);
    L_0x0063:
        r0 = r7.zzbyo;
        if (r0 == 0) goto L_0x0087;
    L_0x0067:
        r0 = r7.zzbyo;
        r0 = r0.zztm();
        r2 = r7.zzbyo;
        r2 = r2.zzuf();
        if (r2 == 0) goto L_0x0078;
    L_0x0075:
        r2.zzuz();
    L_0x0078:
        r2 = r5.zzvw;
        r2 = r2.zzadk;
        if (r2 == 0) goto L_0x0087;
    L_0x007e:
        if (r0 == 0) goto L_0x0087;
    L_0x0080:
        r2 = r5.zzvw;
        r2 = r2.zzadk;
        r0.zzb(r2);
    L_0x0087:
        r0 = com.google.android.gms.common.util.PlatformVersion.isAtLeastIceCreamSandwich();
        if (r0 == 0) goto L_0x00f9;
    L_0x008d:
        r0 = r5.zzvw;
        r0 = r0.zzfo();
        if (r0 == 0) goto L_0x012d;
    L_0x0095:
        r0 = r7.zzbyo;
        if (r0 == 0) goto L_0x0149;
    L_0x0099:
        r0 = r7.zzcob;
        if (r0 == 0) goto L_0x00a6;
    L_0x009d:
        r0 = r5.zzvy;
        r1 = r5.zzvw;
        r1 = r1.zzacv;
        r0.zza(r1, r7);
    L_0x00a6:
        r0 = r7.zzbyo;
        r0 = r0.getView();
        r1 = new com.google.android.gms.internal.ads.zzfp;
        r2 = r5.zzvw;
        r2 = r2.zzrt;
        r1.<init>(r2, r0);
        r2 = com.google.android.gms.ads.internal.zzbv.zzfh();
        r3 = r5.zzvw;
        r3 = r3.zzrt;
        r2 = r2.zzt(r3);
        if (r2 == 0) goto L_0x00e5;
    L_0x00c3:
        r2 = r7.zzccv;
        r2 = com.google.android.gms.ads.internal.zza.zza(r2);
        if (r2 == 0) goto L_0x00e5;
    L_0x00cb:
        r2 = r5.zzvw;
        r2 = r2.zzacp;
        r2 = android.text.TextUtils.isEmpty(r2);
        if (r2 != 0) goto L_0x00e5;
    L_0x00d5:
        r2 = new com.google.android.gms.internal.ads.zzaix;
        r3 = r5.zzvw;
        r3 = r3.zzrt;
        r4 = r5.zzvw;
        r4 = r4.zzacp;
        r2.<init>(r3, r4);
        r1.zza(r2);
    L_0x00e5:
        r2 = r7.zzfz();
        if (r2 == 0) goto L_0x011e;
    L_0x00eb:
        r2 = r7.zzbyo;
        r1.zza(r2);
    L_0x00f0:
        r1 = r7.zzceq;
        if (r1 != 0) goto L_0x00f9;
    L_0x00f4:
        r1 = r5.zzvw;
        r1.zzj(r0);
    L_0x00f9:
        r0 = 1;
        goto L_0x0009;
    L_0x00fc:
        r0 = r1;
        goto L_0x0059;
    L_0x00ff:
        r0 = r5.zzvw;
        r0 = r0.zzfp();
        if (r0 == 0) goto L_0x0119;
    L_0x0107:
        r0 = com.google.android.gms.internal.ads.zznk.zzbbo;
        r3 = com.google.android.gms.internal.ads.zzkb.zzik();
        r0 = r3.zzd(r0);
        r0 = (java.lang.Boolean) r0;
        r0 = r0.booleanValue();
        if (r0 == 0) goto L_0x0063;
    L_0x0119:
        r5.zza(r7, r2);
        goto L_0x0063;
    L_0x011e:
        r2 = r7.zzbyo;
        r2 = r2.zzuf();
        r3 = new com.google.android.gms.ads.internal.zzaa;
        r3.<init>(r1, r7);
        r2.zza(r3);
        goto L_0x00f0;
    L_0x012d:
        r0 = r5.zzvw;
        r0 = r0.zzadu;
        if (r0 == 0) goto L_0x0149;
    L_0x0133:
        r0 = r7.zzcob;
        if (r0 == 0) goto L_0x0149;
    L_0x0137:
        r0 = r5.zzvy;
        r1 = r5.zzvw;
        r1 = r1.zzacv;
        r2 = r5.zzvw;
        r2 = r2.zzadu;
        r0.zza(r1, r7, r2);
        r0 = r5.zzvw;
        r0 = r0.zzadu;
        goto L_0x00f0;
    L_0x0149:
        r0 = r1;
        goto L_0x00f0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzy.zza(com.google.android.gms.internal.ads.zzajh, com.google.android.gms.internal.ads.zzajh):boolean");
    }

    public final boolean zzb(zzjj com_google_android_gms_internal_ads_zzjj) {
        if (com_google_android_gms_internal_ads_zzjj.zzaqb != this.zzvm) {
            int i = com_google_android_gms_internal_ads_zzjj.versionCode;
            long j = com_google_android_gms_internal_ads_zzjj.zzapw;
            Bundle bundle = com_google_android_gms_internal_ads_zzjj.extras;
            int i2 = com_google_android_gms_internal_ads_zzjj.zzapx;
            List list = com_google_android_gms_internal_ads_zzjj.zzapy;
            boolean z = com_google_android_gms_internal_ads_zzjj.zzapz;
            int i3 = com_google_android_gms_internal_ads_zzjj.zzaqa;
            boolean z2 = com_google_android_gms_internal_ads_zzjj.zzaqb || this.zzvm;
            com_google_android_gms_internal_ads_zzjj = new zzjj(i, j, bundle, i2, list, z, i3, z2, com_google_android_gms_internal_ads_zzjj.zzaqc, com_google_android_gms_internal_ads_zzjj.zzaqd, com_google_android_gms_internal_ads_zzjj.zzaqe, com_google_android_gms_internal_ads_zzjj.zzaqf, com_google_android_gms_internal_ads_zzjj.zzaqg, com_google_android_gms_internal_ads_zzjj.zzaqh, com_google_android_gms_internal_ads_zzjj.zzaqi, com_google_android_gms_internal_ads_zzjj.zzaqj, com_google_android_gms_internal_ads_zzjj.zzaqk, com_google_android_gms_internal_ads_zzjj.zzaql);
        }
        return super.zzb(com_google_android_gms_internal_ads_zzjj);
    }

    protected final void zzbq() {
        zzaqw com_google_android_gms_internal_ads_zzaqw = this.zzvw.zzacw != null ? this.zzvw.zzacw.zzbyo : null;
        if (!(this.zzxf || com_google_android_gms_internal_ads_zzaqw == null)) {
            zzc(com_google_android_gms_internal_ads_zzaqw);
        }
        super.zzbq();
    }

    protected final boolean zzca() {
        boolean z = true;
        zzbv.zzek();
        if (!zzakk.zzl(this.zzvw.zzrt, "android.permission.INTERNET")) {
            zzkb.zzif().zza(this.zzvw.zzacs, this.zzvw.zzacv, "Missing internet permission in AndroidManifest.xml.", "Missing internet permission in AndroidManifest.xml. You must have the following declaration: <uses-permission android:name=\"android.permission.INTERNET\" />");
            z = false;
        }
        zzbv.zzek();
        if (!zzakk.zzaj(this.zzvw.zzrt)) {
            zzkb.zzif().zza(this.zzvw.zzacs, this.zzvw.zzacv, "Missing AdActivity with android:configChanges in AndroidManifest.xml.", "Missing AdActivity with android:configChanges in AndroidManifest.xml. You must have the following declaration within the <application> element: <activity android:name=\"com.google.android.gms.ads.AdActivity\" android:configChanges=\"keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize\" />");
            z = false;
        }
        if (!(z || this.zzvw.zzacs == null)) {
            this.zzvw.zzacs.setVisibility(0);
        }
        return z;
    }

    public final void zzcz() {
        this.zzvv.zzdy();
    }

    @VisibleForTesting
    final void zzd(@Nullable zzajh com_google_android_gms_internal_ads_zzajh) {
        if (com_google_android_gms_internal_ads_zzajh != null && !com_google_android_gms_internal_ads_zzajh.zzcoc && this.zzvw.zzacs != null && zzbv.zzek().zza(this.zzvw.zzacs, this.zzvw.zzrt) && this.zzvw.zzacs.getGlobalVisibleRect(new Rect(), null)) {
            if (!(com_google_android_gms_internal_ads_zzajh == null || com_google_android_gms_internal_ads_zzajh.zzbyo == null || com_google_android_gms_internal_ads_zzajh.zzbyo.zzuf() == null)) {
                com_google_android_gms_internal_ads_zzajh.zzbyo.zzuf().zza(null);
            }
            zza(com_google_android_gms_internal_ads_zzajh, false);
            com_google_android_gms_internal_ads_zzajh.zzcoc = true;
        }
    }
}
