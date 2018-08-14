package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import com.google.ads.AdRequest;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import cz.msebera.android.httpclient.protocol.HTTP;
import java.util.ArrayList;
import java.util.List;

@zzadh
public final class zzald {
    private Handler handler;
    private final Context mContext;
    private int state;
    private String zzaej;
    private final float zzbwx;
    @Nullable
    private String zzchz;
    private String zzcrx;
    private float zzcry;
    private float zzcrz;
    private float zzcsa;
    private int zzcsb;
    private float zzcsc;
    private float zzcsd;
    private float zzcse;
    private float zzcsf;
    private Runnable zzcsg;
    private String zzye;

    public zzald(Context context) {
        this.state = 0;
        this.zzcsg = new zzale(this);
        this.mContext = context;
        this.zzbwx = context.getResources().getDisplayMetrics().density;
        this.zzcsb = ViewConfiguration.get(this.mContext).getScaledTouchSlop();
        zzbv.zzez().zzsa();
        this.handler = zzbv.zzez().getHandler();
    }

    public zzald(Context context, String str) {
        this(context);
        this.zzcrx = str;
    }

    private static int zza(List<String> list, String str, boolean z) {
        if (!z) {
            return -1;
        }
        list.add(str);
        return list.size() - 1;
    }

    @VisibleForTesting
    private final void zza(int i, float f, float f2) {
        if (i == 0) {
            this.state = 0;
            this.zzcry = f;
            this.zzcrz = f2;
            this.zzcsa = f2;
        } else if (this.state == -1) {
        } else {
            if (i == 2) {
                if (f2 > this.zzcrz) {
                    this.zzcrz = f2;
                } else if (f2 < this.zzcsa) {
                    this.zzcsa = f2;
                }
                if (this.zzcrz - this.zzcsa > BitmapDescriptorFactory.HUE_ORANGE * this.zzbwx) {
                    this.state = -1;
                    return;
                }
                if (this.state == 0 || this.state == 2) {
                    if (f - this.zzcry >= 50.0f * this.zzbwx) {
                        this.zzcry = f;
                        this.state++;
                    }
                } else if ((this.state == 1 || this.state == 3) && f - this.zzcry <= -50.0f * this.zzbwx) {
                    this.zzcry = f;
                    this.state++;
                }
                if (this.state == 1 || this.state == 3) {
                    if (f > this.zzcry) {
                        this.zzcry = f;
                    }
                } else if (this.state == 2 && f < this.zzcry) {
                    this.zzcry = f;
                }
            } else if (i == 1 && this.state == 4) {
                showDialog();
            }
        }
    }

    private final boolean zza(float f, float f2, float f3, float f4) {
        return Math.abs(this.zzcsc - f) < ((float) this.zzcsb) && Math.abs(this.zzcsd - f2) < ((float) this.zzcsb) && Math.abs(this.zzcse - f3) < ((float) this.zzcsb) && Math.abs(this.zzcsf - f4) < ((float) this.zzcsb);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzrs() {
        /*
        r6 = this;
        r0 = r6.mContext;
        r0 = r0 instanceof android.app.Activity;
        if (r0 != 0) goto L_0x000d;
    L_0x0006:
        r0 = "Can not create dialog without Activity Context";
        com.google.android.gms.internal.ads.zzane.zzdj(r0);
    L_0x000c:
        return;
    L_0x000d:
        r0 = r6.zzcrx;
        r1 = android.text.TextUtils.isEmpty(r0);
        if (r1 != 0) goto L_0x00a2;
    L_0x0015:
        r1 = "\\+";
        r2 = "%20";
        r0 = r0.replaceAll(r1, r2);
        r1 = new android.net.Uri$Builder;
        r1.<init>();
        r0 = r1.encodedQuery(r0);
        r0 = r0.build();
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        com.google.android.gms.ads.internal.zzbv.zzek();
        r2 = com.google.android.gms.internal.ads.zzakk.zzg(r0);
        r0 = r2.keySet();
        r3 = r0.iterator();
    L_0x0040:
        r0 = r3.hasNext();
        if (r0 == 0) goto L_0x0068;
    L_0x0046:
        r0 = r3.next();
        r0 = (java.lang.String) r0;
        r4 = r1.append(r0);
        r5 = " = ";
        r4 = r4.append(r5);
        r0 = r2.get(r0);
        r0 = (java.lang.String) r0;
        r0 = r4.append(r0);
        r4 = "\n\n";
        r0.append(r4);
        goto L_0x0040;
    L_0x0068:
        r0 = r1.toString();
        r0 = r0.trim();
        r1 = android.text.TextUtils.isEmpty(r0);
        if (r1 != 0) goto L_0x00a2;
    L_0x0076:
        r1 = new android.app.AlertDialog$Builder;
        r2 = r6.mContext;
        r1.<init>(r2);
        r1.setMessage(r0);
        r2 = "Ad Information";
        r1.setTitle(r2);
        r2 = "Share";
        r3 = new com.google.android.gms.internal.ads.zzalg;
        r3.<init>(r6, r0);
        r1.setPositiveButton(r2, r3);
        r0 = "Close";
        r2 = com.google.android.gms.internal.ads.zzalh.zzcsl;
        r1.setNegativeButton(r0, r2);
        r0 = r1.create();
        r0.show();
        goto L_0x000c;
    L_0x00a2:
        r0 = "No debug information";
        goto L_0x0076;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzald.zzrs():void");
    }

    public final void setAdUnitId(String str) {
        this.zzye = str;
    }

    public final void showDialog() {
        String str;
        try {
            if (!((Boolean) zzkb.zzik().zzd(zznk.zzbec)).booleanValue()) {
                if (!((Boolean) zzkb.zzik().zzd(zznk.zzbeb)).booleanValue()) {
                    zzrs();
                    return;
                }
            }
            if (this.mContext instanceof Activity) {
                String str2 = !TextUtils.isEmpty(zzbv.zzeu().zzrw()) ? "Creative Preview (Enabled)" : "Creative Preview";
                str = zzbv.zzeu().zzrx() ? "Troubleshooting (Enabled)" : "Troubleshooting";
                List arrayList = new ArrayList();
                int zza = zza(arrayList, "Ad Information", true);
                int zza2 = zza(arrayList, str2, ((Boolean) zzkb.zzik().zzd(zznk.zzbeb)).booleanValue());
                int zza3 = zza(arrayList, str, ((Boolean) zzkb.zzik().zzd(zznk.zzbec)).booleanValue());
                Builder builder = new Builder(this.mContext, zzbv.zzem().zzrq());
                builder.setTitle("Select a Debug Mode").setItems((CharSequence[]) arrayList.toArray(new String[0]), new zzalf(this, zza, zza2, zza3));
                builder.create().show();
                return;
            }
            zzane.zzdj("Can not create dialog without Activity Context");
        } catch (Throwable e) {
            str = "";
            if (zzakb.zzqp()) {
                Log.v(AdRequest.LOGTAG, str, e);
            }
        }
    }

    final /* synthetic */ void zza(int i, int i2, int i3, DialogInterface dialogInterface, int i4) {
        if (i4 == i) {
            zzrs();
            return;
        }
        if (i4 == i2) {
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbeb)).booleanValue()) {
                zzane.zzck("Debug mode [Creative Preview] selected.");
                zzaki.zzb(new zzali(this));
                return;
            }
        }
        if (i4 == i3) {
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbec)).booleanValue()) {
                zzane.zzck("Debug mode [Troubleshooting] selected.");
                zzaki.zzb(new zzalj(this));
            }
        }
    }

    final /* synthetic */ void zza(String str, DialogInterface dialogInterface, int i) {
        zzbv.zzek();
        zzakk.zza(this.mContext, Intent.createChooser(new Intent("android.intent.action.SEND").setType(HTTP.PLAIN_TEXT_TYPE).putExtra("android.intent.extra.TEXT", str), "Share via"));
    }

    public final void zzda(String str) {
        this.zzaej = str;
    }

    public final void zzdb(String str) {
        this.zzcrx = str;
    }

    public final void zzdc(String str) {
        this.zzchz = str;
    }

    public final void zze(MotionEvent motionEvent) {
        int i = 1;
        int actionMasked;
        if (((Boolean) zzkb.zzik().zzd(zznk.zzbed)).booleanValue()) {
            actionMasked = motionEvent.getActionMasked();
            int historySize = motionEvent.getHistorySize();
            int pointerCount = motionEvent.getPointerCount();
            if (actionMasked == 0) {
                this.state = 0;
                this.zzcsc = motionEvent.getX();
                this.zzcsd = motionEvent.getY();
                return;
            } else if (this.state == -1) {
                return;
            } else {
                if (this.state == 0 && actionMasked == 5) {
                    this.state = 5;
                    this.zzcse = motionEvent.getX(1);
                    this.zzcsf = motionEvent.getY(1);
                    this.handler.postDelayed(this.zzcsg, ((Long) zzkb.zzik().zzd(zznk.zzbee)).longValue());
                    return;
                } else if (this.state == 5) {
                    if (pointerCount == 2) {
                        if (actionMasked == 2) {
                            actionMasked = 0;
                            for (pointerCount = 0; pointerCount < historySize; pointerCount++) {
                                if (!zza(motionEvent.getHistoricalX(0, pointerCount), motionEvent.getHistoricalY(0, pointerCount), motionEvent.getHistoricalX(1, pointerCount), motionEvent.getHistoricalY(1, pointerCount))) {
                                    actionMasked = 1;
                                }
                            }
                            if (zza(motionEvent.getX(), motionEvent.getY(), motionEvent.getX(1), motionEvent.getY(1))) {
                                i = actionMasked;
                            }
                        } else {
                            i = 0;
                        }
                    }
                    if (i != 0) {
                        this.state = -1;
                        this.handler.removeCallbacks(this.zzcsg);
                        return;
                    }
                    return;
                } else {
                    return;
                }
            }
        }
        i = motionEvent.getHistorySize();
        for (actionMasked = 0; actionMasked < i; actionMasked++) {
            zza(motionEvent.getActionMasked(), motionEvent.getHistoricalX(0, actionMasked), motionEvent.getHistoricalY(0, actionMasked));
        }
        zza(motionEvent.getActionMasked(), motionEvent.getX(), motionEvent.getY());
    }

    final /* synthetic */ void zzrt() {
        zzbv.zzeu().zza(this.mContext, this.zzye, this.zzaej, this.zzchz);
    }

    final /* synthetic */ void zzru() {
        zzbv.zzeu().zzg(this.mContext, this.zzye, this.zzaej);
    }

    final /* synthetic */ void zzrv() {
        this.state = 4;
        showDialog();
    }
}
