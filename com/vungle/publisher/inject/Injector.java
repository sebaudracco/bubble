package com.vungle.publisher.inject;

import android.content.Context;
import com.vungle.publisher.env.WrapperFramework;
import com.vungle.publisher.log.Logger;

/* compiled from: vungle */
public class Injector {
    private static Injector f10362a;
    private a f10363b;
    private EndpointModule f10364c;
    private t f10365d;
    private w f10366e;

    public static synchronized Injector getInstance() {
        Injector injector;
        synchronized (Injector.class) {
            if (f10362a == null) {
                f10362a = new Injector();
            }
            injector = f10362a;
        }
        return injector;
    }

    private Injector() {
    }

    public void setWrapperFramework(WrapperFramework wrapperFramework) {
        try {
            if (m13372d()) {
                Logger.d(Logger.INJECT_TAG, "wrapper framework in injector NOT set - already initialized");
                return;
            }
            Logger.d(Logger.INJECT_TAG, "setting wrapper framework in injector: " + wrapperFramework);
            m13367e().a(wrapperFramework);
        } catch (Throwable e) {
            Logger.e(Logger.INJECT_TAG, e);
        }
    }

    public void setWrapperFrameworkVersion(String wrapperFrameworkVersion) {
        try {
            if (m13372d()) {
                Logger.d(Logger.INJECT_TAG, "wrapper framework version in injector NOT set - already initialized");
                return;
            }
            Logger.d(Logger.INJECT_TAG, "setting wrapper framework version in injector: " + wrapperFrameworkVersion);
            m13367e().a(wrapperFrameworkVersion);
        } catch (Throwable e) {
            Logger.e(Logger.INJECT_TAG, e);
        }
    }

    private a m13367e() {
        if (this.f10363b == null) {
            this.f10363b = new a();
        }
        return this.f10363b;
    }

    public Injector setEndpointModule(EndpointModule endpointModule) {
        this.f10364c = endpointModule;
        return this;
    }

    public t m13369a() {
        if (this.f10365d == null) {
            this.f10365d = new t();
        }
        return this.f10365d;
    }

    public EndpointModule m13371b() {
        if (this.f10364c == null) {
            this.f10364c = new EndpointModule();
        }
        return this.f10364c;
    }

    public Injector m13368a(w wVar) {
        this.f10366e = wVar;
        return this;
    }

    public static w m13366c() {
        return getInstance().f10366e;
    }

    public void m13370a(Context context, String str) {
        try {
            if (m13372d()) {
                Logger.d(Logger.INJECT_TAG, "already initialized");
                return;
            }
            Logger.d(Logger.INJECT_TAG, "initializing");
            m13367e().a(context, str);
            m13368a(r.a().a(m13367e()).a(m13371b()).a(m13369a()).a());
        } catch (Throwable e) {
            Logger.e(Logger.INJECT_TAG, "error initializing injector", e);
        }
    }

    public boolean m13372d() {
        return m13366c() != null && m13367e().a();
    }
}
