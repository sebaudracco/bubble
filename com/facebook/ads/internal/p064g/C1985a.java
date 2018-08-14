package com.facebook.ads.internal.p064g;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Environment;
import android.os.StatFs;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class C1985a {
    private static SensorManager f4630a = null;
    private static Sensor f4631b = null;
    private static Sensor f4632c = null;
    private static volatile float[] f4633d;
    private static volatile float[] f4634e;
    private static Map<String, String> f4635f = new ConcurrentHashMap();
    private static String[] f4636g = new String[]{"x", "y", "z"};
    private static SensorEventListener f4637h;
    private static SensorEventListener f4638i;

    private static class C1983a implements SensorEventListener {
        private C1983a() {
        }

        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            C1985a.f4633d = sensorEvent.values;
            C1985a.m6267d();
        }
    }

    private static class C1984b implements SensorEventListener {
        private C1984b() {
        }

        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            C1985a.f4634e = sensorEvent.values;
            C1985a.m6269e();
        }
    }

    public static Map<String, String> m6258a() {
        Map hashMap = new HashMap();
        hashMap.putAll(f4635f);
        C1985a.m6260a(hashMap);
        return hashMap;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void m6259a(android.content.Context r5) {
        /*
        r1 = com.facebook.ads.internal.p064g.C1985a.class;
        monitor-enter(r1);
        com.facebook.ads.internal.p064g.C1985a.m6263b(r5);	 Catch:{ all -> 0x0070 }
        com.facebook.ads.internal.p064g.C1985a.m6266c(r5);	 Catch:{ all -> 0x0070 }
        com.facebook.ads.internal.p064g.C1985a.m6268d(r5);	 Catch:{ all -> 0x0070 }
        r0 = f4630a;	 Catch:{ all -> 0x0070 }
        if (r0 != 0) goto L_0x0021;
    L_0x0010:
        r0 = "sensor";
        r0 = r5.getSystemService(r0);	 Catch:{ all -> 0x0070 }
        r0 = (android.hardware.SensorManager) r0;	 Catch:{ all -> 0x0070 }
        f4630a = r0;	 Catch:{ all -> 0x0070 }
        r0 = f4630a;	 Catch:{ all -> 0x0070 }
        if (r0 != 0) goto L_0x0021;
    L_0x001f:
        monitor-exit(r1);
        return;
    L_0x0021:
        r0 = f4631b;	 Catch:{ all -> 0x0070 }
        if (r0 != 0) goto L_0x002e;
    L_0x0025:
        r0 = f4630a;	 Catch:{ all -> 0x0070 }
        r2 = 1;
        r0 = r0.getDefaultSensor(r2);	 Catch:{ all -> 0x0070 }
        f4631b = r0;	 Catch:{ all -> 0x0070 }
    L_0x002e:
        r0 = f4632c;	 Catch:{ all -> 0x0070 }
        if (r0 != 0) goto L_0x003b;
    L_0x0032:
        r0 = f4630a;	 Catch:{ all -> 0x0070 }
        r2 = 4;
        r0 = r0.getDefaultSensor(r2);	 Catch:{ all -> 0x0070 }
        f4632c = r0;	 Catch:{ all -> 0x0070 }
    L_0x003b:
        r0 = f4637h;	 Catch:{ all -> 0x0070 }
        if (r0 != 0) goto L_0x0055;
    L_0x003f:
        r0 = new com.facebook.ads.internal.g.a$a;	 Catch:{ all -> 0x0070 }
        r2 = 0;
        r0.<init>();	 Catch:{ all -> 0x0070 }
        f4637h = r0;	 Catch:{ all -> 0x0070 }
        r0 = f4631b;	 Catch:{ all -> 0x0070 }
        if (r0 == 0) goto L_0x0055;
    L_0x004b:
        r0 = f4630a;	 Catch:{ all -> 0x0070 }
        r2 = f4637h;	 Catch:{ all -> 0x0070 }
        r3 = f4631b;	 Catch:{ all -> 0x0070 }
        r4 = 3;
        r0.registerListener(r2, r3, r4);	 Catch:{ all -> 0x0070 }
    L_0x0055:
        r0 = f4638i;	 Catch:{ all -> 0x0070 }
        if (r0 != 0) goto L_0x001f;
    L_0x0059:
        r0 = new com.facebook.ads.internal.g.a$b;	 Catch:{ all -> 0x0070 }
        r2 = 0;
        r0.<init>();	 Catch:{ all -> 0x0070 }
        f4638i = r0;	 Catch:{ all -> 0x0070 }
        r0 = f4632c;	 Catch:{ all -> 0x0070 }
        if (r0 == 0) goto L_0x001f;
    L_0x0065:
        r0 = f4630a;	 Catch:{ all -> 0x0070 }
        r2 = f4638i;	 Catch:{ all -> 0x0070 }
        r3 = f4632c;	 Catch:{ all -> 0x0070 }
        r4 = 3;
        r0.registerListener(r2, r3, r4);	 Catch:{ all -> 0x0070 }
        goto L_0x001f;
    L_0x0070:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.g.a.a(android.content.Context):void");
    }

    private static void m6260a(Map<String, String> map) {
        int i;
        int i2 = 0;
        float[] fArr = f4633d;
        float[] fArr2 = f4634e;
        if (fArr != null) {
            int min = Math.min(f4636g.length, fArr.length);
            for (i = 0; i < min; i++) {
                map.put("accelerometer_" + f4636g[i], String.valueOf(fArr[i]));
            }
        }
        if (fArr2 != null) {
            i = Math.min(f4636g.length, fArr2.length);
            while (i2 < i) {
                map.put("rotation_" + f4636g[i2], String.valueOf(fArr2[i2]));
                i2++;
            }
        }
    }

    private static void m6263b(Context context) {
        MemoryInfo memoryInfo = new MemoryInfo();
        ((ActivityManager) context.getSystemService(C1404b.aw)).getMemoryInfo(memoryInfo);
        f4635f.put("available_memory", String.valueOf(memoryInfo.availMem));
    }

    private static void m6266c(Context context) {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        long availableBlocks = (long) statFs.getAvailableBlocks();
        f4635f.put("free_space", String.valueOf(availableBlocks * ((long) statFs.getBlockSize())));
    }

    private static synchronized void m6267d() {
        synchronized (C1985a.class) {
            if (f4630a != null) {
                f4630a.unregisterListener(f4637h);
            }
            f4637h = null;
        }
    }

    private static void m6268d(Context context) {
        Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (registerReceiver != null) {
            int intExtra = registerReceiver.getIntExtra(FirebaseAnalytics$Param.LEVEL, -1);
            int intExtra2 = registerReceiver.getIntExtra("scale", -1);
            int intExtra3 = registerReceiver.getIntExtra("status", -1);
            Object obj = (intExtra3 == 2 || intExtra3 == 5) ? 1 : null;
            float f = 0.0f;
            if (intExtra2 > 0) {
                f = (((float) intExtra) / ((float) intExtra2)) * 100.0f;
            }
            f4635f.put("battery", String.valueOf(f));
            f4635f.put("charging", obj != null ? SchemaSymbols.ATTVAL_TRUE_1 : SchemaSymbols.ATTVAL_FALSE_0);
        }
    }

    private static synchronized void m6269e() {
        synchronized (C1985a.class) {
            if (f4630a != null) {
                f4630a.unregisterListener(f4638i);
            }
            f4638i = null;
        }
    }
}
