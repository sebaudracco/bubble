package com.jaredrummler.android.processes.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class ControlGroup implements Parcelable {
    public static final Creator<ControlGroup> CREATOR = new C33471();
    public final String group;
    public final int id;
    public final String subsystems;

    static class C33471 implements Creator<ControlGroup> {
        C33471() {
        }

        public ControlGroup createFromParcel(Parcel source) {
            return new ControlGroup(source);
        }

        public ControlGroup[] newArray(int size) {
            return new ControlGroup[size];
        }
    }

    protected ControlGroup(String line) throws NumberFormatException, IndexOutOfBoundsException {
        String[] fields = line.split(":");
        this.id = Integer.parseInt(fields[0]);
        this.subsystems = fields[1];
        this.group = fields[2];
    }

    protected ControlGroup(Parcel in) {
        this.id = in.readInt();
        this.subsystems = in.readString();
        this.group = in.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.subsystems);
        dest.writeString(this.group);
    }
}
