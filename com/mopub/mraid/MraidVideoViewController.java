package com.mopub.mraid;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.StateListDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.VideoView;
import com.mopub.common.util.Dips;
import com.mopub.common.util.Drawables;
import com.mopub.mobileads.BaseVideoPlayerActivity;
import com.mopub.mobileads.BaseVideoViewController;
import com.mopub.mobileads.BaseVideoViewController.BaseVideoViewControllerListener;

public class MraidVideoViewController extends BaseVideoViewController {
    private static final float CLOSE_BUTTON_PADDING = 8.0f;
    private static final float CLOSE_BUTTON_SIZE = 50.0f;
    private int mButtonPadding;
    private int mButtonSize;
    private ImageButton mCloseButton;
    private final VideoView mVideoView;

    class C37161 implements OnCompletionListener {
        C37161() {
        }

        public void onCompletion(MediaPlayer mp) {
            MraidVideoViewController.this.mCloseButton.setVisibility(0);
            MraidVideoViewController.this.videoCompleted(true);
        }
    }

    class C37172 implements OnErrorListener {
        C37172() {
        }

        public boolean onError(MediaPlayer mediaPlayer, int what, int extra) {
            MraidVideoViewController.this.mCloseButton.setVisibility(0);
            MraidVideoViewController.this.videoError(false);
            return false;
        }
    }

    class C37183 implements OnClickListener {
        C37183() {
        }

        public void onClick(View v) {
            MraidVideoViewController.this.getBaseVideoViewControllerListener().onFinish();
        }
    }

    public MraidVideoViewController(Context context, Bundle intentExtras, Bundle savedInstanceState, BaseVideoViewControllerListener baseVideoViewControllerListener) {
        super(context, null, baseVideoViewControllerListener);
        this.mVideoView = new VideoView(context);
        this.mVideoView.setOnCompletionListener(new C37161());
        this.mVideoView.setOnErrorListener(new C37172());
        this.mVideoView.setVideoPath(intentExtras.getString(BaseVideoPlayerActivity.VIDEO_URL));
    }

    protected void onCreate() {
        super.onCreate();
        this.mButtonSize = Dips.asIntPixels(CLOSE_BUTTON_SIZE, getContext());
        this.mButtonPadding = Dips.asIntPixels(8.0f, getContext());
        createInterstitialCloseButton();
        this.mCloseButton.setVisibility(8);
        this.mVideoView.start();
    }

    protected VideoView getVideoView() {
        return this.mVideoView;
    }

    protected void onDestroy() {
    }

    protected void onPause() {
    }

    protected void onResume() {
    }

    protected void onSaveInstanceState(@NonNull Bundle outState) {
    }

    protected void onConfigurationChanged(Configuration newConfig) {
    }

    protected void onBackPressed() {
    }

    private void createInterstitialCloseButton() {
        this.mCloseButton = new ImageButton(getContext());
        StateListDrawable states = new StateListDrawable();
        states.addState(new int[]{-16842919}, Drawables.INTERSTITIAL_CLOSE_BUTTON_NORMAL.createDrawable(getContext()));
        states.addState(new int[]{16842919}, Drawables.INTERSTITIAL_CLOSE_BUTTON_PRESSED.createDrawable(getContext()));
        this.mCloseButton.setImageDrawable(states);
        this.mCloseButton.setBackgroundDrawable(null);
        this.mCloseButton.setOnClickListener(new C37183());
        LayoutParams buttonLayout = new LayoutParams(this.mButtonSize, this.mButtonSize);
        buttonLayout.addRule(11);
        buttonLayout.setMargins(this.mButtonPadding, 0, this.mButtonPadding, 0);
        getLayout().addView(this.mCloseButton, buttonLayout);
    }
}
