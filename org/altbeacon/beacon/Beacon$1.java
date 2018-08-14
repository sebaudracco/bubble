package org.altbeacon.beacon;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class Beacon$1 implements Creator<Beacon> {
    Beacon$1() {
    }

    public Beacon createFromParcel(Parcel in) {
        return new Beacon(in);
    }

    public Beacon[] newArray(int size) {
        return new Beacon[size];
    }
}
