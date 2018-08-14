package com.altbeacon.beacon.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.altbeacon.beacon.p013c.C0835d;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class C0862a implements Serializable {
    private transient Intent f1725a;
    private String f1726b;

    public C0862a(String str) {
        this.f1726b = str;
        m1750a();
    }

    private void m1750a() {
        if (this.f1726b != null) {
            this.f1725a = new Intent();
            this.f1725a.setComponent(new ComponentName(this.f1726b, "com.altbeacon.beacon.BeaconIntentProcessor"));
        }
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        m1750a();
    }

    public boolean m1751a(Context context, String str, Bundle bundle) {
        if (this.f1725a == null) {
            m1750a();
        }
        if (this.f1725a != null) {
            C0835d.m1657a("Callback", "attempting callback via intent: %s", this.f1725a.getComponent());
            this.f1725a.putExtra(str, bundle);
            try {
                context.startService(this.f1725a);
                return true;
            } catch (Exception e) {
                C0835d.m1663d("Callback", "Failed attempting to start service: " + this.f1725a.getComponent().flattenToString(), e);
            }
        }
        return false;
    }
}
