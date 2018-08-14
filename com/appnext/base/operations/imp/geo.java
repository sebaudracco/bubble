package com.appnext.base.operations.imp;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import com.appnext.base.C1061b;
import com.appnext.base.operations.C1065c;
import com.appnext.base.operations.C1066d;
import com.appnext.base.p019a.p021b.C1020b;
import com.appnext.base.p019a.p021b.C1021c;
import com.appnext.base.p023b.C1042c.C1041a;
import com.appnext.base.p023b.C1043d;
import com.appnext.base.p023b.C1045f;
import com.appnext.base.p023b.C1046g;
import com.appnext.base.p023b.C1055j;
import com.appnext.base.p023b.C1055j.C1054c;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class geo extends C1065c implements C1054c {
    private static final String KEY = geo.class.getSimpleName();
    private C1055j hd;
    private List<C1020b> he;

    public geo(C1021c c1021c, Bundle bundle) {
        super(c1021c, bundle);
    }

    public boolean hasPermission() {
        return bE() && (C1045f.m2133g(C1043d.getContext(), "android.permission.ACCESS_FINE_LOCATION") || C1045f.m2133g(C1043d.getContext(), "android.permission.ACCESS_COARSE_LOCATION"));
    }

    public void bB() {
        try {
            if (hasPermission()) {
                synchronized (this) {
                    this.hd = new C1055j();
                    this.hd.m2160a((C1054c) this);
                    this.hd.init();
                }
                return;
            }
            C1066d.bG().m2200a(this);
        } catch (Throwable th) {
            C1061b.m2191a(th);
        }
    }

    protected boolean bF() {
        return false;
    }

    protected String aX() {
        return geo.class.getSimpleName();
    }

    public void bC() {
        synchronized (this) {
            if (this.hd != null) {
                this.hd.m2160a(null);
                this.hd.cz();
                this.hd = null;
            }
        }
    }

    protected List<C1020b> getData() {
        return this.he;
    }

    public void mo2039a(final Location location) {
        this.hd.m2160a(null);
        C1046g.cw().m2141b(new Runnable(this) {
            final /* synthetic */ geo hg;

            public void run() {
                try {
                    if (location != null) {
                        List fromLocation = new Geocoder(C1043d.getContext(), Locale.getDefault()).getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        if (fromLocation != null && fromLocation.size() > 0) {
                            this.hg.he = new ArrayList();
                            this.hg.he.add(new C1020b(geo.KEY, geo.class.getSimpleName() + "ci", ((Address) fromLocation.get(0)).getLocality(), C1041a.String.getType()));
                            this.hg.he.add(new C1020b(geo.KEY, geo.class.getSimpleName() + "co", ((Address) fromLocation.get(0)).getCountryCode(), C1041a.String.getType()));
                        }
                    }
                } catch (Throwable th) {
                    C1061b.m2191a(th);
                }
                this.hg.bs();
            }
        });
    }

    public void onError(String str) {
        if (this.hd != null) {
            this.hd.m2160a(null);
        }
        C1066d.bG().m2200a(this);
    }

    protected boolean bv() {
        return true;
    }
}
