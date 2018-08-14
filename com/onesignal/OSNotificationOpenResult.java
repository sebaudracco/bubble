package com.onesignal;

import com.appnext.base.p023b.C1042c;
import org.json.JSONException;
import org.json.JSONObject;

public class OSNotificationOpenResult {
    public OSNotificationAction action;
    public OSNotification notification;

    @Deprecated
    public String stringify() {
        JSONObject mainObj = new JSONObject();
        try {
            JSONObject ac = new JSONObject();
            ac.put("actionID", this.action.actionID);
            ac.put("type", this.action.type.ordinal());
            mainObj.put(C1042c.jL, ac);
            mainObj.put("notification", new JSONObject(this.notification.stringify()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mainObj.toString();
    }

    public JSONObject toJSONObject() {
        JSONObject mainObj = new JSONObject();
        try {
            JSONObject jsonObjAction = new JSONObject();
            jsonObjAction.put("actionID", this.action.actionID);
            jsonObjAction.put("type", this.action.type.ordinal());
            mainObj.put(C1042c.jL, jsonObjAction);
            mainObj.put("notification", this.notification.toJSONObject());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mainObj;
    }
}
