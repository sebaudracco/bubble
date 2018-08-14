package com.inmobi.commons.core.utilities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.inmobi.commons.core.p115d.C3132b;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.p112a.C3105a;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: ApplicationFocusChangeObserver */
public class C3152a {
    private static final String f7772a = C3152a.class.getSimpleName();
    private static List<C2982b> f7773b = new ArrayList();
    private static Object f7774c;
    private static boolean f7775d = false;
    private static HandlerThread f7776e = null;
    private static final Object f7777f = new Object();
    private static volatile C3152a f7778g;

    /* compiled from: ApplicationFocusChangeObserver */
    public interface C2982b {
        void mo6162a(boolean z);
    }

    /* compiled from: ApplicationFocusChangeObserver */
    class C31461 implements InvocationHandler {
        final /* synthetic */ C3152a f7761a;
        private final Handler f7762b = new C3148a(C3152a.f7776e.getLooper());
        private WeakReference<Activity> f7763c;

        C31461(C3152a c3152a) {
            this.f7761a = c3152a;
        }

        void m10362a(Activity activity) {
            if (this.f7763c != null && this.f7763c.get() == activity) {
                this.f7762b.sendEmptyMessageDelayed(1001, 3000);
            }
        }

        void m10363b(Activity activity) {
            if (this.f7763c == null || this.f7763c.get() != activity) {
                this.f7763c = new WeakReference(activity);
            }
            this.f7762b.removeMessages(1001);
            this.f7762b.sendEmptyMessage(1002);
        }

        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            if (objArr != null) {
                String name = method.getName();
                int i = -1;
                switch (name.hashCode()) {
                    case 195654633:
                        if (name.equals("onActivityResumed")) {
                            i = 1;
                            break;
                        }
                        break;
                    case 1495889555:
                        if (name.equals("onActivityStarted")) {
                            i = 0;
                            break;
                        }
                        break;
                    case 1508755423:
                        if (name.equals("onActivityStopped")) {
                            i = 2;
                            break;
                        }
                        break;
                }
                switch (i) {
                    case 0:
                    case 1:
                        m10363b((Activity) objArr[0]);
                        break;
                    case 2:
                        m10362a((Activity) objArr[0]);
                        break;
                }
            }
            return null;
        }
    }

    /* compiled from: ApplicationFocusChangeObserver */
    static class C3148a extends Handler {
        boolean f7765a = true;

        public C3148a(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            if (!C3152a.f7775d) {
                if (message.what == 1001 && this.f7765a) {
                    this.f7765a = false;
                    C3152a.m10384b(false);
                    Logger.m10359a(InternalLogLevel.INTERNAL, C3152a.f7772a, "App has gone to background.");
                } else if (message.what == 1002 && !this.f7765a) {
                    this.f7765a = true;
                    C3152a.m10384b(true);
                    Logger.m10359a(InternalLogLevel.INTERNAL, C3152a.f7772a, "App has come to foreground.");
                }
            }
        }
    }

    public static C3152a m10381a() {
        C3152a c3152a = f7778g;
        if (c3152a == null) {
            synchronized (f7777f) {
                c3152a = f7778g;
                if (c3152a == null) {
                    c3152a = new C3152a();
                    f7778g = c3152a;
                }
            }
        }
        return c3152a;
    }

    private C3152a() {
    }

    @TargetApi(14)
    private void m10390h() {
        if (C3105a.m10076a()) {
            String str = "registerActivityLifecycleCallbacks";
            str = "ActivityLifecycleCallbacks";
            str = "onActivityStarted";
            str = "onActivityResumed";
            str = "onActivityStopped";
            f7776e = new HandlerThread("ApplicationFocusChangeObserverHandler");
            f7776e.start();
            Class[] declaredClasses = Application.class.getDeclaredClasses();
            Class cls = null;
            int length = declaredClasses.length;
            int i = 0;
            while (i < length) {
                Class cls2 = declaredClasses[i];
                if (cls2.getSimpleName().equalsIgnoreCase("ActivityLifecycleCallbacks")) {
                    new Class[1][0] = cls2;
                } else {
                    cls2 = cls;
                }
                i++;
                cls = cls2;
            }
            f7774c = Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new C31461(this));
            Application application = (Application) C3105a.m10078b();
            if (f7774c != null) {
                try {
                    Application.class.getMethod("registerActivityLifecycleCallbacks", new Class[]{cls}).invoke(application, new Object[]{f7774c});
                } catch (Throwable e) {
                    Logger.m10360a(InternalLogLevel.INTERNAL, f7772a, "Error while registering activity life cycle listener.", e);
                } catch (Throwable e2) {
                    Logger.m10360a(InternalLogLevel.INTERNAL, f7772a, "Error while registering activity life cycle listener.", e2);
                } catch (Throwable e22) {
                    Logger.m10360a(InternalLogLevel.INTERNAL, f7772a, "Error while registering activity life cycle listener.", e22);
                } catch (Exception e3) {
                    try {
                        Map hashMap = new HashMap();
                        hashMap.put("type", "GenericException");
                        hashMap.put("message", e3.getMessage() + "");
                        C3135c.m10255a().m10280a("root", "ExceptionCaught", hashMap);
                    } catch (Exception e4) {
                        Logger.m10359a(InternalLogLevel.INTERNAL, f7772a, "Error in submitting telemetry event : (" + e3.getMessage() + ")");
                    }
                }
            }
        }
    }

    @TargetApi(14)
    private void m10391i() {
        String str = "unregisterActivityLifecycleCallbacks";
        try {
            Application application = (Application) C3105a.m10078b();
            if (f7774c != null) {
                Application.class.getMethod("unregisterActivityLifecycleCallbacks", (Class[]) null).invoke(application, (Object[]) f7774c);
            }
        } catch (Throwable e) {
            Logger.m10360a(InternalLogLevel.INTERNAL, f7772a, "Error while unregistering activity life cycle listener.", e);
        } catch (Throwable e2) {
            Logger.m10360a(InternalLogLevel.INTERNAL, f7772a, "Error while unregistering activity life cycle listener.", e2);
        } catch (Throwable e22) {
            Logger.m10360a(InternalLogLevel.INTERNAL, f7772a, "Error while unregistering activity life cycle listener.", e22);
        } catch (Exception e3) {
            try {
                Map hashMap = new HashMap();
                hashMap.put("type", "GenericException");
                hashMap.put("message", e3.getMessage() + "");
                C3135c.m10255a().m10280a("root", "ExceptionCaught", hashMap);
            } catch (Exception e4) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f7772a, "Error in submitting telemetry event : (" + e3.getMessage() + ")");
            }
        }
        try {
            if (f7776e != null) {
                f7776e.stop();
                f7776e = null;
            }
        } catch (Throwable e222) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7772a, "Error in activityCallbackHandlerThread.stop : (" + e222.getMessage() + ")");
            C3135c.m10255a().m10279a(new C3132b(e222));
        }
    }

    public void m10392a(C2982b c2982b) {
        if (VERSION.SDK_INT >= 14) {
            f7773b.add(c2982b);
            if (f7773b.size() == 1) {
                m10390h();
            }
        }
    }

    public void m10393b(C2982b c2982b) {
        if (VERSION.SDK_INT >= 14) {
            f7773b.remove(c2982b);
            if (f7773b.size() == 0) {
                m10391i();
            }
        }
    }

    private static void m10384b(final boolean z) {
        Context b = C3105a.m10078b();
        if (b != null) {
            new Handler(b.getMainLooper()).post(new Runnable() {
                public void run() {
                    for (C2982b a : C3152a.f7773b) {
                        try {
                            a.mo6162a(z);
                        } catch (Exception e) {
                            Logger.m10359a(InternalLogLevel.INTERNAL, C3152a.f7772a, "SDK encountered an unexpected error in handling focus change event; " + e.getMessage());
                        }
                    }
                }
            });
        }
    }

    public static void m10383b() {
        f7775d = true;
    }

    public static void m10385c() {
        f7775d = false;
    }
}
