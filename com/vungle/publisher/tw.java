package com.vungle.publisher;

import java.net.HttpURLConnection;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class tw {
    long f3367a;
    private HttpURLConnection f3368b;
    private int f3369c;
    private String f3370d;
    private List<ts> f3371e;

    @Singleton
    /* compiled from: vungle */
    public static class C1673a {
        @Inject
        Provider<tw> f3366a;

        @Inject
        C1673a() {
        }

        public tw m4681a(HttpURLConnection httpURLConnection, int i, List<ts> list, long j) {
            tw twVar = (tw) this.f3366a.get();
            twVar.f3368b = httpURLConnection;
            twVar.f3371e = list;
            twVar.f3369c = i;
            twVar.f3370d = httpURLConnection == null ? null : String.valueOf(httpURLConnection.getURL());
            twVar.f3367a = j;
            return twVar;
        }
    }

    @Inject
    tw() {
    }

    public HttpURLConnection m4686a() {
        return this.f3368b;
    }

    void m4687a(int i) {
        this.f3369c = i;
    }

    public int m4688b() {
        return this.f3369c;
    }
}
