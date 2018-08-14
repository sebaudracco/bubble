package com.facebook.ads.internal.view.p053e.p083a;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.view.p053e.C2249b;

public abstract class C2224c extends RelativeLayout implements C2223b {
    @Nullable
    private C2249b f5418a;

    public C2224c(Context context) {
        super(context);
    }

    public C2224c(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setLayoutParams(new LayoutParams(-1, -1));
    }

    protected void mo3787a() {
    }

    public void mo3777a(C2249b c2249b) {
        this.f5418a = c2249b;
        mo3787a();
    }

    protected void mo3788b() {
    }

    public void mo3778b(C2249b c2249b) {
        mo3788b();
        this.f5418a = null;
    }

    @Nullable
    protected C2249b getVideoView() {
        return this.f5418a;
    }
}
