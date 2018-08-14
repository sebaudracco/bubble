package com.vungle.publisher;

import android.os.Bundle;
import com.vungle.publisher.lf.a;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class lb extends lf {
    private String f3070a;

    @Singleton
    /* compiled from: vungle */
    public static class C1650a extends a {
        @Inject
        Provider<lb> f3069a;

        @Inject
        C1650a() {
        }

        public lb m4326a(String str) {
            lb lbVar = (lb) this.f3069a.get();
            lbVar.f3070a = str;
            return lbVar;
        }

        public lf m4327a(Bundle bundle) {
            return m4326a(bundle.getString("webViewRootContentIndexFile"));
        }
    }

    @Inject
    lb() {
    }

    public boolean m4330a() {
        return this.f3070a != null;
    }

    public String m4331b() {
        return this.f3070a;
    }

    public void m4329a(Bundle bundle) {
        bundle.putString("webViewRootContentIndexFile", this.f3070a);
    }
}
