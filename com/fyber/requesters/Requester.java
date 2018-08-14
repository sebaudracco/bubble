package com.fyber.requesters;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import com.fyber.Fyber;
import com.fyber.requesters.p097a.C2588f;
import com.fyber.requesters.p097a.C2623c;
import com.fyber.requesters.p097a.p098a.C2602f;
import com.fyber.utils.C2412c;
import com.fyber.utils.C2656g;
import com.fyber.utils.C2664l;
import java.lang.ref.WeakReference;
import java.util.Map;

public abstract class Requester<T> {
    public static final String EXTRA_AD_FORMAT = "EXTRA_AD_FORMAT";
    protected C2588f f6475a;
    protected C2623c f6476b;
    protected WeakReference<Context> f6477c;

    protected abstract C2588f mo3991a();

    protected abstract void mo3992a(Context context, C2623c c2623c);

    protected abstract void mo3993b();

    protected abstract T mo3994c();

    protected Requester(@NonNull Callback callback) {
        if (callback == null) {
            throw new IllegalArgumentException("callback cannot be null");
        }
        this.f6475a = mo3991a().m8279a(callback);
        this.f6476b = new C2623c();
        mo3993b();
    }

    protected Requester(@NonNull Requester requester) {
        if (requester == null) {
            throw new IllegalArgumentException("requester cannot be null");
        }
        this.f6475a = mo3991a().m8281a(requester.f6475a);
        this.f6476b = new C2623c(requester.f6476b);
        mo3993b();
    }

    public T addParameters(Map<String, String> map) {
        if (C2664l.m8521b(map)) {
            this.f6476b.m8404a((Map) map);
        }
        return mo3994c();
    }

    public T addParameter(String str, String str2) {
        this.f6476b.m8403a(str, str2);
        return mo3994c();
    }

    public T clearParameters() {
        this.f6476b.m8416d();
        return mo3994c();
    }

    public T removeParameter(String str) {
        this.f6476b.m8418e(str);
        return mo3994c();
    }

    public T withPlacementId(String str) {
        this.f6476b.m8414c(str);
        return mo3994c();
    }

    public T withCallback(@NonNull Callback callback) {
        if (callback == null) {
            throw new IllegalArgumentException("callback cannot be null");
        }
        this.f6475a.m8279a(callback);
        return mo3994c();
    }

    public T invokeCallbackOnHandler(Handler handler) {
        this.f6475a.m8278a(handler);
        return mo3994c();
    }

    public final void request(final Context context) {
        Object obj = null;
        if (context == null) {
            this.f6475a.m8282a(RequestError.NULL_CONTEXT_REFERENCE);
        } else if (!C2656g.m8490f()) {
            this.f6475a.m8282a(RequestError.DEVICE_NOT_SUPPORTED);
        } else if (!Fyber.getConfigs().m7607h()) {
            this.f6475a.m8282a(RequestError.SDK_NOT_STARTED);
        } else if (this.f6475a.m8285a()) {
            obj = 1;
        } else {
            this.f6475a.m8282a(RequestError.MISMATCH_CALLBACK_TYPE);
        }
        if (obj != null) {
            this.f6477c = new WeakReference(context);
            Fyber.getConfigs().m7600a(new C2412c(this) {
                final /* synthetic */ Requester f6483b;

                public final void mo3844a() {
                    this.f6483b.f6475a.m8280a(this.f6483b.f6476b);
                    this.f6483b.f6476b.m8420f();
                    C2602f a = Fyber.getConfigs().m7603d().m8386a(this.f6483b.f6476b);
                    if (a != null) {
                        this.f6483b.f6475a.m8283a(a);
                    } else {
                        this.f6483b.mo3992a(context, this.f6483b.f6476b);
                    }
                }
            });
        }
    }
}
