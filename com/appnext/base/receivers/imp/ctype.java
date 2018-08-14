package com.appnext.base.receivers.imp;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import com.appnext.base.C1061b;
import com.appnext.base.p019a.p021b.C1020b;
import com.appnext.base.p019a.p021b.C1021c;
import com.appnext.base.p023b.C1042c;
import com.appnext.base.p023b.C1042c.C1041a;
import com.appnext.base.p023b.C1043d;
import com.appnext.base.p023b.C1044e;
import com.appnext.base.p023b.C1045f;
import com.appnext.base.p023b.C1057k;
import com.appnext.base.p023b.C1058l;
import com.appnext.base.receivers.C1079a;
import java.util.ArrayList;
import java.util.List;

public class ctype extends C1079a {
    private static final String KEY = ctype.class.getSimpleName();

    public IntentFilter getBRFilter() {
        try {
            if (hasPermission()) {
                return new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
            }
            return null;
        } catch (Throwable th) {
            C1061b.m2191a(th);
            return null;
        }
    }

    public boolean hasPermission() {
        return C1045f.m2133g(C1043d.getContext().getApplicationContext(), "android.permission.ACCESS_NETWORK_STATE");
    }

    public void onReceive(Context context, Intent intent) {
        try {
            super.onReceive(context, intent);
            C1058l.m2184k("Receiver", KEY);
            if ("android.net.conn.CONNECTIVITY_CHANGE".equalsIgnoreCase(intent.getAction()) && hasPermission()) {
                C1021c av = C1044e.cs().av(KEY);
                if (av == null || C1042c.jG.equalsIgnoreCase(av.ba())) {
                    C1057k.m2178j(KEY, "");
                    return;
                }
                NetworkInfo j = C1045f.m2136j(C1043d.getContext());
                if (j != null && j.isConnected()) {
                    List arrayList = new ArrayList();
                    String str = "";
                    String typeName = j.getTypeName();
                    if (!typeName.isEmpty()) {
                        arrayList.add(new C1020b(KEY, "ctype", typeName, C1041a.String.getType()));
                        str = str + typeName;
                    }
                    String subtypeName = j.getSubtypeName();
                    if (!subtypeName.isEmpty()) {
                        arrayList.add(new C1020b(KEY, "mctype", subtypeName, C1041a.String.getType()));
                        str = str + subtypeName;
                    }
                    subtypeName = C1057k.aG(KEY);
                    C1057k.m2178j(KEY, str);
                    if (!arrayList.isEmpty()) {
                        if ((subtypeName != null && !str.equals(subtypeName)) || subtypeName == null) {
                            m2249a(KEY, arrayList);
                        }
                    }
                }
            }
        } catch (Throwable th) {
            C1061b.m2191a(th);
        }
    }

    protected String m2256a(NetworkInfo networkInfo) {
        return networkInfo.getTypeName();
    }
}
