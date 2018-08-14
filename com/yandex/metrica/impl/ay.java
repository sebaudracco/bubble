package com.yandex.metrica.impl;

import android.content.Context;
import android.os.Handler;
import android.os.RemoteException;
import com.yandex.metrica.IMetricaService;
import com.yandex.metrica.impl.C4511p.C4510a;
import com.yandex.metrica.impl.az.C4345c;
import com.yandex.metrica.impl.az.C4352d;
import com.yandex.metrica.impl.ob.dw;
import com.yandex.metrica.impl.utils.C4525g;
import com.yandex.metrica.impl.utils.C4529j;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ay implements C4347s {
    private final Context f11709a;
    private ad f11710b;
    private final NativeCrashesHelper f11711c;
    private final ExecutorService f11712d;
    private C4544z f11713e;
    private C4516u f11714f;
    private dw f11715g;
    private final az f11716h = new az(this);

    class C43461 implements C4345c {
        final /* synthetic */ Map f11707a;
        final /* synthetic */ aw f11708b;

        C43461(Map map, aw awVar) {
            this.f11707a = map;
            this.f11708b = awVar;
        }

        public C4372h mo7012a(C4372h c4372h) {
            return ay.m14742c(c4372h.mo7032c(C4525g.m16271a(this.f11707a)), this.f11708b);
        }
    }

    ay(ExecutorService executorService, Context context, Handler handler) {
        this.f11710b = new ad(context, handler);
        this.f11712d = executorService;
        this.f11709a = context;
        this.f11711c = new NativeCrashesHelper(context);
        this.f11714f = new C4516u(context);
    }

    void m14750a(C4544z c4544z) {
        this.f11713e = c4544z;
    }

    void m14749a(dw dwVar) {
        this.f11715g = dwVar;
        this.f11714f.m14715b(dwVar);
    }

    void m14748a(C4382j c4382j) {
        this.f11714f.m14709a(c4382j);
    }

    void m14757a(boolean z, aw awVar) {
        awVar.m14714b().m14255b(z);
        this.f11711c.m14447a(z);
    }

    void m14752a(String str, aw awVar) {
        C4529j.m16280f().m16243a("Error received: native");
        m14747a(C4511p.m16194a(C4510a.EVENT_TYPE_NATIVE_CRASH, str), awVar);
    }

    void m14751a(String str) {
        m14752a(str, this.f11713e.m14470d());
    }

    private static C4372h m14742c(C4372h c4372h, aw awVar) {
        if (c4372h.m15062c() == C4510a.EVENT_TYPE_EXCEPTION_USER.m16188a()) {
            c4372h.m15069e(awVar.m14719f());
        }
        return c4372h;
    }

    void m14747a(C4372h c4372h, aw awVar) {
        m14744a(m14742c(c4372h, awVar), awVar, null);
    }

    public Future<Void> m14744a(C4372h c4372h, aw awVar, Map<String, Object> map) {
        this.f11710b.m14496c();
        C4352d c4352d = new C4352d(c4372h, awVar);
        if (!bk.m14988a((Map) map)) {
            c4352d.m14777a(new C43461(map, awVar));
        }
        return m14740a(c4352d);
    }

    public void m14761c() {
        m14747a(C4511p.m16208d(C4510a.EVENT_TYPE_STARTUP), this.f11714f);
    }

    public void m14760b(String str) {
        m14747a(C4511p.m16209d(str), this.f11714f);
    }

    public void m14746a(aw awVar) {
        m14747a(C4511p.m16193a(awVar.m14720g()), awVar);
    }

    public void m14755a(List<String> list) {
        this.f11714f.m14714b().m14249a((List) list);
    }

    public void m14756a(Map<String, String> map) {
        this.f11714f.m14714b().m14250a((Map) map);
    }

    public void m14763c(String str) {
        this.f11714f.m14714b().m14274h(str);
    }

    void m14754a(Throwable th, aw awVar) {
        String str;
        if (awVar.m14714b().m14235C()) {
            C4529j.m16280f().m16243a("Error received: uncaught");
        }
        this.f11710b.m14496c();
        String a = bk.m14975a(null, th);
        if (th == null) {
            str = "";
        } else {
            str = th.getClass().getName();
        }
        C4372h c = C4511p.m16205c(str, a);
        c.m15069e(awVar.m14719f());
        try {
            m14740a(new C4352d(c, awVar).m14778a(true)).get();
        } catch (InterruptedException e) {
        } catch (ExecutionException e2) {
        }
    }

    void m14764d() {
        this.f11710b.m14496c();
    }

    void m14765e() {
        this.f11710b.m14495b();
    }

    public void mo7014a(IMetricaService iMetricaService, C4372h c4372h, aw awVar) throws RemoteException {
        m14762c(awVar);
        if (awVar.m14714b().m14279l()) {
            this.f11711c.m14446a(this, this.f11712d);
        }
        iMetricaService.reportData(c4372h.m15052a(awVar.mo7025c()));
        if (this.f11713e == null || this.f11713e.m14472e()) {
            this.f11710b.m14495b();
        }
    }

    public void m14753a(String str, String str2, aw awVar) {
        m14740a(new C4352d(new C4372h().m15053a(C4510a.EVENT_TYPE_APP_ENVIRONMENT_UPDATED.m16188a()).m15057a(str, str2), awVar));
    }

    public void m14759b(aw awVar) {
        m14740a(new C4352d(new C4372h().m15053a(C4510a.EVENT_TYPE_APP_ENVIRONMENT_CLEARED.m16188a()), awVar));
    }

    void m14762c(aw awVar) {
        if (awVar.m14714b().m14235C()) {
            awVar.m14714b().m14266e(C4529j.m16280f().m16247b());
        }
    }

    private Future<Void> m14740a(C4352d c4352d) {
        c4352d.m14776a().m14710a(this.f11715g);
        return this.f11716h.m14785a(c4352d);
    }

    public ad mo7013a() {
        return this.f11710b;
    }

    public Context mo7015b() {
        return this.f11709a;
    }
}
