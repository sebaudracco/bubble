package com.appnext.base.receivers.imp;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.appnext.base.C1061b;
import com.appnext.base.p019a.C1017a;
import com.appnext.base.p019a.p021b.C1020b;
import com.appnext.base.p019a.p021b.C1021c;
import com.appnext.base.p023b.C1042c.C1041a;
import com.appnext.base.p023b.C1058l;
import com.appnext.base.receivers.C1079a;
import java.util.Calendar;
import java.util.List;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class scron extends C1079a {
    public static final String hl = scron.class.getSimpleName();
    public static final String iI = (scron.class.getSimpleName() + "temp");
    private static final String iJ = "0500";
    private static final String iK = "0900";
    private static final int iL = 3;
    private static final String iM = "max";
    private static final String iN = "start";
    private static final String iO = "end";
    private C1021c gP = C1017a.aM().aR().ab(hl);
    private long iP;
    private long iQ;
    private int iR;

    public scron() {
        cc();
        cd();
    }

    public boolean hasPermission() {
        return true;
    }

    public IntentFilter getBRFilter() {
        try {
            if (!hasPermission()) {
                return null;
            }
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            return intentFilter;
        } catch (Throwable th) {
            C1061b.m2191a(th);
            return null;
        }
    }

    public void onReceive(Context context, final Intent intent) {
        super.onReceive(context, intent);
        C1058l.m2184k("Receiver", hl);
        new Thread(new Runnable(this) {
            final /* synthetic */ scron iS;

            public void run() {
                try {
                    if (this.iS.cb()) {
                        if ("android.intent.action.SCREEN_ON".equals(intent.getAction())) {
                            this.iS.m2248a(scron.hl, SchemaSymbols.ATTVAL_TRUE, C1041a.Boolean);
                            C1017a.aM().aP().m2093a(new C1020b(scron.iI, SchemaSymbols.ATTVAL_TRUE, C1041a.Boolean.getType()));
                        }
                    }
                } catch (Throwable th) {
                    C1061b.m2191a(th);
                }
            }
        }).start();
    }

    private boolean cb() {
        return cf() && !ce();
    }

    private void cc() {
        String str = iJ;
        String str2 = iK;
        if (this.gP != null) {
            str = this.gP.m2066e("start", iJ);
            str2 = this.gP.m2066e("end", iK);
        }
        this.iP = m2276g(str, iJ);
        this.iQ = m2276g(str2, iK);
    }

    private long m2276g(String str, String str2) {
        if (str != null && str.length() == 4 && str.matches("[0-9]+")) {
            str2 = str;
        }
        return m2275b(Integer.valueOf(str2.substring(0, 2)).intValue(), Integer.valueOf(str2.substring(2, 4)).intValue());
    }

    private long m2275b(int i, int i2) {
        Calendar instance = Calendar.getInstance();
        instance.set(11, i);
        instance.set(12, i2);
        instance.set(13, 0);
        return instance.getTimeInMillis();
    }

    private void cd() {
        this.iR = 3;
        if (this.gP != null) {
            this.iR = this.gP.m2065b(iM, 3);
        }
    }

    private boolean ce() {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(this.iP);
        long timeInMillis = instance.getTimeInMillis();
        List c = C1017a.aM().aP().m2098c(iI, timeInMillis);
        C1017a.aM().aP().m2095b(iI, timeInMillis);
        return c != null && c.size() >= this.iR;
    }

    private boolean cf() {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(System.currentTimeMillis());
        long timeInMillis = instance.getTimeInMillis();
        return timeInMillis >= this.iP && timeInMillis <= this.iQ;
    }
}
