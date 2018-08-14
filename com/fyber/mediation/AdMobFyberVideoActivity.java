package com.fyber.mediation;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import com.fyber.ads.videos.RewardedVideoActivity;
import com.fyber.mediation.model.FyberAdapterModel;

public class AdMobFyberVideoActivity extends RewardedVideoActivity {
    private static final String TAG = AdMobFyberVideoActivity.class.getSimpleName();

    class C25661 implements Runnable {
        C25661() {
        }

        public void run() {
            FyberAdapter fyberAdapter = FyberAdapterModel.getInstance().getFyberAdapter();
            if (fyberAdapter != null) {
                fyberAdapter.mMediationAdListener.onAdClosed(fyberAdapter);
            } else {
                Log.w(AdMobFyberVideoActivity.TAG, "FyberAdapter instance is null!");
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new FrameLayout(this));
        FyberAdapter fyberAdapter = FyberAdapterModel.getInstance().getFyberAdapter();
        if (fyberAdapter != null) {
            fyberAdapter.mMediationAdListener.onAdOpened(fyberAdapter);
            fyberAdapter.mMediationAdListener.onVideoStarted(fyberAdapter);
            return;
        }
        Log.w(TAG, "FyberAdapter instance is null!");
    }

    protected void setResultAndClose(String status) {
        super.setResultAndClose(status);
        runOnUiThread(new C25661());
    }
}
