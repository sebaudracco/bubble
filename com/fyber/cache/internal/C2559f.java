package com.fyber.cache.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.text.TextUtils;
import com.fyber.utils.FyberLogger;
import java.io.File;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TreeSet;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: CacheStore */
public final class C2559f {
    public static final C2559f f6414a = new C2559f();
    private final File f6415b;
    private final boolean f6416c;
    private final SharedPreferences f6417d;
    private Map<String, C2555c> f6418e;

    /* compiled from: CacheStore */
    private static class C2558a implements Comparator<C2555c> {
        private C2558a() {
        }

        public final /* synthetic */ int compare(Object obj, Object obj2) {
            long g = ((C2555c) obj).m8160g() - ((C2555c) obj2).m8160g();
            if (g < 0) {
                return -1;
            }
            if (g > 0) {
                return 1;
            }
            return 0;
        }
    }

    protected C2559f() {
        this.f6416c = false;
        this.f6415b = null;
        this.f6417d = null;
        this.f6418e = new HashMap();
    }

    public C2559f(Context context) {
        File file = new File(context.getCacheDir(), "FyberVideoCache");
        if (!file.exists()) {
            FyberLogger.m8448d("CacheStore", "The cache directory does not exist, creating...");
            file.mkdirs();
        }
        this.f6415b = file;
        this.f6417d = context.getSharedPreferences("FyberCacheStorage", 0);
        this.f6416c = m8168e();
    }

    private boolean m8168e() {
        try {
            this.f6418e = new HashMap();
            if (this.f6417d.getAll().isEmpty()) {
                m8171h();
            } else {
                try {
                    JSONArray jSONArray = new JSONObject(this.f6417d.getString("FyberCacheStorage", "{\"cache\":[]}")).getJSONArray("cache");
                    int length = jSONArray.length();
                    for (int i = 0; i < length; i++) {
                        C2555c c2555c = new C2555c(jSONArray.getJSONObject(i));
                        this.f6418e.put(Uri.parse(c2555c.m8155b()).getEncodedSchemeSpecificPart(), c2555c);
                    }
                } catch (JSONException e) {
                    m8171h();
                }
            }
            m8169f();
            if (this.f6415b.exists() && this.f6415b.isDirectory()) {
                return true;
            }
            return false;
        } catch (Exception e2) {
            this.f6418e = new HashMap();
            return false;
        }
    }

    private void m8169f() {
        int i = 0;
        for (C2555c c2555c : this.f6418e.values()) {
            int i2;
            if (c2555c.m8152a().exists()) {
                i2 = i;
            } else {
                FyberLogger.m8448d("CacheStore", "Local file for cache entry " + c2555c.m8155b() + " was removed.");
                c2555c.m8153a(0);
                i2 = 1;
            }
            i = i2;
        }
        if (i != 0) {
            FyberLogger.m8448d("CacheStore", "Saving Cache file.");
            m8170g();
        }
    }

    public final C2555c m8174a(String str) {
        return (C2555c) this.f6418e.get(Uri.parse(str).getEncodedSchemeSpecificPart());
    }

    public final Map<String, C2555c> m8175a() {
        return this.f6418e;
    }

    public final C2555c m8173a(C2561h c2561h) {
        C2555c c2555c;
        String b = c2561h.m8186b();
        String encodedSchemeSpecificPart = Uri.parse(b).getEncodedSchemeSpecificPart();
        if (!this.f6416c) {
            c2555c = new C2555c(m8172i(), b, 4);
        } else if (this.f6418e.containsKey(encodedSchemeSpecificPart)) {
            c2555c = (C2555c) this.f6418e.get(encodedSchemeSpecificPart);
        } else {
            c2555c = new C2555c(m8172i(), b, 0);
            this.f6418e.put(encodedSchemeSpecificPart, c2555c);
        }
        c2555c.m8154a(c2561h);
        m8170g();
        return c2555c;
    }

    public final void m8177b() {
        m8170g();
    }

    public static String m8167a(Collection<C2555c> collection) {
        return String.format(Locale.ENGLISH, "{\"cache\":[%s]}", new Object[]{TextUtils.join(",", collection)});
    }

    private void m8171h() {
        FyberLogger.m8448d("CacheStore", "Cache storage file recovering issue, purging the local files...");
        File[] listFiles = this.f6415b.listFiles();
        if (listFiles != null) {
            for (File delete : listFiles) {
                delete.delete();
            }
        }
    }

    public final int m8179c() {
        int size = this.f6418e.size();
        int i = size;
        for (C2555c c2555c : this.f6418e.values()) {
            if (c2555c.m8152a().exists() && c2555c.m8156c() == 2) {
                size = i;
            } else {
                size = i - 1;
            }
            i = size;
        }
        return i;
    }

    private File m8172i() {
        File file = new File(this.f6415b, UUID.randomUUID().toString());
        if (file.exists()) {
            FyberLogger.m8451i("CacheStore", "Video already exists in cache: " + file.getAbsolutePath());
            file = m8172i();
        }
        FyberLogger.m8451i("CacheStore", "Save in file: " + file.getAbsolutePath());
        return file;
    }

    public final void m8176a(int i) {
        if (i >= 0 && i < this.f6418e.size()) {
            FyberLogger.m8451i("CacheStore", "Trimming cache to " + i + " slots");
            m8178b(this.f6418e.size() - i);
        }
    }

    public final void m8178b(int i) {
        if (i > 0 && !this.f6418e.isEmpty()) {
            FyberLogger.m8451i("CacheStore", "Freeing up " + i + " cache slots");
            TreeSet treeSet = new TreeSet(new C2558a());
            treeSet.addAll(this.f6418e.values());
            while (true) {
                C2555c c2555c = (C2555c) treeSet.pollFirst();
                if (c2555c == null || i <= 0) {
                    FyberLogger.m8451i("CacheStore", "Current cache size: " + this.f6418e.size() + " slots");
                } else {
                    c2555c = (C2555c) this.f6418e.remove(Uri.parse(c2555c.m8155b()).getEncodedSchemeSpecificPart());
                    if (c2555c != null) {
                        c2555c.m8152a().delete();
                        m8170g();
                    }
                    i--;
                }
            }
            FyberLogger.m8451i("CacheStore", "Current cache size: " + this.f6418e.size() + " slots");
        }
    }

    public final boolean m8180d() {
        return this.f6416c;
    }

    private void m8170g() {
        if (this.f6416c) {
            this.f6417d.edit().putString("FyberCacheStorage", C2559f.m8167a(this.f6418e.values())).apply();
        }
    }
}
