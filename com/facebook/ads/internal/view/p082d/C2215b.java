package com.facebook.ads.internal.view.p082d;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.adapters.ad;
import com.facebook.ads.internal.p052j.C2000c;
import com.facebook.ads.internal.p056q.p057a.C2133v;
import com.facebook.ads.internal.p056q.p076c.C2142b;
import com.facebook.ads.internal.p056q.p076c.C2147e;
import com.facebook.ads.internal.p056q.p076c.C2147e.C2146a;
import com.facebook.ads.internal.p056q.p076c.C2148f;
import com.facebook.ads.internal.p059a.C1873a;
import com.facebook.ads.internal.p059a.C1874b;
import com.facebook.ads.internal.p069m.C2012c;
import com.facebook.ads.internal.view.C1923a.C1832a;
import com.facebook.ads.internal.view.component.C2200a;
import com.facebook.ads.internal.view.component.C2204e;
import com.facebook.ads.internal.view.component.C2208i;
import com.facebook.ads.internal.view.p034b.C2182a;
import com.facebook.ads.internal.view.p034b.C2182a.C1901b;
import com.facebook.ads.internal.view.p034b.C2186d;
import com.facebook.ads.internal.view.p053e.p054b.C2246z;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

public class C2215b {
    private static final String f5391a = C2215b.class.getSimpleName();
    private static final int f5392b = ((int) (4.0f * C2133v.f5083b));
    private static final int f5393c = ((int) (72.0f * C2133v.f5083b));
    private static final int f5394d = ((int) (8.0f * C2133v.f5083b));
    private final Context f5395e;
    private final C2012c f5396f;
    private final ad f5397g;
    private final String f5398h = C2000c.m6323a(this.f5397g.m5725c());
    private Executor f5399i = AsyncTask.THREAD_POOL_EXECUTOR;
    @Nullable
    private C1832a f5400j;
    @Nullable
    private C2182a f5401k;
    @Nullable
    private C1901b f5402l;

    class C22111 implements C1901b {
        final /* synthetic */ C2215b f5384a;

        class C22101 implements Runnable {
            final /* synthetic */ C22111 f5383a;

            C22101(C22111 c22111) {
                this.f5383a = c22111;
            }

            public void run() {
                if (this.f5383a.f5384a.f5401k == null || this.f5383a.f5384a.f5401k.m6856c()) {
                    Log.w(C2215b.f5391a, "Webview already destroyed, cannot activate");
                } else {
                    this.f5383a.f5384a.f5401k.loadUrl(BridgeUtil.JAVASCRIPT_STR + this.f5383a.f5384a.f5397g.m5726d());
                }
            }
        }

        C22111(C2215b c2215b) {
            this.f5384a = c2215b;
        }

        public void mo3669a() {
            if (this.f5384a.f5401k != null && !TextUtils.isEmpty(this.f5384a.f5397g.m5726d())) {
                this.f5384a.f5401k.post(new C22101(this));
            }
        }

        public void mo3670a(int i) {
        }

        public void mo3671a(String str, Map<String, String> map) {
            Uri parse = Uri.parse(str);
            if ("fbad".equals(parse.getScheme()) && parse.getAuthority().equals("close")) {
                this.f5384a.m7063g();
                return;
            }
            if ("fbad".equals(parse.getScheme()) && C1874b.m5633a(parse.getAuthority()) && this.f5384a.f5400j != null) {
                this.f5384a.f5400j.mo3561a(C2246z.REWARDED_VIDEO_AD_CLICK.m7091a());
            }
            C1873a a = C1874b.m5632a(this.f5384a.f5395e, this.f5384a.f5396f, this.f5384a.f5397g.m5723b(), parse, map);
            if (a != null) {
                try {
                    a.mo3622b();
                } catch (Throwable e) {
                    Log.e(C2215b.f5391a, "Error executing action", e);
                }
            }
        }

        public void mo3672b() {
        }
    }

    class C22122 implements C2146a {
        final /* synthetic */ C2215b f5385a;

        C22122(C2215b c2215b) {
            this.f5385a = c2215b;
        }

        public void mo3774a() {
            if (this.f5385a.f5400j != null) {
                this.f5385a.f5400j.mo3561a(C2246z.REWARD_SERVER_FAILED.m7091a());
            }
        }

        public void mo3775a(C2148f c2148f) {
            if (this.f5385a.f5400j != null) {
                if (c2148f == null || !c2148f.m6878a()) {
                    this.f5385a.f5400j.mo3561a(C2246z.REWARD_SERVER_FAILED.m7091a());
                } else {
                    this.f5385a.f5400j.mo3561a(C2246z.REWARD_SERVER_SUCCESS.m7091a());
                }
            }
        }
    }

    public enum C2214a {
        SCREENSHOTS,
        MARKUP,
        INFO
    }

    public C2215b(Context context, C2012c c2012c, ad adVar, C1832a c1832a) {
        this.f5395e = context;
        this.f5396f = c2012c;
        this.f5397g = adVar;
        this.f5400j = c1832a;
    }

    private void m7063g() {
        if (this.f5400j != null) {
            this.f5400j.mo3561a(C2246z.REWARDED_VIDEO_END_ACTIVITY.m7091a());
        }
    }

    private View m7064h() {
        View c2208i = new C2208i(this.f5395e, this.f5397g.m5736n(), true, false, false);
        c2208i.m7047a(this.f5397g.m5728f(), this.f5397g.m5730h(), false, true);
        c2208i.setAlignment(17);
        View c2200a = new C2200a(this.f5395e, true, false, C2246z.REWARDED_VIDEO_AD_CLICK.m7091a(), this.f5397g.m5736n(), this.f5396f, this.f5400j);
        c2200a.m7044a(this.f5397g.m5739q(), this.f5397g.m5738p(), this.f5397g.m5723b(), new HashMap());
        ImageView c2204e = new C2204e(this.f5395e);
        c2204e.setRadius(50);
        new C2186d(c2204e).m7000a().m7004a(this.f5397g.m5731i());
        View linearLayout = new LinearLayout(this.f5395e);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(17);
        linearLayout.addView(c2204e, new LayoutParams(f5393c, f5393c));
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.setMargins(0, f5394d, 0, f5394d);
        linearLayout.addView(c2208i, layoutParams);
        linearLayout.addView(c2200a, layoutParams);
        return linearLayout;
    }

    private View m7065i() {
        View recyclerView = new RecyclerView(this.f5395e);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.f5395e, 0, false));
        recyclerView.setAdapter(new C2216c(this.f5397g.m5737o(), f5392b));
        return recyclerView;
    }

    private View m7066j() {
        this.f5402l = new C22111(this);
        this.f5401k = new C2182a(this.f5395e, new WeakReference(this.f5402l), 1);
        this.f5401k.loadDataWithBaseURL(C2142b.m6857a(), this.f5398h, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, AudienceNetworkActivity.WEBVIEW_ENCODING, null);
        return this.f5401k;
    }

    public boolean m7067a() {
        return m7068b() == C2214a.MARKUP;
    }

    public C2214a m7068b() {
        return !this.f5397g.m5737o().isEmpty() ? C2214a.SCREENSHOTS : !TextUtils.isEmpty(this.f5398h) ? C2214a.MARKUP : C2214a.INFO;
    }

    public Pair<C2214a, View> m7069c() {
        C2214a b = m7068b();
        switch (b) {
            case MARKUP:
                return new Pair(b, m7066j());
            case SCREENSHOTS:
                return new Pair(b, m7065i());
            default:
                return new Pair(b, m7064h());
        }
    }

    public void m7070d() {
        if (!TextUtils.isEmpty(this.f5397g.m5734l())) {
            C2147e c2147e = new C2147e(this.f5395e, new HashMap());
            c2147e.m6876a(new C22122(this));
            c2147e.executeOnExecutor(this.f5399i, new String[]{this.f5397g.m5734l()});
        }
    }

    public void m7071e() {
        if (this.f5401k != null) {
            C2142b.m6858a(this.f5401k);
            this.f5401k = null;
            this.f5402l = null;
        }
    }
}
