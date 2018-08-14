package com.yandex.metrica.impl;

import android.content.Context;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import java.io.File;
import java.io.FilenameFilter;
import java.util.concurrent.ExecutorService;

class NativeCrashesHelper {
    private String f11560a;
    private final Context f11561b;
    private boolean f11562c;
    private boolean f11563d;

    class C43021 implements FilenameFilter {
        C43021() {
        }

        public boolean accept(File dir, String filename) {
            return filename.endsWith(".dmp");
        }
    }

    private static class C4303a implements Runnable {
        private final ay f11558a;
        private final NativeCrashesHelper f11559b;

        C4303a(ay ayVar, NativeCrashesHelper nativeCrashesHelper) {
            this.f11559b = nativeCrashesHelper;
            this.f11558a = ayVar;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            r7 = this;
            r0 = r7.f11559b;
            r1 = r0.f11560a;
            r2 = com.yandex.metrica.impl.NativeCrashesHelper.m14442a(r1);
            r3 = r2.length;
            r0 = 0;
        L_0x000c:
            if (r0 >= r3) goto L_0x0056;
        L_0x000e:
            r4 = r2[r0];
            r5 = new java.lang.StringBuilder;
            r5.<init>();
            r5 = r5.append(r1);
            r6 = "/";
            r5 = r5.append(r6);
            r4 = r5.append(r4);
            r4 = r4.toString();
            r5 = com.yandex.metrica.impl.C4514r.m16217a(r4);	 Catch:{ Exception -> 0x0042, all -> 0x004c }
            r5 = com.yandex.metrica.impl.C4514r.m16221b(r5);	 Catch:{ Exception -> 0x0042, all -> 0x004c }
            if (r5 == 0) goto L_0x0037;
        L_0x0032:
            r6 = r7.f11558a;	 Catch:{ Exception -> 0x0042, all -> 0x004c }
            r6.m14751a(r5);	 Catch:{ Exception -> 0x0042, all -> 0x004c }
        L_0x0037:
            r5 = new java.io.File;
            r5.<init>(r4);
            r5.delete();
        L_0x003f:
            r0 = r0 + 1;
            goto L_0x000c;
        L_0x0042:
            r5 = move-exception;
            r5 = new java.io.File;
            r5.<init>(r4);
            r5.delete();
            goto L_0x003f;
        L_0x004c:
            r0 = move-exception;
            r1 = new java.io.File;
            r1.<init>(r4);
            r1.delete();
            throw r0;
        L_0x0056:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.NativeCrashesHelper.a.run():void");
        }
    }

    private static native void cancelSetUpNativeUncaughtExceptionHandler();

    private static native void logsEnabled(boolean z);

    private static native void setUpNativeUncaughtExceptionHandler(String str);

    static /* synthetic */ String[] m14442a(String str) {
        File file = new File(str + BridgeUtil.SPLIT_MARK);
        if (!file.mkdir() && !file.exists()) {
            return new String[0];
        }
        String[] list = file.list(new C43021());
        return list == null ? new String[0] : list;
    }

    NativeCrashesHelper(Context context) {
        this.f11561b = context;
    }

    synchronized void m14447a(boolean z) {
        if (z) {
            try {
                if (!this.f11563d && m14448a()) {
                    m14444b(false);
                    this.f11560a = this.f11561b.getFilesDir().getAbsolutePath() + "/YandexMetricaNativeCrashes";
                }
                this.f11563d = true;
                if (m14443b()) {
                    setUpNativeUncaughtExceptionHandler(this.f11560a);
                    this.f11562c = true;
                }
            } catch (Throwable th) {
                this.f11562c = false;
            }
        } else {
            try {
                if (m14445c()) {
                    cancelSetUpNativeUncaughtExceptionHandler();
                }
            } catch (Throwable th2) {
            }
            this.f11562c = false;
        }
    }

    synchronized void m14446a(ay ayVar, ExecutorService executorService) {
        if (m14445c()) {
            executorService.execute(new C4303a(ayVar, this));
            this.f11562c = false;
        }
    }

    private boolean m14443b() {
        return this.f11560a != null;
    }

    private boolean m14445c() {
        return m14443b() && this.f11562c;
    }

    private static boolean m14444b(boolean z) {
        try {
            logsEnabled(z);
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    boolean m14448a() {
        try {
            System.loadLibrary("YandexMetricaNativeModule");
            return true;
        } catch (Throwable th) {
            return false;
        }
    }
}
