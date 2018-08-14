package com.vungle.publisher;

import android.os.Bundle;
import com.vungle.publisher.lf.a;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class li extends lf {
    private String f3074a;
    private String f3075b;

    @Singleton
    /* compiled from: vungle */
    public static class C1651a extends a {
        @Inject
        Provider<li> f3073a;

        @Inject
        C1651a() {
        }

        public li m4334a(String str) {
            li liVar = (li) this.f3073a.get();
            liVar.f3074a = str;
            return liVar;
        }

        public lf m4333a(Bundle bundle) {
            return m4334a(bundle.getString("webViewRootContentString"));
        }
    }

    @Inject
    li() {
    }

    public boolean m4337c() {
        return this.f3074a != null;
    }

    public String m4338d() {
        if (this.f3075b == null) {
            this.f3075b = zk.d(this.f3074a);
        }
        return this.f3075b;
    }

    public void m4336a(Bundle bundle) {
        bundle.putString("webViewRootContentString", this.f3074a);
    }
}
