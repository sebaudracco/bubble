package com.facebook.ads.internal.p059a;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.facebook.ads.internal.p052j.C1998a.C1996a;
import com.facebook.ads.internal.p056q.p076c.C2149g;
import com.facebook.ads.internal.p069m.C2012c;
import java.util.Map;

class C1880g extends C1873a {
    private static final String f4141d = C1880g.class.getSimpleName();
    private final Uri f4142e;
    private final Map<String, String> f4143f;

    C1880g(Context context, C2012c c2012c, String str, Uri uri, Map<String, String> map) {
        super(context, c2012c, str);
        this.f4142e = uri;
        this.f4143f = map;
    }

    public C1996a mo3621a() {
        return C1996a.OPEN_LINK;
    }

    public void mo3622b() {
        m5630a(this.f4143f);
        try {
            C2149g.m6880a(new C2149g(), this.a, Uri.parse(this.f4142e.getQueryParameter("link")), this.c);
        } catch (Throwable e) {
            Log.d(f4141d, "Failed to open link url: " + this.f4142e.toString(), e);
        }
    }
}
