package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.Timeline$Period;
import com.google.android.exoplayer2.Timeline.Window;
import com.google.android.exoplayer2.source.MediaSource.Listener;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;
import java.util.ArrayList;

public final class ClippingMediaSource implements MediaSource, Listener {
    private ClippingTimeline clippingTimeline;
    private final long endUs;
    private final ArrayList<ClippingMediaPeriod> mediaPeriods;
    private final MediaSource mediaSource;
    private Listener sourceListener;
    private final long startUs;

    private static final class ClippingTimeline extends Timeline {
        private final long endUs;
        private final long startUs;
        private final Timeline timeline;

        public ClippingTimeline(Timeline timeline, long startUs, long endUs) {
            long resolvedEndUs;
            Assertions.checkArgument(timeline.getWindowCount() == 1);
            Assertions.checkArgument(timeline.getPeriodCount() == 1);
            Window window = timeline.getWindow(0, new Window(), false);
            Assertions.checkArgument(!window.isDynamic);
            if (endUs == Long.MIN_VALUE) {
                resolvedEndUs = window.durationUs;
            } else {
                resolvedEndUs = endUs;
            }
            if (window.durationUs != -9223372036854775807L) {
                boolean z = startUs == 0 || window.isSeekable;
                Assertions.checkArgument(z);
                Assertions.checkArgument(resolvedEndUs <= window.durationUs);
                Assertions.checkArgument(startUs <= resolvedEndUs);
            }
            Assertions.checkArgument(timeline.getPeriod(0, new Timeline$Period()).getPositionInWindowUs() == 0);
            this.timeline = timeline;
            this.startUs = startUs;
            this.endUs = resolvedEndUs;
        }

        public int getWindowCount() {
            return 1;
        }

        public Window getWindow(int windowIndex, Window window, boolean setIds, long defaultPositionProjectionUs) {
            long j;
            window = this.timeline.getWindow(0, window, setIds, defaultPositionProjectionUs);
            if (this.endUs != -9223372036854775807L) {
                j = this.endUs - this.startUs;
            } else {
                j = -9223372036854775807L;
            }
            window.durationUs = j;
            if (window.defaultPositionUs != -9223372036854775807L) {
                window.defaultPositionUs = Math.max(window.defaultPositionUs, this.startUs);
                if (this.endUs == -9223372036854775807L) {
                    j = window.defaultPositionUs;
                } else {
                    j = Math.min(window.defaultPositionUs, this.endUs);
                }
                window.defaultPositionUs = j;
                window.defaultPositionUs -= this.startUs;
            }
            long startMs = C.usToMs(this.startUs);
            if (window.presentationStartTimeMs != -9223372036854775807L) {
                window.presentationStartTimeMs += startMs;
            }
            if (window.windowStartTimeMs != -9223372036854775807L) {
                window.windowStartTimeMs += startMs;
            }
            return window;
        }

        public int getPeriodCount() {
            return 1;
        }

        public Timeline$Period getPeriod(int periodIndex, Timeline$Period period, boolean setIds) {
            long j = -9223372036854775807L;
            period = this.timeline.getPeriod(0, period, setIds);
            if (this.endUs != -9223372036854775807L) {
                j = this.endUs - this.startUs;
            }
            period.durationUs = j;
            return period;
        }

        public int getIndexOfPeriod(Object uid) {
            return this.timeline.getIndexOfPeriod(uid);
        }
    }

    public ClippingMediaSource(MediaSource mediaSource, long startPositionUs, long endPositionUs) {
        Assertions.checkArgument(startPositionUs >= 0);
        this.mediaSource = (MediaSource) Assertions.checkNotNull(mediaSource);
        this.startUs = startPositionUs;
        this.endUs = endPositionUs;
        this.mediaPeriods = new ArrayList();
    }

    public void prepareSource(ExoPlayer player, boolean isTopLevelSource, Listener listener) {
        this.sourceListener = listener;
        this.mediaSource.prepareSource(player, false, this);
    }

    public void maybeThrowSourceInfoRefreshError() throws IOException {
        this.mediaSource.maybeThrowSourceInfoRefreshError();
    }

    public MediaPeriod createPeriod(int index, Allocator allocator, long positionUs) {
        ClippingMediaPeriod mediaPeriod = new ClippingMediaPeriod(this.mediaSource.createPeriod(index, allocator, this.startUs + positionUs));
        this.mediaPeriods.add(mediaPeriod);
        mediaPeriod.setClipping(this.clippingTimeline.startUs, this.clippingTimeline.endUs);
        return mediaPeriod;
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        Assertions.checkState(this.mediaPeriods.remove(mediaPeriod));
        this.mediaSource.releasePeriod(((ClippingMediaPeriod) mediaPeriod).mediaPeriod);
    }

    public void releaseSource() {
        this.mediaSource.releaseSource();
    }

    public void onSourceInfoRefreshed(Timeline timeline, Object manifest) {
        long endUs;
        this.clippingTimeline = new ClippingTimeline(timeline, this.startUs, this.endUs);
        this.sourceListener.onSourceInfoRefreshed(this.clippingTimeline, manifest);
        long startUs = this.clippingTimeline.startUs;
        if (this.clippingTimeline.endUs == -9223372036854775807L) {
            endUs = Long.MIN_VALUE;
        } else {
            endUs = this.clippingTimeline.endUs;
        }
        int count = this.mediaPeriods.size();
        for (int i = 0; i < count; i++) {
            ((ClippingMediaPeriod) this.mediaPeriods.get(i)).setClipping(startUs, endUs);
        }
    }
}
