package com.vungle.publisher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.vungle.publisher.inject.Injector;
import com.vungle.publisher.log.Logger;
import com.vungle.publisher.mm.a;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class ob extends mm<om> {
    @Inject
    om$a f3173n;

    @Singleton
    /* compiled from: vungle */
    public static class C1664a extends a<ob> {
        @Inject
        Provider<ob> f3172a;

        protected /* synthetic */ mm m4464a() {
            return m4466c();
        }

        @Inject
        C1664a() {
        }

        protected ob m4466c() {
            return (ob) this.f3172a.get();
        }

        protected String m4465b() {
            return "postRollFragment";
        }
    }

    protected /* synthetic */ mj mo3000a(String str, p pVar, x xVar) {
        return m4469b(str, pVar, xVar);
    }

    public void mo3001a() {
        try {
            this.k.m4568a(new ao());
        } catch (Throwable e) {
            Logger.w(Logger.AD_TAG, "exception in onBackPressed", e);
        }
    }

    public String mo3003b() {
        return "postRollFragment";
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        if (Injector.getInstance().d()) {
            Injector.c().m4202a(this);
        } else {
            Logger.w(Logger.AD_TAG, "SDK not initialized");
            getActivity().finish();
        }
        super.onActivityCreated(savedInstanceState);
    }

    protected om m4469b(String str, p pVar, x xVar) {
        return (om) this.f3173n.m4382a(str, pVar, this.c, xVar);
    }
}
