package com.google.android.gms.internal.ads;

import java.lang.Thread.UncaughtExceptionHandler;

final class zzadc implements UncaughtExceptionHandler {
    private final /* synthetic */ UncaughtExceptionHandler zzcca;
    private final /* synthetic */ zzadb zzccb;

    zzadc(zzadb com_google_android_gms_internal_ads_zzadb, UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.zzccb = com_google_android_gms_internal_ads_zzadb;
        this.zzcca = uncaughtExceptionHandler;
    }

    public final void uncaughtException(java.lang.Thread r3, java.lang.Throwable r4) {
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
        r2 = this;
        r0 = r2.zzccb;	 Catch:{ Throwable -> 0x000f, all -> 0x0020 }
        r0.zza(r3, r4);	 Catch:{ Throwable -> 0x000f, all -> 0x0020 }
        r0 = r2.zzcca;
        if (r0 == 0) goto L_0x000e;
    L_0x0009:
        r0 = r2.zzcca;
        r0.uncaughtException(r3, r4);
    L_0x000e:
        return;
    L_0x000f:
        r0 = move-exception;
        r0 = "AdMob exception reporter failed reporting the exception.";	 Catch:{ Throwable -> 0x000f, all -> 0x0020 }
        com.google.android.gms.internal.ads.zzane.e(r0);	 Catch:{ Throwable -> 0x000f, all -> 0x0020 }
        r0 = r2.zzcca;
        if (r0 == 0) goto L_0x000e;
    L_0x001a:
        r0 = r2.zzcca;
        r0.uncaughtException(r3, r4);
        goto L_0x000e;
    L_0x0020:
        r0 = move-exception;
        r1 = r2.zzcca;
        if (r1 == 0) goto L_0x002a;
    L_0x0025:
        r1 = r2.zzcca;
        r1.uncaughtException(r3, r4);
    L_0x002a:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzadc.uncaughtException(java.lang.Thread, java.lang.Throwable):void");
    }
}
