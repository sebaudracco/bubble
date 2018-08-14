package com.inmobi.signals.activityrecognition;

import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.inmobi.commons.core.utilities.C3156e;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.p112a.C3105a;
import com.inmobi.signals.C3277o;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ActivityRecognitionSampler */
public class C3253b {
    private static final String f8177a = C3253b.class.getSimpleName();
    private static final Object f8178b = new Object();
    private static volatile C3253b f8179c;
    private static List<C3251a> f8180d;
    private HandlerThread f8181e = new HandlerThread("ActivityRecognitionSampler");
    private Handler f8182f;

    /* compiled from: ActivityRecognitionSampler */
    static class C3252a extends Handler {
        C3252a(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    int c = ActivityRecognitionManager.m10868c();
                    Logger.m10359a(InternalLogLevel.INTERNAL, C3253b.f8177a, "Polling for ActivityRecognition. Detected activity:" + c);
                    if (c != -1) {
                        C3253b.f8180d.add(new C3251a(c, System.currentTimeMillis()));
                    }
                    if (C3253b.f8180d.size() < C3277o.m10989a().m10994e().m11079s()) {
                        sendEmptyMessageDelayed(0, (long) (C3277o.m10989a().m10994e().m11078r() * 1000));
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public static C3253b m10873a() {
        C3253b c3253b = f8179c;
        if (c3253b == null) {
            synchronized (f8178b) {
                c3253b = f8179c;
                if (c3253b == null) {
                    c3253b = new C3253b();
                    f8179c = c3253b;
                }
            }
        }
        return c3253b;
    }

    private C3253b() {
        f8180d = new ArrayList();
        this.f8181e.start();
        this.f8182f = new C3252a(this.f8181e.getLooper());
    }

    public void m10878b() {
        if (C3253b.m10876h() && C3253b.m10877i() && !this.f8182f.hasMessages(0)) {
            ActivityRecognitionManager.m10864a();
            this.f8182f.sendEmptyMessage(0);
        }
    }

    public void m10879c() {
        if (C3253b.m10876h() && C3253b.m10877i() && this.f8182f.hasMessages(0)) {
            ActivityRecognitionManager.m10867b();
            this.f8182f.removeMessages(0);
        }
    }

    public List<C3251a> m10880d() {
        return f8180d;
    }

    public void m10881e() {
        f8180d = new ArrayList();
    }

    private static boolean m10876h() {
        String str = "com.google.android.gms.permission.ACTIVITY_RECOGNITION";
        if (C3105a.m10076a() && C3156e.m10410a(C3105a.m10078b(), "signals", "com.google.android.gms.permission.ACTIVITY_RECOGNITION")) {
            return true;
        }
        Logger.m10359a(InternalLogLevel.INTERNAL, f8177a, "Activity recognition sampling did not work due to missing permission.");
        return false;
    }

    private static boolean m10877i() {
        try {
            if (C3105a.m10078b().getPackageManager().queryIntentServices(new Intent(C3105a.m10078b(), ActivityRecognitionManager.class), 65536).size() > 0) {
                return true;
            }
            Logger.m10359a(InternalLogLevel.INTERNAL, f8177a, "Activity recognition sampling did not work due to missing service in manifest.");
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
