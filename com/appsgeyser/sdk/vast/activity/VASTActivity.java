package com.appsgeyser.sdk.vast.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.appsgeyser.sdk.utils.vast.Assets;
import com.appsgeyser.sdk.utils.vast.HttpTools;
import com.appsgeyser.sdk.utils.vast.VASTLog;
import com.appsgeyser.sdk.vast.VASTPlayer;
import com.appsgeyser.sdk.vast.model.TRACKING_EVENTS_TYPE;
import com.appsgeyser.sdk.vast.model.VASTModel;
import com.mopub.mobileads.dfp.adapters.MoPubAdapter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class VASTActivity extends Activity implements OnCompletionListener, OnErrorListener, OnPreparedListener, OnVideoSizeChangedListener, Callback {
    private static final long QUARTILE_TIMER_INTERVAL = 250;
    private static final double SKIP_INFO_PADDING_SCALE = 0.1d;
    private static final double SKIP_INFO_SCALE = 0.15d;
    private static String TAG = "VASTActivity";
    private static final long TOOLBAR_HIDE_DELAY = 3000;
    private static final long VIDEO_PROGRESS_TIMER_INTERVAL = 250;
    private RelativeLayout mButtonPanel;
    private ImageButton mCloseButton;
    private int mCurrentVideoPosition;
    private Handler mHandler;
    private ImageButton mInfoButton;
    private boolean mIsCompleted = false;
    private boolean mIsPlayBackError = false;
    private boolean mIsProcessedImpressions = false;
    private boolean mIsVideoPaused = false;
    private final int mMaxProgressTrackingPoints = 20;
    private MediaPlayer mMediaPlayer;
    private RelativeLayout mOverlay;
    private Drawable mPauseDrawable;
    private Drawable mPlayDrawable;
    private ImageButton mPlayPauseButton;
    private ProgressBar mProgressBar;
    private int mQuartile = 0;
    private RelativeLayout mRootLayout;
    private int mScreenHeight;
    private int mScreenWidth;
    private Timer mStartVideoProgressTimer;
    private SurfaceHolder mSurfaceHolder;
    private SurfaceView mSurfaceView;
    private Timer mToolBarTimer;
    private HashMap<TRACKING_EVENTS_TYPE, List<String>> mTrackingEventMap;
    private Timer mTrackingEventTimer;
    private VASTModel mVastModel = null;
    private int mVideoHeight;
    private LinkedList<Integer> mVideoProgressTracker = null;
    private int mVideoWidth;

    class C13221 implements OnClickListener {
        C13221() {
        }

        public void onClick(View v) {
            VASTActivity.this.overlayClicked();
        }
    }

    class C13232 implements OnClickListener {
        C13232() {
        }

        public void onClick(View v) {
            VASTActivity.this.infoClicked();
        }
    }

    class C13243 implements OnClickListener {
        C13243() {
        }

        public void onClick(View v) {
            VASTActivity.this.closeClicked();
        }
    }

    class C13254 implements OnClickListener {
        C13254() {
        }

        public void onClick(View v) {
            VASTActivity.this.playPauseButtonClicked();
        }
    }

    class C13275 extends TimerTask {

        class C13261 implements Runnable {
            C13261() {
            }

            public void run() {
                VASTLog.m2412d(VASTActivity.TAG, "hiding buttons");
                VASTActivity.this.mButtonPanel.setVisibility(8);
            }
        }

        C13275() {
        }

        public void run() {
            VASTActivity.this.mHandler.post(new C13261());
        }
    }

    class C13297 extends TimerTask {
        int maxAmountInList = 19;

        C13297() {
        }

        public void run() {
            if (VASTActivity.this.mMediaPlayer != null) {
                if (VASTActivity.this.mVideoProgressTracker.size() == this.maxAmountInList) {
                    int firstPosition = ((Integer) VASTActivity.this.mVideoProgressTracker.getFirst()).intValue();
                    int lastPosition = ((Integer) VASTActivity.this.mVideoProgressTracker.getLast()).intValue();
                    if (lastPosition > firstPosition) {
                        VASTLog.m2416v(VASTActivity.TAG, "video progressing (position:" + lastPosition + ")");
                        VASTActivity.this.mVideoProgressTracker.removeFirst();
                    } else {
                        VASTLog.m2413e(VASTActivity.TAG, "detected video hang");
                        VASTActivity.this.mIsPlayBackError = true;
                        VASTActivity.this.stopVideoProgressTimer();
                        VASTActivity.this.processErrorEvent();
                        VASTActivity.this.closeClicked();
                        VASTActivity.this.finishVAST();
                    }
                }
                try {
                    VASTActivity.this.mVideoProgressTracker.addLast(Integer.valueOf(VASTActivity.this.mMediaPlayer.getCurrentPosition()));
                } catch (Exception e) {
                }
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        VASTLog.m2412d(TAG, "in onCreate");
        super.onCreate(savedInstanceState);
        int currentOrientation = getResources().getConfiguration().orientation;
        VASTLog.m2412d(TAG, "currentOrientation:" + currentOrientation);
        if (currentOrientation != 2) {
            VASTLog.m2412d(TAG, "Orientation is not landscape.....forcing landscape");
            setRequestedOrientation(0);
            return;
        }
        VASTLog.m2412d(TAG, "orientation is landscape");
        this.mVastModel = (VASTModel) getIntent().getSerializableExtra("com.nexage.android.vast.player.vastModel");
        if (this.mVastModel == null) {
            VASTLog.m2413e(TAG, "vastModel is null. Stopping activity.");
            finishVAST();
            return;
        }
        hideTitleStatusBars();
        this.mHandler = new Handler();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.mScreenWidth = displayMetrics.widthPixels;
        this.mScreenHeight = displayMetrics.heightPixels;
        this.mTrackingEventMap = this.mVastModel.getTrackingUrls();
        createUIComponents();
    }

    protected void onStart() {
        VASTLog.m2412d(TAG, "entered onStart --(life cycle event)");
        super.onStart();
    }

    protected void onResume() {
        VASTLog.m2412d(TAG, "entered on onResume --(life cycle event)");
        super.onResume();
    }

    protected void onStop() {
        VASTLog.m2412d(TAG, "entered on onStop --(life cycle event)");
        super.onStop();
    }

    protected void onRestart() {
        VASTLog.m2412d(TAG, "entered on onRestart --(life cycle event)");
        super.onRestart();
        createMediaPlayer();
    }

    protected void onPause() {
        VASTLog.m2412d(TAG, "entered on onPause --(life cycle event)");
        super.onPause();
        if (this.mMediaPlayer != null) {
            this.mCurrentVideoPosition = this.mMediaPlayer.getCurrentPosition();
        }
        cleanActivityUp();
    }

    protected void onDestroy() {
        VASTLog.m2412d(TAG, "entered on onDestroy --(life cycle event)");
        super.onDestroy();
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        VASTLog.m2412d(TAG, "entered onSaveInstanceState ");
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        VASTLog.m2412d(TAG, "in onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void hideTitleStatusBars() {
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
    }

    private void createUIComponents() {
        LayoutParams params = new LayoutParams(-1, -1);
        createRootLayout(params);
        createSurface(params);
        createMediaPlayer();
        createOverlay(params);
        createButtonPanel(this.mScreenWidth, this.mScreenHeight);
        int size = (int) (SKIP_INFO_SCALE * ((double) Math.min(this.mScreenWidth, this.mScreenHeight)));
        createPlayPauseButton(size);
        createCloseButton(size);
        createInfoButton(size);
        setContentView(this.mRootLayout);
        createProgressBar();
    }

    private void createProgressBar() {
        LayoutParams params = new LayoutParams(-1, -2);
        params.addRule(13);
        this.mProgressBar = new ProgressBar(this);
        this.mProgressBar.setLayoutParams(params);
        this.mRootLayout.addView(this.mProgressBar);
        this.mProgressBar.setVisibility(8);
    }

    private void showProgressBar() {
        this.mProgressBar.setVisibility(0);
    }

    private void hideProgressBar() {
        this.mProgressBar.setVisibility(8);
    }

    private void createRootLayout(LayoutParams params) {
        this.mRootLayout = new RelativeLayout(this);
        this.mRootLayout.setLayoutParams(params);
        this.mRootLayout.setPadding(0, 0, 0, 0);
        this.mRootLayout.setBackgroundColor(-16777216);
    }

    private void createSurface(LayoutParams params) {
        this.mSurfaceView = new SurfaceView(this);
        this.mSurfaceView.setLayoutParams(params);
        this.mSurfaceHolder = this.mSurfaceView.getHolder();
        this.mSurfaceHolder.addCallback(this);
        this.mSurfaceHolder.setType(3);
        this.mRootLayout.addView(this.mSurfaceView);
    }

    private void createMediaPlayer() {
        this.mMediaPlayer = new MediaPlayer();
        this.mMediaPlayer.setOnCompletionListener(this);
        this.mMediaPlayer.setOnErrorListener(this);
        this.mMediaPlayer.setOnPreparedListener(this);
        this.mMediaPlayer.setOnVideoSizeChangedListener(this);
        this.mMediaPlayer.setAudioStreamType(3);
    }

    private void createOverlay(LayoutParams params) {
        this.mOverlay = new RelativeLayout(this);
        this.mOverlay.setLayoutParams(params);
        this.mOverlay.setPadding(0, 0, 0, 0);
        this.mOverlay.setBackgroundColor(0);
        this.mOverlay.setOnClickListener(new C13221());
        this.mRootLayout.addView(this.mOverlay);
    }

    private void createButtonPanel(int screenWidth, int screenHeight) {
        LayoutParams params = new LayoutParams(-1, -2);
        params.addRule(12);
        this.mButtonPanel = new RelativeLayout(this);
        this.mButtonPanel.setLayoutParams(params);
        int padding = (int) (SKIP_INFO_PADDING_SCALE * ((double) Math.min(screenWidth, screenHeight)));
        this.mButtonPanel.setPadding(padding, 0, padding, 0);
        this.mButtonPanel.setBackgroundColor(-16777216);
        this.mButtonPanel.setVisibility(8);
        this.mOverlay.addView(this.mButtonPanel);
    }

    private void createInfoButton(int size) {
        String clickThroughUrl = this.mVastModel.getVideoClicks().getClickThrough();
        if (clickThroughUrl != null && clickThroughUrl.length() > 0) {
            LayoutParams params = new LayoutParams(size, size);
            params.addRule(0, 22);
            this.mInfoButton = new ImageButton(this);
            this.mInfoButton.setImageDrawable(Assets.getDrawableFromBase64(getResources(), Assets.info));
            this.mInfoButton.setLayoutParams(params);
            this.mInfoButton.setScaleType(ScaleType.CENTER_CROP);
            this.mInfoButton.setPadding(0, 0, 0, 0);
            this.mInfoButton.setBackgroundColor(0);
            this.mInfoButton.setEnabled(true);
            this.mInfoButton.setVisibility(0);
            this.mInfoButton.setOnClickListener(new C13232());
            this.mButtonPanel.addView(this.mInfoButton);
        }
    }

    private void createCloseButton(int size) {
        LayoutParams params = new LayoutParams(size, size);
        params.addRule(11);
        this.mCloseButton = new ImageButton(this);
        this.mCloseButton.setId(22);
        this.mCloseButton.setImageDrawable(Assets.getDrawableFromBase64(getResources(), Assets.exit));
        this.mCloseButton.setLayoutParams(params);
        this.mCloseButton.setScaleType(ScaleType.CENTER_CROP);
        this.mCloseButton.setPadding(0, 0, 0, 0);
        this.mCloseButton.setBackgroundColor(0);
        this.mCloseButton.setVisibility(0);
        this.mCloseButton.setOnClickListener(new C13243());
        this.mButtonPanel.addView(this.mCloseButton);
    }

    private void createPlayPauseButton(int size) {
        LayoutParams params = new LayoutParams(size, size);
        params.addRule(9);
        this.mPauseDrawable = Assets.getDrawableFromBase64(getResources(), Assets.pause);
        this.mPlayDrawable = Assets.getDrawableFromBase64(getResources(), Assets.play);
        this.mPlayPauseButton = new ImageButton(this);
        this.mPlayPauseButton.setImageDrawable(this.mPauseDrawable);
        this.mPlayPauseButton.setLayoutParams(params);
        this.mPlayPauseButton.setScaleType(ScaleType.CENTER_CROP);
        this.mPlayPauseButton.setPadding(0, 0, 0, 0);
        this.mPlayPauseButton.setBackgroundColor(0);
        this.mPlayPauseButton.setEnabled(true);
        this.mPlayPauseButton.setVisibility(0);
        this.mPlayPauseButton.setOnClickListener(new C13254());
        this.mButtonPanel.addView(this.mPlayPauseButton);
    }

    private void infoClicked() {
        VASTLog.m2412d(TAG, "entered infoClicked:");
        activateButtons(false);
        if (this.mMediaPlayer.isPlaying()) {
            this.mMediaPlayer.pause();
            this.mCurrentVideoPosition = this.mMediaPlayer.getCurrentPosition();
        }
        processClickThroughEvent();
    }

    private void activateButtons(boolean active) {
        VASTLog.m2412d(TAG, "entered activateButtons:");
        if (active) {
            this.mButtonPanel.setVisibility(0);
        } else {
            this.mButtonPanel.setVisibility(8);
        }
    }

    private void processClickThroughEvent() {
        VASTLog.m2412d(TAG, "entered processClickThroughEvent:");
        if (VASTPlayer.listener != null) {
            VASTPlayer.listener.vastClick();
        }
        String clickThroughUrl = this.mVastModel.getVideoClicks().getClickThrough();
        VASTLog.m2412d(TAG, "clickThrough url: " + clickThroughUrl);
        fireUrls(this.mVastModel.getVideoClicks().getClickTracking());
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(clickThroughUrl));
            if (getPackageManager().resolveActivity(intent, 32) == null) {
                VASTLog.m2413e(TAG, "Clickthrough error occured, uri unresolvable");
                if (((double) this.mCurrentVideoPosition) >= ((double) this.mMediaPlayer.getCurrentPosition()) * 0.99d) {
                    this.mMediaPlayer.start();
                }
                activateButtons(true);
                return;
            }
            startActivity(intent);
        } catch (NullPointerException e) {
            VASTLog.m2414e(TAG, e.getMessage(), e);
        }
    }

    private void closeClicked() {
        VASTLog.m2412d(TAG, "entered closeClicked()");
        cleanActivityUp();
        if (!this.mIsPlayBackError) {
            processEvent(TRACKING_EVENTS_TYPE.close);
        }
        finishVAST();
        VASTLog.m2412d(TAG, "leaving closeClicked()");
    }

    private void playPauseButtonClicked() {
        VASTLog.m2412d(TAG, "entered playPauseClicked");
        if (this.mMediaPlayer == null) {
            VASTLog.m2413e(TAG, "mMediaPlayer is null when playPauseButton was clicked");
            return;
        }
        boolean isPlaying = this.mMediaPlayer.isPlaying();
        VASTLog.m2412d(TAG, "isPlaying:" + isPlaying);
        if (isPlaying) {
            processPauseSteps();
        } else if (this.mIsVideoPaused) {
            processPlaySteps();
            if (!this.mIsCompleted) {
                processEvent(TRACKING_EVENTS_TYPE.resume);
            }
        } else {
            processPlaySteps();
            this.mQuartile = 0;
            startQuartileTimer();
        }
    }

    private void processPauseSteps() {
        this.mIsVideoPaused = true;
        this.mMediaPlayer.pause();
        stopVideoProgressTimer();
        stopToolBarTimer();
        this.mPlayPauseButton.setImageDrawable(this.mPlayDrawable);
        if (!this.mIsCompleted) {
            processEvent(TRACKING_EVENTS_TYPE.pause);
        }
    }

    private void processPlaySteps() {
        this.mIsVideoPaused = false;
        this.mMediaPlayer.start();
        this.mPlayPauseButton.setImageDrawable(this.mPauseDrawable);
        startToolBarTimer();
        startVideoProgressTimer();
    }

    public void onBackPressed() {
        VASTLog.m2412d(TAG, "entered onBackPressed");
        closeClicked();
    }

    public void surfaceCreated(SurfaceHolder holder) {
        VASTLog.m2412d(TAG, "surfaceCreated -- (SurfaceHolder callback)");
        try {
            if (this.mMediaPlayer == null) {
                createMediaPlayer();
            }
            showProgressBar();
            this.mMediaPlayer.setDisplay(this.mSurfaceHolder);
            String url = this.mVastModel.getPickedMediaFileURL();
            VASTLog.m2412d(TAG, "URL for media file:" + url);
            this.mMediaPlayer.setDataSource(url);
            this.mMediaPlayer.prepareAsync();
        } catch (Exception e) {
            VASTLog.m2414e(TAG, e.getMessage(), e);
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int arg1, int arg2, int arg3) {
        VASTLog.m2412d(TAG, "entered surfaceChanged -- (SurfaceHolder callback)");
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        VASTLog.m2412d(TAG, "entered surfaceDestroyed -- (SurfaceHolder callback)");
        cleanUpMediaPlayer();
    }

    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
        VASTLog.m2412d(TAG, "entered onVideoSizeChanged -- (MediaPlayer callback)");
        this.mVideoWidth = width;
        this.mVideoHeight = height;
        VASTLog.m2412d(TAG, "video size: " + this.mVideoWidth + "x" + this.mVideoHeight);
    }

    public void onPrepared(MediaPlayer mp) {
        VASTLog.m2412d(TAG, "entered onPrepared called --(MediaPlayer callback) ....about to play");
        calculateAspectRatio();
        this.mMediaPlayer.start();
        hideProgressBar();
        if (this.mIsVideoPaused) {
            VASTLog.m2412d(TAG, "pausing video");
            this.mMediaPlayer.pause();
        } else {
            startVideoProgressTimer();
        }
        VASTLog.m2412d(TAG, "current location in video:" + this.mCurrentVideoPosition);
        if (this.mCurrentVideoPosition > 0) {
            VASTLog.m2412d(TAG, "seeking to location:" + this.mCurrentVideoPosition);
            this.mMediaPlayer.seekTo(this.mCurrentVideoPosition);
        }
        if (!this.mIsProcessedImpressions) {
            processImpressions();
        }
        startQuartileTimer();
        startToolBarTimer();
        if (!this.mMediaPlayer.isPlaying() && !this.mIsVideoPaused) {
            this.mMediaPlayer.start();
        }
    }

    private void calculateAspectRatio() {
        VASTLog.m2412d(TAG, "entered calculateAspectRatio");
        if (this.mVideoWidth == 0 || this.mVideoHeight == 0) {
            VASTLog.m2417w(TAG, "mVideoWidth or mVideoHeight is 0, skipping calculateAspectRatio");
            return;
        }
        VASTLog.m2412d(TAG, "calculating aspect ratio");
        double widthRatio = (MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE * ((double) this.mScreenWidth)) / ((double) this.mVideoWidth);
        double heightRatio = (MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE * ((double) this.mScreenHeight)) / ((double) this.mVideoHeight);
        double scale = Math.min(widthRatio, heightRatio);
        int surfaceWidth = (int) (((double) this.mVideoWidth) * scale);
        int surfaceHeight = (int) (((double) this.mVideoHeight) * scale);
        LayoutParams params = new LayoutParams(surfaceWidth, surfaceHeight);
        params.addRule(13);
        this.mSurfaceView.setLayoutParams(params);
        this.mSurfaceHolder.setFixedSize(surfaceWidth, surfaceHeight);
        VASTLog.m2412d(TAG, " screen size: " + this.mScreenWidth + "x" + this.mScreenHeight);
        VASTLog.m2412d(TAG, " video size:  " + this.mVideoWidth + "x" + this.mVideoHeight);
        VASTLog.m2412d(TAG, " widthRatio:   " + widthRatio);
        VASTLog.m2412d(TAG, " heightRatio:   " + heightRatio);
        VASTLog.m2412d(TAG, "surface size: " + surfaceWidth + "x" + surfaceHeight);
    }

    private void cleanActivityUp() {
        cleanUpMediaPlayer();
        stopQuartileTimer();
        stopVideoProgressTimer();
        stopToolBarTimer();
    }

    private void cleanUpMediaPlayer() {
        VASTLog.m2412d(TAG, "entered cleanUpMediaPlayer ");
        if (this.mMediaPlayer != null) {
            if (this.mMediaPlayer.isPlaying()) {
                this.mMediaPlayer.stop();
            }
            this.mMediaPlayer.setOnCompletionListener(null);
            this.mMediaPlayer.setOnErrorListener(null);
            this.mMediaPlayer.setOnPreparedListener(null);
            this.mMediaPlayer.setOnVideoSizeChangedListener(null);
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
        }
    }

    public boolean onError(MediaPlayer mp, int what, int extra) {
        VASTLog.m2413e(TAG, "entered onError -- (MediaPlayer callback)");
        this.mIsPlayBackError = true;
        VASTLog.m2413e(TAG, "Shutting down Activity due to Media Player errors: WHAT:" + what + ": EXTRA:" + extra + ":");
        processErrorEvent();
        closeClicked();
        return true;
    }

    private void processErrorEvent() {
        VASTLog.m2412d(TAG, "entered processErrorEvent");
        fireUrls(this.mVastModel.getErrorUrl());
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        VASTLog.m2412d(TAG, "entered onCOMPLETION -- (MediaPlayer callback)");
        stopVideoProgressTimer();
        stopToolBarTimer();
        this.mButtonPanel.setVisibility(0);
        this.mPlayPauseButton.setImageDrawable(this.mPlayDrawable);
        if (!this.mIsPlayBackError && !this.mIsCompleted) {
            this.mIsCompleted = true;
            processEvent(TRACKING_EVENTS_TYPE.complete);
            if (VASTPlayer.listener != null) {
                VASTPlayer.listener.vastComplete();
            }
        }
    }

    private void overlayClicked() {
        startToolBarTimer();
    }

    private void processImpressions() {
        VASTLog.m2412d(TAG, "entered processImpressions");
        this.mIsProcessedImpressions = true;
        fireUrls(this.mVastModel.getImpressions());
    }

    private void fireUrls(List<String> urls) {
        VASTLog.m2412d(TAG, "entered fireUrls");
        if (urls != null) {
            for (String url : urls) {
                VASTLog.m2416v(TAG, "\tfiring url:" + url);
                HttpTools.httpGetURL(url);
            }
            return;
        }
        VASTLog.m2412d(TAG, "\turl list is null");
    }

    private void startToolBarTimer() {
        VASTLog.m2412d(TAG, "entered startToolBarTimer");
        if (this.mQuartile != 4) {
            if (this.mMediaPlayer != null && this.mMediaPlayer.isPlaying()) {
                stopToolBarTimer();
                this.mToolBarTimer = new Timer();
                this.mToolBarTimer.schedule(new C13275(), TOOLBAR_HIDE_DELAY);
                this.mButtonPanel.setVisibility(0);
            }
            if (this.mIsVideoPaused) {
                activateButtons(true);
            }
        }
    }

    private void stopToolBarTimer() {
        VASTLog.m2412d(TAG, "entered stopToolBarTimer");
        if (this.mToolBarTimer != null) {
            this.mToolBarTimer.cancel();
            this.mToolBarTimer = null;
        }
    }

    private void startQuartileTimer() {
        VASTLog.m2412d(TAG, "entered startQuartileTimer");
        stopQuartileTimer();
        if (this.mIsCompleted) {
            VASTLog.m2412d(TAG, "ending quartileTimer becuase the video has been replayed");
            return;
        }
        final int videoDuration = this.mMediaPlayer.getDuration();
        this.mTrackingEventTimer = new Timer();
        this.mTrackingEventTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                try {
                    int curPos = VASTActivity.this.mMediaPlayer.getCurrentPosition();
                    if (curPos != 0) {
                        int percentage = (curPos * 100) / videoDuration;
                        if (percentage >= VASTActivity.this.mQuartile * 25) {
                            if (VASTActivity.this.mQuartile == 0) {
                                VASTLog.m2415i(VASTActivity.TAG, "Video at start: (" + percentage + "%)");
                                VASTActivity.this.processEvent(TRACKING_EVENTS_TYPE.start);
                            } else if (VASTActivity.this.mQuartile == 1) {
                                VASTLog.m2415i(VASTActivity.TAG, "Video at first quartile: (" + percentage + "%)");
                                VASTActivity.this.processEvent(TRACKING_EVENTS_TYPE.firstQuartile);
                            } else if (VASTActivity.this.mQuartile == 2) {
                                VASTLog.m2415i(VASTActivity.TAG, "Video at midpoint: (" + percentage + "%)");
                                VASTActivity.this.processEvent(TRACKING_EVENTS_TYPE.midpoint);
                            } else if (VASTActivity.this.mQuartile == 3) {
                                VASTLog.m2415i(VASTActivity.TAG, "Video at third quartile: (" + percentage + "%)");
                                VASTActivity.this.processEvent(TRACKING_EVENTS_TYPE.thirdQuartile);
                                VASTActivity.this.stopQuartileTimer();
                            }
                            VASTActivity.this.mQuartile = VASTActivity.this.mQuartile + 1;
                        }
                    }
                } catch (Exception e) {
                    VASTLog.m2417w(VASTActivity.TAG, "mediaPlayer.getCurrentPosition exception: " + e.getMessage());
                    cancel();
                }
            }
        }, 0, 250);
    }

    private void stopQuartileTimer() {
        if (this.mTrackingEventTimer != null) {
            this.mTrackingEventTimer.cancel();
            this.mTrackingEventTimer = null;
        }
    }

    private void startVideoProgressTimer() {
        VASTLog.m2412d(TAG, "entered startVideoProgressTimer");
        this.mStartVideoProgressTimer = new Timer();
        this.mVideoProgressTracker = new LinkedList();
        this.mStartVideoProgressTimer.schedule(new C13297(), 0, 250);
    }

    private void stopVideoProgressTimer() {
        VASTLog.m2412d(TAG, "entered stopVideoProgressTimer");
        if (this.mStartVideoProgressTimer != null) {
            this.mStartVideoProgressTimer.cancel();
        }
    }

    private void processEvent(TRACKING_EVENTS_TYPE eventName) {
        VASTLog.m2415i(TAG, "entered Processing Event: " + eventName);
        fireUrls((List) this.mTrackingEventMap.get(eventName));
    }

    private void finishVAST() {
        VASTPlayer.listener.vastDismiss();
        finish();
    }
}
