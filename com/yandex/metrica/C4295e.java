package com.yandex.metrica;

import android.location.Location;
import android.text.TextUtils;
import com.yandex.metrica.YandexMetricaConfig.Builder;
import com.yandex.metrica.impl.bk;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

public class C4295e extends YandexMetricaConfig {
    private final C4275a f11537a;
    private final Map<String, String> f11538b;
    private final String f11539c;
    private final String f11540d;
    private final List<String> f11541e;
    private final Integer f11542f;
    private final Integer f11543g;
    private final Integer f11544h;
    private final Map<String, String> f11545i;
    private final Boolean f11546j;
    private final Boolean f11547k;

    public static final class C4294a {
        public String f11524a;
        Boolean f11525b;
        private Builder f11526c;
        private C4275a f11527d;
        private String f11528e;
        private String f11529f;
        private List<String> f11530g;
        private Integer f11531h;
        private Map<String, String> f11532i;
        private Integer f11533j;
        private Integer f11534k;
        private Map<String, String> f11535l = new HashMap();
        private Boolean f11536m;

        protected C4294a(String str) {
            this.f11526c = YandexMetricaConfig.newConfigBuilder(str);
        }

        public C4294a m14391a(String str) {
            this.f11526c.setAppVersion(str);
            return this;
        }

        public C4294a m14387a(int i) {
            this.f11526c.setSessionTimeout(i);
            return this;
        }

        public C4294a m14397b(String str) {
            this.f11524a = str;
            return this;
        }

        public C4294a m14395a(boolean z) {
            this.f11526c.setReportCrashesEnabled(z);
            return this;
        }

        public C4294a m14399b(boolean z) {
            this.f11526c.setReportNativeCrashesEnabled(z);
            return this;
        }

        public C4294a m14386a() {
            this.f11526c.setLogEnabled();
            return this;
        }

        public C4294a m14388a(Location location) {
            this.f11526c.setLocation(location);
            return this;
        }

        public C4294a m14403c(boolean z) {
            this.f11526c.setTrackLocationEnabled(z);
            return this;
        }

        public C4294a m14406d(boolean z) {
            this.f11526c.setCollectInstalledApps(z);
            return this;
        }

        public C4294a m14392a(String str, String str2) {
            this.f11526c.putErrorEnvironmentValue(str, str2);
            return this;
        }

        public C4294a m14390a(C4275a c4275a) {
            this.f11527d = c4275a;
            return this;
        }

        public C4294a m14402c(String str) {
            this.f11528e = str;
            return this;
        }

        public C4294a m14405d(String str) {
            bk.m14983a(str, "Custom Host URL");
            this.f11529f = str;
            return this;
        }

        public C4294a m14393a(List<String> list) {
            this.f11530g = list;
            return this;
        }

        public C4294a m14396b(int i) {
            if (i < 0) {
                throw new IllegalArgumentException(String.format(Locale.US, "Invalid %1$s. %1$s should be positive.", new Object[]{"App Build Number"}));
            }
            this.f11531h = Integer.valueOf(i);
            return this;
        }

        public C4294a m14394a(Map<String, String> map, Boolean bool) {
            this.f11536m = bool;
            this.f11532i = map;
            return this;
        }

        public C4294a m14401c(int i) {
            this.f11534k = Integer.valueOf(i);
            return this;
        }

        public C4294a m14404d(int i) {
            this.f11533j = Integer.valueOf(i);
            return this;
        }

        public C4294a m14389a(PreloadInfo preloadInfo) {
            this.f11526c.setPreloadInfo(preloadInfo);
            return this;
        }

        public C4294a m14407e(boolean z) {
            this.f11526c.handleFirstActivationAsUpdate(z);
            return this;
        }

        public C4294a m14398b(String str, String str2) {
            this.f11535l.put(str, str2);
            return this;
        }

        public C4295e m14400b() {
            return new C4295e();
        }
    }

    public C4295e(YandexMetricaConfig yandexMetricaConfig) {
        super(yandexMetricaConfig);
        this.f11537a = null;
        this.f11538b = null;
        this.f11540d = null;
        this.f11542f = null;
        this.f11543g = null;
        this.f11544h = null;
        this.f11539c = null;
        this.f11545i = null;
        this.f11546j = null;
        this.f11547k = null;
        this.f11541e = null;
    }

    static C4295e m14409a(YandexMetricaConfig yandexMetricaConfig) {
        if (yandexMetricaConfig instanceof C4295e) {
            return (C4295e) yandexMetricaConfig;
        }
        return new C4295e(yandexMetricaConfig);
    }

    public static C4294a m14408a(String str) {
        return new C4294a(str);
    }

    static C4294a m14410b(YandexMetricaConfig yandexMetricaConfig) {
        boolean z;
        C4294a a = C4295e.m14408a(yandexMetricaConfig.getApiKey());
        if (yandexMetricaConfig.getAppVersion() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            a.m14391a(yandexMetricaConfig.getAppVersion());
        }
        if (yandexMetricaConfig.getSessionTimeout() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            a.m14387a(yandexMetricaConfig.getSessionTimeout().intValue());
        }
        if (yandexMetricaConfig.isReportCrashEnabled() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            a.m14395a(yandexMetricaConfig.isReportCrashEnabled().booleanValue());
        }
        if (yandexMetricaConfig.isReportNativeCrashEnabled() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            a.m14399b(yandexMetricaConfig.isReportNativeCrashEnabled().booleanValue());
        }
        if (yandexMetricaConfig.getLocation() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            a.m14388a(yandexMetricaConfig.getLocation());
        }
        if (yandexMetricaConfig.isTrackLocationEnabled() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            a.m14403c(yandexMetricaConfig.isTrackLocationEnabled().booleanValue());
        }
        if (yandexMetricaConfig.isCollectInstalledApps() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            a.m14406d(yandexMetricaConfig.isCollectInstalledApps().booleanValue());
        }
        if (yandexMetricaConfig.isLogEnabled() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z && yandexMetricaConfig.isLogEnabled().booleanValue()) {
            a.m14386a();
        }
        if (yandexMetricaConfig.getPreloadInfo() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            a.m14389a(yandexMetricaConfig.getPreloadInfo());
        }
        if (yandexMetricaConfig.getErrorEnvironment() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            for (Entry entry : yandexMetricaConfig.getErrorEnvironment().entrySet()) {
                a.m14392a((String) entry.getKey(), (String) entry.getValue());
            }
        }
        if (yandexMetricaConfig.isFirstActivationAsUpdate()) {
            a.m14407e(true);
        }
        if (yandexMetricaConfig instanceof C4295e) {
            C4295e c4295e = (C4295e) yandexMetricaConfig;
            if (c4295e.m14412b() != null) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                a.m14405d(c4295e.m14412b());
            }
            if (c4295e.m14413c() != null) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                a.m14393a(c4295e.m14413c());
            }
        }
        return a;
    }

    private C4295e(C4294a c4294a) {
        List d;
        List list = null;
        super(c4294a.f11526c);
        this.f11540d = c4294a.f11528e;
        this.f11542f = c4294a.f11531h;
        if (c4294a.f11530g != null && !c4294a.f11530g.isEmpty()) {
            d = c4294a.f11530g;
        } else if (TextUtils.isEmpty(c4294a.f11529f)) {
            d = null;
        } else {
            d = new ArrayList();
            d.add(c4294a.f11529f);
        }
        if (d != null) {
            list = Collections.unmodifiableList(d);
        }
        this.f11541e = list;
        this.f11537a = c4294a.f11527d;
        this.f11538b = c4294a.f11532i;
        this.f11544h = c4294a.f11534k;
        this.f11543g = c4294a.f11533j;
        this.f11539c = c4294a.f11524a;
        this.f11545i = c4294a.f11535l;
        this.f11546j = c4294a.f11536m;
        this.f11547k = c4294a.f11525b;
    }

    public String m14411a() {
        return this.f11540d;
    }

    public String m14412b() {
        return (this.f11541e == null || this.f11541e.isEmpty()) ? null : (String) this.f11541e.iterator().next();
    }

    public List<String> m14413c() {
        return this.f11541e;
    }

    public Integer m14414d() {
        return this.f11542f;
    }

    public C4275a m14415e() {
        return this.f11537a;
    }

    public Map<String, String> m14416f() {
        return this.f11538b;
    }

    public String m14417g() {
        return this.f11539c;
    }

    public Integer m14418h() {
        return this.f11544h;
    }

    public Integer m14419i() {
        return this.f11543g;
    }

    public Map<String, String> m14420j() {
        return this.f11545i;
    }

    public Boolean m14421k() {
        return this.f11546j;
    }

    public Boolean m14422l() {
        return this.f11547k;
    }
}
