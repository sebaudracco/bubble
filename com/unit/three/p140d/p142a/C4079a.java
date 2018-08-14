package com.unit.three.p140d.p142a;

import android.content.Context;
import android.text.TextUtils;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.unit.three.p138b.C4053c;
import com.unit.three.p141c.C4078f;
import com.unit.three.p143e.C4087a;
import com.unit.three.p143e.C4089c;
import com.unit.three.p143e.C4090d;
import com.unit.three.p143e.p144a.C4086b;
import java.util.Map;
import java.util.WeakHashMap;
import org.altbeacon.beacon.service.scanner.CycledLeScanner;
import org.json.JSONObject;

public class C4079a implements Runnable {
    private static long f9444a;
    private static Map f9445e = new WeakHashMap();
    private Context f9446b;
    private C4080b f9447c;
    private String f9448d;
    private String f9449f;

    public C4079a(Context context, C4080b c4080b) {
        this.f9446b = context;
        this.f9447c = c4080b;
        C4080b c4080b2 = this.f9447c;
        this.f9448d = C4090d.m12641e("strategy_" + c4080b2.m12592d() + BridgeUtil.UNDERLINE_STR + c4080b2.m12593e() + BridgeUtil.UNDERLINE_STR + c4080b2.m12590b() + BridgeUtil.UNDERLINE_STR + c4080b2.m12589a() + BridgeUtil.UNDERLINE_STR + null);
    }

    private JSONObject m12584a(String str) {
        int i = 0;
        try {
            if (TextUtils.isEmpty(str)) {
                this.f9449f = "data is null";
                return null;
            }
            JSONObject jSONObject = new JSONObject(str);
            JSONObject optJSONObject = jSONObject.optJSONObject("res_status");
            String optString = jSONObject.optString("body");
            int optInt;
            if (optJSONObject != null) {
                optInt = optJSONObject.optInt("resp_code");
                i = optJSONObject.optInt("z");
            } else {
                optInt = 0;
            }
            if (TextUtils.isEmpty(optString) || r3 != 200) {
                this.f9449f = "status !=200 or respBody is empty.";
                return null;
            }
            if (i == 1) {
                optString = C4090d.m12635c(optString);
            }
            if (i == 2) {
                optString = C4086b.m12612b(C4090d.m12635c(optString), "30a161c4b1bde4eea");
                if (optString == null) {
                    this.f9449f = "response body data is null.";
                    return null;
                }
            }
            String str2 = optString;
            optString = new C4087a(System.currentTimeMillis(), str).toString();
            f9445e.put(this.f9448d, optString);
            C4078f.m12567a(C4053c.m12503a().m12515b(), "unit_sharepreference_strategy", this.f9448d, optString);
            return new JSONObject(str2);
        } catch (Throwable th) {
            return null;
        }
    }

    private void m12585a(JSONObject jSONObject) {
        if (this.f9447c != null && this.f9447c.m12591c() != null) {
            if (jSONObject != null) {
                this.f9447c.m12591c().mo6920a(jSONObject);
            } else {
                f9444a = System.currentTimeMillis();
            }
        }
    }

    public void run() {
        synchronized (C4079a.class) {
            CharSequence charSequence = (String) f9445e.get(this.f9448d);
            if (TextUtils.isEmpty(charSequence)) {
                charSequence = C4078f.m12562a("unit_sharepreference_strategy", C4053c.m12503a().m12515b()).getString(this.f9448d, "");
                f9445e.put(this.f9448d, charSequence);
            }
            CharSequence charSequence2 = charSequence;
            if (!TextUtils.isEmpty(charSequence2)) {
                C4087a c4087a = (TextUtils.isEmpty(charSequence2) || charSequence2.length() < 14) ? null : new C4087a(Long.valueOf(charSequence2.substring(0, 13)).longValue(), charSequence2.substring(13));
                if (c4087a != null && System.currentTimeMillis() - c4087a.m12613a() < 28800000) {
                    m12585a(m12584a(c4087a.m12614b()));
                    return;
                }
            }
            if (!C4090d.m12625a(this.f9446b)) {
                this.f9449f = "no network";
                m12585a(null);
            } else if (System.currentTimeMillis() - f9444a < CycledLeScanner.ANDROID_N_MAX_SCAN_DURATION_MILLIS) {
                this.f9449f = "too frequency.";
                m12585a(null);
            } else {
                m12585a(m12584a(new C4089c().m12620a("http://sdata.elephantdata.net/cc/v1/api?serviceid=1", C4078f.m12564a(this.f9446b, this.f9447c), C4090d.m12621a())));
            }
        }
    }
}
