package com.fyber.requesters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import com.fyber.ads.AdFormat;
import com.fyber.ads.internal.Offer;
import com.fyber.ads.videos.C2472a;
import com.fyber.ads.videos.RewardedVideoActivity;
import com.fyber.ads.videos.p093a.C2448b;
import com.fyber.ads.videos.p093a.C2469d;
import com.fyber.requesters.p097a.C2588f;
import com.fyber.requesters.p097a.C2623c;
import com.fyber.utils.FyberLogger;
import com.mopub.common.AdType;

public class RewardedVideoRequester extends Requester<RewardedVideoRequester> {
    protected final /* bridge */ /* synthetic */ Object mo3994c() {
        return this;
    }

    public static RewardedVideoRequester create(@NonNull RequestCallback requestCallback) {
        return new RewardedVideoRequester((Callback) requestCallback);
    }

    public static RewardedVideoRequester from(@NonNull Requester requester) {
        return new RewardedVideoRequester(requester);
    }

    public RewardedVideoRequester notifyUserOnCompletion(boolean z) {
        this.b.m8402a("SHOULD_NOTIFY_ON_USER_ENGAGED", Boolean.valueOf(z));
        return this;
    }

    public RewardedVideoRequester withVirtualCurrencyRequester(VirtualCurrencyRequester virtualCurrencyRequester) {
        this.b.m8402a("CURRENCY_REQUESTER", (Object) virtualCurrencyRequester);
        if (!(virtualCurrencyRequester == null || virtualCurrencyRequester.b.mo3970a("CURRENCY_ID") == null)) {
            FyberLogger.m8453w("RewardedVideoRequester", "A currency ID was detected in the parameters. It will not be used. The currency related to the ad displayed will be used instead.");
        }
        return this;
    }

    protected final void mo3992a(Context context, C2623c c2623c) {
        if (C2469d.f6162a.m7846b()) {
            C2469d.f6162a.m7841a(this.a);
            try {
                C2469d.f6162a.m7845a(c2623c, context);
                return;
            } catch (Exception e) {
                FyberLogger.m8450e("RewardedVideoRequester", "something went wrong with the video request", e);
                this.a.m8282a(RequestError.UNKNOWN_ERROR);
                return;
            }
        }
        this.a.m8282a(RequestError.UNABLE_TO_REQUEST_ADS);
    }

    private RewardedVideoRequester(Callback callback) {
        super(callback);
    }

    private RewardedVideoRequester(Requester requester) {
        super(requester);
    }

    protected final C2588f<C2448b, AdFormat> mo3991a() {
        return new C2588f<C2448b, AdFormat>(this, RequestCallback.class) {
            final /* synthetic */ RewardedVideoRequester f6484a;

            protected final /* synthetic */ void mo3989a(Object obj) {
                ((RequestCallback) this.c).onAdNotAvailable((AdFormat) obj);
            }

            protected final /* synthetic */ void mo3990b(Object obj) {
                C2448b c2448b = (C2448b) obj;
                if (this.c instanceof RequestCallback) {
                    Context context = (Context) this.f6484a.c.get();
                    if (context != null) {
                        Offer j = c2448b.m7627j();
                        Intent intent = new Intent(context, RewardedVideoActivity.class);
                        if (j != null) {
                            intent.putExtra(RewardedVideoActivity.PLAY_EXCHANGE_AD_KEY_BUNDLE, ((Boolean) j.getProviderRequest().mo3971a(RewardedVideoActivity.PLAY_EXCHANGE_AD_KEY_BUNDLE, Boolean.class, Boolean.valueOf(true))).booleanValue());
                        } else {
                            intent.putExtra(RewardedVideoActivity.PLAY_EXCHANGE_AD_KEY_BUNDLE, true);
                        }
                        intent.putExtra(Requester.EXTRA_AD_FORMAT, AdFormat.REWARDED_VIDEO);
                        intent.putExtra("REQUEST_AGENT_CACHE_KEY", this.f6484a.b.mo3972a());
                        ((RequestCallback) this.c).onAdAvailable(intent);
                        return;
                    }
                    ((RequestCallback) this.c).onAdNotAvailable(AdFormat.REWARDED_VIDEO);
                } else if (this.c instanceof AdRequestCallback) {
                    ((AdRequestCallback) this.c).onAdAvailable((C2472a) c2448b.m7626i());
                }
            }
        };
    }

    protected final void mo3993b() {
        this.b.m8411b(AdType.REWARDED_VIDEO).m8405a(true).m8406a(9, 8, 2, 0);
    }
}
