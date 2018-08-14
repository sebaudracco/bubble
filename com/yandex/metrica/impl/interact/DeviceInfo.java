package com.yandex.metrica.impl.interact;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.util.DisplayMetrics;
import com.yandex.metrica.C4275a;
import com.yandex.metrica.impl.am;
import com.yandex.metrica.impl.am.C4324a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class DeviceInfo {
    private static volatile DeviceInfo f11873a;
    public final String appPlatform = "android";
    private final Context f11874b;
    public final String deviceRootStatus;
    public final List<String> deviceRootStatusMarkers;
    public final String deviceType;
    public String locale = am.m14599b(this.f11874b);
    public final String manufacturer = Build.MANUFACTURER;
    public final String model = Build.MODEL;
    public final String osVersion = VERSION.RELEASE;
    public final String platform = "android";
    public final String platformDeviceId = Secure.getString(this.f11874b.getContentResolver(), "android_id");
    public final float scaleFactor = this.f11874b.getResources().getDisplayMetrics().density;
    public final int screenDpi = this.f11874b.getResources().getDisplayMetrics().densityDpi;
    public final int screenHeight = am.m14597a(this.f11874b).y;
    public final int screenWidth = am.m14597a(this.f11874b).x;

    class C43811 extends ArrayList<String> {
        C43811() {
            if (C4324a.m14593a()) {
                add("Superuser.apk");
            }
            if (C4324a.m14594b()) {
                add("su.so");
            }
        }
    }

    public static DeviceInfo getInstance(Context context) {
        if (f11873a == null) {
            synchronized (DeviceInfo.class) {
                if (f11873a == null) {
                    f11873a = new DeviceInfo(context.getApplicationContext());
                }
            }
        }
        return f11873a;
    }

    private DeviceInfo(Context context) {
        Object obj;
        C4275a c4275a;
        this.f11874b = context;
        Context context2 = this.f11874b;
        DisplayMetrics displayMetrics = context2.getResources().getDisplayMetrics();
        Point a = am.m14597a(context2);
        int i = a.x;
        int i2 = a.y;
        float f = displayMetrics.density;
        float min = Math.min(((float) i) / f, ((float) i2) / f);
        f *= 160.0f;
        float f2 = ((float) i) / f;
        f = ((float) i2) / f;
        double sqrt = Math.sqrt((double) ((f * f) + (f2 * f2)));
        if (sqrt < 15.0d || context2.getPackageManager().hasSystemFeature("android.hardware.touchscreen")) {
            obj = null;
        } else {
            obj = 1;
        }
        if (obj != null) {
            c4275a = C4275a.TV;
        } else if (sqrt >= 7.0d || min >= 600.0f) {
            c4275a = C4275a.TABLET;
        } else {
            c4275a = C4275a.PHONE;
        }
        this.deviceType = c4275a.name().toLowerCase(Locale.US);
        this.deviceRootStatus = String.valueOf(C4324a.m14595c());
        this.deviceRootStatusMarkers = Collections.unmodifiableList(new C43811());
    }

    public String getLocale() {
        this.locale = am.m14599b(this.f11874b);
        return this.locale;
    }
}
