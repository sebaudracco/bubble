package com.appnext.banners;

import com.appnext.core.C0921q;
import java.util.HashMap;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class C1008c extends C0921q {
    private static C1008c fF;

    public static synchronized C1008c aF() {
        C1008c c1008c;
        synchronized (C1008c.class) {
            if (fF == null) {
                fF = new C1008c();
            }
            c1008c = fF;
        }
        return c1008c;
    }

    private C1008c() {
    }

    protected String getUrl() {
        return "https://cdn.appnext.com/tools/sdk/config/2.2.5/banner_config.txt";
    }

    protected HashMap<String, String> mo1982D() {
        return null;
    }

    protected HashMap<String, String> mo1983E() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("fq_control", SchemaSymbols.ATTVAL_TRUE);
        hashMap.put("urlApp_protection", SchemaSymbols.ATTVAL_TRUE);
        hashMap.put("resolve_timeout", "8");
        hashMap.put("postpone_impression_sec", SchemaSymbols.ATTVAL_FALSE_0);
        hashMap.put("postpone_vta_sec", SchemaSymbols.ATTVAL_FALSE_0);
        hashMap.put("pview", SchemaSymbols.ATTVAL_TRUE);
        hashMap.put("banner_expiration_time", SchemaSymbols.ATTVAL_FALSE_0);
        hashMap.put("ads_caching_time_minutes", SchemaSymbols.ATTVAL_FALSE_0);
        hashMap.put("new_button_text", "Install");
        hashMap.put("existing_button_text", "Open");
        return hashMap;
    }
}
