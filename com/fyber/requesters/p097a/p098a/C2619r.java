package com.fyber.requesters.p097a.p098a;

import android.text.TextUtils;
import com.fyber.requesters.p097a.C2579k;
import com.fyber.requesters.p097a.C2623c;
import com.fyber.requesters.p097a.C2634m;
import com.fyber.utils.FyberLogger;
import com.fyber.utils.StringUtils;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: UserDataCacheValidator */
public final class C2619r implements C2595e<C2623c> {
    private static final Pattern f6525a = Pattern.compile("(lat|longt)=[^&]*&?");

    public final /* synthetic */ boolean mo3995a(C2602f c2602f, C2579k c2579k) {
        C2623c c2623c = (C2623c) c2579k;
        FyberLogger.m8448d("UserDataCacheValidator", "Checking user data...");
        C2604g c2604g = (C2604g) c2602f.m8344c().mo3970a("CACHE_CONFIG");
        if (c2604g == null || c2604g.m8357d() == null || c2604g.m8357d().length <= 0) {
            String[] strArr = new String[0];
        } else {
            Object[] d = c2604g.m8357d();
        }
        String a = C2619r.m8393a(c2623c.m8419e());
        String a2 = C2619r.m8393a(((C2623c) c2602f.m8344c()).m8419e());
        if (d.length == 0) {
            return C2619r.m8395a(a, a2);
        }
        String str;
        Pattern pattern = (Pattern) c2602f.m8344c().mo3970a("PATTERN_KEY");
        if (pattern == null) {
            pattern = Pattern.compile(("(" + TextUtils.join("|", d) + ")") + "=[^&]*");
            c2602f.m8344c().mo3973b("PATTERN_KEY", pattern);
        }
        Pattern pattern2 = pattern;
        if (c2602f == null) {
            str = null;
        } else {
            str = (String) c2602f.m8344c().mo3970a("MATCHED_USER_DATA_KEY");
            if (str == null) {
                str = C2619r.m8394a(pattern2, a2);
                c2602f.m8344c().mo3973b("MATCHED_USER_DATA_KEY", str);
            }
        }
        return C2619r.m8395a(C2619r.m8394a(pattern2, a), str);
    }

    private static boolean m8395a(String str, String str2) {
        boolean nullOrEmpty = StringUtils.nullOrEmpty(str);
        boolean nullOrEmpty2 = StringUtils.nullOrEmpty(str2);
        if (nullOrEmpty && nullOrEmpty2) {
            FyberLogger.m8448d("UserDataCacheValidator", "User data not provided for both requests - valid. Proceeding...");
            return true;
        } else if (nullOrEmpty != nullOrEmpty2) {
            FyberLogger.m8448d("UserDataCacheValidator", "User data was not provided for one of the requests - invalid");
            return false;
        } else {
            nullOrEmpty2 = str.equals(str2);
            String str3 = "UserDataCacheValidator";
            String str4 = "User data does %smatch for both requests - %s";
            Object[] objArr = new Object[2];
            objArr[0] = nullOrEmpty2 ? "" : "not ";
            objArr[1] = nullOrEmpty2 ? "valid. Proceeding..." : "invalid";
            FyberLogger.m8448d(str3, String.format(str4, objArr));
            return nullOrEmpty2;
        }
    }

    private static String m8393a(C2634m c2634m) {
        String str = (String) c2634m.m8438d().get("X-User-Data");
        if (!StringUtils.notNullNorEmpty(str) || c2634m.m8439e()) {
            return str;
        }
        FyberLogger.m8448d("UserDataCacheValidator", "Auto location enabled - removing lat/longt values, if any...");
        return f6525a.matcher(str).replaceAll("").replaceAll("&$", "");
    }

    private static String m8394a(Pattern pattern, String str) {
        if (StringUtils.nullOrEmpty(str)) {
            return null;
        }
        Iterable arrayList = new ArrayList();
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            arrayList.add(matcher.group());
        }
        return TextUtils.join("&", arrayList);
    }
}
