package com.vungle.publisher;

import com.mopub.common.Constants;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: vungle */
public class wa extends vs {
    Float f11195a;
    List<String> f11196b;

    public /* synthetic */ Object mo6940b() throws JSONException {
        return mo6941a();
    }

    protected wa() {
    }

    public Float m14026c() {
        return this.f11195a;
    }

    public List<String> m14027d() {
        return this.f11196b;
    }

    public JSONObject mo6941a() throws JSONException {
        JSONObject a = super.mo6941a();
        a.putOpt("checkpoint", this.f11195a);
        a.putOpt(Constants.VIDEO_TRACKING_URLS_KEY, this.f11196b);
        return a;
    }
}
