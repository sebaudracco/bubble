package com.inmobi.ads;

import android.content.ContentValues;
import java.util.Map;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

/* compiled from: Placement */
public class bb {
    private static final String f7227a = bb.class.getSimpleName();
    private long f7228b;
    private String f7229c;
    private Map<String, String> f7230d;
    private String f7231e;
    private String f7232f = SchemaSymbols.ATTVAL_INT;

    public bb(long j, String str) {
        this.f7228b = j;
        this.f7229c = str;
        if (this.f7229c == null) {
            this.f7229c = "";
        }
    }

    public void m9473a(Map<String, String> map) {
        this.f7230d = map;
    }

    public void m9472a(String str) {
        this.f7231e = str;
    }

    public Map<String, String> m9471a() {
        return this.f7230d;
    }

    public String m9474b() {
        return this.f7231e;
    }

    public long m9475c() {
        return this.f7228b;
    }

    public String m9476d() {
        return this.f7229c;
    }

    public ContentValues m9477e() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("placement_id", Long.valueOf(this.f7228b));
        contentValues.put("last_accessed_ts", Long.valueOf(System.currentTimeMillis()));
        contentValues.put("tp_key", this.f7229c);
        return contentValues;
    }

    public bb(ContentValues contentValues) {
        this.f7228b = contentValues.getAsLong("placement_id").longValue();
        this.f7229c = contentValues.getAsString("tp_key");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        bb bbVar = (bb) obj;
        if (this.f7228b != bbVar.f7228b) {
            return false;
        }
        if (this.f7229c == null && bbVar.f7229c == null) {
            return true;
        }
        return (this.f7229c == null || bbVar.f7229c == null) ? false : this.f7229c.equals(bbVar.f7229c);
    }

    public int hashCode() {
        int i = (int) (this.f7228b ^ (this.f7228b >>> 32));
        if (this.f7229c != null) {
            return (i * 31) + this.f7229c.hashCode();
        }
        return i;
    }
}
