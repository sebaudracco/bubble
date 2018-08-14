package com.integralads.avid.library.inmobi.p123c;

import com.integralads.avid.library.inmobi.p122b.C3288a;
import com.integralads.avid.library.inmobi.session.internal.C3333a;
import com.integralads.avid.library.inmobi.session.internal.p129a.C3331a;

/* compiled from: AvidDeferredAdSessionListenerImpl */
public class C3295b extends C3288a implements C3294a {
    public C3295b(C3333a c3333a, C3331a c3331a) {
        super(c3333a, c3331a);
    }

    public void a_() {
        m11195d();
        if (m11193b().m11405h()) {
            throw new IllegalStateException("The AVID ad session is already ready. Please ensure you are only calling recordReadyEvent once for the deferred AVID ad session.");
        }
        m11194c().m11388d();
        m11193b().m11410m();
    }
}
