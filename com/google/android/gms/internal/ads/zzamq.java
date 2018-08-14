package com.google.android.gms.internal.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;
import javax.annotation.concurrent.GuardedBy;

@zzadh
public final class zzamq {
    @GuardedBy("this")
    private final BroadcastReceiver zzcuc = new zzamr(this);
    @GuardedBy("this")
    private final Map<BroadcastReceiver, IntentFilter> zzcud = new WeakHashMap();
    private boolean zzcue;
    private boolean zzsh = false;
    private Context zzsz;

    private final synchronized void zzc(Context context, Intent intent) {
        for (Entry entry : this.zzcud.entrySet()) {
            if (((IntentFilter) entry.getValue()).hasAction(intent.getAction())) {
                ((BroadcastReceiver) entry.getKey()).onReceive(context, intent);
            }
        }
    }

    public final synchronized void initialize(Context context) {
        if (!this.zzsh) {
            this.zzsz = context.getApplicationContext();
            if (this.zzsz == null) {
                this.zzsz = context;
            }
            zznk.initialize(this.zzsz);
            this.zzcue = ((Boolean) zzkb.zzik().zzd(zznk.zzbcc)).booleanValue();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.USER_PRESENT");
            this.zzsz.registerReceiver(this.zzcuc, intentFilter);
            this.zzsh = true;
        }
    }

    public final synchronized void zza(Context context, BroadcastReceiver broadcastReceiver) {
        if (this.zzcue) {
            this.zzcud.remove(broadcastReceiver);
        } else {
            context.unregisterReceiver(broadcastReceiver);
        }
    }

    public final synchronized void zza(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        if (this.zzcue) {
            this.zzcud.put(broadcastReceiver, intentFilter);
        } else {
            context.registerReceiver(broadcastReceiver, intentFilter);
        }
    }
}
