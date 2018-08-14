package com.inmobi.commons.p112a;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.inmobi.commons.core.utilities.C3154c;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: SdkContext */
public final class C3105a {
    private static final String f7597a = C3105a.class.getSimpleName();
    private static Context f7598b;
    private static String f7599c = "";
    private static String f7600d = "";
    private static AtomicBoolean f7601e = new AtomicBoolean();

    public static void m10073a(Context context, String str) {
        if (!C3105a.m10076a()) {
            C3105a.m10071a(context.getApplicationContext());
            f7600d = str;
            f7601e.set(true);
            C3105a.m10085d(context);
            C3105a.m10089f();
        }
    }

    public static boolean m10076a() {
        return f7598b != null;
    }

    @Nullable
    public static Context m10078b() {
        return f7598b;
    }

    public static void m10071a(@Nullable Context context) {
        f7598b = context;
    }

    public static String m10081c() {
        return f7600d;
    }

    public static String m10084d() {
        return f7599c;
    }

    public static boolean m10087e() {
        return f7601e.get();
    }

    public static void m10075a(boolean z) {
        f7601e.set(z);
    }

    public static File m10079b(Context context) {
        return new File(context.getFilesDir(), "im_cached_content");
    }

    private static File m10088f(Context context) {
        return new File(context.getCacheDir(), "im_cached_content");
    }

    public static void m10074a(final File file, final String str) {
        new Thread(new Runnable() {
            public void run() {
                if (str == null || str.trim().length() == 0) {
                    C3154c.m10400a(file);
                } else {
                    C3154c.m10400a(new File(file, str));
                }
            }
        }).start();
    }

    public static void m10083c(@NonNull final Context context) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    File e = C3105a.m10088f(context);
                    if (e.exists()) {
                        C3154c.m10400a(e);
                    }
                } catch (Exception e2) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, C3105a.f7597a, "SDK encountered unexpected error in clearOldMediaCacheDirectory; " + e2.getMessage());
                }
            }
        }).start();
    }

    public static void m10085d(final Context context) {
        if (VERSION.SDK_INT >= 17) {
            f7599c = C3105a.m10091g(context);
        } else {
            new Handler(context.getMainLooper()).post(new Runnable() {
                public void run() {
                    try {
                        C3105a.f7599c = new WebView(context).getSettings().getUserAgentString();
                    } catch (Exception e) {
                        C3105a.m10071a(null);
                        Logger.m10359a(InternalLogLevel.INTERNAL, C3105a.f7597a, "SDK encountered an unexpected error in SdkContext.fetchWebviewUserAgent().handler() method; " + e.getMessage());
                    }
                }
            });
        }
    }

    public static void m10089f() {
        File b = C3105a.m10079b(C3105a.m10078b());
        if (!b.mkdir() && !b.isDirectory()) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7597a, "Cannot create media cache directory");
        }
    }

    @TargetApi(17)
    private static String m10091g(Context context) {
        return WebSettings.getDefaultUserAgent(context.getApplicationContext());
    }

    public static boolean m10077a(String str) {
        Context b = C3105a.m10078b();
        if (b == null) {
            return false;
        }
        PackageManager packageManager = b.getPackageManager();
        if (VERSION.SDK_INT >= 23) {
            return C3105a.m10080b(str);
        }
        if (packageManager.checkPermission(str, packageManager.getNameForUid(Binder.getCallingUid())) == 0) {
            return true;
        }
        return false;
    }

    public static boolean m10080b(String str) {
        Context b = C3105a.m10078b();
        if (b == null) {
            return false;
        }
        try {
            PackageInfo packageInfo = b.getPackageManager().getPackageInfo(b.getPackageName(), 4096);
            if (packageInfo.requestedPermissions == null) {
                return false;
            }
            for (String equals : packageInfo.requestedPermissions) {
                if (equals.equals(str)) {
                    return true;
                }
            }
            return false;
        } catch (NameNotFoundException e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7597a, "Could not check manifest for permission:" + str + " Error:" + e.getLocalizedMessage());
            return false;
        } catch (Exception e2) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7597a, "Could not check manifest for permission:" + str + " Error:" + e2.getLocalizedMessage());
            return false;
        }
    }

    public static void m10072a(Context context, Intent intent) {
        if (!(context instanceof Activity)) {
            intent.setFlags(ErrorDialogData.BINDER_CRASH);
        }
        context.startActivity(intent);
    }
}
