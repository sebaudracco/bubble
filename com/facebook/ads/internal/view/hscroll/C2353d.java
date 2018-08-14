package com.facebook.ads.internal.view.hscroll;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class C2353d extends RecyclerView implements OnTouchListener {
    protected final int f5794a = m7443a();
    protected int f5795b = 0;
    private int f5796c = 0;
    private boolean f5797d = true;
    private boolean f5798e = false;
    private LinearLayoutManager f5799f;
    private C2354a f5800g;

    public interface C2354a {
        int mo3822a(int i);
    }

    public C2353d(Context context) {
        super(context);
        setOnTouchListener(this);
    }

    public C2353d(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnTouchListener(this);
    }

    public C2353d(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setOnTouchListener(this);
    }

    private int m7443a() {
        return ((int) getContext().getResources().getDisplayMetrics().density) * 10;
    }

    private int mo3822a(int i) {
        int i2 = this.f5796c - i;
        int a = this.f5800g.mo3822a(i2);
        return i2 > this.f5794a ? m7445a(this.f5795b, a) : i2 < (-this.f5794a) ? m7446b(this.f5795b, a) : this.f5795b;
    }

    private int m7445a(int i, int i2) {
        return Math.min(i + i2, getItemCount() - 1);
    }

    private int m7446b(int i, int i2) {
        return Math.max(i - i2, 0);
    }

    private int getItemCount() {
        return getAdapter() == null ? 0 : getAdapter().getItemCount();
    }

    protected void mo3823a(int i, boolean z) {
        if (getAdapter() != null) {
            this.f5795b = i;
            if (z) {
                smoothScrollToPosition(i);
            } else {
                scrollToPosition(i);
            }
        }
    }

    public int getCurrentPosition() {
        return this.f5795b;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        int rawX = (int) motionEvent.getRawX();
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1 || actionMasked == 6 || actionMasked == 3 || actionMasked == 4) {
            if (this.f5798e) {
                mo3823a(mo3822a(rawX), true);
            }
            this.f5797d = true;
            this.f5798e = false;
            return true;
        } else if (actionMasked != 0 && actionMasked != 5 && (!this.f5797d || actionMasked != 2)) {
            return false;
        } else {
            this.f5796c = rawX;
            if (this.f5797d) {
                this.f5797d = false;
            }
            this.f5798e = true;
            return false;
        }
    }

    public void setLayoutManager(LayoutManager layoutManager) {
        if (layoutManager instanceof LinearLayoutManager) {
            super.setLayoutManager(layoutManager);
            this.f5799f = (LinearLayoutManager) layoutManager;
            return;
        }
        throw new IllegalArgumentException("SnapRecyclerView only supports LinearLayoutManager");
    }

    public void setSnapDelegate(C2354a c2354a) {
        this.f5800g = c2354a;
    }
}
