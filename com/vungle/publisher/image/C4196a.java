package com.vungle.publisher.image;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class C4196a implements Factory<AssetBitmapFactory> {
    static final /* synthetic */ boolean f10356a = (!C4196a.class.desiredAssertionStatus());
    private final MembersInjector<AssetBitmapFactory> f10357b;

    public /* synthetic */ Object get() {
        return m13361a();
    }

    public C4196a(MembersInjector<AssetBitmapFactory> membersInjector) {
        if (f10356a || membersInjector != null) {
            this.f10357b = membersInjector;
            return;
        }
        throw new AssertionError();
    }

    public AssetBitmapFactory m13361a() {
        return (AssetBitmapFactory) MembersInjectors.injectMembers(this.f10357b, new AssetBitmapFactory());
    }

    public static Factory<AssetBitmapFactory> m13360a(MembersInjector<AssetBitmapFactory> membersInjector) {
        return new C4196a(membersInjector);
    }
}
