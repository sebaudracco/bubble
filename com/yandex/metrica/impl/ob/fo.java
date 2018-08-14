package com.yandex.metrica.impl.ob;

import com.facebook.ads.AudienceNetworkActivity;
import com.yandex.metrica.impl.ob.fr.C4476a;
import org.json.JSONObject;

public class fo extends fp<JSONObject> {
    protected /* synthetic */ Object mo7103b(ft ftVar) throws fr {
        return m16053a(ftVar);
    }

    public fo(int i, String str, JSONObject jSONObject) {
        super(i, str, jSONObject == null ? null : jSONObject.toString());
    }

    protected JSONObject m16053a(ft ftVar) throws fr {
        C4476a c4476a;
        try {
            return new JSONObject(new String(ftVar.f12428a, fq.m16065a(ftVar.f12429b, AudienceNetworkActivity.WEBVIEW_ENCODING)));
        } catch (Throwable e) {
            c4476a = C4476a.PARSE;
            throw new fr(e);
        } catch (Throwable e2) {
            c4476a = C4476a.PARSE;
            throw new fr(e2);
        }
    }
}
