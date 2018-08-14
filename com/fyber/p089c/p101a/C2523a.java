package com.fyber.p089c.p101a;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.TextUtils.TruncateAt;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.fyber.C2409a;
import com.fyber.Fyber;
import com.fyber.Fyber$Settings.UIStringIdentifier;
import com.fyber.p089c.p102b.C2526b;
import com.fyber.utils.C2412c;
import com.fyber.utils.C2671s;
import com.fyber.utils.C2686z;
import com.fyber.utils.StringUtils;

/* compiled from: MicroBrowser */
public final class C2523a extends LinearLayout {
    C2522d f6253a;
    private WebView f6254b;
    private TextView f6255c;
    private TextView f6256d;
    private boolean f6257e;
    private String f6258f;
    private C2520b f6259g;
    private C2519a f6260h;
    private C2521c f6261i;
    private InputMethodManager f6262j;

    /* compiled from: MicroBrowser */
    class C25171 implements OnClickListener {
        final /* synthetic */ C2523a f6249a;

        C25171(C2523a c2523a) {
            this.f6249a = c2523a;
        }

        public final void onClick(View view) {
            C2523a.m8010a(this.f6249a);
        }
    }

    /* compiled from: MicroBrowser */
    public interface C2519a {
        void mo3930a();
    }

    /* compiled from: MicroBrowser */
    public interface C2520b {
        void mo3929a();
    }

    /* compiled from: MicroBrowser */
    public interface C2521c {
        boolean mo3931a(C2523a c2523a, String str);
    }

    /* compiled from: MicroBrowser */
    class C2522d extends WebViewClient {
        final /* synthetic */ C2523a f6252a;

        C2522d(C2523a c2523a) {
            this.f6252a = c2523a;
        }

        public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
            boolean z = this.f6252a.f6261i != null && this.f6252a.f6261i.mo3931a(this.f6252a, str);
            if (!z) {
                this.f6252a.f6255c.setText(C2671s.m8532a(UIStringIdentifier.RV_LOADING_MESSAGE));
            }
            return z;
        }

        public final void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            if (this.f6252a.f6260h != null) {
                this.f6252a.f6260h.mo3930a();
            }
        }

        public final void onPageFinished(WebView webView, String str) {
            CharSequence title = webView.getTitle();
            if (StringUtils.notNullNorEmpty(title)) {
                this.f6252a.f6256d.setText(title);
                this.f6252a.f6255c.setText(webView.getUrl());
                return;
            }
            this.f6252a.f6256d.setText("");
            this.f6252a.f6255c.setText("");
        }

        public final void onScaleChanged(WebView webView, float f, float f2) {
            if (webView != null) {
                webView.invalidate();
            }
        }
    }

    private C2523a(Activity activity) {
        super(activity);
        this.f6257e = true;
        this.f6253a = new C2522d(this);
        setContentDescription("microBrowser");
        setBackgroundColor(Color.parseColor("#333333"));
        setOrientation(1);
        View relativeLayout = new RelativeLayout(activity);
        relativeLayout.setLayoutParams(new LayoutParams(-1, -2));
        relativeLayout.setPadding(0, 10, 0, 10);
        relativeLayout.setBackgroundColor(0);
        ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(11);
        layoutParams.addRule(15);
        View c2526b = new C2526b(activity);
        c2526b.setLayoutParams(layoutParams);
        c2526b.setOnClickListener(new C25171(this));
        layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(14);
        this.f6256d = new TextView(activity);
        this.f6256d.setLayoutParams(layoutParams);
        this.f6256d.setGravity(17);
        this.f6256d.setTextSize(1, 17.0f);
        this.f6256d.setTextColor(-1);
        this.f6256d.setSingleLine();
        this.f6256d.setEllipsize(TruncateAt.END);
        this.f6256d.setId(12345);
        this.f6256d.setContentDescription("microBrowserTitle");
        int applyDimension = (int) TypedValue.applyDimension(1, 50.0f, getResources().getDisplayMetrics());
        int applyDimension2 = (int) TypedValue.applyDimension(1, 5.0f, getResources().getDisplayMetrics());
        this.f6256d.setPadding(applyDimension, applyDimension2, applyDimension, applyDimension2);
        layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(14);
        layoutParams.addRule(3, 12345);
        this.f6255c = new TextView(activity);
        this.f6255c.setGravity(17);
        this.f6255c.setLayoutParams(layoutParams);
        this.f6255c.setTextSize(1, 13.0f);
        this.f6255c.setTextColor(-1);
        this.f6255c.setText(C2671s.m8532a(UIStringIdentifier.RV_LOADING_MESSAGE));
        this.f6255c.setContentDescription("microBrowserUrl");
        relativeLayout.addView(this.f6256d);
        relativeLayout.addView(this.f6255c);
        relativeLayout.addView(c2526b);
        addView(relativeLayout);
        this.f6254b = m8009a((Context) activity);
        addView(this.f6254b);
        this.f6262j = (InputMethodManager) getContext().getSystemService("input_method");
    }

    public C2523a(Activity activity, String str) {
        this(activity);
        this.f6258f = str;
        m8011a(str);
    }

    private WebView m8009a(Context context) {
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -1);
        WebView webView = new WebView(context);
        webView.setLayoutParams(layoutParams);
        webView.setScrollBarStyle(0);
        C2686z.m8586b(webView);
        C2686z.m8584a(webView.getSettings());
        C2686z.m8585a(webView);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(this.f6253a);
        return webView;
    }

    private void m8011a(final String str) {
        Fyber.getConfigs();
        C2409a.m7595b(new C2412c(this) {
            final /* synthetic */ C2523a f6251b;

            public final void mo3844a() {
                this.f6251b.f6254b.loadUrl(str);
                this.f6251b.f6254b.invalidate();
            }
        });
    }

    public final void m8017a() {
        removeView(this.f6254b);
        this.f6254b = m8009a(getContext());
        addView(this.f6254b);
        m8011a(this.f6258f);
    }

    public final void m8021b() {
        m8011a("about:blank");
    }

    public final boolean m8022c() {
        if (!this.f6257e || !this.f6254b.canGoBack()) {
            return false;
        }
        this.f6254b.goBack();
        return true;
    }

    @TargetApi(11)
    protected final void onVisibilityChanged(@NonNull View view, int i) {
        super.onVisibilityChanged(view, i);
        if (view == this) {
            this.f6257e = i == 0;
            if (this.f6257e) {
                this.f6254b.onResume();
            }
        }
    }

    public final void m8019a(C2520b c2520b) {
        this.f6259g = c2520b;
    }

    public final void m8020a(C2521c c2521c) {
        this.f6261i = c2521c;
    }

    public final void m8018a(C2519a c2519a) {
        this.f6260h = c2519a;
    }

    static /* synthetic */ void m8010a(C2523a c2523a) {
        c2523a.f6254b.onPause();
        if (c2523a.f6262j != null) {
            c2523a.f6262j.hideSoftInputFromWindow(c2523a.f6254b.getWindowToken(), 0);
        }
        if (c2523a.f6259g != null) {
            c2523a.f6259g.mo3929a();
        }
    }
}
