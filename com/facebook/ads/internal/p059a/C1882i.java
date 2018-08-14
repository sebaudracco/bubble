package com.facebook.ads.internal.p059a;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.facebook.ads.internal.p052j.C1998a.C1996a;
import com.facebook.ads.internal.p056q.p076c.C2149g;
import com.facebook.ads.internal.p069m.C2012c;

public class C1882i extends C1873a {
    private static final String f4147d = C1882i.class.getSimpleName();
    private final Uri f4148e;

    public C1882i(Context context, C2012c c2012c, String str, Uri uri) {
        super(context, c2012c, str);
        this.f4148e = uri;
    }

    public C1996a mo3621a() {
        return C1996a.OPEN_LINK;
    }

    public void mo3622b() {
        try {
            Log.w("REDIRECTACTION: ", this.f4148e.toString());
            C2149g.m6880a(new C2149g(), this.a, this.f4148e, this.c);
        } catch (Throwable e) {
            Log.d(f4147d, "Failed to open link url: " + this.f4148e.toString(), e);
        }
    }
}
