package com.inmobi.signals;

import com.inmobi.commons.core.network.NetworkRequest;
import com.inmobi.commons.core.network.NetworkRequest.RequestType;
import com.inmobi.commons.core.utilities.uid.C3169d;

/* compiled from: CarbGetListNetworkRequest */
public class C3260b extends NetworkRequest {
    private int f8197d;
    private int f8198e;

    public C3260b(String str, int i, int i2, C3169d c3169d) {
        super(RequestType.POST, str, true, c3169d);
        this.f8197d = i;
        this.f8198e = i2;
    }
}
