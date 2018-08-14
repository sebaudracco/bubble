package com.bgjd.ici.p027g;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Build.VERSION;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.loopj.android.http.RequestParams;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Locale;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class C1497d implements C1494a {
    TrustManager[] f2434a = new TrustManager[]{new C14961(this)};
    private URI f2435b;
    private C1415b f2436c;
    private HttpsURLConnection f2437d = null;

    class C14961 implements X509TrustManager {
        final /* synthetic */ C1497d f2433a;

        C14961(C1497d c1497d) {
            this.f2433a = c1497d;
        }

        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    public C1497d(URI uri, C1415b c1415b) {
        this.f2435b = uri;
        this.f2436c = c1415b;
    }

    public void mo2330a() {
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(null, this.f2434a, null);
            HttpsURLConnection.setDefaultSSLSocketFactory(instance.getSocketFactory());
            this.f2437d = (HttpsURLConnection) this.f2435b.toURL().openConnection();
            this.f2437d.setDoOutput(true);
            this.f2437d.setChunkedStreamingMode(0);
            if (this.f2436c != null) {
                this.f2436c.mo2304a();
            }
        } catch (Exception e) {
            if (this.f2436c != null) {
                this.f2436c.mo2306a(e);
            }
        } catch (Exception e2) {
            if (this.f2436c != null) {
                this.f2436c.mo2306a(e2);
            }
        } catch (Exception e22) {
            if (this.f2436c != null) {
                this.f2436c.mo2306a(e22);
            }
        } catch (Exception e222) {
            if (this.f2436c != null) {
                this.f2436c.mo2306a(e222);
            }
        }
    }

    public HttpsURLConnection m3213d() {
        return this.f2437d;
    }

    public void mo2333b() {
        if (this.f2437d != null) {
            this.f2437d.disconnect();
        }
    }

    public void mo2331a(String str) {
        try {
            this.f2437d.setRequestMethod(HttpPost.METHOD_NAME);
            this.f2437d.setRequestProperty("User-Agent", m3206e());
            this.f2437d.setRequestProperty("Content-Type", RequestParams.APPLICATION_JSON);
            this.f2437d.setRequestProperty("Accept", RequestParams.APPLICATION_JSON);
            DataOutputStream dataOutputStream = new DataOutputStream(this.f2437d.getOutputStream());
            dataOutputStream.writeBytes(str);
            dataOutputStream.flush();
            dataOutputStream.close();
            int responseCode = this.f2437d.getResponseCode();
            if (responseCode == 200) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.f2437d.getInputStream()));
                StringBuffer stringBuffer = new StringBuffer();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    stringBuffer.append(readLine);
                }
                bufferedReader.close();
                if (this.f2436c != null) {
                    this.f2436c.mo2307a(stringBuffer.toString());
                }
            } else if (this.f2436c != null) {
                this.f2436c.mo2306a(new Exception("Connection Error " + responseCode));
            }
        } catch (Exception e) {
            this.f2436c.mo2306a(e);
        } catch (Exception e2) {
            this.f2436c.mo2306a(e2);
        }
    }

    private String m3206e() {
        String property = System.getProperty("http.agent");
        if (property.length() == 0) {
            return m3207f();
        }
        return property;
    }

    @SuppressLint({"DefaultLocale"})
    private String m3207f() {
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

    public void mo2332a(byte[] bArr) {
    }

    public C1415b mo2334c() {
        return this.f2436c;
    }
}
