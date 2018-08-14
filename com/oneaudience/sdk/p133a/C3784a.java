package com.oneaudience.sdk.p133a;

import android.content.Context;
import android.content.SharedPreferences;
import com.a.a.e;
import com.oneaudience.sdk.C3843e;
import com.oneaudience.sdk.p135c.C3833d;

public abstract class C3784a {
    protected static final String f9072a = C3843e.class.getSimpleName();
    protected SharedPreferences f9073b = this.f9074c.getSharedPreferences("oneaudience", 0);
    protected Context f9074c;
    protected boolean f9075d;
    protected boolean f9076e;
    private String f9077f;
    private boolean f9078g;
    private long f9079h;
    private String f9080i;
    private String f9081j;
    private String f9082k;
    private String f9083l;
    private boolean f9084m;
    private boolean f9085n;

    class C37831 implements Runnable {
        final /* synthetic */ C3784a f9071a;

        C37831(C3784a c3784a) {
            this.f9071a = c3784a;
        }

        public void run() {
            try {
                String a = this.f9071a.mo6804a();
                if (this.f9071a.f9075d) {
                    this.f9071a.m12084a(a);
                }
                this.f9071a.f9073b.edit().putLong(this.f9071a.f9082k, System.currentTimeMillis() / 1000).apply();
                C3833d.m12246a(C3784a.f9072a, "Collector with key: " + this.f9071a.f9080i + " succeeded");
            } catch (Throwable th) {
                C3833d.m12250b(C3784a.f9072a, "Collector with key: " + this.f9071a.f9080i + " failed. Exception:", th);
            }
        }
    }

    protected C3784a(Context context, String str, boolean z, boolean z2, long j, String str2, String str3, boolean z3, boolean z4) {
        this.f9074c = context;
        this.f9077f = str;
        this.f9076e = z;
        this.f9078g = z2;
        this.f9079h = j;
        this.f9080i = str2;
        this.f9082k = this.f9080i + "_last_scan";
        this.f9083l = this.f9080i + "_last_result";
        this.f9081j = str3;
        this.f9084m = z3;
        this.f9085n = z4;
        this.f9075d = true;
    }

    public abstract String mo6804a();

    protected String m12083a(Object obj) {
        return new e().a(obj);
    }

    public void m12084a(String str) {
        if (!str.isEmpty()) {
            String str2;
            if (this.f9085n || (this.f9084m && str.equals(m12090g()))) {
                str2 = str;
            } else {
                str2 = this.f9073b.getString(this.f9080i, "");
                str2 = str2.isEmpty() ? "[" + str + "]" : str2.substring(0, str2.length() - 1) + "," + str + "]";
            }
            this.f9073b.edit().putString(this.f9080i, str2).apply();
            this.f9073b.edit().putString(this.f9083l, str).apply();
        }
    }

    public abstract String[] mo6805b();

    public boolean m12086c() {
        return this.f9076e;
    }

    public boolean m12087d() {
        return this.f9078g;
    }

    public long m12088e() {
        return this.f9079h;
    }

    public Thread m12089f() {
        return C3843e.m12286a(this.f9074c, this.f9081j, true) ? new Thread(new C37831(this)) : null;
    }

    public String m12090g() {
        return this.f9073b.getString(this.f9083l, "");
    }

    public long m12091h() {
        return this.f9073b.getLong(this.f9082k, 0);
    }
}
