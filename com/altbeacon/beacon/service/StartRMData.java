package com.altbeacon.beacon.service;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.altbeacon.beacon.Region;
import java.io.Serializable;

public class StartRMData implements Parcelable, Serializable {
    public static final Creator<StartRMData> CREATOR = new C08451();
    private Region f1665a;
    private long f1666b;
    private long f1667c;
    private boolean f1668d;
    private String f1669e;

    static class C08451 implements Creator<StartRMData> {
        C08451() {
        }

        public StartRMData m1699a(Parcel parcel) {
            return new StartRMData(parcel);
        }

        public StartRMData[] m1700a(int i) {
            return new StartRMData[i];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m1699a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m1700a(i);
        }
    }

    private StartRMData(Parcel parcel) {
        this.f1665a = (Region) parcel.readParcelable(StartRMData.class.getClassLoader());
        this.f1669e = parcel.readString();
        this.f1666b = parcel.readLong();
        this.f1667c = parcel.readLong();
        this.f1668d = parcel.readByte() != (byte) 0;
    }

    public StartRMData(Region region, String str, long j, long j2, boolean z) {
        this.f1666b = j;
        this.f1667c = j2;
        this.f1665a = region;
        this.f1669e = str;
        this.f1668d = z;
    }

    public long m1701a() {
        return this.f1666b;
    }

    public long m1702b() {
        return this.f1667c;
    }

    public Region m1703c() {
        return this.f1665a;
    }

    public String m1704d() {
        return this.f1669e;
    }

    public int describeContents() {
        return 0;
    }

    public boolean m1705e() {
        return this.f1668d;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f1665a, i);
        parcel.writeString(this.f1669e);
        parcel.writeLong(this.f1666b);
        parcel.writeLong(this.f1667c);
        parcel.writeByte((byte) (this.f1668d ? 1 : 0));
    }
}
