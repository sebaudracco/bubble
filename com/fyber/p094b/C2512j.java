package com.fyber.p094b;

import android.support.annotation.NonNull;
import com.fyber.utils.C2657h;
import com.fyber.utils.FyberLogger;
import java.util.Map;
import java.util.concurrent.Callable;

/* compiled from: RemoteTriggerableOperation */
public final class C2512j<V> implements Callable<V> {
    private final String f6236a;
    private final C2458c<V> f6237b;
    private final C2511d<V> f6238c;
    private Map<String, String> f6239d;

    /* compiled from: RemoteTriggerableOperation */
    public interface C2458c<V> {
        V mo3893a(String str) throws Exception;
    }

    /* compiled from: RemoteTriggerableOperation */
    public static class C2509a<V> {
        private String f6232a;
        private Map<String, String> f6233b;
        private C2511d<V> f6234c;
        private C2458c f6235d = new C2510b();

        public C2509a(@NonNull String str) {
            this.f6232a = str;
        }

        public final C2509a<V> m7992a(Map<String, String> map) {
            this.f6233b = map;
            return this;
        }

        public final C2509a<V> m7991a(C2458c<V> c2458c) {
            this.f6235d = c2458c;
            return this;
        }

        public final C2512j<V> m7993a() {
            return new C2512j();
        }
    }

    /* compiled from: RemoteTriggerableOperation */
    private static class C2510b implements C2458c<String> {
        private C2510b() {
        }

        public final /* bridge */ /* synthetic */ Object mo3893a(String str) throws Exception {
            return str;
        }
    }

    /* compiled from: RemoteTriggerableOperation */
    public interface C2511d<V> {
    }

    private C2512j(C2509a<V> c2509a) {
        this.f6236a = c2509a.f6232a;
        this.f6239d = c2509a.f6233b;
        this.f6238c = c2509a.f6234c;
        this.f6237b = c2509a.f6235d;
    }

    public final V call() throws Exception {
        try {
            return this.f6237b.mo3893a((String) ((C2657h) ((C2657h) C2657h.m8506b(this.f6236a).m8461a(this.f6239d)).mo4005a()).m8466c());
        } catch (Exception e) {
            FyberLogger.m8450e("RemoteTriggerableOperation", "An error occurred, aborting the request... - " + e.getMessage(), e);
            return null;
        }
    }
}
