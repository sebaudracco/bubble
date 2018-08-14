package com.bgjd.ici.p029d;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.bgjd.ici.json.JSONException;
import com.bgjd.ici.json.JSONObject;
import com.bgjd.ici.p025b.C1395h;
import com.bgjd.ici.p025b.C1408j.C1407c.C1406b;
import com.bgjd.ici.p025b.C1409k;

public class C1453d extends C1409k<JSONObject> {
    private JSONObject f2239f;

    class C14521 extends BroadcastReceiver {
        final /* synthetic */ C1453d f2238a;

        C14521(C1453d c1453d) {
            this.f2238a = c1453d;
        }

        public void onReceive(Context context, Intent intent) {
            boolean z = true;
            context.unregisterReceiver(this);
            int intExtra = intent.getIntExtra("plugged", -1);
            Object stringExtra = intent.getStringExtra("technology");
            boolean z2 = intExtra == 2;
            if (intExtra != 1) {
                z = false;
            }
            if (this.f2238a.f2239f.has("d")) {
                try {
                    if (this.f2238a.f2239f.getString("d").length() == 0) {
                        this.f2238a.f2239f.put("a", intExtra);
                        this.f2238a.f2239f.put("b", z2);
                        this.f2238a.f2239f.put("c", z);
                        this.f2238a.f2239f.put("d", stringExtra);
                    }
                } catch (JSONException e) {
                }
            }
        }
    }

    public /* synthetic */ Object mo2325d() {
        return m2992f();
    }

    public C1453d(C1395h c1395h) {
        super(c1395h);
        this.f2239f = null;
        this.b = C1406b.f2158h;
        try {
            this.f2239f = new JSONObject("{\"a\":false,\"b\":false,\"c\":false,\"d\":\"\"}");
            c1395h.getContext().registerReceiver(new C14521(this), new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        } catch (JSONException e) {
        }
    }

    public JSONObject m2992f() {
        return this.f2239f;
    }
}
