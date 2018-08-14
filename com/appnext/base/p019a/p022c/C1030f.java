package com.appnext.base.p019a.p022c;

import org.json.JSONArray;

public class C1030f extends C1026d {
    public static final String gs = "offline_data_table";

    public C1030f() {
        super(gs);
    }

    public static String bl() {
        return C1026d.m2090b(gs, true);
    }

    public long mo2022a(JSONArray jSONArray) {
        return super.m2075a(gs, jSONArray);
    }
}
