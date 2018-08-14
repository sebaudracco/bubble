package com.inmobi.commons.core.configs;

import android.os.SystemClock;
import com.inmobi.commons.core.configs.ConfigNetworkResponse.ConfigResponse;
import com.inmobi.commons.core.network.C3144d;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: ConfigNetworkClient */
class C3123d implements Runnable {
    private static final String f7658b = C3123d.class.getName();
    protected long f7659a = 0;
    private C3124e f7660c;
    private int f7661d;
    private C3118a f7662e;

    /* compiled from: ConfigNetworkClient */
    public interface C3118a {
        void mo6260a(ConfigResponse configResponse);

        void mo6261b();
    }

    public C3123d(C3118a c3118a, C3124e c3124e) {
        this.f7662e = c3118a;
        this.f7660c = c3124e;
        this.f7661d = 0;
    }

    private void m10200a() throws InterruptedException {
        while (this.f7661d <= this.f7660c.m10205c()) {
            C3144d c3144d = new C3144d(this.f7660c);
            this.f7659a = SystemClock.elapsedRealtime();
            Map a = new ConfigNetworkResponse(this.f7660c.m10204b(), c3144d.m10357a(), SystemClock.elapsedRealtime() - this.f7659a).m10165a();
            for (Entry entry : a.entrySet()) {
                ConfigResponse configResponse = (ConfigResponse) entry.getValue();
                String str = (String) entry.getKey();
                if (!configResponse.m10160d()) {
                    this.f7662e.mo6260a(configResponse);
                    this.f7660c.mo6239a(str);
                }
            }
            if (this.f7660c.m10204b().isEmpty()) {
                break;
            }
            this.f7661d++;
            if (this.f7661d > this.f7660c.m10205c()) {
                for (Entry entry2 : this.f7660c.m10204b().entrySet()) {
                    str = (String) entry2.getKey();
                    if (a.containsKey(str)) {
                        this.f7662e.mo6260a((ConfigResponse) a.get(str));
                    }
                }
            } else {
                Thread.sleep((long) (this.f7660c.m10206d() * 1000));
            }
        }
        this.f7662e.mo6261b();
    }

    public void run() {
        try {
            m10200a();
        } catch (InterruptedException e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7658b, "Fetching config interrupted by the component de-initialization.");
        }
    }
}
