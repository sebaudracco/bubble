package com.vungle.publisher;

/* compiled from: vungle */
public abstract class C4239q<R> {
    protected abstract R mo6946a();

    protected abstract R mo6947b();

    protected abstract R mo6948c();

    protected abstract R mo6949d();

    public R m13680a(C4238m c4238m) {
        switch (c4238m) {
            case vungle_local:
                return mo6946a();
            case vungle_streaming:
                return mo6947b();
            case vungle_mraid:
                return mo6948c();
            case third_party_mraid:
                return mo6949d();
            default:
                throw new IllegalArgumentException("unknown ad type " + c4238m);
        }
    }

    public R m13679a(cn cnVar) {
        return m13680a(cnVar.a_());
    }
}
