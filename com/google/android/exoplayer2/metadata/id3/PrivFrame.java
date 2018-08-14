package com.google.android.exoplayer2.metadata.id3;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;

public final class PrivFrame extends Id3Frame {
    public static final Creator<PrivFrame> CREATOR = new C27311();
    public static final String ID = "PRIV";
    public final String owner;
    public final byte[] privateData;

    static class C27311 implements Creator<PrivFrame> {
        C27311() {
        }

        public PrivFrame createFromParcel(Parcel in) {
            return new PrivFrame(in);
        }

        public PrivFrame[] newArray(int size) {
            return new PrivFrame[size];
        }
    }

    public PrivFrame(String owner, byte[] privateData) {
        super("PRIV");
        this.owner = owner;
        this.privateData = privateData;
    }

    PrivFrame(Parcel in) {
        super("PRIV");
        this.owner = in.readString();
        this.privateData = in.createByteArray();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PrivFrame other = (PrivFrame) obj;
        if (Util.areEqual(this.owner, other.owner) && Arrays.equals(this.privateData, other.privateData)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((this.owner != null ? this.owner.hashCode() : 0) + 527) * 31) + Arrays.hashCode(this.privateData);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.owner);
        dest.writeByteArray(this.privateData);
    }
}
