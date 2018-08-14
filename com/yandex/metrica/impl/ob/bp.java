package com.yandex.metrica.impl.ob;

import android.content.Context;
import com.yandex.metrica.impl.bk;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class bp {
    private static volatile bp f11965a;
    private final Map<String, bo> f11966b = new HashMap();
    private final Map<String, bq> f11967c = new HashMap();
    private final Context f11968d;
    private bo f11969e;
    private bq f11970f;
    private bq f11971g;
    private bq f11972h;
    private br f11973i;

    public static bp m15358a(Context context) {
        if (f11965a == null) {
            synchronized (bp.class) {
                if (f11965a == null) {
                    f11965a = new bp(context);
                }
            }
        }
        return f11965a;
    }

    public bp(Context context) {
        this.f11968d = context;
    }

    public synchronized bo m15362a(C4493r c4493r) {
        bo boVar;
        String str = "db_metrica_" + c4493r;
        boVar = (bo) this.f11966b.get(str);
        if (boVar == null) {
            boVar = m15363a(str, bm.m15321a());
            this.f11966b.put(str, boVar);
        }
        return boVar;
    }

    public synchronized bo m15361a() {
        if (this.f11969e == null) {
            this.f11969e = m15363a("metrica_data.db", bm.m15322b());
        }
        return this.f11969e;
    }

    public synchronized bq m15365b(C4493r c4493r) {
        bq bqVar;
        String c4493r2 = c4493r.toString();
        bqVar = (bq) this.f11967c.get(c4493r2);
        if (bqVar == null) {
            bqVar = new bq(m15362a(c4493r), "preferences");
            this.f11967c.put(c4493r2, bqVar);
        }
        return bqVar;
    }

    public synchronized bq m15364b() {
        if (this.f11970f == null) {
            this.f11970f = new bq(m15361a(), "preferences");
        }
        return this.f11970f;
    }

    public synchronized br m15366c() {
        if (this.f11973i == null) {
            this.f11973i = new br(m15361a(), "permissions");
        }
        return this.f11973i;
    }

    public synchronized bq m15367d() {
        if (this.f11971g == null) {
            this.f11971g = new bq(m15361a(), "startup");
        }
        return this.f11971g;
    }

    public synchronized bq m15368e() {
        if (this.f11972h == null) {
            this.f11972h = new bq("preferences", new bw(this.f11968d, m15359a("metrica_client_data.db")));
        }
        return this.f11972h;
    }

    bo m15363a(String str, bs bsVar) {
        return new bo(this.f11968d, m15359a(str), bsVar);
    }

    private String m15359a(String str) {
        if (bk.m14985a(21)) {
            return m15360b(str);
        }
        return str;
    }

    private String m15360b(String str) {
        try {
            File noBackupFilesDir = this.f11968d.getNoBackupFilesDir();
            File file = new File(noBackupFilesDir, str);
            if (!file.exists()) {
                File databasePath = this.f11968d.getDatabasePath(str);
                if (databasePath.exists() && databasePath.renameTo(file)) {
                    String str2 = str + "-journal";
                    this.f11968d.getDatabasePath(str2).renameTo(new File(noBackupFilesDir, str2));
                }
            }
            str = file.getAbsolutePath();
        } catch (Exception e) {
        }
        return str;
    }
}
