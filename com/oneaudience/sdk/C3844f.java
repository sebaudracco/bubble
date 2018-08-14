package com.oneaudience.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import com.oneaudience.sdk.p135c.p136a.C3821a;
import java.util.Map;

class C3844f extends C3839d {
    private static final String f9203d = C3844f.class.getSimpleName();

    C3844f() {
    }

    public C3821a m12292a(Context context, SharedPreferences sharedPreferences, Object obj) {
        Map b = m12274b(context, sharedPreferences);
        Uri parse = Uri.parse(sharedPreferences.getString("report_error_url", "http://robocop.oneaudience.com/reporterror"));
        if (obj instanceof Exception) {
            Exception exception = (Exception) obj;
            b.put("cause", exception.toString());
            b.put("message", exception.getMessage() != null ? exception.getMessage() : "");
            if (exception.getStackTrace() != null) {
                b.put("stack_trace", Log.getStackTraceString(exception));
            }
        } else if (obj instanceof String) {
            b.put("custom_message", (String) obj);
        }
        b.put("logs", C3843e.m12291e(context));
        return new C3821a(parse.toString(), null, b, false);
    }
}
