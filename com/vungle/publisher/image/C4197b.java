package com.vungle.publisher.image;

import android.content.Context;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class C4197b implements MembersInjector<AssetBitmapFactory> {
    static final /* synthetic */ boolean f10358a = (!C4197b.class.desiredAssertionStatus());
    private final Provider<Context> f10359b;

    public /* synthetic */ void injectMembers(Object obj) {
        m13363a((AssetBitmapFactory) obj);
    }

    public C4197b(Provider<Context> provider) {
        if (f10358a || provider != null) {
            this.f10359b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<AssetBitmapFactory> m13362a(Provider<Context> provider) {
        return new C4197b(provider);
    }

    public void m13363a(AssetBitmapFactory assetBitmapFactory) {
        if (assetBitmapFactory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        assetBitmapFactory.a = (Context) this.f10359b.get();
    }
}
