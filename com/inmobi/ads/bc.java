package com.inmobi.ads;

import android.content.ContentValues;
import com.inmobi.commons.core.p116c.C3115b;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: PlacementDao */
public class bc {
    public static final String[] f7233a = new String[]{"id", "placement_id", "tp_key", "last_accessed_ts"};
    private static final String f7234b = bc.class.getSimpleName();
    private static bc f7235c;
    private static Object f7236d = new Object();

    public static bc m9478a() {
        bc bcVar = f7235c;
        if (bcVar == null) {
            synchronized (f7236d) {
                bcVar = f7235c;
                if (bcVar == null) {
                    f7235c = new bc();
                    bcVar = f7235c;
                }
            }
        }
        return bcVar;
    }

    private bc() {
        C3115b a = C3115b.m10131a();
        a.m10136a("placement", "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, placement_id INTEGER NOT NULL,tp_key TEXT,last_accessed_ts INTEGER NOT NULL,UNIQUE(placement_id,tp_key))");
        a.m10140b();
    }

    public int m9479a(long j) {
        C3115b a = C3115b.m10131a();
        long currentTimeMillis = System.currentTimeMillis() - (1000 * j);
        int a2 = a.m10133a("placement", "last_accessed_ts<?", new String[]{String.valueOf(currentTimeMillis)});
        Logger.m10359a(InternalLogLevel.INTERNAL, f7234b, "Deleted " + a2 + " expired pids from cache");
        a.m10140b();
        return a2;
    }

    public synchronized int m9480a(List<bb> list, int i) {
        int i2;
        int i3 = 0;
        synchronized (this) {
            if (list != null) {
                if (list.size() != 0) {
                    C3115b a = C3115b.m10131a();
                    for (int i4 = 0; i4 < list.size(); i4++) {
                        String[] strArr = new String[]{String.valueOf(((bb) list.get(i4)).m9475c()), ((bb) list.get(i4)).m9476d()};
                        a.m10135a("placement", ((bb) list.get(i4)).m9477e(), "placement_id = ? AND tp_key=?", strArr);
                    }
                    int a2 = a.m10132a("placement") - i;
                    if (a2 > 0) {
                        List a3 = a.m10134a("placement", new String[]{"id"}, null, null, null, null, "last_accessed_ts ASC", String.valueOf(a2));
                        String[] strArr2 = new String[a3.size()];
                        while (i3 < a3.size()) {
                            strArr2[i3] = String.valueOf(((ContentValues) a3.get(i3)).getAsInteger("id"));
                            i3++;
                        }
                        a.m10133a("placement", "id IN " + Arrays.toString(strArr2).replace("[", "(").replace("]", ")"), null);
                    }
                    a.m10140b();
                    i2 = a2;
                }
            }
            i2 = 0;
        }
        return i2;
    }

    public List<bb> m9481b() {
        List<bb> arrayList = new ArrayList();
        C3115b a = C3115b.m10131a();
        List<ContentValues> a2 = a.m10134a("placement", f7233a, null, null, null, null, null, null);
        a.m10140b();
        for (ContentValues bbVar : a2) {
            arrayList.add(new bb(bbVar));
        }
        return arrayList;
    }
}
