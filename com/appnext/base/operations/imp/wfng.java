package com.appnext.base.operations.imp;

import android.net.wifi.ScanResult;
import android.os.Bundle;
import com.appnext.base.p019a.p021b.C1021c;
import com.google.firebase.analytics.FirebaseAnalytics$Param;

public class wfng extends wfpx {
    public wfng(C1021c c1021c, Bundle bundle) {
        super(c1021c, bundle);
    }

    protected String mo2043a(ScanResult scanResult) {
        return scanResult.BSSID;
    }

    protected Object al(String str) {
        return str;
    }

    protected Boolean mo2045f(int i) {
        int i2 = 6;
        C1021c bw = bw();
        if (bw != null) {
            i2 = bw.m2065b(FirebaseAnalytics$Param.LEVEL, 6);
        }
        return Boolean.valueOf(i >= i2);
    }

    protected boolean bv() {
        return true;
    }
}
