package com.fyber.ads.interstitials.p088a;

import android.support.annotation.NonNull;
import com.fyber.ads.internal.C2423a;
import com.fyber.ads.interstitials.p091b.C2440a;
import com.fyber.cache.CacheManager;
import com.fyber.cache.internal.C2557e;
import com.fyber.p089c.p090d.C2437b;
import com.fyber.p089c.p090d.C2543d.C2432d;
import com.fyber.p094b.p099c.C2495a;
import com.fyber.p094b.p099c.C2495a.C2494a;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: InterstitialVideoListener */
public final class C2438d implements C2437b, C2432d {
    private final C2440a f6095a;
    private int f6096b;
    private Map<String, String> f6097c = new HashMap(3);
    private AtomicInteger f6098d = new AtomicInteger(0);
    private final byte f6099e = (byte) 25;
    private final byte f6100f = (byte) 50;
    private final byte f6101g = (byte) 75;
    private boolean f6102h;
    private boolean f6103i = false;

    public C2438d(C2440a c2440a, boolean z) {
        this.f6095a = c2440a;
        this.f6102h = z;
    }

    public final void mo3881a() {
        m7731a(C2423a.ValidationTimeout, "video", null);
    }

    public final void mo3884a(String str, boolean z, String str2) {
        C2557e d = CacheManager.m8105a().m8119d();
        int a = d.m8164a();
        this.f6097c.put("is_cached", Boolean.toString(z));
        this.f6097c.put("cache_config_id", str2);
        this.f6097c.put("downloaded_videos_count", Integer.toString(a));
        d.m8166c();
    }

    public final void mo3882a(int i) {
        this.f6096b = i;
        m7731a(C2423a.Progress, "start", this.f6097c);
    }

    public final void mo3886b(int i) {
        int i2 = (int) ((((float) i) / ((float) this.f6096b)) * 100.0f);
        if (i2 >= 25 && this.f6098d.compareAndSet(0, 25)) {
            m7731a(C2423a.Progress, "q25", this.f6097c);
        }
        if (i2 >= 50 && this.f6098d.compareAndSet(25, 50)) {
            m7731a(C2423a.Progress, "q50", this.f6097c);
        }
        if (i2 >= 75 && this.f6098d.compareAndSet(50, 75)) {
            m7731a(C2423a.Progress, "q75", this.f6097c);
        }
    }

    public final void mo3885b() {
        if (this.f6098d.get() != 75) {
            mo3886b(this.f6096b);
        }
        m7731a(C2423a.Progress, "finish", this.f6097c);
    }

    public final void mo3888c() {
        this.f6103i = true;
        m7731a(C2423a.Interaction, "click_through", null);
        if (!this.f6102h) {
            this.f6095a.mo3891l();
        }
    }

    public final void mo3883a(String str) {
        if (this.f6102h) {
            m7731a(C2423a.Interaction, "close_video", null);
        }
    }

    public final void mo3887b(String str) {
        m7731a(C2423a.ShowError, str, null);
    }

    public final void mo3870a(int i, String str) {
        if (this.f6102h) {
            m7731a(C2423a.Progress, "end_card", this.f6097c);
        }
    }

    private void m7731a(@NonNull C2423a c2423a, String str, Map<String, String> map) {
        ((C2495a) ((C2494a) ((C2494a) new C2494a(c2423a).m7860a(str)).m7861a((Map) map)).m7890a(this.f6095a.m7627j())).mo3907b();
    }

    public final boolean m7741d() {
        return this.f6103i;
    }
}
