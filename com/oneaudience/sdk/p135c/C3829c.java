package com.oneaudience.sdk.p135c;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class C3829c {
    public static Object m12240a(Object obj) {
        return obj == JSONObject.NULL ? null : obj instanceof JSONObject ? C3829c.m12243a((JSONObject) obj) : obj instanceof JSONArray ? C3829c.m12242a((JSONArray) obj) : obj;
    }

    public static Object m12241a(String str) {
        return C3829c.m12240a(new JSONTokener(str).nextValue());
    }

    public static List m12242a(JSONArray jSONArray) {
        List arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(C3829c.m12240a(jSONArray.get(i)));
        }
        return arrayList;
    }

    public static Map<String, Object> m12243a(JSONObject jSONObject) {
        Map<String, Object> hashMap = new HashMap();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            hashMap.put(str, C3829c.m12240a(jSONObject.get(str)));
        }
        return hashMap;
    }
}
