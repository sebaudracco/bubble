package com.bgjd.ici.plugin;

import android.content.Context;
import com.bgjd.ici.p025b.C1402i;
import com.bgjd.ici.p030e.C1485h.C1484a.C1483a;

public class MarketPluginIntentSettings {
    private static final String TAG = "MKTPLGSET";

    public static Object getIntentSettings(Context context, String str) {
        try {
            C1520d b = C1532j.m3310b();
            if (b.mo2360b(str)) {
                Object a = b.mo2357a(str);
                a = a.getClass().getMethod(C1483a.m3112h(), new Class[]{Context.class}).invoke(a, new Object[]{context});
                a.getClass().getMethod(C1483a.m3113i(), new Class[]{String.class}).invoke(a, new Object[]{"com.bgjd.ici"});
                a.getClass().getMethod(C1483a.m3114j(), new Class[]{String.class}).invoke(a, new Object[]{"MarketService"});
                return a;
            }
        } catch (Throwable e) {
            C1402i.m2898a(TAG, e, e.getMessage());
        } catch (Throwable e2) {
            C1402i.m2898a(TAG, e2, e2.getMessage());
        } catch (Throwable e22) {
            C1402i.m2898a(TAG, e22, e22.getMessage());
        } catch (Throwable e222) {
            C1402i.m2898a(TAG, e222, e222.getMessage());
        }
        return null;
    }
}
