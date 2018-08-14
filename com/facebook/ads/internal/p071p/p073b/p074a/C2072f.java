package com.facebook.ads.internal.p071p.p073b.p074a;

import android.text.TextUtils;
import com.facebook.ads.internal.p071p.p073b.C2095m;

public class C2072f implements C2066c {
    private String m6658b(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        return (lastIndexOf == -1 || lastIndexOf <= str.lastIndexOf(47) || (lastIndexOf + 2) + 4 <= str.length()) ? "" : str.substring(lastIndexOf + 1, str.length());
    }

    public String mo3753a(String str) {
        Object b = m6658b(str);
        String d = C2095m.m6742d(str);
        return TextUtils.isEmpty(b) ? d : d + "." + b;
    }
}
