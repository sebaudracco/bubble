package com.yandex.metrica.impl.ob;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

class fe {
    private String f12383a;
    private String f12384b;
    private String f12385c;

    fe(Context context) {
        try {
            this.f12383a = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            this.f12383a = "0.0";
        }
        this.f12384b = context.getFilesDir().getAbsolutePath();
        this.f12385c = context.getPackageName();
    }

    String m15999a() {
        return this.f12383a;
    }

    String m16000b() {
        return this.f12384b;
    }

    String m16001c() {
        return this.f12385c;
    }

    fs m15998a(List<X509Certificate> list) throws GeneralSecurityException, IOException {
        return fg.m16006a((List) list);
    }

    fs m16002d() throws GeneralSecurityException, IOException {
        List arrayList = new ArrayList();
        for (String a : C4389a.m15137a()) {
            arrayList.add(ex.m15975a(a));
        }
        return fg.m16006a(arrayList);
    }
}
