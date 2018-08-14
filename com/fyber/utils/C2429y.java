package com.fyber.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.fyber.Fyber$Settings.UIStringIdentifier;
import com.fyber.p094b.C2508i;
import java.lang.ref.WeakReference;

/* compiled from: WebClient */
public abstract class C2429y extends WebViewClient {
    private WeakReference<Activity> f6080a;

    /* compiled from: WebClient */
    class C26851 implements OnClickListener {
        final /* synthetic */ C2429y f6673a;

        C26851(C2429y c2429y) {
            this.f6673a = c2429y;
        }

        public final void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
            if (this.f6673a.mo3865a() != null) {
                this.f6673a.mo3865a().finish();
            }
        }
    }

    protected abstract void mo3866a(int i, String str);

    protected abstract void mo3867a(String str, Uri uri);

    protected abstract void mo3868b();

    public C2429y(Activity activity) {
        this.f6080a = new WeakReference(activity);
    }

    protected Activity mo3865a() {
        return (Activity) this.f6080a.get();
    }

    private static String m7677a(String str, String str2) {
        return Uri.parse(str).getQueryParameter(str2);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        FyberLogger.m8451i("WebClient", "shouldOverrideUrlLoading called with url: " + str);
        if (StringUtils.notNullNorEmpty(str) && str.startsWith("sponsorpay://")) {
            Uri parse = Uri.parse(str);
            String host = parse.getHost();
            if ("exit".equals(host)) {
                int parseInt;
                if (C2665m.m8524b(21)) {
                    CookieSyncManager.getInstance().sync();
                } else {
                    CookieManager.getInstance().flush();
                }
                String a = C2429y.m7677a(str, "status");
                if (a != null) {
                    parseInt = Integer.parseInt(a);
                } else {
                    parseInt = -10;
                }
                host = C2429y.m7677a(str, "url");
                FyberLogger.m8451i("WebClient", "Overriding. Target Url: " + host);
                String a2 = C2429y.m7677a(str, "tracking_url");
                if (StringUtils.notNullNorEmpty(a2)) {
                    FyberLogger.m8451i("WebClient", "Overriding. Tracking Url: " + a2);
                    C2508i.m7986a(a2);
                }
                mo3866a(parseInt, host);
            } else {
                mo3867a(host, parse);
            }
            return true;
        }
        FyberLogger.m8451i("WebClient", "Not overriding");
        return false;
    }

    protected final boolean m7681a(String str) {
        Activity a = mo3865a();
        if (a == null) {
            return false;
        }
        if (StringUtils.isURIValid(str)) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri parse = Uri.parse(str);
            intent.setData(parse);
            try {
                a.startActivity(intent);
                mo3868b();
                return true;
            } catch (ActivityNotFoundException e) {
                if (!parse.getScheme().equalsIgnoreCase("market") || C2663k.m8519a(mo3865a(), "android.intent.action.VIEW", Uri.parse("market://search?q=pname:com.google"))) {
                    return false;
                }
                FyberLogger.m8449e("WebClient", "Play Store is not installed on this device...");
                mo3895c();
                return false;
            }
        }
        FyberLogger.m8449e("WebClient", "Invalid url : " + str);
        return false;
    }

    protected void mo3895c() {
        m7683b(C2671s.m8532a(UIStringIdentifier.ERROR_PLAY_STORE_UNAVAILABLE));
    }

    public final void m7683b(String str) {
        CharSequence a = C2671s.m8532a(UIStringIdentifier.ERROR_DIALOG_TITLE);
        CharSequence a2 = C2671s.m8532a(UIStringIdentifier.DISMISS_ERROR_DIALOG);
        Builder builder = new Builder(mo3865a());
        builder.setTitle(a);
        builder.setMessage(str);
        builder.setNegativeButton(a2, new C26851(this));
        AlertDialog create = builder.create();
        create.setOwnerActivity(mo3865a());
        try {
            create.show();
        } catch (Exception e) {
            FyberLogger.m8450e("WebClient", "Couldn't show error dialog. Not displayed error message is: " + str, e);
        }
    }
}
