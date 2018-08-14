package com.oneaudience.sdk.p135c.p136a;

import java.util.Map;

public class C3822b {
    public final int f9185a;
    public final Map<String, String> f9186b;
    public final Object f9187c;

    public C3822b(int i, Map<String, String> map, Object obj) {
        this.f9185a = i;
        this.f9186b = map;
        this.f9187c = obj;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        C3822b c3822b = (C3822b) obj;
        if (this.f9185a != c3822b.f9185a) {
            return false;
        }
        if (this.f9187c == null ? c3822b.f9187c != null : !this.f9187c.equals(c3822b.f9187c)) {
            return false;
        }
        if (this.f9186b != null) {
            if (this.f9186b.equals(c3822b.f9186b)) {
                return true;
            }
        } else if (c3822b.f9186b == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f9186b != null ? this.f9186b.hashCode() : 0) + (this.f9185a * 31)) * 31;
        if (this.f9187c != null) {
            i = this.f9187c.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "Response{code=" + this.f9185a + ", headerFields=" + this.f9186b + ", data=" + this.f9187c + '}';
    }
}
