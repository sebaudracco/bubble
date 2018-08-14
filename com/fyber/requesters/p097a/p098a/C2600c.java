package com.fyber.requesters.p097a.p098a;

import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: CacheConfig */
public abstract class C2600c {
    private int f6488a;
    private int[] f6489b;

    /* compiled from: CacheConfig */
    static abstract class C2599a {
        protected int f6486a = Integer.MIN_VALUE;
        protected int[] f6487b = new int[0];

        protected C2599a() {
        }

        protected static C2599a m8334a(C2599a c2599a, JSONObject jSONObject, String str) {
            JSONObject optJSONObject = jSONObject.optJSONObject(str);
            if (optJSONObject != null) {
                int[] iArr;
                c2599a.f6486a = optJSONObject.optInt("fill_ttl", Integer.MIN_VALUE);
                JSONArray optJSONArray = optJSONObject.optJSONArray("no_fill_ttl");
                int length = optJSONArray != null ? optJSONArray.length() : 0;
                int[] iArr2 = new int[length];
                for (int i = 0; i < length; i++) {
                    int optInt = optJSONArray.optInt(i, Integer.MIN_VALUE);
                    if (optInt < 0) {
                        iArr = new int[0];
                        break;
                    }
                    iArr2[i] = optInt;
                }
                iArr = iArr2;
                c2599a.f6487b = iArr;
            }
            return c2599a;
        }

        protected static String[] m8335a(JSONObject jSONObject, String str) {
            JSONArray optJSONArray = jSONObject.optJSONArray(str);
            int length = optJSONArray != null ? optJSONArray.length() : 0;
            String[] strArr = new String[length];
            for (int i = 0; i < length; i++) {
                String optString = optJSONArray.optString(i);
                if (optString.isEmpty()) {
                    return new String[0];
                }
                strArr[i] = optString;
            }
            return strArr;
        }
    }

    protected C2600c(C2599a c2599a) {
        this.f6488a = c2599a.f6486a;
        this.f6489b = c2599a.f6487b;
    }

    public final int m8336a() {
        return this.f6488a;
    }

    public final int[] m8337b() {
        return this.f6489b;
    }
}
