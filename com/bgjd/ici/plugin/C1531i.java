package com.bgjd.ici.plugin;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Build.VERSION;
import android.webkit.URLUtil;
import com.bgjd.ici.json.JSONArray;
import com.bgjd.ici.p025b.C1395h;
import com.bgjd.ici.p025b.C1402i;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.bgjd.ici.p029d.C1458i;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.loopj.android.http.RequestParams;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Locale;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class C1531i implements C1519c {
    private static final String f2526b = "PLGDWL";
    TrustManager[] f2527a = new TrustManager[]{new C15271(this)};
    private C1395h f2528c;

    class C15271 implements X509TrustManager {
        final /* synthetic */ C1531i f2515a;

        C15271(C1531i c1531i) {
            this.f2515a = c1531i;
        }

        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private interface C1528a {
        boolean mo2354a();
    }

    private class C1529b implements C1528a {
        final /* synthetic */ C1531i f2516a;
        private String f2517b = "";
        private String f2518c = "";
        private int f2519d = 0;
        private JSONArray f2520e = new JSONArray();

        public C1529b(C1531i c1531i, int i, String str, String str2, JSONArray jSONArray) {
            this.f2516a = c1531i;
            this.f2519d = i;
            this.f2518c = str;
            this.f2517b = str2;
            this.f2520e = jSONArray;
        }

        public boolean mo2354a() {
            Throwable th;
            Throwable th2;
            HttpURLConnection httpURLConnection = null;
            try {
                HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(this.f2517b).openConnection();
                try {
                    httpURLConnection2.setDoOutput(true);
                    httpURLConnection2.setRequestMethod(HttpPost.METHOD_NAME);
                    httpURLConnection2.setRequestProperty("User-Agent", this.f2516a.m3307b());
                    httpURLConnection2.setRequestProperty("Content-Type", RequestParams.APPLICATION_JSON);
                    DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection2.getOutputStream());
                    C1458i c1458i = new C1458i(this.f2516a.f2528c);
                    c1458i.m3014a(this.f2519d);
                    c1458i.m3016a(this.f2518c.toLowerCase());
                    c1458i.m3015a(this.f2520e);
                    dataOutputStream.writeBytes(c1458i.m3018f().toString());
                    dataOutputStream.flush();
                    dataOutputStream.close();
                    if (httpURLConnection2.getResponseCode() == 200) {
                        String headerField = httpURLConnection2.getHeaderField("Content-Disposition");
                        if (headerField != null) {
                            int indexOf = headerField.indexOf("filename=");
                            if (indexOf > 0) {
                                this.f2518c = headerField.substring(indexOf + 10, headerField.length() - 1);
                            }
                        } else {
                            this.f2518c = this.f2517b.substring(this.f2517b.lastIndexOf(BridgeUtil.SPLIT_MARK) + 1, this.f2517b.length());
                        }
                        InputStream inputStream = httpURLConnection2.getInputStream();
                        File file = new File(this.f2516a.f2528c.getContext().getFilesDir(), this.f2518c);
                        if (file.exists()) {
                            file.delete();
                        }
                        FileOutputStream openFileOutput = this.f2516a.f2528c.getContext().openFileOutput(this.f2518c, 0);
                        byte[] bArr = new byte[4096];
                        boolean z = false;
                        while (true) {
                            int read = inputStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            openFileOutput.write(bArr, 0, read);
                            z = true;
                        }
                        openFileOutput.close();
                        inputStream.close();
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        return z;
                    }
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    return false;
                } catch (Throwable e) {
                    th = e;
                    httpURLConnection = httpURLConnection2;
                    th2 = th;
                    try {
                        C1402i.m2898a(C1531i.f2526b, th2, th2.getMessage());
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        return false;
                    } catch (Throwable th3) {
                        th2 = th3;
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        throw th2;
                    }
                } catch (Throwable e2) {
                    th = e2;
                    httpURLConnection = httpURLConnection2;
                    th2 = th;
                    C1402i.m2898a(C1531i.f2526b, th2, th2.getMessage());
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return false;
                } catch (Throwable e22) {
                    th = e22;
                    httpURLConnection = httpURLConnection2;
                    th2 = th;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw th2;
                }
            } catch (MalformedURLException e3) {
                th2 = e3;
                C1402i.m2898a(C1531i.f2526b, th2, th2.getMessage());
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return false;
            } catch (IOException e4) {
                th2 = e4;
                C1402i.m2898a(C1531i.f2526b, th2, th2.getMessage());
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return false;
            }
        }
    }

    private class C1530c implements C1528a {
        final /* synthetic */ C1531i f2521a;
        private String f2522b = "";
        private String f2523c = "";
        private int f2524d = 0;
        private JSONArray f2525e = new JSONArray();

        public C1530c(C1531i c1531i, int i, String str, String str2, JSONArray jSONArray) {
            this.f2521a = c1531i;
            this.f2524d = i;
            this.f2523c = str;
            this.f2522b = str2;
            this.f2525e = jSONArray;
        }

        public boolean mo2354a() {
            HttpsURLConnection httpsURLConnection;
            Throwable th;
            HttpsURLConnection httpsURLConnection2 = null;
            try {
                SSLContext instance = SSLContext.getInstance("TLS");
                instance.init(null, this.f2521a.f2527a, null);
                HttpsURLConnection.setDefaultSSLSocketFactory(instance.getSocketFactory());
                HttpsURLConnection httpsURLConnection3 = (HttpsURLConnection) new URL(this.f2522b).openConnection();
                try {
                    httpsURLConnection3.setDoOutput(true);
                    httpsURLConnection3.setChunkedStreamingMode(0);
                    httpsURLConnection3.setRequestMethod(HttpPost.METHOD_NAME);
                    httpsURLConnection3.setRequestProperty("User-Agent", this.f2521a.m3307b());
                    httpsURLConnection3.setRequestProperty("Content-Type", RequestParams.APPLICATION_JSON);
                    DataOutputStream dataOutputStream = new DataOutputStream(httpsURLConnection3.getOutputStream());
                    C1458i c1458i = new C1458i(this.f2521a.f2528c);
                    c1458i.m3014a(this.f2524d);
                    c1458i.m3016a(this.f2523c.toLowerCase());
                    c1458i.m3015a(this.f2525e);
                    dataOutputStream.writeBytes(c1458i.m3018f().toString());
                    dataOutputStream.flush();
                    dataOutputStream.close();
                    if (httpsURLConnection3.getResponseCode() == 200) {
                        String headerField = httpsURLConnection3.getHeaderField("Content-Disposition");
                        if (headerField != null) {
                            int indexOf = headerField.indexOf("filename=");
                            if (indexOf > 0) {
                                this.f2523c = headerField.substring(indexOf + 10, headerField.length() - 1);
                            }
                        } else {
                            this.f2523c = this.f2522b.substring(this.f2522b.lastIndexOf(BridgeUtil.SPLIT_MARK) + 1, this.f2522b.length());
                        }
                        InputStream inputStream = httpsURLConnection3.getInputStream();
                        File file = new File(this.f2521a.f2528c.getContext().getFilesDir(), this.f2523c);
                        if (file.exists()) {
                            file.delete();
                        }
                        FileOutputStream openFileOutput = this.f2521a.f2528c.getContext().openFileOutput(this.f2523c, 0);
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
                        if (httpsURLConnection3 != null) {
                            httpsURLConnection3.disconnect();
                        }
                        return true;
                    }
                    if (httpsURLConnection3 != null) {
                        httpsURLConnection3.disconnect();
                    }
                    return false;
                } catch (Throwable e) {
                    Throwable th2 = e;
                    httpsURLConnection = httpsURLConnection3;
                    th = th2;
                    try {
                        C1402i.m2898a(C1531i.f2526b, th, th.getMessage());
                        if (httpsURLConnection != null) {
                            httpsURLConnection.disconnect();
                        }
                        return false;
                    } catch (Throwable th3) {
                        th = th3;
                        httpsURLConnection2 = httpsURLConnection;
                        if (httpsURLConnection2 != null) {
                            httpsURLConnection2.disconnect();
                        }
                        throw th;
                    }
                } catch (Throwable e2) {
                    httpsURLConnection2 = httpsURLConnection3;
                    th = e2;
                    try {
                        C1402i.m2898a(C1531i.f2526b, th, th.getMessage());
                        if (httpsURLConnection2 != null) {
                            httpsURLConnection2.disconnect();
                        }
                        return false;
                    } catch (Throwable th4) {
                        th = th4;
                        if (httpsURLConnection2 != null) {
                            httpsURLConnection2.disconnect();
                        }
                        throw th;
                    }
                } catch (Throwable e22) {
                    httpsURLConnection2 = httpsURLConnection3;
                    th = e22;
                    C1402i.m2898a(C1531i.f2526b, th, th.getMessage());
                    if (httpsURLConnection2 != null) {
                        httpsURLConnection2.disconnect();
                    }
                    return false;
                } catch (Throwable e222) {
                    httpsURLConnection2 = httpsURLConnection3;
                    th = e222;
                    C1402i.m2898a(C1531i.f2526b, th, th.getMessage());
                    if (httpsURLConnection2 != null) {
                        httpsURLConnection2.disconnect();
                    }
                    return false;
                } catch (Throwable e2222) {
                    httpsURLConnection2 = httpsURLConnection3;
                    th = e2222;
                    if (httpsURLConnection2 != null) {
                        httpsURLConnection2.disconnect();
                    }
                    throw th;
                }
            } catch (MalformedURLException e3) {
                th = e3;
                httpsURLConnection = null;
                C1402i.m2898a(C1531i.f2526b, th, th.getMessage());
                if (httpsURLConnection != null) {
                    httpsURLConnection.disconnect();
                }
                return false;
            } catch (IOException e4) {
                th = e4;
                C1402i.m2898a(C1531i.f2526b, th, th.getMessage());
                if (httpsURLConnection2 != null) {
                    httpsURLConnection2.disconnect();
                }
                return false;
            } catch (NoSuchAlgorithmException e5) {
                th = e5;
                C1402i.m2898a(C1531i.f2526b, th, th.getMessage());
                if (httpsURLConnection2 != null) {
                    httpsURLConnection2.disconnect();
                }
                return false;
            } catch (KeyManagementException e6) {
                th = e6;
                C1402i.m2898a(C1531i.f2526b, th, th.getMessage());
                if (httpsURLConnection2 != null) {
                    httpsURLConnection2.disconnect();
                }
                return false;
            }
        }
    }

    public C1531i(C1395h c1395h) {
        this.f2528c = c1395h;
    }

    public boolean mo2355a() {
        C1528a c1530c;
        String I = this.f2528c.mo2181I();
        if (URLUtil.isHttpsUrl(I)) {
            c1530c = new C1530c(this, this.f2528c.getExtensionId(), this.f2528c.mo2180H(), I, this.f2528c.mo2183J());
        } else {
            c1530c = new C1529b(this, this.f2528c.getExtensionId(), this.f2528c.mo2180H(), I, this.f2528c.mo2183J());
        }
        return c1530c.mo2354a();
    }

    private String m3307b() {
        String property = System.getProperty("http.agent");
        if (property.length() == 0) {
            return m3308c();
        }
        return property;
    }

    @SuppressLint({"DefaultLocale"})
    private String m3308c() {
        String str = VERSION.RELEASE;
        String str2 = Build.MODEL;
        String str3 = Build.ID;
        Locale locale = Locale.getDefault();
        String language = locale.getLanguage();
        String str4 = "en";
        if (language != null) {
            str4 = language.toLowerCase();
            String country = locale.getCountry();
            if (country != null) {
                str4 = str4 + "-" + country.toLowerCase();
            }
        }
        return String.format(C1404b.f2142t, new Object[]{str, str4, str2, str3});
    }
}
