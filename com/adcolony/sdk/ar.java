package com.adcolony.sdk;

import android.os.StatFs;
import com.adcolony.sdk.aa.C0595a;
import java.io.File;

class ar {
    private String f663a;
    private String f664b;
    private String f665c;
    private String f666d;
    private File f667e;
    private File f668f;
    private File f669g;

    ar() {
    }

    boolean m781a() {
        new C0595a().m622a("Configuring storage").m624a(aa.f480d);
        C0740l a = C0594a.m605a();
        this.f663a = m783c() + "/adc3/";
        this.f664b = this.f663a + "media/";
        this.f667e = new File(this.f664b);
        if (!this.f667e.isDirectory()) {
            this.f667e.delete();
            this.f667e.mkdirs();
        }
        if (!this.f667e.isDirectory()) {
            a.m1259a(true);
            return false;
        } else if (m780a(this.f664b) < 2.097152E7d) {
            new C0595a().m622a("Not enough memory available at media path, disabling AdColony.").m624a(aa.f481e);
            a.m1259a(true);
            return false;
        } else {
            this.f665c = m783c() + "/adc3/data/";
            this.f668f = new File(this.f665c);
            if (!this.f668f.isDirectory()) {
                this.f668f.delete();
            }
            this.f668f.mkdirs();
            this.f666d = this.f663a + "tmp/";
            this.f669g = new File(this.f666d);
            if (!this.f669g.isDirectory()) {
                this.f669g.delete();
                this.f669g.mkdirs();
            }
            return true;
        }
    }

    boolean m782b() {
        if (this.f667e == null || this.f668f == null || this.f669g == null) {
            return false;
        }
        if (!this.f667e.isDirectory()) {
            this.f667e.delete();
        }
        if (!this.f668f.isDirectory()) {
            this.f668f.delete();
        }
        if (!this.f669g.isDirectory()) {
            this.f669g.delete();
        }
        this.f667e.mkdirs();
        this.f668f.mkdirs();
        this.f669g.mkdirs();
        return true;
    }

    String m783c() {
        if (C0594a.m614d()) {
            return C0594a.m613c().getFilesDir().getAbsolutePath();
        }
        return "";
    }

    double m780a(String str) {
        try {
            StatFs statFs = new StatFs(str);
            return (double) (((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize()));
        } catch (RuntimeException e) {
            return 0.0d;
        }
    }

    String m784d() {
        return this.f664b;
    }

    String m785e() {
        return this.f665c;
    }

    String m786f() {
        return this.f666d;
    }

    String m787g() {
        return this.f663a;
    }
}
