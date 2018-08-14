package com.fyber.reporters.p109a;

import android.support.annotation.NonNull;
import com.fyber.Fyber;
import com.fyber.exceptions.IdException;
import com.fyber.p086a.C2408a;
import com.fyber.reporters.Reporter;
import com.fyber.utils.C2672t;

/* compiled from: AppStartReporter */
public final class C2587b extends Reporter {

    /* compiled from: AppStartReporter */
    class C25861 extends C2582c {
        final /* synthetic */ C2587b f6469a;

        C25861(C2587b c2587b) {
            this.f6469a = c2587b;
        }

        public final void mo3983a() {
        }

        protected final String mo3984b() {
            return "InstallReporter";
        }
    }

    public static C2587b m8271a(@NonNull String str) throws IdException {
        if (!C2408a.m7590b(str)) {
            return new C2587b(str);
        }
        throw new IdException("Advertiser AppID cannot be used to report an appstart");
    }

    private C2587b(@NonNull String str) {
        super(str);
    }

    protected final C2672t mo3980a(C2672t c2672t) {
        return c2672t.m8541a(true);
    }

    protected final String mo3986c() {
        return "installs";
    }

    protected final String mo3987d() {
        return "InstallReporter";
    }

    protected final C2582c mo3988e() {
        return new C25861(this);
    }

    protected final C2408a mo3981b() {
        return Fyber.getConfigs().m7608i();
    }
}
