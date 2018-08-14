package com.google.android.exoplayer2.metadata.scte35;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;

public final class TimeSignalCommand extends SpliceCommand {
    public static final Creator<TimeSignalCommand> CREATOR = new C27381();
    public final long playbackPositionUs;
    public final long ptsTime;

    static class C27381 implements Creator<TimeSignalCommand> {
        C27381() {
        }

        public TimeSignalCommand createFromParcel(Parcel in) {
            return new TimeSignalCommand(in.readLong(), in.readLong());
        }

        public TimeSignalCommand[] newArray(int size) {
            return new TimeSignalCommand[size];
        }
    }

    private TimeSignalCommand(long ptsTime, long playbackPositionUs) {
        this.ptsTime = ptsTime;
        this.playbackPositionUs = playbackPositionUs;
    }

    static TimeSignalCommand parseFromSection(ParsableByteArray sectionData, long ptsAdjustment, TimestampAdjuster timestampAdjuster) {
        long ptsTime = parseSpliceTime(sectionData, ptsAdjustment);
        return new TimeSignalCommand(ptsTime, timestampAdjuster.adjustTsTimestamp(ptsTime));
    }

    static long parseSpliceTime(ParsableByteArray sectionData, long ptsAdjustment) {
        long firstByte = (long) sectionData.readUnsignedByte();
        if ((128 & firstByte) != 0) {
            return ((((1 & firstByte) << 32) | sectionData.readUnsignedInt()) + ptsAdjustment) & 8589934591L;
        }
        return -9223372036854775807L;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.ptsTime);
        dest.writeLong(this.playbackPositionUs);
    }
}
