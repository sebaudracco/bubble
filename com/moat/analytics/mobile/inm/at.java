package com.moat.analytics.mobile.inm;

import android.util.Log;
import java.util.Iterator;

class at implements aw {
    final /* synthetic */ ar f8553a;

    at(ar arVar) {
        this.f8553a = arVar;
    }

    public void mo6485a(aq aqVar) {
        if (ar.f8549c != aqVar) {
            synchronized (ar.f8548b) {
                if (aqVar == aq.ON && ar.f8550d) {
                    Log.d("MoatOnOff", "Moat enabled - Version 1.7.11");
                }
                ar.f8549c = aqVar;
                Iterator it = ar.f8548b.iterator();
                while (it.hasNext()) {
                    ap apVar = (ap) it.next();
                    if (aqVar == aq.ON) {
                        apVar.mo6486a();
                    } else {
                        apVar.mo6487b();
                    }
                    it.remove();
                }
            }
        }
        this.f8553a.m11530g();
    }
}
