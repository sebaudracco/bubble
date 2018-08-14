package com.oneaudience.sdk;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;
import com.appnext.base.p023b.C1042c;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.mopub.common.Constants;
import com.oneaudience.sdk.C3882o.C3856b;
import com.oneaudience.sdk.C3882o.C3857a;
import com.oneaudience.sdk.C3882o.C3881d;
import com.oneaudience.sdk.p133a.C3795i;
import com.oneaudience.sdk.p135c.C3833d;
import com.oneaudience.sdk.p135c.p136a.C3822b;

public class C3861k {
    private static String f9242a = C3861k.class.getSimpleName();
    private static C3861k f9243m;
    private final int f9244b = 4;
    private final int f9245c = 4;
    private final int f9246d = 10;
    private final int f9247e = 10;
    private final int f9248f = 10;
    private Context f9249g;
    private Context f9250h;
    private SharedPreferences f9251i;
    private C3858b f9252j;
    private long f9253k = 0;
    private boolean f9254l;

    private class C3855a extends C3854n<Void, Void, Void> {
        String f9232a;
        Activity f9233b;
        final /* synthetic */ C3861k f9234c;

        public C3855a(C3861k c3861k, String str, Activity activity) {
            this.f9234c = c3861k;
            this.f9232a = str;
            this.f9233b = activity;
        }

        protected Void m12312a(Void... voidArr) {
            C3850j.m12300a(this.f9233b, new C3845g().m12293a(this.f9233b, this.f9232a).f9182b);
            return null;
        }
    }

    protected static class C3858b implements C3857a {
        protected Context f9235a = null;

        public C3858b(Context context) {
            this.f9235a = context;
        }

        public void mo6813a(C3854n c3854n, Exception exception) {
            C3833d.m12257e(C3861k.f9242a, "Error has occurred at task: %s\nerror:%s", c3854n.getClass().getSimpleName(), exception);
            if (this.f9235a != null) {
                C3843e.m12284a(this.f9235a, this.f9235a.getSharedPreferences("oneaudience", 0), (Object) exception);
            }
        }
    }

    private class C3860c extends C3854n<Void, Void, Void> {
        final /* synthetic */ C3861k f9238a;
        private String f9239b;
        private boolean f9240c;
        private boolean f9241d;

        public C3860c(C3861k c3861k, String str, boolean z, boolean z2) {
            this.f9238a = c3861k;
            this.f9239b = str;
            this.f9240c = z;
            this.f9241d = z2;
        }

        private int m12315a(boolean z) {
            C3833d.m12252c(C3861k.f9242a, "Performing configuration sync... FETCHING");
            C3840c c3840c = new C3840c(z);
            C3822b a = new C3820b().m12225a(c3840c.m12276a(this.f9238a.f9249g, this.f9238a.f9251i, this.f9239b));
            switch (a.f9185a) {
                case 100000:
                    c3840c.m12277a(this.f9238a.f9249g, this.f9238a.f9251i, a);
                    C3833d.m12252c(C3861k.f9242a, "Performing configuration sync... FETCHING DONE");
                    break;
                case 100001:
                    break;
                default:
                    C3833d.m12254d(C3861k.f9242a, "Performing configuration sync... FETCHING FAILED");
                    break;
            }
            C3833d.m12252c(C3861k.f9242a, "Performing configuration sync... FETCHING UP-TO DATE");
            return a.f9185a;
        }

        private void m12317c() {
            boolean z = false;
            if (!this.f9241d) {
                long j = this.f9238a.f9251i.getLong(C1042c.jE, 86400000);
                long j2 = this.f9238a.f9251i.getLong("last_updated", 0);
                if (j < 0 || j + j2 > System.currentTimeMillis()) {
                    C3833d.m12252c(C3861k.f9242a, "Performing configuration sync... No Need For A Configuration");
                    return;
                }
            }
            int a = m12315a(this.f9240c);
            boolean z2 = a == 100000 || a == 100001;
            if (z2) {
                Editor edit = this.f9238a.f9251i.edit();
                edit.putLong("last_updated", System.currentTimeMillis());
                edit.remove(Constants.VIDEO_TRACKING_EVENTS_KEY);
                if (this.f9240c) {
                    edit.putBoolean("opt_out_reported", true);
                }
                edit.apply();
                C3833d.m12252c(OneAudience.class.getSimpleName(), "One Audience SDK Server OK");
            } else {
                C3833d.m12254d(OneAudience.class.getSimpleName(), "One Audience SDK Server Failed");
            }
            if (!(this.f9240c && z2)) {
                if (z2 || a != 100004) {
                    z = true;
                }
                long j3 = z ? this.f9238a.f9251i.getLong(C1042c.jE, 86400000) : 3600000;
                C3833d.m12252c(C3861k.f9242a, "Status: " + String.valueOf(a));
                C3833d.m12252c(C3861k.f9242a, "Interval for next config is: " + String.valueOf(j3));
                C3843e.m12283a(this.f9238a.f9249g, j3);
            }
            C3833d.m12252c(C3861k.f9242a, "Performing configuration sync... DONE");
        }

        protected Void m12319a(Void... voidArr) {
            C3833d.m12252c(C3861k.f9242a, "Performing configuration sync...");
            if (this.f9238a.f9251i.getBoolean("optout", false)) {
                this.f9240c = true;
                m12317c();
            } else if (C3843e.m12288b(this.f9238a.f9249g, this.f9238a.f9251i) && C3795i.m12126a(this.f9238a.f9249g).m12134b()) {
                final HandlerThread handlerThread = new HandlerThread("MyHandlerThread");
                handlerThread.start();
                new Handler(handlerThread.getLooper()).postDelayed(new Runnable(this) {
                    final /* synthetic */ C3860c f9237b;

                    public void run() {
                        try {
                            this.f9237b.m12317c();
                            handlerThread.quit();
                        } catch (Throwable th) {
                            handlerThread.quit();
                        }
                    }
                }, 10000);
            } else {
                m12317c();
            }
            return null;
        }
    }

    private C3861k(Context context) {
        this.f9250h = context;
        this.f9249g = context.getApplicationContext();
        this.f9251i = this.f9249g.getSharedPreferences("oneaudience", 0);
        this.f9252j = new C3858b(this.f9249g);
    }

    public static synchronized C3861k m12321a(Context context) {
        C3861k c3861k;
        synchronized (C3861k.class) {
            if (f9243m == null && context != null) {
                f9243m = new C3861k(context);
            }
            c3861k = f9243m;
        }
        return c3861k;
    }

    private <Params, Progress, Result> void m12322a(C3856b c3856b, C3854n<Params, Progress, Result> c3854n, Params... paramsArr) {
        C3833d.m12253c(f9242a, "RUNNING TASK NOW: %s/%s", getClass().toString(), c3854n.getClass().toString());
        new C3881d(c3854n).m12393a(c3856b).m12394a((Object[]) paramsArr).m12392a();
    }

    private void m12323a(final String str, final boolean z, final boolean z2, int i) {
        new Handler().postDelayed(new Runnable(this) {
            final /* synthetic */ C3861k f9231d;

            public void run() {
                try {
                    this.f9231d.m12334a(str, z, z2);
                } catch (Throwable th) {
                    C3833d.m12248a(C3861k.f9242a, "Error while trying to post: ", th);
                }
            }
        }, (long) (i * 1000));
    }

    public static synchronized boolean m12324a() {
        boolean z;
        synchronized (C3861k.class) {
            z = f9243m != null;
        }
        return z;
    }

    private boolean m12325a(Context context, String str) {
        boolean z = false;
        C3833d.m12246a(f9242a, "Start permission handling");
        if ((context instanceof Activity) && C3795i.m12126a(context).m12133a((Activity) context)) {
            m12323a(str, false, true, 10);
            z = true;
        }
        this.f9251i.edit().putBoolean("permission_request_shown", true).apply();
        return z;
    }

    private void m12328b(final String str) {
        try {
            if (this.f9249g.getApplicationContext() instanceof Application) {
                Application application = (Application) this.f9249g.getApplicationContext();
                if (VERSION.SDK_INT >= 14) {
                    application.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks(this) {
                        final /* synthetic */ C3861k f9223b;

                        public void onActivityCreated(Activity activity, Bundle bundle) {
                        }

                        public void onActivityDestroyed(Activity activity) {
                        }

                        public void onActivityPaused(Activity activity) {
                        }

                        public void onActivityResumed(Activity activity) {
                            if (this.f9223b.f9251i != null) {
                                this.f9223b.f9251i.edit().putLong("lastOpenDate", System.currentTimeMillis()).apply();
                            }
                        }

                        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                        }

                        public void onActivityStarted(Activity activity) {
                        }

                        public synchronized void onActivityStopped(Activity activity) {
                            C3833d.m12252c(C3861k.f9242a, "Activity stopped checking if request should be sent.");
                            if (!this.f9223b.f9251i.getString(Constants.VIDEO_TRACKING_EVENTS_KEY, "").isEmpty()) {
                                this.f9223b.m12334a(str, false, true);
                            }
                        }
                    });
                }
            }
        } catch (Throwable e) {
            C3833d.m12250b(f9242a, "Exception Registering activity lifecycle", e);
        }
    }

    private boolean m12330f() {
        if (this.f9253k == 0 || System.currentTimeMillis() - this.f9253k >= 4000) {
            this.f9253k = System.currentTimeMillis();
            C3833d.m12252c(f9242a, "** setting timestamp **");
            return true;
        }
        C3833d.m12252c(f9242a, "** preventing duplicate call **");
        return false;
    }

    public void m12331a(int i) {
        Object a = C3843e.m12282a(this.f9249g, this.f9251i);
        if (this.f9249g != null && this.f9251i != null && !TextUtils.isEmpty(a)) {
            int i2 = this.f9251i.getInt("age_from_set_function", -1);
            if (i < 0 || i > 150) {
                C3833d.m12254d(f9242a, "Error occurred while saving age: age not in valid range. Valid range is: 0 - 150, found: " + i);
            } else if (i2 == i) {
                C3833d.m12254d(f9242a, "Error occurred while saving age: Same age saved");
            } else {
                this.f9251i.edit().putInt("age_from_set_function", i).apply();
                m12323a(a, false, true, 10);
            }
        }
    }

    public void m12332a(String str) {
        Object a = C3843e.m12282a(this.f9249g, this.f9251i);
        if (this.f9249g != null && this.f9251i != null && !TextUtils.isEmpty(a)) {
            if (this.f9251i.getString("email_from_set_function", "").equalsIgnoreCase(str)) {
                C3833d.m12254d(f9242a, "Error occurred while saving email address: same email already saved");
            } else if (TextUtils.isEmpty(str)) {
                C3833d.m12254d(f9242a, "Error occurred while saving email address: email cannot be empty or null");
            } else {
                this.f9251i.edit().putString("email_from_set_function", str).apply();
                m12323a(a, false, true, 10);
            }
        }
    }

    public void m12333a(final String str, final Context context, final boolean z) {
        this.f9251i.edit().putString("app_key", str).apply();
        this.f9251i.edit().putLong("lastOpenDate", System.currentTimeMillis()).apply();
        if ((context instanceof Activity) || (context instanceof Application)) {
            m12328b(str);
        }
        String string = this.f9251i.getString("server_config", "");
        boolean z2 = this.f9251i.getBoolean("permission_request_shown", false);
        m12334a(str, false, !this.f9251i.getString(Constants.VIDEO_TRACKING_EVENTS_KEY, "").isEmpty());
        if (string.isEmpty()) {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable(this) {
                final /* synthetic */ C3861k f9227d;

                public void run() {
                    try {
                        if (!z || !this.f9227d.m12325a(context, str)) {
                            this.f9227d.m12334a(str, false, true);
                        }
                    } catch (Throwable th) {
                        C3833d.m12248a(C3861k.f9242a, "Error while trying to post: ", th);
                    }
                }
            }, 4000);
        } else if (!z2 && z) {
            m12325a(context, str);
        }
        C3833d.m12252c(OneAudience.class.getSimpleName(), "One Audience SDK Init Completed");
    }

    public void m12334a(String str, boolean z, boolean z2) {
        try {
            if (!this.f9251i.getBoolean("opt_out_reported", false) && m12330f()) {
                m12322a(this.f9252j, new C3860c(this, str, z, z2), new Void[0]);
            }
        } catch (Throwable e) {
            C3833d.m12250b(f9242a, "Post request Exception", e);
        }
    }

    public boolean m12335a(Context context, String str, boolean z) {
        try {
            this.f9254l = z;
            if (!C3843e.m12286a(this.f9249g, "showEula", false)) {
                C3833d.m12252c(f9242a, "No Eula meta data");
                return true;
            } else if (this.f9251i.getBoolean("eula_shown", false)) {
                return this.f9251i.getBoolean(C1404b.f2104H, false);
            } else {
                if (!(context instanceof Activity)) {
                    return false;
                }
                this.f9251i.edit().putString("app_key", str).apply();
                Activity activity = (Activity) context;
                new Bundle().putString("appKey", str);
                m12322a(this.f9252j, new C3855a(this, str, activity), new Void[0]);
                return false;
            }
        } catch (Throwable e) {
            C3833d.m12250b(f9242a, "Show EULA Exception", e);
            return false;
        }
    }

    public void m12336b() {
        String a = C3843e.m12282a(this.f9249g, this.f9251i);
        this.f9251i.edit().putBoolean("optout", true).apply();
        if (TextUtils.isEmpty(a)) {
            C3833d.m12254d(f9242a, "Couldn't opt out from oneAudience");
        } else {
            m12334a(a, true, true);
        }
    }

    public void m12337b(int i) {
        Object a = C3843e.m12282a(this.f9249g, this.f9251i);
        if (this.f9249g != null && this.f9251i != null && !TextUtils.isEmpty(a)) {
            int i2 = this.f9251i.getInt("gender_from_set_function", -1);
            if (i != 0 && i != 1 && i != 2) {
                C3833d.m12254d(f9242a, "Error occurred while saving gender: invalid gender code. Gender code should be: OneAudience.GENDER_MALE, OneAudience.GENDER_FEMALE or OneAudience.GENDER_UNKNOWN");
            } else if (i2 == i) {
                C3833d.m12254d(f9242a, "Error occurred while saving gender: Same gender already saved");
            } else {
                this.f9251i.edit().putInt("gender_from_set_function", i).apply();
                m12323a(a, false, true, 10);
            }
        }
    }

    public void m12338c() {
        try {
            String a = C3843e.m12282a(this.f9249g, this.f9251i);
            Editor edit = this.f9251i.edit();
            edit.putBoolean(C1404b.f2104H, true);
            edit.putBoolean("eula_shown", true);
            boolean commit = edit.commit();
            C3833d.m12253c(f9242a, "Eula accepted save status: %b", Boolean.valueOf(commit));
            if (!TextUtils.isEmpty(a)) {
                m12333a(a, this.f9250h, this.f9254l);
            }
        } catch (Throwable e) {
            C3833d.m12250b(f9242a, "EULA Accepted Exception", e);
        }
    }

    public void m12339d() {
        try {
            Editor edit = this.f9251i.edit();
            edit.putBoolean("eula_shown", true);
            boolean commit = edit.commit();
            C3833d.m12253c(f9242a, "Eula declined save status: %b", Boolean.valueOf(commit));
        } catch (Throwable e) {
            C3833d.m12250b(f9242a, "EULA Declined Exception", e);
        }
    }
}
