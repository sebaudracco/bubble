package com.core42matters.android.registrar;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.DisplayMetrics;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.google.android.gms.common.internal.Constants;
import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpRetryException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.zip.DeflaterOutputStream;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

final class HttpUtils {
    private static final String CHARSET = "UTF-8";
    private static final int CONNECT_TIMEOUT = 30000;
    private static final String HEADER_APP_ID = "X-42-App-Id";
    private static final String HEADER_HASH = "X-42-Hash";
    private static final String HEADER_LIB_VERSION = "X-42-Lib-Version";
    private static final String HEADER_SIGNATURE = "X-42-Signature";
    private static final String HEADER_TIMESTAMP = "X-42-Timestamp";
    private static final int READ_TIMEOUT = 30000;
    private static final String USER_AGENT = (System.getProperties().getProperty("http.agent") + " Registrar/" + BuildConfig.VERSION_NAME);

    private HttpUtils() {
        throw new AssertionError();
    }

    private static String encodeUrl(Bundle parameters) throws UnsupportedEncodingException {
        if (parameters == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String key : parameters.keySet()) {
            Object parameter = parameters.get(key);
            if (!(parameter == null || (parameter instanceof byte[]))) {
                sb.append("&").append(URLEncoder.encode(key, "UTF-8") + "=" + URLEncoder.encode(parameter.toString(), "UTF-8"));
            }
        }
        if (sb.length() == 0) {
            return "";
        }
        return sb.toString().substring(1);
    }

    static String requestString(Context context, AppId appId, String url, String method, Bundle params) throws IOException, InvalidApiCallException {
        HttpResponse response = request(context, appId, url, method, params);
        int responseCode = response.getStatusLine().getStatusCode();
        Logger.m3320i("response code: " + responseCode + ", " + url);
        String responseString = streamToUTF8String(response.getEntity().getContent());
        if (responseCode / 100 == 2) {
            Logger.m3320i("response: " + responseString);
            return responseString;
        } else if (responseCode / 100 == 4) {
            throw new InvalidApiCallException(responseString);
        } else if (responseCode == HttpStatus.SC_NOT_IMPLEMENTED) {
            throw new HttpRetryException(responseString, responseCode);
        } else {
            Logger.m3322w("error: " + responseString);
            return null;
        }
    }

    static HttpResponse request(Context context, AppId appId, String url, String method, Bundle params) throws IOException, IllegalArgumentException {
        HttpUriRequest request;
        HttpClient httpclient = new DefaultHttpClient();
        HttpParams httpParameters = httpclient.getParams();
        HttpConnectionParams.setConnectionTimeout(httpParameters, 30000);
        HttpConnectionParams.setSoTimeout(httpParameters, 30000);
        if (method.equals("GET")) {
            if (params != null) {
                url = url + "?" + encodeUrl(params);
            }
            request = new HttpGet(url);
            try {
                hashHeaders(appId, request, url);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if (method.equals("DELETE")) {
                if (params != null) {
                    url = url + "?" + encodeUrl(params);
                }
                request = new HttpDelete(url);
                try {
                    hashHeaders(appId, request, url);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } else {
                List<NameValuePair> nameValuePairs;
                String raw;
                Object parameter;
                if (method.equals("PUT")) {
                    HttpPut httpPut = new HttpPut(url);
                    nameValuePairs = new ArrayList(2);
                    raw = url;
                    if (params != null) {
                        for (String key : params.keySet()) {
                            parameter = params.get(key);
                            if (!(parameter == null || (parameter instanceof byte[]))) {
                                nameValuePairs.add(new BasicNameValuePair(key, parameter.toString()));
                                raw = raw + "\n" + key + "=" + parameter.toString();
                            }
                        }
                    }
                    httpPut.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    request = httpPut;
                    try {
                        hashHeaders(appId, request, raw);
                    } catch (Exception e22) {
                        e22.printStackTrace();
                    }
                } else {
                    if (method.equals(HttpPost.METHOD_NAME)) {
                        org.apache.http.client.methods.HttpPost httpPost = new org.apache.http.client.methods.HttpPost(url);
                        nameValuePairs = new ArrayList(2);
                        raw = url;
                        if (params != null) {
                            for (String key2 : params.keySet()) {
                                parameter = params.get(key2);
                                if (!(parameter == null || (parameter instanceof byte[]))) {
                                    nameValuePairs.add(new BasicNameValuePair(key2, parameter.toString()));
                                    raw = raw + "\n" + key2 + "=" + parameter.toString();
                                }
                            }
                        }
                        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                        request = httpPost;
                        try {
                            hashHeaders(appId, request, raw);
                        } catch (Exception e222) {
                            e222.printStackTrace();
                        }
                    } else {
                        throw new IllegalArgumentException("Unsupported method: " + method);
                    }
                }
            }
        }
        putDefaultHeaders(context, request, appId);
        return httpclient.execute(request);
    }

    static String sendPayload(Context context, AppId appId, String url, String payload) throws IOException {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            putDefaultHeaders(context, conn, appId);
            try {
                hashHeaders(appId, conn, payload);
            } catch (Exception e) {
            }
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            conn.setRequestProperty("Content-Encoding", "deflate");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            DeflaterOutputStream dos = new DeflaterOutputStream(conn.getOutputStream());
            dos.write(payload.getBytes("UTF-8"));
            dos.close();
            int responseCode = conn.getResponseCode();
            Logger.m3320i("response code: " + responseCode + ", " + url);
            if (responseCode / 100 == 2) {
                String resp = streamToUTF8String(conn.getInputStream());
                Logger.m3320i(resp);
                return resp;
            }
            Logger.m3322w(streamToUTF8String(conn.getErrorStream()));
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static void putDefaultHeaders(Context context, HttpUriRequest req, AppId appId) {
        HashMap<String, String> headers = getDefaultHeaders(context, appId);
        for (String k : headers.keySet()) {
            req.setHeader(k, (String) headers.get(k));
        }
    }

    private static void putDefaultHeaders(Context context, HttpURLConnection conn, AppId appId) {
        HashMap<String, String> headers = getDefaultHeaders(context, appId);
        for (String k : headers.keySet()) {
            conn.setRequestProperty(k, (String) headers.get(k));
        }
    }

    private static HashMap<String, String> getDefaultHeaders(Context context, AppId appId) {
        HashMap<String, String> headers = new HashMap();
        headers.put(HEADER_APP_ID, appId.getId());
        headers.put(HEADER_LIB_VERSION, String.valueOf(201));
        headers.put("User-Agent", USER_AGENT);
        return headers;
    }

    private static void hashHeaders(AppId appId, HttpUriRequest req, String raw) throws SignatureException {
        String timestamp = String.valueOf(System.currentTimeMillis());
        raw = raw + "\n" + timestamp;
        req.setHeader(HEADER_TIMESTAMP, timestamp);
        req.setHeader(HEADER_SIGNATURE, appId.sign(raw));
        req.setHeader(HEADER_HASH, HashUtils.md5(raw));
    }

    private static void hashHeaders(AppId appId, HttpURLConnection conn, String raw) throws SignatureException {
        String timestamp = String.valueOf(System.currentTimeMillis());
        raw = raw + "\n" + timestamp;
        conn.setRequestProperty(HEADER_TIMESTAMP, timestamp);
        conn.setRequestProperty(HEADER_SIGNATURE, appId.sign(raw));
        conn.setRequestProperty(HEADER_HASH, HashUtils.md5(raw));
    }

    static Bundle createBasicRequestParams(Context context, AppId appId, String udid, boolean withDeviceInfo) {
        Bundle params = new Bundle();
        params.putString("udid", udid);
        params.putString(C1404b.f2147y, appId.getId());
        Locale l = Locale.getDefault();
        params.putString(SchemaSymbols.ATTVAL_LANGUAGE, l.getLanguage());
        params.putString("country", l.getCountry());
        if (withDeviceInfo) {
            DisplayMetrics metrics = context.getResources().getDisplayMetrics();
            params.putFloat(Constants.PARAM_DENSITY, metrics.density);
            params.putInt("widthPixels", metrics.widthPixels);
            params.putInt("heightPixels", metrics.heightPixels);
            params.putString("device", Build.DEVICE);
            params.putString("model", Build.MODEL);
            params.putInt("sdk", VERSION.SDK_INT);
        }
        NetworkInfo info = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            params.putInt("networktype", info.getType());
            params.putInt("networksubtype", info.getSubtype());
        }
        return params;
    }

    static String streamToUTF8String(InputStream is) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(is, "UTF-8"), 4096);
        StringBuilder sb = new StringBuilder();
        for (String line = r.readLine(); line != null; line = r.readLine()) {
            sb.append(line).append('\n');
        }
        r.close();
        return sb.toString();
    }

    static byte[] streamToBytes(InputStream is) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] data = new byte[4096];
        while (true) {
            int nRead = is.read(data, 0, data.length);
            if (nRead != -1) {
                buffer.write(data, 0, nRead);
            } else {
                buffer.flush();
                return buffer.toByteArray();
            }
        }
    }
}
