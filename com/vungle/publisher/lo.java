package com.vungle.publisher;

import android.media.AudioManager;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class lo implements MembersInjector<lm> {
    static final /* synthetic */ boolean f10703a = (!lo.class.desiredAssertionStatus());
    private final Provider<AudioManager> f10704b;

    public /* synthetic */ void injectMembers(Object obj) {
        m13630a((lm) obj);
    }

    public lo(Provider<AudioManager> provider) {
        if (f10703a || provider != null) {
            this.f10704b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<lm> m13629a(Provider<AudioManager> provider) {
        return new lo(provider);
    }

    public void m13630a(lm lmVar) {
        if (lmVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        lmVar.a = (AudioManager) this.f10704b.get();
    }
}
