package com.altbeacon.beacon.p014d;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Process;
import com.bgjd.ici.p025b.C1408j.C1404b;

public class C0839a {
    Context f1656a;

    public C0839a(Context context) {
        this.f1656a = context;
    }

    public String m1677a() {
        for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) this.f1656a.getSystemService(C1404b.aw)).getRunningAppProcesses()) {
            if (runningAppProcessInfo.pid == m1679c()) {
                return runningAppProcessInfo.processName;
            }
        }
        return null;
    }

    public String m1678b() {
        return this.f1656a.getApplicationContext().getPackageName();
    }

    public int m1679c() {
        return Process.myPid();
    }

    public boolean m1680d() {
        return m1678b().equals(m1677a());
    }
}
