package com.jaredrummler.android.processes.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public final class Cgroup extends ProcFile {
    public static final Creator<Cgroup> CREATOR = new C33461();
    public final ArrayList<ControlGroup> groups;

    static class C33461 implements Creator<Cgroup> {
        C33461() {
        }

        public Cgroup createFromParcel(Parcel source) {
            return new Cgroup(source);
        }

        public Cgroup[] newArray(int size) {
            return new Cgroup[size];
        }
    }

    public static Cgroup get(int pid) throws IOException {
        return new Cgroup(String.format("/proc/%d/cgroup", new Object[]{Integer.valueOf(pid)}));
    }

    private Cgroup(String path) throws IOException {
        super(path);
        String[] lines = this.content.split("\n");
        this.groups = new ArrayList();
        for (String line : lines) {
            try {
                this.groups.add(new ControlGroup(line));
            } catch (Exception e) {
            }
        }
    }

    private Cgroup(Parcel in) {
        super(in);
        this.groups = in.createTypedArrayList(ControlGroup.CREATOR);
    }

    public ControlGroup getGroup(String subsystem) {
        Iterator it = this.groups.iterator();
        while (it.hasNext()) {
            ControlGroup group = (ControlGroup) it.next();
            for (String name : group.subsystems.split(",")) {
                if (name.equals(subsystem)) {
                    return group;
                }
            }
        }
        return null;
    }

    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeTypedList(this.groups);
    }
}
