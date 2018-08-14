package com.integralads.avid.library.mopub.utils;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AvidJSONUtil {
    private static String[] KEYS = new String[]{"x", "y", "width", "height"};
    public static final String KEY_CHILD_VIEWS = "childViews";
    public static final String KEY_HEIGHT = "height";
    public static final String KEY_ID = "id";
    public static final String KEY_IS_FRIENDLY_OBSTRUCTION_FOR = "isFriendlyObstructionFor";
    public static final String KEY_ROOT_VIEW = "rootView";
    public static final String KEY_TIMESTAMP = "timestamp";
    public static final String KEY_WIDTH = "width";
    public static final String KEY_X = "x";
    public static final String KEY_Y = "y";
    static float density = Resources.getSystem().getDisplayMetrics().density;

    public static void init(Context context) {
        if (context != null) {
            density = context.getResources().getDisplayMetrics().density;
        }
    }

    public static JSONObject getEmptyTreeJSONObject() {
        return getTreeJSONObject(getViewState(0, 0, 0, 0), AvidTimestamp.getCurrentTime());
    }

    public static JSONObject getTreeJSONObject(JSONObject rootJSONObject, double timestamp) {
        JSONObject treeJSONObject = new JSONObject();
        try {
            treeJSONObject.put("timestamp", timestamp);
            treeJSONObject.put("rootView", rootJSONObject);
        } catch (JSONException e) {
            AvidLogs.m11438e("Error with creating treeJSONObject", e);
        }
        return treeJSONObject;
    }

    public static JSONObject getViewState(int x, int y, int width, int height) {
        JSONObject viewStateObject = new JSONObject();
        try {
            viewStateObject.put("x", (double) pxToDp(x));
            viewStateObject.put("y", (double) pxToDp(y));
            viewStateObject.put("width", (double) pxToDp(width));
            viewStateObject.put("height", (double) pxToDp(height));
        } catch (JSONException e) {
            AvidLogs.m11438e("Error with creating viewStateObject", e);
        }
        return viewStateObject;
    }

    static float pxToDp(int px) {
        return ((float) px) / density;
    }

    public static void addAvidId(JSONObject state, String avidId) {
        try {
            state.put("id", avidId);
        } catch (JSONException e) {
            AvidLogs.m11438e("Error with setting avid id", e);
        }
    }

    public static void addFriendlyObstruction(JSONObject state, List<String> sessionIds) {
        JSONArray array = new JSONArray();
        for (String sessionId : sessionIds) {
            array.put(sessionId);
        }
        try {
            state.put("isFriendlyObstructionFor", array);
        } catch (JSONException e) {
            AvidLogs.m11438e("Error with setting friendly obstruction", e);
        }
    }

    public static void addChildState(JSONObject state, JSONObject childState) {
        try {
            JSONArray children = state.optJSONArray("childViews");
            if (children == null) {
                children = new JSONArray();
                state.put("childViews", children);
            }
            children.put(childState);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void fixStateFrame(JSONObject state) {
        JSONArray children = state.optJSONArray("childViews");
        if (children != null) {
            int width = 0;
            int height = 0;
            int count = children.length();
            for (int i = 0; i < count; i++) {
                JSONObject child = children.optJSONObject(i);
                if (child != null) {
                    int x = child.optInt("x");
                    int y = child.optInt("y");
                    int w = child.optInt("width");
                    int h = child.optInt("height");
                    width = Math.max(width, x + w);
                    height = Math.max(height, y + h);
                }
            }
            try {
                state.put("width", width);
                state.put("height", height);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean equalStates(@NonNull JSONObject state1, @Nullable JSONObject state2) {
        if (state2 != null && compareRequiredValues(state1, state2) && compareSessionId(state1, state2) && compareFriendlySessionIds(state1, state2) && compareChildren(state1, state2)) {
            return true;
        }
        return false;
    }

    private static boolean compareRequiredValues(JSONObject state1, JSONObject state2) {
        for (String key : KEYS) {
            if (state1.optDouble(key) != state2.optDouble(key)) {
                return false;
            }
        }
        return true;
    }

    private static boolean compareSessionId(JSONObject state1, JSONObject state2) {
        return state1.optString("id", "").equals(state2.optString("id", ""));
    }

    private static boolean compareFriendlySessionIds(JSONObject state1, JSONObject state2) {
        JSONArray friendlySessionIds1 = state1.optJSONArray("isFriendlyObstructionFor");
        JSONArray friendlySessionIds2 = state2.optJSONArray("isFriendlyObstructionFor");
        if (!compareJSONArrays(friendlySessionIds1, friendlySessionIds2)) {
            return false;
        }
        if (friendlySessionIds1 == null) {
            return true;
        }
        for (int i = 0; i < friendlySessionIds1.length(); i++) {
            if (!friendlySessionIds1.optString(i, "").equals(friendlySessionIds2.optString(i, ""))) {
                return false;
            }
        }
        return true;
    }

    private static boolean compareChildren(JSONObject state1, JSONObject state2) {
        JSONArray children1 = state1.optJSONArray("childViews");
        JSONArray children2 = state2.optJSONArray("childViews");
        if (!compareJSONArrays(children1, children2)) {
            return false;
        }
        if (children1 == null) {
            return true;
        }
        for (int i = 0; i < children1.length(); i++) {
            if (!equalStates(children1.optJSONObject(i), children2.optJSONObject(i))) {
                return false;
            }
        }
        return true;
    }

    private static boolean compareJSONArrays(JSONArray array1, JSONArray array2) {
        if (array1 == null && array2 == null) {
            return true;
        }
        if ((array1 == null && array2 != null) || (array1 != null && array2 == null)) {
            return false;
        }
        if (array1.length() != array2.length()) {
            return false;
        }
        return true;
    }
}
