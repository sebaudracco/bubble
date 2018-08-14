package com.yandex.metrica.impl.utils;

import android.text.TextUtils;
import com.yandex.metrica.impl.bk;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class C4525g {

    public static class C4524a extends JSONObject {
        public C4524a(String str) throws JSONException {
            super(str);
        }

        public String m16267a(String str) {
            try {
                return super.getString(str);
            } catch (Exception e) {
                return "";
            }
        }

        public Object m16266a(String str, Object obj) {
            try {
                obj = super.get(str);
            } catch (Exception e) {
            }
            return obj;
        }

        public boolean m16268b(String str) {
            try {
                return NULL != super.get(str);
            } catch (Exception e) {
                return false;
            }
        }
    }

    public static Object m16269a(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            if (obj.getClass().isArray()) {
                int length = Array.getLength(obj);
                Collection arrayList = new ArrayList(length);
                for (int i = 0; i < length; i++) {
                    arrayList.add(C4525g.m16269a(Array.get(obj, i)));
                }
                return new JSONArray(arrayList);
            } else if (obj instanceof Collection) {
                Collection<Object> collection = (Collection) obj;
                Collection arrayList2 = new ArrayList(collection.size());
                for (Object a : collection) {
                    arrayList2.add(C4525g.m16269a(a));
                }
                return new JSONArray(arrayList2);
            } else if (!(obj instanceof Map)) {
                return obj;
            } else {
                Map map = (Map) obj;
                Map linkedHashMap = new LinkedHashMap();
                for (Entry entry : map.entrySet()) {
                    String obj2 = entry.getKey().toString();
                    if (obj2 != null) {
                        linkedHashMap.put(obj2, C4525g.m16269a(entry.getValue()));
                    }
                }
                return new JSONObject(linkedHashMap);
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static String m16271a(Map map) {
        if (bk.m14988a(map)) {
            return null;
        }
        if (bk.m14985a(19)) {
            return new JSONObject(map).toString();
        }
        return C4525g.m16269a((Object) map).toString();
    }

    public static String m16270a(List<String> list) {
        if (bk.m14987a((Collection) list)) {
            return null;
        }
        if (bk.m14985a(19)) {
            return new JSONArray(list).toString();
        }
        return C4525g.m16269a((Object) list).toString();
    }

    public static HashMap<String, String> m16272a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return C4525g.m16273a(new JSONObject(str));
            } catch (JSONException e) {
            }
        }
        return null;
    }

    public static List<String> m16274b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            List<String> arrayList = new ArrayList(jSONArray.length());
            int i = 0;
            while (i < jSONArray.length()) {
                try {
                    arrayList.add(jSONArray.getString(i));
                    i++;
                } catch (JSONException e) {
                    return arrayList;
                }
            }
            return arrayList;
        } catch (JSONException e2) {
            return null;
        }
    }

    public static HashMap<String, String> m16273a(JSONObject jSONObject) {
        if (JSONObject.NULL.equals(jSONObject)) {
            return null;
        }
        HashMap<String, String> hashMap = new HashMap();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            String optString = jSONObject.optString(str);
            if (optString != null) {
                hashMap.put(str, optString);
            }
        }
        return hashMap;
    }
}
