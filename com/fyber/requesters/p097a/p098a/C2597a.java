package com.fyber.requesters.p097a.p098a;

import com.fyber.mediation.p108b.C2580a;
import com.fyber.requesters.p097a.C2579k;
import com.fyber.utils.FyberLogger;
import java.util.Locale;

/* compiled from: AdIdValidator */
public final class C2597a implements C2595e<C2580a>, C2596p<C2580a> {
    public final /* synthetic */ boolean mo3995a(C2602f c2602f, C2579k c2579k) {
        return C2597a.m8326a((C2580a) c2602f.m8344c(), (C2580a) c2579k);
    }

    private static boolean m8326a(C2580a c2580a, C2580a c2580a2) {
        String str = (String) c2580a2.mo3971a(C2580a.f6458a, String.class, "no_id");
        String str2 = (String) c2580a.mo3971a(C2580a.f6458a, String.class, "no_id");
        boolean equals = str.equals(str2);
        String str3 = "AdIdValidator";
        Locale locale = Locale.ENGLISH;
        String str4 = "Cached ad id = %s\nRequest ad id = %s\nIDs %smatch for both requests - %s";
        Object[] objArr = new Object[4];
        objArr[0] = str2;
        objArr[1] = str;
        objArr[2] = equals ? "" : "don't ";
        objArr[3] = equals ? "valid. Proceeding..." : "invalid";
        FyberLogger.m8448d(str3, String.format(locale, str4, objArr));
        return equals;
    }
}
