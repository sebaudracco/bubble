package com.adcolony.sdk;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.adcolony.sdk.aa.C0595a;

class aj {
    final int f540a = 30;
    String f541b = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx  x          xxxxxxx                          xxxx x                          xxxxx";
    String f542c = "0123456789ABCDEF";
    String f543d = "0123456789abcdef";

    aj() {
    }

    boolean m713a() {
        if (!C0594a.m614d()) {
            return false;
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) C0594a.m613c().getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return false;
            }
            return activeNetworkInfo.getType() == 1;
        } catch (SecurityException e) {
            new C0595a().m622a("SecurityException - please ensure you added the ACCESS_NETWORK_STATE permission: ").m622a(e.toString()).m624a(aa.f483g);
            return false;
        } catch (Exception e2) {
            new C0595a().m622a("Exception occurred when retrieving activeNetworkInfo in ADCNetwork.using_wifi(): ").m622a(e2.toString()).m624a(aa.f484h);
            return false;
        }
    }

    boolean m715b() {
        if (!C0594a.m614d()) {
            return false;
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) C0594a.m613c().getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return false;
            }
            int type = activeNetworkInfo.getType();
            boolean z = type == 0 || type >= 2;
            return z;
        } catch (SecurityException e) {
            new C0595a().m622a("SecurityException - please ensure you added the ACCESS_NETWORK_STATE permission: ").m622a(e.toString()).m624a(aa.f483g);
            return false;
        } catch (Exception e2) {
            new C0595a().m622a("Exception occurred when retrieving activeNetworkInfo in ADCNetwork.using_mobile(): ").m622a(e2.toString()).m624a(aa.f484h);
            return false;
        }
    }

    String m716c() {
        if (m713a()) {
            return "wifi";
        }
        if (m715b()) {
            return "cell";
        }
        return "none";
    }

    String m712a(String str) {
        if (str == null) {
            str = "null";
        }
        StringBuilder stringBuilder = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt >= '' || this.f541b.charAt(charAt) != ' ') {
                stringBuilder.append('%');
                int i2 = (charAt >> 4) & 15;
                int i3 = charAt & 15;
                if (i2 < 10) {
                    stringBuilder.append((char) (i2 + 48));
                } else {
                    stringBuilder.append((char) ((i2 + 65) - 10));
                }
                if (i3 < 10) {
                    stringBuilder.append((char) (i3 + 48));
                } else {
                    stringBuilder.append((char) ((i3 + 65) - 10));
                }
            } else {
                stringBuilder.append(charAt);
            }
        }
        return stringBuilder.toString();
    }

    int m711a(char c) {
        int indexOf = this.f542c.indexOf(c);
        if (indexOf >= 0) {
            return indexOf;
        }
        indexOf = this.f543d.indexOf(c);
        return indexOf < 0 ? 0 : indexOf;
    }

    String m714b(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        int length = str.length();
        int i = 0;
        while (i < length) {
            int i2;
            char charAt = str.charAt(i);
            if (charAt == '%') {
                char charAt2;
                if (i + 1 < length) {
                    charAt2 = str.charAt(i + 1);
                } else {
                    charAt2 = '0';
                }
                if (i + 2 < length) {
                    charAt = str.charAt(i + 2);
                } else {
                    charAt = '0';
                }
                i += 2;
                stringBuilder.append((char) (m711a(charAt) | (m711a(charAt2) << 8)));
                i2 = i;
            } else {
                stringBuilder.append(charAt);
                i2 = i;
            }
            i = i2 + 1;
        }
        return stringBuilder.toString();
    }
}
