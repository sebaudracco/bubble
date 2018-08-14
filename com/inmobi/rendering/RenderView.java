package com.inmobi.rendering;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.JsResult;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AbsoluteLayout.LayoutParams;
import android.widget.FrameLayout;
import com.appnext.core.Ad;
import com.facebook.ads.AudienceNetworkActivity;
import com.inmobi.ads.AdContainer;
import com.inmobi.ads.AdContainer.C2892a;
import com.inmobi.ads.AdContainer.EventType;
import com.inmobi.ads.AdContainer.RenderingProperties;
import com.inmobi.ads.AdContainer.RenderingProperties.PlacementType;
import com.inmobi.ads.AdUnit.AdMarkupType;
import com.inmobi.ads.C3046c.C3040d;
import com.inmobi.ads.C3046c.C3042f;
import com.inmobi.ads.C3078q;
import com.inmobi.ads.C3098x;
import com.inmobi.ads.ViewableAd;
import com.inmobi.ads.bi;
import com.inmobi.ads.bj;
import com.inmobi.ads.bs;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.C3153b;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.DisplayInfo;
import com.inmobi.commons.core.utilities.info.DisplayInfo.ORIENTATION_VALUES;
import com.inmobi.commons.p112a.C3105a;
import com.inmobi.rendering.mraid.C3221b;
import com.inmobi.rendering.mraid.C3222c;
import com.inmobi.rendering.mraid.C3226e;
import com.inmobi.rendering.mraid.C3227f;
import com.inmobi.rendering.mraid.C3229g;
import com.inmobi.rendering.mraid.C3231h;
import com.inmobi.rendering.mraid.C3232i;
import com.inmobi.rendering.mraid.C3233j;
import com.inmobi.rendering.mraid.C3236k;
import com.inmobi.rendering.mraid.MraidMediaProcessor;
import com.integralads.avid.library.inmobi.session.C3323a;
import com.mopub.mraid.MraidNativeCommandHandler;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"SetJavaScriptEnabled", "ViewConstructor", "ClickableViewAccessibility"})
public final class RenderView extends WebView implements AdContainer {
    static final C2912a f7900a = new C31801();
    private static final String f7901b = RenderView.class.getSimpleName();
    private boolean f7902A = true;
    private boolean f7903B = false;
    private boolean f7904C = false;
    private boolean f7905D = false;
    private boolean f7906E = false;
    private boolean f7907F = false;
    private String f7908G = null;
    private boolean f7909H = false;
    private AtomicBoolean f7910I = new AtomicBoolean(false);
    private final Object f7911J = new Object();
    private final Object f7912K = new Object();
    private boolean f7913L;
    private boolean f7914M = true;
    private View f7915N;
    private CustomViewCallback f7916O;
    private int f7917P = -1;
    private boolean f7918Q = false;
    private String f7919R;
    @Nullable
    private Set<bi> f7920S;
    private ViewableAd f7921T;
    private final C2892a f7922U = new C31834(this);
    private final WebViewClient f7923V = new C31845(this);
    private final WebChromeClient f7924W = new C31926(this);
    private RenderView f7925c;
    private List<C3221b> f7926d = new ArrayList();
    private WeakReference<Activity> f7927e;
    @Nullable
    private WeakReference<Activity> f7928f;
    private boolean f7929g = false;
    private WeakReference<ViewGroup> f7930h;
    private C2912a f7931i;
    private RenderViewState f7932j = RenderViewState.DEFAULT;
    private RenderingProperties f7933k;
    private C3226e f7934l;
    private C3231h f7935m;
    private MraidMediaProcessor f7936n;
    private C3236k f7937o;
    private a f7938p;
    private C3042f f7939q;
    private C3040d f7940r;
    private List<String> f7941s = new ArrayList();
    private boolean f7942t;
    private C3222c f7943u;
    private C3233j f7944v;
    private C3232i f7945w;
    private JSONObject f7946x;
    private JSONObject f7947y;
    private boolean f7948z = true;

    public interface C2912a {
        void mo6103a(RenderView renderView);

        void mo6104a(RenderView renderView, @NonNull HashMap<Object, Object> hashMap);

        void mo6106b(RenderView renderView);

        void mo6107b(RenderView renderView, @NonNull HashMap<Object, Object> hashMap);

        void mo6108b(String str, String str2, Map<String, Object> map);

        void mo6110c(RenderView renderView);

        void mo6111d(RenderView renderView);

        void mo6112e(RenderView renderView);

        void mo6113f(RenderView renderView);

        void mo6114g(RenderView renderView);

        void mo6115h(RenderView renderView);
    }

    static class C31801 implements C2912a {
        C31801() {
        }

        public void mo6103a(RenderView renderView) {
        }

        public void mo6106b(RenderView renderView) {
        }

        public void mo6110c(RenderView renderView) {
        }

        public void mo6111d(RenderView renderView) {
        }

        public void mo6112e(RenderView renderView) {
        }

        public void mo6113f(RenderView renderView) {
        }

        public void mo6114g(RenderView renderView) {
        }

        public void mo6104a(RenderView renderView, @NonNull HashMap<Object, Object> hashMap) {
        }

        public void mo6107b(RenderView renderView, @NonNull HashMap<Object, Object> hashMap) {
        }

        public void mo6115h(RenderView renderView) {
        }

        public void mo6108b(String str, String str2, Map<String, Object> map) {
        }
    }

    class C31823 implements C2912a {
        final /* synthetic */ RenderView f7880a;

        C31823(RenderView renderView) {
            this.f7880a = renderView;
        }

        public void mo6103a(RenderView renderView) {
        }

        public void mo6106b(RenderView renderView) {
        }

        public void mo6110c(RenderView renderView) {
        }

        public void mo6111d(RenderView renderView) {
        }

        public void mo6112e(RenderView renderView) {
        }

        public void mo6113f(RenderView renderView) {
        }

        public void mo6114g(RenderView renderView) {
        }

        public void mo6104a(RenderView renderView, @NonNull HashMap<Object, Object> hashMap) {
        }

        public void mo6107b(RenderView renderView, @NonNull HashMap<Object, Object> hashMap) {
        }

        public void mo6115h(RenderView renderView) {
        }

        public void mo6108b(String str, String str2, Map<String, Object> map) {
        }
    }

    class C31834 implements C2892a {
        final /* synthetic */ RenderView f7881a;

        C31834(RenderView renderView) {
            this.f7881a = renderView;
        }

        public void mo6201a() {
            Logger.m10359a(InternalLogLevel.INTERNAL, RenderView.f7901b, "onAdScreenDisplayFailed");
            if (this.f7881a.f7931i != null) {
                this.f7881a.f7931i.mo6112e(this.f7881a);
            }
        }

        public void mo6202a(Object obj) {
            Logger.m10359a(InternalLogLevel.INTERNAL, RenderView.f7901b, "onAdScreenDisplayed");
            if (PlacementType.PLACEMENT_TYPE_INLINE == this.f7881a.f7933k.m8613a()) {
                if (this.f7881a.f7925c != null) {
                    this.f7881a.f7925c.setAndUpdateViewState(RenderViewState.EXPANDED);
                } else {
                    this.f7881a.setAndUpdateViewState(RenderViewState.EXPANDED);
                }
                this.f7881a.f7913L = false;
            }
            if (this.f7881a.f7931i != null) {
                this.f7881a.f7931i.mo6113f(this.f7881a);
            }
        }

        public void mo6203b(Object obj) {
            Logger.m10359a(InternalLogLevel.INTERNAL, RenderView.f7901b, "onAdScreenDismissed");
            if (PlacementType.PLACEMENT_TYPE_INLINE == this.f7881a.f7933k.m8613a()) {
                this.f7881a.setAndUpdateViewState(RenderViewState.DEFAULT);
                if (this.f7881a.f7925c != null) {
                    this.f7881a.f7925c.setAndUpdateViewState(RenderViewState.DEFAULT);
                }
            } else if (RenderViewState.DEFAULT == this.f7881a.f7932j) {
                this.f7881a.setAndUpdateViewState(RenderViewState.HIDDEN);
            }
            if (this.f7881a.f7931i != null) {
                this.f7881a.f7931i.mo6114g(this.f7881a);
            }
        }
    }

    class C31845 extends WebViewClient {
        final /* synthetic */ RenderView f7882a;

        C31845(RenderView renderView) {
            this.f7882a = renderView;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            if (VERSION.SDK_INT < 21) {
                return false;
            }
            String uri = webResourceRequest.getUrl().toString();
            Logger.m10359a(InternalLogLevel.INTERNAL, RenderView.f7901b, "Placement type: " + this.f7882a.f7933k.m8613a() + " url:" + uri);
            if (!(this.f7882a.f7929g || "about:blank".equals(uri))) {
                this.f7882a.m10618s();
            }
            if (PlacementType.PLACEMENT_TYPE_FULLSCREEN != this.f7882a.f7933k.m8613a()) {
                Logger.m10359a(InternalLogLevel.INTERNAL, RenderView.f7901b, "Override URL loading (returned true): " + uri);
                if (C3153b.m10395a(this.f7882a.getRenderViewContext(), uri, null)) {
                    this.f7882a.getListener().mo6115h(this.f7882a);
                }
                return true;
            } else if (this.f7882a.f7929g && C3153b.m10397a(uri)) {
                Logger.m10359a(InternalLogLevel.INTERNAL, RenderView.f7901b, "Override URL loading (returned false): " + uri);
                return false;
            } else {
                Logger.m10359a(InternalLogLevel.INTERNAL, RenderView.f7901b, "Override URL loading (returned true): " + uri);
                if (C3153b.m10395a(this.f7882a.getRenderViewContext(), uri, null)) {
                    this.f7882a.getListener().mo6115h(this.f7882a);
                }
                return true;
            }
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Logger.m10359a(InternalLogLevel.INTERNAL, RenderView.f7901b, "Placement type: " + this.f7882a.f7933k.m8613a() + " url:" + str);
            if (!(this.f7882a.f7929g || "about:blank".equals(str))) {
                this.f7882a.m10618s();
            }
            if (PlacementType.PLACEMENT_TYPE_FULLSCREEN != this.f7882a.f7933k.m8613a()) {
                Logger.m10359a(InternalLogLevel.INTERNAL, RenderView.f7901b, "Override URL loading (returned true): " + str);
                if (!C3153b.m10395a(this.f7882a.getRenderViewContext(), str, null)) {
                    return true;
                }
                this.f7882a.getListener().mo6115h(this.f7882a);
                return true;
            } else if (this.f7882a.f7929g && C3153b.m10397a(str)) {
                Logger.m10359a(InternalLogLevel.INTERNAL, RenderView.f7901b, "Override URL loading (returned false): " + str);
                return false;
            } else {
                Logger.m10359a(InternalLogLevel.INTERNAL, RenderView.f7901b, "Override URL loading (returned true): " + str);
                if (!C3153b.m10395a(this.f7882a.getRenderViewContext(), str, null)) {
                    return true;
                }
                this.f7882a.getListener().mo6115h(this.f7882a);
                return true;
            }
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            Logger.m10359a(InternalLogLevel.INTERNAL, RenderView.f7901b, "Page load started:" + str);
            this.f7882a.f7909H = false;
            this.f7882a.setAndUpdateViewState(RenderViewState.LOADING);
        }

        public void onPageFinished(WebView webView, String str) {
            Logger.m10359a(InternalLogLevel.INTERNAL, RenderView.f7901b, "Page load finished:" + str);
            if (this.f7882a.f7941s.contains(str) && !this.f7882a.f7909H) {
                this.f7882a.f7909H = true;
                Logger.m10359a(InternalLogLevel.INTERNAL, RenderView.f7901b, "Injecting MRAID javascript for two piece creatives.");
                this.f7882a.m10637b(this.f7882a.getMraidJsString());
            }
            if (RenderViewState.LOADING == this.f7882a.f7932j) {
                this.f7882a.f7931i.mo6110c(this.f7882a);
                this.f7882a.m10619t();
                if (this.f7882a.f7925c != null) {
                    this.f7882a.setAndUpdateViewState(RenderViewState.EXPANDED);
                } else {
                    this.f7882a.setAndUpdateViewState(RenderViewState.DEFAULT);
                }
            }
        }

        public void onLoadResource(WebView webView, String str) {
            Logger.m10359a(InternalLogLevel.INTERNAL, RenderView.f7901b, "Resource loading:" + str);
            String url = this.f7882a.getUrl();
            if (str != null && url != null && str.contains("/mraid.js") && !url.equals("about:blank") && !url.startsWith("file:")) {
                if (!this.f7882a.f7941s.contains(url)) {
                    this.f7882a.f7941s.add(url);
                }
                if (!this.f7882a.f7909H) {
                    this.f7882a.f7909H = true;
                    Logger.m10359a(InternalLogLevel.INTERNAL, RenderView.f7901b, "Injecting MRAID javascript for two piece creatives.");
                    this.f7882a.m10637b(this.f7882a.getMraidJsString());
                }
            }
        }

        @TargetApi(22)
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            Logger.m10359a(InternalLogLevel.INTERNAL, RenderView.f7901b, "Loading error. Error code:" + i + " Error msg:" + str + " Failing url:" + str2);
        }

        @TargetApi(23)
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            Logger.m10359a(InternalLogLevel.INTERNAL, RenderView.f7901b, "Loading error. Error code:" + webResourceError.getErrorCode() + " Error msg:" + webResourceError.getDescription() + " Failing url:" + webResourceRequest.getUrl());
        }
    }

    class C31926 extends WebChromeClient {
        final /* synthetic */ RenderView f7897a;

        class C31884 implements OnTouchListener {
            final /* synthetic */ C31926 f7889a;

            C31884(C31926 c31926) {
                this.f7889a = c31926;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        }

        class C31895 implements OnKeyListener {
            final /* synthetic */ C31926 f7890a;

            C31895(C31926 c31926) {
                this.f7890a = c31926;
            }

            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (4 != keyEvent.getKeyCode() || keyEvent.getAction() != 0) {
                    return false;
                }
                Logger.m10359a(InternalLogLevel.INTERNAL, RenderView.f7901b, "Back pressed when HTML5 video is playing.");
                this.f7890a.m10586a();
                return true;
            }
        }

        C31926(RenderView renderView) {
            this.f7897a = renderView;
        }

        public boolean onJsAlert(WebView webView, String str, String str2, final JsResult jsResult) {
            Logger.m10359a(InternalLogLevel.INTERNAL, RenderView.f7901b, "jsAlert called with: " + str2 + str);
            if (this.f7897a.f7927e == null || this.f7897a.f7927e.get() == null) {
                jsResult.confirm();
            } else {
                new Builder((Context) this.f7897a.f7927e.get()).setMessage(str2).setTitle(str).setPositiveButton(17039370, new OnClickListener(this) {
                    final /* synthetic */ C31926 f7884b;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        jsResult.confirm();
                    }
                }).setCancelable(false).create().show();
            }
            return true;
        }

        public boolean onJsConfirm(WebView webView, String str, String str2, final JsResult jsResult) {
            if (this.f7897a.f7927e == null || this.f7897a.f7927e.get() == null) {
                jsResult.confirm();
            } else {
                new Builder((Context) this.f7897a.f7927e.get()).setMessage(str2).setPositiveButton(17039370, new OnClickListener(this) {
                    final /* synthetic */ C31926 f7888b;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        jsResult.confirm();
                    }
                }).setNegativeButton(17039360, new OnClickListener(this) {
                    final /* synthetic */ C31926 f7886b;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        jsResult.cancel();
                    }
                }).create().show();
            }
            return true;
        }

        public void onShowCustomView(View view, CustomViewCallback customViewCallback) {
            if (this.f7897a.f7927e != null && this.f7897a.f7927e.get() != null) {
                this.f7897a.f7915N = view;
                this.f7897a.f7916O = customViewCallback;
                this.f7897a.f7915N.setOnTouchListener(new C31884(this));
                FrameLayout frameLayout = (FrameLayout) ((Activity) this.f7897a.f7927e.get()).findViewById(16908290);
                this.f7897a.f7915N.setBackgroundColor(-16777216);
                frameLayout.addView(this.f7897a.f7915N, new LayoutParams(-1, -1, 0, 0));
                this.f7897a.f7915N.requestFocus();
                m10587a(this.f7897a.f7915N, new C31895(this));
            }
        }

        private void m10587a(View view, OnKeyListener onKeyListener) {
            view.setOnKeyListener(onKeyListener);
            view.setFocusable(true);
            view.setFocusableInTouchMode(true);
            view.requestFocus();
        }

        public void onHideCustomView() {
            m10586a();
            super.onHideCustomView();
        }

        private void m10586a() {
            if (this.f7897a.f7915N != null) {
                if (this.f7897a.f7916O != null) {
                    this.f7897a.f7916O.onCustomViewHidden();
                    this.f7897a.f7916O = null;
                }
                if (this.f7897a.f7915N != null && this.f7897a.f7915N.getParent() != null) {
                    ((ViewGroup) this.f7897a.f7915N.getParent()).removeView(this.f7897a.f7915N);
                    this.f7897a.f7915N = null;
                }
            }
        }

        public void onGeolocationPermissionsShowPrompt(final String str, final Callback callback) {
            String str2 = "Location Permission";
            str2 = "Allow location access";
            if (!(this.f7897a.f7927e == null || this.f7897a.f7927e.get() == null)) {
                new Builder((Context) this.f7897a.f7927e.get()).setTitle("Location Permission").setMessage("Allow location access").setPositiveButton(17039370, new OnClickListener(this) {
                    final /* synthetic */ C31926 f7896c;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        callback.invoke(str, true, false);
                    }
                }).setNegativeButton(17039360, new OnClickListener(this) {
                    final /* synthetic */ C31926 f7893c;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        callback.invoke(str, false, false);
                    }
                }).create().show();
            }
            super.onGeolocationPermissionsShowPrompt(str, callback);
        }

        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            Logger.m10359a(InternalLogLevel.INTERNAL, RenderView.f7901b, "Console message:" + (consoleMessage.message() + " -- From line " + consoleMessage.lineNumber() + " of " + consoleMessage.sourceId()));
            return true;
        }
    }

    public enum RenderViewState {
        LOADING,
        DEFAULT,
        RESIZED,
        EXPANDED,
        EXPANDING,
        HIDDEN,
        RESIZING
    }

    public RenderView(Context context, RenderingProperties renderingProperties, @Nullable Set<bi> set, @Nullable String str) {
        super(context.getApplicationContext());
        if (context instanceof Activity) {
            this.f7928f = new WeakReference((Activity) context);
        }
        this.f7925c = null;
        this.f7933k = renderingProperties;
        this.f7913L = false;
        this.f7920S = set;
        this.f7919R = str;
    }

    public String getImpressionId() {
        return this.f7919R;
    }

    public void setOriginalRenderView(RenderView renderView) {
        this.f7925c = renderView;
    }

    public RenderView getOriginalRenderView() {
        return this.f7925c;
    }

    public Object getDataModel() {
        return null;
    }

    public C2892a getFullScreenEventsListener() {
        return this.f7922U;
    }

    public RenderingProperties getRenderingProperties() {
        return this.f7933k;
    }

    public RenderViewState getState() {
        return this.f7932j;
    }

    public boolean m10647d() {
        return this.f7905D;
    }

    public Object getDefaultPositionMonitor() {
        return this.f7911J;
    }

    public Object getCurrentPositionMonitor() {
        return this.f7912K;
    }

    public boolean m10649e() {
        return this.f7948z;
    }

    public boolean m10650f() {
        return this.f7902A;
    }

    public void setDefaultPositionLock() {
        this.f7948z = true;
    }

    public void setCurrentPositionLock() {
        this.f7902A = true;
    }

    public Context getRenderViewContext() {
        if (this.f7927e == null || this.f7927e.get() == null) {
            return getContext();
        }
        return (Context) this.f7927e.get();
    }

    public void setDefaultPosition() {
        int[] iArr = new int[2];
        this.f7946x = new JSONObject();
        if (this.f7930h == null) {
            this.f7930h = new WeakReference((ViewGroup) getParent());
        }
        if (this.f7930h.get() != null) {
            ((ViewGroup) this.f7930h.get()).getLocationOnScreen(iArr);
            try {
                this.f7946x.put("x", DisplayInfo.m10422b(iArr[0]));
                this.f7946x.put("y", DisplayInfo.m10422b(iArr[1]));
                int b = DisplayInfo.m10422b(((ViewGroup) this.f7930h.get()).getWidth());
                int b2 = DisplayInfo.m10422b(((ViewGroup) this.f7930h.get()).getHeight());
                this.f7946x.put("width", b);
                this.f7946x.put("height", b2);
            } catch (JSONException e) {
            }
        } else {
            try {
                this.f7946x.put("x", 0);
                this.f7946x.put("y", 0);
                this.f7946x.put("width", 0);
                this.f7946x.put("height", 0);
            } catch (JSONException e2) {
            }
        }
        synchronized (this.f7911J) {
            this.f7948z = false;
            this.f7911J.notifyAll();
        }
    }

    public String getDefaultPosition() {
        return this.f7946x == null ? "" : this.f7946x.toString();
    }

    public void setCurrentPosition() {
        this.f7947y = new JSONObject();
        int[] iArr = new int[2];
        getLocationOnScreen(iArr);
        try {
            this.f7947y.put("x", DisplayInfo.m10422b(iArr[0]));
            this.f7947y.put("y", DisplayInfo.m10422b(iArr[1]));
            int b = DisplayInfo.m10422b(getWidth());
            int b2 = DisplayInfo.m10422b(getHeight());
            this.f7947y.put("width", b);
            this.f7947y.put("height", b2);
        } catch (JSONException e) {
        }
        synchronized (this.f7912K) {
            this.f7902A = false;
            this.f7912K.notifyAll();
        }
    }

    public String getCurrentPosition() {
        return this.f7947y == null ? "" : this.f7947y.toString();
    }

    public void setFullScreenActivityContext(Activity activity) {
        this.f7927e = new WeakReference(activity);
        if (this.f7945w != null) {
            setOrientationProperties(this.f7945w);
        }
    }

    public Activity getFullScreenActivity() {
        return this.f7927e == null ? null : (Activity) this.f7927e.get();
    }

    public Activity getPubActivity() {
        return this.f7928f == null ? null : (Activity) this.f7928f.get();
    }

    public C3042f getRenderingConfig() {
        return this.f7939q;
    }

    public C3040d getMraidConfig() {
        return this.f7940r;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        Logger.m10359a(InternalLogLevel.INTERNAL, f7901b, "onSizeChanged (" + i + ", " + i2 + ")");
        if (i != 0 && i2 != 0) {
            m10592a(DisplayInfo.m10422b(i), DisplayInfo.m10422b(i2));
        }
    }

    public void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        boolean z = i == 0;
        if (this.f7905D != z) {
            m10602e(z);
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        this.f7918Q = !z;
        m10600d(z);
    }

    public void onScreenStateChanged(int i) {
        super.onScreenStateChanged(i);
        if (i == 0) {
            m10600d(false);
        } else if (!this.f7918Q) {
            m10600d(true);
        }
    }

    private void m10600d(boolean z) {
        if (this.f7905D != z) {
            if (VERSION.SDK_INT <= 23 || getFullScreenActivity() == null || !getFullScreenActivity().isInMultiWindowMode()) {
                m10602e(z);
            }
        }
    }

    private void m10602e(boolean z) {
        if (!this.f7913L) {
            this.f7905D = z;
            if (z) {
                this.f7931i.mo6111d(this);
            } else {
                this.f7937o.m10802a(getRenderViewContext());
            }
            m10635a(this.f7905D);
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        m10616q();
        if (this.f7930h == null) {
            this.f7930h = new WeakReference((ViewGroup) getParent());
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        Logger.m10359a(InternalLogLevel.INTERNAL, f7901b, "Touch event received, action:" + motionEvent.getAction());
        m10652g();
        return super.onInterceptTouchEvent(motionEvent);
    }

    @TargetApi(11)
    private void m10616q() {
        if (VERSION.SDK_INT >= 11) {
            this.f7942t = isHardwareAccelerated();
        }
    }

    public void onDetachedFromWindow() {
        this.f7941s.clear();
        this.f7938p.unRegisterBroadcastListener();
        getMediaProcessor().m10736c();
        getMediaProcessor().m10738d();
        getMediaProcessor().m10741g();
        this.f7937o.m10802a(getRenderViewContext());
        try {
            super.onDetachedFromWindow();
        } catch (IllegalArgumentException e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7901b, "Detaching WebView from window encountered an error (" + e.getMessage() + ")");
            try {
                Map hashMap = new HashMap();
                hashMap.put("type", "IllegalArgumentException");
                hashMap.put("message", e.getMessage() + "");
                C3135c.m10255a().m10280a("ads", "ExceptionCaught", hashMap);
            } catch (Exception e2) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f7901b, "Error in submitting telemetey event : (" + e2.getMessage() + ")");
            }
        }
    }

    @SuppressLint({"AddJavascriptInterface"})
    @TargetApi(19)
    public void m10627a(C2912a c2912a, C3042f c3042f, C3040d c3040d) {
        this.f7939q = c3042f;
        this.f7940r = c3040d;
        this.f7931i = c2912a;
        this.f7930h = new WeakReference((ViewGroup) getParent());
        if ("row".equals("staging") && VERSION.SDK_INT >= 19) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        if (getRenderingConfig() != null) {
            setBackgroundColor(getRenderingConfig().m9670d());
        }
        if (getMraidConfig() != null && m10617r()) {
            new C3229g(getMraidConfig().m9647d(), getMraidConfig().m9645b(), getMraidConfig().m9646c()).m10782a();
        }
        if (VERSION.SDK_INT >= 16) {
            setImportantForAccessibility(2);
        }
        setScrollContainer(false);
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        if (VERSION.SDK_INT >= 17) {
            getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
        getSettings().setJavaScriptEnabled(true);
        getSettings().setGeolocationEnabled(true);
        setWebViewClient(this.f7923V);
        setWebChromeClient(this.f7924W);
        this.f7938p = new a(this, this.f7933k);
        addJavascriptInterface(this.f7938p, "sdkController");
        this.f7934l = new C3226e(this);
        this.f7935m = new C3231h(this);
        this.f7936n = new MraidMediaProcessor(this);
        this.f7937o = new C3236k(this);
        this.f7943u = new C3222c();
        this.f7944v = new C3233j();
        this.f7945w = new C3232i();
    }

    void setIsInAppBrowser(boolean z) {
        this.f7929g = z;
    }

    public boolean mo6176c() {
        return this.f7910I.get();
    }

    @TargetApi(11)
    public void destroy() {
        if (!this.f7910I.get()) {
            this.f7910I.set(true);
            this.f7913L = true;
            this.f7917P = -1;
            removeJavascriptInterface("sdkController");
            m10624y();
            if (this.f7927e != null) {
                this.f7927e.clear();
            }
            if (this.f7930h != null) {
                this.f7930h.clear();
            }
            if (this.f7921T != null) {
                this.f7921T.mo6229c();
                this.f7921T.mo6230d();
            }
            super.destroy();
        }
    }

    public void mo6174a(EventType eventType, Map<String, String> map) {
        switch (eventType) {
            case EVENT_TYPE_AD_SERVED:
                m10637b("inmobi.recordEvent(120,null);");
                return;
            default:
                return;
        }
    }

    public void setRequestedScreenOrientation() {
        if (getFullScreenActivity() != null && this.f7945w != null) {
            setOrientationProperties(this.f7945w);
        }
    }

    @Nullable
    public View getVideoContainerView() {
        return null;
    }

    public AdContainer getReferenceContainer() {
        return null;
    }

    @NonNull
    public ViewableAd getViewableAd() {
        if (this.f7921T == null) {
            Activity pubActivity;
            this.f7921T = new bs(this);
            if (getFullScreenActivity() == null) {
                pubActivity = getPubActivity();
            } else {
                pubActivity = getFullScreenActivity();
            }
            if (this.f7920S != null) {
                if (pubActivity != null) {
                    try {
                        for (bi biVar : this.f7920S) {
                            switch (biVar.f7267a) {
                                case AD_TRACKER_TYPE_IAS:
                                    C3323a c3323a = (C3323a) biVar.f7268b.get("avidAdSession");
                                    boolean z = biVar.f7268b.containsKey("deferred") && ((Boolean) biVar.f7268b.get("deferred")).booleanValue();
                                    if (c3323a == null) {
                                        Logger.m10359a(InternalLogLevel.INTERNAL, f7901b, "Did not find a AVID ad session; the IAS decorator will not be applied.");
                                        break;
                                    }
                                    this.f7921T = new C3078q(pubActivity, this.f7921T, c3323a, z);
                                    break;
                                    break;
                                case AD_TRACKER_TYPE_MOAT:
                                    this.f7921T = new C3098x(pubActivity, this.f7921T, biVar.f7268b);
                                    break;
                                default:
                                    break;
                            }
                        }
                    } catch (Exception e) {
                        Logger.m10359a(InternalLogLevel.INTERNAL, f7901b, "Exception occurred while creating the HTML viewable ad : " + e.getMessage());
                    }
                } else {
                    Map hashMap = new HashMap();
                    hashMap.put("type", getMarkupType());
                    if (getImpressionId() != null) {
                        hashMap.put("impId", getImpressionId());
                    }
                    C3135c.m10255a().m10280a("ads", "TrackersForService", hashMap);
                }
            }
        }
        return this.f7921T;
    }

    public AdMarkupType getMarkupType() {
        return AdMarkupType.AD_MARKUP_TYPE_INM_HTML;
    }

    private boolean m10617r() {
        return (System.currentTimeMillis() / 1000) - new C3227f().m10776c() > getMraidConfig().m9644a();
    }

    public void m10628a(String str) {
        this.f7913L = false;
        if (!this.f7910I.get()) {
            loadDataWithBaseURL("", str, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, "UTF-8", null);
        }
    }

    public void stopLoading() {
        if (!this.f7910I.get()) {
            super.stopLoading();
        }
    }

    public void m10652g() {
        m10637b("window.mraidview.onUserInteraction();");
    }

    private void m10618s() {
        m10637b("window.mraidview.detectAndBlockFraud('redirect')");
    }

    private void m10619t() {
        m10637b("window.imaiview.broadcastEvent('ready');");
        m10637b("window.mraidview.broadcastEvent('ready');");
    }

    private void m10592a(int i, int i2) {
        m10637b("window.mraidview.broadcastEvent('sizeChange'," + i + "," + i2 + ");");
    }

    public void m10635a(boolean z) {
        m10637b("window.mraidview.broadcastEvent('viewableChange'," + z + ");");
    }

    private void m10605g(String str) {
        m10637b("window.mraidview.broadcastEvent('stateChange','" + str + "');");
    }

    public void m10631a(String str, String str2, String str3) {
        m10630a(str, "broadcastEvent('error',\"" + str2 + "\", \"" + str3 + "\")");
    }

    public void m10630a(String str, String str2) {
        m10637b(str + "." + str2);
    }

    public void m10634a(String str, String str2, Map<String, Object> map) {
        this.f7931i.mo6108b(str, str2, map);
    }

    public void m10637b(final String str) {
        if (getRenderViewContext() != null) {
            new Handler(getRenderViewContext().getMainLooper()).post(new Runnable(this) {
                final /* synthetic */ RenderView f7879b;

                public void run() {
                    try {
                        if (!this.f7879b.f7910I.get()) {
                            String str = "javascript:try{" + str + "}catch(e){}";
                            Logger.m10359a(InternalLogLevel.INTERNAL, RenderView.f7901b, "Injecting javascript:" + str);
                            if (VERSION.SDK_INT < 19) {
                                this.f7879b.m10608i(str);
                            } else {
                                this.f7879b.m10611j(str);
                            }
                        }
                    } catch (Exception e) {
                        Logger.m10359a(InternalLogLevel.INTERNAL, RenderView.f7901b, "SDK encountered an unexpected error injecting JavaScript in the Ad container; " + e.getMessage());
                    }
                }
            });
        }
    }

    public void m10638b(String str, String str2) {
        if (PlacementType.PLACEMENT_TYPE_FULLSCREEN != this.f7933k.m8613a() && RenderViewState.EXPANDED != getViewState()) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7901b, "Media playback is only supported on full screen ads! Ignoring request ...");
        } else if (this.f7927e == null || this.f7927e.get() == null) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7901b, "Media playback is  not allowed before it is visible! Ignoring request ...");
            m10631a(str, "Media playback is  not allowed before it is visible! Ignoring request ...", "playVideo");
        } else {
            setAdActiveFlag(true);
            this.f7936n.m10733a(str2, (Activity) this.f7927e.get());
            Map hashMap = new HashMap();
            hashMap.put("command", "playVideo");
            hashMap.put("scheme", bj.m9518a(str));
            this.f7931i.mo6108b("ads", "CreativeInvokedAction", hashMap);
        }
    }

    public void m10642c(String str, String str2) {
        if (RenderViewState.DEFAULT == this.f7932j || RenderViewState.RESIZED == this.f7932j) {
            this.f7913L = true;
            this.f7934l.m10772a(str, str2);
            requestLayout();
            invalidate();
            this.f7904C = true;
            setFocusable(true);
            setFocusableInTouchMode(true);
            requestFocus();
            Map hashMap = new HashMap();
            hashMap.put("command", "expand");
            hashMap.put("scheme", bj.m9518a(str));
            this.f7931i.mo6108b("ads", "CreativeInvokedAction", hashMap);
            return;
        }
        Logger.m10359a(InternalLogLevel.INTERNAL, f7901b, "Render view state must be either DEFAULT or RESIZED to admit the expand request. Current state:" + this.f7932j);
    }

    public boolean m10653h() {
        return this.f7903B;
    }

    public void setUseCustomClose(boolean z) {
        this.f7903B = z;
    }

    public boolean m10654i() {
        return this.f7906E;
    }

    public void setCloseRegionDisabled(boolean z) {
        this.f7906E = z;
    }

    public void setDisableBackButton(boolean z) {
        this.f7907F = z;
    }

    public boolean m10655j() {
        return this.f7907F;
    }

    public void m10641c(String str) {
        this.f7908G = str;
    }

    public void m10656k() {
        this.f7908G = null;
    }

    private boolean m10620u() {
        return this.f7908G != null;
    }

    public void m10657l() {
        if (m10620u()) {
            String str = "broadcastEvent('backButtonPressed')";
            m10630a(this.f7908G, "broadcastEvent('backButtonPressed')");
        }
    }

    public void m10640b(boolean z) {
        setCloseRegionDisabled(z);
        View rootView = getRootView();
        if (rootView != null) {
            CustomView customView = (CustomView) rootView.findViewById(65531);
            if (customView != null) {
                customView.setVisibility(this.f7906E ? 8 : 0);
            }
        }
    }

    public void m10644c(boolean z) {
        setUseCustomClose(z);
        if (getRootView() != null) {
            CustomView customView = (CustomView) getRootView().findViewById(65532);
            if (customView != null) {
                customView.setVisibility(this.f7903B ? 8 : 0);
            }
        }
    }

    public void m10646d(String str) {
        if (RenderViewState.DEFAULT != this.f7932j && RenderViewState.RESIZED != this.f7932j) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7901b, "Render view state must be either DEFAULT or RESIZED to admit the resize request");
        } else if (getResizeProperties() == null) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7901b, "Render view state can not resize with invalid resize properties");
        } else {
            this.f7913L = true;
            this.f7935m.m10790a();
            requestLayout();
            invalidate();
            this.f7904C = true;
            setFocusable(true);
            setFocusableInTouchMode(true);
            requestFocus();
            setAndUpdateViewState(RenderViewState.RESIZED);
            this.f7931i.mo6113f(this);
            this.f7913L = false;
            Map hashMap = new HashMap();
            hashMap.put("command", "resize");
            hashMap.put("scheme", bj.m9518a(str));
            this.f7931i.mo6108b("ads", "CreativeInvokedAction", hashMap);
        }
    }

    public void mo6175b() {
        m10624y();
        this.f7936n.m10731a();
        if (RenderViewState.EXPANDED == this.f7932j) {
            m10622w();
        } else if (RenderViewState.RESIZED == this.f7932j) {
            m10623x();
        } else if (RenderViewState.DEFAULT == this.f7932j) {
            setAndUpdateViewState(RenderViewState.HIDDEN);
            if (PlacementType.PLACEMENT_TYPE_FULLSCREEN == this.f7933k.m8613a()) {
                m10621v();
            } else {
                ((ViewGroup) getParent()).removeAllViews();
            }
        }
        this.f7941s.clear();
        this.f7904C = false;
    }

    private void m10621v() {
        InMobiAdActivity.m10553a((Object) this);
        Activity fullScreenActivity = getFullScreenActivity();
        if (fullScreenActivity != null) {
            ((InMobiAdActivity) fullScreenActivity).m10560a(true);
            fullScreenActivity.finish();
            if (this.f7917P != -1) {
                fullScreenActivity.overridePendingTransition(0, this.f7917P);
                return;
            }
            return;
        }
        if (PlacementType.PLACEMENT_TYPE_INLINE == this.f7933k.m8613a()) {
            setAndUpdateViewState(RenderViewState.DEFAULT);
            if (this.f7925c != null) {
                this.f7925c.setAndUpdateViewState(RenderViewState.DEFAULT);
            }
        } else if (RenderViewState.DEFAULT == this.f7932j) {
            setAndUpdateViewState(RenderViewState.HIDDEN);
        }
        if (this.f7931i != null) {
            this.f7931i.mo6114g(this);
        }
    }

    public void setExitAnimation(int i) {
        this.f7917P = i;
    }

    public void m10658m() {
        if (this.f7932j == RenderViewState.RESIZED && getResizeProperties() != null) {
            this.f7935m.m10790a();
        }
    }

    private String m10606h(String str) {
        try {
            str = URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
        }
        return str;
    }

    public void m10632a(String str, String str2, String str3, @Nullable String str4) {
        try {
            C3105a.m10072a(getRenderViewContext(), Intent.parseUri(str3, 1));
            Map hashMap = new HashMap();
            hashMap.put("command", "openExternal");
            hashMap.put("scheme", bj.m9518a(str2));
            this.f7931i.mo6108b("ads", "CreativeInvokedAction", hashMap);
            getListener().mo6115h(this);
            m10630a(str2, "broadcastEvent('" + str + "Successful','" + str3 + "');");
        } catch (URISyntaxException e) {
            m10631a(str2, "Cannot resolve URI (" + m10606h(str3) + ")", str);
            Logger.m10359a(InternalLogLevel.INTERNAL, f7901b, "Error message in processing openExternal: " + e.getMessage());
            if (str4 != null) {
                m10632a(str, str2, str4, null);
            }
        } catch (ActivityNotFoundException e2) {
            m10631a(str2, "No app can handle the URI (" + m10606h(str3) + ")", str);
            Logger.m10359a(InternalLogLevel.INTERNAL, f7901b, "Error message in processing openExternal: " + e2.getMessage());
            if (str4 != null) {
                m10632a(str, str2, str4, null);
            }
        }
    }

    public void m10639b(String str, String str2, String str3) {
        if (str3 == null || (str3.startsWith("http") && !URLUtil.isValidUrl(str3))) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7901b, str + " called with invalid url (" + str3 + ")");
            m10631a(str2, "Invalid URL", str);
        } else if (!str3.startsWith("http") || str3.contains("play.google.com") || str3.contains("market.android.com") || str3.contains("market%3A%2F%2F")) {
            m10632a(str, str2, str3, null);
        } else {
            InMobiAdActivity.m10552a(this);
            Intent intent = new Intent(getRenderViewContext(), InMobiAdActivity.class);
            intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_TYPE", 100);
            intent.putExtra("com.inmobi.rendering.InMobiAdActivity.IN_APP_BROWSER_URL", str3);
            C3105a.m10072a(getRenderViewContext(), intent);
            m10630a(str2, "broadcastEvent('" + str + "Successful','" + str3 + "');");
            Map hashMap = new HashMap();
            hashMap.put("command", "openEmbedded");
            hashMap.put("scheme", bj.m9518a(str2));
            this.f7931i.mo6108b("ads", "CreativeInvokedAction", hashMap);
        }
    }

    public C2912a getListener() {
        if (this.f7931i != null) {
            return this.f7931i;
        }
        C2912a c31823 = new C31823(this);
        this.f7931i = c31823;
        return c31823;
    }

    public RenderViewState getViewState() {
        return this.f7932j;
    }

    public MraidMediaProcessor getMediaProcessor() {
        return this.f7936n;
    }

    public C3222c getExpandProperties() {
        return this.f7943u;
    }

    public void setExpandProperties(C3222c c3222c) {
        if (c3222c.m10754b()) {
            setUseCustomClose(c3222c.m10753a());
        }
        this.f7943u = c3222c;
    }

    public C3233j getResizeProperties() {
        return this.f7944v;
    }

    public void setResizeProperties(C3233j c3233j) {
        this.f7944v = c3233j;
    }

    public void setAndUpdateViewState(RenderViewState renderViewState) {
        this.f7932j = renderViewState;
        Logger.m10359a(InternalLogLevel.INTERNAL, f7901b, "set state:" + this.f7932j);
        m10605g(this.f7932j.toString().toLowerCase(Locale.ENGLISH));
    }

    public void m10659n() {
        setVisibility(0);
        requestLayout();
    }

    public void setAdActiveFlag(boolean z) {
        this.f7904C = z;
    }

    public C3232i getOrientationProperties() {
        return this.f7945w;
    }

    public void setOrientationProperties(C3232i c3232i) {
        this.f7945w = c3232i;
        if (this.f7927e != null && this.f7927e.get() != null && !c3232i.f8123a) {
            String str = c3232i.f8124b;
            int i = -1;
            switch (str.hashCode()) {
                case 729267099:
                    if (str.equals(Ad.ORIENTATION_PORTRAIT)) {
                        i = 1;
                        break;
                    }
                    break;
                case 1430647483:
                    if (str.equals(Ad.ORIENTATION_LANDSCAPE)) {
                        i = 0;
                        break;
                    }
                    break;
            }
            switch (i) {
                case 0:
                    i = (DisplayInfo.m10421b() == ORIENTATION_VALUES.LANDSCAPE.getValue() || DisplayInfo.m10421b() == ORIENTATION_VALUES.REVERSE_LANDSCAPE.getValue()) ? 1 : 0;
                    if (i != 0) {
                        if (ORIENTATION_VALUES.LANDSCAPE.getValue() == DisplayInfo.m10421b()) {
                            ((Activity) this.f7927e.get()).setRequestedOrientation(0);
                            return;
                        } else {
                            ((Activity) this.f7927e.get()).setRequestedOrientation(8);
                            return;
                        }
                    } else if (c3232i.f8125c.equals("left")) {
                        ((Activity) this.f7927e.get()).setRequestedOrientation(8);
                        return;
                    } else if (c3232i.f8125c.equals("right")) {
                        ((Activity) this.f7927e.get()).setRequestedOrientation(0);
                        return;
                    } else {
                        return;
                    }
                case 1:
                    if (DisplayInfo.m10421b() == ORIENTATION_VALUES.REVERSE_PORTRAIT.getValue()) {
                        ((Activity) this.f7927e.get()).setRequestedOrientation(9);
                        return;
                    } else {
                        ((Activity) this.f7927e.get()).setRequestedOrientation(1);
                        return;
                    }
                default:
                    if (DisplayInfo.m10421b() == ORIENTATION_VALUES.REVERSE_PORTRAIT.getValue()) {
                        ((Activity) this.f7927e.get()).setRequestedOrientation(9);
                        return;
                    } else if (DisplayInfo.m10421b() == ORIENTATION_VALUES.REVERSE_LANDSCAPE.getValue()) {
                        ((Activity) this.f7927e.get()).setRequestedOrientation(8);
                        return;
                    } else if (DisplayInfo.m10421b() == ORIENTATION_VALUES.LANDSCAPE.getValue()) {
                        ((Activity) this.f7927e.get()).setRequestedOrientation(0);
                        return;
                    } else {
                        ((Activity) this.f7927e.get()).setRequestedOrientation(1);
                        return;
                    }
            }
        }
    }

    public String getMraidJsString() {
        String str = "var imIsObjValid=function(a){return\"undefined\"!=typeof a&&null!=a?!0:!1},EventListeners=function(a){this.event=a;this.count=0;var b={};this.add=function(a){var d=String(a);b[d]||(b[d]=a,this.count++)};this.remove=function(a){a=String(a);return b[a]?(b[a]=null,delete b[a],this.count--,!0):!1};this.removeAll=function(){for(var a in b)this.remove(b[a])};this.broadcast=function(a){for(var d in b)b[d].apply({},a)};this.toString=function(){var c=[a,\":\"],d;for(d in b)c.push(\"|\",d,\"|\");return c.join(\"\")}},\nInmobiObj=function(){this.listeners=[];this.addEventListener=function(a,b){try{if(imIsObjValid(b)&&imIsObjValid(a)){var c=this.listeners;c[a]||(c[a]=new EventListeners);c[a].add(b);\"micIntensityChange\"==a&&window.imraidview.startListeningMicIntensity();\"deviceMuted\"==a&&window.imraidview.startListeningDeviceMuteEvents();\"deviceVolumeChange\"==a&&window.imraidview.startListeningDeviceVolumeChange();\"volumeChange\"==a&&window.imraidview.startListeningVolumeChange();\"headphones\"==a&&window.imraidview.startListeningHeadphonePluggedEvents();\n\"backButtonPressed\"==a&&window.imraidview.startListeningForBackButtonPressedEvent()}}catch(d){this.log(d)}};this.removeEventListener=function(a,b){if(imIsObjValid(a)){var c=this.listeners;imIsObjValid(c[a])&&(imIsObjValid(b)?c[a].remove(b):c[a].removeAll());\"micIntensityChange\"==a&&0==c[a].count&&window.imraidview.stopListeningMicIntensity();\"deviceMuted\"==a&&0==c[a].count&&window.imraidview.stopListeningDeviceMuteEvents();\"deviceVolumeChange\"==a&&0==c[a].count&&window.imraidview.stopListeningDeviceVolumeChange();\n\"volumeChange\"==a&&0==c[a].count&&window.imraidview.stopListeningVolumeChange();\"headphones\"==a&&0==c[a].count&&window.imraidview.stopListeningHeadphonePluggedEvents();\"backButtonPressed\"==a&&0==c[a].count&&window.imraidview.stopListeningForBackButtonPressedEvent()}};this.broadcastEvent=function(a){if(imIsObjValid(a)){for(var b=Array(arguments.length),c=0;c<arguments.length;c++)b[c]=arguments[c];c=b.shift();try{this.listeners[c]&&this.listeners[c].broadcast(b)}catch(d){}}};this.sendSaveContentResult=\nfunction(a){if(imIsObjValid(a)){for(var b=Array(arguments.length),c=0;c<arguments.length;c++)if(2==c){var d=arguments[c],d=JSON.parse(d);b[c]=d}else b[c]=arguments[c];d=b[1];\"success\"!=d&&(c=b[0].substring(b[0].indexOf(\"_\")+1),imraid.saveContentIDMap[c]&&delete imraid.saveContentIDMap[c]);window.imraid.broadcastEvent(b[0],b[1],b[2])}}},__im__iosNativeMessageHandler=void 0;\nwindow.webkit&&(window.webkit.messageHandlers&&window.webkit.messageHandlers.nativeMessageHandler)&&(__im__iosNativeMessageHandler=window.webkit.messageHandlers.nativeMessageHandler);\nvar __im__iosNativeCall={nativeCallInFlight:!1,nativeCallQueue:[],executeNativeCall:function(a){this.nativeCallInFlight?this.nativeCallQueue.push(a):(this.nativeCallInFlight=!0,imIsObjValid(__im__iosNativeMessageHandler)?__im__iosNativeMessageHandler.postMessage(a):window.location=a)},nativeCallComplete:function(a){0==this.nativeCallQueue.length?this.nativeCallInFlight=!1:(a=this.nativeCallQueue.shift(),imIsObjValid(__im__iosNativeMessageHandler)?__im__iosNativeMessageHandler.postMessage(a):window.location=\na)}},IOSNativeCall=function(){this.urlScheme=\"\";this.executeNativeCall=function(a){if(imIsObjValid(__im__iosNativeMessageHandler)){d={};d.command=a;d.scheme=this.urlScheme;for(var b={},c=1;c<arguments.length;c+=2)e=arguments[c+1],null!=e&&(b[arguments[c]]=\"\"+e);d.params=b}else for(var d=this.urlScheme+\"://\"+a,e,b=!0,c=1;c<arguments.length;c+=2)e=arguments[c+1],null!=e&&(b?(d+=\"?\",b=!1):d+=\"&\",d+=arguments[c]+\"=\"+escape(e));__im__iosNativeCall.executeNativeCall(d);return\"OK\"};this.nativeCallComplete=\nfunction(a){__im__iosNativeCall.nativeCallComplete(a);return\"OK\"};this.updateKV=function(a,b){this[a]=b;var c=this.broadcastMap[a];c&&this.broadcastEvent(c,b)}};\n(function(){var a=window.mraidview={};a.orientationProperties={allowOrientationChange:!0,forceOrientation:\"none\",direction:\"right\"};var b=[],c=!1;a.detectAndBlockFraud=function(d){a.isPossibleFraud()&&a.fireRedirectFraudBeacon(d);return!1};a.zeroPad=function(a){var e=\"\";10>a&&(e+=\"0\");return e+a};a.supports=function(a){console.log(\"bridge: supports (MRAID)\");if(\"string\"!=typeof a)window.mraid.broadcastEvent(\"error\",\"Supports method expects string parameter\",\"supports\");else return\"false\"!=sdkController.supports(\"window.mraidview\",\na)};a.useCustomClose=function(a){try{sdkController.useCustomClose(\"window.mraidview\",a)}catch(e){imraidview.showAlert(\"use CustomClose: \"+e)}};a.close=function(){try{sdkController.close(\"window.mraidview\")}catch(a){imraidview.showAlert(\"close: \"+a)}};a.stackCommands=function(a,e){c?b.push(a):(eval(a),e&&(c=!0))};a.expand=function(a){try{\"undefined\"==typeof a&&(a=null),sdkController.expand(\"window.mraidview\",a)}catch(e){imraidview.showAlert(\"executeNativeExpand: \"+e+\", URL = \"+a)}};a.setExpandProperties=\nfunction(d){try{d?this.props=d:d=null;if(\"undefined\"!=typeof d.lockOrientation&&null!=d.lockOrientation&&\"undefined\"!=typeof d.orientation&&null!=d.orientation){var e={};e.allowOrientationChange=!d.lockOrientation;e.forceOrientation=d.orientation;a.setOrientationProperties(e)}sdkController.setExpandProperties(\"window.mraidview\",a.stringify(d))}catch(b){imraidview.showAlert(\"executeNativesetExpandProperties: \"+b+\", props = \"+d)}};a.getExpandProperties=function(){try{return eval(\"(\"+sdkController.getExpandProperties(\"window.mraidview\")+\n\")\")}catch(a){imraidview.showAlert(\"getExpandProperties: \"+a)}};a.setOrientationProperties=function(d){try{d?(\"undefined\"!=typeof d.allowOrientationChange&&(a.orientationProperties.allowOrientationChange=d.allowOrientationChange),\"undefined\"!=typeof d.forceOrientation&&(a.orientationProperties.forceOrientation=d.forceOrientation)):d=null,sdkController.setOrientationProperties(\"window.mraidview\",a.stringify(a.orientationProperties))}catch(e){imraidview.showAlert(\"setOrientationProperties: \"+e+\", props = \"+\nd)}};a.getOrientationProperties=function(){return{forceOrientation:a.orientationProperties.forceOrientation,allowOrientationChange:a.orientationProperties.allowOrientationChange}};a.resizeProps=null;a.setResizeProperties=function(d){var e,b;try{e=parseInt(d.width);b=parseInt(d.height);if(isNaN(e)||isNaN(b)||1>e||1>b)throw\"Invalid\";d.width=e;d.height=b;a.resizeProps=d;sdkController.setResizeProperties(\"window.mraidview\",a.stringify(d))}catch(c){window.mraid.broadcastEvent(\"error\",\"Invalid properties.\",\n\"setResizeProperties\")}};a.getResizeProperties=function(){try{return eval(\"(\"+sdkController.getResizeProperties(\"window.mraidview\")+\")\")}catch(a){imraidview.showAlert(\"getResizeProperties: \"+a)}};a.open=function(a){\"undefined\"==typeof a&&(a=null);try{sdkController.open(\"window.mraidview\",a)}catch(e){imraidview.showAlert(\"open: \"+e)}};a.getScreenSize=function(){try{return eval(\"(\"+sdkController.getScreenSize(\"window.mraidview\")+\")\")}catch(a){imraidview.showAlert(\"getScreenSize: \"+a)}};a.getMaxSize=\nfunction(){try{return eval(\"(\"+sdkController.getMaxSize(\"window.mraidview\")+\")\")}catch(a){imraidview.showAlert(\"getMaxSize: \"+a)}};a.getCurrentPosition=function(){try{return eval(\"(\"+sdkController.getCurrentPosition(\"window.mraidview\")+\")\")}catch(a){imraidview.showAlert(\"getCurrentPosition: \"+a)}};a.getDefaultPosition=function(){try{return eval(\"(\"+sdkController.getDefaultPosition(\"window.mraidview\")+\")\")}catch(a){imraidview.showAlert(\"getDefaultPosition: \"+a)}};a.getState=function(){try{return String(sdkController.getState(\"window.mraidview\"))}catch(a){imraidview.showAlert(\"getState: \"+\na)}};a.isViewable=function(){try{return sdkController.isViewable(\"window.mraidview\")}catch(a){imraidview.showAlert(\"isViewable: \"+a)}};a.getPlacementType=function(){return sdkController.getPlacementType(\"window.mraidview\")};a.close=function(){try{sdkController.close(\"window.mraidview\")}catch(a){imraidview.showAlert(\"close: \"+a)}};\"function\"!=typeof String.prototype.startsWith&&(String.prototype.startsWith=function(a){return 0==this.indexOf(a)});a.playVideo=function(a){var e=\"\";null!=a&&(e=a);try{sdkController.playVideo(\"window.mraidview\",\ne)}catch(b){imraidview.showAlert(\"playVideo: \"+b)}};a.stringify=function(d){if(\"undefined\"===typeof JSON){var e=\"\",b;if(\"undefined\"==typeof d.length)return a.stringifyArg(d);for(b=0;b<d.length;b++)0<b&&(e+=\",\"),e+=a.stringifyArg(d[b]);return e+\"]\"}return JSON.stringify(d)};a.stringifyArg=function(a){var e,b,c;b=typeof a;e=\"\";if(\"number\"===b||\"boolean\"===b)e+=args;else if(a instanceof Array)e=e+\"[\"+a+\"]\";else if(a instanceof Object){b=!0;e+=\"{\";for(c in a)null!==a[c]&&(b||(e+=\",\"),e=e+'\"'+c+'\":',b=\ntypeof a[c],e=\"number\"===b||\"boolean\"===b?e+a[c]:\"function\"===typeof a[c]?e+'\"\"':a[c]instanceof Object?e+this.stringify(args[i][c]):e+'\"'+a[c]+'\"',b=!1);e+=\"}\"}else a=a.replace(/\\\\/g,\"\\\\\\\\\"),a=a.replace(/\"/g,'\\\\\"'),e=e+'\"'+a+'\"';imraidview.showAlert(\"json:\"+e);return e};getPID=function(a){var e=\"\";null!=a&&(\"undefined\"!=typeof a.id&&null!=a.id)&&(e=a.id);return e};a.resize=function(){if(null==a.resizeProps)window.mraid.broadcastEvent(\"error\",\"Valid resize dimensions must be provided before calling resize\",\n\"resize\");else try{sdkController.resize(\"window.mraidview\")}catch(b){imraidview.showAlert(\"resize called in bridge\")}};a.createCalendarEvent=function(a){var e={};\"object\"!=typeof a&&window.mraid.broadcastEvent(\"error\",\"createCalendarEvent method expects parameter\",\"createCalendarEvent\");if(\"string\"!=typeof a.start||\"string\"!=typeof a.end)window.mraid.broadcastEvent(\"error\",\"createCalendarEvent method expects string parameters for start and end dates\",\"createCalendarEvent\");else{\"string\"!=typeof a.id&&\n(a.id=\"\");\"string\"!=typeof a.location&&(a.location=\"\");\"string\"!=typeof a.description&&(a.description=\"\");\"string\"!=typeof a.summary&&(a.summary=\"\");\"string\"==typeof a.status&&(\"pending\"==a.status||\"tentative\"==a.status||\"confirmed\"==a.status||\"cancelled\"==a.status)||(a.status=\"\");\"string\"==typeof a.transparency&&(\"opaque\"==a.transparency||\"transparent\"==a.transparency)||(a.transparency=\"\");if(null==a.recurrence||\"\"==a.recurrence)e={};else{\"string\"==typeof a.summary&&(e.frequency=a.recurrence.frequency);\nnull!=a.recurrence.interval&&(e.interval=a.recurrence.interval);\"string\"==typeof a.summary&&(e.expires=a.recurrence.expires);null!=a.recurrence.exceptionDates&&(e.exceptionDates=a.recurrence.exceptionDates);if(null!=a.recurrence.daysInWeek){var b=formatDaysInWeek(a.recurrence.daysInWeek);null!=b?e.daysInWeek=b:imraidview.showAlert(\"daysInWeek invalid format \")}e.daysInMonth=a.recurrence.daysInMonth;e.daysInYear=a.recurrence.daysInYear;e.weeksInMonth=a.recurrence.weeksInMonth;e.monthsInYear=a.recurrence.monthsInYear}\"string\"!=\ntypeof a.reminder&&(a.reminder=\"\");try{sdkController.createCalendarEvent(\"window.mraidview\",a.id,a.start,a.end,a.location,a.description,a.summary,a.status,a.transparency,JSON.stringify(e),a.reminder)}catch(c){sdkController.createCalendarEvent(\"window.mraidview\",a.start,a.end,a.location,a.description)}}};formatDaysInWeek=function(a){try{if(0!=a.length){for(var e=0;e<a.length;e++)switch(a[e]){case 0:a[e]=\"SU\";break;case 1:a[e]=\"MO\";break;case 2:a[e]=\"TU\";break;case 3:a[e]=\"WE\";break;case 4:a[e]=\"TH\";\nbreak;case 5:a[e]=\"FR\";break;case 6:a[e]=\"SA\";break;default:return null}return a}}catch(b){}return null};a.storePicture=function(b){console.log(\"bridge: storePicture\");if(\"string\"!=typeof b)window.mraid.broadcastEvent(\"error\",\"storePicture method expects url as string parameter\",\"storePicture\");else{if(a.supports(\"storePicture\"))return!window.confirm(\"Do you want to download the file?\")?(window.mraid.broadcastEvent(\"error\",\"Store picture on \"+b+\" was cancelled by user.\",\"storePicture\"),!1):sdkController.storePicture(\"window.mraidview\",\nb);window.mraid.broadcastEvent(\"error\",\"Store picture on \"+b+\" was cancelled because it is unsupported in this device/app.\",\"storePicture\")}};a.fireMediaTrackingEvent=function(a,e){};a.fireMediaErrorEvent=function(a,e){};a.fireMediaTimeUpdateEvent=function(a,e,b){};a.fireMediaCloseEvent=function(a,e,b){};a.fireMediaVolumeChangeEvent=function(a,e,b){};a.broadcastEvent=function(){window.mraid.broadcastEvent.apply(window.mraid,arguments)}})();\n(function(){var a=window.mraid=new InmobiObj,b=window.mraidview,c=!1;b.isAdShownToUser=!1;b.onUserInteraction=function(){c=!0};b.isPossibleFraud=function(){return a.supports(\"redirectFraudDetection\")&&(!b.isAdShownToUser||!c)};b.fireRedirectFraudBeacon=function(a){if(\"undefined\"!=typeof inmobi&&inmobi.recordEvent){var e={};e.trigger=a;e.isAdShown=b.isAdShownToUser.toString();inmobi.recordEvent(135,e)}};window.onbeforeunload=function(){b.detectAndBlockFraud(\"redirect\")};a.addEventListener(\"viewableChange\",\nfunction(a){a&&!b.isAdShownToUser&&(b.isAdShownToUser=!0)});a.useCustomClose=b.useCustomClose;a.close=b.close;a.getExpandProperties=b.getExpandProperties;a.setExpandProperties=function(c){\"undefined\"!=typeof c&&(\"useCustomClose\"in c&&\"undefined\"!=typeof a.getState()&&\"expanded\"!=a.getState())&&a.useCustomClose(c.useCustomClose);b.setExpandProperties(c)};a.getResizeProperties=b.getResizeProperties;a.setResizeProperties=b.setResizeProperties;a.getOrientationProperties=b.getOrientationProperties;a.setOrientationProperties=\nb.setOrientationProperties;a.expand=b.expand;a.getMaxSize=b.getMaxSize;a.getState=b.getState;a.isViewable=b.isViewable;a.createCalendarEvent=function(a){b.detectAndBlockFraud(\"mraid.createCalendarEvent\")||b.createCalendarEvent(a)};a.open=function(c){b.detectAndBlockFraud(\"mraid.open\")||(\"string\"!=typeof c?a.broadcastEvent(\"error\",\"URL is required.\",\"open\"):b.open(c))};a.resize=b.resize;a.getVersion=function(){return\"2.0\"};a.getPlacementType=b.getPlacementType;a.playVideo=function(a){b.playVideo(a)};\na.getScreenSize=b.getScreenSize;a.getCurrentPosition=b.getCurrentPosition;a.getDefaultPosition=b.getDefaultPosition;a.supports=function(a){return b.supports(a)};a.storePicture=function(c){\"string\"!=typeof c?a.broadcastEvent(\"error\",\"Request must specify a valid URL\",\"storePicture\"):b.storePicture(c)}})();\n(function(){var a=window.imraidview={},b,c=!0;a.setOrientationProperties=function(e){try{e?(\"undefined\"!=typeof e.allowOrientationChange&&(mraidview.orientationProperties.allowOrientationChange=e.allowOrientationChange),\"undefined\"!=typeof e.forceOrientation&&(mraidview.orientationProperties.forceOrientation=e.forceOrientation),\"undefined\"!=typeof e.direction&&(mraidview.orientationProperties.direction=e.direction)):e=null,sdkController.setOrientationProperties(\"window.imraidview\",mraidview.stringify(mraidview.orientationProperties))}catch(b){a.showAlert(\"setOrientationProperties: \"+\nb+\", props = \"+e)}};a.getOrientationProperties=function(){return mraidview.orientationProperties};a.getWindowOrientation=function(){var a=window.orientation;0>a&&(a+=360);window.innerWidth!==this.previousWidth&&0==a&&window.innerWidth>window.innerHeight&&(a=90);return a};var d=function(){window.setTimeout(function(){if(c||a.getWindowOrientation()!==b)c=!1,b=a.getWindowOrientation(),sdkController.onOrientationChange(\"window.imraidview\"),imraid.broadcastEvent(\"orientationChange\",b)},200)};a.registerOrientationListener=\nfunction(){b=a.getWindowOrientation();window.addEventListener(\"resize\",d,!1);window.addEventListener(\"orientationchange\",d,!1)};a.unRegisterOrientationListener=function(){window.removeEventListener(\"resize\",d,!1);window.removeEventListener(\"orientationchange\",d,!1)};window.imraidview.registerOrientationListener();a.firePostStatusEvent=function(a){window.imraid.broadcastEvent(\"postStatus\",a)};a.fireMediaTrackingEvent=function(a,b){var c={};c.name=a;var d=\"inmobi_media_\"+a;\"undefined\"!=typeof b&&(null!=\nb&&\"\"!=b)&&(d=d+\"_\"+b);window.imraid.broadcastEvent(d,c)};a.fireMediaErrorEvent=function(a,b){var c={name:\"error\"};c.code=b;var d=\"inmobi_media_\"+c.name;\"undefined\"!=typeof a&&(null!=a&&\"\"!=a)&&(d=d+\"_\"+a);window.imraid.broadcastEvent(d,c)};a.fireMediaTimeUpdateEvent=function(a,b,c){var d={name:\"timeupdate\",target:{}};d.target.currentTime=b;d.target.duration=c;b=\"inmobi_media_\"+d.name;\"undefined\"!=typeof a&&(null!=a&&\"\"!=a)&&(b=b+\"_\"+a);window.imraid.broadcastEvent(b,d)};a.saveContent=function(a,\nb,c){window.imraid.addEventListener(\"saveContent_\"+a,c);sdkController.saveContent(\"window.imraidview\",a,b)};a.cancelSaveContent=function(a){sdkController.cancelSaveContent(\"window.imraidview\",a)};a.disableCloseRegion=function(a){sdkController.disableCloseRegion(\"window.imraidview\",a)};a.fireGalleryImageSelectedEvent=function(a,b,c){var d=new Image;d.src=\"data:image/jpeg;base64,\"+a;d.width=b;d.height=c;window.imraid.broadcastEvent(\"galleryImageSelected\",d)};a.fireCameraPictureCatpturedEvent=function(a,\nb,c){var d=new Image;d.src=\"data:image/jpeg;base64,\"+a;d.width=b;d.height=c;window.imraid.broadcastEvent(\"cameraPictureCaptured\",d)};a.fireMediaCloseEvent=function(a,b,c){var d={name:\"close\"};d.viaUserInteraction=b;d.target={};d.target.currentTime=c;b=\"inmobi_media_\"+d.name;\"undefined\"!=typeof a&&(null!=a&&\"\"!=a)&&(b=b+\"_\"+a);window.imraid.broadcastEvent(b,d)};a.fireMediaVolumeChangeEvent=function(a,b,c){var d={name:\"volumechange\",target:{}};d.target.volume=b;d.target.muted=c;b=\"inmobi_media_\"+d.name;\n\"undefined\"!=typeof a&&(null!=a&&\"\"!=a)&&(b=b+\"_\"+a);window.imraid.broadcastEvent(b,d)};a.fireDeviceMuteChangeEvent=function(a){window.imraid.broadcastEvent(\"deviceMuted\",a)};a.fireDeviceVolumeChangeEvent=function(a){window.imraid.broadcastEvent(\"deviceVolumeChange\",a)};a.fireHeadphonePluggedEvent=function(a){window.imraid.broadcastEvent(\"headphones\",a)};a.showAlert=function(a){sdkController.showAlert(\"window.imraidview\",a)};a.openExternal=function(b,c){try{600<=getSdkVersionInt()?sdkController.openExternal(\"window.imraidview\",\nb,c):sdkController.openExternal(\"window.imraidview\",b)}catch(d){a.showAlert(\"openExternal: \"+d)}};a.log=function(b){try{sdkController.log(\"window.imraidview\",b)}catch(c){a.showAlert(\"log: \"+c)}};a.getPlatform=function(){return\"android\"};a.asyncPing=function(b){try{sdkController.asyncPing(\"window.imraidview\",b)}catch(c){a.showAlert(\"asyncPing: \"+c)}};a.startListeningDeviceMuteEvents=function(){sdkController.registerDeviceMuteEventListener(\"window.imraidview\")};a.stopListeningDeviceMuteEvents=function(){sdkController.unregisterDeviceMuteEventListener(\"window.imraidview\")};\na.startListeningDeviceVolumeChange=function(){sdkController.registerDeviceVolumeChangeEventListener(\"window.imraidview\")};a.stopListeningDeviceVolumeChange=function(){sdkController.unregisterDeviceVolumeChangeEventListener(\"window.imraidview\")};a.startListeningHeadphonePluggedEvents=function(){sdkController.registerHeadphonePluggedEventListener(\"window.imraidview\")};a.stopListeningHeadphonePluggedEvents=function(){sdkController.unregisterHeadphonePluggedEventListener(\"window.imraidview\")};getSdkVersionInt=\nfunction(){for(var b=a.getSdkVersion().split(\".\"),c=b.length,d=\"\",f=0;f<c;f++)d+=b[f];return parseInt(d)};a.getSdkVersion=function(){return window._im_imaiview.getSdkVersion()};a.supports=function(a){console.log(\"bridge: supports (IMRAID)\");if(\"string\"!=typeof a)window.imraid.broadcastEvent(\"error\",\"Supports method expects string parameter\",\"supports\");else return\"false\"!=sdkController.supports(\"window.imraidview\",a)};a.postToSocial=function(a,b,c,d){a=parseInt(a);isNaN(a)?window.imraid.broadcastEvent(\"error\",\n\"socialType must be an integer\",\"postToSocial\"):(\"string\"!=typeof b&&(b=\"\"),\"string\"!=typeof c&&(c=\"\"),\"string\"!=typeof d&&(d=\"\"),sdkController.postToSocial(\"window.imraidview\",a,b,c,d))};a.incentCompleted=function(a){if(\"object\"!=typeof a||null==a)sdkController.incentCompleted(\"window.imraidview\",null);else try{sdkController.incentCompleted(\"window.imraidview\",JSON.stringify(a))}catch(b){sdkController.incentCompleted(\"window.imraidview\",null)}};a.getOrientation=function(){try{return String(sdkController.getOrientation(\"window.imraidview\"))}catch(b){a.showAlert(\"getOrientation: \"+\nb)}};a.acceptAction=function(b){try{sdkController.acceptAction(\"window.imraidview\",mraidview.stringify(b))}catch(c){a.showAlert(\"acceptAction: \"+c+\", params = \"+b)}};a.rejectAction=function(b){try{sdkController.rejectAction(\"window.imraidview\",mraidview.stringify(b))}catch(c){a.showAlert(\"rejectAction: \"+c+\", params = \"+b)}};a.updateToPassbook=function(b){window.imraid.broadcastEvent(\"error\",\"Method not supported\",\"updateToPassbook\");a.log(\"Method not supported\")};a.isDeviceMuted=function(){return\"false\"!=\nsdkController.isDeviceMuted(\"window.imraidview\")};a.getDeviceVolume=function(){return 603>=getSdkVersionInt()?-1:sdkController.getDeviceVolume(\"window.imraidview\")};a.isHeadPhonesPlugged=function(){return\"false\"!=sdkController.isHeadphonePlugged(\"window.imraidview\")};a.sendSaveContentResult=function(){window.imraid.sendSaveContentResult.apply(window.imraid,arguments)};a.broadcastEvent=function(){window.imraid.broadcastEvent.apply(window.imraid,arguments)};a.disableBackButton=function(a){void 0==a||\n\"boolean\"!=typeof a?console.log(\"disableBackButton called with invalid params\"):sdkController.disableBackButton(\"window.imraidview\",a)};a.isBackButtonDisabled=function(){return sdkController.isBackButtonDisabled(\"window.imraidview\")};a.startListeningForBackButtonPressedEvent=function(){sdkController.registerBackButtonPressedEventListener(\"window.imraidview\")};a.stopListeningForBackButtonPressedEvent=function(){sdkController.unregisterBackButtonPressedEventListener(\"window.imraidview\")}})();\n(function(){var a=window.imraid=new InmobiObj,b=window.imraidview;a.getOrientation=b.getOrientation;a.setOrientationProperties=b.setOrientationProperties;a.getOrientationProperties=b.getOrientationProperties;a.saveContentIDMap={};a.saveContent=function(c,d,e){var k=arguments.length,h,f=null;if(3>k){if(\"function\"===typeof arguments[k-1])h=arguments[k-1];else return;f={reason:1}}else a.saveContentIDMap[c]&&(h=arguments[2],f={reason:11,url:arguments[1]});\"function\"!==!h&&(f?(window.imraid.addEventListener(\"saveContent_failed_\"+\nc,h),window.imraid.sendSaveContentResult(\"saveContent_failed_\"+c,\"failed\",JSON.stringify(f))):(a.removeEventListener(\"saveContent_\"+c),a.saveContentIDMap[c]=!0,b.saveContent(c,d,e)))};a.cancelSaveContent=function(a){b.cancelSaveContent(a)};a.asyncPing=function(c){\"string\"!=typeof c?a.broadcastEvent(\"error\",\"URL is required.\",\"asyncPing\"):b.asyncPing(c)};a.disableCloseRegion=b.disableCloseRegion;a.getSdkVersion=b.getSdkVersion;a.log=function(c){\"undefined\"==typeof c?a.broadcastEvent(\"error\",\"message is required.\",\n\"log\"):\"string\"==typeof c?b.log(c):b.log(JSON.stringify(c))};a.getInMobiAIVersion=function(){return\"2.0\"};a.getVendorName=function(){return\"inmobi\"};a.openExternal=function(a,d){mraidview.detectAndBlockFraud(\"imraid.openExternal\")||b.openExternal(a,d)};a.updateToPassbook=function(c){mraidview.detectAndBlockFraud(\"imraid.updateToPassbook\")||(\"string\"!=typeof c?a.broadcastEvent(\"error\",\"Request must specify a valid URL\",\"updateToPassbook\"):b.updateToPassbook(c))};a.postToSocial=function(a,d,e,k){mraidview.detectAndBlockFraud(\"imraid.postToSocial\")||\nb.postToSocial(a,d,e,k)};a.getPlatform=b.getPlatform;a.incentCompleted=b.incentCompleted;a.loadSKStore=b.loadSKStore;a.showSKStore=function(a){mraidview.detectAndBlockFraud(\"imraid.showSKStore\")||b.showSKStore(a)};a.supports=function(a){return b.supports(a)};a.isDeviceMuted=function(){return!imIsObjValid(a.listeners.deviceMuted)?-1:b.isDeviceMuted()};a.isHeadPhonesPlugged=function(){return!imIsObjValid(a.listeners.headphones)?!1:b.isHeadPhonesPlugged()};a.getDeviceVolume=function(){return b.getDeviceVolume()};\na.setDeviceVolume=function(a){b.setDeviceVolume(a)};a.hideStatusBar=function(){b.hideStatusBar()};a.setOpaqueBackground=function(){b.setOpaqueBackground()};a.disableBackButton=b.disableBackButton;a.isBackButtonDisabled=b.isBackButtonDisabled})();\n(function(){var a=window._im_imaiview={ios:{}};window.imaiview=a;a.broadcastEvent=function(){for(var a=Array(arguments.length),c=0;c<arguments.length;c++)a[c]=arguments[c];c=a.shift();try{window.mraid.broadcastEvent(c,a)}catch(d){}};a.getPlatform=function(){return\"android\"};a.getPlatformVersion=function(){return sdkController.getPlatformVersion(\"window.imaiview\")};a.log=function(a){sdkController.log(\"window.imaiview\",a)};a.openEmbedded=function(a){sdkController.openEmbedded(\"window.imaiview\",a)};\na.openExternal=function(a,c){600<=getSdkVersionInt()?sdkController.openExternal(\"window.imaiview\",a,c):sdkController.openExternal(\"window.imaiview\",a)};a.ping=function(a,c){sdkController.ping(\"window.imaiview\",a,c)};a.pingInWebView=function(a,c){sdkController.pingInWebView(\"window.imaiview\",a,c)};a.getSdkVersion=function(){try{var a=sdkController.getSdkVersion(\"window.imaiview\");if(\"string\"==typeof a&&null!=a)return a}catch(c){return\"3.7.0\"}};a.onUserInteraction=function(a){if(\"object\"!=typeof a||\nnull==a)sdkController.onUserInteraction(\"window.imaiview\",null);else try{sdkController.onUserInteraction(\"window.imaiview\",JSON.stringify(a))}catch(c){sdkController.onUserInteraction(\"window.imaiview\",null)}};a.fireAdReady=function(){sdkController.fireAdReady(\"window.imaiview\")};a.fireAdFailed=function(){sdkController.fireAdFailed(\"window.imaiview\")};a.broadcastEvent=function(){window.imai.broadcastEvent.apply(window.imai,arguments)}})();\n(function(){var a=window._im_imaiview;window._im_imai=new InmobiObj;window._im_imai.ios=new InmobiObj;var b=window._im_imai;window.imai=window._im_imai;b.matchString=function(a,b){if(\"string\"!=typeof a||null==a||null==b)return-1;var e=-1;try{e=a.indexOf(b)}catch(k){}return e};b.isHttpUrl=function(a){return\"string\"!=typeof a||null==a?!1:0==b.matchString(a,\"http://\")?!0:0==b.matchString(a,\"https://\")?!0:!1};b.appendTapParams=function(a,d,e){if(!imIsObjValid(d)||!imIsObjValid(e))return a;b.isHttpUrl(a)&&\n(a=-1==b.matchString(a,\"?\")?a+(\"?u-tap-o=\"+d+\",\"+e):a+(\"&u-tap-o=\"+d+\",\"+e));return a};b.performAdClick=function(a,d){d=d||event;if(imIsObjValid(a)){var e=a.clickConfig,k=a.landingConfig;if(!imIsObjValid(e)&&!imIsObjValid(k))b.log(\"click/landing config are invalid, Nothing to process .\"),this.broadcastEvent(\"error\",\"click/landing config are invalid, Nothing to process .\");else{var h=null,f=null,g=null,m=null,n=null,l=null,q=null,p=null;if(imIsObjValid(d))try{m=d.changedTouches[0].pageX,n=d.changedTouches[0].pageY}catch(r){n=\nm=0}imIsObjValid(k)?imIsObjValid(e)?(l=k.url,q=k.fallbackUrl,p=k.urlType,h=e.url,f=e.pingWV,g=e.fr):(l=k.url,p=k.urlType):(l=e.url,p=e.urlType);e=b.getPlatform();try{if(\"boolean\"!=typeof g&&\"number\"!=typeof g||null==g)g=!0;if(0>g||1<g)g=!0;if(\"boolean\"!=typeof f&&\"number\"!=typeof f||null==f)f=!0;if(0>f||1<f)f=!0;if(\"number\"!=typeof p||null==p)p=0;h=b.appendTapParams(h,m,n);imIsObjValid(h)?!0==f?b.pingInWebView(h,g):b.ping(h,g):b.log(\"clickurl provided is null.\");if(imIsObjValid(l))switch(imIsObjValid(h)||\n(l=b.appendTapParams(l,m,n)),p){case 1:b.openEmbedded(l);break;case 2:\"ios\"==e?b.ios.openItunesProductView(l):this.broadcastEvent(\"error\",\"Cannot process openItunesProductView for os\"+e);break;default:b.openExternal(l,q)}else b.log(\"Landing url provided is null.\")}catch(s){}}}else b.log(\" invalid config, nothing to process .\"),this.broadcastEvent(\"error\",\"invalid config, nothing to process .\")};b.performActionClick=function(a,d){d=d||event;if(imIsObjValid(a)){var e=a.clickConfig,k=a.landingConfig;\nif(!imIsObjValid(e)&&!imIsObjValid(k))b.log(\"click/landing config are invalid, Nothing to process .\"),this.broadcastEvent(\"error\",\"click/landing config are invalid, Nothing to process .\");else{var h=null,f=null,g=null,m=null,n=null;if(imIsObjValid(d))try{m=d.changedTouches[0].pageX,n=d.changedTouches[0].pageY}catch(l){n=m=0}imIsObjValid(e)&&(h=e.url,f=e.pingWV,g=e.fr);try{if(\"boolean\"!=typeof g&&\"number\"!=typeof g||null==g)g=!0;if(0>g||1<g)g=!0;if(\"boolean\"!=typeof f&&\"number\"!=typeof f||null==f)f=\n!0;if(0>f||1<f)f=!0;h=b.appendTapParams(h,m,n);imIsObjValid(h)?!0==f?b.pingInWebView(h,g):b.ping(h,g):b.log(\"clickurl provided is null.\");b.onUserInteraction(k)}catch(q){}}}else b.log(\" invalid config, nothing to process .\"),this.broadcastEvent(\"error\",\"invalid config, nothing to process .\")};b.getVersion=function(){return\"1.0\"};b.getPlatform=a.getPlatform;b.getPlatformVersion=a.getPlatformVersion;b.log=a.log;b.openEmbedded=function(b){mraidview.detectAndBlockFraud(\"imai.openEmbedded\")||a.openEmbedded(b)};\nb.openExternal=function(b,d){mraidview.detectAndBlockFraud(\"imai.openExternal\")||a.openExternal(b,d)};b.ping=a.ping;b.pingInWebView=a.pingInWebView;b.onUserInteraction=a.onUserInteraction;b.getSdkVersion=a.getSdkVersion;b.loadSKStore=a.loadSKStore;b.showSKStore=function(b){mraidview.detectAndBlockFraud(\"imai.showSKStore\")||a.showSKStore(b)};b.ios.openItunesProductView=function(b){mraidview.detectAndBlockFraud(\"imai.ios.openItunesProductView\")||a.ios.openItunesProductView(b)};b.fireAdReady=a.fireAdReady;\nb.fireAdFailed=a.fireAdFailed})();";
        str = new C3227f().m10775b();
        if (str == null) {
            str = "var imIsObjValid=function(a){return\"undefined\"!=typeof a&&null!=a?!0:!1},EventListeners=function(a){this.event=a;this.count=0;var b={};this.add=function(a){var d=String(a);b[d]||(b[d]=a,this.count++)};this.remove=function(a){a=String(a);return b[a]?(b[a]=null,delete b[a],this.count--,!0):!1};this.removeAll=function(){for(var a in b)this.remove(b[a])};this.broadcast=function(a){for(var d in b)b[d].apply({},a)};this.toString=function(){var c=[a,\":\"],d;for(d in b)c.push(\"|\",d,\"|\");return c.join(\"\")}},\nInmobiObj=function(){this.listeners=[];this.addEventListener=function(a,b){try{if(imIsObjValid(b)&&imIsObjValid(a)){var c=this.listeners;c[a]||(c[a]=new EventListeners);c[a].add(b);\"micIntensityChange\"==a&&window.imraidview.startListeningMicIntensity();\"deviceMuted\"==a&&window.imraidview.startListeningDeviceMuteEvents();\"deviceVolumeChange\"==a&&window.imraidview.startListeningDeviceVolumeChange();\"volumeChange\"==a&&window.imraidview.startListeningVolumeChange();\"headphones\"==a&&window.imraidview.startListeningHeadphonePluggedEvents();\n\"backButtonPressed\"==a&&window.imraidview.startListeningForBackButtonPressedEvent()}}catch(d){this.log(d)}};this.removeEventListener=function(a,b){if(imIsObjValid(a)){var c=this.listeners;imIsObjValid(c[a])&&(imIsObjValid(b)?c[a].remove(b):c[a].removeAll());\"micIntensityChange\"==a&&0==c[a].count&&window.imraidview.stopListeningMicIntensity();\"deviceMuted\"==a&&0==c[a].count&&window.imraidview.stopListeningDeviceMuteEvents();\"deviceVolumeChange\"==a&&0==c[a].count&&window.imraidview.stopListeningDeviceVolumeChange();\n\"volumeChange\"==a&&0==c[a].count&&window.imraidview.stopListeningVolumeChange();\"headphones\"==a&&0==c[a].count&&window.imraidview.stopListeningHeadphonePluggedEvents();\"backButtonPressed\"==a&&0==c[a].count&&window.imraidview.stopListeningForBackButtonPressedEvent()}};this.broadcastEvent=function(a){if(imIsObjValid(a)){for(var b=Array(arguments.length),c=0;c<arguments.length;c++)b[c]=arguments[c];c=b.shift();try{this.listeners[c]&&this.listeners[c].broadcast(b)}catch(d){}}};this.sendSaveContentResult=\nfunction(a){if(imIsObjValid(a)){for(var b=Array(arguments.length),c=0;c<arguments.length;c++)if(2==c){var d=arguments[c],d=JSON.parse(d);b[c]=d}else b[c]=arguments[c];d=b[1];\"success\"!=d&&(c=b[0].substring(b[0].indexOf(\"_\")+1),imraid.saveContentIDMap[c]&&delete imraid.saveContentIDMap[c]);window.imraid.broadcastEvent(b[0],b[1],b[2])}}},__im__iosNativeMessageHandler=void 0;\nwindow.webkit&&(window.webkit.messageHandlers&&window.webkit.messageHandlers.nativeMessageHandler)&&(__im__iosNativeMessageHandler=window.webkit.messageHandlers.nativeMessageHandler);\nvar __im__iosNativeCall={nativeCallInFlight:!1,nativeCallQueue:[],executeNativeCall:function(a){this.nativeCallInFlight?this.nativeCallQueue.push(a):(this.nativeCallInFlight=!0,imIsObjValid(__im__iosNativeMessageHandler)?__im__iosNativeMessageHandler.postMessage(a):window.location=a)},nativeCallComplete:function(a){0==this.nativeCallQueue.length?this.nativeCallInFlight=!1:(a=this.nativeCallQueue.shift(),imIsObjValid(__im__iosNativeMessageHandler)?__im__iosNativeMessageHandler.postMessage(a):window.location=\na)}},IOSNativeCall=function(){this.urlScheme=\"\";this.executeNativeCall=function(a){if(imIsObjValid(__im__iosNativeMessageHandler)){d={};d.command=a;d.scheme=this.urlScheme;for(var b={},c=1;c<arguments.length;c+=2)e=arguments[c+1],null!=e&&(b[arguments[c]]=\"\"+e);d.params=b}else for(var d=this.urlScheme+\"://\"+a,e,b=!0,c=1;c<arguments.length;c+=2)e=arguments[c+1],null!=e&&(b?(d+=\"?\",b=!1):d+=\"&\",d+=arguments[c]+\"=\"+escape(e));__im__iosNativeCall.executeNativeCall(d);return\"OK\"};this.nativeCallComplete=\nfunction(a){__im__iosNativeCall.nativeCallComplete(a);return\"OK\"};this.updateKV=function(a,b){this[a]=b;var c=this.broadcastMap[a];c&&this.broadcastEvent(c,b)}};\n(function(){var a=window.mraidview={};a.orientationProperties={allowOrientationChange:!0,forceOrientation:\"none\",direction:\"right\"};var b=[],c=!1;a.detectAndBlockFraud=function(d){a.isPossibleFraud()&&a.fireRedirectFraudBeacon(d);return!1};a.zeroPad=function(a){var e=\"\";10>a&&(e+=\"0\");return e+a};a.supports=function(a){console.log(\"bridge: supports (MRAID)\");if(\"string\"!=typeof a)window.mraid.broadcastEvent(\"error\",\"Supports method expects string parameter\",\"supports\");else return\"false\"!=sdkController.supports(\"window.mraidview\",\na)};a.useCustomClose=function(a){try{sdkController.useCustomClose(\"window.mraidview\",a)}catch(e){imraidview.showAlert(\"use CustomClose: \"+e)}};a.close=function(){try{sdkController.close(\"window.mraidview\")}catch(a){imraidview.showAlert(\"close: \"+a)}};a.stackCommands=function(a,e){c?b.push(a):(eval(a),e&&(c=!0))};a.expand=function(a){try{\"undefined\"==typeof a&&(a=null),sdkController.expand(\"window.mraidview\",a)}catch(e){imraidview.showAlert(\"executeNativeExpand: \"+e+\", URL = \"+a)}};a.setExpandProperties=\nfunction(d){try{d?this.props=d:d=null;if(\"undefined\"!=typeof d.lockOrientation&&null!=d.lockOrientation&&\"undefined\"!=typeof d.orientation&&null!=d.orientation){var e={};e.allowOrientationChange=!d.lockOrientation;e.forceOrientation=d.orientation;a.setOrientationProperties(e)}sdkController.setExpandProperties(\"window.mraidview\",a.stringify(d))}catch(b){imraidview.showAlert(\"executeNativesetExpandProperties: \"+b+\", props = \"+d)}};a.getExpandProperties=function(){try{return eval(\"(\"+sdkController.getExpandProperties(\"window.mraidview\")+\n\")\")}catch(a){imraidview.showAlert(\"getExpandProperties: \"+a)}};a.setOrientationProperties=function(d){try{d?(\"undefined\"!=typeof d.allowOrientationChange&&(a.orientationProperties.allowOrientationChange=d.allowOrientationChange),\"undefined\"!=typeof d.forceOrientation&&(a.orientationProperties.forceOrientation=d.forceOrientation)):d=null,sdkController.setOrientationProperties(\"window.mraidview\",a.stringify(a.orientationProperties))}catch(e){imraidview.showAlert(\"setOrientationProperties: \"+e+\", props = \"+\nd)}};a.getOrientationProperties=function(){return{forceOrientation:a.orientationProperties.forceOrientation,allowOrientationChange:a.orientationProperties.allowOrientationChange}};a.resizeProps=null;a.setResizeProperties=function(d){var e,b;try{e=parseInt(d.width);b=parseInt(d.height);if(isNaN(e)||isNaN(b)||1>e||1>b)throw\"Invalid\";d.width=e;d.height=b;a.resizeProps=d;sdkController.setResizeProperties(\"window.mraidview\",a.stringify(d))}catch(c){window.mraid.broadcastEvent(\"error\",\"Invalid properties.\",\n\"setResizeProperties\")}};a.getResizeProperties=function(){try{return eval(\"(\"+sdkController.getResizeProperties(\"window.mraidview\")+\")\")}catch(a){imraidview.showAlert(\"getResizeProperties: \"+a)}};a.open=function(a){\"undefined\"==typeof a&&(a=null);try{sdkController.open(\"window.mraidview\",a)}catch(e){imraidview.showAlert(\"open: \"+e)}};a.getScreenSize=function(){try{return eval(\"(\"+sdkController.getScreenSize(\"window.mraidview\")+\")\")}catch(a){imraidview.showAlert(\"getScreenSize: \"+a)}};a.getMaxSize=\nfunction(){try{return eval(\"(\"+sdkController.getMaxSize(\"window.mraidview\")+\")\")}catch(a){imraidview.showAlert(\"getMaxSize: \"+a)}};a.getCurrentPosition=function(){try{return eval(\"(\"+sdkController.getCurrentPosition(\"window.mraidview\")+\")\")}catch(a){imraidview.showAlert(\"getCurrentPosition: \"+a)}};a.getDefaultPosition=function(){try{return eval(\"(\"+sdkController.getDefaultPosition(\"window.mraidview\")+\")\")}catch(a){imraidview.showAlert(\"getDefaultPosition: \"+a)}};a.getState=function(){try{return String(sdkController.getState(\"window.mraidview\"))}catch(a){imraidview.showAlert(\"getState: \"+\na)}};a.isViewable=function(){try{return sdkController.isViewable(\"window.mraidview\")}catch(a){imraidview.showAlert(\"isViewable: \"+a)}};a.getPlacementType=function(){return sdkController.getPlacementType(\"window.mraidview\")};a.close=function(){try{sdkController.close(\"window.mraidview\")}catch(a){imraidview.showAlert(\"close: \"+a)}};\"function\"!=typeof String.prototype.startsWith&&(String.prototype.startsWith=function(a){return 0==this.indexOf(a)});a.playVideo=function(a){var e=\"\";null!=a&&(e=a);try{sdkController.playVideo(\"window.mraidview\",\ne)}catch(b){imraidview.showAlert(\"playVideo: \"+b)}};a.stringify=function(d){if(\"undefined\"===typeof JSON){var e=\"\",b;if(\"undefined\"==typeof d.length)return a.stringifyArg(d);for(b=0;b<d.length;b++)0<b&&(e+=\",\"),e+=a.stringifyArg(d[b]);return e+\"]\"}return JSON.stringify(d)};a.stringifyArg=function(a){var e,b,c;b=typeof a;e=\"\";if(\"number\"===b||\"boolean\"===b)e+=args;else if(a instanceof Array)e=e+\"[\"+a+\"]\";else if(a instanceof Object){b=!0;e+=\"{\";for(c in a)null!==a[c]&&(b||(e+=\",\"),e=e+'\"'+c+'\":',b=\ntypeof a[c],e=\"number\"===b||\"boolean\"===b?e+a[c]:\"function\"===typeof a[c]?e+'\"\"':a[c]instanceof Object?e+this.stringify(args[i][c]):e+'\"'+a[c]+'\"',b=!1);e+=\"}\"}else a=a.replace(/\\\\/g,\"\\\\\\\\\"),a=a.replace(/\"/g,'\\\\\"'),e=e+'\"'+a+'\"';imraidview.showAlert(\"json:\"+e);return e};getPID=function(a){var e=\"\";null!=a&&(\"undefined\"!=typeof a.id&&null!=a.id)&&(e=a.id);return e};a.resize=function(){if(null==a.resizeProps)window.mraid.broadcastEvent(\"error\",\"Valid resize dimensions must be provided before calling resize\",\n\"resize\");else try{sdkController.resize(\"window.mraidview\")}catch(b){imraidview.showAlert(\"resize called in bridge\")}};a.createCalendarEvent=function(a){var e={};\"object\"!=typeof a&&window.mraid.broadcastEvent(\"error\",\"createCalendarEvent method expects parameter\",\"createCalendarEvent\");if(\"string\"!=typeof a.start||\"string\"!=typeof a.end)window.mraid.broadcastEvent(\"error\",\"createCalendarEvent method expects string parameters for start and end dates\",\"createCalendarEvent\");else{\"string\"!=typeof a.id&&\n(a.id=\"\");\"string\"!=typeof a.location&&(a.location=\"\");\"string\"!=typeof a.description&&(a.description=\"\");\"string\"!=typeof a.summary&&(a.summary=\"\");\"string\"==typeof a.status&&(\"pending\"==a.status||\"tentative\"==a.status||\"confirmed\"==a.status||\"cancelled\"==a.status)||(a.status=\"\");\"string\"==typeof a.transparency&&(\"opaque\"==a.transparency||\"transparent\"==a.transparency)||(a.transparency=\"\");if(null==a.recurrence||\"\"==a.recurrence)e={};else{\"string\"==typeof a.summary&&(e.frequency=a.recurrence.frequency);\nnull!=a.recurrence.interval&&(e.interval=a.recurrence.interval);\"string\"==typeof a.summary&&(e.expires=a.recurrence.expires);null!=a.recurrence.exceptionDates&&(e.exceptionDates=a.recurrence.exceptionDates);if(null!=a.recurrence.daysInWeek){var b=formatDaysInWeek(a.recurrence.daysInWeek);null!=b?e.daysInWeek=b:imraidview.showAlert(\"daysInWeek invalid format \")}e.daysInMonth=a.recurrence.daysInMonth;e.daysInYear=a.recurrence.daysInYear;e.weeksInMonth=a.recurrence.weeksInMonth;e.monthsInYear=a.recurrence.monthsInYear}\"string\"!=\ntypeof a.reminder&&(a.reminder=\"\");try{sdkController.createCalendarEvent(\"window.mraidview\",a.id,a.start,a.end,a.location,a.description,a.summary,a.status,a.transparency,JSON.stringify(e),a.reminder)}catch(c){sdkController.createCalendarEvent(\"window.mraidview\",a.start,a.end,a.location,a.description)}}};formatDaysInWeek=function(a){try{if(0!=a.length){for(var e=0;e<a.length;e++)switch(a[e]){case 0:a[e]=\"SU\";break;case 1:a[e]=\"MO\";break;case 2:a[e]=\"TU\";break;case 3:a[e]=\"WE\";break;case 4:a[e]=\"TH\";\nbreak;case 5:a[e]=\"FR\";break;case 6:a[e]=\"SA\";break;default:return null}return a}}catch(b){}return null};a.storePicture=function(b){console.log(\"bridge: storePicture\");if(\"string\"!=typeof b)window.mraid.broadcastEvent(\"error\",\"storePicture method expects url as string parameter\",\"storePicture\");else{if(a.supports(\"storePicture\"))return!window.confirm(\"Do you want to download the file?\")?(window.mraid.broadcastEvent(\"error\",\"Store picture on \"+b+\" was cancelled by user.\",\"storePicture\"),!1):sdkController.storePicture(\"window.mraidview\",\nb);window.mraid.broadcastEvent(\"error\",\"Store picture on \"+b+\" was cancelled because it is unsupported in this device/app.\",\"storePicture\")}};a.fireMediaTrackingEvent=function(a,e){};a.fireMediaErrorEvent=function(a,e){};a.fireMediaTimeUpdateEvent=function(a,e,b){};a.fireMediaCloseEvent=function(a,e,b){};a.fireMediaVolumeChangeEvent=function(a,e,b){};a.broadcastEvent=function(){window.mraid.broadcastEvent.apply(window.mraid,arguments)}})();\n(function(){var a=window.mraid=new InmobiObj,b=window.mraidview,c=!1;b.isAdShownToUser=!1;b.onUserInteraction=function(){c=!0};b.isPossibleFraud=function(){return a.supports(\"redirectFraudDetection\")&&(!b.isAdShownToUser||!c)};b.fireRedirectFraudBeacon=function(a){if(\"undefined\"!=typeof inmobi&&inmobi.recordEvent){var e={};e.trigger=a;e.isAdShown=b.isAdShownToUser.toString();inmobi.recordEvent(135,e)}};window.onbeforeunload=function(){b.detectAndBlockFraud(\"redirect\")};a.addEventListener(\"viewableChange\",\nfunction(a){a&&!b.isAdShownToUser&&(b.isAdShownToUser=!0)});a.useCustomClose=b.useCustomClose;a.close=b.close;a.getExpandProperties=b.getExpandProperties;a.setExpandProperties=function(c){\"undefined\"!=typeof c&&(\"useCustomClose\"in c&&\"undefined\"!=typeof a.getState()&&\"expanded\"!=a.getState())&&a.useCustomClose(c.useCustomClose);b.setExpandProperties(c)};a.getResizeProperties=b.getResizeProperties;a.setResizeProperties=b.setResizeProperties;a.getOrientationProperties=b.getOrientationProperties;a.setOrientationProperties=\nb.setOrientationProperties;a.expand=b.expand;a.getMaxSize=b.getMaxSize;a.getState=b.getState;a.isViewable=b.isViewable;a.createCalendarEvent=function(a){b.detectAndBlockFraud(\"mraid.createCalendarEvent\")||b.createCalendarEvent(a)};a.open=function(c){b.detectAndBlockFraud(\"mraid.open\")||(\"string\"!=typeof c?a.broadcastEvent(\"error\",\"URL is required.\",\"open\"):b.open(c))};a.resize=b.resize;a.getVersion=function(){return\"2.0\"};a.getPlacementType=b.getPlacementType;a.playVideo=function(a){b.playVideo(a)};\na.getScreenSize=b.getScreenSize;a.getCurrentPosition=b.getCurrentPosition;a.getDefaultPosition=b.getDefaultPosition;a.supports=function(a){return b.supports(a)};a.storePicture=function(c){\"string\"!=typeof c?a.broadcastEvent(\"error\",\"Request must specify a valid URL\",\"storePicture\"):b.storePicture(c)}})();\n(function(){var a=window.imraidview={},b,c=!0;a.setOrientationProperties=function(e){try{e?(\"undefined\"!=typeof e.allowOrientationChange&&(mraidview.orientationProperties.allowOrientationChange=e.allowOrientationChange),\"undefined\"!=typeof e.forceOrientation&&(mraidview.orientationProperties.forceOrientation=e.forceOrientation),\"undefined\"!=typeof e.direction&&(mraidview.orientationProperties.direction=e.direction)):e=null,sdkController.setOrientationProperties(\"window.imraidview\",mraidview.stringify(mraidview.orientationProperties))}catch(b){a.showAlert(\"setOrientationProperties: \"+\nb+\", props = \"+e)}};a.getOrientationProperties=function(){return mraidview.orientationProperties};a.getWindowOrientation=function(){var a=window.orientation;0>a&&(a+=360);window.innerWidth!==this.previousWidth&&0==a&&window.innerWidth>window.innerHeight&&(a=90);return a};var d=function(){window.setTimeout(function(){if(c||a.getWindowOrientation()!==b)c=!1,b=a.getWindowOrientation(),sdkController.onOrientationChange(\"window.imraidview\"),imraid.broadcastEvent(\"orientationChange\",b)},200)};a.registerOrientationListener=\nfunction(){b=a.getWindowOrientation();window.addEventListener(\"resize\",d,!1);window.addEventListener(\"orientationchange\",d,!1)};a.unRegisterOrientationListener=function(){window.removeEventListener(\"resize\",d,!1);window.removeEventListener(\"orientationchange\",d,!1)};window.imraidview.registerOrientationListener();a.firePostStatusEvent=function(a){window.imraid.broadcastEvent(\"postStatus\",a)};a.fireMediaTrackingEvent=function(a,b){var c={};c.name=a;var d=\"inmobi_media_\"+a;\"undefined\"!=typeof b&&(null!=\nb&&\"\"!=b)&&(d=d+\"_\"+b);window.imraid.broadcastEvent(d,c)};a.fireMediaErrorEvent=function(a,b){var c={name:\"error\"};c.code=b;var d=\"inmobi_media_\"+c.name;\"undefined\"!=typeof a&&(null!=a&&\"\"!=a)&&(d=d+\"_\"+a);window.imraid.broadcastEvent(d,c)};a.fireMediaTimeUpdateEvent=function(a,b,c){var d={name:\"timeupdate\",target:{}};d.target.currentTime=b;d.target.duration=c;b=\"inmobi_media_\"+d.name;\"undefined\"!=typeof a&&(null!=a&&\"\"!=a)&&(b=b+\"_\"+a);window.imraid.broadcastEvent(b,d)};a.saveContent=function(a,\nb,c){window.imraid.addEventListener(\"saveContent_\"+a,c);sdkController.saveContent(\"window.imraidview\",a,b)};a.cancelSaveContent=function(a){sdkController.cancelSaveContent(\"window.imraidview\",a)};a.disableCloseRegion=function(a){sdkController.disableCloseRegion(\"window.imraidview\",a)};a.fireGalleryImageSelectedEvent=function(a,b,c){var d=new Image;d.src=\"data:image/jpeg;base64,\"+a;d.width=b;d.height=c;window.imraid.broadcastEvent(\"galleryImageSelected\",d)};a.fireCameraPictureCatpturedEvent=function(a,\nb,c){var d=new Image;d.src=\"data:image/jpeg;base64,\"+a;d.width=b;d.height=c;window.imraid.broadcastEvent(\"cameraPictureCaptured\",d)};a.fireMediaCloseEvent=function(a,b,c){var d={name:\"close\"};d.viaUserInteraction=b;d.target={};d.target.currentTime=c;b=\"inmobi_media_\"+d.name;\"undefined\"!=typeof a&&(null!=a&&\"\"!=a)&&(b=b+\"_\"+a);window.imraid.broadcastEvent(b,d)};a.fireMediaVolumeChangeEvent=function(a,b,c){var d={name:\"volumechange\",target:{}};d.target.volume=b;d.target.muted=c;b=\"inmobi_media_\"+d.name;\n\"undefined\"!=typeof a&&(null!=a&&\"\"!=a)&&(b=b+\"_\"+a);window.imraid.broadcastEvent(b,d)};a.fireDeviceMuteChangeEvent=function(a){window.imraid.broadcastEvent(\"deviceMuted\",a)};a.fireDeviceVolumeChangeEvent=function(a){window.imraid.broadcastEvent(\"deviceVolumeChange\",a)};a.fireHeadphonePluggedEvent=function(a){window.imraid.broadcastEvent(\"headphones\",a)};a.showAlert=function(a){sdkController.showAlert(\"window.imraidview\",a)};a.openExternal=function(b,c){try{600<=getSdkVersionInt()?sdkController.openExternal(\"window.imraidview\",\nb,c):sdkController.openExternal(\"window.imraidview\",b)}catch(d){a.showAlert(\"openExternal: \"+d)}};a.log=function(b){try{sdkController.log(\"window.imraidview\",b)}catch(c){a.showAlert(\"log: \"+c)}};a.getPlatform=function(){return\"android\"};a.asyncPing=function(b){try{sdkController.asyncPing(\"window.imraidview\",b)}catch(c){a.showAlert(\"asyncPing: \"+c)}};a.startListeningDeviceMuteEvents=function(){sdkController.registerDeviceMuteEventListener(\"window.imraidview\")};a.stopListeningDeviceMuteEvents=function(){sdkController.unregisterDeviceMuteEventListener(\"window.imraidview\")};\na.startListeningDeviceVolumeChange=function(){sdkController.registerDeviceVolumeChangeEventListener(\"window.imraidview\")};a.stopListeningDeviceVolumeChange=function(){sdkController.unregisterDeviceVolumeChangeEventListener(\"window.imraidview\")};a.startListeningHeadphonePluggedEvents=function(){sdkController.registerHeadphonePluggedEventListener(\"window.imraidview\")};a.stopListeningHeadphonePluggedEvents=function(){sdkController.unregisterHeadphonePluggedEventListener(\"window.imraidview\")};getSdkVersionInt=\nfunction(){for(var b=a.getSdkVersion().split(\".\"),c=b.length,d=\"\",f=0;f<c;f++)d+=b[f];return parseInt(d)};a.getSdkVersion=function(){return window._im_imaiview.getSdkVersion()};a.supports=function(a){console.log(\"bridge: supports (IMRAID)\");if(\"string\"!=typeof a)window.imraid.broadcastEvent(\"error\",\"Supports method expects string parameter\",\"supports\");else return\"false\"!=sdkController.supports(\"window.imraidview\",a)};a.postToSocial=function(a,b,c,d){a=parseInt(a);isNaN(a)?window.imraid.broadcastEvent(\"error\",\n\"socialType must be an integer\",\"postToSocial\"):(\"string\"!=typeof b&&(b=\"\"),\"string\"!=typeof c&&(c=\"\"),\"string\"!=typeof d&&(d=\"\"),sdkController.postToSocial(\"window.imraidview\",a,b,c,d))};a.incentCompleted=function(a){if(\"object\"!=typeof a||null==a)sdkController.incentCompleted(\"window.imraidview\",null);else try{sdkController.incentCompleted(\"window.imraidview\",JSON.stringify(a))}catch(b){sdkController.incentCompleted(\"window.imraidview\",null)}};a.getOrientation=function(){try{return String(sdkController.getOrientation(\"window.imraidview\"))}catch(b){a.showAlert(\"getOrientation: \"+\nb)}};a.acceptAction=function(b){try{sdkController.acceptAction(\"window.imraidview\",mraidview.stringify(b))}catch(c){a.showAlert(\"acceptAction: \"+c+\", params = \"+b)}};a.rejectAction=function(b){try{sdkController.rejectAction(\"window.imraidview\",mraidview.stringify(b))}catch(c){a.showAlert(\"rejectAction: \"+c+\", params = \"+b)}};a.updateToPassbook=function(b){window.imraid.broadcastEvent(\"error\",\"Method not supported\",\"updateToPassbook\");a.log(\"Method not supported\")};a.isDeviceMuted=function(){return\"false\"!=\nsdkController.isDeviceMuted(\"window.imraidview\")};a.getDeviceVolume=function(){return 603>=getSdkVersionInt()?-1:sdkController.getDeviceVolume(\"window.imraidview\")};a.isHeadPhonesPlugged=function(){return\"false\"!=sdkController.isHeadphonePlugged(\"window.imraidview\")};a.sendSaveContentResult=function(){window.imraid.sendSaveContentResult.apply(window.imraid,arguments)};a.broadcastEvent=function(){window.imraid.broadcastEvent.apply(window.imraid,arguments)};a.disableBackButton=function(a){void 0==a||\n\"boolean\"!=typeof a?console.log(\"disableBackButton called with invalid params\"):sdkController.disableBackButton(\"window.imraidview\",a)};a.isBackButtonDisabled=function(){return sdkController.isBackButtonDisabled(\"window.imraidview\")};a.startListeningForBackButtonPressedEvent=function(){sdkController.registerBackButtonPressedEventListener(\"window.imraidview\")};a.stopListeningForBackButtonPressedEvent=function(){sdkController.unregisterBackButtonPressedEventListener(\"window.imraidview\")}})();\n(function(){var a=window.imraid=new InmobiObj,b=window.imraidview;a.getOrientation=b.getOrientation;a.setOrientationProperties=b.setOrientationProperties;a.getOrientationProperties=b.getOrientationProperties;a.saveContentIDMap={};a.saveContent=function(c,d,e){var k=arguments.length,h,f=null;if(3>k){if(\"function\"===typeof arguments[k-1])h=arguments[k-1];else return;f={reason:1}}else a.saveContentIDMap[c]&&(h=arguments[2],f={reason:11,url:arguments[1]});\"function\"!==!h&&(f?(window.imraid.addEventListener(\"saveContent_failed_\"+\nc,h),window.imraid.sendSaveContentResult(\"saveContent_failed_\"+c,\"failed\",JSON.stringify(f))):(a.removeEventListener(\"saveContent_\"+c),a.saveContentIDMap[c]=!0,b.saveContent(c,d,e)))};a.cancelSaveContent=function(a){b.cancelSaveContent(a)};a.asyncPing=function(c){\"string\"!=typeof c?a.broadcastEvent(\"error\",\"URL is required.\",\"asyncPing\"):b.asyncPing(c)};a.disableCloseRegion=b.disableCloseRegion;a.getSdkVersion=b.getSdkVersion;a.log=function(c){\"undefined\"==typeof c?a.broadcastEvent(\"error\",\"message is required.\",\n\"log\"):\"string\"==typeof c?b.log(c):b.log(JSON.stringify(c))};a.getInMobiAIVersion=function(){return\"2.0\"};a.getVendorName=function(){return\"inmobi\"};a.openExternal=function(a,d){mraidview.detectAndBlockFraud(\"imraid.openExternal\")||b.openExternal(a,d)};a.updateToPassbook=function(c){mraidview.detectAndBlockFraud(\"imraid.updateToPassbook\")||(\"string\"!=typeof c?a.broadcastEvent(\"error\",\"Request must specify a valid URL\",\"updateToPassbook\"):b.updateToPassbook(c))};a.postToSocial=function(a,d,e,k){mraidview.detectAndBlockFraud(\"imraid.postToSocial\")||\nb.postToSocial(a,d,e,k)};a.getPlatform=b.getPlatform;a.incentCompleted=b.incentCompleted;a.loadSKStore=b.loadSKStore;a.showSKStore=function(a){mraidview.detectAndBlockFraud(\"imraid.showSKStore\")||b.showSKStore(a)};a.supports=function(a){return b.supports(a)};a.isDeviceMuted=function(){return!imIsObjValid(a.listeners.deviceMuted)?-1:b.isDeviceMuted()};a.isHeadPhonesPlugged=function(){return!imIsObjValid(a.listeners.headphones)?!1:b.isHeadPhonesPlugged()};a.getDeviceVolume=function(){return b.getDeviceVolume()};\na.setDeviceVolume=function(a){b.setDeviceVolume(a)};a.hideStatusBar=function(){b.hideStatusBar()};a.setOpaqueBackground=function(){b.setOpaqueBackground()};a.disableBackButton=b.disableBackButton;a.isBackButtonDisabled=b.isBackButtonDisabled})();\n(function(){var a=window._im_imaiview={ios:{}};window.imaiview=a;a.broadcastEvent=function(){for(var a=Array(arguments.length),c=0;c<arguments.length;c++)a[c]=arguments[c];c=a.shift();try{window.mraid.broadcastEvent(c,a)}catch(d){}};a.getPlatform=function(){return\"android\"};a.getPlatformVersion=function(){return sdkController.getPlatformVersion(\"window.imaiview\")};a.log=function(a){sdkController.log(\"window.imaiview\",a)};a.openEmbedded=function(a){sdkController.openEmbedded(\"window.imaiview\",a)};\na.openExternal=function(a,c){600<=getSdkVersionInt()?sdkController.openExternal(\"window.imaiview\",a,c):sdkController.openExternal(\"window.imaiview\",a)};a.ping=function(a,c){sdkController.ping(\"window.imaiview\",a,c)};a.pingInWebView=function(a,c){sdkController.pingInWebView(\"window.imaiview\",a,c)};a.getSdkVersion=function(){try{var a=sdkController.getSdkVersion(\"window.imaiview\");if(\"string\"==typeof a&&null!=a)return a}catch(c){return\"3.7.0\"}};a.onUserInteraction=function(a){if(\"object\"!=typeof a||\nnull==a)sdkController.onUserInteraction(\"window.imaiview\",null);else try{sdkController.onUserInteraction(\"window.imaiview\",JSON.stringify(a))}catch(c){sdkController.onUserInteraction(\"window.imaiview\",null)}};a.fireAdReady=function(){sdkController.fireAdReady(\"window.imaiview\")};a.fireAdFailed=function(){sdkController.fireAdFailed(\"window.imaiview\")};a.broadcastEvent=function(){window.imai.broadcastEvent.apply(window.imai,arguments)}})();\n(function(){var a=window._im_imaiview;window._im_imai=new InmobiObj;window._im_imai.ios=new InmobiObj;var b=window._im_imai;window.imai=window._im_imai;b.matchString=function(a,b){if(\"string\"!=typeof a||null==a||null==b)return-1;var e=-1;try{e=a.indexOf(b)}catch(k){}return e};b.isHttpUrl=function(a){return\"string\"!=typeof a||null==a?!1:0==b.matchString(a,\"http://\")?!0:0==b.matchString(a,\"https://\")?!0:!1};b.appendTapParams=function(a,d,e){if(!imIsObjValid(d)||!imIsObjValid(e))return a;b.isHttpUrl(a)&&\n(a=-1==b.matchString(a,\"?\")?a+(\"?u-tap-o=\"+d+\",\"+e):a+(\"&u-tap-o=\"+d+\",\"+e));return a};b.performAdClick=function(a,d){d=d||event;if(imIsObjValid(a)){var e=a.clickConfig,k=a.landingConfig;if(!imIsObjValid(e)&&!imIsObjValid(k))b.log(\"click/landing config are invalid, Nothing to process .\"),this.broadcastEvent(\"error\",\"click/landing config are invalid, Nothing to process .\");else{var h=null,f=null,g=null,m=null,n=null,l=null,q=null,p=null;if(imIsObjValid(d))try{m=d.changedTouches[0].pageX,n=d.changedTouches[0].pageY}catch(r){n=\nm=0}imIsObjValid(k)?imIsObjValid(e)?(l=k.url,q=k.fallbackUrl,p=k.urlType,h=e.url,f=e.pingWV,g=e.fr):(l=k.url,p=k.urlType):(l=e.url,p=e.urlType);e=b.getPlatform();try{if(\"boolean\"!=typeof g&&\"number\"!=typeof g||null==g)g=!0;if(0>g||1<g)g=!0;if(\"boolean\"!=typeof f&&\"number\"!=typeof f||null==f)f=!0;if(0>f||1<f)f=!0;if(\"number\"!=typeof p||null==p)p=0;h=b.appendTapParams(h,m,n);imIsObjValid(h)?!0==f?b.pingInWebView(h,g):b.ping(h,g):b.log(\"clickurl provided is null.\");if(imIsObjValid(l))switch(imIsObjValid(h)||\n(l=b.appendTapParams(l,m,n)),p){case 1:b.openEmbedded(l);break;case 2:\"ios\"==e?b.ios.openItunesProductView(l):this.broadcastEvent(\"error\",\"Cannot process openItunesProductView for os\"+e);break;default:b.openExternal(l,q)}else b.log(\"Landing url provided is null.\")}catch(s){}}}else b.log(\" invalid config, nothing to process .\"),this.broadcastEvent(\"error\",\"invalid config, nothing to process .\")};b.performActionClick=function(a,d){d=d||event;if(imIsObjValid(a)){var e=a.clickConfig,k=a.landingConfig;\nif(!imIsObjValid(e)&&!imIsObjValid(k))b.log(\"click/landing config are invalid, Nothing to process .\"),this.broadcastEvent(\"error\",\"click/landing config are invalid, Nothing to process .\");else{var h=null,f=null,g=null,m=null,n=null;if(imIsObjValid(d))try{m=d.changedTouches[0].pageX,n=d.changedTouches[0].pageY}catch(l){n=m=0}imIsObjValid(e)&&(h=e.url,f=e.pingWV,g=e.fr);try{if(\"boolean\"!=typeof g&&\"number\"!=typeof g||null==g)g=!0;if(0>g||1<g)g=!0;if(\"boolean\"!=typeof f&&\"number\"!=typeof f||null==f)f=\n!0;if(0>f||1<f)f=!0;h=b.appendTapParams(h,m,n);imIsObjValid(h)?!0==f?b.pingInWebView(h,g):b.ping(h,g):b.log(\"clickurl provided is null.\");b.onUserInteraction(k)}catch(q){}}}else b.log(\" invalid config, nothing to process .\"),this.broadcastEvent(\"error\",\"invalid config, nothing to process .\")};b.getVersion=function(){return\"1.0\"};b.getPlatform=a.getPlatform;b.getPlatformVersion=a.getPlatformVersion;b.log=a.log;b.openEmbedded=function(b){mraidview.detectAndBlockFraud(\"imai.openEmbedded\")||a.openEmbedded(b)};\nb.openExternal=function(b,d){mraidview.detectAndBlockFraud(\"imai.openExternal\")||a.openExternal(b,d)};b.ping=a.ping;b.pingInWebView=a.pingInWebView;b.onUserInteraction=a.onUserInteraction;b.getSdkVersion=a.getSdkVersion;b.loadSKStore=a.loadSKStore;b.showSKStore=function(b){mraidview.detectAndBlockFraud(\"imai.showSKStore\")||a.showSKStore(b)};b.ios.openItunesProductView=function(b){mraidview.detectAndBlockFraud(\"imai.ios.openItunesProductView\")||a.ios.openItunesProductView(b)};b.fireAdReady=a.fireAdReady;\nb.fireAdFailed=a.fireAdFailed})();";
            Logger.m10359a(InternalLogLevel.INTERNAL, f7901b, "Returning default Mraid Js string.");
            return str;
        }
        Logger.m10359a(InternalLogLevel.INTERNAL, f7901b, "Returning fetched Mraid Js string.");
        return str;
    }

    public void m10633a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
        this.f7937o.m10804a(str, getRenderViewContext(), str2, str3, str4, str5, str6, str7, str8, str9, str10, str11);
    }

    public void m10629a(String str, int i, String str2, String str3, String str4) {
        if (m10651f("postToSocial")) {
            this.f7937o.m10803a(str, getRenderViewContext(), i, str2, str3, str4);
            return;
        }
        Logger.m10359a(InternalLogLevel.INTERNAL, f7901b, "postToSocial called even when it is not supported");
    }

    private void m10622w() {
        if (RenderViewState.DEFAULT != this.f7932j) {
            this.f7913L = true;
            this.f7934l.m10771a();
            m10621v();
            this.f7913L = false;
        }
    }

    private void m10623x() {
        if (RenderViewState.DEFAULT != this.f7932j) {
            this.f7913L = true;
            this.f7935m.m10791b();
            setAndUpdateViewState(RenderViewState.DEFAULT);
            this.f7931i.mo6114g(this);
            this.f7913L = false;
        }
    }

    public void m10643c(String str, String str2, String str3) {
        Logger.m10359a(InternalLogLevel.INTERNAL, f7901b, "saveContent called: content ID: " + str2 + "; URL: " + str3);
        JSONObject jSONObject;
        if (m10651f("saveContent")) {
            File file = new File(C3105a.m10079b(getRenderViewContext()), String.valueOf(hashCode()));
            if (file.mkdirs() || file.isDirectory()) {
                C3221b c3221b = new C3221b(str, new File(file, UUID.randomUUID().toString()), str3, str2, this);
                this.f7926d.add(c3221b);
                c3221b.execute(new Void[0]);
                return;
            }
            Logger.m10359a(InternalLogLevel.INTERNAL, f7901b, "Cannot create temp directory to save ");
            jSONObject = new JSONObject();
            try {
                jSONObject.put("url", str3);
                jSONObject.put("reason", 9);
            } catch (JSONException e) {
            }
            m10630a(str, "sendSaveContentResult(\"saveContent_" + str2 + "\", 'failed', \"" + jSONObject.toString().replace("\"", "\\\"") + "\");");
            return;
        }
        Logger.m10359a(InternalLogLevel.INTERNAL, f7901b, "saveContent called despite the fact that it is not supported");
        jSONObject = new JSONObject();
        try {
            jSONObject.put("url", str3);
            jSONObject.put("reason", 5);
        } catch (JSONException e2) {
        }
        m10630a(str, "sendSaveContentResult(\"saveContent_" + str2 + "\", 'failed', \"" + jSONObject.toString().replace("\"", "\\\"") + "\");");
    }

    public void m10648e(String str) {
        for (C3221b c3221b : this.f7926d) {
            if (str != null && str.trim().length() != 0 && str.equals(c3221b.m10749a())) {
                c3221b.cancel(true);
                return;
            }
        }
    }

    private void m10624y() {
        for (C3221b cancel : this.f7926d) {
            cancel.cancel(true);
        }
        this.f7926d.clear();
        C3105a.m10074a(C3105a.m10079b(getRenderViewContext()), String.valueOf(hashCode()));
    }

    private void m10608i(String str) {
        loadUrl(str);
    }

    @TargetApi(19)
    private void m10611j(String str) {
        evaluateJavascript(str, null);
    }

    public void mo6173a() {
        Logger.m10359a(InternalLogLevel.INTERNAL, f7901b, "disableHardwareAcceleration called.");
        this.f7914M = false;
        String str = "setLayerType";
        if (VERSION.SDK_INT >= 14) {
            try {
                getClass().getMethod("setLayerType", new Class[]{Integer.TYPE, Paint.class}).invoke(this, new Object[]{Integer.valueOf(1), null});
            } catch (Throwable e) {
                Logger.m10360a(InternalLogLevel.INTERNAL, f7901b, "disableHardwareAcceleration failed.", e);
            } catch (Throwable e2) {
                Logger.m10360a(InternalLogLevel.INTERNAL, f7901b, "disableHardwareAcceleration failed.", e2);
            } catch (Throwable e22) {
                Logger.m10360a(InternalLogLevel.INTERNAL, f7901b, "disableHardwareAcceleration failed.", e22);
            } catch (Throwable e222) {
                Logger.m10360a(InternalLogLevel.INTERNAL, f7901b, "disableHardwareAcceleration failed.", e222);
            }
        }
    }

    public boolean m10660o() {
        return this.f7914M;
    }

    @TargetApi(16)
    public boolean m10651f(String str) {
        boolean z = false;
        boolean z2 = true;
        PackageManager packageManager = getRenderViewContext().getPackageManager();
        boolean z3 = true;
        switch (str.hashCode()) {
            case -1886160473:
                if (str.equals("playVideo")) {
                    z3 = true;
                    break;
                }
                break;
            case -1647691422:
                if (str.equals("inlineVideo")) {
                    z3 = true;
                    break;
                }
                break;
            case -178324674:
                if (str.equals("calendar")) {
                    z3 = true;
                    break;
                }
                break;
            case 459238621:
                if (str.equals("storePicture")) {
                    z3 = true;
                    break;
                }
                break;
            case 1509574865:
                if (str.equals("html5video")) {
                    z3 = true;
                    break;
                }
                break;
            case 1642189884:
                if (str.equals("saveContent")) {
                    z3 = true;
                    break;
                }
                break;
            case 1772979069:
                if (str.equals("redirectFraudDetection")) {
                    z3 = false;
                    break;
                }
                break;
            case 1921345160:
                if (str.equals("postToSocial")) {
                    z3 = true;
                    break;
                }
                break;
        }
        switch (z3) {
            case false:
            case true:
            case true:
                return true;
            case true:
            case true:
                if (VERSION.SDK_INT >= 11 && !(this.f7942t && m10660o())) {
                    z2 = false;
                }
                Logger.m10359a(InternalLogLevel.INTERNAL, f7901b, "HTML5 video supported:" + z2);
                return z2;
            case true:
                if (VERSION.SDK_INT >= 19 || packageManager.checkPermission("android.permission.WRITE_EXTERNAL_STORAGE", packageManager.getNameForUid(Binder.getCallingUid())) == 0) {
                    z = true;
                }
                return z;
            case true:
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setType(MraidNativeCommandHandler.ANDROID_CALENDAR_CONTENT_TYPE);
                ResolveInfo resolveActivity = getRenderViewContext().getPackageManager().resolveActivity(intent, 65536);
                boolean a = C3105a.m10077a("android.permission.WRITE_CALENDAR");
                boolean a2 = C3105a.m10077a("android.permission.READ_CALENDAR");
                if (resolveActivity != null && a && a2) {
                    return true;
                }
                return false;
            case true:
                return C3105a.m10077a("android.permission.WRITE_EXTERNAL_STORAGE");
            default:
                return false;
        }
    }
}
