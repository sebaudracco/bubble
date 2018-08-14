package com.vungle.publisher;

import com.vungle.publisher.log.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONObject;
import rx.Observable;
import rx.functions.Func1;

@Singleton
/* compiled from: vungle */
public class yg$a implements Func1<wd, Observable<wc>> {
    @Inject
    wg$a f3476a;
    @Inject
    wj$a f3477b;
    @Inject
    wr$a f3478c;

    public /* synthetic */ Object call(Object obj) {
        return m4902b((wd) obj);
    }

    @Inject
    yg$a() {
    }

    protected wc m4901a(wd wdVar) {
        boolean n = wdVar.n();
        m d = wdVar.d();
        if (!n) {
            return null;
        }
        if (wdVar.k()) {
            Logger.i(Logger.PREPARE_TAG, "received expired ad from server, tossing it and getting a new one");
            throw new RuntimeException("ad is expired");
        } else if (d == null) {
            Logger.w(Logger.PREPARE_TAG, "received null adType from server, tossing it and getting a new one");
            throw new RuntimeException("adType is null");
        } else {
            Logger.v(Logger.PREPARE_TAG, "received a valid ad, continue processing ad with type: " + d);
            return m4900a(d, wdVar.o());
        }
    }

    private wc m4900a(m mVar, JSONObject jSONObject) {
        return (wc) new 1(this, jSONObject).a(mVar);
    }

    public Observable<wc> m4902b(wd wdVar) {
        return Observable.just(m4901a(wdVar));
    }
}
