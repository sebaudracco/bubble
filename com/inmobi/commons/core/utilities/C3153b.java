package com.inmobi.commons.core.utilities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.mopub.common.Constants;
import java.net.URISyntaxException;
import java.net.URLDecoder;

/* compiled from: ClickUrlHandler */
public class C3153b {
    private static final String f7779a = C3153b.class.getSimpleName();

    public static boolean m10394a(Context context, String str) {
        if (str == null) {
            return false;
        }
        if (context == null) {
            return C3153b.m10396a(Uri.parse(str));
        }
        try {
            if (new Intent("android.intent.action.VIEW", Uri.parse(str)).resolveActivity(context.getPackageManager()) != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean m10395a(Context context, @NonNull String str, @Nullable String str2) {
        if (context == null) {
            return false;
        }
        try {
            Intent parseUri = Intent.parseUri(str, 0);
            if (parseUri.resolveActivity(context.getPackageManager()) != null) {
                parseUri.setFlags(ErrorDialogData.BINDER_CRASH);
                context.startActivity(parseUri);
                return true;
            }
            Logger.m10359a(InternalLogLevel.INTERNAL, f7779a, "No app can handle the:" + str + ", trying with fallback URL if any");
            if (!TextUtils.isEmpty(str2)) {
                return C3153b.m10395a(context, str2, null);
            }
            if (!Constants.INTENT_SCHEME.equals(Uri.parse(str).getScheme())) {
                return false;
            }
            Object b = C3153b.m10398b(str);
            if (TextUtils.isEmpty(b)) {
                return false;
            }
            return C3153b.m10395a(context, URLDecoder.decode(b, "UTF-8"), null);
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7779a, "No app can handle the url:" + str + ", Log : " + e.getMessage());
            return false;
        }
    }

    @Nullable
    private static String m10398b(String str) {
        String str2 = null;
        try {
            str2 = Intent.parseUri(str, 1).getStringExtra("browser_fallback_url");
        } catch (URISyntaxException e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7779a, "Exception while getting Fallback Url :" + e.getMessage());
        }
        return str2;
    }

    private static boolean m10396a(Uri uri) {
        return "http".equals(uri.getScheme()) || "https".equals(uri.getScheme());
    }

    public static boolean m10397a(@NonNull String str) {
        Uri parse = Uri.parse(str);
        return (!C3153b.m10396a(parse) || "play.google.com".equals(parse.getHost()) || "market.android.com".equals(parse.getHost()) || "market".equals(parse.getScheme())) ? false : true;
    }
}
