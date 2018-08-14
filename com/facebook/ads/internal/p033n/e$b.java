package com.facebook.ads.internal.p033n;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import com.facebook.ads.internal.n.e;
import java.util.HashMap;
import java.util.Map;

class e$b extends BroadcastReceiver {
    final /* synthetic */ e f4800a;
    private boolean f4801b;

    private e$b(e eVar) {
        this.f4800a = eVar;
    }

    public void m6470a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.facebook.ads.native.impression:" + e.r(this.f4800a));
        intentFilter.addAction("com.facebook.ads.native.click:" + e.r(this.f4800a));
        LocalBroadcastManager.getInstance(e.o(this.f4800a)).registerReceiver(this, intentFilter);
        this.f4801b = true;
    }

    public void m6471b() {
        if (this.f4801b) {
            try {
                LocalBroadcastManager.getInstance(e.o(this.f4800a)).unregisterReceiver(this);
            } catch (Exception e) {
            }
        }
    }

    public void onReceive(Context context, Intent intent) {
        Object obj = intent.getAction().split(":")[0];
        if ("com.facebook.ads.native.impression".equals(obj) && e.h(this.f4800a) != null) {
            e.h(this.f4800a).m5670a();
        } else if ("com.facebook.ads.native.click".equals(obj) && this.f4800a.a != null) {
            Map hashMap = new HashMap();
            hashMap.put("mil", String.valueOf(true));
            this.f4800a.a.mo3640b(hashMap);
        }
    }
}
