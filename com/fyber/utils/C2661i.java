package com.fyber.utils;

import cz.msebera.android.httpclient.HttpStatus;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.List;

/* compiled from: HttpConnectionFiles */
public final class C2661i extends C2642b<C2661i, C2660a> {
    private final File f6621h;
    private boolean f6622i = false;

    /* compiled from: HttpConnectionFiles */
    public class C2660a {
        final /* synthetic */ C2661i f6616a;
        private boolean f6617b = false;
        private InputStream f6618c = null;
        private final HttpURLConnection f6619d;
        private boolean f6620e = true;

        /* compiled from: HttpConnectionFiles */
        public class C2658a extends Throwable {
            final /* synthetic */ C2660a f6614a;

            public C2658a(C2660a c2660a) {
                this.f6614a = c2660a;
            }
        }

        /* compiled from: HttpConnectionFiles */
        public class C2659b extends Throwable {
            final /* synthetic */ C2660a f6615a;

            public C2659b(C2660a c2660a) {
                this.f6615a = c2660a;
            }
        }

        public C2660a(C2661i c2661i, HttpURLConnection httpURLConnection) {
            this.f6616a = c2661i;
            this.f6619d = httpURLConnection;
            try {
                this.f6618c = c2661i.mo4006c(httpURLConnection);
                if (this.f6618c != null) {
                    this.f6617b = true;
                    return;
                }
                FyberLogger.m8451i("HttpConnectionFiles", "There was an error, the file will not be saved locally");
                C2642b.m8456b(httpURLConnection);
            } catch (IOException e) {
                FyberLogger.m8448d("HttpConnectionFiles", "An error occurred. Aborting file save operation");
            }
        }

        public final boolean m8508a() throws C2658a, C2659b {
            Exception e;
            HttpURLConnection httpURLConnection = null;
            if (!this.f6617b) {
                throw new C2659b(this);
            } else if (this.f6616a.f6621h == null) {
                return false;
            } else {
                try {
                    boolean z;
                    FileOutputStream fileOutputStream;
                    byte[] bArr;
                    int read;
                    List a = this.f6616a.m8463a("Content-Length");
                    if (!(a == null || a.isEmpty())) {
                        if (this.f6616a.f6621h.getParentFile().getUsableSpace() < Long.parseLong((String) a.get(0))) {
                            throw new C2658a(this);
                        }
                    }
                    FyberLogger.m8451i("HttpConnectionFiles", "Download: " + this.f6616a.a.toExternalForm() + " to local file: " + this.f6616a.f6621h.getAbsolutePath());
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(this.f6618c, 1024);
                    if (this.f6616a.f6622i) {
                        HttpURLConnection httpURLConnection2;
                        if (this.f6616a.c == HttpStatus.SC_PARTIAL_CONTENT || this.f6616a.d.containsKey("Content-Range")) {
                            httpURLConnection2 = 1;
                        } else {
                            httpURLConnection2 = null;
                        }
                        if (httpURLConnection2 != null) {
                            z = true;
                            fileOutputStream = new FileOutputStream(this.f6616a.f6621h, z);
                            bArr = new byte[1024];
                            while (true) {
                                read = bufferedInputStream.read(bArr);
                                if (read <= 0 || !this.f6620e) {
                                    fileOutputStream.flush();
                                    fileOutputStream.close();
                                    bufferedInputStream.close();
                                    z = this.f6620e;
                                } else {
                                    fileOutputStream.write(bArr, 0, read);
                                }
                            }
                            fileOutputStream.flush();
                            fileOutputStream.close();
                            bufferedInputStream.close();
                            z = this.f6620e;
                            C2642b.m8456b(this.f6619d);
                            return z;
                        }
                    }
                    z = false;
                    fileOutputStream = new FileOutputStream(this.f6616a.f6621h, z);
                    bArr = new byte[1024];
                    while (true) {
                        read = bufferedInputStream.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        break;
                        fileOutputStream.write(bArr, 0, read);
                    }
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    bufferedInputStream.close();
                    z = this.f6620e;
                    C2642b.m8456b(this.f6619d);
                    return z;
                } catch (IOException e2) {
                    e = e2;
                    try {
                        FyberLogger.m8451i("HttpConnectionFiles", "File downloading from URL: " + this.f6616a.a + " has been interrupted.");
                        FyberLogger.m8449e("HttpConnectionFiles", "An error occurred while downloading: " + e.getMessage());
                        return httpURLConnection;
                    } finally {
                        httpURLConnection = this.f6619d;
                        C2642b.m8456b(httpURLConnection);
                    }
                } catch (IllegalStateException e3) {
                    e = e3;
                    FyberLogger.m8451i("HttpConnectionFiles", "File downloading from URL: " + this.f6616a.a + " has been interrupted.");
                    FyberLogger.m8449e("HttpConnectionFiles", "An error occurred while downloading: " + e.getMessage());
                    return httpURLConnection;
                }
            }
        }

        public final void m8509b() {
            this.f6620e = false;
        }
    }

    public final /* synthetic */ C2642b mo4005a() throws IOException {
        return mo4007e();
    }

    private C2661i(String str, File file) throws MalformedURLException, IllegalArgumentException {
        super(str);
        if (file == null) {
            throw new IllegalArgumentException("The localFile parameter is required");
        }
        this.f6621h = file;
        this.f = false;
        this.g = false;
    }

    public static C2661i m8510a(String str, File file) throws MalformedURLException, IllegalArgumentException {
        return new C2661i(str, file);
    }

    public final C2661i m8514a(boolean z) {
        this.f6622i = z;
        return this;
    }

    public final C2661i mo4007e() throws IOException {
        if (this.f6622i) {
            m8460a("Range", "bytes=" + this.f6621h.length() + "-");
        }
        return (C2661i) super.mo4005a();
    }

    protected final InputStream mo4006c(HttpURLConnection httpURLConnection) throws IOException {
        return this.c < HttpStatus.SC_BAD_REQUEST ? httpURLConnection.getInputStream() : null;
    }

    protected final /* synthetic */ Object mo4004a(HttpURLConnection httpURLConnection) throws IOException {
        return new C2660a(this, httpURLConnection);
    }
}
