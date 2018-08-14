package com.vungle.publisher;

import com.vungle.publisher.ei.a;
import com.vungle.publisher.ei.b;
import com.vungle.publisher.log.Logger;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import rx.exceptions.Exceptions;
import rx.functions.Func2;

/* compiled from: vungle */
public class uu implements Func2<tw, gd<?>, gd<?>> {
    public /* synthetic */ Object call(Object obj, Object obj2) {
        return m4719a((tw) obj, (gd) obj2);
    }

    public gd<?> m4719a(tw twVar, gd<?> gdVar) {
        try {
            return m4720b(twVar, gdVar);
        } catch (Throwable e) {
            throw Exceptions.propagate(e);
        }
    }

    public gd<?> m4720b(tw twVar, gd<?> gdVar) throws IOException {
        OutputStream fileOutputStream;
        Throwable th;
        int i = 0;
        gd<?> gdVar2 = null;
        String i2 = gdVar.i();
        InputStream inputStream;
        try {
            HttpURLConnection a = twVar.m4686a();
            inputStream = a.getInputStream();
            try {
                File file = new File(i2);
                if (qr.d(file)) {
                    Logger.d(Logger.PREPARE_TAG, "downloading to: " + i2);
                    byte[] bArr = new byte[8192];
                    fileOutputStream = new FileOutputStream(file);
                    while (true) {
                        try {
                            int read = inputStream.read(bArr);
                            if (read <= 0) {
                                break;
                            }
                            i += read;
                            fileOutputStream.write(bArr, 0, read);
                        } catch (IOException e) {
                            throw new qn("could not write ad to disk");
                        } catch (Throwable th2) {
                            th = th2;
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (Throwable e2) {
                                    Logger.d(Logger.PREPARE_TAG, "error closing input stream", e2);
                                }
                            }
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (Throwable e3) {
                                    Logger.d(Logger.PREPARE_TAG, "error closing output stream", e3);
                                }
                            }
                            throw th;
                        }
                    }
                    fileOutputStream.flush();
                    int contentLength = a.getContentLength();
                    Logger.v(Logger.PREPARE_TAG, "response ContentLength = " + contentLength);
                    if (contentLength <= i) {
                        Logger.d(Logger.PREPARE_TAG, "download complete: " + i2 + ", size: " + i);
                        gdVar2 = m4718a(i, (gd) gdVar);
                    } else {
                        Logger.w(Logger.PREPARE_TAG, "download size mismatch: " + i2 + ", expected size: " + contentLength + ", actual size: " + i);
                        gdVar.a(a.d);
                        gdVar.f_();
                    }
                } else {
                    Logger.w(Logger.PREPARE_TAG, "could not create or directory not writeable: " + file);
                    fileOutputStream = null;
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable e22) {
                        Logger.d(Logger.PREPARE_TAG, "error closing input stream", e22);
                    }
                }
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable e32) {
                        Logger.d(Logger.PREPARE_TAG, "error closing output stream", e32);
                    }
                }
                return gdVar2;
            } catch (Throwable e322) {
                Throwable th3 = e322;
                fileOutputStream = null;
                th = th3;
                if (inputStream != null) {
                    inputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable e3222) {
            inputStream = null;
            th = e3222;
            Object obj = null;
            if (inputStream != null) {
                inputStream.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
    }

    gd<?> m4718a(int i, gd<?> gdVar) {
        b o = gdVar.o();
        switch (1.a[o.ordinal()]) {
            case 1:
            case 2:
            case 3:
                gdVar.a(Integer.valueOf(i));
                break;
        }
        Logger.d(Logger.PREPARE_TAG, o + " downloaded for " + "ad" + " " + gdVar.f());
        gdVar.a(a.b);
        gdVar.f_();
        return gdVar;
    }
}
