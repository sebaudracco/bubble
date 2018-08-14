package com.fyber.mediation;

import com.bgjd.ici.p025b.C1408j.C1404b;

public enum FyberBundleExtras {
    APP_ID(C1404b.f2147y),
    TOKEN("securityToken");
    
    private String mKey;

    private FyberBundleExtras(String pKey) {
        this.mKey = pKey;
    }

    String getKey() {
        return this.mKey;
    }
}
