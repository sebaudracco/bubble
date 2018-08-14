package com.google.android.exoplayer2.ui;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayer$EventListener;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.Timeline$Period;
import com.google.android.exoplayer2.Timeline.Window;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.TimeBar.OnScrubListener;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Locale;

public class PlaybackControlView extends FrameLayout {
    public static final ControlDispatcher DEFAULT_CONTROL_DISPATCHER = new C27661();
    public static final int DEFAULT_FAST_FORWARD_MS = 15000;
    public static final int DEFAULT_REWIND_MS = 5000;
    public static final int DEFAULT_SHOW_TIMEOUT_MS = 5000;
    private static final long MAX_POSITION_FOR_SEEK_TO_PREVIOUS = 3000;
    public static final int MAX_WINDOWS_FOR_MULTI_WINDOW_TIME_BAR = 100;
    private long[] adBreakTimesMs;
    private final ComponentListener componentListener;
    private ControlDispatcher controlDispatcher;
    private final TextView durationView;
    private final View fastForwardButton;
    private int fastForwardMs;
    private final StringBuilder formatBuilder;
    private final Formatter formatter;
    private final Runnable hideAction;
    private long hideAtMs;
    private boolean isAttachedToWindow;
    private boolean multiWindowTimeBar;
    private final View nextButton;
    private final View pauseButton;
    private final Timeline$Period period;
    private final View playButton;
    private ExoPlayer player;
    private final TextView positionView;
    private final View previousButton;
    private final View rewindButton;
    private int rewindMs;
    private boolean scrubbing;
    private boolean showMultiWindowTimeBar;
    private int showTimeoutMs;
    private final TimeBar timeBar;
    private final Runnable updateProgressAction;
    private VisibilityListener visibilityListener;
    private final Window window;

    public interface ControlDispatcher {
        boolean dispatchSeekTo(ExoPlayer exoPlayer, int i, long j);

        boolean dispatchSetPlayWhenReady(ExoPlayer exoPlayer, boolean z);
    }

    static class C27661 implements ControlDispatcher {
        C27661() {
        }

        public boolean dispatchSetPlayWhenReady(ExoPlayer player, boolean playWhenReady) {
            player.setPlayWhenReady(playWhenReady);
            return true;
        }

        public boolean dispatchSeekTo(ExoPlayer player, int windowIndex, long positionMs) {
            player.seekTo(windowIndex, positionMs);
            return true;
        }
    }

    class C27672 implements Runnable {
        C27672() {
        }

        public void run() {
            PlaybackControlView.this.updateProgress();
        }
    }

    class C27683 implements Runnable {
        C27683() {
        }

        public void run() {
            PlaybackControlView.this.hide();
        }
    }

    private final class ComponentListener implements ExoPlayer$EventListener, OnScrubListener, OnClickListener {
        private ComponentListener() {
        }

        public void onScrubStart(TimeBar timeBar, long position) {
            PlaybackControlView.this.removeCallbacks(PlaybackControlView.this.hideAction);
            PlaybackControlView.this.scrubbing = true;
        }

        public void onScrubMove(TimeBar timeBar, long position) {
            if (PlaybackControlView.this.positionView != null) {
                PlaybackControlView.this.positionView.setText(Util.getStringForTime(PlaybackControlView.this.formatBuilder, PlaybackControlView.this.formatter, position));
            }
        }

        public void onScrubStop(TimeBar timeBar, long position, boolean canceled) {
            PlaybackControlView.this.scrubbing = false;
            if (!(canceled || PlaybackControlView.this.player == null)) {
                PlaybackControlView.this.seekToTimebarPosition(position);
            }
            PlaybackControlView.this.hideAfterTimeout();
        }

        public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
            PlaybackControlView.this.updatePlayPauseButton();
            PlaybackControlView.this.updateProgress();
        }

        public void onPositionDiscontinuity() {
            PlaybackControlView.this.updateNavigation();
            PlaybackControlView.this.updateProgress();
        }

        public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        }

        public void onTimelineChanged(Timeline timeline, Object manifest) {
            PlaybackControlView.this.updateNavigation();
            PlaybackControlView.this.updateTimeBarMode();
            PlaybackControlView.this.updateProgress();
        }

        public void onLoadingChanged(boolean isLoading) {
        }

        public void onTracksChanged(TrackGroupArray tracks, TrackSelectionArray selections) {
        }

        public void onPlayerError(ExoPlaybackException error) {
        }

        public void onClick(View view) {
            if (PlaybackControlView.this.player != null) {
                if (PlaybackControlView.this.nextButton == view) {
                    PlaybackControlView.this.next();
                } else if (PlaybackControlView.this.previousButton == view) {
                    PlaybackControlView.this.previous();
                } else if (PlaybackControlView.this.fastForwardButton == view) {
                    PlaybackControlView.this.fastForward();
                } else if (PlaybackControlView.this.rewindButton == view) {
                    PlaybackControlView.this.rewind();
                } else if (PlaybackControlView.this.playButton == view) {
                    PlaybackControlView.this.controlDispatcher.dispatchSetPlayWhenReady(PlaybackControlView.this.player, true);
                } else if (PlaybackControlView.this.pauseButton == view) {
                    PlaybackControlView.this.controlDispatcher.dispatchSetPlayWhenReady(PlaybackControlView.this.player, false);
                }
            }
            PlaybackControlView.this.hideAfterTimeout();
        }
    }

    public interface VisibilityListener {
        void onVisibilityChange(int i);
    }

    public PlaybackControlView(Context context) {
        this(context, null);
    }

    public PlaybackControlView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlaybackControlView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.updateProgressAction = new C27672();
        this.hideAction = new C27683();
        int controllerLayoutId = R$layout.exo_playback_control_view;
        this.rewindMs = 5000;
        this.fastForwardMs = 15000;
        this.showTimeoutMs = 5000;
        if (attrs != null) {
            TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PlaybackControlView, 0, 0);
            try {
                this.rewindMs = a.getInt(R.styleable.PlaybackControlView_rewind_increment, this.rewindMs);
                this.fastForwardMs = a.getInt(R.styleable.PlaybackControlView_fastforward_increment, this.fastForwardMs);
                this.showTimeoutMs = a.getInt(R.styleable.PlaybackControlView_show_timeout, this.showTimeoutMs);
                controllerLayoutId = a.getResourceId(R.styleable.PlaybackControlView_controller_layout_id, controllerLayoutId);
            } finally {
                a.recycle();
            }
        }
        this.period = new Timeline$Period();
        this.window = new Window();
        this.formatBuilder = new StringBuilder();
        this.formatter = new Formatter(this.formatBuilder, Locale.getDefault());
        this.adBreakTimesMs = new long[0];
        this.componentListener = new ComponentListener();
        this.controlDispatcher = DEFAULT_CONTROL_DISPATCHER;
        LayoutInflater.from(context).inflate(controllerLayoutId, this);
        setDescendantFocusability(262144);
        this.durationView = (TextView) findViewById(R$id.exo_duration);
        this.positionView = (TextView) findViewById(R$id.exo_position);
        this.timeBar = (TimeBar) findViewById(R$id.exo_progress);
        if (this.timeBar != null) {
            this.timeBar.setListener(this.componentListener);
        }
        this.playButton = findViewById(R$id.exo_play);
        if (this.playButton != null) {
            this.playButton.setOnClickListener(this.componentListener);
        }
        this.pauseButton = findViewById(R$id.exo_pause);
        if (this.pauseButton != null) {
            this.pauseButton.setOnClickListener(this.componentListener);
        }
        this.previousButton = findViewById(R$id.exo_prev);
        if (this.previousButton != null) {
            this.previousButton.setOnClickListener(this.componentListener);
        }
        this.nextButton = findViewById(R$id.exo_next);
        if (this.nextButton != null) {
            this.nextButton.setOnClickListener(this.componentListener);
        }
        this.rewindButton = findViewById(R$id.exo_rew);
        if (this.rewindButton != null) {
            this.rewindButton.setOnClickListener(this.componentListener);
        }
        this.fastForwardButton = findViewById(R$id.exo_ffwd);
        if (this.fastForwardButton != null) {
            this.fastForwardButton.setOnClickListener(this.componentListener);
        }
    }

    public ExoPlayer getPlayer() {
        return this.player;
    }

    public void setPlayer(ExoPlayer player) {
        if (this.player != player) {
            if (this.player != null) {
                this.player.removeListener(this.componentListener);
            }
            this.player = player;
            if (player != null) {
                player.addListener(this.componentListener);
            }
            updateAll();
        }
    }

    public void setShowMultiWindowTimeBar(boolean showMultiWindowTimeBar) {
        this.showMultiWindowTimeBar = showMultiWindowTimeBar;
        updateTimeBarMode();
    }

    public void setVisibilityListener(VisibilityListener listener) {
        this.visibilityListener = listener;
    }

    public void setControlDispatcher(ControlDispatcher controlDispatcher) {
        if (controlDispatcher == null) {
            controlDispatcher = DEFAULT_CONTROL_DISPATCHER;
        }
        this.controlDispatcher = controlDispatcher;
    }

    public void setRewindIncrementMs(int rewindMs) {
        this.rewindMs = rewindMs;
        updateNavigation();
    }

    public void setFastForwardIncrementMs(int fastForwardMs) {
        this.fastForwardMs = fastForwardMs;
        updateNavigation();
    }

    public int getShowTimeoutMs() {
        return this.showTimeoutMs;
    }

    public void setShowTimeoutMs(int showTimeoutMs) {
        this.showTimeoutMs = showTimeoutMs;
    }

    public void show() {
        if (!isVisible()) {
            setVisibility(0);
            if (this.visibilityListener != null) {
                this.visibilityListener.onVisibilityChange(getVisibility());
            }
            updateAll();
            requestPlayPauseFocus();
        }
        hideAfterTimeout();
    }

    public void hide() {
        if (isVisible()) {
            setVisibility(8);
            if (this.visibilityListener != null) {
                this.visibilityListener.onVisibilityChange(getVisibility());
            }
            removeCallbacks(this.updateProgressAction);
            removeCallbacks(this.hideAction);
            this.hideAtMs = -9223372036854775807L;
        }
    }

    public boolean isVisible() {
        return getVisibility() == 0;
    }

    private void hideAfterTimeout() {
        removeCallbacks(this.hideAction);
        if (this.showTimeoutMs > 0) {
            this.hideAtMs = SystemClock.uptimeMillis() + ((long) this.showTimeoutMs);
            if (this.isAttachedToWindow) {
                postDelayed(this.hideAction, (long) this.showTimeoutMs);
                return;
            }
            return;
        }
        this.hideAtMs = -9223372036854775807L;
    }

    private void updateAll() {
        updatePlayPauseButton();
        updateNavigation();
        updateProgress();
    }

    private void updatePlayPauseButton() {
        int i = 8;
        int i2 = 1;
        if (isVisible() && this.isAttachedToWindow) {
            boolean playing;
            boolean requestPlayPauseFocus = false;
            if (this.player == null || !this.player.getPlayWhenReady()) {
                playing = false;
            } else {
                playing = true;
            }
            if (this.playButton != null) {
                int i3;
                if (playing && this.playButton.isFocused()) {
                    i3 = 1;
                } else {
                    i3 = 0;
                }
                requestPlayPauseFocus = false | i3;
                View view = this.playButton;
                if (playing) {
                    i3 = 8;
                } else {
                    i3 = 0;
                }
                view.setVisibility(i3);
            }
            if (this.pauseButton != null) {
                if (playing || !this.pauseButton.isFocused()) {
                    i2 = 0;
                }
                requestPlayPauseFocus |= i2;
                View view2 = this.pauseButton;
                if (playing) {
                    i = 0;
                }
                view2.setVisibility(i);
            }
            if (requestPlayPauseFocus) {
                requestPlayPauseFocus();
            }
        }
    }

    private void updateNavigation() {
        boolean z = true;
        if (isVisible() && this.isAttachedToWindow) {
            boolean haveNonEmptyTimeline;
            boolean z2;
            Timeline timeline = this.player != null ? this.player.getCurrentTimeline() : null;
            if (timeline == null || timeline.isEmpty()) {
                haveNonEmptyTimeline = false;
            } else {
                haveNonEmptyTimeline = true;
            }
            boolean isSeekable = false;
            boolean enablePrevious = false;
            boolean enableNext = false;
            if (haveNonEmptyTimeline) {
                int windowIndex = this.player.getCurrentWindowIndex();
                timeline.getWindow(windowIndex, this.window);
                isSeekable = this.window.isSeekable;
                if (windowIndex > 0 || isSeekable || !this.window.isDynamic) {
                    enablePrevious = true;
                } else {
                    enablePrevious = false;
                }
                if (windowIndex < timeline.getWindowCount() - 1 || this.window.isDynamic) {
                    enableNext = true;
                } else {
                    enableNext = false;
                }
                if (timeline.getPeriod(this.player.getCurrentPeriodIndex(), this.period).isAd) {
                    hide();
                }
            }
            setButtonEnabled(enablePrevious, this.previousButton);
            setButtonEnabled(enableNext, this.nextButton);
            if (this.fastForwardMs <= 0 || !isSeekable) {
                z2 = false;
            } else {
                z2 = true;
            }
            setButtonEnabled(z2, this.fastForwardButton);
            if (this.rewindMs <= 0 || !isSeekable) {
                z = false;
            }
            setButtonEnabled(z, this.rewindButton);
            if (this.timeBar != null) {
                this.timeBar.setEnabled(isSeekable);
            }
        }
    }

    private void updateTimeBarMode() {
        if (this.player != null) {
            boolean z = this.showMultiWindowTimeBar && canShowMultiWindowTimeBar(this.player.getCurrentTimeline(), this.period);
            this.multiWindowTimeBar = z;
        }
    }

    private void updateProgress() {
        if (isVisible() && this.isAttachedToWindow) {
            long position = 0;
            long bufferedPosition = 0;
            long duration = 0;
            if (this.player != null) {
                if (this.multiWindowTimeBar) {
                    Timeline timeline = this.player.getCurrentTimeline();
                    int windowCount = timeline.getWindowCount();
                    int periodIndex = this.player.getCurrentPeriodIndex();
                    long positionUs = 0;
                    long bufferedPositionUs = 0;
                    long durationUs = 0;
                    boolean isInAdBreak = false;
                    boolean isPlayingAd = false;
                    int adBreakCount = 0;
                    for (int i = 0; i < windowCount; i++) {
                        timeline.getWindow(i, this.window);
                        int j = this.window.firstPeriodIndex;
                        while (j <= this.window.lastPeriodIndex) {
                            if (timeline.getPeriod(j, this.period).isAd) {
                                isPlayingAd |= j == periodIndex ? 1 : 0;
                                if (!isInAdBreak) {
                                    isInAdBreak = true;
                                    if (adBreakCount == this.adBreakTimesMs.length) {
                                        this.adBreakTimesMs = Arrays.copyOf(this.adBreakTimesMs, this.adBreakTimesMs.length == 0 ? 1 : this.adBreakTimesMs.length * 2);
                                    }
                                    int adBreakCount2 = adBreakCount + 1;
                                    this.adBreakTimesMs[adBreakCount] = C.usToMs(durationUs);
                                    adBreakCount = adBreakCount2;
                                }
                            } else {
                                isInAdBreak = false;
                                long periodDurationUs = this.period.getDurationUs();
                                Assertions.checkState(periodDurationUs != -9223372036854775807L);
                                long periodDurationInWindowUs = periodDurationUs;
                                if (j == this.window.firstPeriodIndex) {
                                    periodDurationInWindowUs -= this.window.positionInFirstPeriodUs;
                                }
                                if (i < periodIndex) {
                                    positionUs += periodDurationInWindowUs;
                                    bufferedPositionUs += periodDurationInWindowUs;
                                }
                                durationUs += periodDurationInWindowUs;
                            }
                            j++;
                        }
                    }
                    position = C.usToMs(positionUs);
                    bufferedPosition = C.usToMs(bufferedPositionUs);
                    duration = C.usToMs(durationUs);
                    if (!isPlayingAd) {
                        position += this.player.getCurrentPosition();
                        bufferedPosition += this.player.getBufferedPosition();
                    }
                    if (this.timeBar != null) {
                        this.timeBar.setAdBreakTimesMs(this.adBreakTimesMs, adBreakCount);
                    }
                } else {
                    position = this.player.getCurrentPosition();
                    bufferedPosition = this.player.getBufferedPosition();
                    duration = this.player.getDuration();
                }
            }
            if (this.durationView != null) {
                this.durationView.setText(Util.getStringForTime(this.formatBuilder, this.formatter, duration));
            }
            if (!(this.positionView == null || this.scrubbing)) {
                this.positionView.setText(Util.getStringForTime(this.formatBuilder, this.formatter, position));
            }
            if (this.timeBar != null) {
                this.timeBar.setPosition(position);
                this.timeBar.setBufferedPosition(bufferedPosition);
                this.timeBar.setDuration(duration);
            }
            removeCallbacks(this.updateProgressAction);
            int playbackState = this.player == null ? 1 : this.player.getPlaybackState();
            if (playbackState != 1 && playbackState != 4) {
                long delayMs;
                if (this.player.getPlayWhenReady() && playbackState == 3) {
                    delayMs = 1000 - (position % 1000);
                    if (delayMs < 200) {
                        delayMs += 1000;
                    }
                } else {
                    delayMs = 1000;
                }
                postDelayed(this.updateProgressAction, delayMs);
            }
        }
    }

    private void requestPlayPauseFocus() {
        boolean playing = this.player != null && this.player.getPlayWhenReady();
        if (!playing && this.playButton != null) {
            this.playButton.requestFocus();
        } else if (playing && this.pauseButton != null) {
            this.pauseButton.requestFocus();
        }
    }

    private void setButtonEnabled(boolean enabled, View view) {
        if (view != null) {
            view.setEnabled(enabled);
            if (Util.SDK_INT >= 11) {
                setViewAlphaV11(view, enabled ? 1.0f : 0.3f);
                view.setVisibility(0);
                return;
            }
            view.setVisibility(enabled ? 0 : 4);
        }
    }

    @TargetApi(11)
    private void setViewAlphaV11(View view, float alpha) {
        view.setAlpha(alpha);
    }

    private void previous() {
        Timeline timeline = this.player.getCurrentTimeline();
        if (!timeline.isEmpty()) {
            int windowIndex = this.player.getCurrentWindowIndex();
            timeline.getWindow(windowIndex, this.window);
            if (windowIndex <= 0 || (this.player.getCurrentPosition() > MAX_POSITION_FOR_SEEK_TO_PREVIOUS && (!this.window.isDynamic || this.window.isSeekable))) {
                seekTo(0);
            } else {
                seekTo(windowIndex - 1, -9223372036854775807L);
            }
        }
    }

    private void next() {
        Timeline timeline = this.player.getCurrentTimeline();
        if (!timeline.isEmpty()) {
            int windowIndex = this.player.getCurrentWindowIndex();
            if (windowIndex < timeline.getWindowCount() - 1) {
                seekTo(windowIndex + 1, -9223372036854775807L);
            } else if (timeline.getWindow(windowIndex, this.window, false).isDynamic) {
                seekTo(windowIndex, -9223372036854775807L);
            }
        }
    }

    private void rewind() {
        if (this.rewindMs > 0) {
            seekTo(Math.max(this.player.getCurrentPosition() - ((long) this.rewindMs), 0));
        }
    }

    private void fastForward() {
        if (this.fastForwardMs > 0) {
            long durationMs = this.player.getDuration();
            long seekPositionMs = this.player.getCurrentPosition() + ((long) this.fastForwardMs);
            if (durationMs != -9223372036854775807L) {
                seekPositionMs = Math.min(seekPositionMs, durationMs);
            }
            seekTo(seekPositionMs);
        }
    }

    private void seekTo(long positionMs) {
        seekTo(this.player.getCurrentWindowIndex(), positionMs);
    }

    private void seekTo(int windowIndex, long positionMs) {
        if (!this.controlDispatcher.dispatchSeekTo(this.player, windowIndex, positionMs)) {
            updateProgress();
        }
    }

    private void seekToTimebarPosition(long timebarPositionMs) {
        if (this.multiWindowTimeBar) {
            Timeline timeline = this.player.getCurrentTimeline();
            int windowCount = timeline.getWindowCount();
            long remainingMs = timebarPositionMs;
            for (int i = 0; i < windowCount; i++) {
                timeline.getWindow(i, this.window);
                int j = this.window.firstPeriodIndex;
                while (j <= this.window.lastPeriodIndex) {
                    if (!timeline.getPeriod(j, this.period).isAd) {
                        long periodDurationMs = this.period.getDurationMs();
                        if (periodDurationMs == -9223372036854775807L) {
                            throw new IllegalStateException();
                        }
                        if (j == this.window.firstPeriodIndex) {
                            periodDurationMs -= this.window.getPositionInFirstPeriodMs();
                        }
                        if (i == windowCount - 1 && j == this.window.lastPeriodIndex && remainingMs >= periodDurationMs) {
                            seekTo(i, this.window.getDurationMs());
                            return;
                        } else if (remainingMs < periodDurationMs) {
                            seekTo(i, this.period.getPositionInWindowMs() + remainingMs);
                            return;
                        } else {
                            remainingMs -= periodDurationMs;
                        }
                    }
                    j++;
                }
            }
            return;
        }
        seekTo(timebarPositionMs);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.isAttachedToWindow = true;
        if (this.hideAtMs != -9223372036854775807L) {
            long delayMs = this.hideAtMs - SystemClock.uptimeMillis();
            if (delayMs <= 0) {
                hide();
            } else {
                postDelayed(this.hideAction, delayMs);
            }
        }
        updateAll();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.isAttachedToWindow = false;
        removeCallbacks(this.updateProgressAction);
        removeCallbacks(this.hideAction);
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        boolean handled = dispatchMediaKeyEvent(event) || super.dispatchKeyEvent(event);
        if (handled) {
            show();
        }
        return handled;
    }

    public boolean dispatchMediaKeyEvent(KeyEvent event) {
        boolean z = false;
        int keyCode = event.getKeyCode();
        if (this.player == null || !isHandledMediaKey(keyCode)) {
            return false;
        }
        if (event.getAction() == 0) {
            switch (keyCode) {
                case 85:
                    ControlDispatcher controlDispatcher = this.controlDispatcher;
                    ExoPlayer exoPlayer = this.player;
                    if (!this.player.getPlayWhenReady()) {
                        z = true;
                    }
                    controlDispatcher.dispatchSetPlayWhenReady(exoPlayer, z);
                    break;
                case 87:
                    next();
                    break;
                case 88:
                    previous();
                    break;
                case 89:
                    rewind();
                    break;
                case 90:
                    fastForward();
                    break;
                case 126:
                    this.controlDispatcher.dispatchSetPlayWhenReady(this.player, true);
                    break;
                case 127:
                    this.controlDispatcher.dispatchSetPlayWhenReady(this.player, false);
                    break;
            }
        }
        show();
        return true;
    }

    @SuppressLint({"InlinedApi"})
    private static boolean isHandledMediaKey(int keyCode) {
        return keyCode == 90 || keyCode == 89 || keyCode == 85 || keyCode == 126 || keyCode == 127 || keyCode == 87 || keyCode == 88;
    }

    private static boolean canShowMultiWindowTimeBar(Timeline timeline, Timeline$Period period) {
        if (timeline.getWindowCount() > 100) {
            return false;
        }
        int periodCount = timeline.getPeriodCount();
        for (int i = 0; i < periodCount; i++) {
            timeline.getPeriod(i, period);
            if (!period.isAd && period.durationUs == -9223372036854775807L) {
                return false;
            }
        }
        return true;
    }
}
