package com.fyber.reporters;

import android.support.annotation.NonNull;
import com.fyber.exceptions.IdException;
import com.fyber.reporters.p109a.C2582c;
import com.fyber.utils.C2662j;
import com.fyber.utils.C2672t;

public class RewardedActionReporter extends AdvertiserReporter {
    private String f6467d;

    class C25841 extends C2582c {
        final /* synthetic */ RewardedActionReporter f6466a;

        C25841(RewardedActionReporter rewardedActionReporter) {
            this.f6466a = rewardedActionReporter;
        }

        public final void mo3983a() {
            this.f6466a.a.m8264a(this.f6466a.f6467d);
        }

        protected final String mo3984b() {
            return "RewardedActionReporter";
        }
    }

    public static RewardedActionReporter create(@NonNull String str, @NonNull String str2) throws IdException {
        C2662j.m8518a(str2);
        return new RewardedActionReporter(str, str2);
    }

    private RewardedActionReporter(@NonNull String str, @NonNull String str2) {
        super(str);
        this.f6467d = str2;
    }

    protected final String mo3985a() {
        return this.a.m8266b(this.f6467d);
    }

    protected final String mo3986c() {
        return "actions";
    }

    protected final C2672t mo3980a(C2672t c2672t) {
        return super.mo3980a(c2672t.m8539a("action_id", this.f6467d));
    }

    protected final C2582c mo3988e() {
        return new C25841(this);
    }

    protected final String mo3987d() {
        return "RewardedActionReporter";
    }
}
