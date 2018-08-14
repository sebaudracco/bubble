package com.elephant.data.p035a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import com.elephant.data.p035a.p036a.C1719h;
import com.elephant.data.p037d.p042a.C1742a;
import com.elephant.data.p044e.p045a.C1780d;
import com.elephant.data.p044e.p045a.C1781e;
import com.elephant.data.p046f.p047a.C1800g;
import com.elephant.data.p048g.C1814b;
import com.elephant.data.p048g.C1816d;
import com.elephant.data.p048g.C1821i;
import com.elephant.data.p048g.p050b.C1813b;

public class C1727g extends BroadcastReceiver {
    public static final String f3541a = C1814b.kk;
    public static final String f3542b = C1814b.kl;
    private static String f3543c;
    private static boolean f3544d = true;

    static {
        String str = C1814b.kj;
    }

    private void m4980a(Context context, String str) {
        C1821i.m5346a(new C1729i(this, context, str));
    }

    static /* synthetic */ void m4981a(C1727g c1727g, Context context, String str) {
        C1816d.m5311g(context, str);
        C1719h.m4963a(context.getApplicationContext(), str, 0);
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (f3541a.equals(action) || f3542b.equals(action)) {
            C1800g.m5175a(context).m5176a();
            if (!TextUtils.isEmpty(C1813b.m5252a(context))) {
                Uri data = intent.getData();
                if (data != null) {
                    String schemeSpecificPart = data.getSchemeSpecificPart();
                    if (action.equals(f3542b)) {
                        try {
                            f3543c = new C1742a(System.currentTimeMillis(), schemeSpecificPart).toString();
                            f3544d = true;
                            try {
                                new Handler().postDelayed(new C1728h(this, context, schemeSpecificPart), 20000);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    } else if (action.equals(f3541a)) {
                        try {
                            f3544d = false;
                            long currentTimeMillis = System.currentTimeMillis();
                            long j = 0;
                            C1742a a = C1742a.m5013a(f3543c);
                            if (a != null && schemeSpecificPart.equals(a.m5015b())) {
                                j = a.m5014a();
                            }
                            if (currentTimeMillis - j < 30000) {
                                m4980a(context, schemeSpecificPart);
                                C1781e.m5131a(context.getApplicationContext(), "", C1780d.UPDATEAPP.m5130a(), C1814b.kn, schemeSpecificPart);
                                return;
                            }
                            C1813b.m5253a(context, C1813b.m5270g(context) + 1);
                            m4980a(context, schemeSpecificPart);
                            C1781e.m5131a(context.getApplicationContext(), "", C1780d.INSTALLAPP.m5130a(), C1814b.ko, schemeSpecificPart);
                        } catch (Exception e22) {
                            e22.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
