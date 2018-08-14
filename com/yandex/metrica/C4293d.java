package com.yandex.metrica;

import android.text.TextUtils;
import java.util.Map;

public class C4293d {
    private String f11521a;
    private String f11522b;
    private Map<String, String> f11523c;

    public String m14369a() {
        return this.f11521a;
    }

    public void m14370a(String str) {
        this.f11521a = str;
    }

    public String m14372b() {
        return this.f11522b;
    }

    public void m14373b(String str) {
        this.f11522b = str;
    }

    public Map<String, String> m14374c() {
        return this.f11523c;
    }

    public void m14371a(Map<String, String> map) {
        this.f11523c = map;
    }

    public boolean equals(Object o) {
        if (!(o instanceof C4293d)) {
            return false;
        }
        C4293d c4293d = (C4293d) o;
        if (!TextUtils.equals(this.f11521a, c4293d.f11521a) || !TextUtils.equals(this.f11522b, c4293d.f11522b)) {
            return false;
        }
        if (this.f11523c == c4293d.f11523c || this.f11523c == null || this.f11523c.equals(c4293d.f11523c)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.f11521a != null) {
            hashCode = this.f11521a.hashCode();
        } else {
            hashCode = 0;
        }
        int i2 = hashCode * 31;
        if (this.f11522b != null) {
            hashCode = this.f11522b.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + i2) * 31;
        if (this.f11523c != null) {
            i = this.f11523c.hashCode();
        }
        return hashCode + i;
    }
}
