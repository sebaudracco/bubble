package com.oneaudience.sdk.p133a;

import android.content.Context;
import android.os.SystemClock;
import com.google.android.gms.common.internal.GmsIntents;
import com.oneaudience.sdk.model.CurrentAppsInfo;
import com.oneaudience.sdk.model.ProcessInfo;
import com.oneaudience.sdk.p135c.C3833d;
import com.processes.oneaudience.C3930a;
import com.processes.oneaudience.models.AndroidAppProcess;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class C3797k extends C3784a {
    private static final String[] f9117f = new String[0];
    private static final String[] f9118g = new String[]{"com.google.android.gms", "com.android.vending", "com.google.android.inputmethod.latin", "com.opera.max.oem.meizu", GmsIntents.GOOGLE_NOW_PACKAGE_NAME};

    protected C3797k(Context context, String str, boolean z, boolean z2, long j) {
        super(context, str, z, z2, j, "current_apps_data", "disableCurrentAppsCollector", false, false);
    }

    public String mo6804a() {
        List<AndroidAppProcess> a = C3930a.m12395a(this.c);
        long currentTimeMillis = System.currentTimeMillis();
        long elapsedRealtime = currentTimeMillis - SystemClock.elapsedRealtime();
        ArrayList arrayList = new ArrayList();
        try {
            for (AndroidAppProcess androidAppProcess : a) {
                if (!Arrays.asList(f9118g).contains(androidAppProcess.c)) {
                    arrayList.add(new ProcessInfo(androidAppProcess.c, (10 * androidAppProcess.m12402c().m12420b()) + elapsedRealtime));
                }
            }
        } catch (Throwable th) {
            C3833d.m12256e(a, "Can't get current app");
        }
        return arrayList.isEmpty() ? "" : m12083a((Object) new CurrentAppsInfo(currentTimeMillis, arrayList));
    }

    public String[] mo6805b() {
        return f9117f;
    }
}
