package com.appnext.core;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.appnext.core.C1123e.C0899a;

public abstract class AppnextActivity extends Activity {
    protected String banner = "";
    protected String guid = "";
    protected Handler handler;
    protected String la = "";
    private RelativeLayout lb;
    protected RelativeLayout lc;
    protected String placementID;
    protected C1149r userAction;

    class C10971 implements Runnable {
        final /* synthetic */ AppnextActivity ld;

        class C10961 implements Runnable {
            final /* synthetic */ C10971 le;

            C10961(C10971 c10971) {
                this.le = c10971;
            }

            public void run() {
                this.le.ld.onError(AppnextError.CONNECTION_ERROR);
            }
        }

        C10971(AppnextActivity appnextActivity) {
            this.ld = appnextActivity;
        }

        public void run() {
            if (!C1128g.m2331B(this.ld)) {
                this.ld.finish();
                this.ld.runOnUiThread(new C10961(this));
            }
        }
    }

    class C10982 implements OnClickListener {
        final /* synthetic */ AppnextActivity ld;

        C10982(AppnextActivity appnextActivity) {
            this.ld = appnextActivity;
        }

        public void onClick(View view) {
        }
    }

    class C10993 implements Runnable {
        final /* synthetic */ AppnextActivity ld;

        C10993(AppnextActivity appnextActivity) {
            this.ld = appnextActivity;
        }

        public void run() {
            this.ld.cQ();
        }
    }

    protected abstract C0921q getConfig();

    protected abstract void onError(String str);

    protected void onCreate(Bundle bundle) {
        new Thread(new C10971(this)).start();
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        getWindow().addFlags(128);
        super.onCreate(bundle);
        this.handler = new Handler();
    }

    @SuppressLint({"NewApi"})
    protected void cO() {
        int systemUiVisibility = getWindow().getDecorView().getSystemUiVisibility() ^ 2;
        if (VERSION.SDK_INT >= 16) {
            systemUiVisibility ^= 4;
        }
        if (VERSION.SDK_INT >= 18) {
            systemUiVisibility ^= 4096;
        }
        getWindow().getDecorView().setSystemUiVisibility(systemUiVisibility);
    }

    @SuppressLint({"NewApi"})
    protected void cP() {
        int systemUiVisibility = getWindow().getDecorView().getSystemUiVisibility() | 2;
        if (VERSION.SDK_INT >= 16) {
            systemUiVisibility |= 4;
        }
        if (VERSION.SDK_INT >= 18) {
            systemUiVisibility |= 4096;
        }
        getWindow().getDecorView().setSystemUiVisibility(systemUiVisibility);
    }

    protected void mo1936a(AppnextAd appnextAd, C0899a c0899a) {
        if (this.userAction != null && appnextAd != null) {
            this.userAction.m2395a(appnextAd, appnextAd.getAppURL(), c0899a);
        }
    }

    protected void mo1937b(AppnextAd appnextAd, C0899a c0899a) {
        if (this.userAction != null && appnextAd != null) {
            this.userAction.m2396a(appnextAd, appnextAd.getAppURL(), this.placementID, c0899a);
        }
    }

    protected void m1830a(ViewGroup viewGroup, Drawable drawable) {
        if (this.lb != null) {
            cQ();
        }
        this.lb = new RelativeLayout(this);
        this.lb.setBackgroundColor(Color.parseColor("#77ffffff"));
        viewGroup.addView(this.lb);
        this.lb.getLayoutParams().height = -1;
        this.lb.getLayoutParams().width = -1;
        View progressBar = new ProgressBar(this, null, 16842871);
        progressBar.setIndeterminateDrawable(drawable);
        progressBar.setIndeterminate(true);
        this.lb.addView(progressBar);
        Animation rotateAnimation = new RotateAnimation(360.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setRepeatCount(-1);
        progressBar.setAnimation(rotateAnimation);
        ((LayoutParams) progressBar.getLayoutParams()).addRule(13, -1);
        this.lb.setOnClickListener(new C10982(this));
        this.handler.postDelayed(new C10993(this), 8000);
    }

    protected void cQ() {
        if (this.lb != null) {
            this.lb.removeAllViews();
            this.lb.removeAllViewsInLayout();
            if (this.lb.getParent() != null) {
                ((RelativeLayout) this.lb.getParent()).removeView(this.lb);
            }
        }
        if (this.handler != null) {
            this.handler.removeCallbacks(null);
        }
        this.lb = null;
    }

    protected void onDestroy() {
        super.onDestroy();
        try {
            this.handler.removeCallbacks(null);
            this.handler = null;
        } catch (Throwable th) {
        }
    }
}
