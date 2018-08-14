package com.adcolony.sdk;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import com.adcolony.sdk.aa.C0595a;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import org.json.JSONObject;

class ba extends TextureView implements OnCompletionListener, OnErrorListener, OnPreparedListener, OnSeekCompleteListener, SurfaceTextureListener {
    private boolean f768A;
    private boolean f769B;
    private boolean f770C;
    private boolean f771D;
    private boolean f772E;
    private String f773F;
    private String f774G;
    private FileInputStream f775H;
    private af f776I;
    private C0673c f777J;
    private Surface f778K;
    private SurfaceTexture f779L;
    private RectF f780M = new RectF();
    private C0651a f781N;
    private ProgressBar f782O;
    private MediaPlayer f783P;
    private JSONObject f784Q = C0802y.m1453a();
    private ExecutorService f785R = Executors.newSingleThreadExecutor();
    private af f786S;
    private float f787a;
    private float f788b;
    private float f789c;
    private float f790d;
    private float f791e;
    private float f792f;
    private int f793g;
    private boolean f794h = true;
    private Paint f795i = new Paint();
    private Paint f796j = new Paint(1);
    private int f797k;
    private int f798l;
    private int f799m;
    private int f800n;
    private int f801o;
    private int f802p;
    private int f803q;
    private int f804r;
    private double f805s;
    private double f806t;
    private long f807u;
    private boolean f808v;
    private boolean f809w;
    private boolean f810x;
    private boolean f811y;
    private boolean f812z;

    class C06421 implements ah {
        final /* synthetic */ ba f758a;

        C06421(ba baVar) {
            this.f758a = baVar;
        }

        public void mo1863a(af afVar) {
            if (this.f758a.m916a(afVar)) {
                this.f758a.m960e();
            }
        }
    }

    class C06432 implements ah {
        final /* synthetic */ ba f759a;

        C06432(ba baVar) {
            this.f759a = baVar;
        }

        public void mo1863a(af afVar) {
            if (this.f759a.m916a(afVar)) {
                this.f759a.m921b(afVar);
            }
        }
    }

    class C06443 implements ah {
        final /* synthetic */ ba f760a;

        C06443(ba baVar) {
            this.f760a = baVar;
        }

        public void mo1863a(af afVar) {
            if (this.f760a.m916a(afVar)) {
                this.f760a.m925c(afVar);
            }
        }
    }

    class C06454 implements ah {
        final /* synthetic */ ba f761a;

        C06454(ba baVar) {
            this.f761a = baVar;
        }

        public void mo1863a(af afVar) {
            if (this.f761a.m916a(afVar)) {
                this.f761a.m961f();
            }
        }
    }

    class C06465 implements ah {
        final /* synthetic */ ba f762a;

        C06465(ba baVar) {
            this.f762a = baVar;
        }

        public void mo1863a(af afVar) {
            if (this.f762a.m916a(afVar)) {
                this.f762a.m931e(afVar);
            }
        }
    }

    class C06476 implements ah {
        final /* synthetic */ ba f763a;

        C06476(ba baVar) {
            this.f763a = baVar;
        }

        public void mo1863a(af afVar) {
            if (this.f763a.m916a(afVar)) {
                this.f763a.m928d(afVar);
            }
        }
    }

    class C06487 implements Runnable {
        final /* synthetic */ ba f764a;

        C06487(ba baVar) {
            this.f764a = baVar;
        }

        public void run() {
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (this.f764a.f786S != null) {
                JSONObject a = C0802y.m1453a();
                C0802y.m1472b(a, "id", this.f764a.f801o);
                C0802y.m1462a(a, "ad_session_id", this.f764a.f774G);
                C0802y.m1465a(a, "success", true);
                this.f764a.f786S.m694a(a).m695b();
                this.f764a.f786S = null;
            }
        }
    }

    class C06498 implements Runnable {
        final /* synthetic */ ba f765a;

        C06498(ba baVar) {
            this.f765a = baVar;
        }

        public void run() {
            this.f765a.f807u = 0;
            while (!this.f765a.f808v && !this.f765a.f811y && C0594a.m614d()) {
                if (!this.f765a.f808v && !this.f765a.f768A) {
                    if (this.f765a.f783P.isPlaying()) {
                        if (this.f765a.f807u == 0 && C0594a.f473b) {
                            this.f765a.f807u = System.currentTimeMillis();
                        }
                        this.f765a.f810x = true;
                        this.f765a.f805s = ((double) this.f765a.f783P.getCurrentPosition()) / 1000.0d;
                        this.f765a.f806t = ((double) this.f765a.f783P.getDuration()) / 1000.0d;
                        if (System.currentTimeMillis() - this.f765a.f807u > 1000 && !this.f765a.f771D && C0594a.f473b) {
                            if (this.f765a.f805s == 0.0d) {
                                new C0595a().m622a("getCurrentPosition() not working, firing AdSession.on_error").m624a(aa.f484h);
                                this.f765a.m940k();
                            } else {
                                this.f765a.f771D = true;
                            }
                        }
                        if (this.f765a.f770C) {
                            this.f765a.m958c();
                        }
                    }
                    if (!(!this.f765a.f810x || this.f765a.f808v || this.f765a.f811y)) {
                        C0802y.m1472b(this.f765a.f784Q, "id", this.f765a.f801o);
                        C0802y.m1472b(this.f765a.f784Q, "container_id", this.f765a.f777J.m1060d());
                        C0802y.m1462a(this.f765a.f784Q, "ad_session_id", this.f765a.f774G);
                        C0802y.m1460a(this.f765a.f784Q, "elapsed", this.f765a.f805s);
                        C0802y.m1460a(this.f765a.f784Q, "duration", this.f765a.f806t);
                        new af("VideoView.on_progress", this.f765a.f777J.m1057c(), this.f765a.f784Q).m695b();
                    }
                    if (this.f765a.f809w || C0594a.m613c().isFinishing()) {
                        this.f765a.f809w = false;
                        this.f765a.m959d();
                        return;
                    }
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        this.f765a.m940k();
                        new C0595a().m622a("InterruptedException in ADCVideoView's update thread.").m624a(aa.f483g);
                    }
                } else {
                    return;
                }
            }
            if (this.f765a.f809w) {
                this.f765a.m959d();
            }
        }
    }

    class C06509 implements Runnable {
        final /* synthetic */ ba f766a;

        C06509(ba baVar) {
            this.f766a = baVar;
        }

        public void run() {
            this.f766a.f781N = new C0651a(this.f766a, C0594a.m613c());
            LayoutParams layoutParams = new FrameLayout.LayoutParams((int) (this.f766a.f789c * 4.0f), (int) (this.f766a.f789c * 4.0f));
            layoutParams.setMargins(0, this.f766a.f777J.m1083p() - ((int) (this.f766a.f789c * 4.0f)), 0, 0);
            layoutParams.gravity = 0;
            this.f766a.f777J.addView(this.f766a.f781N, layoutParams);
        }
    }

    private class C0651a extends View {
        final /* synthetic */ ba f767a;

        C0651a(ba baVar, Context context) {
            this.f767a = baVar;
            super(context);
            setWillNotDraw(false);
            try {
                getClass().getMethod("setLayerType", new Class[]{Integer.TYPE, Paint.class}).invoke(this, new Object[]{Integer.valueOf(1), null});
            } catch (Exception e) {
            }
        }

        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawArc(this.f767a.f780M, BitmapDescriptorFactory.HUE_VIOLET, this.f767a.f790d, false, this.f767a.f795i);
            canvas.drawText("" + this.f767a.f793g, this.f767a.f780M.centerX(), (float) (((double) this.f767a.f780M.centerY()) + (((double) this.f767a.f796j.getFontMetrics().bottom) * 1.35d)), this.f767a.f796j);
            invalidate();
        }
    }

    ba(Context context, af afVar, int i, C0673c c0673c) {
        super(context);
        this.f777J = c0673c;
        this.f776I = afVar;
        this.f801o = i;
        setSurfaceTextureListener(this);
    }

    void m956a() {
        if (this.f779L != null) {
            this.f768A = true;
        }
        this.f785R.shutdown();
    }

    void m957b() {
        JSONObject c = this.f776I.m698c();
        this.f774G = C0802y.m1468b(c, "ad_session_id");
        this.f797k = C0802y.m1473c(c, "x");
        this.f798l = C0802y.m1473c(c, "y");
        this.f799m = C0802y.m1473c(c, "width");
        this.f800n = C0802y.m1473c(c, "height");
        this.f770C = C0802y.m1477d(c, "enable_timer");
        this.f772E = C0802y.m1477d(c, "enable_progress");
        this.f773F = C0802y.m1468b(c, "filepath");
        this.f802p = C0802y.m1473c(c, "video_width");
        this.f803q = C0802y.m1473c(c, "video_height");
        this.f792f = C0594a.m605a().f1295c.m1324o();
        new C0595a().m622a("Original video dimensions = ").m620a(this.f802p).m622a("x").m620a(this.f803q).m624a(aa.f478b);
        setVisibility(4);
        LayoutParams layoutParams = new FrameLayout.LayoutParams(this.f799m, this.f800n);
        layoutParams.setMargins(this.f797k, this.f798l, 0, 0);
        layoutParams.gravity = 0;
        this.f777J.addView(this, layoutParams);
        if (this.f772E && C0594a.m614d()) {
            this.f782O = new ProgressBar(C0594a.m613c());
            this.f777J.addView(this.f782O, new FrameLayout.LayoutParams((int) (this.f792f * 100.0f), (int) (this.f792f * 100.0f), 17));
        }
        this.f783P = new MediaPlayer();
        this.f812z = false;
        try {
            if (this.f773F.startsWith("http")) {
                this.f769B = true;
                this.f783P.setDataSource(this.f773F);
            } else {
                this.f775H = new FileInputStream(this.f773F);
                this.f783P.setDataSource(this.f775H.getFD());
            }
            this.f783P.setOnErrorListener(this);
            this.f783P.setOnPreparedListener(this);
            this.f783P.setOnCompletionListener(this);
            this.f783P.prepareAsync();
        } catch (IOException e) {
            new C0595a().m622a("Failed to create/prepare MediaPlayer: ").m622a(e.toString()).m624a(aa.f483g);
            m940k();
        }
        this.f777J.m1080n().add(C0594a.m604a("VideoView.play", new C06421(this), true));
        this.f777J.m1080n().add(C0594a.m604a("VideoView.set_bounds", new C06432(this), true));
        this.f777J.m1080n().add(C0594a.m604a("VideoView.set_visible", new C06443(this), true));
        this.f777J.m1080n().add(C0594a.m604a("VideoView.pause", new C06454(this), true));
        this.f777J.m1080n().add(C0594a.m604a("VideoView.seek_to_time", new C06465(this), true));
        this.f777J.m1080n().add(C0594a.m604a("VideoView.set_volume", new C06476(this), true));
        this.f777J.m1082o().add("VideoView.play");
        this.f777J.m1082o().add("VideoView.set_bounds");
        this.f777J.m1082o().add("VideoView.set_visible");
        this.f777J.m1082o().add("VideoView.pause");
        this.f777J.m1082o().add("VideoView.seek_to_time");
        this.f777J.m1082o().add("VideoView.set_volume");
    }

    private boolean m916a(af afVar) {
        JSONObject c = afVar.m698c();
        return C0802y.m1473c(c, "id") == this.f801o && C0802y.m1473c(c, "container_id") == this.f777J.m1060d() && C0802y.m1468b(c, "ad_session_id").equals(this.f777J.m1053b());
    }

    public void onSurfaceTextureAvailable(SurfaceTexture texture, int w, int h) {
        if (texture == null || this.f768A) {
            new C0595a().m622a("Null texture provided by system's onSurfaceTextureAvailable or MediaPlayer has been destroyed.").m624a(aa.f484h);
            return;
        }
        this.f778K = new Surface(texture);
        try {
            this.f783P.setSurface(this.f778K);
        } catch (IllegalStateException e) {
            new C0595a().m622a("IllegalStateException thrown when calling MediaPlayer.setSurface()").m624a(aa.f483g);
            m940k();
        }
        this.f779L = texture;
    }

    public void onSurfaceTextureUpdated(SurfaceTexture texture) {
        this.f779L = texture;
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture texture) {
        this.f779L = texture;
        if (!this.f768A) {
            return false;
        }
        texture.release();
        return true;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture texture, int w, int h) {
        this.f779L = texture;
    }

    public boolean onTouchEvent(MotionEvent event) {
        C0740l a = C0594a.m605a();
        C0690d m = a.m1283m();
        int action = event.getAction() & 255;
        if (action != 0 && action != 1 && action != 3 && action != 2 && action != 5 && action != 6) {
            return false;
        }
        int x = (int) event.getX();
        int y = (int) event.getY();
        JSONObject a2 = C0802y.m1453a();
        C0802y.m1472b(a2, "view_id", this.f801o);
        C0802y.m1462a(a2, "ad_session_id", this.f774G);
        C0802y.m1472b(a2, "container_x", this.f797k + x);
        C0802y.m1472b(a2, "container_y", this.f798l + y);
        C0802y.m1472b(a2, "view_x", x);
        C0802y.m1472b(a2, "view_y", y);
        C0802y.m1472b(a2, "id", this.f777J.m1060d());
        switch (action) {
            case 0:
                new af("AdContainer.on_touch_began", this.f777J.m1057c(), a2).m695b();
                break;
            case 1:
                if (!this.f777J.m1085r()) {
                    a.m1255a((bc) m.m1159f().get(this.f774G));
                }
                new af("AdContainer.on_touch_ended", this.f777J.m1057c(), a2).m695b();
                break;
            case 2:
                new af("AdContainer.on_touch_moved", this.f777J.m1057c(), a2).m695b();
                break;
            case 3:
                new af("AdContainer.on_touch_cancelled", this.f777J.m1057c(), a2).m695b();
                break;
            case 5:
                int action2 = (event.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                C0802y.m1472b(a2, "container_x", ((int) event.getX(action2)) + this.f797k);
                C0802y.m1472b(a2, "container_y", ((int) event.getY(action2)) + this.f798l);
                C0802y.m1472b(a2, "view_x", (int) event.getX(action2));
                C0802y.m1472b(a2, "view_y", (int) event.getY(action2));
                new af("AdContainer.on_touch_began", this.f777J.m1057c(), a2).m695b();
                break;
            case 6:
                action = (event.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                C0802y.m1472b(a2, "container_x", ((int) event.getX(action)) + this.f797k);
                C0802y.m1472b(a2, "container_y", ((int) event.getY(action)) + this.f798l);
                C0802y.m1472b(a2, "view_x", (int) event.getX(action));
                C0802y.m1472b(a2, "view_y", (int) event.getY(action));
                if (!this.f777J.m1085r()) {
                    a.m1255a((bc) m.m1159f().get(this.f774G));
                }
                new af("AdContainer.on_touch_ended", this.f777J.m1057c(), a2).m695b();
                break;
        }
        return true;
    }

    public void onMeasure(int width_measure_spec, int height_measure_spec) {
        double d = ((double) this.f799m) / ((double) this.f802p);
        double d2 = ((double) this.f800n) / ((double) this.f803q);
        if (d <= d2) {
            d2 = d;
        }
        int i = (int) (((double) this.f802p) * d2);
        int i2 = (int) (d2 * ((double) this.f803q));
        new C0595a().m622a("setMeasuredDimension to ").m620a(i).m622a(" by ").m620a(i2).m624a(aa.f480d);
        setMeasuredDimension(i, i2);
        if (this.f769B) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
            layoutParams.width = i;
            layoutParams.height = i2;
            layoutParams.gravity = 17;
            layoutParams.setMargins(0, 0, 0, 0);
            setLayoutParams(layoutParams);
        }
    }

    private void m940k() {
        JSONObject a = C0802y.m1453a();
        C0802y.m1462a(a, "id", this.f774G);
        new af("AdSession.on_error", this.f777J.m1057c(), a).m695b();
        this.f808v = true;
    }

    public boolean onError(MediaPlayer mp, int what, int extra) {
        m940k();
        new C0595a().m622a("MediaPlayer error: ").m620a(what).m622a(",").m620a(extra).m624a(aa.f483g);
        return true;
    }

    public void onPrepared(MediaPlayer mp) {
        this.f812z = true;
        if (this.f772E) {
            this.f777J.removeView(this.f782O);
        }
        if (this.f769B) {
            this.f802p = mp.getVideoWidth();
            this.f803q = mp.getVideoHeight();
            onMeasure(this.f802p, this.f803q);
            new C0595a().m622a("MediaPlayer getVideoWidth = ").m620a(mp.getVideoWidth()).m624a(aa.f480d);
            new C0595a().m622a("MediaPlayer getVideoHeight = ").m620a(mp.getVideoHeight()).m624a(aa.f480d);
        }
        JSONObject a = C0802y.m1453a();
        C0802y.m1472b(a, "id", this.f801o);
        C0802y.m1472b(a, "container_id", this.f777J.m1060d());
        C0802y.m1462a(a, "ad_session_id", this.f774G);
        new C0595a().m622a("ADCVideoView is prepared").m624a(aa.f478b);
        new af("VideoView.on_ready", this.f777J.m1057c(), a).m695b();
    }

    public void onCompletion(MediaPlayer mp) {
        this.f808v = true;
        this.f805s = this.f806t;
        C0802y.m1472b(this.f784Q, "id", this.f801o);
        C0802y.m1472b(this.f784Q, "container_id", this.f777J.m1060d());
        C0802y.m1462a(this.f784Q, "ad_session_id", this.f774G);
        C0802y.m1460a(this.f784Q, "elapsed", this.f805s);
        C0802y.m1460a(this.f784Q, "duration", this.f806t);
        new af("VideoView.on_progress", this.f777J.m1057c(), this.f784Q).m695b();
    }

    public void onSeekComplete(MediaPlayer mp) {
        if (this.f785R != null && !this.f785R.isShutdown()) {
            try {
                this.f785R.submit(new C06487(this));
            } catch (RejectedExecutionException e) {
                m940k();
            }
        }
    }

    private void m942l() {
        try {
            this.f785R.submit(new C06498(this));
        } catch (RejectedExecutionException e) {
            m940k();
        }
    }

    void m958c() {
        float f = 6.0f;
        float f2 = 4.0f;
        if (this.f794h) {
            this.f791e = (float) (360.0d / this.f806t);
            this.f796j.setColor(-3355444);
            this.f796j.setShadowLayer((float) ((int) (this.f792f * 2.0f)), 0.0f, 0.0f, -16777216);
            this.f796j.setTextAlign(Align.CENTER);
            this.f796j.setLinearText(true);
            this.f796j.setTextSize(12.0f * this.f792f);
            this.f795i.setStyle(Style.STROKE);
            if (this.f792f * 2.0f <= 6.0f) {
                f = this.f792f * 2.0f;
            }
            if (f >= 4.0f) {
                f2 = f;
            }
            this.f795i.setStrokeWidth(f2);
            this.f795i.setShadowLayer((float) ((int) (this.f792f * 3.0f)), 0.0f, 0.0f, -16777216);
            this.f795i.setColor(-3355444);
            Rect rect = new Rect();
            this.f796j.getTextBounds("0123456789", 0, 9, rect);
            this.f789c = (float) rect.height();
            if (C0594a.m614d()) {
                az.m880a(new C06509(this));
            }
            this.f794h = false;
        }
        this.f793g = (int) (this.f806t - this.f805s);
        this.f787a = (float) ((int) this.f789c);
        this.f788b = (float) ((int) (this.f789c * 3.0f));
        this.f780M.set(this.f787a - (this.f789c / 2.0f), this.f788b - (this.f789c * 2.0f), this.f787a + (this.f789c * 2.0f), this.f788b + (this.f789c / 2.0f));
        this.f790d = (float) (((double) this.f791e) * (this.f806t - this.f805s));
    }

    void m959d() {
        new C0595a().m622a("MediaPlayer stopped and released.").m624a(aa.f480d);
        try {
            if (!this.f808v && this.f812z && this.f783P.isPlaying()) {
                this.f783P.stop();
            }
        } catch (IllegalStateException e) {
            new C0595a().m622a("Caught IllegalStateException when calling stop on MediaPlayer").m624a(aa.f482f);
        }
        if (this.f782O != null) {
            this.f777J.removeView(this.f782O);
        }
        this.f808v = true;
        this.f812z = false;
        this.f783P.release();
    }

    private void m921b(af afVar) {
        JSONObject c = afVar.m698c();
        this.f797k = C0802y.m1473c(c, "x");
        this.f798l = C0802y.m1473c(c, "y");
        this.f799m = C0802y.m1473c(c, "width");
        this.f800n = C0802y.m1473c(c, "height");
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        layoutParams.setMargins(this.f797k, this.f798l, 0, 0);
        layoutParams.width = this.f799m;
        layoutParams.height = this.f800n;
        setLayoutParams(layoutParams);
        if (this.f770C && this.f781N != null) {
            LayoutParams layoutParams2 = new FrameLayout.LayoutParams((int) (this.f789c * 4.0f), (int) (this.f789c * 4.0f));
            layoutParams2.setMargins(0, this.f777J.m1083p() - ((int) (this.f789c * 4.0f)), 0, 0);
            layoutParams2.gravity = 0;
            this.f781N.setLayoutParams(layoutParams2);
        }
    }

    private void m925c(af afVar) {
        if (C0802y.m1477d(afVar.m698c(), "visible")) {
            setVisibility(0);
            if (this.f770C && this.f781N != null) {
                this.f781N.setVisibility(0);
                return;
            }
            return;
        }
        setVisibility(4);
        if (this.f770C && this.f781N != null) {
            this.f781N.setVisibility(4);
        }
    }

    private boolean m928d(af afVar) {
        boolean z = false;
        if (!this.f812z) {
            return false;
        }
        float e = (float) C0802y.m1478e(afVar.m698c(), "volume");
        AdColonyInterstitial u = C0594a.m605a().m1291u();
        if (u != null) {
            if (((double) e) <= 0.0d) {
                z = true;
            }
            u.m572b(z);
        }
        this.f783P.setVolume(e, e);
        JSONObject a = C0802y.m1453a();
        C0802y.m1465a(a, "success", true);
        afVar.m694a(a).m695b();
        return true;
    }

    boolean m960e() {
        if (!this.f812z) {
            return false;
        }
        if (!this.f811y && C0594a.f473b) {
            this.f783P.start();
            m942l();
            new C0595a().m622a("MediaPlayer is prepared - ADCVideoView play() called.").m624a(aa.f478b);
        } else if (!this.f808v && C0594a.f473b) {
            this.f783P.start();
            this.f811y = false;
            if (!this.f785R.isShutdown()) {
                m942l();
            }
            if (this.f781N != null) {
                this.f781N.invalidate();
            }
        }
        setWillNotDraw(false);
        return true;
    }

    boolean m961f() {
        if (!this.f812z) {
            new C0595a().m622a("ADCVideoView pause() called while MediaPlayer is not prepared.").m624a(aa.f482f);
            return false;
        } else if (this.f810x) {
            this.f804r = this.f783P.getCurrentPosition();
            this.f806t = (double) this.f783P.getDuration();
            this.f783P.pause();
            this.f811y = true;
            new C0595a().m622a("Video view paused").m624a(aa.f478b);
            return true;
        } else {
            new C0595a().m622a("Ignoring ADCVideoView pause due to invalid MediaPlayer state.").m624a(aa.f480d);
            return false;
        }
    }

    private boolean m931e(af afVar) {
        if (!this.f812z) {
            return false;
        }
        if (this.f808v) {
            this.f808v = false;
        }
        this.f786S = afVar;
        int c = C0802y.m1473c(afVar.m698c(), "time");
        int i = c * 1000;
        int duration = this.f783P.getDuration() / 1000;
        this.f783P.setOnSeekCompleteListener(this);
        this.f783P.seekTo(i);
        if (duration == c) {
            this.f808v = true;
        }
        return true;
    }

    void m962g() {
        this.f809w = true;
    }

    boolean m963h() {
        return this.f783P != null;
    }

    MediaPlayer m964i() {
        return this.f783P;
    }

    boolean m965j() {
        return this.f808v;
    }
}
