package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.io.InputStream;

public class zzaj implements zzm {
    private static final boolean DEBUG = zzaf.DEBUG;
    @Deprecated
    private final zzar zzbo;
    private final zzai zzbp;
    private final zzak zzbq;

    public zzaj(zzai com_google_android_gms_internal_ads_zzai) {
        this(com_google_android_gms_internal_ads_zzai, new zzak(4096));
    }

    private zzaj(zzai com_google_android_gms_internal_ads_zzai, zzak com_google_android_gms_internal_ads_zzak) {
        this.zzbp = com_google_android_gms_internal_ads_zzai;
        this.zzbo = com_google_android_gms_internal_ads_zzai;
        this.zzbq = com_google_android_gms_internal_ads_zzak;
    }

    @Deprecated
    public zzaj(zzar com_google_android_gms_internal_ads_zzar) {
        this(com_google_android_gms_internal_ads_zzar, new zzak(4096));
    }

    @Deprecated
    private zzaj(zzar com_google_android_gms_internal_ads_zzar, zzak com_google_android_gms_internal_ads_zzak) {
        this.zzbo = com_google_android_gms_internal_ads_zzar;
        this.zzbp = new zzah(com_google_android_gms_internal_ads_zzar);
        this.zzbq = com_google_android_gms_internal_ads_zzak;
    }

    private static void zza(String str, zzr<?> com_google_android_gms_internal_ads_zzr_, zzae com_google_android_gms_internal_ads_zzae) throws zzae {
        zzab zzj = com_google_android_gms_internal_ads_zzr_.zzj();
        int zzi = com_google_android_gms_internal_ads_zzr_.zzi();
        try {
            zzj.zza(com_google_android_gms_internal_ads_zzae);
            com_google_android_gms_internal_ads_zzr_.zzb(String.format("%s-retry [timeout=%s]", new Object[]{str, Integer.valueOf(zzi)}));
        } catch (zzae e) {
            com_google_android_gms_internal_ads_zzr_.zzb(String.format("%s-timeout-giveup [timeout=%s]", new Object[]{str, Integer.valueOf(zzi)}));
            throw e;
        }
    }

    private final byte[] zza(InputStream inputStream, int i) throws IOException, zzac {
        zzau com_google_android_gms_internal_ads_zzau = new zzau(this.zzbq, i);
        if (inputStream == null) {
            try {
                throw new zzac();
            } catch (Throwable th) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        zzaf.v("Error occurred when closing InputStream", new Object[0]);
                    }
                }
                this.zzbq.zza(null);
                com_google_android_gms_internal_ads_zzau.close();
            }
        } else {
            byte[] zzb = this.zzbq.zzb(1024);
            while (true) {
                int read = inputStream.read(zzb);
                if (read == -1) {
                    break;
                }
                com_google_android_gms_internal_ads_zzau.write(zzb, 0, read);
            }
            byte[] toByteArray = com_google_android_gms_internal_ads_zzau.toByteArray();
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e2) {
                    zzaf.v("Error occurred when closing InputStream", new Object[0]);
                }
            }
            this.zzbq.zza(zzb);
            com_google_android_gms_internal_ads_zzau.close();
            return toByteArray;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.internal.ads.zzp zzc(com.google.android.gms.internal.ads.zzr<?> r21) throws com.google.android.gms.internal.ads.zzae {
        /*
        r20 = this;
        r18 = android.os.SystemClock.elapsedRealtime();
    L_0x0004:
        r3 = 0;
        r9 = 0;
        r8 = java.util.Collections.emptyList();
        r4 = r21.zzf();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x023e }
        if (r4 != 0) goto L_0x0040;
    L_0x0010:
        r2 = java.util.Collections.emptyMap();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x023e }
    L_0x0014:
        r0 = r20;
        r4 = r0.zzbp;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x023e }
        r0 = r21;
        r17 = r4.zza(r0, r2);	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x023e }
        r3 = r17.getStatusCode();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        r8 = r17.zzq();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        r2 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        if (r3 != r2) goto L_0x0184;
    L_0x002a:
        r4 = r21.zzf();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        if (r4 != 0) goto L_0x0075;
    L_0x0030:
        r2 = new com.google.android.gms.internal.ads.zzp;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        r3 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        r4 = 0;
        r5 = 1;
        r6 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        r6 = r6 - r18;
        r2.<init>(r3, r4, r5, r6, r8);	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
    L_0x003f:
        return r2;
    L_0x0040:
        r2 = new java.util.HashMap;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x023e }
        r2.<init>();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x023e }
        r5 = r4.zza;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x023e }
        if (r5 == 0) goto L_0x0051;
    L_0x0049:
        r5 = "If-None-Match";
        r6 = r4.zza;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x023e }
        r2.put(r5, r6);	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x023e }
    L_0x0051:
        r6 = r4.zzc;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x023e }
        r10 = 0;
        r5 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1));
        if (r5 <= 0) goto L_0x0014;
    L_0x0059:
        r5 = "If-Modified-Since";
        r6 = r4.zzc;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x023e }
        r4 = com.google.android.gms.internal.ads.zzap.zzb(r6);	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x023e }
        r2.put(r5, r4);	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x023e }
        goto L_0x0014;
    L_0x0066:
        r2 = move-exception;
        r2 = "socket";
        r3 = new com.google.android.gms.internal.ads.zzad;
        r3.<init>();
        r0 = r21;
        zza(r2, r0, r3);
        goto L_0x0004;
    L_0x0075:
        r5 = new java.util.TreeSet;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        r2 = java.lang.String.CASE_INSENSITIVE_ORDER;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        r5.<init>(r2);	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        r2 = r8.isEmpty();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        if (r2 != 0) goto L_0x00b7;
    L_0x0082:
        r3 = r8.iterator();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
    L_0x0086:
        r2 = r3.hasNext();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        if (r2 == 0) goto L_0x00b7;
    L_0x008c:
        r2 = r3.next();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        r2 = (com.google.android.gms.internal.ads.zzl) r2;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        r2 = r2.getName();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        r5.add(r2);	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        goto L_0x0086;
    L_0x009a:
        r2 = move-exception;
        r3 = r2;
        r4 = new java.lang.RuntimeException;
        r5 = "Bad URL ";
        r2 = r21.getUrl();
        r2 = java.lang.String.valueOf(r2);
        r6 = r2.length();
        if (r6 == 0) goto L_0x0200;
    L_0x00af:
        r2 = r5.concat(r2);
    L_0x00b3:
        r4.<init>(r2, r3);
        throw r4;
    L_0x00b7:
        r16 = new java.util.ArrayList;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        r0 = r16;
        r0.<init>(r8);	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        r2 = r4.zzg;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        if (r2 == 0) goto L_0x0132;
    L_0x00c2:
        r2 = r4.zzg;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        r2 = r2.isEmpty();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        if (r2 != 0) goto L_0x0171;
    L_0x00ca:
        r2 = r4.zzg;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        r3 = r2.iterator();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
    L_0x00d0:
        r2 = r3.hasNext();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        if (r2 == 0) goto L_0x0171;
    L_0x00d6:
        r2 = r3.next();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        r2 = (com.google.android.gms.internal.ads.zzl) r2;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        r6 = r2.getName();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        r6 = r5.contains(r6);	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        if (r6 != 0) goto L_0x00d0;
    L_0x00e6:
        r0 = r16;
        r0.add(r2);	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        goto L_0x00d0;
    L_0x00ec:
        r2 = move-exception;
        r4 = r9;
        r3 = r17;
    L_0x00f0:
        if (r3 == 0) goto L_0x0207;
    L_0x00f2:
        r3 = r3.getStatusCode();
        r2 = "Unexpected response code %d for %s";
        r5 = 2;
        r5 = new java.lang.Object[r5];
        r6 = 0;
        r7 = java.lang.Integer.valueOf(r3);
        r5[r6] = r7;
        r6 = 1;
        r7 = r21.getUrl();
        r5[r6] = r7;
        com.google.android.gms.internal.ads.zzaf.e(r2, r5);
        if (r4 == 0) goto L_0x022f;
    L_0x010f:
        r2 = new com.google.android.gms.internal.ads.zzp;
        r5 = 0;
        r6 = android.os.SystemClock.elapsedRealtime();
        r6 = r6 - r18;
        r2.<init>(r3, r4, r5, r6, r8);
        r4 = 401; // 0x191 float:5.62E-43 double:1.98E-321;
        if (r3 == r4) goto L_0x0123;
    L_0x011f:
        r4 = 403; // 0x193 float:5.65E-43 double:1.99E-321;
        if (r3 != r4) goto L_0x020d;
    L_0x0123:
        r3 = "auth";
        r4 = new com.google.android.gms.internal.ads.zza;
        r4.<init>(r2);
        r0 = r21;
        zza(r3, r0, r4);
        goto L_0x0004;
    L_0x0132:
        r2 = r4.zzf;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        r2 = r2.isEmpty();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        if (r2 != 0) goto L_0x0171;
    L_0x013a:
        r2 = r4.zzf;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        r2 = r2.entrySet();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        r6 = r2.iterator();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
    L_0x0144:
        r2 = r6.hasNext();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        if (r2 == 0) goto L_0x0171;
    L_0x014a:
        r2 = r6.next();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        r2 = (java.util.Map.Entry) r2;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        r3 = r2.getKey();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        r3 = r5.contains(r3);	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        if (r3 != 0) goto L_0x0144;
    L_0x015a:
        r7 = new com.google.android.gms.internal.ads.zzl;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        r3 = r2.getKey();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        r3 = (java.lang.String) r3;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        r2 = r2.getValue();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        r2 = (java.lang.String) r2;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        r7.<init>(r3, r2);	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        r0 = r16;
        r0.add(r7);	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        goto L_0x0144;
    L_0x0171:
        r10 = new com.google.android.gms.internal.ads.zzp;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        r11 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        r12 = r4.data;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        r13 = 1;
        r2 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        r14 = r2 - r18;
        r10.<init>(r11, r12, r13, r14, r16);	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        r2 = r10;
        goto L_0x003f;
    L_0x0184:
        r2 = r17.getContent();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        if (r2 == 0) goto L_0x01ea;
    L_0x018a:
        r4 = r17.getContentLength();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        r0 = r20;
        r4 = r0.zza(r2, r4);	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
    L_0x0194:
        r6 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x01e5 }
        r6 = r6 - r18;
        r2 = DEBUG;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x01e5 }
        if (r2 != 0) goto L_0x01a4;
    L_0x019e:
        r10 = 3000; // 0xbb8 float:4.204E-42 double:1.482E-320;
        r2 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1));
        if (r2 <= 0) goto L_0x01d7;
    L_0x01a4:
        r5 = "HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]";
        r2 = 5;
        r9 = new java.lang.Object[r2];	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x01e5 }
        r2 = 0;
        r9[r2] = r21;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x01e5 }
        r2 = 1;
        r6 = java.lang.Long.valueOf(r6);	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x01e5 }
        r9[r2] = r6;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x01e5 }
        r6 = 2;
        if (r4 == 0) goto L_0x01ee;
    L_0x01b7:
        r2 = r4.length;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x01e5 }
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x01e5 }
    L_0x01bc:
        r9[r6] = r2;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x01e5 }
        r2 = 3;
        r6 = java.lang.Integer.valueOf(r3);	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x01e5 }
        r9[r2] = r6;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x01e5 }
        r2 = 4;
        r6 = r21.zzj();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x01e5 }
        r6 = r6.zzd();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x01e5 }
        r6 = java.lang.Integer.valueOf(r6);	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x01e5 }
        r9[r2] = r6;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x01e5 }
        com.google.android.gms.internal.ads.zzaf.d(r5, r9);	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x01e5 }
    L_0x01d7:
        r2 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r3 < r2) goto L_0x01df;
    L_0x01db:
        r2 = 299; // 0x12b float:4.19E-43 double:1.477E-321;
        if (r3 <= r2) goto L_0x01f2;
    L_0x01df:
        r2 = new java.io.IOException;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x01e5 }
        r2.<init>();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x01e5 }
        throw r2;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x01e5 }
    L_0x01e5:
        r2 = move-exception;
        r3 = r17;
        goto L_0x00f0;
    L_0x01ea:
        r2 = 0;
        r4 = new byte[r2];	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x00ec }
        goto L_0x0194;
    L_0x01ee:
        r2 = "null";
        goto L_0x01bc;
    L_0x01f2:
        r2 = new com.google.android.gms.internal.ads.zzp;	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x01e5 }
        r5 = 0;
        r6 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x01e5 }
        r6 = r6 - r18;
        r2.<init>(r3, r4, r5, r6, r8);	 Catch:{ SocketTimeoutException -> 0x0066, MalformedURLException -> 0x009a, IOException -> 0x01e5 }
        goto L_0x003f;
    L_0x0200:
        r2 = new java.lang.String;
        r2.<init>(r5);
        goto L_0x00b3;
    L_0x0207:
        r3 = new com.google.android.gms.internal.ads.zzq;
        r3.<init>(r2);
        throw r3;
    L_0x020d:
        r4 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        if (r3 < r4) goto L_0x021b;
    L_0x0211:
        r4 = 499; // 0x1f3 float:6.99E-43 double:2.465E-321;
        if (r3 > r4) goto L_0x021b;
    L_0x0215:
        r3 = new com.google.android.gms.internal.ads.zzg;
        r3.<init>(r2);
        throw r3;
    L_0x021b:
        r4 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        if (r3 < r4) goto L_0x0229;
    L_0x021f:
        r4 = 599; // 0x257 float:8.4E-43 double:2.96E-321;
        if (r3 > r4) goto L_0x0229;
    L_0x0223:
        r3 = new com.google.android.gms.internal.ads.zzac;
        r3.<init>(r2);
        throw r3;
    L_0x0229:
        r3 = new com.google.android.gms.internal.ads.zzac;
        r3.<init>(r2);
        throw r3;
    L_0x022f:
        r2 = "network";
        r3 = new com.google.android.gms.internal.ads.zzo;
        r3.<init>();
        r0 = r21;
        zza(r2, r0, r3);
        goto L_0x0004;
    L_0x023e:
        r2 = move-exception;
        r4 = r9;
        goto L_0x00f0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaj.zzc(com.google.android.gms.internal.ads.zzr):com.google.android.gms.internal.ads.zzp");
    }
}
