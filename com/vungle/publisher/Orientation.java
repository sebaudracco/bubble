package com.vungle.publisher;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

/* compiled from: vungle */
public enum Orientation implements Parcelable {
    autoRotate,
    matchVideo;
    
    public static final Creator<Orientation> CREATOR = null;

    /* compiled from: vungle */
    static class C41641 implements Creator<Orientation> {
        C41641() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m12873a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m12874a(i);
        }

        public Orientation m12873a(Parcel parcel) {
            return Orientation.values()[parcel.readInt()];
        }

        public Orientation[] m12874a(int i) {
            return new Orientation[i];
        }
    }

    static {
        CREATOR = new C41641();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(ordinal());
    }
}
