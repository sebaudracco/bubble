package com.altbeacon.beacon.p012b;

import android.content.Context;
import android.os.AsyncTask;
import android.provider.Settings.Secure;

public class C0828f extends AsyncTask<Void, Void, Void> {
    private Context f1627a;
    private C0824d f1628b;
    private C0825a f1629c;

    interface C0825a {
        void mo1873a(String str, Exception exception, int i);
    }

    public C0828f(Context context, String str, C0825a c0825a) {
        this.f1627a = context;
        this.f1628b = new C0824d(str, m1612a());
        this.f1629c = c0825a;
    }

    private String m1612a() {
        return "Android Beacon Library;" + m1616e() + ";" + m1613b() + ";" + m1615d() + ";" + m1614c();
    }

    private String m1613b() {
        return this.f1627a.getPackageName();
    }

    private String m1614c() {
        return C0821a.m1584a().toString();
    }

    private String m1615d() {
        return Secure.getString(this.f1627a.getContentResolver(), "android_id");
    }

    private String m1616e() {
        return "1.0";
    }

    protected Void m1617a(Void... voidArr) {
        this.f1628b.m1595d();
        if (this.f1629c != null) {
            this.f1629c.mo1873a(this.f1628b.m1593b(), this.f1628b.m1594c(), this.f1628b.m1592a());
        }
        return null;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m1617a((Void[]) objArr);
    }
}
