package com.oneaudience.sdk;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Path.FillType;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.StateSet;
import android.util.TypedValue;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.webkit.URLUtil;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.cuebiq.cuebiqsdk.model.config.Settings;
import com.facebook.ads.AudienceNetworkActivity;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.mopub.mobileads.resource.DrawableConstants.CloseButton;
import com.mopub.mobileads.resource.DrawableConstants.CtaButton;
import com.oneaudience.sdk.p135c.C3828b;
import com.oneaudience.sdk.p135c.C3833d;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

class C3875l extends RelativeLayout {
    private static final String f9270a = C3875l.class.getSimpleName();
    private static final int f9271b = Color.parseColor("#323232");
    private final long f9272c;
    private final long f9273d;
    private final C3872f f9274e;
    private long f9275f;
    private Context f9276g;
    private final int f9277h;
    private C3867a f9278i;
    private ProgressBar f9279j;
    private C3849h f9280k;
    private boolean f9281l = false;
    private boolean f9282m = false;
    private boolean f9283n = true;
    private boolean f9284o = false;
    private final Handler f9285p = new Handler();
    private final Handler f9286q = new Handler();
    private final Handler f9287r = new Handler();
    private final AlphaAnimation f9288s = new AlphaAnimation(1.0f, 0.0f);
    private final AlphaAnimation f9289t = new AlphaAnimation(0.0f, 1.0f);

    interface C3849h {
        void mo6809a(C3875l c3875l);

        void mo6810a(C3875l c3875l, boolean z);

        void mo6811b(C3875l c3875l);
    }

    class C38621 implements Runnable {
        final /* synthetic */ C3875l f9255a;

        C38621(C3875l c3875l) {
            this.f9255a = c3875l;
        }

        public void run() {
            try {
                this.f9255a.f9285p.removeCallbacksAndMessages(null);
                this.f9255a.f9284o = true;
                this.f9255a.m12362e();
            } catch (Throwable e) {
                C3833d.m12250b(C3875l.f9270a, "Error in webview dismiss", e);
            }
        }
    }

    class C38632 implements AnimationListener {
        final /* synthetic */ C3875l f9256a;

        C38632(C3875l c3875l) {
            this.f9256a = c3875l;
        }

        public void onAnimationEnd(Animation animation) {
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
            this.f9256a.f9274e.setVisibility(0);
        }
    }

    class C38643 implements AnimationListener {
        final /* synthetic */ C3875l f9257a;

        C38643(C3875l c3875l) {
            this.f9257a = c3875l;
        }

        public void onAnimationEnd(Animation animation) {
            this.f9257a.f9279j.setVisibility(4);
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }
    }

    class C38654 implements OnClickListener {
        final /* synthetic */ C3875l f9258a;

        C38654(C3875l c3875l) {
            this.f9258a = c3875l;
        }

        public void onClick(View view) {
            if (this.f9258a.f9280k != null) {
                this.f9258a.f9280k.mo6810a(this.f9258a, false);
            }
        }
    }

    class C38665 implements Runnable {
        final /* synthetic */ C3875l f9259a;

        C38665(C3875l c3875l) {
            this.f9259a = c3875l;
        }

        public void run() {
            try {
                if (!this.f9259a.m12378b() && this.f9259a.f9280k != null) {
                    this.f9259a.f9280k.mo6811b(this.f9259a);
                }
            } catch (Throwable e) {
                C3833d.m12250b(C3875l.f9270a, "Error here", e);
            }
        }
    }

    private final class C3867a extends WebView {
        final /* synthetic */ C3875l f9260a;
        private float f9261b;
        private int f9262c;
        private int f9263d;

        public C3867a(C3875l c3875l, Context context) {
            this.f9260a = c3875l;
            super(context);
            this.f9261b = TypedValue.applyDimension(1, CtaButton.TEXT_SIZE_SP, context.getResources().getDisplayMetrics());
        }

        private void m12340a(View view, boolean z) {
            int i = view.getVisibility() == 0 ? 1 : 0;
            if (i != 0 && !z) {
                view.setVisibility(4);
            } else if (i == 0 && z) {
                view.setVisibility(0);
            }
        }

        private Paint m12341b() {
            Paint paint = new Paint();
            paint.setColor(0);
            paint.setStyle(Style.FILL);
            paint.setAntiAlias(true);
            paint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
            return paint;
        }

        void m12342a() {
            View c = this.f9260a.f9274e;
            boolean z = this.f9260a.f9283n && getScrollY() <= 0;
            m12340a(c, z);
        }

        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Path path = new Path();
            path.setFillType(FillType.INVERSE_WINDING);
            path.addRoundRect(new RectF(0.0f, (float) getScrollY(), (float) this.f9262c, (float) (getScrollY() + this.f9263d)), this.f9261b, this.f9261b, Direction.CW);
            canvas.drawPath(path, m12341b());
        }

        protected void onScrollChanged(int i, int i2, int i3, int i4) {
            super.onScrollChanged(i, i2, i3, i4);
            m12342a();
        }

        protected void onSizeChanged(int i, int i2, int i3, int i4) {
            super.onSizeChanged(i, i2, i3, i4);
            this.f9262c = i;
            this.f9263d = i2;
        }
    }

    private final class C3868b extends WebViewClient {
        final /* synthetic */ C3875l f9264a;

        private C3868b(C3875l c3875l) {
            this.f9264a = c3875l;
        }

        public void onPageFinished(WebView webView, String str) {
            C3833d.m12253c(C3875l.f9270a, URLUtil.isValidUrl(str) ? "Finish loading page: %s" : "Finish loading page.", str);
            this.f9264a.f9281l = true;
            this.f9264a.m12348a(this.f9264a.f9273d);
            this.f9264a.f9278i.setVisibility(0);
            this.f9264a.m12379c();
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            C3833d.m12253c(C3875l.f9270a, URLUtil.isValidUrl(str) ? "Start loading page: %s" : "Start loading page.", str);
            if (this.f9264a.f9275f > 0) {
                this.f9264a.m12353b(this.f9264a.f9275f);
            }
            this.f9264a.f9281l = false;
            this.f9264a.f9279j.setVisibility(0);
            if (this.f9264a.f9280k != null) {
                this.f9264a.f9280k.mo6809a(this.f9264a);
            }
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            C3833d.m12255d(C3875l.f9270a, URLUtil.isValidUrl(str2) ? "Failed loading page: %s" : "Failed loading page.", str2);
            this.f9264a.f9281l = false;
            if (this.f9264a.f9280k != null) {
                this.f9264a.f9280k.mo6811b(this.f9264a);
            }
        }

        public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
            if (VERSION.SDK_INT < 11) {
                C3833d.m12252c(C3875l.f9270a, "NOT INTERCEPTING");
                return super.shouldInterceptRequest(webView, str);
            } else if (str.contains(".css") || str.contains(".js") || str.contains(".png") || str.contains(".jpg") || str.contains(".bmp")) {
                String substring = str.substring(str.lastIndexOf(47) + 1);
                C3833d.m12253c(C3875l.f9270a, "Intercepting: %s , %s", str, substring);
                String str2 = this.f9264a.f9276g.getCacheDir().getAbsolutePath() + BridgeUtil.SPLIT_MARK + substring;
                String substring2 = substring.substring(substring.lastIndexOf(46) + 1);
                if (new File(str2).exists()) {
                    C3833d.m12253c(C3875l.f9270a, "shouldInterceptRequest: %s, CACHE FOUND!", substring);
                    String str3 = "";
                    if (substring2.equals("bmp") || substring2.equals("jpg") || substring2.equals("png")) {
                        str3 = "image/*";
                    }
                    if (substring2.equals("js")) {
                        str3 = "text/javascript";
                    }
                    if (substring2.equals("js")) {
                        str3 = "text/javascript";
                    }
                    if (substring2.equals(C1404b.aJ)) {
                        str3 = "text/css";
                    }
                    if (str3.isEmpty()) {
                        C3833d.m12257e(C3875l.f9270a, "shouldInterceptRequest: %s, ext: %s UNKNOWN MIME", substring, substring2);
                        return super.shouldInterceptRequest(webView, str);
                    }
                    try {
                        return new WebResourceResponse(str3, "UTF-8", new FileInputStream(str2));
                    } catch (FileNotFoundException e) {
                        C3833d.m12257e(C3875l.f9270a, "shouldInterceptRequest: %s, Couldn't create file stream", substring);
                        return super.shouldInterceptRequest(webView, str);
                    }
                }
                C3833d.m12257e(C3875l.f9270a, "shouldInterceptRequest: %s, NO CACHE FOUND", substring);
                return super.shouldInterceptRequest(webView, str);
            } else {
                C3833d.m12253c(C3875l.f9270a, "shouldInterceptRequest: %s", str);
                return super.shouldInterceptRequest(webView, str);
            }
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (!TextUtils.isEmpty(str)) {
                C3833d.m12253c(C3875l.f9270a, "Overriding url %s", str);
                if (str.contains(C3839d.f9193b)) {
                    this.f9264a.m12351a(true);
                    return true;
                } else if (str.contains(C3839d.f9194c)) {
                    this.f9264a.m12351a(false);
                    return true;
                }
            }
            return false;
        }
    }

    private interface C3869e {
    }

    private static class C3870c extends GradientDrawable implements C3869e {
        C3870c(int i, int i2) {
            this(Orientation.TOP_BOTTOM, new int[]{i, i2});
        }

        C3870c(Orientation orientation, int[] iArr) {
            super(orientation, iArr);
            setShape(1);
        }
    }

    private static class C3871d extends ImageView {
        public C3871d(Context context) {
            this(context, null);
        }

        public C3871d(Context context, C3869e c3869e) {
            this(context, c3869e, null);
        }

        public C3871d(Context context, C3869e c3869e, Drawable drawable) {
            super(context);
            if (c3869e != null) {
                m12344a((Drawable) c3869e);
            }
            if (drawable != null) {
                setImageDrawable(drawable);
            }
        }

        private int m12343a(int i) {
            int mode = MeasureSpec.getMode(i);
            int size = MeasureSpec.getSize(i);
            switch (mode) {
                case Integer.MIN_VALUE:
                case 0:
                    return m12345b();
                default:
                    return size;
            }
        }

        @TargetApi(16)
        private void m12344a(Drawable drawable) {
            if (VERSION.SDK_INT >= 16) {
                setBackground(drawable);
            } else {
                setBackgroundDrawable(drawable);
            }
        }

        private int m12345b() {
            return (int) TypedValue.applyDimension(1, CloseButton.TEXT_SIZE_SP, getResources().getDisplayMetrics());
        }

        @TargetApi(11)
        void m12346a() {
            if (VERSION.SDK_INT >= 11) {
                setLayerType(1, null);
            }
        }

        protected void onMeasure(int i, int i2) {
            int min = Math.min((m12343a(i2) + getPaddingLeft()) + getPaddingRight(), (m12343a(i) + getPaddingTop()) + getPaddingBottom());
            setMeasuredDimension(min, min);
        }
    }

    private static class C3872f extends C3871d {
        private static final int f9265a = Color.parseColor("#8D8E91");
        private static final int f9266b = Color.parseColor("#646567");
        private static final int f9267c = Color.parseColor("#5A5A5D");
        private static final int f9268d = Color.parseColor("#2F3031");

        public C3872f(Context context) {
            super(context);
            setImageDrawable(new LayerDrawable(new Drawable[]{new C3874i(new C3870c(f9265a, f9266b), new C3870c(f9267c, f9268d)), new C3873g()}));
            m12346a();
        }
    }

    private static class C3873g extends Drawable {
        private final Paint f9269a = new Paint(1);

        C3873g() {
            this.f9269a.setStyle(Style.STROKE);
            this.f9269a.setColor(-1);
            this.f9269a.setStrokeJoin(Join.ROUND);
            this.f9269a.setStrokeCap(Cap.ROUND);
        }

        public void draw(Canvas canvas) {
            Rect bounds = getBounds();
            this.f9269a.setStrokeWidth(((float) bounds.width()) / Settings.LOCATION_REQUEST_SMALLEST_DISPLACEMENT);
            float height = (float) (bounds.height() / 2);
            float width = ((float) bounds.width()) / 4.0f;
            float width2 = ((float) bounds.width()) - width;
            canvas.save();
            canvas.rotate(45.0f, height, height);
            canvas.drawLine(width, height, width2, height, this.f9269a);
            canvas.rotate(90.0f, height, height);
            canvas.drawLine(width, height, width2, height, this.f9269a);
            canvas.restore();
        }

        public int getOpacity() {
            return -2;
        }

        public void setAlpha(int i) {
            this.f9269a.setAlpha(i);
        }

        public void setColorFilter(ColorFilter colorFilter) {
            this.f9269a.setColorFilter(colorFilter);
        }
    }

    private static class C3874i extends StateListDrawable implements C3869e {
        C3874i(C3870c c3870c, C3870c c3870c2) {
            addState(new int[]{16842919}, c3870c2);
            addState(StateSet.WILD_CARD, c3870c);
        }
    }

    public C3875l(Context context, long j, long j2) {
        super(context);
        this.f9276g = context;
        this.f9275f = j2;
        this.f9272c = j;
        this.f9273d = this.f9272c;
        m12363f();
        this.f9277h = (int) TypedValue.applyDimension(1, 4.0f, getResources().getDisplayMetrics());
        this.f9278i = m12366g();
        this.f9279j = m12369i();
        this.f9274e = m12368h();
        this.f9278i.setVisibility(4);
        this.f9274e.setVisibility(4);
        addView(this.f9278i, m12373k());
        addView(this.f9279j, m12374l());
        addView(this.f9274e, m12372j());
    }

    private void m12348a(long j) {
        C3833d.m12253c(f9270a, "Showing Dismiss X in %d", Long.valueOf(j));
        if (this.f9274e.getVisibility() != 0 && this.f9283n) {
            if (this.f9284o) {
                m12362e();
            } else {
                this.f9285p.postDelayed(new C38621(this), j);
            }
        }
    }

    @TargetApi(11)
    private void m12349a(View view) {
        if (VERSION.SDK_INT >= 11) {
            view.setLayerType(1, null);
        }
    }

    private void m12351a(boolean z) {
        if (this.f9280k != null) {
            this.f9280k.mo6810a(this, z);
        }
    }

    private void m12353b(long j) {
        this.f9287r.postDelayed(new C38665(this), j);
    }

    private void m12362e() {
        if (this.f9278i.getScrollY() != 0) {
            this.f9278i.m12342a();
        } else if (this.f9274e.getVisibility() != 0) {
            invalidate();
            this.f9274e.setAnimation(this.f9289t);
        }
    }

    private void m12363f() {
        this.f9289t.setDuration(300);
        this.f9289t.setInterpolator(new AccelerateInterpolator());
        this.f9289t.setAnimationListener(new C38632(this));
        this.f9288s.setDuration(300);
        this.f9288s.setInterpolator(new AccelerateInterpolator());
        this.f9288s.setAnimationListener(new C38643(this));
    }

    private C3867a m12366g() {
        C3867a c3867a = new C3867a(this, this.f9276g);
        c3867a.setWebViewClient(new C3868b());
        WebSettings settings = c3867a.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(false);
        settings.setSupportZoom(false);
        settings.setCacheMode(-1);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAllowFileAccess(true);
        c3867a.setVerticalScrollBarEnabled(false);
        c3867a.setHorizontalScrollBarEnabled(false);
        return c3867a;
    }

    private C3872f m12368h() {
        C3872f c3872f = new C3872f(this.f9276g);
        c3872f.setPadding(this.f9277h, this.f9277h, this.f9277h, this.f9277h);
        c3872f.setOnClickListener(new C38654(this));
        return c3872f;
    }

    private ProgressBar m12369i() {
        View progressBar = new ProgressBar(this.f9276g, null, 16842871);
        progressBar.setIndeterminate(true);
        m12349a(progressBar);
        return progressBar;
    }

    private LayoutParams m12372j() {
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.addRule(11);
        layoutParams.addRule(10);
        int applyDimension = (int) TypedValue.applyDimension(1, 5.0f, getResources().getDisplayMetrics());
        layoutParams.setMargins(0, applyDimension, applyDimension, 0);
        return layoutParams;
    }

    private LayoutParams m12373k() {
        return new LayoutParams(-1, -1);
    }

    private LayoutParams m12374l() {
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.addRule(14);
        layoutParams.addRule(15);
        return layoutParams;
    }

    public void m12375a() {
        if (this.f9278i.canGoBack()) {
            this.f9278i.goBack();
        }
    }

    public final void m12376a(C3849h c3849h) {
        this.f9280k = c3849h;
    }

    public final void m12377a(String str, String str2) {
        if (str == null) {
            this.f9278i.loadUrl(str2);
            return;
        }
        try {
            String a = C3828b.m12237a(new File(str));
            String str3 = AudienceNetworkActivity.WEBVIEW_MIME_TYPE;
            String str4 = "UTF-8";
            C3833d.m12257e(f9270a, "LOADING BASE: %s", str2);
            if (a == null || a.isEmpty()) {
                this.f9278i.loadUrl(str2);
            } else {
                this.f9278i.loadDataWithBaseURL(str2, a, str3, str4, str2);
            }
        } catch (IOException e) {
            C3833d.m12256e(f9270a, "Error loading HTML from file: " + str2);
            this.f9278i.loadUrl(str2);
        }
    }

    public final boolean m12378b() {
        return this.f9281l;
    }

    public void m12379c() {
        if (this.f9279j.getVisibility() != 4) {
            this.f9279j.startAnimation(this.f9288s);
        }
    }

    protected void onAttachedToWindow() {
        C3833d.m12249b(f9270a, "#### onAttachedToWindow");
        super.onAttachedToWindow();
    }

    protected void onDetachedFromWindow() {
        C3833d.m12249b(f9270a, "#### onDetachedFromWindow");
        this.f9285p.removeCallbacksAndMessages(null);
        this.f9286q.removeCallbacksAndMessages(null);
        super.onDetachedFromWindow();
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        C3833d.m12249b(f9270a, "#### onRestoreInstanceState");
        if (parcelable != null && (parcelable instanceof Bundle)) {
            Bundle bundle = (Bundle) parcelable;
            this.f9284o = bundle.getBoolean("instance_state_dismiss_animated", false);
            parcelable = bundle.getParcelable("popupadview_instance_state");
        }
        super.onRestoreInstanceState(parcelable);
    }

    protected Parcelable onSaveInstanceState() {
        C3833d.m12249b(f9270a, "#### onSaveInstanceState");
        Parcelable bundle = new Bundle();
        bundle.putParcelable("popupadview_instance_state", super.onSaveInstanceState());
        bundle.putBoolean("instance_state_dismiss_animated", this.f9284o);
        return bundle;
    }
}
