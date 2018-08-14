package com.google.android.gms.internal.ads;

import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.net.ssl.SSLSocketFactory;

public final class zzas extends zzai {
    private final zzat zzci;
    private final SSLSocketFactory zzcj;

    public zzas() {
        this(null);
    }

    private zzas(zzat com_google_android_gms_internal_ads_zzat) {
        this(null, null);
    }

    private zzas(zzat com_google_android_gms_internal_ads_zzat, SSLSocketFactory sSLSocketFactory) {
        this.zzci = null;
        this.zzcj = null;
    }

    private static InputStream zza(HttpURLConnection httpURLConnection) {
        try {
            return httpURLConnection.getInputStream();
        } catch (IOException e) {
            return httpURLConnection.getErrorStream();
        }
    }

    private static List<zzl> zza(Map<String, List<String>> map) {
        List<zzl> arrayList = new ArrayList(map.size());
        for (Entry entry : map.entrySet()) {
            if (entry.getKey() != null) {
                for (String com_google_android_gms_internal_ads_zzl : (List) entry.getValue()) {
                    arrayList.add(new zzl((String) entry.getKey(), com_google_android_gms_internal_ads_zzl));
                }
            }
        }
        return arrayList;
    }

    private static void zza(HttpURLConnection httpURLConnection, zzr<?> com_google_android_gms_internal_ads_zzr_) throws IOException, zza {
        byte[] zzg = com_google_android_gms_internal_ads_zzr_.zzg();
        if (zzg != null) {
            httpURLConnection.setDoOutput(true);
            String str = "Content-Type";
            String str2 = "application/x-www-form-urlencoded; charset=";
            String valueOf = String.valueOf("UTF-8");
            httpURLConnection.addRequestProperty(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.write(zzg);
            dataOutputStream.close();
        }
    }

    public final zzaq zza(zzr<?> com_google_android_gms_internal_ads_zzr_, Map<String, String> map) throws IOException, zza {
        String zzg;
        String url = com_google_android_gms_internal_ads_zzr_.getUrl();
        HashMap hashMap = new HashMap();
        hashMap.putAll(com_google_android_gms_internal_ads_zzr_.getHeaders());
        hashMap.putAll(map);
        if (this.zzci != null) {
            zzg = this.zzci.zzg(url);
            if (zzg == null) {
                String str = "URL blocked by rewriter: ";
                zzg = String.valueOf(url);
                throw new IOException(zzg.length() != 0 ? str.concat(zzg) : new String(str));
            }
        }
        zzg = url;
        URL url2 = new URL(zzg);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url2.openConnection();
        httpURLConnection.setInstanceFollowRedirects(HttpURLConnection.getFollowRedirects());
        int zzi = com_google_android_gms_internal_ads_zzr_.zzi();
        httpURLConnection.setConnectTimeout(zzi);
        httpURLConnection.setReadTimeout(zzi);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setDoInput(true);
        "https".equals(url2.getProtocol());
        for (String url3 : hashMap.keySet()) {
            httpURLConnection.addRequestProperty(url3, (String) hashMap.get(url3));
        }
        switch (com_google_android_gms_internal_ads_zzr_.getMethod()) {
            case -1:
                break;
            case 0:
                httpURLConnection.setRequestMethod("GET");
                break;
            case 1:
                httpURLConnection.setRequestMethod(HttpPost.METHOD_NAME);
                zza(httpURLConnection, (zzr) com_google_android_gms_internal_ads_zzr_);
                break;
            case 2:
                httpURLConnection.setRequestMethod("PUT");
                zza(httpURLConnection, (zzr) com_google_android_gms_internal_ads_zzr_);
                break;
            case 3:
                httpURLConnection.setRequestMethod("DELETE");
                break;
            case 4:
                httpURLConnection.setRequestMethod("HEAD");
                break;
            case 5:
                httpURLConnection.setRequestMethod("OPTIONS");
                break;
            case 6:
                httpURLConnection.setRequestMethod("TRACE");
                break;
            case 7:
                httpURLConnection.setRequestMethod("PATCH");
                zza(httpURLConnection, (zzr) com_google_android_gms_internal_ads_zzr_);
                break;
            default:
                throw new IllegalStateException("Unknown method type.");
        }
        zzi = httpURLConnection.getResponseCode();
        if (zzi == -1) {
            throw new IOException("Could not retrieve response code from HttpUrlConnection.");
        }
        boolean z = (com_google_android_gms_internal_ads_zzr_.getMethod() == 4 || ((100 <= zzi && zzi < 200) || zzi == HttpStatus.SC_NO_CONTENT || zzi == HttpStatus.SC_NOT_MODIFIED)) ? false : true;
        return !z ? new zzaq(zzi, zza(httpURLConnection.getHeaderFields())) : new zzaq(zzi, zza(httpURLConnection.getHeaderFields()), httpURLConnection.getContentLength(), zza(httpURLConnection));
    }
}
