package com.inmobi.commons.core.utilities.p117a;

import com.inmobi.commons.core.p116c.C3116c;

/* compiled from: EncryptionDao */
public class C3149a {
    private C3116c f7766a = C3116c.m10143b("aes_key_store");

    public static String m10364a() {
        return C3116c.m10142a("aes_key_store");
    }

    public void m10365a(String str) {
        this.f7766a.m10146a("aes_public_key", str);
        this.f7766a.m10145a("last_generated_ts", System.currentTimeMillis() / 1000);
    }

    public String m10366b() {
        return this.f7766a.m10150b("aes_public_key", null);
    }

    public long m10367c() {
        return this.f7766a.m10149b("last_generated_ts", 0);
    }
}
