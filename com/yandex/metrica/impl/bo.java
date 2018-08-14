package com.yandex.metrica.impl;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.yandex.metrica.C4276b;
import com.yandex.metrica.C4295e;
import com.yandex.metrica.DeferredDeeplinkParametersListener;
import com.yandex.metrica.IIdentifierCallback;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.impl.C4342i.C4365a;
import com.yandex.metrica.impl.C4382j.C4368a;
import com.yandex.metrica.impl.GoogleAdvertisingIdGetter.C4301b;
import com.yandex.metrica.impl.ax.C4344a;
import com.yandex.metrica.impl.ob.bp;
import com.yandex.metrica.impl.ob.bz;
import com.yandex.metrica.impl.ob.dv;
import com.yandex.metrica.impl.utils.C4527h.C4526a;
import com.yandex.metrica.impl.utils.C4529j;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class bo implements C4368a {
    private static bo f11824a;
    private static C4387n f11825b = new C4387n();
    private final Context f11826c;
    private final ax f11827d;
    private C4544z f11828e;
    private ah f11829f;
    private final ExecutorService f11830g = Executors.newSingleThreadExecutor();
    private final dv f11831h;
    private final ar f11832i;
    private C4378g f11833j;
    private C4342i f11834k;

    class C43661 implements C4365a {
        final /* synthetic */ bo f11823a;

        C43661(bo boVar) {
            this.f11823a = boVar;
        }

        public boolean mo7028a(Throwable th) {
            return this.f11823a.f11828e.m16334f();
        }
    }

    class C43672 implements C4365a {
        C43672() {
        }

        public boolean mo7028a(Throwable th) {
            String str = "com.yandex.metrica";
            Object a = bk.m14976a(th);
            return !TextUtils.isEmpty(a) && a.contains("at " + str + ".");
        }
    }

    private bo(Context context, String str) {
        StringBuilder stringBuilder = new StringBuilder("Initializing of Metrica");
        stringBuilder.append(", Release type");
        stringBuilder.append(", Version 2.73");
        stringBuilder.append(", API Level 58");
        stringBuilder.append(", Dated 15.06.2017.");
        Log.i(C4529j.m16280f().mo7121d(), stringBuilder.toString());
        C4529j.m16279a(context);
        this.f11826c = context.getApplicationContext();
        C4301b.f11553a.m14437a(this.f11826c);
        Handler handler = new Handler(Looper.getMainLooper());
        ay ayVar = new ay(this.f11830g, this.f11826c, handler);
        bz bzVar = new bz(bp.m15358a(this.f11826c).m15368e());
        new C4376f(bzVar).m14557a(this.f11826c);
        this.f11831h = new dv(ayVar, str, bzVar);
        ayVar.m14749a(this.f11831h);
        this.f11832i = new ar(ayVar, bzVar);
        C4382j c4382j = new C4382j(handler);
        c4382j.m15105a(this);
        ayVar.m14748a(c4382j);
        this.f11827d = new C4344a().m14721a(this.f11826c).m14725a(this.f11831h).m14723a(ayVar).m14722a(handler).m14724a(c4382j).m14726a();
        if (bc.m14875b()) {
            this.f11833j = new C4378g(bzVar, new C4369c(this.f11826c), this.f11830g);
        }
    }

    void m15028a() {
        ah ahVar = new ah(Thread.getDefaultUncaughtExceptionHandler());
        ahVar.m14552a(new av(this.f11827d.m14733a("20799a27-fa80-4b36-b2db-0f8141f24180"), new C43672()));
        this.f11829f = ahVar;
        Thread.setDefaultUncaughtExceptionHandler(this.f11829f);
    }

    public static synchronized void m15012a(Context context, C4295e c4295e) {
        synchronized (bo.class) {
            boolean i = f11825b.m15134i();
            C4295e a = f11825b.m15118a(c4295e);
            m15018b(context, a);
            if (f11824a.f11828e == null) {
                if (Boolean.TRUE.equals(a.isLogEnabled())) {
                    C4529j.m16280f().m16240a();
                }
                bo boVar = f11824a;
                boVar.f11828e = boVar.f11827d.m14734a(a, i);
                m15015a(boVar.f11828e.m14470d().m14714b().m14278k());
            } else {
                f11824a.f11828e.m16321a(a, i);
            }
            ((C4276b) YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180")).mo6971a(1);
        }
    }

    public static synchronized void m15011a(Context context) {
        synchronized (bo.class) {
            m15018b(context, null);
        }
    }

    public static synchronized void m15018b(Context context, C4295e c4295e) {
        synchronized (bo.class) {
            bk.m14981a((Object) context, "App Context");
            if (f11824a == null) {
                bo boVar = new bo(context.getApplicationContext(), c4295e != null ? c4295e.m14411a() : null);
                f11824a = boVar;
                C4539v.m16296a(boVar.f11826c);
                if (c4295e != null) {
                    boVar.f11831h.m15803a(c4295e.m14413c());
                    boVar.f11831h.m15804a(c4295e.m14416f());
                    boVar.f11831h.m15802a(c4295e.m14417g());
                }
                boVar.f11831h.m15808d();
                boVar.f11830g.execute(new C4526a(boVar.f11826c));
                f11824a.m15028a();
            }
        }
    }

    public static synchronized bo m15016b() {
        bo boVar;
        synchronized (bo.class) {
            if (f11824a == null) {
                throw bl.f11815a;
            }
            boVar = f11824a;
        }
        return boVar;
    }

    public static bo m15017b(Context context) {
        m15011a(context);
        return m15016b();
    }

    public static synchronized C4544z m15020c() {
        C4544z c4544z;
        synchronized (bo.class) {
            bo b = m15016b();
            if (b.f11828e == null) {
                throw bl.f11815a;
            }
            c4544z = b.f11828e;
        }
        return c4544z;
    }

    static synchronized boolean m15024d() {
        boolean z;
        synchronized (bo.class) {
            z = (f11824a == null || f11824a.f11828e == null) ? false : true;
        }
        return z;
    }

    public C4276b m15027a(String str) {
        return this.f11827d.m14733a(str);
    }

    public void m15032b(String str) {
        this.f11832i.m14657a(str);
    }

    private static ac m15026g() {
        return m15024d() ? m15016b().f11828e : f11825b;
    }

    public static void m15010a(int i) {
        m15026g().setSessionTimeout(i);
    }

    public static void m15015a(boolean z) {
        if (m15024d()) {
            bo b = m15016b();
            if (z) {
                if (b.f11834k == null) {
                    b.f11834k = new av(b.f11828e, new C43661(b));
                }
                b.f11829f.m14552a(b.f11834k);
            } else {
                b.f11829f.m14553b(b.f11834k);
            }
            b.f11828e.m16330c(z);
            return;
        }
        f11825b.m15127c(z);
    }

    public static void m15019b(boolean z) {
        m15026g().mo7039d(z);
    }

    public static void m15013a(Location location) {
        m15026g().mo7034a(location);
    }

    public static void m15022c(boolean z) {
        m15026g().mo7038b(z);
    }

    public static void m15021c(String str) {
        m15026g().mo7035a(str);
    }

    public static void m15023d(boolean z) {
        m15026g().mo7037a(z);
    }

    public static boolean m15025e() {
        return m15026g().mo7040h();
    }

    public static void m15014a(String str, String str2) {
        m15026g().mo7036a(str, str2);
    }

    public String m15033f() {
        return this.f11831h.mo7080a();
    }

    public void m15031a(IIdentifierCallback iIdentifierCallback) {
        this.f11831h.m15801a(iIdentifierCallback);
    }

    public void mo7029a(int i, Bundle bundle) {
        switch (i) {
            case 1:
                this.f11831h.m15800a(bundle);
                if (this.f11833j != null) {
                    this.f11833j.m15100a();
                    return;
                }
                return;
            case 2:
                this.f11831h.m15806b(bundle);
                return;
            default:
                return;
        }
    }

    public void m15030a(DeferredDeeplinkParametersListener deferredDeeplinkParametersListener) {
        this.f11832i.m14656a(deferredDeeplinkParametersListener);
    }
}
