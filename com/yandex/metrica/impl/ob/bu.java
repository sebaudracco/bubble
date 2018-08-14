package com.yandex.metrica.impl.ob;

import android.database.Cursor;
import java.util.Arrays;
import java.util.HashMap;

public class bu implements bt {
    private final HashMap<String, String[]> f11987a;

    public bu(HashMap<String, String[]> hashMap) {
        this.f11987a = hashMap;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo7057a(android.database.sqlite.SQLiteDatabase r15) {
        /*
        r14 = this;
        r10 = 0;
        r12 = 0;
        r11 = 1;
        r1 = r14.f11987a;	 Catch:{ Exception -> 0x0042 }
        r1 = r1.entrySet();	 Catch:{ Exception -> 0x0042 }
        r13 = r1.iterator();	 Catch:{ Exception -> 0x0042 }
    L_0x000d:
        r1 = r13.hasNext();	 Catch:{ Exception -> 0x0042 }
        if (r1 == 0) goto L_0x004b;
    L_0x0013:
        r1 = r13.next();	 Catch:{ Exception -> 0x0042 }
        r0 = r1;
        r0 = (java.util.Map.Entry) r0;	 Catch:{ Exception -> 0x0042 }
        r9 = r0;
        r2 = r9.getKey();	 Catch:{ all -> 0x0045 }
        r2 = (java.lang.String) r2;	 Catch:{ all -> 0x0045 }
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r1 = r15;
        r2 = r1.query(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ all -> 0x0045 }
        if (r2 != 0) goto L_0x0033;
    L_0x002e:
        com.yandex.metrica.impl.bk.m14978a(r2);	 Catch:{ Exception -> 0x0042 }
        r1 = r10;
    L_0x0032:
        return r1;
    L_0x0033:
        r1 = r9.getValue();	 Catch:{ all -> 0x004d }
        r1 = (java.lang.String[]) r1;	 Catch:{ all -> 0x004d }
        r1 = r14.m15397a(r2, r1);	 Catch:{ all -> 0x004d }
        r11 = r11 & r1;
        com.yandex.metrica.impl.bk.m14978a(r2);	 Catch:{ Exception -> 0x0042 }
        goto L_0x000d;
    L_0x0042:
        r1 = move-exception;
        r1 = r10;
        goto L_0x0032;
    L_0x0045:
        r1 = move-exception;
        r2 = r12;
    L_0x0047:
        com.yandex.metrica.impl.bk.m14978a(r2);	 Catch:{ Exception -> 0x0042 }
        throw r1;	 Catch:{ Exception -> 0x0042 }
    L_0x004b:
        r1 = r11;
        goto L_0x0032;
    L_0x004d:
        r1 = move-exception;
        goto L_0x0047;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ob.bu.a(android.database.sqlite.SQLiteDatabase):boolean");
    }

    boolean m15397a(Cursor cursor, String[] strArr) {
        String[] columnNames = cursor.getColumnNames();
        Arrays.sort(columnNames);
        Arrays.sort(strArr);
        return Arrays.equals(columnNames, strArr);
    }
}
