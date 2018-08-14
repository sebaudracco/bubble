package com.bgjd.ici.p025b;

import android.net.Uri;
import com.bgjd.ici.p025b.C1408j.C1404b;

public class C1426t {
    private String f2205a;
    private Uri f2206b;
    private String[] f2207c;

    public C1426t(String str, Uri uri) {
        this.f2205a = "";
        this.f2206b = null;
        this.f2207c = new String[]{"_id", "url", C1404b.as, C1404b.at, "date"};
        this.f2205a = str;
        this.f2206b = uri;
    }

    public C1426t(String str, String[] strArr, Uri uri) {
        this.f2205a = "";
        this.f2206b = null;
        this.f2207c = new String[]{"_id", "url", C1404b.as, C1404b.at, "date"};
        this.f2205a = str;
        this.f2207c = strArr;
        this.f2206b = uri;
    }

    public String[] m2944a() {
        return this.f2207c;
    }

    public String m2945b() {
        return this.f2205a;
    }

    public Uri m2946c() {
        return this.f2206b;
    }
}
