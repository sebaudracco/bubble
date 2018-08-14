package com.vungle.publisher.inject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.telephony.TelephonyManager;
import android.view.WindowManager;
import com.vungle.publisher.FlexViewAdActivity;
import com.vungle.publisher.MraidFullScreenAdActivity;
import com.vungle.publisher.VideoFullScreenAdActivity;
import com.vungle.publisher.VungleAdActivity;
import com.vungle.publisher.env.AndroidDevice;
import com.vungle.publisher.env.WrapperFramework;
import com.vungle.publisher.env.h;
import com.vungle.publisher.env.i;
import com.vungle.publisher.env.n;
import com.vungle.publisher.log.Logger;
import com.vungle.publisher.qm;
import com.vungle.publisher.qr;
import com.vungle.publisher.sv;
import com.vungle.publisher.sz;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
/* compiled from: vungle */
public class C1638a {
    private Context f3015a;
    private String f3016b;
    private Class<? extends VungleAdActivity> f3017c;
    private Class<? extends VungleAdActivity> f3018d;
    private Class<? extends VungleAdActivity> f3019e;
    private WrapperFramework f3020f;
    private String f3021g;
    private boolean f3022h;

    public void m4181a(WrapperFramework wrapperFramework) {
        if (this.f3022h) {
            Logger.d(Logger.INJECT_TAG, "wrapper framework in publisher module NOT set - already initialized");
            return;
        }
        Logger.d(Logger.INJECT_TAG, "setting framework in publisher module: " + wrapperFramework);
        this.f3020f = wrapperFramework;
    }

    public void m4182a(String str) {
        if (this.f3022h) {
            Logger.d(Logger.INJECT_TAG, "wrapper framework version in publisher module NOT set - already initialized");
            return;
        }
        Logger.d(Logger.INJECT_TAG, "setting framework in publisher module: " + str);
        this.f3021g = str;
    }

    public boolean m4183a() {
        return this.f3022h;
    }

    public void m4180a(Context context, String str) {
        if (this.f3022h) {
            Logger.d(Logger.INJECT_TAG, "publisher module already initialized");
            return;
        }
        Logger.d(Logger.INJECT_TAG, "initializing publisher module");
        this.f3015a = context.getApplicationContext();
        this.f3016b = str;
        this.f3022h = true;
    }

    @Provides
    Context m4184b() {
        return this.f3015a;
    }

    @Provides
    String m4179a(Context context) {
        if (context.getExternalFilesDir(null) == null) {
            throw new qm();
        }
        return qr.a(new Object[]{context.getExternalFilesDir(null).getAbsolutePath(), ".vungle"});
    }

    @Provides
    String m4185b(Context context) {
        if (context.getExternalCacheDir() == null) {
            throw new qm();
        }
        return qr.a(new Object[]{context.getExternalCacheDir(), ".VungleCacheDir"});
    }

    @Singleton
    @Provides
    AudioManager m4186c(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        if (audioManager == null) {
            Logger.d(Logger.DEVICE_TAG, "AudioManager not avaialble");
        }
        return audioManager;
    }

    @Provides
    ConnectivityManager m4188d(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            Logger.d(Logger.DEVICE_TAG, "ConnectivityManager not available");
        }
        return connectivityManager;
    }

    @Singleton
    @Provides
    i m4176a(AndroidDevice androidDevice) {
        return androidDevice;
    }

    @Provides
    SharedPreferences m4190e(Context context) {
        return context.getSharedPreferences("VUNGLE_PUB_APP_INFO", 0);
    }

    @Provides
    Class m4187c() {
        return this.f3017c == null ? VideoFullScreenAdActivity.class : this.f3017c;
    }

    @Provides
    Class m4189d() {
        return this.f3018d == null ? MraidFullScreenAdActivity.class : this.f3018d;
    }

    @Provides
    Class m4191e() {
        return this.f3019e == null ? FlexViewAdActivity.class : this.f3019e;
    }

    @Singleton
    @Provides
    sz m4178a(sv svVar) {
        return svVar;
    }

    @Singleton
    @Provides
    n m4177a(Context context, WrapperFramework wrapperFramework) {
        String str = null;
        try {
            str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            Logger.e(Logger.INJECT_TAG, "cannot get App's version");
        }
        return new h(context.getPackageName(), this.f3016b, wrapperFramework, str);
    }

    @Provides
    TelephonyManager m4192f(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            Logger.d(Logger.DEVICE_TAG, "TelephonyManager not avaialble");
        }
        return telephonyManager;
    }

    @Provides
    WindowManager m4194g(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager == null) {
            Logger.d(Logger.DEVICE_TAG, "WindowManager not available");
        }
        return windowManager;
    }

    @Provides
    WrapperFramework m4193f() {
        return this.f3020f == null ? WrapperFramework.none : this.f3020f;
    }

    @Provides
    String m4195g() {
        return this.f3021g == null ? "" : this.f3021g;
    }
}
