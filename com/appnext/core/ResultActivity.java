package com.appnext.core;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ResultActivity extends Activity {
    private Intent lr;
    private WebView f1810o;

    class C11011 extends WebViewClient {
        final /* synthetic */ ResultActivity ms;

        C11011(ResultActivity resultActivity) {
            this.ms = resultActivity;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (str == null) {
                return false;
            }
            C1128g.m2333W("result page url " + str);
            if (str.startsWith("http")) {
                if (this.ms.hasNewResolver(this.ms.aK(str).setComponent(null))) {
                    this.ms.openLink(str);
                } else {
                    webView.loadUrl(str);
                }
                return true;
            } else if (str.startsWith("intent://")) {
                try {
                    r2 = Intent.parseUri(str, 1);
                    if (this.ms.getPackageManager().resolveActivity(r2, 65536) != null) {
                        this.ms.openLink(r2.getData().toString());
                        return true;
                    }
                    String string;
                    if (r2.getExtras() != null && r2.getExtras().containsKey("browser_fallback_url") && !r2.getExtras().getString("browser_fallback_url").equals("")) {
                        string = r2.getExtras().getString("browser_fallback_url");
                    } else if (!r2.getExtras().containsKey("market_referrer") || r2.getExtras().getString("market_referrer").equals("")) {
                        return true;
                    } else {
                        string = "market://details?id=" + r2.getPackage() + "&referrer=" + r2.getExtras().getString("market_referrer");
                    }
                    this.ms.openLink(string);
                    return true;
                } catch (Throwable th) {
                    C1128g.m2351c(th);
                    return false;
                }
            } else {
                r2 = new Intent("android.intent.action.VIEW");
                r2.setData(Uri.parse(str));
                try {
                    if (this.ms.getPackageManager().queryIntentActivities(r2, 0).size() <= 0) {
                        return false;
                    }
                    this.ms.openLink(str);
                    return true;
                } catch (Throwable th2) {
                    C1128g.m2351c(th2);
                    return false;
                }
            }
        }
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    protected void onCreate(@Nullable Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        View linearLayout = new LinearLayout(this);
        setContentView(linearLayout);
        linearLayout.setOrientation(1);
        this.f1810o = new WebView(getApplicationContext());
        this.f1810o.getSettings().setTextZoom(100);
        this.f1810o.getSettings().setJavaScriptEnabled(true);
        this.f1810o.getSettings().setAllowFileAccess(true);
        this.f1810o.getSettings().setAppCacheEnabled(true);
        this.f1810o.getSettings().setDomStorageEnabled(true);
        this.f1810o.getSettings().setDatabaseEnabled(true);
        if (VERSION.SDK_INT >= 21) {
            this.f1810o.getSettings().setMixedContentMode(0);
        }
        if (VERSION.SDK_INT >= 17) {
            this.f1810o.getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
        if (VERSION.SDK_INT >= 19) {
            this.f1810o.setLayerType(2, null);
        } else {
            this.f1810o.setLayerType(1, null);
        }
        this.f1810o.setWebViewClient(new C11011(this));
        linearLayout.addView(this.f1810o);
        this.f1810o.setLayoutParams(new LayoutParams(-1, 0));
        ((LayoutParams) this.f1810o.getLayoutParams()).weight = 1.0f;
        try {
            String string = getIntent().getExtras().getString("url");
            getIntent().getExtras().getString("title");
            C1128g.m2333W("loading result page " + string);
            this.lr = new Intent(aK(string)).setComponent(null);
            if (VERSION.SDK_INT >= 15) {
                Intent selector = this.lr.getSelector();
                if (selector != null) {
                    selector.setComponent(null);
                }
            }
            this.f1810o.loadUrl(string);
        } catch (Throwable th) {
            C1128g.m2351c(th);
            finish();
        }
    }

    private static List m2296b(Context context, Intent intent) {
        List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
        List arrayList = new ArrayList();
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            arrayList.add(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name));
        }
        return arrayList;
    }

    public final boolean hasNewResolver(Intent intent) {
        if (this.lr == null) {
            boolean z;
            if (intent != null) {
                z = true;
            } else {
                z = false;
            }
            return z;
        } else if (intent == null) {
            return false;
        } else {
            List<ComponentName> b = m2296b((Context) this, intent);
            HashSet hashSet = new HashSet();
            hashSet.addAll(m2296b((Context) this, this.lr));
            for (ComponentName contains : b) {
                if (!hashSet.contains(contains)) {
                    return true;
                }
            }
            return false;
        }
    }

    private Intent aK(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        return intent;
    }

    private void dd() {
        onBackPressed();
    }

    private void openLink(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        intent.addFlags(ErrorDialogData.BINDER_CRASH);
        startActivity(intent);
    }

    public void onBackPressed() {
        try {
            if (this.f1810o == null || !this.f1810o.canGoBack()) {
                super.onBackPressed();
            } else {
                this.f1810o.goBack();
            }
        } catch (Throwable th) {
            C1128g.m2351c(th);
            finish();
        }
    }
}
