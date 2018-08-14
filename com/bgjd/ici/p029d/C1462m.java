package com.bgjd.ici.p029d;

import android.annotation.SuppressLint;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ServiceInfo;
import android.os.Build.VERSION;
import com.bgjd.ici.p025b.C1408j.C1403a;
import com.bgjd.ici.p025b.ac;
import java.io.File;
import java.text.Normalizer;
import java.text.Normalizer.Form;

public class C1462m {
    private String f2307a = "";
    private String f2308b = "";
    private boolean f2309c = false;
    private long f2310d = 0;
    private boolean f2311e = false;

    public C1462m(PackageManager packageManager, PackageInfo packageInfo, String str) {
        this.f2308b = packageInfo.packageName;
        this.f2309c = this.f2308b.matches(str);
        try {
            if (VERSION.SDK_INT > 8) {
                try {
                    this.f2310d = PackageInfo.class.getField("firstInstallTime").getLong(packageInfo);
                } catch (NoSuchFieldException e) {
                } catch (IllegalAccessException e2) {
                } catch (IllegalArgumentException e3) {
                }
            } else {
                this.f2310d = new File(packageInfo.applicationInfo.sourceDir).lastModified();
            }
            this.f2307a = (String) (packageInfo.applicationInfo != null ? packageManager.getApplicationLabel(packageInfo.applicationInfo) : "(unknown)");
            if (this.f2307a != null) {
                this.f2307a = ac.m2876e(this.f2307a);
            }
            ServiceInfo[] serviceInfoArr = packageManager.getPackageInfo(this.f2308b, 4).services;
            if (serviceInfoArr != null && serviceInfoArr.length > 0) {
                for (ServiceInfo serviceInfo : serviceInfoArr) {
                    if (serviceInfo.name.contains("MarketService")) {
                        this.f2311e = true;
                        return;
                    }
                }
            }
        } catch (NameNotFoundException e4) {
        } catch (Exception e5) {
        }
    }

    public String m3026a() {
        int i;
        int i2 = 1;
        this.f2307a = ac.m2876e(this.f2307a);
        this.f2308b = ac.m2876e(this.f2308b);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("INSERT OR IGNORE INTO %s (name,package,is_system,installdate,hassdk,market_id,status) VALUES ", new Object[]{C1403a.f2093w}));
        String str = "('%s','%s',%s,%s,%s,0,0)";
        Object[] objArr = new Object[5];
        objArr[0] = this.f2307a;
        objArr[1] = this.f2308b;
        if (this.f2309c) {
            i = 1;
        } else {
            i = 0;
        }
        objArr[2] = Integer.valueOf(i);
        objArr[3] = Long.valueOf(this.f2310d);
        if (!this.f2311e) {
            i2 = 0;
        }
        objArr[4] = Integer.valueOf(i2);
        stringBuilder.append(String.format(str, objArr));
        return stringBuilder.toString();
    }

    public String toString() {
        int i;
        int i2 = 1;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("INSERT OR IGNORE INTO %s (name,package,is_system,installdate,hassdk,market_id,status) VALUES ", new Object[]{C1403a.f2093w}));
        String str = "('%s','%s',%s,%s,%s,0,0)";
        Object[] objArr = new Object[5];
        objArr[0] = this.f2307a;
        objArr[1] = this.f2308b;
        if (this.f2309c) {
            i = 1;
        } else {
            i = 0;
        }
        objArr[2] = Integer.valueOf(i);
        objArr[3] = Long.valueOf(this.f2310d);
        if (!this.f2311e) {
            i2 = 0;
        }
        objArr[4] = Integer.valueOf(i2);
        stringBuilder.append(String.format(str, objArr));
        return stringBuilder.toString();
    }

    public String m3027b() {
        return this.f2307a;
    }

    public String m3028c() {
        return this.f2308b;
    }

    public int m3029d() {
        return this.f2309c ? 1 : 0;
    }

    public long m3030e() {
        return this.f2310d;
    }

    public int m3031f() {
        return this.f2311e ? 1 : 0;
    }

    @SuppressLint({"NewApi"})
    private String m3025a(String str) {
        char[] cArr = new char[str.length()];
        String replaceAll = Normalizer.normalize(str, Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        int length = replaceAll.length();
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3;
            char charAt = replaceAll.charAt(i);
            if (charAt <= '') {
                i3 = i2 + 1;
                cArr[i2] = charAt;
            } else {
                i3 = i2;
            }
            i++;
            i2 = i3;
        }
        return new String(cArr);
    }
}
