package com.elephant.data.p041c;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri.Builder;
import com.elephant.data.p035a.C1730j;
import com.elephant.data.p044e.p045a.C1783g;
import com.elephant.data.p048g.C1814b;
import com.elephant.data.p048g.C1816d;
import com.elephant.data.p048g.C1821i;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import cz.msebera.android.httpclient.HttpStatus;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public final class C1736e {
    private Context f3575a;
    private C1735d f3576b;
    private boolean f3577c = false;

    static {
        String str = C1814b.fA;
    }

    public C1736e(Context context) {
        this.f3575a = context;
    }

    private synchronized C1735d m5000b() {
        if (this.f3576b == null) {
            this.f3576b = new C1735d(this.f3575a);
        }
        return this.f3576b;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.elephant.data.p044e.p045a.C1783g m5002a(java.lang.String r9) {
        /*
        r8 = this;
        r6 = 0;
        r0 = r8.f3575a;	 Catch:{ OutOfMemoryError -> 0x0098, Exception -> 0x0078 }
        r0 = r0.getContentResolver();	 Catch:{ OutOfMemoryError -> 0x0098, Exception -> 0x0078 }
        r1 = new android.net.Uri$Builder;	 Catch:{ OutOfMemoryError -> 0x0098, Exception -> 0x0078 }
        r1.<init>();	 Catch:{ OutOfMemoryError -> 0x0098, Exception -> 0x0078 }
        r2 = "content";
        r1 = r1.scheme(r2);	 Catch:{ OutOfMemoryError -> 0x0098, Exception -> 0x0078 }
        r2 = r8.f3575a;	 Catch:{ OutOfMemoryError -> 0x0098, Exception -> 0x0078 }
        r2 = com.elephant.data.p048g.C1816d.m5329t(r2);	 Catch:{ OutOfMemoryError -> 0x0098, Exception -> 0x0078 }
        r1 = r1.authority(r2);	 Catch:{ OutOfMemoryError -> 0x0098, Exception -> 0x0078 }
        r2 = com.elephant.data.p035a.C1730j.f3550a;	 Catch:{ OutOfMemoryError -> 0x0098, Exception -> 0x0078 }
        r1 = r1.appendPath(r2);	 Catch:{ OutOfMemoryError -> 0x0098, Exception -> 0x0078 }
        r1 = r1.build();	 Catch:{ OutOfMemoryError -> 0x0098, Exception -> 0x0078 }
        r2 = 0;
        r3 = new java.lang.StringBuilder;	 Catch:{ OutOfMemoryError -> 0x0098, Exception -> 0x0078 }
        r3.<init>();	 Catch:{ OutOfMemoryError -> 0x0098, Exception -> 0x0078 }
        r4 = com.elephant.data.p048g.C1814b.gc;	 Catch:{ OutOfMemoryError -> 0x0098, Exception -> 0x0078 }
        r3 = r3.append(r4);	 Catch:{ OutOfMemoryError -> 0x0098, Exception -> 0x0078 }
        r3 = r3.append(r9);	 Catch:{ OutOfMemoryError -> 0x0098, Exception -> 0x0078 }
        r4 = com.elephant.data.p048g.C1814b.gd;	 Catch:{ OutOfMemoryError -> 0x0098, Exception -> 0x0078 }
        r3 = r3.append(r4);	 Catch:{ OutOfMemoryError -> 0x0098, Exception -> 0x0078 }
        r3 = r3.toString();	 Catch:{ OutOfMemoryError -> 0x0098, Exception -> 0x0078 }
        r4 = 0;
        r5 = 0;
        r1 = r0.query(r1, r2, r3, r4, r5);	 Catch:{ OutOfMemoryError -> 0x0098, Exception -> 0x0078 }
        if (r1 == 0) goto L_0x0061;
    L_0x0049:
        r0 = r1.getCount();	 Catch:{ OutOfMemoryError -> 0x006f, Exception -> 0x008e, all -> 0x008b }
        if (r0 <= 0) goto L_0x0061;
    L_0x004f:
        r0 = 0;
        r1.moveToPosition(r0);	 Catch:{ OutOfMemoryError -> 0x006f, Exception -> 0x008e, all -> 0x008b }
        r0 = new com.elephant.data.e.a.g;	 Catch:{ OutOfMemoryError -> 0x006f, Exception -> 0x008e, all -> 0x008b }
        r0.<init>();	 Catch:{ OutOfMemoryError -> 0x006f, Exception -> 0x008e, all -> 0x008b }
        r0.m5134a(r1);	 Catch:{ OutOfMemoryError -> 0x009b, Exception -> 0x0094, all -> 0x008b }
    L_0x005b:
        if (r1 == 0) goto L_0x0060;
    L_0x005d:
        r1.close();
    L_0x0060:
        return r0;
    L_0x0061:
        r0 = r8.f3577c;	 Catch:{ OutOfMemoryError -> 0x006f, Exception -> 0x008e, all -> 0x008b }
        if (r0 == 0) goto L_0x009e;
    L_0x0065:
        if (r1 != 0) goto L_0x009e;
    L_0x0067:
        r0 = new java.lang.IllegalArgumentException;	 Catch:{ OutOfMemoryError -> 0x006f, Exception -> 0x008e, all -> 0x008b }
        r2 = com.elephant.data.p048g.C1814b.ge;	 Catch:{ OutOfMemoryError -> 0x006f, Exception -> 0x008e, all -> 0x008b }
        r0.<init>(r2);	 Catch:{ OutOfMemoryError -> 0x006f, Exception -> 0x008e, all -> 0x008b }
        throw r0;	 Catch:{ OutOfMemoryError -> 0x006f, Exception -> 0x008e, all -> 0x008b }
    L_0x006f:
        r0 = move-exception;
        r0 = r6;
        r6 = r1;
    L_0x0072:
        if (r6 == 0) goto L_0x0060;
    L_0x0074:
        r6.close();
        goto L_0x0060;
    L_0x0078:
        r0 = move-exception;
        r1 = r0;
        r0 = r6;
    L_0x007b:
        r1.printStackTrace();	 Catch:{ all -> 0x0084 }
        if (r6 == 0) goto L_0x0060;
    L_0x0080:
        r6.close();
        goto L_0x0060;
    L_0x0084:
        r0 = move-exception;
    L_0x0085:
        if (r6 == 0) goto L_0x008a;
    L_0x0087:
        r6.close();
    L_0x008a:
        throw r0;
    L_0x008b:
        r0 = move-exception;
        r6 = r1;
        goto L_0x0085;
    L_0x008e:
        r0 = move-exception;
        r7 = r0;
        r0 = r6;
        r6 = r1;
        r1 = r7;
        goto L_0x007b;
    L_0x0094:
        r2 = move-exception;
        r6 = r1;
        r1 = r2;
        goto L_0x007b;
    L_0x0098:
        r0 = move-exception;
        r0 = r6;
        goto L_0x0072;
    L_0x009b:
        r2 = move-exception;
        r6 = r1;
        goto L_0x0072;
    L_0x009e:
        r0 = r6;
        goto L_0x005b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.elephant.data.c.e.a(java.lang.String):com.elephant.data.e.a.g");
    }

    public final LinkedList m5003a() {
        Cursor query;
        Exception exception;
        Cursor cursor;
        LinkedList linkedList;
        Throwable th;
        Cursor cursor2 = null;
        LinkedList linkedList2 = new LinkedList();
        C1783g c1783g;
        try {
            query = this.f3575a.getContentResolver().query(new Builder().scheme(FirebaseAnalytics$Param.CONTENT).authority(C1816d.m5329t(this.f3575a)).appendPath(C1730j.f3550a).build(), null, C1814b.fH, null, C1732a.f3564b + C1814b.fI + HttpStatus.SC_MULTIPLE_CHOICES);
            if (query != null) {
                try {
                    query.moveToPosition(-1);
                    while (query.moveToNext()) {
                        c1783g = new C1783g();
                        c1783g.m5134a(query);
                        linkedList2.add(c1783g);
                    }
                } catch (OutOfMemoryError e) {
                    if (query != null) {
                        query.close();
                    }
                    return linkedList2;
                } catch (Exception e2) {
                    exception = e2;
                    cursor = query;
                    try {
                        cursor = m5000b().m4997a(C1732a.f3563a, null, C1814b.fK, null, C1732a.f3564b + C1814b.fL + HttpStatus.SC_MULTIPLE_CHOICES);
                        if (cursor != null) {
                            try {
                                if (cursor.getCount() > 0) {
                                    linkedList = new LinkedList();
                                    try {
                                        cursor.moveToPosition(-1);
                                        while (cursor.moveToNext()) {
                                            c1783g = new C1783g();
                                            c1783g.m5134a(cursor);
                                            linkedList.add(c1783g);
                                        }
                                        linkedList2 = linkedList;
                                    } catch (OutOfMemoryError e3) {
                                        linkedList2 = linkedList;
                                        if (cursor != null) {
                                            cursor.close();
                                        }
                                        return linkedList2;
                                    } catch (Exception e4) {
                                        exception.printStackTrace();
                                        linkedList2 = linkedList;
                                        if (cursor != null) {
                                            cursor.close();
                                        }
                                        return linkedList2;
                                    }
                                    if (cursor != null) {
                                        cursor.close();
                                    }
                                    return linkedList2;
                                }
                            } catch (OutOfMemoryError e5) {
                                linkedList = linkedList2;
                                linkedList2 = linkedList;
                                if (cursor != null) {
                                    cursor.close();
                                }
                                return linkedList2;
                            } catch (Exception e6) {
                                linkedList = linkedList2;
                                exception.printStackTrace();
                                linkedList2 = linkedList;
                                if (cursor != null) {
                                    cursor.close();
                                }
                                return linkedList2;
                            }
                        }
                        linkedList = linkedList2;
                        linkedList2 = linkedList;
                        if (cursor != null) {
                            cursor.close();
                        }
                        return linkedList2;
                    } catch (Throwable th2) {
                        th = th2;
                        cursor2 = cursor;
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    cursor2 = query;
                    th = th3;
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    throw th;
                }
            } else if (this.f3577c && query == null) {
                throw new IllegalArgumentException(C1814b.fJ);
            }
            if (query != null) {
                query.close();
            }
        } catch (OutOfMemoryError e7) {
            query = null;
            if (query != null) {
                query.close();
            }
            return linkedList2;
        } catch (Exception e8) {
            cursor = null;
            exception = e8;
            cursor = m5000b().m4997a(C1732a.f3563a, null, C1814b.fK, null, C1732a.f3564b + C1814b.fL + HttpStatus.SC_MULTIPLE_CHOICES);
            if (cursor != null) {
                if (cursor.getCount() > 0) {
                    linkedList = new LinkedList();
                    cursor.moveToPosition(-1);
                    while (cursor.moveToNext()) {
                        c1783g = new C1783g();
                        c1783g.m5134a(cursor);
                        linkedList.add(c1783g);
                    }
                    linkedList2 = linkedList;
                    if (cursor != null) {
                        cursor.close();
                    }
                    return linkedList2;
                }
            }
            linkedList = linkedList2;
            linkedList2 = linkedList;
            if (cursor != null) {
                cursor.close();
            }
            return linkedList2;
        } catch (Throwable th4) {
            th = th4;
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        }
        return linkedList2;
    }

    public final LinkedList m5004a(HashSet hashSet) {
        StringBuffer stringBuffer;
        Exception exception;
        Cursor cursor;
        LinkedList linkedList;
        Throwable th;
        Cursor cursor2 = null;
        LinkedList linkedList2 = new LinkedList();
        if (hashSet == null || hashSet.size() <= 0) {
            stringBuffer = null;
        } else {
            StringBuffer stringBuffer2 = new StringBuffer(C1814b.fB);
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                stringBuffer2.append(((String) it.next()) + C1814b.fC);
            }
            stringBuffer2.deleteCharAt(stringBuffer2.length() - 1);
            stringBuffer2.append(C1814b.fD);
            stringBuffer = stringBuffer2;
        }
        if (stringBuffer != null) {
            Cursor query;
            C1783g c1783g;
            try {
                query = this.f3575a.getContentResolver().query(new Builder().scheme(FirebaseAnalytics$Param.CONTENT).authority(C1816d.m5329t(this.f3575a)).appendPath(C1730j.f3550a).build(), null, stringBuffer.toString(), null, C1732a.f3564b + C1814b.fE + HttpStatus.SC_MULTIPLE_CHOICES);
                if (query != null) {
                    try {
                        query.moveToPosition(-1);
                        while (query.moveToNext()) {
                            c1783g = new C1783g();
                            c1783g.m5134a(query);
                            linkedList2.add(c1783g);
                        }
                    } catch (OutOfMemoryError e) {
                        if (query != null) {
                            query.close();
                        }
                        return linkedList2;
                    } catch (Exception e2) {
                        exception = e2;
                        cursor = query;
                        try {
                            cursor = m5000b().m4997a(C1732a.f3563a, null, stringBuffer.toString(), null, C1732a.f3564b + C1814b.fG + HttpStatus.SC_MULTIPLE_CHOICES);
                            if (cursor != null) {
                                try {
                                    if (cursor.getCount() > 0) {
                                        linkedList = new LinkedList();
                                        try {
                                            cursor.moveToPosition(-1);
                                            while (cursor.moveToNext()) {
                                                c1783g = new C1783g();
                                                c1783g.m5134a(cursor);
                                                linkedList.add(c1783g);
                                            }
                                            linkedList2 = linkedList;
                                        } catch (OutOfMemoryError e3) {
                                            linkedList2 = linkedList;
                                            if (cursor != null) {
                                                cursor.close();
                                            }
                                            return linkedList2;
                                        } catch (Exception e4) {
                                            exception.printStackTrace();
                                            linkedList2 = linkedList;
                                            if (cursor != null) {
                                                cursor.close();
                                            }
                                            return linkedList2;
                                        }
                                        if (cursor != null) {
                                            cursor.close();
                                        }
                                        return linkedList2;
                                    }
                                } catch (OutOfMemoryError e5) {
                                    linkedList = linkedList2;
                                    linkedList2 = linkedList;
                                    if (cursor != null) {
                                        cursor.close();
                                    }
                                    return linkedList2;
                                } catch (Exception e6) {
                                    linkedList = linkedList2;
                                    exception.printStackTrace();
                                    linkedList2 = linkedList;
                                    if (cursor != null) {
                                        cursor.close();
                                    }
                                    return linkedList2;
                                }
                            }
                            linkedList = linkedList2;
                            linkedList2 = linkedList;
                            if (cursor != null) {
                                cursor.close();
                            }
                            return linkedList2;
                        } catch (Throwable th2) {
                            th = th2;
                            cursor2 = cursor;
                            if (cursor2 != null) {
                                cursor2.close();
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        cursor2 = query;
                        th = th3;
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        throw th;
                    }
                } else if (this.f3577c && query == null) {
                    throw new IllegalArgumentException(C1814b.fF);
                }
                if (query != null) {
                    query.close();
                }
            } catch (OutOfMemoryError e7) {
                query = null;
                if (query != null) {
                    query.close();
                }
                return linkedList2;
            } catch (Exception e8) {
                cursor = null;
                exception = e8;
                cursor = m5000b().m4997a(C1732a.f3563a, null, stringBuffer.toString(), null, C1732a.f3564b + C1814b.fG + HttpStatus.SC_MULTIPLE_CHOICES);
                if (cursor != null) {
                    if (cursor.getCount() > 0) {
                        linkedList = new LinkedList();
                        cursor.moveToPosition(-1);
                        while (cursor.moveToNext()) {
                            c1783g = new C1783g();
                            c1783g.m5134a(cursor);
                            linkedList.add(c1783g);
                        }
                        linkedList2 = linkedList;
                        if (cursor != null) {
                            cursor.close();
                        }
                        return linkedList2;
                    }
                }
                linkedList = linkedList2;
                linkedList2 = linkedList;
                if (cursor != null) {
                    cursor.close();
                }
                return linkedList2;
            } catch (Throwable th4) {
                th = th4;
                if (cursor2 != null) {
                    cursor2.close();
                }
                throw th;
            }
        }
        return linkedList2;
    }

    public final void m5005a(C1783g c1783g) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(C1814b.fM);
        int i = 0;
        for (C1783g c1783g2 = c1783g; c1783g2 != null; c1783g2 = c1783g2.f3742h) {
            i++;
            stringBuilder.append(C1814b.fN);
            stringBuilder.append(c1783g2.f3737c);
            stringBuilder.append(C1814b.fO);
            if (c1783g2.f3742h != null) {
                stringBuilder.append(C1814b.fP);
            }
        }
        stringBuilder.append(C1814b.fQ);
        String str = i > 1 ? C1732a.f3564b + C1814b.fR + stringBuilder.toString() : C1732a.f3564b + C1814b.fS + c1783g.f3737c + C1814b.fT;
        try {
            this.f3575a.getContentResolver().delete(new Builder().scheme(FirebaseAnalytics$Param.CONTENT).authority(C1816d.m5329t(this.f3575a)).appendPath(C1730j.f3550a).build(), str, null);
        } catch (Exception e) {
            try {
                m5000b().m4995a(C1732a.f3563a, str, null);
            } catch (Exception e2) {
                e.printStackTrace();
            }
        }
    }

    public final void m5006a(C1783g c1783g, C1734c c1734c) {
        Runnable c1733b = new C1733b();
        c1733b.m4991a(c1734c);
        c1733b.m4992a(new C1737f(this, c1783g));
        try {
            C1821i.m5346a(c1733b);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void m5007b(C1783g c1783g) {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(C1814b.fU + c1783g.f3737c + C1814b.fV);
            for (C1783g c1783g2 = c1783g.f3742h; c1783g2 != null; c1783g2 = c1783g2.f3742h) {
                stringBuffer.append(C1814b.fW + c1783g2.f3737c + C1814b.fX);
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            String stringBuffer2 = stringBuffer.toString();
            ContentValues contentValues = new ContentValues();
            contentValues.put(C1732a.f3569g, Boolean.valueOf(true));
            try {
                this.f3575a.getContentResolver().update(new Builder().scheme(FirebaseAnalytics$Param.CONTENT).authority(C1816d.m5329t(this.f3575a)).appendPath(C1730j.f3550a).build(), contentValues, C1814b.fY + stringBuffer2 + C1814b.fZ, null);
            } catch (Exception e) {
                try {
                    m5000b().m4994a(C1732a.f3563a, contentValues, C1814b.ga + stringBuffer2 + C1814b.gb, null);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        } catch (Exception e22) {
            e22.printStackTrace();
        }
    }
}
