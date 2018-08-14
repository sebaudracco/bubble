package com.oneaudience.sdk;

import android.text.TextUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.oneaudience.sdk.p134b.C3817b;
import com.oneaudience.sdk.p135c.C3829c;
import com.oneaudience.sdk.p135c.C3833d;
import com.oneaudience.sdk.p135c.C3836g;
import com.oneaudience.sdk.p135c.p136a.C3821a;
import com.oneaudience.sdk.p135c.p136a.C3822b;
import com.oneaudience.sdk.p135c.p137b.C3825b;
import com.oneaudience.sdk.p135c.p137b.C3826c;
import cz.msebera.android.httpclient.HttpStatus;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class C3820b {
    private static final String f9179a = C3820b.class.getSimpleName();
    private static final C3817b f9180b = C3817b.m12208a();

    static class C38101 implements X509TrustManager {
        C38101() {
        }

        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }

    static {
        System.setProperty("http.keepAlive", SchemaSymbols.ATTVAL_FALSE);
    }

    private static int m12213a(int i) {
        switch (i) {
            case 200:
            case HttpStatus.SC_ACCEPTED /*202*/:
                return 100000;
            case HttpStatus.SC_NOT_MODIFIED /*304*/:
                return 100001;
            case HttpStatus.SC_REQUEST_TIMEOUT /*408*/:
            case HttpStatus.SC_INTERNAL_SERVER_ERROR /*500*/:
            case HttpStatus.SC_SERVICE_UNAVAILABLE /*503*/:
                return 100004;
            default:
                return 100003;
        }
    }

    private static C3822b m12214a(int i, Map<String, String> map, String str, String str2) {
        int a = C3820b.m12213a(i);
        int i2 = (TextUtils.isEmpty(str) || !str.toLowerCase().contains(RequestParams.APPLICATION_JSON)) ? 0 : 1;
        if (!C3826c.m12234b(str2)) {
            return new C3822b(a, map, null);
        }
        Object a2;
        C3833d.m12251b(f9179a, "GETTING <---- %s", str2);
        if (i2 != 0) {
            a2 = C3829c.m12241a(str2);
        }
        return new C3822b(a, map, a2);
    }

    private static OutputStream m12215a(HttpURLConnection httpURLConnection) {
        return new BufferedOutputStream(httpURLConnection.getOutputStream());
    }

    private static String m12216a(Reader reader) {
        C3833d.m12249b(f9179a, "reading...");
        StringBuilder stringBuilder = new StringBuilder();
        char[] cArr = new char[8192];
        while (true) {
            int read = reader.read(cArr, 0, cArr.length);
            if (read > 0) {
                stringBuilder.append(cArr, 0, read);
            } else {
                reader.close();
                C3833d.m12249b(f9179a, "reading... DONE");
                return stringBuilder.toString();
            }
        }
    }

    private static Map<String, String> m12217a(Map<String, List<String>> map) {
        Map<String, String> hashMap = new HashMap();
        if (C3825b.m12232b((Map) map)) {
            for (Entry entry : map.entrySet()) {
                if (!TextUtils.isEmpty((CharSequence) entry.getKey())) {
                    hashMap.put(((String) entry.getKey()).toLowerCase(), TextUtils.join(",", (Iterable) entry.getValue()));
                }
            }
        }
        return hashMap;
    }

    private static void m12218a() {
        TrustManager[] trustManagerArr = new TrustManager[]{new C38101()};
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(null, trustManagerArr, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(instance.getSocketFactory());
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
        }
    }

    private static void m12219a(OutputStream outputStream, String str) {
        C3833d.m12249b(f9179a, "writing...");
        outputStream.write(str.getBytes());
        outputStream.flush();
        outputStream.close();
        C3833d.m12249b(f9179a, "writing... DONE");
    }

    private static void m12220a(HttpURLConnection httpURLConnection, Map<String, String> map) {
        for (Entry entry : map.entrySet()) {
            httpURLConnection.addRequestProperty((String) entry.getKey(), (String) entry.getValue());
        }
    }

    private static Reader m12221b(HttpURLConnection httpURLConnection) {
        Object contentEncoding = httpURLConnection.getContentEncoding();
        contentEncoding = (TextUtils.isEmpty(contentEncoding) || !contentEncoding.toLowerCase().contains(AsyncHttpClient.ENCODING_GZIP)) ? null : 1;
        InputStream inputStream = httpURLConnection.getInputStream();
        return new BufferedReader(new InputStreamReader(new BufferedInputStream(contentEncoding != null ? new GZIPInputStream(inputStream) : inputStream)));
    }

    private static HttpURLConnection m12222b(C3821a c3821a) {
        String str = c3821a.f9182b;
        C3820b.m12218a();
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        if (C3825b.m12232b(c3821a.f9183c)) {
            C3820b.m12220a(httpURLConnection, c3821a.f9183c);
        }
        httpURLConnection.addRequestProperty("accept-encoding", AsyncHttpClient.ENCODING_GZIP);
        httpURLConnection.setConnectTimeout(7000);
        httpURLConnection.setReadTimeout(60000);
        if (c3821a.m12226a()) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.addRequestProperty("content-type", RequestParams.APPLICATION_JSON);
            httpURLConnection.setChunkedStreamingMode(0);
        }
        httpURLConnection.connect();
        return httpURLConnection;
    }

    private static String m12223c(C3821a c3821a) {
        String jSONObject = c3821a.f9184d instanceof Map ? new JSONObject((Map) c3821a.f9184d).toString() : new JSONArray((Collection) c3821a.f9184d).toString();
        C3833d.m12251b(f9179a, "POSTING ----> %s", jSONObject);
        return c3821a.f9181a ? f9180b.m12209a(jSONObject) : jSONObject;
    }

    private static void m12224c(HttpURLConnection httpURLConnection) {
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }

    public C3822b m12225a(C3821a c3821a) {
        HttpURLConnection b;
        Throwable e;
        C3822b c3822b;
        Closeable closeable = null;
        if (C3826c.m12233a(c3821a.f9182b)) {
            C3833d.m12257e(f9179a, "invalid request %s", c3821a);
            return new C3822b(100003, null, null);
        }
        Closeable a;
        try {
            C3833d.m12248a(f9179a, "Request ----> %s", c3821a);
            b = C3820b.m12222b(c3821a);
            try {
                String c;
                if (c3821a.m12226a()) {
                    c = C3820b.m12223c(c3821a);
                    a = C3820b.m12215a(b);
                    try {
                        C3833d.m12251b(f9179a, "Sending ----> %s", c);
                        C3820b.m12219a((OutputStream) a, c);
                        C3833d.m12249b(f9179a, "Sending ----> DONE");
                    } catch (SocketTimeoutException e2) {
                        e = e2;
                        try {
                            C3833d.m12247a(f9179a, "", e);
                            c3822b = new C3822b(100004, null, null);
                            C3820b.m12224c(b);
                            C3836g.m12266a(a);
                            C3836g.m12266a(null);
                            return c3822b;
                        } catch (Throwable th) {
                            e = th;
                            C3820b.m12224c(b);
                            C3836g.m12266a(a);
                            C3836g.m12266a(null);
                            throw e;
                        }
                    } catch (IOException e3) {
                        e = e3;
                        C3833d.m12247a(f9179a, "", e);
                        c3822b = new C3822b(100002, null, null);
                        C3820b.m12224c(b);
                        C3836g.m12266a(a);
                        C3836g.m12266a(null);
                        return c3822b;
                    } catch (JSONException e4) {
                        e = e4;
                        C3833d.m12247a(f9179a, "", e);
                        c3822b = new C3822b(100003, null, null);
                        C3820b.m12224c(b);
                        C3836g.m12266a(a);
                        C3836g.m12266a(null);
                        return c3822b;
                    } catch (Throwable th2) {
                        e = th2;
                        C3833d.m12247a(f9179a, "", e);
                        c3822b = new C3822b(100003, null, null);
                        C3820b.m12224c(b);
                        C3836g.m12266a(a);
                        C3836g.m12266a(null);
                        return c3822b;
                    }
                }
                a = null;
                int responseCode = b.getResponseCode();
                if (responseCode == 200) {
                    closeable = C3820b.m12221b(b);
                    c = C3820b.m12216a((Reader) closeable);
                    C3833d.m12251b(f9179a, "Receiving <---- %s", c);
                } else {
                    C3833d.m12255d(f9179a, "error status code: %d", Integer.valueOf(responseCode));
                    c = null;
                }
                C3833d.m12248a(f9179a, "Receiving <---- %s", C3820b.m12214a(responseCode, C3820b.m12217a(b.getHeaderFields()), b.getContentType(), c));
                C3820b.m12224c(b);
                C3836g.m12266a(a);
                C3836g.m12266a(closeable);
                return c3822b;
            } catch (SocketTimeoutException e5) {
                e = e5;
                a = null;
                C3833d.m12247a(f9179a, "", e);
                c3822b = new C3822b(100004, null, null);
                C3820b.m12224c(b);
                C3836g.m12266a(a);
                C3836g.m12266a(null);
                return c3822b;
            } catch (IOException e6) {
                e = e6;
                a = null;
                C3833d.m12247a(f9179a, "", e);
                c3822b = new C3822b(100002, null, null);
                C3820b.m12224c(b);
                C3836g.m12266a(a);
                C3836g.m12266a(null);
                return c3822b;
            } catch (JSONException e7) {
                e = e7;
                a = null;
                C3833d.m12247a(f9179a, "", e);
                c3822b = new C3822b(100003, null, null);
                C3820b.m12224c(b);
                C3836g.m12266a(a);
                C3836g.m12266a(null);
                return c3822b;
            } catch (Throwable th3) {
                e = th3;
                a = null;
                C3820b.m12224c(b);
                C3836g.m12266a(a);
                C3836g.m12266a(null);
                throw e;
            }
        } catch (SocketTimeoutException e8) {
            e = e8;
            a = null;
            b = null;
            C3833d.m12247a(f9179a, "", e);
            c3822b = new C3822b(100004, null, null);
            C3820b.m12224c(b);
            C3836g.m12266a(a);
            C3836g.m12266a(null);
            return c3822b;
        } catch (IOException e9) {
            e = e9;
            a = null;
            b = null;
            C3833d.m12247a(f9179a, "", e);
            c3822b = new C3822b(100002, null, null);
            C3820b.m12224c(b);
            C3836g.m12266a(a);
            C3836g.m12266a(null);
            return c3822b;
        } catch (JSONException e10) {
            e = e10;
            a = null;
            b = null;
            C3833d.m12247a(f9179a, "", e);
            c3822b = new C3822b(100003, null, null);
            C3820b.m12224c(b);
            C3836g.m12266a(a);
            C3836g.m12266a(null);
            return c3822b;
        } catch (Throwable th4) {
            e = th4;
            a = null;
            b = null;
            C3820b.m12224c(b);
            C3836g.m12266a(a);
            C3836g.m12266a(null);
            throw e;
        }
    }
}
