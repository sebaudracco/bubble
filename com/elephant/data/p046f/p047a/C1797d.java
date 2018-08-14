package com.elephant.data.p046f.p047a;

import android.os.Handler;
import android.os.Looper;
import com.elephant.data.p046f.C1796h;

final class C1797d implements C1796h {
    final /* synthetic */ C1795c f3784a;

    C1797d(C1795c c1795c) {
        this.f3784a = c1795c;
    }

    public final void mo3541a() {
        this.f3784a.f3783a.f3781c = false;
        new Handler(Looper.getMainLooper()).post(new C1798e(this));
    }
}
