package com.vungle.publisher;

import android.database.Cursor;
import android.support.v4.app.NotificationCompat;
import com.vungle.publisher.da.a;

/* compiled from: vungle */
public abstract class jl<P extends jj<?, P, ?>> extends da<P> {

    /* compiled from: vungle */
    public enum C4227a implements jf {
        back,
        close,
        f10561c,
        download,
        cta,
        muted,
        unmuted,
        videoerror,
        videoreset,
        volume,
        volumedown,
        volumeup;
        
        private final String f10572m;

        private C4227a(String str) {
            this.f10572m = str;
        }

        public String toString() {
            return this.f10572m == null ? name() : this.f10572m;
        }

        public boolean mo6944a() {
            return false;
        }
    }

    /* compiled from: vungle */
    protected static abstract class C4228b<E extends jl<P>, P extends jj<?, P, E>> extends a<P, E> {
        protected C4228b() {
        }

        protected jf m13537a(Cursor cursor) {
            return (jf) ce.a(cursor, NotificationCompat.CATEGORY_EVENT, C4227a.class);
        }
    }
}
