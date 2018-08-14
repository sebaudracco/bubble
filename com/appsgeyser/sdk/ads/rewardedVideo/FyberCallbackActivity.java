package com.appsgeyser.sdk.ads.rewardedVideo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.fyber.ads.interstitials.InterstitialActivity;
import com.fyber.ads.interstitials.InterstitialAdCloseReason;
import com.fyber.ads.videos.RewardedVideoActivity;
import de.greenrobot.event.EventBus;

public class FyberCallbackActivity extends AppCompatActivity {
    private static final String ADS_TYPE_EXTRA = "ads_type_extra";
    public static final String FULLSCREEN_TYPE = "fullscreen_type";
    private static final String INTENT_EXTRA = "intent_to_start";
    public static final int REQUEST_CODE = 11111;
    public static final String REWARDED_TYPE = "rewarded_type";

    public static class fyberInterstitialClickedEvent {
    }

    public static class fyberInterstitialClosedEvent {
    }

    public static class fyberInterstitialErrorEvent {
        public final String errorMessage;

        public fyberInterstitialErrorEvent(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }

    public static class fyberInterstitialOpenedEvent {
    }

    public static class fyberVideoClosedEvent {
    }

    public static class fyberVideoErrorEvent {
        public final String errorMessage;

        public fyberVideoErrorEvent(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }

    public static class fyberVideoFinishedEvent {
    }

    public static class fyberVideoOpenedEvent {
    }

    public static void start(Context context, Intent intent, String adsType) {
        Intent fyberActivityIntent = new Intent(context, FyberCallbackActivity.class);
        fyberActivityIntent.putExtra(INTENT_EXTRA, intent);
        fyberActivityIntent.putExtra(ADS_TYPE_EXTRA, adsType);
        context.startActivity(fyberActivityIntent);
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intentToStart = (Intent) getIntent().getParcelableExtra(INTENT_EXTRA);
        if (getIntent().getStringExtra(ADS_TYPE_EXTRA).equals(REWARDED_TYPE)) {
            EventBus.getDefault().post(new fyberVideoOpenedEvent());
        }
        if (getIntent().getStringExtra(ADS_TYPE_EXTRA).equals(FULLSCREEN_TYPE)) {
            EventBus.getDefault().post(new fyberInterstitialOpenedEvent());
        }
        startActivityForResult(intentToStart, REQUEST_CODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Object obj = -1;
        if (requestCode == REQUEST_CODE && resultCode == -1) {
            if (getIntent().getStringExtra(ADS_TYPE_EXTRA).equals(REWARDED_TYPE)) {
                String engagementResult = data.getStringExtra(RewardedVideoActivity.ENGAGEMENT_STATUS);
                switch (engagementResult.hashCode()) {
                    case 66247144:
                        if (engagementResult.equals(RewardedVideoActivity.REQUEST_STATUS_PARAMETER_ERROR)) {
                            obj = 2;
                            break;
                        }
                        break;
                    case 1107354696:
                        if (engagementResult.equals(RewardedVideoActivity.REQUEST_STATUS_PARAMETER_ABORTED_VALUE)) {
                            obj = 1;
                            break;
                        }
                        break;
                    case 1972965113:
                        if (engagementResult.equals(RewardedVideoActivity.REQUEST_STATUS_PARAMETER_FINISHED_VALUE)) {
                            obj = null;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case null:
                        EventBus.getDefault().post(new fyberVideoFinishedEvent());
                        EventBus.getDefault().post(new fyberVideoClosedEvent());
                        break;
                    case 1:
                        EventBus.getDefault().post(new fyberVideoClosedEvent());
                        break;
                    case 2:
                        EventBus.getDefault().post(new fyberVideoErrorEvent("Can't show fyber video"));
                        break;
                }
            } else if (getIntent().getStringExtra(ADS_TYPE_EXTRA).equals(FULLSCREEN_TYPE)) {
                InterstitialAdCloseReason adStatus = (InterstitialAdCloseReason) data.getSerializableExtra(InterstitialActivity.AD_STATUS);
                if (adStatus.equals(InterstitialAdCloseReason.ReasonUserClickedOnAd)) {
                    EventBus.getDefault().post(new fyberInterstitialClickedEvent());
                    EventBus.getDefault().post(new fyberInterstitialClosedEvent());
                } else if (adStatus.equals(InterstitialAdCloseReason.ReasonUserClosedAd)) {
                    EventBus.getDefault().post(new fyberInterstitialClosedEvent());
                } else if (adStatus.equals(InterstitialAdCloseReason.ReasonError)) {
                    EventBus.getDefault().post(new fyberInterstitialErrorEvent("Fyber interstitial error"));
                } else if (adStatus.equals(InterstitialAdCloseReason.ReasonUnknown)) {
                    EventBus.getDefault().post(new fyberInterstitialClosedEvent());
                }
            }
        }
        finish();
    }
}
