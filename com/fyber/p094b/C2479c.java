package com.fyber.p094b;

import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import com.fyber.Fyber;
import com.fyber.utils.C2646d;
import com.fyber.utils.C2657h;
import com.fyber.utils.C2664l;
import com.fyber.utils.C2672t;
import com.fyber.utils.FyberLogger;
import com.fyber.utils.StringUtils;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

/* compiled from: EventNetworkOperation */
public abstract class C2479c extends C2478g<Void> {
    final String f6214a;

    /* compiled from: EventNetworkOperation */
    public static abstract class C2476a<T extends C2479c, U extends C2476a> {
        protected final String f6206a;
        protected final C2672t f6207b;
        StringBuilder f6208c = new StringBuilder();
        String f6209d = "";

        protected abstract T mo3896a();

        protected abstract U mo3897b();

        public C2476a(@NonNull String str, @NonNull String str2) {
            this.f6206a = str;
            this.f6207b = C2672t.m8534a(C2646d.m8469a(str2), Fyber.getConfigs().m7608i()).m8537a().m8542b().m8539a(NotificationCompat.CATEGORY_EVENT, str);
        }

        public final U m7861a(Map<String, String> map) {
            if (C2664l.m8521b(map)) {
                this.f6207b.m8540a((Map) map);
                this.f6209d += "\n\t\tAdditional parameters:\n\t\t\t" + TextUtils.join("\n\t\t\t", map.entrySet());
            }
            return mo3897b();
        }

        public final U m7860a(String str) {
            if (StringUtils.notNullNorEmpty(str)) {
                this.f6209d += "\n\t\tEvent attribute: " + str;
                this.f6207b.m8539a(this.f6206a, str);
            }
            return mo3897b();
        }

        protected T mo3898c() {
            this.f6208c.insert(0, String.format(Locale.ENGLISH, "Notifying tracker of event=%s", new Object[]{this.f6206a})).append(this.f6209d);
            return mo3896a();
        }
    }

    protected /* synthetic */ Object mo3899a(C2657h c2657h) throws Exception {
        return mo3906b(c2657h);
    }

    protected /* synthetic */ Object mo3901b(IOException iOException) {
        return mo3905a(iOException);
    }

    protected C2479c(C2476a c2476a) {
        super(c2476a.f6207b);
        this.f6214a = c2476a.f6208c.toString();
    }

    protected boolean a_() {
        FyberLogger.m8448d(b_(), this.f6214a);
        return true;
    }

    public void mo3907b() {
        if (Fyber.getConfigs().m7607h()) {
            Fyber.getConfigs().m7600a((Runnable) this);
        } else {
            FyberLogger.m8448d(b_(), "It appears that Fyber SDK has not been started yet.");
        }
    }

    protected Void mo3905a(IOException iOException) {
        FyberLogger.m8449e(b_(), "An exception occurred when trying to send the tracking event: " + iOException);
        return null;
    }

    protected Void mo3906b(C2657h c2657h) throws IOException {
        FyberLogger.m8448d(b_(), "Event communication successful - " + (c2657h.m8464b() == 200));
        return null;
    }
}
