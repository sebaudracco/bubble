package com.vungle.publisher;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.vungle.publisher.env.i;
import com.vungle.publisher.log.Logger;
import java.lang.ref.WeakReference;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import rx.subjects.BehaviorSubject;

/* compiled from: vungle */
public abstract class mg<A extends cn> {
    A f3104a;
    WeakReference<VungleAdActivity> f3105b = new WeakReference(null);
    mf f3106c;
    me<?> f3107d;
    yj<?> f3108e;
    protected String f3109f;
    protected String f3110g;
    protected BehaviorSubject<Void> f3111h = BehaviorSubject.create();
    @Inject
    qg f3112i;
    @Inject
    zc f3113j;
    @Inject
    i f3114k;
    private p f3115l;

    @Singleton
    /* compiled from: vungle */
    public static class C1655a {
        @Inject
        Provider<oy> f3101a;
        @Inject
        Provider<nk> f3102b;
        @Inject
        Provider<ns> f3103c;

        @Inject
        C1655a() {
        }

        public <P extends mg<A>, A extends cn> P m4364a(A a) {
            if (x.b == x.a(a.s)) {
                return (mg) this.f3103c.get();
            }
            return (mg) new 1(this).a(a);
        }
    }

    protected abstract me<?> mo3011a();

    protected abstract yj<?> mo3010b();

    public void mo3009a(VungleAdActivity vungleAdActivity, A a, String str, p pVar, Bundle bundle) {
        this.f3104a = a;
        this.f3105b = new WeakReference(vungleAdActivity);
        this.f3115l = pVar;
        this.f3110g = str;
        this.f3107d = mo3011a();
        this.f3108e = mo3010b();
        this.f3107d.register();
        this.f3108e.register();
        Object obj = bundle != null ? 1 : null;
        if (obj == null) {
            this.f3112i.m4568a(new av(a, str, pVar));
        }
        this.f3109f = obj != null ? bundle.getString("currentFragment") : null;
    }

    public void m4369a(Bundle bundle) {
        try {
            bundle.putString("currentFragment", this.f3106c.mo3003b());
        } catch (Throwable e) {
            Logger.e(Logger.AD_TAG, "error in onSaveInstanceState", e);
        }
    }

    public void mo3013a(Configuration configuration) {
    }

    public boolean m4375a(int i, KeyEvent keyEvent) {
        return this.f3106c.m4362a(i, keyEvent);
    }

    public void m4378c() {
        this.f3106c.mo3001a();
    }

    public void m4373a(boolean z) {
        this.f3106c.mo3002a(z);
    }

    public void mo3008a(VungleAdActivity vungleAdActivity) {
        this.f3111h.onNext(null);
    }

    void m4374a(boolean z, boolean z2) {
        Activity activity;
        try {
            this.f3112i.m4568a(z ? new bv(this.f3104a, this.f3110g, z2) : new bu(this.f3104a, this.f3110g, z2));
            this.f3111h.onNext(null);
            activity = (Activity) this.f3105b.get();
            if (activity != null) {
                activity.finish();
            }
        } catch (Throwable e) {
            Logger.e(Logger.AD_TAG, "error exiting ad", e);
            activity = (Activity) this.f3105b.get();
            if (activity != null) {
                activity.finish();
            }
        } catch (Throwable e2) {
            Throwable th = e2;
            activity = (Activity) this.f3105b.get();
            if (activity != null) {
                activity.finish();
            }
        }
    }

    void m4368a(Uri uri) {
        try {
            Intent a = this.f3113j.m4922a("android.intent.action.VIEW", uri);
            a.addFlags(ErrorDialogData.BINDER_CRASH);
            Activity activity = (Activity) this.f3105b.get();
            if (activity != null) {
                activity.startActivity(a);
            } else {
                Logger.e(Logger.AD_TAG, "error loading URL: " + uri.toString(), new Throwable("Activity destroyed."));
            }
        } catch (Throwable e) {
            Logger.e(Logger.AD_TAG, "error loading URL: " + uri.toString(), e);
        }
    }

    void m4377b(Uri uri) {
        m4368a(uri);
    }

    protected void m4372a(mf mfVar) {
        Activity activity = (Activity) this.f3105b.get();
        if (mfVar != this.f3106c && activity != null) {
            FragmentTransaction beginTransaction = activity.getFragmentManager().beginTransaction();
            if (this.f3115l == null || this.f3115l.isTransitionAnimationEnabled()) {
                beginTransaction.setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            }
            this.f3106c = mfVar;
            beginTransaction.replace(16908290, mfVar, mfVar.mo3003b());
            beginTransaction.commit();
        }
    }

    protected void m4366a(int i) {
        try {
            Activity activity = (Activity) this.f3105b.get();
            if (activity != null) {
                activity.setRequestedOrientation(i);
            }
        } catch (Throwable e) {
            Logger.e(Logger.AD_TAG, "could not set orientation", e);
        }
    }
}
