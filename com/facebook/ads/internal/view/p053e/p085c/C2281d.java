package com.facebook.ads.internal.view.p053e.p085c;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import com.facebook.ads.internal.view.p053e.C2249b;
import com.facebook.ads.internal.view.p053e.p054b.C1841k;
import com.facebook.ads.internal.view.p053e.p054b.C1842i;
import com.facebook.ads.internal.view.p053e.p054b.C1844c;
import com.facebook.ads.internal.view.p053e.p054b.C2229b;
import com.facebook.ads.internal.view.p053e.p054b.C2233h;
import com.facebook.ads.internal.view.p053e.p054b.C2234j;
import com.facebook.ads.internal.view.p053e.p054b.C2241t;
import com.facebook.ads.internal.view.p053e.p054b.C2242u;
import com.facebook.ads.internal.view.p053e.p083a.C2223b;
import com.stepleaderdigital.reveal.Reveal;

@TargetApi(12)
public class C2281d implements C2223b {
    private final C1842i f5511a;
    private final C1841k f5512b;
    private final C1844c f5513c;
    private final C2242u f5514d;
    private final Handler f5515e;
    private final boolean f5516f;
    private View f5517g;
    @Nullable
    private C2280a f5518h;
    @Nullable
    private C2249b f5519i;
    private boolean f5520j;

    class C22731 extends C1842i {
        final /* synthetic */ C2281d f5500a;

        C22731(C2281d c2281d) {
            this.f5500a = c2281d;
        }

        public void m7182a(C2233h c2233h) {
            this.f5500a.m7190a(1, 0);
        }
    }

    class C22742 extends C1841k {
        final /* synthetic */ C2281d f5501a;

        C22742(C2281d c2281d) {
            this.f5501a = c2281d;
        }

        public void m7184a(C2234j c2234j) {
            if (!this.f5501a.f5520j) {
                return;
            }
            if (this.f5501a.f5518h == C2280a.FADE_OUT_ON_PLAY || this.f5501a.f5516f) {
                this.f5501a.f5518h = null;
                this.f5501a.m7196c();
                return;
            }
            this.f5501a.m7190a(0, 8);
        }
    }

    class C22753 extends C1844c {
        final /* synthetic */ C2281d f5502a;

        C22753(C2281d c2281d) {
            this.f5502a = c2281d;
        }

        public void m7186a(C2229b c2229b) {
            if (this.f5502a.f5518h != C2280a.INVSIBLE) {
                this.f5502a.f5517g.setAlpha(1.0f);
                this.f5502a.f5517g.setVisibility(0);
            }
        }
    }

    class C22784 extends C2242u {
        final /* synthetic */ C2281d f5505a;

        class C22771 extends AnimatorListenerAdapter {
            final /* synthetic */ C22784 f5504a;

            class C22761 implements Runnable {
                final /* synthetic */ C22771 f5503a;

                C22761(C22771 c22771) {
                    this.f5503a = c22771;
                }

                public void run() {
                    if (this.f5503a.f5504a.f5505a.f5520j) {
                        this.f5503a.f5504a.f5505a.m7196c();
                    }
                }
            }

            C22771(C22784 c22784) {
                this.f5504a = c22784;
            }

            public void onAnimationEnd(Animator animator) {
                this.f5504a.f5505a.f5515e.postDelayed(new C22761(this), Reveal.CHECK_DELAY);
            }
        }

        C22784(C2281d c2281d) {
            this.f5505a = c2281d;
        }

        public void m7188a(C2241t c2241t) {
            if (this.f5505a.f5519i != null && c2241t.m7089b().getAction() == 0) {
                this.f5505a.f5515e.removeCallbacksAndMessages(null);
                this.f5505a.m7191a(new C22771(this));
            }
        }
    }

    class C22795 extends AnimatorListenerAdapter {
        final /* synthetic */ C2281d f5506a;

        C22795(C2281d c2281d) {
            this.f5506a = c2281d;
        }

        public void onAnimationEnd(Animator animator) {
            this.f5506a.f5517g.setVisibility(8);
        }
    }

    public enum C2280a {
        VISIBLE,
        INVSIBLE,
        FADE_OUT_ON_PLAY
    }

    public C2281d(View view, C2280a c2280a) {
        this(view, c2280a, false);
    }

    public C2281d(View view, C2280a c2280a, boolean z) {
        this.f5511a = new C22731(this);
        this.f5512b = new C22742(this);
        this.f5513c = new C22753(this);
        this.f5514d = new C22784(this);
        this.f5520j = true;
        this.f5515e = new Handler();
        this.f5516f = z;
        m7202a(view, c2280a);
    }

    private void m7190a(int i, int i2) {
        this.f5515e.removeCallbacksAndMessages(null);
        this.f5517g.clearAnimation();
        this.f5517g.setAlpha((float) i);
        this.f5517g.setVisibility(i2);
    }

    private void m7191a(AnimatorListenerAdapter animatorListenerAdapter) {
        this.f5517g.setVisibility(0);
        this.f5517g.animate().alpha(1.0f).setDuration(500).setListener(animatorListenerAdapter);
    }

    private void m7196c() {
        this.f5517g.animate().alpha(0.0f).setDuration(500).setListener(new C22795(this));
    }

    public void m7202a(View view, C2280a c2280a) {
        this.f5518h = c2280a;
        this.f5517g = view;
        this.f5517g.clearAnimation();
        if (c2280a == C2280a.INVSIBLE) {
            this.f5517g.setAlpha(0.0f);
            this.f5517g.setVisibility(8);
            return;
        }
        this.f5517g.setAlpha(1.0f);
        this.f5517g.setVisibility(0);
    }

    public void mo3777a(C2249b c2249b) {
        this.f5519i = c2249b;
        c2249b.getEventBus().m6328a(this.f5511a, this.f5512b, this.f5514d, this.f5513c);
    }

    public boolean m7204a() {
        return this.f5520j;
    }

    public void m7205b() {
        this.f5520j = false;
        m7191a(null);
    }

    public void mo3778b(C2249b c2249b) {
        m7190a(1, 0);
        c2249b.getEventBus().m6330b(this.f5513c, this.f5514d, this.f5512b, this.f5511a);
        this.f5519i = null;
    }
}
