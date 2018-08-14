package com.oneaudience.sdk.p134b;

import com.oneaudience.sdk.p134b.C3819c.C3815a;
import java.util.Random;
import org.json.JSONException;
import org.json.JSONObject;

public class C3817b {
    private static String f9168a = "encryptedData";
    private static String f9169b = "encryptedKey";
    private static C3817b f9170c;
    private static C3816a f9171d;
    private C3813a f9172e;
    private C3819c f9173f;

    private static class C3816a implements C3815a {
        private static String f9167a = C3816a.m12206b();

        private C3816a() {
        }

        private static String m12206b() {
            Random random = new Random();
            String str = "qwertyuiopasdfghjklzxcvbnm1234567890";
            char[] cArr = new char[10];
            for (int i = 0; i < 10; i++) {
                cArr[i] = str.charAt(random.nextInt(str.length()));
            }
            return new String(cArr);
        }

        public String mo6807a() {
            return f9167a;
        }
    }

    private C3817b(C3813a c3813a, C3819c c3819c) {
        this.f9173f = c3819c;
        this.f9172e = c3813a;
    }

    public static synchronized C3817b m12208a() {
        C3817b c3817b;
        synchronized (C3817b.class) {
            if (f9170c == null) {
                C3813a a = C3813a.m12203a();
                f9171d = new C3816a();
                f9170c = new C3817b(a, new C3819c(f9171d));
            }
            c3817b = f9170c;
        }
        return c3817b;
    }

    public String m12209a(String str) {
        String a = this.f9173f.m12212a(str);
        String a2 = this.f9172e.m12204a(f9171d.mo6807a());
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(f9168a, a);
            jSONObject.put(f9169b, a2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }
}
