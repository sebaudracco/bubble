package com.vungle.publisher;

import com.vungle.publisher.bw.b;
import com.vungle.publisher.env.C1614r;
import com.vungle.publisher.log.Logger;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class pj extends pi {
    VungleAdEventListener f3235a;
    @Inject
    bw f3236b;
    @Inject
    C1591c f3237c;
    @Inject
    C1614r f3238d;
    private int f3239e;
    private boolean f3240f;

    @Singleton
    /* compiled from: vungle */
    public static class C1668a {
        @Inject
        Provider<pj> f3234a;

        @Inject
        C1668a() {
        }

        public pj m4539a(VungleAdEventListener vungleAdEventListener) {
            pj pjVar = (pj) this.f3234a.get();
            pjVar.f3235a = vungleAdEventListener;
            return pjVar;
        }
    }

    @Inject
    pj() {
    }

    public void onEvent(z adAvailabilityEvent) {
        String a = adAvailabilityEvent.a();
        boolean a2 = this.f3237c.m3491a(a);
        Logger.d(Logger.EVENT_TAG, "Ad availability notification for placement " + a + " with isAdAvailable = " + a2);
        m4540a(pk.m4552a(this, a, a2));
    }

    /* synthetic */ void m4551c(String str, boolean z) {
        this.f3235a.onAdAvailabilityUpdate(str, z);
    }

    public void onEvent(bn notInitializedCancelLoadAdEvent) {
        String str = notInitializedCancelLoadAdEvent.a;
        Logger.d(Logger.EVENT_TAG, "Not initialized: Unable to load ad for placement " + str);
        m4540a(pm.m4554a(this, str));
    }

    /* synthetic */ void m4549b(String str) {
        this.f3235a.onAdAvailabilityUpdate(str, false);
    }

    public void onEvent(bl errorCancelLoadAdEvent) {
        String str = errorCancelLoadAdEvent.a;
        Logger.d(Logger.EVENT_TAG, "Unable to load ad for placement - " + str);
        m4540a(pn.m4555a(this, str));
    }

    /* synthetic */ void m4547a(String str) {
        this.f3235a.onAdAvailabilityUpdate(str, false);
    }

    public void onEvent(av<cn> startPlayAdEvent) {
        Logger.d(Logger.EVENT_TAG, "onAdStart() callback");
        this.f3239e = 0;
        this.f3240f = false;
        m4540a(po.m4556a(this, startPlayAdEvent));
    }

    /* synthetic */ void m4541a(av avVar) {
        this.f3235a.onAdStart(avVar.c());
    }

    public void onEvent(bv<cn> playAdSuccessEvent) {
        m4548a(playAdSuccessEvent.c(), playAdSuccessEvent.a());
    }

    public void onEvent(ax event) {
        this.f3240f = true;
    }

    public void onEvent(bi errorEndPlayEvent) {
        if (errorEndPlayEvent instanceof bs) {
            Logger.d(Logger.EVENT_TAG, "onAdEnd() - activity destroyed");
        } else {
            Logger.d(Logger.EVENT_TAG, "onAdEnd() - error during playback");
        }
        m4548a(errorEndPlayEvent.c(), false);
    }

    void m4548a(String str, boolean z) {
        Logger.d(Logger.EVENT_TAG, "onAdEnd(" + z + ") callback");
        m4540a(pp.m4557a(this, str, z));
    }

    /* synthetic */ void m4550b(String str, boolean z) {
        this.f3235a.onAdEnd(str, this.f3240f, z);
    }

    public void onEvent(bm errorCancelPlayEvent) {
        Logger.d(Logger.EVENT_TAG, "onUnableToPlayAd(error) callback");
        m4540a(pq.m4558a(this, errorCancelPlayEvent));
    }

    /* synthetic */ void m4543a(bm bmVar) {
        this.f3235a.onUnableToPlayAd(bmVar.c(), "Error launching ad");
    }

    public void onEvent(bj alreadyPlayingCancelPlayEvent) {
        Logger.d(Logger.EVENT_TAG, "onUnableToPlayAd(already playing) callback");
        m4540a(pr.m4559a(this, alreadyPlayingCancelPlayEvent));
    }

    /* synthetic */ void m4542a(bj bjVar) {
        this.f3235a.onUnableToPlayAd(bjVar.a(), "Ad already playing");
    }

    public void onEvent(bo notInitializedCancelPlayEvent) {
        Logger.d(Logger.EVENT_TAG, "onUnableToPlayAd(not initialized) callback");
        m4540a(ps.m4560a(this, notInitializedCancelPlayEvent));
    }

    /* synthetic */ void m4544a(bo boVar) {
        this.f3235a.onUnableToPlayAd(boVar.c(), "Vungle Publisher SDK was not successfully initialized - please check the logs");
    }

    public void onEvent(bp throttledCancelPlayEvent) {
        Logger.d(Logger.EVENT_TAG, "onUnableToPlayAd(throttled) callback");
        m4540a(pt.m4561a(this, throttledCancelPlayEvent));
    }

    /* synthetic */ void m4545a(bp bpVar) {
        this.f3235a.onUnableToPlayAd(bpVar.c(), "Only " + bpVar.a() + " of minimum " + bpVar.d() + " seconds elapsed between ads");
    }

    public void onEvent(bq unavailableCancelPlayEvent) {
        Logger.d(Logger.EVENT_TAG, "onUnableToPlayAd(unavailable) callback");
        m4540a(pl.m4553a(this, unavailableCancelPlayEvent));
    }

    /* synthetic */ void m4546a(bq bqVar) {
        this.f3235a.onUnableToPlayAd(bqVar.c(), "No cached or streaming ad available");
    }

    public void onEvent(ai endPlayVideoEvent) {
        int a = endPlayVideoEvent.a();
        if (a > this.f3239e) {
            Logger.d(Logger.EVENT_TAG, "new watched millis " + a);
            this.f3239e = a;
            return;
        }
        Logger.d(Logger.EVENT_TAG, "shorter watched millis " + a);
    }

    private void m4540a(Runnable runnable) {
        this.f3236b.m3474a(runnable, b.p);
    }
}
