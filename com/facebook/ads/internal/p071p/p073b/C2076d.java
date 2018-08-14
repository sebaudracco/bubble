package com.facebook.ads.internal.p071p.p073b;

import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class C2076d {
    private static final Pattern f4915d = Pattern.compile("[R,r]ange:[ ]?bytes=(\\d*)-");
    private static final Pattern f4916e = Pattern.compile("GET /(.*) HTTP");
    public final String f4917a;
    public final long f4918b;
    public final boolean f4919c;

    public C2076d(String str) {
        C2092j.m6733a(str);
        long a = m6664a(str);
        this.f4918b = Math.max(0, a);
        this.f4919c = a >= 0;
        this.f4917a = m6666b(str);
    }

    private long m6664a(String str) {
        Matcher matcher = f4915d.matcher(str);
        return matcher.find() ? Long.parseLong(matcher.group(1)) : -1;
    }

    public static C2076d m6665a(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            Object readLine = bufferedReader.readLine();
            if (TextUtils.isEmpty(readLine)) {
                return new C2076d(stringBuilder.toString());
            }
            stringBuilder.append(readLine).append('\n');
        }
    }

    private String m6666b(String str) {
        Matcher matcher = f4916e.matcher(str);
        if (matcher.find()) {
            return matcher.group(1);
        }
        throw new IllegalArgumentException("Invalid request `" + str + "`: url not found!");
    }

    public String toString() {
        return "GetRequest{rangeOffset=" + this.f4918b + ", partial=" + this.f4919c + ", uri='" + this.f4917a + '\'' + '}';
    }
}
