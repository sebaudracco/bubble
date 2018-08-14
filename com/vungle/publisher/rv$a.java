package com.vungle.publisher;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class rv$a {
    @Inject
    rv$a() {
    }

    public rv m4631a(String str) {
        for (rv rvVar : rv.values()) {
            if (rv.a(rvVar).equals(str)) {
                return rvVar;
            }
        }
        return rv.t;
    }
}
