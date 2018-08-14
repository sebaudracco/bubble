package com.processes.oneaudience.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class Statm extends ProcFile {
    public static final Creator<Statm> CREATOR = new C39381();
    public final String[] f9317a;

    static class C39381 implements Creator<Statm> {
        C39381() {
        }

        public Statm m12422a(Parcel parcel) {
            return new Statm(parcel);
        }

        public Statm[] m12423a(int i) {
            return new Statm[i];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m12422a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m12423a(i);
        }
    }

    private Statm(Parcel parcel) {
        super(parcel);
        this.f9317a = parcel.createStringArray();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeStringArray(this.f9317a);
    }
}
