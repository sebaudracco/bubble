package com.inmobi.commons.core.utilities.info;

import android.location.Location;
import com.inmobi.commons.core.p116c.C3116c;
import com.inmobi.commons.p112a.C3105a;
import java.util.HashMap;
import java.util.Locale;

/* compiled from: PublisherProvidedUserInfoDao */
public class C3163e {
    private static int f7802a = Integer.MIN_VALUE;
    private static String f7803b;
    private static String f7804c;
    private static String f7805d;
    private static String f7806e;
    private static String f7807f;
    private static String f7808g;
    private static int f7809h = Integer.MIN_VALUE;
    private static String f7810i;
    private static String f7811j;
    private static String f7812k;
    private static String f7813l;
    private static int f7814m = Integer.MIN_VALUE;
    private static String f7815n;
    private static String f7816o;
    private static String f7817p;
    private static String f7818q;
    private static String f7819r;
    private static Location f7820s;

    public static String m10444a() {
        return C3116c.m10142a("user_info_store");
    }

    public static void m10450b() {
        C3163e.m10446a(f7802a);
        C3163e.m10448a(f7803b);
        C3163e.m10452b(f7804c);
        C3163e.m10455c(f7805d);
        C3163e.m10457d(f7806e);
        C3163e.m10459e(f7807f);
        C3163e.m10461f(f7808g);
        C3163e.m10451b(f7809h);
        C3163e.m10463g(f7810i);
        C3163e.m10465h(f7811j);
        C3163e.m10467i(f7812k);
        C3163e.m10469j(f7813l);
        C3163e.m10454c(f7814m);
        C3163e.m10471k(f7815n);
        C3163e.m10473l(f7816o);
        C3163e.m10475m(f7817p);
        C3163e.m10477n(f7818q);
        C3163e.m10459e(f7819r);
        C3163e.m10447a(f7820s);
    }

    public static void m10446a(int i) {
        if (!C3105a.m10076a() || i == Integer.MIN_VALUE) {
            f7802a = i;
        } else {
            C3116c.m10143b("user_info_store").m10144a("user_age", i);
        }
    }

    private static int m10464h() {
        if (f7802a != Integer.MIN_VALUE) {
            return f7802a;
        }
        return C3116c.m10143b("user_info_store").m10148b("user_age", Integer.MIN_VALUE);
    }

    public static void m10448a(String str) {
        if (!C3105a.m10076a() || str == null) {
            f7803b = str;
        } else {
            C3116c.m10143b("user_info_store").m10146a("user_age_group", str);
        }
    }

    private static String m10466i() {
        if (f7803b != null) {
            return f7803b;
        }
        return C3116c.m10143b("user_info_store").m10150b("user_age_group", null);
    }

    public static void m10452b(String str) {
        if (!C3105a.m10076a() || str == null) {
            f7804c = str;
        } else {
            C3116c.m10143b("user_info_store").m10146a("user_area_code", str);
        }
    }

    private static String m10468j() {
        if (f7804c != null) {
            return f7804c;
        }
        return C3116c.m10143b("user_info_store").m10150b("user_area_code", null);
    }

    public static void m10455c(String str) {
        if (!C3105a.m10076a() || str == null) {
            f7805d = str;
        } else {
            C3116c.m10143b("user_info_store").m10146a("user_post_code", str);
        }
    }

    public static String m10453c() {
        if (f7805d != null) {
            return f7805d;
        }
        return C3116c.m10143b("user_info_store").m10150b("user_post_code", null);
    }

    public static void m10457d(String str) {
        if (!C3105a.m10076a() || str == null) {
            f7806e = str;
        } else {
            C3116c.m10143b("user_info_store").m10146a("user_city_code", str);
        }
    }

    private static String m10470k() {
        if (f7806e != null) {
            return f7806e;
        }
        return C3116c.m10143b("user_info_store").m10150b("user_city_code", null);
    }

    private static String m10472l() {
        if (f7807f != null) {
            return f7807f;
        }
        return C3116c.m10143b("user_info_store").m10150b("user_state_code", null);
    }

    public static void m10459e(String str) {
        if (!C3105a.m10076a() || str == null) {
            f7807f = str;
        } else {
            C3116c.m10143b("user_info_store").m10146a("user_state_code", str);
        }
    }

    public static void m10461f(String str) {
        if (!C3105a.m10076a() || str == null) {
            f7808g = str;
        } else {
            C3116c.m10143b("user_info_store").m10146a("user_country_code", str);
        }
    }

    private static String m10474m() {
        if (f7808g != null) {
            return f7808g;
        }
        return C3116c.m10143b("user_info_store").m10150b("user_country_code", null);
    }

    public static void m10451b(int i) {
        if (!C3105a.m10076a() || i == Integer.MIN_VALUE) {
            f7809h = i;
        } else {
            C3116c.m10143b("user_info_store").m10144a("user_yob", i);
        }
    }

    private static int m10476n() {
        if (f7809h != Integer.MIN_VALUE) {
            return f7809h;
        }
        return C3116c.m10143b("user_info_store").m10148b("user_yob", Integer.MIN_VALUE);
    }

    public static void m10463g(String str) {
        if (!C3105a.m10076a() || str == null) {
            f7810i = str;
        } else {
            C3116c.m10143b("user_info_store").m10146a("user_gender", str);
        }
    }

    private static String m10478o() {
        if (f7810i != null) {
            return f7810i;
        }
        return C3116c.m10143b("user_info_store").m10150b("user_gender", null);
    }

    public static void m10465h(String str) {
        if (!C3105a.m10076a() || str == null) {
            f7811j = str;
        } else {
            C3116c.m10143b("user_info_store").m10146a("user_ethnicity", str);
        }
    }

    private static String m10480p() {
        if (f7811j != null) {
            return f7811j;
        }
        return C3116c.m10143b("user_info_store").m10150b("user_ethnicity", null);
    }

    public static void m10467i(String str) {
        if (!C3105a.m10076a() || str == null) {
            f7812k = str;
        } else {
            C3116c.m10143b("user_info_store").m10146a("user_education", str);
        }
    }

    private static String m10481q() {
        if (f7812k != null) {
            return f7812k;
        }
        return C3116c.m10143b("user_info_store").m10150b("user_education", null);
    }

    private static String m10482r() {
        if (f7813l != null) {
            return f7813l;
        }
        return C3116c.m10143b("user_info_store").m10150b("user_language", null);
    }

    public static void m10469j(String str) {
        if (!C3105a.m10076a() || str == null) {
            f7813l = str;
        } else {
            C3116c.m10143b("user_info_store").m10146a("user_language", str);
        }
    }

    public static void m10454c(int i) {
        if (!C3105a.m10076a() || i == Integer.MIN_VALUE) {
            f7814m = i;
        } else {
            C3116c.m10143b("user_info_store").m10144a("user_income", i);
        }
    }

    private static int m10483s() {
        if (f7814m != Integer.MIN_VALUE) {
            return f7814m;
        }
        return C3116c.m10143b("user_info_store").m10148b("user_income", Integer.MIN_VALUE);
    }

    public static void m10471k(String str) {
        if (!C3105a.m10076a() || str == null) {
            f7815n = str;
        } else {
            C3116c.m10143b("user_info_store").m10146a("user_house_income", str);
        }
    }

    private static String m10484t() {
        if (f7815n != null) {
            return f7815n;
        }
        return C3116c.m10143b("user_info_store").m10150b("user_house_income", null);
    }

    public static void m10473l(String str) {
        if (!C3105a.m10076a() || str == null) {
            f7816o = str;
        } else {
            C3116c.m10143b("user_info_store").m10146a("user_interest", str);
        }
    }

    private static String m10485u() {
        if (f7816o != null) {
            return f7816o;
        }
        return C3116c.m10143b("user_info_store").m10150b("user_interest", null);
    }

    public static void m10475m(String str) {
        if (!C3105a.m10076a() || str == null) {
            f7817p = str;
        } else {
            C3116c.m10143b("user_info_store").m10146a("user_nationality", str);
        }
    }

    private static String m10486v() {
        if (f7817p != null) {
            return f7817p;
        }
        return C3116c.m10143b("user_info_store").m10150b("user_nationality", null);
    }

    public static String m10456d() {
        if (f7818q != null) {
            return f7818q;
        }
        return C3116c.m10143b("user_info_store").m10150b("user_login_id", null);
    }

    public static void m10477n(String str) {
        if (!C3105a.m10076a() || str == null) {
            f7818q = str;
        } else {
            C3116c.m10143b("user_info_store").m10146a("user_login_id", str);
        }
    }

    public static String m10458e() {
        if (f7819r != null) {
            return f7819r;
        }
        return C3116c.m10143b("user_info_store").m10150b("user_session_id", null);
    }

    public static void m10479o(String str) {
        if (!C3105a.m10076a() || str == null) {
            f7819r = str;
        } else {
            C3116c.m10143b("user_info_store").m10146a("user_session_id", str);
        }
    }

    public static Location m10460f() {
        if (f7820s != null) {
            return f7820s;
        }
        String b = C3116c.m10143b("user_info_store").m10150b("user_location", null);
        if (b == null) {
            return null;
        }
        Location location = new Location("");
        try {
            String[] split = b.split(",");
            location.setLatitude(Double.parseDouble(split[0]));
            location.setLongitude(Double.parseDouble(split[1]));
            location.setAccuracy(Float.parseFloat(split[2]));
            location.setTime(Long.parseLong(split[3]));
        } catch (NumberFormatException e) {
            location = null;
        } catch (ArrayIndexOutOfBoundsException e2) {
            location = null;
        }
        return location;
    }

    public static void m10447a(Location location) {
        if (!C3105a.m10076a() || location == null) {
            f7820s = location;
            return;
        }
        C3116c.m10143b("user_info_store").m10146a("user_location", C3163e.m10449b(location));
    }

    public static HashMap<String, String> m10462g() {
        HashMap<String, String> hashMap = new HashMap();
        int h = C3163e.m10464h();
        if (h != Integer.MIN_VALUE && h > 0) {
            hashMap.put("u-age", String.valueOf(h));
        }
        h = C3163e.m10476n();
        if (h != Integer.MIN_VALUE && h > 0) {
            hashMap.put("u-yearofbirth", String.valueOf(h));
        }
        h = C3163e.m10483s();
        if (h != Integer.MIN_VALUE && h > 0) {
            hashMap.put("u-income", String.valueOf(h));
        }
        String a = C3163e.m10445a(C3163e.m10470k(), C3163e.m10472l(), C3163e.m10474m());
        if (!(a == null || a.trim().length() == 0)) {
            hashMap.put("u-location", a);
        }
        a = C3163e.m10466i();
        if (a != null) {
            hashMap.put("u-agegroup", a.toString().toLowerCase(Locale.ENGLISH));
        }
        a = C3163e.m10468j();
        if (a != null) {
            hashMap.put("u-areacode", a);
        }
        a = C3163e.m10453c();
        if (a != null) {
            hashMap.put("u-postalcode", a);
        }
        a = C3163e.m10478o();
        if (a != null) {
            hashMap.put("u-gender", a);
        }
        a = C3163e.m10480p();
        if (a != null) {
            hashMap.put("u-ethnicity", a);
        }
        a = C3163e.m10481q();
        if (a != null) {
            hashMap.put("u-education", a);
        }
        a = C3163e.m10482r();
        if (a != null) {
            hashMap.put("u-language", a);
        }
        a = C3163e.m10484t();
        if (a != null) {
            hashMap.put("u-householdincome", a);
        }
        a = C3163e.m10485u();
        if (a != null) {
            hashMap.put("u-interests", a);
        }
        a = C3163e.m10486v();
        if (a != null) {
            hashMap.put("u-nationality", a);
        }
        return hashMap;
    }

    private static String m10445a(String str, String str2, String str3) {
        String str4 = "";
        if (!(str == null || str.trim().length() == 0)) {
            str4 = str.trim();
        }
        if (!(str2 == null || str2.trim().length() == 0)) {
            str4 = str4 + "-" + str2.trim();
        }
        if (str3 == null || str3.trim().length() == 0) {
            return str4;
        }
        return str4 + "-" + str3.trim();
    }

    private static String m10449b(Location location) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(location.getLatitude());
        stringBuilder.append(",");
        stringBuilder.append(location.getLongitude());
        stringBuilder.append(",");
        stringBuilder.append((int) location.getAccuracy());
        stringBuilder.append(",");
        stringBuilder.append(location.getTime());
        return stringBuilder.toString();
    }
}
