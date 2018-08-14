package com.google.android.exoplayer2.metadata.scte35;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class SpliceNullCommand extends SpliceCommand {
    public static final Creator<SpliceNullCommand> CREATOR = new C27361();

    static class C27361 implements Creator<SpliceNullCommand> {
        C27361() {
        }

        public SpliceNullCommand createFromParcel(Parcel in) {
            return new SpliceNullCommand();
        }

        public SpliceNullCommand[] newArray(int size) {
            return new SpliceNullCommand[size];
        }
    }

    public void writeToParcel(Parcel dest, int flags) {
    }
}
