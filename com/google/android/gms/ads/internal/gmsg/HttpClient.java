package com.google.android.gms.ads.internal.gmsg;

import android.content.Context;
import android.support.annotation.Keep;
import android.text.TextUtils;
import com.appnext.base.p019a.p022c.C1028c;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzaki;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zzamy;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzue;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import java.io.BufferedOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONObject;

@Keep
@KeepName
@zzadh
public class HttpClient implements zzv<zzue> {
    private final Context mContext;
    private final zzang zzyf;

    @zzadh
    @VisibleForTesting
    static class zza {
        private final String mKey;
        private final String mValue;

        public zza(String str, String str2) {
            this.mKey = str;
            this.mValue = str2;
        }

        public final String getKey() {
            return this.mKey;
        }

        public final String getValue() {
            return this.mValue;
        }
    }

    @zzadh
    @VisibleForTesting
    static class zzb {
        private final String zzbmm;
        private final URL zzbmn;
        private final ArrayList<zza> zzbmo;
        private final String zzbmp;

        zzb(String str, URL url, ArrayList<zza> arrayList, String str2) {
            this.zzbmm = str;
            this.zzbmn = url;
            this.zzbmo = arrayList;
            this.zzbmp = str2;
        }

        public final String zzkv() {
            return this.zzbmm;
        }

        public final URL zzkw() {
            return this.zzbmn;
        }

        public final ArrayList<zza> zzkx() {
            return this.zzbmo;
        }

        public final String zzky() {
            return this.zzbmp;
        }
    }

    @zzadh
    @VisibleForTesting
    class zzc {
        private final zzd zzbmq;
        private final boolean zzbmr;
        private final String zzbms;

        public zzc(HttpClient httpClient, boolean z, zzd com_google_android_gms_ads_internal_gmsg_HttpClient_zzd, String str) {
            this.zzbmr = z;
            this.zzbmq = com_google_android_gms_ads_internal_gmsg_HttpClient_zzd;
            this.zzbms = str;
        }

        public final String getReason() {
            return this.zzbms;
        }

        public final boolean isSuccess() {
            return this.zzbmr;
        }

        public final zzd zzkz() {
            return this.zzbmq;
        }
    }

    @zzadh
    @VisibleForTesting
    static class zzd {
        private final String zzbhy;
        private final String zzbmm;
        private final int zzbmt;
        private final List<zza> zzcf;

        zzd(String str, int i, List<zza> list, String str2) {
            this.zzbmm = str;
            this.zzbmt = i;
            this.zzcf = list;
            this.zzbhy = str2;
        }

        public final String getBody() {
            return this.zzbhy;
        }

        public final int getResponseCode() {
            return this.zzbmt;
        }

        public final String zzkv() {
            return this.zzbmm;
        }

        public final Iterable<zza> zzla() {
            return this.zzcf;
        }
    }

    public HttpClient(Context context, zzang com_google_android_gms_internal_ads_zzang) {
        this.mContext = context;
        this.zzyf = com_google_android_gms_internal_ads_zzang;
    }

    private final zzc zza(zzb com_google_android_gms_ads_internal_gmsg_HttpClient_zzb) {
        Exception e;
        HttpURLConnection httpURLConnection;
        zzc com_google_android_gms_ads_internal_gmsg_HttpClient_zzc;
        Throwable th;
        HttpURLConnection httpURLConnection2 = null;
        try {
            HttpURLConnection httpURLConnection3 = (HttpURLConnection) com_google_android_gms_ads_internal_gmsg_HttpClient_zzb.zzkw().openConnection();
            try {
                byte[] bytes;
                zzbv.zzek().zza(this.mContext, this.zzyf.zzcw, false, httpURLConnection3);
                ArrayList zzkx = com_google_android_gms_ads_internal_gmsg_HttpClient_zzb.zzkx();
                int size = zzkx.size();
                int i = 0;
                while (i < size) {
                    Object obj = zzkx.get(i);
                    i++;
                    zza com_google_android_gms_ads_internal_gmsg_HttpClient_zza = (zza) obj;
                    httpURLConnection3.addRequestProperty(com_google_android_gms_ads_internal_gmsg_HttpClient_zza.getKey(), com_google_android_gms_ads_internal_gmsg_HttpClient_zza.getValue());
                }
                if (!TextUtils.isEmpty(com_google_android_gms_ads_internal_gmsg_HttpClient_zzb.zzky())) {
                    httpURLConnection3.setDoOutput(true);
                    bytes = com_google_android_gms_ads_internal_gmsg_HttpClient_zzb.zzky().getBytes();
                    httpURLConnection3.setFixedLengthStreamingMode(bytes.length);
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection3.getOutputStream());
                    bufferedOutputStream.write(bytes);
                    bufferedOutputStream.close();
                }
                zzamy com_google_android_gms_internal_ads_zzamy = new zzamy();
                com_google_android_gms_internal_ads_zzamy.zza(httpURLConnection3, bytes);
                List arrayList = new ArrayList();
                if (httpURLConnection3.getHeaderFields() != null) {
                    for (Entry entry : httpURLConnection3.getHeaderFields().entrySet()) {
                        for (String com_google_android_gms_ads_internal_gmsg_HttpClient_zza2 : (List) entry.getValue()) {
                            arrayList.add(new zza((String) entry.getKey(), com_google_android_gms_ads_internal_gmsg_HttpClient_zza2));
                        }
                    }
                }
                String zzkv = com_google_android_gms_ads_internal_gmsg_HttpClient_zzb.zzkv();
                int responseCode = httpURLConnection3.getResponseCode();
                zzbv.zzek();
                zzd com_google_android_gms_ads_internal_gmsg_HttpClient_zzd = new zzd(zzkv, responseCode, arrayList, zzakk.zza(new InputStreamReader(httpURLConnection3.getInputStream())));
                com_google_android_gms_internal_ads_zzamy.zza(httpURLConnection3, com_google_android_gms_ads_internal_gmsg_HttpClient_zzd.getResponseCode());
                com_google_android_gms_internal_ads_zzamy.zzdg(com_google_android_gms_ads_internal_gmsg_HttpClient_zzd.getBody());
                zzc com_google_android_gms_ads_internal_gmsg_HttpClient_zzc2 = new zzc(this, true, com_google_android_gms_ads_internal_gmsg_HttpClient_zzd, null);
                if (httpURLConnection3 != null) {
                    httpURLConnection3.disconnect();
                }
                return com_google_android_gms_ads_internal_gmsg_HttpClient_zzc2;
            } catch (Exception e2) {
                e = e2;
                httpURLConnection = httpURLConnection3;
                try {
                    com_google_android_gms_ads_internal_gmsg_HttpClient_zzc = new zzc(this, false, null, e.toString());
                    if (httpURLConnection != null) {
                        return com_google_android_gms_ads_internal_gmsg_HttpClient_zzc;
                    }
                    httpURLConnection.disconnect();
                    return com_google_android_gms_ads_internal_gmsg_HttpClient_zzc;
                } catch (Throwable th2) {
                    th = th2;
                    httpURLConnection2 = httpURLConnection;
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                httpURLConnection2 = httpURLConnection3;
                th = th3;
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            httpURLConnection = null;
            com_google_android_gms_ads_internal_gmsg_HttpClient_zzc = new zzc(this, false, null, e.toString());
            if (httpURLConnection != null) {
                return com_google_android_gms_ads_internal_gmsg_HttpClient_zzc;
            }
            httpURLConnection.disconnect();
            return com_google_android_gms_ads_internal_gmsg_HttpClient_zzc;
        } catch (Throwable th4) {
            th = th4;
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            throw th;
        }
    }

    private static JSONObject zza(zzd com_google_android_gms_ads_internal_gmsg_HttpClient_zzd) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("http_request_id", com_google_android_gms_ads_internal_gmsg_HttpClient_zzd.zzkv());
            if (com_google_android_gms_ads_internal_gmsg_HttpClient_zzd.getBody() != null) {
                jSONObject.put("body", com_google_android_gms_ads_internal_gmsg_HttpClient_zzd.getBody());
            }
            JSONArray jSONArray = new JSONArray();
            for (zza com_google_android_gms_ads_internal_gmsg_HttpClient_zza : com_google_android_gms_ads_internal_gmsg_HttpClient_zzd.zzla()) {
                jSONArray.put(new JSONObject().put(C1028c.gv, com_google_android_gms_ads_internal_gmsg_HttpClient_zza.getKey()).put(FirebaseAnalytics$Param.VALUE, com_google_android_gms_ads_internal_gmsg_HttpClient_zza.getValue()));
            }
            jSONObject.put("headers", jSONArray);
            jSONObject.put("response_code", com_google_android_gms_ads_internal_gmsg_HttpClient_zzd.getResponseCode());
        } catch (Throwable e) {
            zzane.zzb("Error constructing JSON for http response.", e);
        }
        return jSONObject;
    }

    private static zzb zzc(JSONObject jSONObject) {
        URL url;
        String optString = jSONObject.optString("http_request_id");
        String optString2 = jSONObject.optString("url");
        String optString3 = jSONObject.optString("post_body", null);
        try {
            url = new URL(optString2);
        } catch (Throwable e) {
            zzane.zzb("Error constructing http request.", e);
            url = null;
        }
        ArrayList arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("headers");
        if (optJSONArray == null) {
            optJSONArray = new JSONArray();
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(new zza(optJSONObject.optString(C1028c.gv), optJSONObject.optString(FirebaseAnalytics$Param.VALUE)));
            }
        }
        return new zzb(optString, url, arrayList, optString3);
    }

    @Keep
    @KeepName
    public JSONObject send(JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        String str = "";
        try {
            str = jSONObject.optString("http_request_id");
            zzc zza = zza(zzc(jSONObject));
            if (zza.isSuccess()) {
                jSONObject2.put("response", zza(zza.zzkz()));
                jSONObject2.put("success", true);
            } else {
                jSONObject2.put("response", new JSONObject().put("http_request_id", str));
                jSONObject2.put("success", false);
                jSONObject2.put("reason", zza.getReason());
            }
        } catch (Throwable e) {
            zzane.zzb("Error executing http request.", e);
            try {
                jSONObject2.put("response", new JSONObject().put("http_request_id", str));
                jSONObject2.put("success", false);
                jSONObject2.put("reason", e.toString());
            } catch (Throwable e2) {
                zzane.zzb("Error executing http request.", e2);
            }
        }
        return jSONObject2;
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        zzaki.zzb(new zzw(this, map, (zzue) obj));
    }
}
