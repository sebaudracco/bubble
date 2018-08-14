package com.unit.two.p151f;

import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.unit.two.p147c.C4096a;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;
import javax.net.ssl.SSLException;

final class C4142i {
    static {
        String str = C4096a.aZ;
    }

    private C4142i() {
    }

    protected static String m12787a(C4140g c4140g) {
        return new C4142i().m12789b(c4140g);
    }

    private static void m12788a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable th) {
            }
        }
    }

    private String m12789b(C4140g c4140g) {
        if (c4140g == null) {
            return null;
        }
        try {
            switch (C4141h.f9683a[c4140g.m12784e().ordinal()]) {
                case 1:
                    return m12790c(c4140g);
                case 2:
                    return m12791d(c4140g);
                default:
                    throw new IllegalStateException(C4096a.ba + c4140g.m12784e());
            }
        } catch (Throwable th) {
            return null;
        }
    }

    private String m12790c(C4140g c4140g) {
        Closeable bufferedReader;
        Closeable closeable;
        HttpURLConnection httpURLConnection;
        Closeable closeable2;
        Throwable th;
        HttpURLConnection httpURLConnection2;
        Throwable th2;
        Closeable closeable3 = null;
        Closeable inputStream;
        Closeable inputStreamReader;
        try {
            String c = c4140g.m12782c();
            Map f = c4140g.m12785f();
            if (f != null && f.size() > 0) {
                Builder buildUpon = Uri.parse(c).buildUpon();
                for (Entry entry : f.entrySet()) {
                    if (!TextUtils.isEmpty((CharSequence) entry.getKey())) {
                        buildUpon.appendQueryParameter((String) entry.getKey(), entry.getValue() == null ? "" : entry.getValue().toString());
                    }
                }
                c = buildUpon.toString();
            }
            c4140g.m12776a(c);
            HttpURLConnection httpURLConnection3 = (HttpURLConnection) new URL(c4140g.m12782c()).openConnection();
            try {
                httpURLConnection3.setUseCaches(false);
                httpURLConnection3.setReadTimeout(30000);
                httpURLConnection3.setConnectTimeout(10000);
                httpURLConnection3.setRequestMethod(C4096a.bb);
                if (c4140g.m12780b() != null && c4140g.m12780b().size() > 0) {
                    for (Entry entry2 : c4140g.m12780b().entrySet()) {
                        httpURLConnection3.setRequestProperty((String) entry2.getKey(), (String) entry2.getValue());
                    }
                }
                inputStream = httpURLConnection3.getInputStream();
                try {
                    if (httpURLConnection3.getResponseCode() != 200) {
                        throw new Exception(String.format(C4096a.bc, new Object[]{Integer.valueOf(httpURLConnection3.getResponseCode())}));
                    } else if (c4140g.m12783d()) {
                        inputStreamReader = new InputStreamReader(inputStream);
                        try {
                            bufferedReader = new BufferedReader(inputStreamReader);
                            try {
                                StringBuffer stringBuffer = new StringBuffer();
                                while (true) {
                                    String readLine = bufferedReader.readLine();
                                    if (readLine == null) {
                                        break;
                                    }
                                    stringBuffer.append(readLine);
                                }
                                r1 = stringBuffer.toString();
                                C4142i.m12788a(bufferedReader);
                                C4142i.m12788a(inputStreamReader);
                                C4142i.m12788a(inputStream);
                                if (httpURLConnection3 != null) {
                                    httpURLConnection3.disconnect();
                                }
                                return r1;
                            } catch (SSLException e) {
                                closeable = bufferedReader;
                                httpURLConnection = httpURLConnection3;
                                closeable2 = inputStreamReader;
                                inputStreamReader = inputStream;
                                try {
                                    if (C4145l.m12839e(c4140g.m12782c())) {
                                        m12790c(c4140g.m12776a(C4145l.m12842f(c4140g.m12782c())));
                                    }
                                    C4142i.m12788a(closeable);
                                    C4142i.m12788a(closeable2);
                                    C4142i.m12788a(inputStreamReader);
                                    if (httpURLConnection != null) {
                                        httpURLConnection.disconnect();
                                    }
                                    return null;
                                } catch (Throwable th3) {
                                    inputStream = inputStreamReader;
                                    Closeable closeable4 = closeable2;
                                    th = th3;
                                    closeable3 = closeable4;
                                    Closeable closeable5 = closeable;
                                    httpURLConnection2 = httpURLConnection;
                                    bufferedReader = closeable5;
                                    C4142i.m12788a(bufferedReader);
                                    C4142i.m12788a(closeable3);
                                    C4142i.m12788a(inputStream);
                                    if (httpURLConnection2 != null) {
                                        httpURLConnection2.disconnect();
                                    }
                                    throw th;
                                }
                            } catch (Throwable th4) {
                                closeable3 = inputStreamReader;
                                th2 = th4;
                                httpURLConnection2 = httpURLConnection3;
                                th = th2;
                                C4142i.m12788a(bufferedReader);
                                C4142i.m12788a(closeable3);
                                C4142i.m12788a(inputStream);
                                if (httpURLConnection2 != null) {
                                    httpURLConnection2.disconnect();
                                }
                                throw th;
                            }
                        } catch (SSLException e2) {
                            closeable = null;
                            httpURLConnection = httpURLConnection3;
                            closeable2 = inputStreamReader;
                            inputStreamReader = inputStream;
                            if (C4145l.m12839e(c4140g.m12782c())) {
                                m12790c(c4140g.m12776a(C4145l.m12842f(c4140g.m12782c())));
                            }
                            C4142i.m12788a(closeable);
                            C4142i.m12788a(closeable2);
                            C4142i.m12788a(inputStreamReader);
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            return null;
                        } catch (Throwable th42) {
                            bufferedReader = null;
                            closeable3 = inputStreamReader;
                            th2 = th42;
                            httpURLConnection2 = httpURLConnection3;
                            th = th2;
                            C4142i.m12788a(bufferedReader);
                            C4142i.m12788a(closeable3);
                            C4142i.m12788a(inputStream);
                            if (httpURLConnection2 != null) {
                                httpURLConnection2.disconnect();
                            }
                            throw th;
                        }
                    } else {
                        r1 = C4096a.bd;
                        C4142i.m12788a(null);
                        C4142i.m12788a(null);
                        C4142i.m12788a(inputStream);
                        if (httpURLConnection3 != null) {
                            httpURLConnection3.disconnect();
                        }
                        return r1;
                    }
                } catch (SSLException e3) {
                    closeable = null;
                    inputStreamReader = inputStream;
                    httpURLConnection = httpURLConnection3;
                    closeable2 = null;
                    if (C4145l.m12839e(c4140g.m12782c())) {
                        m12790c(c4140g.m12776a(C4145l.m12842f(c4140g.m12782c())));
                    }
                    C4142i.m12788a(closeable);
                    C4142i.m12788a(closeable2);
                    C4142i.m12788a(inputStreamReader);
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return null;
                } catch (Throwable th422) {
                    bufferedReader = null;
                    th2 = th422;
                    httpURLConnection2 = httpURLConnection3;
                    th = th2;
                    C4142i.m12788a(bufferedReader);
                    C4142i.m12788a(closeable3);
                    C4142i.m12788a(inputStream);
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    throw th;
                }
            } catch (SSLException e4) {
                closeable = null;
                inputStreamReader = null;
                httpURLConnection = httpURLConnection3;
                closeable2 = null;
                if (C4145l.m12839e(c4140g.m12782c())) {
                    m12790c(c4140g.m12776a(C4145l.m12842f(c4140g.m12782c())));
                }
                C4142i.m12788a(closeable);
                C4142i.m12788a(closeable2);
                C4142i.m12788a(inputStreamReader);
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return null;
            } catch (Throwable th4222) {
                bufferedReader = null;
                inputStream = null;
                th2 = th4222;
                httpURLConnection2 = httpURLConnection3;
                th = th2;
                C4142i.m12788a(bufferedReader);
                C4142i.m12788a(closeable3);
                C4142i.m12788a(inputStream);
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                throw th;
            }
        } catch (SSLException e5) {
            closeable2 = null;
            closeable = null;
            inputStreamReader = null;
            httpURLConnection = null;
        } catch (Throwable th5) {
            th = th5;
            bufferedReader = null;
            inputStream = null;
            httpURLConnection2 = null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String m12791d(com.unit.two.p151f.C4140g r12) {
        /*
        r11 = this;
        r7 = 1;
        r4 = 0;
        r5 = "";
        r1 = r12.m12785f();
        r0 = r12.m12786g();
        if (r1 == 0) goto L_0x00fc;
    L_0x000f:
        r2 = r1.size();
        if (r2 <= 0) goto L_0x00fc;
    L_0x0015:
        r2 = new android.net.Uri$Builder;
        r2.<init>();
        r0 = r1.entrySet();
        r3 = r0.iterator();
    L_0x0022:
        r0 = r3.hasNext();
        if (r0 == 0) goto L_0x0056;
    L_0x0028:
        r0 = r3.next();
        r0 = (java.util.Map.Entry) r0;
        r1 = r0.getKey();
        r1 = (java.lang.CharSequence) r1;
        r1 = android.text.TextUtils.isEmpty(r1);
        if (r1 != 0) goto L_0x0022;
    L_0x003a:
        r1 = r0.getKey();
        r1 = (java.lang.String) r1;
        r6 = r0.getValue();
        if (r6 != 0) goto L_0x004d;
    L_0x0046:
        r0 = "";
    L_0x0049:
        r2.appendQueryParameter(r1, r0);
        goto L_0x0022;
    L_0x004d:
        r0 = r0.getValue();
        r0 = r0.toString();
        goto L_0x0049;
    L_0x0056:
        r0 = r2.toString();
        r0 = r0.substring(r7);
        r3 = r0;
    L_0x005f:
        r0 = r12.m12782c();	 Catch:{ SSLException -> 0x020a, Throwable -> 0x01f2, all -> 0x01d5 }
        r0 = android.net.Uri.parse(r0);	 Catch:{ SSLException -> 0x020a, Throwable -> 0x01f2, all -> 0x01d5 }
        r1 = com.unit.two.p147c.C4096a.be;	 Catch:{ SSLException -> 0x020a, Throwable -> 0x01f2, all -> 0x01d5 }
        r6 = r0.getQueryParameter(r1);	 Catch:{ SSLException -> 0x020a, Throwable -> 0x01f2, all -> 0x01d5 }
        r0 = new java.net.URL;	 Catch:{ SSLException -> 0x020a, Throwable -> 0x01f2, all -> 0x01d5 }
        r1 = r12.m12782c();	 Catch:{ SSLException -> 0x020a, Throwable -> 0x01f2, all -> 0x01d5 }
        r0.<init>(r1);	 Catch:{ SSLException -> 0x020a, Throwable -> 0x01f2, all -> 0x01d5 }
        r0 = r0.openConnection();	 Catch:{ SSLException -> 0x020a, Throwable -> 0x01f2, all -> 0x01d5 }
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ SSLException -> 0x020a, Throwable -> 0x01f2, all -> 0x01d5 }
        r1 = com.unit.two.p147c.C4096a.bf;	 Catch:{ SSLException -> 0x00cf, Throwable -> 0x01f7, all -> 0x01da }
        r0.setRequestMethod(r1);	 Catch:{ SSLException -> 0x00cf, Throwable -> 0x01f7, all -> 0x01da }
        r1 = com.unit.two.p147c.C4096a.bg;	 Catch:{ SSLException -> 0x00cf, Throwable -> 0x01f7, all -> 0x01da }
        r2 = "*/*";
        r0.setRequestProperty(r1, r2);	 Catch:{ SSLException -> 0x00cf, Throwable -> 0x01f7, all -> 0x01da }
        r1 = com.unit.two.p147c.C4096a.bh;	 Catch:{ SSLException -> 0x00cf, Throwable -> 0x01f7, all -> 0x01da }
        r2 = com.unit.two.p147c.C4096a.bi;	 Catch:{ SSLException -> 0x00cf, Throwable -> 0x01f7, all -> 0x01da }
        r0.setRequestProperty(r1, r2);	 Catch:{ SSLException -> 0x00cf, Throwable -> 0x01f7, all -> 0x01da }
        r1 = com.unit.two.p147c.C4096a.bj;	 Catch:{ SSLException -> 0x00cf, Throwable -> 0x01f7, all -> 0x01da }
        r2 = com.unit.two.p147c.C4096a.bk;	 Catch:{ SSLException -> 0x00cf, Throwable -> 0x01f7, all -> 0x01da }
        r0.setRequestProperty(r1, r2);	 Catch:{ SSLException -> 0x00cf, Throwable -> 0x01f7, all -> 0x01da }
        r1 = r12.m12780b();	 Catch:{ SSLException -> 0x00cf, Throwable -> 0x01f7, all -> 0x01da }
        if (r1 == 0) goto L_0x00ff;
    L_0x009d:
        r1 = r12.m12780b();	 Catch:{ SSLException -> 0x00cf, Throwable -> 0x01f7, all -> 0x01da }
        r1 = r1.size();	 Catch:{ SSLException -> 0x00cf, Throwable -> 0x01f7, all -> 0x01da }
        if (r1 <= 0) goto L_0x00ff;
    L_0x00a7:
        r1 = r12.m12780b();	 Catch:{ SSLException -> 0x00cf, Throwable -> 0x01f7, all -> 0x01da }
        r1 = r1.entrySet();	 Catch:{ SSLException -> 0x00cf, Throwable -> 0x01f7, all -> 0x01da }
        r7 = r1.iterator();	 Catch:{ SSLException -> 0x00cf, Throwable -> 0x01f7, all -> 0x01da }
    L_0x00b3:
        r1 = r7.hasNext();	 Catch:{ SSLException -> 0x00cf, Throwable -> 0x01f7, all -> 0x01da }
        if (r1 == 0) goto L_0x00ff;
    L_0x00b9:
        r1 = r7.next();	 Catch:{ SSLException -> 0x00cf, Throwable -> 0x01f7, all -> 0x01da }
        r1 = (java.util.Map.Entry) r1;	 Catch:{ SSLException -> 0x00cf, Throwable -> 0x01f7, all -> 0x01da }
        r2 = r1.getKey();	 Catch:{ SSLException -> 0x00cf, Throwable -> 0x01f7, all -> 0x01da }
        r2 = (java.lang.String) r2;	 Catch:{ SSLException -> 0x00cf, Throwable -> 0x01f7, all -> 0x01da }
        r1 = r1.getValue();	 Catch:{ SSLException -> 0x00cf, Throwable -> 0x01f7, all -> 0x01da }
        r1 = (java.lang.String) r1;	 Catch:{ SSLException -> 0x00cf, Throwable -> 0x01f7, all -> 0x01da }
        r0.setRequestProperty(r2, r1);	 Catch:{ SSLException -> 0x00cf, Throwable -> 0x01f7, all -> 0x01da }
        goto L_0x00b3;
    L_0x00cf:
        r1 = move-exception;
        r1 = r5;
        r2 = r4;
        r3 = r4;
    L_0x00d3:
        r5 = r12.m12782c();	 Catch:{ all -> 0x01ed }
        r5 = com.unit.two.p151f.C4145l.m12839e(r5);	 Catch:{ all -> 0x01ed }
        if (r5 == 0) goto L_0x01c5;
    L_0x00dd:
        r1 = r12.m12782c();	 Catch:{ all -> 0x01ed }
        r1 = com.unit.two.p151f.C4145l.m12842f(r1);	 Catch:{ all -> 0x01ed }
        r1 = r12.m12776a(r1);	 Catch:{ all -> 0x01ed }
        r1 = r11.m12791d(r1);	 Catch:{ all -> 0x01ed }
        com.unit.two.p151f.C4142i.m12788a(r3);
        com.unit.two.p151f.C4142i.m12788a(r4);
        com.unit.two.p151f.C4142i.m12788a(r2);
        if (r0 == 0) goto L_0x00fb;
    L_0x00f8:
        r0.disconnect();
    L_0x00fb:
        return r1;
    L_0x00fc:
        r3 = r0;
        goto L_0x005f;
    L_0x00ff:
        r1 = 0;
        r0.setUseCaches(r1);	 Catch:{ SSLException -> 0x00cf, Throwable -> 0x01f7, all -> 0x01da }
        r1 = 1;
        r0.setDoOutput(r1);	 Catch:{ SSLException -> 0x00cf, Throwable -> 0x01f7, all -> 0x01da }
        r1 = 1;
        r0.setDoInput(r1);	 Catch:{ SSLException -> 0x00cf, Throwable -> 0x01f7, all -> 0x01da }
        r1 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        r0.setReadTimeout(r1);	 Catch:{ SSLException -> 0x00cf, Throwable -> 0x01f7, all -> 0x01da }
        r1 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r0.setConnectTimeout(r1);	 Catch:{ SSLException -> 0x00cf, Throwable -> 0x01f7, all -> 0x01da }
        if (r3 == 0) goto L_0x021f;
    L_0x0117:
        r1 = r3.trim();	 Catch:{ SSLException -> 0x00cf, Throwable -> 0x01f7, all -> 0x01da }
        r2 = "";
        r1 = r1.equals(r2);	 Catch:{ SSLException -> 0x00cf, Throwable -> 0x01f7, all -> 0x01da }
        if (r1 != 0) goto L_0x021f;
    L_0x0124:
        r1 = new java.io.PrintWriter;	 Catch:{ SSLException -> 0x00cf, Throwable -> 0x01f7, all -> 0x01da }
        r2 = r0.getOutputStream();	 Catch:{ SSLException -> 0x00cf, Throwable -> 0x01f7, all -> 0x01da }
        r1.<init>(r2);	 Catch:{ SSLException -> 0x00cf, Throwable -> 0x01f7, all -> 0x01da }
        r1.print(r3);	 Catch:{ SSLException -> 0x0211, Throwable -> 0x01fe, all -> 0x01e1 }
        r1.flush();	 Catch:{ SSLException -> 0x0211, Throwable -> 0x01fe, all -> 0x01e1 }
        r3 = r1;
    L_0x0134:
        r2 = r0.getInputStream();	 Catch:{ SSLException -> 0x0217, Throwable -> 0x0204, all -> 0x01e7 }
        r1 = r0.getResponseCode();	 Catch:{ SSLException -> 0x015a, Throwable -> 0x018a }
        r7 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r1 == r7) goto L_0x015e;
    L_0x0140:
        r1 = new java.lang.Exception;	 Catch:{ SSLException -> 0x015a, Throwable -> 0x018a }
        r6 = com.unit.two.p147c.C4096a.bl;	 Catch:{ SSLException -> 0x015a, Throwable -> 0x018a }
        r7 = 1;
        r7 = new java.lang.Object[r7];	 Catch:{ SSLException -> 0x015a, Throwable -> 0x018a }
        r8 = 0;
        r9 = r0.getResponseCode();	 Catch:{ SSLException -> 0x015a, Throwable -> 0x018a }
        r9 = java.lang.Integer.valueOf(r9);	 Catch:{ SSLException -> 0x015a, Throwable -> 0x018a }
        r7[r8] = r9;	 Catch:{ SSLException -> 0x015a, Throwable -> 0x018a }
        r6 = java.lang.String.format(r6, r7);	 Catch:{ SSLException -> 0x015a, Throwable -> 0x018a }
        r1.<init>(r6);	 Catch:{ SSLException -> 0x015a, Throwable -> 0x018a }
        throw r1;	 Catch:{ SSLException -> 0x015a, Throwable -> 0x018a }
    L_0x015a:
        r1 = move-exception;
        r1 = r5;
        goto L_0x00d3;
    L_0x015e:
        r1 = r12.m12783d();	 Catch:{ SSLException -> 0x015a, Throwable -> 0x018a }
        if (r1 != 0) goto L_0x0175;
    L_0x0164:
        r1 = com.unit.two.p147c.C4096a.bm;	 Catch:{ SSLException -> 0x015a, Throwable -> 0x018a }
        com.unit.two.p151f.C4142i.m12788a(r3);
        com.unit.two.p151f.C4142i.m12788a(r4);
        com.unit.two.p151f.C4142i.m12788a(r2);
        if (r0 == 0) goto L_0x00fb;
    L_0x0171:
        r0.disconnect();
        goto L_0x00fb;
    L_0x0175:
        r1 = 512; // 0x200 float:7.175E-43 double:2.53E-321;
        r1 = new byte[r1];	 Catch:{ SSLException -> 0x015a, Throwable -> 0x018a }
        r7 = new java.io.ByteArrayOutputStream;	 Catch:{ SSLException -> 0x015a, Throwable -> 0x018a }
        r7.<init>();	 Catch:{ SSLException -> 0x015a, Throwable -> 0x018a }
    L_0x017e:
        r8 = r2.read(r1);	 Catch:{ SSLException -> 0x015a, Throwable -> 0x018a }
        r9 = -1;
        if (r8 == r9) goto L_0x019f;
    L_0x0185:
        r9 = 0;
        r7.write(r1, r9, r8);	 Catch:{ SSLException -> 0x015a, Throwable -> 0x018a }
        goto L_0x017e;
    L_0x018a:
        r1 = move-exception;
        r10 = r1;
        r1 = r0;
        r0 = r10;
    L_0x018e:
        throw r0;	 Catch:{ all -> 0x018f }
    L_0x018f:
        r0 = move-exception;
    L_0x0190:
        com.unit.two.p151f.C4142i.m12788a(r3);
        com.unit.two.p151f.C4142i.m12788a(r4);
        com.unit.two.p151f.C4142i.m12788a(r2);
        if (r1 == 0) goto L_0x019e;
    L_0x019b:
        r1.disconnect();
    L_0x019e:
        throw r0;
    L_0x019f:
        r7.flush();	 Catch:{ SSLException -> 0x015a, Throwable -> 0x018a }
        r7.close();	 Catch:{ SSLException -> 0x015a, Throwable -> 0x018a }
        r1 = r7.toString();	 Catch:{ SSLException -> 0x015a, Throwable -> 0x018a }
        r5 = com.unit.two.p147c.C4096a.bn;	 Catch:{ SSLException -> 0x021c, Throwable -> 0x018a }
        r5 = r5.equals(r6);	 Catch:{ SSLException -> 0x021c, Throwable -> 0x018a }
        if (r5 == 0) goto L_0x01b5;
    L_0x01b1:
        r1 = com.unit.two.p151f.C4145l.m12829b(r1);	 Catch:{ SSLException -> 0x021c, Throwable -> 0x018a }
    L_0x01b5:
        com.unit.two.p151f.C4142i.m12788a(r3);
        com.unit.two.p151f.C4142i.m12788a(r4);
        com.unit.two.p151f.C4142i.m12788a(r2);
        if (r0 == 0) goto L_0x00fb;
    L_0x01c0:
        r0.disconnect();
        goto L_0x00fb;
    L_0x01c5:
        com.unit.two.p151f.C4142i.m12788a(r3);
        com.unit.two.p151f.C4142i.m12788a(r4);
        com.unit.two.p151f.C4142i.m12788a(r2);
        if (r0 == 0) goto L_0x00fb;
    L_0x01d0:
        r0.disconnect();
        goto L_0x00fb;
    L_0x01d5:
        r0 = move-exception;
        r1 = r4;
        r2 = r4;
        r3 = r4;
        goto L_0x0190;
    L_0x01da:
        r1 = move-exception;
        r2 = r4;
        r3 = r4;
        r10 = r1;
        r1 = r0;
        r0 = r10;
        goto L_0x0190;
    L_0x01e1:
        r2 = move-exception;
        r3 = r1;
        r1 = r0;
        r0 = r2;
        r2 = r4;
        goto L_0x0190;
    L_0x01e7:
        r1 = move-exception;
        r2 = r4;
        r10 = r0;
        r0 = r1;
        r1 = r10;
        goto L_0x0190;
    L_0x01ed:
        r1 = move-exception;
        r10 = r1;
        r1 = r0;
        r0 = r10;
        goto L_0x0190;
    L_0x01f2:
        r0 = move-exception;
        r1 = r4;
        r2 = r4;
        r3 = r4;
        goto L_0x018e;
    L_0x01f7:
        r1 = move-exception;
        r2 = r4;
        r3 = r4;
        r10 = r1;
        r1 = r0;
        r0 = r10;
        goto L_0x018e;
    L_0x01fe:
        r2 = move-exception;
        r3 = r1;
        r1 = r0;
        r0 = r2;
        r2 = r4;
        goto L_0x018e;
    L_0x0204:
        r1 = move-exception;
        r2 = r4;
        r10 = r0;
        r0 = r1;
        r1 = r10;
        goto L_0x018e;
    L_0x020a:
        r0 = move-exception;
        r0 = r4;
        r1 = r5;
        r2 = r4;
        r3 = r4;
        goto L_0x00d3;
    L_0x0211:
        r2 = move-exception;
        r2 = r4;
        r3 = r1;
        r1 = r5;
        goto L_0x00d3;
    L_0x0217:
        r1 = move-exception;
        r1 = r5;
        r2 = r4;
        goto L_0x00d3;
    L_0x021c:
        r5 = move-exception;
        goto L_0x00d3;
    L_0x021f:
        r3 = r4;
        goto L_0x0134;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unit.two.f.i.d(com.unit.two.f.g):java.lang.String");
    }
}
