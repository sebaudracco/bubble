package com.vungle.publisher;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class ts {
    private String f3362a;
    private String f3363b;
    private int f3364c;
    private Long f3365d;

    @Singleton
    /* compiled from: vungle */
    public static class C1672a {
        @Inject
        Provider<ts> f3361a;

        @Inject
        C1672a() {
        }

        public ts m4676a(String str, int i, String str2, Long l) {
            ts tsVar = (ts) this.f3361a.get();
            tsVar.f3363b = str;
            tsVar.f3364c = i;
            tsVar.f3362a = str2;
            tsVar.f3365d = l;
            return tsVar;
        }
    }

    @Inject
    ts() {
    }
}
