package com.oneaudience.sdk.p135c;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class C3828b {
    private static final String f9190a = C3828b.class.getSimpleName();

    private C3828b() {
    }

    public static String m12237a(File file) {
        return C3836g.m12265a(new FileInputStream(file));
    }

    public static void m12238a(InputStream inputStream, File file) {
        Closeable bufferedOutputStream;
        Throwable th;
        Closeable closeable = null;
        try {
            Closeable bufferedInputStream = new BufferedInputStream(inputStream);
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            } catch (Throwable th2) {
                th = th2;
                bufferedOutputStream = null;
                closeable = bufferedInputStream;
                C3836g.m12266a(closeable);
                C3836g.m12266a(bufferedOutputStream);
                throw th;
            }
            try {
                C3836g.m12267a(bufferedInputStream, bufferedOutputStream);
                C3836g.m12266a(bufferedInputStream);
                C3836g.m12266a(bufferedOutputStream);
            } catch (Throwable th3) {
                th = th3;
                closeable = bufferedInputStream;
                C3836g.m12266a(closeable);
                C3836g.m12266a(bufferedOutputStream);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedOutputStream = null;
            C3836g.m12266a(closeable);
            C3836g.m12266a(bufferedOutputStream);
            throw th;
        }
    }

    public static void m12239a(String str, File file) {
        C3828b.m12238a(new ByteArrayInputStream(str.getBytes()), file);
    }
}
