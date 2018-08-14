package com.yandex.metrica.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import com.yandex.metrica.MetricaEventHandler;
import com.yandex.metrica.MetricaService;
import com.yandex.metrica.YandexMetrica;
import java.util.List;

public class C4539v {

    static class C4536a extends RuntimeException {
        public C4536a(String str) {
            super(str);
        }

        public C4536a(String str, String str2) {
            super("\nPlease check " + str + " in AndroidManifest file.\n" + str2);
        }
    }

    static class C4537b extends C4536a {
        public C4537b(String str, String str2) {
            super(str, "It should not include intent-filter with action " + str2 + "\n");
        }
    }

    static class C4538c extends C4536a {
        public C4538c(String str) {
            super(str, "Attribute metrica:api:level should be equal to " + YandexMetrica.getLibraryApiLevel() + ".\n");
        }
    }

    public static boolean m16298a(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    private static boolean m16297a(Context context, Intent intent) {
        List<ResolveInfo> queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 0);
        String name = MetricaEventHandler.class.getName();
        for (ResolveInfo resolveInfo : queryBroadcastReceivers) {
            if (resolveInfo.activityInfo.packageName.equals(context.getPackageName()) && resolveInfo.activityInfo.name.equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static void m16296a(Context context) {
        Object obj = 1;
        Object obj2 = null;
        if (C4539v.m16298a("com.yandex.metrica.CounterConfiguration")) {
            Object obj3;
            if ((context.getApplicationInfo().flags & 2) != 0) {
                obj3 = 1;
            } else {
                obj3 = null;
            }
            if (obj3 != null) {
                try {
                    Bundle bundle = context.getPackageManager().getServiceInfo(new ComponentName(context, MetricaService.class), 640).metaData;
                    if (bundle != null && bundle.containsKey("metrica:api:level")) {
                        if (bundle.getInt("metrica:api:level") != YandexMetrica.getLibraryApiLevel()) {
                            obj = null;
                        }
                        obj2 = obj;
                    }
                } catch (Exception e) {
                }
                if (obj2 == null) {
                    throw new C4538c(MetricaService.class.getName());
                }
                String str = "com.yandex.metrica.intent.action.SYNC";
                String str2 = MetricaEventHandler.class.getName() + " receiver";
                Intent intent = new Intent(str);
                Intent intent2 = new Intent(null, Uri.parse("package://fake.data"));
                if (C4539v.m16297a(context, intent)) {
                    throw new C4537b(str2, str);
                } else if (C4539v.m16297a(context, intent2.setAction("android.intent.action.PACKAGE_DATA_CLEARED"))) {
                    throw new C4537b(str2, "android.intent.action.PACKAGE_DATA_CLEARED");
                } else if (C4539v.m16297a(context, intent2.setAction("android.intent.action.PACKAGE_ADDED"))) {
                    throw new C4537b(str2, "android.intent.action.PACKAGE_ADDED");
                } else {
                    return;
                }
            }
            return;
        }
        throw new C4536a("\nClass com.yandex.metrica.CounterConfiguration isn't found.\nPerhaps this is due to obfuscation.\nIf you build your application with ProGuard,\nyou need to keep the Metrica for Apps.\nPlease try to use the following lines of code:\n##########################################\n-keep class com.yandex.metrica.** { *; }\n-dontwarn com.yandex.metrica.**\n##########################################");
    }
}
