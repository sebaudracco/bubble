package com.fyber.ads.interstitials.p088a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.appnext.core.Ad;
import com.facebook.ads.AudienceNetworkActivity;
import com.fyber.Fyber;
import com.fyber.Fyber$Settings.UIStringIdentifier;
import com.fyber.ads.AdFormat;
import com.fyber.ads.interstitials.InterstitialActivity;
import com.fyber.ads.interstitials.InterstitialAdCloseReason;
import com.fyber.ads.interstitials.mediation.InterstitialMediationAdapter;
import com.fyber.ads.interstitials.p091b.C2439c;
import com.fyber.ads.interstitials.p091b.C2440a;
import com.fyber.mediation.MediationUserActivityListener;
import com.fyber.mediation.p107a.C2572a;
import com.fyber.p089c.p090d.C2543d;
import com.fyber.p089c.p090d.C2543d.C2432d;
import com.fyber.p089c.p090d.C2543d.C2541a;
import com.fyber.p089c.p102b.C2525a;
import com.fyber.requesters.OfferWallRequester;
import com.fyber.requesters.RequestCallback;
import com.fyber.requesters.RequestError;
import com.fyber.utils.C2429y;
import com.fyber.utils.C2671s;
import com.fyber.utils.C2686z;
import com.fyber.utils.FyberLogger;
import com.fyber.utils.StringUtils;
import com.mopub.mobileads.BaseVideoPlayerActivity;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: ExchangeInterstitial */
public final class C2433b extends InterstitialMediationAdapter<C2572a> implements OnClickListener, C2432d, MediationUserActivityListener, RequestCallback {
    private Activity f6084a;
    private FrameLayout f6085b;
    private Handler f6086c = new Handler(Looper.getMainLooper(), new C24281(this));
    private WebView f6087d;
    private WebViewClient f6088e;
    private String f6089f;
    private AtomicBoolean f6090g = new AtomicBoolean(false);
    private C2541a f6091h;
    private C2543d f6092i;
    private C2438d f6093j;
    private String f6094k;

    /* compiled from: ExchangeInterstitial */
    class C24281 implements Callback {
        final /* synthetic */ C2433b f6079a;

        C24281(C2433b c2433b) {
            this.f6079a = c2433b;
        }

        public final boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    FyberLogger.m8448d("ExchangeInterstitial", "Creating webview...");
                    Context context = (Context) message.obj;
                    this.f6079a.f6087d = new WebView(context);
                    C2433b.m7696a(this.f6079a, context, this.f6079a.f6087d);
                    C2686z.m8586b(this.f6079a.f6087d);
                    this.f6079a.f6087d.setWebViewClient(C2433b.m7701b(this.f6079a));
                    this.f6079a.f6087d.setScrollBarStyle(0);
                    this.f6079a.f6087d.setBackgroundColor(-16777216);
                    break;
                case 1:
                    if (StringUtils.notNullNorEmpty(this.f6079a.f6089f) && this.f6079a.f6090g.compareAndSet(false, true)) {
                        FyberLogger.m8448d("ExchangeInterstitial", "Loading html...");
                        this.f6079a.f6087d.loadDataWithBaseURL(null, this.f6079a.f6089f, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, "UTF-8", null);
                        break;
                    }
            }
            return false;
        }
    }

    /* compiled from: ExchangeInterstitial */
    class C24302 extends C2429y {
        final /* synthetic */ C2433b f6081a;

        C24302(C2433b c2433b) {
            this.f6081a = c2433b;
            super(null);
        }

        public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (!super.shouldOverrideUrlLoading(webView, str)) {
                m7685c(str);
            }
            return true;
        }

        protected final void mo3866a(int i, String str) {
            m7685c(str);
        }

        protected final Activity mo3865a() {
            return this.f6081a.f6084a;
        }

        protected final void mo3867a(String str, Uri uri) {
            if (str.contains("offerwall") && uri != null) {
                C2433b.m7697a(this.f6081a, uri);
            }
        }

        protected final void mo3868b() {
        }

        public final void onReceivedError(WebView webView, int i, String str, String str2) {
            String format = String.format(Locale.ENGLISH, "Interstitials WebView triggered an error. Error code: %d, error description: %s. Failing URL: %s", new Object[]{Integer.valueOf(i), str, str2});
            FyberLogger.m8449e("ExchangeInterstitial", format);
            if (this.f6081a.f6085b == null || !((Boolean) this.f6081a.f6085b.getTag()).booleanValue()) {
                FyberLogger.m8448d("ExchangeInterstitial", "Page loading error, storing the value for the show phase - error message: " + format);
                this.f6081a.f6094k = format;
                return;
            }
            this.f6081a.interstitialError(format);
        }

        private void m7685c(String str) {
            if (this.f6081a.f6084a != null) {
                this.f6081a.interstitialClicked();
                m7681a(str);
            }
        }
    }

    static /* synthetic */ void m7696a(C2433b c2433b, Context context, WebView webView) {
        c2433b.f6085b = new FrameLayout(context);
        c2433b.f6085b.setContentDescription("interstitialStaticLayout");
        c2433b.f6085b.setTag(Boolean.valueOf(false));
        View c2525a = new C2525a(context);
        int a = c2525a.m8025a();
        c2525a.setLayoutParams(new LayoutParams(a, a, 53));
        webView.setLayoutParams(new LayoutParams(-1, -1));
        webView.setContentDescription("interstitialWebView");
        c2433b.f6085b.addView(webView);
        c2433b.f6085b.addView(c2525a);
        c2525a.setOnClickListener(c2433b);
        c2525a.setContentDescription("interstitialCloseButton");
    }

    public C2433b(C2572a c2572a) {
        super(c2572a);
    }

    protected final void checkForAds(Context context) {
        m7711f();
        m7708e();
        C2427a g = m7713g();
        switch (g) {
            case VIDEO:
                String str = (String) this.request.m8231a(BaseVideoPlayerActivity.VIDEO_URL, String.class);
                if (StringUtils.notNullNorEmpty(str)) {
                    String str2 = (String) this.request.m8231a("alert_message", String.class);
                    if (StringUtils.nullOrEmpty(str2)) {
                        str2 = C2671s.m8532a(UIStringIdentifier.INT_VIDEO_DIALOG_CLOSE);
                    }
                    this.f6091h = new C2541a().m8053a(str).m8056b((String) this.request.m8231a("click_through_url", String.class)).m8057c((String) this.request.m8231a("click_through_text", String.class)).m8058d(str2).m8052a(Float.valueOf(((Number) this.request.mo3971a("close_button_delay", Number.class, Float.valueOf(0.0f))).floatValue())).m8050a(new C2436c()).m8048a().m8054a(((Boolean) this.request.mo3971a("show_alert", Boolean.class, Boolean.valueOf(false))).booleanValue());
                    m7700a(context);
                    setAdAvailable();
                    return;
                }
                setAdError("invalid_offer");
                return;
            case STATIC:
                if (m7700a(context)) {
                    setAdAvailable();
                    return;
                } else {
                    setAdError("invalid_offer");
                    return;
                }
            default:
                setAdError("invalid_offer", "trying to check ads for an unknown creative type: " + g);
                return;
        }
    }

    private boolean m7700a(Context context) {
        this.f6089f = (String) this.request.m8231a("html", String.class);
        boolean notNullNorEmpty = StringUtils.notNullNorEmpty(this.f6089f);
        if (notNullNorEmpty) {
            if (this.f6087d == null) {
                Message obtain = Message.obtain(this.f6086c);
                obtain.what = 0;
                obtain.obj = context;
                obtain.sendToTarget();
            }
            if (((Boolean) this.request.mo3971a("preload", Boolean.class, Boolean.valueOf(false))).booleanValue()) {
                FyberLogger.m8448d("ExchangeInterstitial", "Preload payload - true");
                m7707d();
            }
        }
        return notNullNorEmpty;
    }

    public final void mo3871a(Activity activity, C2439c c2439c) {
        super.mo3871a(activity, c2439c);
        if (!m7705c()) {
            this.f6084a = activity;
            if (this.f6084a instanceof InterstitialActivity) {
                ((InterstitialActivity) this.f6084a).setMarketPlaceInterstitialListener(this);
            }
            Window window = activity.getWindow();
            window.setBackgroundDrawable(new ColorDrawable(-16777216));
            window.requestFeature(1);
            window.setFlags(1024, 1024);
            C2427a g = m7713g();
            switch (g) {
                case VIDEO:
                    C2440a c2440a = (C2440a) c2439c;
                    if (this.f6091h != null) {
                        if (this.f6085b != null) {
                            m7694a();
                        }
                        this.f6093j = new C2438d(c2440a, StringUtils.notNullNorEmpty(this.f6089f));
                        this.f6092i = this.f6091h.m8049a(this.f6093j).m8051a((C2432d) this).m8055a(activity);
                        activity.setRequestedOrientation(6);
                        this.f6092i.m8096a();
                        this.f6092i.m8099b();
                        activity.addContentView(this.f6092i, new LayoutParams(-1, -1));
                        interstitialShown();
                        return;
                    }
                    interstitialError("An error occurred while trying to show the ad");
                    return;
                case STATIC:
                    m7694a();
                    m7703b();
                    interstitialShown();
                    return;
                default:
                    interstitialError("trying to show interstitial for an unknown creative type: " + g);
                    return;
            }
        }
    }

    protected final void show(Activity activity) {
    }

    private void m7694a() {
        this.f6084a.addContentView(this.f6085b, new LayoutParams(-1, -1));
        this.f6085b.setVisibility(8);
        if (!this.f6090g.get()) {
            m7707d();
        }
        if (m7713g() == C2427a.STATIC) {
            String str = (String) getContextData().get("orientation");
            int parseInt = Integer.parseInt((String) getContextData().get("rotation"));
            boolean e = Fyber.getConfigs().m7597a().m8503e();
            if (str.equalsIgnoreCase(Ad.ORIENTATION_PORTRAIT)) {
                if (e) {
                    if (parseInt == 1) {
                        m7695a(9);
                    } else {
                        m7695a(1);
                    }
                } else if (parseInt == 2) {
                    m7695a(9);
                } else {
                    m7695a(1);
                }
            } else if (!str.equalsIgnoreCase(Ad.ORIENTATION_LANDSCAPE)) {
            } else {
                if (e) {
                    if (parseInt == 2) {
                        m7695a(8);
                    } else {
                        m7695a(0);
                    }
                } else if (parseInt == 3) {
                    m7695a(8);
                } else {
                    m7695a(0);
                }
            }
        }
    }

    private void m7703b() {
        this.f6085b.setVisibility(0);
        this.f6085b.setTag(Boolean.valueOf(true));
    }

    private boolean m7705c() {
        if (!StringUtils.notNullNorEmpty(this.f6094k)) {
            return false;
        }
        interstitialError(this.f6094k, "preload");
        return true;
    }

    private void m7707d() {
        Message obtain = Message.obtain(this.f6086c);
        obtain.what = 1;
        obtain.sendToTarget();
    }

    private void m7708e() {
        if (this.f6092i != null) {
            this.f6092i.removeAllViews();
            this.f6092i = null;
        }
        if (this.f6085b != null) {
            this.f6085b.removeAllViews();
            ViewGroup viewGroup = (ViewGroup) this.f6085b.getParent();
            if (viewGroup != null) {
                viewGroup.removeAllViews();
            }
            this.f6085b = null;
        }
    }

    private void m7711f() {
        this.f6090g.set(false);
        this.f6089f = null;
        this.f6091h = null;
        this.f6084a = null;
        this.f6087d = null;
        this.f6093j = null;
        this.f6094k = null;
    }

    public final boolean notifyOnBackPressed() {
        if (this.f6092i != null && this.f6092i.notifyOnBackPressed()) {
            return true;
        }
        m7699a("back_btn", null);
        return false;
    }

    public final void notifyOnUserLeft() {
        m7699a("app_background", null);
    }

    public final void onClick(View view) {
        m7699a("abort_btn", null);
    }

    private void m7699a(String str, InterstitialAdCloseReason interstitialAdCloseReason) {
        interstitialClosed(str, interstitialAdCloseReason);
        m7708e();
        m7711f();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.fyber.ads.interstitials.p088a.C2427a m7713g() {
        /*
        r3 = this;
        r0 = r3.getContextData();
        r1 = "creative_type";
        r0 = r0.get(r1);
        r0 = (java.lang.String) r0;
        if (r0 != 0) goto L_0x0012;
    L_0x000f:
        r0 = com.fyber.ads.interstitials.p088a.C2427a.UNDEFINED;
    L_0x0011:
        return r0;
    L_0x0012:
        r1 = -1;
        r2 = r0.hashCode();
        switch(r2) {
            case -892481938: goto L_0x002c;
            case 112202875: goto L_0x0021;
            default: goto L_0x001a;
        };
    L_0x001a:
        r0 = r1;
    L_0x001b:
        switch(r0) {
            case 0: goto L_0x0037;
            case 1: goto L_0x003a;
            default: goto L_0x001e;
        };
    L_0x001e:
        r0 = com.fyber.ads.interstitials.p088a.C2427a.UNDEFINED;
        goto L_0x0011;
    L_0x0021:
        r2 = "video";
        r0 = r0.equals(r2);
        if (r0 == 0) goto L_0x001a;
    L_0x002a:
        r0 = 0;
        goto L_0x001b;
    L_0x002c:
        r2 = "static";
        r0 = r0.equals(r2);
        if (r0 == 0) goto L_0x001a;
    L_0x0035:
        r0 = 1;
        goto L_0x001b;
    L_0x0037:
        r0 = com.fyber.ads.interstitials.p088a.C2427a.VIDEO;
        goto L_0x0011;
    L_0x003a:
        r0 = com.fyber.ads.interstitials.p088a.C2427a.STATIC;
        goto L_0x0011;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fyber.ads.interstitials.a.b.g():com.fyber.ads.interstitials.a.a");
    }

    private void m7695a(int i) {
        this.f6084a.setRequestedOrientation(i);
    }

    public final void mo3870a(int i, String str) {
        this.f6093j.mo3870a(i, str);
        if (this.f6085b != null) {
            if (!m7705c()) {
                m7703b();
            }
        } else if (i != 1 || this.f6093j.m7741d()) {
            m7699a(str, null);
        } else {
            m7699a(str, InterstitialAdCloseReason.ReasonVideoEnded);
        }
    }

    public final void onAdAvailable(Intent intent) {
        this.f6084a.startActivity(intent);
    }

    public final void onAdNotAvailable(AdFormat adFormat) {
    }

    public final void onRequestError(RequestError requestError) {
    }

    static /* synthetic */ WebViewClient m7701b(C2433b c2433b) {
        if (c2433b.f6088e == null) {
            c2433b.f6088e = new C24302(c2433b);
        }
        return c2433b.f6088e;
    }

    static /* synthetic */ void m7697a(C2433b c2433b, Uri uri) {
        String queryParameter = uri.getQueryParameter("placementId");
        FyberLogger.m8448d("ExchangeInterstitial", "Placement ID - " + queryParameter);
        ((OfferWallRequester) OfferWallRequester.create(c2433b).closeOnRedirect(true).withPlacementId(queryParameter)).request(c2433b.f6084a);
    }
}
