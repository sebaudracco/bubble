package com.altbeacon.beacon.service.p009a;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothAdapter.LeScanCallback;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import com.altbeacon.beacon.p013c.C0835d;
import com.altbeacon.p010a.C0811b;

@TargetApi(18)
public class C0852c extends C0847b {
    private LeScanCallback f1702k;

    class C08481 implements Runnable {
        final /* synthetic */ C0852c f1694a;

        C08481(C0852c c0852c) {
            this.f1694a = c0852c;
        }

        public void run() {
            this.f1694a.m1714a(Boolean.valueOf(true));
        }
    }

    class C08514 implements LeScanCallback {
        final /* synthetic */ C0852c f1701a;

        C08514(C0852c c0852c) {
            this.f1701a = c0852c;
        }

        public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
            C0835d.m1657a("CycledLeScannerForJellyBeanMr2", "got record", new Object[0]);
            this.f1701a.h.mo1868a(bluetoothDevice, i, bArr);
            this.f1701a.g.m1508a(bluetoothDevice, this.f1701a.m1730o());
        }
    }

    public C0852c(Context context, long j, long j2, boolean z, C0804a c0804a, C0811b c0811b) {
        super(context, j, j2, z, c0804a, c0811b);
    }

    private void m1728m() {
        final BluetoothAdapter i = m1723i();
        if (i != null) {
            final LeScanCallback o = m1730o();
            this.f.removeCallbacksAndMessages(null);
            this.f.post(new Runnable(this) {
                final /* synthetic */ C0852c f1697c;

                public void run() {
                    try {
                        i.startLeScan(o);
                    } catch (Throwable e) {
                        C0835d.m1661b(e, "CycledLeScannerForJellyBeanMr2", "Internal Android exception in startLeScan()", new Object[0]);
                    }
                }
            });
        }
    }

    private void m1729n() {
        final BluetoothAdapter i = m1723i();
        if (i != null) {
            final LeScanCallback o = m1730o();
            this.f.removeCallbacksAndMessages(null);
            this.f.post(new Runnable(this) {
                final /* synthetic */ C0852c f1700c;

                public void run() {
                    try {
                        i.stopLeScan(o);
                    } catch (Throwable e) {
                        C0835d.m1661b(e, "CycledLeScannerForJellyBeanMr2", "Internal Android exception in stopLeScan()", new Object[0]);
                    }
                }
            });
        }
    }

    private LeScanCallback m1730o() {
        if (this.f1702k == null) {
            this.f1702k = new C08514(this);
        }
        return this.f1702k;
    }

    protected void mo1880d() {
        m1729n();
    }

    protected boolean mo1881e() {
        long j = 1000;
        long elapsedRealtime = this.a - SystemClock.elapsedRealtime();
        if (elapsedRealtime <= 0) {
            return false;
        }
        C0835d.m1657a("CycledLeScannerForJellyBeanMr2", "Waiting to start next Bluetooth scan for another %s milliseconds", Long.valueOf(elapsedRealtime));
        if (this.i) {
            m1724j();
        }
        Handler handler = this.e;
        Runnable c08481 = new C08481(this);
        if (elapsedRealtime <= 1000) {
            j = elapsedRealtime;
        }
        handler.postDelayed(c08481, j);
        return true;
    }

    protected void mo1882f() {
        m1728m();
    }

    protected void mo1883h() {
        m1729n();
        this.b = true;
    }
}
