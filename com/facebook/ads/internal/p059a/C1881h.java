package com.facebook.ads.internal.p059a;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.facebook.ads.internal.p052j.C1998a.C1996a;
import com.facebook.ads.internal.p069m.C2012c;
import com.facebook.ads.internal.p069m.C2015e;
import java.util.Map;

class C1881h extends C1873a {
    private static final String f4144d = C1881h.class.getSimpleName();
    private final Uri f4145e;
    private final Map<String, String> f4146f;

    C1881h(Context context, C2012c c2012c, String str, Uri uri, Map<String, String> map) {
        super(context, c2012c, str);
        this.f4145e = uri;
        this.f4146f = map;
    }

    public C1996a mo3621a() {
        return null;
    }

    public void mo3622b() {
        C2015e c2015e = C2015e.IMMEDIATE;
        Object queryParameter = this.f4145e.getQueryParameter("priority");
        if (!TextUtils.isEmpty(queryParameter)) {
            try {
                c2015e = C2015e.values()[Integer.valueOf(queryParameter).intValue()];
            } catch (Exception e) {
            }
        }
        this.b.mo3710a(this.c, this.f4146f, this.f4145e.getQueryParameter("type"), c2015e);
    }
}
