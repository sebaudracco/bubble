package com.integralads.avid.library.adcolony.utils;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AvidJSONUtil {
    public static final String KEY_CHILD_VIEWS = "childViews";
    public static final String KEY_HEIGHT = "height";
    public static final String KEY_ID = "id";
    public static final String KEY_IS_FRIENDLY_OBSTRUCTION_FOR = "isFriendlyObstructionFor";
    public static final String KEY_ROOT_VIEW = "rootView";
    public static final String KEY_TIMESTAMP = "timestamp";
    public static final String KEY_WIDTH = "width";
    public static final String KEY_X = "x";
    public static final String KEY_Y = "y";
    static float f8397a = Resources.getSystem().getDisplayMetrics().density;
    private static String[] f8398b = new String[]{"x", "y", "width", "height"};

    public static void init(Context context) {
        if (context != null) {
            f8397a = context.getResources().getDisplayMetrics().density;
        }
    }

    public static JSONObject getEmptyTreeJSONObject() {
        return getTreeJSONObject(getViewState(0, 0, 0, 0), AvidTimestamp.getCurrentTime());
    }

    public static JSONObject getTreeJSONObject(JSONObject rootJSONObject, double timestamp) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("timestamp", timestamp);
            jSONObject.put("rootView", rootJSONObject);
        } catch (Exception e) {
            AvidLogs.m11162e("Error with creating treeJSONObject", e);
        }
        return jSONObject;
    }

    public static JSONObject getViewState(int x, int y, int width, int height) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("x", (double) m11154a(x));
            jSONObject.put("y", (double) m11154a(y));
            jSONObject.put("width", (double) m11154a(width));
            jSONObject.put("height", (double) m11154a(height));
        } catch (Exception e) {
            AvidLogs.m11162e("Error with creating viewStateObject", e);
        }
        return jSONObject;
    }

    static float m11154a(int i) {
        return ((float) i) / f8397a;
    }

    public static void addAvidId(JSONObject state, String avidId) {
        try {
            state.put("id", avidId);
        } catch (Exception e) {
            AvidLogs.m11162e("Error with setting avid id", e);
        }
    }

    public static void addFriendlyObstruction(JSONObject state, List<String> sessionIds) {
        JSONArray jSONArray = new JSONArray();
        for (String put : sessionIds) {
            jSONArray.put(put);
        }
        try {
            state.put("isFriendlyObstructionFor", jSONArray);
        } catch (Exception e) {
            AvidLogs.m11162e("Error with setting friendly obstruction", e);
        }
    }

    public static void addChildState(JSONObject state, JSONObject childState) {
        try {
            JSONArray optJSONArray = state.optJSONArray("childViews");
            if (optJSONArray == null) {
                optJSONArray = new JSONArray();
                state.put("childViews", optJSONArray);
            }
            optJSONArray.put(childState);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void fixStateFrame(JSONObject state) {
        int i = 0;
        JSONArray optJSONArray = state.optJSONArray("childViews");
        if (optJSONArray != null) {
            int length = optJSONArray.length();
            int i2 = 0;
            for (int i3 = 0; i3 < length; i3++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i3);
                if (optJSONObject != null) {
                    int optInt = optJSONObject.optInt("x");
                    int optInt2 = optJSONObject.optInt("y");
                    int optInt3 = optJSONObject.optInt("width");
                    int optInt4 = optJSONObject.optInt("height");
                    i2 = Math.max(i2, optInt + optInt3);
                    i = Math.max(i, optInt4 + optInt2);
                }
            }
            try {
                state.put("width", i2);
                state.put("height", i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean equalStates(@NonNull JSONObject state1, @Nullable JSONObject state2) {
        if (state2 != null && m11156a(state1, state2) && m11157b(state1, state2) && m11158c(state1, state2) && m11159d(state1, state2)) {
            return true;
        }
        return false;
    }

    private static boolean m11156a(JSONObject jSONObject, JSONObject jSONObject2) {
        for (String str : f8398b) {
            if (jSONObject.optDouble(str) != jSONObject2.optDouble(str)) {
                return false;
            }
        }
        return true;
    }

    private static boolean m11157b(JSONObject jSONObject, JSONObject jSONObject2) {
        return jSONObject.optString("id", "").equals(jSONObject2.optString("id", ""));
    }

    private static boolean m11158c(JSONObject jSONObject, JSONObject jSONObject2) {
        JSONArray optJSONArray = jSONObject.optJSONArray("isFriendlyObstructionFor");
        JSONArray optJSONArray2 = jSONObject2.optJSONArray("isFriendlyObstructionFor");
        if (!m11155a(optJSONArray, optJSONArray2)) {
            return false;
        }
        if (optJSONArray == null) {
            return true;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            if (!optJSONArray.optString(i, "").equals(optJSONArray2.optString(i, ""))) {
                return false;
            }
        }
        return true;
    }

    private static boolean m11159d(JSONObject jSONObject, JSONObject jSONObject2) {
        JSONArray optJSONArray = jSONObject.optJSONArray("childViews");
        JSONArray optJSONArray2 = jSONObject2.optJSONArray("childViews");
        if (!m11155a(optJSONArray, optJSONArray2)) {
            return false;
        }
        if (optJSONArray == null) {
            return true;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            if (!equalStates(optJSONArray.optJSONObject(i), optJSONArray2.optJSONObject(i))) {
                return false;
            }
        }
        return true;
    }

    private static boolean m11155a(JSONArray jSONArray, JSONArray jSONArray2) {
        if (jSONArray == null && jSONArray2 == null) {
            return true;
        }
        if ((jSONArray == null && jSONArray2 != null) || (jSONArray != null && jSONArray2 == null)) {
            return false;
        }
        if (jSONArray.length() != jSONArray2.length()) {
            return false;
        }
        return true;
    }
}
