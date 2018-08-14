package com.google.android.exoplayer2.source.dash;

import android.os.SystemClock;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ChunkIndex;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor;
import com.google.android.exoplayer2.extractor.mp4.FragmentedMp4Extractor;
import com.google.android.exoplayer2.extractor.rawcc.RawCcExtractor;
import com.google.android.exoplayer2.source.BehindLiveWindowException;
import com.google.android.exoplayer2.source.chunk.Chunk;
import com.google.android.exoplayer2.source.chunk.ChunkExtractorWrapper;
import com.google.android.exoplayer2.source.chunk.ChunkHolder;
import com.google.android.exoplayer2.source.chunk.ChunkedTrackBlacklistUtil;
import com.google.android.exoplayer2.source.chunk.ContainerMediaChunk;
import com.google.android.exoplayer2.source.chunk.InitializationChunk;
import com.google.android.exoplayer2.source.chunk.MediaChunk;
import com.google.android.exoplayer2.source.chunk.SingleSampleMediaChunk;
import com.google.android.exoplayer2.source.dash.manifest.AdaptationSet;
import com.google.android.exoplayer2.source.dash.manifest.DashManifest;
import com.google.android.exoplayer2.source.dash.manifest.RangedUri;
import com.google.android.exoplayer2.source.dash.manifest.Representation;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.HttpDataSource.InvalidResponseCodeException;
import com.google.android.exoplayer2.upstream.LoaderErrorThrower;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.List;

public class DefaultDashChunkSource implements DashChunkSource {
    private final int adaptationSetIndex;
    private final DataSource dataSource;
    private final long elapsedRealtimeOffsetMs;
    private IOException fatalError;
    private DashManifest manifest;
    private final LoaderErrorThrower manifestLoaderErrorThrower;
    private final int maxSegmentsPerLoad;
    private boolean missingLastSegment;
    private int periodIndex;
    private final RepresentationHolder[] representationHolders;
    private final TrackSelection trackSelection;

    public static final class Factory implements com.google.android.exoplayer2.source.dash.DashChunkSource.Factory {
        private final com.google.android.exoplayer2.upstream.DataSource.Factory dataSourceFactory;
        private final int maxSegmentsPerLoad;

        public Factory(com.google.android.exoplayer2.upstream.DataSource.Factory dataSourceFactory) {
            this(dataSourceFactory, 1);
        }

        public Factory(com.google.android.exoplayer2.upstream.DataSource.Factory dataSourceFactory, int maxSegmentsPerLoad) {
            this.dataSourceFactory = dataSourceFactory;
            this.maxSegmentsPerLoad = maxSegmentsPerLoad;
        }

        public DashChunkSource createDashChunkSource(LoaderErrorThrower manifestLoaderErrorThrower, DashManifest manifest, int periodIndex, int adaptationSetIndex, TrackSelection trackSelection, long elapsedRealtimeOffsetMs, boolean enableEventMessageTrack, boolean enableCea608Track) {
            return new DefaultDashChunkSource(manifestLoaderErrorThrower, manifest, periodIndex, adaptationSetIndex, trackSelection, this.dataSourceFactory.createDataSource(), elapsedRealtimeOffsetMs, this.maxSegmentsPerLoad, enableEventMessageTrack, enableCea608Track);
        }
    }

    protected static final class RepresentationHolder {
        public final ChunkExtractorWrapper extractorWrapper;
        private long periodDurationUs;
        public Representation representation;
        public DashSegmentIndex segmentIndex;
        private int segmentNumShift;
        public final int trackType;

        public RepresentationHolder(long periodDurationUs, Representation representation, boolean enableEventMessageTrack, boolean enableCea608Track, int trackType) {
            this.periodDurationUs = periodDurationUs;
            this.representation = representation;
            this.trackType = trackType;
            String containerMimeType = representation.format.containerMimeType;
            if (mimeTypeIsRawText(containerMimeType)) {
                this.extractorWrapper = null;
            } else {
                Extractor extractor;
                if ("application/x-rawcc".equals(containerMimeType)) {
                    extractor = new RawCcExtractor(representation.format);
                } else if (mimeTypeIsWebm(containerMimeType)) {
                    extractor = new MatroskaExtractor(1);
                } else {
                    int flags = 0;
                    if (enableEventMessageTrack) {
                        flags = 0 | 4;
                    }
                    if (enableCea608Track) {
                        flags |= 8;
                    }
                    extractor = new FragmentedMp4Extractor(flags);
                }
                this.extractorWrapper = new ChunkExtractorWrapper(extractor, representation.format);
            }
            this.segmentIndex = representation.getIndex();
        }

        public void updateRepresentation(long newPeriodDurationUs, Representation newRepresentation) throws BehindLiveWindowException {
            DashSegmentIndex oldIndex = this.representation.getIndex();
            DashSegmentIndex newIndex = newRepresentation.getIndex();
            this.periodDurationUs = newPeriodDurationUs;
            this.representation = newRepresentation;
            if (oldIndex != null) {
                this.segmentIndex = newIndex;
                if (oldIndex.isExplicit()) {
                    int oldIndexSegmentCount = oldIndex.getSegmentCount(this.periodDurationUs);
                    if (oldIndexSegmentCount != 0) {
                        int oldIndexLastSegmentNum = (oldIndex.getFirstSegmentNum() + oldIndexSegmentCount) - 1;
                        long oldIndexEndTimeUs = oldIndex.getTimeUs(oldIndexLastSegmentNum) + oldIndex.getDurationUs(oldIndexLastSegmentNum, this.periodDurationUs);
                        int newIndexFirstSegmentNum = newIndex.getFirstSegmentNum();
                        long newIndexStartTimeUs = newIndex.getTimeUs(newIndexFirstSegmentNum);
                        if (oldIndexEndTimeUs == newIndexStartTimeUs) {
                            this.segmentNumShift += (oldIndexLastSegmentNum + 1) - newIndexFirstSegmentNum;
                        } else if (oldIndexEndTimeUs < newIndexStartTimeUs) {
                            throw new BehindLiveWindowException();
                        } else {
                            this.segmentNumShift += oldIndex.getSegmentNum(newIndexStartTimeUs, this.periodDurationUs) - newIndexFirstSegmentNum;
                        }
                    }
                }
            }
        }

        public int getFirstSegmentNum() {
            return this.segmentIndex.getFirstSegmentNum() + this.segmentNumShift;
        }

        public int getSegmentCount() {
            return this.segmentIndex.getSegmentCount(this.periodDurationUs);
        }

        public long getSegmentStartTimeUs(int segmentNum) {
            return this.segmentIndex.getTimeUs(segmentNum - this.segmentNumShift);
        }

        public long getSegmentEndTimeUs(int segmentNum) {
            return getSegmentStartTimeUs(segmentNum) + this.segmentIndex.getDurationUs(segmentNum - this.segmentNumShift, this.periodDurationUs);
        }

        public int getSegmentNum(long positionUs) {
            return this.segmentIndex.getSegmentNum(positionUs, this.periodDurationUs) + this.segmentNumShift;
        }

        public RangedUri getSegmentUrl(int segmentNum) {
            return this.segmentIndex.getSegmentUrl(segmentNum - this.segmentNumShift);
        }

        private static boolean mimeTypeIsWebm(String mimeType) {
            return mimeType.startsWith("video/webm") || mimeType.startsWith("audio/webm") || mimeType.startsWith("application/webm");
        }

        private static boolean mimeTypeIsRawText(String mimeType) {
            return MimeTypes.isText(mimeType) || "application/ttml+xml".equals(mimeType);
        }
    }

    public DefaultDashChunkSource(LoaderErrorThrower manifestLoaderErrorThrower, DashManifest manifest, int periodIndex, int adaptationSetIndex, TrackSelection trackSelection, DataSource dataSource, long elapsedRealtimeOffsetMs, int maxSegmentsPerLoad, boolean enableEventMessageTrack, boolean enableCea608Track) {
        this.manifestLoaderErrorThrower = manifestLoaderErrorThrower;
        this.manifest = manifest;
        this.adaptationSetIndex = adaptationSetIndex;
        this.trackSelection = trackSelection;
        this.dataSource = dataSource;
        this.periodIndex = periodIndex;
        this.elapsedRealtimeOffsetMs = elapsedRealtimeOffsetMs;
        this.maxSegmentsPerLoad = maxSegmentsPerLoad;
        long periodDurationUs = manifest.getPeriodDurationUs(periodIndex);
        AdaptationSet adaptationSet = getAdaptationSet();
        List<Representation> representations = adaptationSet.representations;
        this.representationHolders = new RepresentationHolder[trackSelection.length()];
        for (int i = 0; i < this.representationHolders.length; i++) {
            this.representationHolders[i] = new RepresentationHolder(periodDurationUs, (Representation) representations.get(trackSelection.getIndexInTrackGroup(i)), enableEventMessageTrack, enableCea608Track, adaptationSet.type);
        }
    }

    public void updateManifest(DashManifest newManifest, int newPeriodIndex) {
        try {
            this.manifest = newManifest;
            this.periodIndex = newPeriodIndex;
            long periodDurationUs = this.manifest.getPeriodDurationUs(this.periodIndex);
            List<Representation> representations = getAdaptationSet().representations;
            for (int i = 0; i < this.representationHolders.length; i++) {
                this.representationHolders[i].updateRepresentation(periodDurationUs, (Representation) representations.get(this.trackSelection.getIndexInTrackGroup(i)));
            }
        } catch (BehindLiveWindowException e) {
            this.fatalError = e;
        }
    }

    public void maybeThrowError() throws IOException {
        if (this.fatalError != null) {
            throw this.fatalError;
        }
        this.manifestLoaderErrorThrower.maybeThrowError();
    }

    public int getPreferredQueueSize(long playbackPositionUs, List<? extends MediaChunk> queue) {
        if (this.fatalError != null || this.trackSelection.length() < 2) {
            return queue.size();
        }
        return this.trackSelection.evaluateQueueSize(playbackPositionUs, queue);
    }

    public final void getNextChunk(MediaChunk previous, long playbackPositionUs, ChunkHolder out) {
        if (this.fatalError == null) {
            this.trackSelection.updateSelectedTrack(previous != null ? previous.endTimeUs - playbackPositionUs : 0);
            RepresentationHolder representationHolder = this.representationHolders[this.trackSelection.getSelectedIndex()];
            if (representationHolder.extractorWrapper != null) {
                Representation selectedRepresentation = representationHolder.representation;
                RangedUri pendingInitializationUri = null;
                RangedUri pendingIndexUri = null;
                if (representationHolder.extractorWrapper.getSampleFormats() == null) {
                    pendingInitializationUri = selectedRepresentation.getInitializationUri();
                }
                if (representationHolder.segmentIndex == null) {
                    pendingIndexUri = selectedRepresentation.getIndexUri();
                }
                if (!(pendingInitializationUri == null && pendingIndexUri == null)) {
                    out.chunk = newInitializationChunk(representationHolder, this.dataSource, this.trackSelection.getSelectedFormat(), this.trackSelection.getSelectionReason(), this.trackSelection.getSelectionData(), pendingInitializationUri, pendingIndexUri);
                    return;
                }
            }
            long nowUs = getNowUnixTimeUs();
            int availableSegmentCount = representationHolder.getSegmentCount();
            boolean z;
            if (availableSegmentCount == 0) {
                z = !this.manifest.dynamic || this.periodIndex < this.manifest.getPeriodCount() - 1;
                out.endOfStream = z;
                return;
            }
            int lastAvailableSegmentNum;
            int segmentNum;
            int firstAvailableSegmentNum = representationHolder.getFirstSegmentNum();
            if (availableSegmentCount == -1) {
                long liveEdgeTimeInPeriodUs = (nowUs - (this.manifest.availabilityStartTime * 1000)) - (this.manifest.getPeriod(this.periodIndex).startMs * 1000);
                if (this.manifest.timeShiftBufferDepth != -9223372036854775807L) {
                    firstAvailableSegmentNum = Math.max(firstAvailableSegmentNum, representationHolder.getSegmentNum(liveEdgeTimeInPeriodUs - (this.manifest.timeShiftBufferDepth * 1000)));
                }
                lastAvailableSegmentNum = representationHolder.getSegmentNum(liveEdgeTimeInPeriodUs) - 1;
            } else {
                lastAvailableSegmentNum = (firstAvailableSegmentNum + availableSegmentCount) - 1;
            }
            if (previous == null) {
                segmentNum = Util.constrainValue(representationHolder.getSegmentNum(playbackPositionUs), firstAvailableSegmentNum, lastAvailableSegmentNum);
            } else {
                segmentNum = previous.getNextChunkIndex();
                if (segmentNum < firstAvailableSegmentNum) {
                    this.fatalError = new BehindLiveWindowException();
                    return;
                }
            }
            if (segmentNum > lastAvailableSegmentNum || (this.missingLastSegment && segmentNum >= lastAvailableSegmentNum)) {
                if (!this.manifest.dynamic || this.periodIndex < this.manifest.getPeriodCount() - 1) {
                    z = true;
                } else {
                    z = false;
                }
                out.endOfStream = z;
                return;
            }
            out.chunk = newMediaChunk(representationHolder, this.dataSource, this.trackSelection.getSelectedFormat(), this.trackSelection.getSelectionReason(), this.trackSelection.getSelectionData(), segmentNum, Math.min(this.maxSegmentsPerLoad, (lastAvailableSegmentNum - segmentNum) + 1));
        }
    }

    public void onChunkLoadCompleted(Chunk chunk) {
        if (chunk instanceof InitializationChunk) {
            RepresentationHolder representationHolder = this.representationHolders[this.trackSelection.indexOf(((InitializationChunk) chunk).trackFormat)];
            if (representationHolder.segmentIndex == null) {
                SeekMap seekMap = representationHolder.extractorWrapper.getSeekMap();
                if (seekMap != null) {
                    representationHolder.segmentIndex = new DashWrappingSegmentIndex((ChunkIndex) seekMap);
                }
            }
        }
    }

    public boolean onChunkLoadError(Chunk chunk, boolean cancelable, Exception e) {
        if (!cancelable) {
            return false;
        }
        if (!this.manifest.dynamic && (chunk instanceof MediaChunk) && (e instanceof InvalidResponseCodeException) && ((InvalidResponseCodeException) e).responseCode == 404) {
            RepresentationHolder representationHolder = this.representationHolders[this.trackSelection.indexOf(chunk.trackFormat)];
            int segmentCount = representationHolder.getSegmentCount();
            if (!(segmentCount == -1 || segmentCount == 0 || ((MediaChunk) chunk).getNextChunkIndex() <= (representationHolder.getFirstSegmentNum() + segmentCount) - 1)) {
                this.missingLastSegment = true;
                return true;
            }
        }
        return ChunkedTrackBlacklistUtil.maybeBlacklistTrack(this.trackSelection, this.trackSelection.indexOf(chunk.trackFormat), e);
    }

    private AdaptationSet getAdaptationSet() {
        return (AdaptationSet) this.manifest.getPeriod(this.periodIndex).adaptationSets.get(this.adaptationSetIndex);
    }

    private long getNowUnixTimeUs() {
        if (this.elapsedRealtimeOffsetMs != 0) {
            return (SystemClock.elapsedRealtime() + this.elapsedRealtimeOffsetMs) * 1000;
        }
        return System.currentTimeMillis() * 1000;
    }

    private static Chunk newInitializationChunk(RepresentationHolder representationHolder, DataSource dataSource, Format trackFormat, int trackSelectionReason, Object trackSelectionData, RangedUri initializationUri, RangedUri indexUri) {
        RangedUri requestUri;
        String baseUrl = representationHolder.representation.baseUrl;
        if (initializationUri != null) {
            requestUri = initializationUri.attemptMerge(indexUri, baseUrl);
            if (requestUri == null) {
                requestUri = initializationUri;
            }
        } else {
            requestUri = indexUri;
        }
        return new InitializationChunk(dataSource, new DataSpec(requestUri.resolveUri(baseUrl), requestUri.start, requestUri.length, representationHolder.representation.getCacheKey()), trackFormat, trackSelectionReason, trackSelectionData, representationHolder.extractorWrapper);
    }

    private static Chunk newMediaChunk(RepresentationHolder representationHolder, DataSource dataSource, Format trackFormat, int trackSelectionReason, Object trackSelectionData, int firstSegmentNum, int maxSegmentCount) {
        Representation representation = representationHolder.representation;
        long startTimeUs = representationHolder.getSegmentStartTimeUs(firstSegmentNum);
        RangedUri segmentUri = representationHolder.getSegmentUrl(firstSegmentNum);
        String baseUrl = representation.baseUrl;
        if (representationHolder.extractorWrapper == null) {
            long endTimeUs = representationHolder.getSegmentEndTimeUs(firstSegmentNum);
            return new SingleSampleMediaChunk(dataSource, new DataSpec(segmentUri.resolveUri(baseUrl), segmentUri.start, segmentUri.length, representation.getCacheKey()), trackFormat, trackSelectionReason, trackSelectionData, startTimeUs, endTimeUs, firstSegmentNum, representationHolder.trackType, trackFormat);
        }
        int segmentCount = 1;
        for (int i = 1; i < maxSegmentCount; i++) {
            RangedUri mergedSegmentUri = segmentUri.attemptMerge(representationHolder.getSegmentUrl(firstSegmentNum + i), baseUrl);
            if (mergedSegmentUri == null) {
                break;
            }
            segmentUri = mergedSegmentUri;
            segmentCount++;
        }
        endTimeUs = representationHolder.getSegmentEndTimeUs((firstSegmentNum + segmentCount) - 1);
        return new ContainerMediaChunk(dataSource, new DataSpec(segmentUri.resolveUri(baseUrl), segmentUri.start, segmentUri.length, representation.getCacheKey()), trackFormat, trackSelectionReason, trackSelectionData, startTimeUs, endTimeUs, firstSegmentNum, segmentCount, -representation.presentationTimeOffsetUs, representationHolder.extractorWrapper);
    }
}
