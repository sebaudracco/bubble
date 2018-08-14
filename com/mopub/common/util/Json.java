package com.mopub.common.util;

import android.text.TextUtils;
import com.appnext.base.p019a.p022c.C1028c;
import com.mopub.common.logging.MoPubLog;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Json {
    public static Map<String, String> jsonStringToMap(String jsonParams) throws JSONException {
        Map<String, String> jsonMap = new HashMap();
        if (!TextUtils.isEmpty(jsonParams)) {
            JSONObject jsonObject = (JSONObject) new JSONTokener(jsonParams).nextValue();
            Iterator<?> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                jsonMap.put(key, jsonObject.getString(key));
            }
        }
        return jsonMap;
    }

    public static String mapToJsonString(Map<String, String> map) {
        if (map == null) {
            return "{}";
        }
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        boolean first = true;
        for (Entry<String, String> entry : map.entrySet()) {
            if (!first) {
                builder.append(",");
            }
            builder.append("\"");
            builder.append((String) entry.getKey());
            builder.append("\":\"");
            builder.append((String) entry.getValue());
            builder.append("\"");
            first = false;
        }
        builder.append("}");
        return builder.toString();
    }

    public static String[] jsonArrayToStringArray(String jsonString) {
        try {
            JSONArray jsonArray = ((JSONObject) new JSONTokener("{key:" + jsonString + "}").nextValue()).getJSONArray(C1028c.gv);
            String[] strArr = new String[jsonArray.length()];
            for (int i = 0; i < strArr.length; i++) {
                strArr[i] = jsonArray.getString(i);
            }
            return strArr;
        } catch (JSONException e) {
            return new String[0];
        }
    }

    public static <T> T getJsonValue(JSONObject jsonObject, String key, Class<T> valueClass) {
        if (jsonObject == null || key == null || valueClass == null) {
            throw new IllegalArgumentException("Cannot pass any null argument to getJsonValue");
        }
        Object object = jsonObject.opt(key);
        if (object == null) {
            MoPubLog.m12069w("Tried to get Json value with key: " + key + ", but it was null");
            return null;
        } else if (valueClass.isInstance(object)) {
            return valueClass.cast(object);
        } else {
            MoPubLog.m12069w("Tried to get Json value with key: " + key + ", of type: " + valueClass.toString() + ", its type did not match");
            return null;
        }
    }
}
