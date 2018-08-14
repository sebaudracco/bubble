package com.appnext.base.p019a.p022c;

import android.content.ContentValues;
import android.database.Cursor;
import com.appnext.base.p019a.p021b.C1018d;
import com.appnext.base.p019a.p021b.C1022e;
import com.appnext.base.p019a.p022c.C1024e.C1029a;
import com.appnext.base.p023b.C1059m;
import java.util.ArrayList;
import java.util.List;

public class C1032g extends C1024e<C1022e> {
    private static final String gK = "latitude";
    private static final String gL = "longitude";
    private static final String gM = "additional_data";
    private static final String gN = "date";
    private static final String gO = "times_type";
    public static final String gs = "times_location_table";
    private String[] gu = new String[]{"latitude", "longitude", gM, "date"};

    private enum C1031a {
        Morning("morning"),
        AfterNoon("afternoon"),
        Night("night");
        
        private String mValue;

        private C1031a(String str) {
            this.mValue = str;
        }

        public String getValue() {
            return this.mValue;
        }
    }

    protected /* synthetic */ C1018d mo2020b(Cursor cursor) {
        return m2111f(cursor);
    }

    public static String bl() {
        return "create table times_location_table ( latitude real, longitude real, additional_data text, date integer, times_type text)";
    }

    public long m2106a(C1022e c1022e) {
        m2105a(C1031a.Morning);
        return super.m2074a(gs, m2107a(c1022e, C1031a.Morning));
    }

    public long m2108b(C1022e c1022e) {
        m2105a(C1031a.AfterNoon);
        return super.m2074a(gs, m2107a(c1022e, C1031a.AfterNoon));
    }

    public long m2110c(C1022e c1022e) {
        m2105a(C1031a.Night);
        return super.m2074a(gs, m2107a(c1022e, C1031a.Night));
    }

    public void delete() {
        super.delete(gs);
    }

    public List<C1022e> bp() {
        List arrayList = new ArrayList();
        arrayList.add(C1029a.Equals);
        return super.m2077a(gs, new String[]{gO}, new String[]{String.valueOf(C1031a.Morning.getValue())}, ah("date"), arrayList);
    }

    public List<C1022e> bq() {
        List arrayList = new ArrayList();
        arrayList.add(C1029a.Equals);
        return super.m2077a(gs, new String[]{gO}, new String[]{String.valueOf(C1031a.AfterNoon.getValue())}, ah("date"), arrayList);
    }

    public List<C1022e> br() {
        List arrayList = new ArrayList();
        arrayList.add(C1029a.Equals);
        return super.m2077a(gs, new String[]{gO}, new String[]{String.valueOf(C1031a.Night.getValue())}, ah("date"), arrayList);
    }

    protected ContentValues m2107a(C1022e c1022e, C1031a c1031a) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("latitude", c1022e.bi());
        contentValues.put("longitude", c1022e.bj());
        contentValues.put(gM, c1022e.bk());
        contentValues.put("date", Long.valueOf(c1022e.getDate().getTime()));
        contentValues.put(gO, c1031a.getValue());
        return contentValues;
    }

    protected String[] bn() {
        return this.gu;
    }

    protected C1022e m2111f(Cursor cursor) {
        return new C1022e(Double.valueOf(cursor.getDouble(cursor.getColumnIndex("latitude"))), Double.valueOf(cursor.getDouble(cursor.getColumnIndex("longitude"))), cursor.getString(cursor.getColumnIndex(gM)), C1059m.m2189d((long) cursor.getInt(cursor.getColumnIndex("date"))));
    }

    private void m2105a(C1031a c1031a) {
        super.m2082f(gs, ("date NOT IN (SELECT date FROM times_location_table WHERE times_type like '" + c1031a.getValue() + "' ORDER BY " + "date" + " DESC LIMIT 1 ) ") + " AND times_type like '" + c1031a.getValue() + "'");
    }
}
