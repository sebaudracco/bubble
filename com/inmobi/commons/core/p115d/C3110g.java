package com.inmobi.commons.core.p115d;

import android.content.ContentValues;
import java.util.UUID;

/* compiled from: TelemetryEvent */
public class C3110g {
    private static final String f7610a = C3110g.class.getSimpleName();
    private String f7611b;
    private String f7612c;
    private String f7613d;
    private long f7614e;
    private String f7615f;

    public C3110g(String str, String str2) {
        this.f7611b = UUID.randomUUID().toString();
        this.f7613d = str;
        this.f7612c = str2;
        this.f7615f = null;
        this.f7614e = System.currentTimeMillis();
    }

    public C3110g(String str, String str2, String str3, String str4) {
        this.f7611b = str;
        this.f7613d = str2;
        this.f7612c = str3;
        this.f7615f = str4;
        this.f7614e = System.currentTimeMillis();
    }

    public String m10121a() {
        return this.f7611b;
    }

    public String m10123b() {
        return this.f7613d;
    }

    public String m10124c() {
        return this.f7612c;
    }

    public String m10125d() {
        return this.f7615f == null ? "" : this.f7615f;
    }

    public long m10126e() {
        return this.f7614e;
    }

    public void m10122a(String str) {
        this.f7615f = str;
    }

    public String toString() {
        return m10124c() + "@" + m10123b() + " ";
    }

    public static C3110g m10120a(ContentValues contentValues) {
        String asString = contentValues.getAsString("eventId");
        String asString2 = contentValues.getAsString("eventType");
        String asString3 = contentValues.getAsString("componentType");
        String asString4 = contentValues.getAsString("payload");
        long longValue = Long.valueOf(contentValues.getAsString("ts")).longValue();
        C3110g c3110g = new C3110g(asString, asString3, asString2, asString4);
        c3110g.f7614e = longValue;
        return c3110g;
    }

    public ContentValues m10127f() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("eventId", m10121a());
        contentValues.put("componentType", m10123b());
        contentValues.put("eventType", m10124c());
        contentValues.put("payload", m10125d());
        contentValues.put("ts", String.valueOf(m10126e()));
        return contentValues;
    }
}
