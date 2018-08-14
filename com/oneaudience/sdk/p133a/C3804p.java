package com.oneaudience.sdk.p133a;

import android.content.Context;
import android.support.v4.app.NotificationCompat;
import com.oneaudience.sdk.C3820b;
import com.oneaudience.sdk.C3847i;
import com.oneaudience.sdk.model.SocialData;
import com.oneaudience.sdk.p135c.C3823a;
import com.oneaudience.sdk.p135c.C3833d;
import com.oneaudience.sdk.p135c.C3837h;
import com.oneaudience.sdk.p135c.p136a.C3822b;

public class C3804p extends C3784a {
    protected C3804p(Context context, String str, boolean z, boolean z2, long j) {
        super(context, str, z, z2, j, NotificationCompat.CATEGORY_SOCIAL, "disableSocialCollector", true, true);
    }

    public String mo6804a() {
        String str = "";
        String a = C3823a.m12227a();
        if (a != null) {
            C3822b a2 = new C3820b().m12225a(new C3847i().m12294a(this.c, this.b, a));
            if (a2.f9185a != 100000) {
                C3833d.m12257e(a, "Can't get Json From Facebook get Code: %d", Integer.valueOf(a2.f9185a));
            }
            str = a2.f9187c.toString();
        }
        Object a3 = C3837h.m12269a();
        a = a3 != null ? m12083a(a3) : "";
        C3833d.m12246a(a, "FOCEBOOK JSON: " + str);
        C3833d.m12246a(a, "TWITTER JSON: " + a);
        return m12083a((Object) new SocialData(str, a));
    }

    public String[] mo6805b() {
        return new String[0];
    }
}
