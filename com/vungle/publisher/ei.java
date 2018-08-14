package com.vungle.publisher;

/* compiled from: vungle */
public interface ei<A extends cn> extends gj<Integer> {

    /* compiled from: vungle */
    public enum C4173a {
        aware,
        downloaded,
        ready,
        failed
    }

    /* compiled from: vungle */
    public enum C4174b {
        localVideo,
        postRoll,
        streamingVideo,
        template,
        asset
    }

    void m13119a(C4173a c4173a);

    void m13120b(C4173a c4173a);

    String m13121d();

    String m13122f();

    C4173a m13123j();

    C4174b m13124o();
}
