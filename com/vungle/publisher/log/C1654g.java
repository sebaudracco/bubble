package com.vungle.publisher.log;

import android.content.Context;
import android.os.Build.VERSION;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.vungle.publisher.env.AndroidDevice;
import com.vungle.publisher.env.C1613o;
import com.vungle.publisher.sv;
import com.vungle.publisher.vc;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class C1654g {
    private static String f3082j = "VungleSDKLog";
    public Logger f3083a;
    public Logger f3084b;
    public Logger f3085c;
    @Inject
    Context f3086d;
    @Inject
    C1653d f3087e;
    @Inject
    C1652a f3088f;
    @Inject
    AndroidDevice f3089g;
    @Inject
    sv f3090h;
    @Inject
    C1613o f3091i;

    @Inject
    C1654g() {
    }

    public void m4348a() {
        this.f3083a = Logger.getLogger("SDK Initialization");
        this.f3083a.addHandler(m4347a(this.f3086d));
        this.f3084b = Logger.getLogger("Ad Lifecycle");
        this.f3084b.addHandler(m4347a(this.f3086d));
        this.f3085c = Logger.getLogger("Vungle Network");
        this.f3085c.addHandler(m4347a(this.f3086d));
    }

    public FileHandler m4347a(Context context) {
        try {
            FileHandler fileHandler = new FileHandler(new File(context.getCacheDir(), f3082j).getAbsolutePath(), 1048576, 20);
            try {
                fileHandler.setFormatter(this.f3087e);
                return fileHandler;
            } catch (IOException e) {
                return fileHandler;
            }
        } catch (IOException e2) {
            return null;
        }
    }

    public void m4350a(boolean z) {
        if (!z || this.f3089g.m3883j()) {
            Logger.getLogger("global").setLevel(Level.OFF);
            for (File file : this.f3086d.getCacheDir().listFiles()) {
                if (file.getName().startsWith(f3082j) && !file.getName().endsWith(".csv")) {
                    file.delete();
                }
            }
            return;
        }
        Logger.getLogger("global").setLevel(Level.ALL);
    }

    public void m4349a(vc vcVar) {
        if (VERSION.SDK_INT >= 16) {
            if (this.f3090h.m4649b()) {
                return;
            }
        } else if (!(C1404b.f2124b.equals(this.f3090h.m4648a()) && this.f3091i.m3939i())) {
            return;
        }
        try {
            String absolutePath = this.f3086d.getCacheDir().getAbsolutePath();
            File cacheDir = this.f3086d.getCacheDir();
            HashSet hashSet = new HashSet();
            hashSet.addAll(Arrays.asList(cacheDir.list()));
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                if (!(!str.startsWith(f3082j) || str.endsWith(".lck") || str.endsWith(".csv") || hashSet.contains(str + ".lck"))) {
                    File file = new File(absolutePath + File.separator + str);
                    cacheDir = new File(file.getAbsolutePath() + ".csv");
                    if (file.exists() && file.renameTo(cacheDir)) {
                        vcVar.m4730a(cacheDir.getAbsolutePath(), this.f3088f.m4345a(cacheDir));
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}
