package com.fyber.cache.internal;

import java.io.Serializable;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: VideoEntry */
public final class C2561h implements Serializable {
    private final int f6422a;
    private final String f6423b;

    public C2561h(int i, String str) {
        this.f6422a = i;
        this.f6423b = str;
    }

    public C2561h(JSONObject jSONObject) throws JSONException {
        this.f6422a = jSONObject.getInt("ad_id");
        this.f6423b = jSONObject.getString("url");
    }

    public final int m8185a() {
        return this.f6422a;
    }

    public final String m8186b() {
        return this.f6423b;
    }

    public final int hashCode() {
        return this.f6422a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2561h)) {
            return false;
        }
        if (this.f6422a != ((C2561h) obj).f6422a) {
            return false;
        }
        return true;
    }

    public final String toString() {
        return String.format(Locale.ENGLISH, "{\"ad_id\":%d, \"url\":\"%s\"}", new Object[]{Integer.valueOf(this.f6422a), this.f6423b});
    }
}
