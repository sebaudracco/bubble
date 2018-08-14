package com.facebook.ads.internal.p056q.p057a;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.util.Log;
import com.bgjd.ici.p025b.C1408j.C1404b;

public class C2116g {
    public static boolean m6789a(int i, int i2) {
        return i >= 640 && i2 >= 640;
    }

    public static boolean m6790a(Context context) {
        try {
            RunningTaskInfo runningTaskInfo = (RunningTaskInfo) ((ActivityManager) context.getSystemService(C1404b.aw)).getRunningTasks(2).get(0);
            String str = runningTaskInfo.topActivity.getPackageName() + ".UnityPlayerActivity";
            boolean z = runningTaskInfo.topActivity.getClassName() == str || C2116g.m6791a(str);
            Boolean valueOf = Boolean.valueOf(z);
            Log.d("IS_UNITY", Boolean.toString(valueOf.booleanValue()));
            return valueOf.booleanValue();
        } catch (Throwable th) {
            return false;
        }
    }

    public static boolean m6791a(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (Throwable th) {
            return false;
        }
    }
}
