package com.yandex.metrica.impl.ob;

import java.util.ArrayList;
import java.util.List;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ds {
    private final long f12260a;
    private final String f12261b;
    private final List<Integer> f12262c;

    public ds(long j, String str, List<Integer> list) {
        this.f12260a = j;
        this.f12261b = str;
        this.f12262c = list;
    }

    public ds(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        this.f12260a = jSONObject.getLong("seconds_to_live");
        this.f12261b = jSONObject.getString(SchemaSymbols.ATTVAL_TOKEN);
        this.f12262c = m15786a(jSONObject.getJSONArray("ports"));
    }

    private static ArrayList<Integer> m15786a(JSONArray jSONArray) throws JSONException {
        ArrayList<Integer> arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(Integer.valueOf(jSONArray.getInt(i)));
        }
        return arrayList;
    }

    public long m15787a() {
        return this.f12260a;
    }

    public String m15788b() {
        return this.f12261b;
    }

    public List<Integer> m15789c() {
        return this.f12262c;
    }

    public String m15790d() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("seconds_to_live", this.f12260a);
        jSONObject.put(SchemaSymbols.ATTVAL_TOKEN, this.f12261b);
        jSONObject.put("ports", new JSONArray(this.f12262c));
        return jSONObject.toString();
    }
}
