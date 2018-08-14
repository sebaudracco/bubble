package com.unit.three.p139a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.unit.three.p139a.C4042b.C4038a;
import com.unit.three.p139a.C4042b.C4039c;
import com.unit.three.p139a.C4042b.C4041d;
import java.util.Iterator;
import java.util.List;

final class C4043c extends BroadcastReceiver {
    private /* synthetic */ C4042b f9330a;

    C4043c(C4042b c4042b) {
        this.f9330a = c4042b;
    }

    public final void onReceive(Context context, Intent intent) {
        try {
            String action = intent.getAction();
            List<C4039c> a = this.f9330a.f9329c.get(Class.forName(action));
            if (a != null) {
                if (C4038a.EVENT_RECEIVED_HEART.m12460a().equals(action)) {
                    intent.getIntExtra("extra", -1);
                    intent.getBooleanExtra("exception", false);
                    Iterator it = a.iterator();
                    while (it.hasNext()) {
                        it.next();
                    }
                } else if (C4038a.EVENT_RECONNECT.m12460a().equals(action)) {
                    for (C4039c c4039c : a) {
                        if (c4039c != null) {
                            ((C4041d) c4039c).mo6916a();
                        }
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
