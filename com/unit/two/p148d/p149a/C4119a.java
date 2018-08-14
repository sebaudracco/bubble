package com.unit.two.p148d.p149a;

import android.content.Context;
import android.text.TextUtils;
import com.unit.two.p146b.C4094a;
import com.unit.two.p147c.C4096a;
import com.unit.two.p147c.C4104i;
import com.unit.two.p151f.C4139f;
import com.unit.two.p151f.C4140g;
import com.unit.two.p151f.C4143j;
import com.unit.two.p151f.C4144k;
import com.unit.two.p151f.C4145l;
import com.unit.two.p151f.p152a.C4133b;
import java.util.Map;
import java.util.WeakHashMap;
import org.altbeacon.beacon.service.scanner.CycledLeScanner;
import org.json.JSONObject;

public class C4119a extends C4094a {
    private static long f9591a;
    private static Map f9592e = new WeakHashMap();
    private Context f9593b;
    private C4120b f9594c;
    private String f9595d;
    private String f9596f;

    static {
        String str = C4096a.bF;
    }

    public C4119a(Context context, C4120b c4120b) {
        this.f9593b = context;
        this.f9594c = c4120b;
        C4120b c4120b2 = this.f9594c;
        this.f9595d = C4145l.m12836d(new StringBuilder(C4096a.bR).append(c4120b2.m12725d()).append(C4096a.bS).append(c4120b2.m12726e()).append(C4096a.bS).append(c4120b2.m12723b()).append(C4096a.bS).append(c4120b2.m12722a()).append(C4096a.bS).append(c4120b2.m12727f()).toString());
    }

    private JSONObject m12714a(String str) {
        int i = 0;
        try {
            if (TextUtils.isEmpty(str)) {
                this.f9596f = C4096a.bG;
                return null;
            }
            JSONObject jSONObject = new JSONObject(str);
            JSONObject optJSONObject = jSONObject.optJSONObject(C4096a.bH);
            String optString = jSONObject.optString(C4096a.bI);
            int optInt;
            if (optJSONObject != null) {
                optInt = optJSONObject.optInt(C4096a.bJ);
                i = optJSONObject.optInt(C4096a.bK);
                C4144k.m12810d(this.f9593b, (long) optJSONObject.optInt(C4096a.bL, 0));
            } else {
                optInt = 0;
            }
            if (TextUtils.isEmpty(optString) || r3 != 200) {
                this.f9596f = C4096a.bM;
                return null;
            }
            if (i == 1) {
                optString = C4145l.m12829b(optString);
            }
            if (i == 2) {
                optString = C4133b.m12763b(C4145l.m12829b(optString), C4096a.bN);
                if (optString == null) {
                    this.f9596f = C4096a.bO;
                    return null;
                }
            }
            String str2 = optString;
            optString = new C4139f(System.currentTimeMillis(), str).toString();
            f9592e.put(this.f9595d, optString);
            C4144k.m12804b(this.f9593b, this.f9595d, optString);
            return new JSONObject(str2);
        } catch (Throwable th) {
            return null;
        }
    }

    private JSONObject m12715c() {
        synchronized (C4119a.class) {
            CharSequence charSequence = (String) f9592e.get(this.f9595d);
            if (TextUtils.isEmpty(charSequence)) {
                charSequence = C4144k.m12813g(this.f9593b).getString(this.f9595d, "");
                f9592e.put(this.f9595d, charSequence);
            }
            CharSequence charSequence2 = charSequence;
            if (!TextUtils.isEmpty(charSequence2)) {
                C4139f c4139f = (TextUtils.isEmpty(charSequence2) || charSequence2.length() < 14) ? null : new C4139f(Long.valueOf(charSequence2.substring(0, 13)).longValue(), charSequence2.substring(13));
                if (c4139f != null && System.currentTimeMillis() - c4139f.m12773a() < 28800000) {
                    JSONObject a = m12714a(c4139f.m12774b());
                    return a;
                }
            }
            if (!C4145l.m12833c(this.f9593b)) {
                this.f9596f = C4096a.bP;
                return null;
            } else if (System.currentTimeMillis() - f9591a < CycledLeScanner.ANDROID_N_MAX_SCAN_DURATION_MILLIS) {
                this.f9596f = C4096a.bQ;
                return null;
            } else {
                a = m12714a(new C4140g(C4104i.f9563a).m12775a(C4143j.f9685b).m12781c(C4145l.m12825a(C4122d.m12729a(this.f9593b, this.f9594c), 1)).m12779b(C4145l.m12822a(this.f9593b)).m12778a());
                return a;
            }
        }
    }

    protected final /* synthetic */ Object mo6923a() {
        return m12715c();
    }

    protected final /* synthetic */ void mo6924a(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        if (this.f9594c != null && this.f9594c.m12724c() != null) {
            if (jSONObject != null) {
                this.f9594c.m12724c().mo6925a(jSONObject);
            } else {
                f9591a = System.currentTimeMillis();
            }
        }
    }
}
