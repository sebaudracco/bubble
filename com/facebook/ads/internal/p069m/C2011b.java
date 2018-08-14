package com.facebook.ads.internal.p069m;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.facebook.ads.internal.p056q.p076c.C2145d;
import com.facebook.ads.internal.p058o.C2039d;
import com.facebook.ads.internal.p068l.C2005a;
import com.facebook.ads.internal.p071p.p072a.C2048a;
import com.facebook.ads.internal.p071p.p072a.C2060n;
import com.facebook.ads.internal.p071p.p072a.C2062p;
import com.mopub.common.Constants;
import com.stepleaderdigital.reveal.Reveal;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;

public class C2011b {
    private static final String f4729a = C2011b.class.getSimpleName();
    private static final String f4730b = C2039d.m6540b();
    private final C2010a f4731c;
    private final ThreadPoolExecutor f4732d;
    private final ConnectivityManager f4733e;
    private final C2048a f4734f;
    private final Handler f4735g;
    private final long f4736h;
    private final long f4737i;
    private final Runnable f4738j = new C20091(this);
    private volatile boolean f4739k;
    private int f4740l;
    private long f4741m;

    class C20091 implements Runnable {
        final /* synthetic */ C2011b f4728a;

        class C20081 extends AsyncTask<Void, Void, Void> {
            final /* synthetic */ C20091 f4727a;

            C20081(C20091 c20091) {
                this.f4727a = c20091;
            }

            protected Void m6382a(Void... voidArr) {
                C2011b.m6391b(this.f4727a.f4728a);
                if (this.f4727a.f4728a.f4741m > 0) {
                    try {
                        Thread.sleep(this.f4727a.f4728a.f4741m);
                    } catch (InterruptedException e) {
                    }
                }
                this.f4727a.f4728a.m6394d();
                return null;
            }

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m6382a((Void[]) objArr);
            }
        }

        C20091(C2011b c2011b) {
            this.f4728a = c2011b;
        }

        public void run() {
            this.f4728a.f4739k = false;
            if (this.f4728a.f4732d.getQueue().isEmpty()) {
                new C20081(this).executeOnExecutor(this.f4728a.f4732d, new Void[0]);
            }
        }
    }

    interface C2010a {
        JSONObject mo3718a();

        boolean mo3719a(JSONArray jSONArray);

        void mo3720b();

        void mo3721b(JSONArray jSONArray);

        boolean mo3722c();
    }

    C2011b(Context context, C2010a c2010a) {
        this.f4731c = c2010a;
        this.f4732d = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
        this.f4733e = (ConnectivityManager) context.getSystemService("connectivity");
        this.f4734f = C2145d.m6865b(context);
        this.f4735g = new Handler(Looper.getMainLooper());
        this.f4736h = C2005a.m6347h(context);
        this.f4737i = C2005a.m6348i(context);
    }

    private void m6389a(long j) {
        this.f4735g.postDelayed(this.f4738j, j);
    }

    static /* synthetic */ int m6391b(C2011b c2011b) {
        int i = c2011b.f4740l + 1;
        c2011b.f4740l = i;
        return i;
    }

    private void m6393c() {
        if (this.f4740l >= 5) {
            m6396e();
            m6398b();
            return;
        }
        if (this.f4740l == 1) {
            this.f4741m = Reveal.CHECK_DELAY;
        } else {
            this.f4741m *= 2;
        }
        m6397a();
    }

    @WorkerThread
    private void m6394d() {
        try {
            NetworkInfo activeNetworkInfo = this.f4733e.getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting()) {
                m6389a(this.f4737i);
                return;
            }
            JSONObject a = this.f4731c.mo3718a();
            if (a == null) {
                m6396e();
                return;
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("attempt", String.valueOf(this.f4740l));
            a.put("data", jSONObject);
            C2062p c2062p = new C2062p();
            c2062p.m6626a("payload", a.toString());
            C2060n b = this.f4734f.m6587b(f4730b, c2062p);
            Object e = b != null ? b.m6620e() : null;
            if (TextUtils.isEmpty(e)) {
                if (a.has(Constants.VIDEO_TRACKING_EVENTS_KEY)) {
                    this.f4731c.mo3721b(a.getJSONArray(Constants.VIDEO_TRACKING_EVENTS_KEY));
                }
                m6393c();
            } else if (b.m6616a() != 200) {
                if (a.has(Constants.VIDEO_TRACKING_EVENTS_KEY)) {
                    this.f4731c.mo3721b(a.getJSONArray(Constants.VIDEO_TRACKING_EVENTS_KEY));
                }
                m6393c();
            } else if (!this.f4731c.mo3719a(new JSONArray(e))) {
                m6393c();
            } else if (this.f4731c.mo3722c()) {
                m6393c();
            } else {
                m6396e();
            }
        } catch (Exception e2) {
            m6393c();
        }
    }

    private void m6396e() {
        this.f4740l = 0;
        this.f4741m = 0;
        if (this.f4732d.getQueue().size() == 0) {
            this.f4731c.mo3720b();
        }
    }

    void m6397a() {
        this.f4739k = true;
        this.f4735g.removeCallbacks(this.f4738j);
        m6389a(this.f4736h);
    }

    void m6398b() {
        if (!this.f4739k) {
            this.f4739k = true;
            this.f4735g.removeCallbacks(this.f4738j);
            m6389a(this.f4737i);
        }
    }
}
