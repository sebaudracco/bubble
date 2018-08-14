package org.altbeacon.beacon.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Process;
import android.support.annotation.NonNull;
import com.bgjd.ici.p025b.C1408j.C1404b;
import java.util.List;

public class ProcessUtils {
    Context mContext;

    public ProcessUtils(@NonNull Context context) {
        this.mContext = context;
    }

    public String getProcessName() {
        List<RunningAppProcessInfo> processes = ((ActivityManager) this.mContext.getSystemService(C1404b.aw)).getRunningAppProcesses();
        if (processes != null) {
            for (RunningAppProcessInfo processInfo : processes) {
                if (processInfo.pid == getPid()) {
                    return processInfo.processName;
                }
            }
        }
        return null;
    }

    public String getPackageName() {
        return this.mContext.getApplicationContext().getPackageName();
    }

    public int getPid() {
        return Process.myPid();
    }

    public boolean isMainProcess() {
        return getPackageName().equals(getProcessName());
    }
}
