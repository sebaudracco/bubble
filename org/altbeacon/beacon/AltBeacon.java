package org.altbeacon.beacon;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;

public class AltBeacon extends Beacon {
    public static final Creator<AltBeacon> CREATOR = new C47851();
    private static final String TAG = "AltBeacon";

    static class C47851 implements Creator<AltBeacon> {
        C47851() {
        }

        public AltBeacon createFromParcel(Parcel in) {
            return new AltBeacon(in);
        }

        public AltBeacon[] newArray(int size) {
            return new AltBeacon[size];
        }
    }

    public static class Builder extends Beacon$Builder {
        public Beacon build() {
            return new AltBeacon(super.build());
        }

        public Builder setMfgReserved(int mfgReserved) {
            if (this.mBeacon.mDataFields.size() != 0) {
                this.mBeacon.mDataFields = new ArrayList();
            }
            this.mBeacon.mDataFields.add(Long.valueOf((long) mfgReserved));
            return this;
        }
    }

    protected AltBeacon(Beacon beacon) {
        super(beacon);
    }

    protected AltBeacon() {
    }

    protected AltBeacon(Parcel in) {
        super(in);
    }

    public int getMfgReserved() {
        return ((Long) this.mDataFields.get(0)).intValue();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
    }
}
