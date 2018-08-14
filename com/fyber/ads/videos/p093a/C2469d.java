package com.fyber.ads.videos.p093a;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.WindowManager.BadTokenException;
import android.webkit.JsResult;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout.LayoutParams;
import android.widget.Toast;
import com.fyber.C2409a;
import com.fyber.Fyber;
import com.fyber.Fyber$Settings.UIStringIdentifier;
import com.fyber.ads.AdFormat;
import com.fyber.ads.internal.C2423a;
import com.fyber.ads.internal.C2424c;
import com.fyber.ads.internal.Offer;
import com.fyber.ads.videos.RewardedVideoActivity;
import com.fyber.ads.videos.a.d.AnonymousClass10;
import com.fyber.ads.videos.mediation.C2465a;
import com.fyber.ads.videos.mediation.TPNVideoEvent;
import com.fyber.ads.videos.mediation.TPNVideoValidationResult;
import com.fyber.ads.videos.p093a.C2446e.C2470a;
import com.fyber.ads.videos.p093a.C2469d;
import com.fyber.ads.videos.p093a.C2469d.10.C24521;
import com.fyber.ads.videos.p093a.C2469d.10.C24532;
import com.fyber.ads.videos.p093a.C2469d.10.C24543;
import com.fyber.cache.CacheManager;
import com.fyber.cache.internal.C2553a;
import com.fyber.cache.internal.C2557e;
import com.fyber.mediation.C2573a;
import com.fyber.mediation.p108b.C2580a;
import com.fyber.p089c.p090d.C2437b;
import com.fyber.p089c.p090d.C2532a;
import com.fyber.p089c.p090d.C2543d;
import com.fyber.p089c.p090d.C2543d.C2432d;
import com.fyber.p089c.p090d.C2543d.C2541a;
import com.fyber.p094b.C2512j.C2458c;
import com.fyber.p094b.C2512j.C2509a;
import com.fyber.p094b.p100d.C2503a;
import com.fyber.p094b.p100d.C2503a.C2502a;
import com.fyber.requesters.VirtualCurrencyRequester;
import com.fyber.requesters.p097a.C2579k;
import com.fyber.requesters.p097a.C2588f;
import com.fyber.requesters.p097a.C2623c;
import com.fyber.requesters.p097a.p098a.C2602f;
import com.fyber.requesters.p097a.p098a.C2604g;
import com.fyber.requesters.p097a.p098a.C2604g.C2603a;
import com.fyber.requesters.p097a.p098a.C2610j;
import com.fyber.requesters.p097a.p098a.C2613l.C2612a;
import com.fyber.utils.C2412c;
import com.fyber.utils.C2429y;
import com.fyber.utils.C2646d;
import com.fyber.utils.C2664l;
import com.fyber.utils.C2665m;
import com.fyber.utils.C2671s;
import com.fyber.utils.C2672t;
import com.fyber.utils.C2686z;
import com.fyber.utils.FyberLogger;
import com.fyber.utils.StringUtils;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.common.server.FavaDiagnosticsEntity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import mf.org.apache.xerces.impl.Constants;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONObject;

/* compiled from: RewardedVideoClient */
public final class C2469d implements C2432d {
    public static final C2469d f6162a = new C2469d();
    private Handler f6163b;
    private Handler f6164c;
    private RewardedVideoActivity f6165d;
    private Context f6166e;
    private WebView f6167f;
    private boolean f6168g = false;
    private String f6169h;
    private C2471f f6170i = C2471f.MUST_QUERY_SERVER_FOR_OFFERS;
    private C2446e f6171j;
    private WebViewClient f6172k;
    private WebChromeClient f6173l;
    private C2543d f6174m;
    private C2468a f6175n;
    private boolean f6176o = false;
    private boolean f6177p;
    private boolean f6178q = false;
    private C2588f<C2448b, AdFormat> f6179r;
    private C2623c f6180s;
    private Offer f6181t;
    private CountDownLatch f6182u;
    private String f6183v = "Sponsorpay.MBE.SDKInterface";
    private long f6184w;

    /* compiled from: RewardedVideoClient */
    class C24551 implements Callback {
        final /* synthetic */ C2469d f6140a;

        C24551(C2469d c2469d) {
            this.f6140a = c2469d;
        }

        public final boolean handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.f6140a.m7814c(C2671s.m8532a(UIStringIdentifier.RV_ERROR_DIALOG_MESSAGE_DEFAULT));
                    break;
                case 2:
                    FyberLogger.m8448d("RewardedVideoClient", "Timeout reached, canceling request...");
                    C2469d.m7800a(this.f6140a);
                    C2469d.m7801a(this.f6140a, 0, null, true);
                    break;
                default:
                    FyberLogger.m8449e("RewardedVideoClient", "Unknown message what field");
                    break;
            }
            return true;
        }
    }

    /* compiled from: RewardedVideoClient */
    class C24562 implements Runnable {
        final /* synthetic */ C2469d f6141a;

        C24562(C2469d c2469d) {
            this.f6141a = c2469d;
        }

        public final void run() {
            this.f6141a.m7814c(C2671s.m8532a(UIStringIdentifier.RV_ERROR_DIALOG_MESSAGE_OFFLINE));
        }
    }

    /* compiled from: RewardedVideoClient */
    class C24573 implements Callback {
        final /* synthetic */ C2469d f6142a;

        C24573(C2469d c2469d) {
            this.f6142a = c2469d;
        }

        @TargetApi(19)
        public final boolean handleMessage(Message message) {
            String obj;
            switch (message.what) {
                case 123:
                    if (this.f6142a.f6167f == null) {
                        FyberLogger.m8448d("RewardedVideoClient", "Cannot load url because the webview doesn't exist anymore");
                        break;
                    }
                    obj = message.obj.toString();
                    C2623c c = this.f6142a.f6180s;
                    if (!obj.startsWith("http") || this.f6142a.m7852h()) {
                        this.f6142a.f6167f.loadUrl(obj);
                    } else {
                        this.f6142a.f6167f.loadUrl(obj, c.m8419e().m8438d());
                    }
                    if (obj.equals("about:blank")) {
                        this.f6142a.f6167f = null;
                        this.f6142a.f6172k = null;
                        this.f6142a.f6165d = null;
                        if (!this.f6142a.f6177p) {
                            this.f6142a.f6166e = null;
                            break;
                        }
                    }
                    break;
                case 522:
                    C2469d.m7823i(this.f6142a);
                    break;
                case 9876:
                    if (this.f6142a.f6167f == null) {
                        FyberLogger.m8448d("RewardedVideoClient", "Cannot load url because the webview doesn't exist anymore");
                        break;
                    }
                    obj = message.obj.toString();
                    FyberLogger.m8448d("RewardedVideoClient", "load url - " + obj);
                    this.f6142a.f6167f.evaluateJavascript(obj, null);
                    break;
                default:
                    FyberLogger.m8449e("RewardedVideoClient", "Unknown message what field");
                    break;
            }
            return true;
        }
    }

    /* compiled from: RewardedVideoClient */
    class C24594 implements C2458c<JSONObject> {
        final /* synthetic */ C2469d f6143a;

        C24594(C2469d c2469d) {
            this.f6143a = c2469d;
        }

        public final /* synthetic */ Object mo3893a(String str) throws Exception {
            FyberLogger.m8451i("RewardedVideoClient", "Rewarded Video response - " + str);
            return new JSONObject(str);
        }
    }

    /* compiled from: RewardedVideoClient */
    class C24616 implements Callable<WebView> {
        final /* synthetic */ C2469d f6146a;

        C24616(C2469d c2469d) {
            this.f6146a = c2469d;
        }

        public final /* synthetic */ Object call() throws Exception {
            WebView webView = new WebView(this.f6146a.f6166e);
            WebSettings settings = webView.getSettings();
            C2686z.m8586b(webView);
            C2686z.m8584a(settings);
            C2686z.m8585a(webView);
            if (C2665m.m8523a(17)) {
                webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
            }
            settings.setUseWideViewPort(false);
            webView.setBackgroundColor(-16777216);
            webView.setScrollBarStyle(0);
            webView.setWebChromeClient(C2469d.m7825k(this.f6146a));
            webView.setWebViewClient(C2469d.m7826l(this.f6146a));
            return webView;
        }
    }

    /* compiled from: RewardedVideoClient */
    class C24627 implements OnClickListener {
        final /* synthetic */ C2469d f6147a;

        C24627(C2469d c2469d) {
            this.f6147a = c2469d;
        }

        public final void onClick(DialogInterface dialogInterface, int i) {
            this.f6147a.m7805a(C2470a.ERROR);
            this.f6147a.m7822i();
            this.f6147a.f6168g = false;
        }
    }

    /* compiled from: RewardedVideoClient */
    static class C2468a implements C2437b {
        private final Handler f6157a;
        private String f6158b;
        private double f6159c;
        private String f6160d;
        private boolean f6161e;

        C2468a(@NonNull Handler handler, String str) {
            this.f6157a = handler;
            this.f6158b = str;
        }

        public final void mo3881a() {
            m7785a(C2532a.TimeoutEvent, "video");
        }

        public final void mo3884a(String str, boolean z, String str2) {
            this.f6160d = str;
            this.f6161e = z;
        }

        public final void mo3882a(int i) {
            this.f6159c = ((double) i) / 1000.0d;
            String str = this.f6158b;
            double d = this.f6159c;
            String str2 = this.f6160d;
            m7787c(String.format(Locale.ENGLISH, "javascript:%s.notify('play', {tpn:'%s', result:'%s', duration:'%.2f', id:'%s'})", new Object[]{str, ImagesContract.LOCAL, C2532a.PlayingEvent, Double.valueOf(d), str2}));
        }

        public final void mo3886b(int i) {
            double d = ((double) i) / 1000.0d;
            String str = this.f6158b;
            double d2 = this.f6159c;
            String str2 = this.f6160d;
            m7787c(String.format(Locale.ENGLISH, "javascript:%s.notify('play', {tpn:'%s', result:'%s', currentTime:'%.3f', duration:'%.2f', id:'%s'})", new Object[]{str, ImagesContract.LOCAL, C2532a.TimeUpdateEvent, Double.valueOf(d), Double.valueOf(d2), str2}));
        }

        public final void mo3885b() {
            m7785a(C2532a.EndedEvent, null);
        }

        public final void mo3888c() {
            m7785a(C2532a.ClickThroughEvent, null);
        }

        public final void mo3883a(String str) {
            m7785a(C2532a.CancelEvent, str);
        }

        public final void mo3887b(String str) {
            m7785a(C2532a.ErrorEvent, str);
        }

        private void m7785a(C2532a c2532a, String str) {
            String a;
            if (StringUtils.notNullNorEmpty(str)) {
                a = C2447a.m7768a(false, c2532a.toString(), str);
            } else {
                a = C2447a.m7768a(false, new String[0]);
            }
            String str2 = this.f6158b;
            String str3 = this.f6160d;
            m7787c(String.format(Locale.ENGLISH, "javascript:%s.notify('play', {tpn:'%s', result:'%s', id:'%s', %s})", new Object[]{str2, ImagesContract.LOCAL, c2532a, str3, a}));
        }

        private void m7787c(String str) {
            FyberLogger.m8448d("RewardedVideoClient", "javascript client called with URL:" + str);
            if (StringUtils.notNullNorEmpty(str)) {
                Message obtain = Message.obtain(this.f6157a);
                obtain.what = 123;
                obtain.obj = str;
                obtain.sendToTarget();
            }
        }
    }

    static /* synthetic */ void m7802a(C2469d c2469d, Offer offer, String str, String str2, TPNVideoValidationResult tPNVideoValidationResult, String str3) {
        String str4 = c2469d.f6183v;
        List arrayList = new ArrayList();
        arrayList.add("adapter_version");
        arrayList.add(str2);
        if (tPNVideoValidationResult == TPNVideoValidationResult.Timeout) {
            arrayList.add("timeout");
            arrayList.add("network");
        } else if (tPNVideoValidationResult == TPNVideoValidationResult.Success && offer != null) {
            C2580a providerRequest = offer.getProviderRequest();
            C2610j b = C2573a.f6454a.m8221b(offer.getProviderType(), AdFormat.REWARDED_VIDEO);
            if (b != null) {
                arrayList.addAll(C2424c.m7672a(0, b.m8376b(providerRequest.mo3972a()), false));
            }
        }
        String a = C2447a.m7769a((String[]) arrayList.toArray(new String[0]));
        a = String.format(Locale.ENGLISH, "javascript:%s.notify('validate', {tpn:'%s', result:'%s', id:'%s', %s})", new Object[]{str4, str, tPNVideoValidationResult, str3, a});
        FyberLogger.m8448d("RewardedVideoClient", "Notifying - " + a);
        c2469d.m7811b(a);
    }

    private C2469d() {
        HandlerThread handlerThread = new HandlerThread("RVTimer", 1);
        handlerThread.start();
        this.f6163b = new Handler(handlerThread.getLooper(), new C24551(this));
        this.f6164c = new Handler(Looper.getMainLooper(), new C24573(this));
    }

    public final boolean m7845a(C2623c c2623c, Context context) throws Exception {
        Exception e;
        if (m7846b()) {
            Callable c24616;
            Future a;
            if (this.f6167f == null) {
                this.f6166e = context;
                this.f6177p = false;
                c24616 = new C24616(this);
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    this.f6167f = (WebView) c24616.call();
                } else {
                    Object futureTask = new FutureTask(c24616);
                    Fyber.getConfigs();
                    C2409a.m7595b(futureTask);
                    this.f6167f = (WebView) futureTask.get();
                }
                this.f6167f.setContentDescription("videoPlayerWebview");
            }
            this.f6176o = false;
            this.f6180s = c2623c;
            m7808a(C2471f.QUERYING_SERVER_FOR_OFFERS);
            this.f6182u = new CountDownLatch(1);
            c24616 = new C2509a(c2623c.m8419e().m8435a()).m7992a(c2623c.m8419e().m8438d()).m7991a(new C24594(this)).m7993a();
            if (Fyber.getConfigs().m7607h()) {
                a = Fyber.getConfigs().m7599a(c24616);
            } else {
                a = null;
            }
            C2672t c = c2623c.m8419e().m8437c();
            FyberLogger.m8448d("RewardedVideoClient", "Loading mBE client...");
            String e2 = C2672t.m8533a(c).m8538a(C2646d.m8469a("videos")).m8539a("mode", "noop").m8547e();
            FyberLogger.m8448d("RewardedVideoClient", "Loading URL: " + e2);
            m7811b(e2);
            this.f6163b.sendEmptyMessageDelayed(2, 10000);
            this.f6184w = System.currentTimeMillis();
            try {
                JSONObject jSONObject = (JSONObject) a.get(10500, TimeUnit.MILLISECONDS);
                if (jSONObject == null || m7852h()) {
                    this.f6163b.removeMessages(2);
                    this.f6179r.m8288d(AdFormat.REWARDED_VIDEO);
                    m7799a(C2423a.ValidationError, "json_parsing");
                    m7822i();
                    return true;
                }
                try {
                    this.f6182u.await(10000, TimeUnit.MILLISECONDS);
                    c2623c.mo3973b("json_response", jSONObject);
                    String str = Constants.DOM_VALIDATE;
                    Map hashMap = new HashMap(1);
                    hashMap.put("time_until_global_timeout", Long.valueOf(10000 - (System.currentTimeMillis() - this.f6184w)));
                    m7811b(C2447a.m7767a(this.f6183v, "run", "dont_care", str, jSONObject, hashMap));
                } catch (InterruptedException e3) {
                    this.f6163b.removeMessages(2);
                    this.f6179r.m8288d(AdFormat.REWARDED_VIDEO);
                    m7799a(C2423a.ValidationError, "javascript");
                    m7822i();
                }
                return true;
            } catch (InterruptedException e4) {
                e = e4;
                FyberLogger.m8450e("RewardedVideoClient", "Impossible to get the list of ads from \"" + c2623c.m8419e().m8435a() + "\"", e);
                this.f6163b.removeMessages(2);
                this.f6179r.m8288d(AdFormat.REWARDED_VIDEO);
                m7799a(C2423a.ValidationError, "json_parsing");
                m7822i();
            } catch (ExecutionException e5) {
                e = e5;
                FyberLogger.m8450e("RewardedVideoClient", "Impossible to get the list of ads from \"" + c2623c.m8419e().m8435a() + "\"", e);
                this.f6163b.removeMessages(2);
                this.f6179r.m8288d(AdFormat.REWARDED_VIDEO);
                m7799a(C2423a.ValidationError, "json_parsing");
                m7822i();
            } catch (Exception e6) {
                this.f6163b.removeMessages(2);
                if (!m7852h()) {
                    FyberLogger.m8450e("RewardedVideoClient", "Timeout when retrieving the list of ads from \"" + c2623c.m8419e().m8435a() + "\"", e6);
                    this.f6179r.m8288d(AdFormat.REWARDED_VIDEO);
                    m7799a(C2423a.ValidationTimeout, "global");
                }
                m7822i();
            }
        } else {
            FyberLogger.m8448d("RewardedVideoClient", "RewardedVideoClient cannot request offers at this point. It might be requesting offers right now or an offer might be currently being presented to the user.");
            return false;
        }
    }

    public final boolean m7843a(final RewardedVideoActivity rewardedVideoActivity, boolean z, C2602f c2602f) {
        if (rewardedVideoActivity == null) {
            FyberLogger.m8448d("RewardedVideoClient", "The provided activity is null, RewardedVideoClient cannot start the engagement.");
        } else if (this.f6170i.m7853a()) {
            Collection emptyList;
            C2580a providerRequest;
            if (c2602f == null || !c2602f.m8350i() || c2602f.m8346e() <= 0) {
                emptyList = Collections.emptyList();
            } else {
                emptyList = Arrays.asList(new String[]{"container_fill_cached", SchemaSymbols.ATTVAL_TRUE, "", "container_fill_cache_age", String.valueOf(System.currentTimeMillis() - c2602f.m8342b()), ""});
            }
            String str = this.f6183v;
            Offer offer = this.f6181t;
            C2553a b = CacheManager.m8105a().m8116b();
            String str2 = "";
            if (!(b == null || b.equals(C2553a.f6397a))) {
                str2 = String.format(Locale.ENGLISH, ", cache_config_id:'%s'", new Object[]{b.m8143a()});
            }
            List arrayList = new ArrayList();
            arrayList.addAll(emptyList);
            if (offer != null) {
                providerRequest = offer.getProviderRequest();
                C2610j b2 = C2573a.f6454a.m8221b(offer.getProviderType(), AdFormat.REWARDED_VIDEO);
                if (b2 != null) {
                    arrayList.addAll(C2424c.m7672a(1, b2.m8376b(providerRequest.mo3972a()), true));
                }
            }
            String a = C2447a.m7768a(true, (String[]) arrayList.toArray(new String[0]));
            r7 = new Object[5];
            CacheManager.m8105a().m8119d();
            r7[1] = C2557e.m8162d();
            r7[2] = Integer.valueOf(CacheManager.m8105a().m8119d().m8164a());
            r7[3] = str2;
            r7[4] = a;
            a = String.format(Locale.ENGLISH, "javascript:%s.do_start({cached_ad_ids:%s, downloaded_videos_count:%d%s, %s})", r7);
            FyberLogger.m8451i("RewardedVideoClient", a);
            m7811b(a);
            CacheManager.m8105a().m8119d().m8166c();
            if (this.f6181t != null) {
                providerRequest = this.f6181t.getProviderRequest();
                C2610j b3 = C2573a.f6454a.m8221b(this.f6181t.getProviderType(), AdFormat.REWARDED_VIDEO);
                if (b3 != null) {
                    b3.m8374a(providerRequest.mo3972a());
                }
            }
            this.f6165d = rewardedVideoActivity;
            if (z) {
                Fyber.getConfigs();
                C2409a.m7595b(new C2412c(this) {
                    final /* synthetic */ C2469d f6145b;

                    public final void mo3844a() {
                        rewardedVideoActivity.addContentView(this.f6145b.f6167f, new LayoutParams(-1, -1));
                    }
                });
            }
            this.f6163b.sendEmptyMessageDelayed(1, 10000);
            return true;
        } else {
            FyberLogger.m8448d("RewardedVideoClient", "RewardedVideoClient is not ready to show offers. Call requestOffers() and wait until your listener is called with the confirmation that offers have been received.");
        }
        return false;
    }

    public final void m7839a() {
        if (!this.f6170i.equals(C2471f.USER_ENGAGED) && !this.f6170i.equals(C2471f.SHOWING_OFFERS) && !this.f6170i.equals(C2471f.READY_TO_SHOW_OFFERS)) {
            return;
        }
        if (this.f6170i == C2471f.USER_ENGAGED) {
            m7806a(RewardedVideoActivity.REQUEST_STATUS_PARAMETER_FINISHED_VALUE);
        } else {
            m7806a(RewardedVideoActivity.REQUEST_STATUS_PARAMETER_ABORTED_VALUE);
        }
    }

    public final boolean m7846b() {
        return this.f6170i.m7854b() && !this.f6178q;
    }

    public final boolean m7847c() {
        return this.f6170i.m7853a();
    }

    private void m7806a(String str) {
        if (!m7852h()) {
            if (str.equals("STARTED")) {
                this.f6163b.removeMessages(1);
                if (m7808a(C2471f.SHOWING_OFFERS)) {
                    m7805a(C2470a.STARTED);
                }
            } else if (str.equals(RewardedVideoActivity.REQUEST_STATUS_PARAMETER_FINISHED_VALUE)) {
                C2579k c2579k = this.f6180s;
                if (c2579k != null) {
                    if (((Boolean) c2579k.mo3970a("SHOULD_NOTIFY_ON_USER_ENGAGED")).booleanValue() && this.f6166e != null) {
                        Toast.makeText(this.f6166e, C2671s.m8532a(UIStringIdentifier.RV_REWARD_NOTIFICATION), 1).show();
                    }
                    if (!(this.f6175n == null || !this.f6175n.f6161e || this.f6165d == null)) {
                        Intent intent = new Intent(this.f6165d.getPackageName() + ".cache.DONE_PRECACHING");
                        intent.putExtra("refresh.interval", 5);
                        FyberLogger.m8451i("RewardedVideoClient", "Broadcasting intent with refresh interval = 5");
                        this.f6165d.sendBroadcast(intent);
                    }
                    C2623c c2623c = this.f6180s;
                    if (m7852h()) {
                        FyberLogger.m8448d("RewardedVideoClient", "I'm sure users will complain, but it's that or a crash...");
                    } else {
                        VirtualCurrencyRequester virtualCurrencyRequester = (VirtualCurrencyRequester) c2623c.mo3970a("CURRENCY_REQUESTER");
                        if (virtualCurrencyRequester != null) {
                            this.f6177p = true;
                            virtualCurrencyRequester = ((VirtualCurrencyRequester) VirtualCurrencyRequester.from(virtualCurrencyRequester).withPlacementId(c2623c.m8422h())).forCurrencyId(this.f6169h);
                            this.f6163b.postDelayed(new Runnable(this) {
                                final /* synthetic */ C2469d f6149b;

                                public final void run() {
                                    if (this.f6149b.f6166e != null) {
                                        virtualCurrencyRequester.request(this.f6149b.f6166e);
                                        if (this.f6149b.f6177p) {
                                            this.f6149b.f6166e = null;
                                            return;
                                        }
                                        return;
                                    }
                                    FyberLogger.m8448d("RewardedVideoClient", "There's no context available to perform a VCS request");
                                }
                            }, 3000);
                        }
                    }
                    m7822i();
                }
                m7805a(C2470a.CLOSE_FINISHED);
            } else if (str.equals(RewardedVideoActivity.REQUEST_STATUS_PARAMETER_ABORTED_VALUE)) {
                this.f6163b.removeMessages(1);
                m7822i();
                m7805a(C2470a.CLOSE_ABORTED);
            } else if (str.equals(RewardedVideoActivity.REQUEST_STATUS_PARAMETER_ERROR)) {
                m7814c(C2671s.m8532a(UIStringIdentifier.RV_ERROR_DIALOG_MESSAGE_DEFAULT));
            } else if (str.equals("USER_ENGAGED")) {
                m7808a(C2471f.USER_ENGAGED);
            }
        }
    }

    private void m7822i() {
        m7808a(C2471f.MUST_QUERY_SERVER_FOR_OFFERS);
        if (this.f6167f != null) {
            m7811b("about:blank");
        }
        if (this.f6174m != null) {
            this.f6174m.m8097a("unknown", "forceClose");
        }
        this.f6180s = null;
        this.f6169h = null;
        this.f6181t = null;
        this.f6163b.removeMessages(2);
        this.f6163b.removeMessages(1);
    }

    private void m7811b(String str) {
        if (!StringUtils.notNullNorEmpty(str)) {
            return;
        }
        if (m7852h()) {
            FyberLogger.m8448d("RewardedVideoClient", "The client has already been cleared, not loading the following url - " + str);
            return;
        }
        Message obtain = Message.obtain(this.f6164c);
        if (URLUtil.isJavaScriptUrl(str) && C2665m.m8523a(19)) {
            obtain.what = 9876;
        } else {
            obtain.what = 123;
        }
        obtain.obj = str;
        obtain.sendToTarget();
    }

    public final void m7841a(C2588f<C2448b, AdFormat> c2588f) {
        this.f6179r = c2588f;
    }

    public final boolean m7844a(C2446e c2446e) {
        this.f6171j = c2446e;
        return true;
    }

    private void m7805a(C2470a c2470a) {
        if (this.f6171j != null) {
            FyberLogger.m8451i("RewardedVideoClient", "RewardedVideoClientStatus -> " + c2470a);
            this.f6171j.didChangeStatus(c2470a);
        }
    }

    private void m7814c(String str) {
        if (!this.f6168g && this.f6167f != null) {
            this.f6168g = true;
            if (this.f6174m != null) {
                this.f6174m.m8101d();
                this.f6174m.m8102e();
            }
            Builder builder = new Builder(this.f6165d == null ? this.f6166e : this.f6165d);
            builder.setTitle(C2671s.m8532a(UIStringIdentifier.RV_ERROR_DIALOG_TITLE)).setMessage(str).setNeutralButton(C2671s.m8532a(UIStringIdentifier.RV_ERROR_DIALOG_BUTTON_TITLE_DISMISS), new C24627(this)).setCancelable(false);
            try {
                builder.show();
            } catch (BadTokenException e) {
                this.f6168g = false;
                FyberLogger.m8449e("RewardedVideoClient", "Unable to show the dialog window");
            }
        }
    }

    private boolean m7808a(C2471f c2471f) {
        if (this.f6170i == c2471f || c2471f.ordinal() - this.f6170i.ordinal() > 1) {
            return false;
        }
        this.f6170i = c2471f;
        FyberLogger.m8448d("RewardedVideoClient", "RewardedVideoClient status -> " + c2471f.name());
        return true;
    }

    protected final boolean m7848d() {
        return this.f6181t == null || ((Boolean) this.f6181t.getProviderRequest().mo3971a(RewardedVideoActivity.PLAY_EXCHANGE_AD_KEY_BUNDLE, Boolean.class, Boolean.valueOf(true))).booleanValue();
    }

    public final void m7849e() {
        Message obtain = Message.obtain(this.f6164c);
        obtain.what = 522;
        obtain.sendToTarget();
    }

    public final void mo3870a(int i, String str) {
        this.f6174m = null;
        this.f6176o = true;
    }

    public final void m7850f() {
        if (this.f6176o && this.f6170i == C2471f.MUST_QUERY_SERVER_FOR_OFFERS) {
            m7805a(C2470a.CLOSE_ABORTED);
        }
    }

    public final void m7851g() {
        if (this.f6170i == C2471f.SHOWING_OFFERS) {
            FyberLogger.m8449e("RewardedVideoClient", "Connection has been lost");
            this.f6163b.post(new C24562(this));
        }
    }

    public final void m7842a(boolean z) {
        this.f6178q = z;
    }

    public final boolean m7852h() {
        return this.f6180s == null;
    }

    private void m7799a(@NonNull C2423a c2423a, String str) {
        ((C2503a) ((C2502a) ((C2502a) new C2502a(c2423a).m7860a(str)).m7861a(null)).m7891b(m7852h() ? "made_up_request_id" : this.f6180s.m8413b())).mo3907b();
    }

    static /* synthetic */ void m7800a(C2469d c2469d) {
        if (c2469d.f6170i == C2471f.QUERYING_SERVER_FOR_OFFERS) {
            C2502a c2502a = (C2502a) new C2502a(C2423a.ValidationTimeout).m7860a("global");
            String str = "made_up_request_id";
            C2623c c2623c = c2469d.f6180s;
            if (c2469d.m7852h()) {
                FyberLogger.m8448d("RewardedVideoClient", "We can't retrieve the request data... We're still tracking this with an easy to check `requestId`");
            } else {
                str = c2623c.m8413b();
                String h = c2623c.m8422h();
                if (StringUtils.notNullNorEmpty(h)) {
                    c2502a.m7861a(Collections.singletonMap("placement_id", h));
                }
            }
            ((C2503a) c2502a.m7891b(str)).mo3907b();
            return;
        }
        FyberLogger.m8448d("RewardedVideoClient", "The timeout didn't occur while requesting for offers, we'll not send it...\nWe received it probably at the same time as an ad availability coming from mBE");
    }

    static /* synthetic */ void m7801a(C2469d c2469d, int i, C2604g c2604g, boolean z) {
        if (c2469d.f6170i == C2471f.QUERYING_SERVER_FOR_OFFERS) {
            c2469d.f6163b.removeMessages(2);
            C2579k c2579k = c2469d.f6180s;
            if (c2469d.m7852h()) {
                FyberLogger.m8448d("RewardedVideoClient", "The client seems to already have been cleared. Not sending cache information in any tracking related");
            } else {
                c2579k.mo3973b("CACHE_CONFIG", c2604g);
            }
            if ((i > 0 ? 1 : null) != null) {
                C2448b c2448b;
                c2469d.m7808a(C2471f.READY_TO_SHOW_OFFERS);
                if (c2469d.f6181t != null) {
                    c2469d.f6181t.getProviderRequest().m8228a(RewardedVideoActivity.PLAY_EXCHANGE_AD_KEY_BUNDLE, Boolean.valueOf(z));
                    c2448b = new C2448b(c2469d.f6180s, Collections.singletonList(c2469d.f6181t));
                } else {
                    c2448b = new C2448b(c2469d.f6180s, Collections.emptyList());
                }
                c2448b.m7612a(10000).m7613a(c2604g);
                c2469d.f6179r.m8287c(c2448b);
                return;
            }
            c2469d.m7822i();
            c2469d.f6179r.m8288d(AdFormat.REWARDED_VIDEO);
            return;
        }
        FyberLogger.m8448d("RewardedVideoClient", "An issue happened - we receive a response after being `cleared`.");
    }

    static /* synthetic */ void m7823i(C2469d c2469d) {
        if (c2469d.f6167f != null && c2469d.f6174m == null) {
            c2469d.f6167f.onPause();
        }
    }

    static /* synthetic */ WebChromeClient m7825k(C2469d c2469d) {
        if (c2469d.f6173l == null) {
            c2469d.f6173l = new WebChromeClient(c2469d) {
                final /* synthetic */ C2469d f6139a;

                /* compiled from: RewardedVideoClient */
                class C24521 implements OnCancelListener {
                    final /* synthetic */ AnonymousClass10 f6136a;

                    C24521(AnonymousClass10 anonymousClass10) {
                        this.f6136a = anonymousClass10;
                    }

                    public final void onCancel(DialogInterface dialogInterface) {
                        this.f6136a.f6139a.f6168g = false;
                    }
                }

                /* compiled from: RewardedVideoClient */
                class C24532 implements OnClickListener {
                    final /* synthetic */ AnonymousClass10 f6137a;

                    C24532(AnonymousClass10 anonymousClass10) {
                        this.f6137a = anonymousClass10;
                    }

                    public final void onClick(DialogInterface dialogInterface, int i) {
                        this.f6137a.f6139a.f6168g = false;
                    }
                }

                /* compiled from: RewardedVideoClient */
                class C24543 implements OnClickListener {
                    final /* synthetic */ AnonymousClass10 f6138a;

                    C24543(AnonymousClass10 anonymousClass10) {
                        this.f6138a = anonymousClass10;
                    }

                    public final void onClick(DialogInterface dialogInterface, int i) {
                        this.f6138a.f6139a.m7806a(RewardedVideoActivity.REQUEST_STATUS_PARAMETER_ABORTED_VALUE);
                        this.f6138a.f6139a.f6168g = false;
                    }
                }

                {
                    this.f6139a = r1;
                }

                public final boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
                    FyberLogger.m8448d("RewardedVideoClient", "js alert - " + str2);
                    FyberLogger.m8448d("RewardedVideoClient", "js alert - " + str2);
                    if (!this.f6139a.f6168g) {
                        this.f6139a.f6168g = true;
                        Builder builder = new Builder(this.f6139a.f6165d == null ? this.f6139a.f6166e : this.f6139a.f6165d);
                        builder.setTitle(C2671s.m8532a(UIStringIdentifier.RV_FORFEIT_DIALOG_TITLE)).setMessage(str2).setPositiveButton("OK", new C24543(this)).setNegativeButton("Cancel", new C24532(this)).setOnCancelListener(new C24521(this));
                        builder.show();
                    }
                    jsResult.cancel();
                    return true;
                }
            };
        }
        return c2469d.f6173l;
    }

    static /* synthetic */ WebViewClient m7826l(C2469d c2469d) {
        if (c2469d.f6172k == null) {
            c2469d.f6172k = new C2429y(c2469d, c2469d.f6165d) {
                final /* synthetic */ C2469d f6156a;

                /* compiled from: RewardedVideoClient */
                class C24662 implements C2465a {
                    final /* synthetic */ C24679 f6155a;

                    C24662(C24679 c24679) {
                        this.f6155a = c24679;
                    }

                    public final void mo3894a(String str, String str2, TPNVideoEvent tPNVideoEvent, Map<String, String> map) {
                        if (tPNVideoEvent == TPNVideoEvent.Started) {
                            this.f6155a.f6156a.m7806a("STARTED");
                        }
                        String s = this.f6155a.f6156a.f6183v;
                        String str3 = (String) map.get("id");
                        String a = C2447a.m7768a(false, "adapter_version", str2);
                        str3 = String.format(Locale.ENGLISH, "javascript:%s.notify('play', {tpn:'%s', result:'%s', id:'%s', %s})", new Object[]{s, str, tPNVideoEvent, str3, a});
                        FyberLogger.m8448d("RewardedVideoClient", "Notifying - " + str3);
                        this.f6155a.f6156a.m7811b(str3);
                    }
                }

                protected final void mo3867a(String str, Uri uri) {
                    C2623c c;
                    String queryParameter;
                    String optString;
                    if (str.equals("requestOffers")) {
                        int parseInt = Integer.parseInt(uri.getQueryParameter("n"));
                        c = this.f6156a.f6180s;
                        if (!this.f6156a.m7852h()) {
                            queryParameter = uri.getQueryParameter("params");
                            if (StringUtils.notNullNorEmpty(queryParameter)) {
                                try {
                                    JSONObject optJSONObject = new JSONObject(queryParameter).optJSONObject("fill");
                                    if (optJSONObject != null) {
                                        optString = optJSONObject.optString("provider_type");
                                        Object optString2 = optJSONObject.optString("id");
                                        if (optJSONObject.optBoolean("uses_tpn", false)) {
                                            this.f6156a.f6181t = new Offer(optString, optString2, c.m8413b());
                                            this.f6156a.f6181t.getProviderRequest().m8228a(C2580a.f6458a, optString2).m8228a("AD_FORMAT", AdFormat.REWARDED_VIDEO).m8228a("PROVIDER_STATUS", Integer.valueOf(0)).m8228a("id", optString2).m8228a(RewardedVideoActivity.PLAY_EXCHANGE_AD_KEY_BUNDLE, Boolean.valueOf(false));
                                        }
                                    }
                                } catch (Exception e) {
                                    FyberLogger.m8450e("RewardedVideoClient", "Couldn't create the offer", e);
                                }
                            }
                            C2469d.m7801a(this.f6156a, parseInt, C2603a.m8351a(uri.getQueryParameter("params")).m8355a(), this.f6156a.m7848d());
                            this.f6156a.f6169h = uri.getQueryParameter("currency_id");
                        }
                    } else if (str.equals("start")) {
                        this.f6156a.m7806a(uri.getQueryParameter("status"));
                    } else if (str.equals(Constants.DOM_VALIDATE)) {
                        queryParameter = uri.getQueryParameter("tpn");
                        final String a = C2573a.f6454a.m8213a(queryParameter);
                        optString = uri.getQueryParameter("id");
                        FyberLogger.m8448d("RewardedVideoClient", "RewardedVideo client asks to validate a third party network: " + queryParameter);
                        if (C2573a.f6454a.m8220a(queryParameter, AdFormat.REWARDED_VIDEO)) {
                            final Uri uri2 = uri;
                            Fyber.getConfigs().m7600a(new Runnable(this) {
                                final /* synthetic */ C24679 f6154e;

                                public final void run() {
                                    C2623c c = this.f6154e.f6156a.f6180s;
                                    if (this.f6154e.f6156a.m7852h()) {
                                        FyberLogger.m8448d("RewardedVideoClient", "It seems the client has already been cleared... Not performing the validation of the following network - " + queryParameter);
                                        return;
                                    }
                                    Offer offer = new Offer(queryParameter, optString, c.m8413b());
                                    C2580a a = new C2580a().m8228a(C2580a.f6458a, optString).m8228a("AD_FORMAT", AdFormat.REWARDED_VIDEO).m8228a("PROVIDER_STATUS", Integer.valueOf(-1)).m8228a("id", optString);
                                    offer.setProviderRequest(a);
                                    a.m8228a("CACHE_CONFIG", C2612a.m8380a(uri2.getQueryParameter("params")).m8382a());
                                    try {
                                        if (((Boolean) C2573a.f6454a.m8215a(this.f6154e.f6156a.f6166e, offer).get(4500, TimeUnit.MILLISECONDS)).booleanValue()) {
                                            C2469d.m7802a(this.f6154e.f6156a, offer, queryParameter, a, TPNVideoValidationResult.Success, optString);
                                        } else {
                                            C2469d.m7802a(this.f6154e.f6156a, offer, queryParameter, a, TPNVideoValidationResult.NoVideoAvailable, optString);
                                        }
                                    } catch (InterruptedException e) {
                                        C2469d.m7802a(this.f6154e.f6156a, offer, queryParameter, a, TPNVideoValidationResult.Error, optString);
                                    } catch (ExecutionException e2) {
                                        C2469d.m7802a(this.f6154e.f6156a, offer, queryParameter, a, TPNVideoValidationResult.Error, optString);
                                    } catch (TimeoutException e3) {
                                        C2469d.m7802a(this.f6154e.f6156a, offer, queryParameter, a, TPNVideoValidationResult.Timeout, optString);
                                    }
                                }
                            });
                            return;
                        }
                        C2469d.m7802a(this.f6156a, null, queryParameter, a, TPNVideoValidationResult.AdapterNotIntegrated, optString);
                    } else if (str.equals("ready")) {
                        r1 = uri.getQueryParameter(FavaDiagnosticsEntity.EXTRA_NAMESPACE);
                        C2469d c2469d = this.f6156a;
                        if (r1 == null) {
                            r0 = "Sponsorpay.MBE.SDKInterface";
                        } else {
                            r0 = r1;
                        }
                        c2469d.f6183v = r0;
                        FyberLogger.m8448d("RewardedVideoClient", "JavascriptInterface 'ready' called with namespace = " + r1);
                        this.f6156a.f6182u.countDown();
                    } else if (str.equals("play")) {
                        r0 = uri.getQueryParameter("tpn");
                        if (r0.equals(ImagesContract.LOCAL)) {
                            this.f6156a.f6163b.removeMessages(1);
                            if (this.f6156a.f6165d != null) {
                                if (this.f6156a.f6175n == null) {
                                    this.f6156a.f6175n = new C2468a(this.f6156a.f6164c, this.f6156a.f6183v);
                                }
                                this.f6156a.f6174m = new C2541a().m8049a(this.f6156a.f6175n).m8053a(uri.getQueryParameter("id")).m8056b(uri.getQueryParameter("clickThroughUrl")).m8058d(uri.getQueryParameter("alertMessage")).m8059e(uri.getQueryParameter("showAlert")).m8051a(this.f6156a).m8050a(new C2451c()).m8055a(this.f6156a.f6165d);
                                this.f6156a.f6165d.setRewardedVideoListener(this.f6156a.f6174m);
                                this.f6156a.f6174m.m8096a();
                                this.f6156a.f6174m.m8099b();
                                this.f6156a.f6165d.addContentView(this.f6156a.f6174m, new LayoutParams(-1, -1));
                                return;
                            }
                            FyberLogger.m8448d("RewardedVideoClient", "There was an issue - we were unable to attach the video player. ");
                            return;
                        }
                        Map hashMap = new HashMap(1);
                        hashMap.put("id", uri.getQueryParameter("id"));
                        FyberLogger.m8448d("RewardedVideoClient", "RewardedVideo client asks to play an offer from a third party network:" + r0);
                        C2573a.f6454a.m8218a(this.f6156a.f6165d, r0, hashMap, new C24662(this));
                    } else if (str.equals("jud")) {
                        c = this.f6156a.f6180s;
                        if (this.f6156a.m7852h()) {
                            FyberLogger.m8448d("RewardedVideoClient", "It seems that the client was already cleared, not adding any user data information");
                            r0 = null;
                        } else {
                            Map d = c.m8419e().m8438d();
                            if (C2664l.m8521b(d)) {
                                r0 = (String) d.get("X-User-Data");
                            }
                            r0 = null;
                        }
                        if (StringUtils.nullOrEmpty(r0)) {
                            r0 = "";
                        }
                        r1 = this.f6156a.f6183v;
                        r0 = String.format(Locale.ENGLISH, "javascript:%s.trigger('jud', '%s')", new Object[]{r1, r0});
                        FyberLogger.m8451i("RewardedVideoClient", "JUD tracking event will be called:" + r0);
                        this.f6156a.m7811b(r0);
                    }
                }

                public final void onReceivedError(WebView webView, int i, String str, String str2) {
                    FyberLogger.m8448d("RewardedVideoClient", "onReceivedError url - " + str2 + " - " + str);
                    if (str2.startsWith("market://")) {
                        FyberLogger.m8448d("RewardedVideoClient", "discarding error - market:// url");
                        return;
                    }
                    if (this.f6156a.f6170i == C2471f.QUERYING_SERVER_FOR_OFFERS) {
                        this.f6156a.f6163b.removeMessages(2);
                        this.f6156a.f6179r.m8288d(AdFormat.REWARDED_VIDEO);
                        this.f6156a.m7822i();
                    } else if (this.f6156a.f6174m != null) {
                        this.f6156a.f6174m.onError(null, -1, -1);
                    } else {
                        this.f6156a.m7814c(C2671s.m8532a(UIStringIdentifier.RV_ERROR_DIALOG_MESSAGE_DEFAULT));
                    }
                    super.onReceivedError(webView, i, str, str2);
                }

                protected final void mo3868b() {
                    this.f6156a.m7806a("USER_ENGAGED");
                    this.f6156a.m7805a(C2470a.PENDING_CLOSE);
                }

                protected final void mo3895c() {
                    this.f6156a.m7814c(C2671s.m8532a(UIStringIdentifier.ERROR_PLAY_STORE_UNAVAILABLE));
                }

                protected final Activity mo3865a() {
                    return this.f6156a.f6165d;
                }

                protected final void mo3866a(int i, String str) {
                    Activity p = this.f6156a.f6165d;
                    if (p != null) {
                        p.setResult(i);
                        m7681a(str);
                    }
                }
            };
        }
        return c2469d.f6172k;
    }
}
