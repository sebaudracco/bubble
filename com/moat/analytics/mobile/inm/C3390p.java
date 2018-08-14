package com.moat.analytics.mobile.inm;

import android.util.Log;
import android.webkit.ValueCallback;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

class C3390p implements ValueCallback<String> {
    final /* synthetic */ C3388n f8614a;

    C3390p(C3388n c3388n) {
        this.f8614a = c3388n;
    }

    public void m11618a(String str) {
        if (str == null || str.equalsIgnoreCase("null") || str.equalsIgnoreCase(SchemaSymbols.ATTVAL_FALSE)) {
            if (this.f8614a.f8607d.mo6482b()) {
                Log.d("MoatJavaScriptBridge", "Received value is:" + (str == null ? "null" : "(String)" + str));
            }
            if (this.f8614a.f8608e == -1 || this.f8614a.f8608e == 50) {
                this.f8614a.m11614g();
            }
            this.f8614a.f8608e = this.f8614a.f8608e + 1;
        } else if (str.equalsIgnoreCase(SchemaSymbols.ATTVAL_TRUE)) {
            this.f8614a.f8608e = -1;
            this.f8614a.m11611e();
        } else if (this.f8614a.f8607d.mo6482b()) {
            Log.d("MoatJavaScriptBridge", "Received unusual value from Javascript:" + str);
        }
    }

    public /* synthetic */ void onReceiveValue(Object obj) {
        m11618a((String) obj);
    }
}
