package com.oneaudience.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import com.oneaudience.sdk.p135c.C3838i;
import com.oneaudience.sdk.p135c.p136a.C3821a;
import java.util.HashMap;
import java.util.Map;

public class C3847i extends C3839d {
    private static final String f9210d = C3847i.class.getSimpleName();
    private static final Uri f9211e = Uri.parse("https://graph.facebook.com/v2.5/me");

    public C3821a m12294a(Context context, SharedPreferences sharedPreferences, String str) {
        Map hashMap = new HashMap();
        hashMap.put("access_token", str);
        hashMap.put("fields", "id,first_name,gender,last_name,link,locale,name,timezone,updated_time,verified,email");
        return new C3821a(C3838i.m12271a(f9211e, hashMap).toString(), null, null, false);
    }
}
