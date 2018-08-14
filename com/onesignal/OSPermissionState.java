package com.onesignal;

import android.content.SharedPreferences.Editor;
import org.json.JSONObject;

public class OSPermissionState implements Cloneable {
    private boolean enabled;
    OSObservable<Object, OSPermissionState> observable = new OSObservable("changed", false);

    OSPermissionState(boolean asFrom) {
        if (asFrom) {
            this.enabled = OneSignal.getGcmPreferences(OneSignal.appContext).getBoolean("ONESIGNAL_ACCEPTED_NOTIFICATION_LAST", false);
        } else {
            refreshAsTo();
        }
    }

    void refreshAsTo() {
        setEnabled(OSUtils.areNotificationsEnabled(OneSignal.appContext));
    }

    public boolean getEnabled() {
        return this.enabled;
    }

    private void setEnabled(boolean set) {
        boolean changed = this.enabled != set;
        this.enabled = set;
        if (changed) {
            this.observable.notifyChange(this);
        }
    }

    void persistAsFrom() {
        Editor editor = OneSignal.getGcmPreferences(OneSignal.appContext).edit();
        editor.putBoolean("ONESIGNAL_ACCEPTED_NOTIFICATION_LAST", this.enabled);
        editor.commit();
    }

    boolean compare(OSPermissionState from) {
        return this.enabled != from.enabled;
    }

    protected Object clone() {
        try {
            return super.clone();
        } catch (Throwable th) {
            return null;
        }
    }

    public JSONObject toJSONObject() {
        JSONObject mainObj = new JSONObject();
        try {
            mainObj.put("enabled", this.enabled);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return mainObj;
    }

    public String toString() {
        return toJSONObject().toString();
    }
}
