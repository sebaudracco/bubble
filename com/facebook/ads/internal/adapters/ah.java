package com.facebook.ads.internal.adapters;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import com.facebook.ads.internal.view.C2380n;
import com.facebook.ads.internal.view.p053e.p054b.C2229b;
import com.facebook.ads.internal.view.p053e.p054b.C2231f;
import com.facebook.ads.internal.view.p053e.p054b.C2232g;
import com.facebook.ads.internal.view.p053e.p054b.C2233h;
import com.facebook.ads.internal.view.p053e.p054b.C2234j;
import com.facebook.ads.internal.view.p053e.p054b.C2238p;
import com.facebook.ads.internal.view.p053e.p083a.C2222a;
import java.io.Serializable;

public class ah extends BroadcastReceiver {
    private Context f4201a;
    private C2380n f4202b;
    private boolean f4203c = false;

    public ah(C2380n c2380n, Context context) {
        this.f4202b = c2380n;
        this.f4201a = context.getApplicationContext();
    }

    public void m5749a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.facebook.ads.interstitial.displayed:" + this.f4202b.getUniqueId());
        intentFilter.addAction("videoInterstitalEvent:" + this.f4202b.getUniqueId());
        intentFilter.addAction("performCtaClick:" + this.f4202b.getUniqueId());
        LocalBroadcastManager.getInstance(this.f4201a).registerReceiver(this, intentFilter);
    }

    public void m5750b() {
        try {
            LocalBroadcastManager.getInstance(this.f4201a).unregisterReceiver(this);
        } catch (Exception e) {
        }
    }

    public void onReceive(Context context, Intent intent) {
        String[] split = intent.getAction().split(":");
        if (split.length != 2 || !split[1].equals(this.f4202b.getUniqueId())) {
            return;
        }
        if (split[0].equals("com.facebook.ads.interstitial.displayed")) {
            if (this.f4202b.getListener() != null) {
                this.f4202b.getListener().mo3578g();
                this.f4202b.getListener().mo3572a();
            }
        } else if (split[0].equals("videoInterstitalEvent")) {
            Serializable serializableExtra = intent.getSerializableExtra(NotificationCompat.CATEGORY_EVENT);
            if (serializableExtra instanceof C2238p) {
                if (this.f4202b.getListener() != null) {
                    this.f4202b.getListener().mo3577f();
                    this.f4202b.getListener().mo3572a();
                }
                if (this.f4203c) {
                    this.f4202b.m7102a(1);
                } else {
                    this.f4202b.m7102a(((C2238p) serializableExtra).m7087b());
                }
                this.f4202b.setVisibility(0);
                this.f4202b.m7104a(C2222a.USER_STARTED);
            } else if (serializableExtra instanceof C2231f) {
                if (this.f4202b.getListener() != null) {
                    this.f4202b.getListener().mo3575d();
                }
            } else if (serializableExtra instanceof C2232g) {
                if (this.f4202b.getListener() != null) {
                    this.f4202b.getListener().mo3576e();
                }
            } else if (serializableExtra instanceof C2229b) {
                if (this.f4202b.getListener() != null) {
                    this.f4202b.getListener().mo3579h();
                }
                this.f4203c = true;
            } else if (serializableExtra instanceof C2234j) {
                if (this.f4202b.getListener() != null) {
                    this.f4202b.getListener().mo3574c();
                }
                this.f4203c = false;
            } else if ((serializableExtra instanceof C2233h) && this.f4202b.getListener() != null) {
                this.f4202b.getListener().mo3573b();
            }
        } else if (split[0].equals("performCtaClick")) {
            this.f4202b.m7524b();
        }
    }
}
