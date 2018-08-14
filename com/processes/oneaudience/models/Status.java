package com.processes.oneaudience.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.Locale;

public final class Status extends ProcFile {
    public static final Creator<Status> CREATOR = new C39391();

    static class C39391 implements Creator<Status> {
        C39391() {
        }

        public Status m12424a(Parcel parcel) {
            return new Status(parcel);
        }

        public Status[] m12425a(int i) {
            return new Status[i];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m12424a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m12425a(i);
        }
    }

    private Status(Parcel parcel) {
        super(parcel);
    }

    private Status(String str) {
        super(str);
    }

    public static Status m12426a(int i) {
        return new Status(String.format(Locale.ENGLISH, "/proc/%d/status", new Object[]{Integer.valueOf(i)}));
    }

    public int m12427a() {
        try {
            return Integer.parseInt(m12428a("Uid").split("\\s+")[0]);
        } catch (Exception e) {
            return -1;
        }
    }

    public String m12428a(String str) {
        for (String str2 : this.b.split("\n")) {
            if (str2.startsWith(str + ":")) {
                return str2.split(str + ":")[1].trim();
            }
        }
        return null;
    }
}
