package com.inmobi.ads;

import android.content.ContentValues;
import com.inmobi.ads.C2968a.C2965a;
import com.inmobi.ads.InMobiAdRequest.MonetizationContext;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.p116c.C3115b;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.mopub.mobileads.BaseVideoPlayerActivity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

/* compiled from: AdDao */
public class C3047d {
    public static final String[] f7393a = new String[]{"id", "ad_content", BaseVideoPlayerActivity.VIDEO_URL, "video_track_duration", "click_url", "video_trackers", "companion_ads", "asset_urls", "ad_type", "ad_size", "placement_id", "insertion_ts", "imp_id", "m10_context", "client_request_id"};
    private static final String f7394b = C3047d.class.getSimpleName();
    private static C3047d f7395c;
    private static Object f7396d = new Object();

    public static C3047d m9733a() {
        C3047d c3047d = f7395c;
        if (c3047d == null) {
            synchronized (f7396d) {
                c3047d = f7395c;
                if (c3047d == null) {
                    f7395c = new C3047d();
                    c3047d = f7395c;
                }
            }
        }
        return c3047d;
    }

    private C3047d() {
        C3115b a = C3115b.m10131a();
        a.m10136a("ad", "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, placement_id INTEGER NOT NULL, ad_content TEXT NOT NULL, ad_type TEXT NOT NULL, ad_size TEXT, asset_urls TEXT, video_url TEXT, video_track_duration TEXT, click_url TEXT, video_trackers TEXT, companion_ads TEXT, insertion_ts INTEGER NOT NULL, imp_id TEXT NOT NULL, m10_context TEXT NOT NULL, client_request_id TEXT NOT NULL)");
        a.m10140b();
    }

    public int m9735a(String str, long j) {
        C3115b a = C3115b.m10131a();
        long nanoTime = System.nanoTime() - (((j * 1000) * 1000) * 1000);
        int a2 = a.m10133a("ad", "ad_type=? AND insertion_ts<?", new String[]{str, String.valueOf(nanoTime)});
        Logger.m10359a(InternalLogLevel.INTERNAL, f7394b, "Deleted " + a2 + " expired ads from cache of type:" + str);
        a.m10140b();
        return a2;
    }

    public int m9734a(long j, String str, MonetizationContext monetizationContext) {
        int b;
        C3115b a = C3115b.m10131a();
        if (str == null || str.trim().length() == 0) {
            b = a.m10139b("ad", "placement_id=? AND m10_context=?", new String[]{String.valueOf(j), monetizationContext.m8787a()});
        } else {
            b = a.m10139b("ad", "placement_id=? AND ad_size=? AND m10_context=?", new String[]{String.valueOf(j), str, monetizationContext.m8787a()});
        }
        a.m10140b();
        return b;
    }

    public synchronized C2968a m9739b(long j, String str, MonetizationContext monetizationContext) {
        C2968a c2968a;
        List a;
        C3115b a2 = C3115b.m10131a();
        if (str == null || str.trim().length() == 0) {
            a = a2.m10134a("ad", f7393a, "placement_id=? AND m10_context=?", new String[]{String.valueOf(j), monetizationContext.m8787a()}, null, null, "insertion_ts", SchemaSymbols.ATTVAL_TRUE_1);
        } else {
            a = a2.m10134a("ad", f7393a, "placement_id=? AND ad_size=? AND m10_context=?", new String[]{String.valueOf(j), str, monetizationContext.m8787a()}, null, null, "insertion_ts", SchemaSymbols.ATTVAL_TRUE_1);
        }
        if (a == null || a.size() == 0) {
            c2968a = null;
        } else {
            a2.m10133a("ad", "id=?", new String[]{String.valueOf(((ContentValues) a.get(0)).getAsInteger("id").intValue())});
            c2968a = C2965a.m9110a(r1);
        }
        return c2968a;
    }

    public synchronized List<C2968a> m9741c(long j, String str, MonetizationContext monetizationContext) {
        List<C2968a> arrayList;
        arrayList = new ArrayList();
        C3115b a = C3115b.m10131a();
        List a2;
        if (str == null || str.trim().length() == 0) {
            a2 = a.m10134a("ad", f7393a, "placement_id=? AND m10_context=?", new String[]{String.valueOf(j), monetizationContext.m8787a()}, null, null, "insertion_ts", null);
        } else {
            a2 = a.m10134a("ad", f7393a, "placement_id=? AND ad_size=? AND m10_context=?", new String[]{String.valueOf(j), str, monetizationContext.m8787a()}, null, null, "insertion_ts", null);
        }
        for (ContentValues a3 : r0) {
            arrayList.add(C2965a.m9110a(a3));
        }
        return arrayList;
    }

    public synchronized List<C2968a> m9736a(String str, String str2) {
        List<C2968a> arrayList;
        arrayList = new ArrayList();
        C3115b a = C3115b.m10131a();
        List a2;
        if (str2 == null || str2.trim().length() == 0) {
            a2 = a.m10134a("ad", f7393a, "video_url=?", new String[]{str}, null, null, "insertion_ts", null);
        } else {
            a2 = a.m10134a("ad", f7393a, "video_url=? AND ad_size=?", new String[]{str, str2}, null, null, "insertion_ts", null);
        }
        for (ContentValues a3 : r0) {
            arrayList.add(C2965a.m9110a(a3));
        }
        return arrayList;
    }

    public synchronized List<C2968a> m9740b(String str, String str2) {
        List<C2968a> arrayList;
        arrayList = new ArrayList();
        C3115b a = C3115b.m10131a();
        List a2;
        if (str2 == null || str2.trim().length() == 0) {
            a2 = a.m10134a("ad", f7393a, "video_url=?", new String[]{str}, null, null, "insertion_ts", null);
        } else {
            a2 = a.m10134a("ad", f7393a, "video_url=? AND ad_size=?", new String[]{str, str2}, null, null, "insertion_ts", null);
        }
        for (ContentValues asInteger : r1) {
            a.m10133a("ad", "id=?", new String[]{String.valueOf(asInteger.getAsInteger("id").intValue())});
            arrayList.add(C2965a.m9110a(asInteger));
        }
        return arrayList;
    }

    public void m9737a(C2968a c2968a) {
        C3115b a = C3115b.m10131a();
        a.m10133a("ad", "placement_id = ?", new String[]{String.valueOf(c2968a.m9124d())});
        a.m10140b();
    }

    public synchronized void m9738a(List<C2968a> list, int i, String str) {
        if (i != 0) {
            if (list.size() != 0) {
                int i2;
                for (C2968a a : list) {
                    a.m9121a(System.nanoTime());
                }
                C3115b a2 = C3115b.m10131a();
                for (i2 = 0; i2 < list.size(); i2++) {
                    a2.m10137a("ad", ((C2968a) list.get(i2)).mo6224a());
                }
                int b = a2.m10139b("ad", "ad_type=?", new String[]{str}) - i;
                if (b > 0) {
                    Map hashMap = new HashMap();
                    hashMap.put("type", str);
                    hashMap.put("count", Integer.valueOf(b));
                    C3135c.m10255a().m10280a("ads", "DbSpaceOverflow", hashMap);
                    List a3 = a2.m10134a("ad", new String[]{"id"}, "ad_type=?", new String[]{str}, null, null, "insertion_ts ASC", String.valueOf(b));
                    String[] strArr = new String[a3.size()];
                    for (i2 = 0; i2 < a3.size(); i2++) {
                        strArr[i2] = String.valueOf(((ContentValues) a3.get(i2)).getAsInteger("id"));
                    }
                    a2.m10133a("ad", "id IN " + Arrays.toString(strArr).replace("[", "(").replace("]", ")"), null);
                }
                a2.m10140b();
            }
        }
    }
}
