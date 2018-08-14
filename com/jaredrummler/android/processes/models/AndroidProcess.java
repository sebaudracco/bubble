package com.jaredrummler.android.processes.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import java.io.IOException;

public class AndroidProcess implements Parcelable {
    public static final Creator<AndroidProcess> CREATOR = new C33451();
    public final String name;
    public final int pid;

    static class C33451 implements Creator<AndroidProcess> {
        C33451() {
        }

        public AndroidProcess createFromParcel(Parcel source) {
            return new AndroidProcess(source);
        }

        public AndroidProcess[] newArray(int size) {
            return new AndroidProcess[size];
        }
    }

    static String getProcessName(int pid) throws IOException {
        String cmdline = null;
        try {
            cmdline = ProcFile.readFile(String.format("/proc/%d/cmdline", new Object[]{Integer.valueOf(pid)})).trim();
        } catch (IOException e) {
        }
        if (TextUtils.isEmpty(cmdline)) {
            return Stat.get(pid).getComm();
        }
        return cmdline;
    }

    public AndroidProcess(int pid) throws IOException {
        this.pid = pid;
        this.name = getProcessName(pid);
    }

    public String read(String filename) throws IOException {
        return ProcFile.readFile(String.format("/proc/%d/%s", new Object[]{Integer.valueOf(this.pid), filename}));
    }

    public String attr_current() throws IOException {
        return read("attr/current");
    }

    public String cmdline() throws IOException {
        return read("cmdline");
    }

    public Cgroup cgroup() throws IOException {
        return Cgroup.get(this.pid);
    }

    public int oom_adj() throws IOException {
        return Integer.parseInt(read("oom_adj"));
    }

    public int oom_score_adj() throws IOException {
        return Integer.parseInt(read("oom_score_adj"));
    }

    public Stat stat() throws IOException {
        return Stat.get(this.pid);
    }

    public Statm statm() throws IOException {
        return Statm.get(this.pid);
    }

    public Status status() throws IOException {
        return Status.get(this.pid);
    }

    public String wchan() throws IOException {
        return read("wchan");
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.pid);
    }

    protected AndroidProcess(Parcel in) {
        this.name = in.readString();
        this.pid = in.readInt();
    }
}
