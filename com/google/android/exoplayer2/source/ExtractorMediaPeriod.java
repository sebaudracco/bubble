package com.google.android.exoplayer2.source;

import android.net.Uri;
import android.os.Handler;
import android.util.SparseArray;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.extractor.DefaultExtractorInput;
import com.google.android.exoplayer2.extractor.DefaultTrackOutput;
import com.google.android.exoplayer2.extractor.DefaultTrackOutput.UpstreamFormatChangedListener;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.source.ExtractorMediaSource.EventListener;
import com.google.android.exoplayer2.source.MediaSource.Listener;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.upstream.Loader.Callback;
import com.google.android.exoplayer2.upstream.Loader.Loadable;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ConditionVariable;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.io.EOFException;
import java.io.IOException;

final class ExtractorMediaPeriod implements MediaPeriod, ExtractorOutput, Callback<ExtractingLoadable>, UpstreamFormatChangedListener {
    private static final long DEFAULT_LAST_SAMPLE_DURATION_US = 10000;
    private final Allocator allocator;
    private MediaPeriod.Callback callback;
    private final String customCacheKey;
    private final DataSource dataSource;
    private long durationUs;
    private int enabledTrackCount;
    private final Handler eventHandler;
    private final EventListener eventListener;
    private int extractedSamplesCountAtStartOfLoad;
    private final ExtractorHolder extractorHolder;
    private final Handler handler;
    private boolean haveAudioVideoTracks;
    private long lastSeekPositionUs;
    private long length;
    private final ConditionVariable loadCondition;
    private final Loader loader = new Loader("Loader:ExtractorMediaPeriod");
    private boolean loadingFinished;
    private final Runnable maybeFinishPrepareRunnable;
    private final int minLoadableRetryCount;
    private boolean notifyReset;
    private final Runnable onContinueLoadingRequestedRunnable;
    private long pendingResetPositionUs;
    private boolean prepared;
    private boolean released;
    private final SparseArray<DefaultTrackOutput> sampleQueues;
    private SeekMap seekMap;
    private boolean seenFirstTrackSelection;
    private final Listener sourceListener;
    private boolean[] trackEnabledStates;
    private boolean[] trackIsAudioVideoFlags;
    private TrackGroupArray tracks;
    private boolean tracksBuilt;
    private final Uri uri;

    class C27461 implements Runnable {
        C27461() {
        }

        public void run() {
            ExtractorMediaPeriod.this.maybeFinishPrepare();
        }
    }

    class C27472 implements Runnable {
        C27472() {
        }

        public void run() {
            if (!ExtractorMediaPeriod.this.released) {
                ExtractorMediaPeriod.this.callback.onContinueLoadingRequested(ExtractorMediaPeriod.this);
            }
        }
    }

    final class ExtractingLoadable implements Loadable {
        private static final int CONTINUE_LOADING_CHECK_INTERVAL_BYTES = 1048576;
        private final DataSource dataSource;
        private final ExtractorHolder extractorHolder;
        private long length = -1;
        private volatile boolean loadCanceled;
        private final ConditionVariable loadCondition;
        private boolean pendingExtractorSeek = true;
        private final PositionHolder positionHolder = new PositionHolder();
        private long seekTimeUs;
        private final Uri uri;

        public ExtractingLoadable(Uri uri, DataSource dataSource, ExtractorHolder extractorHolder, ConditionVariable loadCondition) {
            this.uri = (Uri) Assertions.checkNotNull(uri);
            this.dataSource = (DataSource) Assertions.checkNotNull(dataSource);
            this.extractorHolder = (ExtractorHolder) Assertions.checkNotNull(extractorHolder);
            this.loadCondition = loadCondition;
        }

        public void setLoadPosition(long position, long timeUs) {
            this.positionHolder.position = position;
            this.seekTimeUs = timeUs;
            this.pendingExtractorSeek = true;
        }

        public void cancelLoad() {
            this.loadCanceled = true;
        }

        public boolean isLoadCanceled() {
            return this.loadCanceled;
        }

        public void load() throws IOException, InterruptedException {
            Throwable th;
            int result = 0;
            while (result == 0 && !this.loadCanceled) {
                ExtractorInput input;
                try {
                    long position = this.positionHolder.position;
                    this.length = this.dataSource.open(new DataSpec(this.uri, position, -1, ExtractorMediaPeriod.this.customCacheKey));
                    if (this.length != -1) {
                        this.length += position;
                    }
                    input = new DefaultExtractorInput(this.dataSource, position, this.length);
                    try {
                        Extractor extractor = this.extractorHolder.selectExtractor(input, this.dataSource.getUri());
                        if (this.pendingExtractorSeek) {
                            extractor.seek(position, this.seekTimeUs);
                            this.pendingExtractorSeek = false;
                        }
                        while (result == 0 && !this.loadCanceled) {
                            this.loadCondition.block();
                            result = extractor.read(input, this.positionHolder);
                            if (input.getPosition() > 1048576 + position) {
                                position = input.getPosition();
                                this.loadCondition.close();
                                ExtractorMediaPeriod.this.handler.post(ExtractorMediaPeriod.this.onContinueLoadingRequestedRunnable);
                            }
                        }
                        if (result == 1) {
                            result = 0;
                        } else if (input != null) {
                            this.positionHolder.position = input.getPosition();
                        }
                        Util.closeQuietly(this.dataSource);
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    input = null;
                }
            }
            return;
            if (result != 1) {
                if (input != null) {
                    this.positionHolder.position = input.getPosition();
                }
            }
            Util.closeQuietly(this.dataSource);
            throw th;
        }
    }

    private static final class ExtractorHolder {
        private Extractor extractor;
        private final ExtractorOutput extractorOutput;
        private final Extractor[] extractors;

        public ExtractorHolder(Extractor[] extractors, ExtractorOutput extractorOutput) {
            this.extractors = extractors;
            this.extractorOutput = extractorOutput;
        }

        public Extractor selectExtractor(ExtractorInput input, Uri uri) throws IOException, InterruptedException {
            if (this.extractor != null) {
                return this.extractor;
            }
            Extractor[] extractorArr = this.extractors;
            int length = extractorArr.length;
            int i = 0;
            loop0:
            while (i < length) {
                Extractor extractor = extractorArr[i];
                try {
                    if (extractor.sniff(input)) {
                        this.extractor = extractor;
                        input.resetPeekPosition();
                        break loop0;
                    }
                    i++;
                } catch (EOFException e) {
                    i++;
                } finally {
                    input.resetPeekPosition();
                }
            }
            if (this.extractor == null) {
                throw new UnrecognizedInputFormatException("None of the available extractors (" + Util.getCommaDelimitedSimpleClassNames(this.extractors) + ") could read the stream.", uri);
            }
            this.extractor.init(this.extractorOutput);
            return this.extractor;
        }

        public void release() {
            if (this.extractor != null) {
                this.extractor.release();
                this.extractor = null;
            }
        }
    }

    private final class SampleStreamImpl implements SampleStream {
        private final int track;

        public SampleStreamImpl(int track) {
            this.track = track;
        }

        public boolean isReady() {
            return ExtractorMediaPeriod.this.isReady(this.track);
        }

        public void maybeThrowError() throws IOException {
            ExtractorMediaPeriod.this.maybeThrowError();
        }

        public int readData(FormatHolder formatHolder, DecoderInputBuffer buffer, boolean formatRequired) {
            return ExtractorMediaPeriod.this.readData(this.track, formatHolder, buffer, formatRequired);
        }

        public void skipData(long positionUs) {
            ExtractorMediaPeriod.this.skipData(this.track, positionUs);
        }
    }

    public ExtractorMediaPeriod(Uri uri, DataSource dataSource, Extractor[] extractors, int minLoadableRetryCount, Handler eventHandler, EventListener eventListener, Listener sourceListener, Allocator allocator, String customCacheKey) {
        this.uri = uri;
        this.dataSource = dataSource;
        this.minLoadableRetryCount = minLoadableRetryCount;
        this.eventHandler = eventHandler;
        this.eventListener = eventListener;
        this.sourceListener = sourceListener;
        this.allocator = allocator;
        this.customCacheKey = customCacheKey;
        this.extractorHolder = new ExtractorHolder(extractors, this);
        this.loadCondition = new ConditionVariable();
        this.maybeFinishPrepareRunnable = new C27461();
        this.onContinueLoadingRequestedRunnable = new C27472();
        this.handler = new Handler();
        this.pendingResetPositionUs = -9223372036854775807L;
        this.sampleQueues = new SparseArray();
        this.length = -1;
    }

    public void release() {
        final ExtractorHolder extractorHolder = this.extractorHolder;
        this.loader.release(new Runnable() {
            public void run() {
                extractorHolder.release();
                int trackCount = ExtractorMediaPeriod.this.sampleQueues.size();
                for (int i = 0; i < trackCount; i++) {
                    ((DefaultTrackOutput) ExtractorMediaPeriod.this.sampleQueues.valueAt(i)).disable();
                }
            }
        });
        this.handler.removeCallbacksAndMessages(null);
        this.released = true;
    }

    public void prepare(MediaPeriod.Callback callback) {
        this.callback = callback;
        this.loadCondition.open();
        startLoading();
    }

    public void maybeThrowPrepareError() throws IOException {
        maybeThrowError();
    }

    public TrackGroupArray getTrackGroups() {
        return this.tracks;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long selectTracks(com.google.android.exoplayer2.trackselection.TrackSelection[] r10, boolean[] r11, com.google.android.exoplayer2.source.SampleStream[] r12, boolean[] r13, long r14) {
        /*
        r9 = this;
        r5 = r9.prepared;
        com.google.android.exoplayer2.util.Assertions.checkState(r5);
        r0 = 0;
    L_0x0006:
        r5 = r10.length;
        if (r0 >= r5) goto L_0x0040;
    L_0x0009:
        r5 = r12[r0];
        if (r5 == 0) goto L_0x003d;
    L_0x000d:
        r5 = r10[r0];
        if (r5 == 0) goto L_0x0015;
    L_0x0011:
        r5 = r11[r0];
        if (r5 != 0) goto L_0x003d;
    L_0x0015:
        r5 = r12[r0];
        r5 = (com.google.android.exoplayer2.source.ExtractorMediaPeriod.SampleStreamImpl) r5;
        r3 = r5.track;
        r5 = r9.trackEnabledStates;
        r5 = r5[r3];
        com.google.android.exoplayer2.util.Assertions.checkState(r5);
        r5 = r9.enabledTrackCount;
        r5 = r5 + -1;
        r9.enabledTrackCount = r5;
        r5 = r9.trackEnabledStates;
        r6 = 0;
        r5[r3] = r6;
        r5 = r9.sampleQueues;
        r5 = r5.valueAt(r3);
        r5 = (com.google.android.exoplayer2.extractor.DefaultTrackOutput) r5;
        r5.disable();
        r5 = 0;
        r12[r0] = r5;
    L_0x003d:
        r0 = r0 + 1;
        goto L_0x0006;
    L_0x0040:
        r1 = 0;
        r0 = 0;
    L_0x0042:
        r5 = r10.length;
        if (r0 >= r5) goto L_0x0098;
    L_0x0045:
        r5 = r12[r0];
        if (r5 != 0) goto L_0x008f;
    L_0x0049:
        r5 = r10[r0];
        if (r5 == 0) goto L_0x008f;
    L_0x004d:
        r2 = r10[r0];
        r5 = r2.length();
        r6 = 1;
        if (r5 != r6) goto L_0x0092;
    L_0x0056:
        r5 = 1;
    L_0x0057:
        com.google.android.exoplayer2.util.Assertions.checkState(r5);
        r5 = 0;
        r5 = r2.getIndexInTrackGroup(r5);
        if (r5 != 0) goto L_0x0094;
    L_0x0061:
        r5 = 1;
    L_0x0062:
        com.google.android.exoplayer2.util.Assertions.checkState(r5);
        r5 = r9.tracks;
        r6 = r2.getTrackGroup();
        r3 = r5.indexOf(r6);
        r5 = r9.trackEnabledStates;
        r5 = r5[r3];
        if (r5 != 0) goto L_0x0096;
    L_0x0075:
        r5 = 1;
    L_0x0076:
        com.google.android.exoplayer2.util.Assertions.checkState(r5);
        r5 = r9.enabledTrackCount;
        r5 = r5 + 1;
        r9.enabledTrackCount = r5;
        r5 = r9.trackEnabledStates;
        r6 = 1;
        r5[r3] = r6;
        r5 = new com.google.android.exoplayer2.source.ExtractorMediaPeriod$SampleStreamImpl;
        r5.<init>(r3);
        r12[r0] = r5;
        r5 = 1;
        r13[r0] = r5;
        r1 = 1;
    L_0x008f:
        r0 = r0 + 1;
        goto L_0x0042;
    L_0x0092:
        r5 = 0;
        goto L_0x0057;
    L_0x0094:
        r5 = 0;
        goto L_0x0062;
    L_0x0096:
        r5 = 0;
        goto L_0x0076;
    L_0x0098:
        r5 = r9.seenFirstTrackSelection;
        if (r5 != 0) goto L_0x00b9;
    L_0x009c:
        r5 = r9.sampleQueues;
        r4 = r5.size();
        r0 = 0;
    L_0x00a3:
        if (r0 >= r4) goto L_0x00b9;
    L_0x00a5:
        r5 = r9.trackEnabledStates;
        r5 = r5[r0];
        if (r5 != 0) goto L_0x00b6;
    L_0x00ab:
        r5 = r9.sampleQueues;
        r5 = r5.valueAt(r0);
        r5 = (com.google.android.exoplayer2.extractor.DefaultTrackOutput) r5;
        r5.disable();
    L_0x00b6:
        r0 = r0 + 1;
        goto L_0x00a3;
    L_0x00b9:
        r5 = r9.enabledTrackCount;
        if (r5 != 0) goto L_0x00d1;
    L_0x00bd:
        r5 = 0;
        r9.notifyReset = r5;
        r5 = r9.loader;
        r5 = r5.isLoading();
        if (r5 == 0) goto L_0x00cd;
    L_0x00c8:
        r5 = r9.loader;
        r5.cancelLoading();
    L_0x00cd:
        r5 = 1;
        r9.seenFirstTrackSelection = r5;
        return r14;
    L_0x00d1:
        r5 = r9.seenFirstTrackSelection;
        if (r5 == 0) goto L_0x00e9;
    L_0x00d5:
        if (r1 == 0) goto L_0x00cd;
    L_0x00d7:
        r14 = r9.seekToUs(r14);
        r0 = 0;
    L_0x00dc:
        r5 = r12.length;
        if (r0 >= r5) goto L_0x00cd;
    L_0x00df:
        r5 = r12[r0];
        if (r5 == 0) goto L_0x00e6;
    L_0x00e3:
        r5 = 1;
        r13[r0] = r5;
    L_0x00e6:
        r0 = r0 + 1;
        goto L_0x00dc;
    L_0x00e9:
        r6 = 0;
        r5 = (r14 > r6 ? 1 : (r14 == r6 ? 0 : -1));
        if (r5 == 0) goto L_0x00cd;
    L_0x00ef:
        goto L_0x00d7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.ExtractorMediaPeriod.selectTracks(com.google.android.exoplayer2.trackselection.TrackSelection[], boolean[], com.google.android.exoplayer2.source.SampleStream[], boolean[], long):long");
    }

    public void discardBuffer(long positionUs) {
    }

    public boolean continueLoading(long playbackPositionUs) {
        if (this.loadingFinished || (this.prepared && this.enabledTrackCount == 0)) {
            return false;
        }
        boolean continuedLoading = this.loadCondition.open();
        if (this.loader.isLoading()) {
            return continuedLoading;
        }
        startLoading();
        return true;
    }

    public long getNextLoadPositionUs() {
        return this.enabledTrackCount == 0 ? Long.MIN_VALUE : getBufferedPositionUs();
    }

    public long readDiscontinuity() {
        if (!this.notifyReset) {
            return -9223372036854775807L;
        }
        this.notifyReset = false;
        return this.lastSeekPositionUs;
    }

    public long getBufferedPositionUs() {
        if (this.loadingFinished) {
            return Long.MIN_VALUE;
        }
        if (isPendingReset()) {
            return this.pendingResetPositionUs;
        }
        long largestQueuedTimestampUs;
        if (this.haveAudioVideoTracks) {
            largestQueuedTimestampUs = Long.MAX_VALUE;
            int trackCount = this.sampleQueues.size();
            for (int i = 0; i < trackCount; i++) {
                if (this.trackIsAudioVideoFlags[i]) {
                    largestQueuedTimestampUs = Math.min(largestQueuedTimestampUs, ((DefaultTrackOutput) this.sampleQueues.valueAt(i)).getLargestQueuedTimestampUs());
                }
            }
        } else {
            largestQueuedTimestampUs = getLargestQueuedTimestampUs();
        }
        return largestQueuedTimestampUs == Long.MIN_VALUE ? this.lastSeekPositionUs : largestQueuedTimestampUs;
    }

    public long seekToUs(long positionUs) {
        boolean seekInsideBuffer;
        if (!this.seekMap.isSeekable()) {
            positionUs = 0;
        }
        this.lastSeekPositionUs = positionUs;
        int trackCount = this.sampleQueues.size();
        if (isPendingReset()) {
            seekInsideBuffer = false;
        } else {
            seekInsideBuffer = true;
        }
        int i = 0;
        while (seekInsideBuffer && i < trackCount) {
            if (this.trackEnabledStates[i]) {
                seekInsideBuffer = ((DefaultTrackOutput) this.sampleQueues.valueAt(i)).skipToKeyframeBefore(positionUs, false);
            }
            i++;
        }
        if (!seekInsideBuffer) {
            this.pendingResetPositionUs = positionUs;
            this.loadingFinished = false;
            if (this.loader.isLoading()) {
                this.loader.cancelLoading();
            } else {
                for (i = 0; i < trackCount; i++) {
                    ((DefaultTrackOutput) this.sampleQueues.valueAt(i)).reset(this.trackEnabledStates[i]);
                }
            }
        }
        this.notifyReset = false;
        return positionUs;
    }

    boolean isReady(int track) {
        return this.loadingFinished || !(isPendingReset() || ((DefaultTrackOutput) this.sampleQueues.valueAt(track)).isEmpty());
    }

    void maybeThrowError() throws IOException {
        this.loader.maybeThrowError();
    }

    int readData(int track, FormatHolder formatHolder, DecoderInputBuffer buffer, boolean formatRequired) {
        if (this.notifyReset || isPendingReset()) {
            return -3;
        }
        return ((DefaultTrackOutput) this.sampleQueues.valueAt(track)).readData(formatHolder, buffer, formatRequired, this.loadingFinished, this.lastSeekPositionUs);
    }

    void skipData(int track, long positionUs) {
        DefaultTrackOutput sampleQueue = (DefaultTrackOutput) this.sampleQueues.valueAt(track);
        if (!this.loadingFinished || positionUs <= sampleQueue.getLargestQueuedTimestampUs()) {
            sampleQueue.skipToKeyframeBefore(positionUs, true);
        } else {
            sampleQueue.skipAll();
        }
    }

    public void onLoadCompleted(ExtractingLoadable loadable, long elapsedRealtimeMs, long loadDurationMs) {
        copyLengthFromLoader(loadable);
        this.loadingFinished = true;
        if (this.durationUs == -9223372036854775807L) {
            long largestQueuedTimestampUs = getLargestQueuedTimestampUs();
            this.durationUs = largestQueuedTimestampUs == Long.MIN_VALUE ? 0 : 10000 + largestQueuedTimestampUs;
            this.sourceListener.onSourceInfoRefreshed(new SinglePeriodTimeline(this.durationUs, this.seekMap.isSeekable()), null);
        }
        this.callback.onContinueLoadingRequested(this);
    }

    public void onLoadCanceled(ExtractingLoadable loadable, long elapsedRealtimeMs, long loadDurationMs, boolean released) {
        copyLengthFromLoader(loadable);
        if (!released && this.enabledTrackCount > 0) {
            int trackCount = this.sampleQueues.size();
            for (int i = 0; i < trackCount; i++) {
                ((DefaultTrackOutput) this.sampleQueues.valueAt(i)).reset(this.trackEnabledStates[i]);
            }
            this.callback.onContinueLoadingRequested(this);
        }
    }

    public int onLoadError(ExtractingLoadable loadable, long elapsedRealtimeMs, long loadDurationMs, IOException error) {
        copyLengthFromLoader(loadable);
        notifyLoadError(error);
        if (isLoadableExceptionFatal(error)) {
            return 3;
        }
        boolean madeProgress;
        if (getExtractedSamplesCount() > this.extractedSamplesCountAtStartOfLoad) {
            madeProgress = true;
        } else {
            madeProgress = false;
        }
        configureRetry(loadable);
        this.extractedSamplesCountAtStartOfLoad = getExtractedSamplesCount();
        if (madeProgress) {
            return 1;
        }
        return 0;
    }

    public TrackOutput track(int id, int type) {
        DefaultTrackOutput trackOutput = (DefaultTrackOutput) this.sampleQueues.get(id);
        if (trackOutput != null) {
            return trackOutput;
        }
        trackOutput = new DefaultTrackOutput(this.allocator);
        trackOutput.setUpstreamFormatChangeListener(this);
        this.sampleQueues.put(id, trackOutput);
        return trackOutput;
    }

    public void endTracks() {
        this.tracksBuilt = true;
        this.handler.post(this.maybeFinishPrepareRunnable);
    }

    public void seekMap(SeekMap seekMap) {
        this.seekMap = seekMap;
        this.handler.post(this.maybeFinishPrepareRunnable);
    }

    public void onUpstreamFormatChanged(Format format) {
        this.handler.post(this.maybeFinishPrepareRunnable);
    }

    private void maybeFinishPrepare() {
        if (!this.released && !this.prepared && this.seekMap != null && this.tracksBuilt) {
            int trackCount = this.sampleQueues.size();
            int i = 0;
            while (i < trackCount) {
                if (((DefaultTrackOutput) this.sampleQueues.valueAt(i)).getUpstreamFormat() != null) {
                    i++;
                } else {
                    return;
                }
            }
            this.loadCondition.close();
            TrackGroup[] trackArray = new TrackGroup[trackCount];
            this.trackIsAudioVideoFlags = new boolean[trackCount];
            this.trackEnabledStates = new boolean[trackCount];
            this.durationUs = this.seekMap.getDurationUs();
            for (i = 0; i < trackCount; i++) {
                boolean isAudioVideo;
                trackArray[i] = new TrackGroup(((DefaultTrackOutput) this.sampleQueues.valueAt(i)).getUpstreamFormat());
                String mimeType = trackFormat.sampleMimeType;
                if (MimeTypes.isVideo(mimeType) || MimeTypes.isAudio(mimeType)) {
                    isAudioVideo = true;
                } else {
                    isAudioVideo = false;
                }
                this.trackIsAudioVideoFlags[i] = isAudioVideo;
                this.haveAudioVideoTracks |= isAudioVideo;
            }
            this.tracks = new TrackGroupArray(trackArray);
            this.prepared = true;
            this.sourceListener.onSourceInfoRefreshed(new SinglePeriodTimeline(this.durationUs, this.seekMap.isSeekable()), null);
            this.callback.onPrepared(this);
        }
    }

    private void copyLengthFromLoader(ExtractingLoadable loadable) {
        if (this.length == -1) {
            this.length = loadable.length;
        }
    }

    private void startLoading() {
        ExtractingLoadable loadable = new ExtractingLoadable(this.uri, this.dataSource, this.extractorHolder, this.loadCondition);
        if (this.prepared) {
            Assertions.checkState(isPendingReset());
            if (this.durationUs == -9223372036854775807L || this.pendingResetPositionUs < this.durationUs) {
                loadable.setLoadPosition(this.seekMap.getPosition(this.pendingResetPositionUs), this.pendingResetPositionUs);
                this.pendingResetPositionUs = -9223372036854775807L;
            } else {
                this.loadingFinished = true;
                this.pendingResetPositionUs = -9223372036854775807L;
                return;
            }
        }
        this.extractedSamplesCountAtStartOfLoad = getExtractedSamplesCount();
        int minRetryCount = this.minLoadableRetryCount;
        if (minRetryCount == -1) {
            minRetryCount = (this.prepared && this.length == -1 && (this.seekMap == null || this.seekMap.getDurationUs() == -9223372036854775807L)) ? 6 : 3;
        }
        this.loader.startLoading(loadable, this, minRetryCount);
    }

    private void configureRetry(ExtractingLoadable loadable) {
        if (this.length != -1) {
            return;
        }
        if (this.seekMap == null || this.seekMap.getDurationUs() == -9223372036854775807L) {
            this.lastSeekPositionUs = 0;
            this.notifyReset = this.prepared;
            int trackCount = this.sampleQueues.size();
            for (int i = 0; i < trackCount; i++) {
                DefaultTrackOutput defaultTrackOutput = (DefaultTrackOutput) this.sampleQueues.valueAt(i);
                boolean z = !this.prepared || this.trackEnabledStates[i];
                defaultTrackOutput.reset(z);
            }
            loadable.setLoadPosition(0, 0);
        }
    }

    private int getExtractedSamplesCount() {
        int extractedSamplesCount = 0;
        for (int i = 0; i < this.sampleQueues.size(); i++) {
            extractedSamplesCount += ((DefaultTrackOutput) this.sampleQueues.valueAt(i)).getWriteIndex();
        }
        return extractedSamplesCount;
    }

    private long getLargestQueuedTimestampUs() {
        long largestQueuedTimestampUs = Long.MIN_VALUE;
        int trackCount = this.sampleQueues.size();
        for (int i = 0; i < trackCount; i++) {
            largestQueuedTimestampUs = Math.max(largestQueuedTimestampUs, ((DefaultTrackOutput) this.sampleQueues.valueAt(i)).getLargestQueuedTimestampUs());
        }
        return largestQueuedTimestampUs;
    }

    private boolean isPendingReset() {
        return this.pendingResetPositionUs != -9223372036854775807L;
    }

    private boolean isLoadableExceptionFatal(IOException e) {
        return e instanceof UnrecognizedInputFormatException;
    }

    private void notifyLoadError(final IOException error) {
        if (this.eventHandler != null && this.eventListener != null) {
            this.eventHandler.post(new Runnable() {
                public void run() {
                    ExtractorMediaPeriod.this.eventListener.onLoadError(error);
                }
            });
        }
    }
}
