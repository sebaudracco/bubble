package com.elephant.data.p037d.p043c;

import android.content.Context;
import android.os.Build.VERSION;
import com.elephant.data.p048g.C1814b;
import java.util.List;

public final class C1753a {
    private Context f3637a;
    private boolean f3638b;
    private C1764l f3639c;

    static {
        String str = C1814b.iJ;
    }

    public C1753a(Context context) {
        this.f3637a = context;
        this.f3639c = new C1764l(context);
    }

    public final void m5054a(C1756c c1756c) {
        this.f3638b = true;
        if (!this.f3639c.m5078a()) {
            C1757d.m5063a(this.f3637a).m5068b();
        } else if ((c1756c.f3642b == 3 && this.f3639c.m5080c()) || (this.f3639c.m5081d() && c1756c.f3642b == 2)) {
            new C1761h(this.f3637a, c1756c, new C1755b(this)).m5071a();
        } else {
            this.f3638b = false;
        }
    }

    public final void m5055a(boolean z) {
        this.f3638b = z;
    }

    public final boolean m5056a() {
        return (VERSION.SDK_INT < 24 && !this.f3639c.m5079b() && this.f3639c.m5078a()) ? this.f3639c.m5080c() || this.f3639c.m5081d() : false;
    }

    public final int m5057b() {
        return this.f3639c.m5082e();
    }

    public final List m5058c() {
        return this.f3639c.m5083f();
    }

    public final boolean m5059d() {
        return this.f3638b;
    }
}
