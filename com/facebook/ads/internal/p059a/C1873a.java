package com.facebook.ads.internal.p059a;

import android.content.Context;
import android.text.TextUtils;
import com.facebook.ads.internal.p052j.C1998a.C1996a;
import com.facebook.ads.internal.p056q.p057a.C2110d;
import com.facebook.ads.internal.p069m.C2012c;
import java.util.Map;

public abstract class C1873a {
    protected final Context f4124a;
    protected final C2012c f4125b;
    protected final String f4126c;

    public C1873a(Context context, C2012c c2012c, String str) {
        this.f4124a = context;
        this.f4125b = c2012c;
        this.f4126c = str;
    }

    public abstract C1996a mo3621a();

    protected void m5630a(Map<String, String> map) {
        if (!TextUtils.isEmpty(this.f4126c)) {
            if (this instanceof C1878e) {
                this.f4125b.mo3716g(this.f4126c, map);
            } else {
                this.f4125b.mo3712c(this.f4126c, map);
            }
        }
        C2110d.m6772a(this.f4124a, "Click logged");
    }

    public abstract void mo3622b();
}
