package com.inmobi.signals.p119a;

import com.github.lzyzsd.jsbridge.BridgeUtil;
import java.util.Locale;

/* compiled from: CellOperatorInfo */
public class C3245a {
    private int f8160a = -1;
    private int f8161b = -1;
    private int f8162c = -1;
    private int f8163d = -1;
    private String f8164e;

    public void m10835a(int i) {
        this.f8160a = i;
    }

    public void m10838b(int i) {
        this.f8161b = i;
    }

    public void m10840c(int i) {
        this.f8162c = i;
    }

    public void m10841d(int i) {
        this.f8163d = i;
    }

    public String m10834a() {
        if (this.f8160a == -1 && this.f8161b == -1) {
            return null;
        }
        return this.f8160a + BridgeUtil.UNDERLINE_STR + this.f8161b;
    }

    public String m10837b() {
        if (this.f8162c == -1 && this.f8163d == -1) {
            return null;
        }
        return this.f8162c + BridgeUtil.UNDERLINE_STR + this.f8163d;
    }

    public void m10836a(String str) {
        if (str != null) {
            this.f8164e = str.toLowerCase(Locale.ENGLISH);
        }
    }

    public String m10839c() {
        return this.f8164e;
    }
}
