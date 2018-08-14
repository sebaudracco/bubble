package com.yandex.metrica;

public enum C4275a {
    PHONE("phone"),
    TABLET("tablet"),
    TV("tv");
    
    private final String f11446d;

    private C4275a(String str) {
        this.f11446d = str;
    }

    public String m14313a() {
        return this.f11446d;
    }

    public static C4275a m14312a(String str) {
        for (C4275a c4275a : C4275a.values()) {
            if (c4275a.f11446d.equals(str)) {
                return c4275a;
            }
        }
        return null;
    }
}
