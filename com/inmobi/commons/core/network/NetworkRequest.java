package com.inmobi.commons.core.network;

import android.support.annotation.Nullable;
import android.util.Base64;
import com.inmobi.commons.core.configs.C3045a;
import com.inmobi.commons.core.configs.C3121b;
import com.inmobi.commons.core.configs.C3125f;
import com.inmobi.commons.core.utilities.C3155d;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.C3159a;
import com.inmobi.commons.core.utilities.info.C3160b;
import com.inmobi.commons.core.utilities.info.C3162d;
import com.inmobi.commons.core.utilities.p117a.C3150b;
import com.inmobi.commons.core.utilities.uid.C3169d;
import com.inmobi.commons.p112a.C3105a;
import java.util.HashMap;
import java.util.Map;

public class NetworkRequest {
    private static final String f7401d = NetworkRequest.class.getSimpleName();
    protected Map<String, String> f7402a;
    protected Map<String, String> f7403b;
    protected Map<String, String> f7404c;
    private RequestType f7405e;
    private String f7406f;
    private C3169d f7407g;
    private int f7408h;
    private int f7409i;
    private boolean f7410j;
    private boolean f7411k;
    private byte[] f7412l;
    private byte[] f7413m;
    private boolean f7414n;
    private long f7415o;
    private boolean f7416p;

    public enum RequestType {
        GET,
        POST
    }

    public NetworkRequest(RequestType requestType, String str, boolean z, C3169d c3169d) {
        this(requestType, str, z, c3169d, false);
    }

    public NetworkRequest(RequestType requestType, String str, boolean z, C3169d c3169d, boolean z2) {
        this.f7402a = new HashMap();
        this.f7403b = new HashMap();
        this.f7404c = new HashMap();
        this.f7408h = 60000;
        this.f7409i = 60000;
        this.f7410j = true;
        this.f7414n = true;
        this.f7415o = -1;
        this.f7405e = requestType;
        this.f7406f = str;
        this.f7411k = z;
        this.f7407g = c3169d;
        this.f7402a.put("User-Agent", C3105a.m10084d());
        this.f7416p = z2;
    }

    public void m9750a(long j) {
        this.f7415o = j;
    }

    public long m9759g() {
        return this.f7415o;
    }

    public boolean m9760h() {
        return this.f7415o != -1;
    }

    public void m9751a(boolean z) {
        this.f7414n = z;
    }

    public String m9761i() {
        return this.f7406f;
    }

    public void m9756c(Map<String, String> map) {
        if (map != null) {
            this.f7402a.putAll(map);
        }
    }

    public void m9757d(Map<String, String> map) {
        if (map != null) {
            this.f7403b.putAll(map);
        }
    }

    public void m9758e(Map<String, String> map) {
        if (map != null) {
            this.f7404c.putAll(map);
        }
    }

    public Map<String, String> m9762j() {
        C3155d.m10405a(this.f7402a);
        return this.f7402a;
    }

    public String m9763k() {
        String str = this.f7406f;
        String l = m9764l();
        if (l == null || l.trim().length() == 0) {
            return str;
        }
        if (!str.contains("?")) {
            str = str + "?";
        }
        if (!(str.endsWith("&") || str.endsWith("?"))) {
            str = str + "&";
        }
        return str + l;
    }

    public void mo6238a() {
        if (!this.f7414n) {
            return;
        }
        if (this.f7405e == RequestType.GET) {
            mo6240a(this.f7403b);
        } else if (this.f7405e == RequestType.POST) {
            mo6240a(this.f7404c);
        }
    }

    public String m9764l() {
        C3155d.m10405a(this.f7403b);
        String a = C3155d.m10403a(this.f7403b, "&");
        Logger.m10359a(InternalLogLevel.INTERNAL, f7401d, "Get params: " + a);
        return a;
    }

    public String m9765m() {
        C3155d.m10405a(this.f7404c);
        String a = C3155d.m10403a(this.f7404c, "&");
        Logger.m10359a(InternalLogLevel.INTERNAL, f7401d, "Post body url: " + m9761i());
        Logger.m10359a(InternalLogLevel.INTERNAL, f7401d, "Post body: " + a);
        if (!m9770r()) {
            return a;
        }
        a = mo6239a(a);
        Logger.m10359a(InternalLogLevel.INTERNAL, f7401d, "Encrypted post body: " + a);
        return a;
    }

    public boolean m9766n() {
        return this.f7410j;
    }

    public void m9754b(boolean z) {
        this.f7410j = z;
    }

    public RequestType m9767o() {
        return this.f7405e;
    }

    public int m9768p() {
        return this.f7408h;
    }

    public void m9753b(int i) {
        this.f7408h = i;
    }

    public void m9755c(int i) {
        this.f7409i = i;
    }

    public int m9769q() {
        return this.f7409i;
    }

    public boolean m9770r() {
        return this.f7411k;
    }

    public boolean m9771s() {
        return this.f7416p;
    }

    private void mo6240a(Map<String, String> map) {
        map.putAll(C3159a.m10427a().m10431c());
        map.putAll(C3160b.m10433a());
        map.putAll(C3162d.m10441a());
        if (this.f7407g == null) {
            return;
        }
        if (m9770r()) {
            map.putAll(this.f7407g.m10538a());
        } else {
            map.putAll(this.f7407g.m10540b());
        }
    }

    private String mo6239a(String str) {
        byte[] a = C3150b.m10370a(8);
        this.f7412l = C3150b.m10370a(16);
        this.f7413m = C3150b.m10375b();
        Map hashMap = new HashMap();
        C3045a c3125f = new C3125f();
        C3121b.m10178a().m10190a(c3125f, null);
        hashMap.put("sm", C3150b.m10368a(str, this.f7413m, this.f7412l, a, c3125f.m10213f(), c3125f.m10212e()));
        hashMap.put("sn", c3125f.m10214g());
        return C3155d.m10403a(hashMap, "&");
    }

    @Nullable
    protected byte[] m9752a(byte[] bArr) {
        try {
            return C3150b.m10374a(Base64.decode(bArr, 0), this.f7413m, this.f7412l);
        } catch (IllegalArgumentException e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7401d, "Msg : " + e.getMessage());
            return null;
        }
    }

    public long m9772t() {
        long j = 0;
        try {
            j = 0 + ((long) m9765m().length());
            return j + ((long) m9764l().length());
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7401d, "Error in getting request size");
            return j;
        }
    }
}
