package com.inmobi.ads;

import android.content.Context;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import com.inmobi.ads.NativeV2ScrollableContainer.C2963a;
import com.inmobi.ads.NativeV2ScrollableContainer.TYPE;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;

/* compiled from: ScrollableDeckPagesContainer */
class bh extends NativeV2ScrollableContainer implements OnPageChangeListener {
    private static final String f7260a = bh.class.getSimpleName();
    private ViewPager f7261b;
    private Point f7262c = new Point();
    private Point f7263d = new Point();
    private boolean f7264e;
    private boolean f7265f = false;
    @Nullable
    private C2963a f7266g;

    public bh(Context context, TYPE type) {
        super(context, type);
        setClipChildren(false);
        if (VERSION.SDK_INT >= 11) {
            setLayerType(1, null);
        }
        this.f7261b = new ViewPager(getContext());
        this.f7261b.addOnPageChangeListener(this);
        addView(this.f7261b);
    }

    void m9516a() {
        this.f7266g = null;
    }

    public void mo6215a(@NonNull ak akVar, @NonNull aq aqVar, int i, int i2, @NonNull C2963a c2963a) {
        LayoutParams layoutParams = (LayoutParams) NativeStrandViewFactory.m8952a(akVar.m9362b(0), (ViewGroup) this);
        if (VERSION.SDK_INT >= 17) {
            layoutParams.setMarginStart(20);
            layoutParams.setMarginEnd(20);
        } else {
            layoutParams.setMargins(20, 0, 20, 0);
        }
        layoutParams.gravity = i2;
        this.f7261b.setLayoutParams(layoutParams);
        this.f7261b.setAdapter((ad) aqVar);
        this.f7261b.setOffscreenPageLimit(2);
        this.f7261b.setPageMargin(16);
        this.f7261b.setCurrentItem(i);
        this.f7266g = c2963a;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        this.f7262c.x = i / 2;
        this.f7262c.y = i2 / 2;
    }

    public boolean onTouchEvent(@NonNull MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.f7263d.x = (int) motionEvent.getX();
                this.f7263d.y = (int) motionEvent.getY();
                break;
            case 1:
                int a = m9515a((float) this.f7263d.x, motionEvent.getX());
                if (a != 0) {
                    motionEvent.setAction(3);
                    this.f7261b.setCurrentItem(a + this.f7261b.getCurrentItem());
                }
                motionEvent.offsetLocation((float) (this.f7262c.x - this.f7263d.x), (float) (this.f7262c.y - this.f7263d.y));
                break;
        }
        motionEvent.offsetLocation((float) (this.f7262c.x - this.f7263d.x), (float) (this.f7262c.y - this.f7263d.y));
        return this.f7261b.dispatchTouchEvent(motionEvent);
    }

    public void onPageScrolled(int i, float f, int i2) {
        if (this.f7264e) {
            invalidate();
        }
    }

    public void onPageSelected(int i) {
        Logger.m10359a(InternalLogLevel.INTERNAL, f7260a, "Page Selected:" + i);
        LayoutParams layoutParams = (LayoutParams) this.f7261b.getLayoutParams();
        if (this.f7266g != null) {
            layoutParams.gravity = this.f7266g.mo6172a(i);
            this.f7261b.requestLayout();
        }
    }

    public void onPageScrollStateChanged(int i) {
        this.f7264e = i != 0;
    }

    private int m9515a(float f, float f2) {
        int currentItem = this.f7261b.getCurrentItem();
        int count = this.f7261b.getAdapter().getCount();
        int width = this.f7261b.getWidth();
        int width2 = getWidth();
        if (currentItem == 0 || count - 1 == currentItem) {
            count = width2 - width;
            if (currentItem == 0) {
                if (f <= ((float) count) || f2 <= ((float) count)) {
                    return 0;
                }
                return (int) Math.ceil((double) ((f2 - ((float) count)) / ((float) width)));
            } else if (f >= ((float) count) || f2 >= ((float) count)) {
                return 0;
            } else {
                return -((int) Math.ceil((double) ((((float) count) - f2) / ((float) width))));
            }
        }
        currentItem = (width2 - width) / 2;
        if (f < ((float) currentItem) && f2 < ((float) currentItem)) {
            return -((int) Math.ceil((double) ((((float) currentItem) - f2) / ((float) width))));
        }
        currentItem = (width2 + width) / 2;
        if (f <= ((float) currentItem) || f2 <= ((float) currentItem)) {
            return 0;
        }
        return (int) Math.ceil((double) ((f2 - ((float) currentItem)) / ((float) width)));
    }
}
