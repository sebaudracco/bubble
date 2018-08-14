package com.processes.oneaudience.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.Locale;

public final class Stat extends ProcFile {
    public static final Creator<Stat> CREATOR = new C39371();
    private final String[] f9316a;

    static class C39371 implements Creator<Stat> {
        C39371() {
        }

        public Stat m12416a(Parcel parcel) {
            return new Stat(parcel);
        }

        public Stat[] m12417a(int i) {
            return new Stat[i];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m12416a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m12417a(i);
        }
    }

    private Stat(Parcel parcel) {
        super(parcel);
        this.f9316a = parcel.createStringArray();
    }

    private Stat(String str) {
        super(str);
        this.f9316a = this.b.split("\\s+");
    }

    public static Stat m12418a(int i) {
        return new Stat(String.format(Locale.ENGLISH, "/proc/%d/stat", new Object[]{Integer.valueOf(i)}));
    }

    public String m12419a() {
        return this.f9316a[1].replace("(", "").replace(")", "");
    }

    public long m12420b() {
        return Long.parseLong(this.f9316a[21]);
    }

    public int m12421c() {
        return Integer.parseInt(this.f9316a[40]);
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeStringArray(this.f9316a);
    }
}
