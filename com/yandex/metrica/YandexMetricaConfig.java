package com.yandex.metrica;

import android.location.Location;
import com.yandex.metrica.impl.ab;
import com.yandex.metrica.impl.bk;
import java.util.HashMap;
import java.util.Map;

public class YandexMetricaConfig {
    private final String f11430a;
    private final String f11431b;
    private final Integer f11432c;
    private final Boolean f11433d;
    private final Boolean f11434e;
    private final Location f11435f;
    private final Boolean f11436g;
    private final Boolean f11437h;
    private final Boolean f11438i;
    private final PreloadInfo f11439j;
    private final Map<String, String> f11440k;
    private final boolean f11441l;

    public static class Builder {
        private final String f11418a;
        private String f11419b;
        private Integer f11420c;
        private Boolean f11421d;
        private Boolean f11422e;
        private Location f11423f;
        private Boolean f11424g;
        private Boolean f11425h;
        private Boolean f11426i;
        private PreloadInfo f11427j;
        private Map<String, String> f11428k = new HashMap();
        private boolean f11429l;

        protected Builder(String apiKey) {
            bk.m14982a(apiKey);
            this.f11418a = apiKey;
        }

        public Builder setAppVersion(String appVersion) {
            bk.m14983a(appVersion, "App Version");
            this.f11419b = appVersion;
            return this;
        }

        public Builder setSessionTimeout(int sessionTimeout) {
            this.f11420c = Integer.valueOf(sessionTimeout);
            return this;
        }

        public Builder setReportCrashesEnabled(boolean reportCrashesEnabled) {
            this.f11421d = Boolean.valueOf(reportCrashesEnabled);
            return this;
        }

        public Builder setReportNativeCrashesEnabled(boolean reportNativeCrashesEnabled) {
            this.f11422e = Boolean.valueOf(reportNativeCrashesEnabled);
            return this;
        }

        public Builder setLogEnabled() {
            this.f11426i = Boolean.valueOf(true);
            return this;
        }

        public Builder setLocation(Location location) {
            this.f11423f = location;
            return this;
        }

        public Builder setTrackLocationEnabled(boolean trackLocationEnabled) {
            this.f11424g = Boolean.valueOf(trackLocationEnabled);
            return this;
        }

        public Builder setCollectInstalledApps(boolean collectInstalledApps) {
            this.f11425h = Boolean.valueOf(collectInstalledApps);
            return this;
        }

        public Builder setPreloadInfo(PreloadInfo preloadInfo) {
            this.f11427j = preloadInfo;
            return this;
        }

        public Builder putErrorEnvironmentValue(String key, String value) {
            this.f11428k.put(key, value);
            return this;
        }

        public Builder handleFirstActivationAsUpdate(boolean value) {
            this.f11429l = value;
            return this;
        }

        public YandexMetricaConfig build() {
            return new YandexMetricaConfig(this);
        }
    }

    public static Builder newConfigBuilder(String apiKey) {
        return new Builder(apiKey);
    }

    public static YandexMetricaConfig fromJson(String json) {
        return new ab().m14477a(json);
    }

    protected YandexMetricaConfig(Builder builder) {
        this.f11430a = builder.f11418a;
        this.f11431b = builder.f11419b;
        this.f11432c = builder.f11420c;
        this.f11433d = builder.f11421d;
        this.f11434e = builder.f11422e;
        this.f11435f = builder.f11423f;
        this.f11436g = builder.f11424g;
        this.f11437h = builder.f11425h;
        this.f11438i = builder.f11426i;
        this.f11439j = builder.f11427j;
        this.f11440k = builder.f11428k;
        this.f11441l = builder.f11429l;
    }

    protected YandexMetricaConfig(YandexMetricaConfig source) {
        this.f11430a = source.f11430a;
        this.f11431b = source.f11431b;
        this.f11432c = source.f11432c;
        this.f11433d = source.f11433d;
        this.f11434e = source.f11434e;
        this.f11435f = source.f11435f;
        this.f11436g = source.f11436g;
        this.f11437h = source.f11437h;
        this.f11438i = source.f11438i;
        this.f11439j = source.f11439j;
        this.f11440k = source.f11440k;
        this.f11441l = source.f11441l;
    }

    public String getApiKey() {
        return this.f11430a;
    }

    public String getAppVersion() {
        return this.f11431b;
    }

    public Integer getSessionTimeout() {
        return this.f11432c;
    }

    public Boolean isReportCrashEnabled() {
        return this.f11433d;
    }

    public Boolean isReportNativeCrashEnabled() {
        return this.f11434e;
    }

    public Location getLocation() {
        return this.f11435f;
    }

    public Boolean isTrackLocationEnabled() {
        return this.f11436g;
    }

    public Boolean isLogEnabled() {
        return this.f11438i;
    }

    public Boolean isCollectInstalledApps() {
        return this.f11437h;
    }

    public PreloadInfo getPreloadInfo() {
        return this.f11439j;
    }

    public Map<String, String> getErrorEnvironment() {
        return this.f11440k;
    }

    public boolean isFirstActivationAsUpdate() {
        return this.f11441l;
    }

    public String toJson() {
        return new ab().m14478a(this);
    }
}
