package com.vungle.publisher;

import android.text.TextUtils;

/* compiled from: vungle */
public class hp {
    public final String f10291a;
    public final String[] f10292b;
    public final String f10293c;
    public final String[] f10294d;
    public final String f10295e;
    public final String f10296f;
    public final String f10297g;
    public final String f10298h;

    /* compiled from: vungle */
    public static final class C4193a {
        private String f10283a;
        private String[] f10284b;
        private String f10285c = "";
        private String[] f10286d = new String[0];
        private String f10287e;
        private String f10288f;
        private String f10289g;
        private String f10290h;

        public C4193a m13319a(String str) {
            this.f10283a = str;
            return this;
        }

        public C4193a m13322b(String str) {
            this.f10285c = this.f10285c.concat(str);
            return this;
        }

        public C4193a m13320a(String[] strArr) {
            this.f10286d = (String[]) yz.a(new String[][]{this.f10286d, strArr});
            return this;
        }

        public C4193a m13323c(String str) {
            return m13320a(new String[]{str});
        }

        public hp m13321a() {
            return new hp();
        }
    }

    private hp(C4193a c4193a) {
        this.f10291a = c4193a.f10283a;
        this.f10292b = c4193a.f10284b;
        this.f10293c = c4193a.f10285c;
        this.f10294d = c4193a.f10286d;
        this.f10295e = c4193a.f10287e;
        this.f10296f = c4193a.f10288f;
        this.f10297g = c4193a.f10289g;
        this.f10298h = c4193a.f10290h;
    }

    public String m13324a() {
        String str;
        Object a = zk.a(this.f10292b);
        Object a2 = zk.a(this.f10294d);
        StringBuilder append = new StringBuilder().append(TextUtils.isEmpty(this.f10291a) ? "" : "table: " + this.f10291a + "; ").append(TextUtils.isEmpty(a) ? "" : "columns: " + a + "; ").append(TextUtils.isEmpty(this.f10293c) ? "" : "selection: " + this.f10293c + "; ").append(TextUtils.isEmpty(a2) ? "" : "selectionArgs: " + a2 + "; ").append(TextUtils.isEmpty(this.f10295e) ? "" : "groupBy: " + this.f10295e + "; ").append(TextUtils.isEmpty(this.f10296f) ? "" : "having: " + this.f10296f + "; ").append(TextUtils.isEmpty(this.f10297g) ? "" : "orderBy: " + this.f10297g + "; ");
        if (TextUtils.isEmpty(this.f10298h)) {
            str = "";
        } else {
            str = "limit: " + this.f10298h + "; ";
        }
        return append.append(str).toString();
    }
}
