package com.yandex.metrica.impl;

import android.text.TextUtils;
import com.yandex.metrica.PreloadInfo;
import com.yandex.metrica.impl.utils.C4529j;
import org.json.JSONException;
import org.json.JSONObject;

public class an {
    private PreloadInfo f11643a;

    public an(PreloadInfo preloadInfo) {
        if (preloadInfo == null) {
            return;
        }
        if (TextUtils.isEmpty(preloadInfo.getTrackingId())) {
            C4529j.m16280f().m16250c("Required field \"PreloadInfo.trackingId\" is empty!\nThis preload info will be skipped.");
        } else {
            this.f11643a = preloadInfo;
        }
    }

    String m14601a() {
        if (this.f11643a == null) {
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("preloadInfo", m14602b());
        } catch (JSONException e) {
        }
        return jSONObject.toString();
    }

    public JSONObject m14602b() {
        if (this.f11643a == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("trackingId", this.f11643a.getTrackingId());
            if (this.f11643a.getAdditionalParams().isEmpty()) {
                return jSONObject;
            }
            jSONObject.put("additionalParams", new JSONObject(this.f11643a.getAdditionalParams()));
            return jSONObject;
        } catch (JSONException e) {
            return jSONObject;
        }
    }
}
