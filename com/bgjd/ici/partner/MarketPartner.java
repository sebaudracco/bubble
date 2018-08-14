package com.bgjd.ici.partner;

import android.content.Context;
import android.content.Intent;
import com.bgjd.ici.p025b.C1395h;
import com.bgjd.ici.p025b.C1402i;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.bgjd.ici.p025b.aa;
import com.bgjd.ici.partner.IMarketPartner.EULA;

public class MarketPartner implements IMarketPartner {
    private static final String f2491a = "MKTPTNRSDK";
    private Builder f2492b;
    private boolean f2493c;

    public static class Builder {
        private C1516a buildType = C1516a.REGISTRATION;
        private Context context = null;
        private EULA eulaType = EULA.DEFAULT;
        private C1395h marketStatus = null;
        private String partnerEulaActivity = "";
        private boolean sandbox = false;

        public Builder(Context context) {
            this.context = context;
            this.marketStatus = new aa(context);
        }

        public Builder setSandBox(boolean z) {
            this.sandbox = z;
            return this;
        }

        public IMarketPartner build() {
            if (this.marketStatus.mo2192S()) {
                this.buildType = C1516a.LOGGING;
                if (!(this.marketStatus.mo2211a() || this.marketStatus.isAccepted() || this.marketStatus.mo2226c())) {
                    this.buildType = C1516a.REGISTRATION;
                }
                return new MarketPartner();
            }
            C1402i.m2901b("MKT", "This sdk currently don't support custom EULA, please contact support for more information.");
            return null;
        }

        public Builder addIntegrationData(String str, Object obj) {
            this.marketStatus.mo2207a(str, obj);
            return this;
        }

        public Builder setPartnerEulaType(EULA eula) {
            this.eulaType = eula;
            this.marketStatus.mo2280p(eula.getValue());
            return this;
        }

        public Builder setPartnerEula(String str) {
            this.partnerEulaActivity = str;
            this.marketStatus.mo2278o(str);
            return this;
        }
    }

    public enum C1516a {
        REGISTRATION,
        LOGGING,
        f2489c;

        public static C1516a m3260a(String str) {
            return (C1516a) Enum.valueOf(C1516a.class, str);
        }

        public static C1516a[] m3261a() {
            return (C1516a[]) f2490d.clone();
        }
    }

    private MarketPartner(Builder builder) {
        this.f2493c = true;
        this.f2492b = builder;
    }

    public void start() {
        if (this.f2492b.eulaType == EULA.ACTIVITY && this.f2492b.partnerEulaActivity.length() == 0 && this.f2492b.buildType == C1516a.REGISTRATION) {
            C1402i.m2901b(f2491a, "Please set/specify the eula package and activity name.");
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(this.f2492b.context, C1404b.aQ);
        intent.putExtra(C1404b.f2099C, this.f2492b.sandbox);
        String str = C1404b.af;
        switch (this.f2492b.eulaType) {
            case DIALOG:
                if (this.f2492b.buildType == C1516a.REGISTRATION) {
                    str = C1404b.ac;
                    intent.putExtra(C1404b.av, C1404b.ax);
                    break;
                }
                break;
            default:
                if (this.f2492b.buildType == C1516a.REGISTRATION && this.f2493c) {
                    str = C1404b.ac;
                    intent.putExtra(C1404b.av, C1404b.aw);
                    break;
                }
        }
        intent.setAction(str);
        this.f2492b.context.startService(intent);
    }

    public void onEulaAccepted() {
        m3262a(true);
    }

    public void onEulaDeclined() {
        m3262a(false);
    }

    public boolean isEulaShown() {
        return this.f2492b.marketStatus.mo2211a();
    }

    public boolean isEulaAccepted() {
        return this.f2492b.marketStatus.isAccepted();
    }

    public boolean isBlacklisted() {
        return this.f2492b.marketStatus.mo2226c();
    }

    public boolean isMaxDeclineReached() {
        return this.f2492b.marketStatus.mo2193T();
    }

    private void m3262a(boolean z) {
        this.f2492b.marketStatus.mo2225c(true);
        this.f2492b.marketStatus.mo2210a(z);
        this.f2493c = false;
        start();
    }
}
