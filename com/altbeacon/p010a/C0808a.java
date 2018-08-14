package com.altbeacon.p010a;

import java.util.ArrayList;
import java.util.List;

public class C0808a {
    private List<C0812c> f1518a = m1490b();
    private byte[] f1519b;

    public C0808a(byte[] bArr) {
        this.f1519b = bArr;
    }

    private List<C0812c> m1490b() {
        List arrayList = new ArrayList();
        int i = 0;
        do {
            C0812c a = C0812c.m1511a(this.f1519b, i);
            if (a != null) {
                i = (i + a.m1513b()) + 1;
                arrayList.add(a);
            }
            if (a == null) {
                break;
            }
        } while (i < this.f1519b.length);
        return arrayList;
    }

    public List<C0812c> m1491a() {
        return this.f1518a;
    }
}
