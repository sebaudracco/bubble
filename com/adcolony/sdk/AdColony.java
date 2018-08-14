package com.adcolony.sdk;

import android.app.Activity;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import com.adcolony.sdk.aa.C0595a;
import com.adcolony.sdk.az.C0639a;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import org.json.JSONObject;

public class AdColony {
    static ExecutorService f368a = Executors.newSingleThreadExecutor();

    public static boolean disable() {
        if (!C0594a.m615e()) {
            return false;
        }
        if (C0594a.m614d() && (C0594a.m613c() instanceof C0589b)) {
            C0594a.m613c().finish();
        }
        final C0740l a = C0594a.m605a();
        for (final AdColonyInterstitial adColonyInterstitial : a.m1283m().m1155c().values()) {
            az.m880a(new Runnable() {
                public void run() {
                    AdColonyInterstitialListener listener = adColonyInterstitial.getListener();
                    adColonyInterstitial.m566a(true);
                    if (listener != null) {
                        listener.onExpiring(adColonyInterstitial);
                    }
                }
            });
        }
        az.m880a(new Runnable() {
            public void run() {
                ArrayList arrayList = new ArrayList();
                Iterator it = a.m1287q().m708c().iterator();
                while (it.hasNext()) {
                    arrayList.add((ai) it.next());
                }
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    ai aiVar = (ai) it2.next();
                    a.m1260a(aiVar.mo1841a());
                    if (aiVar instanceof bb) {
                        bb bbVar = (bb) aiVar;
                        if (!bbVar.m1001g()) {
                            bbVar.loadUrl("about:blank");
                            bbVar.clearCache(true);
                            bbVar.removeAllViews();
                            bbVar.m991a(true);
                        }
                    }
                }
            }
        });
        C0594a.m605a().m1259a(true);
        return true;
    }

    public static boolean configure(Activity activity, @NonNull String appId, @NonNull String... zoneIds) {
        return configure(activity, null, appId, zoneIds);
    }

    public static boolean configure(Activity activity, AdColonyAppOptions options, @NonNull String appId, @NonNull String... zoneIds) {
        if (ak.m717a(0, null)) {
            new C0595a().m622a("Cannot configure AdColony; configuration mechanism requires 5 seconds between attempts.").m624a(aa.f481e);
            return false;
        }
        if (activity == null && C0594a.m614d()) {
            activity = C0594a.m613c();
        }
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }
        if (options == null) {
            options = new AdColonyAppOptions();
        }
        if (C0594a.m612b() && !C0802y.m1477d(C0594a.m605a().m1271d().m554d(), "reconfigurable")) {
            C0740l a = C0594a.m605a();
            if (!a.m1271d().m551a().equals(appId)) {
                new C0595a().m622a("Ignoring call to AdColony.configure, as the app id does not match what was used during the initial configuration.").m624a(aa.f481e);
                return false;
            } else if (az.m884a(zoneIds, a.m1271d().m552b())) {
                new C0595a().m622a("Ignoring call to AdColony.configure, as the same zone ids were used during the previous configuration.").m624a(aa.f481e);
                return false;
            }
        }
        options.m549a(appId);
        options.m550a(zoneIds);
        options.m555e();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss:SSS");
        long currentTimeMillis = System.currentTimeMillis();
        String format = simpleDateFormat.format(new Date(currentTimeMillis));
        int i = 0;
        boolean z = true;
        while (i < zoneIds.length) {
            if (!(zoneIds[i] == null || zoneIds[i].equals(""))) {
                z = false;
            }
            i++;
        }
        if (appId.equals("") || r2) {
            new C0595a().m622a("AdColony.configure() called with an empty app or zone id String.").m624a(aa.f483g);
            return false;
        }
        C0594a.f472a = true;
        if (VERSION.SDK_INT < 14) {
            new C0595a().m622a("The minimum API level for the AdColony SDK is 14.").m624a(aa.f481e);
            C0594a.m607a(activity, options, true);
        } else {
            C0594a.m607a(activity, options, false);
        }
        String str = C0594a.m605a().m1285o().m783c() + "/adc3/AppInfo";
        JSONObject a2 = C0802y.m1453a();
        if (new File(str).exists()) {
            a2 = C0802y.m1475c(str);
        }
        JSONObject a3 = C0802y.m1453a();
        if (C0802y.m1468b(a2, "appId").equals(appId)) {
            C0802y.m1463a(a3, "zoneIds", C0802y.m1451a(C0802y.m1481g(a2, "zoneIds"), zoneIds, true));
            C0802y.m1462a(a3, "appId", appId);
        } else {
            C0802y.m1463a(a3, "zoneIds", C0802y.m1452a(zoneIds));
            C0802y.m1462a(a3, "appId", appId);
        }
        C0802y.m1482h(a3, str);
        new C0595a().m622a("Configure: Total Time (ms): ").m622a((System.currentTimeMillis() - currentTimeMillis) + " and started at " + format).m624a(aa.f482f);
        return true;
    }

    public static AdColonyZone getZone(@NonNull String zoneId) {
        if (C0594a.m615e()) {
            HashMap f = C0594a.m605a().m1276f();
            if (f.containsKey(zoneId)) {
                return (AdColonyZone) f.get(zoneId);
            }
            AdColonyZone adColonyZone = new AdColonyZone(zoneId);
            C0594a.m605a().m1276f().put(zoneId, adColonyZone);
            return adColonyZone;
        }
        new C0595a().m622a("Ignoring call to AdColony.getZone() as AdColony has not yet been configured.").m624a(aa.f481e);
        return null;
    }

    public static boolean notifyIAPComplete(@NonNull String productId, @NonNull String transId) {
        return notifyIAPComplete(productId, transId, null, -1.0d);
    }

    public static boolean notifyIAPComplete(@NonNull String productId, @NonNull String transId, String currencyCode, @FloatRange(from = 0.0d) double price) {
        if (!C0594a.m615e()) {
            new C0595a().m622a("Ignoring call to notifyIAPComplete as AdColony has not yet been configured.").m624a(aa.f481e);
            return false;
        } else if (az.m894d(productId) && az.m894d(transId)) {
            if (currencyCode != null && currencyCode.length() > 3) {
                new C0595a().m622a("You are trying to report an IAP event with a currency String containing more than 3 characters.").m624a(aa.f481e);
            }
            final double d = price;
            final String str = currencyCode;
            final String str2 = productId;
            final String str3 = transId;
            f368a.execute(new Runnable() {
                public void run() {
                    AdColony.m536a();
                    JSONObject a = C0802y.m1453a();
                    if (d >= 0.0d) {
                        C0802y.m1460a(a, FirebaseAnalytics$Param.PRICE, d);
                    }
                    if (str != null && str.length() <= 3) {
                        C0802y.m1462a(a, "currency_code", str);
                    }
                    C0802y.m1462a(a, "product_id", str2);
                    C0802y.m1462a(a, FirebaseAnalytics$Param.TRANSACTION_ID, str3);
                    new af("AdColony.on_iap_report", 1, a).m695b();
                }
            });
            return true;
        } else {
            new C0595a().m622a("Ignoring call to notifyIAPComplete as one of the passed Strings is greater than ").m620a(128).m622a(" characters.").m624a(aa.f481e);
            return false;
        }
    }

    static boolean m539a(String str, bd bdVar, int i, int i2) {
        return true;
    }

    public static boolean requestNativeAdView(@NonNull String zoneId, @NonNull AdColonyNativeAdViewListener listener, @NonNull AdColonyAdSize size) {
        return requestNativeAdView(zoneId, listener, size, null);
    }

    public static boolean requestNativeAdView(@NonNull final String zoneId, @NonNull final AdColonyNativeAdViewListener listener, @NonNull final AdColonyAdSize size, final AdColonyAdOptions options) {
        if (C0594a.m615e()) {
            Bundle bundle = new Bundle();
            bundle.putString("zone_id", zoneId);
            if (ak.m717a(1, bundle)) {
                m538a(listener, zoneId);
                return false;
            }
            try {
                f368a.execute(new Runnable() {
                    public void run() {
                        C0740l a = C0594a.m605a();
                        if (a.m1277g() || a.m1278h()) {
                            AdColony.m540b();
                            AdColony.m538a(listener, zoneId);
                        }
                        if (!AdColony.m536a() && C0594a.m614d()) {
                            AdColony.m538a(listener, zoneId);
                        }
                        if (((AdColonyZone) a.m1276f().get(zoneId)) == null) {
                            AdColonyZone adColonyZone = new AdColonyZone(zoneId);
                            new C0595a().m622a("Zone info for ").m622a(zoneId).m622a(" doesn't exist in hashmap").m624a(aa.f478b);
                        }
                        a.m1283m().m1149a(zoneId, listener, size, options);
                    }
                });
                return true;
            } catch (RejectedExecutionException e) {
                m538a(listener, zoneId);
                return false;
            }
        }
        new C0595a().m622a("Ignoring call to requestNativeAdView as AdColony has not yet been configured.").m624a(aa.f481e);
        m538a(listener, zoneId);
        return false;
    }

    public static boolean setAppOptions(@NonNull final AdColonyAppOptions options) {
        if (C0594a.m615e()) {
            C0594a.m605a().m1264b(options);
            options.m555e();
            try {
                f368a.execute(new Runnable() {
                    public void run() {
                        AdColony.m536a();
                        JSONObject a = C0802y.m1453a();
                        C0802y.m1464a(a, "options", options.m554d());
                        new af("Options.set_options", 1, a).m695b();
                    }
                });
                return true;
            } catch (RejectedExecutionException e) {
                return false;
            }
        }
        new C0595a().m622a("Ignoring call to AdColony.setAppOptions() as AdColony has not yet been configured.").m624a(aa.f481e);
        return false;
    }

    public static AdColonyAppOptions getAppOptions() {
        if (C0594a.m615e()) {
            return C0594a.m605a().m1271d();
        }
        return null;
    }

    public static boolean setRewardListener(@NonNull AdColonyRewardListener listener) {
        if (C0594a.m615e()) {
            C0594a.m605a().m1254a(listener);
            return true;
        }
        new C0595a().m622a("Ignoring call to AdColony.setRewardListener() as AdColony has not yet been configured.").m624a(aa.f481e);
        return false;
    }

    public static boolean removeRewardListener() {
        if (C0594a.m615e()) {
            C0594a.m605a().m1254a(null);
            return true;
        }
        new C0595a().m622a("Ignoring call to AdColony.removeRewardListener() as AdColony has not yet been configured.").m624a(aa.f481e);
        return false;
    }

    public static String getSDKVersion() {
        if (C0594a.m615e()) {
            return C0594a.m605a().m1284n().m1305D();
        }
        return "";
    }

    public static AdColonyRewardListener getRewardListener() {
        if (C0594a.m615e()) {
            return C0594a.m605a().m1279i();
        }
        return null;
    }

    public static boolean addCustomMessageListener(@NonNull AdColonyCustomMessageListener listener, final String type) {
        if (!C0594a.m615e()) {
            new C0595a().m622a("Ignoring call to AdColony.addCustomMessageListener as AdColony has not yet been configured.").m624a(aa.f481e);
            return false;
        } else if (az.m894d(type)) {
            try {
                C0594a.m605a().m1296z().put(type, listener);
                f368a.execute(new Runnable() {
                    public void run() {
                        AdColony.m536a();
                        JSONObject a = C0802y.m1453a();
                        C0802y.m1462a(a, "type", type);
                        new af("CustomMessage.register", 1, a).m695b();
                    }
                });
                return true;
            } catch (RejectedExecutionException e) {
                return false;
            }
        } else {
            new C0595a().m622a("Ignoring call to AdColony.addCustomMessageListener.").m624a(aa.f481e);
            return false;
        }
    }

    public static AdColonyCustomMessageListener getCustomMessageListener(@NonNull String type) {
        if (C0594a.m615e()) {
            return (AdColonyCustomMessageListener) C0594a.m605a().m1296z().get(type);
        }
        return null;
    }

    public static boolean removeCustomMessageListener(@NonNull final String type) {
        if (C0594a.m615e()) {
            C0594a.m605a().m1296z().remove(type);
            f368a.execute(new Runnable() {
                public void run() {
                    AdColony.m536a();
                    JSONObject a = C0802y.m1453a();
                    C0802y.m1462a(a, "type", type);
                    new af("CustomMessage.unregister", 1, a).m695b();
                }
            });
            return true;
        }
        new C0595a().m622a("Ignoring call to AdColony.removeCustomMessageListener as AdColony has not yet been configured.").m624a(aa.f481e);
        return false;
    }

    public static boolean clearCustomMessageListeners() {
        if (C0594a.m615e()) {
            C0594a.m605a().m1296z().clear();
            f368a.execute(new Runnable() {
                public void run() {
                    AdColony.m536a();
                    for (String str : C0594a.m605a().m1296z().keySet()) {
                        JSONObject a = C0802y.m1453a();
                        C0802y.m1462a(a, "type", str);
                        new af("CustomMessage.unregister", 1, a).m695b();
                    }
                }
            });
            return true;
        }
        new C0595a().m622a("Ignoring call to AdColony.clearCustomMessageListeners as AdColony has not yet been configured.").m624a(aa.f481e);
        return false;
    }

    public static boolean requestInterstitial(@NonNull String zoneId, @NonNull AdColonyInterstitialListener listener) {
        return requestInterstitial(zoneId, listener, null);
    }

    public static boolean requestInterstitial(@NonNull final String zoneId, @NonNull final AdColonyInterstitialListener listener, final AdColonyAdOptions options) {
        if (C0594a.m615e()) {
            Bundle bundle = new Bundle();
            bundle.putString("zone_id", zoneId);
            if (ak.m717a(1, bundle)) {
                AdColonyZone adColonyZone = (AdColonyZone) C0594a.m605a().m1276f().get(zoneId);
                if (adColonyZone == null) {
                    adColonyZone = new AdColonyZone(zoneId);
                    new C0595a().m622a("Zone info for ").m622a(zoneId).m622a(" doesn't exist in hashmap").m624a(aa.f478b);
                }
                listener.onRequestNotFilled(adColonyZone);
                return false;
            }
            try {
                f368a.execute(new Runnable() {
                    public void run() {
                        C0740l a = C0594a.m605a();
                        if (a.m1277g() || a.m1278h()) {
                            AdColony.m540b();
                            AdColony.m537a(listener, zoneId);
                        } else if (AdColony.m536a() || !C0594a.m614d()) {
                            AdColonyZone adColonyZone = (AdColonyZone) a.m1276f().get(zoneId);
                            if (adColonyZone == null) {
                                adColonyZone = new AdColonyZone(zoneId);
                                new C0595a().m622a("Zone info for ").m622a(zoneId).m622a(" doesn't exist in hashmap").m624a(aa.f478b);
                            }
                            if (adColonyZone.getZoneType() != 2) {
                                a.m1283m().m1148a(zoneId, listener, options);
                            } else if (C0594a.m614d()) {
                                C0594a.m613c().runOnUiThread(new Runnable(this) {
                                    final /* synthetic */ AnonymousClass11 f347b;

                                    public void run() {
                                        listener.onRequestNotFilled(adColonyZone);
                                    }
                                });
                            }
                        } else {
                            AdColony.m537a(listener, zoneId);
                        }
                    }
                });
                return true;
            } catch (RejectedExecutionException e) {
                m537a(listener, zoneId);
                return false;
            }
        }
        new C0595a().m622a("Ignoring call to AdColony.requestInterstitial as AdColony has not yet been configured.").m624a(aa.f481e);
        listener.onRequestNotFilled(new AdColonyZone(zoneId));
        return false;
    }

    static boolean m536a() {
        C0639a c0639a = new C0639a(15.0d);
        C0740l a = C0594a.m605a();
        while (!a.m1247A() && !c0639a.m866b()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
        return a.m1247A();
    }

    static boolean m537a(final AdColonyInterstitialListener adColonyInterstitialListener, final String str) {
        if (adColonyInterstitialListener != null && C0594a.m614d()) {
            az.m880a(new Runnable() {
                public void run() {
                    AdColonyZone adColonyZone = (AdColonyZone) C0594a.m605a().m1276f().get(str);
                    if (adColonyZone == null) {
                        adColonyZone = new AdColonyZone(str);
                    }
                    adColonyInterstitialListener.onRequestNotFilled(adColonyZone);
                }
            });
        }
        return false;
    }

    static boolean m538a(final AdColonyNativeAdViewListener adColonyNativeAdViewListener, final String str) {
        if (adColonyNativeAdViewListener != null && C0594a.m614d()) {
            az.m880a(new Runnable() {
                public void run() {
                    AdColonyZone adColonyZone = !C0594a.m612b() ? null : (AdColonyZone) C0594a.m605a().m1276f().get(str);
                    if (adColonyZone == null) {
                        adColonyZone = new AdColonyZone(str);
                    }
                    adColonyNativeAdViewListener.onRequestNotFilled(adColonyZone);
                }
            });
        }
        return false;
    }

    static void m535a(Activity activity, AdColonyAppOptions adColonyAppOptions) {
        if (adColonyAppOptions != null && activity != null) {
            String a = az.m871a(activity);
            String b = az.m888b();
            int c = az.m891c();
            String h = C0594a.m605a().f1295c.m1317h();
            Object obj = "none";
            if (C0594a.m605a().m1286p().m713a()) {
                obj = "wifi";
            } else if (C0594a.m605a().m1286p().m715b()) {
                obj = "mobile";
            }
            HashMap hashMap = new HashMap();
            hashMap.put("sessionId", "unknown");
            hashMap.put("advertiserId", "unknown");
            hashMap.put("countryLocale", Locale.getDefault().getDisplayLanguage() + " (" + Locale.getDefault().getDisplayCountry() + ")");
            hashMap.put("countryLocalShort", C0594a.m605a().f1295c.m1331v());
            hashMap.put("manufacturer", C0594a.m605a().f1295c.m1333x());
            hashMap.put("model", C0594a.m605a().f1295c.m1334y());
            hashMap.put("osVersion", C0594a.m605a().f1295c.m1335z());
            hashMap.put("carrierName", h);
            hashMap.put("networkType", obj);
            hashMap.put("platform", "android");
            hashMap.put("appName", a);
            hashMap.put("appVersion", b);
            hashMap.put("appBuildNumber", Integer.valueOf(c));
            hashMap.put("appId", "" + adColonyAppOptions.m551a());
            hashMap.put("apiLevel", Integer.valueOf(C0594a.m605a().f1295c.m1328s()));
            hashMap.put("sdkVersion", C0594a.m605a().f1295c.m1305D());
            hashMap.put("controllerVersion", "unknown");
            hashMap.put("zoneIds", adColonyAppOptions.m553c());
            JSONObject mediationInfo = adColonyAppOptions.getMediationInfo();
            JSONObject pluginInfo = adColonyAppOptions.getPluginInfo();
            if (!C0802y.m1468b(mediationInfo, "mediation_network").equals("")) {
                hashMap.put("mediationNetwork", C0802y.m1468b(mediationInfo, "mediation_network"));
                hashMap.put("mediationNetworkVersion", C0802y.m1468b(mediationInfo, "mediation_network_version"));
            }
            if (!C0802y.m1468b(pluginInfo, "plugin").equals("")) {
                hashMap.put("plugin", C0802y.m1468b(pluginInfo, "plugin"));
                hashMap.put("pluginVersion", C0802y.m1468b(pluginInfo, "plugin_version"));
            }
            ac.m658a(hashMap);
        }
    }

    static void m540b() {
        new C0595a().m622a("The AdColony API is not available while AdColony is disabled.").m624a(aa.f483g);
    }

    static String m541c() {
        return C0594a.m605a().m1284n().m1312c();
    }

    static String m542d() {
        return C0594a.m605a().m1284n().m1311b();
    }
}
