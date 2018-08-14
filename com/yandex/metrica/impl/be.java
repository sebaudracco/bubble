package com.yandex.metrica.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.net.Uri;
import android.os.Bundle;
import com.yandex.metrica.IMetricaService;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class be {

    public static class C4359a implements Comparable<C4359a> {
        public final int f11774a;
        public final int f11775b;
        public final long f11776c;
        public final ServiceInfo f11777d;
        public final String f11778e;

        public /* synthetic */ int compareTo(Object obj) {
            return m14885a((C4359a) obj);
        }

        public C4359a(ServiceInfo serviceInfo, int i, int i2, long j) {
            this.f11774a = i2;
            this.f11775b = i;
            this.f11777d = serviceInfo;
            this.f11776c = j;
            this.f11778e = serviceInfo.applicationInfo.packageName;
        }

        public int m14885a(C4359a c4359a) {
            if (this.f11775b != c4359a.f11775b) {
                return Integer.valueOf(this.f11775b).compareTo(Integer.valueOf(c4359a.f11775b));
            }
            if (this.f11776c != c4359a.f11776c) {
                return Long.valueOf(this.f11776c).compareTo(Long.valueOf(c4359a.f11776c));
            }
            return 0;
        }

        public String toString() {
            return "MetricaServiceDescriptor{apiLevel=" + this.f11774a + ", score=" + this.f11775b + ", timeInstalled=" + this.f11776c + '}';
        }
    }

    public static Intent m14888a(Context context) {
        Intent intent = new Intent(IMetricaService.class.getName(), Uri.parse("metrica://" + context.getPackageName()));
        if (bk.m14991b(11)) {
            intent.addFlags(32);
        }
        return intent;
    }

    public static List<ResolveInfo> m14889a(Context context, Intent intent) {
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 128);
        return queryIntentServices != null ? queryIntentServices : new ArrayList();
    }

    public static List<C4359a> m14891b(Context context) {
        PackageManager packageManager = context.getPackageManager();
        List<C4359a> arrayList = new ArrayList();
        for (ResolveInfo resolveInfo : m14889a(context, m14888a(context))) {
            int i;
            PackageItemInfo packageItemInfo = resolveInfo.serviceInfo;
            int i2 = !packageItemInfo.enabled ? 1 : 0;
            if (packageItemInfo.exported) {
                i = 0;
            } else {
                i = 1;
            }
            i |= i2;
            if (bi.m14957a(packageItemInfo.permission)) {
                i2 = 0;
            } else {
                i2 = 1;
            }
            if ((i2 | i) == 0) {
                long a = m14887a(packageManager, packageItemInfo.packageName);
                if (m14890a(packageManager, packageItemInfo.packageName, "android.permission.INTERNET")) {
                    int a2 = m14886a(packageItemInfo);
                    i = (a2 << 5) + ((m14890a(packageManager, packageItemInfo.packageName, "android.permission.ACCESS_COARSE_LOCATION") ? 1 : 0) * 16);
                    if (m14890a(packageManager, packageItemInfo.packageName, "android.permission.ACCESS_FINE_LOCATION")) {
                        i2 = 1;
                    } else {
                        i2 = 0;
                    }
                    i += i2 * 8;
                    if (m14890a(packageManager, packageItemInfo.packageName, "android.permission.ACCESS_WIFI_STATE")) {
                        i2 = 1;
                    } else {
                        i2 = 0;
                    }
                    i += i2 * 4;
                    if (m14890a(packageManager, packageItemInfo.packageName, "android.permission.ACCESS_NETWORK_STATE")) {
                        i2 = 1;
                    } else {
                        i2 = 0;
                    }
                    i += i2 * 2;
                    if (m14890a(packageManager, packageItemInfo.packageName, "android.permission.READ_PHONE_STATE")) {
                        i2 = 1;
                    } else {
                        i2 = 0;
                    }
                    arrayList.add(new C4359a(resolveInfo.serviceInfo, i + (i2 * 1), a2, a));
                }
            }
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    public static long m14887a(PackageManager packageManager, String str) {
        long max;
        long j = -1;
        try {
            if (bk.m14991b(8)) {
                PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
                max = Math.max(packageInfo.firstInstallTime, packageInfo.lastUpdateTime);
            } else {
                max = -1;
            }
        } catch (Exception e) {
            max = -1;
        }
        try {
            File file = new File(packageManager.getApplicationInfo(str, 0).sourceDir);
            if (file.exists()) {
                j = file.lastModified();
            }
        } catch (Exception e2) {
        }
        return Math.max(max, j);
    }

    private static boolean m14890a(PackageManager packageManager, String str, String str2) {
        return str2 == null || packageManager.checkPermission(str2, str) == 0;
    }

    public static int m14886a(PackageItemInfo packageItemInfo) {
        int i = -1;
        if (packageItemInfo.metaData != null) {
            try {
                i = packageItemInfo.metaData.getInt("metrica:api:level");
            } catch (Exception e) {
            }
        }
        return i;
    }

    public static Intent m14892c(Context context) {
        return m14888a(context).putExtras(m14894e(context)).setPackage(context.getApplicationContext().getPackageName());
    }

    private static Bundle m14894e(Context context) {
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle == null) {
                return new Bundle();
            }
            return bundle;
        } catch (Exception e) {
            return new Bundle();
        }
    }

    public static String m14893d(Context context) {
        List b = m14891b(context);
        ServiceInfo serviceInfo = ((C4359a) b.get(b.size() - 1)).f11777d;
        return new ComponentName(serviceInfo.packageName, serviceInfo.name).getPackageName();
    }
}
