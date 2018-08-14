package com.processes.oneaudience.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.Locale;

public class ControlGroup implements Parcelable {
    public static final Creator<ControlGroup> CREATOR = new C39351();
    public final int f9313a;
    public final String f9314b;
    public final String f9315c;

    static class C39351 implements Creator<ControlGroup> {
        C39351() {
        }

        public ControlGroup m12412a(Parcel parcel) {
            return new ControlGroup(parcel);
        }

        public ControlGroup[] m12413a(int i) {
            return new ControlGroup[i];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m12412a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m12413a(i);
        }
    }

    protected ControlGroup(Parcel parcel) {
        this.f9313a = parcel.readInt();
        this.f9314b = parcel.readString();
        this.f9315c = parcel.readString();
    }

    protected ControlGroup(String str) {
        String[] split = str.split(":");
        this.f9313a = Integer.parseInt(split[0]);
        this.f9314b = split[1];
        this.f9315c = split[2];
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return String.format(Locale.ENGLISH, "%d:%s:%s", new Object[]{Integer.valueOf(this.f9313a), this.f9314b, this.f9315c});
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f9313a);
        parcel.writeString(this.f9314b);
        parcel.writeString(this.f9315c);
    }
}
