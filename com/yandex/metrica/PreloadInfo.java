package com.yandex.metrica;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PreloadInfo {
    private String f11416a;
    private Map<String, String> f11417b;

    public static class Builder {
        private String f11414a;
        private Map<String, String> f11415b;

        private Builder(String trackingId) {
            this.f11414a = trackingId;
            this.f11415b = new HashMap();
        }

        public Builder setAdditionalParams(String key, String value) {
            if (!(key == null || value == null)) {
                this.f11415b.put(key, value);
            }
            return this;
        }

        public PreloadInfo build() {
            return new PreloadInfo();
        }
    }

    private PreloadInfo(Builder builder) {
        this.f11416a = builder.f11414a;
        this.f11417b = Collections.unmodifiableMap(builder.f11415b);
    }

    public static Builder newBuilder(String trackingId) {
        return new Builder(trackingId);
    }

    public String getTrackingId() {
        return this.f11416a;
    }

    public Map<String, String> getAdditionalParams() {
        return this.f11417b;
    }
}
