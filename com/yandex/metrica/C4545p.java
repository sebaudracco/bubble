package com.yandex.metrica;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import com.yandex.metrica.impl.C4543y;
import com.yandex.metrica.impl.aq;
import com.yandex.metrica.impl.bc;
import com.yandex.metrica.impl.be;
import com.yandex.metrica.impl.bn;
import com.yandex.metrica.impl.bo;
import com.yandex.metrica.impl.interact.CellularNetworkInfo;
import com.yandex.metrica.impl.interact.DeviceInfo;
import java.util.Map;

public final class C4545p {
    public static String m16337u(String sdkName) {
        return bc.m14874a(sdkName);
    }

    public static Boolean plat() {
        return bn.m15004c();
    }

    public static boolean iifa() {
        return bn.m15002a();
    }

    public static String pgai() {
        return bn.m15003b();
    }

    public static String gmsvn(int apiLevel) {
        return aq.m14650a(apiLevel);
    }

    public static YandexMetricaConfig cpcwh(YandexMetricaConfig config, String h) {
        return C4295e.m14410b(config).m14405d(h).m14400b();
    }

    public static void rolu(Context context, Object registrant) {
        C4543y.m16305a(context).m16311a(registrant);
    }

    public static void urolu(Context context, Object registrant) {
        C4543y.m16305a(context).m16314b(registrant);
    }

    public static Location glkl(Context context) {
        return C4543y.m16305a(context).m16316d();
    }

    public static Integer gbc(Context context) {
        Intent registerReceiver = context.getApplicationContext().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (registerReceiver == null) {
            return null;
        }
        int intExtra = registerReceiver.getIntExtra(FirebaseAnalytics$Param.LEVEL, -1);
        int intExtra2 = registerReceiver.getIntExtra("scale", -1);
        return (intExtra < 0 || intExtra2 <= 0) ? null : Integer.valueOf(Math.round((((float) intExtra) / ((float) intExtra2)) * 100.0f));
    }

    public static void m16336a(IIdentifierCallback callback) {
        bo.m15016b().m15031a(callback);
    }

    public static DeviceInfo gdi(Context context) {
        return DeviceInfo.getInstance(context);
    }

    public static String gcni(Context context) {
        return new CellularNetworkInfo(context).getCelluralInfo();
    }

    public static String guid() {
        return bo.m15016b().m15033f();
    }

    public static String mpn(Context context) {
        return be.m14893d(context);
    }

    public static void rce(int type, String name, String value, Map<String, String> environment) {
        bo.m15020c().m14457a(type, name, value, environment);
    }
}
