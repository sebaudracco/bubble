package com.yandex.metrica.impl;

import android.content.Context;
import android.util.Base64;
import com.yandex.metrica.YandexMetrica;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public final class C4514r {
    public static String m16216a(InputStream inputStream) throws IOException {
        Reader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        Writer stringWriter = new StringWriter();
        C4514r.m16214a(inputStreamReader, stringWriter);
        return stringWriter.toString();
    }

    public static String m16217a(String str) throws IOException {
        Throwable th;
        Closeable fileInputStream;
        try {
            fileInputStream = new FileInputStream(str);
            try {
                String a = C4514r.m16216a((InputStream) fileInputStream);
                bk.m14980a(fileInputStream);
                return a;
            } catch (Throwable th2) {
                th = th2;
                bk.m14980a(fileInputStream);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
            bk.m14980a(fileInputStream);
            throw th;
        }
    }

    public static int m16214a(Reader reader, Writer writer) throws IOException {
        char[] cArr = new char[4096];
        int i = 0;
        while (true) {
            int read = reader.read(cArr, 0, 4096);
            if (-1 == read) {
                return i;
            }
            writer.write(cArr, 0, read);
            i += read;
        }
    }

    public static String m16221b(String str) {
        Closeable byteArrayOutputStream;
        Closeable gZIPOutputStream;
        Throwable th;
        Object obj;
        String str2 = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                try {
                    gZIPOutputStream.write(str.getBytes("UTF-8"));
                    gZIPOutputStream.finish();
                    str2 = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
                    bk.m14980a(gZIPOutputStream);
                    bk.m14980a(byteArrayOutputStream);
                } catch (Exception e) {
                    bk.m14980a(gZIPOutputStream);
                    bk.m14980a(byteArrayOutputStream);
                    return str2;
                } catch (Throwable th2) {
                    th = th2;
                    bk.m14980a(gZIPOutputStream);
                    bk.m14980a(byteArrayOutputStream);
                    throw th;
                }
            } catch (Exception e2) {
                obj = str2;
                bk.m14980a(gZIPOutputStream);
                bk.m14980a(byteArrayOutputStream);
                return str2;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                obj = str2;
                th = th4;
                bk.m14980a(gZIPOutputStream);
                bk.m14980a(byteArrayOutputStream);
                throw th;
            }
        } catch (Exception e3) {
            gZIPOutputStream = str2;
            byteArrayOutputStream = str2;
            bk.m14980a(gZIPOutputStream);
            bk.m14980a(byteArrayOutputStream);
            return str2;
        } catch (Throwable th32) {
            byteArrayOutputStream = str2;
            String str3 = str2;
            th = th32;
            gZIPOutputStream = str3;
            bk.m14980a(gZIPOutputStream);
            bk.m14980a(byteArrayOutputStream);
            throw th;
        }
        return str2;
    }

    public static String m16225c(String str) {
        Closeable byteArrayInputStream;
        Closeable gZIPInputStream;
        Object obj;
        Throwable th;
        String str2 = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(Base64.decode(str, 0));
            try {
                gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            } catch (Exception e) {
                obj = str2;
                bk.m14980a(byteArrayInputStream);
                bk.m14980a(gZIPInputStream);
                return str2;
            } catch (Throwable th2) {
                Throwable th3 = th2;
                obj = str2;
                th = th3;
                bk.m14980a(byteArrayInputStream);
                bk.m14980a(gZIPInputStream);
                throw th;
            }
            try {
                str2 = C4514r.m16216a((InputStream) gZIPInputStream);
                bk.m14980a(byteArrayInputStream);
                bk.m14980a(gZIPInputStream);
            } catch (Exception e2) {
                bk.m14980a(byteArrayInputStream);
                bk.m14980a(gZIPInputStream);
                return str2;
            } catch (Throwable th4) {
                th = th4;
                bk.m14980a(byteArrayInputStream);
                bk.m14980a(gZIPInputStream);
                throw th;
            }
        } catch (Exception e3) {
            gZIPInputStream = str2;
            byteArrayInputStream = str2;
            bk.m14980a(byteArrayInputStream);
            bk.m14980a(gZIPInputStream);
            return str2;
        } catch (Throwable th22) {
            byteArrayInputStream = str2;
            String str3 = str2;
            th = th22;
            gZIPInputStream = str3;
            bk.m14980a(byteArrayInputStream);
            bk.m14980a(gZIPInputStream);
            throw th;
        }
        return str2;
    }

    public static byte[] m16224b(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        byte[] bArr = new byte[8192];
        Closeable byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (-1 == read) {
                    break;
                } else if (read > 0) {
                    byteArrayOutputStream.write(bArr, 0, read);
                }
            } finally {
                bk.m14980a(byteArrayOutputStream);
            }
        }
        bArr = byteArrayOutputStream.toByteArray();
        return bArr;
    }

    public static String m16215a(Context context, File file) {
        byte[] b = C4514r.m16223b(context, file);
        try {
            return new String(b, "UTF-8");
        } catch (Throwable e) {
            Throwable th = e;
            String str = new String(b);
            YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180").reportError("read_share_file_with_unsupported_encoding", th);
            return str;
        }
    }

    public static byte[] m16223b(Context context, File file) {
        FileLock lock;
        FileLock fileLock;
        Closeable closeable;
        Throwable th;
        Throwable th2;
        byte[] bArr = null;
        Closeable randomAccessFile;
        try {
            randomAccessFile = new RandomAccessFile(file, "r");
            try {
                FileChannel channel = randomAccessFile.getChannel();
                lock = channel.lock(0, Long.MAX_VALUE, true);
                try {
                    ByteBuffer allocate = ByteBuffer.allocate((int) file.length());
                    channel.read(allocate);
                    allocate.flip();
                    bArr = allocate.array();
                    file.getAbsolutePath();
                    C4514r.m16220a(lock);
                    bk.m14980a(randomAccessFile);
                } catch (IOException e) {
                    fileLock = lock;
                    closeable = randomAccessFile;
                    file.getAbsolutePath();
                    C4514r.m16220a(fileLock);
                    bk.m14980a(closeable);
                    return bArr;
                } catch (SecurityException e2) {
                    file.getAbsolutePath();
                    C4514r.m16220a(lock);
                    bk.m14980a(randomAccessFile);
                    return bArr;
                } catch (Throwable th3) {
                    th = th3;
                    try {
                        YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180").reportError("error_during_file_reading", th);
                        file.getAbsolutePath();
                        C4514r.m16220a(lock);
                        bk.m14980a(randomAccessFile);
                        return bArr;
                    } catch (Throwable th4) {
                        th2 = th4;
                        file.getAbsolutePath();
                        C4514r.m16220a(lock);
                        bk.m14980a(randomAccessFile);
                        throw th2;
                    }
                }
            } catch (IOException e3) {
                fileLock = bArr;
                closeable = randomAccessFile;
                file.getAbsolutePath();
                C4514r.m16220a(fileLock);
                bk.m14980a(closeable);
                return bArr;
            } catch (SecurityException e4) {
                lock = bArr;
                file.getAbsolutePath();
                C4514r.m16220a(lock);
                bk.m14980a(randomAccessFile);
                return bArr;
            } catch (Throwable th5) {
                lock = bArr;
                th2 = th5;
                file.getAbsolutePath();
                C4514r.m16220a(lock);
                bk.m14980a(randomAccessFile);
                throw th2;
            }
        } catch (IOException e5) {
            fileLock = bArr;
            closeable = bArr;
            file.getAbsolutePath();
            C4514r.m16220a(fileLock);
            bk.m14980a(closeable);
            return bArr;
        } catch (SecurityException e6) {
            lock = bArr;
            randomAccessFile = bArr;
            file.getAbsolutePath();
            C4514r.m16220a(lock);
            bk.m14980a(randomAccessFile);
            return bArr;
        } catch (Throwable th52) {
            lock = bArr;
            randomAccessFile = bArr;
            th2 = th52;
            file.getAbsolutePath();
            C4514r.m16220a(lock);
            bk.m14980a(randomAccessFile);
            throw th2;
        }
        return bArr;
    }

    public static void m16220a(FileLock fileLock) {
        if (fileLock != null && fileLock.isValid()) {
            try {
                fileLock.release();
            } catch (IOException e) {
            }
        }
    }

    public static void m16222b(Context context, String str, String str2) {
        File file = new File(context.getNoBackupFilesDir(), str);
        try {
            C4514r.m16219a(str2, new FileOutputStream(file));
            C4514r.m16226c(context, file);
        } catch (FileNotFoundException e) {
        }
    }

    public static void m16226c(final Context context, final File file) {
        if (file.exists()) {
            file.setReadable(true, false);
            if (bk.m14985a(24)) {
                new File(context.getApplicationInfo().dataDir).setExecutable(true, false);
                return;
            }
            return;
        }
        YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("make_non_existed_world_readable", new HashMap<String, Object>() {
        });
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void m16219a(java.lang.String r5, java.io.FileOutputStream r6) {
        /*
        r0 = 0;
        r1 = r6.getChannel();	 Catch:{ IOException -> 0x0029, all -> 0x0031 }
        r0 = r1.lock();	 Catch:{ IOException -> 0x0029, all -> 0x0031 }
        r2 = "UTF-8";
        r2 = r5.getBytes(r2);	 Catch:{ IOException -> 0x0029, all -> 0x003c }
        r3 = r2.length;	 Catch:{ IOException -> 0x0029, all -> 0x003c }
        r3 = java.nio.ByteBuffer.allocate(r3);	 Catch:{ IOException -> 0x0029, all -> 0x003c }
        r3.put(r2);	 Catch:{ IOException -> 0x0029, all -> 0x003c }
        r3.flip();	 Catch:{ IOException -> 0x0029, all -> 0x003c }
        r1.write(r3);	 Catch:{ IOException -> 0x0029, all -> 0x003c }
        r2 = 1;
        r1.force(r2);	 Catch:{ IOException -> 0x0029, all -> 0x003c }
        com.yandex.metrica.impl.C4514r.m16220a(r0);
        com.yandex.metrica.impl.bk.m14980a(r6);
    L_0x0028:
        return;
    L_0x0029:
        r1 = move-exception;
        com.yandex.metrica.impl.C4514r.m16220a(r0);
        com.yandex.metrica.impl.bk.m14980a(r6);
        goto L_0x0028;
    L_0x0031:
        r1 = move-exception;
        r4 = r1;
        r1 = r0;
        r0 = r4;
    L_0x0035:
        com.yandex.metrica.impl.C4514r.m16220a(r1);
        com.yandex.metrica.impl.bk.m14980a(r6);
        throw r0;
    L_0x003c:
        r1 = move-exception;
        r4 = r1;
        r1 = r0;
        r0 = r4;
        goto L_0x0035;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.r.a(java.lang.String, java.io.FileOutputStream):void");
    }

    public static void m16218a(Context context, String str, String str2) {
        try {
            if (bk.m14985a(24)) {
                C4514r.m16219a(str2, context.openFileOutput(str, 0));
                C4514r.m16226c(context, context.getFileStreamPath(str));
                return;
            }
            C4514r.m16219a(str2, context.openFileOutput(str, 1));
        } catch (FileNotFoundException e) {
        }
    }
}
