package com.facebook.ads.internal.p071p.p073b;

import com.facebook.ads.internal.p071p.p073b.p074a.C2063a;
import com.facebook.ads.internal.p071p.p073b.p074a.C2066c;
import java.io.File;

class C2075c {
    public final File f4912a;
    public final C2066c f4913b;
    public final C2063a f4914c;

    C2075c(File file, C2066c c2066c, C2063a c2063a) {
        this.f4912a = file;
        this.f4913b = c2066c;
        this.f4914c = c2063a;
    }

    File m6663a(String str) {
        return new File(this.f4912a, this.f4913b.mo3753a(str));
    }
}
