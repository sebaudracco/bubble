package com.vungle.publisher;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.RequiresApi;
import android.telephony.TelephonyManager;
import com.vungle.publisher.inject.Injector;
import com.vungle.publisher.log.Logger;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class sv implements sz {
    @Inject
    ConnectivityManager f3338a;
    @Inject
    Provider<ta> f3339b;
    @Inject
    TelephonyManager f3340c;

    @Inject
    sv() {
        Injector.c().m4205a(this);
    }

    public sy m4648a() {
        try {
            NetworkInfo activeNetworkInfo = this.f3338a.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return null;
            }
            int type = activeNetworkInfo.getType();
            switch (type) {
                case 0:
                    return sy.a;
                case 1:
                case 6:
                    return sy.b;
                default:
                    Logger.d(Logger.NETWORK_TAG, "unknown connectivity type: " + type);
                    return null;
            }
        } catch (Throwable e) {
            Logger.d(Logger.NETWORK_TAG, "error getting connectivity type", e);
            return null;
        }
    }

    @RequiresApi(api = 16)
    public boolean m4649b() {
        return this.f3338a != null && this.f3338a.isActiveNetworkMetered();
    }
}
