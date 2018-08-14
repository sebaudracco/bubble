package org.altbeacon.beacon;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class Region$1 implements Creator<Region> {
    Region$1() {
    }

    public Region createFromParcel(Parcel in) {
        return new Region(in);
    }

    public Region[] newArray(int size) {
        return new Region[size];
    }
}
