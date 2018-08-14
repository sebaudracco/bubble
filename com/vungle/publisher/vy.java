package com.vungle.publisher;

import com.vungle.publisher.log.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: vungle */
abstract class vy<T> extends vz<T> {
    protected abstract T mo6962c(JSONObject jSONObject) throws JSONException;

    vy() {
    }

    public final T m14018a(String str) throws JSONException {
        return str == null ? null : mo6962c(new JSONObject(str));
    }

    protected final T[] m14020a(JSONArray jSONArray) throws JSONException {
        T[] tArr = null;
        if (jSONArray != null) {
            int length = jSONArray.length();
            tArr = m14017b(length);
            for (int i = 0; i < length; i++) {
                tArr[i] = mo6962c(jSONArray.optJSONObject(i));
            }
        }
        return tArr;
    }

    protected final void m14019a(JSONObject jSONObject, String str, Object obj) {
        if (obj == null) {
            Object opt = jSONObject.opt(str);
            if (opt == null) {
                Logger.m13635d(Logger.PROTOCOL_TAG, "null " + str + " is required input");
            } else {
                Logger.m13635d(Logger.PROTOCOL_TAG, "invalid " + str + ": " + opt);
            }
        } else if ((obj instanceof String) && ((String) obj).length() == 0) {
            Logger.m13635d(Logger.PROTOCOL_TAG, "empty " + str + " is required input");
        } else if ((obj instanceof JSONArray) && ((JSONArray) obj).length() == 0) {
            Logger.m13635d(Logger.PROTOCOL_TAG, "empty array " + str + " is required input");
        }
    }
}
