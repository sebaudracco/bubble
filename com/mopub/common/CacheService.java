package com.mopub.common;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.DiskLruCache.Editor;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.DeviceUtils;
import com.mopub.common.util.Streams;
import com.mopub.common.util.Utils;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CacheService {
    private static final int APP_VERSION = 1;
    private static final int DISK_CACHE_INDEX = 0;
    static final String UNIQUE_CACHE_NAME = "mopub-cache";
    private static final int VALUE_COUNT = 1;
    private static DiskLruCache sDiskLruCache;

    public static byte[] getFromDiskCache(java.lang.String r8) {
        /* JADX: method processing error */
/*
Error: java.util.NoSuchElementException
	at java.util.HashMap$HashIterator.nextNode(HashMap.java:1431)
	at java.util.HashMap$KeyIterator.next(HashMap.java:1453)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.applyRemove(BlockFinallyExtract.java:535)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.extractFinally(BlockFinallyExtract.java:175)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.processExceptionHandler(BlockFinallyExtract.java:79)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.visit(BlockFinallyExtract.java:51)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r5 = 0;
        r6 = sDiskLruCache;
        if (r6 != 0) goto L_0x0007;
    L_0x0005:
        r1 = r5;
    L_0x0006:
        return r1;
    L_0x0007:
        r1 = 0;
        r4 = 0;
        r6 = sDiskLruCache;	 Catch:{ all -> 0x003c, Exception -> 0x0041 }
        r7 = createValidDiskCacheKey(r8);	 Catch:{ all -> 0x003c, Exception -> 0x0041 }
        r4 = r6.get(r7);	 Catch:{ all -> 0x003c, Exception -> 0x0041 }
        if (r4 != 0) goto L_0x001c;
    L_0x0015:
        if (r4 == 0) goto L_0x001a;
    L_0x0017:
        r4.close();
    L_0x001a:
        r1 = r5;
        goto L_0x0006;
    L_0x001c:
        r5 = 0;
        r3 = r4.getInputStream(r5);	 Catch:{ all -> 0x003c, Exception -> 0x0041 }
        if (r3 == 0) goto L_0x0036;	 Catch:{ all -> 0x003c, Exception -> 0x0041 }
    L_0x0023:
        r5 = 0;	 Catch:{ all -> 0x003c, Exception -> 0x0041 }
        r6 = r4.getLength(r5);	 Catch:{ all -> 0x003c, Exception -> 0x0041 }
        r5 = (int) r6;	 Catch:{ all -> 0x003c, Exception -> 0x0041 }
        r1 = new byte[r5];	 Catch:{ all -> 0x003c, Exception -> 0x0041 }
        r0 = new java.io.BufferedInputStream;	 Catch:{ all -> 0x003c, Exception -> 0x0041 }
        r0.<init>(r3);	 Catch:{ all -> 0x003c, Exception -> 0x0041 }
        com.mopub.common.util.Streams.readStream(r0, r1);	 Catch:{ all -> 0x003c, Exception -> 0x0041 }
        com.mopub.common.util.Streams.closeStream(r0);	 Catch:{ all -> 0x003c, Exception -> 0x0041 }
    L_0x0036:
        if (r4 == 0) goto L_0x0006;
    L_0x0038:
        r4.close();
        goto L_0x0006;
    L_0x003c:
        r5 = move-exception;
        com.mopub.common.util.Streams.closeStream(r0);	 Catch:{ all -> 0x003c, Exception -> 0x0041 }
        throw r5;	 Catch:{ all -> 0x003c, Exception -> 0x0041 }
    L_0x0041:
        r2 = move-exception;
        r5 = "Unable to get from DiskLruCache";	 Catch:{ all -> 0x004e }
        com.mopub.common.logging.MoPubLog.d(r5, r2);	 Catch:{ all -> 0x004e }
        if (r4 == 0) goto L_0x0006;
    L_0x004a:
        r4.close();
        goto L_0x0006;
    L_0x004e:
        r5 = move-exception;
        if (r4 == 0) goto L_0x0054;
    L_0x0051:
        r4.close();
    L_0x0054:
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mopub.common.CacheService.getFromDiskCache(java.lang.String):byte[]");
    }

    public static boolean initializeDiskCache(Context context) {
        if (context == null) {
            return false;
        }
        if (sDiskLruCache == null) {
            File cacheDirectory = getDiskCacheDirectory(context);
            if (cacheDirectory == null) {
                return false;
            }
            try {
                sDiskLruCache = DiskLruCache.open(cacheDirectory, 1, 1, DeviceUtils.diskCacheSizeBytes(cacheDirectory));
            } catch (IOException e) {
                MoPubLog.d("Unable to create DiskLruCache", e);
                return false;
            }
        }
        return true;
    }

    public static void initialize(Context context) {
        initializeDiskCache(context);
    }

    public static String createValidDiskCacheKey(String key) {
        return Utils.sha1(key);
    }

    @Nullable
    public static File getDiskCacheDirectory(@NonNull Context context) {
        File cacheDir = context.getCacheDir();
        if (cacheDir == null) {
            return null;
        }
        return new File(cacheDir.getPath() + File.separator + UNIQUE_CACHE_NAME);
    }

    public static boolean containsKeyDiskCache(String key) {
        if (sDiskLruCache == null) {
            return false;
        }
        try {
            if (sDiskLruCache.get(createValidDiskCacheKey(key)) != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getFilePathDiskCache(String key) {
        if (sDiskLruCache == null) {
            return null;
        }
        return sDiskLruCache.getDirectory() + File.separator + createValidDiskCacheKey(key) + "." + 0;
    }

    public static void getFromDiskCacheAsync(String key, DiskLruCacheGetListener diskLruCacheGetListener) {
        new DiskLruCacheGetTask(key, diskLruCacheGetListener).execute(new Void[0]);
    }

    public static boolean putToDiskCache(String key, byte[] content) {
        return putToDiskCache(key, new ByteArrayInputStream(content));
    }

    public static boolean putToDiskCache(String key, InputStream content) {
        if (sDiskLruCache == null) {
            return false;
        }
        Editor editor = null;
        try {
            editor = sDiskLruCache.edit(createValidDiskCacheKey(key));
            if (editor == null) {
                return false;
            }
            OutputStream outputStream = new BufferedOutputStream(editor.newOutputStream(0));
            Streams.copyContent(content, outputStream);
            outputStream.flush();
            outputStream.close();
            sDiskLruCache.flush();
            editor.commit();
            return true;
        } catch (Exception e) {
            MoPubLog.d("Unable to put to DiskLruCache", e);
            if (editor == null) {
                return false;
            }
            try {
                editor.abort();
                return false;
            } catch (IOException e2) {
                return false;
            }
        }
    }

    public static void putToDiskCacheAsync(String key, byte[] content) {
        new DiskLruCachePutTask(key, content).execute(new Void[0]);
    }

    @Deprecated
    @VisibleForTesting
    public static void clearAndNullCaches() {
        if (sDiskLruCache != null) {
            try {
                sDiskLruCache.delete();
                sDiskLruCache = null;
            } catch (IOException e) {
                sDiskLruCache = null;
            }
        }
    }

    @Deprecated
    @VisibleForTesting
    public static DiskLruCache getDiskLruCache() {
        return sDiskLruCache;
    }
}
