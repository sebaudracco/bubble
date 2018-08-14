package com.vungle.publisher;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import com.cuebiq.cuebiqsdk.model.config.Settings;
import com.loopj.android.http.AsyncHttpClient;
import com.vungle.publisher.log.Logger;
import com.vungle.publisher.tr.b;
import com.vungle.publisher.ts.C1672a;
import com.vungle.publisher.tw.C1673a;
import cz.msebera.android.httpclient.HttpHeaders;
import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.HttpVersion;
import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

@Singleton
/* compiled from: vungle */
public class ue {
    @Inject
    uh f3380a;
    @Inject
    C1673a f3381b;
    @Inject
    C1672a f3382c;

    static {
        if (VERSION.SDK_INT < 8) {
            System.setProperty("http.keepAlive", SchemaSymbols.ATTVAL_FALSE);
        }
    }

    @Inject
    ue() {
    }

    public tw m4702a(tr trVar) {
        int i;
        long j;
        Throwable e;
        HttpURLConnection httpURLConnection;
        Throwable th;
        int i2 = -1;
        List arrayList = new ArrayList();
        try {
            long j2;
            HttpURLConnection httpURLConnection2;
            b a = trVar.a();
            String c = trVar.c();
            i = 0;
            j = -1;
            HttpURLConnection httpURLConnection3 = null;
            while (i <= 5) {
                try {
                    Logger.d(Logger.NETWORK_TAG, a + " " + c);
                    HttpURLConnection a2 = this.f3380a.m4710a(c);
                    try {
                        long j3;
                        m4704a(a2, a);
                        m4708b(a2, trVar);
                        m4705a(a2, trVar);
                        i2 = a2.getResponseCode();
                        Object headerField = a2.getHeaderField(HttpHeaders.RETRY_AFTER);
                        if (TextUtils.isEmpty(headerField)) {
                            j3 = j;
                        } else {
                            try {
                                j3 = Long.parseLong(headerField);
                            } catch (NumberFormatException e2) {
                                j3 = -1;
                            }
                        }
                        try {
                            if (m4707a(i, i2)) {
                                String headerField2 = a2.getHeaderField(HttpHeaders.LOCATION);
                                arrayList.add(this.f3382c.m4676a(c, i2, headerField2, a2.getHeaderFieldDate("Date", -1) == -1 ? null : Long.valueOf(a2.getHeaderFieldDate("Date", -1))));
                                if (m4709b(i2)) {
                                    Logger.d(Logger.NETWORK_TAG, m4703a(a, i, i2, a2.getContentType(), trVar.c(), c, null));
                                    j2 = j3;
                                    httpURLConnection2 = a2;
                                    break;
                                }
                                Logger.i(Logger.NETWORK_TAG, m4703a(a, i, i2, a2.getContentType(), trVar.c(), c, headerField2));
                                i++;
                                c = headerField2;
                                j = j3;
                                httpURLConnection3 = a2;
                            } else if (m4709b(i2)) {
                                Logger.d(Logger.NETWORK_TAG, m4703a(a, i, i2, a2.getContentType(), trVar.c(), c, null));
                                j2 = j3;
                                httpURLConnection2 = a2;
                            } else {
                                Logger.i(Logger.NETWORK_TAG, m4703a(a, i, i2, a2.getContentType(), trVar.c(), c, null));
                                j2 = j3;
                                httpURLConnection2 = a2;
                            }
                        } catch (MalformedURLException e3) {
                            e = e3;
                            j = j3;
                            httpURLConnection = a2;
                        } catch (ConnectException e4) {
                            e = e4;
                            j = j3;
                            httpURLConnection = a2;
                        } catch (SocketTimeoutException e5) {
                            e = e5;
                            j = j3;
                            httpURLConnection = a2;
                        } catch (IOException e6) {
                            e = e6;
                            j = j3;
                            httpURLConnection = a2;
                        }
                    } catch (MalformedURLException e7) {
                        e = e7;
                        httpURLConnection = a2;
                    } catch (ConnectException e8) {
                        e = e8;
                        httpURLConnection = a2;
                    } catch (SocketTimeoutException e9) {
                        e = e9;
                        httpURLConnection = a2;
                    } catch (IOException e10) {
                        e = e10;
                        httpURLConnection = a2;
                    }
                } catch (Throwable e11) {
                    th = e11;
                    httpURLConnection = httpURLConnection3;
                    e = th;
                } catch (Throwable e112) {
                    th = e112;
                    httpURLConnection = httpURLConnection3;
                    e = th;
                } catch (Throwable e1122) {
                    th = e1122;
                    httpURLConnection = httpURLConnection3;
                    e = th;
                } catch (Throwable e11222) {
                    th = e11222;
                    httpURLConnection = httpURLConnection3;
                    e = th;
                }
            }
            httpURLConnection2 = httpURLConnection3;
            j2 = j;
            j = j2;
            httpURLConnection = httpURLConnection2;
            i = i2;
        } catch (MalformedURLException e12) {
            e = e12;
            j = -1;
            httpURLConnection = null;
            Logger.w(Logger.NETWORK_TAG, zb.a(e));
            i = 601;
            return this.f3381b.m4681a(httpURLConnection, i, arrayList, j);
        } catch (ConnectException e13) {
            e = e13;
            j = -1;
            httpURLConnection = null;
            Logger.d(Logger.NETWORK_TAG, zb.a(e));
            i = 602;
            return this.f3381b.m4681a(httpURLConnection, i, arrayList, j);
        } catch (SocketTimeoutException e14) {
            e = e14;
            j = -1;
            httpURLConnection = null;
            Logger.d(Logger.NETWORK_TAG, zb.a(e));
            i = 603;
            return this.f3381b.m4681a(httpURLConnection, i, arrayList, j);
        } catch (IOException e15) {
            e = e15;
            j = -1;
            httpURLConnection = null;
            Logger.w(Logger.NETWORK_TAG, zb.a(e));
            i = Settings.MAX_DYNAMIC_ACQUISITION;
            return this.f3381b.m4681a(httpURLConnection, i, arrayList, j);
        }
        return this.f3381b.m4681a(httpURLConnection, i, arrayList, j);
    }

    void m4704a(HttpURLConnection httpURLConnection, b bVar) throws ProtocolException {
        httpURLConnection.setConnectTimeout(10000);
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setReadTimeout(10000);
        httpURLConnection.setUseCaches(false);
        if (bVar != null) {
            httpURLConnection.setRequestMethod(bVar.toString());
        }
    }

    void m4705a(HttpURLConnection httpURLConnection, tr trVar) throws IOException {
        String f = trVar.f();
        if (!TextUtils.isEmpty(f)) {
            Logger.d(Logger.NETWORK_TAG, "request body: " + f);
            byte[] bytes = f.getBytes();
            if (AsyncHttpClient.ENCODING_GZIP.equals(trVar.e())) {
                int length = bytes.length;
                bytes = zi.a(bytes);
                Logger.v(Logger.NETWORK_TAG, "gzipped request from " + length + " bytes down to " + bytes.length + " bytes");
            }
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setFixedLengthStreamingMode(bytes.length);
            httpURLConnection.getOutputStream().write(bytes);
        }
    }

    void m4708b(HttpURLConnection httpURLConnection, tr trVar) {
        Bundle d = trVar.d();
        if (d != null) {
            for (String str : d.keySet()) {
                if (d.get(str) instanceof String[]) {
                    for (String str2 : d.getStringArray(str)) {
                        Logger.v(Logger.NETWORK_TAG, "request header: " + str + ": " + str2);
                        httpURLConnection.addRequestProperty(str, str2);
                    }
                } else {
                    String valueOf = String.valueOf(d.get(str));
                    Logger.v(Logger.NETWORK_TAG, "request header: " + str + ": " + valueOf);
                    httpURLConnection.addRequestProperty(str, valueOf);
                }
            }
        }
    }

    boolean m4707a(int i, int i2) {
        return i > 0 || m4706a(i2);
    }

    boolean m4706a(int i) {
        return i == HttpStatus.SC_MOVED_PERMANENTLY || i == HttpStatus.SC_MOVED_TEMPORARILY;
    }

    boolean m4709b(int i) {
        return i == 200;
    }

    String m4703a(b bVar, int i, int i2, String str, String str2, String str3, String str4) {
        StringBuilder stringBuilder = new StringBuilder(HttpVersion.HTTP);
        boolean a = m4707a(i, i2);
        if (a) {
            stringBuilder.append(" redirect count ").append(i).append(',');
        }
        stringBuilder.append(" response code ").append(i2).append(", content-type ").append(str).append(" for ").append(bVar).append(" to");
        if (i > 0) {
            stringBuilder.append(" original URL ").append(str2).append(',');
        }
        stringBuilder.append(" requested URL ").append(str3);
        if (a) {
            stringBuilder.append(", next URL ").append(str4);
        }
        return stringBuilder.toString();
    }
}
