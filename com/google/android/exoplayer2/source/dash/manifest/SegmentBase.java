package com.google.android.exoplayer2.source.dash.manifest;

import com.google.android.exoplayer2.util.Util;
import java.util.List;

public abstract class SegmentBase {
    final RangedUri initialization;
    final long presentationTimeOffset;
    final long timescale;

    public static abstract class MultiSegmentBase extends SegmentBase {
        final long duration;
        final List<SegmentTimelineElement> segmentTimeline;
        final int startNumber;

        public abstract int getSegmentCount(long j);

        public abstract RangedUri getSegmentUrl(Representation representation, int i);

        public MultiSegmentBase(RangedUri initialization, long timescale, long presentationTimeOffset, int startNumber, long duration, List<SegmentTimelineElement> segmentTimeline) {
            super(initialization, timescale, presentationTimeOffset);
            this.startNumber = startNumber;
            this.duration = duration;
            this.segmentTimeline = segmentTimeline;
        }

        public int getSegmentNum(long timeUs, long periodDurationUs) {
            int firstSegmentNum = getFirstSegmentNum();
            int segmentCount = getSegmentCount(periodDurationUs);
            if (segmentCount == 0) {
                return firstSegmentNum;
            }
            if (this.segmentTimeline == null) {
                int segmentNum = this.startNumber + ((int) (timeUs / ((this.duration * 1000000) / this.timescale)));
                if (segmentNum < firstSegmentNum) {
                    return firstSegmentNum;
                }
                if (segmentCount == -1) {
                    return segmentNum;
                }
                return Math.min(segmentNum, (firstSegmentNum + segmentCount) - 1);
            }
            int lowIndex = firstSegmentNum;
            int highIndex = (firstSegmentNum + segmentCount) - 1;
            while (lowIndex <= highIndex) {
                int midIndex = lowIndex + ((highIndex - lowIndex) / 2);
                long midTimeUs = getSegmentTimeUs(midIndex);
                if (midTimeUs < timeUs) {
                    lowIndex = midIndex + 1;
                } else if (midTimeUs <= timeUs) {
                    return midIndex;
                } else {
                    highIndex = midIndex - 1;
                }
            }
            if (lowIndex != firstSegmentNum) {
                lowIndex = highIndex;
            }
            return lowIndex;
        }

        public final long getSegmentDurationUs(int sequenceNumber, long periodDurationUs) {
            if (this.segmentTimeline != null) {
                return (((SegmentTimelineElement) this.segmentTimeline.get(sequenceNumber - this.startNumber)).duration * 1000000) / this.timescale;
            }
            int segmentCount = getSegmentCount(periodDurationUs);
            return (segmentCount == -1 || sequenceNumber != (getFirstSegmentNum() + segmentCount) - 1) ? (this.duration * 1000000) / this.timescale : periodDurationUs - getSegmentTimeUs(sequenceNumber);
        }

        public final long getSegmentTimeUs(int sequenceNumber) {
            long unscaledSegmentTime;
            if (this.segmentTimeline != null) {
                unscaledSegmentTime = ((SegmentTimelineElement) this.segmentTimeline.get(sequenceNumber - this.startNumber)).startTime - this.presentationTimeOffset;
            } else {
                unscaledSegmentTime = ((long) (sequenceNumber - this.startNumber)) * this.duration;
            }
            return Util.scaleLargeTimestamp(unscaledSegmentTime, 1000000, this.timescale);
        }

        public int getFirstSegmentNum() {
            return this.startNumber;
        }

        public boolean isExplicit() {
            return this.segmentTimeline != null;
        }
    }

    public static class SegmentList extends MultiSegmentBase {
        final List<RangedUri> mediaSegments;

        public SegmentList(RangedUri initialization, long timescale, long presentationTimeOffset, int startNumber, long duration, List<SegmentTimelineElement> segmentTimeline, List<RangedUri> mediaSegments) {
            super(initialization, timescale, presentationTimeOffset, startNumber, duration, segmentTimeline);
            this.mediaSegments = mediaSegments;
        }

        public RangedUri getSegmentUrl(Representation representation, int sequenceNumber) {
            return (RangedUri) this.mediaSegments.get(sequenceNumber - this.startNumber);
        }

        public int getSegmentCount(long periodDurationUs) {
            return this.mediaSegments.size();
        }

        public boolean isExplicit() {
            return true;
        }
    }

    public static class SegmentTemplate extends MultiSegmentBase {
        final UrlTemplate initializationTemplate;
        final UrlTemplate mediaTemplate;

        public SegmentTemplate(RangedUri initialization, long timescale, long presentationTimeOffset, int startNumber, long duration, List<SegmentTimelineElement> segmentTimeline, UrlTemplate initializationTemplate, UrlTemplate mediaTemplate) {
            super(initialization, timescale, presentationTimeOffset, startNumber, duration, segmentTimeline);
            this.initializationTemplate = initializationTemplate;
            this.mediaTemplate = mediaTemplate;
        }

        public RangedUri getInitialization(Representation representation) {
            if (this.initializationTemplate != null) {
                return new RangedUri(this.initializationTemplate.buildUri(representation.format.id, 0, representation.format.bitrate, 0), 0, -1);
            }
            return super.getInitialization(representation);
        }

        public RangedUri getSegmentUrl(Representation representation, int sequenceNumber) {
            long time;
            if (this.segmentTimeline != null) {
                time = ((SegmentTimelineElement) this.segmentTimeline.get(sequenceNumber - this.startNumber)).startTime;
            } else {
                time = ((long) (sequenceNumber - this.startNumber)) * this.duration;
            }
            return new RangedUri(this.mediaTemplate.buildUri(representation.format.id, sequenceNumber, representation.format.bitrate, time), 0, -1);
        }

        public int getSegmentCount(long periodDurationUs) {
            if (this.segmentTimeline != null) {
                return this.segmentTimeline.size();
            }
            if (periodDurationUs != -9223372036854775807L) {
                return (int) Util.ceilDivide(periodDurationUs, (this.duration * 1000000) / this.timescale);
            }
            return -1;
        }
    }

    public static class SegmentTimelineElement {
        final long duration;
        final long startTime;

        public SegmentTimelineElement(long startTime, long duration) {
            this.startTime = startTime;
            this.duration = duration;
        }
    }

    public static class SingleSegmentBase extends SegmentBase {
        final long indexLength;
        final long indexStart;

        public SingleSegmentBase(RangedUri initialization, long timescale, long presentationTimeOffset, long indexStart, long indexLength) {
            super(initialization, timescale, presentationTimeOffset);
            this.indexStart = indexStart;
            this.indexLength = indexLength;
        }

        public SingleSegmentBase() {
            this(null, 1, 0, 0, 0);
        }

        public RangedUri getIndex() {
            return this.indexLength <= 0 ? null : new RangedUri(null, this.indexStart, this.indexLength);
        }
    }

    public SegmentBase(RangedUri initialization, long timescale, long presentationTimeOffset) {
        this.initialization = initialization;
        this.timescale = timescale;
        this.presentationTimeOffset = presentationTimeOffset;
    }

    public RangedUri getInitialization(Representation representation) {
        return this.initialization;
    }

    public long getPresentationTimeOffsetUs() {
        return Util.scaleLargeTimestamp(this.presentationTimeOffset, 1000000, this.timescale);
    }
}
