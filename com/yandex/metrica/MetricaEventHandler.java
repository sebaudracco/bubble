package com.yandex.metrica;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.yandex.metrica.impl.bi;
import com.yandex.metrica.impl.bo;
import com.yandex.metrica.impl.utils.C4529j;
import java.util.HashSet;
import java.util.Set;

public final class MetricaEventHandler extends BroadcastReceiver {
    public static final Set<BroadcastReceiver> f11408a = new HashSet();

    static void m14294a(BroadcastReceiver... broadcastReceiverArr) {
        for (Object add : broadcastReceiverArr) {
            f11408a.add(add);
        }
    }

    public void onReceive(Context context, Intent intent) {
        if ("com.android.vending.INSTALL_REFERRER".equals(intent.getAction())) {
            String stringExtra = intent.getStringExtra("referrer");
            if (!bi.m14957a(stringExtra)) {
                bo.m15017b(context).m15032b(stringExtra);
            }
        }
        for (BroadcastReceiver broadcastReceiver : f11408a) {
            C4529j.m16280f().m16243a(String.format("Sending referrer to %s", new Object[]{broadcastReceiver.getClass().getName()}));
            broadcastReceiver.onReceive(context, intent);
        }
    }
}
