package com.areametrics.areametricssdk;

import android.location.Location;
import com.areametrics.areametricssdk.C1338a.C1337a;
import com.areametrics.areametricssdk.C1351c.C1350a;
import com.areametrics.areametricssdk.C1367d.C1361a;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.stepleaderdigital.reveal.Reveal;
import java.util.HashSet;
import java.util.Set;

class C1381h implements C1337a, C1350a, C1361a {
    private final String f2002a = ("AMS-" + C1381h.class.getSimpleName());
    private C1378g f2003b;
    private C1351c f2004c = new C1351c(this);
    private C1338a f2005d = new C1338a(this);
    private C1367d f2006e;
    private long f2007f = 0;
    private Location f2008g = null;
    private int f2009h = 0;
    private int f2010i = 0;

    static /* synthetic */ class C13791 {
        static final /* synthetic */ int[] f1995a = new int[C1380a.m2631a().length];

        static {
            try {
                f1995a[C1380a.f1996a - 1] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f1995a[C1380a.f1997b - 1] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f1995a[C1380a.f1998c - 1] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f1995a[C1380a.f1999d - 1] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    enum C1380a {
        ;
        
        public static final int f1996a = 0;
        public static final int f1997b = 0;
        public static final int f1998c = 0;
        public static final int f1999d = 0;
        public static final int f2000e = 0;

        static {
            f1996a = 1;
            f1997b = 2;
            f1998c = 3;
            f1999d = 4;
            f2000e = 5;
            f2001f = new int[]{f1996a, f1997b, f1998c, f1999d, f2000e};
        }

        public static int[] m2631a() {
            return (int[]) f2001f.clone();
        }
    }

    C1381h() {
    }

    private void m2632d() {
        this.f2004c.m2461a();
        C1338a c1338a = this.f2005d;
        if (c1338a.f1848b != null) {
            c1338a.f1848b.removeCallbacksAndMessages(null);
        }
        c1338a.f1850d = 0;
        c1338a.f1849c = false;
        Location location = this.f2008g;
        this.f2007f = 0;
        Set hashSet = new HashSet();
        if (m2633e() != null) {
            hashSet = m2633e().m2623h();
        }
        if (m2641c() != null) {
            m2641c().m2526a(hashSet, location, this.f2010i);
        }
        this.f2008g = null;
        this.f2009h = 0;
        this.f2010i = 0;
    }

    private C1378g m2633e() {
        return this.f2003b != null ? this.f2003b : AreaMetricsSDK.INSTANCE.getUserData();
    }

    public final void mo2160a() {
        if (this.f2008g != null || this.f2009h != 0) {
            m2632d();
        }
    }

    public final void mo2161a(int i) {
        this.f2009h = i;
        if (!this.f2005d.f1849c) {
            m2632d();
        }
    }

    public final void mo2162a(Location location) {
        this.f2008g = location;
        if (!this.f2005d.f1849c) {
            m2632d();
        }
    }

    public final void mo2163a(Location location, int i) {
        this.f2009h = i;
        if (location != null) {
            this.f2008g = location;
        }
        if (!this.f2005d.f1849c) {
            m2632d();
        }
    }

    public final void mo2165a(Set<String> set) {
        if (set != null && m2633e() != null) {
            m2633e().m2600a((Set) set);
        }
    }

    final void m2639b() {
        if (m2641c() != null) {
            m2641c().m2531e();
        }
    }

    final void m2640b(int i) {
        if (i != 0) {
            switch (C13791.f1995a[i - 1]) {
                case 1:
                    this.f2010i = i;
                    this.f2005d.m2434a(8000);
                    break;
                case 2:
                    this.f2010i = i;
                    this.f2005d.m2434a(Reveal.CHECK_DELAY);
                    break;
                case 3:
                    this.f2010i = i;
                    this.f2005d.m2434a(Reveal.CHECK_DELAY);
                    break;
                case 4:
                    if (this.f2010i == 0) {
                        if (AreaMetricsSDK.INSTANCE.isAppVisible()) {
                            this.f2010i = C1380a.f1998c;
                        } else {
                            this.f2010i = i;
                        }
                    }
                    if (this.f2007f >= 3) {
                        this.f2005d.m2434a(1000);
                        break;
                    } else {
                        this.f2005d.m2434a(3000);
                        break;
                    }
                default:
                    this.f2007f++;
            }
            if (!this.f2004c.f1874a) {
                this.f2009h = 0;
                this.f2004c.m2462a((float) BitmapDescriptorFactory.HUE_ORANGE);
            }
            this.f2007f++;
        }
    }

    final C1367d m2641c() {
        return this.f2006e != null ? this.f2006e : AreaMetricsSDK.INSTANCE.getNetworkController();
    }
}
