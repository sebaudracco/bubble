package com.adcolony.sdk;

class C0801x {
    private String f1506a;
    private String f1507b;
    private String f1508c;
    private String f1509d = "%s_%s_%s";

    public C0801x(String str, String str2, String str3) {
        this.f1506a = str;
        this.f1507b = str2;
        this.f1508c = str3;
    }

    public String m1445a() {
        return String.format(this.f1509d, new Object[]{m1446b(), m1447c(), m1448d()});
    }

    public String m1446b() {
        return this.f1506a;
    }

    public String m1447c() {
        return this.f1507b;
    }

    public String m1448d() {
        return this.f1508c;
    }
}
