package com.inmobi.ads;

import com.inmobi.ads.C3046c.C3043g;
import com.inmobi.commons.core.p115d.C3132b;
import com.inmobi.commons.core.p115d.C3135c;
import com.mopub.mobileads.dfp.adapters.MoPubAdapter;
import java.util.ArrayList;
import java.util.List;

/* compiled from: VastResponse */
public class bo implements bp {
    private List<bn> f7296a;
    private String f7297b;
    private String f7298c;
    private String f7299d;
    private List<ah> f7300e;
    private List<bl> f7301f;
    private bl f7302g;
    private C3043g f7303h;
    private VastErrorCode f7304i;
    private bn f7305j;

    public bo(C3043g c3043g) {
        this.f7305j = null;
        this.f7296a = new ArrayList();
        this.f7300e = new ArrayList();
        this.f7301f = new ArrayList();
        this.f7303h = c3043g;
        this.f7304i = VastErrorCode.NO_ERROR;
    }

    public bo(List<ah> list, C3043g c3043g) {
        this(c3043g);
        if (list.size() != 0) {
            this.f7300e = new ArrayList(list);
        }
    }

    public bo(String str, String str2, String str3, List<ah> list, List<bl> list2, C3043g c3043g) {
        this(list, c3043g);
        if (list2.size() != 0) {
            this.f7301f = new ArrayList(list2);
        }
        this.f7297b = str;
        this.f7296a.add(new bn(str, 1000));
        this.f7298c = str2;
        this.f7299d = str3;
    }

    public String mo6216a() {
        return this.f7299d;
    }

    public String mo6219b() {
        bn bnVar = null;
        if (this.f7297b != null) {
            return this.f7297b;
        }
        double b = (double) (2 * (this.f7303h.m9684b() / 1048576));
        double c = (double) (this.f7303h.m9685c() / 1048576);
        bn bnVar2 = null;
        for (bn bnVar3 : this.f7296a) {
            int parseInt;
            bn bnVar32;
            String[] split = this.f7298c.split(":");
            try {
                parseInt = Integer.parseInt(split[2]) + (Integer.parseInt(split[1]) * 60);
            } catch (Throwable e) {
                Throwable th = e;
                parseInt = 0;
                C3135c.m10255a().m10279a(new C3132b(th));
            }
            double b2 = ((MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE * ((double) bnVar32.m9556b())) * ((double) parseInt)) / 8192.0d;
            bnVar32.m9555a(b2);
            if (b2 <= 0.0d || b2 > b) {
                if (b2 > b && b2 <= c) {
                    if (bnVar == null) {
                        bnVar = bnVar2;
                    } else if (b2 < bnVar.m9557c()) {
                        bnVar = bnVar2;
                    }
                }
                bnVar32 = bnVar;
                bnVar = bnVar2;
            } else if (bnVar2 == null) {
                r14 = bnVar;
                bnVar = bnVar32;
                bnVar32 = r14;
            } else {
                if (b2 > bnVar2.m9557c()) {
                    r14 = bnVar;
                    bnVar = bnVar32;
                    bnVar32 = r14;
                }
                bnVar32 = bnVar;
                bnVar = bnVar2;
            }
            bnVar2 = bnVar;
            bnVar = bnVar32;
        }
        if (bnVar2 != null) {
            this.f7305j = bnVar2;
            this.f7297b = bnVar2.m9554a();
        } else if (bnVar != null) {
            this.f7305j = bnVar;
            this.f7297b = bnVar.m9554a();
        }
        return this.f7297b;
    }

    public void mo6218a(String str) {
        if (this.f7297b != null) {
            this.f7297b = str;
        }
    }

    public VastErrorCode m9575c() {
        return this.f7304i;
    }

    public List<bn> mo6220d() {
        return this.f7296a;
    }

    public List<ah> mo6221e() {
        return this.f7300e;
    }

    public List<bl> mo6222f() {
        return this.f7301f;
    }

    public void mo6217a(bl blVar) {
        this.f7302g = blVar;
    }

    public bl mo6223g() {
        return this.f7302g;
    }

    void m9570a(bn bnVar) {
        this.f7296a.add(bnVar);
    }

    void m9568a(ah ahVar) {
        this.f7300e.add(ahVar);
    }

    void m9573b(bl blVar) {
        this.f7301f.add(blVar);
    }

    void m9574b(String str) {
        this.f7299d = str;
    }

    void m9567a(VastErrorCode vastErrorCode) {
        this.f7304i = vastErrorCode;
    }

    public String m9581h() {
        return this.f7298c;
    }

    public void m9576c(String str) {
        this.f7298c = str;
    }
}
