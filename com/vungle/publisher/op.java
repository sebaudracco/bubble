package com.vungle.publisher;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.VideoView;
import com.moat.analytics.mobile.vng.MoatAdEvent;
import com.moat.analytics.mobile.vng.MoatAdEventType;
import com.vungle.publisher.env.i;
import com.vungle.publisher.image.AssetBitmapFactory.a;
import com.vungle.publisher.inject.Injector;
import com.vungle.publisher.log.Logger;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class op extends mf implements OnCompletionListener, OnErrorListener, OnPreparedListener {
    private Bitmap f3181A;
    private final Handler f3182B = new Handler();
    private final Runnable f3183C = new c(this);
    private String f3184D;
    private int f3185E;
    private boolean f3186F;
    private int f3187G;
    private AtomicBoolean f3188H = new AtomicBoolean();
    private int f3189I;
    private boolean f3190J;
    private AtomicBoolean f3191K = new AtomicBoolean();
    private AtomicBoolean f3192L = new AtomicBoolean();
    private int f3193M;
    private int f3194N;
    private AtomicBoolean f3195O = new AtomicBoolean(true);
    private MediaPlayer f3196P;
    jg<?> f3197e;
    TouchDelegate f3198f;
    @Inject
    mv f3199g;
    @Inject
    qg f3200h;
    @Inject
    C1665a f3201i;
    @Inject
    og$a f3202j;
    @Inject
    oj$a f3203k;
    @Inject
    i f3204l;
    @Inject
    zo f3205m;
    @Inject
    cb f3206n;
    @Inject
    lm f3207o;
    @Inject
    zf f3208p;
    private ImageView f3209q;
    private ImageView f3210r;
    private oj f3211s;
    private oa f3212t;
    private RelativeLayout f3213u;
    private VideoView f3214v;
    private ViewGroup f3215w;
    private Bitmap f3216x;
    private Bitmap f3217y;
    private Bitmap f3218z;

    @Singleton
    /* compiled from: vungle */
    public static class C1665a {
        @Inject
        Provider<op> f3180a;

        @Inject
        C1665a() {
        }

        public op m4480a(Activity activity, jh<?, ?, ?> jhVar, p pVar, boolean z, String str) {
            op a = m4479a(activity);
            if (a == null) {
                a = (op) this.f3180a.get();
            }
            a.f3197e = jhVar.m3837D();
            a.b = pVar;
            a.c = z;
            a.f3184D = jhVar.m3845y();
            a.f3208p.m4926a((jh) jhVar, str);
            return a;
        }

        public void m4481a(op opVar, Bundle bundle) {
            if (bundle != null) {
                Logger.d(Logger.AD_TAG, "Restoring saved state");
                opVar.b = (p) bundle.getParcelable("adConfig");
                opVar.f3190J = bundle.getBoolean("adStarted");
                opVar.f3194N = bundle.getInt("currentVideoPosition");
            }
        }

        public op m4479a(Activity activity) {
            return (op) activity.getFragmentManager().findFragmentByTag("videoFragment");
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.f3215w = new RelativeLayout(getActivity().getApplicationContext());
        this.f3215w.setBackgroundColor(-16777216);
        return this.f3215w;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        try {
            super.onActivityCreated(savedInstanceState);
            try {
                if (Injector.getInstance().d()) {
                    Injector.c().m4203a(this);
                } else {
                    Logger.w(Logger.AD_TAG, "SDK not initialized");
                    getActivity().finish();
                }
            } catch (Throwable th) {
                Logger.i(Logger.AD_TAG, "Unexpected error in fragment injection", th);
            }
            this.f3201i.m4481a(this, savedInstanceState);
            if (this.f3190J) {
                this.f3200h.m4568a(new ba());
            }
            Context applicationContext = getActivity().getApplicationContext();
            View videoView = new VideoView(applicationContext);
            this.f3214v = videoView;
            ImageView oaVar = new oa(applicationContext);
            this.f3210r = oaVar;
            View oaVar2 = new oa(applicationContext);
            this.f3212t = oaVar2;
            this.f3216x = this.f3205m.m4937a(a.d);
            this.f3217y = this.f3205m.m4937a(a.e);
            this.f3195O.set(!this.b.isSoundEnabled());
            oaVar2.setOnClickListener(oq.m4526a(this));
            View a = this.f3202j.m4471a(applicationContext, false);
            View a2 = this.f3203k.m4472a(1);
            this.f3211s = a2;
            this.f3215w.addView(videoView);
            LayoutParams layoutParams = (LayoutParams) videoView.getLayoutParams();
            layoutParams.height = -1;
            layoutParams.width = -1;
            layoutParams.addRule(13);
            View relativeLayout = new RelativeLayout(applicationContext);
            this.f3215w.addView(relativeLayout);
            layoutParams = (LayoutParams) relativeLayout.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -2;
            layoutParams.addRule(10);
            relativeLayout.addView(oaVar);
            this.f3205m.m4939a(oaVar, a.a);
            layoutParams = (LayoutParams) oaVar.getLayoutParams();
            layoutParams.addRule(9);
            layoutParams.addRule(15);
            relativeLayout.addView(a);
            a.setVisibility(0);
            layoutParams = (LayoutParams) a.getLayoutParams();
            layoutParams.addRule(11);
            layoutParams.addRule(15);
            ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-1, a2.getProgressBarHeight());
            this.f3215w.addView(a2, layoutParams2);
            layoutParams2.addRule(12);
            a = new RelativeLayout(applicationContext);
            this.f3213u = a;
            this.f3215w.addView(a);
            layoutParams = (LayoutParams) a.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -2;
            layoutParams.addRule(2, 1);
            a.addView(oaVar2);
            layoutParams = (LayoutParams) oaVar2.getLayoutParams();
            layoutParams.addRule(11);
            layoutParams.addRule(15);
            int round = Math.round(this.f3199g.m4396a(2));
            int round2 = Math.round(this.f3199g.m4396a(1));
            relativeLayout.setPadding(round, round2, round, round2);
            a.setPadding(round, round2, round, round2);
            m4514f();
            Logger.i(Logger.AD_TAG, "video play URI = " + this.f3197e.x());
            videoView.setVideoURI(this.f3197e.x());
            m4508c();
            Integer I = this.c ? this.f3197e.I() : this.f3197e.J();
            if (I == null) {
                this.f3189I = 0;
                this.f3192L.set(true);
            } else {
                this.f3189I = I.intValue();
                oaVar.setAlpha(0.0f);
                this.f3192L.set(false);
            }
            oaVar.setOnClickListener(new b(this));
            videoView.setOnTouchListener(or.m4527a(this));
            videoView.setOnCompletionListener(this);
            videoView.setOnErrorListener(this);
            videoView.setOnPreparedListener(this);
        } catch (Throwable th2) {
            Logger.e(Logger.AD_TAG, "exception in onActivityCreated", th2);
        }
    }

    /* synthetic */ void m4500a(View view) {
        Logger.d(Logger.AD_TAG, this.f3195O.get() ? "Muting" : "Unmuting");
        m4522n();
    }

    /* synthetic */ boolean m4505a(View view, MotionEvent motionEvent) {
        Logger.v(Logger.AD_TAG, "video onTouch");
        if (this.f3198f != null) {
            this.f3198f.onTouchEvent(motionEvent);
        }
        return m4504a(motionEvent);
    }

    public void onSaveInstanceState(Bundle outState) {
        try {
            super.onSaveInstanceState(outState);
            outState.putParcelable("adConfig", (Parcelable) this.b);
            outState.putBoolean("adStarted", this.f3190J);
            outState.putInt("currentVideoPosition", this.f3194N);
        } catch (Throwable e) {
            Logger.e(Logger.AD_TAG, "exception in onSaveInstanceState", e);
        }
    }

    void m4508c() {
        if (Boolean.TRUE.equals(this.f3197e.F())) {
            this.f3218z = this.f3205m.m4937a(a.b);
            this.f3181A = this.f3205m.m4937a(a.c);
            m4510d();
            View oaVar = new oa(getActivity());
            this.f3209q = oaVar;
            this.f3213u.addView(oaVar);
            LayoutParams layoutParams = (LayoutParams) oaVar.getLayoutParams();
            layoutParams.addRule(9);
            layoutParams.addRule(15);
            m4360a((ImageView) oaVar);
            Float D = this.f3197e.D();
            if (D == null || D.floatValue() <= 1.0f) {
                Logger.v(Logger.AD_TAG, "cta clickable area not scaled");
            } else {
                oaVar.post(os.m4528a(this, D, oaVar));
            }
            if (Boolean.TRUE.equals(this.f3197e.G())) {
                oaVar.setAlpha(0.0f);
                oaVar.setImageBitmap(this.f3218z);
            } else {
                m4511d(this.f3187G >= this.f3185E);
            }
            oaVar.setOnClickListener(ot.m4529a(this, oaVar));
        }
    }

    /* synthetic */ void m4502a(Float f, ImageView imageView) {
        float sqrt = (float) Math.sqrt((double) f.floatValue());
        int height = imageView.getHeight();
        int width = imageView.getWidth();
        int round = Math.round(((float) height) * sqrt);
        int round2 = Math.round(sqrt * ((float) width));
        Logger.v(Logger.AD_TAG, "scaling cta clickable area " + f + "x - width: " + width + " --> " + round2 + ", height: " + height + " --> " + round);
        Rect rect = new Rect();
        imageView.getHitRect(rect);
        rect.bottom = rect.top + round;
        rect.left = rect.right - round2;
        this.f3198f = new TouchDelegate(rect, imageView);
    }

    /* synthetic */ void m4501a(ImageView imageView, View view) {
        if (this.f3186F) {
            Logger.d(Logger.AD_TAG, "cta overlay onClick");
            imageView.setOnClickListener(null);
            m4507b(false);
            this.f3200h.m4568a(new aa(ji.a.m));
            return;
        }
        Logger.v(Logger.AD_TAG, "cta overlay onClick, but not enabled");
    }

    void m4510d() {
        int i = 0;
        Integer E = this.f3197e.E();
        Integer H = this.f3197e.H();
        if (E == null) {
            if (H != null) {
                Logger.v(Logger.AD_TAG, "overriding cta enabled from null to " + H);
                E = H;
            }
        } else if (H == null) {
            Logger.v(Logger.AD_TAG, "overriding cta shown from null to " + E);
            H = E;
        } else if (H.intValue() > E.intValue()) {
            Logger.v(Logger.AD_TAG, "overriding cta shown from " + H + " to " + E);
            H = E;
        }
        Logger.d(Logger.AD_TAG, "cta shown at " + H + " seconds; enabled at " + E + " seconds");
        this.f3185E = E == null ? 0 : E.intValue();
        if (H != null) {
            i = H.intValue();
        }
        this.f3187G = i;
    }

    boolean m4504a(MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return false;
        }
        m4512e();
        return true;
    }

    void m4512e() {
        if (this.f3209q != null && this.f3188H.compareAndSet(false, true)) {
            ObjectAnimator.ofFloat(this.f3209q, "alpha", new float[]{1.0f}).setDuration(750).start();
        }
    }

    void m4514f() {
        m4360a(this.f3210r);
        m4360a(this.f3212t);
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        this.f3196P = mediaPlayer;
        int duration = mediaPlayer.getDuration();
        Logger.d(Logger.AD_TAG, "video ready: duration " + duration + " ms");
        this.f3208p.m4924a(duration, this.f3214v);
        m4523o();
        this.f3211s.setMaxTimeMillis(duration);
        this.f3200h.m4568a(new ah(duration));
        if (!this.f3204l.a(getActivity())) {
            m4515g();
        }
    }

    public void onResume() {
        try {
            super.onResume();
            Logger.d(Logger.AD_TAG, "video onResume");
            this.f3206n.m3503b();
            m4515g();
        } catch (Throwable e) {
            Logger.e(Logger.AD_TAG, "error resuming VideoFragment", e);
            m4494r();
        }
    }

    public void onPause() {
        Logger.d(Logger.AD_TAG, "video onPause");
        try {
            super.onPause();
            m4516h();
            this.f3206n.m3504c();
            if (this.f3190J) {
                this.f3200h.m4568a(new ar(this.f3214v.getCurrentPosition()));
            }
        } catch (Throwable e) {
            Logger.e(Logger.AD_TAG, "error in VideoFragment.onPause()", e);
            m4494r();
        }
    }

    public void mo3002a(boolean z) {
        super.mo3002a(z);
        if (z) {
            onResume();
        } else {
            onPause();
        }
    }

    private void m4494r() {
        m4496t();
    }

    private void m4495s() {
        this.f3182B.post(this.f3183C);
    }

    private void m4496t() {
        this.f3182B.removeCallbacks(this.f3183C);
    }

    void m4515g() {
        if (!this.f3214v.isPlaying()) {
            if (m4518j()) {
                Logger.v(Logger.AD_TAG, "Confirm dialog showing. Starting video briefly.");
                this.f3214v.seekTo(this.f3194N);
                this.f3214v.start();
                this.f3214v.pause();
                this.f3193M = this.f3194N;
                return;
            }
            Logger.d(Logger.AD_TAG, "Starting video");
            this.f3190J = true;
            this.f3214v.requestFocus();
            this.f3214v.seekTo(this.f3194N);
            this.f3214v.start();
            this.f3193M = this.f3194N;
            m4520l();
            m4495s();
            if (this.f3214v.isPlaying()) {
                this.f3208p.m4925a(MoatAdEventType.AD_EVT_PLAYING, this.f3194N);
            }
        }
    }

    void m4516h() {
        if (this.f3214v.isPlaying()) {
            Logger.d(Logger.AD_TAG, "Pausing video");
            this.f3194N = this.f3214v.getCurrentPosition();
            this.f3208p.m4925a(MoatAdEventType.AD_EVT_PAUSED, this.f3194N);
            this.f3214v.pause();
            m4496t();
        }
    }

    void m4507b(boolean z) {
        m4496t();
        m4509c(z);
        if (z) {
            this.f3208p.m4925a(MoatAdEventType.AD_EVT_COMPLETE, this.f3193M);
            this.f3200h.m4568a(new af(this.f3193M));
        } else {
            this.f3208p.m4925a(MoatAdEventType.AD_EVT_STOPPED, this.f3193M);
            this.f3200h.m4568a(new ai(this.f3193M));
        }
        m4517i();
    }

    void m4509c(boolean z) {
        int duration = z ? this.f3214v.getDuration() : this.f3214v.getCurrentPosition();
        if (duration > this.f3193M) {
            this.f3193M = duration;
        }
    }

    void m4517i() {
        this.f3190J = false;
        this.f3214v.seekTo(0);
        this.f3193M = 0;
        this.f3194N = 0;
        this.f3191K.set(false);
    }

    boolean m4518j() {
        return this.a != null && this.a.isShowing();
    }

    public void mo3001a() {
        Logger.v(Logger.AD_TAG, "back button pressed");
        m4492f(true);
    }

    boolean m4519k() {
        return this.f3192L.get() || this.b.isBackButtonImmediatelyEnabled();
    }

    void m4520l() {
        boolean z = true;
        if (!Boolean.TRUE.equals(this.f3197e.F())) {
            return;
        }
        if (Boolean.TRUE.equals(this.f3197e.G())) {
            if (this.f3209q.getAlpha() < 1.0f) {
                z = false;
            }
            m4513e(z);
            return;
        }
        if (this.f3193M > (this.f3187G * 1000) - 750 && this.f3188H.compareAndSet(false, true)) {
            ObjectAnimator.ofFloat(this.f3209q, "alpha", new float[]{1.0f}).setDuration(750).start();
        }
        if (this.f3193M < this.f3185E * 1000) {
            z = false;
        }
        m4513e(z);
    }

    void m4511d(boolean z) {
        boolean z2 = z && this.f3184D != null;
        Logger.v(Logger.AD_TAG, "cta button " + (z2 ? "enabled" : "disabled"));
        this.f3186F = z2;
        this.f3209q.setImageBitmap(z2 ? this.f3218z : this.f3181A);
    }

    void m4513e(boolean z) {
        if (z != this.f3186F) {
            m4511d(z);
        }
    }

    void m4521m() {
        if (this.f3193M > (this.f3189I * 1000) - 750 && this.f3192L.compareAndSet(false, true)) {
            ObjectAnimator.ofFloat(this.f3210r, "alpha", new float[]{1.0f}).setDuration(750).start();
        }
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        Logger.d(Logger.AD_TAG, "video.onCompletion");
        m4507b(true);
        this.f3196P = null;
        this.f3208p.m4929b();
        this.f3200h.m4568a(new az());
    }

    public boolean onError(MediaPlayer mediaPlayer, int what, int extra) {
        Logger.e(Logger.AD_TAG, "video.onError: " + what + ", " + extra);
        m4507b(false);
        this.f3208p.m4929b();
        this.f3200h.m4568a(new az());
        return true;
    }

    private void m4497u() {
        m4507b(false);
        this.f3214v.stopPlayback();
        this.f3208p.m4929b();
        this.f3200h.m4568a(new ay());
    }

    private void m4492f(boolean z) {
        if (z) {
            if (!m4519k()) {
                return;
            }
        } else if (!this.f3192L.get()) {
            return;
        }
        if (this.f3191K.compareAndSet(false, true)) {
            Logger.d(Logger.AD_TAG, "exiting video");
            if (this.c) {
                onPause();
                AlertDialog v = m4498v();
                this.a = v;
                v.show();
                return;
            }
            this.f3210r.setOnClickListener(null);
            m4497u();
        }
    }

    void m4522n() {
        if (this.f3195O.compareAndSet(this.f3195O.get(), !this.f3195O.get())) {
            this.f3200h.m4568a(new bb(this.f3195O.get()));
            m4523o();
        }
    }

    void m4523o() {
        Logger.d(Logger.AD_TAG, "refresh mute state. isAdMuted = " + this.f3195O.get());
        this.f3212t.setImageBitmap(this.f3195O.get() ? this.f3216x : this.f3217y);
        if (this.f3195O.get()) {
            m4524p();
        } else {
            m4525q();
        }
    }

    public void m4524p() {
        try {
            if (this.f3196P != null) {
                Logger.d(Logger.AD_TAG, "Muting the video");
                this.f3196P.setVolume(0.0f, 0.0f);
                this.f3208p.m4928a(MoatAdEvent.VOLUME_MUTED);
            }
        } catch (IllegalStateException e) {
            Logger.i(Logger.AD_TAG, "Failed to mute the video: " + e.getMessage());
        }
    }

    public void m4525q() {
        try {
            if (this.f3196P != null) {
                Logger.d(Logger.AD_TAG, "Unmuting the video");
                float b = this.f3207o.m4340b();
                this.f3196P.setVolume(b, b);
                this.f3208p.m4928a(MoatAdEvent.VOLUME_UNMUTED);
            }
        } catch (IllegalStateException e) {
            Logger.i(Logger.AD_TAG, "Failed to unmute the video: " + e.getMessage());
        }
    }

    private AlertDialog m4498v() {
        if (this.a != null) {
            return this.a;
        }
        return this.d.m4391a(getActivity(), this.b, new 1(this));
    }

    public String mo3003b() {
        return "videoFragment";
    }
}
