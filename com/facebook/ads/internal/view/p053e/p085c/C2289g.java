package com.facebook.ads.internal.view.p053e.p085c;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.p052j.C1839f;
import com.facebook.ads.internal.view.p034b.C2186d;
import com.facebook.ads.internal.view.p053e.p054b.C2229b;
import com.facebook.ads.internal.view.p053e.p054b.C2234j;
import com.facebook.ads.internal.view.p053e.p083a.C2224c;

public class C2289g extends C2224c implements OnLayoutChangeListener {
    private final ImageView f5536a;
    private final C1839f<C2234j> f5537b = new C22871(this);
    private final C1839f<C2229b> f5538c = new C22882(this);

    class C22871 extends C1839f<C2234j> {
        final /* synthetic */ C2289g f5534a;

        C22871(C2289g c2289g) {
            this.f5534a = c2289g;
        }

        public Class<C2234j> mo3580a() {
            return C2234j.class;
        }

        public void m7226a(C2234j c2234j) {
            this.f5534a.setVisibility(8);
        }
    }

    class C22882 extends C1839f<C2229b> {
        final /* synthetic */ C2289g f5535a;

        C22882(C2289g c2289g) {
            this.f5535a = c2289g;
        }

        public Class<C2229b> mo3580a() {
            return C2229b.class;
        }

        public void m7229a(C2229b c2229b) {
            this.f5535a.setVisibility(0);
        }
    }

    public C2289g(Context context) {
        super(context);
        this.f5536a = new ImageView(context);
        this.f5536a.setScaleType(ScaleType.FIT_CENTER);
        this.f5536a.setBackgroundColor(-16777216);
        this.f5536a.setLayoutParams(new LayoutParams(-1, -1));
        addView(this.f5536a);
    }

    protected void mo3787a() {
        super.mo3787a();
        if (getVideoView() != null) {
            getVideoView().getEventBus().m6328a(this.f5537b, this.f5538c);
            getVideoView().addOnLayoutChangeListener(this);
        }
    }

    protected void mo3788b() {
        if (getVideoView() != null) {
            getVideoView().removeOnLayoutChangeListener(this);
            getVideoView().getEventBus().m6330b(this.f5538c, this.f5537b);
        }
        super.mo3788b();
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        LayoutParams layoutParams = (LayoutParams) getLayoutParams();
        int i9 = i4 - i2;
        int i10 = i3 - i;
        if (layoutParams.height != i9 || layoutParams.width != i10 || layoutParams.topMargin != i2 || layoutParams.leftMargin != i) {
            ViewGroup.LayoutParams layoutParams2 = new LayoutParams(i10, i9);
            layoutParams2.topMargin = i2;
            layoutParams2.leftMargin = i;
            this.f5536a.setLayoutParams(new LayoutParams(i10, i9));
            setLayoutParams(layoutParams2);
        }
    }

    public void setImage(@Nullable String str) {
        if (str == null) {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        new C2186d(this.f5536a).m7000a().m7004a(str);
    }
}
