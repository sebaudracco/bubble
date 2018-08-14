package com.oneaudience.sdk;

import android.content.Context;
import android.net.Uri;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.oneaudience.sdk.p135c.p136a.C3821a;

public class C3845g extends C3839d {
    public static final Uri f9204d = a.buildUpon().appendPath(C1404b.f2104H).build();
    private static final String f9205e = C3845g.class.getSimpleName();

    public C3821a m12293a(Context context, String str) {
        return new C3821a(f9204d.buildUpon().appendPath(str).appendQueryParameter("app_name", C3843e.m12287b(context)).build().toString(), null, null);
    }
}
