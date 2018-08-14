package com.jaredrummler.android.processes;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.Process;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.jaredrummler.android.processes.models.AndroidAppProcess;
import com.jaredrummler.android.processes.models.AndroidAppProcess.NotAndroidAppProcessException;
import com.jaredrummler.android.processes.models.AndroidProcess;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProcessManager {

    public static class Filter {
        private boolean apps;
        private String name;
        private int pid = -1;
        private int ppid = -1;

        public Filter setName(String name) {
            this.name = name;
            return this;
        }

        public Filter setPid(int pid) {
            this.pid = pid;
            return this;
        }

        public Filter setPpid(int ppid) {
            this.ppid = ppid;
            return this;
        }

        public Filter setApps(boolean apps) {
            this.apps = apps;
            return this;
        }

        public List<AndroidProcess> run() {
            List<AndroidProcess> processes = new ArrayList();
            for (File file : new File("/proc").listFiles()) {
                if (file.isDirectory()) {
                    try {
                        int pid = Integer.parseInt(file.getName());
                        if (this.pid == -1 || pid == this.pid) {
                            try {
                                AndroidProcess process;
                                if (this.apps) {
                                    process = new AndroidAppProcess(pid);
                                } else {
                                    process = new AndroidProcess(pid);
                                }
                                if ((this.name == null || process.name.contains(this.name)) && (this.ppid == -1 || process.stat().ppid() == this.ppid)) {
                                    processes.add(process);
                                }
                            } catch (IOException e) {
                            } catch (NotAndroidAppProcessException e2) {
                            }
                        }
                    } catch (NumberFormatException e3) {
                    }
                }
            }
            return processes;
        }
    }

    public static final class ProcessComparator implements Comparator<AndroidProcess> {
        public int compare(AndroidProcess p1, AndroidProcess p2) {
            return p1.name.compareToIgnoreCase(p2.name);
        }
    }

    private ProcessManager() {
        throw new AssertionError("no instances");
    }

    public static List<AndroidProcess> getRunningProcesses() {
        List<AndroidProcess> processes = new ArrayList();
        for (File file : new File("/proc").listFiles()) {
            if (file.isDirectory()) {
                try {
                    try {
                        processes.add(new AndroidProcess(Integer.parseInt(file.getName())));
                    } catch (IOException e) {
                    }
                } catch (NumberFormatException e2) {
                }
            }
        }
        return processes;
    }

    public static List<AndroidAppProcess> getRunningAppProcesses() {
        List<AndroidAppProcess> processes = new ArrayList();
        for (File file : new File("/proc").listFiles()) {
            if (file.isDirectory()) {
                try {
                    try {
                        processes.add(new AndroidAppProcess(Integer.parseInt(file.getName())));
                    } catch (NotAndroidAppProcessException e) {
                    } catch (IOException e2) {
                    }
                } catch (NumberFormatException e3) {
                }
            }
        }
        return processes;
    }

    public static List<AndroidAppProcess> getRunningForegroundApps(Context ctx) {
        List<AndroidAppProcess> processes = new ArrayList();
        File[] files = new File("/proc").listFiles();
        PackageManager pm = ctx.getPackageManager();
        for (File file : files) {
            if (file.isDirectory()) {
                try {
                    try {
                        AndroidAppProcess process = new AndroidAppProcess(Integer.parseInt(file.getName()));
                        if (process.foreground && !((process.uid >= 1000 && process.uid <= 9999) || process.name.contains(":") || pm.getLaunchIntentForPackage(process.getPackageName()) == null)) {
                            processes.add(process);
                        }
                    } catch (NotAndroidAppProcessException e) {
                    } catch (IOException e2) {
                    }
                } catch (NumberFormatException e3) {
                }
            }
        }
        return processes;
    }

    public static boolean isMyProcessInTheForeground() {
        List<AndroidAppProcess> processes = getRunningAppProcesses();
        int myPid = Process.myPid();
        for (AndroidAppProcess process : processes) {
            if (process.pid == myPid && process.foreground) {
                return true;
            }
        }
        return false;
    }

    public static List<RunningAppProcessInfo> getRunningAppProcessInfo(Context ctx) {
        if (VERSION.SDK_INT < 22) {
            return ((ActivityManager) ctx.getSystemService(C1404b.aw)).getRunningAppProcesses();
        }
        List<AndroidAppProcess> runningAppProcesses = getRunningAppProcesses();
        List<RunningAppProcessInfo> arrayList = new ArrayList();
        for (AndroidAppProcess process : runningAppProcesses) {
            RunningAppProcessInfo info = new RunningAppProcessInfo(process.name, process.pid, null);
            info.uid = process.uid;
            arrayList.add(info);
        }
        return arrayList;
    }
}
