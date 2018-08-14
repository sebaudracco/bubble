package com.facebook.ads.internal.p071p.p073b.p074a;

import java.io.File;

public class C2073g extends C2071e {
    private final long f4911a;

    public C2073g(long j) {
        if (j <= 0) {
            throw new IllegalArgumentException("Max size must be positive number!");
        }
        this.f4911a = j;
    }

    public /* bridge */ /* synthetic */ void mo3752a(File file) {
        super.mo3752a(file);
    }

    protected boolean mo3754a(File file, long j, int i) {
        return j <= this.f4911a;
    }
}
