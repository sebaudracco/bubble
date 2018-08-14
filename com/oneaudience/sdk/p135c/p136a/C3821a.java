package com.oneaudience.sdk.p135c.p136a;

import com.oneaudience.sdk.p135c.p137b.C3825b;
import java.util.Collection;
import java.util.Map;

public class C3821a {
    public final boolean f9181a;
    public final String f9182b;
    public final Map<String, String> f9183c;
    public final Object f9184d;

    public C3821a(String str, Map<String, String> map, Object obj) {
        this.f9182b = str;
        this.f9181a = true;
        this.f9183c = map;
        this.f9184d = obj;
    }

    public C3821a(String str, Map<String, String> map, Object obj, boolean z) {
        this.f9182b = str;
        this.f9181a = z;
        this.f9183c = map;
        this.f9184d = obj;
    }

    public boolean m12226a() {
        return this.f9184d != null && ((this.f9184d instanceof Map) ? C3825b.m12232b((Map) this.f9184d) : C3825b.m12231b((Collection) this.f9184d));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        C3821a c3821a = (C3821a) obj;
        if (this.f9184d == null ? c3821a.f9184d != null : !this.f9184d.equals(c3821a.f9184d)) {
            return false;
        }
        if (this.f9183c == null ? c3821a.f9183c != null : !this.f9183c.equals(c3821a.f9183c)) {
            return false;
        }
        if (this.f9182b != null) {
            if (this.f9182b.equals(c3821a.f9182b)) {
                return true;
            }
        } else if (c3821a.f9182b == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f9183c != null ? this.f9183c.hashCode() : 0) + ((this.f9182b != null ? this.f9182b.hashCode() : 0) * 31)) * 31;
        if (this.f9184d != null) {
            i = this.f9184d.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "Request{url='" + this.f9182b + '\'' + ", headerFields=" + this.f9183c + ", body=" + this.f9184d + '}';
    }
}
