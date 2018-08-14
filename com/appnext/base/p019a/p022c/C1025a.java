package com.appnext.base.p019a.p022c;

import android.content.ContentValues;
import android.database.Cursor;
import com.appnext.base.p019a.p021b.C1018d;
import com.appnext.base.p019a.p021b.C1019a;
import com.appnext.base.p019a.p022c.C1024e.C1029a;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

public class C1025a extends C1024e<C1019a> {
    private static final String COLUMN_PACKAGE_NAME = "package_name";
    public static final String gs = "category_table";
    private static final String gt = "category";
    private String[] gu = new String[]{COLUMN_PACKAGE_NAME, gt};

    protected /* synthetic */ C1018d mo2020b(Cursor cursor) {
        return m2088a(cursor);
    }

    public static String bl() {
        return "create table category_table ( package_name text, category integer)";
    }

    public long m2086a(C1019a c1019a) {
        return super.m2074a(gs, m2083b(c1019a));
    }

    public long m2087a(JSONArray jSONArray) {
        return super.m2075a(gs, jSONArray);
    }

    public void delete() {
        super.delete(gs);
    }

    public void m2084Y(String str) {
        List arrayList = new ArrayList();
        arrayList.add(C1029a.Equals);
        super.m2072a(gs, new String[]{COLUMN_PACKAGE_NAME}, new String[]{str}, arrayList);
    }

    public List<C1019a> bm() {
        return super.ag(gs);
    }

    public List<C1019a> m2085Z(String str) {
        List arrayList = new ArrayList();
        arrayList.add(C1029a.Equals);
        return super.m2077a(gs, new String[]{COLUMN_PACKAGE_NAME}, new String[]{str}, null, arrayList);
    }

    private ContentValues m2083b(C1019a c1019a) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PACKAGE_NAME, c1019a.getPackageName());
        contentValues.put(gt, c1019a.aW());
        return contentValues;
    }

    protected String[] bn() {
        return this.gu;
    }

    protected C1019a m2088a(Cursor cursor) {
        return new C1019a(cursor.getString(cursor.getColumnIndex(COLUMN_PACKAGE_NAME)), Integer.valueOf(cursor.getInt(cursor.getColumnIndex(gt))));
    }
}
