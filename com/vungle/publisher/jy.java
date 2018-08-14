package com.vungle.publisher;

import android.database.Cursor;
import android.support.v4.app.NotificationCompat;
import com.vungle.publisher.dp.C1592a;
import com.vungle.publisher.eh.a;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class jy extends eh {
    @Inject
    C1645a f3053d;

    @Singleton
    /* compiled from: vungle */
    public static class C1645a extends a<jy, vu, wr> {
        @Inject
        Provider<jy> f3052a;

        public /* bridge */ /* synthetic */ int m4272a(List list) {
            return super.a(list);
        }

        protected /* synthetic */ dp[] m4281d(int i) {
            return m4280c(i);
        }

        protected /* synthetic */ dp g_() {
            return m4275a();
        }

        @Inject
        C1645a() {
        }

        Map<jf, List<jy>> m4278a(String str, vu vuVar) {
            if (vuVar == null) {
                return null;
            }
            Map<jf, List<jy>> hashMap = new HashMap();
            for (String str2 : vuVar.m4764c()) {
                a(str, hashMap, new hf(str2), vuVar.m4763a(str2));
            }
            return hashMap;
        }

        protected jy m4276a(jy jyVar, Cursor cursor, boolean z) {
            super.a(jyVar, cursor, z);
            jyVar.c = new hf(ce.e(cursor, NotificationCompat.CATEGORY_EVENT));
            return jyVar;
        }

        protected jy m4275a() {
            return (jy) this.f3052a.get();
        }

        protected jy[] m4280c(int i) {
            return new jy[i];
        }
    }

    protected /* synthetic */ C1592a b_() {
        return m4282e();
    }

    @Inject
    jy() {
    }

    protected C1645a m4282e() {
        return this.f3053d;
    }
}
