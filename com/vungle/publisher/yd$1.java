package com.vungle.publisher;

import com.vungle.publisher.log.Logger;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.functions.Func1;

/* compiled from: vungle */
class yd$1 implements Func1<Integer, Observable<Long>> {
    final /* synthetic */ String f11334a;
    final /* synthetic */ yd f11335b;

    yd$1(yd ydVar, String str) {
        this.f11335b = ydVar;
        this.f11334a = str;
    }

    public /* synthetic */ Object call(Object obj) {
        return m14144a((Integer) obj);
    }

    public Observable<Long> m14144a(Integer num) {
        int a = zn.m14224a(num.intValue(), 2000, 300000);
        Logger.m13635d(Logger.PREPARE_TAG, "retry in " + a + " millis - " + this.f11334a);
        return Observable.timer((long) a, TimeUnit.MILLISECONDS, this.f11335b.a);
    }
}
