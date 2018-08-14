package com.yandex.metrica.impl;

import android.os.Bundle;
import com.yandex.metrica.impl.utils.C4523f;
import com.yandex.metrica.impl.utils.C4523f.C4522a;
import org.json.JSONException;
import org.json.JSONObject;

public class C4305a {
    private JSONObject f11566a = new JSONObject();
    private long f11567b;
    private boolean f11568c;
    private C4522a f11569d = C4522a.m16256d();
    private final C4523f f11570e = new C4523f();

    public static final class C4304a {
        public final String f11564a;
        public final long f11565b;

        public C4304a(String str, long j) {
            this.f11564a = str;
            this.f11565b = j;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            C4304a c4304a = (C4304a) o;
            if (this.f11565b != c4304a.f11565b) {
                return false;
            }
            if (this.f11564a != null) {
                if (this.f11564a.equals(c4304a.f11564a)) {
                    return true;
                }
            } else if (c4304a.f11564a == null) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return ((this.f11564a != null ? this.f11564a.hashCode() : 0) * 31) + ((int) (this.f11565b ^ (this.f11565b >>> 32)));
        }
    }

    public C4305a(String str, long j) {
        this.f11567b = j;
        try {
            this.f11566a = new JSONObject(str);
        } catch (JSONException e) {
            this.f11566a = new JSONObject();
            this.f11567b = 0;
        }
    }

    public synchronized void m14449a() {
        this.f11566a = new JSONObject();
        this.f11567b = 0;
    }

    public synchronized void m14451a(String str, String str2) {
        try {
            String a = this.f11570e.m16261a(str, this.f11569d.m16258b(), "App Environment");
            String a2 = this.f11570e.m16261a(str2, this.f11569d.m16259c(), "App Environment");
            if (this.f11566a.has(a)) {
                String string = this.f11566a.getString(a);
                if (a2 == null || !a2.equals(string)) {
                    m14453b(a, a2);
                }
            } else if (a2 != null) {
                m14453b(a, a2);
            }
        } catch (JSONException e) {
        }
    }

    public synchronized void m14450a(Bundle bundle) {
        for (String str : bundle.keySet()) {
            m14451a(str, bundle.getString(str));
        }
    }

    synchronized void m14453b(String str, String str2) throws JSONException {
        if (this.f11566a.length() < this.f11569d.m16257a() || (this.f11569d.m16257a() == this.f11566a.length() && this.f11566a.has(str))) {
            this.f11566a.put(str, str2);
            this.f11568c = true;
        } else {
            this.f11570e.m16265b(str, this.f11569d.m16257a(), "App Environment");
        }
    }

    public synchronized C4304a m14452b() {
        if (this.f11568c) {
            this.f11567b++;
            this.f11568c = false;
        }
        return new C4304a(this.f11566a.toString(), this.f11567b);
    }

    public synchronized String toString() {
        StringBuilder stringBuilder;
        stringBuilder = new StringBuilder("Map size ");
        stringBuilder.append(this.f11566a.length());
        stringBuilder.append(". Is changed ");
        stringBuilder.append(this.f11568c);
        stringBuilder.append(". Current revision ");
        stringBuilder.append(this.f11567b);
        return stringBuilder.toString();
    }
}
