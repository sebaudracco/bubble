package com.facebook.ads.internal.p058o;

import android.content.Context;
import com.facebook.ads.internal.p052j.C1999b;
import com.facebook.ads.internal.p056q.p057a.C2120k;
import com.facebook.ads.internal.p056q.p057a.C2129r;
import com.facebook.ads.internal.p056q.p057a.C2134x;
import com.facebook.ads.internal.p061c.C1951b;
import com.facebook.ads.internal.p066i.C1995c;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.protocol.C2100c;
import com.facebook.ads.internal.protocol.C2101d;
import com.facebook.ads.internal.protocol.C2105h;
import com.facebook.ads.internal.protocol.f;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class C2033b {
    protected String f4831a;
    public Context f4832b;
    public f f4833c;
    private C2100c f4834d;
    private final AdPlacementType f4835e = this.f4834d.m6753a();
    private final String f4836f;
    private final String f4837g;
    private C2101d f4838h;
    private boolean f4839i;
    private boolean f4840j;
    private int f4841k;
    private C2120k f4842l;
    private final Map<String, String> f4843m;
    private final C2105h f4844n;
    private String f4845o;

    public C2033b(Context context, C1995c c1995c, String str, C2120k c2120k, f fVar, C2101d c2101d, String str2, String str3, int i, boolean z, boolean z2, C2105h c2105h, String str4) {
        this.f4831a = str;
        this.f4842l = c2120k;
        this.f4833c = fVar;
        this.f4834d = C2100c.m6752a(fVar);
        this.f4838h = c2101d;
        this.f4836f = str2;
        this.f4837g = str3;
        this.f4841k = i;
        this.f4839i = z;
        this.f4840j = z2;
        this.f4843m = c1995c.m6315b();
        this.f4844n = c2105h;
        this.f4832b = context;
        this.f4845o = str4;
    }

    private void m6509a(Map<String, String> map, String str, String str2) {
        map.put(str, str2);
    }

    public String m6510a() {
        return this.f4831a;
    }

    public C2100c m6511b() {
        return this.f4834d;
    }

    public C2120k m6512c() {
        return this.f4842l;
    }

    public int m6513d() {
        return this.f4841k;
    }

    public C2105h m6514e() {
        return this.f4844n;
    }

    public Map<String, String> m6515f() {
        Map<String, String> hashMap = new HashMap(this.f4843m);
        m6509a(hashMap, "IDFA", C1951b.f4526b);
        m6509a(hashMap, "IDFA_FLAG", C1951b.f4527c ? SchemaSymbols.ATTVAL_FALSE_0 : SchemaSymbols.ATTVAL_TRUE_1);
        m6509a(hashMap, "COPPA", String.valueOf(this.f4840j));
        m6509a(hashMap, "PLACEMENT_ID", this.f4831a);
        if (this.f4835e != AdPlacementType.UNKNOWN) {
            m6509a(hashMap, "PLACEMENT_TYPE", this.f4835e.toString().toLowerCase());
        }
        if (this.f4842l != null) {
            m6509a(hashMap, "WIDTH", String.valueOf(this.f4842l.m6802b()));
            m6509a(hashMap, "HEIGHT", String.valueOf(this.f4842l.m6801a()));
        }
        m6509a(hashMap, "ADAPTERS", this.f4837g);
        if (this.f4833c != null) {
            m6509a(hashMap, "TEMPLATE_ID", String.valueOf(this.f4833c.a()));
        }
        if (this.f4838h != null) {
            m6509a(hashMap, "REQUEST_TYPE", String.valueOf(this.f4838h.m6754a()));
        }
        if (this.f4839i) {
            m6509a(hashMap, "TEST_MODE", SchemaSymbols.ATTVAL_TRUE_1);
        }
        if (this.f4836f != null) {
            m6509a(hashMap, "DEMO_AD_ID", this.f4836f);
        }
        if (this.f4841k != 0) {
            m6509a(hashMap, "NUM_ADS_REQUESTED", String.valueOf(this.f4841k));
        }
        m6509a(hashMap, "CLIENT_EVENTS", C1999b.m6320a());
        m6509a(hashMap, "KG_RESTRICTED", String.valueOf(C2134x.m6837a(this.f4832b)));
        m6509a(hashMap, "REQUEST_TIME", C2129r.m6819a(System.currentTimeMillis()));
        if (this.f4844n.m6761c()) {
            m6509a(hashMap, "BID_ID", this.f4844n.m6762d());
        }
        if (this.f4845o != null) {
            m6509a(hashMap, "STACK_TRACE", this.f4845o);
        }
        m6509a(hashMap, "CLIENT_REQUEST_ID", UUID.randomUUID().toString());
        return hashMap;
    }
}
