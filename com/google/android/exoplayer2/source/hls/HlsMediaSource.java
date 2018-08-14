package com.google.android.exoplayer2.source.hls;

import android.net.Uri;
import android.os.Handler;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.source.AdaptiveMediaSourceEventListener;
import com.google.android.exoplayer2.source.AdaptiveMediaSourceEventListener.EventDispatcher;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSource.Listener;
import com.google.android.exoplayer2.source.SinglePeriodTimeline;
import com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist.Segment;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker.PrimaryPlaylistListener;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DataSource.Factory;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;
import java.util.List;

public final class HlsMediaSource implements MediaSource, PrimaryPlaylistListener {
    public static final int DEFAULT_MIN_LOADABLE_RETRY_COUNT = 3;
    private final HlsDataSourceFactory dataSourceFactory;
    private final EventDispatcher eventDispatcher;
    private final Uri manifestUri;
    private final int minLoadableRetryCount;
    private HlsPlaylistTracker playlistTracker;
    private Listener sourceListener;

    public HlsMediaSource(Uri manifestUri, Factory dataSourceFactory, Handler eventHandler, AdaptiveMediaSourceEventListener eventListener) {
        this(manifestUri, dataSourceFactory, 3, eventHandler, eventListener);
    }

    public HlsMediaSource(Uri manifestUri, Factory dataSourceFactory, int minLoadableRetryCount, Handler eventHandler, AdaptiveMediaSourceEventListener eventListener) {
        this(manifestUri, new DefaultHlsDataSourceFactory(dataSourceFactory), minLoadableRetryCount, eventHandler, eventListener);
    }

    public HlsMediaSource(Uri manifestUri, HlsDataSourceFactory dataSourceFactory, int minLoadableRetryCount, Handler eventHandler, AdaptiveMediaSourceEventListener eventListener) {
        this.manifestUri = manifestUri;
        this.dataSourceFactory = dataSourceFactory;
        this.minLoadableRetryCount = minLoadableRetryCount;
        this.eventDispatcher = new EventDispatcher(eventHandler, eventListener);
    }

    public void prepareSource(ExoPlayer player, boolean isTopLevelSource, Listener listener) {
        Assertions.checkState(this.playlistTracker == null);
        this.playlistTracker = new HlsPlaylistTracker(this.manifestUri, this.dataSourceFactory, this.eventDispatcher, this.minLoadableRetryCount, this);
        this.sourceListener = listener;
        this.playlistTracker.start();
    }

    public void maybeThrowSourceInfoRefreshError() throws IOException {
        this.playlistTracker.maybeThrowPrimaryPlaylistRefreshError();
    }

    public MediaPeriod createPeriod(int index, Allocator allocator, long positionUs) {
        Assertions.checkArgument(index == 0);
        return new HlsMediaPeriod(this.playlistTracker, this.dataSourceFactory, this.minLoadableRetryCount, this.eventDispatcher, allocator, positionUs);
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        ((HlsMediaPeriod) mediaPeriod).release();
    }

    public void releaseSource() {
        if (this.playlistTracker != null) {
            this.playlistTracker.release();
            this.playlistTracker = null;
        }
        this.sourceListener = null;
    }

    public void onPrimaryPlaylistRefreshed(HlsMediaPlaylist playlist) {
        SinglePeriodTimeline timeline;
        long windowDefaultStartPositionUs = playlist.startOffsetUs;
        if (this.playlistTracker.isLive()) {
            long periodDurationUs = playlist.hasEndTag ? playlist.startTimeUs + playlist.durationUs : -9223372036854775807L;
            List<Segment> segments = playlist.segments;
            if (windowDefaultStartPositionUs == -9223372036854775807L) {
                if (segments.isEmpty()) {
                    windowDefaultStartPositionUs = 0;
                } else {
                    windowDefaultStartPositionUs = ((Segment) segments.get(Math.max(0, segments.size() - 3))).relativeStartTimeUs;
                }
            }
            timeline = new SinglePeriodTimeline(periodDurationUs, playlist.durationUs, playlist.startTimeUs, windowDefaultStartPositionUs, true, !playlist.hasEndTag);
        } else {
            if (windowDefaultStartPositionUs == -9223372036854775807L) {
                windowDefaultStartPositionUs = 0;
            }
            SinglePeriodTimeline singlePeriodTimeline = new SinglePeriodTimeline(playlist.startTimeUs + playlist.durationUs, playlist.durationUs, playlist.startTimeUs, windowDefaultStartPositionUs, true, false);
        }
        this.sourceListener.onSourceInfoRefreshed(timeline, new HlsManifest(this.playlistTracker.getMasterPlaylist(), playlist));
    }
}
