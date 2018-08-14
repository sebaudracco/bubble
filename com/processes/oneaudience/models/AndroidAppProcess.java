package com.processes.oneaudience.models;

import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.processes.oneaudience.C3930a;
import java.io.File;
import java.util.regex.Pattern;

public class AndroidAppProcess extends AndroidProcess {
    public static final Creator<AndroidAppProcess> CREATOR = new C39311();
    private static final boolean f9307e = new File("/dev/cpuctl/tasks").exists();
    private static final Pattern f9308f = Pattern.compile("^([A-Za-z]{1}[A-Za-z0-9_]*[\\.|:])*[A-Za-z][A-Za-z0-9_]*$");
    public final boolean f9309a;
    public final int f9310b;

    static class C39311 implements Creator<AndroidAppProcess> {
        C39311() {
        }

        public AndroidAppProcess m12398a(Parcel parcel) {
            return new AndroidAppProcess(parcel);
        }

        public AndroidAppProcess[] m12399a(int i) {
            return new AndroidAppProcess[i];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m12398a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m12399a(i);
        }
    }

    public static final class C3932a extends Exception {
        public C3932a(int i) {
            super(String.format("The process %d does not belong to any application", new Object[]{Integer.valueOf(i)}));
        }
    }

    public AndroidAppProcess(int i) {
        super(i);
        if (this.c != null && f9308f.matcher(this.c).matches() && new File("/data/data", m12404a()).exists()) {
            boolean z;
            int parseInt;
            if (f9307e) {
                Cgroup b = m12401b();
                ControlGroup a = b.m12411a("cpuacct");
                ControlGroup a2 = b.m12411a("cpu");
                if (VERSION.SDK_INT >= 21) {
                    if (a2 == null || a == null || !a.f9315c.contains("pid_")) {
                        throw new C3932a(i);
                    }
                    z = !a2.f9315c.contains("bg_non_interactive");
                    try {
                        parseInt = Integer.parseInt(a.f9315c.split(BridgeUtil.SPLIT_MARK)[1].replace("uid_", ""));
                    } catch (Exception e) {
                        parseInt = m12403d().m12427a();
                    }
                    C3930a.m12396a("name=%s, pid=%d, uid=%d, foreground=%b, cpuacct=%s, cpu=%s", this.c, Integer.valueOf(i), Integer.valueOf(parseInt), Boolean.valueOf(z), a.toString(), a2.toString());
                } else if (a2 == null || a == null || !a2.f9315c.contains("apps")) {
                    throw new C3932a(i);
                } else {
                    z = !a2.f9315c.contains("bg_non_interactive");
                    try {
                        parseInt = Integer.parseInt(a.f9315c.substring(a.f9315c.lastIndexOf(BridgeUtil.SPLIT_MARK) + 1));
                    } catch (Exception e2) {
                        parseInt = m12403d().m12427a();
                    }
                    C3930a.m12396a("name=%s, pid=%d, uid=%d foreground=%b, cpuacct=%s, cpu=%s", this.c, Integer.valueOf(i), Integer.valueOf(parseInt), Boolean.valueOf(z), a.toString(), a2.toString());
                }
            } else {
                Stat c = m12402c();
                Status d = m12403d();
                z = c.m12421c() == 0;
                parseInt = d.m12427a();
                C3930a.m12396a("name=%s, pid=%d, uid=%d foreground=%b", this.c, Integer.valueOf(i), Integer.valueOf(parseInt), Boolean.valueOf(z));
            }
            this.f9309a = z;
            this.f9310b = parseInt;
            return;
        }
        throw new C3932a(i);
    }

    protected AndroidAppProcess(Parcel parcel) {
        super(parcel);
        this.f9309a = parcel.readByte() != (byte) 0;
        this.f9310b = parcel.readInt();
    }

    public String m12404a() {
        return this.c.split(":")[0];
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeByte((byte) (this.f9309a ? 1 : 0));
        parcel.writeInt(this.f9310b);
    }
}
