package com.appnext.core;

import android.os.Handler;
import com.appnext.core.C0919c.C0914a;
import java.util.ArrayList;
import java.util.Iterator;

public class C1104a {
    private ArrayList<?> aL = null;
    private Long kK = Long.valueOf(0);
    private ArrayList<C0914a> kL = new ArrayList();
    private String placementID;
    private int state = 0;

    public void m2300a(C0914a c0914a) {
        if (c0914a != null) {
            this.kL.add(c0914a);
        }
    }

    public void m2299a(C1104a c1104a) {
        if (c1104a != null && c1104a.kL != null) {
            this.kL.addAll(c1104a.kL);
        }
    }

    public void m2303b(C0914a c0914a) {
        if (c0914a != null) {
            this.kL.remove(c0914a);
        }
    }

    public Long cK() {
        return this.kK;
    }

    public ArrayList<?> cL() {
        if (this.aL == null) {
            return null;
        }
        ArrayList<?> arrayList = new ArrayList();
        Iterator it = this.aL.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    public void setPlacementID(String str) {
        this.placementID = str;
    }

    public int getState() {
        return this.state;
    }

    public String getPlacementID() {
        return this.placementID;
    }

    public void setState(int i) {
        this.state = i;
    }

    public void m2301a(Long l) {
        this.kK = l;
    }

    public void m2304h(ArrayList<?> arrayList) {
        m2302a(arrayList, true);
    }

    public void m2302a(ArrayList<?> arrayList, boolean z) {
        this.aL = arrayList;
        if (z) {
            m2301a(Long.valueOf(System.currentTimeMillis()));
        }
    }

    public synchronized void m2305i(final ArrayList<?> arrayList) {
        new Handler().post(new Runnable(this) {
            final /* synthetic */ C1104a kN;

            public void run() {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(this.kN.kL);
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    C0914a c0914a = (C0914a) it.next();
                    if (c0914a != null) {
                        c0914a.mo1971a(arrayList);
                    }
                }
                this.kN.kL.clear();
            }
        });
    }

    public synchronized void aI(final String str) {
        new Handler().post(new Runnable(this) {
            final /* synthetic */ C1104a kN;

            public void run() {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(this.kN.kL);
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    C0914a c0914a = (C0914a) it.next();
                    if (c0914a != null) {
                        c0914a.error(str);
                    }
                }
                this.kN.kL.clear();
            }
        });
    }
}
