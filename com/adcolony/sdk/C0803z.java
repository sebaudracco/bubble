package com.adcolony.sdk;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import java.io.DataOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.zip.GZIPOutputStream;

class C0803z {
    URL f1510a;

    public C0803z(URL url) {
        this.f1510a = url;
    }

    public int m1484a(String str) throws IOException {
        DataOutputStream dataOutputStream;
        IOException iOException;
        Object obj;
        Throwable th;
        GZIPOutputStream gZIPOutputStream;
        FilterOutputStream filterOutputStream;
        HttpURLConnection httpURLConnection;
        DataOutputStream dataOutputStream2;
        Throwable th2;
        IOException iOException2;
        DataOutputStream dataOutputStream3 = null;
        Object obj2 = null;
        try {
            FilterOutputStream gZIPOutputStream2;
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) this.f1510a.openConnection();
            try {
                httpURLConnection2.setRequestMethod(HttpPost.METHOD_NAME);
                httpURLConnection2.setRequestProperty("Content-Encoding", AsyncHttpClient.ENCODING_GZIP);
                httpURLConnection2.setRequestProperty("Content-Type", RequestParams.APPLICATION_JSON);
                httpURLConnection2.setDoInput(true);
                gZIPOutputStream2 = new GZIPOutputStream(httpURLConnection2.getOutputStream());
            } catch (IOException e) {
                dataOutputStream = null;
                obj2 = httpURLConnection2;
                iOException = e;
                obj = null;
                try {
                    throw iOException;
                } catch (Throwable th3) {
                    th = th3;
                    gZIPOutputStream = filterOutputStream;
                    dataOutputStream3 = dataOutputStream;
                    Object obj3 = obj;
                    httpURLConnection = dataOutputStream2;
                    obj2 = obj3;
                }
            } catch (Throwable th4) {
                gZIPOutputStream = null;
                th2 = th4;
                httpURLConnection = httpURLConnection2;
                th = th2;
                dataOutputStream3.close();
                if (gZIPOutputStream != null) {
                    gZIPOutputStream.close();
                }
                if (httpURLConnection != null) {
                    if (httpURLConnection.getInputStream() != null) {
                        httpURLConnection.getInputStream().close();
                    }
                    httpURLConnection.disconnect();
                }
                throw th;
            }
            try {
                DataOutputStream dataOutputStream4 = new DataOutputStream(gZIPOutputStream2);
                try {
                    dataOutputStream4.write(str.getBytes(Charset.forName("UTF-8")));
                    dataOutputStream4.close();
                    int responseCode;
                    try {
                        responseCode = httpURLConnection2.getResponseCode();
                        if (dataOutputStream4 != null) {
                            if (gZIPOutputStream2 != null) {
                                gZIPOutputStream2.close();
                            }
                            if (httpURLConnection2 != null) {
                                if (httpURLConnection2.getInputStream() != null) {
                                    httpURLConnection2.getInputStream().close();
                                }
                                httpURLConnection2.disconnect();
                            }
                            return responseCode;
                        }
                        if (gZIPOutputStream2 != null) {
                            gZIPOutputStream2.close();
                        }
                        if (httpURLConnection2 != null) {
                            if (httpURLConnection2.getInputStream() != null) {
                                httpURLConnection2.getInputStream().close();
                            }
                            httpURLConnection2.disconnect();
                        }
                        return responseCode;
                    } catch (IOException e2) {
                        filterOutputStream = gZIPOutputStream2;
                        obj2 = httpURLConnection2;
                        iOException = e2;
                        responseCode = 1;
                        dataOutputStream = dataOutputStream4;
                        throw iOException;
                    } catch (Throwable th42) {
                        int i = 1;
                        dataOutputStream3 = dataOutputStream4;
                        th2 = th42;
                        httpURLConnection = httpURLConnection2;
                        th = th2;
                        dataOutputStream3.close();
                        if (gZIPOutputStream != null) {
                            gZIPOutputStream.close();
                        }
                        if (httpURLConnection != null) {
                            if (httpURLConnection.getInputStream() != null) {
                                httpURLConnection.getInputStream().close();
                            }
                            httpURLConnection.disconnect();
                        }
                        throw th;
                    }
                } catch (IOException e22) {
                    dataOutputStream = dataOutputStream4;
                    filterOutputStream = gZIPOutputStream2;
                    iOException2 = e22;
                    obj = null;
                    obj2 = httpURLConnection2;
                    iOException = iOException2;
                    throw iOException;
                } catch (Throwable th422) {
                    dataOutputStream3 = dataOutputStream4;
                    th2 = th422;
                    httpURLConnection = httpURLConnection2;
                    th = th2;
                    dataOutputStream3.close();
                    if (gZIPOutputStream != null) {
                        gZIPOutputStream.close();
                    }
                    if (httpURLConnection != null) {
                        if (httpURLConnection.getInputStream() != null) {
                            httpURLConnection.getInputStream().close();
                        }
                        httpURLConnection.disconnect();
                    }
                    throw th;
                }
            } catch (IOException e222) {
                dataOutputStream = null;
                filterOutputStream = gZIPOutputStream2;
                iOException2 = e222;
                obj = null;
                obj2 = httpURLConnection2;
                iOException = iOException2;
                throw iOException;
            } catch (Throwable th4222) {
                th2 = th4222;
                httpURLConnection = httpURLConnection2;
                th = th2;
                if (dataOutputStream3 != null && r3 == null) {
                    dataOutputStream3.close();
                }
                if (gZIPOutputStream != null) {
                    gZIPOutputStream.close();
                }
                if (httpURLConnection != null) {
                    if (httpURLConnection.getInputStream() != null) {
                        httpURLConnection.getInputStream().close();
                    }
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        } catch (IOException e3) {
            iOException = e3;
            obj = null;
            dataOutputStream = null;
            dataOutputStream2 = null;
            throw iOException;
        } catch (Throwable th5) {
            th = th5;
            gZIPOutputStream = null;
            httpURLConnection = null;
            dataOutputStream3.close();
            if (gZIPOutputStream != null) {
                gZIPOutputStream.close();
            }
            if (httpURLConnection != null) {
                if (httpURLConnection.getInputStream() != null) {
                    httpURLConnection.getInputStream().close();
                }
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }
}
