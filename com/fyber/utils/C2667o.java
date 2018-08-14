package com.fyber.utils;

import android.text.TextUtils;
import com.fyber.Fyber;
import java.util.HashMap;
import java.util.Map;

/* compiled from: SDKParamsProvider */
public final class C2667o implements C2648n {
    private static final String[] f6625a = new String[]{"MPI", "VPL", "JUD", "BLE", "INV", "IVE"};
    private Map<String, String> f6626b = new C26661(this);

    /* compiled from: SDKParamsProvider */
    class C26661 extends HashMap<String, String> {
        final /* synthetic */ C2667o f6624a;

        C26661(C2667o c2667o) {
            this.f6624a = c2667o;
            put("sdk_version", Fyber.RELEASE_VERSION_STRING);
            put("platform", "android");
            put("client", "sdk");
            put("sdk_features", TextUtils.join(",", C2667o.f6625a));
        }
    }

    public final synchronized Map<String, String> mo4003a() {
        return this.f6626b;
    }
}
