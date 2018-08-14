package com.bgjd.ici.plugin;

import com.bgjd.ici.p025b.C1395h;
import com.bgjd.ici.p030e.C1485h;

public abstract class C1523g implements C1518b {
    private static final String f2499i = "PLGBS";
    protected C1485h f2500a;
    protected C1395h f2501b;
    protected String f2502c;
    protected int f2503d;
    protected String f2504e;
    protected String f2505f;
    protected C1520d f2506g;
    protected ClassLoader f2507h;

    protected C1523g(String str, C1485h c1485h, C1395h c1395h) {
        this.f2500a = null;
        this.f2502c = "";
        this.f2503d = 0;
        this.f2504e = "";
        this.f2505f = "";
        this.f2506g = null;
        this.f2507h = null;
        this.f2502c = str;
        this.f2500a = c1485h;
        this.f2501b = c1395h;
    }

    protected C1523g(C1395h c1395h) {
        this("", null, c1395h);
    }

    protected C1523g(String str, C1395h c1395h) {
        this(str, null, c1395h);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected boolean m3287i() {
        /*
        r10 = this;
        r1 = 1;
        r0 = 0;
        r2 = com.bgjd.ici.plugin.C1532j.m3310b();
        r10.f2506g = r2;
        r2 = "com.bgjd.ici.MarketBeaconService";
        r2 = java.lang.Class.forName(r2);	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
        if (r2 != 0) goto L_0x0012;
    L_0x0011:
        return r0;
    L_0x0012:
        r2 = com.bgjd.ici.MarketBeaconService.f2015a;	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
        if (r2 == 0) goto L_0x001c;
    L_0x0016:
        r2 = com.bgjd.ici.MarketBeaconService.f2015a;	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
        r10.f2507h = r2;	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
        r0 = r1;
        goto L_0x0011;
    L_0x001c:
        r2 = new com.bgjd.ici.plugin.i;	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
        r3 = r10.f2501b;	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
        r2.<init>(r3);	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
        r3 = r10.f2501b;	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
        r3 = r3.getExtensionId();	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
        r4 = r10.f2501b;	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
        r4 = r4.mo2180H();	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
        r4 = r4.toLowerCase();	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
        r2 = r2.mo2355a();	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
        if (r2 == 0) goto L_0x0011;
    L_0x0039:
        r2 = com.bgjd.ici.MarketBeaconService.f2015a;	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
        if (r2 != 0) goto L_0x010e;
    L_0x003d:
        r2 = new java.io.File;	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
        r5 = r10.f2501b;	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
        r5 = r5.getContext();	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
        r5 = r5.getFilesDir();	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
        r6 = new java.lang.StringBuilder;	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
        r6.<init>();	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
        r7 = r4.toLowerCase();	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
        r6 = r6.append(r7);	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
        r7 = ".zip";
        r6 = r6.append(r7);	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
        r6 = r6.toString();	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
        r2.<init>(r5, r6);	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
        r5 = new java.io.File;	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
        r6 = r10.f2501b;	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
        r6 = r6.getContext();	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
        r7 = "dex";
        r8 = 0;
        r6 = r6.getDir(r7, r8);	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
        r7 = new java.lang.StringBuilder;	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
        r7.<init>();	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
        r8 = r4.toLowerCase();	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
        r7 = r7.append(r8);	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
        r8 = ".zip";
        r7 = r7.append(r8);	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
        r7 = r7.toString();	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
        r5.<init>(r6, r7);	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
        r6 = r5.exists();	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
        if (r6 == 0) goto L_0x0098;
    L_0x0095:
        r5.delete();	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
    L_0x0098:
        r6 = new java.io.BufferedInputStream;	 Catch:{ Exception -> 0x00be, ClassNotFoundException -> 0x00d1 }
        r7 = new java.io.FileInputStream;	 Catch:{ Exception -> 0x00be, ClassNotFoundException -> 0x00d1 }
        r7.<init>(r2);	 Catch:{ Exception -> 0x00be, ClassNotFoundException -> 0x00d1 }
        r6.<init>(r7);	 Catch:{ Exception -> 0x00be, ClassNotFoundException -> 0x00d1 }
        r2 = new java.io.BufferedOutputStream;	 Catch:{ Exception -> 0x00be, ClassNotFoundException -> 0x00d1 }
        r7 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x00be, ClassNotFoundException -> 0x00d1 }
        r7.<init>(r5);	 Catch:{ Exception -> 0x00be, ClassNotFoundException -> 0x00d1 }
        r2.<init>(r7);	 Catch:{ Exception -> 0x00be, ClassNotFoundException -> 0x00d1 }
        r7 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r7 = new byte[r7];	 Catch:{ Exception -> 0x00be, ClassNotFoundException -> 0x00d1 }
    L_0x00b0:
        r8 = 0;
        r9 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r8 = r6.read(r7, r8, r9);	 Catch:{ Exception -> 0x00be, ClassNotFoundException -> 0x00d1 }
        if (r8 <= 0) goto L_0x00d4;
    L_0x00b9:
        r9 = 0;
        r2.write(r7, r9, r8);	 Catch:{ Exception -> 0x00be, ClassNotFoundException -> 0x00d1 }
        goto L_0x00b0;
    L_0x00be:
        r1 = move-exception;
        r2 = "PLGBS";
        r3 = 1;
        r3 = new java.lang.String[r3];	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
        r4 = 0;
        r5 = r1.getMessage();	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
        r3[r4] = r5;	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
        com.bgjd.ici.p025b.C1402i.m2898a(r2, r1, r3);	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
        goto L_0x0011;
    L_0x00d1:
        r1 = move-exception;
        goto L_0x0011;
    L_0x00d4:
        r2.close();	 Catch:{ Exception -> 0x00be, ClassNotFoundException -> 0x00d1 }
        r6.close();	 Catch:{ Exception -> 0x00be, ClassNotFoundException -> 0x00d1 }
        r2 = r10.f2501b;	 Catch:{ Exception -> 0x00be, ClassNotFoundException -> 0x00d1 }
        r2 = r2.getContext();	 Catch:{ Exception -> 0x00be, ClassNotFoundException -> 0x00d1 }
        r6 = "outdex";
        r7 = 0;
        r2 = r2.getDir(r6, r7);	 Catch:{ Exception -> 0x00be, ClassNotFoundException -> 0x00d1 }
        r6 = new dalvik.system.DexClassLoader;	 Catch:{ Exception -> 0x00be, ClassNotFoundException -> 0x00d1 }
        r5 = r5.getAbsolutePath();	 Catch:{ Exception -> 0x00be, ClassNotFoundException -> 0x00d1 }
        r2 = r2.getAbsolutePath();	 Catch:{ Exception -> 0x00be, ClassNotFoundException -> 0x00d1 }
        r7 = 0;
        r8 = r10.f2501b;	 Catch:{ Exception -> 0x00be, ClassNotFoundException -> 0x00d1 }
        r8 = r8.getContext();	 Catch:{ Exception -> 0x00be, ClassNotFoundException -> 0x00d1 }
        r8 = r8.getClassLoader();	 Catch:{ Exception -> 0x00be, ClassNotFoundException -> 0x00d1 }
        r6.<init>(r5, r2, r7, r8);	 Catch:{ Exception -> 0x00be, ClassNotFoundException -> 0x00d1 }
        r10.f2507h = r6;	 Catch:{ Exception -> 0x00be, ClassNotFoundException -> 0x00d1 }
        r2 = r10.f2507h;	 Catch:{ Exception -> 0x00be, ClassNotFoundException -> 0x00d1 }
        com.bgjd.ici.MarketBeaconService.f2015a = r2;	 Catch:{ Exception -> 0x00be, ClassNotFoundException -> 0x00d1 }
        r2 = r10.f2501b;	 Catch:{ Exception -> 0x00be, ClassNotFoundException -> 0x00d1 }
        r2.mo2217b(r4, r3);	 Catch:{ Exception -> 0x00be, ClassNotFoundException -> 0x00d1 }
        r0 = r1;
        goto L_0x0011;
    L_0x010e:
        r2 = com.bgjd.ici.MarketBeaconService.f2015a;	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
        r10.f2507h = r2;	 Catch:{ ClassNotFoundException -> 0x00d1, Exception -> 0x0115 }
        r0 = r1;
        goto L_0x0011;
    L_0x0115:
        r1 = move-exception;
        goto L_0x0011;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bgjd.ici.plugin.g.i():boolean");
    }
}
