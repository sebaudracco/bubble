package com.inmobi.ads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.inmobi.commons.core.network.C3143c;
import com.inmobi.commons.core.p115d.C3132b;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import cz.msebera.android.httpclient.client.cache.HeaderConstants;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

/* compiled from: AdAsset */
public class C3029b {
    private static final String f7217i = C3029b.class.getSimpleName();
    long f7218a = 0;
    int f7219b;
    int f7220c;
    String f7221d;
    long f7222e;
    long f7223f;
    long f7224g;
    long f7225h;
    private String f7226j;

    /* compiled from: AdAsset */
    static final class C3027a {
        int f7209a = (new Random().nextInt() & Integer.MAX_VALUE);
        int f7210b;
        String f7211c;
        String f7212d;
        long f7213e = System.currentTimeMillis();
        long f7214f = System.currentTimeMillis();
        long f7215g;
        long f7216h;

        public C3027a m9461a(String str, int i, long j) {
            this.f7211c = str;
            this.f7210b = i;
            this.f7215g = System.currentTimeMillis() + j;
            return this;
        }

        public C3027a m9462a(String str, String str2, C3143c c3143c, int i, long j) {
            String str3;
            Object obj;
            long a;
            long j2;
            Map d = c3143c.m10354d();
            long currentTimeMillis = System.currentTimeMillis();
            long j3 = 0;
            long j4 = 0;
            long j5 = 0;
            Object obj2 = null;
            ArrayList arrayList = new ArrayList();
            List list = (List) d.get("Date");
            if (list != null && list.size() > 0) {
                str3 = (String) ((List) d.get("Date")).get(0);
                if (str3 != null) {
                    j3 = m9460a(str3);
                }
            }
            list = (List) d.get("Cache-Control");
            if (list != null && list.size() > 0) {
                str3 = (String) ((List) d.get("Cache-Control")).get(0);
                if (str3 != null) {
                    String[] split = str3.split(",");
                    obj = null;
                    long j6 = 0;
                    j5 = 0;
                    for (String trim : split) {
                        String trim2 = trim2.trim();
                        if (!(trim2.equals(HeaderConstants.CACHE_CONTROL_NO_CACHE) || trim2.equals(HeaderConstants.CACHE_CONTROL_NO_STORE))) {
                            if (trim2.startsWith("max-age=")) {
                                try {
                                    j5 = Long.parseLong(trim2.substring(8));
                                } catch (Exception e) {
                                    Logger.m10359a(InternalLogLevel.INTERNAL, C3029b.f7217i, e.getMessage());
                                }
                            } else if (trim2.startsWith("stale-while-revalidate=")) {
                                try {
                                    j6 = Long.parseLong(trim2.substring(23));
                                } catch (Exception e2) {
                                    Logger.m10359a(InternalLogLevel.INTERNAL, C3029b.f7217i, e2.getMessage());
                                }
                            } else if (trim2.equals(HeaderConstants.CACHE_CONTROL_MUST_REVALIDATE) || trim2.equals(HeaderConstants.CACHE_CONTROL_PROXY_REVALIDATE)) {
                                obj = 1;
                            }
                        }
                    }
                    j4 = j5;
                    j5 = j6;
                    obj2 = obj;
                    obj = 1;
                    list = (List) d.get("Expires");
                    if (list != null && list.size() > 0) {
                        str3 = (String) ((List) d.get("Expires")).get(0);
                        if (str3 != null) {
                            a = m9460a(str3);
                            list = (List) d.get("Last-Modified");
                            if (list != null && list.size() > 0) {
                                str3 = (String) ((List) d.get("Last-Modified")).get(0);
                                if (str3 != null) {
                                    m9460a(str3);
                                }
                            }
                            list = (List) d.get("ETag");
                            if (list != null && list.size() > 0) {
                                str3 = (String) ((List) d.get("ETag")).get(0);
                            }
                            if (obj == null) {
                                j3 = currentTimeMillis + (1000 * j4);
                                j2 = obj2 == null ? j3 : (1000 * j5) + j3;
                            } else if (j3 > 0 || a < j3) {
                                j2 = 0;
                                j3 = 0;
                            } else {
                                j2 = (a - j3) + currentTimeMillis;
                                j3 = j2;
                            }
                            this.f7211c = str;
                            this.f7212d = str2;
                            this.f7210b = i;
                            this.f7215g = (1000 * j) + currentTimeMillis;
                            this.f7216h = j3;
                            this.f7215g = Math.min(this.f7215g, j2);
                            return this;
                        }
                    }
                    a = 0;
                    list = (List) d.get("Last-Modified");
                    str3 = (String) ((List) d.get("Last-Modified")).get(0);
                    if (str3 != null) {
                        m9460a(str3);
                    }
                    list = (List) d.get("ETag");
                    str3 = (String) ((List) d.get("ETag")).get(0);
                    if (obj == null) {
                        if (j3 > 0) {
                        }
                        j2 = 0;
                        j3 = 0;
                    } else {
                        j3 = currentTimeMillis + (1000 * j4);
                        if (obj2 == null) {
                        }
                    }
                    this.f7211c = str;
                    this.f7212d = str2;
                    this.f7210b = i;
                    this.f7215g = (1000 * j) + currentTimeMillis;
                    this.f7216h = j3;
                    this.f7215g = Math.min(this.f7215g, j2);
                    return this;
                }
            }
            obj = null;
            list = (List) d.get("Expires");
            str3 = (String) ((List) d.get("Expires")).get(0);
            if (str3 != null) {
                a = m9460a(str3);
                list = (List) d.get("Last-Modified");
                str3 = (String) ((List) d.get("Last-Modified")).get(0);
                if (str3 != null) {
                    m9460a(str3);
                }
                list = (List) d.get("ETag");
                str3 = (String) ((List) d.get("ETag")).get(0);
                if (obj == null) {
                    j3 = currentTimeMillis + (1000 * j4);
                    if (obj2 == null) {
                    }
                } else {
                    if (j3 > 0) {
                    }
                    j2 = 0;
                    j3 = 0;
                }
                this.f7211c = str;
                this.f7212d = str2;
                this.f7210b = i;
                this.f7215g = (1000 * j) + currentTimeMillis;
                this.f7216h = j3;
                this.f7215g = Math.min(this.f7215g, j2);
                return this;
            }
            a = 0;
            list = (List) d.get("Last-Modified");
            str3 = (String) ((List) d.get("Last-Modified")).get(0);
            if (str3 != null) {
                m9460a(str3);
            }
            list = (List) d.get("ETag");
            str3 = (String) ((List) d.get("ETag")).get(0);
            if (obj == null) {
                if (j3 > 0) {
                }
                j2 = 0;
                j3 = 0;
            } else {
                j3 = currentTimeMillis + (1000 * j4);
                if (obj2 == null) {
                }
            }
            this.f7211c = str;
            this.f7212d = str2;
            this.f7210b = i;
            this.f7215g = (1000 * j) + currentTimeMillis;
            this.f7216h = j3;
            this.f7215g = Math.min(this.f7215g, j2);
            return this;
        }

        private long m9460a(String str) {
            try {
                return new SimpleDateFormat("EEE,dd MMM yyyy HH:mm:ss z", Locale.ENGLISH).parse(str).getTime();
            } catch (Throwable e) {
                C3135c.m10255a().m10279a(new C3132b(e));
                return 0;
            }
        }

        @NonNull
        public C3029b m9463a() {
            return new C3029b(this.f7209a, this.f7211c, this.f7212d, this.f7210b, this.f7213e, this.f7214f, this.f7215g, this.f7216h);
        }
    }

    public C3029b(int i, @NonNull String str, @Nullable String str2, int i2, long j, long j2, long j3, long j4) {
        this.f7219b = i;
        this.f7226j = str;
        this.f7221d = str2;
        this.f7220c = i2;
        this.f7222e = j;
        this.f7223f = j2;
        this.f7224g = j3;
        this.f7225h = j4;
    }

    public String m9465a() {
        return this.f7221d;
    }

    public String m9467b() {
        return this.f7226j;
    }

    public boolean m9468c() {
        return System.currentTimeMillis() > this.f7224g;
    }

    public boolean m9469d() {
        if (m9465a() == null || m9465a().length() == 0 || !new File(this.f7221d).exists()) {
            return false;
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.f7226j.equals(((C3029b) obj).f7226j);
    }

    public int hashCode() {
        return this.f7226j.hashCode();
    }

    public long m9470e() {
        return this.f7218a;
    }

    public void m9466a(long j) {
        this.f7218a = j;
    }
}
