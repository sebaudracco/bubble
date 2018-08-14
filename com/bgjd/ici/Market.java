package com.bgjd.ici;

import android.app.Activity;
import android.content.Intent;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.bgjd.ici.p025b.aa;

public class Market implements IMarket {
    private static C1393a f2011a = null;
    private static Activity f2012b = null;
    private static boolean f2013c = false;

    public static class Builder {
        private Activity context = null;
        private C1393a marketStatus = null;
        private boolean sandbox = false;

        public Builder(Activity activity) {
            this.context = activity;
            this.marketStatus = new aa(activity);
        }

        public Builder setSandBox(boolean z) {
            this.sandbox = z;
            return this;
        }

        @Deprecated
        public Builder setListener(IMarketDialogStatusListener iMarketDialogStatusListener) {
            return this;
        }

        public IMarket build() {
            return new Market();
        }
    }

    private Market(Builder builder) {
        f2011a = builder.marketStatus;
        f2012b = builder.context;
        f2013c = builder.sandbox;
    }

    private void m2642a() {
        if (!f2011a.mo2226c()) {
            Intent intent = new Intent();
            intent.setClassName(f2012b, C1404b.aQ);
            intent.putExtra(C1404b.f2099C, f2013c);
            if (f2011a.mo2211a()) {
                intent.setAction(C1404b.af);
            } else {
                intent.setAction(C1404b.ac);
                intent.putExtra(C1404b.av, C1404b.aw);
            }
            f2012b.startService(intent);
        }
    }

    public void unregisterReceiver() {
    }

    public void start() {
        m2642a();
    }
}
