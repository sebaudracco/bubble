package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0449u;
import com.p000a.p001a.C0452t;
import com.p000a.p001a.C0552e;
import com.p000a.p001a.C0556i;
import com.p000a.p001a.C0562q;
import com.p000a.p001a.p002a.C0444b;
import com.p000a.p001a.p003b.C0512c;
import com.p000a.p001a.p007c.C0542a;

public final class C0458d implements C0449u {
    private final C0512c f26a;

    public C0458d(C0512c c0512c) {
        this.f26a = c0512c;
    }

    C0452t<?> m36a(C0512c c0512c, C0552e c0552e, C0542a<?> c0542a, C0444b c0444b) {
        C0452t<?> c0452t;
        Object a = c0512c.m348a(C0542a.m402b(c0444b.m5a())).mo1830a();
        if (a instanceof C0452t) {
            c0452t = (C0452t) a;
        } else if (a instanceof C0449u) {
            c0452t = ((C0449u) a).mo1792a(c0552e, c0542a);
        } else if ((a instanceof C0562q) || (a instanceof C0556i)) {
            c0452t = new C0482l(a instanceof C0562q ? (C0562q) a : null, a instanceof C0556i ? (C0556i) a : null, c0552e, c0542a, null);
        } else {
            throw new IllegalArgumentException("Invalid attempt to bind an instance of " + a.getClass().getName() + " as a @JsonAdapter for " + c0542a.toString() + ". @JsonAdapter value must be a TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer.");
        }
        return (c0452t == null || !c0444b.m6b()) ? c0452t : c0452t.m20a();
    }

    public <T> C0452t<T> mo1792a(C0552e c0552e, C0542a<T> c0542a) {
        C0444b c0444b = (C0444b) c0542a.m403a().getAnnotation(C0444b.class);
        return c0444b == null ? null : m36a(this.f26a, c0552e, c0542a, c0444b);
    }
}
