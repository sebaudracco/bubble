package com.vungle.publisher;

/* compiled from: vungle */
public class hf implements jf {
    final String f10257a;

    public String toString() {
        return this.f10257a;
    }

    public hf(String str) {
        this.f10257a = str;
    }

    public boolean equals(Object object) {
        if (object instanceof hf) {
            return this.f10257a.equals(object.toString());
        }
        return false;
    }

    public int hashCode() {
        return this.f10257a.hashCode();
    }

    public boolean m13294a() {
        return true;
    }
}
