package com.vungle.publisher;

import com.google.firebase.analytics.FirebaseAnalytics$Param;
import com.vungle.publisher.log.Logger;
import javax.inject.Inject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import rx.exceptions.Exceptions;

/* compiled from: vungle */
public abstract class wc$a<R extends wc> extends vy<R> {
    @Inject
    protected m$a f3420a;
    private JSONObject f3421b;

    public /* synthetic */ Object mo3019c(JSONObject jSONObject) throws JSONException {
        return mo3018a(jSONObject);
    }

    public R mo3018a(JSONObject jSONObject) throws JSONException {
        R r = null;
        if (jSONObject != null) {
            r = (wc) b();
            JSONObject d = mo3020d(jSONObject);
            if (d != null) {
                String f = ra.f(d, "placement_reference_id");
                Logger.d(Logger.PROTOCOL_TAG, "Got AdUnit for placement: " + f);
                d = d.getJSONObject("ad_markup");
                if (d != null) {
                    m4774b(d);
                    r.j = f;
                    r.c = ra.f(d, "ad_token");
                    r.i = ra.f(d, "app_id");
                    r.h = ra.d(d, "delay");
                    r.l = ra.f(d, "id");
                    r.f = this.f3420a.m4355a(ra.f(d, VungleAdActivity.AD_TYPE_EXTRA_KEY));
                    r.g = ra.f(d, FirebaseAnalytics$Param.CAMPAIGN);
                    a(d, "id", r.l);
                    a(d, FirebaseAnalytics$Param.CAMPAIGN, r.g);
                    Long e = ra.e(d, "expiry");
                    r.d = e;
                    a(d, "expiry", e);
                    r.b = ra.d(d, "sleep");
                    r.a = ra.f(d, "sleepCode");
                } else {
                    Exceptions.propagate(new Throwable("No ad_markup in v5/api/ads response"));
                }
            } else {
                Exceptions.propagate(new Throwable("No AdPlacementUnit in v5/api/ads response"));
            }
        }
        return r;
    }

    private JSONObject mo3020d(JSONObject jSONObject) throws JSONException {
        if (jSONObject.has("ads")) {
            JSONArray optJSONArray = jSONObject.optJSONArray("ads");
            if (optJSONArray != null) {
                return optJSONArray.getJSONObject(0);
            }
            return null;
        } else if (jSONObject.has("ad_markup")) {
            return jSONObject;
        } else {
            return null;
        }
    }

    public JSONObject m4773a() {
        return this.f3421b;
    }

    public void m4774b(JSONObject jSONObject) {
        this.f3421b = jSONObject;
    }
}
