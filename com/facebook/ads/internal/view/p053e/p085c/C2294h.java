package com.facebook.ads.internal.view.p053e.p085c;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.view.p053e.C2249b;
import com.facebook.ads.internal.view.p053e.p054b.C1840m;
import com.facebook.ads.internal.view.p053e.p054b.C1841k;
import com.facebook.ads.internal.view.p053e.p054b.C1842i;
import com.facebook.ads.internal.view.p053e.p054b.C1844c;
import com.facebook.ads.internal.view.p053e.p054b.C2229b;
import com.facebook.ads.internal.view.p053e.p054b.C2233h;
import com.facebook.ads.internal.view.p053e.p054b.C2234j;
import com.facebook.ads.internal.view.p053e.p054b.C2235l;
import com.facebook.ads.internal.view.p053e.p083a.C2222a;
import com.facebook.ads.internal.view.p053e.p083a.C2224c;
import com.facebook.ads.internal.view.p053e.p084d.C2336d;

public class C2294h extends C2224c implements OnTouchListener {
    private final C1840m f5543a;
    private final C1842i f5544b;
    private final C1841k f5545c;
    private final C1844c f5546d;
    private final C2313m f5547e;

    class C22901 extends C1840m {
        final /* synthetic */ C2294h f5539a;

        C22901(C2294h c2294h) {
            this.f5539a = c2294h;
        }

        public void m7233a(C2235l c2235l) {
            this.f5539a.setVisibility(0);
        }
    }

    class C22912 extends C1842i {
        final /* synthetic */ C2294h f5540a;

        C22912(C2294h c2294h) {
            this.f5540a = c2294h;
        }

        public void m7235a(C2233h c2233h) {
            this.f5540a.f5547e.setChecked(true);
        }
    }

    class C22923 extends C1841k {
        final /* synthetic */ C2294h f5541a;

        C22923(C2294h c2294h) {
            this.f5541a = c2294h;
        }

        public void m7237a(C2234j c2234j) {
            this.f5541a.f5547e.setChecked(false);
        }
    }

    class C22934 extends C1844c {
        final /* synthetic */ C2294h f5542a;

        C22934(C2294h c2294h) {
            this.f5542a = c2294h;
        }

        public void m7239a(C2229b c2229b) {
            this.f5542a.f5547e.setChecked(true);
        }
    }

    public C2294h(Context context) {
        this(context, null);
    }

    public C2294h(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public C2294h(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f5543a = new C22901(this);
        this.f5544b = new C22912(this);
        this.f5545c = new C22923(this);
        this.f5546d = new C22934(this);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.f5547e = new C2313m(context);
        this.f5547e.setChecked(true);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (displayMetrics.density * 25.0f), (int) (displayMetrics.density * 25.0f));
        setVisibility(8);
        addView(this.f5547e, layoutParams);
        setClickable(true);
        setFocusable(true);
    }

    protected void mo3787a() {
        super.mo3787a();
        this.f5547e.setOnTouchListener(this);
        setOnTouchListener(this);
        if (getVideoView() != null) {
            getVideoView().getEventBus().m6328a(this.f5543a, this.f5546d, this.f5544b, this.f5545c);
        }
    }

    protected void mo3788b() {
        if (getVideoView() != null) {
            getVideoView().getEventBus().m6330b(this.f5545c, this.f5544b, this.f5546d, this.f5543a);
        }
        setOnTouchListener(null);
        this.f5547e.setOnTouchListener(null);
        super.mo3788b();
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 1) {
            return false;
        }
        C2249b videoView = getVideoView();
        if (videoView == null) {
            return false;
        }
        if (videoView.getState() == C2336d.PREPARED || videoView.getState() == C2336d.PAUSED || videoView.getState() == C2336d.PLAYBACK_COMPLETED) {
            videoView.m7104a(C2222a.USER_STARTED);
            return true;
        } else if (videoView.getState() != C2336d.STARTED) {
            return false;
        } else {
            videoView.m7107a(true);
            return false;
        }
    }
}
