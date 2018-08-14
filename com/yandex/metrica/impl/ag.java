package com.yandex.metrica.impl;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import com.yandex.metrica.CounterConfiguration;
import com.yandex.metrica.MetricaService.C4271b;
import com.yandex.metrica.impl.C4511p.C4510a;
import com.yandex.metrica.impl.GoogleAdvertisingIdGetter.C4301b;
import com.yandex.metrica.impl.ob.C4484g;
import com.yandex.metrica.impl.ob.C4490o;
import com.yandex.metrica.impl.ob.C4492q;
import com.yandex.metrica.impl.ob.C4493r;
import com.yandex.metrica.impl.ob.C4503t;
import com.yandex.metrica.impl.ob.bp;
import com.yandex.metrica.impl.ob.cd;
import com.yandex.metrica.impl.ob.ci;
import com.yandex.metrica.impl.ob.co;
import com.yandex.metrica.impl.ob.cp;
import com.yandex.metrica.impl.ob.dr;
import com.yandex.metrica.impl.ob.ds;
import com.yandex.metrica.impl.ob.ef;
import com.yandex.metrica.impl.utils.C4531k;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;

public class ag implements ae {
    private static final Executor f11614c = new cp();
    private static final ExecutorService f11615d = Executors.newSingleThreadExecutor();
    private static final Map<String, C4503t> f11616e = new HashMap();
    private static final C4492q f11617f = new C4492q();
    private Context f11618a;
    private C4271b f11619b;

    private final class C4319a implements Runnable {
        final /* synthetic */ ag f11609a;
        private final int f11610b;
        private final C4372h f11611c;
        private final Bundle f11612d;
        private final Context f11613e;

        C4319a(ag agVar, Context context, C4372h c4372h, Bundle bundle, int i) {
            this.f11609a = agVar;
            this.f11613e = context.getApplicationContext();
            this.f11610b = i;
            this.f11611c = c4372h;
            this.f11612d = bundle;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            r8 = this;
            r0 = r8.f11612d;
            r0 = com.yandex.metrica.CounterConfiguration.m14232b(r0);
            r1 = com.yandex.metrica.impl.ag.m14533a(r0);
            if (r1 == 0) goto L_0x000d;
        L_0x000c:
            return;
        L_0x000d:
            r1 = r8.f11609a;
            r2 = r8.f11611c;
            r3 = r8.f11610b;
            r1 = r1.m14541a(r2, r0, r3);
            if (r1 == 0) goto L_0x000c;
        L_0x0019:
            r2 = r1.m16105b();
            com.yandex.metrica.impl.ag.m14538b(r0, r2);
            r2 = com.yandex.metrica.impl.ag.f11616e;
            monitor-enter(r2);
            r3 = r8.f11609a;	 Catch:{ all -> 0x009c }
            r3.m14537b(r0);	 Catch:{ all -> 0x009c }
            r3 = r8.f11609a;	 Catch:{ all -> 0x009c }
            r4 = r8.f11613e;	 Catch:{ all -> 0x009c }
            r4 = r4.getPackageName();	 Catch:{ all -> 0x009c }
            r5 = r0.m14267f();	 Catch:{ all -> 0x009c }
            r4 = r4.equals(r5);	 Catch:{ all -> 0x009c }
            r5 = r0.m14280m();	 Catch:{ all -> 0x009c }
            com.yandex.metrica.impl.C4543y.m16305a(r3.f11618a).m16312a(r3, r4, r5);	 Catch:{ all -> 0x009c }
            r3 = r0.m14277j();	 Catch:{ all -> 0x009c }
            r4 = r8.f11612d;	 Catch:{ all -> 0x009c }
            r5 = "COUNTER_MIGRATION_CFG_OBJ";
            r4 = r4.containsKey(r5);	 Catch:{ all -> 0x009c }
            if (r4 == 0) goto L_0x008b;
        L_0x0050:
            r4 = r8.f11612d;	 Catch:{ all -> 0x009c }
            r4 = com.yandex.metrica.impl.ag.C4319a.m14527a(r4);	 Catch:{ all -> 0x009c }
            if (r4 == 0) goto L_0x008b;
        L_0x0058:
            r5 = r4.m14236D();	 Catch:{ all -> 0x009c }
            if (r5 == 0) goto L_0x008b;
        L_0x005e:
            r5 = r8.f11613e;	 Catch:{ all -> 0x009c }
            r6 = r8.f11610b;	 Catch:{ all -> 0x009c }
            r6 = java.lang.Integer.valueOf(r6);	 Catch:{ all -> 0x009c }
            r7 = 0;
            r5 = com.yandex.metrica.impl.ob.C4493r.m16102a(r5, r4, r6, r7);	 Catch:{ all -> 0x009c }
            r6 = com.yandex.metrica.impl.ag.f11616e;	 Catch:{ all -> 0x009c }
            r7 = r5.toString();	 Catch:{ all -> 0x009c }
            r6 = r6.containsKey(r7);	 Catch:{ all -> 0x009c }
            if (r6 != 0) goto L_0x008b;
        L_0x0079:
            r6 = new com.yandex.metrica.CounterConfiguration;	 Catch:{ all -> 0x009c }
            r6.<init>(r4);	 Catch:{ all -> 0x009c }
            r6.m14248a(r3);	 Catch:{ all -> 0x009c }
            r3 = r8.f11609a;	 Catch:{ all -> 0x009c }
            r4 = 0;
            r3 = com.yandex.metrica.impl.ag.m14528a(r3, r5, r6, r4);	 Catch:{ all -> 0x009c }
            r3.m16159f();	 Catch:{ all -> 0x009c }
        L_0x008b:
            r3 = r8.f11609a;	 Catch:{ all -> 0x009c }
            r4 = r8.f11611c;	 Catch:{ all -> 0x009c }
            r1 = com.yandex.metrica.impl.ag.m14528a(r3, r1, r0, r4);	 Catch:{ all -> 0x009c }
            r3 = com.yandex.metrica.impl.ag.m14534a(r1);	 Catch:{ all -> 0x009c }
            if (r3 == 0) goto L_0x009f;
        L_0x0099:
            monitor-exit(r2);	 Catch:{ all -> 0x009c }
            goto L_0x000c;
        L_0x009c:
            r0 = move-exception;
            monitor-exit(r2);	 Catch:{ all -> 0x009c }
            throw r0;
        L_0x009f:
            r3 = r8.f11613e;	 Catch:{ all -> 0x009c }
            r3 = com.yandex.metrica.impl.C4543y.m16305a(r3);	 Catch:{ all -> 0x009c }
            r4 = r8.f11611c;	 Catch:{ all -> 0x009c }
            r4 = r4.m15068e();	 Catch:{ all -> 0x009c }
            r3.m16310a(r4);	 Catch:{ all -> 0x009c }
            r3 = r8.f11611c;	 Catch:{ all -> 0x009c }
            r3 = r3.m15062c();	 Catch:{ all -> 0x009c }
            r3 = com.yandex.metrica.impl.C4511p.m16197a(r3);	 Catch:{ all -> 0x009c }
            if (r3 != 0) goto L_0x00bd;
        L_0x00ba:
            r1.m16143a(r0);	 Catch:{ all -> 0x009c }
        L_0x00bd:
            r0 = r8.f11611c;	 Catch:{ all -> 0x009c }
            r0 = com.yandex.metrica.impl.ag.m14535a(r1, r0);	 Catch:{ all -> 0x009c }
            if (r0 != 0) goto L_0x00ca;
        L_0x00c5:
            r0 = r8.f11611c;	 Catch:{ all -> 0x009c }
            r1.m16144a(r0);	 Catch:{ all -> 0x009c }
        L_0x00ca:
            monitor-exit(r2);	 Catch:{ all -> 0x009c }
            goto L_0x000c;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ag.a.run():void");
        }

        private static CounterConfiguration m14527a(Bundle bundle) {
            CounterConfiguration counterConfiguration;
            if (bundle != null) {
                try {
                    counterConfiguration = (CounterConfiguration) bundle.getParcelable("COUNTER_MIGRATION_CFG_OBJ");
                } catch (Throwable th) {
                    return null;
                }
            }
            counterConfiguration = null;
            return counterConfiguration;
        }
    }

    static /* synthetic */ boolean m14535a(C4503t c4503t, C4372h c4372h) {
        if (C4510a.EVENT_TYPE_STARTUP.m16188a() == c4372h.m15062c()) {
            c4503t.m16157e();
            return true;
        } else if (C4510a.EVENT_TYPE_REFERRER_RECEIVED.m16188a() != c4372h.m15062c()) {
            return false;
        } else {
            c4503t.m16150b(c4372h);
            return true;
        }
    }

    public ag(Context context, C4271b c4271b) {
        this.f11618a = context;
        this.f11619b = c4271b;
    }

    public void mo6981a() {
        new bd(this.f11618a).m14557a(this.f11618a);
        C4531k.m16287a().m16289a(this.f11618a);
        C4301b.f11553a.m14437a(this.f11618a);
        cd cdVar = new cd(bp.m15358a(this.f11618a).m15367d(), this.f11618a.getPackageName());
        co.m15605a().m15608a(this.f11618a, cdVar.m15510b(null), cdVar.m15524h(null));
        m14548a(cdVar);
        ci.m15569a().m15575a(this.f11618a);
    }

    public void mo6985a(Intent intent, int i) {
        m14536b(intent, i);
    }

    public void mo6986a(Intent intent, int i, int i2) {
        m14536b(intent, i2);
    }

    public void mo6984a(Intent intent) {
        dr.m15783a(this.f11618a).m15784a();
        C4543y.m16305a(this.f11618a).m16311a((Object) this);
        ef.m15896a(this.f11618a).mo7084a();
    }

    public void mo6988b(Intent intent) {
        dr.m15783a(this.f11618a).m15784a();
    }

    public void mo6989c(Intent intent) {
        String encodedAuthority = intent.getData().getEncodedAuthority();
        synchronized (f11616e) {
            for (Entry entry : new HashMap(f11616e).entrySet()) {
                Object obj;
                String str = (String) entry.getKey();
                C4503t c4503t = (C4503t) entry.getValue();
                if (str == null || c4503t == null || str.startsWith(encodedAuthority)) {
                    obj = 1;
                } else {
                    obj = null;
                }
                if (obj != null) {
                    f11616e.remove(str);
                    if (c4503t != null) {
                        c4503t.m16153c();
                    }
                }
            }
        }
        if (f11616e.isEmpty()) {
            dr.m15783a(this.f11618a).m15785b();
        }
    }

    public void mo6983a(int i, String str, int i2, String str2, Bundle bundle) throws RemoteException {
        bundle.setClassLoader(CounterConfiguration.class.getClassLoader());
        m14529a(i, new C4372h(str2, str, i2), bundle);
    }

    public void mo6982a(int i, Bundle bundle) throws RemoteException {
        bundle.setClassLoader(CounterConfiguration.class.getClassLoader());
        m14529a(i, C4372h.m15051b(bundle), bundle);
    }

    void m14548a(cd cdVar) {
        Object l = cdVar.m15532l();
        if (TextUtils.isEmpty(l)) {
            C4484g.m16084a().m16089a(C4490o.class);
            return;
        }
        try {
            C4484g.m16084a().m16092b(new C4490o(new ds(l)));
        } catch (JSONException e) {
        }
    }

    private void m14536b(Intent intent, int i) {
        if (intent != null) {
            Object obj;
            intent.getExtras().setClassLoader(CounterConfiguration.class.getClassLoader());
            if (intent == null || intent.getData() == null) {
                obj = 1;
            } else {
                obj = null;
            }
            if (obj == null) {
                C4372h b = C4372h.m15051b(intent.getExtras());
                if (b.m15080n()) {
                    int intExtra = intent.getIntExtra("EXTRA_KEY_KEY_START_TYPE", C4510a.EVENT_TYPE_UNDEFINED.m16188a());
                    b.m15053a(intExtra).mo7031b(intent.getStringExtra("EXTRA_KEY_KEY_START_EVENT")).mo7032c("");
                }
                if ((b.m15079m() | b.m15080n()) == 0) {
                    Bundle bundleExtra = intent.getBundleExtra("EXTRA_KEY_LIB_CFG");
                    if (bundleExtra == null) {
                        bundleExtra = intent.getExtras();
                    }
                    CounterConfiguration b2 = CounterConfiguration.m14232b(bundleExtra);
                    if (b2 == null) {
                        obj = 1;
                    } else {
                        obj = null;
                    }
                    if (obj == null) {
                        String encodedAuthority = intent.getData().getEncodedAuthority();
                        m14538b(b2, encodedAuthority);
                        m14537b(b2);
                        C4543y.m16305a(this.f11618a).m16310a(b.m15068e());
                        try {
                            C4503t c4503t = new C4503t(this.f11618a, f11614c, C4493r.m16102a(this.f11618a, b2, null, encodedAuthority), b2, f11617f);
                            c4503t.m16144a(b);
                            c4503t.m16155d();
                        } catch (Exception e) {
                        }
                    }
                }
            }
        }
        this.f11619b.mo6965a(i);
    }

    private void m14537b(CounterConfiguration counterConfiguration) {
        if (TextUtils.isEmpty(counterConfiguration.m14273h())) {
            m14540c(counterConfiguration);
            return;
        }
        CharSequence d = ci.m15569a().m15580d();
        Object obj = (TextUtils.isEmpty(d) || TextUtils.equals(counterConfiguration.m14273h(), d)) ? 1 : null;
        if (obj == null) {
            m14540c(counterConfiguration);
        }
    }

    private void m14540c(CounterConfiguration counterConfiguration) {
        String g = ci.m15571g(this.f11618a, counterConfiguration.m14267f());
        if (!TextUtils.isEmpty(g)) {
            counterConfiguration.m14265e(g);
        }
    }

    private static void m14538b(CounterConfiguration counterConfiguration, String str) {
        if (TextUtils.isEmpty(counterConfiguration.m14267f())) {
            counterConfiguration.m14258c(str);
        }
    }

    C4493r m14541a(C4372h c4372h, CounterConfiguration counterConfiguration, int i) {
        if (!C4511p.m16198a(c4372h)) {
            return C4493r.m16102a(this.f11618a, counterConfiguration, Integer.valueOf(i), null);
        }
        int i2;
        String l = c4372h.m15078l();
        for (ApplicationInfo applicationInfo : this.f11618a.getPackageManager().getInstalledApplications(0)) {
            if (applicationInfo.packageName.equals(l)) {
                i2 = 1;
                break;
            }
        }
        i2 = 0;
        if (i2 != 0) {
            return C4493r.m16103a(l);
        }
        return null;
    }

    private void m14529a(int i, C4372h c4372h, Bundle bundle) {
        if (!c4372h.m15080n()) {
            f11615d.execute(new C4319a(this, this.f11618a, c4372h, bundle, i));
        }
    }

    public void mo6987b() {
        C4543y.m16305a(this.f11618a).m16314b((Object) this);
        ef.m15896a(this.f11618a).mo7087b();
    }

    static /* synthetic */ C4503t m14528a(ag agVar, C4493r c4493r, CounterConfiguration counterConfiguration, C4372h c4372h) {
        if (c4493r == null) {
            return null;
        }
        C4503t c4503t = (C4503t) f11616e.get(c4493r.toString());
        if (c4503t == null) {
            c4503t = new C4503t(agVar.f11618a, f11614c, c4493r, counterConfiguration, f11617f);
            if (c4372h != null && C4511p.m16198a(c4372h)) {
                return c4503t;
            }
            f11616e.put(c4493r.toString(), c4503t);
            return c4503t;
        }
        c4503t.m16149b(counterConfiguration);
        return c4503t;
    }

    static /* synthetic */ boolean m14533a(CounterConfiguration counterConfiguration) {
        return counterConfiguration == null;
    }

    static /* synthetic */ boolean m14534a(C4503t c4503t) {
        return c4503t == null || c4503t.mo7115o();
    }
}
