package com.fyber.p094b;

import android.support.annotation.NonNull;
import com.fyber.Fyber;
import com.fyber.ads.Ad;
import com.fyber.ads.AdFormat;
import com.fyber.ads.C2410a;
import com.fyber.ads.internal.Offer;
import com.fyber.exceptions.C2565a;
import com.fyber.mediation.C2573a;
import com.fyber.mediation.p108b.C2580a;
import com.fyber.requesters.p097a.C2623c;
import com.fyber.requesters.p097a.p098a.C2604g;
import com.fyber.requesters.p097a.p098a.C2604g.C2603a;
import com.fyber.requesters.p097a.p098a.C2613l.C2612a;
import com.fyber.utils.C2656g;
import com.fyber.utils.C2657h;
import com.fyber.utils.FyberLogger;
import com.fyber.utils.StringUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: AdRequesterNetworkOperation */
public abstract class C2488b<A extends Ad<A, ?>, P extends C2410a<A>> extends C2478g<P> {
    protected boolean f6220a = false;
    protected final C2623c f6221b;

    @NonNull
    protected abstract AdFormat mo3916a();

    @NonNull
    protected abstract P mo3917a(C2623c c2623c, List<Offer> list);

    protected abstract int mo3918b();

    protected final /* synthetic */ Object mo3899a(C2657h c2657h) throws Exception {
        return m7913b(c2657h);
    }

    protected C2488b(@NonNull C2623c c2623c) {
        super(c2623c.m8419e().m8435a(), c2623c.m8419e().m8438d());
        this.f6221b = c2623c;
        this.c = false;
    }

    private P m7913b(C2657h c2657h) throws Exception {
        List arrayList = new ArrayList();
        int b = c2657h.m8464b();
        if (b < 200 || b > 299) {
            throw new C2565a("server_" + b, "ERROR - Status code returned by the server - " + b);
        }
        int i;
        C2604g a;
        String str = (String) c2657h.m8466c();
        if (StringUtils.notNullNorEmpty(str)) {
            Object obj = null;
            i = 0;
            if (this.f6220a) {
                C2656g a2 = Fyber.getConfigs().m7597a();
                obj = a2.m8501c();
                i = a2.m8502d();
            }
            FyberLogger.m8448d(b_(), "Parsing ads response\n" + str);
            try {
                JSONObject jSONObject = new JSONObject(str);
                int optInt = jSONObject.optInt("validation_timeout", 0);
                if (optInt > 180 || optInt <= 0) {
                    optInt = mo3918b();
                }
                a = C2603a.m8352a(jSONObject).m8355a();
                JSONArray jSONArray = jSONObject.getJSONArray("ads");
                int length = jSONArray.length();
                for (int i2 = 0; i2 < length; i2++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                    String string = jSONObject2.getString("provider_type");
                    Object string2 = jSONObject2.getString("ad_id");
                    Offer offer = new Offer(string.toLowerCase(Locale.ENGLISH), string2, this.f6221b.m8413b());
                    offer.setProviderRequest(new C2580a().m8228a(C2580a.f6458a, string2).m8228a("AD_FORMAT", mo3916a()).m8228a("PROVIDER_STATUS", Integer.valueOf(-1)));
                    Offer placementId = offer.setPlacementId(this.f6221b.m8422h());
                    placementId.getProviderRequest().m8228a("CACHE_CONFIG", C2612a.m8381a(jSONObject2).m8382a());
                    m7912a(placementId, jSONObject2.optJSONObject("tracking_params"));
                    string = C2573a.f6454a.m8213a(string);
                    C2580a providerRequest = placementId.getProviderRequest();
                    providerRequest.m8229a("adapter_version", string);
                    JSONArray names = jSONObject2.names();
                    for (int i3 = 0; i3 < names.length(); i3++) {
                        String string3 = names.getString(i3);
                        if (!(string3.equals("ad_id") || string3.equals("provider_type") || string3.equals("tracking_params") || jSONObject2.isNull(string3))) {
                            providerRequest.m8228a(string3, jSONObject2.get(string3));
                        }
                    }
                    if (this.f6220a) {
                        if (!providerRequest.m8235b().containsKey("orientation")) {
                            providerRequest.m8228a("orientation", obj);
                        }
                        providerRequest.m8228a("rotation", Integer.toString(i));
                    }
                    arrayList.add(placementId);
                }
                i = optInt;
            } catch (Exception e) {
                FyberLogger.m8450e(b_(), e.getMessage(), e);
                throw new C2565a("json_parsing", e.getMessage());
            }
        }
        a = null;
        i = 0;
        this.f6221b.m8402a("AD_FORMAT", mo3916a());
        P a3 = mo3917a(this.f6221b, arrayList);
        a3.m7612a(i).m7613a(a);
        return a3;
    }

    private void m7912a(Offer offer, JSONObject jSONObject) {
        if (jSONObject != null) {
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                try {
                    Object obj = jSONObject.get(str);
                    if (obj != null) {
                        offer.getProviderRequest().m8229a(str, obj.toString());
                    }
                } catch (JSONException e) {
                    FyberLogger.m8449e(b_(), e.getMessage());
                }
            }
        }
    }
}
