package com.google.android.exoplayer2;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.exoplayer2.ExoPlayerImplInternal.PlaybackInfo;
import com.google.android.exoplayer2.ExoPlayerImplInternal.SourceInfo;
import com.google.android.exoplayer2.Timeline.Window;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectorResult;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

final class ExoPlayerImpl implements ExoPlayer {
    private static final String TAG = "ExoPlayerImpl";
    private final TrackSelectionArray emptyTrackSelections;
    private final Handler eventHandler;
    private final ExoPlayerImplInternal internalPlayer;
    private boolean isLoading;
    private final CopyOnWriteArraySet<ExoPlayer$EventListener> listeners;
    private Object manifest;
    private int maskingPeriodIndex;
    private int maskingWindowIndex;
    private long maskingWindowPositionMs;
    private int pendingPrepareAcks;
    private int pendingSeekAcks;
    private final Timeline$Period period;
    private boolean playWhenReady;
    private PlaybackInfo playbackInfo;
    private PlaybackParameters playbackParameters;
    private int playbackState;
    private final Renderer[] renderers;
    private Timeline timeline;
    private TrackGroupArray trackGroups;
    private TrackSelectionArray trackSelections;
    private final TrackSelector trackSelector;
    private boolean tracksSelected;
    private final Window window;

    @SuppressLint({"HandlerLeak"})
    public ExoPlayerImpl(Renderer[] renderers, TrackSelector trackSelector, LoadControl loadControl) {
        boolean z;
        Log.i(TAG, "Init ExoPlayerLib/2.4.4 [" + Util.DEVICE_DEBUG_INFO + "]");
        if (renderers.length > 0) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkState(z);
        this.renderers = (Renderer[]) Assertions.checkNotNull(renderers);
        this.trackSelector = (TrackSelector) Assertions.checkNotNull(trackSelector);
        this.playWhenReady = false;
        this.playbackState = 1;
        this.listeners = new CopyOnWriteArraySet();
        this.emptyTrackSelections = new TrackSelectionArray(new TrackSelection[renderers.length]);
        this.timeline = Timeline.EMPTY;
        this.window = new Window();
        this.period = new Timeline$Period();
        this.trackGroups = TrackGroupArray.EMPTY;
        this.trackSelections = this.emptyTrackSelections;
        this.playbackParameters = PlaybackParameters.DEFAULT;
        this.eventHandler = new Handler(Looper.myLooper() != null ? Looper.myLooper() : Looper.getMainLooper()) {
            public void handleMessage(Message msg) {
                ExoPlayerImpl.this.handleEvent(msg);
            }
        };
        this.playbackInfo = new PlaybackInfo(0, 0);
        this.internalPlayer = new ExoPlayerImplInternal(renderers, trackSelector, loadControl, this.playWhenReady, this.eventHandler, this.playbackInfo, this);
    }

    public void addListener(ExoPlayer$EventListener listener) {
        this.listeners.add(listener);
    }

    public void removeListener(ExoPlayer$EventListener listener) {
        this.listeners.remove(listener);
    }

    public int getPlaybackState() {
        return this.playbackState;
    }

    public void prepare(MediaSource mediaSource) {
        prepare(mediaSource, true, true);
    }

    public void prepare(MediaSource mediaSource, boolean resetPosition, boolean resetState) {
        if (resetState) {
            Iterator it;
            if (!(this.timeline.isEmpty() && this.manifest == null)) {
                this.timeline = Timeline.EMPTY;
                this.manifest = null;
                it = this.listeners.iterator();
                while (it.hasNext()) {
                    ((ExoPlayer$EventListener) it.next()).onTimelineChanged(this.timeline, this.manifest);
                }
            }
            if (this.tracksSelected) {
                this.tracksSelected = false;
                this.trackGroups = TrackGroupArray.EMPTY;
                this.trackSelections = this.emptyTrackSelections;
                this.trackSelector.onSelectionActivated(null);
                it = this.listeners.iterator();
                while (it.hasNext()) {
                    ((ExoPlayer$EventListener) it.next()).onTracksChanged(this.trackGroups, this.trackSelections);
                }
            }
        }
        this.pendingPrepareAcks++;
        this.internalPlayer.prepare(mediaSource, resetPosition);
    }

    public void setPlayWhenReady(boolean playWhenReady) {
        if (this.playWhenReady != playWhenReady) {
            this.playWhenReady = playWhenReady;
            this.internalPlayer.setPlayWhenReady(playWhenReady);
            Iterator it = this.listeners.iterator();
            while (it.hasNext()) {
                ((ExoPlayer$EventListener) it.next()).onPlayerStateChanged(playWhenReady, this.playbackState);
            }
        }
    }

    public boolean getPlayWhenReady() {
        return this.playWhenReady;
    }

    public boolean isLoading() {
        return this.isLoading;
    }

    public void seekToDefaultPosition() {
        seekToDefaultPosition(getCurrentWindowIndex());
    }

    public void seekToDefaultPosition(int windowIndex) {
        seekTo(windowIndex, -9223372036854775807L);
    }

    public void seekTo(long positionMs) {
        seekTo(getCurrentWindowIndex(), positionMs);
    }

    public void seekTo(int windowIndex, long positionMs) {
        if (windowIndex < 0 || (!this.timeline.isEmpty() && windowIndex >= this.timeline.getWindowCount())) {
            throw new IllegalSeekPositionException(this.timeline, windowIndex, positionMs);
        }
        this.pendingSeekAcks++;
        this.maskingWindowIndex = windowIndex;
        if (this.timeline.isEmpty()) {
            this.maskingPeriodIndex = 0;
        } else {
            long resolvedPositionMs;
            this.timeline.getWindow(windowIndex, this.window);
            if (positionMs == -9223372036854775807L) {
                resolvedPositionMs = this.window.getDefaultPositionUs();
            } else {
                resolvedPositionMs = positionMs;
            }
            int periodIndex = this.window.firstPeriodIndex;
            long periodPositionUs = this.window.getPositionInFirstPeriodUs() + C.msToUs(resolvedPositionMs);
            long periodDurationUs = this.timeline.getPeriod(periodIndex, this.period).getDurationUs();
            while (periodDurationUs != -9223372036854775807L && periodPositionUs >= periodDurationUs && periodIndex < this.window.lastPeriodIndex) {
                periodPositionUs -= periodDurationUs;
                periodIndex++;
                periodDurationUs = this.timeline.getPeriod(periodIndex, this.period).getDurationUs();
            }
            this.maskingPeriodIndex = periodIndex;
        }
        if (positionMs == -9223372036854775807L) {
            this.maskingWindowPositionMs = 0;
            this.internalPlayer.seekTo(this.timeline, windowIndex, -9223372036854775807L);
            return;
        }
        this.maskingWindowPositionMs = positionMs;
        this.internalPlayer.seekTo(this.timeline, windowIndex, C.msToUs(positionMs));
        Iterator it = this.listeners.iterator();
        while (it.hasNext()) {
            ((ExoPlayer$EventListener) it.next()).onPositionDiscontinuity();
        }
    }

    public void setPlaybackParameters(@Nullable PlaybackParameters playbackParameters) {
        if (playbackParameters == null) {
            playbackParameters = PlaybackParameters.DEFAULT;
        }
        this.internalPlayer.setPlaybackParameters(playbackParameters);
    }

    public PlaybackParameters getPlaybackParameters() {
        return this.playbackParameters;
    }

    public void stop() {
        this.internalPlayer.stop();
    }

    public void release() {
        this.internalPlayer.release();
        this.eventHandler.removeCallbacksAndMessages(null);
    }

    public void sendMessages(ExoPlayer$ExoPlayerMessage... messages) {
        this.internalPlayer.sendMessages(messages);
    }

    public void blockingSendMessages(ExoPlayer$ExoPlayerMessage... messages) {
        this.internalPlayer.blockingSendMessages(messages);
    }

    public int getCurrentPeriodIndex() {
        if (this.timeline.isEmpty() || this.pendingSeekAcks > 0) {
            return this.maskingPeriodIndex;
        }
        return this.playbackInfo.periodIndex;
    }

    public int getCurrentWindowIndex() {
        if (this.timeline.isEmpty() || this.pendingSeekAcks > 0) {
            return this.maskingWindowIndex;
        }
        return this.timeline.getPeriod(this.playbackInfo.periodIndex, this.period).windowIndex;
    }

    public long getDuration() {
        if (this.timeline.isEmpty()) {
            return -9223372036854775807L;
        }
        return this.timeline.getWindow(getCurrentWindowIndex(), this.window).getDurationMs();
    }

    public long getCurrentPosition() {
        if (this.timeline.isEmpty() || this.pendingSeekAcks > 0) {
            return this.maskingWindowPositionMs;
        }
        this.timeline.getPeriod(this.playbackInfo.periodIndex, this.period);
        return this.period.getPositionInWindowMs() + C.usToMs(this.playbackInfo.positionUs);
    }

    public long getBufferedPosition() {
        if (this.timeline.isEmpty() || this.pendingSeekAcks > 0) {
            return this.maskingWindowPositionMs;
        }
        this.timeline.getPeriod(this.playbackInfo.periodIndex, this.period);
        return this.period.getPositionInWindowMs() + C.usToMs(this.playbackInfo.bufferedPositionUs);
    }

    public int getBufferedPercentage() {
        int i = 100;
        if (this.timeline.isEmpty()) {
            return 0;
        }
        long position = getBufferedPosition();
        long duration = getDuration();
        if (position == -9223372036854775807L || duration == -9223372036854775807L) {
            i = 0;
        } else if (duration != 0) {
            i = Util.constrainValue((int) ((100 * position) / duration), 0, 100);
        }
        return i;
    }

    public boolean isCurrentWindowDynamic() {
        return !this.timeline.isEmpty() && this.timeline.getWindow(getCurrentWindowIndex(), this.window).isDynamic;
    }

    public boolean isCurrentWindowSeekable() {
        return !this.timeline.isEmpty() && this.timeline.getWindow(getCurrentWindowIndex(), this.window).isSeekable;
    }

    public int getRendererCount() {
        return this.renderers.length;
    }

    public int getRendererType(int index) {
        return this.renderers[index].getTrackType();
    }

    public TrackGroupArray getCurrentTrackGroups() {
        return this.trackGroups;
    }

    public TrackSelectionArray getCurrentTrackSelections() {
        return this.trackSelections;
    }

    public Timeline getCurrentTimeline() {
        return this.timeline;
    }

    public Object getCurrentManifest() {
        return this.manifest;
    }

    void handleEvent(Message msg) {
        boolean z = true;
        Iterator it;
        switch (msg.what) {
            case 0:
                this.pendingPrepareAcks--;
                return;
            case 1:
                this.playbackState = msg.arg1;
                it = this.listeners.iterator();
                while (it.hasNext()) {
                    ((ExoPlayer$EventListener) it.next()).onPlayerStateChanged(this.playWhenReady, this.playbackState);
                }
                return;
            case 2:
                if (msg.arg1 == 0) {
                    z = false;
                }
                this.isLoading = z;
                it = this.listeners.iterator();
                while (it.hasNext()) {
                    ((ExoPlayer$EventListener) it.next()).onLoadingChanged(this.isLoading);
                }
                return;
            case 3:
                if (this.pendingPrepareAcks == 0) {
                    TrackSelectorResult trackSelectorResult = msg.obj;
                    this.tracksSelected = true;
                    this.trackGroups = trackSelectorResult.groups;
                    this.trackSelections = trackSelectorResult.selections;
                    this.trackSelector.onSelectionActivated(trackSelectorResult.info);
                    it = this.listeners.iterator();
                    while (it.hasNext()) {
                        ((ExoPlayer$EventListener) it.next()).onTracksChanged(this.trackGroups, this.trackSelections);
                    }
                    return;
                }
                return;
            case 4:
                int i = this.pendingSeekAcks - 1;
                this.pendingSeekAcks = i;
                if (i == 0) {
                    this.playbackInfo = (PlaybackInfo) msg.obj;
                    if (msg.arg1 != 0) {
                        it = this.listeners.iterator();
                        while (it.hasNext()) {
                            ((ExoPlayer$EventListener) it.next()).onPositionDiscontinuity();
                        }
                        return;
                    }
                    return;
                }
                return;
            case 5:
                if (this.pendingSeekAcks == 0) {
                    this.playbackInfo = (PlaybackInfo) msg.obj;
                    it = this.listeners.iterator();
                    while (it.hasNext()) {
                        ((ExoPlayer$EventListener) it.next()).onPositionDiscontinuity();
                    }
                    return;
                }
                return;
            case 6:
                SourceInfo sourceInfo = msg.obj;
                this.pendingSeekAcks -= sourceInfo.seekAcks;
                if (this.pendingPrepareAcks == 0) {
                    this.timeline = sourceInfo.timeline;
                    this.manifest = sourceInfo.manifest;
                    this.playbackInfo = sourceInfo.playbackInfo;
                    it = this.listeners.iterator();
                    while (it.hasNext()) {
                        ((ExoPlayer$EventListener) it.next()).onTimelineChanged(this.timeline, this.manifest);
                    }
                    return;
                }
                return;
            case 7:
                PlaybackParameters playbackParameters = msg.obj;
                if (!this.playbackParameters.equals(playbackParameters)) {
                    this.playbackParameters = playbackParameters;
                    it = this.listeners.iterator();
                    while (it.hasNext()) {
                        ((ExoPlayer$EventListener) it.next()).onPlaybackParametersChanged(playbackParameters);
                    }
                    return;
                }
                return;
            case 8:
                ExoPlaybackException exception = msg.obj;
                it = this.listeners.iterator();
                while (it.hasNext()) {
                    ((ExoPlayer$EventListener) it.next()).onPlayerError(exception);
                }
                return;
            default:
                throw new IllegalStateException();
        }
    }
}
