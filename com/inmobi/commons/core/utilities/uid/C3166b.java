package com.inmobi.commons.core.utilities.uid;

import android.content.Context;
import com.inmobi.commons.core.p116c.C3116c;

/* compiled from: UidDao */
class C3166b {
    private C3116c f7831a;

    public C3166b() {
        this.f7831a = C3116c.m10143b("uid_store");
    }

    public C3166b(Context context) {
        this.f7831a = C3116c.m10141a(context, "uid_store");
    }

    public void m10503a(String str) {
        this.f7831a.m10146a("adv_id", str);
    }

    public String m10501a() {
        return this.f7831a.m10150b("adv_id", null);
    }

    public void m10504a(boolean z) {
        this.f7831a.m10147a("limit_ad_tracking", z);
    }

    public Boolean m10505b() {
        return this.f7831a.m10152c("limit_ad_tracking") ? Boolean.valueOf(this.f7831a.m10151b("limit_ad_tracking", true)) : null;
    }

    public void m10506b(String str) {
        this.f7831a.m10146a("app_id", str);
    }

    public String m10507c() {
        return this.f7831a.m10150b("app_id", null);
    }

    public void m10508c(String str) {
        this.f7831a.m10146a("im_id", str);
    }

    public String m10509d() {
        return this.f7831a.m10150b("im_id", null);
    }

    public void m10502a(long j) {
        this.f7831a.m10145a("imid_timestamp", j);
    }

    public long m10511e() {
        return this.f7831a.m10149b("imid_timestamp", 0);
    }

    public void m10510d(String str) {
        this.f7831a.m10146a("appended_id", str);
    }

    public String m10512f() {
        return this.f7831a.m10150b("appended_id", null);
    }
}
