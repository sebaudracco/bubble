package com.elephant.data.p048g;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;

class C1819g {
    private static C1819g f3927b;
    private static String[] f3928c = new String[]{C1814b.bc, C1814b.bd};
    private static String[] f3929d = new String[]{C1814b.be, C1814b.bf};
    private static String[] f3930e = new String[]{C1814b.bg};
    private Context f3931a;

    static {
        String[] strArr = new String[]{C1814b.aI, C1814b.aJ, C1814b.aK, C1814b.aL, C1814b.aM, C1814b.aN, C1814b.aO, C1814b.aP, C1814b.aQ, C1814b.aR, C1814b.aS, C1814b.aT, C1814b.aU, C1814b.aV, C1814b.aW, C1814b.aX};
        strArr = new String[]{C1814b.aY, C1814b.aZ, C1814b.ba};
        new String[1][0] = C1814b.bb;
    }

    private C1819g(Context context) {
        this.f3931a = context;
    }

    public static C1819g m5337a(Context context) {
        if (f3927b == null) {
            synchronized (C1819g.class) {
                if (f3927b == null) {
                    f3927b = new C1819g(context.getApplicationContext());
                }
            }
        }
        return f3927b;
    }

    private static String m5338a(Context context, String str) {
        try {
            Class loadClass = context.getClassLoader().loadClass(C1814b.bD);
            return (String) loadClass.getMethod(C1814b.bE, new Class[]{String.class}).invoke(loadClass, new Object[]{new String(str)});
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e2) {
            throw null;
        }
    }

    public final boolean m5339a() {
        for (String str : f3928c) {
            if (new File(str).exists()) {
                new StringBuilder().append(C1814b.bh).append(str);
                return true;
            }
        }
        return false;
    }

    public final boolean m5340b() {
        for (String str : f3929d) {
            if (new File(str).exists()) {
                new StringBuilder().append(C1814b.bi).append(str);
                return true;
            }
        }
        return false;
    }

    public final boolean m5341c() {
        File[] fileArr = new File[]{new File(C1814b.bj), new File(C1814b.bk)};
        for (int i = 0; i < 2; i++) {
            File file = fileArr[i];
            if (file.exists() && file.canRead()) {
                byte[] bArr = new byte[1024];
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    fileInputStream.read(bArr);
                    fileInputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String str = new String(bArr);
                for (String str2 : f3930e) {
                    if (str.indexOf(str2) != -1) {
                        new StringBuilder().append(C1814b.bl).append(str2);
                        return true;
                    }
                }
                continue;
            }
        }
        return false;
    }

    public final boolean m5342d() {
        try {
            boolean a = C1818f.m5336a();
            new StringBuilder().append(C1814b.bz).append(a);
            return a;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public final boolean m5343e() {
        String a = C1819g.m5338a(this.f3931a, C1814b.bA);
        boolean z = !TextUtils.isEmpty(a) && a.equals(C1814b.bB);
        if (z) {
            new StringBuilder().append(C1814b.bC).append(a);
        }
        return z;
    }
}
