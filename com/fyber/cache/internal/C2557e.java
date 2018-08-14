package com.fyber.cache.internal;

import android.content.Context;
import android.content.SharedPreferences;
import com.fyber.cache.CacheManager;
import org.json.JSONArray;

/* compiled from: CacheStatistics */
public final class C2557e {
    public static final C2557e f6411a = new C2557e();
    private final SharedPreferences f6412b;
    private volatile int f6413c;

    protected C2557e() {
        this.f6412b = null;
    }

    public C2557e(Context context) {
        this.f6412b = context.getSharedPreferences("FyberCacheStatistics", 0);
        this.f6413c = this.f6412b.getInt("download.count", 0);
    }

    public final int m8164a() {
        return this.f6413c;
    }

    public final void m8165b() {
        this.f6413c++;
        m8163e();
    }

    public final void m8166c() {
        this.f6413c = 0;
        m8163e();
    }

    public static String m8162d() {
        JSONArray jSONArray = new JSONArray();
        C2559f c = CacheManager.m8105a().m8117c();
        if (c != null) {
            for (C2555c c2555c : c.m8175a().values()) {
                if (c2555c.m8156c() == 2 && c2555c.m8152a().exists()) {
                    for (C2561h a : c2555c.m8159f()) {
                        jSONArray.put(a.m8185a());
                    }
                }
            }
        }
        return jSONArray.toString();
    }

    private void m8163e() {
        if (this.f6412b != null) {
            this.f6412b.edit().putInt("download.count", this.f6413c).apply();
        }
    }
}
