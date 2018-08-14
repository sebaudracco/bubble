package com.inmobi.signals;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.inmobi.commons.core.p115d.C3110g;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.C3156e;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.p112a.C3105a;
import com.inmobi.signals.C3280p.C3279b;
import com.inmobi.signals.activityrecognition.C3253b;
import com.inmobi.signals.p120b.C3254a;
import com.inmobi.signals.p120b.C3255b;
import com.inmobi.signals.p120b.C3259c;
import com.inmobi.signals.p120b.C3259c.C3258a;
import java.util.ArrayList;
import java.util.List;

/* compiled from: IceCollector */
class C3270i {
    private static final String f8222a = C3270i.class.getSimpleName();
    private HandlerThread f8223b = new HandlerThread("DataCollectionHandler");
    private C3269a f8224c;

    /* compiled from: IceCollector */
    static class C3269a extends Handler {
        private List<C3275m> f8221a = new ArrayList();

        C3269a(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    m10951b();
                    return;
                case 2:
                    m10952c();
                    return;
                case 3:
                    Logger.m10359a(InternalLogLevel.INTERNAL, C3270i.f8222a, "Polling for samples.");
                    if (VERSION.SDK_INT >= 14 || C3269a.m10950a()) {
                        if (C3277o.m10989a().m10994e().m11077q()) {
                            C3253b.m10873a().m10878b();
                        } else {
                            C3253b.m10873a().m10879c();
                        }
                        m10953d();
                        sendEmptyMessageDelayed(3, (long) (C3277o.m10989a().m10994e().m11062b() * 1000));
                        return;
                    }
                    sendEmptyMessage(2);
                    return;
                case 4:
                    m10948a(m10954e());
                    m10955f();
                    return;
                default:
                    return;
            }
        }

        private void m10951b() {
            Logger.m10359a(InternalLogLevel.INTERNAL, C3270i.f8222a, "User data collection started.");
            sendEmptyMessage(3);
        }

        private void m10952c() {
            Logger.m10359a(InternalLogLevel.INTERNAL, C3270i.f8222a, "Stopping user data collection.");
            C3253b.m10873a().m10879c();
            removeMessages(3);
            sendEmptyMessage(4);
        }

        private void m10953d() {
            final C3275m c3275m = new C3275m();
            c3275m.m10972a(C3255b.m10891a());
            c3275m.m10974a(LocationInfo.m10813a().m10832f());
            if (!C3277o.m10989a().m10994e().m11071k() || !C3255b.m10900c()) {
                m10949a(c3275m);
            } else if (!C3259c.m10908a(new C3258a(this) {
                final /* synthetic */ C3269a f8220b;

                public void mo6268a() {
                    Logger.m10359a(InternalLogLevel.INTERNAL, C3270i.f8222a, "Wifi scan timeout.");
                    this.f8220b.m10949a(c3275m);
                }

                public void mo6269a(List<C3254a> list) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, C3270i.f8222a, "Wifi scan successful.");
                    c3275m.m10973a((List) list);
                    this.f8220b.m10949a(c3275m);
                }
            })) {
                m10949a(c3275m);
            }
        }

        private void m10949a(C3275m c3275m) {
            if (this.f8221a != null && c3275m.m10975a()) {
                this.f8221a.add(c3275m);
                if (this.f8221a.size() > C3277o.m10989a().m10994e().m11064d()) {
                    try {
                        C3135c.m10255a().m10279a(new C3110g("signals", "SampleSizeExceeded"));
                    } catch (Exception e) {
                        Logger.m10359a(InternalLogLevel.INTERNAL, C3270i.f8222a, "Error in submitting telemetry event : (" + e.getMessage() + ")");
                    }
                    while (this.f8221a.size() > C3277o.m10989a().m10994e().m11064d()) {
                        this.f8221a.remove(0);
                    }
                }
            }
        }

        private C3274l m10954e() {
            C3274l c3274l = new C3274l();
            c3274l.m10969a(LocationInfo.m10813a().m10831e());
            c3274l.m10968a(this.f8221a);
            c3274l.m10967a(C3276n.m10977a().m10984d());
            c3274l.m10970b(C3253b.m10873a().m10880d());
            return c3274l;
        }

        private void m10955f() {
            C3253b.m10873a().m10881e();
            this.f8221a = new ArrayList();
        }

        private void m10948a(C3274l c3274l) {
            C3279b e = C3277o.m10989a().m10994e();
            new C3272j(new C3273k(e.m11065e(), e.m11066f(), e.m11067g(), C3277o.m10989a().m10993d(), c3274l)).m10962a();
        }

        public static boolean m10950a() {
            Context b = C3105a.m10078b();
            if (b == null) {
                return false;
            }
            ActivityManager activityManager = (ActivityManager) b.getSystemService(C1404b.aw);
            if (activityManager != null) {
                try {
                    if (((RunningTaskInfo) activityManager.getRunningTasks(1).get(0)).topActivity.getPackageName().equalsIgnoreCase(C3105a.m10078b().getPackageName())) {
                        Logger.m10359a(InternalLogLevel.INTERNAL, C3270i.f8222a, "Is app in foreground check for below ICS: true");
                        return true;
                    }
                } catch (Throwable e) {
                    Logger.m10360a(InternalLogLevel.INTERNAL, C3270i.f8222a, "NPE while determining if app is in foreground for below ICS devices.", e);
                }
            }
            return false;
        }
    }

    public C3270i() {
        this.f8223b.start();
        this.f8224c = new C3269a(this.f8223b.getLooper());
    }

    public synchronized void m10957a() {
        if (VERSION.SDK_INT < 14 && !m10958b()) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f8222a, "User data collection can not be started as the data collector is not properly initialized.");
        } else if (this.f8224c.hasMessages(3)) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f8222a, "User data collection already running.");
        } else {
            this.f8224c.removeMessages(2);
            this.f8224c.sendEmptyMessage(1);
        }
    }

    public boolean m10958b() {
        try {
            return C3156e.m10410a(C3105a.m10078b(), "signals", "android.permission.GET_TASKS");
        } catch (Exception e) {
            return false;
        }
    }

    public void m10959c() {
        this.f8224c.sendEmptyMessageDelayed(2, (long) (C3277o.m10989a().m10994e().m11063c() * 1000));
    }
}
