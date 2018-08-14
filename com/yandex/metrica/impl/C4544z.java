package com.yandex.metrica.impl;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.location.Location;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.yandex.metrica.C4295e;
import com.yandex.metrica.impl.ob.dw;
import com.yandex.metrica.impl.utils.C4529j;
import java.util.Map;

public class C4544z extends C4306b implements ac {
    C4544z(Context context, C4295e c4295e, ay ayVar) {
        super(context, c4295e.getApiKey(), ayVar, new aw());
        this.b.m14708a(new an(c4295e.getPreloadInfo()));
    }

    void mo7124a(dw dwVar) {
        super.mo7124a(dwVar);
    }

    void mo7123a(C4382j c4382j) {
        super.mo7123a(c4382j);
    }

    public void reportEvent(String eventName) {
        super.reportEvent(eventName);
    }

    public void reportEvent(String eventName, String jsonValue) {
        super.reportEvent(eventName, jsonValue);
        C4544z.m16317c(eventName, jsonValue);
    }

    public void reportEvent(String eventName, Map<String, Object> attributes) {
        super.reportEvent(eventName, (Map) attributes);
        C4544z.m16317c(eventName, attributes == null ? null : attributes.toString());
    }

    private static void m16317c(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder("Event received: ");
        stringBuilder.append(str);
        if (!TextUtils.isEmpty(str2)) {
            stringBuilder.append(". With value: ");
            stringBuilder.append(str2);
        }
        C4529j.m16280f().m16243a(stringBuilder.toString());
    }

    public void reportError(String message, Throwable error) {
        super.reportError(message, error);
        C4529j.m16280f().m16244a("Error received: %s", message);
    }

    public void m16318a(Activity activity) {
        if (activity == null) {
            C4529j.m16280f().m16245b("Null activity parameter for reportAppOpen(Activity)");
        } else if (activity.getIntent() != null) {
            Uri data = activity.getIntent().getData();
            if (data != null) {
                this.c.m14747a(C4511p.m16192a(data), this.b);
            }
        }
    }

    public void m16333e(String str) {
        if (TextUtils.isEmpty(str)) {
            C4529j.m16280f().m16245b("Null or empty deeplink value for reportAppOpen(String) was ignored.");
        } else {
            this.c.m14747a(C4511p.m16210e(str), this.b);
        }
    }

    public void m16319a(Application application) {
        bk.m14981a((Object) application, "Application");
        if (VERSION.SDK_INT >= 14) {
            C4529j.m16280f().m16243a("Enable activity auto tracking");
            application.registerActivityLifecycleCallbacks(new C4386m(this));
            return;
        }
        C4529j.m16280f().m16245b("Could not enable activity auto tracking. API level should be more than 14 (ICE_CREAM_SANDWICH)");
    }

    public void m16326b(Activity activity) {
        m14465b(m16331d(activity));
    }

    public void m16329c(Activity activity) {
        m14468c(m16331d(activity));
    }

    String m16331d(Activity activity) {
        if (activity != null) {
            return activity.getClass().getSimpleName();
        }
        return null;
    }

    void m16321a(C4295e c4295e, boolean z) {
        this.b.m14714b().m14247a(c4295e);
        mo7039d(this.b.m14714b().m14279l());
        if (z) {
            m14464b();
        }
        m14467b(c4295e.m14420j());
        m14463a(c4295e.getErrorEnvironment());
    }

    public void m16330c(boolean z) {
        this.b.m14714b().m14251a(z);
    }

    public void mo7039d(boolean z) {
        this.c.m14757a(z, this.b);
    }

    public void mo7034a(Location location) {
        this.b.m14714b().m14241a(location);
    }

    public void mo7038b(boolean z) {
        this.b.m14714b().m14259c(z);
    }

    public void mo7125b(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            C4529j.m16280f().m16246b("Invalid App Environment (key,value) pair: (%s,%s).", str, str2);
            return;
        }
        super.mo7125b(str, str2);
    }

    public void mo7036a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            C4529j.m16280f().m16246b("Invalid Error Environment (key,value) pair: (%s,%s).", str, str2);
            return;
        }
        super.mo7036a(str, str2);
    }

    public void mo7037a(boolean z) {
        this.b.m14714b().m14263d(z);
        this.c.m14747a(C4511p.m16190a(), this.b);
    }

    public boolean mo7040h() {
        return this.b.m14714b().m14284q();
    }

    public boolean m16334f() {
        return this.b.m14714b().m14278k();
    }
}
