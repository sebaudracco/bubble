package com.moat.analytics.mobile.vng;

import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.moat.analytics.mobile.vng.C3523v.C3520c;
import com.moat.analytics.mobile.vng.C3523v.C3521d;
import com.moat.analytics.mobile.vng.C3523v.C3522e;
import com.moat.analytics.mobile.vng.C3535x.C3469a;
import com.moat.analytics.mobile.vng.p130a.p131a.C3473a;
import com.moat.analytics.mobile.vng.p130a.p132b.C3474a;
import java.lang.ref.WeakReference;
import java.util.Map;

class C3507n extends MoatFactory {
    C3507n() {
        if (!m11952a()) {
            C3511p.m11976a(3, "Factory", (Object) this, "Failed to initialize MoatFactory. Please check that you've initialized the Moat SDK correctly.");
            C3511p.m11978a("[ERROR] ", "Failed to initialize MoatFactory, SDK was not started");
            throw new C3502m();
        }
    }

    private NativeDisplayTracker m11947a(View view, final Map<String, String> map) {
        C3473a.m11839a(view);
        C3473a.m11839a(map);
        final WeakReference weakReference = new WeakReference(view);
        return (NativeDisplayTracker) C3535x.m12027a(new C3469a<NativeDisplayTracker>(this) {
            final /* synthetic */ C3507n f8957c;

            public C3474a<NativeDisplayTracker> mo6526a() {
                View view = (View) weakReference.get();
                if (view == null) {
                    C3511p.m11976a(3, "Factory", (Object) this, "Target view is null. Not creating NativeDisplayTracker.");
                    C3511p.m11978a("[ERROR] ", "NativeDisplayTracker creation failed, subject view is null");
                    return C3474a.m11840a();
                } else if (map == null || map.isEmpty()) {
                    C3511p.m11976a(3, "Factory", (Object) this, "adIds is null or empty. NativeDisplayTracker initialization failed.");
                    C3511p.m11978a("[ERROR] ", "NativeDisplayTracker creation failed, adIds is null or empty");
                    return C3474a.m11840a();
                } else {
                    C3511p.m11976a(3, "Factory", (Object) this, "Creating NativeDisplayTracker for " + view.getClass().getSimpleName() + "@" + view.hashCode());
                    C3511p.m11978a("[INFO] ", "Attempting to create NativeDisplayTracker for " + view.getClass().getSimpleName() + "@" + view.hashCode());
                    return C3474a.m11841a(new C3516t(view, map));
                }
            }
        }, NativeDisplayTracker.class);
    }

    private NativeVideoTracker m11948a(final String str) {
        return (NativeVideoTracker) C3535x.m12027a(new C3469a<NativeVideoTracker>(this) {
            final /* synthetic */ C3507n f8959b;

            public C3474a<NativeVideoTracker> mo6526a() {
                if (str == null || str.isEmpty()) {
                    C3511p.m11976a(3, "Factory", (Object) this, "partnerCode is null or empty. NativeVideoTracker initialization failed.");
                    C3511p.m11978a("[ERROR] ", "NativeDisplayTracker creation failed, partnerCode is null or empty");
                    return C3474a.m11840a();
                }
                C3511p.m11976a(3, "Factory", (Object) this, "Creating NativeVideo tracker.");
                C3511p.m11978a("[INFO] ", "Attempting to create NativeVideoTracker");
                return C3474a.m11841a(new C3517u(str));
            }
        }, NativeVideoTracker.class);
    }

    private WebAdTracker m11949a(ViewGroup viewGroup) {
        C3473a.m11839a(viewGroup);
        final WeakReference weakReference = new WeakReference(viewGroup);
        return (WebAdTracker) C3535x.m12027a(new C3469a<WebAdTracker>(this) {
            final /* synthetic */ C3507n f8954b;

            public C3474a<WebAdTracker> mo6526a() {
                ViewGroup viewGroup = (ViewGroup) weakReference.get();
                if (viewGroup == null) {
                    C3511p.m11976a(3, "Factory", (Object) this, "Target ViewGroup is null. Not creating WebAdTracker.");
                    C3511p.m11978a("[ERROR] ", "WebAdTracker not created, adContainer ViewGroup is null");
                    return C3474a.m11840a();
                }
                C3511p.m11976a(3, "Factory", (Object) this, "Creating WebAdTracker for " + viewGroup.getClass().getSimpleName() + "@" + viewGroup.hashCode());
                C3511p.m11978a("[INFO] ", "Attempting to create WebAdTracker for " + viewGroup.getClass().getSimpleName() + "@" + viewGroup.hashCode());
                C3474a a = ab.m11857a(viewGroup);
                boolean c = a.m11845c();
                C3511p.m11976a(3, "Factory", (Object) this, "WebView " + (c ? "" : "not ") + "found inside of ad container.");
                if (!c) {
                    C3511p.m11978a("[ERROR] ", "WebAdTracker not created, no WebView to track inside of ad container");
                }
                return C3474a.m11841a(new aa((WebView) a.m11844c(null)));
            }
        }, WebAdTracker.class);
    }

    private WebAdTracker m11950a(WebView webView) {
        C3473a.m11839a(webView);
        final WeakReference weakReference = new WeakReference(webView);
        return (WebAdTracker) C3535x.m12027a(new C3469a<WebAdTracker>(this) {
            final /* synthetic */ C3507n f8952b;

            public C3474a<WebAdTracker> mo6526a() {
                WebView webView = (WebView) weakReference.get();
                if (webView == null) {
                    C3511p.m11976a(3, "Factory", (Object) this, "Target ViewGroup is null. Not creating WebAdTracker.");
                    C3511p.m11978a("[ERROR] ", "WebAdTracker not created, webView is null");
                    return C3474a.m11840a();
                }
                C3511p.m11976a(3, "Factory", (Object) this, "Creating WebAdTracker for " + webView.getClass().getSimpleName() + "@" + webView.hashCode());
                C3511p.m11978a("[INFO] ", "Attempting to create WebAdTracker for " + webView.getClass().getSimpleName() + "@" + webView.hashCode());
                return C3474a.m11841a(new aa(webView));
            }
        }, WebAdTracker.class);
    }

    private <T> T m11951a(MoatPlugin<T> moatPlugin) {
        return moatPlugin.mo6527a();
    }

    private boolean m11952a() {
        return ((C3500k) MoatAnalytics.getInstance()).m11931a();
    }

    public <T> T createCustomTracker(MoatPlugin<T> moatPlugin) {
        try {
            return m11951a((MoatPlugin) moatPlugin);
        } catch (Exception e) {
            C3502m.m11942a(e);
            return moatPlugin.mo6528b();
        }
    }

    public NativeDisplayTracker createNativeDisplayTracker(View view, Map<String, String> map) {
        try {
            return m11947a(view, map);
        } catch (Exception e) {
            C3502m.m11942a(e);
            return new C3520c();
        }
    }

    public NativeVideoTracker createNativeVideoTracker(String str) {
        try {
            return m11948a(str);
        } catch (Exception e) {
            C3502m.m11942a(e);
            return new C3521d();
        }
    }

    public WebAdTracker createWebAdTracker(ViewGroup viewGroup) {
        try {
            return m11949a(viewGroup);
        } catch (Exception e) {
            C3502m.m11942a(e);
            return new C3522e();
        }
    }

    public WebAdTracker createWebAdTracker(WebView webView) {
        try {
            return m11950a(webView);
        } catch (Exception e) {
            C3502m.m11942a(e);
            return new C3522e();
        }
    }
}
