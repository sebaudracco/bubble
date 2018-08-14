package com.google.android.gms.internal.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.json.JSONObject;

@zzadh
public final class zzahx extends zzajx implements zzahw {
    private final Context mContext;
    private final Object mLock;
    private final zzaji zzbze;
    private final long zzclp;
    private final ArrayList<zzahn> zzcmd;
    private final List<zzahq> zzcme;
    private final HashSet<String> zzcmf;
    private final zzago zzcmg;

    public zzahx(Context context, zzaji com_google_android_gms_internal_ads_zzaji, zzago com_google_android_gms_internal_ads_zzago) {
        this(context, com_google_android_gms_internal_ads_zzaji, com_google_android_gms_internal_ads_zzago, ((Long) zzkb.zzik().zzd(zznk.zzaye)).longValue());
    }

    @VisibleForTesting
    private zzahx(Context context, zzaji com_google_android_gms_internal_ads_zzaji, zzago com_google_android_gms_internal_ads_zzago, long j) {
        this.zzcmd = new ArrayList();
        this.zzcme = new ArrayList();
        this.zzcmf = new HashSet();
        this.mLock = new Object();
        this.mContext = context;
        this.zzbze = com_google_android_gms_internal_ads_zzaji;
        this.zzcmg = com_google_android_gms_internal_ads_zzago;
        this.zzclp = j;
    }

    private final zzajh zza(int i, @Nullable String str, @Nullable zzwx com_google_android_gms_internal_ads_zzwx) {
        String stringBuilder;
        zzjj com_google_android_gms_internal_ads_zzjj = this.zzbze.zzcgs.zzccv;
        List list = this.zzbze.zzcos.zzbsn;
        List list2 = this.zzbze.zzcos.zzbso;
        List list3 = this.zzbze.zzcos.zzces;
        int i2 = this.zzbze.zzcos.orientation;
        long j = this.zzbze.zzcos.zzbsu;
        String str2 = this.zzbze.zzcgs.zzccy;
        boolean z = this.zzbze.zzcos.zzceq;
        zzwy com_google_android_gms_internal_ads_zzwy = this.zzbze.zzcod;
        long j2 = this.zzbze.zzcos.zzcer;
        zzjn com_google_android_gms_internal_ads_zzjn = this.zzbze.zzacv;
        long j3 = this.zzbze.zzcos.zzcep;
        long j4 = this.zzbze.zzcoh;
        long j5 = this.zzbze.zzcos.zzceu;
        String str3 = this.zzbze.zzcos.zzcev;
        JSONObject jSONObject = this.zzbze.zzcob;
        zzaig com_google_android_gms_internal_ads_zzaig = this.zzbze.zzcos.zzcfe;
        List list4 = this.zzbze.zzcos.zzcff;
        List list5 = this.zzbze.zzcos.zzcfg;
        boolean z2 = this.zzbze.zzcos.zzcfh;
        zzael com_google_android_gms_internal_ads_zzael = this.zzbze.zzcos.zzcfi;
        StringBuilder stringBuilder2 = new StringBuilder("");
        if (this.zzcme == null) {
            stringBuilder = stringBuilder2.toString();
        } else {
            for (zzahq com_google_android_gms_internal_ads_zzahq : this.zzcme) {
                if (!(com_google_android_gms_internal_ads_zzahq == null || TextUtils.isEmpty(com_google_android_gms_internal_ads_zzahq.zzbru))) {
                    int i3;
                    String str4 = com_google_android_gms_internal_ads_zzahq.zzbru;
                    switch (com_google_android_gms_internal_ads_zzahq.errorCode) {
                        case 3:
                            i3 = 1;
                            break;
                        case 4:
                            i3 = 2;
                            break;
                        case 5:
                            i3 = 4;
                            break;
                        case 6:
                            i3 = 0;
                            break;
                        case 7:
                            i3 = 3;
                            break;
                        default:
                            i3 = 6;
                            break;
                    }
                    stringBuilder2 = stringBuilder2;
                    stringBuilder2.append(String.valueOf(new StringBuilder(String.valueOf(str4).length() + 33).append(str4).append(".").append(i3).append(".").append(com_google_android_gms_internal_ads_zzahq.zzbub).toString()).concat(BridgeUtil.UNDERLINE_STR));
                }
            }
            stringBuilder = stringBuilder2.substring(0, Math.max(0, stringBuilder2.length() - 1));
        }
        return new zzajh(com_google_android_gms_internal_ads_zzjj, null, list, i, list2, list3, i2, j, str2, z, com_google_android_gms_internal_ads_zzwx, null, str, com_google_android_gms_internal_ads_zzwy, null, j2, com_google_android_gms_internal_ads_zzjn, j3, j4, j5, str3, jSONObject, null, com_google_android_gms_internal_ads_zzaig, list4, list5, z2, com_google_android_gms_internal_ads_zzael, stringBuilder, this.zzbze.zzcos.zzbsr, this.zzbze.zzcos.zzcfl, this.zzbze.zzcoq, this.zzbze.zzcos.zzzl, this.zzbze.zzcor, this.zzbze.zzcos.zzcfp, this.zzbze.zzcos.zzbsp, this.zzbze.zzcos.zzzm, this.zzbze.zzcos.zzcfq);
    }

    public final void onStop() {
    }

    public final void zza(String str, int i) {
    }

    public final void zzcb(String str) {
        synchronized (this.mLock) {
            this.zzcmf.add(str);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzdn() {
        /*
        r15 = this;
        r14 = 0;
        r10 = 0;
        r0 = r15.zzbze;
        r0 = r0.zzcod;
        r0 = r0.zzbsm;
        r11 = r0.iterator();
    L_0x000c:
        r0 = r11.hasNext();
        if (r0 == 0) goto L_0x00ae;
    L_0x0012:
        r4 = r11.next();
        r4 = (com.google.android.gms.internal.ads.zzwx) r4;
        r3 = r4.zzbsb;
        r0 = r4.zzbrt;
        r12 = r0.iterator();
    L_0x0020:
        r0 = r12.hasNext();
        if (r0 == 0) goto L_0x000c;
    L_0x0026:
        r0 = r12.next();
        r0 = (java.lang.String) r0;
        r1 = "com.google.android.gms.ads.mediation.customevent.CustomEventAdapter";
        r1 = r1.equals(r0);
        if (r1 != 0) goto L_0x003e;
    L_0x0035:
        r1 = "com.google.ads.mediation.customevent.CustomEventAdapter";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x019b;
    L_0x003e:
        r0 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0089 }
        r0.<init>(r3);	 Catch:{ JSONException -> 0x0089 }
        r1 = "class_name";
        r2 = r0.getString(r1);	 Catch:{ JSONException -> 0x0089 }
    L_0x004a:
        r13 = r15.mLock;
        monitor-enter(r13);
        r0 = r15.zzcmg;	 Catch:{ all -> 0x0086 }
        r6 = r0.zzca(r2);	 Catch:{ all -> 0x0086 }
        if (r6 == 0) goto L_0x0061;
    L_0x0055:
        r0 = r6.zzpf();	 Catch:{ all -> 0x0086 }
        if (r0 == 0) goto L_0x0061;
    L_0x005b:
        r0 = r6.zzpe();	 Catch:{ all -> 0x0086 }
        if (r0 != 0) goto L_0x0091;
    L_0x0061:
        r0 = r15.zzcme;	 Catch:{ all -> 0x0086 }
        r1 = new com.google.android.gms.internal.ads.zzahs;	 Catch:{ all -> 0x0086 }
        r1.<init>();	 Catch:{ all -> 0x0086 }
        r5 = r4.zzbru;	 Catch:{ all -> 0x0086 }
        r1 = r1.zzcd(r5);	 Catch:{ all -> 0x0086 }
        r1 = r1.zzcc(r2);	 Catch:{ all -> 0x0086 }
        r6 = 0;
        r1 = r1.zzg(r6);	 Catch:{ all -> 0x0086 }
        r2 = 7;
        r1 = r1.zzad(r2);	 Catch:{ all -> 0x0086 }
        r1 = r1.zzpd();	 Catch:{ all -> 0x0086 }
        r0.add(r1);	 Catch:{ all -> 0x0086 }
        monitor-exit(r13);	 Catch:{ all -> 0x0086 }
        goto L_0x0020;
    L_0x0086:
        r0 = move-exception;
        monitor-exit(r13);	 Catch:{ all -> 0x0086 }
        throw r0;
    L_0x0089:
        r0 = move-exception;
        r1 = "Unable to determine custom event class name, skipping...";
        com.google.android.gms.internal.ads.zzane.zzb(r1, r0);
        goto L_0x0020;
    L_0x0091:
        r0 = new com.google.android.gms.internal.ads.zzahn;	 Catch:{ all -> 0x0086 }
        r1 = r15.mContext;	 Catch:{ all -> 0x0086 }
        r5 = r15.zzbze;	 Catch:{ all -> 0x0086 }
        r8 = r15.zzclp;	 Catch:{ all -> 0x0086 }
        r7 = r15;
        r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ all -> 0x0086 }
        r1 = r15.zzcmg;	 Catch:{ all -> 0x0086 }
        r1 = r1.zzos();	 Catch:{ all -> 0x0086 }
        r0.zza(r1);	 Catch:{ all -> 0x0086 }
        r1 = r15.zzcmd;	 Catch:{ all -> 0x0086 }
        r1.add(r0);	 Catch:{ all -> 0x0086 }
        monitor-exit(r13);	 Catch:{ all -> 0x0086 }
        goto L_0x0020;
    L_0x00ae:
        r3 = new java.util.HashSet;
        r3.<init>();
        r0 = r15.zzcmd;
        r0 = (java.util.ArrayList) r0;
        r4 = r0.size();
        r2 = r10;
    L_0x00bc:
        if (r2 >= r4) goto L_0x00d2;
    L_0x00be:
        r1 = r0.get(r2);
        r2 = r2 + 1;
        r1 = (com.google.android.gms.internal.ads.zzahn) r1;
        r5 = r1.zzbth;
        r5 = r3.add(r5);
        if (r5 == 0) goto L_0x00bc;
    L_0x00ce:
        r1.zzoz();
        goto L_0x00bc;
    L_0x00d2:
        r0 = r15.zzcmd;
        r0 = (java.util.ArrayList) r0;
        r4 = r0.size();
        r2 = r10;
    L_0x00db:
        if (r2 >= r4) goto L_0x0145;
    L_0x00dd:
        r1 = r0.get(r2);
        r3 = r2 + 1;
        r1 = (com.google.android.gms.internal.ads.zzahn) r1;
        r2 = r1.zzoz();	 Catch:{ InterruptedException -> 0x0128, Exception -> 0x0158 }
        r2.get();	 Catch:{ InterruptedException -> 0x0128, Exception -> 0x0158 }
        r2 = r15.mLock;
        monitor-enter(r2);
        r5 = r1.zzbth;	 Catch:{ all -> 0x0125 }
        r5 = android.text.TextUtils.isEmpty(r5);	 Catch:{ all -> 0x0125 }
        if (r5 != 0) goto L_0x0100;
    L_0x00f7:
        r5 = r15.zzcme;	 Catch:{ all -> 0x0125 }
        r6 = r1.zzpa();	 Catch:{ all -> 0x0125 }
        r5.add(r6);	 Catch:{ all -> 0x0125 }
    L_0x0100:
        monitor-exit(r2);	 Catch:{ all -> 0x0125 }
        r2 = r15.mLock;
        monitor-enter(r2);
        r5 = r15.zzcmf;	 Catch:{ all -> 0x0198 }
        r6 = r1.zzbth;	 Catch:{ all -> 0x0198 }
        r5 = r5.contains(r6);	 Catch:{ all -> 0x0198 }
        if (r5 == 0) goto L_0x0194;
    L_0x010e:
        r0 = r1.zzbth;	 Catch:{ all -> 0x0198 }
        r1 = r1.zzpb();	 Catch:{ all -> 0x0198 }
        r3 = -2;
        r0 = r15.zza(r3, r0, r1);	 Catch:{ all -> 0x0198 }
        r1 = com.google.android.gms.internal.ads.zzamu.zzsy;	 Catch:{ all -> 0x0198 }
        r3 = new com.google.android.gms.internal.ads.zzahy;	 Catch:{ all -> 0x0198 }
        r3.<init>(r15, r0);	 Catch:{ all -> 0x0198 }
        r1.post(r3);	 Catch:{ all -> 0x0198 }
        monitor-exit(r2);	 Catch:{ all -> 0x0198 }
    L_0x0124:
        return;
    L_0x0125:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0125 }
        throw r0;
    L_0x0128:
        r0 = move-exception;
        r0 = java.lang.Thread.currentThread();	 Catch:{ all -> 0x017a }
        r0.interrupt();	 Catch:{ all -> 0x017a }
        r2 = r15.mLock;
        monitor-enter(r2);
        r0 = r1.zzbth;	 Catch:{ all -> 0x0155 }
        r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ all -> 0x0155 }
        if (r0 != 0) goto L_0x0144;
    L_0x013b:
        r0 = r15.zzcme;	 Catch:{ all -> 0x0155 }
        r1 = r1.zzpa();	 Catch:{ all -> 0x0155 }
        r0.add(r1);	 Catch:{ all -> 0x0155 }
    L_0x0144:
        monitor-exit(r2);	 Catch:{ all -> 0x0155 }
    L_0x0145:
        r0 = 3;
        r0 = r15.zza(r0, r14, r14);
        r1 = com.google.android.gms.internal.ads.zzamu.zzsy;
        r2 = new com.google.android.gms.internal.ads.zzahz;
        r2.<init>(r15, r0);
        r1.post(r2);
        goto L_0x0124;
    L_0x0155:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0155 }
        throw r0;
    L_0x0158:
        r2 = move-exception;
        r5 = "Unable to resolve rewarded adapter.";
        com.google.android.gms.internal.ads.zzane.zzc(r5, r2);	 Catch:{ all -> 0x017a }
        r2 = r15.mLock;
        monitor-enter(r2);
        r5 = r1.zzbth;	 Catch:{ all -> 0x0177 }
        r5 = android.text.TextUtils.isEmpty(r5);	 Catch:{ all -> 0x0177 }
        if (r5 != 0) goto L_0x0173;
    L_0x016a:
        r5 = r15.zzcme;	 Catch:{ all -> 0x0177 }
        r1 = r1.zzpa();	 Catch:{ all -> 0x0177 }
        r5.add(r1);	 Catch:{ all -> 0x0177 }
    L_0x0173:
        monitor-exit(r2);	 Catch:{ all -> 0x0177 }
        r2 = r3;
        goto L_0x00db;
    L_0x0177:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0177 }
        throw r0;
    L_0x017a:
        r0 = move-exception;
        r2 = r15.mLock;
        monitor-enter(r2);
        r3 = r1.zzbth;	 Catch:{ all -> 0x0191 }
        r3 = android.text.TextUtils.isEmpty(r3);	 Catch:{ all -> 0x0191 }
        if (r3 != 0) goto L_0x018f;
    L_0x0186:
        r3 = r15.zzcme;	 Catch:{ all -> 0x0191 }
        r1 = r1.zzpa();	 Catch:{ all -> 0x0191 }
        r3.add(r1);	 Catch:{ all -> 0x0191 }
    L_0x018f:
        monitor-exit(r2);	 Catch:{ all -> 0x0191 }
        throw r0;
    L_0x0191:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0191 }
        throw r0;
    L_0x0194:
        monitor-exit(r2);	 Catch:{ all -> 0x0198 }
        r2 = r3;
        goto L_0x00db;
    L_0x0198:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0198 }
        throw r0;
    L_0x019b:
        r2 = r0;
        goto L_0x004a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzahx.zzdn():void");
    }

    final /* synthetic */ void zzl(zzajh com_google_android_gms_internal_ads_zzajh) {
        this.zzcmg.zzot().zzb(com_google_android_gms_internal_ads_zzajh);
    }

    final /* synthetic */ void zzm(zzajh com_google_android_gms_internal_ads_zzajh) {
        this.zzcmg.zzot().zzb(com_google_android_gms_internal_ads_zzajh);
    }
}
