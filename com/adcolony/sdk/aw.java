package com.adcolony.sdk;

import android.util.Log;

class aw {
    final String f697a = "AdColonyTest";
    String f698b;
    Runnable f699c;
    int f700d;

    aw(Runnable runnable, String str, int i) {
        this.f699c = runnable;
        this.f698b = str;
        this.f700d = i;
    }

    void m824a() {
        this.f699c.run();
    }

    void m826b() {
        Log.i("AdColonyTest", "Test for " + this.f698b + " has completed.");
        Log.i("AdColonyTest", "Passed: true");
        Log.i("AdColonyTest", "---------------------------------------------------------");
        C0594a.m605a().f1296d.m823a(true);
    }

    void m825a(String str) {
        Log.i("AdColonyTest", "Test for " + this.f698b + " has completed.");
        Log.i("AdColonyTest", "Passed: false");
        Log.i("AdColonyTest", "Reason: " + str);
        Log.i("AdColonyTest", "---------------------------------------------------------");
        C0594a.m605a().f1296d.m823a(false);
    }
}
