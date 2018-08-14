package com.google.android.gms.internal.ads;

import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.ads.internal.zzbv;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
public final class zzafn extends zzaeo {
    private static final Object sLock = new Object();
    @GuardedBy("sLock")
    private static zzafn zzchh;
    private final Context mContext;
    private final zzafm zzchi;
    private final ScheduledExecutorService zzchj = Executors.newSingleThreadScheduledExecutor();

    private zzafn(Context context, zzafm com_google_android_gms_internal_ads_zzafm) {
        this.mContext = context;
        this.zzchi = com_google_android_gms_internal_ads_zzafm;
    }

    private static zzaej zza(Context context, zzafm com_google_android_gms_internal_ads_zzafm, zzaef com_google_android_gms_internal_ads_zzaef, ScheduledExecutorService scheduledExecutorService) {
        Future zza;
        zzane.zzck("Starting ad request from service using: google.afma.request.getAdDictionary");
        zznx com_google_android_gms_internal_ads_zznx = new zznx(((Boolean) zzkb.zzik().zzd(zznk.zzawh)).booleanValue(), "load_ad", com_google_android_gms_internal_ads_zzaef.zzacv.zzarb);
        if (com_google_android_gms_internal_ads_zzaef.versionCode > 10 && com_google_android_gms_internal_ads_zzaef.zzcdl != -1) {
            com_google_android_gms_internal_ads_zznx.zza(com_google_android_gms_internal_ads_zznx.zzd(com_google_android_gms_internal_ads_zzaef.zzcdl), "cts");
        }
        zznv zzjj = com_google_android_gms_internal_ads_zznx.zzjj();
        Future zza2 = zzano.zza(com_google_android_gms_internal_ads_zzafm.zzche.zzk(context), ((Long) zzkb.zzik().zzd(zznk.zzbdf)).longValue(), TimeUnit.MILLISECONDS, scheduledExecutorService);
        Future zza3 = zzano.zza(com_google_android_gms_internal_ads_zzafm.zzchd.zzr(context), ((Long) zzkb.zzik().zzd(zznk.zzbah)).longValue(), TimeUnit.MILLISECONDS, scheduledExecutorService);
        Future zzcl = com_google_android_gms_internal_ads_zzafm.zzcgy.zzcl(com_google_android_gms_internal_ads_zzaef.zzccw.packageName);
        Future zzcm = com_google_android_gms_internal_ads_zzafm.zzcgy.zzcm(com_google_android_gms_internal_ads_zzaef.zzccw.packageName);
        Future zza4 = com_google_android_gms_internal_ads_zzafm.zzchf.zza(com_google_android_gms_internal_ads_zzaef.zzccx, com_google_android_gms_internal_ads_zzaef.zzccw);
        Future zzq = zzbv.zzev().zzq(context);
        zzanz zzi = zzano.zzi(null);
        Bundle bundle = com_google_android_gms_internal_ads_zzaef.zzccv.extras;
        Object obj = (bundle == null || bundle.getString("_ad") == null) ? null : 1;
        if (com_google_android_gms_internal_ads_zzaef.zzcdr && obj == null) {
            zzi = com_google_android_gms_internal_ads_zzafm.zzchb.zza(com_google_android_gms_internal_ads_zzaef.applicationInfo);
        }
        zzanz zza5 = zzano.zza(zzi, ((Long) zzkb.zzik().zzd(zznk.zzbco)).longValue(), TimeUnit.MILLISECONDS, scheduledExecutorService);
        zzany zzi2 = zzano.zzi(null);
        if (((Boolean) zzkb.zzik().zzd(zznk.zzayj)).booleanValue()) {
            zza = zzano.zza(com_google_android_gms_internal_ads_zzafm.zzchf.zzae(context), ((Long) zzkb.zzik().zzd(zznk.zzayk)).longValue(), TimeUnit.MILLISECONDS, scheduledExecutorService);
        } else {
            Object obj2 = zzi2;
        }
        Bundle bundle2 = (com_google_android_gms_internal_ads_zzaef.versionCode < 4 || com_google_android_gms_internal_ads_zzaef.zzcdc == null) ? null : com_google_android_gms_internal_ads_zzaef.zzcdc;
        ((Boolean) zzkb.zzik().zzd(zznk.zzawx)).booleanValue();
        zzbv.zzek();
        if (zzakk.zzl(context, "android.permission.ACCESS_NETWORK_STATE")) {
            if (((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo() == null) {
                zzane.zzck("Device is offline.");
            }
        }
        String uuid = com_google_android_gms_internal_ads_zzaef.versionCode >= 7 ? com_google_android_gms_internal_ads_zzaef.zzcdi : UUID.randomUUID().toString();
        zzaft com_google_android_gms_internal_ads_zzaft = new zzaft(context, uuid, com_google_android_gms_internal_ads_zzaef.applicationInfo.packageName);
        if (com_google_android_gms_internal_ads_zzaef.zzccv.extras != null) {
            String string = com_google_android_gms_internal_ads_zzaef.zzccv.extras.getString("_ad");
            if (string != null) {
                return zzafs.zza(context, com_google_android_gms_internal_ads_zzaef, string);
            }
        }
        List zzf = com_google_android_gms_internal_ads_zzafm.zzcgz.zzf(com_google_android_gms_internal_ads_zzaef.zzcdj);
        bundle = (Bundle) zzano.zza(zza2, null, ((Long) zzkb.zzik().zzd(zznk.zzbdf)).longValue(), TimeUnit.MILLISECONDS);
        zzagk com_google_android_gms_internal_ads_zzagk = (zzagk) zzano.zza(zza3, null);
        Location location = (Location) zzano.zza((Future) zza5, null);
        Info info = (Info) zzano.zza(zza, null);
        String str = (String) zzano.zza(zza4, null);
        String str2 = (String) zzano.zza(zzcl, null);
        String str3 = (String) zzano.zza(zzcm, null);
        zzaga com_google_android_gms_internal_ads_zzaga = (zzaga) zzano.zza(zzq, null);
        if (com_google_android_gms_internal_ads_zzaga == null) {
            zzane.zzdk("Error fetching device info. This is not recoverable.");
            return new zzaej(0);
        }
        zzafl com_google_android_gms_internal_ads_zzafl = new zzafl();
        com_google_android_gms_internal_ads_zzafl.zzcgs = com_google_android_gms_internal_ads_zzaef;
        com_google_android_gms_internal_ads_zzafl.zzcgt = com_google_android_gms_internal_ads_zzaga;
        com_google_android_gms_internal_ads_zzafl.zzcgo = com_google_android_gms_internal_ads_zzagk;
        com_google_android_gms_internal_ads_zzafl.zzaqe = location;
        com_google_android_gms_internal_ads_zzafl.zzcgn = bundle;
        com_google_android_gms_internal_ads_zzafl.zzccx = str;
        com_google_android_gms_internal_ads_zzafl.zzcgr = info;
        if (zzf == null) {
            com_google_android_gms_internal_ads_zzafl.zzcdj.clear();
        }
        com_google_android_gms_internal_ads_zzafl.zzcdj = zzf;
        com_google_android_gms_internal_ads_zzafl.zzcdc = bundle2;
        com_google_android_gms_internal_ads_zzafl.zzcgp = str2;
        com_google_android_gms_internal_ads_zzafl.zzcgq = str3;
        com_google_android_gms_internal_ads_zzafl.zzcgu = com_google_android_gms_internal_ads_zzafm.zzcgx.zze(context);
        com_google_android_gms_internal_ads_zzafl.zzcgv = com_google_android_gms_internal_ads_zzafm.zzcgv;
        JSONObject zza6 = zzafs.zza(context, com_google_android_gms_internal_ads_zzafl);
        if (zza6 == null) {
            return new zzaej(0);
        }
        if (com_google_android_gms_internal_ads_zzaef.versionCode < 7) {
            try {
                zza6.put("request_id", uuid);
            } catch (JSONException e) {
            }
        }
        com_google_android_gms_internal_ads_zznx.zza(zzjj, "arc");
        com_google_android_gms_internal_ads_zznx.zzjj();
        Future zza7 = zzano.zza(zzano.zza(com_google_android_gms_internal_ads_zzafm.zzchg.zzof().zzf(zza6), zzafo.zzxn, (Executor) scheduledExecutorService), 10, TimeUnit.SECONDS, scheduledExecutorService);
        zzi = com_google_android_gms_internal_ads_zzafm.zzcha.zzop();
        if (zzi != null) {
            zzanm.zza(zzi, "AdRequestServiceImpl.loadAd.flags");
        }
        zzafz com_google_android_gms_internal_ads_zzafz = (zzafz) zzano.zza(zza7, null);
        if (com_google_android_gms_internal_ads_zzafz == null) {
            return new zzaej(0);
        }
        if (com_google_android_gms_internal_ads_zzafz.getErrorCode() != -2) {
            return new zzaej(com_google_android_gms_internal_ads_zzafz.getErrorCode());
        }
        com_google_android_gms_internal_ads_zznx.zzjm();
        zzaej com_google_android_gms_internal_ads_zzaej = null;
        if (!TextUtils.isEmpty(com_google_android_gms_internal_ads_zzafz.zzom())) {
            com_google_android_gms_internal_ads_zzaej = zzafs.zza(context, com_google_android_gms_internal_ads_zzaef, com_google_android_gms_internal_ads_zzafz.zzom());
        }
        if (com_google_android_gms_internal_ads_zzaej == null && !TextUtils.isEmpty(com_google_android_gms_internal_ads_zzafz.getUrl())) {
            com_google_android_gms_internal_ads_zzaej = zza(com_google_android_gms_internal_ads_zzaef, context, com_google_android_gms_internal_ads_zzaef.zzacr.zzcw, com_google_android_gms_internal_ads_zzafz.getUrl(), str2, str3, com_google_android_gms_internal_ads_zzafz, com_google_android_gms_internal_ads_zznx, com_google_android_gms_internal_ads_zzafm);
        }
        if (com_google_android_gms_internal_ads_zzaej == null) {
            com_google_android_gms_internal_ads_zzaej = new zzaej(0);
        }
        com_google_android_gms_internal_ads_zznx.zza(zzjj, "tts");
        com_google_android_gms_internal_ads_zzaej.zzcfd = com_google_android_gms_internal_ads_zznx.zzjk();
        return com_google_android_gms_internal_ads_zzaej;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.ads.zzaej zza(com.google.android.gms.internal.ads.zzaef r14, android.content.Context r15, java.lang.String r16, java.lang.String r17, java.lang.String r18, java.lang.String r19, com.google.android.gms.internal.ads.zzafz r20, com.google.android.gms.internal.ads.zznx r21, com.google.android.gms.internal.ads.zzafm r22) {
        /*
        if (r21 == 0) goto L_0x0116;
    L_0x0002:
        r2 = r21.zzjj();
        r4 = r2;
    L_0x0007:
        r9 = new com.google.android.gms.internal.ads.zzafx;	 Catch:{ IOException -> 0x0121 }
        r2 = r20.zzoi();	 Catch:{ IOException -> 0x0121 }
        r9.<init>(r14, r2);	 Catch:{ IOException -> 0x0121 }
        r3 = "AdRequestServiceImpl: Sending request: ";
        r2 = java.lang.String.valueOf(r17);	 Catch:{ IOException -> 0x0121 }
        r5 = r2.length();	 Catch:{ IOException -> 0x0121 }
        if (r5 == 0) goto L_0x011a;
    L_0x001d:
        r2 = r3.concat(r2);	 Catch:{ IOException -> 0x0121 }
    L_0x0021:
        com.google.android.gms.internal.ads.zzane.zzck(r2);	 Catch:{ IOException -> 0x0121 }
        r3 = new java.net.URL;	 Catch:{ IOException -> 0x0121 }
        r0 = r17;
        r3.<init>(r0);	 Catch:{ IOException -> 0x0121 }
        r2 = 0;
        r5 = com.google.android.gms.ads.internal.zzbv.zzer();	 Catch:{ IOException -> 0x0121 }
        r10 = r5.elapsedRealtime();	 Catch:{ IOException -> 0x0121 }
        r7 = r2;
        r8 = r3;
    L_0x0036:
        if (r22 == 0) goto L_0x003f;
    L_0x0038:
        r0 = r22;
        r2 = r0.zzchc;	 Catch:{ IOException -> 0x0121 }
        r2.zzoq();	 Catch:{ IOException -> 0x0121 }
    L_0x003f:
        r2 = r8.openConnection();	 Catch:{ IOException -> 0x0121 }
        r2 = (java.net.HttpURLConnection) r2;	 Catch:{ IOException -> 0x0121 }
        r3 = com.google.android.gms.ads.internal.zzbv.zzek();	 Catch:{ all -> 0x0147 }
        r5 = 0;
        r0 = r16;
        r3.zza(r15, r0, r5, r2);	 Catch:{ all -> 0x0147 }
        r3 = r20.zzok();	 Catch:{ all -> 0x0147 }
        if (r3 == 0) goto L_0x0071;
    L_0x0055:
        r3 = android.text.TextUtils.isEmpty(r18);	 Catch:{ all -> 0x0147 }
        if (r3 != 0) goto L_0x0063;
    L_0x005b:
        r3 = "x-afma-drt-cookie";
        r0 = r18;
        r2.addRequestProperty(r3, r0);	 Catch:{ all -> 0x0147 }
    L_0x0063:
        r3 = android.text.TextUtils.isEmpty(r19);	 Catch:{ all -> 0x0147 }
        if (r3 != 0) goto L_0x0071;
    L_0x0069:
        r3 = "x-afma-drt-v2-cookie";
        r0 = r19;
        r2.addRequestProperty(r3, r0);	 Catch:{ all -> 0x0147 }
    L_0x0071:
        r3 = r14.zzcds;	 Catch:{ all -> 0x0147 }
        r5 = android.text.TextUtils.isEmpty(r3);	 Catch:{ all -> 0x0147 }
        if (r5 != 0) goto L_0x0085;
    L_0x0079:
        r5 = "Sending webview cookie in ad request header.";
        com.google.android.gms.internal.ads.zzane.zzck(r5);	 Catch:{ all -> 0x0147 }
        r5 = "Cookie";
        r2.addRequestProperty(r5, r3);	 Catch:{ all -> 0x0147 }
    L_0x0085:
        r3 = 0;
        if (r20 == 0) goto L_0x00b2;
    L_0x0088:
        r5 = r20.zzoj();	 Catch:{ all -> 0x0147 }
        r5 = android.text.TextUtils.isEmpty(r5);	 Catch:{ all -> 0x0147 }
        if (r5 != 0) goto L_0x00b2;
    L_0x0092:
        r3 = 1;
        r2.setDoOutput(r3);	 Catch:{ all -> 0x0147 }
        r3 = r20.zzoj();	 Catch:{ all -> 0x0147 }
        r3 = r3.getBytes();	 Catch:{ all -> 0x0147 }
        r5 = r3.length;	 Catch:{ all -> 0x0147 }
        r2.setFixedLengthStreamingMode(r5);	 Catch:{ all -> 0x0147 }
        r6 = 0;
        r5 = new java.io.BufferedOutputStream;	 Catch:{ all -> 0x0141 }
        r12 = r2.getOutputStream();	 Catch:{ all -> 0x0141 }
        r5.<init>(r12);	 Catch:{ all -> 0x0141 }
        r5.write(r3);	 Catch:{ all -> 0x0216 }
        com.google.android.gms.common.util.IOUtils.closeQuietly(r5);	 Catch:{ all -> 0x0147 }
    L_0x00b2:
        r12 = new com.google.android.gms.internal.ads.zzamy;	 Catch:{ all -> 0x0147 }
        r5 = r14.zzcdi;	 Catch:{ all -> 0x0147 }
        r12.<init>(r5);	 Catch:{ all -> 0x0147 }
        r12.zza(r2, r3);	 Catch:{ all -> 0x0147 }
        r3 = r2.getResponseCode();	 Catch:{ all -> 0x0147 }
        r13 = r2.getHeaderFields();	 Catch:{ all -> 0x0147 }
        r12.zza(r2, r3);	 Catch:{ all -> 0x0147 }
        r5 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r3 < r5) goto L_0x015b;
    L_0x00cb:
        r5 = 300; // 0x12c float:4.2E-43 double:1.48E-321;
        if (r3 >= r5) goto L_0x015b;
    L_0x00cf:
        r7 = r8.toString();	 Catch:{ all -> 0x0147 }
        r6 = 0;
        r5 = new java.io.InputStreamReader;	 Catch:{ all -> 0x0155 }
        r8 = r2.getInputStream();	 Catch:{ all -> 0x0155 }
        r5.<init>(r8);	 Catch:{ all -> 0x0155 }
        com.google.android.gms.ads.internal.zzbv.zzek();	 Catch:{ all -> 0x0212 }
        r6 = com.google.android.gms.internal.ads.zzakk.zza(r5);	 Catch:{ all -> 0x0212 }
        com.google.android.gms.common.util.IOUtils.closeQuietly(r5);	 Catch:{ all -> 0x0147 }
        r12.zzdg(r6);	 Catch:{ all -> 0x0147 }
        zza(r7, r13, r6, r3);	 Catch:{ all -> 0x0147 }
        r9.zza(r7, r13, r6);	 Catch:{ all -> 0x0147 }
        if (r21 == 0) goto L_0x0100;
    L_0x00f2:
        r3 = 1;
        r3 = new java.lang.String[r3];	 Catch:{ all -> 0x0147 }
        r5 = 0;
        r6 = "ufe";
        r3[r5] = r6;	 Catch:{ all -> 0x0147 }
        r0 = r21;
        r0.zza(r4, r3);	 Catch:{ all -> 0x0147 }
    L_0x0100:
        r3 = r20.zzon();	 Catch:{ all -> 0x0147 }
        r3 = r9.zza(r10, r3);	 Catch:{ all -> 0x0147 }
        r2.disconnect();	 Catch:{ IOException -> 0x0121 }
        if (r22 == 0) goto L_0x0114;
    L_0x010d:
        r0 = r22;
        r2 = r0.zzchc;	 Catch:{ IOException -> 0x0121 }
        r2.zzor();	 Catch:{ IOException -> 0x0121 }
    L_0x0114:
        r2 = r3;
    L_0x0115:
        return r2;
    L_0x0116:
        r2 = 0;
        r4 = r2;
        goto L_0x0007;
    L_0x011a:
        r2 = new java.lang.String;	 Catch:{ IOException -> 0x0121 }
        r2.<init>(r3);	 Catch:{ IOException -> 0x0121 }
        goto L_0x0021;
    L_0x0121:
        r2 = move-exception;
        r3 = "Error while connecting to ad server: ";
        r2 = r2.getMessage();
        r2 = java.lang.String.valueOf(r2);
        r4 = r2.length();
        if (r4 == 0) goto L_0x020b;
    L_0x0133:
        r2 = r3.concat(r2);
    L_0x0137:
        com.google.android.gms.internal.ads.zzane.zzdk(r2);
        r2 = new com.google.android.gms.internal.ads.zzaej;
        r3 = 2;
        r2.<init>(r3);
        goto L_0x0115;
    L_0x0141:
        r3 = move-exception;
        r4 = r6;
    L_0x0143:
        com.google.android.gms.common.util.IOUtils.closeQuietly(r4);	 Catch:{ all -> 0x0147 }
        throw r3;	 Catch:{ all -> 0x0147 }
    L_0x0147:
        r3 = move-exception;
        r2.disconnect();	 Catch:{ IOException -> 0x0121 }
        if (r22 == 0) goto L_0x0154;
    L_0x014d:
        r0 = r22;
        r2 = r0.zzchc;	 Catch:{ IOException -> 0x0121 }
        r2.zzor();	 Catch:{ IOException -> 0x0121 }
    L_0x0154:
        throw r3;	 Catch:{ IOException -> 0x0121 }
    L_0x0155:
        r3 = move-exception;
        r4 = r6;
    L_0x0157:
        com.google.android.gms.common.util.IOUtils.closeQuietly(r4);	 Catch:{ all -> 0x0147 }
        throw r3;	 Catch:{ all -> 0x0147 }
    L_0x015b:
        r5 = r8.toString();	 Catch:{ all -> 0x0147 }
        r6 = 0;
        zza(r5, r13, r6, r3);	 Catch:{ all -> 0x0147 }
        r5 = 300; // 0x12c float:4.2E-43 double:1.48E-321;
        if (r3 < r5) goto L_0x01c6;
    L_0x0167:
        r5 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        if (r3 >= r5) goto L_0x01c6;
    L_0x016b:
        r3 = "Location";
        r3 = r2.getHeaderField(r3);	 Catch:{ all -> 0x0147 }
        r5 = android.text.TextUtils.isEmpty(r3);	 Catch:{ all -> 0x0147 }
        if (r5 == 0) goto L_0x0192;
    L_0x0178:
        r3 = "No location header to follow redirect.";
        com.google.android.gms.internal.ads.zzane.zzdk(r3);	 Catch:{ all -> 0x0147 }
        r3 = new com.google.android.gms.internal.ads.zzaej;	 Catch:{ all -> 0x0147 }
        r4 = 0;
        r3.<init>(r4);	 Catch:{ all -> 0x0147 }
        r2.disconnect();	 Catch:{ IOException -> 0x0121 }
        if (r22 == 0) goto L_0x0190;
    L_0x0189:
        r0 = r22;
        r2 = r0.zzchc;	 Catch:{ IOException -> 0x0121 }
        r2.zzor();	 Catch:{ IOException -> 0x0121 }
    L_0x0190:
        r2 = r3;
        goto L_0x0115;
    L_0x0192:
        r6 = new java.net.URL;	 Catch:{ all -> 0x0147 }
        r6.<init>(r3);	 Catch:{ all -> 0x0147 }
        r5 = r7 + 1;
        r3 = com.google.android.gms.internal.ads.zznk.zzbes;	 Catch:{ all -> 0x0147 }
        r7 = com.google.android.gms.internal.ads.zzkb.zzik();	 Catch:{ all -> 0x0147 }
        r3 = r7.zzd(r3);	 Catch:{ all -> 0x0147 }
        r3 = (java.lang.Integer) r3;	 Catch:{ all -> 0x0147 }
        r3 = r3.intValue();	 Catch:{ all -> 0x0147 }
        if (r5 <= r3) goto L_0x01f4;
    L_0x01ab:
        r3 = "Too many redirects.";
        com.google.android.gms.internal.ads.zzane.zzdk(r3);	 Catch:{ all -> 0x0147 }
        r3 = new com.google.android.gms.internal.ads.zzaej;	 Catch:{ all -> 0x0147 }
        r4 = 0;
        r3.<init>(r4);	 Catch:{ all -> 0x0147 }
        r2.disconnect();	 Catch:{ IOException -> 0x0121 }
        if (r22 == 0) goto L_0x01c3;
    L_0x01bc:
        r0 = r22;
        r2 = r0.zzchc;	 Catch:{ IOException -> 0x0121 }
        r2.zzor();	 Catch:{ IOException -> 0x0121 }
    L_0x01c3:
        r2 = r3;
        goto L_0x0115;
    L_0x01c6:
        r4 = 46;
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0147 }
        r5.<init>(r4);	 Catch:{ all -> 0x0147 }
        r4 = "Received error HTTP response code: ";
        r4 = r5.append(r4);	 Catch:{ all -> 0x0147 }
        r3 = r4.append(r3);	 Catch:{ all -> 0x0147 }
        r3 = r3.toString();	 Catch:{ all -> 0x0147 }
        com.google.android.gms.internal.ads.zzane.zzdk(r3);	 Catch:{ all -> 0x0147 }
        r3 = new com.google.android.gms.internal.ads.zzaej;	 Catch:{ all -> 0x0147 }
        r4 = 0;
        r3.<init>(r4);	 Catch:{ all -> 0x0147 }
        r2.disconnect();	 Catch:{ IOException -> 0x0121 }
        if (r22 == 0) goto L_0x01f1;
    L_0x01ea:
        r0 = r22;
        r2 = r0.zzchc;	 Catch:{ IOException -> 0x0121 }
        r2.zzor();	 Catch:{ IOException -> 0x0121 }
    L_0x01f1:
        r2 = r3;
        goto L_0x0115;
    L_0x01f4:
        r9.zzl(r13);	 Catch:{ all -> 0x0147 }
        r2.disconnect();	 Catch:{ IOException -> 0x0121 }
        if (r22 == 0) goto L_0x0207;
    L_0x01fc:
        r0 = r22;
        r2 = r0.zzchc;	 Catch:{ IOException -> 0x0121 }
        r2.zzor();	 Catch:{ IOException -> 0x0121 }
        r7 = r5;
        r8 = r6;
        goto L_0x0036;
    L_0x0207:
        r7 = r5;
        r8 = r6;
        goto L_0x0036;
    L_0x020b:
        r2 = new java.lang.String;
        r2.<init>(r3);
        goto L_0x0137;
    L_0x0212:
        r3 = move-exception;
        r4 = r5;
        goto L_0x0157;
    L_0x0216:
        r3 = move-exception;
        r4 = r5;
        goto L_0x0143;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzafn.zza(com.google.android.gms.internal.ads.zzaef, android.content.Context, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.google.android.gms.internal.ads.zzafz, com.google.android.gms.internal.ads.zznx, com.google.android.gms.internal.ads.zzafm):com.google.android.gms.internal.ads.zzaej");
    }

    public static zzafn zza(Context context, zzafm com_google_android_gms_internal_ads_zzafm) {
        zzafn com_google_android_gms_internal_ads_zzafn;
        synchronized (sLock) {
            if (zzchh == null) {
                if (context.getApplicationContext() != null) {
                    context = context.getApplicationContext();
                }
                zznk.initialize(context);
                zzchh = new zzafn(context, com_google_android_gms_internal_ads_zzafm);
                if (context.getApplicationContext() != null) {
                    zzbv.zzek().zzal(context);
                }
                zzajz.zzai(context);
            }
            com_google_android_gms_internal_ads_zzafn = zzchh;
        }
        return com_google_android_gms_internal_ads_zzafn;
    }

    private static void zza(String str, Map<String, List<String>> map, String str2, int i) {
        if (zzane.isLoggable(2)) {
            zzakb.m3428v(new StringBuilder(String.valueOf(str).length() + 39).append("Http Response: {\n  URL:\n    ").append(str).append("\n  Headers:").toString());
            if (map != null) {
                for (String str3 : map.keySet()) {
                    String str32;
                    zzakb.m3428v(new StringBuilder(String.valueOf(str32).length() + 5).append("    ").append(str32).append(":").toString());
                    for (String str322 : (List) map.get(str322)) {
                        String str4 = "      ";
                        str322 = String.valueOf(str322);
                        zzakb.m3428v(str322.length() != 0 ? str4.concat(str322) : new String(str4));
                    }
                }
            }
            zzakb.m3428v("  Body:");
            if (str2 != null) {
                for (int i2 = 0; i2 < Math.min(str2.length(), 100000); i2 += 1000) {
                    zzakb.m3428v(str2.substring(i2, Math.min(str2.length(), i2 + 1000)));
                }
            } else {
                zzakb.m3428v("    null");
            }
            zzakb.m3428v("  Response Code:\n    " + i + "\n}");
        }
    }

    public final void zza(zzaef com_google_android_gms_internal_ads_zzaef, zzaeq com_google_android_gms_internal_ads_zzaeq) {
        zzbv.zzeo().zzd(this.mContext, com_google_android_gms_internal_ads_zzaef.zzacr);
        Future zzb = zzaki.zzb(new zzafp(this, com_google_android_gms_internal_ads_zzaef, com_google_android_gms_internal_ads_zzaeq));
        zzbv.zzez().zzsa();
        zzbv.zzez().getHandler().postDelayed(new zzafq(this, zzb), 60000);
    }

    public final void zza(zzaey com_google_android_gms_internal_ads_zzaey, zzaet com_google_android_gms_internal_ads_zzaet) {
        zzakb.m3428v("Nonagon code path entered in octagon");
        throw new IllegalArgumentException();
    }

    public final zzaej zzb(zzaef com_google_android_gms_internal_ads_zzaef) {
        return zza(this.mContext, this.zzchi, com_google_android_gms_internal_ads_zzaef, this.zzchj);
    }

    public final void zzb(zzaey com_google_android_gms_internal_ads_zzaey, zzaet com_google_android_gms_internal_ads_zzaet) {
        zzakb.m3428v("Nonagon code path entered in octagon");
        throw new IllegalArgumentException();
    }
}
