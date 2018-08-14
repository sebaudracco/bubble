package com.fyber.requesters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import com.fyber.ads.AdFormat;
import com.fyber.ads.ofw.OfferWallActivity;
import com.fyber.requesters.p097a.C2588f;
import com.fyber.requesters.p097a.C2623c;
import java.io.Serializable;

public class OfferWallRequester extends Requester<OfferWallRequester> {
    protected final /* bridge */ /* synthetic */ Object mo3994c() {
        return this;
    }

    public static OfferWallRequester create(@NonNull RequestCallback requestCallback) {
        return new OfferWallRequester(requestCallback);
    }

    public static OfferWallRequester from(@NonNull Requester requester) {
        return new OfferWallRequester(requester);
    }

    public OfferWallRequester closeOnRedirect(boolean z) {
        this.b.m8402a("CLOSE_ON_REDIRECT", Boolean.valueOf(z));
        return this;
    }

    protected final void mo3992a(Context context, C2623c c2623c) {
        this.a.m8287c(new Intent(context, OfferWallActivity.class).putExtra(OfferWallActivity.EXTRA_SHOULD_CLOSE_ON_REDIRECT_KEY, (Serializable) c2623c.m8408a("CLOSE_ON_REDIRECT", Boolean.class)).putExtra(OfferWallActivity.EXTRA_URL, c2623c.m8419e().m8435a()).putExtra(OfferWallActivity.EXTRA_USER_SEGMENTS, (String) c2623c.m8419e().m8438d().get("X-User-Data")).putExtra(Requester.EXTRA_AD_FORMAT, AdFormat.OFFER_WALL));
    }

    protected final C2588f<Intent, Void> mo3991a() {
        return new C2588f<Intent, Void>(this, RequestCallback.class) {
            final /* synthetic */ OfferWallRequester f6479a;

            protected final /* bridge */ /* synthetic */ void mo3989a(Object obj) {
            }

            protected final /* synthetic */ void mo3990b(Object obj) {
                ((RequestCallback) this.c).onAdAvailable((Intent) obj);
            }
        };
    }

    protected final void mo3993b() {
        this.b.m8411b("ofw").m8405a(false).m8406a(9, 8, 1, 0);
    }

    private OfferWallRequester(RequestCallback requestCallback) {
        super((Callback) requestCallback);
    }

    private OfferWallRequester(Requester requester) {
        super(requester);
    }
}
