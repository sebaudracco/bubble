package com.vungle.publisher;

import rx.exceptions.Exceptions;
import rx.functions.Func2;

/* compiled from: vungle */
class yd$2 implements Func2<Throwable, Integer, Integer> {
    final /* synthetic */ int f11336a;
    final /* synthetic */ yd f11337b;

    yd$2(yd ydVar, int i) {
        this.f11337b = ydVar;
        this.f11336a = i;
    }

    public /* synthetic */ Object call(Object obj, Object obj2) {
        return m14145a((Throwable) obj, (Integer) obj2);
    }

    public Integer m14145a(Throwable th, Integer num) {
        if (num.intValue() < this.f11336a) {
            return num;
        }
        throw Exceptions.propagate(th);
    }
}
