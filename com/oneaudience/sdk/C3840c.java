package com.oneaudience.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.net.Uri.Builder;
import com.a.a.e;
import com.appnext.base.p023b.C1042c;
import com.fyber.mediation.admob.AdMobMediationAdapter;
import com.mopub.common.Constants;
import com.oneaudience.sdk.C3877m.C3876a;
import com.oneaudience.sdk.model.ServerConfig;
import com.oneaudience.sdk.p133a.C3795i;
import com.oneaudience.sdk.p135c.C3833d;
import com.oneaudience.sdk.p135c.p136a.C3821a;
import com.oneaudience.sdk.p135c.p136a.C3822b;
import com.oneaudience.sdk.p135c.p137b.C3826c;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

class C3840c extends C3839d {
    private static final String f9195d = C3840c.class.getSimpleName();
    private static final Uri f9196e = Uri.parse("https://api.oneaudience.com/api");
    private Uri f9197f;

    public C3840c() {
        this(false);
    }

    public C3840c(boolean z) {
        Builder appendPath = f9196e.buildUpon().appendPath("devices");
        if (z) {
            appendPath.appendPath("optout");
        }
        this.f9197f = appendPath.build();
    }

    public C3821a m12275a(Context context, SharedPreferences sharedPreferences) {
        String string;
        boolean z = false;
        Map hashMap = new HashMap();
        try {
            hashMap = m12274b(context, sharedPreferences);
        } catch (Object e) {
            C3833d.m12250b(f9195d, "Error getting basic parameters", (Throwable) e);
            C3843e.m12284a(context, sharedPreferences, e);
        }
        for (String str : C3876a.f9290a) {
            try {
                hashMap.put(str, sharedPreferences.getString(str, ""));
            } catch (Object e2) {
                C3833d.m12250b(f9195d, "Error getting data from collector: " + str, (Throwable) e2);
                C3843e.m12284a(context, sharedPreferences, e2);
            }
        }
        for (String str2 : C3876a.f9291b) {
            try {
                string = sharedPreferences.getString(str2, "");
                if (string.isEmpty()) {
                    hashMap.put(str2, string);
                } else {
                    hashMap.put(str2, new JSONObject(string));
                }
            } catch (Object e22) {
                C3833d.m12250b(f9195d, "Error getting data from collector: " + str2, (Throwable) e22);
                C3843e.m12284a(context, sharedPreferences, e22);
            }
        }
        for (String str22 : C3876a.f9292c) {
            try {
                string = sharedPreferences.getString(str22, "");
                if (string.isEmpty()) {
                    hashMap.put(str22, string);
                } else {
                    hashMap.put(str22, new JSONArray(string));
                }
            } catch (Object e222) {
                C3833d.m12250b(f9195d, "Error getting data from collector: " + str22, (Throwable) e222);
                C3843e.m12284a(context, sharedPreferences, e222);
            }
        }
        hashMap.put("email_setter_get_accounts", Boolean.valueOf(sharedPreferences.getBoolean("email_setter_get_accounts", false)));
        string = hashMap.get("email").toString();
        String string2 = sharedPreferences.getString("email_from_set_function", "");
        String str3 = "email_setter_set_function";
        if (!string2.isEmpty()) {
            z = true;
        }
        hashMap.put(str3, Boolean.valueOf(z));
        if (!(string2.isEmpty() || string.toLowerCase().contains(string2.toLowerCase()))) {
            String str4 = "";
            if (!string.isEmpty()) {
                str4 = string + ",";
            }
            hashMap.put("email", str4 + string2);
        }
        hashMap.put("age", Integer.valueOf(sharedPreferences.getInt("age_from_set_function", -1)));
        hashMap.put(AdMobMediationAdapter.GENDER_KEY, Integer.valueOf(sharedPreferences.getInt("gender_from_set_function", -1)));
        CharSequence string3 = sharedPreferences.getString("oneAudienceId", "");
        if (C3826c.m12234b(string3)) {
            hashMap.put("id", string3);
        }
        hashMap.put("lastOpenDate", Long.valueOf(sharedPreferences.getLong("lastOpenDate", 0)));
        try {
            hashMap.put(Constants.VIDEO_TRACKING_EVENTS_KEY, new JSONArray(sharedPreferences.getString(Constants.VIDEO_TRACKING_EVENTS_KEY, "[]")));
        } catch (Object e3) {
            C3843e.m12284a(context, sharedPreferences, e3);
        }
        return new C3821a(this.f9197f.toString(), new HashMap(), hashMap);
    }

    public C3821a m12276a(Context context, SharedPreferences sharedPreferences, String str) {
        C3821a a = m12275a(context, sharedPreferences);
        try {
            ((HashMap) a.f9184d).put("appKey", str);
        } catch (Object e) {
            C3843e.m12284a(context, sharedPreferences, e);
        }
        return a;
    }

    public void m12277a(Context context, SharedPreferences sharedPreferences, C3822b c3822b) {
        if (c3822b.f9187c instanceof Map) {
            C3833d.m12246a(f9195d, "Got Map from the server");
            String obj = c3822b.f9187c.toString();
            String string = sharedPreferences.getString("server_config", "");
            if (!(obj.isEmpty() || string.equals(obj))) {
                ServerConfig serverConfig = (ServerConfig) new e().a(obj, ServerConfig.class);
                String str = serverConfig.oneAudienceId;
                long j = serverConfig.interval;
                boolean z = serverConfig.optout;
                Editor edit = sharedPreferences.edit();
                edit.putString("server_config", obj);
                edit.putString("oneAudienceId", str);
                edit.putLong(C1042c.jE, j);
                edit.putBoolean("optout", z);
                edit.putBoolean("opt_out_reported", z);
                edit.apply();
                C3795i.m12126a(context).m12132a();
            }
            Editor edit2 = sharedPreferences.edit();
            for (String putString : C3876a.f9293d) {
                edit2.putString(putString, "");
            }
            edit2.apply();
            C3833d.m12252c(f9195d, obj);
            return;
        }
        C3833d.m12256e(f9195d, "Something is wrong didn't get a map from the server");
        if (c3822b.f9187c instanceof String) {
            C3843e.m12284a(context, sharedPreferences, String.format("Configuration Response Got: %s", new Object[]{(String) c3822b.f9187c}));
        }
    }
}
