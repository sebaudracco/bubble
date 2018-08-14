package com.fyber.cache.internal;

import android.text.TextUtils;
import java.io.File;
import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: CacheEntry */
public final class C2555c implements Serializable {
    private final File f6404a;
    private final String f6405b;
    private int f6406c;
    private Set<C2561h> f6407d = new HashSet();
    private long f6408e;
    private int f6409f = 0;

    public C2555c(File file, String str, int i) {
        this.f6404a = file;
        this.f6405b = str;
        this.f6406c = i;
        m8151h();
    }

    public C2555c(JSONObject jSONObject) throws JSONException {
        int i = 0;
        this.f6404a = new File(jSONObject.getString("local_file"));
        this.f6405b = jSONObject.getString("remote_url");
        this.f6406c = jSONObject.getInt("download_state");
        this.f6409f = jSONObject.getInt("retries");
        this.f6408e = jSONObject.getLong("created_at");
        this.f6407d = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("video_entries");
        int length = jSONArray.length();
        while (i < length) {
            this.f6407d.add(new C2561h(jSONArray.getJSONObject(i)));
            i++;
        }
    }

    public final File m8152a() {
        return this.f6404a;
    }

    public final String m8155b() {
        return this.f6405b;
    }

    public final int m8156c() {
        return this.f6406c;
    }

    public final void m8153a(int i) {
        this.f6406c = i;
        if (i == 1) {
            this.f6409f++;
        }
    }

    public final int m8157d() {
        return this.f6409f;
    }

    public final void m8158e() {
        this.f6409f = 0;
    }

    public final Set<C2561h> m8159f() {
        return this.f6407d;
    }

    public final boolean m8154a(C2561h c2561h) {
        boolean add = this.f6407d.add(c2561h);
        m8151h();
        return add;
    }

    private void m8151h() {
        this.f6408e = Calendar.getInstance().getTimeInMillis();
    }

    public final long m8160g() {
        return this.f6408e;
    }

    public final String toString() {
        String join = TextUtils.join(",", this.f6407d);
        return String.format(Locale.ENGLISH, "{\"local_file\":\"%s\", \"remote_url\":\"%s\",\"download_state\":%d,\"created_at\":%d,\"retries\":%d,\"video_entries\":[%s]}", new Object[]{this.f6404a.getAbsolutePath(), this.f6405b, Integer.valueOf(this.f6406c), Long.valueOf(this.f6408e), Integer.valueOf(this.f6409f), join});
    }

    public final int hashCode() {
        return this.f6405b.hashCode();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2555c)) {
            return false;
        }
        return this.f6405b.equals(((C2555c) obj).f6405b);
    }
}
