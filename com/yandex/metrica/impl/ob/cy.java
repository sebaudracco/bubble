package com.yandex.metrica.impl.ob;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import java.util.ArrayList;
import java.util.List;

class cy implements cv {
    private final Context f12128a;

    public cy(Context context) {
        this.f12128a = context;
    }

    public List<cw> mo7071a() {
        List arrayList = new ArrayList();
        try {
            PackageInfo packageInfo = this.f12128a.getPackageManager().getPackageInfo(this.f12128a.getPackageName(), 4096);
            for (int i = 0; i < packageInfo.requestedPermissions.length; i++) {
                String str = packageInfo.requestedPermissions[i];
                if ((packageInfo.requestedPermissionsFlags[i] & 2) != 0) {
                    arrayList.add(new cw(str, true));
                } else {
                    arrayList.add(new cw(str, false));
                }
            }
        } catch (NameNotFoundException e) {
        }
        return arrayList;
    }
}
