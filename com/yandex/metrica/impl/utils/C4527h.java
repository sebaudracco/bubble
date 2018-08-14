package com.yandex.metrica.impl.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.ServiceInfo;
import com.yandex.metrica.MetricaService;

public class C4527h {

    public static final class C4526a implements Runnable {
        final Context f12580a;

        public C4526a(Context context) {
            this.f12580a = context;
        }

        public void run() {
            Context context = this.f12580a;
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 516);
                if (packageInfo.services != null) {
                    for (ServiceInfo serviceInfo : packageInfo.services) {
                        if (MetricaService.class.getName().equals(serviceInfo.name) && !serviceInfo.enabled) {
                            context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, MetricaService.class), 1, 1);
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
    }
}
