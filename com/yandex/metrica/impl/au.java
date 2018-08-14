package com.yandex.metrica.impl;

import android.database.Cursor;
import com.yandex.metrica.C4292c.C4291a.C4288d.C4286b;
import com.yandex.metrica.impl.at.C4340b;
import com.yandex.metrica.impl.ob.C4503t;
import com.yandex.metrica.impl.ob.bl;

class au extends at {
    public au(C4503t c4503t) {
        super(c4503t);
    }

    protected long mo7008s() {
        return Long.MIN_VALUE;
    }

    protected long mo7009t() {
        return Long.MIN_VALUE;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected com.yandex.metrica.impl.at.C4341c mo7006x() {
        /*
        r7 = this;
        r0 = 0;
        r1 = r7.mo7007z();	 Catch:{ Exception -> 0x0043, all -> 0x004c }
        if (r1 == 0) goto L_0x0013;
    L_0x0007:
        r2 = r1.moveToFirst();	 Catch:{ Exception -> 0x0062, all -> 0x0058 }
        if (r2 == 0) goto L_0x0013;
    L_0x000d:
        r2 = r1.getCount();	 Catch:{ Exception -> 0x0062, all -> 0x0058 }
        if (r2 != 0) goto L_0x0038;
    L_0x0013:
        r2 = r7.o;	 Catch:{ Exception -> 0x0062, all -> 0x0058 }
        r4 = r7.mo7008s();	 Catch:{ Exception -> 0x0062, all -> 0x0058 }
        r3 = com.yandex.metrica.impl.ob.bl.BACKGROUND;	 Catch:{ Exception -> 0x0062, all -> 0x0058 }
        r0 = r2.m15355b(r4, r3);	 Catch:{ Exception -> 0x0062, all -> 0x0058 }
        if (r0 == 0) goto L_0x0038;
    L_0x0021:
        r2 = r0.moveToFirst();	 Catch:{ Exception -> 0x0067, all -> 0x005d }
        if (r2 == 0) goto L_0x0038;
    L_0x0027:
        r2 = r0.getCount();	 Catch:{ Exception -> 0x0067, all -> 0x005d }
        if (r2 <= 0) goto L_0x0038;
    L_0x002d:
        r2 = r7.o;	 Catch:{ Exception -> 0x0067, all -> 0x005d }
        r4 = r7.mo7008s();	 Catch:{ Exception -> 0x0067, all -> 0x005d }
        r3 = com.yandex.metrica.impl.ob.bl.BACKGROUND;	 Catch:{ Exception -> 0x0067, all -> 0x005d }
        r2.m15351a(r4, r3);	 Catch:{ Exception -> 0x0067, all -> 0x005d }
    L_0x0038:
        com.yandex.metrica.impl.bk.m14978a(r1);
        com.yandex.metrica.impl.bk.m14978a(r0);
    L_0x003e:
        r0 = super.mo7006x();
        return r0;
    L_0x0043:
        r1 = move-exception;
        r1 = r0;
    L_0x0045:
        com.yandex.metrica.impl.bk.m14978a(r0);
        com.yandex.metrica.impl.bk.m14978a(r1);
        goto L_0x003e;
    L_0x004c:
        r1 = move-exception;
        r2 = r0;
        r6 = r0;
        r0 = r1;
        r1 = r6;
    L_0x0051:
        com.yandex.metrica.impl.bk.m14978a(r1);
        com.yandex.metrica.impl.bk.m14978a(r2);
        throw r0;
    L_0x0058:
        r2 = move-exception;
        r6 = r2;
        r2 = r0;
        r0 = r6;
        goto L_0x0051;
    L_0x005d:
        r2 = move-exception;
        r6 = r2;
        r2 = r0;
        r0 = r6;
        goto L_0x0051;
    L_0x0062:
        r2 = move-exception;
        r6 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x0045;
    L_0x0067:
        r2 = move-exception;
        r6 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x0045;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.au.x():com.yandex.metrica.impl.at$c");
    }

    protected Cursor mo7007z() {
        return this.o.m15347a(mo7008s(), this.b);
    }

    protected Cursor mo7003a(long j, bl blVar) {
        return this.o.m15355b(mo7008s(), blVar);
    }

    protected boolean mo7005a(long j) {
        return false;
    }

    protected C4340b mo7004a(long j, C4286b c4286b) {
        return super.mo7004a(mo7009t(), c4286b);
    }

    public String mo6995a() {
        return super.mo6995a() + " [" + mo7008s() + "]";
    }
}
