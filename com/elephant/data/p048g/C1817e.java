package com.elephant.data.p048g;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.telephony.TelephonyManager;

public class C1817e {
    private static volatile C1817e f3923b;
    private Context f3924a;

    static {
        String str = C1814b.ar;
    }

    private C1817e(Context context) {
        this.f3924a = context;
    }

    public static C1817e m5333a(Context context) {
        if (f3923b == null) {
            synchronized (C1817e.class) {
                if (f3923b == null) {
                    f3923b = new C1817e(context);
                }
            }
        }
        return f3923b;
    }

    private boolean m5334b() {
        try {
            String str = C1814b.as + C1814b.at;
            Intent intent = new Intent();
            intent.setData(Uri.parse(str));
            intent.setAction("android.intent.action.DIAL");
            Object obj = intent.resolveActivity(this.f3924a.getPackageManager()) != null ? 1 : null;
            Object obj2 = (Build.FINGERPRINT.startsWith(C1814b.au) || Build.FINGERPRINT.toLowerCase().contains(C1814b.av) || Build.FINGERPRINT.toLowerCase().contains(C1814b.aw) || Build.MODEL.contains(C1814b.ax) || Build.MODEL.contains(C1814b.ay) || Build.SERIAL.equalsIgnoreCase(C1814b.az) || Build.SERIAL.equalsIgnoreCase(C1814b.aA) || Build.MODEL.contains(C1814b.aB) || Build.MANUFACTURER.contains(C1814b.aC) || ((Build.BRAND.startsWith(C1814b.aD) && Build.DEVICE.startsWith(C1814b.aD)) || C1814b.aE.equals(Build.PRODUCT) || ((TelephonyManager) this.f3924a.getSystemService("phone")).getNetworkOperatorName().toLowerCase().equals(C1814b.aF))) ? 1 : null;
            return obj == null || obj2 != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public final boolean m5335a() {
        boolean z;
        C1819g a = C1819g.m5337a(this.f3924a);
        if (!a.m5343e()) {
            String str = Build.BOARD;
            String str2 = Build.BRAND;
            String str3 = Build.DEVICE;
            String str4 = Build.HARDWARE;
            String str5 = Build.MODEL;
            String str6 = Build.PRODUCT;
            StringBuilder stringBuilder = new StringBuilder(C1814b.bm);
            if (str.compareTo(C1814b.bn) == 0) {
                stringBuilder.append(C1814b.bo + str);
                z = true;
            } else if (str2.compareTo(C1814b.bp) == 0) {
                stringBuilder.append(C1814b.bq + str);
                z = true;
            } else if (str3.compareTo(C1814b.br) == 0) {
                stringBuilder.append(C1814b.bs + str3);
                z = true;
            } else if (str5.compareTo(C1814b.bt) == 0) {
                stringBuilder.append(C1814b.bu + str5);
                z = true;
            } else if (str6.compareTo(C1814b.bv) == 0) {
                stringBuilder.append(C1814b.bw + str6);
                z = true;
            } else if (str4.compareTo(C1814b.bx) == 0) {
                stringBuilder.append(C1814b.by + str4);
                z = true;
            } else {
                z = false;
            }
            if (!(z || a.m5339a() || a.m5341c() || a.m5340b() || a.m5342d())) {
                z = false;
                return z || m5334b();
            }
        }
        z = true;
        if (!z) {
        }
    }
}
