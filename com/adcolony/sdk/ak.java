package com.adcolony.sdk;

import android.os.Bundle;
import java.util.HashMap;

class ak {
    static final int f544a = 5;
    static final int f545b = 1;
    static final int f546c = 3;
    static final int f547d = 0;
    static final int f548e = 1;
    private static int f549f;
    private static HashMap<String, Integer> f550g = new HashMap();
    private static HashMap<String, Integer> f551h = new HashMap();

    ak() {
    }

    static boolean m717a(int i, Bundle bundle) {
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        switch (i) {
            case 0:
                if (currentTimeMillis - f549f < 5) {
                    return true;
                }
                f549f = currentTimeMillis;
                return false;
            case 1:
                if (bundle == null) {
                    return false;
                }
                String string = bundle.getString("zone_id");
                if (f550g.get(string) == null) {
                    f550g.put(string, Integer.valueOf(currentTimeMillis));
                }
                if (f551h.get(string) == null) {
                    f551h.put(string, Integer.valueOf(0));
                }
                if (currentTimeMillis - ((Integer) f550g.get(string)).intValue() > 1) {
                    f551h.put(string, Integer.valueOf(1));
                    f550g.put(string, Integer.valueOf(currentTimeMillis));
                    return false;
                }
                int intValue = ((Integer) f551h.get(string)).intValue() + 1;
                f551h.put(string, Integer.valueOf(intValue));
                if (intValue > 3) {
                    return true;
                }
                return false;
            default:
                return false;
        }
    }
}
