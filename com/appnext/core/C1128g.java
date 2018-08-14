package com.appnext.core;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Process;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Pair;
import android.webkit.WebView;
import com.appnext.base.p023b.C1042c;
import com.appnext.base.p023b.C1042c.C1041a;
import com.appnext.base.p023b.C1043d;
import com.appnext.base.p023b.C1047h;
import com.appnext.base.p023b.C1048i;
import com.appnext.base.p023b.C1057k;
import com.appnext.base.p023b.C1058l;
import com.bgjd.ici.p030e.C1485h.C1484a;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.google.android.gms.wearable.WearableStatusCodes;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.stripe.android.model.Card;
import cz.msebera.android.httpclient.HttpHeaders;
import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.PushbackInputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpRetryException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.GZIPInputStream;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import net.hockeyapp.android.LoginActivity;
import org.json.JSONArray;
import org.json.JSONObject;

public class C1128g {
    private static final boolean DEBUG = false;
    public static final String VID = "2.2.5.468";
    static final int ju = 8000;
    private static final String lH = "encryptKeys";
    private static final String lI = "expiredTimems";
    private static double lJ = -1.0d;
    private static HashMap<String, Bitmap> lK = new HashMap();
    private static String lL = "";
    private static String lM = "";
    private static String lN = "";
    public static final Executor lO = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() + 1, (Runtime.getRuntime().availableProcessors() * 2) + 1, 1, TimeUnit.SECONDS, new LinkedBlockingQueue(128), new C11251());

    static class C11251 implements ThreadFactory {
        private final AtomicInteger lP = new AtomicInteger(1);

        C11251() {
        }

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "AsyncTask #" + this.lP.getAndIncrement());
        }
    }

    static {
        CookieHandler.setDefault(new CookieManager());
        C1128g.cY();
    }

    public static synchronized String m2358u(Context context) {
        String c;
        synchronized (C1128g.class) {
            c = C1128g.m2350c(context, true);
        }
        return c;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized java.lang.String m2350c(android.content.Context r3, boolean r4) {
        /*
        r1 = com.appnext.core.C1128g.class;
        monitor-enter(r1);
        if (r3 == 0) goto L_0x000b;
    L_0x0005:
        r0 = r3.getApplicationContext();	 Catch:{ all -> 0x007a }
        if (r0 != 0) goto L_0x0010;
    L_0x000b:
        r0 = "";
    L_0x000e:
        monitor-exit(r1);
        return r0;
    L_0x0010:
        r0 = "com.google.android.gms.ads.identifier.AdvertisingIdClient";
        java.lang.Class.forName(r0);	 Catch:{ ClassNotFoundException -> 0x0057 }
        r0 = r3.getApplicationContext();	 Catch:{ ClassNotFoundException -> 0x0057 }
        r0 = com.appnext.core.AdsIDHelper.m2286a(r0, r4);	 Catch:{ ClassNotFoundException -> 0x0057 }
    L_0x001e:
        if (r0 == 0) goto L_0x0029;
    L_0x0020:
        r2 = "";
        r2 = r0.equals(r2);	 Catch:{ Throwable -> 0x0067 }
        if (r2 == 0) goto L_0x0064;
    L_0x0029:
        r0 = r3.getApplicationContext();	 Catch:{ Throwable -> 0x0067 }
        r0 = com.appnext.core.C1124f.m2327b(r0, r4);	 Catch:{ Throwable -> 0x0067 }
        r2 = "";
        r2 = r0.equals(r2);	 Catch:{ Throwable -> 0x0067 }
        if (r2 == 0) goto L_0x0064;
    L_0x003a:
        if (r4 == 0) goto L_0x004a;
    L_0x003c:
        r0 = r3.getApplicationContext();	 Catch:{ Throwable -> 0x0060 }
        r0 = com.appnext.core.C1115d.m2307r(r0);	 Catch:{ Throwable -> 0x0060 }
        r0 = r0.cR();	 Catch:{ Throwable -> 0x0060 }
        if (r0 != 0) goto L_0x005c;
    L_0x004a:
        r0 = r3.getApplicationContext();	 Catch:{ Throwable -> 0x0060 }
        r0 = com.appnext.core.C1115d.m2307r(r0);	 Catch:{ Throwable -> 0x0060 }
        r0 = r0.getId();	 Catch:{ Throwable -> 0x0060 }
        goto L_0x000e;
    L_0x0057:
        r0 = move-exception;
        r0 = "";
        goto L_0x001e;
    L_0x005c:
        r0 = "";
        goto L_0x000e;
    L_0x0060:
        r0 = move-exception;
        r0 = "";
    L_0x0064:
        lM = r0;	 Catch:{ Throwable -> 0x0067 }
        goto L_0x000e;
    L_0x0067:
        r0 = move-exception;
        r0 = lM;	 Catch:{ all -> 0x007a }
        r2 = "";
        r0 = r0.equals(r2);	 Catch:{ all -> 0x007a }
        if (r0 != 0) goto L_0x0076;
    L_0x0073:
        r0 = lM;	 Catch:{ all -> 0x007a }
        goto L_0x000e;
    L_0x0076:
        r0 = "";
        goto L_0x000e;
    L_0x007a:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.core.g.c(android.content.Context, boolean):java.lang.String");
    }

    static void m2359v(Context context) {
        try {
            WebView webView = new WebView(context);
            C1128g.aL(webView.getSettings().getUserAgentString());
            webView.destroy();
        } catch (Throwable th) {
        }
    }

    static String cU() {
        return lL;
    }

    private static void aL(String str) {
        lL = str;
    }

    public static String cV() {
        try {
            return URLEncoder.encode("android " + VERSION.SDK_INT + " " + Build.MANUFACTURER + " " + Build.MODEL, "UTF-8");
        } catch (Throwable th) {
            C1128g.m2351c(th);
            return "android";
        }
    }

    public static int cW() {
        Runtime runtime = Runtime.getRuntime();
        return (int) ((runtime.maxMemory() / 1048576) - ((runtime.totalMemory() - runtime.freeMemory()) / 1048576));
    }

    public static String m2336a(String str, HashMap<String, String> hashMap) throws IOException {
        return C1128g.m2339a(str, (HashMap) hashMap, true, 8000);
    }

    public static String m2338a(String str, HashMap<String, String> hashMap, boolean z) throws IOException {
        return C1128g.m2339a(str, (HashMap) hashMap, z, 8000);
    }

    public static String m2337a(String str, HashMap<String, String> hashMap, int i) throws IOException {
        return C1128g.m2339a(str, (HashMap) hashMap, true, i);
    }

    public static String m2339a(String str, HashMap<String, String> hashMap, boolean z, int i) throws IOException {
        return new String(C1128g.m2349b(str, hashMap, z, i, C1041a.HashMap), "UTF-8");
    }

    public static String m2353e(String str, ArrayList<Pair<String, String>> arrayList) throws IOException {
        return C1128g.m2335a(str, (ArrayList) arrayList, 8000);
    }

    public static String m2335a(String str, ArrayList<Pair<String, String>> arrayList, int i) throws IOException {
        return new String(C1128g.m2349b(str, arrayList, true, i, C1041a.ArrayList), "UTF-8");
    }

    public static String m2345b(String str, JSONObject jSONObject) throws IOException {
        return new String(C1128g.m2349b(str, jSONObject, true, 8000, C1041a.JSONObject), "UTF-8");
    }

    public static byte[] m2342a(String str, Object obj, boolean z, int i) throws IOException {
        return C1128g.m2349b(str, obj, z, i, C1041a.HashMap);
    }

    private static boolean aM(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("aid")) {
                return false;
            }
            Object string = jSONObject.getString("aid");
            if (TextUtils.isEmpty(string) || !string.equals(C1128g.m2358u(C1043d.getContext()))) {
                return false;
            }
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    private static boolean aN(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has(lI)) {
                return true;
            }
            if (System.currentTimeMillis() < jSONObject.getLong(lI)) {
                return false;
            }
            return true;
        } catch (Throwable th) {
            return true;
        }
    }

    public static byte[] m2343a(String str, Object obj, boolean z, int i, C1041a c1041a) throws Exception {
        byte[] b;
        JSONObject jSONObject;
        HttpURLConnection httpURLConnection;
        InputStream inputStream = null;
        String str2 = "";
        str2 = "";
        str2 = C1048i.cy().getString(lH, inputStream);
        if (TextUtils.isEmpty(str2) || !C1128g.aM(str2) || C1128g.aN(str2)) {
            C1058l.m2184k("encrypted", "go to server for secret");
            HashMap hashMap = new HashMap();
            hashMap.put("aid", C1047h.cx().aD(C1128g.m2358u(C1043d.getContext())));
            try {
                b = C1128g.m2349b(C1042c.cq() + "/api/token", hashMap, false, 15000, C1041a.HashMap);
                if (b == null) {
                    return inputStream;
                }
                str2 = new String(b, "UTF-8");
                C1058l.m2184k("encrypted", str2);
                jSONObject = new JSONObject(str2);
                jSONObject.put("aid", C1128g.m2358u(C1043d.getContext()));
                jSONObject.put(lI, (System.currentTimeMillis() + (jSONObject.getLong(C1484a.f2400g) * 1000)) - 10000);
                C1048i.cy().putString(lH, jSONObject.toString());
            } catch (Throwable th) {
                Throwable th2;
                Exception exception = new Exception(th2.getMessage());
            }
        }
        jSONObject = new JSONObject(str2);
        if (!jSONObject.has(LoginActivity.EXTRA_SECRET) || !jSONObject.has("rnd")) {
            return inputStream;
        }
        String string = jSONObject.getString("rnd");
        String string2 = jSONObject.getString(LoginActivity.EXTRA_SECRET);
        URL url = new URL(str);
        C1128g.m2333W("encryptedHttpRequest " + str);
        Object u;
        try {
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) url.openConnection();
            try {
                httpURLConnection2.setReadTimeout(i);
                httpURLConnection2.setConnectTimeout(i);
                httpURLConnection2.setRequestProperty("Accept-Encoding", AsyncHttpClient.ENCODING_GZIP);
                httpURLConnection2.setRequestProperty("User-Agent", C1128g.cU());
                httpURLConnection2.setRequestProperty("rnd", string);
                u = C1128g.m2358u(C1043d.getContext());
                if (TextUtils.isEmpty(u)) {
                    u = C1057k.m2163a("aidForSend", C1041a.String);
                    if (u == null || !(u instanceof String)) {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        return inputStream;
                    }
                    u = (String) u;
                } else {
                    C1057k.m2176e("aidForSend", u, C1041a.String);
                }
                if (TextUtils.isEmpty(u)) {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    return inputStream;
                }
                httpURLConnection2.setRequestProperty("aid", C1047h.cx().aD(u));
                if (obj != null) {
                    httpURLConnection2.setDoOutput(true);
                    httpURLConnection2.setRequestMethod(HttpPost.METHOD_NAME);
                    if (c1041a == C1041a.JSONObject || c1041a == C1041a.JSONArray) {
                        httpURLConnection2.setRequestProperty("Content-Type", RequestParams.APPLICATION_JSON);
                        C1128g.m2333W("report params " + obj.toString());
                    }
                    String str3 = "";
                    if (c1041a == C1041a.JSONArray) {
                        str3 = ((JSONArray) obj).toString();
                    } else if (c1041a == C1041a.JSONObject) {
                        str3 = ((JSONObject) obj).toString();
                    } else if (c1041a == C1041a.HashMap) {
                        HashMap hashMap2 = (HashMap) obj;
                        List arrayList = new ArrayList();
                        for (Entry entry : hashMap2.entrySet()) {
                            arrayList.add(new Pair(entry.getKey(), entry.getValue()));
                        }
                        str3 = C1128g.m2347b(arrayList, z);
                    } else if (c1041a == C1041a.ArrayList) {
                        str3 = C1128g.m2347b((List) (ArrayList) obj, z);
                    }
                    b = C1047h.cx().m2145h(str3, string2);
                    OutputStream outputStream = httpURLConnection2.getOutputStream();
                    if (b != null) {
                        outputStream.write(b);
                    }
                    outputStream.close();
                }
                int responseCode = httpURLConnection2.getResponseCode();
                if (responseCode == 200) {
                    inputStream = httpURLConnection2.getInputStream();
                    b = C1128g.m2352c(C1128g.m2344b(inputStream));
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    return b;
                } else if (responseCode == HttpStatus.SC_MOVED_PERMANENTLY || responseCode == HttpStatus.SC_MOVED_TEMPORARILY || responseCode == HttpStatus.SC_SEE_OTHER) {
                    b = C1128g.m2349b(httpURLConnection2.getHeaderField(HttpHeaders.LOCATION), obj, z, i, c1041a);
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    return b;
                } else {
                    throw new HttpRetryException(new String(C1128g.m2352c(httpURLConnection2.getErrorStream()), "UTF-8"), responseCode);
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                httpURLConnection = httpURLConnection2;
                th2 = th4;
                if (inputStream != null) {
                    inputStream.close();
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th2;
            }
        } catch (Throwable th5) {
            th2 = th5;
            u = inputStream;
            if (inputStream != null) {
                inputStream.close();
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th2;
        }
    }

    public static byte[] m2349b(String str, Object obj, boolean z, int i, C1041a c1041a) throws IOException {
        Throwable th;
        InputStream inputStream;
        Throwable th2;
        HttpURLConnection httpURLConnection = null;
        URL url = new URL(str);
        C1128g.m2333W("performURLCall " + str);
        try {
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) url.openConnection();
            try {
                httpURLConnection2.setReadTimeout(i);
                httpURLConnection2.setConnectTimeout(i);
                httpURLConnection2.setRequestProperty("Accept-Encoding", AsyncHttpClient.ENCODING_GZIP);
                httpURLConnection2.setRequestProperty("User-Agent", C1128g.cU());
                if (obj != null) {
                    httpURLConnection2.setDoOutput(true);
                    httpURLConnection2.setRequestMethod(HttpPost.METHOD_NAME);
                    if (c1041a == C1041a.JSONObject || c1041a == C1041a.JSONArray) {
                        httpURLConnection2.setRequestProperty("Content-Type", RequestParams.APPLICATION_JSON);
                        C1128g.m2333W("report params " + obj.toString());
                    }
                    OutputStream outputStream = httpURLConnection2.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    if (c1041a == C1041a.JSONArray) {
                        bufferedWriter.write(((JSONArray) obj).toString());
                    } else if (c1041a == C1041a.JSONObject) {
                        bufferedWriter.write(((JSONObject) obj).toString());
                    } else if (c1041a == C1041a.HashMap) {
                        HashMap hashMap = (HashMap) obj;
                        List arrayList = new ArrayList();
                        for (Entry entry : hashMap.entrySet()) {
                            arrayList.add(new Pair(entry.getKey(), entry.getValue()));
                        }
                        bufferedWriter.write(C1128g.m2347b(arrayList, z));
                    } else if (c1041a == C1041a.ArrayList) {
                        bufferedWriter.write(C1128g.m2347b((List) (ArrayList) obj, z));
                    }
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                }
                int responseCode = httpURLConnection2.getResponseCode();
                byte[] c;
                if (responseCode == 200) {
                    InputStream inputStream2 = httpURLConnection2.getInputStream();
                    try {
                        c = C1128g.m2352c(C1128g.m2344b(inputStream2));
                        if (inputStream2 != null) {
                            inputStream2.close();
                        }
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        return c;
                    } catch (Throwable th3) {
                        th = th3;
                        inputStream = inputStream2;
                        httpURLConnection = httpURLConnection2;
                        th2 = th;
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        throw th2;
                    }
                } else if (responseCode == HttpStatus.SC_MOVED_PERMANENTLY || responseCode == HttpStatus.SC_MOVED_TEMPORARILY || responseCode == HttpStatus.SC_SEE_OTHER) {
                    c = C1128g.m2349b(httpURLConnection2.getHeaderField(HttpHeaders.LOCATION), obj, z, i, c1041a);
                    if (httpURLConnection != null) {
                        httpURLConnection.close();
                    }
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    return c;
                } else {
                    throw new HttpRetryException(new String(C1128g.m2352c(httpURLConnection2.getErrorStream()), "UTF-8"), responseCode);
                }
            } catch (Throwable th32) {
                th = th32;
                inputStream = httpURLConnection;
                httpURLConnection = httpURLConnection2;
                th2 = th;
                if (inputStream != null) {
                    inputStream.close();
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th2;
            }
        } catch (Throwable th4) {
            th2 = th4;
            Object obj2 = httpURLConnection;
            if (inputStream != null) {
                inputStream.close();
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th2;
        }
    }

    public static InputStream m2344b(InputStream inputStream) throws IOException {
        InputStream pushbackInputStream = new PushbackInputStream(inputStream, 2);
        byte[] bArr = new byte[2];
        try {
            pushbackInputStream.unread(bArr, 0, pushbackInputStream.read(bArr));
            if (bArr[0] == (byte) 31 && bArr[1] == (byte) -117) {
                return new GZIPInputStream(pushbackInputStream);
            }
            return pushbackInputStream;
        } catch (Throwable th) {
            return inputStream;
        }
    }

    private static byte[] m2352c(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                inputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    public static Bitmap aO(String str) {
        Throwable th;
        HttpURLConnection httpURLConnection = null;
        HttpURLConnection httpURLConnection2;
        try {
            if (lK.get(str) != null) {
                Bitmap bitmap = (Bitmap) lK.get(str);
                if (httpURLConnection == null) {
                    return bitmap;
                }
                httpURLConnection.disconnect();
                return bitmap;
            }
            URL url = new URL(str);
            C1128g.m2333W("performURLCall " + str);
            httpURLConnection2 = (HttpURLConnection) url.openConnection();
            try {
                httpURLConnection2.setDoInput(true);
                httpURLConnection2.connect();
                InputStream inputStream = httpURLConnection2.getInputStream();
                Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
                if (decodeStream == null) {
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    return httpURLConnection;
                }
                if (!lK.containsKey(str)) {
                    lK.put(str, decodeStream);
                }
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                return decodeStream;
            } catch (Throwable th2) {
                Throwable th3 = th2;
                httpURLConnection = httpURLConnection2;
                th = th3;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }

    private static String m2347b(List<Pair<String, String>> list, boolean z) {
        Object obj;
        StringBuilder stringBuilder = new StringBuilder();
        Object obj2 = 1;
        for (Pair pair : list) {
            Object obj3;
            try {
                if (!(pair.first == null || pair.second == null)) {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    if (obj2 != null) {
                        obj = null;
                    } else {
                        stringBuilder2.append("&");
                        obj = obj2;
                    }
                    if (z) {
                        try {
                            stringBuilder2.append(URLEncoder.encode(URLDecoder.decode((String) pair.first, "UTF-8"), "UTF-8"));
                            stringBuilder2.append("=");
                            stringBuilder2.append(URLEncoder.encode(URLDecoder.decode((String) pair.second, "UTF-8"), "UTF-8"));
                        } catch (Throwable th) {
                            obj3 = obj;
                        }
                    } else {
                        stringBuilder2.append(URLEncoder.encode((String) pair.first, "UTF-8"));
                        stringBuilder2.append("=");
                        stringBuilder2.append(URLEncoder.encode((String) pair.second, "UTF-8"));
                    }
                    C1128g.m2333W("params: key: " + ((String) pair.first) + " value: " + ((String) pair.second));
                    stringBuilder.append(stringBuilder2);
                    obj3 = obj;
                    obj2 = obj3;
                }
            } catch (Throwable th2) {
                obj3 = obj2;
            }
        }
        C1128g.m2333W("raw params: " + stringBuilder.toString());
        return stringBuilder.toString();
    }

    public static int m2334a(Context context, float f) {
        return (int) ((((float) context.getResources().getDisplayMetrics().densityDpi) / 160.0f) * f);
    }

    public static void m2341a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        try {
            final String str10 = str5;
            final String str11 = str6;
            final String str12 = str;
            final String str13 = str2;
            final String str14 = str3;
            final String str15 = str4;
            final String str16 = str7;
            final String str17 = str8;
            final String str18 = str9;
            new Thread(new Runnable() {
                public void run() {
                    String str = "";
                    String str2 = "";
                    try {
                        str = URLEncoder.encode(str10.replace(" ", BridgeUtil.UNDERLINE_STR), "UTF-8");
                    } catch (Throwable th) {
                    }
                    try {
                        str2 = URLEncoder.encode(str11.replace(" ", BridgeUtil.UNDERLINE_STR), "UTF-8");
                    } catch (Throwable th2) {
                    }
                    String str3 = "https://admin.appnext.com/tp12.aspx?tid=%s&vid=%s&osid=%s&auid=%s&session_id=%s&pid=%s&ref=%s&ads_type=%s&bid=%s&cid=%s";
                    Object[] objArr = new Object[10];
                    objArr[0] = str12;
                    objArr[1] = str13;
                    objArr[2] = "100";
                    objArr[3] = str14;
                    objArr[4] = str;
                    objArr[5] = str15;
                    objArr[6] = str2;
                    objArr[7] = str16;
                    objArr[8] = str17.equals("") ? SchemaSymbols.ATTVAL_FALSE_0 : str17;
                    objArr[9] = str18.equals("") ? SchemaSymbols.ATTVAL_FALSE_0 : str18;
                    str2 = String.format(str3, objArr);
                    try {
                        C1128g.m2333W("report: " + str2);
                        C1128g.m2336a(str2, null);
                    } catch (Throwable th3) {
                        C1128g.m2333W("report error: " + th3.getMessage());
                    }
                }
            }).start();
        } catch (Throwable th) {
        }
    }

    private static void m2340a(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        try {
            final Context context2 = context;
            final String str9 = str;
            final String str10 = str2;
            final String str11 = str3;
            final String str12 = str4;
            final String str13 = str5;
            final String str14 = str6;
            final String str15 = str7;
            final String str16 = str8;
            new Thread(new Runnable() {
                public void run() {
                    String str = "";
                    try {
                        str = C1128g.m2363z(context2);
                    } catch (Throwable th) {
                    }
                    C1128g.m2341a(str9, str10, str11, str12, str, str13, str14, str15, str16);
                }
            }).start();
        } catch (Throwable th) {
        }
    }

    public static boolean m2356h(Context context, String str) {
        try {
            context.getPackageManager().getPackageInfo(str, 128);
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    public static String m2357p(String str, String str2) {
        String cookie = android.webkit.CookieManager.getInstance().getCookie(str);
        if (cookie == null) {
            return "";
        }
        cookie = null;
        for (String str3 : cookie.split(";")) {
            if (str3.contains(str2)) {
                cookie = str3.split("=")[1];
            }
        }
        return cookie;
    }

    public static String m2360w(Context context) {
        if (context.checkPermission("android.permission.ACCESS_NETWORK_STATE", Process.myPid(), Process.myUid()) != 0) {
            return Card.UNKNOWN;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return "-";
        }
        if (activeNetworkInfo.getType() == 1) {
            return "wifi";
        }
        if (activeNetworkInfo.getType() == 0) {
            return "" + activeNetworkInfo.getSubtype();
        }
        return Card.UNKNOWN;
    }

    public static String m2361x(Context context) {
        if (context == null) {
            return Card.UNKNOWN;
        }
        if (context.checkPermission("android.permission.ACCESS_NETWORK_STATE", Process.myPid(), Process.myUid()) != 0) {
            return Card.UNKNOWN;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return "-";
        }
        if (activeNetworkInfo.getType() == 1) {
            return "wifi";
        }
        if (activeNetworkInfo.getType() != 0) {
            return Card.UNKNOWN;
        }
        switch (activeNetworkInfo.getSubtype()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return "2G";
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return "3G";
            case 13:
                return "4G";
            default:
                return Card.UNKNOWN;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int aP(java.lang.String r7) {
        /*
        r3 = 3;
        r2 = 2;
        r1 = 1;
        r0 = 0;
        r4 = -1;
        r5 = java.util.Locale.US;
        r5 = r7.toLowerCase(r5);
        r6 = r5.hashCode();
        switch(r6) {
            case 1653: goto L_0x0018;
            case 1684: goto L_0x0023;
            case 1715: goto L_0x002e;
            case 3649301: goto L_0x0039;
            default: goto L_0x0012;
        };
    L_0x0012:
        r5 = r4;
    L_0x0013:
        switch(r5) {
            case 0: goto L_0x0017;
            case 1: goto L_0x0044;
            case 2: goto L_0x0046;
            case 3: goto L_0x0048;
            default: goto L_0x0016;
        };
    L_0x0016:
        r0 = r4;
    L_0x0017:
        return r0;
    L_0x0018:
        r6 = "2g";
        r5 = r5.equals(r6);
        if (r5 == 0) goto L_0x0012;
    L_0x0021:
        r5 = r0;
        goto L_0x0013;
    L_0x0023:
        r6 = "3g";
        r5 = r5.equals(r6);
        if (r5 == 0) goto L_0x0012;
    L_0x002c:
        r5 = r1;
        goto L_0x0013;
    L_0x002e:
        r6 = "4g";
        r5 = r5.equals(r6);
        if (r5 == 0) goto L_0x0012;
    L_0x0037:
        r5 = r2;
        goto L_0x0013;
    L_0x0039:
        r6 = "wifi";
        r5 = r5.equals(r6);
        if (r5 == 0) goto L_0x0012;
    L_0x0042:
        r5 = r3;
        goto L_0x0013;
    L_0x0044:
        r0 = r1;
        goto L_0x0017;
    L_0x0046:
        r0 = r2;
        goto L_0x0017;
    L_0x0048:
        r0 = r3;
        goto L_0x0017;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.core.g.aP(java.lang.String):int");
    }

    public static String m2346b(Throwable th) {
        Writer stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        String obj = stringWriter.toString();
        return obj.length() > 512 ? obj.substring(0, 512) : obj;
    }

    public static boolean m2354g(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str) || context.checkPermission(str, Process.myPid(), Process.myUid()) != 0) {
            return false;
        }
        return true;
    }

    public static double cX() {
        return lJ;
    }

    private static synchronized double cY() {
        double d = -1.0d;
        synchronized (C1128g.class) {
            if (lJ > -1.0d) {
                d = lJ;
            } else if (lJ > 0.0d) {
                d = lJ;
            } else {
                long currentTimeMillis = System.currentTimeMillis();
                long totalRxBytes = TrafficStats.getTotalRxBytes();
                InputStream inputStream;
                try {
                    URLConnection openConnection = new URL("https://cdn.appnext.com/banner/appnext.mp4").openConnection();
                    openConnection.setUseCaches(false);
                    openConnection.setReadTimeout(WearableStatusCodes.TARGET_NODE_NOT_CONNECTED);
                    openConnection.setConnectTimeout(WearableStatusCodes.TARGET_NODE_NOT_CONNECTED);
                    openConnection.connect();
                    inputStream = openConnection.getInputStream();
                    do {
                    } while (inputStream.read(new byte[1024]) != -1);
                    try {
                        inputStream.close();
                    } catch (Throwable th) {
                    }
                    d = (((double) (TrafficStats.getTotalRxBytes() - totalRxBytes)) / (((double) (System.currentTimeMillis() - currentTimeMillis)) / 1000.0d)) / 1024.0d;
                    lJ = d;
                } catch (Throwable th2) {
                }
            }
        }
        return d;
    }

    public static String m2362y(Context context) {
        String str = "";
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager.getSimState() != 5) {
            return str;
        }
        String simOperator = telephonyManager.getSimOperator();
        return simOperator.substring(0, 3) + BridgeUtil.UNDERLINE_STR + simOperator.substring(3);
    }

    public static String cv() {
        return Locale.getDefault().getLanguage();
    }

    public static String m2363z(Context context) {
        if (lN.equals("")) {
            synchronized ("2.2.5.468") {
                if (lN.equals("")) {
                    lN = C1128g.m2330A(context);
                }
            }
        }
        return lN;
    }

    public static String m2330A(Context context) {
        String u = C1128g.m2358u(context);
        if (u.equals("")) {
            return C1128g.m2355h(32);
        }
        return C1128g.aQ(u + BridgeUtil.UNDERLINE_STR + (System.currentTimeMillis() / 1000));
    }

    private static String aQ(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.reset();
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            int length = digest.length;
            StringBuilder stringBuilder = new StringBuilder(length << 1);
            for (int i = 0; i < length; i++) {
                stringBuilder.append(Character.forDigit((digest[i] & 240) >> 4, 16));
                stringBuilder.append(Character.forDigit(digest[i] & 15, 16));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            return C1128g.m2355h(32);
        }
    }

    private static String m2355h(int i) {
        char[] toCharArray = "0123456789abcdef".toCharArray();
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            stringBuilder.append(toCharArray[random.nextInt(toCharArray.length)]);
        }
        return stringBuilder.toString();
    }

    public static String m2332N(String str) {
        String substring = str.substring(str.lastIndexOf(BridgeUtil.SPLIT_MARK) + 1);
        if (substring.contains("?")) {
            substring = substring.substring(0, substring.indexOf("?"));
        }
        try {
            String queryParameter = Uri.parse(str).getQueryParameter("rnd");
            if (!(queryParameter == null || queryParameter.equals(""))) {
                substring = substring.substring(0, substring.lastIndexOf(46)) + BridgeUtil.UNDERLINE_STR + queryParameter + substring.substring(substring.lastIndexOf(46));
            }
        } catch (Throwable th) {
        }
        return substring;
    }

    public static boolean m2331B(Context context) {
        try {
            if (context.checkPermission("android.permission.ACCESS_NETWORK_STATE", Process.myPid(), Process.myUid()) != 0) {
                C1128g.m2336a("http://www.appnext.com/myid.html", null);
            } else {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                    throw new IOException();
                }
            }
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    public static void m2333W(String str) {
    }

    public static void m2351c(Throwable th) {
    }

    public static void m2348b(File file) {
        if (file.isDirectory()) {
            for (File b : file.listFiles()) {
                C1128g.m2348b(b);
            }
        }
        file.delete();
    }
}
