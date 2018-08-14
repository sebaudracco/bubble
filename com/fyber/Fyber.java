package com.fyber;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import com.adcolony.sdk.AdColonyAppOptions;
import com.fyber.a.a;
import com.fyber.annotations.SDKFeatures;
import com.fyber.cache.CacheManager;
import com.fyber.exceptions.IdException;
import com.fyber.mediation.annotations.MediationAPI;
import com.fyber.reporters.a.b;
import com.fyber.utils.FyberLogger;
import com.fyber.utils.StringUtils;
import com.fyber.utils.g;
import java.lang.ref.WeakReference;
import java.util.Locale;
import java.util.Map;

@MediationAPI(5)
@SDKFeatures({"banners"})
public class Fyber {
    public static final String RELEASE_VERSION_STRING = String.format(Locale.ENGLISH, "%s", new Object[]{"8.20.0"});
    private static Fyber f2634a;
    private final Context f2635b;
    private final WeakReference<Activity> f2636c;
    private a f2637d;
    private boolean f2638e = false;

    private Fyber(String str, Activity activity) {
        this.f2637d = new a(str, activity.getApplicationContext());
        this.f2635b = activity.getApplicationContext();
        this.f2636c = new WeakReference(activity);
    }

    public static Fyber with(@NonNull String str, @NonNull Activity activity) throws IllegalArgumentException {
        if (f2634a == null) {
            if (activity == null) {
                throw new IllegalArgumentException("Activity cannot be null.");
            } else if (StringUtils.nullOrEmpty(str)) {
                throw new IllegalArgumentException("App id cannot be null nor empty.");
            } else if (a.b(str)) {
                throw new IllegalArgumentException("Advertisers cannot start the sdk");
            } else {
                synchronized (Fyber.class) {
                    if (f2634a == null) {
                        f2634a = new Fyber(str, activity);
                    }
                }
            }
        } else if (!f2634a.f2638e) {
            f2634a.f2637d.e.a(str);
        }
        return f2634a;
    }

    public Fyber withUserId(String str) {
        if (!this.f2638e && StringUtils.notNullNorEmpty(str)) {
            this.f2637d.e.b(str);
        }
        return this;
    }

    public Fyber withManualPrecaching() {
        if (!this.f2638e) {
            CacheManager.pauseDownloads(this.f2635b);
        }
        return this;
    }

    public Fyber withSecurityToken(String str) {
        if (!this.f2638e) {
            this.f2637d.e.c(str);
        }
        return this;
    }

    public Fyber withParameters(Map<String, String> map) {
        if (!this.f2638e) {
            this.f2637d.c.addParameters(map);
        }
        return this;
    }

    public Settings start() {
        if (!this.f2638e && g.f()) {
            a a = this.f2637d.e.a();
            this.f2638e = true;
            this.f2637d.d = a;
            try {
                b.a(a.a()).report(this.f2635b);
            } catch (IdException e) {
            }
            Activity activity = (Activity) this.f2636c.get();
            if (activity != null) {
                com.fyber.mediation.a.a.a(activity);
            } else {
                FyberLogger.d(AdColonyAppOptions.FYBER, "There was an issue starting the adapters - the activity might have been closed.");
            }
            CacheManager.a().a(this.f2635b);
        }
        return this.f2637d.c;
    }

    public static a getConfigs() {
        if (f2634a != null) {
            return f2634a.f2637d;
        }
        return a.a;
    }
}
