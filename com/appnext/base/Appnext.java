package com.appnext.base;

import android.annotation.SuppressLint;
import android.content.Context;
import com.appnext.base.p019a.p020a.C1016a;
import com.appnext.base.p023b.C1042c.C1041a;
import com.appnext.base.p023b.C1043d;
import com.appnext.base.p023b.C1046g;
import com.appnext.base.p023b.C1048i;
import com.appnext.base.p023b.C1057k;
import com.appnext.base.services.OperationService;
import com.appnext.base.services.ReceiverService;
import com.appnext.core.C1128g;

public class Appnext {
    @SuppressLint({"StaticFieldLeak"})
    private static final Appnext fI = new Appnext();
    private Context fH = null;
    private boolean fJ = false;

    class C10131 implements Runnable {
        final /* synthetic */ Appnext fK;

        C10131(Appnext appnext) {
            this.fK = appnext;
        }

        public void run() {
            try {
                C1057k.m2176e("aidForSend", C1128g.m2358u(C1043d.getContext()), C1041a.String);
                C1048i.cy().init(this.fK.fH);
                if (C1057k.m2183q(this.fK.fH)) {
                    this.fK.fJ = false;
                    C1048i.cy().putBoolean(C1048i.ko, true);
                    return;
                }
                C1043d.init(this.fK.fH);
                C1048i.cy().init(this.fK.fH);
                this.fK.aH();
                C1016a.m2060g(this.fK.fH);
                C1057k.m2181o(this.fK.fH);
            } catch (Throwable th) {
                C1061b.m2191a(th);
            }
        }
    }

    private Appnext() {
    }

    protected static Appnext aG() {
        return fI;
    }

    public static void init(Context context) {
        aG().m2059e(context);
    }

    private void m2059e(Context context) throws ExceptionInInitializerError {
        if (context == null) {
            throw new ExceptionInInitializerError("Cannot init Appnext with null context");
        } else if (!this.fJ || this.fH == null) {
            this.fJ = true;
            C1043d.init(context);
            this.fH = context.getApplicationContext();
            if (C1057k.m2169a(OperationService.class) && C1057k.m2169a(ReceiverService.class)) {
                C1046g.cw().m2141b(new C10131(this));
            }
        } else {
            this.fH = context.getApplicationContext();
        }
    }

    private void aH() {
        String u = C1128g.m2358u(this.fH);
        if (!u.equals(C1048i.cy().getString(C1048i.ki, ""))) {
            C1048i.cy().clear();
            C1048i.cy().putString(C1048i.ki, u);
        }
    }
}
