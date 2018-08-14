package com.vungle.publisher;

import com.vungle.publisher.log.Logger;
import com.vungle.publisher.qu.a;
import java.io.File;
import java.util.List;

/* compiled from: vungle */
class ej$1 implements a {
    final /* synthetic */ List f10008a;
    final /* synthetic */ ej f10009b;

    ej$1(ej ejVar, List list) {
        this.f10009b = ejVar;
        this.f10008a = list;
    }

    public void m13125a(File file, String str, long j) {
        Logger.v(Logger.PREPARE_TAG, "extracted " + file + ": " + j + " bytes");
        this.f10008a.add(this.f10009b.e.a(ej.a(this.f10009b), file, str, (int) j));
    }
}
