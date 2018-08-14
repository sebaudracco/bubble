package com.facebook.ads.internal.adapters;

import android.content.Context;
import com.facebook.ads.internal.p056q.p057a.C2110d;
import com.facebook.ads.internal.p070r.C2154a;
import java.util.HashMap;
import java.util.Map;

public abstract class C1884b {
    protected final C1885c f4162a;
    protected final C2154a f4163b;
    private final Context f4164c;
    private boolean f4165d;

    public C1884b(Context context, C1885c c1885c, C2154a c2154a) {
        this.f4164c = context;
        this.f4162a = c1885c;
        this.f4163b = c2154a;
    }

    public final void m5670a() {
        if (!this.f4165d) {
            if (this.f4162a != null) {
                this.f4162a.mo3673a();
            }
            Map hashMap = new HashMap();
            if (this.f4163b != null) {
                this.f4163b.m6919a(hashMap);
            }
            mo3624a(hashMap);
            this.f4165d = true;
            C2110d.m6772a(this.f4164c, "Impression logged");
            if (this.f4162a != null) {
                this.f4162a.mo3727b();
            }
        }
    }

    protected abstract void mo3624a(Map<String, String> map);
}
