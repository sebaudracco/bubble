package com.appnext.base.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.appnext.base.C1061b;
import com.appnext.base.p019a.p021b.C1020b;
import com.appnext.base.p023b.C1042c.C1041a;
import com.appnext.base.p023b.C1043d;
import com.appnext.base.p023b.C1048i;
import com.appnext.base.p023b.C1057k;
import java.util.List;

public abstract class C1079a extends BroadcastReceiver implements C1078c {
    public abstract IntentFilter getBRFilter();

    public void onReceive(Context context, Intent intent) {
        if (context != null) {
            try {
                C1043d.init(context);
                C1048i.cy().init(C1043d.getContext());
            } catch (Throwable th) {
                C1061b.m2191a(th);
            }
        }
    }

    protected void m2248a(String str, String str2, C1041a c1041a) {
        C1057k.m2175d(str, str2, c1041a);
    }

    protected void m2249a(String str, List<C1020b> list) {
        C1057k.m2172b(str, (List) list);
    }

    protected void m2250b(String str, String str2, C1041a c1041a) {
        Object aG = C1057k.aG(str);
        if (TextUtils.isEmpty(aG) || !aG.equalsIgnoreCase(str2)) {
            m2248a(str, str2, c1041a);
            C1057k.m2178j(str, str2);
        }
    }

    public boolean hasPermission() {
        return false;
    }

    public boolean register() {
        IntentFilter bRFilter = getBRFilter();
        if (bRFilter == null) {
            return false;
        }
        C1043d.getContext().registerReceiver(this, bRFilter);
        return true;
    }

    public void unregister() {
        C1043d.getContext().unregisterReceiver(this);
    }
}
