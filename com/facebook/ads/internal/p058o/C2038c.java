package com.facebook.ads.internal.p058o;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.facebook.ads.internal.p056q.p057a.C2117h;
import com.facebook.ads.internal.p056q.p057a.C2121l;
import com.facebook.ads.internal.p056q.p076c.C2145d;
import com.facebook.ads.internal.p056q.p076c.C2145d.C2144a;
import com.facebook.ads.internal.p056q.p077d.C2150a;
import com.facebook.ads.internal.p058o.C2042f.C2041a;
import com.facebook.ads.internal.p061c.C1951b;
import com.facebook.ads.internal.p063f.C1978a;
import com.facebook.ads.internal.p063f.C1981e;
import com.facebook.ads.internal.p064g.C1985a;
import com.facebook.ads.internal.p065h.C1989c;
import com.facebook.ads.internal.p067k.C2004a;
import com.facebook.ads.internal.p068l.C2005a;
import com.facebook.ads.internal.p071p.p072a.C2035b;
import com.facebook.ads.internal.p071p.p072a.C2048a;
import com.facebook.ads.internal.p071p.p072a.C2059m;
import com.facebook.ads.internal.p071p.p072a.C2060n;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.C2097a;
import com.facebook.ads.internal.protocol.C2098b;
import com.facebook.ads.internal.protocol.C2106i;
import com.facebook.ads.internal.protocol.f;
import java.security.MessageDigest;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import org.json.JSONException;

public class C2038c {
    private static final C2121l f4850i = new C2121l();
    private static final ThreadPoolExecutor f4851j = ((ThreadPoolExecutor) Executors.newCachedThreadPool(f4850i));
    private final Context f4852a;
    private final C2040e f4853b = C2040e.m6541a();
    private final C2005a f4854c = new C2005a(this.f4852a);
    private Map<String, String> f4855d;
    private C1870a f4856e;
    private C2033b f4857f;
    private C2048a f4858g;
    private final String f4859h = C2039d.m6539a();

    public interface C1870a {
        void mo3619a(C2043g c2043g);

        void mo3620a(C2097a c2097a);
    }

    class C20362 extends C2035b {
        final /* synthetic */ C2038c f4848a;

        C20362(C2038c c2038c) {
            this.f4848a = c2038c;
        }

        void m6518a(C2059m c2059m) {
            C2032a.m6506b(this.f4848a.f4857f);
            this.f4848a.f4858g = null;
            try {
                C2060n a = c2059m.m6615a();
                if (a != null) {
                    String e = a.m6620e();
                    C2042f a2 = this.f4848a.f4853b.m6545a(e);
                    if (a2.m6547b() == C2041a.ERROR) {
                        C2044h c2044h = (C2044h) a2;
                        String f = c2044h.m6553f();
                        this.f4848a.m6527a(C2097a.m6746a(AdErrorType.adErrorTypeFromCode(c2044h.m6554g(), AdErrorType.ERROR_MESSAGE), f == null ? e : f));
                        return;
                    }
                }
            } catch (JSONException e2) {
            }
            this.f4848a.m6527a(C2097a.m6746a(AdErrorType.NETWORK_ERROR, c2059m.getMessage()));
        }

        public void mo3730a(C2060n c2060n) {
            if (c2060n != null) {
                String e = c2060n.m6620e();
                C2032a.m6506b(this.f4848a.f4857f);
                this.f4848a.f4858g = null;
                this.f4848a.m6528a(e);
            }
        }

        public void mo3731a(Exception exception) {
            if (C2059m.class.equals(exception.getClass())) {
                m6518a((C2059m) exception);
            } else {
                this.f4848a.m6527a(C2097a.m6746a(AdErrorType.NETWORK_ERROR, exception.getMessage()));
            }
        }
    }

    public C2038c(Context context) {
        this.f4852a = context.getApplicationContext();
    }

    private void m6526a(C2043g c2043g) {
        if (this.f4856e != null) {
            this.f4856e.mo3619a(c2043g);
        }
        m6536a();
    }

    private void m6527a(C2097a c2097a) {
        if (this.f4856e != null) {
            this.f4856e.mo3620a(c2097a);
        }
        m6536a();
    }

    private void m6528a(String str) {
        try {
            C2042f a = this.f4853b.m6545a(str);
            C1989c a2 = a.mo3732a();
            if (a2 != null) {
                this.f4854c.m6362a(a2.m6288b());
                C2032a.m6503a(a2.m6286a().m6295d(), this.f4857f);
            }
            switch (a.m6547b()) {
                case ADS:
                    C2043g c2043g = (C2043g) a;
                    if (a2 != null) {
                        if (a2.m6286a().m6296e()) {
                            C2032a.m6504a(str, this.f4857f);
                        }
                        Object obj = this.f4855d != null ? (String) this.f4855d.get("CLIENT_REQUEST_ID") : null;
                        String c = a.m6548c();
                        if (!(TextUtils.isEmpty(c) || TextUtils.isEmpty(obj))) {
                            StringBuilder stringBuilder = new StringBuilder();
                            for (int i = 0; i < "qo290s8p6q174sr59pr123o45pr7pp29".length(); i++) {
                                char charAt = "qo290s8p6q174sr59pr123o45pr7pp29".charAt(i);
                                if ((charAt >= 'a' && charAt <= 'm') || (charAt >= 'A' && charAt <= 'M')) {
                                    charAt = (char) (charAt + 13);
                                } else if ((charAt >= 'n' && charAt <= 'z') || (charAt >= 'N' && charAt <= 'Z')) {
                                    charAt = (char) (charAt - 13);
                                }
                                stringBuilder.append(charAt);
                            }
                            byte[] bytes = (obj + c + stringBuilder.toString()).getBytes("iso-8859-1");
                            MessageDigest instance = MessageDigest.getInstance("SHA-1");
                            instance.update(bytes, 0, bytes.length);
                            if (!a.m6549d().equals(C2117h.m6794a(instance.digest()))) {
                                C2150a.m6888a(new C2106i(), this.f4852a);
                            }
                            bytes = (c + obj + stringBuilder.toString()).getBytes("iso-8859-1");
                            instance = MessageDigest.getInstance("SHA-1");
                            instance.update(bytes, 0, bytes.length);
                            C1981e.m6249a(new C1978a(c, C2117h.m6794a(instance.digest())), this.f4852a);
                        }
                        if (!(TextUtils.isEmpty(a.m6550e()) || TextUtils.isEmpty(obj))) {
                            new C2004a(this.f4852a, obj, a.m6550e()).m6338a();
                        }
                    }
                    m6526a(c2043g);
                    return;
                case ERROR:
                    C2044h c2044h = (C2044h) a;
                    String f = c2044h.m6553f();
                    AdErrorType adErrorTypeFromCode = AdErrorType.adErrorTypeFromCode(c2044h.m6554g(), AdErrorType.ERROR_MESSAGE);
                    if (f != null) {
                        str = f;
                    }
                    m6527a(C2097a.m6746a(adErrorTypeFromCode, str));
                    return;
                default:
                    m6527a(C2097a.m6746a(AdErrorType.UNKNOWN_RESPONSE, str));
                    return;
            }
        } catch (Exception e) {
            m6527a(C2097a.m6746a(AdErrorType.PARSER_FAILURE, e.getMessage()));
        }
        m6527a(C2097a.m6746a(AdErrorType.PARSER_FAILURE, e.getMessage()));
    }

    private C2035b m6529b() {
        return new C20362(this);
    }

    public void m6536a() {
        if (this.f4858g != null) {
            this.f4858g.m6592c(1);
            this.f4858g.m6590b(1);
            this.f4858g = null;
        }
    }

    public void m6537a(final C2033b c2033b) {
        m6536a();
        if (C2145d.m6867c(this.f4852a) == C2144a.NONE) {
            m6527a(new C2097a(AdErrorType.NETWORK_ERROR, "No network connection"));
            return;
        }
        this.f4857f = c2033b;
        C1985a.m6259a(this.f4852a);
        if (C2032a.m6505a(c2033b)) {
            String c = C2032a.m6507c(c2033b);
            if (c != null) {
                m6528a(c);
                return;
            } else {
                m6527a(C2097a.m6746a(AdErrorType.LOAD_TOO_FREQUENTLY, null));
                return;
            }
        }
        f4851j.submit(new Runnable(this) {
            final /* synthetic */ C2038c f4847b;

            public void run() {
                C1951b.m6157a(this.f4847b.f4852a);
                if (c2033b.m6514e().m6759a()) {
                    try {
                        c2033b.m6514e().m6758a(C1951b.f4526b);
                    } catch (C2098b e) {
                        this.f4847b.m6527a(C2097a.m6747a(e));
                    }
                    this.f4847b.m6528a(c2033b.m6514e().m6760b());
                    return;
                }
                this.f4847b.f4855d = c2033b.m6515f();
                try {
                    this.f4847b.f4855d.put("M_BANNER_KEY", new String(Base64.encode((this.f4847b.f4852a.getPackageName() + " " + this.f4847b.f4852a.getPackageManager().getInstallerPackageName(this.f4847b.f4852a.getPackageName())).getBytes(), 2)));
                } catch (Exception e2) {
                }
                try {
                    boolean z = c2033b.f4833c == f.l || c2033b.f4833c == f.j || c2033b.f4833c == null;
                    this.f4847b.f4858g = C2145d.m6866b(this.f4847b.f4852a, z);
                    this.f4847b.f4858g.m6582a(this.f4847b.f4859h, this.f4847b.f4858g.m6589b().m6623a(this.f4847b.f4855d), this.f4847b.m6529b());
                } catch (Exception e3) {
                    this.f4847b.m6527a(C2097a.m6746a(AdErrorType.AD_REQUEST_FAILED, e3.getMessage()));
                }
            }
        });
    }

    public void m6538a(C1870a c1870a) {
        this.f4856e = c1870a;
    }
}
