package com.elephant.data.p048g;

public class C1818f {
    private long f3925a;
    private int f3926b;

    private C1818f(String str, String str2, String str3) {
        Integer.parseInt(str, 16);
        this.f3925a = Long.parseLong(str2, 16);
        this.f3926b = Integer.parseInt(str3, 16);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean m5336a() {
        /*
        r10 = 0;
        r4 = 1;
        r6 = -1;
        r3 = 0;
        r2 = 0;
        r1 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x00a4, all -> 0x0094 }
        r0 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x00a4, all -> 0x0094 }
        r5 = new java.io.FileInputStream;	 Catch:{ Exception -> 0x00a4, all -> 0x0094 }
        r7 = com.elephant.data.p048g.C1814b.aG;	 Catch:{ Exception -> 0x00a4, all -> 0x0094 }
        r5.<init>(r7);	 Catch:{ Exception -> 0x00a4, all -> 0x0094 }
        r0.<init>(r5);	 Catch:{ Exception -> 0x00a4, all -> 0x0094 }
        r5 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r1.<init>(r0, r5);	 Catch:{ Exception -> 0x00a4, all -> 0x0094 }
        r1.readLine();	 Catch:{ Exception -> 0x003f, all -> 0x009f }
        r2 = new java.util.ArrayList;	 Catch:{ Exception -> 0x003f, all -> 0x009f }
        r2.<init>();	 Catch:{ Exception -> 0x003f, all -> 0x009f }
    L_0x0021:
        r0 = r1.readLine();	 Catch:{ Exception -> 0x003f, all -> 0x009f }
        if (r0 == 0) goto L_0x004a;
    L_0x0027:
        r5 = com.elephant.data.p048g.C1814b.aH;	 Catch:{ Exception -> 0x003f, all -> 0x009f }
        r0 = r0.split(r5);	 Catch:{ Exception -> 0x003f, all -> 0x009f }
        r5 = new com.elephant.data.g.f;	 Catch:{ Exception -> 0x003f, all -> 0x009f }
        r7 = 1;
        r7 = r0[r7];	 Catch:{ Exception -> 0x003f, all -> 0x009f }
        r8 = 2;
        r8 = r0[r8];	 Catch:{ Exception -> 0x003f, all -> 0x009f }
        r9 = 3;
        r0 = r0[r9];	 Catch:{ Exception -> 0x003f, all -> 0x009f }
        r5.<init>(r7, r8, r0);	 Catch:{ Exception -> 0x003f, all -> 0x009f }
        r2.add(r5);	 Catch:{ Exception -> 0x003f, all -> 0x009f }
        goto L_0x0021;
    L_0x003f:
        r0 = move-exception;
        r2 = r1;
        r1 = r0;
        r0 = r3;
    L_0x0043:
        r1.printStackTrace();	 Catch:{ all -> 0x00a1 }
        r2.close();	 Catch:{ Exception -> 0x008f }
    L_0x0049:
        return r0;
    L_0x004a:
        r1.close();	 Catch:{ Exception -> 0x003f, all -> 0x009f }
        r5 = r2.iterator();	 Catch:{ Exception -> 0x003f, all -> 0x009f }
    L_0x0051:
        r0 = r5.hasNext();	 Catch:{ Exception -> 0x003f, all -> 0x009f }
        if (r0 == 0) goto L_0x00b0;
    L_0x0057:
        r0 = r5.next();	 Catch:{ Exception -> 0x003f, all -> 0x009f }
        r0 = (com.elephant.data.p048g.C1818f) r0;	 Catch:{ Exception -> 0x003f, all -> 0x009f }
        r8 = r0.f3925a;	 Catch:{ Exception -> 0x003f, all -> 0x009f }
        r7 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
        if (r7 != 0) goto L_0x0051;
    L_0x0063:
        r0 = r0.f3926b;	 Catch:{ Exception -> 0x003f, all -> 0x009f }
        r5 = r0;
    L_0x0066:
        if (r5 == r6) goto L_0x00ae;
    L_0x0068:
        r6 = r2.iterator();	 Catch:{ Exception -> 0x003f, all -> 0x009f }
        r2 = r3;
    L_0x006d:
        r0 = r6.hasNext();	 Catch:{ Exception -> 0x00a8, all -> 0x009f }
        if (r0 == 0) goto L_0x0085;
    L_0x0073:
        r0 = r6.next();	 Catch:{ Exception -> 0x00a8, all -> 0x009f }
        r0 = (com.elephant.data.p048g.C1818f) r0;	 Catch:{ Exception -> 0x00a8, all -> 0x009f }
        r8 = r0.f3925a;	 Catch:{ Exception -> 0x00a8, all -> 0x009f }
        r3 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
        if (r3 == 0) goto L_0x006d;
    L_0x007f:
        r0 = r0.f3926b;	 Catch:{ Exception -> 0x00a8, all -> 0x009f }
        if (r0 != r5) goto L_0x006d;
    L_0x0083:
        r2 = r4;
        goto L_0x006d;
    L_0x0085:
        r0 = r2;
    L_0x0086:
        r1.close();	 Catch:{ Exception -> 0x008a }
        goto L_0x0049;
    L_0x008a:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0049;
    L_0x008f:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0049;
    L_0x0094:
        r0 = move-exception;
        r1 = r2;
    L_0x0096:
        r1.close();	 Catch:{ Exception -> 0x009a }
    L_0x0099:
        throw r0;
    L_0x009a:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0099;
    L_0x009f:
        r0 = move-exception;
        goto L_0x0096;
    L_0x00a1:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0096;
    L_0x00a4:
        r0 = move-exception;
        r1 = r0;
        r0 = r3;
        goto L_0x0043;
    L_0x00a8:
        r0 = move-exception;
        r12 = r0;
        r0 = r2;
        r2 = r1;
        r1 = r12;
        goto L_0x0043;
    L_0x00ae:
        r0 = r3;
        goto L_0x0086;
    L_0x00b0:
        r5 = r6;
        goto L_0x0066;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.elephant.data.g.f.a():boolean");
    }
}
