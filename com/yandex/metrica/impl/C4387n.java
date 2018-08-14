package com.yandex.metrica.impl;

import android.location.Location;
import com.yandex.metrica.C4295e;
import com.yandex.metrica.C4295e.C4294a;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class C4387n implements ac {
    private Integer f11882a;
    private Boolean f11883b;
    private Boolean f11884c;
    private Location f11885d;
    private Boolean f11886e;
    private String f11887f;
    private Boolean f11888g;
    private Map<String, String> f11889h = new HashMap();
    private Map<String, String> f11890i = new HashMap();
    private boolean f11891j;
    private boolean f11892k;

    public Integer m15119a() {
        return this.f11882a;
    }

    public Boolean m15124b() {
        return this.f11883b;
    }

    public Boolean m15126c() {
        return this.f11884c;
    }

    public Location m15128d() {
        return this.f11885d;
    }

    public Boolean m15130e() {
        return this.f11886e;
    }

    public String m15131f() {
        return this.f11887f;
    }

    public Boolean m15132g() {
        return this.f11888g;
    }

    public boolean mo7040h() {
        if (this.f11888g == null) {
            return false;
        }
        return this.f11888g.booleanValue();
    }

    public void mo7037a(boolean z) {
        this.f11888g = Boolean.valueOf(z);
    }

    public void mo7035a(String str) {
        this.f11887f = str;
    }

    public void mo7038b(boolean z) {
        this.f11886e = Boolean.valueOf(z);
    }

    public void setSessionTimeout(int sessionTimeout) {
        this.f11882a = Integer.valueOf(sessionTimeout);
    }

    public void m15127c(boolean z) {
        this.f11883b = Boolean.valueOf(z);
    }

    public void mo7034a(Location location) {
        this.f11885d = location;
    }

    public void mo7039d(boolean z) {
        this.f11884c = Boolean.valueOf(z);
    }

    public boolean m15134i() {
        return this.f11891j;
    }

    public void mo7036a(String str, String str2) {
        this.f11890i.put(str, str2);
    }

    public C4295e m15118a(C4295e c4295e) {
        if (this.f11892k) {
            return c4295e;
        }
        boolean z;
        C4294a a = C4295e.m14408a(c4295e.getApiKey());
        a.m14394a(c4295e.m14416f(), c4295e.m14421k());
        a.m14390a(c4295e.m14415e());
        a.m14389a(c4295e.getPreloadInfo());
        a.m14402c(c4295e.m14411a());
        a.m14388a(c4295e.getLocation());
        if (c4295e.m14413c() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            a.m14393a(c4295e.m14413c());
        }
        if (c4295e.getAppVersion() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            a.m14391a(c4295e.getAppVersion());
        }
        if (c4295e.m14419i() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            a.m14404d(c4295e.m14419i().intValue());
        }
        if (c4295e.m14414d() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            a.m14396b(c4295e.m14414d().intValue());
        }
        if (c4295e.m14418h() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            a.m14401c(c4295e.m14418h().intValue());
        }
        if (c4295e.isLogEnabled() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z && c4295e.isLogEnabled().booleanValue()) {
            a.m14386a();
        }
        if (c4295e.getSessionTimeout() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            a.m14387a(c4295e.getSessionTimeout().intValue());
        }
        if (c4295e.isReportCrashEnabled() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            a.m14395a(c4295e.isReportCrashEnabled().booleanValue());
        }
        if (c4295e.isReportNativeCrashEnabled() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            a.m14399b(c4295e.isReportNativeCrashEnabled().booleanValue());
        }
        if (c4295e.isTrackLocationEnabled() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            a.m14403c(c4295e.isTrackLocationEnabled().booleanValue());
        }
        if (c4295e.isCollectInstalledApps() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            a.m14406d(c4295e.isCollectInstalledApps().booleanValue());
        }
        if (c4295e.m14417g() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            a.m14397b(c4295e.m14417g());
        }
        if (c4295e.isFirstActivationAsUpdate()) {
            a.m14407e(true);
        }
        C4387n.m15116a(c4295e.m14420j(), a);
        C4387n.m15117b(c4295e.getErrorEnvironment(), a);
        Integer a2 = m15119a();
        if (c4295e.getSessionTimeout() == null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (a2 != null) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                a.m14387a(a2.intValue());
            }
        }
        Boolean b = m15124b();
        if (c4295e.isReportCrashEnabled() == null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (b != null) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                a.m14395a(b.booleanValue());
            }
        }
        b = m15126c();
        if (c4295e.isReportNativeCrashEnabled() == null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (b != null) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                a.m14399b(b.booleanValue());
            }
        }
        b = m15130e();
        if (c4295e.isTrackLocationEnabled() == null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (b != null) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                a.m14403c(b.booleanValue());
            }
        }
        Location d = m15128d();
        if (c4295e.getLocation() == null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (d != null) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                a.m14388a(d);
            }
        }
        b = m15132g();
        if (c4295e.isCollectInstalledApps() == null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (b != null) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                a.m14406d(b.booleanValue());
            }
        }
        String f = m15131f();
        if (c4295e.getAppVersion() == null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (f != null) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                a.m14391a(f);
            }
        }
        C4387n.m15116a(this.f11889h, a);
        C4387n.m15117b(this.f11890i, a);
        this.f11892k = true;
        this.f11882a = null;
        this.f11883b = null;
        this.f11884c = null;
        this.f11885d = null;
        this.f11886e = null;
        this.f11887f = null;
        this.f11888g = null;
        this.f11889h.clear();
        this.f11890i.clear();
        this.f11891j = false;
        return a.m14400b();
    }

    private static void m15116a(Map<String, String> map, C4294a c4294a) {
        if (!bk.m14988a((Map) map)) {
            for (Entry entry : map.entrySet()) {
                c4294a.m14398b((String) entry.getKey(), (String) entry.getValue());
            }
        }
    }

    private static void m15117b(Map<String, String> map, C4294a c4294a) {
        if (!bk.m14988a((Map) map)) {
            for (Entry entry : map.entrySet()) {
                c4294a.m14392a((String) entry.getKey(), (String) entry.getValue());
            }
        }
    }
}
