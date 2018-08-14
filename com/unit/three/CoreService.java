package com.unit.three;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import com.unit.three.p138b.C4053c;
import com.unit.three.p138b.C4059h;
import com.unit.three.p138b.C4059h.C4033a;
import com.unit.three.p138b.C4067j;
import com.unit.three.p139a.C4037a;
import com.unit.three.p140d.C4083a;
import com.unit.three.p141c.C4076c;
import com.unit.three.p141c.C4078f;

public class CoreService extends Service {

    class C40321 implements Runnable {
        private /* synthetic */ CoreService f9321a;

        C40321(CoreService coreService) {
            this.f9321a = coreService;
        }

        public void run() {
            try {
                C4053c.m12503a().m12516c(this.f9321a);
                String h = C4078f.m12572h();
                int i = C4078f.m12573i();
                if (CoreService.m12445a(this.f9321a, h, i)) {
                    long a = CoreService.m12444a(this.f9321a);
                    C4083a.m12599a(this.f9321a);
                    Thread.sleep(a + C4083a.m12597a().m12583g());
                    CoreService.m12446b(this.f9321a, h, i);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    class C40342 implements C4033a {
        private /* synthetic */ CoreService f9322a;

        C40342(CoreService coreService) {
            this.f9322a = coreService;
        }

        public void reconnected() {
            if (C4083a.m12599a(this.f9322a).m12602a(true)) {
                C4037a.m12449a();
                C4037a.m12456b();
            }
        }
    }

    static /* synthetic */ long m12444a(CoreService coreService) {
        long k = C4078f.m12575k();
        if (k == -1) {
            return 0;
        }
        C4083a.m12599a(C4053c.m12503a().m12515b());
        long e = C4083a.m12597a().m12581e();
        k = System.currentTimeMillis() - k;
        return e - k > 0 ? e - k : 0;
    }

    static /* synthetic */ boolean m12445a(CoreService coreService, String str, int i) {
        return (TextUtils.isEmpty(str) || i == -1) ? false : true;
    }

    static /* synthetic */ void m12446b(CoreService coreService, String str, int i) {
        C4067j.m12536a().m12545a(str, i);
        C4059h.m12523a().m12525a(new C40342(coreService));
        new C4076c(str).m12555b();
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        new Thread(new C40321(this)).start();
    }

    public void onDestroy() {
        super.onDestroy();
    }
}
