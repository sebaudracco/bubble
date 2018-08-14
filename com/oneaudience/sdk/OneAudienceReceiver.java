package com.oneaudience.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.text.TextUtils;
import com.mopub.common.Constants;
import com.oneaudience.sdk.p135c.C3833d;
import com.oneaudience.sdk.p135c.C3835f;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OneAudienceReceiver extends BroadcastReceiver {
    private static final String f9055a = OneAudienceReceiver.class.getSimpleName();

    private JSONObject m12077a(C3846h c3846h, String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        if (c3846h == null || TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            jSONObject.put("eventType", c3846h.name());
            jSONObject.put("packageName", str);
            jSONObject.put("eventDate", System.currentTimeMillis());
            if (TextUtils.isEmpty(str2)) {
                return jSONObject;
            }
            jSONObject.put("packageVer", str2);
            return jSONObject;
        } catch (JSONException e) {
            C3833d.m12254d(f9055a, "Error creating event json object");
            return null;
        }
    }

    private void m12078a(Context context, SharedPreferences sharedPreferences, Intent intent, Uri uri, String str) {
        C3846h c3846h;
        String str2 = null;
        String schemeSpecificPart = uri.getSchemeSpecificPart();
        boolean booleanExtra = intent.getBooleanExtra("android.intent.extra.REPLACING", false);
        PackageInfo a;
        if (str.equalsIgnoreCase("android.intent.action.PACKAGE_ADDED") && !booleanExtra) {
            c3846h = C3846h.Install;
            a = C3835f.m12263a(context.getPackageManager(), schemeSpecificPart);
            if (a != null) {
                str2 = a.versionName;
            }
        } else if (str.equalsIgnoreCase("android.intent.action.PACKAGE_REPLACED") && booleanExtra) {
            c3846h = C3846h.Update;
            a = C3835f.m12263a(context.getPackageManager(), schemeSpecificPart);
            if (a != null) {
                str2 = a.versionName;
            }
        } else {
            c3846h = (!str.equalsIgnoreCase("android.intent.action.PACKAGE_REMOVED") || booleanExtra) ? null : C3846h.Remove;
        }
        JSONObject a2 = m12077a(c3846h, schemeSpecificPart, str2);
        if (a2 != null) {
            try {
                JSONArray jSONArray = new JSONArray(sharedPreferences.getString(Constants.VIDEO_TRACKING_EVENTS_KEY, "[]"));
                jSONArray.put(a2);
                C3833d.m12256e(f9055a, jSONArray.toString());
                C3833d.m12256e(f9055a, String.valueOf(sharedPreferences.edit().putString(Constants.VIDEO_TRACKING_EVENTS_KEY, jSONArray.toString()).commit()));
            } catch (Throwable e) {
                C3833d.m12250b(f9055a, "Error Converting events string to JSON Array", e);
            }
        }
    }

    public void onReceive(Context context, Intent intent) {
        if (intent == null || context == null) {
            try {
                C3833d.m12252c(f9055a, "Intent or Context is null");
                return;
            } catch (Throwable e) {
                C3833d.m12250b(f9055a, "Error OneAudienceReceiver, onReceive", e);
                return;
            }
        }
        String action = intent.getAction();
        Uri data = intent.getData();
        if (action == null) {
            C3833d.m12252c(f9055a, "Intent Action is null");
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("oneaudience", 0);
        String a = C3843e.m12282a(context, sharedPreferences);
        if (action.equalsIgnoreCase("com.oneaudience.action.CONFIGURATION")) {
            if (TextUtils.isEmpty(a)) {
                C3833d.m12256e(f9055a, "Not Sending Configuration AppKey is Empty");
            } else {
                C3861k.m12321a(context).m12334a(a, false, false);
            }
        } else if (action.equalsIgnoreCase("com.oneaudience.action.COLLECTION")) {
            C3861k.m12321a(context).m12334a(a, false, false);
        } else if (data == null) {
            C3833d.m12252c(f9055a, "Intent Action or Intent Data is null");
        } else if (C3843e.m12288b(context, sharedPreferences)) {
            m12078a(context, sharedPreferences, intent, data, action);
        } else {
            C3833d.m12252c(f9055a, "Not allowed to collect device events");
        }
    }
}
