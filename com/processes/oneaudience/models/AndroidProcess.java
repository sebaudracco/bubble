package com.processes.oneaudience.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import java.io.IOException;
import java.util.Locale;

public class AndroidProcess implements Parcelable {
    public static final Creator<AndroidProcess> CREATOR = new C39331();
    public final String f9305c;
    public final int f9306d;

    static class C39331 implements Creator<AndroidProcess> {
        C39331() {
        }

        public AndroidProcess m12405a(Parcel parcel) {
            return new AndroidProcess(parcel);
        }

        public AndroidProcess[] m12406a(int i) {
            return new AndroidProcess[i];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m12405a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m12406a(i);
        }
    }

    public AndroidProcess(int i) {
        this.f9306d = i;
        this.f9305c = m12400a(i);
    }

    protected AndroidProcess(Parcel parcel) {
        this.f9305c = parcel.readString();
        this.f9306d = parcel.readInt();
    }

    private String m12400a(int i) {
        String str = null;
        try {
            str = ProcFile.m12409b(String.format(Locale.ENGLISH, "/proc/%d/cmdline", new Object[]{Integer.valueOf(i)})).trim();
        } catch (IOException e) {
        }
        if (TextUtils.isEmpty(str)) {
            try {
                str = Stat.m12418a(i).m12419a();
            } catch (Exception e2) {
                throw new IOException(String.format(Locale.ENGLISH, "Error reading /proc/%d/stat", new Object[]{Integer.valueOf(i)}));
            }
        }
        return str;
    }

    public Cgroup m12401b() {
        return Cgroup.m12410a(this.f9306d);
    }

    public Stat m12402c() {
        return Stat.m12418a(this.f9306d);
    }

    public Status m12403d() {
        return Status.m12426a(this.f9306d);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f9305c);
        parcel.writeInt(this.f9306d);
    }
}
