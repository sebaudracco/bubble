package com.facebook.ads.internal.view.p053e.p085c;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.p052j.C1839f;
import com.facebook.ads.internal.view.p053e.p054b.C2235l;
import com.facebook.ads.internal.view.p053e.p083a.C2224c;

public class C2305k extends C2224c {
    private final C1839f<C2235l> f5579a;

    class C23041 extends C1839f<C2235l> {
        final /* synthetic */ C2305k f5578a;

        C23041(C2305k c2305k) {
            this.f5578a = c2305k;
        }

        public Class<C2235l> mo3580a() {
            return C2235l.class;
        }

        public void m7273a(C2235l c2235l) {
            this.f5578a.setVisibility(8);
        }
    }

    public C2305k(Context context) {
        this(context, null);
    }

    public C2305k(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public C2305k(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f5579a = new C23041(this);
        int applyDimension = (int) TypedValue.applyDimension(1, 40.0f, getResources().getDisplayMetrics());
        View progressBar = new ProgressBar(getContext());
        progressBar.setIndeterminate(true);
        progressBar.getIndeterminateDrawable().setColorFilter(-1, Mode.SRC_IN);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(applyDimension, applyDimension);
        layoutParams.addRule(13);
        addView(progressBar, layoutParams);
    }

    protected void mo3787a() {
        super.mo3787a();
        setVisibility(0);
        if (getVideoView() != null) {
            getVideoView().getEventBus().m6329a(this.f5579a);
        }
    }

    protected void mo3788b() {
        if (getVideoView() != null) {
            getVideoView().getEventBus().m6331b(this.f5579a);
        }
        setVisibility(8);
        super.mo3788b();
    }
}
