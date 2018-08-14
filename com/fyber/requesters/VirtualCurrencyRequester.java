package com.fyber.requesters;

import android.content.Context;
import android.support.annotation.NonNull;
import com.fyber.Fyber;
import com.fyber.currency.VirtualCurrencyErrorResponse;
import com.fyber.currency.VirtualCurrencyResponse;
import com.fyber.p094b.C2516n;
import com.fyber.requesters.p097a.C2588f;
import com.fyber.requesters.p097a.C2623c;
import com.fyber.utils.StringUtils;

public class VirtualCurrencyRequester extends Requester<VirtualCurrencyRequester> {
    protected final /* bridge */ /* synthetic */ Object mo3994c() {
        return this;
    }

    public static VirtualCurrencyRequester create(@NonNull VirtualCurrencyCallback virtualCurrencyCallback) {
        return new VirtualCurrencyRequester(virtualCurrencyCallback);
    }

    public static VirtualCurrencyRequester from(@NonNull Requester requester) {
        return new VirtualCurrencyRequester(requester);
    }

    public VirtualCurrencyRequester withTransactionId(String str) {
        this.b.m8402a("TRANSACTION_ID", (Object) str);
        return this;
    }

    public VirtualCurrencyRequester forCurrencyId(String str) {
        this.b.m8402a("CURRENCY_ID", (Object) str);
        return this;
    }

    public VirtualCurrencyRequester withVirtualCurrencyCallback(VirtualCurrencyCallback virtualCurrencyCallback) {
        return (VirtualCurrencyRequester) withCallback(virtualCurrencyCallback);
    }

    public VirtualCurrencyRequester notifyUserOnReward(boolean z) {
        this.b.m8402a("NOTIFY_USER_ON_REWARD", Boolean.valueOf(z));
        return this;
    }

    protected final void mo3992a(Context context, C2623c c2623c) {
        String c = Fyber.getConfigs().m7608i().m7594c();
        if (StringUtils.nullOrEmpty(c)) {
            this.a.m8282a(RequestError.SECURITY_TOKEN_NOT_PROVIDED);
            return;
        }
        Fyber.getConfigs().m7599a(new C2516n(c2623c, c, context).m8000a(this.a));
    }

    protected final C2588f<VirtualCurrencyResponse, VirtualCurrencyErrorResponse> mo3991a() {
        return new C2588f<VirtualCurrencyResponse, VirtualCurrencyErrorResponse>(this, VirtualCurrencyCallback.class) {
            final /* synthetic */ VirtualCurrencyRequester f6485a;

            protected final /* synthetic */ void mo3989a(Object obj) {
                ((VirtualCurrencyCallback) this.c).onError((VirtualCurrencyErrorResponse) obj);
            }

            protected final /* synthetic */ void mo3990b(Object obj) {
                ((VirtualCurrencyCallback) this.c).onSuccess((VirtualCurrencyResponse) obj);
            }
        };
    }

    protected final void mo3993b() {
        this.b.m8411b("vcs").m8405a(false).m8406a(6, 5, 0);
    }

    private VirtualCurrencyRequester(Requester requester) {
        super(requester);
    }

    private VirtualCurrencyRequester(@NonNull VirtualCurrencyCallback virtualCurrencyCallback) {
        super((Callback) virtualCurrencyCallback);
    }
}
