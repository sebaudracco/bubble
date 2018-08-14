package com.altbeacon.beacon.p012b;

import android.os.Build;
import android.os.Build.VERSION;
import com.altbeacon.beacon.p013c.C0835d;

public class C0821a {
    String f1606a;
    String f1607b;
    String f1608c;
    String f1609d;

    public C0821a(String str, String str2, String str3, String str4) {
        this.f1606a = str;
        this.f1607b = str2;
        this.f1608c = str3;
        this.f1609d = str4;
    }

    public static C0821a m1584a() {
        return new C0821a(VERSION.RELEASE, Build.ID, Build.MODEL, Build.MANUFACTURER);
    }

    public int m1585a(C0821a c0821a) {
        int i = this.f1609d.equalsIgnoreCase(c0821a.f1609d) ? 1 : 0;
        if (i == 1 && this.f1608c.equals(c0821a.f1608c)) {
            i = 2;
        }
        if (i == 2 && this.f1607b.equals(c0821a.f1607b)) {
            i = 3;
        }
        if (i == 3 && this.f1606a.equals(c0821a.f1606a)) {
            i = 4;
        }
        C0835d.m1657a("AndroidModel", "Score is %s for %s compared to %s", Integer.valueOf(i), toString(), c0821a);
        return i;
    }

    public String m1586b() {
        return this.f1606a;
    }

    public String m1587c() {
        return this.f1607b;
    }

    public String m1588d() {
        return this.f1608c;
    }

    public String m1589e() {
        return this.f1609d;
    }

    public String toString() {
        return "" + this.f1609d + ";" + this.f1608c + ";" + this.f1607b + ";" + this.f1606a;
    }
}
