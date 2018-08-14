package com.fyber.reporters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.webkit.CookieSyncManager;
import com.fyber.Fyber;
import com.fyber.p086a.C2408a;
import com.fyber.p086a.C2408a.C2407a;
import com.fyber.reporters.p109a.C2585a;
import com.fyber.utils.C2665m;
import com.fyber.utils.C2672t;
import com.fyber.utils.StringUtils;

public abstract class AdvertiserReporter extends Reporter {
    protected C2585a f6464a;

    protected abstract String mo3985a();

    protected AdvertiserReporter(@NonNull String str) {
        super(str);
    }

    public boolean report(Context context) {
        this.f6464a = new C2585a(context);
        if (!Fyber.getConfigs().m7607h() && C2665m.m8524b(21)) {
            CookieSyncManager.createInstance(context);
        }
        return super.report(context);
    }

    protected C2672t mo3980a(C2672t c2672t) {
        String a = this.f6464a.m8263a();
        if (StringUtils.notNullNorEmpty(a)) {
            c2672t.m8539a("subid", a);
        }
        a = this.f6464a.m8265b();
        if (StringUtils.notNullNorEmpty(a)) {
            c2672t.m8539a("install_referrer", a);
        }
        return c2672t.m8539a("answer_received", mo3985a()).m8541a(false);
    }

    protected final C2408a mo3981b() {
        return new C2407a(this.b).m7587a();
    }
}
