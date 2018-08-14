package com.vungle.publisher;

/* compiled from: vungle */
public abstract class ad<A extends cn> extends ph implements ac<A> {
    protected final A f9768b;
    protected final String f9769c;

    protected ad(A a, String str, long j) {
        super(j);
        this.f9768b = a;
        this.f9769c = str;
    }

    protected ad(A a, String str) {
        this.f9768b = a;
        this.f9769c = str;
    }

    public A mo6932b() {
        return this.f9768b;
    }

    public String mo6933c() {
        return this.f9769c;
    }
}
