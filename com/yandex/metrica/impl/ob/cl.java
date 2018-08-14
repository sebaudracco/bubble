package com.yandex.metrica.impl.ob;

import android.content.pm.FeatureInfo;

public abstract class cl {

    public static class C4432a extends cl {
        public cm mo7066a(FeatureInfo featureInfo) {
            return new cm(featureInfo.name, featureInfo.version, m15599c(featureInfo));
        }
    }

    public static class C4433b extends cl {
        public cm mo7066a(FeatureInfo featureInfo) {
            return new cm(featureInfo.name, m15599c(featureInfo));
        }
    }

    protected abstract cm mo7066a(FeatureInfo featureInfo);

    public cm m15598b(FeatureInfo featureInfo) {
        if (featureInfo.name != null) {
            return mo7066a(featureInfo);
        }
        if (featureInfo.reqGlEsVersion == 0) {
            return mo7066a(featureInfo);
        }
        return new cm("openGlFeature", featureInfo.reqGlEsVersion, m15599c(featureInfo));
    }

    boolean m15599c(FeatureInfo featureInfo) {
        return (featureInfo.flags & 1) != 0;
    }
}
