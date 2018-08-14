package com.mobfox.sdk.runnables;

import android.content.Context;
import android.location.Location;
import android.util.Log;
import com.jaredrummler.android.processes.ProcessManager;
import com.jaredrummler.android.processes.models.AndroidAppProcess;
import com.mobfox.sdk.dmp.Bundle;
import com.mobfox.sdk.services.UASLocationService;
import com.mobfox.sdk.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class MobFoxUASRunnable extends MobFoxRunnable {
    private static final String ACTION_CLOSE = "CLOSE";
    private static final String ACTION_OPEN = "OPEN";
    private static final String BUNDLE_FILE = "mobfox-uam-bundle";
    private static final String TAG = "MobFoxUASRunnable";
    static final String UAM_URL = "https://devices.starbolt.io/";
    private static List<AndroidAppProcess> mLast;
    private String mAndAdvId = "";
    private UASLocationService mManager;

    MobFoxUASRunnable(Context context) {
        super(context);
        mLast = new ArrayList();
        this.mManager = new UASLocationService(context);
    }

    protected boolean condition() {
        if (this.mAndAdvId == null || this.mAndAdvId.isEmpty()) {
            return false;
        }
        return true;
    }

    public void mobFoxRun() {
        update();
    }

    private void update() {
        try {
            String line;
            Location location;
            List<AndroidAppProcess> running = ProcessManager.getRunningForegroundApps(this.context);
            for (AndroidAppProcess lastApp : mLast) {
                if (!appFoundInList(lastApp, running)) {
                    line = this.mAndAdvId + Utils.FILE_SEPARATOR + System.currentTimeMillis() + Utils.FILE_SEPARATOR + lastApp.uid + Utils.FILE_SEPARATOR + lastApp.getPackageName();
                    location = this.mManager.getLast();
                    if (location != null) {
                        line = line + Utils.FILE_SEPARATOR + location.getLatitude() + Utils.FILE_SEPARATOR + location.getLongitude();
                    } else {
                        line = line + "\t\t";
                    }
                    encodeAndBundle(line, ACTION_CLOSE);
                }
            }
            for (AndroidAppProcess currentApp : running) {
                if (!appFoundInList(currentApp, mLast)) {
                    line = this.mAndAdvId + Utils.FILE_SEPARATOR + System.currentTimeMillis() + Utils.FILE_SEPARATOR + currentApp.uid + Utils.FILE_SEPARATOR + currentApp.getPackageName();
                    location = this.mManager.getLast();
                    if (location != null) {
                        line = line + Utils.FILE_SEPARATOR + location.getLatitude() + Utils.FILE_SEPARATOR + location.getLongitude();
                    } else {
                        line = line + "\t\t";
                    }
                    encodeAndBundle(line, ACTION_OPEN);
                }
            }
            mLast = running;
        } catch (Exception e) {
            Log.e(BUNDLE_FILE, "Some error in UAM: " + e.toString());
        }
    }

    private void encodeAndBundle(String line, String action) {
        Utils.write(this.context, BUNDLE_FILE, Utils.encodeXor(line + Utils.FILE_SEPARATOR + action, Bundle.KEY) + "\n", 32768);
    }

    private boolean appFoundInList(AndroidAppProcess app, List<AndroidAppProcess> list) {
        for (AndroidAppProcess listApp : list) {
            if (listApp.uid == app.uid) {
                return true;
            }
        }
        return false;
    }

    public Object getBundle() {
        String content = Utils.read(this.context, BUNDLE_FILE);
        if (content == null || content.isEmpty()) {
            return null;
        }
        JSONObject updateObj = new JSONObject();
        Object array = new JSONArray();
        try {
            updateObj.put("body", content);
            array.put(updateObj);
            return array;
        } catch (JSONException e) {
            Log.e(TAG, "uam json exception", e);
            return null;
        }
    }

    void bundleSentComplete() {
        Log.e("bundleSentComplete", "bundle file send - empty file");
        Utils.write(this.context, BUNDLE_FILE, "");
    }

    void setAndAdvId(String o_andadvid) {
        this.mAndAdvId = o_andadvid;
    }
}
