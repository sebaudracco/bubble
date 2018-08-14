package com.stepleaderdigital.reveal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import com.stepleaderdigital.reveal.Reveal.RevealLogger;

@TargetApi(18)
public class BackgroundPowerSaver implements ActivityLifecycleCallbacks {
    private int activeActivityCount = 0;
    private BeaconManager beaconManager;

    public BackgroundPowerSaver(Context context) {
        if (VERSION.SDK_INT < 18) {
            RevealLogger.m12441w("BackgroundPowerSaver requires API 18 or higher.");
            return;
        }
        this.beaconManager = BeaconManager.getInstanceForApplication(context);
        ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(this);
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
        this.activeActivityCount++;
        if (this.activeActivityCount < 1) {
            RevealLogger.m12430d("reset active activity count on resume.  It was " + this.activeActivityCount);
            this.activeActivityCount = 1;
        }
        this.beaconManager.setBackgroundMode(false);
        RevealLogger.m12430d("activity resumed: " + activity + " active activities: " + this.activeActivityCount);
    }

    public void onActivityPaused(Activity activity) {
        this.activeActivityCount--;
        RevealLogger.m12430d("activity paused: " + activity + " active activities: " + this.activeActivityCount);
        if (this.activeActivityCount < 1) {
            RevealLogger.m12430d("setting background mode");
            this.beaconManager.setBackgroundMode(true);
        }
    }

    public void onActivityStopped(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
    }
}
