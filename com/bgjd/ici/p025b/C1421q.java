package com.bgjd.ici.p025b;

import android.webkit.URLUtil;
import com.bgjd.ici.p030e.C1487j;
import com.github.lzyzsd.jsbridge.BridgeUtil;
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
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class C1421q implements C1399d {
    TrustManager[] f2192a;
    private C1395h f2193b;
    private C1412b f2194c;

    public interface C1412b {
        void mo2302a(Throwable th, String str);

        void mo2303a(boolean z);
    }

    class C14171 implements X509TrustManager {
        final /* synthetic */ C1421q f2183a;

        C14171(C1421q c1421q) {
            this.f2183a = c1421q;
        }

        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private interface C1418a {
        void mo2309a();
    }

    private class C1419c implements C1418a {
        final /* synthetic */ C1421q f2184a;
        private String f2185b = "";
        private String f2186c = "";
        private String f2187d = "";

        public C1419c(C1421q c1421q, String str, String str2, String str3) {
            this.f2184a = c1421q;
            this.f2185b = str;
            this.f2187d = str2;
            this.f2186c = str3;
        }

        public void mo2309a() {
            Throwable th;
            Throwable th2;
            HttpURLConnection httpURLConnection = null;
            try {
                HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(this.f2185b).openConnection();
                try {
                    if (httpURLConnection2.getResponseCode() == 200) {
                        String headerField = httpURLConnection2.getHeaderField("Content-Disposition");
                        if (headerField != null) {
                            int indexOf = headerField.indexOf("filename=");
                            if (indexOf > 0) {
                                this.f2186c = headerField.substring(indexOf + 10, headerField.length() - 1);
                            }
                        } else {
                            this.f2186c = this.f2185b.substring(this.f2185b.lastIndexOf(BridgeUtil.SPLIT_MARK) + 1, this.f2185b.length());
                        }
                        InputStream inputStream = httpURLConnection2.getInputStream();
                        File file = new File(this.f2184a.f2193b.getContext().getFilesDir(), this.f2186c);
                        if (file.exists()) {
                            file.delete();
                        }
                        FileOutputStream openFileOutput = this.f2184a.f2193b.getContext().openFileOutput(this.f2186c, 0);
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
                        if (this.f2187d.length() <= 0) {
                            this.f2184a.f2194c.mo2303a(true);
                        } else if (ac.m2863a(this.f2187d, file)) {
                            this.f2184a.f2194c.mo2303a(true);
                        }
                    }
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                } catch (Throwable e) {
                    th = e;
                    httpURLConnection = httpURLConnection2;
                    th2 = th;
                    try {
                        this.f2184a.f2194c.mo2302a(th2, th2.getMessage());
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
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
                    this.f2184a.f2194c.mo2302a(th2, th2.getMessage());
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
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
                this.f2184a.f2194c.mo2302a(th2, th2.getMessage());
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
            } catch (IOException e4) {
                th2 = e4;
                this.f2184a.f2194c.mo2302a(th2, th2.getMessage());
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
            }
        }
    }

    private class C1420d implements C1418a {
        final /* synthetic */ C1421q f2188a;
        private String f2189b = "";
        private String f2190c = "";
        private String f2191d = "";

        public C1420d(C1421q c1421q, String str, String str2, String str3) {
            this.f2188a = c1421q;
            this.f2189b = str;
            this.f2191d = str2;
            this.f2190c = str3;
        }

        public void mo2309a() {
            Throwable th;
            Throwable th2;
            HttpsURLConnection httpsURLConnection = null;
            try {
                SSLContext instance = SSLContext.getInstance("TLS");
                instance.init(null, this.f2188a.f2192a, null);
                HttpsURLConnection.setDefaultSSLSocketFactory(instance.getSocketFactory());
                HttpsURLConnection httpsURLConnection2 = (HttpsURLConnection) new URL(this.f2189b).openConnection();
                try {
                    if (httpsURLConnection2.getResponseCode() == 200) {
                        String headerField = httpsURLConnection2.getHeaderField("Content-Disposition");
                        if (headerField != null) {
                            int indexOf = headerField.indexOf("filename=");
                            if (indexOf > 0) {
                                this.f2190c = headerField.substring(indexOf + 10, headerField.length() - 1);
                            }
                        } else {
                            this.f2190c = this.f2189b.substring(this.f2189b.lastIndexOf(BridgeUtil.SPLIT_MARK) + 1, this.f2189b.length());
                        }
                        InputStream inputStream = httpsURLConnection2.getInputStream();
                        File file = new File(this.f2188a.f2193b.getContext().getFilesDir(), this.f2190c);
                        if (file.exists()) {
                            file.delete();
                        }
                        FileOutputStream openFileOutput = this.f2188a.f2193b.getContext().openFileOutput(this.f2190c, 0);
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
                        if (this.f2191d.length() <= 0) {
                            this.f2188a.f2194c.mo2303a(true);
                        } else if (ac.m2863a(this.f2191d, file)) {
                            this.f2188a.f2194c.mo2303a(true);
                        }
                    }
                    if (httpsURLConnection2 != null) {
                        httpsURLConnection2.disconnect();
                    }
                } catch (Throwable e) {
                    th = e;
                    httpsURLConnection = httpsURLConnection2;
                    th2 = th;
                    try {
                        this.f2188a.f2194c.mo2302a(th2, th2.getMessage());
                        if (httpsURLConnection != null) {
                            httpsURLConnection.disconnect();
                        }
                    } catch (Throwable th3) {
                        th2 = th3;
                        if (httpsURLConnection != null) {
                            httpsURLConnection.disconnect();
                        }
                        throw th2;
                    }
                } catch (Throwable e2) {
                    th = e2;
                    httpsURLConnection = httpsURLConnection2;
                    th2 = th;
                    this.f2188a.f2194c.mo2302a(th2, th2.getMessage());
                    if (httpsURLConnection != null) {
                        httpsURLConnection.disconnect();
                    }
                } catch (Throwable e22) {
                    th = e22;
                    httpsURLConnection = httpsURLConnection2;
                    th2 = th;
                    this.f2188a.f2194c.mo2302a(th2, th2.getMessage());
                    if (httpsURLConnection != null) {
                        httpsURLConnection.disconnect();
                    }
                } catch (Throwable e222) {
                    th = e222;
                    httpsURLConnection = httpsURLConnection2;
                    th2 = th;
                    this.f2188a.f2194c.mo2302a(th2, th2.getMessage());
                    if (httpsURLConnection != null) {
                        httpsURLConnection.disconnect();
                    }
                } catch (Throwable e2222) {
                    th = e2222;
                    httpsURLConnection = httpsURLConnection2;
                    th2 = th;
                    if (httpsURLConnection != null) {
                        httpsURLConnection.disconnect();
                    }
                    throw th2;
                }
            } catch (MalformedURLException e3) {
                th2 = e3;
                this.f2188a.f2194c.mo2302a(th2, th2.getMessage());
                if (httpsURLConnection != null) {
                    httpsURLConnection.disconnect();
                }
            } catch (IOException e4) {
                th2 = e4;
                this.f2188a.f2194c.mo2302a(th2, th2.getMessage());
                if (httpsURLConnection != null) {
                    httpsURLConnection.disconnect();
                }
            } catch (NoSuchAlgorithmException e5) {
                th2 = e5;
                this.f2188a.f2194c.mo2302a(th2, th2.getMessage());
                if (httpsURLConnection != null) {
                    httpsURLConnection.disconnect();
                }
            } catch (KeyManagementException e6) {
                th2 = e6;
                this.f2188a.f2194c.mo2302a(th2, th2.getMessage());
                if (httpsURLConnection != null) {
                    httpsURLConnection.disconnect();
                }
            }
        }
    }

    public C1421q(C1395h c1395h, C1412b c1412b) {
        this.f2192a = new TrustManager[]{new C14171(this)};
        this.f2193b = c1395h;
        this.f2194c = c1412b;
    }

    public C1421q(C1395h c1395h) {
        this.f2192a = new TrustManager[]{new C14171(this)};
        this.f2193b = c1395h;
    }

    public void mo2310a(C1487j c1487j, C1412b c1412b) {
        C1418a c1420d;
        this.f2194c = c1412b;
        String g = c1487j.m3155g();
        if (URLUtil.isHttpsUrl(g)) {
            c1420d = new C1420d(this, g, c1487j.m3156h(), c1487j.m3158j());
        } else {
            c1420d = new C1419c(this, g, c1487j.m3156h(), c1487j.m3158j());
        }
        c1420d.mo2309a();
    }
}
