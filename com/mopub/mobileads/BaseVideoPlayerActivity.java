package com.mopub.mobileads;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.mopub.common.AdType;
import com.mopub.common.Constants;
import com.mopub.common.DataKeys;
import com.mopub.common.FullAdType;
import com.mopub.common.logging.MoPubLog;

public class BaseVideoPlayerActivity extends Activity {
    public static final String VIDEO_CLASS_EXTRAS_KEY = "video_view_class_name";
    public static final String VIDEO_URL = "video_url";

    public static void startMraid(Context context, String videoUrl) {
        try {
            context.startActivity(createIntentMraid(context, videoUrl));
        } catch (ActivityNotFoundException e) {
            MoPubLog.m12061d("Activity MraidVideoPlayerActivity not found. Did you declare it in your AndroidManifest.xml?");
        }
    }

    static Intent createIntentMraid(Context context, String videoUrl) {
        Intent intentVideoPlayerActivity = new Intent(context, MraidVideoPlayerActivity.class);
        intentVideoPlayerActivity.setFlags(ErrorDialogData.BINDER_CRASH);
        intentVideoPlayerActivity.putExtra(VIDEO_CLASS_EXTRAS_KEY, AdType.MRAID);
        intentVideoPlayerActivity.putExtra(VIDEO_URL, videoUrl);
        return intentVideoPlayerActivity;
    }

    static void startVast(Context context, VastVideoConfig vastVideoConfig, long broadcastIdentifier) {
        try {
            context.startActivity(createIntentVast(context, vastVideoConfig, broadcastIdentifier));
        } catch (ActivityNotFoundException e) {
            MoPubLog.m12061d("Activity MraidVideoPlayerActivity not found. Did you declare it in your AndroidManifest.xml?");
        }
    }

    static Intent createIntentVast(Context context, VastVideoConfig vastVideoConfig, long broadcastIdentifier) {
        Intent intentVideoPlayerActivity = new Intent(context, MraidVideoPlayerActivity.class);
        intentVideoPlayerActivity.setFlags(ErrorDialogData.BINDER_CRASH);
        intentVideoPlayerActivity.putExtra(VIDEO_CLASS_EXTRAS_KEY, FullAdType.VAST);
        intentVideoPlayerActivity.putExtra("vast_video_config", vastVideoConfig);
        intentVideoPlayerActivity.putExtra(DataKeys.BROADCAST_IDENTIFIER_KEY, broadcastIdentifier);
        return intentVideoPlayerActivity;
    }

    public static void startNativeVideo(Context context, long nativeVideoId, VastVideoConfig vastVideoConfig) {
        try {
            context.startActivity(createIntentNativeVideo(context, nativeVideoId, vastVideoConfig));
        } catch (ActivityNotFoundException e) {
            MoPubLog.m12061d("Activity MraidVideoPlayerActivity not found. Did you declare it in your AndroidManifest.xml?");
        }
    }

    public static Intent createIntentNativeVideo(Context context, long nativeVideoId, VastVideoConfig vastVideoConfig) {
        Intent intentVideoPlayerActivity = new Intent(context, MraidVideoPlayerActivity.class);
        intentVideoPlayerActivity.setFlags(ErrorDialogData.BINDER_CRASH);
        intentVideoPlayerActivity.putExtra(VIDEO_CLASS_EXTRAS_KEY, "native");
        intentVideoPlayerActivity.putExtra(Constants.NATIVE_VIDEO_ID, nativeVideoId);
        intentVideoPlayerActivity.putExtra(Constants.NATIVE_VAST_VIDEO_CONFIG, vastVideoConfig);
        return intentVideoPlayerActivity;
    }

    protected void onDestroy() {
        super.onDestroy();
        AudioManager am = (AudioManager) getSystemService("audio");
        if (am != null) {
            am.abandonAudioFocus(null);
        }
    }
}
