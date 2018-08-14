package com.facebook.ads.internal.p058o;

import android.text.TextUtils;
import com.facebook.ads.internal.settings.AdInternalSettings;

public class C2039d {
    public static String m6539a() {
        if (TextUtils.isEmpty(AdInternalSettings.getUrlPrefix())) {
            return "https://graph.facebook.com/network_ads_common";
        }
        return String.format("https://graph.%s.facebook.com/network_ads_common", new Object[]{AdInternalSettings.getUrlPrefix()});
    }

    public static String m6540b() {
        if (TextUtils.isEmpty(AdInternalSettings.getUrlPrefix())) {
            return "https://www.facebook.com/adnw_logging/";
        }
        return String.format("https://www.%s.facebook.com/adnw_logging/", new Object[]{AdInternalSettings.getUrlPrefix()});
    }
}
