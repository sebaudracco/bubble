package com.unit.three.p139a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class C4042b {
    private static C4042b f9327a;
    private BroadcastReceiver f9328b;
    private Map f9329c = new HashMap();

    public enum C4038a {
        EVENT_RECEIVED_HEART(C4040b.class),
        EVENT_RECONNECT(C4041d.class);
        
        private Class f9326c;

        static {
            EVENT_RECEIVED_HEART = new C4038a("EVENT_RECEIVED_HEART", 0, C4040b.class);
            EVENT_RECONNECT = new C4038a("EVENT_RECONNECT", 1, C4041d.class);
            C4038a[] c4038aArr = new C4038a[]{EVENT_RECEIVED_HEART, EVENT_RECONNECT};
        }

        private C4038a(Class cls) {
            this.f9326c = cls;
        }

        public final String m12460a() {
            return this.f9326c.getName();
        }
    }

    public interface C4039c {
    }

    public interface C4040b extends C4039c {
    }

    public interface C4041d extends C4039c {
        void mo6916a();
    }

    private C4042b() {
    }

    public static C4042b m12462a() {
        if (f9327a == null) {
            synchronized (C4042b.class) {
                if (f9327a == null) {
                    f9327a = new C4042b();
                }
            }
        }
        return f9327a;
    }

    public static void m12464a(Context context, C4038a c4038a, Intent intent) {
        try {
            intent.setAction(c4038a.m12460a());
            context.sendBroadcast(intent);
        } catch (Throwable th) {
        }
    }

    public final void m12465a(Context context) {
        try {
            this.f9328b = new C4043c(this);
            IntentFilter intentFilter = new IntentFilter(C4038a.EVENT_RECEIVED_HEART.m12460a());
            intentFilter.addAction(C4038a.EVENT_RECONNECT.m12460a());
            context.registerReceiver(this.f9328b, intentFilter);
        } catch (Throwable th) {
        }
    }

    public final void m12466a(C4041d c4041d) {
        List list;
        Class cls = C4041d.class;
        if (this.f9329c.containsKey(cls)) {
            list = (List) this.f9329c.get(cls);
        } else {
            list = new ArrayList();
            this.f9329c.put(cls, list);
        }
        list.add(c4041d);
    }

    public final void m12467b(Context context) {
        try {
            context.unregisterReceiver(this.f9328b);
        } catch (Throwable th) {
        }
    }
}
