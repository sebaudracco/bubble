package com.mopub.nativeads;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Surface;
import android.view.TextureView;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayer$EventListener;
import com.google.android.exoplayer2.ExoPlayer$ExoPlayerMessage;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Renderer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.audio.MediaCodecAudioRenderer;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.mp4.Mp4Extractor;
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSource.Factory;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.video.MediaCodecVideoRenderer;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.event.BaseEvent.Category;
import com.mopub.common.event.BaseEvent.Name;
import com.mopub.common.event.BaseEvent.SamplingRate;
import com.mopub.common.event.Event;
import com.mopub.common.event.EventDetails;
import com.mopub.common.event.MoPubEvents;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.VastVideoConfig;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NativeVideoController implements ExoPlayer$EventListener, OnAudioFocusChangeListener {
    private static final int BUFFER_SEGMENT_COUNT = 32;
    private static final int BUFFER_SEGMENT_SIZE = 65536;
    public static final long RESUME_FINISHED_THRESHOLD = 750;
    public static final int STATE_BUFFERING = 2;
    public static final int STATE_CLEARED = 5;
    public static final int STATE_ENDED = 4;
    public static final int STATE_IDLE = 1;
    public static final int STATE_READY = 3;
    @NonNull
    private static final Map<Long, NativeVideoController> sManagerMap = new HashMap(4);
    private boolean mAppAudioEnabled;
    private boolean mAudioEnabled;
    @NonNull
    private AudioManager mAudioManager;
    @Nullable
    private MediaCodecAudioRenderer mAudioRenderer;
    @NonNull
    private final Context mContext;
    @Nullable
    private EventDetails mEventDetails;
    @Nullable
    private volatile ExoPlayer mExoPlayer;
    private boolean mExoPlayerStateStartedFromIdle;
    @Nullable
    private BitmapDrawable mFinalFrame;
    @NonNull
    private final Handler mHandler;
    @Nullable
    private Listener mListener;
    @NonNull
    private final MoPubExoPlayerFactory mMoPubExoPlayerFactory;
    @NonNull
    private NativeVideoProgressRunnable mNativeVideoProgressRunnable;
    @Nullable
    private OnAudioFocusChangeListener mOnAudioFocusChangeListener;
    @Nullable
    private WeakReference<Object> mOwnerRef;
    private boolean mPlayWhenReady;
    private int mPreviousExoPlayerState;
    @Nullable
    private Surface mSurface;
    @Nullable
    private TextureView mTextureView;
    @NonNull
    private VastVideoConfig mVastVideoConfig;
    @Nullable
    private MediaCodecVideoRenderer mVideoRenderer;

    class C37481 implements Factory {
        C37481() {
        }

        public DataSource createDataSource() {
            return new HttpDiskCompositeDataSource(NativeVideoController.this.mContext, "exo_demo", NativeVideoController.this.mEventDetails);
        }
    }

    class C37492 implements ExtractorsFactory {
        C37492() {
        }

        public Extractor[] createExtractors() {
            return new Extractor[]{new Mp4Extractor()};
        }
    }

    public interface Listener {
        void onError(Exception exception);

        void onStateChanged(boolean z, int i);
    }

    @VisibleForTesting
    static class MoPubExoPlayerFactory {
        MoPubExoPlayerFactory() {
        }

        public ExoPlayer newInstance(@NonNull Renderer[] renderers, @NonNull TrackSelector trackSelector, @Nullable LoadControl loadControl) {
            return ExoPlayerFactory.newInstance(renderers, trackSelector, loadControl);
        }
    }

    static class VisibilityTrackingEvent {
        boolean isTracked;
        int minimumPercentageVisible;
        Integer minimumVisiblePx;
        OnTrackedStrategy strategy;
        int totalQualifiedPlayCounter;
        int totalRequiredPlayTimeMs;

        interface OnTrackedStrategy {
            void execute();
        }

        VisibilityTrackingEvent() {
        }
    }

    @NonNull
    public static NativeVideoController createForId(long id, @NonNull Context context, @NonNull List<VisibilityTrackingEvent> visibilityTrackingEvents, @NonNull VastVideoConfig vastVideoConfig, @Nullable EventDetails eventDetails) {
        NativeVideoController nvc = new NativeVideoController(context, visibilityTrackingEvents, vastVideoConfig, eventDetails);
        sManagerMap.put(Long.valueOf(id), nvc);
        return nvc;
    }

    @NonNull
    @VisibleForTesting
    public static NativeVideoController createForId(long id, @NonNull Context context, @NonNull VastVideoConfig vastVideoConfig, @NonNull NativeVideoProgressRunnable nativeVideoProgressRunnable, @NonNull MoPubExoPlayerFactory moPubExoPlayerFactory, @Nullable EventDetails eventDetails, @NonNull AudioManager audioManager) {
        NativeVideoController nvc = new NativeVideoController(context, vastVideoConfig, nativeVideoProgressRunnable, moPubExoPlayerFactory, eventDetails, audioManager);
        sManagerMap.put(Long.valueOf(id), nvc);
        return nvc;
    }

    @VisibleForTesting
    public static void setForId(long id, @NonNull NativeVideoController nativeVideoController) {
        sManagerMap.put(Long.valueOf(id), nativeVideoController);
    }

    @Nullable
    public static NativeVideoController getForId(long id) {
        return (NativeVideoController) sManagerMap.get(Long.valueOf(id));
    }

    @Nullable
    public static NativeVideoController remove(long id) {
        return (NativeVideoController) sManagerMap.remove(Long.valueOf(id));
    }

    private NativeVideoController(@NonNull Context context, @NonNull List<VisibilityTrackingEvent> visibilityTrackingEvents, @NonNull VastVideoConfig vastVideoConfig, @Nullable EventDetails eventDetails) {
        this(context, vastVideoConfig, new NativeVideoProgressRunnable(context, new Handler(Looper.getMainLooper()), visibilityTrackingEvents, vastVideoConfig), new MoPubExoPlayerFactory(), eventDetails, (AudioManager) context.getSystemService("audio"));
    }

    private NativeVideoController(@NonNull Context context, @NonNull VastVideoConfig vastVideoConfig, @NonNull NativeVideoProgressRunnable nativeVideoProgressRunnable, @NonNull MoPubExoPlayerFactory moPubExoPlayerFactory, @Nullable EventDetails eventDetails, @NonNull AudioManager audioManager) {
        this.mPreviousExoPlayerState = 1;
        this.mExoPlayerStateStartedFromIdle = true;
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(vastVideoConfig);
        Preconditions.checkNotNull(moPubExoPlayerFactory);
        Preconditions.checkNotNull(audioManager);
        this.mContext = context.getApplicationContext();
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mVastVideoConfig = vastVideoConfig;
        this.mNativeVideoProgressRunnable = nativeVideoProgressRunnable;
        this.mMoPubExoPlayerFactory = moPubExoPlayerFactory;
        this.mEventDetails = eventDetails;
        this.mAudioManager = audioManager;
    }

    public void setListener(@Nullable Listener listener) {
        this.mListener = listener;
    }

    public void setProgressListener(@Nullable C3750x4df381db progressListener) {
        this.mNativeVideoProgressRunnable.setProgressListener(progressListener);
    }

    public void setOnAudioFocusChangeListener(@Nullable OnAudioFocusChangeListener onAudioFocusChangeListener) {
        this.mOnAudioFocusChangeListener = onAudioFocusChangeListener;
    }

    public void setPlayWhenReady(boolean playWhenReady) {
        if (this.mPlayWhenReady != playWhenReady) {
            this.mPlayWhenReady = playWhenReady;
            setExoPlayWhenReady();
        }
    }

    public int getPlaybackState() {
        if (this.mExoPlayer == null) {
            return 5;
        }
        return this.mExoPlayer.getPlaybackState();
    }

    public void setAudioEnabled(boolean audioEnabled) {
        this.mAudioEnabled = audioEnabled;
        setExoAudio();
    }

    public void setAppAudioEnabled(boolean audioEnabled) {
        if (this.mAppAudioEnabled != audioEnabled) {
            this.mAppAudioEnabled = audioEnabled;
            if (this.mAppAudioEnabled) {
                this.mAudioManager.requestAudioFocus(this, 3, 1);
            } else {
                this.mAudioManager.abandonAudioFocus(this);
            }
        }
    }

    public void setAudioVolume(float volume) {
        if (this.mAudioEnabled) {
            setExoAudio(volume);
        }
    }

    public void onAudioFocusChange(int focusChange) {
        if (this.mOnAudioFocusChangeListener != null) {
            this.mOnAudioFocusChangeListener.onAudioFocusChange(focusChange);
        }
    }

    public void setTextureView(@NonNull TextureView textureView) {
        Preconditions.checkNotNull(textureView);
        this.mSurface = new Surface(textureView.getSurfaceTexture());
        this.mTextureView = textureView;
        this.mNativeVideoProgressRunnable.setTextureView(this.mTextureView);
        setExoSurface(this.mSurface);
    }

    public void prepare(@NonNull Object owner) {
        Preconditions.checkNotNull(owner);
        this.mOwnerRef = new WeakReference(owner);
        clearExistingPlayer();
        preparePlayer();
        setExoSurface(this.mSurface);
    }

    public void clear() {
        setPlayWhenReady(false);
        this.mSurface = null;
        clearExistingPlayer();
    }

    public void release(@NonNull Object owner) {
        Preconditions.checkNotNull(owner);
        if ((this.mOwnerRef == null ? null : this.mOwnerRef.get()) == owner) {
            clearExistingPlayer();
        }
    }

    public void onTimelineChanged(Timeline timeline, Object manifest) {
    }

    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
    }

    public void onLoadingChanged(boolean isLoading) {
    }

    public void onPlayerStateChanged(boolean playWhenReady, int newState) {
        if (newState == 4 && this.mFinalFrame == null) {
            if (this.mExoPlayer == null || this.mSurface == null || this.mTextureView == null) {
                MoPubLog.m12069w("onPlayerStateChanged called afer view has been recycled.");
                return;
            } else {
                this.mFinalFrame = new BitmapDrawable(this.mContext.getResources(), this.mTextureView.getBitmap());
                this.mNativeVideoProgressRunnable.requestStop();
            }
        }
        if (this.mPreviousExoPlayerState == 3 && newState == 2) {
            MoPubEvents.log(Event.createEventFromDetails(Name.DOWNLOAD_BUFFERING, Category.NATIVE_VIDEO, SamplingRate.NATIVE_VIDEO, this.mEventDetails));
        }
        if (this.mExoPlayerStateStartedFromIdle && this.mPreviousExoPlayerState == 2 && newState == 3) {
            MoPubEvents.log(Event.createEventFromDetails(Name.DOWNLOAD_VIDEO_READY, Category.NATIVE_VIDEO, SamplingRate.NATIVE_VIDEO, this.mEventDetails));
        }
        this.mPreviousExoPlayerState = newState;
        if (newState == 3) {
            this.mExoPlayerStateStartedFromIdle = false;
        } else if (newState == 1) {
            this.mExoPlayerStateStartedFromIdle = true;
        }
        if (this.mListener != null) {
            this.mListener.onStateChanged(playWhenReady, newState);
        }
    }

    public void seekTo(long ms) {
        if (this.mExoPlayer != null) {
            this.mExoPlayer.seekTo(ms);
            this.mNativeVideoProgressRunnable.seekTo(ms);
        }
    }

    public long getCurrentPosition() {
        return this.mNativeVideoProgressRunnable.getCurrentPosition();
    }

    public long getDuration() {
        return this.mNativeVideoProgressRunnable.getDuration();
    }

    public void onPlayerError(ExoPlaybackException e) {
        if (this.mListener != null) {
            MoPubEvents.log(Event.createEventFromDetails(Name.ERROR_DURING_PLAYBACK, Category.NATIVE_VIDEO, SamplingRate.NATIVE_VIDEO, this.mEventDetails));
            this.mListener.onError(e);
            this.mNativeVideoProgressRunnable.requestStop();
        }
    }

    public void onPositionDiscontinuity() {
    }

    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
    }

    public void handleCtaClick(@NonNull Context context) {
        triggerImpressionTrackers();
        this.mVastVideoConfig.handleClickWithoutResult(context, 0);
    }

    public boolean hasFinalFrame() {
        return this.mFinalFrame != null;
    }

    @Nullable
    public Drawable getFinalFrame() {
        return this.mFinalFrame;
    }

    void triggerImpressionTrackers() {
        this.mNativeVideoProgressRunnable.checkImpressionTrackers(true);
    }

    private void clearExistingPlayer() {
        if (this.mExoPlayer != null) {
            setExoSurface(null);
            this.mExoPlayer.stop();
            this.mExoPlayer.release();
            this.mExoPlayer = null;
            this.mNativeVideoProgressRunnable.stop();
            this.mNativeVideoProgressRunnable.setExoPlayer(null);
        }
    }

    private void preparePlayer() {
        if (this.mExoPlayer == null) {
            this.mVideoRenderer = new MediaCodecVideoRenderer(this.mContext, MediaCodecSelector.DEFAULT, 0, this.mHandler, null, 10);
            this.mAudioRenderer = new MediaCodecAudioRenderer(MediaCodecSelector.DEFAULT);
            DefaultAllocator allocator = new DefaultAllocator(true, 65536, 32);
            this.mExoPlayer = this.mMoPubExoPlayerFactory.newInstance(new Renderer[]{this.mVideoRenderer, this.mAudioRenderer}, new DefaultTrackSelector(), new DefaultLoadControl(allocator));
            this.mNativeVideoProgressRunnable.setExoPlayer(this.mExoPlayer);
            this.mExoPlayer.addListener(this);
            this.mExoPlayer.prepare(new ExtractorMediaSource(Uri.parse(this.mVastVideoConfig.getNetworkMediaFileUrl()), new C37481(), new C37492(), this.mHandler, null));
            this.mNativeVideoProgressRunnable.startRepeating(50);
        }
        setExoAudio();
        setExoPlayWhenReady();
    }

    private void setExoPlayWhenReady() {
        if (this.mExoPlayer != null) {
            this.mExoPlayer.setPlayWhenReady(this.mPlayWhenReady);
        }
    }

    private void setExoAudio() {
        setExoAudio(this.mAudioEnabled ? 1.0f : 0.0f);
    }

    private void setExoAudio(float volume) {
        boolean z = volume >= 0.0f && volume <= 1.0f;
        Preconditions.checkArgument(z);
        if (this.mExoPlayer != null) {
            this.mExoPlayer.sendMessages(new ExoPlayer$ExoPlayerMessage[]{new ExoPlayer$ExoPlayerMessage(this.mAudioRenderer, 2, Float.valueOf(volume))});
        }
    }

    private void setExoSurface(@Nullable Surface surface) {
        if (this.mExoPlayer != null) {
            this.mExoPlayer.sendMessages(new ExoPlayer$ExoPlayerMessage[]{new ExoPlayer$ExoPlayerMessage(this.mVideoRenderer, 1, surface)});
        }
    }
}
