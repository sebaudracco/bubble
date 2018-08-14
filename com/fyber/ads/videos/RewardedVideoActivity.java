package com.fyber.ads.videos;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Window;
import com.fyber.Fyber;
import com.fyber.ads.videos.p093a.C2446e;
import com.fyber.ads.videos.p093a.C2446e.C2470a;
import com.fyber.ads.videos.p093a.C2469d;
import com.fyber.mediation.MediationUserActivityListener;
import com.fyber.requesters.p097a.p098a.C2602f;
import com.fyber.utils.C2656g;
import com.fyber.utils.FyberLogger;
import com.fyber.utils.StringUtils;

public class RewardedVideoActivity extends Activity implements C2446e {
    public static final String ENGAGEMENT_STATUS = "ENGAGEMENT_STATUS";
    public static final String PLAY_EXCHANGE_AD_KEY_BUNDLE = "PLAY_EXCHANGE_AD_KEY_BUNDLE";
    public static final String REQUEST_STATUS_PARAMETER_ABORTED_VALUE = "CLOSE_ABORTED";
    public static final String REQUEST_STATUS_PARAMETER_ERROR = "ERROR";
    public static final String REQUEST_STATUS_PARAMETER_FINISHED_VALUE = "CLOSE_FINISHED";
    private boolean f6122a = false;
    private boolean f6123b = false;
    private boolean f6124c = true;
    private MediationUserActivityListener f6125d;
    private boolean f6126e = false;
    private IntentFilter f6127f = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
    private BroadcastReceiver f6128g = new C24441(this);

    class C24441 extends BroadcastReceiver {
        final /* synthetic */ RewardedVideoActivity f6120a;

        C24441(RewardedVideoActivity rewardedVideoActivity) {
            this.f6120a = rewardedVideoActivity;
        }

        public final void onReceive(Context context, Intent intent) {
            if (!C2656g.m8482a(context).m8499a()) {
                C2469d.f6162a.m7851g();
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        String string;
        super.onCreate(bundle);
        Window window = getWindow();
        window.requestFeature(1);
        window.setFlags(1024, 1024);
        window.addFlags(128);
        registerReceiver(this.f6128g, this.f6127f);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            string = extras.getString("REQUEST_AGENT_CACHE_KEY");
            if (!StringUtils.notNullNorEmpty(string)) {
                string = "";
            }
        } else {
            string = "";
        }
        if (C2469d.f6162a.m7847c()) {
            C2602f a;
            if (bundle != null) {
                this.f6122a = bundle.getBoolean("PENDING_CLOSE");
                this.f6123b = bundle.getBoolean("ENGAGEMENT_ALREADY_CLOSE");
            }
            this.f6124c = getIntent().getBooleanExtra(PLAY_EXCHANGE_AD_KEY_BUNDLE, true);
            if (StringUtils.notNullNorEmpty(string)) {
                a = Fyber.getConfigs().m7603d().m8388a(string);
            } else {
                a = null;
            }
            if (this.f6124c) {
                setRequestedOrientation(6);
            }
            C2469d.f6162a.m7844a((C2446e) this);
            C2469d.f6162a.m7843a(this, this.f6124c, a);
            return;
        }
        FyberLogger.m8448d("RewardedVideoActivity", "Currently it is not possible to show offers. Make sure you have requested offers.");
        setResultAndClose(REQUEST_STATUS_PARAMETER_ERROR);
    }

    protected void onResume() {
        super.onResume();
        C2469d.f6162a.m7842a(true);
        if (this.f6122a) {
            C2469d.f6162a.m7839a();
        } else if (this.f6124c) {
            C2469d.f6162a.m7844a((C2446e) this);
            C2469d.f6162a.m7850f();
        }
    }

    protected void onPause() {
        super.onPause();
        C2469d.f6162a.m7842a(false);
        if (!this.f6122a && this.f6124c && !this.f6123b) {
            C2469d.f6162a.m7849e();
            C2469d.f6162a.m7839a();
            C2469d.f6162a.m7844a(null);
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("PENDING_CLOSE", this.f6122a);
        bundle.putBoolean("ENGAGEMENT_ALREADY_CLOSE", this.f6123b);
    }

    protected void onDestroy() {
        unregisterReceiver(this.f6128g);
        this.f6125d = null;
        super.onDestroy();
    }

    protected void closeActivity() {
        this.f6123b = true;
        C2469d.f6162a.m7844a(null);
        finish();
    }

    public void didChangeStatus(C2470a c2470a) {
        switch (c2470a) {
            case CLOSE_FINISHED:
                setResultAndClose(REQUEST_STATUS_PARAMETER_FINISHED_VALUE);
                return;
            case CLOSE_ABORTED:
                setResultAndClose(REQUEST_STATUS_PARAMETER_ABORTED_VALUE);
                return;
            case ERROR:
                setResultAndClose(REQUEST_STATUS_PARAMETER_ERROR);
                return;
            case PENDING_CLOSE:
                this.f6122a = true;
                return;
            case STARTED:
                this.f6126e = true;
                return;
            default:
                return;
        }
    }

    protected void setResultAndClose(String str) {
        Intent intent = new Intent();
        intent.putExtra(ENGAGEMENT_STATUS, str);
        setResult(-1, intent);
        closeActivity();
    }

    public void setRewardedVideoListener(MediationUserActivityListener mediationUserActivityListener) {
        if (this.f6125d == null) {
            this.f6125d = mediationUserActivityListener;
        }
    }

    protected void onUserLeaveHint() {
        if (this.f6125d != null) {
            this.f6125d.notifyOnUserLeft();
        }
        this.f6126e = true;
        super.onUserLeaveHint();
    }

    public void onBackPressed() {
        if (!this.f6126e) {
            return;
        }
        if (this.f6125d != null && this.f6125d.notifyOnBackPressed()) {
            return;
        }
        if (this.f6124c) {
            C2469d.f6162a.m7839a();
        } else {
            super.onBackPressed();
        }
    }
}
