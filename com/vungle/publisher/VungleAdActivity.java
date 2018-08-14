package com.vungle.publisher;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import com.vungle.publisher.env.C1614r;
import com.vungle.publisher.inject.Injector;
import com.vungle.publisher.log.C1654g;
import com.vungle.publisher.log.Logger;
import com.vungle.publisher.mg.C1655a;
import java.util.logging.Level;
import javax.inject.Inject;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: vungle */
public class VungleAdActivity extends Activity {
    public static final String AD_ID_EXTRA_KEY = "adId";
    public static final String AD_PLACEMENT_REFERENCE_ID_KEY = "placementReferenceId";
    public static final String AD_TYPE_EXTRA_KEY = "adType";
    mg<cn> f2690a;
    String f2691b;
    @Inject
    qg f2692c;
    @Inject
    bz f2693d;
    @Inject
    C1614r f2694e;
    @Inject
    cn$b f2695f;
    @Inject
    lm f2696g;
    @Inject
    C1655a f2697h;
    @Inject
    C1674u f2698i;
    @Inject
    C1654g f2699j;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Logger.d(Logger.AD_TAG, "interstitial ad");
            if (Injector.getInstance().d()) {
                Injector.c().m4198a(this);
                zo.m4933a((Context) this);
                Intent intent = getIntent();
                p a = this.f2698i.m4689a(intent);
                String stringExtra = intent.getStringExtra(AD_ID_EXTRA_KEY);
                m mVar = (m) intent.getSerializableExtra(AD_TYPE_EXTRA_KEY);
                String stringExtra2 = intent.getStringExtra(AD_PLACEMENT_REFERENCE_ID_KEY);
                cn a2 = this.f2695f.m3558a(mVar, stringExtra);
                if (a2 == null) {
                    this.f2699j.f3084b.warning("no ad in activity");
                    this.f2692c.m4568a(new bq(null, stringExtra2));
                    finish();
                    return;
                }
                this.f2699j.f3084b.info("creating ad activity with status: " + a2.g());
                String e = a2.e();
                if (e != null) {
                    try {
                        this.f2691b = ra.f(new JSONObject(e.substring(3)), "eventID");
                        this.f2699j.f3084b.log(Level.INFO, "extracted event id for the ad", this.f2691b);
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
                this.f2690a = this.f2697h.m4364a(a2);
                this.f2690a.mo3009a(this, a2, stringExtra2, a, savedInstanceState);
                return;
            }
            this.f2699j.f3084b.warning("SDK not initialized");
            finish();
        } catch (Throwable e3) {
            Logger.e(Logger.AD_TAG, "error playing ad", e3);
            finish();
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        try {
            this.f2690a.m4369a(outState);
            super.onSaveInstanceState(outState);
        } catch (Throwable e) {
            Logger.e(Logger.AD_TAG, "error in onSaveInstanceState", e);
        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
        this.f2690a.mo3013a(newConfig);
        super.onConfigurationChanged(newConfig);
    }

    protected void onResume() {
        try {
            super.onResume();
            this.f2694e.m3954e();
            this.f2696g.m4341c();
        } catch (Throwable e) {
            Logger.e(Logger.AD_TAG, "error in onResume()", e);
        }
    }

    protected void onPause() {
        try {
            super.onPause();
            this.f2694e.m3959i();
            this.f2693d.m3481a();
            this.f2696g.m4342d();
        } catch (Throwable e) {
            Logger.e(Logger.AD_TAG, "error in onPause()", e);
        }
    }

    protected void onDestroy() {
        try {
            this.f2690a.mo3008a(this);
            this.f2694e.m3956f();
        } catch (Throwable e) {
            Logger.e(Logger.AD_TAG, "error in onDestroy()", e);
        }
        if (!isFinishing()) {
            Logger.i(Logger.AD_TAG, "finishing ad that is being destroyed");
            finish();
        }
        super.onDestroy();
    }

    public void onBackPressed() {
        try {
            Logger.v(Logger.AD_TAG, "back button pressed");
            this.f2692c.m4568a(new y());
            this.f2690a.m4378c();
        } catch (Throwable e) {
            Logger.e(Logger.AD_TAG, "error in onBackPressed", e);
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        this.f2690a.m4375a(keyCode, event);
        return super.onKeyDown(keyCode, event);
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        try {
            super.onWindowFocusChanged(hasFocus);
            this.f2690a.m4373a(hasFocus);
        } catch (Throwable e) {
            Logger.e(Logger.AD_TAG, "error in onWindowFocusChanged", e);
        }
    }
}
