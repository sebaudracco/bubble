package com.oneaudience.sdk.p133a;

import android.content.Context;
import android.content.pm.PackageInfo;
import com.oneaudience.sdk.model.InstalledPackage;
import com.oneaudience.sdk.p135c.C3833d;
import java.util.ArrayList;
import java.util.List;

public class C3801n extends C3784a {
    private final String f9125f = C3801n.class.getSimpleName();

    protected C3801n(Context context, String str, boolean z, boolean z2, long j) {
        super(context, str, z, z2, j, "installed_apps", "disableInstallAppsCollector", true, true);
    }

    private ArrayList<InstalledPackage> m12152i() {
        List<PackageInfo> installedPackages = this.c.getPackageManager().getInstalledPackages(8192);
        ArrayList<InstalledPackage> arrayList = new ArrayList();
        for (PackageInfo packageInfo : installedPackages) {
            if (!(packageInfo == null || packageInfo.packageName.equalsIgnoreCase("com.android.keyguard"))) {
                arrayList.add(new InstalledPackage(packageInfo.packageName, packageInfo.firstInstallTime, packageInfo.lastUpdateTime));
            }
        }
        return arrayList;
    }

    public String mo6804a() {
        try {
            return m12083a((Object) m12152i());
        } catch (Exception e) {
            this.c.getApplicationContext();
            C3833d.m12256e(this.f9125f, "error collecting installed apps");
            return null;
        }
    }

    public String[] mo6805b() {
        return new String[0];
    }
}
