package com.altbeacon.beacon.service;

import android.os.SystemClock;
import com.altbeacon.beacon.p013c.C0835d;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class C0874k implements C0871j {
    private static long f1757a = 20000;
    private ArrayList<C0873a> f1758b = new ArrayList();

    private class C0873a implements Comparable<C0873a> {
        Integer f1754a;
        long f1755b;
        final /* synthetic */ C0874k f1756c;

        private C0873a(C0874k c0874k) {
            this.f1756c = c0874k;
        }

        public int m1802a(C0873a c0873a) {
            return this.f1754a.compareTo(c0873a.f1754a);
        }

        public /* synthetic */ int compareTo(Object obj) {
            return m1802a((C0873a) obj);
        }
    }

    private synchronized void m1803c() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f1758b.iterator();
        while (it.hasNext()) {
            C0873a c0873a = (C0873a) it.next();
            if (SystemClock.elapsedRealtime() - c0873a.f1755b < f1757a) {
                arrayList.add(c0873a);
            }
        }
        this.f1758b = arrayList;
        Collections.sort(this.f1758b);
    }

    public void mo1884a(Integer num) {
        C0873a c0873a = new C0873a();
        c0873a.f1754a = num;
        c0873a.f1755b = SystemClock.elapsedRealtime();
        this.f1758b.add(c0873a);
    }

    public boolean mo1885a() {
        return this.f1758b.size() == 0;
    }

    public double mo1886b() {
        int i;
        int i2;
        m1803c();
        int size = this.f1758b.size();
        int i3 = size - 1;
        if (size > 2) {
            i = (size / 10) + 1;
            i2 = (size - (size / 10)) - 2;
        } else {
            i2 = i3;
            i = 0;
        }
        double d = 0.0d;
        for (int i4 = i; i4 <= i2; i4++) {
            d += (double) ((C0873a) this.f1758b.get(i4)).f1754a.intValue();
        }
        C0835d.m1657a("RunningAverageRssiFilter", "Running average mRssi based on %s measurements: %s", Integer.valueOf(size), Double.valueOf(d / ((double) ((i2 - i) + 1))));
        return d / ((double) ((i2 - i) + 1));
    }
}
