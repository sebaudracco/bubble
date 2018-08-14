package com.vungle.publisher.env;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.WindowManager;
import com.vungle.publisher.lm;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class C4178g implements MembersInjector<AndroidDevice> {
    static final /* synthetic */ boolean f10022a = (!C4178g.class.desiredAssertionStatus());
    private final Provider<lm> f10023b;
    private final Provider<WindowManager> f10024c;
    private final Provider<Context> f10025d;
    private final Provider<SharedPreferences> f10026e;
    private final Provider<AndroidDevice$DeviceIdStrategy> f10027f;
    private final Provider<String> f10028g;

    public /* synthetic */ void injectMembers(Object obj) {
        m13136a((AndroidDevice) obj);
    }

    public C4178g(Provider<lm> provider, Provider<WindowManager> provider2, Provider<Context> provider3, Provider<SharedPreferences> provider4, Provider<AndroidDevice$DeviceIdStrategy> provider5, Provider<String> provider6) {
        if (f10022a || provider != null) {
            this.f10023b = provider;
            if (f10022a || provider2 != null) {
                this.f10024c = provider2;
                if (f10022a || provider3 != null) {
                    this.f10025d = provider3;
                    if (f10022a || provider4 != null) {
                        this.f10026e = provider4;
                        if (f10022a || provider5 != null) {
                            this.f10027f = provider5;
                            if (f10022a || provider6 != null) {
                                this.f10028g = provider6;
                                return;
                            }
                            throw new AssertionError();
                        }
                        throw new AssertionError();
                    }
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<AndroidDevice> m13135a(Provider<lm> provider, Provider<WindowManager> provider2, Provider<Context> provider3, Provider<SharedPreferences> provider4, Provider<AndroidDevice$DeviceIdStrategy> provider5, Provider<String> provider6) {
        return new C4178g(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public void m13136a(AndroidDevice androidDevice) {
        if (androidDevice == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        androidDevice.e = (lm) this.f10023b.get();
        androidDevice.f = (WindowManager) this.f10024c.get();
        androidDevice.g = (Context) this.f10025d.get();
        androidDevice.h = (SharedPreferences) this.f10026e.get();
        androidDevice.i = (AndroidDevice$DeviceIdStrategy) this.f10027f.get();
        androidDevice.j = (String) this.f10028g.get();
    }
}
