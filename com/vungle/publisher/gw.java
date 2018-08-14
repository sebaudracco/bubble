package com.vungle.publisher;

import android.database.Cursor;
import android.support.v4.app.NotificationCompat;
import com.vungle.publisher.da.a;
import com.vungle.publisher.dp.C1592a;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class gw extends da<gq> {
    @Inject
    C1629a f2984e;

    @Singleton
    /* compiled from: vungle */
    public static class C1629a extends a<gq, gw> {
        @Inject
        Provider<gw> f2983a;

        public /* bridge */ /* synthetic */ List m4082a(String str, String[] strArr) {
            return super.a(str, strArr);
        }

        protected /* synthetic */ dp[] m4084d(int i) {
            return m4083c(i);
        }

        protected /* synthetic */ dp g_() {
            return m4080a();
        }

        @Inject
        C1629a() {
        }

        protected gw m4080a() {
            return (gw) this.f2983a.get();
        }

        protected gw[] m4083c(int i) {
            return new gw[i];
        }

        protected jf m4081a(Cursor cursor) {
            return new hf(ce.e(cursor, NotificationCompat.CATEGORY_EVENT));
        }
    }

    protected /* synthetic */ C1592a b_() {
        return m4085m();
    }

    @Inject
    gw() {
    }

    protected C1629a m4085m() {
        return this.f2984e;
    }
}
