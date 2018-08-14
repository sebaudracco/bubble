package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.CollectionUtils;
import java.util.Set;

@zzadh
public final class zzaab extends zzaal {
    private static final Set<String> zzbvy = CollectionUtils.setOf("top-left", "top-right", "top-center", "center", "bottom-left", "bottom-right", "bottom-center");
    private final Object mLock = new Object();
    private zzaam zzbmy;
    private final zzaqw zzbnd;
    private final Activity zzbvp;
    private String zzbvz = "top-right";
    private boolean zzbwa = true;
    private int zzbwb = 0;
    private int zzbwc = 0;
    private int zzbwd = 0;
    private int zzbwe = 0;
    private zzasi zzbwf;
    private ImageView zzbwg;
    private LinearLayout zzbwh;
    private PopupWindow zzbwi;
    private RelativeLayout zzbwj;
    private ViewGroup zzbwk;
    private int zzuq = -1;
    private int zzur = -1;

    public zzaab(zzaqw com_google_android_gms_internal_ads_zzaqw, zzaam com_google_android_gms_internal_ads_zzaam) {
        super(com_google_android_gms_internal_ads_zzaqw, "resize");
        this.zzbnd = com_google_android_gms_internal_ads_zzaqw;
        this.zzbvp = com_google_android_gms_internal_ads_zzaqw.zzto();
        this.zzbmy = com_google_android_gms_internal_ads_zzaam;
    }

    private final void zza(int i, int i2) {
        zzb(i, i2 - zzbv.zzek().zzh(this.zzbvp)[0], this.zzuq, this.zzur);
    }

    private final int[] zzne() {
        int i;
        int[] zzg = zzbv.zzek().zzg(this.zzbvp);
        int[] zzh = zzbv.zzek().zzh(this.zzbvp);
        int i2 = zzg[0];
        int i3 = zzg[1];
        if (this.zzuq < 50 || this.zzuq > i2) {
            zzane.zzdk("Width is too small or too large.");
            i3 = 0;
        } else if (this.zzur < 50 || this.zzur > i3) {
            zzane.zzdk("Height is too small or too large.");
            i3 = 0;
        } else if (this.zzur == i3 && this.zzuq == i2) {
            zzane.zzdk("Cannot resize to a full-screen ad.");
            i3 = 0;
        } else {
            if (this.zzbwa) {
                String str = this.zzbvz;
                i3 = -1;
                switch (str.hashCode()) {
                    case -1364013995:
                        if (str.equals("center")) {
                            i3 = 2;
                            break;
                        }
                        break;
                    case -1012429441:
                        if (str.equals("top-left")) {
                            i3 = 0;
                            break;
                        }
                        break;
                    case -655373719:
                        if (str.equals("bottom-left")) {
                            i3 = 3;
                            break;
                        }
                        break;
                    case 1163912186:
                        if (str.equals("bottom-right")) {
                            i3 = 5;
                            break;
                        }
                        break;
                    case 1288627767:
                        if (str.equals("bottom-center")) {
                            i3 = 4;
                            break;
                        }
                        break;
                    case 1755462605:
                        if (str.equals("top-center")) {
                            i3 = 1;
                            break;
                        }
                        break;
                }
                switch (i3) {
                    case 0:
                        i = this.zzbwd + this.zzbwb;
                        i3 = this.zzbwc + this.zzbwe;
                        break;
                    case 1:
                        i = ((this.zzbwb + this.zzbwd) + (this.zzuq / 2)) - 25;
                        i3 = this.zzbwc + this.zzbwe;
                        break;
                    case 2:
                        i = ((this.zzbwb + this.zzbwd) + (this.zzuq / 2)) - 25;
                        i3 = ((this.zzbwc + this.zzbwe) + (this.zzur / 2)) - 25;
                        break;
                    case 3:
                        i = this.zzbwd + this.zzbwb;
                        i3 = ((this.zzbwc + this.zzbwe) + this.zzur) - 50;
                        break;
                    case 4:
                        i = ((this.zzbwb + this.zzbwd) + (this.zzuq / 2)) - 25;
                        i3 = ((this.zzbwc + this.zzbwe) + this.zzur) - 50;
                        break;
                    case 5:
                        i = ((this.zzbwb + this.zzbwd) + this.zzuq) - 50;
                        i3 = ((this.zzbwc + this.zzbwe) + this.zzur) - 50;
                        break;
                    default:
                        i = ((this.zzbwb + this.zzbwd) + this.zzuq) - 50;
                        i3 = this.zzbwc + this.zzbwe;
                        break;
                }
                if (i < 0 || i + 50 > i2 || r0 < zzh[0] || r0 + 50 > zzh[1]) {
                    i3 = 0;
                }
            }
            i3 = 1;
        }
        if (i3 == 0) {
            return null;
        }
        if (this.zzbwa) {
            return new int[]{this.zzbwb + this.zzbwd, this.zzbwc + this.zzbwe};
        }
        zzg = zzbv.zzek().zzg(this.zzbvp);
        zzh = zzbv.zzek().zzh(this.zzbvp);
        i2 = zzg[0];
        i3 = this.zzbwb + this.zzbwd;
        i = this.zzbwc + this.zzbwe;
        if (i3 < 0) {
            i3 = 0;
        } else if (this.zzuq + i3 > i2) {
            i3 = i2 - this.zzuq;
        }
        if (i < zzh[0]) {
            i = zzh[0];
        } else if (this.zzur + i > zzh[1]) {
            i = zzh[1] - this.zzur;
        }
        return new int[]{i3, i};
    }

    public final void zza(int i, int i2, boolean z) {
        synchronized (this.mLock) {
            this.zzbwb = i;
            this.zzbwc = i2;
            if (this.zzbwi != null && z) {
                int[] zzne = zzne();
                if (zzne != null) {
                    PopupWindow popupWindow = this.zzbwi;
                    zzkb.zzif();
                    int zza = zzamu.zza(this.zzbvp, zzne[0]);
                    zzkb.zzif();
                    popupWindow.update(zza, zzamu.zza(this.zzbvp, zzne[1]), this.zzbwi.getWidth(), this.zzbwi.getHeight());
                    zza(zzne[0], zzne[1]);
                } else {
                    zzm(true);
                }
            }
        }
    }

    public final void zzb(int i, int i2) {
        this.zzbwb = i;
        this.zzbwc = i2;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzk(java.util.Map<java.lang.String, java.lang.String> r14) {
        /*
        r13 = this;
        r4 = -1;
        r5 = 1;
        r3 = 0;
        r6 = r13.mLock;
        monitor-enter(r6);
        r1 = r13.zzbvp;	 Catch:{ all -> 0x0022 }
        if (r1 != 0) goto L_0x0012;
    L_0x000a:
        r1 = "Not an activity context. Cannot resize.";
        r13.zzbw(r1);	 Catch:{ all -> 0x0022 }
        monitor-exit(r6);	 Catch:{ all -> 0x0022 }
    L_0x0011:
        return;
    L_0x0012:
        r1 = r13.zzbnd;	 Catch:{ all -> 0x0022 }
        r1 = r1.zzud();	 Catch:{ all -> 0x0022 }
        if (r1 != 0) goto L_0x0025;
    L_0x001a:
        r1 = "Webview is not yet available, size is not set.";
        r13.zzbw(r1);	 Catch:{ all -> 0x0022 }
        monitor-exit(r6);	 Catch:{ all -> 0x0022 }
        goto L_0x0011;
    L_0x0022:
        r1 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x0022 }
        throw r1;
    L_0x0025:
        r1 = r13.zzbnd;	 Catch:{ all -> 0x0022 }
        r1 = r1.zzud();	 Catch:{ all -> 0x0022 }
        r1 = r1.zzvs();	 Catch:{ all -> 0x0022 }
        if (r1 == 0) goto L_0x0039;
    L_0x0031:
        r1 = "Is interstitial. Cannot resize an interstitial.";
        r13.zzbw(r1);	 Catch:{ all -> 0x0022 }
        monitor-exit(r6);	 Catch:{ all -> 0x0022 }
        goto L_0x0011;
    L_0x0039:
        r1 = r13.zzbnd;	 Catch:{ all -> 0x0022 }
        r1 = r1.zzuj();	 Catch:{ all -> 0x0022 }
        if (r1 == 0) goto L_0x0049;
    L_0x0041:
        r1 = "Cannot resize an expanded banner.";
        r13.zzbw(r1);	 Catch:{ all -> 0x0022 }
        monitor-exit(r6);	 Catch:{ all -> 0x0022 }
        goto L_0x0011;
    L_0x0049:
        r1 = "width";
        r1 = r14.get(r1);	 Catch:{ all -> 0x0022 }
        r1 = (java.lang.CharSequence) r1;	 Catch:{ all -> 0x0022 }
        r1 = android.text.TextUtils.isEmpty(r1);	 Catch:{ all -> 0x0022 }
        if (r1 != 0) goto L_0x006a;
    L_0x0058:
        com.google.android.gms.ads.internal.zzbv.zzek();	 Catch:{ all -> 0x0022 }
        r1 = "width";
        r1 = r14.get(r1);	 Catch:{ all -> 0x0022 }
        r1 = (java.lang.String) r1;	 Catch:{ all -> 0x0022 }
        r1 = com.google.android.gms.internal.ads.zzakk.zzcv(r1);	 Catch:{ all -> 0x0022 }
        r13.zzuq = r1;	 Catch:{ all -> 0x0022 }
    L_0x006a:
        r1 = "height";
        r1 = r14.get(r1);	 Catch:{ all -> 0x0022 }
        r1 = (java.lang.CharSequence) r1;	 Catch:{ all -> 0x0022 }
        r1 = android.text.TextUtils.isEmpty(r1);	 Catch:{ all -> 0x0022 }
        if (r1 != 0) goto L_0x008b;
    L_0x0079:
        com.google.android.gms.ads.internal.zzbv.zzek();	 Catch:{ all -> 0x0022 }
        r1 = "height";
        r1 = r14.get(r1);	 Catch:{ all -> 0x0022 }
        r1 = (java.lang.String) r1;	 Catch:{ all -> 0x0022 }
        r1 = com.google.android.gms.internal.ads.zzakk.zzcv(r1);	 Catch:{ all -> 0x0022 }
        r13.zzur = r1;	 Catch:{ all -> 0x0022 }
    L_0x008b:
        r1 = "offsetX";
        r1 = r14.get(r1);	 Catch:{ all -> 0x0022 }
        r1 = (java.lang.CharSequence) r1;	 Catch:{ all -> 0x0022 }
        r1 = android.text.TextUtils.isEmpty(r1);	 Catch:{ all -> 0x0022 }
        if (r1 != 0) goto L_0x00ac;
    L_0x009a:
        com.google.android.gms.ads.internal.zzbv.zzek();	 Catch:{ all -> 0x0022 }
        r1 = "offsetX";
        r1 = r14.get(r1);	 Catch:{ all -> 0x0022 }
        r1 = (java.lang.String) r1;	 Catch:{ all -> 0x0022 }
        r1 = com.google.android.gms.internal.ads.zzakk.zzcv(r1);	 Catch:{ all -> 0x0022 }
        r13.zzbwd = r1;	 Catch:{ all -> 0x0022 }
    L_0x00ac:
        r1 = "offsetY";
        r1 = r14.get(r1);	 Catch:{ all -> 0x0022 }
        r1 = (java.lang.CharSequence) r1;	 Catch:{ all -> 0x0022 }
        r1 = android.text.TextUtils.isEmpty(r1);	 Catch:{ all -> 0x0022 }
        if (r1 != 0) goto L_0x00cd;
    L_0x00bb:
        com.google.android.gms.ads.internal.zzbv.zzek();	 Catch:{ all -> 0x0022 }
        r1 = "offsetY";
        r1 = r14.get(r1);	 Catch:{ all -> 0x0022 }
        r1 = (java.lang.String) r1;	 Catch:{ all -> 0x0022 }
        r1 = com.google.android.gms.internal.ads.zzakk.zzcv(r1);	 Catch:{ all -> 0x0022 }
        r13.zzbwe = r1;	 Catch:{ all -> 0x0022 }
    L_0x00cd:
        r1 = "allowOffscreen";
        r1 = r14.get(r1);	 Catch:{ all -> 0x0022 }
        r1 = (java.lang.CharSequence) r1;	 Catch:{ all -> 0x0022 }
        r1 = android.text.TextUtils.isEmpty(r1);	 Catch:{ all -> 0x0022 }
        if (r1 != 0) goto L_0x00eb;
    L_0x00dc:
        r1 = "allowOffscreen";
        r1 = r14.get(r1);	 Catch:{ all -> 0x0022 }
        r1 = (java.lang.String) r1;	 Catch:{ all -> 0x0022 }
        r1 = java.lang.Boolean.parseBoolean(r1);	 Catch:{ all -> 0x0022 }
        r13.zzbwa = r1;	 Catch:{ all -> 0x0022 }
    L_0x00eb:
        r1 = "customClosePosition";
        r1 = r14.get(r1);	 Catch:{ all -> 0x0022 }
        r1 = (java.lang.String) r1;	 Catch:{ all -> 0x0022 }
        r2 = android.text.TextUtils.isEmpty(r1);	 Catch:{ all -> 0x0022 }
        if (r2 != 0) goto L_0x00fc;
    L_0x00fa:
        r13.zzbvz = r1;	 Catch:{ all -> 0x0022 }
    L_0x00fc:
        r1 = r13.zzuq;	 Catch:{ all -> 0x0022 }
        if (r1 < 0) goto L_0x0110;
    L_0x0100:
        r1 = r13.zzur;	 Catch:{ all -> 0x0022 }
        if (r1 < 0) goto L_0x0110;
    L_0x0104:
        r1 = r5;
    L_0x0105:
        if (r1 != 0) goto L_0x0112;
    L_0x0107:
        r1 = "Invalid width and height options. Cannot resize.";
        r13.zzbw(r1);	 Catch:{ all -> 0x0022 }
        monitor-exit(r6);	 Catch:{ all -> 0x0022 }
        goto L_0x0011;
    L_0x0110:
        r1 = r3;
        goto L_0x0105;
    L_0x0112:
        r1 = r13.zzbvp;	 Catch:{ all -> 0x0022 }
        r7 = r1.getWindow();	 Catch:{ all -> 0x0022 }
        if (r7 == 0) goto L_0x0120;
    L_0x011a:
        r1 = r7.getDecorView();	 Catch:{ all -> 0x0022 }
        if (r1 != 0) goto L_0x0129;
    L_0x0120:
        r1 = "Activity context is not ready, cannot get window or decor view.";
        r13.zzbw(r1);	 Catch:{ all -> 0x0022 }
        monitor-exit(r6);	 Catch:{ all -> 0x0022 }
        goto L_0x0011;
    L_0x0129:
        r8 = r13.zzne();	 Catch:{ all -> 0x0022 }
        if (r8 != 0) goto L_0x0138;
    L_0x012f:
        r1 = "Resize location out of screen or close button is not visible.";
        r13.zzbw(r1);	 Catch:{ all -> 0x0022 }
        monitor-exit(r6);	 Catch:{ all -> 0x0022 }
        goto L_0x0011;
    L_0x0138:
        com.google.android.gms.internal.ads.zzkb.zzif();	 Catch:{ all -> 0x0022 }
        r1 = r13.zzbvp;	 Catch:{ all -> 0x0022 }
        r2 = r13.zzuq;	 Catch:{ all -> 0x0022 }
        r9 = com.google.android.gms.internal.ads.zzamu.zza(r1, r2);	 Catch:{ all -> 0x0022 }
        com.google.android.gms.internal.ads.zzkb.zzif();	 Catch:{ all -> 0x0022 }
        r1 = r13.zzbvp;	 Catch:{ all -> 0x0022 }
        r2 = r13.zzur;	 Catch:{ all -> 0x0022 }
        r10 = com.google.android.gms.internal.ads.zzamu.zza(r1, r2);	 Catch:{ all -> 0x0022 }
        r1 = r13.zzbnd;	 Catch:{ all -> 0x0022 }
        r1 = r1.getView();	 Catch:{ all -> 0x0022 }
        r2 = r1.getParent();	 Catch:{ all -> 0x0022 }
        if (r2 == 0) goto L_0x0290;
    L_0x015a:
        r1 = r2 instanceof android.view.ViewGroup;	 Catch:{ all -> 0x0022 }
        if (r1 == 0) goto L_0x0290;
    L_0x015e:
        r0 = r2;
        r0 = (android.view.ViewGroup) r0;	 Catch:{ all -> 0x0022 }
        r1 = r0;
        r11 = r13.zzbnd;	 Catch:{ all -> 0x0022 }
        r11 = r11.getView();	 Catch:{ all -> 0x0022 }
        r1.removeView(r11);	 Catch:{ all -> 0x0022 }
        r1 = r13.zzbwi;	 Catch:{ all -> 0x0022 }
        if (r1 != 0) goto L_0x0289;
    L_0x016f:
        r2 = (android.view.ViewGroup) r2;	 Catch:{ all -> 0x0022 }
        r13.zzbwk = r2;	 Catch:{ all -> 0x0022 }
        com.google.android.gms.ads.internal.zzbv.zzek();	 Catch:{ all -> 0x0022 }
        r1 = r13.zzbnd;	 Catch:{ all -> 0x0022 }
        r1 = r1.getView();	 Catch:{ all -> 0x0022 }
        r1 = com.google.android.gms.internal.ads.zzakk.zzs(r1);	 Catch:{ all -> 0x0022 }
        r2 = new android.widget.ImageView;	 Catch:{ all -> 0x0022 }
        r11 = r13.zzbvp;	 Catch:{ all -> 0x0022 }
        r2.<init>(r11);	 Catch:{ all -> 0x0022 }
        r13.zzbwg = r2;	 Catch:{ all -> 0x0022 }
        r2 = r13.zzbwg;	 Catch:{ all -> 0x0022 }
        r2.setImageBitmap(r1);	 Catch:{ all -> 0x0022 }
        r1 = r13.zzbnd;	 Catch:{ all -> 0x0022 }
        r1 = r1.zzud();	 Catch:{ all -> 0x0022 }
        r13.zzbwf = r1;	 Catch:{ all -> 0x0022 }
        r1 = r13.zzbwk;	 Catch:{ all -> 0x0022 }
        r2 = r13.zzbwg;	 Catch:{ all -> 0x0022 }
        r1.addView(r2);	 Catch:{ all -> 0x0022 }
    L_0x019d:
        r1 = new android.widget.RelativeLayout;	 Catch:{ all -> 0x0022 }
        r2 = r13.zzbvp;	 Catch:{ all -> 0x0022 }
        r1.<init>(r2);	 Catch:{ all -> 0x0022 }
        r13.zzbwj = r1;	 Catch:{ all -> 0x0022 }
        r1 = r13.zzbwj;	 Catch:{ all -> 0x0022 }
        r2 = 0;
        r1.setBackgroundColor(r2);	 Catch:{ all -> 0x0022 }
        r1 = r13.zzbwj;	 Catch:{ all -> 0x0022 }
        r2 = new android.view.ViewGroup$LayoutParams;	 Catch:{ all -> 0x0022 }
        r2.<init>(r9, r10);	 Catch:{ all -> 0x0022 }
        r1.setLayoutParams(r2);	 Catch:{ all -> 0x0022 }
        com.google.android.gms.ads.internal.zzbv.zzek();	 Catch:{ all -> 0x0022 }
        r1 = r13.zzbwj;	 Catch:{ all -> 0x0022 }
        r2 = 0;
        r1 = com.google.android.gms.internal.ads.zzakk.zza(r1, r9, r10, r2);	 Catch:{ all -> 0x0022 }
        r13.zzbwi = r1;	 Catch:{ all -> 0x0022 }
        r1 = r13.zzbwi;	 Catch:{ all -> 0x0022 }
        r2 = 1;
        r1.setOutsideTouchable(r2);	 Catch:{ all -> 0x0022 }
        r1 = r13.zzbwi;	 Catch:{ all -> 0x0022 }
        r2 = 1;
        r1.setTouchable(r2);	 Catch:{ all -> 0x0022 }
        r2 = r13.zzbwi;	 Catch:{ all -> 0x0022 }
        r1 = r13.zzbwa;	 Catch:{ all -> 0x0022 }
        if (r1 != 0) goto L_0x0299;
    L_0x01d4:
        r1 = r5;
    L_0x01d5:
        r2.setClippingEnabled(r1);	 Catch:{ all -> 0x0022 }
        r1 = r13.zzbwj;	 Catch:{ all -> 0x0022 }
        r2 = r13.zzbnd;	 Catch:{ all -> 0x0022 }
        r2 = r2.getView();	 Catch:{ all -> 0x0022 }
        r11 = -1;
        r12 = -1;
        r1.addView(r2, r11, r12);	 Catch:{ all -> 0x0022 }
        r1 = new android.widget.LinearLayout;	 Catch:{ all -> 0x0022 }
        r2 = r13.zzbvp;	 Catch:{ all -> 0x0022 }
        r1.<init>(r2);	 Catch:{ all -> 0x0022 }
        r13.zzbwh = r1;	 Catch:{ all -> 0x0022 }
        r2 = new android.widget.RelativeLayout$LayoutParams;	 Catch:{ all -> 0x0022 }
        com.google.android.gms.internal.ads.zzkb.zzif();	 Catch:{ all -> 0x0022 }
        r1 = r13.zzbvp;	 Catch:{ all -> 0x0022 }
        r11 = 50;
        r1 = com.google.android.gms.internal.ads.zzamu.zza(r1, r11);	 Catch:{ all -> 0x0022 }
        com.google.android.gms.internal.ads.zzkb.zzif();	 Catch:{ all -> 0x0022 }
        r11 = r13.zzbvp;	 Catch:{ all -> 0x0022 }
        r12 = 50;
        r11 = com.google.android.gms.internal.ads.zzamu.zza(r11, r12);	 Catch:{ all -> 0x0022 }
        r2.<init>(r1, r11);	 Catch:{ all -> 0x0022 }
        r1 = r13.zzbvz;	 Catch:{ all -> 0x0022 }
        r11 = r1.hashCode();	 Catch:{ all -> 0x0022 }
        switch(r11) {
            case -1364013995: goto L_0x02b4;
            case -1012429441: goto L_0x029c;
            case -655373719: goto L_0x02c0;
            case 1163912186: goto L_0x02d8;
            case 1288627767: goto L_0x02cc;
            case 1755462605: goto L_0x02a8;
            default: goto L_0x0212;
        };	 Catch:{ all -> 0x0022 }
    L_0x0212:
        r1 = r4;
    L_0x0213:
        switch(r1) {
            case 0: goto L_0x02e4;
            case 1: goto L_0x02f0;
            case 2: goto L_0x02fc;
            case 3: goto L_0x0303;
            case 4: goto L_0x030f;
            case 5: goto L_0x031b;
            default: goto L_0x0216;
        };	 Catch:{ all -> 0x0022 }
    L_0x0216:
        r1 = 10;
        r2.addRule(r1);	 Catch:{ all -> 0x0022 }
        r1 = 11;
        r2.addRule(r1);	 Catch:{ all -> 0x0022 }
    L_0x0220:
        r1 = r13.zzbwh;	 Catch:{ all -> 0x0022 }
        r3 = new com.google.android.gms.internal.ads.zzaac;	 Catch:{ all -> 0x0022 }
        r3.<init>(r13);	 Catch:{ all -> 0x0022 }
        r1.setOnClickListener(r3);	 Catch:{ all -> 0x0022 }
        r1 = r13.zzbwh;	 Catch:{ all -> 0x0022 }
        r3 = "Close button";
        r1.setContentDescription(r3);	 Catch:{ all -> 0x0022 }
        r1 = r13.zzbwj;	 Catch:{ all -> 0x0022 }
        r3 = r13.zzbwh;	 Catch:{ all -> 0x0022 }
        r1.addView(r3, r2);	 Catch:{ all -> 0x0022 }
        r1 = r13.zzbwi;	 Catch:{ RuntimeException -> 0x0327 }
        r2 = r7.getDecorView();	 Catch:{ RuntimeException -> 0x0327 }
        r3 = 0;
        com.google.android.gms.internal.ads.zzkb.zzif();	 Catch:{ RuntimeException -> 0x0327 }
        r4 = r13.zzbvp;	 Catch:{ RuntimeException -> 0x0327 }
        r5 = 0;
        r5 = r8[r5];	 Catch:{ RuntimeException -> 0x0327 }
        r4 = com.google.android.gms.internal.ads.zzamu.zza(r4, r5);	 Catch:{ RuntimeException -> 0x0327 }
        com.google.android.gms.internal.ads.zzkb.zzif();	 Catch:{ RuntimeException -> 0x0327 }
        r5 = r13.zzbvp;	 Catch:{ RuntimeException -> 0x0327 }
        r7 = 1;
        r7 = r8[r7];	 Catch:{ RuntimeException -> 0x0327 }
        r5 = com.google.android.gms.internal.ads.zzamu.zza(r5, r7);	 Catch:{ RuntimeException -> 0x0327 }
        r1.showAtLocation(r2, r3, r4, r5);	 Catch:{ RuntimeException -> 0x0327 }
        r1 = 0;
        r1 = r8[r1];	 Catch:{ all -> 0x0022 }
        r2 = 1;
        r2 = r8[r2];	 Catch:{ all -> 0x0022 }
        r3 = r13.zzbmy;	 Catch:{ all -> 0x0022 }
        if (r3 == 0) goto L_0x026e;
    L_0x0265:
        r3 = r13.zzbmy;	 Catch:{ all -> 0x0022 }
        r4 = r13.zzuq;	 Catch:{ all -> 0x0022 }
        r5 = r13.zzur;	 Catch:{ all -> 0x0022 }
        r3.zza(r1, r2, r4, r5);	 Catch:{ all -> 0x0022 }
    L_0x026e:
        r1 = r13.zzbnd;	 Catch:{ all -> 0x0022 }
        r2 = com.google.android.gms.internal.ads.zzasi.zzi(r9, r10);	 Catch:{ all -> 0x0022 }
        r1.zza(r2);	 Catch:{ all -> 0x0022 }
        r1 = 0;
        r1 = r8[r1];	 Catch:{ all -> 0x0022 }
        r2 = 1;
        r2 = r8[r2];	 Catch:{ all -> 0x0022 }
        r13.zza(r1, r2);	 Catch:{ all -> 0x0022 }
        r1 = "resized";
        r13.zzby(r1);	 Catch:{ all -> 0x0022 }
        monitor-exit(r6);	 Catch:{ all -> 0x0022 }
        goto L_0x0011;
    L_0x0289:
        r1 = r13.zzbwi;	 Catch:{ all -> 0x0022 }
        r1.dismiss();	 Catch:{ all -> 0x0022 }
        goto L_0x019d;
    L_0x0290:
        r1 = "Webview is detached, probably in the middle of a resize or expand.";
        r13.zzbw(r1);	 Catch:{ all -> 0x0022 }
        monitor-exit(r6);	 Catch:{ all -> 0x0022 }
        goto L_0x0011;
    L_0x0299:
        r1 = r3;
        goto L_0x01d5;
    L_0x029c:
        r5 = "top-left";
        r1 = r1.equals(r5);	 Catch:{ all -> 0x0022 }
        if (r1 == 0) goto L_0x0212;
    L_0x02a5:
        r1 = r3;
        goto L_0x0213;
    L_0x02a8:
        r3 = "top-center";
        r1 = r1.equals(r3);	 Catch:{ all -> 0x0022 }
        if (r1 == 0) goto L_0x0212;
    L_0x02b1:
        r1 = r5;
        goto L_0x0213;
    L_0x02b4:
        r3 = "center";
        r1 = r1.equals(r3);	 Catch:{ all -> 0x0022 }
        if (r1 == 0) goto L_0x0212;
    L_0x02bd:
        r1 = 2;
        goto L_0x0213;
    L_0x02c0:
        r3 = "bottom-left";
        r1 = r1.equals(r3);	 Catch:{ all -> 0x0022 }
        if (r1 == 0) goto L_0x0212;
    L_0x02c9:
        r1 = 3;
        goto L_0x0213;
    L_0x02cc:
        r3 = "bottom-center";
        r1 = r1.equals(r3);	 Catch:{ all -> 0x0022 }
        if (r1 == 0) goto L_0x0212;
    L_0x02d5:
        r1 = 4;
        goto L_0x0213;
    L_0x02d8:
        r3 = "bottom-right";
        r1 = r1.equals(r3);	 Catch:{ all -> 0x0022 }
        if (r1 == 0) goto L_0x0212;
    L_0x02e1:
        r1 = 5;
        goto L_0x0213;
    L_0x02e4:
        r1 = 10;
        r2.addRule(r1);	 Catch:{ all -> 0x0022 }
        r1 = 9;
        r2.addRule(r1);	 Catch:{ all -> 0x0022 }
        goto L_0x0220;
    L_0x02f0:
        r1 = 10;
        r2.addRule(r1);	 Catch:{ all -> 0x0022 }
        r1 = 14;
        r2.addRule(r1);	 Catch:{ all -> 0x0022 }
        goto L_0x0220;
    L_0x02fc:
        r1 = 13;
        r2.addRule(r1);	 Catch:{ all -> 0x0022 }
        goto L_0x0220;
    L_0x0303:
        r1 = 12;
        r2.addRule(r1);	 Catch:{ all -> 0x0022 }
        r1 = 9;
        r2.addRule(r1);	 Catch:{ all -> 0x0022 }
        goto L_0x0220;
    L_0x030f:
        r1 = 12;
        r2.addRule(r1);	 Catch:{ all -> 0x0022 }
        r1 = 14;
        r2.addRule(r1);	 Catch:{ all -> 0x0022 }
        goto L_0x0220;
    L_0x031b:
        r1 = 12;
        r2.addRule(r1);	 Catch:{ all -> 0x0022 }
        r1 = 11;
        r2.addRule(r1);	 Catch:{ all -> 0x0022 }
        goto L_0x0220;
    L_0x0327:
        r1 = move-exception;
        r2 = "Cannot show popup window: ";
        r1 = r1.getMessage();	 Catch:{ all -> 0x0022 }
        r1 = java.lang.String.valueOf(r1);	 Catch:{ all -> 0x0022 }
        r3 = r1.length();	 Catch:{ all -> 0x0022 }
        if (r3 == 0) goto L_0x036b;
    L_0x0339:
        r1 = r2.concat(r1);	 Catch:{ all -> 0x0022 }
    L_0x033d:
        r13.zzbw(r1);	 Catch:{ all -> 0x0022 }
        r1 = r13.zzbwj;	 Catch:{ all -> 0x0022 }
        r2 = r13.zzbnd;	 Catch:{ all -> 0x0022 }
        r2 = r2.getView();	 Catch:{ all -> 0x0022 }
        r1.removeView(r2);	 Catch:{ all -> 0x0022 }
        r1 = r13.zzbwk;	 Catch:{ all -> 0x0022 }
        if (r1 == 0) goto L_0x0368;
    L_0x034f:
        r1 = r13.zzbwk;	 Catch:{ all -> 0x0022 }
        r2 = r13.zzbwg;	 Catch:{ all -> 0x0022 }
        r1.removeView(r2);	 Catch:{ all -> 0x0022 }
        r1 = r13.zzbwk;	 Catch:{ all -> 0x0022 }
        r2 = r13.zzbnd;	 Catch:{ all -> 0x0022 }
        r2 = r2.getView();	 Catch:{ all -> 0x0022 }
        r1.addView(r2);	 Catch:{ all -> 0x0022 }
        r1 = r13.zzbnd;	 Catch:{ all -> 0x0022 }
        r2 = r13.zzbwf;	 Catch:{ all -> 0x0022 }
        r1.zza(r2);	 Catch:{ all -> 0x0022 }
    L_0x0368:
        monitor-exit(r6);	 Catch:{ all -> 0x0022 }
        goto L_0x0011;
    L_0x036b:
        r1 = new java.lang.String;	 Catch:{ all -> 0x0022 }
        r1.<init>(r2);	 Catch:{ all -> 0x0022 }
        goto L_0x033d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaab.zzk(java.util.Map):void");
    }

    public final void zzm(boolean z) {
        synchronized (this.mLock) {
            if (this.zzbwi != null) {
                this.zzbwi.dismiss();
                this.zzbwj.removeView(this.zzbnd.getView());
                if (this.zzbwk != null) {
                    this.zzbwk.removeView(this.zzbwg);
                    this.zzbwk.addView(this.zzbnd.getView());
                    this.zzbnd.zza(this.zzbwf);
                }
                if (z) {
                    zzby("default");
                    if (this.zzbmy != null) {
                        this.zzbmy.zzcq();
                    }
                }
                this.zzbwi = null;
                this.zzbwj = null;
                this.zzbwk = null;
                this.zzbwh = null;
            }
        }
    }

    public final boolean zznf() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzbwi != null;
        }
        return z;
    }
}
