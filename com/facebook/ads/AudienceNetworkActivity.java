package com.facebook.ads;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.ads.internal.adapters.ad;
import com.facebook.ads.internal.adapters.m;
import com.facebook.ads.internal.adapters.n;
import com.facebook.ads.internal.j.d;
import com.facebook.ads.internal.q.a.v;
import com.facebook.ads.internal.settings.a.a;
import com.facebook.ads.internal.view.b.c;
import com.facebook.ads.internal.view.e.b;
import com.facebook.ads.internal.view.e.b.z;
import com.facebook.ads.internal.view.f;
import com.facebook.ads.internal.view.q;
import java.util.ArrayList;
import java.util.List;

public class AudienceNetworkActivity extends Activity {
    @Deprecated
    public static final String AD_ICON_URL = "adIconUrl";
    @Deprecated
    public static final String AD_SUBTITLE = "adSubtitle";
    @Deprecated
    public static final String AD_TITLE = "adTitle";
    public static final String AUDIENCE_NETWORK_UNIQUE_ID_EXTRA = "uniqueId";
    public static final String AUTOPLAY = "autoplay";
    public static final String BROWSER_URL = "browserURL";
    public static final String CLIENT_TOKEN = "clientToken";
    @Deprecated
    public static final String CONTEXT_SWITCH_BEHAVIOR = "contextSwitchBehavior";
    @Deprecated
    public static final String END_CARD_ACTIVATION_COMMAND = "facebookRewardedVideoEndCardActivationCommand";
    @Deprecated
    public static final String END_CARD_MARKUP = "facebookRewardedVideoEndCardMarkup";
    public static final String HANDLER_TIME = "handlerTime";
    public static final String PLACEMENT_ID = "placementId";
    public static final String PREDEFINED_ORIENTATION_KEY = "predefinedOrientationKey";
    public static final String REQUEST_TIME = "requestTime";
    public static final String REWARD_SERVER_URL = "rewardServerURL";
    public static final String SKIP_DELAY_SECONDS_KEY = "skipAfterSeconds";
    public static final String VIDEO_LOGGER = "videoLogger";
    public static final String VIDEO_MPD = "videoMPD";
    @Deprecated
    public static final String VIDEO_REPORT_URL = "videoReportURL";
    public static final String VIDEO_SEEK_TIME = "videoSeekTime";
    public static final String VIDEO_URL = "videoURL";
    public static final String VIEW_TYPE = "viewType";
    @Deprecated
    public static final String WEBVIEW_ENCODING = "utf-8";
    @Deprecated
    public static final String WEBVIEW_MIME_TYPE = "text/html";
    private final List<BackButtonInterceptor> f2541a = new ArrayList();
    private RelativeLayout f2542b;
    private int f2543c = -1;
    private String f2544d;
    private a f2545e;
    private long f2546f;
    private long f2547g;
    private int f2548h;
    private com.facebook.ads.internal.view.a f2549i;
    private c f2550j;

    private void m3324a(Intent intent, Bundle bundle) {
        if (bundle != null) {
            this.f2543c = bundle.getInt(PREDEFINED_ORIENTATION_KEY, -1);
            this.f2544d = bundle.getString(AUDIENCE_NETWORK_UNIQUE_ID_EXTRA);
            this.f2545e = (a) bundle.getSerializable(VIEW_TYPE);
            return;
        }
        this.f2543c = intent.getIntExtra(PREDEFINED_ORIENTATION_KEY, -1);
        this.f2544d = intent.getStringExtra(AUDIENCE_NETWORK_UNIQUE_ID_EXTRA);
        this.f2545e = (a) intent.getSerializableExtra(VIEW_TYPE);
        this.f2548h = intent.getIntExtra(SKIP_DELAY_SECONDS_KEY, 0) * 1000;
    }

    private void m3325a(Intent intent, boolean z) {
        if (com.facebook.ads.internal.l.a.b(this) && this.f2545e != a.g) {
            this.f2550j = new c();
            this.f2550j.a(intent.getStringExtra("placementId"));
            this.f2550j.b(getPackageName());
            long longExtra = intent.getLongExtra(REQUEST_TIME, 0);
            if (longExtra != 0) {
                this.f2550j.a(longExtra);
            }
            View textView = new TextView(this);
            textView.setText("Debug");
            textView.setTextColor(-1);
            textView.setBackgroundColor(Color.argb(160, 0, 0, 0));
            textView.setPadding(5, 5, 5, 5);
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(12, -1);
            layoutParams.addRule(11, -1);
            textView.setLayoutParams(layoutParams);
            OnLongClickListener aVar = new a(this, null);
            textView.setOnLongClickListener(aVar);
            if (z) {
                this.f2542b.addView(textView);
            } else {
                this.f2542b.setOnLongClickListener(aVar);
            }
            this.f2542b.getOverlay().add(this.f2550j);
        }
    }

    private void m3328a(String str) {
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(str + ":" + this.f2544d));
    }

    private void m3329a(String str, d dVar) {
        Intent intent = new Intent(str + ":" + this.f2544d);
        intent.putExtra(NotificationCompat.CATEGORY_EVENT, dVar);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    public void addBackButtonInterceptor(BackButtonInterceptor backButtonInterceptor) {
        this.f2541a.add(backButtonInterceptor);
    }

    public void finish() {
        if (!isFinishing()) {
            if (this.f2545e == a.f) {
                m3328a(z.g.a());
            } else {
                m3328a("com.facebook.ads.interstitial.dismissed");
            }
            super.finish();
        }
    }

    public void onBackPressed() {
        long currentTimeMillis = System.currentTimeMillis();
        this.f2547g += currentTimeMillis - this.f2546f;
        this.f2546f = currentTimeMillis;
        if (this.f2547g > ((long) this.f2548h)) {
            Object obj = null;
            for (BackButtonInterceptor interceptBackButton : this.f2541a) {
                obj = interceptBackButton.interceptBackButton() ? 1 : obj;
            }
            if (obj == null) {
                super.onBackPressed();
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (this.f2549i instanceof n) {
            ((n) this.f2549i).a(configuration);
        } else if (this.f2549i instanceof q) {
            ((q) this.f2549i).a(configuration);
        }
        super.onConfigurationChanged(configuration);
    }

    public void onCreate(Bundle bundle) {
        boolean z = true;
        super.onCreate(bundle);
        com.facebook.ads.internal.q.a.d.a();
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        this.f2542b = new RelativeLayout(this);
        v.a(this.f2542b, -16777216);
        setContentView(this.f2542b, new RelativeLayout.LayoutParams(-1, -1));
        Intent intent = getIntent();
        m3324a(intent, bundle);
        com.facebook.ads.internal.m.c a = com.facebook.ads.internal.m.d.a(this);
        if (this.f2545e == a.e) {
            com.facebook.ads.internal.view.a vVar = new com.facebook.ads.internal.view.v(this, a, new 1(this));
            vVar.a(this.f2542b);
            this.f2549i = vVar;
            z = false;
        } else if (this.f2545e == a.f) {
            this.f2549i = new q(this, a, new b(this), new 2(this), (ad) intent.getSerializableExtra("rewardedVideoAdDataBundle"));
            z = false;
        } else if (this.f2545e == a.a) {
            this.f2549i = new f(this, a, new 3(this));
        } else if (this.f2545e == a.g) {
            this.f2549i = new com.facebook.ads.internal.view.b(this, a, new 4(this));
            z = false;
        } else if (this.f2545e == a.b || this.f2545e == a.c || this.f2545e == a.d) {
            this.f2549i = m.a(intent.getStringExtra(AUDIENCE_NETWORK_UNIQUE_ID_EXTRA));
            if (this.f2549i == null) {
                com.facebook.ads.internal.j.b.a(com.facebook.ads.internal.j.a.a(null, "Unable to find view"));
                m3328a("com.facebook.ads.interstitial.error");
                finish();
                return;
            }
            this.f2549i.setListener(new 5(this));
            z = false;
        } else {
            com.facebook.ads.internal.j.b.a(com.facebook.ads.internal.j.a.a(null, "Unable to infer viewType from intent or savedInstanceState"));
            m3328a("com.facebook.ads.interstitial.error");
            finish();
            return;
        }
        this.f2549i.a(intent, bundle, this);
        m3328a("com.facebook.ads.interstitial.displayed");
        this.f2546f = System.currentTimeMillis();
        m3325a(intent, z);
    }

    protected void onDestroy() {
        if (this.f2542b != null) {
            this.f2542b.removeAllViews();
        }
        if (this.f2549i != null) {
            m.a(this.f2549i);
            this.f2549i.onDestroy();
            this.f2549i = null;
        }
        if (this.f2550j != null && com.facebook.ads.internal.l.a.b(this)) {
            this.f2550j.b();
        }
        super.onDestroy();
    }

    public void onPause() {
        this.f2547g += System.currentTimeMillis() - this.f2546f;
        if (this.f2549i != null) {
            this.f2549i.i();
        }
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        this.f2546f = System.currentTimeMillis();
        if (this.f2549i != null) {
            this.f2549i.j();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.f2549i != null) {
            this.f2549i.a(bundle);
        }
        bundle.putInt(PREDEFINED_ORIENTATION_KEY, this.f2543c);
        bundle.putString(AUDIENCE_NETWORK_UNIQUE_ID_EXTRA, this.f2544d);
        bundle.putSerializable(VIEW_TYPE, this.f2545e);
    }

    public void onStart() {
        super.onStart();
        if (this.f2543c != -1) {
            setRequestedOrientation(this.f2543c);
        }
    }

    public void removeBackButtonInterceptor(BackButtonInterceptor backButtonInterceptor) {
        this.f2541a.remove(backButtonInterceptor);
    }
}
