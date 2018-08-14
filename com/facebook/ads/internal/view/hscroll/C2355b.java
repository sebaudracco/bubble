package com.facebook.ads.internal.view.hscroll;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import com.facebook.ads.internal.view.hscroll.C2353d.C2354a;

public class C2355b extends C2353d implements C2354a {
    private final HScrollLinearLayoutManager f5801c;
    private C2352a f5802d;
    private int f5803e = -1;
    private int f5804f = -1;
    private int f5805g = 0;
    private int f5806h = 0;

    public interface C2352a {
        void m7442a(int i, int i2);
    }

    public C2355b(Context context) {
        super(context);
        this.f5801c = new HScrollLinearLayoutManager(context, new C2356c(), new C2351a());
        m7449a();
    }

    public C2355b(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f5801c = new HScrollLinearLayoutManager(context, new C2356c(), new C2351a());
        m7449a();
    }

    public C2355b(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f5801c = new HScrollLinearLayoutManager(context, new C2356c(), new C2351a());
        m7449a();
    }

    private void m7449a() {
        this.f5801c.setOrientation(0);
        setLayoutManager(this.f5801c);
        setSaveEnabled(false);
        setSnapDelegate(this);
    }

    private void m7450a(int i, int i2) {
        if (i != this.f5803e || i2 != this.f5804f) {
            this.f5803e = i;
            this.f5804f = i2;
            if (this.f5802d != null) {
                this.f5802d.m7442a(this.f5803e, this.f5804f);
            }
        }
    }

    private int m7451b(int i) {
        int i2 = this.f5806h * 2;
        int measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - i2;
        int itemCount = getAdapter().getItemCount();
        int i3 = 0;
        int i4 = Integer.MAX_VALUE;
        while (i4 > i) {
            i3++;
            if (i3 >= itemCount) {
                return i;
            }
            i4 = (int) (((float) (measuredWidth - (i3 * i2))) / (((float) i3) + 0.333f));
        }
        return i4;
    }

    public int mo3822a(int i) {
        int abs = Math.abs(i);
        return abs <= this.a ? 0 : this.f5805g == 0 ? 1 : (abs / this.f5805g) + 1;
    }

    protected void mo3823a(int i, boolean z) {
        super.mo3823a(i, z);
        m7450a(i, 0);
    }

    public int getChildSpacing() {
        return this.f5806h;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int round = Math.round(((float) getMeasuredWidth()) / 1.91f);
        switch (MeasureSpec.getMode(i2)) {
            case Integer.MIN_VALUE:
                round = Math.min(MeasureSpec.getSize(i2), round);
                break;
            case 1073741824:
                round = MeasureSpec.getSize(i2);
                break;
        }
        int paddingTop = getPaddingTop() + getPaddingBottom();
        round = m7451b(round - paddingTop);
        setMeasuredDimension(getMeasuredWidth(), paddingTop + round);
        setChildWidth(round + (this.f5806h * 2));
    }

    public void setAdapter(@Nullable Adapter adapter) {
        this.f5801c.m7437a(adapter == null ? -1 : adapter.hashCode());
        super.setAdapter(adapter);
    }

    public void setChildSpacing(int i) {
        this.f5806h = i;
    }

    public void setChildWidth(int i) {
        this.f5805g = i;
        int measuredWidth = getMeasuredWidth();
        this.f5801c.m7438b((((measuredWidth - getPaddingLeft()) - getPaddingRight()) - this.f5805g) / 2);
        this.f5801c.m7436a(((double) this.f5805g) / ((double) measuredWidth));
    }

    public void setCurrentPosition(int i) {
        mo3823a(i, false);
    }

    public void setOnPageChangedListener(C2352a c2352a) {
        this.f5802d = c2352a;
    }
}
