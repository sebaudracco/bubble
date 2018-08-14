package com.inmobi.rendering.mraid;

import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.GravityCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.inmobi.commons.core.utilities.info.DisplayInfo;
import com.inmobi.rendering.CustomView;
import com.inmobi.rendering.CustomView.SwitchIconType;
import com.inmobi.rendering.RenderView;

/* compiled from: MraidResizeProcessor */
public final class C3231h {
    private static final String f8118a = C3231h.class.getSimpleName();
    private RenderView f8119b;
    private ViewGroup f8120c;
    private int f8121d;

    /* compiled from: MraidResizeProcessor */
    class C32301 implements OnClickListener {
        final /* synthetic */ C3231h f8117a;

        C32301(C3231h c3231h) {
            this.f8117a = c3231h;
        }

        public void onClick(View view) {
            this.f8117a.f8119b.mo6175b();
        }
    }

    public C3231h(RenderView renderView) {
        this.f8119b = renderView;
    }

    public void m10790a() {
        if (this.f8120c == null) {
            this.f8120c = (ViewGroup) this.f8119b.getParent();
            this.f8121d = this.f8120c.indexOfChild(this.f8119b);
        }
        C3233j resizeProperties = this.f8119b.getResizeProperties();
        m10789c();
        m10788a(resizeProperties);
    }

    private void m10789c() {
        View frameLayout = new FrameLayout(this.f8119b.getRenderViewContext());
        LayoutParams layoutParams = new LayoutParams(this.f8119b.getWidth(), this.f8119b.getHeight());
        frameLayout.setId(SupportMenu.USER_MASK);
        this.f8120c.addView(frameLayout, this.f8121d, layoutParams);
        this.f8120c.removeView(this.f8119b);
    }

    private void m10788a(C3233j c3233j) {
        float c = DisplayInfo.m10420a().m10440c();
        int i = (int) ((((float) c3233j.f8129b) * c) + 0.5f);
        int i2 = (int) ((c * ((float) c3233j.f8130c)) + 0.5f);
        FrameLayout frameLayout = (FrameLayout) this.f8120c.getRootView().findViewById(16908290);
        View frameLayout2 = new FrameLayout(this.f8119b.getRenderViewContext());
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        ViewGroup relativeLayout = new RelativeLayout(this.f8119b.getRenderViewContext());
        LayoutParams layoutParams2 = new FrameLayout.LayoutParams(i, i2);
        LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(i, i2);
        frameLayout2.setId(65534);
        if (this.f8119b.getParent() != null) {
            ((ViewGroup) this.f8119b.getParent()).removeAllViews();
        }
        relativeLayout.addView(this.f8119b, layoutParams3);
        m10786a(relativeLayout, c3233j.f8128a);
        frameLayout2.addView(relativeLayout, layoutParams2);
        frameLayout.addView(frameLayout2, layoutParams);
        m10787a(frameLayout, frameLayout2, c3233j);
        frameLayout2.setBackgroundColor(0);
    }

    private void m10786a(ViewGroup viewGroup, String str) {
        float c = DisplayInfo.m10420a().m10440c();
        View customView = new CustomView(this.f8119b.getRenderViewContext(), c, SwitchIconType.CLOSE_TRANSPARENT);
        customView.setId(65531);
        customView.setOnClickListener(new C32301(this));
        viewGroup.addView(customView, m10783a(str, c));
    }

    private RelativeLayout.LayoutParams m10783a(String str, float f) {
        String a = m10785a(str);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (50.0f * f), (int) (50.0f * f));
        if (a.equals("top-right") || a.equals("bottom-right")) {
            layoutParams.addRule(11);
        }
        if (a.equals("bottom-right") || a.equals("bottom-left") || a.equals("bottom-center")) {
            layoutParams.addRule(12);
            layoutParams.addRule(4);
        }
        if (a.equals("bottom-center") || a.equals("top-center") || a.equals("center")) {
            layoutParams.addRule(13);
        }
        if (a.equals("top-center")) {
            layoutParams.addRule(10);
        }
        return layoutParams;
    }

    private String m10785a(String str) {
        if (str == null || str.length() == 0) {
            return "top-right";
        }
        if (str.equals("top-left") || str.equals("top-right") || str.equals("bottom-left") || str.equals("bottom-right") || str.equals("top-center") || str.equals("bottom-center") || str.equals("center")) {
            return str;
        }
        return "top-right";
    }

    private void m10787a(FrameLayout frameLayout, FrameLayout frameLayout2, C3233j c3233j) {
        float c = DisplayInfo.m10420a().m10440c();
        int i = (int) ((((float) c3233j.f8129b) * c) + 0.5f);
        int i2 = (int) ((((float) c3233j.f8130c) * c) + 0.5f);
        int i3 = (int) ((((float) c3233j.f8131d) * c) + 0.5f);
        int i4 = (int) ((c * ((float) c3233j.f8132e)) + 0.5f);
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        this.f8120c.getLocationOnScreen(iArr);
        frameLayout.getLocationOnScreen(iArr2);
        iArr[1] = iArr[1] - iArr2[1];
        iArr[0] = iArr[0] - iArr2[0];
        iArr[0] = i3 + iArr[0];
        iArr[1] = i4 + iArr[1];
        if (!c3233j.f8133f) {
            if (i > frameLayout.getWidth() - iArr[0]) {
                iArr[0] = frameLayout.getWidth() - i;
            }
            if (i2 > frameLayout.getHeight() - iArr[1]) {
                iArr[1] = frameLayout.getHeight() - i2;
            }
            if (iArr[0] < 0) {
                iArr[0] = 0;
            }
            if (iArr[1] < 0) {
                iArr[1] = 0;
            }
        }
        LayoutParams layoutParams = new FrameLayout.LayoutParams(i, i2);
        layoutParams.leftMargin = iArr[0];
        layoutParams.topMargin = iArr[1];
        layoutParams.gravity = GravityCompat.START;
        frameLayout2.setLayoutParams(layoutParams);
    }

    public void m10791b() {
        ViewGroup viewGroup = (ViewGroup) this.f8119b.getParent();
        View findViewById = viewGroup.getRootView().findViewById(65534);
        View findViewById2 = this.f8120c.getRootView().findViewById(SupportMenu.USER_MASK);
        ((ViewGroup) findViewById.getParent()).removeView(findViewById);
        ((ViewGroup) findViewById2.getParent()).removeView(findViewById2);
        viewGroup.removeView(this.f8119b);
        this.f8120c.addView(this.f8119b, this.f8121d, new RelativeLayout.LayoutParams(this.f8120c.getWidth(), this.f8120c.getHeight()));
        this.f8119b.m10659n();
    }
}
