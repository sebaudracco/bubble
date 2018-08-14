package com.yandex.metrica.impl.utils;

import android.text.TextUtils;
import com.yandex.metrica.impl.bk;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class C4532l {
    public static String m16291a(Map<String, String> map) {
        String str = "";
        if (bk.m14988a((Map) map)) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : map.entrySet()) {
            if (!TextUtils.isEmpty((CharSequence) entry.getKey())) {
                stringBuilder.append((String) entry.getKey()).append(":").append((String) entry.getValue()).append(",");
            }
        }
        stringBuilder.setLength(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    public static Map<String, String> m16292a(String str) {
        Map<String, String> hashMap = new HashMap();
        if (!TextUtils.isEmpty(str)) {
            for (String str2 : str.split(",")) {
                int indexOf = str2.indexOf(":");
                if (indexOf != -1) {
                    CharSequence substring = str2.substring(0, indexOf);
                    if (!TextUtils.isEmpty(substring)) {
                        hashMap.put(substring, str2.substring(indexOf + 1));
                    }
                }
            }
        }
        return hashMap;
    }
}
