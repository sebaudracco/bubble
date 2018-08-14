package com.processes.oneaudience.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

public final class Cgroup extends ProcFile {
    public static final Creator<Cgroup> CREATOR = new C39341();
    public final ArrayList<ControlGroup> f9312a;

    static class C39341 implements Creator<Cgroup> {
        C39341() {
        }

        public Cgroup m12407a(Parcel parcel) {
            return new Cgroup(parcel);
        }

        public Cgroup[] m12408a(int i) {
            return new Cgroup[i];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m12407a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m12408a(i);
        }
    }

    private Cgroup(Parcel parcel) {
        super(parcel);
        this.f9312a = parcel.createTypedArrayList(ControlGroup.CREATOR);
    }

    private Cgroup(String str) {
        super(str);
        String[] split = this.b.split("\n");
        this.f9312a = new ArrayList();
        for (String controlGroup : split) {
            try {
                this.f9312a.add(new ControlGroup(controlGroup));
            } catch (Exception e) {
            }
        }
    }

    public static Cgroup m12410a(int i) {
        return new Cgroup(String.format(Locale.ENGLISH, "/proc/%d/cgroup", new Object[]{Integer.valueOf(i)}));
    }

    public ControlGroup m12411a(String str) {
        Iterator it = this.f9312a.iterator();
        while (it.hasNext()) {
            ControlGroup controlGroup = (ControlGroup) it.next();
            for (String equals : controlGroup.f9314b.split(",")) {
                if (equals.equals(str)) {
                    return controlGroup;
                }
            }
        }
        return null;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeTypedList(this.f9312a);
    }
}
