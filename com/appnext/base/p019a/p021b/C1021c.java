package com.appnext.base.p019a.p021b;

import android.text.TextUtils;
import org.json.JSONObject;

public class C1021c extends C1018d {
    private String gf;
    private String gg;
    private String gh;
    private String gi;
    private String gj;
    private String gk;
    private boolean gl;
    private String gm;
    private JSONObject gn;

    public C1021c(String str, String str2, String str3, String str4, String str5, boolean z, String str6, String str7, String str8) {
        this.gf = str;
        this.gg = str2;
        this.gh = str3;
        this.gi = str4;
        this.gj = str5;
        this.gk = str6;
        this.gl = z;
        this.gm = str7;
        if (TextUtils.isEmpty(str8)) {
            this.gn = null;
            return;
        }
        try {
            this.gn = new JSONObject(str8);
        } catch (Throwable th) {
            this.gn = null;
        }
    }

    public String ba() {
        return this.gf;
    }

    public String bb() {
        return this.gg;
    }

    public String bc() {
        return this.gh;
    }

    public String bd() {
        return this.gi;
    }

    public String be() {
        return this.gj;
    }

    public String getKey() {
        return this.gk;
    }

    public String bf() {
        return this.gm;
    }

    public boolean bg() {
        return this.gl;
    }

    public JSONObject bh() {
        return this.gn;
    }

    private boolean m2062X(String str) {
        if (this.gn == null || !this.gn.has(str) || this.gn.isNull(str)) {
            return false;
        }
        return true;
    }

    public String m2066e(String str, String str2) {
        if (m2062X(str)) {
            try {
                str2 = this.gn.getString(str);
            } catch (Throwable th) {
            }
        }
        return str2;
    }

    public long m2063a(String str, long j) {
        if (m2062X(str)) {
            try {
                j = this.gn.getLong(str);
            } catch (Throwable th) {
            }
        }
        return j;
    }

    public int m2065b(String str, int i) {
        if (m2062X(str)) {
            try {
                i = this.gn.getInt(str);
            } catch (Throwable th) {
            }
        }
        return i;
    }

    public boolean m2064a(String str, boolean z) {
        if (m2062X(str)) {
            try {
                z = this.gn.getBoolean(str);
            } catch (Throwable th) {
            }
        }
        return z;
    }
}
