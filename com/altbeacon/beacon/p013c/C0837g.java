package com.altbeacon.beacon.p013c;

import android.util.Log;

final class C0837g extends C0832a {
    C0837g() {
    }

    public void mo1874a(String str, String str2, Object... objArr) {
        Log.d(str, m1644a(str2, objArr));
    }

    public void mo1875a(Throwable th, String str, String str2, Object... objArr) {
        Log.w(str, m1644a(str2, objArr), th);
    }

    public void mo1876b(String str, String str2, Object... objArr) {
        Log.i(str, m1644a(str2, objArr));
    }

    public void mo1877b(Throwable th, String str, String str2, Object... objArr) {
        Log.e(str, m1644a(str2, objArr), th);
    }

    public void mo1878c(String str, String str2, Object... objArr) {
        Log.w(str, m1644a(str2, objArr));
    }

    public void mo1879d(String str, String str2, Object... objArr) {
        Log.e(str, m1644a(str2, objArr));
    }
}
