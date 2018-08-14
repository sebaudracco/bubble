package com.appnext.base.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.appnext.base.C1061b;
import com.appnext.base.p019a.p021b.C1021c;
import com.appnext.base.p023b.C1042c;
import com.appnext.base.p023b.C1043d;
import com.appnext.base.p023b.C1044e;
import com.appnext.base.p023b.C1057k;
import com.appnext.base.p023b.C1058l;
import com.appnext.base.receivers.C1078c;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReceiverService extends Service {
    private static final String ja = "com.appnext.base.receivers.imp";
    private List<C1078c> jb;

    class C10901 implements Runnable {
        final /* synthetic */ ReceiverService jc;

        C10901(ReceiverService receiverService) {
            this.jc = receiverService;
        }

        public void run() {
            try {
                List<C1021c> ct = C1044e.cs().ct();
                if (ct != null) {
                    for (C1021c c1021c : ct) {
                        if (c1021c != null && C1042c.jB.equalsIgnoreCase(c1021c.bc())) {
                            String key = c1021c.getKey();
                            if (!C1057k.m2170a(key, C1042c.jB, c1021c)) {
                                C1058l.m2184k(key, " *** No Permission ***");
                            } else if (C1042c.jF.equalsIgnoreCase(c1021c.ba())) {
                                this.jc.m2285b(c1021c);
                            }
                        }
                    }
                    this.jc.ck();
                }
            } catch (Throwable th) {
            }
        }
    }

    public void onCreate() {
        super.onCreate();
        C1043d.init(this);
        cg();
    }

    public void onDestroy() {
        cj();
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        return 1;
    }

    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void cg() {
        ch();
        ci();
    }

    private void ch() {
        this.jb = new ArrayList();
    }

    private void ci() {
        try {
            new Thread(new C10901(this)).start();
        } catch (Throwable th) {
            C1061b.m2191a(th);
        }
    }

    private void cj() {
        try {
            for (C1078c unregister : this.jb) {
                unregister.unregister();
            }
            this.jb.clear();
        } catch (Throwable th) {
        }
    }

    private void m2285b(C1021c c1021c) {
        if (c1021c != null) {
            try {
                this.jb.add((C1078c) Class.forName(getOperationClassName(c1021c.getKey())).getConstructor(new Class[0]).newInstance(new Object[0]));
            } catch (ClassNotFoundException e) {
            } catch (Throwable th) {
                C1061b.m2191a(th);
            }
        }
    }

    private void ck() {
        Iterator listIterator = this.jb.listIterator();
        while (listIterator.hasNext()) {
            if (!((C1078c) listIterator.next()).register()) {
                listIterator.remove();
            }
        }
    }

    public static String getOperationClassName(String str) {
        return "com.appnext.base.receivers.imp." + str;
    }
}
