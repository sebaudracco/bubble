package com.vungle.publisher;

import com.vungle.publisher.image.AssetBitmapFactory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class zr implements MembersInjector<zo> {
    static final /* synthetic */ boolean f11396a = (!zr.class.desiredAssertionStatus());
    private final Provider<AssetBitmapFactory> f11397b;

    public /* synthetic */ void injectMembers(Object obj) {
        m14228a((zo) obj);
    }

    public zr(Provider<AssetBitmapFactory> provider) {
        if (f11396a || provider != null) {
            this.f11397b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<zo> m14227a(Provider<AssetBitmapFactory> provider) {
        return new zr(provider);
    }

    public void m14228a(zo zoVar) {
        if (zoVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        zoVar.a = (AssetBitmapFactory) this.f11397b.get();
    }
}
