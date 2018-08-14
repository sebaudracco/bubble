package com.processes.oneaudience;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import com.processes.oneaudience.models.AndroidAppProcess;
import com.processes.oneaudience.models.AndroidAppProcess.C3932a;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class C3930a {
    private static boolean f9304a;

    public static List<AndroidAppProcess> m12395a(Context context) {
        List<AndroidAppProcess> arrayList = new ArrayList();
        File[] listFiles = new File("/proc").listFiles();
        PackageManager packageManager = context.getPackageManager();
        for (File file : listFiles) {
            if (file.isDirectory()) {
                try {
                    try {
                        AndroidAppProcess androidAppProcess = new AndroidAppProcess(Integer.parseInt(file.getName()));
                        if (androidAppProcess.f9309a && !((androidAppProcess.f9310b >= 1000 && androidAppProcess.f9310b <= 9999) || androidAppProcess.c.contains(":") || packageManager.getLaunchIntentForPackage(androidAppProcess.m12404a()) == null)) {
                            arrayList.add(androidAppProcess);
                        }
                    } catch (C3932a e) {
                    } catch (Throwable e2) {
                        C3930a.m12397a(e2, "Error reading from /proc/%d.", Integer.valueOf(r7));
                    }
                } catch (NumberFormatException e3) {
                }
            }
        }
        return arrayList;
    }

    public static void m12396a(String str, Object... objArr) {
        if (f9304a) {
            String str2 = "AndroidProcesses";
            if (objArr.length != 0) {
                str = String.format(str, objArr);
            }
            Log.d(str2, str);
        }
    }

    public static void m12397a(Throwable th, String str, Object... objArr) {
        if (f9304a) {
            String str2 = "AndroidProcesses";
            if (objArr.length != 0) {
                str = String.format(str, objArr);
            }
            Log.d(str2, str, th);
        }
    }
}
