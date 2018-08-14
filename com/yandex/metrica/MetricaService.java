package com.yandex.metrica;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.yandex.metrica.IMetricaService.Stub;
import com.yandex.metrica.impl.ae;
import com.yandex.metrica.impl.af;
import com.yandex.metrica.impl.ag;
import com.yandex.metrica.impl.utils.C4529j;

public class MetricaService extends Service {
    private C4271b f11411a = new C42721(this);
    private ae f11412b;
    private final Stub f11413c = new C42732(this);

    public interface C4271b {
        void mo6965a(int i);
    }

    class C42721 implements C4271b {
        final /* synthetic */ MetricaService f11409a;

        C42721(MetricaService metricaService) {
            this.f11409a = metricaService;
        }

        public void mo6965a(int i) {
            this.f11409a.stopSelfResult(i);
        }
    }

    class C42732 extends Stub {
        final /* synthetic */ MetricaService f11410a;

        C42732(MetricaService this$0) {
            this.f11410a = this$0;
        }

        public void reportEvent(String event, int type, String value, Bundle data) throws RemoteException {
            this.f11410a.f11412b.mo6983a(C42732.getCallingUid(), event, type, value, data);
        }

        public void reportData(Bundle data) throws RemoteException {
            this.f11410a.f11412b.mo6982a(C42732.getCallingUid(), data);
        }
    }

    static class C4274a extends Binder {
        C4274a() {
        }
    }

    public void onCreate() {
        super.onCreate();
        C4529j.m16279a(getApplicationContext());
        this.f11412b = new af(new ag(getApplicationContext(), this.f11411a));
        this.f11412b.mo6981a();
    }

    public void onStart(Intent intent, int startId) {
        this.f11412b.mo6985a(intent, startId);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        this.f11412b.mo6986a(intent, flags, startId);
        return 2;
    }

    public IBinder onBind(Intent intent) {
        if ("com.yandex.metrica.ACTION_BIND_TO_LOCAL_SERVER".equals(intent.getAction())) {
            return new C4274a();
        }
        this.f11412b.mo6984a(intent);
        return this.f11413c;
    }

    public void onRebind(Intent intent) {
        super.onRebind(intent);
        this.f11412b.mo6988b(intent);
    }

    public void onDestroy() {
        super.onDestroy();
        this.f11412b.mo6987b();
    }

    public boolean onUnbind(Intent intent) {
        if ("com.yandex.metrica.ACTION_BIND_TO_LOCAL_SERVER".equals(intent.getAction())) {
            return false;
        }
        boolean z;
        if (intent == null || intent.getData() == null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return false;
        }
        this.f11412b.mo6989c(intent);
        return true;
    }
}
