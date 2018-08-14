package com.yandex.metrica.impl.ob;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import java.util.ArrayList;
import java.util.List;

class cz implements cv {
    private final Context f12129a;

    public cz(Context context) {
        this.f12129a = context;
    }

    public List<cw> mo7071a() {
        List arrayList = new ArrayList();
        try {
            for (String cwVar : this.f12129a.getPackageManager().getPackageInfo(this.f12129a.getPackageName(), 4096).requestedPermissions) {
                arrayList.add(new cw(cwVar, true));
            }
        } catch (NameNotFoundException e) {
        }
        return arrayList;
    }
}
