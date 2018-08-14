package com.vungle.mediation;

import android.os.Bundle;
import android.util.Log;
import com.bgjd.ici.p025b.C1408j.C1404b;

class AdapterParametersParser {
    private static final String TAG = VungleManager.class.getSimpleName();

    static class Config {
        private String[] allPlacements;
        private String appId;

        Config() {
        }

        String getAppId() {
            return this.appId;
        }

        String[] getAllPlacements() {
            return this.allPlacements;
        }
    }

    AdapterParametersParser() {
    }

    public static Config parse(Bundle networkExtras, Bundle serverParameters) throws IllegalArgumentException {
        String[] placements = null;
        if (networkExtras != null) {
            placements = networkExtras.getStringArray("allPlacements");
        }
        String appId = serverParameters.getString(C1404b.f2147y);
        if (appId == null || appId.isEmpty()) {
            Log.e(TAG, "Vungle app ID should be specified!");
            throw new IllegalArgumentException();
        } else if (placements == null || placements.length == 0) {
            Log.e(TAG, "At least one placement should be specified!");
            throw new IllegalArgumentException();
        } else {
            Config ret = new Config();
            ret.appId = appId;
            ret.allPlacements = placements;
            return ret;
        }
    }
}
