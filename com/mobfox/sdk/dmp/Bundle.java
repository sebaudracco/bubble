package com.mobfox.sdk.dmp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.mobfox.sdk.constants.Constants;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Bundle {
    public static String KEY = "IvoryLatta12";
    JSONObject bundleObj = new JSONObject();

    public Bundle(String id) {
        try {
            this.bundleObj.put("id", id);
        } catch (JSONException e) {
        }
    }

    protected static String prepare(String input) {
        char[] key = new char[]{'I', 'v', 'o', 'r', 'y', 'L', 'a', 't', 't', 'a', '1', '2'};
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            output.append((char) (input.charAt(i) ^ key[i % key.length]));
        }
        return output.toString();
    }

    protected static String parse(int[] arr) {
        char[] key = new char[]{'I', 'L', '1', '2'};
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            output.append((char) (((char) arr[i]) ^ key[i % key.length]));
        }
        return output.toString();
    }

    public void addData(Context c, List<int[]> data, String IPAddress, String ua) {
        new Intent("android.intent.action.MAIN", null).addCategory("android.intent.category.LAUNCHER");
        try {
            Object ret1 = Context.class.getMethod(parse((int[]) data.get(0)), new Class[0]).invoke(c, new Object[0]);
            List<Object> list = (List) ret1.getClass().getMethod(parse((int[]) data.get(1)), new Class[]{Intent.class, Integer.TYPE}).invoke(ret1, new Object[]{mainIntent, Integer.valueOf(0)});
            JSONArray arr = new JSONArray();
            for (Object inf : list) {
                Object fv = inf.getClass().getField(parse((int[]) data.get(2))).get(inf);
                arr.put(fv.getClass().getField(parse((int[]) data.get(3))).get(fv));
            }
            this.bundleObj.put("arr", arr);
            this.bundleObj.put("IPAddress", IPAddress);
            this.bundleObj.put("ua", ua);
            this.bundleObj.put("core", Constants.MOBFOX_SDK_VERSION);
        } catch (Exception e) {
            if (e.getMessage() != null) {
                Log.d(Constants.MOBFOX_DMP, "dmp.bundle " + e.getMessage());
            } else {
                Log.d(Constants.MOBFOX_DMP, "dmp.bundle exception");
            }
        } catch (Throwable th) {
            Log.d(Constants.MOBFOX_DMP, "dmp.bundle throwable");
        }
    }

    public String toString() {
        return prepare(this.bundleObj.toString());
    }

    public JSONObject getBundleObj() {
        return this.bundleObj;
    }
}
