package com.inmobi.signals;

import com.inmobi.commons.core.network.NetworkRequest;
import com.inmobi.commons.core.network.NetworkRequest.RequestType;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.uid.C3169d;

/* compiled from: IceNetworkRequest */
public class C3273k extends NetworkRequest {
    private static final String f8228d = C3273k.class.getSimpleName();
    private int f8229e;
    private int f8230f;
    private C3274l f8231g;

    public C3273k(String str, int i, int i2, C3169d c3169d, C3274l c3274l) {
        super(RequestType.POST, str, true, c3169d);
        this.f8229e = i;
        this.f8230f = i2;
        this.f8231g = c3274l;
        String jSONObject = this.f8231g.m10965a().toString();
        this.c.put("payload", jSONObject);
        Logger.m10359a(InternalLogLevel.INTERNAL, f8228d, "Ice payload being sent:" + jSONObject);
    }

    public int m10963b() {
        return this.f8229e;
    }

    public int m10964c() {
        return this.f8230f;
    }
}
