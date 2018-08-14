package com.altbeacon.beacon.p014d;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class C0841b {
    private static List<C0840a> f1659a = new ArrayList();

    private static class C0840a {
        public final String f1657a;
        public final byte f1658b;

        public C0840a(String str, byte b) {
            this.f1657a = str;
            this.f1658b = b;
        }
    }

    static {
        f1659a.add(new C0840a(".com/", (byte) 0));
        f1659a.add(new C0840a(".org/", (byte) 1));
        f1659a.add(new C0840a(".edu/", (byte) 2));
        f1659a.add(new C0840a(".net/", (byte) 3));
        f1659a.add(new C0840a(".info/", (byte) 4));
        f1659a.add(new C0840a(".biz/", (byte) 5));
        f1659a.add(new C0840a(".gov/", (byte) 6));
        f1659a.add(new C0840a(".com", (byte) 7));
        f1659a.add(new C0840a(".org", (byte) 8));
        f1659a.add(new C0840a(".edu", (byte) 9));
        f1659a.add(new C0840a(".net", (byte) 10));
        f1659a.add(new C0840a(".info", (byte) 11));
        f1659a.add(new C0840a(".biz", (byte) 12));
        f1659a.add(new C0840a(".gov", (byte) 13));
    }

    private static String m1681a(Byte b) {
        String str = null;
        Iterator it = f1659a.iterator();
        Object obj = null;
        while (obj == null && it.hasNext()) {
            C0840a c0840a = (C0840a) it.next();
            obj = c0840a.f1658b == b.byteValue() ? 1 : null;
            str = obj != null ? c0840a.f1657a : str;
        }
        return str;
    }

    public static String m1682a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        switch (bArr[0] & 15) {
            case 0:
                stringBuffer.append("http://www.");
                break;
            case 1:
                stringBuffer.append("https://www.");
                break;
            case 2:
                stringBuffer.append("http://");
                break;
            case 3:
                stringBuffer.append("https://");
                break;
        }
        byte b = (byte) -1;
        int i = 1;
        while (i < bArr.length) {
            byte b2 = bArr[i];
            if (b == (byte) 0 && b2 == (byte) 0) {
                return stringBuffer.toString();
            }
            String a = C0841b.m1681a(Byte.valueOf(b2));
            if (a != null) {
                stringBuffer.append(a);
            } else {
                stringBuffer.append((char) b2);
            }
            i++;
            b = b2;
        }
        return stringBuffer.toString();
    }
}
