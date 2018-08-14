package com.yandex.metrica.impl;

import com.yandex.metrica.IReporter;
import com.yandex.metrica.impl.C4342i.C4365a;

public class av extends C4342i {
    private final IReporter f11694a;

    av(IReporter iReporter, C4365a c4365a) {
        super(c4365a);
        this.f11694a = iReporter;
    }

    void mo7011b(Throwable th) {
        this.f11694a.reportUnhandledException(th);
    }
}
