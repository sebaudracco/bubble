package com.vungle.publisher;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.vungle.publisher.env.i;
import com.vungle.publisher.log.Logger;
import javax.inject.Inject;

/* compiled from: vungle */
public abstract class mm<W extends mj> extends mf {
    protected lf f3124e;
    protected W f3125f;
    protected String f3126g;
    protected p f3127h;
    protected x f3128i;
    @Inject
    i f3129j;
    @Inject
    qg f3130k;
    @Inject
    lf$b f3131l;
    @Inject
    Context f3132m;

    protected abstract W mo3000a(String str, p pVar, x xVar);

    public void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            if (savedInstanceState != null) {
                this.f3124e = this.f3131l.m4332a(savedInstanceState);
                this.f3126g = savedInstanceState.getString(VungleAdActivity.AD_ID_EXTRA_KEY);
                this.f3128i = x.a(savedInstanceState.getString("templateType"));
            }
        } catch (Throwable e) {
            Logger.w(Logger.AD_TAG, "exception in onCreate", e);
        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.f3125f.onConfigurationChanged(newConfig);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View relativeLayout;
        Throwable e;
        try {
            View a = mo3000a(this.f3126g, this.f3127h, this.f3128i);
            this.f3125f = a;
            a.a(this.f3124e);
            this.f3129j.a(a);
            zo.m4934a(a);
            relativeLayout = new RelativeLayout(this.f3132m);
            try {
                relativeLayout.addView(a);
                LayoutParams layoutParams = a.getLayoutParams();
                layoutParams.width = -1;
                layoutParams.height = -1;
            } catch (Exception e2) {
                e = e2;
                Logger.w(Logger.AD_TAG, "exception in onCreateView", e);
                return relativeLayout;
            }
        } catch (Throwable e3) {
            Throwable th = e3;
            relativeLayout = null;
            e = th;
            Logger.w(Logger.AD_TAG, "exception in onCreateView", e);
            return relativeLayout;
        }
        return relativeLayout;
    }

    public void onDestroy() {
        Logger.d(Logger.AD_TAG, "webview fragment onDestroy()");
        super.onDestroy();
        if (this.f3125f != null) {
            this.f3125f.destroy();
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putString(VungleAdActivity.AD_ID_EXTRA_KEY, this.f3126g);
        outState.putString("templateType", this.f3128i.name());
        this.f3124e.a(outState);
        super.onSaveInstanceState(outState);
    }
}
