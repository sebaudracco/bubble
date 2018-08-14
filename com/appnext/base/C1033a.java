package com.appnext.base;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import com.appnext.base.operations.C1066d;
import com.appnext.base.p019a.p021b.C1021c;
import com.appnext.base.p023b.C1042c;
import com.appnext.base.p023b.C1043d;
import com.appnext.base.p023b.C1044e;
import com.appnext.base.p023b.C1048i;
import com.appnext.base.p023b.C1057k;
import com.appnext.base.p023b.C1058l;
import com.appnext.base.services.OperationService;
import com.appnext.base.services.ReceiverService;
import java.util.List;

public class C1033a {
    public static final String TAG = C1033a.class.getSimpleName();
    private static volatile C1033a fL;

    public static C1033a aI() {
        if (fL == null) {
            synchronized (C1033a.class) {
                if (fL == null) {
                    fL = new C1033a();
                }
            }
        }
        return fL;
    }

    private C1033a() {
    }

    public void m2113a(List<C1021c> list) {
        for (C1021c c1021c : list) {
            if (c1021c != null) {
                Object bf = c1021c.bf();
                C1058l.m2184k(" *** stop *** ", bf);
                if (!TextUtils.isEmpty(bf)) {
                    PendingIntent service = PendingIntent.getService(C1043d.getContext(), bf.hashCode(), new Intent(C1043d.getContext(), OperationService.class), 134217728);
                    if (service != null) {
                        ((AlarmManager) C1043d.getContext().getSystemService(NotificationCompat.CATEGORY_ALARM)).cancel(service);
                    }
                }
            }
        }
        C1043d.getContext().stopService(new Intent(C1043d.getContext(), OperationService.class));
        C1043d.getContext().stopService(new Intent(C1043d.getContext(), ReceiverService.class));
        C1066d.bG().bH();
    }

    public void aJ() {
        aL();
        aK();
    }

    private void aK() {
        try {
            List<C1021c> ct = C1044e.cs().ct();
            if (ct != null) {
                for (C1021c c1021c : ct) {
                    if (!(c1021c == null || C1042c.jB.equalsIgnoreCase(c1021c.bc()))) {
                        String key = c1021c.getKey();
                        if (C1057k.m2170a(key, C1042c.jE, c1021c)) {
                            Intent intent = new Intent(C1043d.getContext(), OperationService.class);
                            if (C1042c.jF.equalsIgnoreCase(c1021c.ba())) {
                                long i = C1057k.m2177i(c1021c.bb(), c1021c.bc());
                                if (!"time".equalsIgnoreCase(c1021c.bc())) {
                                    long j = C1048i.cy().getLong(key + C1048i.ke, 0);
                                    i += j;
                                    if (!c1021c.be().equalsIgnoreCase(C1042c.jD)) {
                                        C1057k.m2167a(C1043d.getContext(), OperationService.class, Math.max(i, System.currentTimeMillis()), c1021c);
                                    } else if (j == 0) {
                                        intent.putExtra(C1042c.jv, key);
                                        C1057k.m2166a(C1043d.getContext(), intent);
                                    }
                                } else if (i > 0) {
                                    C1057k.m2167a(C1043d.getContext(), OperationService.class, i, c1021c);
                                }
                            }
                        } else {
                            C1058l.m2184k(key, " *** No Permission ***");
                        }
                    }
                }
            }
        } catch (Throwable th) {
            C1061b.m2191a(th);
            C1058l.m2184k(TAG, th.toString());
        }
    }

    private void aL() {
        C1057k.m2166a(C1043d.getContext(), new Intent(C1043d.getContext(), ReceiverService.class));
    }

    public void m2112a(C1021c c1021c) {
        new Intent(C1043d.getContext(), OperationService.class).putExtra(C1042c.jv, c1021c.getKey());
        C1057k.m2167a(C1043d.getContext(), OperationService.class, C1057k.m2177i(c1021c.bb(), c1021c.bc()) + System.currentTimeMillis(), c1021c);
    }
}
