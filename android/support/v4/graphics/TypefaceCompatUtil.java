package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.os.Process;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.util.Log;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

@RestrictTo({Scope.LIBRARY_GROUP})
public class TypefaceCompatUtil {
    private static final String CACHE_FILE_PREFIX = ".font";
    private static final String TAG = "TypefaceCompatUtil";

    private TypefaceCompatUtil() {
    }

    public static File getTempFile(Context context) {
        String prefix = CACHE_FILE_PREFIX + Process.myPid() + "-" + Process.myTid() + "-";
        int i = 0;
        while (i < 100) {
            File file = new File(context.getCacheDir(), prefix + i);
            try {
                if (file.createNewFile()) {
                    return file;
                }
                i++;
            } catch (IOException e) {
            }
        }
        return null;
    }

    @RequiresApi(19)
    private static ByteBuffer mmap(File file) {
        Throwable th;
        try {
            Throwable th2;
            FileInputStream fis = new FileInputStream(file);
            Throwable th3 = null;
            try {
                FileChannel channel = fis.getChannel();
                ByteBuffer map = channel.map(MapMode.READ_ONLY, 0, channel.size());
                if (fis == null) {
                    return map;
                }
                if (null != null) {
                    try {
                        fis.close();
                        return map;
                    } catch (Throwable th22) {
                        th3.addSuppressed(th22);
                        return map;
                    }
                }
                fis.close();
                return map;
            } catch (Throwable th222) {
                Throwable th4 = th222;
                th222 = th;
                th = th4;
            }
            if (fis != null) {
                if (th222 != null) {
                    try {
                        fis.close();
                    } catch (Throwable th5) {
                        th222.addSuppressed(th5);
                    }
                } else {
                    fis.close();
                }
            }
            throw th;
            throw th;
        } catch (IOException e) {
            return null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.support.annotation.RequiresApi(19)
    public static java.nio.ByteBuffer mmap(android.content.Context r13, android.os.CancellationSignal r14, android.net.Uri r15) {
        /*
        r9 = r13.getContentResolver();
        r1 = "r";
        r8 = r9.openFileDescriptor(r15, r1, r14);	 Catch:{ IOException -> 0x0048 }
        r11 = 0;
        r7 = new java.io.FileInputStream;	 Catch:{ Throwable -> 0x003a, all -> 0x004f }
        r1 = r8.getFileDescriptor();	 Catch:{ Throwable -> 0x003a, all -> 0x004f }
        r7.<init>(r1);	 Catch:{ Throwable -> 0x003a, all -> 0x004f }
        r10 = 0;
        r0 = r7.getChannel();	 Catch:{ Throwable -> 0x005b, all -> 0x007b }
        r4 = r0.size();	 Catch:{ Throwable -> 0x005b, all -> 0x007b }
        r1 = java.nio.channels.FileChannel.MapMode.READ_ONLY;	 Catch:{ Throwable -> 0x005b, all -> 0x007b }
        r2 = 0;
        r1 = r0.map(r1, r2, r4);	 Catch:{ Throwable -> 0x005b, all -> 0x007b }
        if (r7 == 0) goto L_0x002d;
    L_0x0028:
        if (r10 == 0) goto L_0x004b;
    L_0x002a:
        r7.close();	 Catch:{ Throwable -> 0x0035, all -> 0x004f }
    L_0x002d:
        if (r8 == 0) goto L_0x0034;
    L_0x002f:
        if (r11 == 0) goto L_0x0057;
    L_0x0031:
        r8.close();	 Catch:{ Throwable -> 0x0052 }
    L_0x0034:
        return r1;
    L_0x0035:
        r2 = move-exception;
        r10.addSuppressed(r2);	 Catch:{ Throwable -> 0x003a, all -> 0x004f }
        goto L_0x002d;
    L_0x003a:
        r1 = move-exception;
        throw r1;	 Catch:{ all -> 0x003c }
    L_0x003c:
        r2 = move-exception;
        r12 = r2;
        r2 = r1;
        r1 = r12;
    L_0x0040:
        if (r8 == 0) goto L_0x0047;
    L_0x0042:
        if (r2 == 0) goto L_0x0077;
    L_0x0044:
        r8.close();	 Catch:{ Throwable -> 0x0072 }
    L_0x0047:
        throw r1;	 Catch:{ IOException -> 0x0048 }
    L_0x0048:
        r6 = move-exception;
        r1 = 0;
        goto L_0x0034;
    L_0x004b:
        r7.close();	 Catch:{ Throwable -> 0x003a, all -> 0x004f }
        goto L_0x002d;
    L_0x004f:
        r1 = move-exception;
        r2 = r11;
        goto L_0x0040;
    L_0x0052:
        r2 = move-exception;
        r11.addSuppressed(r2);	 Catch:{ IOException -> 0x0048 }
        goto L_0x0034;
    L_0x0057:
        r8.close();	 Catch:{ IOException -> 0x0048 }
        goto L_0x0034;
    L_0x005b:
        r1 = move-exception;
        throw r1;	 Catch:{ all -> 0x005d }
    L_0x005d:
        r2 = move-exception;
        r12 = r2;
        r2 = r1;
        r1 = r12;
    L_0x0061:
        if (r7 == 0) goto L_0x0068;
    L_0x0063:
        if (r2 == 0) goto L_0x006e;
    L_0x0065:
        r7.close();	 Catch:{ Throwable -> 0x0069, all -> 0x004f }
    L_0x0068:
        throw r1;	 Catch:{ Throwable -> 0x003a, all -> 0x004f }
    L_0x0069:
        r3 = move-exception;
        r2.addSuppressed(r3);	 Catch:{ Throwable -> 0x003a, all -> 0x004f }
        goto L_0x0068;
    L_0x006e:
        r7.close();	 Catch:{ Throwable -> 0x003a, all -> 0x004f }
        goto L_0x0068;
    L_0x0072:
        r3 = move-exception;
        r2.addSuppressed(r3);	 Catch:{ IOException -> 0x0048 }
        goto L_0x0047;
    L_0x0077:
        r8.close();	 Catch:{ IOException -> 0x0048 }
        goto L_0x0047;
    L_0x007b:
        r1 = move-exception;
        r2 = r10;
        goto L_0x0061;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatUtil.mmap(android.content.Context, android.os.CancellationSignal, android.net.Uri):java.nio.ByteBuffer");
    }

    @RequiresApi(19)
    public static ByteBuffer copyToDirectBuffer(Context context, Resources res, int id) {
        ByteBuffer byteBuffer = null;
        File tmpFile = getTempFile(context);
        if (tmpFile != null) {
            try {
                if (copyToFile(tmpFile, res, id)) {
                    byteBuffer = mmap(tmpFile);
                    tmpFile.delete();
                }
            } finally {
                tmpFile.delete();
            }
        }
        return byteBuffer;
    }

    public static boolean copyToFile(File file, InputStream is) {
        IOException e;
        Throwable th;
        FileOutputStream fileOutputStream = null;
        try {
            FileOutputStream os = new FileOutputStream(file, false);
            try {
                byte[] buffer = new byte[1024];
                while (true) {
                    int readLen = is.read(buffer);
                    if (readLen != -1) {
                        os.write(buffer, 0, readLen);
                    } else {
                        closeQuietly(os);
                        fileOutputStream = os;
                        return true;
                    }
                }
            } catch (IOException e2) {
                e = e2;
                fileOutputStream = os;
                try {
                    Log.e(TAG, "Error copying resource contents to temp file: " + e.getMessage());
                    closeQuietly(fileOutputStream);
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    closeQuietly(fileOutputStream);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = os;
                closeQuietly(fileOutputStream);
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
            Log.e(TAG, "Error copying resource contents to temp file: " + e.getMessage());
            closeQuietly(fileOutputStream);
            return false;
        }
    }

    public static boolean copyToFile(File file, Resources res, int id) {
        InputStream inputStream = null;
        try {
            inputStream = res.openRawResource(id);
            boolean copyToFile = copyToFile(file, inputStream);
            return copyToFile;
        } finally {
            closeQuietly(inputStream);
        }
    }

    public static void closeQuietly(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException e) {
            }
        }
    }
}
