package com.appnext.base.p019a.p022c;

import android.content.ContentValues;
import android.database.Cursor;
import com.appnext.base.p019a.p021b.C1018d;
import com.appnext.base.p019a.p021b.C1020b;
import com.appnext.base.p019a.p022c.C1024e.C1029a;
import com.appnext.base.p023b.C1059m;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import org.json.JSONArray;

public class C1026d extends C1024e<C1020b> {
    public static final String COLUMN_TYPE = "type";
    public static final String gD = "primary_key";
    public static final String gE = "collected_data";
    public static final String gF = "collected_data_date";
    public static final String gG = "collected_data_type";
    private String gH;
    private String[] gu = new String[]{gD, "type", gE, gF, gG};

    protected /* synthetic */ C1018d mo2020b(Cursor cursor) {
        return m2099d(cursor);
    }

    public C1026d(String str) {
        this.gH = str;
    }

    public long m2093a(C1020b c1020b) {
        return super.m2074a(this.gH, m2091c(c1020b));
    }

    public long mo2022a(JSONArray jSONArray) {
        return super.m2080b(this.gH, jSONArray);
    }

    public long m2096b(C1020b c1020b) {
        ac(c1020b.getType());
        return super.m2074a(this.gH, m2091c(c1020b));
    }

    public void ac(String str) {
        List arrayList = new ArrayList();
        arrayList.add(C1029a.Equals);
        super.m2072a(this.gH, new String[]{"type"}, new String[]{str}, arrayList);
    }

    public int m2095b(String str, long j) {
        List arrayList = new ArrayList();
        arrayList.add(C1029a.Equals);
        arrayList.add(C1029a.LessThan);
        return super.m2072a(this.gH, new String[]{"type", gF}, new String[]{str, String.valueOf(j)}, arrayList);
    }

    public void delete() {
        super.delete(this.gH);
    }

    public List<C1020b> bm() {
        return super.ag(this.gH);
    }

    public List<C1020b> ad(String str) {
        List arrayList = new ArrayList();
        arrayList.add(C1029a.Equals);
        return super.m2077a(this.gH, new String[]{"type"}, new String[]{str}, null, arrayList);
    }

    public List<C1020b> ae(String str) {
        List arrayList = new ArrayList();
        arrayList.add(C1029a.Equals);
        return super.m2077a(this.gH, new String[]{gD}, new String[]{str}, null, arrayList);
    }

    public List<C1020b> m2098c(String str, long j) {
        List arrayList = new ArrayList();
        arrayList.add(C1029a.Equals);
        arrayList.add(C1029a.GreaterThan);
        return super.m2077a(this.gH, new String[]{"type", gF}, new String[]{str, String.valueOf(j)}, null, arrayList);
    }

    public List<C1020b> af(String str) {
        List arrayList = new ArrayList();
        arrayList.add(C1029a.Equals);
        List<C1020b> a = super.m2077a(this.gH, new String[]{"type"}, new String[]{str}, ah(gF), arrayList);
        if (a == null || a.isEmpty()) {
            return null;
        }
        ListIterator listIterator = a.listIterator();
        listIterator.next();
        while (listIterator.hasNext()) {
            listIterator.next();
            listIterator.remove();
        }
        return a;
    }

    private ContentValues m2091c(C1020b c1020b) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(gD, c1020b.aX());
        contentValues.put("type", c1020b.getType());
        contentValues.put(gE, c1020b.aY());
        contentValues.put(gG, c1020b.getDataType());
        return contentValues;
    }

    protected String[] bn() {
        return this.gu;
    }

    protected C1020b m2099d(Cursor cursor) {
        return new C1020b(cursor.getString(cursor.getColumnIndex(gD)), cursor.getString(cursor.getColumnIndex("type")), cursor.getString(cursor.getColumnIndex(gE)), C1059m.m2189d(cursor.getLong(cursor.getColumnIndex(gF)) * 1000), cursor.getString(cursor.getColumnIndex(gG)));
    }

    protected static String m2090b(String str, boolean z) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("create table ");
        stringBuilder.append(str);
        stringBuilder.append(" ( primary_key text not null, type text not null " + C1026d.m2092c(z) + ", " + gE + " text not null, " + gF + " text default (strftime('%s','now')), " + gG + " text)");
        return stringBuilder.toString();
    }

    private static String m2092c(boolean z) {
        if (z) {
            return "primary key";
        }
        return "";
    }
}
