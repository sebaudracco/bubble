package com.yandex.metrica.impl.ob;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.yandex.metrica.impl.C4514r;
import com.yandex.metrica.impl.bk;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class bw implements bv {
    private final Context f11988a;
    private final String f11989b;
    private File f11990c;
    private bo f11991d;
    private FileLock f11992e;
    private RandomAccessFile f11993f;
    private FileChannel f11994g;

    public bw(Context context, String str) {
        this.f11988a = context;
        this.f11989b = str;
    }

    public synchronized SQLiteDatabase mo7058a() {
        SQLiteDatabase writableDatabase;
        try {
            this.f11990c = new File(this.f11988a.getFilesDir(), new File(this.f11989b).getName() + ".lock");
            this.f11993f = new RandomAccessFile(this.f11990c, "rw");
            this.f11994g = this.f11993f.getChannel();
            this.f11992e = this.f11994g.lock();
            this.f11991d = new bo(this.f11988a, this.f11989b, bm.m15323c());
            writableDatabase = this.f11991d.getWritableDatabase();
        } catch (Exception e) {
            writableDatabase = null;
        }
        return writableDatabase;
    }

    public synchronized void mo7059a(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase != null) {
            try {
                sQLiteDatabase.close();
            } catch (Exception e) {
            }
        }
        bk.m14980a(this.f11991d);
        this.f11990c.getAbsolutePath();
        C4514r.m16220a(this.f11992e);
        bk.m14980a(this.f11993f);
        bk.m14980a(this.f11994g);
        this.f11991d = null;
        this.f11993f = null;
        this.f11992e = null;
        this.f11994g = null;
    }
}
