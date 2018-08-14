package com.inmobi.signals;

import com.inmobi.commons.core.network.NetworkRequest;
import com.inmobi.commons.core.network.NetworkRequest.RequestType;
import com.inmobi.commons.core.utilities.uid.C3169d;
import java.util.List;
import org.json.JSONArray;

/* compiled from: CarbPostListNetworkRequest */
public class C3264f extends NetworkRequest {
    private int f8208d;
    private int f8209e;
    private C3261c f8210f;
    private List<C3262d> f8211g;

    public C3264f(String str, int i, int i2, C3169d c3169d, List<C3262d> list, C3261c c3261c) {
        super(RequestType.POST, str, true, c3169d);
        this.f8208d = i;
        this.f8209e = i2;
        this.f8211g = list;
        this.f8210f = c3261c;
        this.c.put("req_id", this.f8210f.m10919c());
        this.c.put("i_till", Integer.toString(this.f8210f.m10920d()));
        this.c.put("p_a_apps", m10926d());
    }

    private String m10926d() {
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < this.f8211g.size(); i++) {
            jSONArray.put(((C3262d) this.f8211g.get(i)).m10923b());
        }
        return jSONArray.toString();
    }

    public int m10927b() {
        return this.f8208d;
    }

    public int m10928c() {
        return this.f8209e;
    }
}
