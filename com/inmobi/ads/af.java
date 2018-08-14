package com.inmobi.ads;

import android.media.MediaPlayer;

/* compiled from: NativeStrandMediaPlayer */
public class af extends MediaPlayer {
    private static final Object f7064d = new Object();
    private static af f7065e;
    private static int f7066f = 0;
    private int f7067a = 0;
    private int f7068b = 0;
    private af f7069c;

    public static af m9278a() {
        synchronized (f7064d) {
            if (f7065e != null) {
                af afVar = f7065e;
                f7065e = afVar.f7069c;
                afVar.f7069c = null;
                f7066f--;
                return afVar;
            }
            return new af();
        }
    }

    public void m9281a(int i) {
        this.f7067a = i;
    }

    public int m9282b() {
        return this.f7067a;
    }

    public void m9283b(int i) {
        this.f7068b = i;
    }

    public int m9284c() {
        return this.f7068b;
    }

    public void m9285d() {
        if (!m9280f()) {
            m9279e();
        }
    }

    private void m9279e() {
        synchronized (f7064d) {
            if (f7066f < 5) {
                this.f7069c = f7065e;
                f7065e = this;
                f7066f++;
            }
        }
    }

    private boolean m9280f() {
        return 3 == m9282b();
    }
}
