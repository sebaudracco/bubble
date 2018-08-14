package com.altbeacon.beacon;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public class AltBeacon extends Beacon {
    public static final Creator<AltBeacon> CREATOR = new C08141();

    static class C08141 implements Creator<AltBeacon> {
        C08141() {
        }

        public AltBeacon m1536a(Parcel parcel) {
            return new AltBeacon(parcel);
        }

        public AltBeacon[] m1537a(int i) {
            return new AltBeacon[i];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m1536a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m1537a(i);
        }
    }

    protected AltBeacon() {
    }

    protected AltBeacon(Parcel parcel) {
        super(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }
}
