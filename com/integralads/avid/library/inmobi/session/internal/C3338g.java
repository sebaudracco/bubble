package com.integralads.avid.library.inmobi.session.internal;

import android.content.Context;
import com.integralads.avid.library.inmobi.p127g.C3319b;
import com.integralads.avid.library.inmobi.session.C3329g;

/* compiled from: InternalAvidManagedVideoAdSession */
public class C3338g extends C3337f {
    private C3319b f8495a = new C3319b(this, m11406i());

    public C3338g(Context context, String str, C3329g c3329g) {
        super(context, str, c3329g);
    }

    public C3319b m11431v() {
        return this.f8495a;
    }

    public SessionType mo6357a() {
        return SessionType.MANAGED_VIDEO;
    }

    public MediaType mo6358b() {
        return MediaType.VIDEO;
    }

    public void mo6360l() {
        this.f8495a.m11192a();
        super.mo6360l();
    }
}
