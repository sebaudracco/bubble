package com.google.android.gms.internal.ads;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.util.Base64;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zztw {
    private final Map<zztx, zzty> zzbok = new HashMap();
    private final LinkedList<zztx> zzbol = new LinkedList();
    @Nullable
    private zzss zzbom;

    private static void zza(String str, zztx com_google_android_gms_internal_ads_zztx) {
        if (zzane.isLoggable(2)) {
            zzakb.m3428v(String.format(str, new Object[]{com_google_android_gms_internal_ads_zztx}));
        }
    }

    private static String[] zzax(String str) {
        try {
            String[] split = str.split("\u0000");
            for (int i = 0; i < split.length; i++) {
                split[i] = new String(Base64.decode(split[i], 0), "UTF-8");
            }
            return split;
        } catch (UnsupportedEncodingException e) {
            return new String[0];
        }
    }

    private static boolean zzay(String str) {
        try {
            return Pattern.matches((String) zzkb.zzik().zzd(zznk.zzazf), str);
        } catch (Throwable e) {
            zzbv.zzeo().zza(e, "InterstitialAdPool.isExcludedAdUnit");
            return false;
        }
    }

    @VisibleForTesting
    private static String zzaz(String str) {
        try {
            Matcher matcher = Pattern.compile("([^/]+/[0-9]+).*").matcher(str);
            if (matcher.matches()) {
                str = matcher.group(1);
            }
        } catch (RuntimeException e) {
        }
        return str;
    }

    private static void zzb(Bundle bundle, String str) {
        while (true) {
            String[] split = str.split(BridgeUtil.SPLIT_MARK, 2);
            if (split.length != 0) {
                String str2 = split[0];
                if (split.length == 1) {
                    bundle.remove(str2);
                    return;
                }
                bundle = bundle.getBundle(str2);
                if (bundle != null) {
                    str = split[1];
                } else {
                    return;
                }
            }
            return;
        }
    }

    static Set<String> zzh(zzjj com_google_android_gms_internal_ads_zzjj) {
        Set<String> hashSet = new HashSet();
        hashSet.addAll(com_google_android_gms_internal_ads_zzjj.extras.keySet());
        Bundle bundle = com_google_android_gms_internal_ads_zzjj.zzaqg.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
        if (bundle != null) {
            hashSet.addAll(bundle.keySet());
        }
        return hashSet;
    }

    static zzjj zzi(zzjj com_google_android_gms_internal_ads_zzjj) {
        zzjj zzk = zzk(com_google_android_gms_internal_ads_zzjj);
        String str = "_skipMediation";
        Bundle bundle = zzk.zzaqg.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
        if (bundle != null) {
            bundle.putBoolean(str, true);
        }
        zzk.extras.putBoolean(str, true);
        return zzk;
    }

    @VisibleForTesting
    private static zzjj zzj(zzjj com_google_android_gms_internal_ads_zzjj) {
        zzjj zzk = zzk(com_google_android_gms_internal_ads_zzjj);
        for (String str : ((String) zzkb.zzik().zzd(zznk.zzazb)).split(",")) {
            zzb(zzk.zzaqg, str);
            String str2 = "com.google.ads.mediation.admob.AdMobAdapter/";
            if (str.startsWith(str2)) {
                zzb(zzk.extras, str.replaceFirst(str2, ""));
            }
        }
        return zzk;
    }

    @VisibleForTesting
    private static zzjj zzk(zzjj com_google_android_gms_internal_ads_zzjj) {
        Parcel obtain = Parcel.obtain();
        com_google_android_gms_internal_ads_zzjj.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        zzjj com_google_android_gms_internal_ads_zzjj2 = (zzjj) zzjj.CREATOR.createFromParcel(obtain);
        obtain.recycle();
        return ((Boolean) zzkb.zzik().zzd(zznk.zzayo)).booleanValue() ? com_google_android_gms_internal_ads_zzjj2.zzhv() : com_google_android_gms_internal_ads_zzjj2;
    }

    private final String zzle() {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            Iterator it = this.zzbol.iterator();
            while (it.hasNext()) {
                stringBuilder.append(Base64.encodeToString(((zztx) it.next()).toString().getBytes("UTF-8"), 0));
                if (it.hasNext()) {
                    stringBuilder.append("\u0000");
                }
            }
            return stringBuilder.toString();
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    @Nullable
    final zztz zza(zzjj com_google_android_gms_internal_ads_zzjj, String str) {
        if (zzay(str)) {
            return null;
        }
        zzty com_google_android_gms_internal_ads_zzty;
        int i = new zzagb(this.zzbom.getApplicationContext()).zzoo().zzcjx;
        zzjj zzj = zzj(com_google_android_gms_internal_ads_zzjj);
        String zzaz = zzaz(str);
        zztx com_google_android_gms_internal_ads_zztx = new zztx(zzj, zzaz, i);
        zzty com_google_android_gms_internal_ads_zzty2 = (zzty) this.zzbok.get(com_google_android_gms_internal_ads_zztx);
        if (com_google_android_gms_internal_ads_zzty2 == null) {
            zza("Interstitial pool created at %s.", com_google_android_gms_internal_ads_zztx);
            com_google_android_gms_internal_ads_zzty2 = new zzty(zzj, zzaz, i);
            this.zzbok.put(com_google_android_gms_internal_ads_zztx, com_google_android_gms_internal_ads_zzty2);
            com_google_android_gms_internal_ads_zzty = com_google_android_gms_internal_ads_zzty2;
        } else {
            com_google_android_gms_internal_ads_zzty = com_google_android_gms_internal_ads_zzty2;
        }
        this.zzbol.remove(com_google_android_gms_internal_ads_zztx);
        this.zzbol.add(com_google_android_gms_internal_ads_zztx);
        com_google_android_gms_internal_ads_zzty.zzli();
        while (true) {
            if (this.zzbol.size() <= ((Integer) zzkb.zzik().zzd(zznk.zzazc)).intValue()) {
                break;
            }
            zztx com_google_android_gms_internal_ads_zztx2 = (zztx) this.zzbol.remove();
            zzty com_google_android_gms_internal_ads_zzty3 = (zzty) this.zzbok.get(com_google_android_gms_internal_ads_zztx2);
            zza("Evicting interstitial queue for %s.", com_google_android_gms_internal_ads_zztx2);
            while (com_google_android_gms_internal_ads_zzty3.size() > 0) {
                zztz zzl = com_google_android_gms_internal_ads_zzty3.zzl(null);
                if (zzl.zzwa) {
                    zzua.zzlk().zzlm();
                }
                zzl.zzbor.zzdj();
            }
            this.zzbok.remove(com_google_android_gms_internal_ads_zztx2);
        }
        while (com_google_android_gms_internal_ads_zzty.size() > 0) {
            zztz zzl2 = com_google_android_gms_internal_ads_zzty.zzl(zzj);
            if (zzl2.zzwa) {
                if (zzbv.zzer().currentTimeMillis() - zzl2.zzbou > 1000 * ((long) ((Integer) zzkb.zzik().zzd(zznk.zzaze)).intValue())) {
                    zza("Expired interstitial at %s.", com_google_android_gms_internal_ads_zztx);
                    zzua.zzlk().zzll();
                }
            }
            String str2 = zzl2.zzbos != null ? " (inline) " : " ";
            zza(new StringBuilder(String.valueOf(str2).length() + 34).append("Pooled interstitial").append(str2).append("returned at %s.").toString(), com_google_android_gms_internal_ads_zztx);
            return zzl2;
        }
        return null;
    }

    final void zza(zzss com_google_android_gms_internal_ads_zzss) {
        Throwable e;
        if (this.zzbom == null) {
            this.zzbom = com_google_android_gms_internal_ads_zzss.zzlc();
            if (this.zzbom != null) {
                zztx com_google_android_gms_internal_ads_zztx;
                SharedPreferences sharedPreferences = this.zzbom.getApplicationContext().getSharedPreferences("com.google.android.gms.ads.internal.interstitial.InterstitialAdPool", 0);
                while (this.zzbol.size() > 0) {
                    com_google_android_gms_internal_ads_zztx = (zztx) this.zzbol.remove();
                    zzty com_google_android_gms_internal_ads_zzty = (zzty) this.zzbok.get(com_google_android_gms_internal_ads_zztx);
                    zza("Flushing interstitial queue for %s.", com_google_android_gms_internal_ads_zztx);
                    while (com_google_android_gms_internal_ads_zzty.size() > 0) {
                        com_google_android_gms_internal_ads_zzty.zzl(null).zzbor.zzdj();
                    }
                    this.zzbok.remove(com_google_android_gms_internal_ads_zztx);
                }
                try {
                    Map hashMap = new HashMap();
                    for (Entry entry : sharedPreferences.getAll().entrySet()) {
                        if (!((String) entry.getKey()).equals("PoolKeys")) {
                            zzuc zzba = zzuc.zzba((String) entry.getValue());
                            zztx com_google_android_gms_internal_ads_zztx2 = new zztx(zzba.zzaao, zzba.zzye, zzba.zzbop);
                            if (!this.zzbok.containsKey(com_google_android_gms_internal_ads_zztx2)) {
                                this.zzbok.put(com_google_android_gms_internal_ads_zztx2, new zzty(zzba.zzaao, zzba.zzye, zzba.zzbop));
                                hashMap.put(com_google_android_gms_internal_ads_zztx2.toString(), com_google_android_gms_internal_ads_zztx2);
                                zza("Restored interstitial queue for %s.", com_google_android_gms_internal_ads_zztx2);
                            }
                        }
                    }
                    for (Object obj : zzax(sharedPreferences.getString("PoolKeys", ""))) {
                        com_google_android_gms_internal_ads_zztx = (zztx) hashMap.get(obj);
                        if (this.zzbok.containsKey(com_google_android_gms_internal_ads_zztx)) {
                            this.zzbol.add(com_google_android_gms_internal_ads_zztx);
                        }
                    }
                } catch (RuntimeException e2) {
                    e = e2;
                    zzbv.zzeo().zza(e, "InterstitialAdPool.restore");
                    zzane.zzc("Malformed preferences value for InterstitialAdPool.", e);
                    this.zzbok.clear();
                    this.zzbol.clear();
                } catch (IOException e3) {
                    e = e3;
                    zzbv.zzeo().zza(e, "InterstitialAdPool.restore");
                    zzane.zzc("Malformed preferences value for InterstitialAdPool.", e);
                    this.zzbok.clear();
                    this.zzbol.clear();
                }
            }
        }
    }

    final void zzb(zzjj com_google_android_gms_internal_ads_zzjj, String str) {
        if (this.zzbom != null) {
            int i = new zzagb(this.zzbom.getApplicationContext()).zzoo().zzcjx;
            zzjj zzj = zzj(com_google_android_gms_internal_ads_zzjj);
            String zzaz = zzaz(str);
            zztx com_google_android_gms_internal_ads_zztx = new zztx(zzj, zzaz, i);
            zzty com_google_android_gms_internal_ads_zzty = (zzty) this.zzbok.get(com_google_android_gms_internal_ads_zztx);
            if (com_google_android_gms_internal_ads_zzty == null) {
                zza("Interstitial pool created at %s.", com_google_android_gms_internal_ads_zztx);
                com_google_android_gms_internal_ads_zzty = new zzty(zzj, zzaz, i);
                this.zzbok.put(com_google_android_gms_internal_ads_zztx, com_google_android_gms_internal_ads_zzty);
            }
            com_google_android_gms_internal_ads_zzty.zza(this.zzbom, com_google_android_gms_internal_ads_zzjj);
            com_google_android_gms_internal_ads_zzty.zzli();
            zza("Inline entry added to the queue at %s.", com_google_android_gms_internal_ads_zztx);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final void zzld() {
        /*
        r9 = this;
        r8 = 2;
        r7 = 0;
        r0 = r9.zzbom;
        if (r0 != 0) goto L_0x0007;
    L_0x0006:
        return;
    L_0x0007:
        r0 = r9.zzbok;
        r0 = r0.entrySet();
        r4 = r0.iterator();
    L_0x0011:
        r0 = r4.hasNext();
        if (r0 == 0) goto L_0x008e;
    L_0x0017:
        r0 = r4.next();
        r0 = (java.util.Map.Entry) r0;
        r1 = r0.getKey();
        r1 = (com.google.android.gms.internal.ads.zztx) r1;
        r0 = r0.getValue();
        r0 = (com.google.android.gms.internal.ads.zzty) r0;
        r2 = com.google.android.gms.internal.ads.zzane.isLoggable(r8);
        if (r2 == 0) goto L_0x0057;
    L_0x002f:
        r2 = r0.size();
        r3 = r0.zzlg();
        if (r3 >= r2) goto L_0x0057;
    L_0x0039:
        r5 = "Loading %s/%s pooled interstitials for %s.";
        r6 = 3;
        r6 = new java.lang.Object[r6];
        r3 = r2 - r3;
        r3 = java.lang.Integer.valueOf(r3);
        r6[r7] = r3;
        r3 = 1;
        r2 = java.lang.Integer.valueOf(r2);
        r6[r3] = r2;
        r6[r8] = r1;
        r2 = java.lang.String.format(r5, r6);
        com.google.android.gms.internal.ads.zzakb.m3428v(r2);
    L_0x0057:
        r2 = r0.zzlh();
        r2 = r2 + 0;
        r3 = r2;
    L_0x005e:
        r5 = r0.size();
        r2 = com.google.android.gms.internal.ads.zznk.zzazd;
        r6 = com.google.android.gms.internal.ads.zzkb.zzik();
        r2 = r6.zzd(r2);
        r2 = (java.lang.Integer) r2;
        r2 = r2.intValue();
        if (r5 >= r2) goto L_0x0086;
    L_0x0074:
        r2 = "Pooling and loading one new interstitial for %s.";
        zza(r2, r1);
        r2 = r9.zzbom;
        r2 = r0.zzb(r2);
        if (r2 == 0) goto L_0x005e;
    L_0x0082:
        r2 = r3 + 1;
        r3 = r2;
        goto L_0x005e;
    L_0x0086:
        r0 = com.google.android.gms.internal.ads.zzua.zzlk();
        r0.zzw(r3);
        goto L_0x0011;
    L_0x008e:
        r0 = r9.zzbom;
        if (r0 == 0) goto L_0x0006;
    L_0x0092:
        r0 = r9.zzbom;
        r0 = r0.getApplicationContext();
        r1 = "com.google.android.gms.ads.internal.interstitial.InterstitialAdPool";
        r0 = r0.getSharedPreferences(r1, r7);
        r2 = r0.edit();
        r2.clear();
        r0 = r9.zzbok;
        r0 = r0.entrySet();
        r3 = r0.iterator();
    L_0x00b0:
        r0 = r3.hasNext();
        if (r0 == 0) goto L_0x00e5;
    L_0x00b6:
        r0 = r3.next();
        r0 = (java.util.Map.Entry) r0;
        r1 = r0.getKey();
        r1 = (com.google.android.gms.internal.ads.zztx) r1;
        r0 = r0.getValue();
        r0 = (com.google.android.gms.internal.ads.zzty) r0;
        r4 = r0.zzlj();
        if (r4 == 0) goto L_0x00b0;
    L_0x00ce:
        r4 = new com.google.android.gms.internal.ads.zzuc;
        r4.<init>(r0);
        r0 = r4.zzlu();
        r4 = r1.toString();
        r2.putString(r4, r0);
        r0 = "Saved interstitial queue for %s.";
        zza(r0, r1);
        goto L_0x00b0;
    L_0x00e5:
        r0 = "PoolKeys";
        r1 = r9.zzle();
        r2.putString(r0, r1);
        r2.apply();
        goto L_0x0006;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zztw.zzld():void");
    }
}
