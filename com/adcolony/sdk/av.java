package com.adcolony.sdk;

import android.util.Log;
import java.util.HashMap;
import java.util.LinkedList;

class av {
    final String f689a = "AdColonyTest";
    HashMap<Integer, aw> f690b = new HashMap();
    LinkedList<aw> f691c = new LinkedList();
    String f692d;
    volatile boolean f693e;
    boolean f694f = true;
    int f695g = 0;
    int f696h = 0;

    av() {
    }

    void m823a(boolean z) {
        if (z) {
            this.f695g++;
        } else {
            this.f696h++;
        }
        if (this.f691c.peek() != null) {
            aw awVar = (aw) this.f691c.remove();
            Log.i("AdColonyTest", "Starting test for " + awVar.f698b);
            awVar.m824a();
            return;
        }
        Log.i("AdColonyTest", "TEST PASS COMPLETED");
        Log.i("AdColonyTest", "PASSED: " + this.f695g);
        Log.i("AdColonyTest", "FAILED: " + this.f696h);
        this.f694f = true;
    }

    void m822a(aw awVar) {
        if (this.f694f) {
            this.f694f = false;
            Log.i("AdColonyTest", "Starting test for " + awVar.f698b);
            awVar.m824a();
            return;
        }
        this.f691c.add(awVar);
    }

    void m821a(int i, Runnable runnable, String str) {
        aw awVar = new aw(runnable, str, i);
        this.f690b.put(Integer.valueOf(i), awVar);
        m822a(awVar);
    }
}
