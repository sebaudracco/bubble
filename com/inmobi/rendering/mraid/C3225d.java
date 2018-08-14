package com.inmobi.rendering.mraid;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.MediaController;
import android.widget.VideoView;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.p112a.C3105a;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

@SuppressLint({"ViewConstructor"})
/* compiled from: MediaRenderView */
final class C3225d extends VideoView implements ActivityLifecycleCallbacks, OnCompletionListener, OnErrorListener, OnPreparedListener {
    private static final String f8090h = C3225d.class.getSimpleName();
    int f8091a;
    boolean f8092b;
    String f8093c;
    String f8094d;
    boolean f8095e;
    int f8096f;
    int f8097g;
    private C3224a f8098i;
    private Bitmap f8099j;
    private ViewGroup f8100k;
    private C3216b f8101l;
    private boolean f8102m = false;
    private MediaPlayer f8103n;
    private WeakReference<Activity> f8104o;

    /* compiled from: MediaRenderView */
    interface C3216b {
        void mo6265a(C3225d c3225d);

        void mo6266b(C3225d c3225d);
    }

    /* compiled from: MediaRenderView */
    class C32231 implements OnVideoSizeChangedListener {
        final /* synthetic */ C3225d f8089a;

        C32231(C3225d c3225d) {
            this.f8089a = c3225d;
        }

        public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
            Logger.m10359a(InternalLogLevel.INTERNAL, C3225d.f8090h, ">>> onVideoSizeChanged");
            if (this.f8089a.f8098i == null) {
                this.f8089a.f8098i = new C3224a(this.f8089a.getContext());
                this.f8089a.f8098i.setAnchorView(this.f8089a);
                this.f8089a.setMediaController(this.f8089a.f8098i);
                this.f8089a.requestLayout();
                this.f8089a.requestFocus();
            }
        }
    }

    /* compiled from: MediaRenderView */
    static class C3224a extends MediaController {
        public C3224a(Context context) {
            super(context);
        }

        public void show(int i) {
            super.show(i);
            if (VERSION.SDK_INT < 19) {
                try {
                    Field declaredField = MediaController.class.getDeclaredField("mAnchor");
                    declaredField.setAccessible(true);
                    View view = (View) declaredField.get(this);
                    Field declaredField2 = MediaController.class.getDeclaredField("mDecor");
                    declaredField2.setAccessible(true);
                    View view2 = (View) declaredField2.get(this);
                    Field declaredField3 = MediaController.class.getDeclaredField("mDecorLayoutParams");
                    declaredField3.setAccessible(true);
                    LayoutParams layoutParams = (LayoutParams) declaredField3.get(this);
                    Field declaredField4 = MediaController.class.getDeclaredField("mWindowManager");
                    declaredField4.setAccessible(true);
                    WindowManager windowManager = (WindowManager) declaredField4.get(this);
                    int[] iArr = new int[2];
                    view.getLocationOnScreen(iArr);
                    view2.measure(MeasureSpec.makeMeasureSpec(view.getWidth(), Integer.MIN_VALUE), MeasureSpec.makeMeasureSpec(view.getHeight(), Integer.MIN_VALUE));
                    view2.setPadding(0, 0, 0, 0);
                    layoutParams.verticalMargin = 0.0f;
                    layoutParams.horizontalMargin = 0.0f;
                    layoutParams.width = view.getWidth();
                    layoutParams.gravity = 8388659;
                    layoutParams.x = iArr[0];
                    layoutParams.y = (view.getHeight() + iArr[1]) - view2.getMeasuredHeight();
                    windowManager.updateViewLayout(view2, layoutParams);
                } catch (Exception e) {
                }
            }
        }
    }

    public C3225d(Activity activity) {
        super(activity);
        setZOrderOnTop(true);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setDrawingCacheEnabled(true);
        this.f8091a = 100;
        this.f8096f = -1;
        this.f8097g = 0;
        this.f8092b = false;
        this.f8104o = new WeakReference(activity);
        activity.getApplication().registerActivityLifecycleCallbacks(this);
    }

    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        Logger.m10359a(InternalLogLevel.INTERNAL, f8090h, ">>> onWindowVisibilityChanged (" + i + ")");
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        getHolder().setSizeFromLayout();
    }

    @TargetApi(16)
    protected void onVisibilityChanged(@NonNull View view, int i) {
        super.onVisibilityChanged(view, i);
        Logger.m10359a(InternalLogLevel.INTERNAL, f8090h, ">>> onVisibilityChanged (" + i + ")");
        if (i != 0) {
            return;
        }
        if (VERSION.SDK_INT >= 16) {
            Context b = C3105a.m10078b();
            if (b != null) {
                setBackground(new BitmapDrawable(b.getResources(), this.f8099j));
                return;
            }
            return;
        }
        setBackgroundDrawable(new BitmapDrawable(this.f8099j));
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        Logger.m10359a(InternalLogLevel.INTERNAL, f8090h, ">>> onCompletion");
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        Logger.m10359a(InternalLogLevel.INTERNAL, f8090h, ">>> onError (" + i + ", " + i2 + ")");
        m10768b();
        return false;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        Logger.m10359a(InternalLogLevel.INTERNAL, f8090h, ">>> onPrepared");
        this.f8103n = mediaPlayer;
        mediaPlayer.setOnVideoSizeChangedListener(new C32231(this));
        m10764a(this.f8097g);
        this.f8095e = true;
        this.f8101l.mo6266b(this);
        start();
    }

    public void m10767a(String str) {
        this.f8094d = m10759b(str);
        this.f8093c = "anonymous";
        if (this.f8099j == null) {
            this.f8099j = Bitmap.createBitmap(24, 24, Config.ARGB_8888);
            this.f8099j = m10760c(this.f8094d);
        }
    }

    public void start() {
        if (!this.f8102m) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f8090h, "Start media playback");
            super.start();
        }
    }

    public void pause() {
        Logger.m10359a(InternalLogLevel.INTERNAL, f8090h, "Pause media playback");
        super.pause();
    }

    public void m10763a() {
        setVideoPath(this.f8094d);
        setOnCompletionListener(this);
        setOnPreparedListener(this);
        setOnErrorListener(this);
        if (this.f8098i == null && VERSION.SDK_INT >= 19) {
            this.f8098i = new C3224a(getContext());
            this.f8098i.setAnchorView(this);
            setMediaController(this.f8098i);
        }
    }

    public void m10764a(int i) {
        if (i < getDuration()) {
            this.f8097g = i;
            seekTo(i);
        }
    }

    public void m10768b() {
        Logger.m10359a(InternalLogLevel.INTERNAL, f8090h, "Release the media render view");
        stopPlayback();
        m10762e();
        super.setMediaController(null);
        this.f8098i = null;
        if (this.f8101l != null) {
            this.f8101l.mo6265a(this);
        }
    }

    public ViewGroup m10769c() {
        return this.f8100k;
    }

    public void m10765a(ViewGroup viewGroup) {
        this.f8100k = viewGroup;
    }

    public void m10766a(C3216b c3216b) {
        this.f8101l = c3216b;
    }

    private void m10762e() {
        if (this.f8100k != null) {
            ViewGroup viewGroup = (ViewGroup) this.f8100k.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(this.f8100k);
            }
            viewGroup = (ViewGroup) getParent();
            if (viewGroup != null) {
                viewGroup.removeView(this);
            }
            setBackgroundColor(0);
            this.f8100k = null;
        }
    }

    private String m10759b(String str) {
        String str2 = "";
        byte[] bytes = str.getBytes();
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            if ((b & 128) > 0) {
                stringBuilder.append("%").append(m10758a(b));
            } else {
                stringBuilder.append((char) b);
            }
        }
        try {
            return new String(stringBuilder.toString().getBytes(), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            return str2;
        }
    }

    private Bitmap m10760c(String str) {
        try {
            return (Bitmap) Class.forName("android.media.ThumbnailUtils").getDeclaredMethod("createVideoThumbnail", new Class[]{String.class, Integer.TYPE}).invoke(null, new Object[]{str, Integer.valueOf(1)});
        } catch (ClassNotFoundException e) {
            return null;
        } catch (InvocationTargetException e2) {
            return null;
        } catch (NoSuchMethodException e3) {
            return null;
        } catch (IllegalAccessException e4) {
            return null;
        }
    }

    private String m10758a(byte b) {
        char[] cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        return new String(new char[]{cArr[(b >> 4) & 15], cArr[b & 15]});
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
        if (this.f8104o.get() != null && ((Activity) this.f8104o.get()).equals(activity)) {
            this.f8102m = false;
            start();
        }
    }

    public void onActivityResumed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
        Activity activity2 = (Activity) this.f8104o.get();
        if (activity2 != null && activity2.equals(activity)) {
            this.f8102m = true;
            if (getCurrentPosition() != 0) {
                this.f8097g = getCurrentPosition();
            }
            pause();
        }
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
        activity.getApplication().unregisterActivityLifecycleCallbacks(this);
    }
}
