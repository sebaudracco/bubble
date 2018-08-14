package com.yandex.metrica.impl.utils;

import android.text.TextUtils;
import com.yandex.metrica.impl.bi;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public class C4523f {

    public static class C4522a {
        private final int f12577a;
        private final int f12578b;
        private final int f12579c;

        public C4522a(int i, int i2, int i3) {
            this.f12577a = i;
            this.f12578b = i2;
            this.f12579c = i3;
        }

        public int m16257a() {
            return this.f12577a;
        }

        public int m16258b() {
            return this.f12578b;
        }

        public int m16259c() {
            return this.f12579c;
        }

        public static C4522a m16256d() {
            return new C4522a(30, 50, 100);
        }
    }

    public boolean m16263a(String str, String str2) {
        return !bi.m14958a(str, str2);
    }

    public Map<String, String> m16262a(Map<String, String> map, String str, String str2, C4522a c4522a, String str3) {
        if (map != null) {
            String a = m16261a(str, c4522a.m16258b(), str3);
            String a2 = m16261a(str2, c4522a.m16259c(), str3);
            if (map.size() < c4522a.m16257a() || map.containsKey(a)) {
                map.put(a, a2);
            } else {
                m16265b(a, c4522a.m16257a(), str3);
            }
        }
        return map;
    }

    public String m16261a(String str, int i, String str2) {
        if (str == null || str.length() <= i) {
            return str;
        }
        String substring = str.substring(0, i);
        C4529j.m16280f().m16246b("\"%s\"'s parameter %s size exceeded limit of %d characters", str2, str, Integer.valueOf(i));
        return substring;
    }

    public String m16260a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            byte[] bytes = str.getBytes("UTF-8");
            if (bytes.length > i) {
                return new String(bytes, 0, i, "UTF-8");
            }
            return str;
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }

    public byte[] m16264a(byte[] bArr, int i) {
        if (bArr.length <= i) {
            return bArr;
        }
        Object obj = new byte[i];
        System.arraycopy(bArr, 0, obj, 0, i);
        return obj;
    }

    public void m16265b(String str, int i, String str2) {
        C4529j.m16280f().m16246b("The %s has reached the limit of %d items. Item with key %s will be ignored", str2, Integer.valueOf(i), str);
    }
}
