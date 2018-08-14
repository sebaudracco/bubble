package com.appnext.base.p019a.p022c;

import android.database.Cursor;
import android.text.TextUtils;
import com.appnext.base.p019a.p021b.C1018d;
import com.appnext.base.p019a.p021b.C1021c;
import com.appnext.base.p019a.p022c.C1024e.C1029a;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class C1028c extends C1024e<C1021c> {
    private static final String COLUMN_STATUS = "status";
    private static final String gA = "service_key";
    private static final String gB = "exact";
    private static final String gC = "data";
    public static final String gs = "config_table";
    public static final String gv = "key";
    private static final String gw = "sample";
    private static final String gx = "sample_type";
    private static final String gy = "cycle";
    private static final String gz = "cycle_type";
    private String[] gu = new String[]{gv, "status", gw, gx, gy, gz, gB, "service_key", "data"};

    protected /* synthetic */ C1018d mo2020b(Cursor cursor) {
        return m2103c(cursor);
    }

    public static String bl() {
        return "create table config_table ( key text primary key, status text not null default 'off', sample text not null default '1', sample_type text not null default '',cycle text not null default '1', cycle_type text not null default 'once', exact text not null default 'false', service_key text not null default '', data text not null default '')";
    }

    public long m2101b(JSONArray jSONArray) {
        return super.m2075a(gs, jSONArray);
    }

    public long m2100a(JSONObject jSONObject) {
        return super.m2076a(gs, jSONObject);
    }

    public void delete() {
        super.delete(gs);
    }

    public void aa(String str) {
        if (!TextUtils.isEmpty(str)) {
            List arrayList = new ArrayList();
            arrayList.add(C1029a.Equals);
            super.m2072a(gs, new String[]{gv}, new String[]{str}, arrayList);
        }
    }

    public List<C1021c> bm() {
        return super.ag(gs);
    }

    public C1021c ab(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        List arrayList = new ArrayList();
        arrayList.add(C1029a.Equals);
        List a = super.m2077a(gs, new String[]{gv}, new String[]{str}, null, arrayList);
        if (a == null || a.size() <= 0) {
            return null;
        }
        return (C1021c) a.get(0);
    }

    protected String[] bn() {
        return this.gu;
    }

    protected C1021c m2103c(Cursor cursor) {
        String string = cursor.getString(cursor.getColumnIndex(gv));
        return new C1021c(cursor.getString(cursor.getColumnIndex("status")), cursor.getString(cursor.getColumnIndex(gw)), cursor.getString(cursor.getColumnIndex(gx)), cursor.getString(cursor.getColumnIndex(gy)), cursor.getString(cursor.getColumnIndex(gz)), Boolean.valueOf(cursor.getString(cursor.getColumnIndex(gB))).booleanValue(), string, cursor.getString(cursor.getColumnIndex("service_key")), cursor.getString(cursor.getColumnIndex("data")));
    }
}
