package com.facebook.ads.internal.view.hscroll;

import android.content.Context;
import android.graphics.PointF;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.Recycler;
import android.support.v7.widget.RecyclerView.State;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.MeasureSpec;
import com.mopub.mobileads.dfp.adapters.MoPubAdapter;

public class HScrollLinearLayoutManager extends LinearLayoutManager {
    private final C2356c f5785a;
    private final C2351a f5786b;
    private final Context f5787c;
    private int[] f5788d;
    private int f5789e = 0;
    private float f5790f = 50.0f;
    private C2350a f5791g;
    private int f5792h;

    private class C2350a extends LinearSmoothScroller {
        final /* synthetic */ HScrollLinearLayoutManager f5784a;

        public C2350a(HScrollLinearLayoutManager hScrollLinearLayoutManager, Context context) {
            this.f5784a = hScrollLinearLayoutManager;
            super(context);
        }

        public int calculateDxToMakeVisible(View view, int i) {
            LayoutManager layoutManager = getLayoutManager();
            if (!layoutManager.canScrollHorizontally()) {
                return 0;
            }
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            return calculateDtToFit(layoutManager.getDecoratedLeft(view) - layoutParams.leftMargin, layoutManager.getDecoratedRight(view) + layoutParams.rightMargin, layoutManager.getPaddingLeft(), layoutManager.getWidth() - layoutManager.getPaddingRight(), i) + this.f5784a.f5789e;
        }

        protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
            return this.f5784a.f5790f / ((float) displayMetrics.densityDpi);
        }

        public PointF computeScrollVectorForPosition(int i) {
            return this.f5784a.computeScrollVectorForPosition(i);
        }

        protected int getHorizontalSnapPreference() {
            return -1;
        }
    }

    public HScrollLinearLayoutManager(Context context, C2356c c2356c, C2351a c2351a) {
        super(context);
        this.f5787c = context;
        this.f5785a = c2356c;
        this.f5786b = c2351a;
        this.f5792h = -1;
        this.f5791g = new C2350a(this, this.f5787c);
    }

    public void m7436a(double d) {
        if (d <= 0.0d) {
            d = MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE;
        }
        this.f5790f = (float) (50.0d / d);
        this.f5791g = new C2350a(this, this.f5787c);
    }

    void m7437a(int i) {
        this.f5792h = i;
    }

    public void m7438b(int i) {
        this.f5789e = i;
    }

    public void onMeasure(Recycler recycler, State state, int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        if ((mode == 1073741824 && getOrientation() == 1) || (mode2 == 1073741824 && getOrientation() == 0)) {
            super.onMeasure(recycler, state, i, i2);
            return;
        }
        int[] a;
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        if (this.f5786b.m7441b(this.f5792h)) {
            a = this.f5786b.m7440a(this.f5792h);
        } else {
            a = new int[]{0, 0};
            if (state.getItemCount() >= 1) {
                for (int i3 = 0; i3 < 1; i3++) {
                    this.f5788d = this.f5785a.m7454a(recycler, i3, MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
                    if (getOrientation() == 0) {
                        a[0] = a[0] + this.f5788d[0];
                        if (i3 == 0) {
                            a[1] = (this.f5788d[1] + getPaddingTop()) + getPaddingBottom();
                        }
                    } else {
                        a[1] = a[1] + this.f5788d[1];
                        if (i3 == 0) {
                            a[0] = (this.f5788d[0] + getPaddingLeft()) + getPaddingRight();
                        }
                    }
                }
                if (this.f5792h != -1) {
                    this.f5786b.m7439a(this.f5792h, a);
                }
            }
        }
        if (mode == 1073741824) {
            a[0] = size;
        }
        if (mode2 == 1073741824) {
            a[1] = size2;
        }
        setMeasuredDimension(a[0], a[1]);
    }

    public void scrollToPosition(int i) {
        super.scrollToPositionWithOffset(i, this.f5789e);
    }

    public void smoothScrollToPosition(RecyclerView recyclerView, State state, int i) {
        this.f5791g.setTargetPosition(i);
        startSmoothScroll(this.f5791g);
    }
}
