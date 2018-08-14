package com.facebook.ads.internal.p056q.p076c;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.p068l.C2005a;
import com.facebook.ads.internal.settings.C2159a.C2158a;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;

public class C2149g {
    private Intent m6879a(Uri uri) {
        Intent intent = new Intent("android.intent.action.VIEW", uri);
        intent.setComponent(null);
        if (VERSION.SDK_INT >= 15) {
            intent.setSelector(null);
        }
        return intent;
    }

    public static void m6880a(C2149g c2149g, Context context, Uri uri, String str) {
        Object obj = (C2149g.m6882a(uri.getScheme()) && uri.getHost().equals("play.google.com")) ? 1 : null;
        if (uri.getScheme().equals("market") || obj != null) {
            try {
                c2149g.m6886a(context, uri);
                return;
            } catch (C2143c e) {
                c2149g.m6887a(context, uri, str);
                return;
            }
        }
        c2149g.m6887a(context, uri, str);
    }

    private boolean m6881a(Context context) {
        for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse(GooglePlayServicesUtilLight.GOOGLE_PLAY_STORE_URI_STRING)), 0)) {
            if (resolveInfo.activityInfo.applicationInfo.packageName.equals("com.android.vending")) {
                return true;
            }
        }
        return false;
    }

    private static boolean m6882a(String str) {
        return "http".equalsIgnoreCase(str) || "https".equalsIgnoreCase(str);
    }

    private void m6883b(Context context, Uri uri) {
        context.startActivity(m6885c(context, uri));
    }

    private void m6884b(Context context, Uri uri, String str) {
        Intent intent = new Intent();
        intent.setClassName(context.getPackageName(), "com.facebook.ads.AudienceNetworkActivity");
        intent.addFlags(ErrorDialogData.BINDER_CRASH);
        intent.putExtra(AudienceNetworkActivity.VIEW_TYPE, C2158a.BROWSER);
        intent.putExtra(AudienceNetworkActivity.BROWSER_URL, uri.toString());
        intent.putExtra(AudienceNetworkActivity.CLIENT_TOKEN, str);
        intent.putExtra(AudienceNetworkActivity.HANDLER_TIME, System.currentTimeMillis());
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            intent.setClassName(context.getPackageName(), "com.facebook.ads.InterstitialAdActivity");
            try {
                context.startActivity(intent);
            } catch (ActivityNotFoundException e2) {
                m6883b(context, uri);
            }
        }
    }

    private Intent m6885c(Context context, Uri uri) {
        Intent a = m6879a(uri);
        a.addCategory("android.intent.category.BROWSABLE");
        a.addFlags(ErrorDialogData.BINDER_CRASH);
        a.putExtra("com.android.browser.application_id", context.getPackageName());
        a.putExtra("create_new_tab", false);
        return a;
    }

    public void m6886a(Context context, Uri uri) {
        if (m6881a(context)) {
            Intent c = m6885c(context, uri);
            c.setPackage("com.android.vending");
            context.startActivity(c);
            return;
        }
        throw new C2143c();
    }

    public void m6887a(Context context, Uri uri, String str) {
        if (C2149g.m6882a(uri.getScheme()) && C2005a.m6345f(context)) {
            m6884b(context, uri, str);
        } else {
            m6883b(context, uri);
        }
    }
}
