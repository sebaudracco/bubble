package com.vungle.publisher;

import android.os.Bundle;
import com.vungle.publisher.lb.C1650a;
import com.vungle.publisher.li.C1651a;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class lf$b {
    @Inject
    C1650a f3071a;
    @Inject
    C1651a f3072b;

    @Inject
    lf$b() {
    }

    public lf m4332a(Bundle bundle) {
        if (bundle.containsKey("webViewRootContentIndexFile")) {
            return this.f3071a.m4327a(bundle);
        }
        if (bundle.containsKey("webViewRootContentString")) {
            return this.f3072b.m4333a(bundle);
        }
        return null;
    }
}
