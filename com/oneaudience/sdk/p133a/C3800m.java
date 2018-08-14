package com.oneaudience.sdk.p133a;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import com.bgjd.ici.p025b.C1408j.C1403a;
import com.oneaudience.sdk.model.GameData;
import com.oneaudience.sdk.p135c.C3833d;
import java.util.concurrent.TimeUnit;

public class C3800m extends C3784a {
    protected C3800m(Context context, String str, boolean z, boolean z2, long j) {
        super(context, str, z, z2, j, "game_statistics_data", "disableGameStatisticsCollector", true, true);
    }

    public String mo6804a() {
        m12151i();
        return "";
    }

    public String[] mo6805b() {
        return new String[0];
    }

    public void m12151i() {
        try {
            this.d = true;
            Class cls = Class.forName("com.google.android.gms.games.Games");
            Object obj = cls.getField("API").get(null);
            Object obj2 = cls.getField("SCOPE_GAMES").get(null);
            final Object obj3 = cls.getField("Stats").get(null);
            final Class cls2 = Class.forName("com.google.android.gms.common.api.GoogleApiClient");
            Object newInstance = Class.forName("com.google.android.gms.common.api.GoogleApiClient$Builder").getConstructor(new Class[]{Context.class}).newInstance(new Object[]{this.c});
            newInstance = newInstance.getClass().getMethod("addApi", new Class[]{r1.getType()}).invoke(newInstance, new Object[]{obj});
            newInstance = newInstance.getClass().getMethod("addScope", new Class[]{r5.getType()}).invoke(newInstance, new Object[]{obj2});
            obj = newInstance.getClass().getMethod(C1403a.f2088r, new Class[0]).invoke(newInstance, new Object[0]);
            obj.getClass().getMethod("connect", new Class[0]).invoke(obj, new Object[0]);
            this.d = false;
            final HandlerThread handlerThread = new HandlerThread("MyHandlerThread");
            handlerThread.start();
            new Handler(handlerThread.getLooper()).postDelayed(new Runnable(this) {
                final /* synthetic */ C3800m f9124e;

                public void run() {
                    try {
                        this.f9124e.d = true;
                        if (((Boolean) obj.getClass().getMethod("isConnected", new Class[0]).invoke(obj, new Object[0])).booleanValue()) {
                            Object invoke = obj3.getClass().getMethod("loadPlayerStats", new Class[]{cls2, Boolean.TYPE}).invoke(obj3, new Object[]{obj, Boolean.valueOf(true)});
                            invoke = invoke.getClass().getMethod("await", new Class[]{Long.TYPE, TimeUnit.class}).invoke(invoke, new Object[]{Integer.valueOf(10), TimeUnit.SECONDS});
                            Object invoke2 = invoke.getClass().getMethod("getPlayerStats", new Class[0]).invoke(invoke, new Object[0]);
                            float floatValue = ((Float) invoke2.getClass().getMethod("getAverageSessionLength", new Class[0]).invoke(invoke2, new Object[0])).floatValue();
                            float floatValue2 = ((Float) invoke2.getClass().getMethod("getSpendProbability", new Class[0]).invoke(invoke2, new Object[0])).floatValue();
                            C3833d.m12246a(C3784a.f9072a, "Average Session: " + String.valueOf(floatValue) + ", Spend Probability: " + String.valueOf(floatValue2));
                            this.f9124e.m12084a(this.f9124e.m12083a((Object) new GameData(floatValue, floatValue2)));
                        } else {
                            C3833d.m12246a(C3784a.f9072a, "Failed connect to google play services");
                        }
                        try {
                            obj.getClass().getMethod("disconnect", new Class[0]).invoke(obj, new Object[0]);
                        } catch (Throwable e) {
                            C3833d.m12250b(C3784a.f9072a, "Can't disconnect from google play games services", e);
                        }
                        handlerThread.quit();
                    } catch (Exception e2) {
                        C3833d.m12248a(C3784a.f9072a, "Failed collect game data after connect", e2);
                        try {
                            obj.getClass().getMethod("disconnect", new Class[0]).invoke(obj, new Object[0]);
                        } catch (Throwable e3) {
                            C3833d.m12250b(C3784a.f9072a, "Can't disconnect from google play games services", e3);
                        }
                        handlerThread.quit();
                    } catch (Throwable th) {
                        try {
                            obj.getClass().getMethod("disconnect", new Class[0]).invoke(obj, new Object[0]);
                        } catch (Throwable e4) {
                            C3833d.m12250b(C3784a.f9072a, "Can't disconnect from google play games services", e4);
                        }
                        handlerThread.quit();
                    }
                }
            }, 10000);
        } catch (Throwable th) {
            C3833d.m12250b(a, "Can't get game statistics data", th);
        }
    }
}
