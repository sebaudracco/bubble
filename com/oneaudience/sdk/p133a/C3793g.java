package com.oneaudience.sdk.p133a;

import android.content.Context;
import android.telephony.TelephonyManager;

public class C3793g extends C3784a {
    private static final String[] f9106f = new String[0];

    protected C3793g(Context context, String str, boolean z, boolean z2, long j) {
        super(context, str, z, z2, j, "carrier_data", "disableCarrierCollector", true, true);
    }

    public String mo6804a() {
        return ((TelephonyManager) this.c.getSystemService("phone")).getNetworkOperatorName();
    }

    public String[] mo6805b() {
        return f9106f;
    }
}
