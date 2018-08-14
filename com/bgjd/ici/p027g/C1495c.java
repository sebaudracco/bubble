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
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.Locale;

public class C1495c implements C1494a {
    private URI f2430a;
    private C1415b f2431b;
    private HttpURLConnection f2432c = null;

    public C1495c(URI uri, C1415b c1415b) {
        this.f2430a = uri;
        this.f2431b = c1415b;
    }

    public void mo2330a() {
        try {
            this.f2432c = (HttpURLConnection) this.f2430a.toURL().openConnection();
            this.f2432c.setDoOutput(true);
            this.f2432c.setChunkedStreamingMode(0);
            if (this.f2431b != null) {
                this.f2431b.mo2304a();
            }
        } catch (Exception e) {
            if (this.f2431b != null) {
                this.f2431b.mo2306a(e);
            }
        } catch (Exception e2) {
            if (this.f2431b != null) {
                this.f2431b.mo2306a(e2);
            }
        }
    }

    public HttpURLConnection m3205d() {
        return this.f2432c;
    }

    public void mo2333b() {
        if (this.f2432c != null) {
            this.f2432c.disconnect();
        }
    }

    public void mo2331a(String str) {
        try {
            this.f2432c.setRequestMethod(HttpPost.METHOD_NAME);
            this.f2432c.setRequestProperty("User-Agent", m3198e());
            this.f2432c.setRequestProperty("Content-Type", RequestParams.APPLICATION_JSON);
            this.f2432c.setRequestProperty("Accept", RequestParams.APPLICATION_JSON);
            DataOutputStream dataOutputStream = new DataOutputStream(this.f2432c.getOutputStream());
            dataOutputStream.writeBytes(str);
            dataOutputStream.flush();
            dataOutputStream.close();
            int responseCode = this.f2432c.getResponseCode();
            if (responseCode == 200) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.f2432c.getInputStream()));
                StringBuffer stringBuffer = new StringBuffer();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    stringBuffer.append(readLine);
                }
                bufferedReader.close();
                if (this.f2431b != null) {
                    this.f2431b.mo2307a(stringBuffer.toString());
                }
            } else if (this.f2431b != null) {
                this.f2431b.mo2306a(new Exception("Connection Error " + responseCode));
            }
        } catch (Exception e) {
            this.f2431b.mo2306a(e);
        } catch (Exception e2) {
            this.f2431b.mo2306a(e2);
        }
    }

    private String m3198e() {
        String property = System.getProperty("http.agent");
        if (property.length() == 0) {
            return m3199f();
        }
        return property;
    }

    @SuppressLint({"DefaultLocale"})
    private String m3199f() {
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
        return this.f2431b;
    }
}
