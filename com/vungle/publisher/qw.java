package com.vungle.publisher;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.PowerManager;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import com.appnext.base.p023b.C1042c;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.loopj.android.http.RequestParams;
import com.vungle.publisher.env.WrapperFramework;
import com.vungle.publisher.env.i;
import com.vungle.publisher.env.n;
import com.vungle.publisher.env.w;
import com.vungle.publisher.inject.Injector;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import mf.org.apache.xerces.impl.Constants;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

/* compiled from: vungle */
public class qw {
    private static final qw f3272f = new qw();
    private static Map<String, String> f3273g;
    @Inject
    Context f3274a;
    @Inject
    n f3275b;
    @Inject
    i f3276c;
    @Inject
    WrapperFramework f3277d;
    @Inject
    String f3278e;
    private qv f3279h;
    private qv f3280i;
    private String f3281j;
    private String f3282k;
    private String f3283l;
    private String f3284m;
    private String f3285n;
    private JsonObject f3286o;
    private JsonObject f3287p;
    private int f3288q;
    private JsonObject f3289r;

    private qw() {
        OkHttpClient build = new Builder().build();
        OkHttpClient build2 = new Builder().addInterceptor(new a(this, null)).build();
        Retrofit build3 = new Retrofit.Builder().baseUrl("https://api.vungle.com/").addCallAdapterFactory(RxJavaCallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).client(build).build();
        Retrofit build4 = new Retrofit.Builder().baseUrl("https://api.vungle.com/").addCallAdapterFactory(RxJavaCallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).client(build2).build();
        this.f3279h = (qv) build3.create(qv.class);
        this.f3280i = (qv) build4.create(qv.class);
        f3273g = new HashMap();
    }

    public static void m4586a() {
        Injector.c().m4204a(f3272f);
        f3273g.put("Content-Type", RequestParams.APPLICATION_JSON);
        f3273g.put("Vungle-Version", "5.0.0");
        Object obj = w.c + BuildConfig.VERSION_NAME;
        if (!(f3272f.f3277d == null || f3272f.f3277d == WrapperFramework.none)) {
            obj = obj + ";" + f3272f.f3277d;
            if (f3272f.f3278e != null) {
                obj = obj + BridgeUtil.SPLIT_MARK + f3272f.f3278e;
            }
        }
        f3273g.put("User-Agent", obj);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", f3272f.f3275b.b());
        jsonObject.addProperty("bundle", f3272f.f3275b.a());
        jsonObject.addProperty("ver", f3272f.f3275b.c());
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.addProperty("make", Build.MANUFACTURER);
        jsonObject2.addProperty("model", Build.MODEL);
        jsonObject2.addProperty("osv", VERSION.RELEASE);
        jsonObject2.addProperty("carrier", ((TelephonyManager) f3272f.f3274a.getSystemService("phone")).getNetworkOperatorName());
        jsonObject2.addProperty("lmt", Integer.valueOf(f3272f.f3276c.j() ? 1 : 0));
        jsonObject2.addProperty("os", w.b);
        jsonObject2.addProperty("ifa", f3272f.f3276c.a() != null ? f3272f.f3276c.a() : f3272f.f3276c.d());
        jsonObject2.addProperty("ua", f3272f.f3276c.o());
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) f3272f.f3274a.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        jsonObject2.addProperty("w", Integer.valueOf(displayMetrics.widthPixels));
        jsonObject2.addProperty(com.mobfox.sdk.networking.RequestParams.f9035H, Integer.valueOf(displayMetrics.heightPixels));
        JsonElement jsonObject3 = new JsonObject();
        jsonObject3.add("vungle", new JsonObject());
        jsonObject2.add("ext", jsonObject3);
        f3272f.f3287p = jsonObject;
        f3272f.f3286o = jsonObject2;
        if (zj.c(f3272f.f3274a)) {
            f3272f.f3289r = f3272f.f3276c.u();
        }
    }

    public static Observable<JsonObject> m4584a(String str, Collection<String> collection) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("device", f3272f.m4591d());
            jsonObject.add("app", f3272f.f3287p);
            JsonElement jsonObject2 = new JsonObject();
            JsonElement jsonArray = new JsonArray();
            for (String add : collection) {
                jsonArray.add(add);
            }
            jsonObject2.add("placements", jsonArray);
            jsonObject.add("request", jsonObject2);
            return f3272f.f3279h.m4575a(f3273g, str, jsonObject).subscribeOn(Schedulers.io()).doOnNext(qx.m4593a());
        } catch (Throwable e) {
            return Observable.error(e);
        }
    }

    static /* synthetic */ void m4592d(JsonObject jsonObject) {
        Log.d("VungleApiClient", "Config Response: " + jsonObject);
        JsonObject asJsonObject = jsonObject.getAsJsonObject("endpoints");
        HttpUrl parse = HttpUrl.parse(asJsonObject.get("new").getAsString());
        HttpUrl parse2 = HttpUrl.parse(asJsonObject.get("ads").getAsString());
        HttpUrl parse3 = HttpUrl.parse(asJsonObject.get("will_play_ad").getAsString());
        HttpUrl parse4 = HttpUrl.parse(asJsonObject.get("report_ad").getAsString());
        HttpUrl parse5 = HttpUrl.parse(asJsonObject.get("log").getAsString());
        f3272f.f3281j = parse.toString();
        f3272f.f3282k = parse2.toString();
        f3272f.f3284m = parse3.toString();
        f3272f.f3283l = parse4.toString();
        f3272f.f3285n = parse5.toString();
        asJsonObject = jsonObject.getAsJsonObject("will_play_ad");
        f3272f.f3288q = asJsonObject.get("request_timeout").getAsInt();
    }

    public static Observable<JsonObject> m4587b() {
        if (f3272f.f3281j == null) {
            return Observable.error(new IllegalStateException("API Client not configured yet! Must call /config first."));
        }
        Map hashMap = new HashMap(2);
        hashMap.put("app_id", f3272f.f3275b.b());
        if (f3272f.f3276c.a() != null) {
            hashMap.put("ifa", f3272f.f3276c.a());
        }
        return f3272f.f3279h.m4576a(f3273g, f3272f.f3281j, hashMap);
    }

    public static Observable<String> m4583a(String str) {
        if (f3272f.f3282k == null) {
            return Observable.error(new IllegalStateException("API Client not configured yet! Must call /config first."));
        }
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("device", f3272f.m4591d());
            jsonObject.add("app", f3272f.f3287p);
            JsonElement jsonObject2 = new JsonObject();
            JsonElement jsonArray = new JsonArray();
            jsonArray.add(str);
            jsonObject2.add("placements", jsonArray);
            jsonObject.add("request", jsonObject2);
            return f3272f.f3279h.m4577b(f3273g, f3272f.f3282k, jsonObject).map(qy.m4594a());
        } catch (Throwable e) {
            return Observable.error(e);
        }
    }

    public static Observable<JsonObject> m4585a(String str, boolean z, String str2) {
        if (f3272f.f3284m == null) {
            return Observable.error(new IllegalStateException("API Client not configured yet! Must call /config first."));
        }
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("device", f3272f.m4591d());
            jsonObject.add("app", f3272f.f3287p);
            JsonElement jsonObject2 = new JsonObject();
            JsonElement jsonObject3 = new JsonObject();
            jsonObject3.addProperty("reference_id", str);
            jsonObject3.addProperty("is_auto_cached", Boolean.valueOf(z));
            jsonObject2.add("placement", jsonObject3);
            jsonObject2.addProperty("ad_token", str2);
            jsonObject.add("request", jsonObject2);
            return f3272f.f3279h.m4578c(f3273g, f3272f.f3284m, jsonObject).subscribeOn(Schedulers.io()).timeout((long) f3272f.f3288q, TimeUnit.MILLISECONDS);
        } catch (Throwable e) {
            return Observable.error(e);
        }
    }

    public static Observable<JsonObject> m4590c() {
        return Observable.just(null);
    }

    public static Observable<JsonObject> m4581a(JsonObject jsonObject) {
        if (f3272f.f3283l == null) {
            return Observable.error(new IllegalStateException("API Client not configured yet! Must call /config first."));
        }
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.add("device", f3272f.m4591d());
        jsonObject2.add("app", f3272f.f3287p);
        jsonObject2.add("request", jsonObject);
        return f3272f.f3279h.m4579d(f3273g, f3272f.f3283l, jsonObject2).subscribeOn(Schedulers.io());
    }

    public static Observable<JsonObject> m4582a(cz czVar) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("placement_reference_id", czVar.m3659r());
        jsonObject.addProperty("ad_token", czVar.m3655i().l());
        jsonObject.addProperty("app_id", czVar.m3663v());
        jsonObject.addProperty("incentivized", Integer.valueOf(czVar.m3656m() ? 1 : 0));
        if (czVar.m3656m()) {
            jsonObject.addProperty("user", czVar.m3657n());
        }
        jsonObject.addProperty("adStartTime", czVar.m3662u());
        jsonObject.addProperty("url", czVar.m3655i().h());
        jsonObject.addProperty("adDuration", Integer.valueOf(czVar.m3661t()));
        if (czVar instanceof ki) {
            jsonObject.addProperty("ttDownload", Integer.valueOf(((ki) czVar).m4310F()));
        } else if (czVar instanceof fg) {
            jsonObject.addProperty("ttDownload", Integer.valueOf(((fg) czVar).m3999F()));
        } else {
            jsonObject.addProperty("ttDownload", Integer.valueOf(-1));
        }
        jsonObject.addProperty(FirebaseAnalytics$Param.CAMPAIGN, czVar.m3655i().n());
        jsonObject.addProperty(VungleAdActivity.AD_TYPE_EXTRA_KEY, czVar.m3655i().a_().toString());
        jsonObject.addProperty("templateId", czVar.m3655i().i());
        if (czVar.m3664w() > 0) {
            jsonObject.addProperty("ordinal_view", Integer.valueOf(czVar.m3664w()));
        }
        JsonElement jsonArray = new JsonArray();
        JsonElement jsonArray2 = new JsonArray();
        for (cy cyVar : czVar.m3666y()) {
            JsonElement jsonObject2 = new JsonObject();
            jsonObject2.addProperty("startTime", czVar.m3662u());
            jsonObject2.addProperty("videoLength", czVar.m3660s());
            jsonObject2.addProperty("videoViewed", Integer.valueOf(czVar.m3661t()));
            JsonElement jsonArray3 = new JsonArray();
            for (da daVar : cyVar.e()) {
                JsonElement jsonObject3 = new JsonObject();
                jsonObject3.addProperty(C1042c.jL, String.valueOf(daVar.a()));
                jsonObject3.addProperty("timestamp_millis", Long.valueOf(daVar.e()));
                jsonObject3.addProperty(FirebaseAnalytics$Param.VALUE, daVar.i());
                jsonArray3.add(jsonObject3);
                jsonArray2.add(String.valueOf(daVar.a()));
            }
            jsonObject2.add("userActions", jsonArray3);
            jsonArray.add(jsonObject2);
        }
        jsonObject.add("plays", jsonArray);
        jsonObject.add("clickedThrough", jsonArray2);
        JsonElement jsonArray4 = new JsonArray();
        for (Object obj : czVar.m3653e()) {
            jsonArray4.add(obj.toString());
        }
        jsonObject.add("errors", jsonArray4);
        return m4581a(jsonObject);
    }

    public static Observable<JsonObject> m4588b(JsonObject jsonObject) {
        if (f3272f.f3285n == null) {
            return Observable.error(new IllegalStateException("API Client not configured yet! Must call /config first."));
        }
        return f3272f.f3280i.m4580e(f3273g, f3272f.f3285n, jsonObject).subscribeOn(Schedulers.io());
    }

    private JsonObject m4591d() throws IllegalStateException {
        int i = 1;
        if (this.f3274a == null) {
            throw new IllegalStateException("Context is null, SDK not initialized");
        }
        String str;
        String str2;
        int i2;
        JsonElement jsonObject = new JsonObject();
        jsonObject.addProperty("gaid", this.f3276c.a());
        Intent registerReceiver = this.f3274a.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        int intExtra = registerReceiver.getIntExtra(FirebaseAnalytics$Param.LEVEL, -1);
        int intExtra2 = registerReceiver.getIntExtra("scale", -1);
        if (intExtra > 0 && intExtra2 > 0) {
            jsonObject.addProperty("battery_level", Float.valueOf(((float) intExtra) / ((float) intExtra2)));
        }
        intExtra = registerReceiver.getIntExtra("status", -1);
        if (intExtra == -1) {
            str = C1404b.f2123a;
        } else if (intExtra == 2 || intExtra == 5) {
            switch (registerReceiver.getIntExtra("plugged", -1)) {
                case 1:
                    str = "BATTERY_PLUGGED_AC";
                    break;
                case 2:
                    str = "BATTERY_PLUGGED_USB";
                    break;
                case 4:
                    str = "BATTERY_PLUGGED_WIRELESS";
                    break;
                default:
                    str = "BATTERY_PLUGGED_OTHERS";
                    break;
            }
        } else {
            str = "NOT_CHARGING";
        }
        jsonObject.addProperty("battery_state", str);
        if (VERSION.SDK_INT >= 21) {
            str2 = "battery_saver_enabled";
            if (((PowerManager) this.f3274a.getSystemService("power")).isPowerSaveMode()) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            jsonObject.addProperty(str2, Integer.valueOf(i2));
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) this.f3274a.getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
            switch (activeNetworkInfo.getType()) {
                case 0:
                    str2 = C1404b.f2126d;
                    break;
                case 1:
                case 6:
                    str2 = C1404b.f2124b;
                    break;
                case 7:
                    str2 = "BLUETOOTH";
                    break;
                case 9:
                    str2 = "ETHERNET";
                    break;
                default:
                    str2 = C1404b.f2123a;
                    break;
            }
            jsonObject.addProperty("connection_type", str2);
        }
        jsonObject.addProperty("connection_type_detail", C1404b.f2124b);
        if (VERSION.SDK_INT >= 24) {
            if (connectivityManager.isActiveNetworkMetered()) {
                switch (connectivityManager.getRestrictBackgroundStatus()) {
                    case 1:
                        str = "DISABLED";
                        break;
                    case 2:
                        str = "WHITELISTED";
                        break;
                    case 3:
                        str = "ENABLED";
                        break;
                    default:
                        str = C1404b.f2123a;
                        break;
                }
                jsonObject.addProperty("data_saver_status", str);
                jsonObject.addProperty("network_metered", Integer.valueOf(1));
            } else {
                jsonObject.addProperty("data_saver_status", "NOT_APPLICABLE");
                jsonObject.addProperty("network_metered", Integer.valueOf(0));
            }
        }
        jsonObject.addProperty(Constants.LOCALE_PROPERTY, Locale.getDefault().toString());
        jsonObject.addProperty(SchemaSymbols.ATTVAL_LANGUAGE, Locale.getDefault().getLanguage());
        jsonObject.addProperty("time_zone", TimeZone.getDefault().getID());
        jsonObject.addProperty("volume_level", f3272f.f3276c.k());
        str2 = "sound_enabled";
        if (f3272f.f3276c.k().floatValue() > 0.0f) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        jsonObject.addProperty(str2, Integer.valueOf(i2));
        str = "sd_card_available";
        if (!f3272f.f3276c.l()) {
            i = 0;
        }
        jsonObject.addProperty(str, Integer.valueOf(i));
        jsonObject.addProperty("os_name", f3272f.f3276c.q());
        jsonObject.addProperty("storage_bytes_available", this.f3276c.p());
        jsonObject.addProperty("vduid", "");
        jsonObject.addProperty("os_api_level", Integer.valueOf(f3272f.f3276c.r()));
        jsonObject.addProperty("is_tv", Boolean.valueOf(f3272f.f3276c.t()));
        jsonObject.addProperty("is_sideload_enabled", Boolean.valueOf(f3272f.f3276c.s()));
        jsonObject.addProperty("android_id", f3272f.f3276c.d());
        if (f3272f.f3289r != null) {
            jsonObject.add("location", f3272f.f3289r);
        }
        this.f3286o.getAsJsonObject("ext").getAsJsonObject("vungle").add(w.b, jsonObject);
        return this.f3286o;
    }
}
