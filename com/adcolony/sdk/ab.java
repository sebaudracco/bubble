package com.adcolony.sdk;

import com.github.lzyzsd.jsbridge.BridgeUtil;
import java.text.SimpleDateFormat;
import java.util.Date;

class ab {
    static final SimpleDateFormat f488l = new SimpleDateFormat("yyyyMMdd'T'HHmmss.SSSZ");
    static final String f489m = "message";
    static final String f490n = "timestamp";
    private Date f491a;
    private int f492b;
    private C0801x f493c;
    protected String f494o;

    static class C0596a {
        protected ab f487b = new ab();

        C0596a() {
        }

        C0596a m627a(int i) {
            this.f487b.f492b = i;
            return this;
        }

        C0596a m628a(C0801x c0801x) {
            this.f487b.f493c = c0801x;
            return this;
        }

        C0596a m629a(String str) {
            this.f487b.f494o = str;
            return this;
        }

        C0596a mo1866a(Date date) {
            this.f487b.f491a = date;
            return this;
        }

        ab m631a() {
            if (this.f487b.f491a == null) {
                this.f487b.f491a = new Date(System.currentTimeMillis());
            }
            return this.f487b;
        }
    }

    ab() {
    }

    void m637a(C0801x c0801x) {
        this.f493c = c0801x;
    }

    void m636a(int i) {
        this.f492b = i;
    }

    String m638b() {
        switch (this.f492b) {
            case -1:
                return "Fatal";
            case 0:
                return "Error";
            case 1:
                return "Warn";
            case 2:
                return "Info";
            case 3:
                return "Debug";
            default:
                return "UNKNOWN LOG LEVEL";
        }
    }

    int m639c() {
        return this.f492b;
    }

    String m640d() {
        return this.f494o;
    }

    String m641e() {
        return f488l.format(this.f491a);
    }

    C0801x m642f() {
        return this.f493c;
    }

    public String toString() {
        return m641e() + " " + m638b() + BridgeUtil.SPLIT_MARK + m642f().m1448d() + ": " + m640d();
    }
}
