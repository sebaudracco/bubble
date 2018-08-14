package com.fyber.ads.ofw;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.fyber.Fyber;
import com.fyber.Fyber$Settings.UIStringIdentifier;
import com.fyber.ads.ofw.p092a.C2443a;
import com.fyber.cache.CacheManager;
import com.fyber.p086a.C2408a;
import com.fyber.utils.C2656g;
import com.fyber.utils.C2671s;
import com.fyber.utils.C2686z;
import com.fyber.utils.FyberLogger;
import java.util.Collections;

public class OfferWallActivity extends Activity {
    public static final String EXTRA_SHOULD_CLOSE_ON_REDIRECT_KEY = "EXTRA_SHOULD_CLOSE_ON_REDIRECT_KEY";
    public static final String EXTRA_URL = "EXTRA_URL";
    public static final String EXTRA_USER_SEGMENTS = "EXTRA_USER_SEGMENTS";
    public static final int RESULT_CODE_NO_STATUS_CODE = -10;
    public static final String TAG = "OfferWallActivity";
    private boolean f6113a;
    private ProgressDialog f6114b;
    private AlertDialog f6115c;
    private String f6116d;
    private String f6117e;
    private C2443a f6118f;
    protected WebView webView;

    class C24421 extends WebChromeClient {
        final /* synthetic */ OfferWallActivity f6112a;

        C24421(OfferWallActivity offerWallActivity) {
            this.f6112a = offerWallActivity;
        }

        public final void onProgressChanged(WebView webView, int i) {
            if (i > 50 && this.f6112a.f6114b != null) {
                this.f6112a.f6114b.dismiss();
                this.f6112a.f6114b = null;
            }
            super.onProgressChanged(webView, i);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (C2656g.m8490f()) {
            getWindow().requestFeature(1);
            this.f6114b = new ProgressDialog(this);
            this.f6114b.setOwnerActivity(this);
            this.f6114b.setIndeterminate(true);
            this.f6114b.setMessage(C2671s.m8532a(UIStringIdentifier.LOADING_OFFERWALL));
            this.f6114b.show();
            fetchPassedExtras();
            this.webView = new WebView(getApplicationContext());
            this.webView.setScrollBarStyle(0);
            setContentView(this.webView);
            C2686z.m8586b(this.webView);
            C2686z.m8584a(this.webView.getSettings());
            C2686z.m8585a(this.webView);
            this.f6118f = new C2443a(this, this.f6113a);
            this.webView.setWebViewClient(this.f6118f);
            this.webView.setWebChromeClient(new C24421(this));
            return;
        }
        setResult(-20);
        finish();
    }

    protected void fetchPassedExtras() {
        Intent intent = getIntent();
        if (!Fyber.getConfigs().m7607h()) {
            SharedPreferences preferences = getPreferences(0);
            String string = preferences.getString("app.id.key", "");
            String string2 = preferences.getString("user.id.key", "");
            String string3 = preferences.getString("security.token.key", "");
            boolean z = preferences.getBoolean("precaching.enabled", false);
            Fyber withSecurityToken = Fyber.with(string, this).withUserId(string2).withSecurityToken(string3);
            if (z) {
                withSecurityToken.withManualPrecaching();
            }
            withSecurityToken.start();
            getPreferences(0).edit().clear().commit();
        }
        this.f6113a = intent.getBooleanExtra(EXTRA_SHOULD_CLOSE_ON_REDIRECT_KEY, shouldCloseOnRedirectDefault());
        this.f6116d = intent.getStringExtra(EXTRA_URL);
        this.f6117e = intent.getStringExtra(EXTRA_USER_SEGMENTS);
    }

    protected void onPause() {
        if (this.f6115c != null) {
            this.f6115c.dismiss();
            this.f6115c = null;
        }
        if (this.f6114b != null) {
            this.f6114b.dismiss();
            this.f6114b = null;
        }
        C2408a i = Fyber.getConfigs().m7608i();
        getPreferences(0).edit().putString("app.id.key", i.m7591a()).putString("user.id.key", i.m7593b()).putString("security.token.key", i.m7594c()).putBoolean("precaching.enabled", CacheManager.m8105a().m8120e()).apply();
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
        try {
            FyberLogger.m8448d(TAG, "Offer Wall request url: " + this.f6116d);
            this.webView.loadUrl(this.f6116d, Collections.singletonMap("X-User-Data", this.f6117e));
        } catch (Exception e) {
            FyberLogger.m8450e(TAG, "An exception occurred when launching the Offer Wall", e);
            this.f6118f.m7683b(e.getMessage());
        }
    }

    public boolean shouldCloseOnRedirectDefault() {
        return false;
    }
}
