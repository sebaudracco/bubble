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
public class kj extends da<kd> {
    @Inject
    C1648a f3065e;

    @Singleton
    /* compiled from: vungle */
    public static class C1648a extends a<kd, kj> {
        @Inject
        Provider<kj> f3064a;

        public /* bridge */ /* synthetic */ List m4316a(String str, String[] strArr) {
            return super.a(str, strArr);
        }

        protected /* synthetic */ dp[] m4318d(int i) {
            return m4317c(i);
        }

        protected /* synthetic */ dp g_() {
            return m4315a();
        }

        @Inject
        C1648a() {
        }

        protected kj m4315a() {
            return (kj) this.f3064a.get();
        }

        protected kj[] m4317c(int i) {
            return new kj[i];
        }

        protected jf m4314a(Cursor cursor) {
            return new hf(ce.e(cursor, NotificationCompat.CATEGORY_EVENT));
        }
    }

    protected /* synthetic */ C1592a b_() {
        return m4319m();
    }

    @Inject
    kj() {
    }

    protected C1648a m4319m() {
        return this.f3065e;
    }
}
