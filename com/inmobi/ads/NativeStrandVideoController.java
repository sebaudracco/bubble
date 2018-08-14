package com.inmobi.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.LayerDrawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.inmobi.commons.core.p115d.C3132b;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.DisplayInfo;
import com.inmobi.rendering.CustomView;
import com.inmobi.rendering.CustomView.SwitchIconType;
import java.lang.ref.WeakReference;

@TargetApi(15)
public class NativeStrandVideoController extends FrameLayout {
    private static final String f6803a = NativeStrandVideoController.class.getSimpleName();
    private au f6804b;
    private C2938a f6805c;
    private NativeStrandVideoView f6806d;
    private boolean f6807e;
    private CustomView f6808f;
    private CustomView f6809g;
    private ProgressBar f6810h;
    private RelativeLayout f6811i;
    private boolean f6812j;
    private float f6813k;
    private final OnClickListener f6814l;

    class C29371 implements OnClickListener {
        final /* synthetic */ NativeStrandVideoController f6801a;

        C29371(NativeStrandVideoController nativeStrandVideoController) {
            this.f6801a = nativeStrandVideoController;
        }

        public void onClick(View view) {
            this.f6801a.m8878m();
        }
    }

    static final class C2938a extends Handler {
        @NonNull
        private final WeakReference<NativeStrandVideoController> f6802a;

        public C2938a(@NonNull NativeStrandVideoController nativeStrandVideoController) {
            this.f6802a = new WeakReference(nativeStrandVideoController);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 2:
                    NativeStrandVideoController nativeStrandVideoController = (NativeStrandVideoController) this.f6802a.get();
                    if (nativeStrandVideoController != null) {
                        int a = nativeStrandVideoController.m8875j();
                        if (nativeStrandVideoController.m8881b() && nativeStrandVideoController.f6806d.isPlaying()) {
                            sendMessageDelayed(obtainMessage(2), (long) (1000 - (a % 1000)));
                            return;
                        }
                        return;
                    }
                    return;
                default:
                    super.handleMessage(message);
                    return;
            }
        }
    }

    public NativeStrandVideoController(Context context) {
        this(context, null);
    }

    public NativeStrandVideoController(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NativeStrandVideoController(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f6812j = false;
        this.f6814l = new C29371(this);
        m8870e();
        m8871f();
    }

    private void m8870e() {
        this.f6811i = new RelativeLayout(getContext());
        addView(this.f6811i, new LayoutParams(-1, -1));
        this.f6811i.setPadding(0, 0, 0, 0);
    }

    private void m8871f() {
        if (this.f6811i != null) {
            this.f6813k = DisplayInfo.m10420a().m10440c();
            this.f6808f = new CustomView(getContext(), this.f6813k, SwitchIconType.MUTE_BUTTON);
            this.f6809g = new CustomView(getContext(), this.f6813k, SwitchIconType.UNMUTE_BUTTON);
            this.f6810h = new ProgressBar(getContext(), null, 16842872);
            m8872g();
            m8874i();
        }
        this.f6805c = new C2938a(this);
    }

    public void setMediaPlayer(@NonNull NativeStrandVideoView nativeStrandVideoView) {
        this.f6806d = nativeStrandVideoView;
        m8876k();
    }

    private void m8872g() {
        ViewGroup.LayoutParams layoutParams = new LayoutParams((int) (this.f6813k * 50.0f), (int) (this.f6813k * 50.0f));
        layoutParams.addRule(9, -1);
        layoutParams.addRule(12, -1);
        layoutParams.setMargins(0, 0, 0, (int) (5.0f * this.f6813k));
        this.f6811i.addView(this.f6808f, layoutParams);
        this.f6808f.setOnClickListener(this.f6814l);
    }

    private void m8873h() {
        ViewGroup.LayoutParams layoutParams = new LayoutParams((int) (this.f6813k * 50.0f), (int) (this.f6813k * 50.0f));
        layoutParams.addRule(9, -1);
        layoutParams.addRule(12, -1);
        layoutParams.setMargins(0, 0, 0, (int) (5.0f * this.f6813k));
        this.f6811i.addView(this.f6809g, layoutParams);
        this.f6809g.setOnClickListener(this.f6814l);
    }

    private void m8874i() {
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.addRule(12, -1);
        float c = DisplayInfo.m10420a().m10440c();
        layoutParams.setMargins(0, (int) (-6.0f * c), 0, (int) (c * -6.0f));
        LayerDrawable layerDrawable = (LayerDrawable) this.f6810h.getProgressDrawable();
        if (layerDrawable != null) {
            layerDrawable.getDrawable(0).setColorFilter(Color.parseColor("#FFFFFFFF"), Mode.SRC_IN);
            layerDrawable.getDrawable(2).setColorFilter(Color.parseColor("#FFFB0006"), Mode.SRC_IN);
        }
        this.f6811i.addView(this.f6810h, layoutParams);
    }

    public void m8879a() {
        m8880a(1500);
    }

    public void m8880a(int i) {
        int i2 = 4;
        if (!this.f6807e) {
            m8875j();
            this.f6807e = true;
            aw awVar = (aw) this.f6806d.getTag();
            if (awVar != null) {
                this.f6808f.setVisibility(awVar.m9438A() ? 0 : 4);
                ProgressBar progressBar = this.f6810h;
                if (awVar.m9440C()) {
                    i2 = 0;
                }
                progressBar.setVisibility(i2);
            }
            setVisibility(0);
        }
        this.f6805c.sendEmptyMessage(2);
    }

    public boolean m8881b() {
        return this.f6807e;
    }

    public void m8882c() {
        if (this.f6807e) {
            try {
                this.f6805c.removeMessages(2);
                setVisibility(8);
            } catch (Throwable e) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f6803a, "MediaController already removed");
                C3135c.m10255a().m10279a(new C3132b(e));
            }
            this.f6807e = false;
        }
    }

    @TargetApi(15)
    private int m8875j() {
        if (VERSION.SDK_INT < 15 || this.f6806d == null) {
            return 0;
        }
        int currentPosition = this.f6806d.getCurrentPosition();
        int duration = this.f6806d.getDuration();
        if (this.f6810h == null || duration == 0) {
            return currentPosition;
        }
        this.f6810h.setProgress((currentPosition * 100) / duration);
        return currentPosition;
    }

    @TargetApi(15)
    public boolean onTrackballEvent(MotionEvent motionEvent) {
        if (VERSION.SDK_INT >= 15 && this.f6806d != null && this.f6806d.m8913b()) {
            m8883d();
        }
        return false;
    }

    @TargetApi(15)
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        boolean z = keyEvent.getRepeatCount() == 0 && keyEvent.getAction() == 0;
        if (VERSION.SDK_INT >= 15) {
            if (keyCode == 79 || keyCode == 85 || keyCode == 62) {
                if (!z) {
                    return true;
                }
                m8877l();
                m8880a(1500);
                return true;
            } else if (keyCode == 126) {
                if (!z || this.f6806d.isPlaying()) {
                    return true;
                }
                this.f6806d.start();
                m8880a(1500);
                return true;
            } else if (keyCode == 86 || keyCode == 127) {
                if (!z || !this.f6806d.isPlaying()) {
                    return true;
                }
                this.f6806d.pause();
                m8880a(1500);
                return true;
            } else if (keyCode == 25 || keyCode == 24 || keyCode == 164 || keyCode == 27) {
                return super.dispatchKeyEvent(keyEvent);
            } else {
                m8880a(1500);
            }
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    private void m8876k() {
        aw awVar = (aw) this.f6806d.getTag();
        if (awVar != null && awVar.m9438A() && !awVar.m9448z()) {
            this.f6812j = true;
            this.f6811i.removeView(this.f6809g);
            this.f6811i.removeView(this.f6808f);
            m8873h();
        }
    }

    private void m8877l() {
        if (this.f6806d.isPlaying()) {
            this.f6806d.pause();
        } else {
            this.f6806d.start();
        }
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(NativeStrandVideoController.class.getName());
    }

    @TargetApi(15)
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (VERSION.SDK_INT >= 15) {
            accessibilityNodeInfo.setClassName(NativeStrandVideoController.class.getName());
        }
    }

    private void m8878m() {
        if (this.f6806d != null) {
            this.f6806d.getMediaPlayer();
            aw awVar = (aw) this.f6806d.getTag();
            if (this.f6812j) {
                this.f6806d.m8916e();
                this.f6812j = false;
                this.f6811i.removeView(this.f6809g);
                this.f6811i.removeView(this.f6808f);
                m8872g();
                if (awVar != null && this.f6804b != null) {
                    try {
                        this.f6804b.m9430f(awVar);
                        awVar.m9445b(true);
                        return;
                    } catch (Throwable e) {
                        Logger.m10359a(InternalLogLevel.INTERNAL, f6803a, "SDK encountered unexpected error in handling the onVideoUnMuted event; " + e.getMessage());
                        C3135c.m10255a().m10279a(new C3132b(e));
                        return;
                    }
                }
                return;
            }
            this.f6806d.m8915d();
            this.f6812j = true;
            this.f6811i.removeView(this.f6808f);
            this.f6811i.removeView(this.f6809g);
            m8873h();
            if (awVar != null && this.f6804b != null) {
                try {
                    this.f6804b.m9429e(awVar);
                    awVar.m9445b(false);
                } catch (Throwable e2) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, f6803a, "SDK encountered unexpected error in handling the onVideoMuted event; " + e2.getMessage());
                    C3135c.m10255a().m10279a(new C3132b(e2));
                }
            }
        }
    }

    void m8883d() {
        if (m8881b()) {
            m8882c();
        } else {
            m8879a();
        }
    }

    public void setVideoAd(au auVar) {
        this.f6804b = auVar;
    }
}
