package com.vungle.publisher;

import com.vungle.publisher.cn.c;
import com.vungle.publisher.eb.C1603b;
import com.vungle.publisher.log.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;
import rx.exceptions.Exceptions;
import rx.functions.Func1;

@Singleton
/* compiled from: vungle */
public class xd implements Func1<wc, Observable<dr<cn>>> {
    @Inject
    cn$b f3429a;
    @Inject
    C1603b f3430b;

    public /* synthetic */ Object call(Object obj) {
        return m4846a((wc) obj);
    }

    @Inject
    xd() {
    }

    public Observable<dr<cn>> m4846a(wc wcVar) {
        Object obj;
        Throwable e;
        ea a = this.f3429a.m3559a(wcVar.d());
        String str = Logger.PREPARE_TAG;
        String g = wcVar.g();
        dr a2 = a.a(g, true);
        if (a2 != null) {
            try {
                a.b(a2.m_(), wcVar);
            } catch (Throwable e2) {
                Logger.w(Logger.PREPARE_TAG, "error updating ad " + g, e2);
            }
            c g2 = a2.g();
            g = "received " + a2.B() + " in " + "status" + " " + g2;
            dr drVar;
            switch (1.a[g2.ordinal()]) {
                case 1:
                case 2:
                case 3:
                case 4:
                    Logger.i(Logger.PREPARE_TAG, g);
                    drVar = a2;
                    break;
                case 5:
                case 6:
                    Logger.w(Logger.PREPARE_TAG, g + " - retrying");
                    throw new RuntimeException("received invalid ad in status: " + g2);
                default:
                    Logger.i(Logger.PREPARE_TAG, g + " - ignoring");
                    drVar = a2;
                    break;
            }
        }
        try {
            obj = (dr) a.a(wcVar);
            try {
                Logger.i(Logger.PREPARE_TAG, "received new " + obj.B());
                obj.m_().d_();
            } catch (Exception e3) {
                e = e3;
                Logger.e(Logger.PREPARE_TAG, "error preparing ad " + g + ", retrying", e);
                Exceptions.propagate(e);
                return Observable.just(obj);
            }
        } catch (Throwable e22) {
            Throwable th = e22;
            obj = a2;
            e = th;
            Logger.e(Logger.PREPARE_TAG, "error preparing ad " + g + ", retrying", e);
            Exceptions.propagate(e);
            return Observable.just(obj);
        }
        return Observable.just(obj);
    }
}
