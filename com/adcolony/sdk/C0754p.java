package com.adcolony.sdk;

import com.adcolony.sdk.aa.C0595a;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import cz.msebera.android.httpclient.HttpHeaders;
import cz.msebera.android.httpclient.HttpStatus;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import mf.javax.xml.transform.OutputKeys;
import org.json.JSONObject;

class C0754p implements Runnable {
    String f1353a = "";
    String f1354b = "";
    boolean f1355c;
    int f1356d;
    int f1357e;
    private HttpURLConnection f1358f;
    private InputStream f1359g;
    private af f1360h;
    private C0739a f1361i;
    private final int f1362j = 4096;
    private String f1363k;
    private int f1364l = 0;
    private boolean f1365m = false;
    private Map<String, List<String>> f1366n;
    private String f1367o = "";

    interface C0739a {
        void mo1865a(C0754p c0754p, af afVar, Map<String, List<String>> map);
    }

    C0754p(af afVar, C0739a c0739a) {
        this.f1360h = afVar;
        this.f1361i = c0739a;
    }

    public void run() {
        boolean z = false;
        this.f1355c = false;
        try {
            if (m1346b()) {
                this.f1355c = m1347c();
                if (this.f1360h.m699d().equals("WebServices.post") && this.f1357e != 200) {
                    this.f1355c = false;
                }
            }
            z = true;
        } catch (MalformedURLException e) {
            new C0595a().m622a("MalformedURLException: ").m622a(e.toString()).m624a(aa.f484h);
            this.f1355c = true;
            z = true;
        } catch (OutOfMemoryError e2) {
            new C0595a().m622a("Out of memory error - disabling AdColony. (").m620a(this.f1356d).m622a(BridgeUtil.SPLIT_MARK).m620a(this.f1364l).m622a("): " + this.f1353a).m624a(aa.f483g);
            C0594a.m605a().m1259a(true);
            z = true;
        } catch (IOException e3) {
            int i;
            new C0595a().m622a("Download of ").m622a(this.f1353a).m622a(" failed: ").m622a(e3.toString()).m624a(aa.f482f);
            if (this.f1357e == 0) {
                i = HttpStatus.SC_GATEWAY_TIMEOUT;
            } else {
                i = this.f1357e;
            }
            this.f1357e = i;
            z = true;
        } catch (IllegalStateException e4) {
            new C0595a().m622a("okhttp error: ").m622a(e4.toString()).m624a(aa.f483g);
            e4.printStackTrace();
        } catch (Exception e5) {
            new C0595a().m622a("Exception: ").m622a(e5.toString()).m624a(aa.f483g);
            e5.printStackTrace();
            z = true;
        }
        if (this.f1355c) {
            new C0595a().m622a("Downloaded ").m622a(this.f1353a).m624a(aa.f480d);
        }
        if (z) {
            this.f1361i.mo1865a(this, this.f1360h, this.f1366n);
        }
    }

    private boolean m1346b() throws IOException {
        JSONObject c = this.f1360h.m698c();
        String b = C0802y.m1468b(c, FirebaseAnalytics$Param.CONTENT_TYPE);
        String b2 = C0802y.m1468b(c, FirebaseAnalytics$Param.CONTENT);
        boolean d = C0802y.m1477d(c, "no_redirect");
        this.f1353a = C0802y.m1468b(c, "url");
        this.f1367o = C0802y.m1468b(c, "filepath");
        this.f1363k = C0802y.m1468b(c, OutputKeys.ENCODING);
        this.f1364l = C0802y.m1449a(c, "max_size", 0);
        this.f1365m = this.f1364l != 0;
        this.f1356d = 0;
        this.f1359g = null;
        this.f1358f = null;
        this.f1366n = null;
        String str = "file://";
        if (this.f1353a.startsWith(str)) {
            b = "file:///android_asset/";
            if (!this.f1353a.startsWith(b)) {
                this.f1359g = new FileInputStream(this.f1353a.substring(str.length()));
            } else if (C0594a.m614d()) {
                this.f1359g = C0594a.m613c().getAssets().open(this.f1353a.substring(b.length()));
            }
        } else {
            this.f1358f = (HttpURLConnection) new URL(this.f1353a).openConnection();
            this.f1358f.setInstanceFollowRedirects(!d);
            this.f1358f.setRequestProperty(HttpHeaders.ACCEPT_CHARSET, "UTF-8");
            if (!b.equals("")) {
                this.f1358f.setRequestProperty("Content-Type", b);
            }
            if (this.f1360h.m699d().equals("WebServices.post")) {
                this.f1358f.setDoOutput(true);
                this.f1358f.setFixedLengthStreamingMode(b2.getBytes("UTF-8").length);
                new PrintStream(this.f1358f.getOutputStream()).print(b2);
            }
        }
        if (this.f1358f == null && this.f1359g == null) {
            return false;
        }
        return true;
    }

    private boolean m1347c() throws Exception {
        OutputStream outputStream = null;
        String d = this.f1360h.m699d();
        if (this.f1359g != null) {
            if (this.f1367o.length() == 0) {
                outputStream = new ByteArrayOutputStream(4096);
            } else {
                outputStream = new FileOutputStream(new File(this.f1367o).getAbsolutePath());
            }
        } else if (d.equals("WebServices.download")) {
            this.f1359g = this.f1358f.getInputStream();
            outputStream = new FileOutputStream(this.f1367o);
        } else if (d.equals("WebServices.get")) {
            this.f1359g = this.f1358f.getInputStream();
            outputStream = new ByteArrayOutputStream(4096);
        } else if (d.equals("WebServices.post")) {
            this.f1358f.connect();
            this.f1359g = this.f1358f.getResponseCode() == 200 ? this.f1358f.getInputStream() : this.f1358f.getErrorStream();
            outputStream = new ByteArrayOutputStream(4096);
        }
        if (this.f1358f != null) {
            this.f1357e = this.f1358f.getResponseCode();
            this.f1366n = this.f1358f.getHeaderFields();
        }
        return m1345a(this.f1359g, outputStream);
    }

    private boolean m1345a(InputStream inputStream, OutputStream outputStream) throws Exception {
        Exception e;
        Throwable th;
        BufferedInputStream bufferedInputStream;
        try {
            bufferedInputStream = new BufferedInputStream(inputStream);
            try {
                String str;
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = bufferedInputStream.read(bArr, 0, 4096);
                    if (read == -1) {
                        break;
                    }
                    this.f1356d += read;
                    if (!this.f1365m || this.f1356d <= this.f1364l) {
                        outputStream.write(bArr, 0, read);
                    } else {
                        throw new Exception("Data exceeds expected maximum (" + this.f1356d + BridgeUtil.SPLIT_MARK + this.f1364l + "): " + this.f1358f.getURL().toString());
                    }
                }
                String str2 = "UTF-8";
                if (this.f1363k == null || this.f1363k.isEmpty()) {
                    str = str2;
                } else {
                    str = this.f1363k;
                }
                if (outputStream instanceof ByteArrayOutputStream) {
                    this.f1354b = ((ByteArrayOutputStream) outputStream).toString(str);
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                return true;
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            bufferedInputStream = null;
            try {
                throw e;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedInputStream = null;
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            throw th;
        }
    }

    af m1348a() {
        return this.f1360h;
    }
}
