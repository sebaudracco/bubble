package com.fyber.requesters.p097a;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import com.fyber.utils.C2664l;
import com.fyber.utils.StringUtils;
import java.util.Map;

/* compiled from: CacheDisablerCustomizer */
public final class C2622b implements C2620d {
    private final boolean f6526a;

    public C2622b(Context context) {
        this.f6526a = C2622b.m8399a(context);
    }

    public final void mo4002a(C2623c c2623c, C2634m c2634m) {
        Map g = c2623c.m8421g();
        if (this.f6526a && C2664l.m8521b(g)) {
            String str = "nevergonnagiveyouup";
            if (StringUtils.notNullNorEmpty((String) g.get(str))) {
                c2623c.m8418e(str);
                c2623c.m8402a("DISABLE_CACHE", Boolean.valueOf(true));
            }
        }
    }

    private static boolean m8399a(Context context) {
        boolean z = false;
        if (context != null) {
            try {
                z = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getBoolean("FyberDisableGlobalCache", false);
            } catch (NameNotFoundException e) {
            } catch (NullPointerException e2) {
            }
        }
        return z;
    }
}
