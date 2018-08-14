package com.fyber.reporters;

import android.support.annotation.NonNull;
import com.fyber.reporters.p109a.C2582c;

public class InstallReporter extends AdvertiserReporter {

    class C25831 extends C2582c {
        final /* synthetic */ InstallReporter f6465a;

        C25831(InstallReporter installReporter) {
            this.f6465a = installReporter;
        }

        public final void mo3983a() {
            this.f6465a.a.m8264a(null);
        }

        protected final String mo3984b() {
            return "InstallReporter";
        }
    }

    public static InstallReporter create(@NonNull String str) {
        return new InstallReporter(str);
    }

    private InstallReporter(@NonNull String str) {
        super(str);
    }

    protected final String mo3986c() {
        return "installs";
    }

    protected final String mo3985a() {
        return this.a.m8266b(null);
    }

    protected final String mo3987d() {
        return "InstallReporter";
    }

    protected final C2582c mo3988e() {
        return new C25831(this);
    }
}
