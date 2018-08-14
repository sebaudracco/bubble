package com.jaredrummler.android.processes.models;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import java.io.IOException;

public class AndroidAppProcess extends AndroidProcess {
    public static final Creator<AndroidAppProcess> CREATOR = new C33441();
    private final Cgroup cgroup;
    public boolean foreground;
    public int uid;

    static class C33441 implements Creator<AndroidAppProcess> {
        C33441() {
        }

        public AndroidAppProcess createFromParcel(Parcel source) {
            return new AndroidAppProcess(source);
        }

        public AndroidAppProcess[] newArray(int size) {
            return new AndroidAppProcess[size];
        }
    }

    public static final class NotAndroidAppProcessException extends Exception {
        public NotAndroidAppProcessException(int pid) {
            super(String.format("The process %d does not belong to any application", new Object[]{Integer.valueOf(pid)}));
        }
    }

    public AndroidAppProcess(int pid) throws IOException, NotAndroidAppProcessException {
        boolean z = true;
        super(pid);
        this.cgroup = super.cgroup();
        ControlGroup cpuacct = this.cgroup.getGroup("cpuacct");
        ControlGroup cpu = this.cgroup.getGroup("cpu");
        if (cpu == null || cpuacct == null || !cpuacct.group.contains("pid_")) {
            throw new NotAndroidAppProcessException(pid);
        }
        if (cpu.group.contains("bg_non_interactive")) {
            z = false;
        }
        this.foreground = z;
        try {
            this.uid = Integer.parseInt(cpuacct.group.split(BridgeUtil.SPLIT_MARK)[1].replace("uid_", ""));
        } catch (Exception e) {
            this.uid = status().getUid();
        }
    }

    public String getPackageName() {
        return this.name.split(":")[0];
    }

    public PackageInfo getPackageInfo(Context context, int flags) throws NameNotFoundException {
        return context.getPackageManager().getPackageInfo(getPackageName(), flags);
    }

    public Cgroup cgroup() {
        return this.cgroup;
    }

    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.cgroup, flags);
        dest.writeByte((byte) (this.foreground ? 1 : 0));
    }

    protected AndroidAppProcess(Parcel in) {
        super(in);
        this.cgroup = (Cgroup) in.readParcelable(Cgroup.class.getClassLoader());
        this.foreground = in.readByte() != (byte) 0;
    }
}
