package com.core42matters.android.registrar;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.mopub.common.GpsHelper;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import org.json.JSONObject;

class SettingsUpdateTask implements Runnable {
    private final AppId appId;
    final Context context;
    private final Store store;

    SettingsUpdateTask(Context context, Store store, AppId appId) {
        if (context == null || store == null) {
            throw new NullPointerException();
        }
        this.context = context;
        this.store = store;
        this.appId = appId;
    }

    public void run() {
        try {
            PackageUtils.validateManifest(this.context);
        } catch (SecurityException e) {
            e.printStackTrace();
            Logger.showToast(this.context, e.getMessage());
        }
        String udid = IdUtils.getUDID(this.context);
        if (!TextUtils.isEmpty(udid)) {
            Bundle params = new Bundle();
            params.putString("udid", udid);
            AdvertisingId advertisingId = AdvertisingId.get(this.context);
            if (advertisingId != null) {
                params.putString(GpsHelper.ADVERTISING_ID_KEY, advertisingId.id);
                params.putBoolean(GpsHelper.IS_LIMIT_AD_TRACKING_ENABLED_KEY, advertisingId.isLimitAdTrackingEnabled);
            }
            try {
                JSONObject jsonResponse = new JSONObject(HttpUtils.requestString(this.context, this.appId, "https://profiler.42matters.com/1/settings", HttpPost.METHOD_NAME, params));
                Logger.m3320i("resp: " + jsonResponse);
                JSONObject jsonSettings = jsonResponse.getJSONObject(C1404b.f2107K);
                jsonSettings.put("lastUpdate", System.currentTimeMillis());
                Logger.m3320i("settings: " + jsonSettings);
                this.store.putSettings(udid, jsonSettings);
            } catch (InvalidApiCallException e2) {
                e2.printStackTrace();
            } catch (Exception e3) {
                Logger.m3319e(e3.getMessage());
            }
        }
    }
}
