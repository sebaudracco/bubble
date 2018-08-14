package com.inmobi.commons.core.utilities.uid;

import android.util.Base64;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.p117a.C3151c;
import com.inmobi.commons.p112a.C3105a;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* compiled from: UidMap */
public class C3169d {
    public static final long f7839a = TimeUnit.DAYS.toMillis(30);
    private Map<String, Boolean> f7840b;

    public C3169d(Map<String, Boolean> map) {
        this.f7840b = map;
    }

    public HashMap<String, String> m10538a() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("u-id-map", m10537c());
        return hashMap;
    }

    public Map<String, String> m10540b() {
        String num = Integer.toString(new Random().nextInt());
        String a = C3151c.m10378a(new JSONObject(m10539a(num, true)).toString());
        Map<String, String> hashMap = new HashMap();
        hashMap.put("u-id-map", a);
        hashMap.put("u-id-key", num);
        hashMap.put("u-key-ver", C3168c.m10513a().m10527f());
        return hashMap;
    }

    private String m10537c() {
        return new JSONObject(m10539a(null, false)).toString();
    }

    public Map<String, String> m10539a(String str, boolean z) {
        Map<String, String> hashMap = new HashMap();
        try {
            Object b;
            if (((Boolean) this.f7840b.get("UM5")).booleanValue() && !C3168c.m10513a().m10533l()) {
                b = C3168c.m10513a().m10522b(C3168c.m10513a().m10530i());
                if (z) {
                    b = m10536a((String) b, str);
                }
                hashMap.put("UM5", b);
            }
            if (((Boolean) this.f7840b.get("LID")).booleanValue()) {
                b = C3168c.m10513a().m10528g();
                if (b != null && b.trim().length() > 0) {
                    if (z) {
                        b = m10536a((String) b, str);
                    }
                    hashMap.put("LID", b);
                }
            }
            if (((Boolean) this.f7840b.get("SID")).booleanValue()) {
                b = C3168c.m10513a().m10529h();
                if (b != null && b.trim().length() > 0) {
                    if (z) {
                        b = m10536a((String) b, str);
                    }
                    hashMap.put("SID", b);
                }
            }
            if (((Boolean) this.f7840b.get("GPID")).booleanValue()) {
                C3165a j = C3168c.m10513a().m10531j();
                if (j != null) {
                    b = j.m10500b();
                    if (b != null) {
                        if (z) {
                            b = m10536a((String) b, str);
                        }
                        hashMap.put("GPID", b);
                    }
                }
                b = C3168c.m10513a().m10518a(C3168c.m10513a().m10530i());
                if (z) {
                    b = m10536a((String) b, str);
                }
                hashMap.put("O1", b);
            }
            if (((Boolean) this.f7840b.get("IMID")).booleanValue()) {
                b = C3168c.m10513a().m10517a(C3105a.m10078b());
                if (b != null) {
                    if (z) {
                        b = m10536a((String) b, str);
                    }
                    hashMap.put("IMID", b);
                }
            }
            if (((Boolean) this.f7840b.get("AIDL")).booleanValue()) {
                b = C3168c.m10513a().m10521b(C3105a.m10078b());
                if (b != null) {
                    if (z) {
                        b = m10536a((String) b, str);
                    }
                    hashMap.put("AIDL", b);
                }
            }
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, C3169d.class.getSimpleName(), "SDK encountered unexpected error in getting UID map");
        }
        return hashMap;
    }

    private String m10536a(String str, String str2) {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            byte[] bArr = new byte[bytes.length];
            byte[] bytes2 = str2.getBytes("UTF-8");
            for (int i = 0; i < bytes.length; i++) {
                bArr[i] = (byte) (bytes[i] ^ bytes2[i % bytes2.length]);
            }
            return new String(Base64.encode(bArr, 2), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
