package com.fyber.utils;

import com.google.android.gms.common.util.AndroidUtilsLight;
import java.security.MessageDigest;
import java.util.Formatter;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

/* compiled from: SignatureTools */
public final class C2669q {
    public static String m8530a(Map<String, String> map, String str) {
        Iterator it = new TreeSet(map.keySet()).iterator();
        String str2 = "";
        while (it.hasNext()) {
            String str3 = (String) it.next();
            String str4 = (String) map.get(str3);
            StringBuilder append = new StringBuilder().append(str2);
            String str5 = "%s=%s&";
            Object[] objArr = new Object[2];
            objArr[0] = str3;
            if (str4 == null) {
                str4 = "";
            }
            objArr[1] = str4;
            str2 = append.append(String.format(str5, objArr)).toString();
        }
        return C2669q.m8529a(str2, str);
    }

    public static String m8529a(String str, String str2) {
        return C2669q.m8528a(str + str2);
    }

    public static String m8528a(String str) {
        String str2 = "nosha1";
        try {
            byte[] digest = MessageDigest.getInstance(AndroidUtilsLight.DIGEST_ALGORITHM_SHA1).digest(str.getBytes());
            Formatter formatter = new Formatter();
            int length = digest.length;
            for (int i = 0; i < length; i++) {
                formatter.format("%02x", new Object[]{Byte.valueOf(digest[i])});
            }
            String formatter2 = formatter.toString();
            formatter.close();
            return formatter2;
        } catch (Exception e) {
            FyberLogger.m8450e("UrlBuilder", "SHA1 algorithm not available.", e);
            return str2;
        }
    }
}
