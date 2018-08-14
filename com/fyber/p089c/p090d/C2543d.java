package com.fyber.p089c.p090d;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import android.widget.VideoView;
import com.fyber.Fyber$Settings.UIStringIdentifier;
import com.fyber.cache.CacheManager;
import com.fyber.mediation.MediationUserActivityListener;
import com.fyber.p089c.p090d.p104a.C2531a;
import com.fyber.p089c.p090d.p104a.C2531a.C2530a;
import com.fyber.p089c.p101a.C2523a;
import com.fyber.p089c.p101a.C2523a.C2519a;
import com.fyber.p089c.p101a.C2523a.C2520b;
import com.fyber.p089c.p101a.C2523a.C2521c;
import com.fyber.p089c.p102b.C2525a;
import com.fyber.p089c.p103c.C2529b;
import com.fyber.p089c.p105e.C2545b;
import com.fyber.utils.C2663k;
import com.fyber.utils.C2665m;
import com.fyber.utils.C2671s;
import com.fyber.utils.C2682v;
import com.fyber.utils.FyberLogger;
import com.fyber.utils.StringUtils;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import cz.msebera.android.httpclient.HttpStatus;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: VideoPlayerView */
public final class C2543d extends FrameLayout implements OnCompletionListener, OnErrorListener, OnPreparedListener, OnClickListener, OnTouchListener, C2530a, MediationUserActivityListener, Runnable {
    private boolean f6322A;
    private boolean f6323B;
    private int f6324C;
    private int f6325D;
    private C2531a f6326E;
    private C2432d f6327F;
    private boolean f6328G;
    private boolean f6329H;
    private boolean f6330I;
    private FrameLayout f6331J;
    private boolean f6332K;
    private Activity f6333a;
    private String f6334b;
    private VideoView f6335c;
    private MediaPlayer f6336d;
    private int f6337e;
    private C2545b f6338f;
    private C2525a f6339g;
    private Integer f6340h;
    private Float f6341i;
    private C2529b f6342j;
    private long f6343k;
    private boolean f6344l;
    private String f6345m;
    private TextView f6346n;
    private String f6347o;
    private volatile boolean f6348p;
    private volatile int f6349q;
    private int f6350r;
    private boolean f6351s;
    private String f6352t;
    private C2523a f6353u;
    private C2434b f6354v;
    private int f6355w;
    private ScheduledExecutorService f6356x;
    private C2437b f6357y;
    private final C2542c f6358z;

    /* compiled from: VideoPlayerView */
    public interface C2432d {
        void mo3870a(int i, String str);
    }

    /* compiled from: VideoPlayerView */
    public interface C2434b {
        boolean mo3878a(C2543d c2543d, C2523a c2523a, String str);
    }

    /* compiled from: VideoPlayerView */
    class C25341 implements C2520b {
        final /* synthetic */ C2543d f6303a;

        C25341(C2543d c2543d) {
            this.f6303a = c2543d;
        }

        public final void mo3929a() {
            this.f6303a.m8100c();
        }
    }

    /* compiled from: VideoPlayerView */
    class C25352 implements C2519a {
        final /* synthetic */ C2543d f6304a;

        C25352(C2543d c2543d) {
            this.f6304a = c2543d;
        }

        public final void mo3930a() {
            this.f6304a.m8097a("redirect_fail", "Redirection Error");
        }
    }

    /* compiled from: VideoPlayerView */
    class C25363 implements C2521c {
        final /* synthetic */ C2543d f6305a;

        C25363(C2543d c2543d) {
            this.f6305a = c2543d;
        }

        public final boolean mo3931a(C2523a c2523a, String str) {
            return this.f6305a.f6354v != null && this.f6305a.f6354v.mo3878a(this.f6305a, c2523a, str);
        }
    }

    /* compiled from: VideoPlayerView */
    class C25374 implements AnimationListener {
        final /* synthetic */ C2543d f6306a;

        C25374(C2543d c2543d) {
            this.f6306a = c2543d;
        }

        public final void onAnimationStart(Animation animation) {
        }

        public final void onAnimationEnd(Animation animation) {
            this.f6306a.f6346n.setVisibility(8);
        }

        public final void onAnimationRepeat(Animation animation) {
        }
    }

    /* compiled from: VideoPlayerView */
    class C25385 implements DialogInterface.OnClickListener {
        final /* synthetic */ C2543d f6307a;

        C25385(C2543d c2543d) {
            this.f6307a = c2543d;
        }

        public final void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.cancel();
            this.f6307a.f6329H = false;
            if (this.f6307a.f6328G && this.f6307a.f6336d != null) {
                try {
                    this.f6307a.f6336d.start();
                } catch (IllegalStateException e) {
                    FyberLogger.m8449e("VideoPlayerView", "Unable to start video playback at this moment");
                }
            }
        }
    }

    /* compiled from: VideoPlayerView */
    static /* synthetic */ class C25407 {
        static final /* synthetic */ int[] f6310a = new int[C2533c.m8034a().length];

        static {
            try {
                f6310a[C2533c.f6299h - 1] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f6310a[C2533c.f6293b - 1] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f6310a[C2533c.f6292a - 1] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f6310a[C2533c.f6294c - 1] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f6310a[C2533c.f6295d - 1] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f6310a[C2533c.f6296e - 1] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f6310a[C2533c.f6297f - 1] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f6310a[C2533c.f6298g - 1] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                f6310a[C2533c.f6300i - 1] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                f6310a[C2533c.f6301j - 1] = 10;
            } catch (NoSuchFieldError e10) {
            }
        }
    }

    /* compiled from: VideoPlayerView */
    public static class C2541a {
        private boolean f6311a = true;
        private C2437b f6312b;
        private String f6313c;
        private String f6314d;
        private String f6315e;
        private String f6316f;
        private C2432d f6317g;
        private Float f6318h;
        private C2434b f6319i;
        private boolean f6320j = false;

        public final C2541a m8049a(C2437b c2437b) {
            this.f6312b = c2437b;
            return this;
        }

        public final C2541a m8053a(String str) {
            this.f6313c = str;
            return this;
        }

        public final C2541a m8056b(String str) {
            this.f6314d = str;
            return this;
        }

        public final C2541a m8057c(String str) {
            this.f6316f = str;
            return this;
        }

        public final C2541a m8058d(String str) {
            this.f6315e = str;
            return this;
        }

        public final C2541a m8054a(boolean z) {
            this.f6311a = z;
            return this;
        }

        public final C2541a m8059e(String str) {
            if (StringUtils.notNullNorEmpty(str)) {
                this.f6311a = Boolean.parseBoolean(str);
            }
            return this;
        }

        public final C2541a m8052a(Float f) {
            this.f6318h = f;
            return this;
        }

        public final C2541a m8051a(C2432d c2432d) {
            this.f6317g = c2432d;
            return this;
        }

        public final C2541a m8050a(C2434b c2434b) {
            this.f6319i = c2434b;
            return this;
        }

        public final C2541a m8048a() {
            this.f6320j = true;
            return this;
        }

        public final C2543d m8055a(@NonNull Activity activity) {
            return new C2543d(activity, this);
        }
    }

    /* compiled from: VideoPlayerView */
    private static class C2542c extends Handler {
        private final WeakReference<C2543d> f6321a;

        C2542c(C2543d c2543d) {
            this.f6321a = new WeakReference(c2543d);
        }

        public final void handleMessage(Message message) {
            int i = C2533c.m8034a()[message.what];
            C2543d c2543d = (C2543d) this.f6321a.get();
            if (c2543d != null) {
                switch (C25407.f6310a[i - 1]) {
                    case 1:
                        c2543d.removeView(c2543d.f6331J);
                        return;
                    case 2:
                        C2543d.m8076g(c2543d);
                        FyberLogger.m8448d("VideoPlayerView", "Buffering video");
                        return;
                    case 3:
                        c2543d.m8085l();
                        FyberLogger.m8448d("VideoPlayerView", "No longer buffering video");
                        return;
                    case 4:
                        if (c2543d.f6342j != null) {
                            c2543d.f6342j.m8028a((long) c2543d.f6349q);
                            return;
                        }
                        return;
                    case 5:
                        c2543d.f6322A = true;
                        c2543d.m8082j();
                        return;
                    case 6:
                        C2543d.m8088m(c2543d);
                        return;
                    case 7:
                        c2543d.m8085l();
                        c2543d.m8066a(C2671s.m8532a(UIStringIdentifier.RV_ALERT_DIALOG_TITLE), C2671s.m8532a(UIStringIdentifier.RV_ERROR_DIALOG_MESSAGE_DEFAULT), "OK", null, "error");
                        return;
                    case 8:
                        FyberLogger.m8451i("VideoPlayerView", "displayErrorLoadingDialog(): Error Loading video");
                        c2543d.f6348p = true;
                        c2543d.f6357y.mo3881a();
                        if (c2543d.f6332K) {
                            c2543d.m8066a(C2671s.m8532a(UIStringIdentifier.RV_ALERT_DIALOG_TITLE), C2671s.m8532a(UIStringIdentifier.RV_ALERT_DIALOG_MESSAGE), "OK", null, "error");
                            return;
                        }
                        c2543d.m8075g();
                        c2543d.m8062a(2, "video");
                        return;
                    case 9:
                        c2543d.m8080i();
                        return;
                    case 10:
                        C2543d.m8095s(c2543d);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    static /* synthetic */ void m8095s(C2543d c2543d) {
        ViewGroup viewGroup = (ViewGroup) c2543d.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(c2543d);
        }
        c2543d.f6335c = null;
        c2543d.f6346n = null;
        c2543d.f6339g = null;
        c2543d.f6342j = null;
        c2543d.f6336d = null;
        c2543d.f6338f = null;
        c2543d.f6333a = null;
        if (c2543d.f6353u != null) {
            c2543d.f6353u.m8021b();
            c2543d.f6353u = null;
        }
    }

    private C2543d(Activity activity, C2541a c2541a) {
        boolean z = false;
        super(activity);
        this.f6344l = false;
        this.f6348p = false;
        this.f6350r = 2000;
        this.f6355w = -1;
        this.f6322A = false;
        this.f6323B = false;
        this.f6324C = 0;
        this.f6328G = true;
        this.f6329H = false;
        this.f6330I = false;
        this.f6333a = activity;
        this.f6357y = c2541a.f6312b;
        this.f6334b = c2541a.f6313c;
        String c = c2541a.f6314d;
        if (!StringUtils.nullOrEmpty(c)) {
            if (C2682v.m8582a(c)) {
                z = true;
            } else {
                z = C2663k.m8519a(getContext(), "android.intent.action.VIEW", Uri.parse(c));
            }
        }
        if (z) {
            this.f6345m = c2541a.f6314d;
            this.f6347o = c2541a.f6316f;
        }
        this.f6352t = c2541a.f6315e;
        this.f6351s = c2541a.f6311a;
        this.f6327F = c2541a.f6317g;
        this.f6341i = c2541a.f6318h;
        this.f6354v = c2541a.f6319i;
        this.f6332K = c2541a.f6320j;
        this.f6358z = new C2542c(this);
    }

    public final void m8096a() {
        Uri a = CacheManager.m8105a().m8112a(this.f6334b, getContext());
        boolean contains = a.getScheme().contains("file");
        this.f6335c = new VideoView(this.f6333a);
        this.f6335c.setContentDescription("videoPlayer");
        this.f6339g = new C2525a(this.f6333a);
        int a2 = this.f6339g.m8025a();
        this.f6339g.setLayoutParams(new LayoutParams(a2, a2, 53));
        this.f6339g.setTag(Boolean.valueOf(true));
        if (!contains) {
            this.f6338f = new C2545b(this.f6333a);
            this.f6338f.setLayoutParams(new LayoutParams(-2, -2, 17));
        }
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        this.f6335c.setLayoutParams(layoutParams);
        this.f6331J = new FrameLayout(this.f6333a);
        this.f6331J.setBackgroundColor(-16777216);
        this.f6331J.setLayoutParams(new LayoutParams(-1, -1));
        this.f6331J.setTag(Boolean.valueOf(true));
        setBackgroundColor(-16777216);
        addView(this.f6335c);
        addView(this.f6331J);
        if (!contains) {
            addView(this.f6338f);
        }
        if (StringUtils.notNullNorEmpty(this.f6345m)) {
            this.f6346n = new TextView(this.f6333a);
            this.f6346n.setTag(Boolean.valueOf(true));
            this.f6346n.setGravity(17);
            this.f6346n.setLayoutParams(new LayoutParams(-1, (int) TypedValue.applyDimension(1, BitmapDescriptorFactory.HUE_ORANGE, getResources().getDisplayMetrics()), 80));
            if (StringUtils.notNullNorEmpty(this.f6347o)) {
                this.f6346n.setText(this.f6347o);
            } else {
                this.f6346n.setText(C2671s.m8532a(UIStringIdentifier.RV_CLICKTHROUGH_HINT));
            }
            this.f6346n.setBackgroundColor(-1304543682);
            this.f6346n.setTextColor(-1);
            this.f6346n.setTextSize(1, 14.0f);
            this.f6346n.setContentDescription("clickThroughHint");
        }
        this.f6335c.setVideoURI(a);
        this.f6357y.mo3884a(this.f6334b, contains, CacheManager.m8105a().m8116b().m8143a());
        this.f6326E = new C2531a(this);
        this.f6335c.requestFocus();
        this.f6358z.sendEmptyMessageDelayed(C2533c.f6298g - 1, 15000);
        this.f6335c.setOnPreparedListener(this);
        this.f6335c.setOnCompletionListener(this);
        this.f6335c.setOnErrorListener(this);
        this.f6335c.setOnTouchListener(this);
        this.f6339g.setOnClickListener(this);
    }

    public final void m8099b() {
        if (this.f6338f != null) {
            this.f6338f.m8103a();
        }
    }

    public final void onPrepared(MediaPlayer mediaPlayer) {
        FyberLogger.m8448d("VideoPlayerView", "onPrepared()");
        if (this.f6335c != null) {
            m8087m();
            if (!this.f6348p) {
                this.f6336d = mediaPlayer;
                this.f6335c.start();
                this.f6326E.m8031a();
                m8085l();
                this.f6337e = this.f6335c.getDuration();
                this.f6326E.m8032a((long) this.f6337e);
                this.f6342j = new C2529b(this.f6333a, this.f6337e);
                int applyDimension = (int) TypedValue.applyDimension(1, BitmapDescriptorFactory.HUE_YELLOW, getResources().getDisplayMetrics());
                this.f6342j.setLayoutParams(new LayoutParams(applyDimension, applyDimension, 51));
                this.f6357y.mo3882a(this.f6337e);
                this.f6343k = (long) Math.min(((double) this.f6337e) * 0.25d, 15000.0d);
                addView(this.f6342j);
                float floatValue = this.f6341i != null ? this.f6341i.floatValue() : m8083k();
                applyDimension = 0;
                if (floatValue == -1.0f) {
                    applyDimension = this.f6337e + 1000;
                } else if (floatValue >= 0.0f && floatValue < 1.0f) {
                    applyDimension = (int) (((float) this.f6337e) * floatValue);
                } else if (floatValue >= 1.0f) {
                    applyDimension = (int) (1000.0f * floatValue);
                }
                FyberLogger.m8448d("VideoPlayerView", "Delay for close button - " + applyDimension + "ms");
                this.f6340h = Integer.valueOf(applyDimension);
                if (this.f6340h.intValue() == 0) {
                    m8082j();
                    this.f6322A = true;
                }
                this.f6356x = Executors.newSingleThreadScheduledExecutor();
                this.f6356x.scheduleAtFixedRate(this, 0, 50, TimeUnit.MILLISECONDS);
                if (this.f6346n != null && !C2543d.m8067a(this.f6346n)) {
                    addView(this.f6346n);
                }
            }
        }
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        FyberLogger.m8448d("VideoPlayerView", "onCompletion() - mediaPlayer = " + mediaPlayer);
        this.f6335c.stopPlayback();
        m8077h();
        m8073f();
        if (mediaPlayer != null) {
            this.f6357y.mo3885b();
            m8062a(1, null);
            return;
        }
        m8097a("unknown", "onCompletion - video playing more than total duration");
    }

    public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        String str;
        this.f6330I = true;
        FyberLogger.m8449e("VideoPlayerView", "An error occurred, error: " + i);
        this.f6335c.stopPlayback();
        switch (i2) {
            case Integer.MIN_VALUE:
                str = "media_error_system";
                break;
            case -1010:
                str = "media_error_unsupported";
                break;
            case -1007:
                str = "media_error_malformed";
                break;
            case -1004:
                str = "media_error_io";
                break;
            case -110:
                str = "media_error_time_out";
                break;
            default:
                str = "media_error_unknown";
                break;
        }
        this.f6357y.mo3887b(str);
        m8087m();
        m8061a(C2533c.f6297f);
        return true;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        FyberLogger.m8448d("VideoPlayerView", "onTouch()");
        if (!(!StringUtils.notNullNorEmpty(this.f6345m) || this.f6344l || this.f6336d == null)) {
            try {
                this.f6336d.pause();
                this.f6344l = true;
                if (((Boolean) this.f6346n.getTag()).booleanValue()) {
                    this.f6346n.setTag(Boolean.valueOf(false));
                    this.f6346n.setVisibility(8);
                }
                this.f6357y.mo3888c();
                if (C2682v.m8582a(this.f6345m)) {
                    if (this.f6353u != null) {
                        this.f6353u.setVisibility(0);
                    } else {
                        this.f6353u = new C2523a(this.f6333a, this.f6345m);
                        this.f6353u.m8019a(new C25341(this));
                        this.f6353u.m8018a(new C25352(this));
                        this.f6353u.m8020a(new C25363(this));
                        this.f6333a.addContentView(this.f6353u, new LayoutParams(-1, -1));
                    }
                    C2543d.m8063a(this.f6339g, false);
                    return true;
                } else if (this.f6354v != null) {
                    this.f6354v.mo3878a(this, null, this.f6345m);
                    return true;
                }
            } catch (IllegalStateException e) {
                FyberLogger.m8449e("VideoPlayerView", "Unable to pause video playback at this moment");
            }
        }
        return false;
    }

    public final void onClick(View view) {
        if (!this.f6344l) {
            m8069b("abort_btn", "closed button was pressed");
        }
    }

    public final void notifyOnUserLeft() {
        m8097a("app_background", "notifyOnUserLeft()");
    }

    public final boolean notifyOnBackPressed() {
        if (this.f6323B) {
            return false;
        }
        if (this.f6344l) {
            if (!(this.f6353u == null || this.f6353u.m8022c())) {
                m8100c();
            }
        } else if (this.f6322A) {
            String str = "back button was pressed";
            if (getVisibility() != 0 || this.f6336d == null) {
                m8097a("back_btn", str);
                return false;
            }
            m8069b("back_btn", str);
        }
        return true;
    }

    public final void m8100c() {
        if (this.f6353u != null) {
            this.f6353u.setVisibility(8);
            this.f6353u.m8017a();
        }
        if (this.f6336d != null) {
            try {
                this.f6336d.start();
            } catch (IllegalStateException e) {
                FyberLogger.m8449e("VideoPlayerView", "Unable to start video playback at this moment");
            }
        }
        C2543d.m8063a(this.f6339g, true);
        this.f6344l = false;
    }

    private void m8073f() {
        if (!(this.f6356x == null || this.f6356x.isShutdown())) {
            this.f6356x.shutdownNow();
        }
        CacheManager.m8105a().m8118c(getContext());
    }

    public final void run() {
        int orientation = this.f6333a.getWindowManager().getDefaultDisplay().getOrientation();
        if (this.f6325D != orientation) {
            this.f6324C++;
            if (this.f6324C == 6) {
                this.f6324C = 0;
                this.f6325D = orientation;
            }
        } else if (!this.f6330I) {
            if ((this.f6336d != null && this.f6336d.isPlaying()) || C2665m.m8522a()) {
                if (this.f6349q > this.f6337e + HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                    onCompletion(null);
                }
                this.f6349q = this.f6335c.getCurrentPosition();
                if (this.f6349q + this.f6350r >= this.f6337e) {
                    if (this.f6339g != null) {
                        this.f6339g.setOnClickListener(null);
                    }
                    this.f6322A = false;
                    this.f6350r = -1000;
                }
                if (((Boolean) this.f6331J.getTag()).booleanValue() && this.f6349q > 120) {
                    this.f6331J.setTag(Boolean.valueOf(false));
                    m8061a(C2533c.f6299h);
                }
                this.f6355w++;
                if (this.f6355w == 20) {
                    this.f6355w = 0;
                }
                if ((this.f6355w != 0 && this.f6355w != 10) || !this.f6326E.m8033a((long) this.f6349q, this.f6328G, m8089n())) {
                    m8061a(C2533c.f6294c);
                    if (this.f6355w == 0) {
                        this.f6357y.mo3886b(this.f6349q);
                    }
                    if (this.f6346n != null && ((Boolean) this.f6346n.getTag()).booleanValue() && ((long) this.f6349q) > this.f6343k) {
                        m8061a(C2533c.f6296e);
                    }
                    if (this.f6339g != null && ((Boolean) this.f6339g.getTag()).booleanValue() && this.f6349q > this.f6340h.intValue()) {
                        m8061a(C2533c.f6295d);
                    }
                }
            }
        }
    }

    public final void m8097a(String str, String str2) {
        FyberLogger.m8448d("VideoPlayerView", "video cancelling: " + str2);
        this.f6357y.mo3883a(str);
        m8075g();
        m8062a(2, str);
    }

    private void m8075g() {
        m8087m();
        m8073f();
        m8077h();
    }

    private void m8077h() {
        this.f6323B = true;
        if (Looper.myLooper() == Looper.getMainLooper()) {
            m8080i();
        } else {
            m8061a(C2533c.f6300i);
        }
    }

    private void m8062a(int i, String str) {
        if (this.f6327F != null) {
            this.f6327F.mo3870a(i, str);
            this.f6327F = null;
        }
    }

    @UiThread
    @TargetApi(16)
    private void m8080i() {
        if (this.f6335c != null) {
            if (C2665m.m8523a(16)) {
                int parseColor = Color.parseColor("#10000000");
                int parseColor2 = Color.parseColor("#000000");
                Drawable transitionDrawable = new TransitionDrawable(new ColorDrawable[]{new ColorDrawable(parseColor), new ColorDrawable(parseColor2)});
                this.f6335c.setBackground(transitionDrawable);
                transitionDrawable.startTransition(HttpStatus.SC_BAD_REQUEST);
            } else {
                setVisibility(8);
            }
        }
        this.f6358z.sendEmptyMessageDelayed(C2533c.f6301j - 1, 400);
        if (this.f6338f != null) {
            this.f6338f.m8104b();
        }
    }

    private void m8082j() {
        this.f6339g.setTag(Boolean.valueOf(false));
        if (!C2543d.m8067a(this.f6339g)) {
            addView(this.f6339g);
        }
    }

    private float m8083k() {
        Exception e;
        try {
            Object obj = this.f6333a.getPackageManager().getApplicationInfo(this.f6333a.getPackageName(), 128).metaData.get("FYBVideoPlayerOptionCloseButtonDelay");
            if (obj != null) {
                return Float.parseFloat(obj.toString());
            }
        } catch (NameNotFoundException e2) {
            e = e2;
            FyberLogger.m8449e("VideoPlayerView", "Failed to load meta-data from Manifest: " + e.getMessage());
            return 0.0f;
        } catch (NullPointerException e3) {
            e = e3;
            FyberLogger.m8449e("VideoPlayerView", "Failed to load meta-data from Manifest: " + e.getMessage());
            return 0.0f;
        } catch (NumberFormatException e4) {
            e = e4;
            FyberLogger.m8449e("VideoPlayerView", "Failed to load meta-data from Manifest: " + e.getMessage());
            return 0.0f;
        }
        return 0.0f;
    }

    private void m8085l() {
        if (this.f6338f != null) {
            this.f6338f.setVisibility(8);
        }
    }

    private void m8069b(String str, String str2) {
        try {
            if (this.f6336d == null) {
                return;
            }
            if (this.f6351s) {
                this.f6336d.pause();
                this.f6329H = true;
                m8066a(C2671s.m8532a(UIStringIdentifier.RV_ALERT_DIALOG_EXIT_VIDEO_TEXT), this.f6352t, C2671s.m8532a(UIStringIdentifier.RV_ALERT_DIALOG_CLOSE_VIDEO_TEXT), C2671s.m8532a(UIStringIdentifier.RV_ALERT_DIALOG_RESUME_VIDEO_TEXT), str);
                return;
            }
            m8097a(str, str2);
        } catch (IllegalStateException e) {
            FyberLogger.m8449e("VideoPlayerView", "Unable to pause video playback at this moment");
        }
    }

    public final void mo3932a(boolean z) {
        FyberLogger.m8451i("VideoPlayerView", "onBufferingStateChanged - state = " + z);
        if (z) {
            m8061a(C2533c.f6293b);
            if (C2665m.m8522a() && this.f6336d != null) {
                this.f6336d.pause();
                this.f6328G = false;
                return;
            }
            return;
        }
        m8061a(C2533c.f6292a);
        if (!(m8089n() || this.f6336d == null)) {
            this.f6336d.start();
        }
        if (C2665m.m8522a()) {
            this.f6328G = true;
        }
    }

    private void m8066a(String str, String str2, String str3, String str4, final String str5) {
        if (!this.f6333a.isFinishing()) {
            Builder builder = new Builder(this.f6333a);
            if (StringUtils.notNullNorEmpty(str4)) {
                builder.setPositiveButton(str4, new C25385(this));
            }
            builder.setTitle(str).setMessage(str2).setCancelable(false).setNegativeButton(str3, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ C2543d f6309b;

                public final void onClick(DialogInterface dialogInterface, int i) {
                    this.f6309b.m8097a(str5, "Close button on the dialog was pressed");
                }
            }).create().show();
        }
    }

    private static boolean m8067a(View view) {
        return view.getParent() != null;
    }

    private void m8087m() {
        this.f6358z.removeMessages(C2533c.f6298g - 1);
    }

    private void m8061a(int i) {
        this.f6358z.sendEmptyMessage(i - 1);
    }

    private boolean m8089n() {
        return this.f6344l || this.f6329H;
    }

    private static void m8063a(View view, boolean z) {
        if (view != null) {
            view.setClickable(z);
        }
    }

    public final void m8101d() {
        if (this.f6336d != null && !this.f6330I) {
            try {
                this.f6336d.pause();
            } catch (IllegalStateException e) {
                FyberLogger.m8449e("VideoPlayerView", "Unable to pause video playback at this moment");
            }
        }
    }

    public final void m8102e() {
        this.f6330I = true;
    }

    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (!m8089n()) {
            super.onLayout(z, i, i2, i3, i4);
        }
    }

    static /* synthetic */ void m8076g(C2543d c2543d) {
        if (c2543d.f6338f != null) {
            c2543d.f6338f.setVisibility(0);
        }
    }

    static /* synthetic */ void m8088m(C2543d c2543d) {
        c2543d.f6346n.setTag(Boolean.valueOf(false));
        Animation alphaAnimation = new AlphaAnimation(0.7f, 0.0f);
        alphaAnimation.setDuration(1000);
        alphaAnimation.setAnimationListener(new C25374(c2543d));
        c2543d.f6346n.startAnimation(alphaAnimation);
    }
}
