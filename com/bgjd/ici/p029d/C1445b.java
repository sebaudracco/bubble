package com.bgjd.ici.p029d;

import android.annotation.SuppressLint;
import android.content.Context;
import com.bgjd.ici.json.JSONArray;
import com.bgjd.ici.json.JSONException;
import com.bgjd.ici.json.JSONObject;
import com.bgjd.ici.p025b.C1395h;
import com.bgjd.ici.p025b.C1402i;
import com.bgjd.ici.p025b.C1409k;
import com.bgjd.ici.p025b.C1421q;
import com.bgjd.ici.p025b.C1421q.C1412b;
import com.bgjd.ici.p025b.ac;
import com.bgjd.ici.p030e.C1487j;
import java.lang.reflect.Method;
import java.util.Calendar;

@SuppressLint({"NewApi"})
public class C1445b extends C1409k<JSONObject> {
    private static final String f2230f = "ADDINF";
    private JSONArray f2231g;

    public /* synthetic */ Object mo2325d() {
        return m2978f();
    }

    public C1445b(C1395h c1395h) {
        super(c1395h);
        this.f2231g = new JSONArray();
        this.b = "sdk-additional";
    }

    public JSONObject m2978f() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray F = this.a.mo2178F();
            if (F != null && F.length() > 0) {
                for (int i = 0; i < F.length(); i++) {
                    final C1487j c1487j = new C1487j(F.getJSONObject(i));
                    if (c1487j.m3161m()) {
                        final long h = this.a.mo2263h(c1487j.m3157i());
                        if (h <= 0 || c1487j.m3154f() <= 0 || (Calendar.getInstance().getTimeInMillis() - h) / 86400000 < ((long) c1487j.m3154f())) {
                            if (this.a.mo2265i(c1487j.m3158j()).equalsIgnoreCase(c1487j.m3159k())) {
                                m2976a(c1487j, h);
                            } else {
                                new C1421q(this.a).mo2310a(c1487j, new C1412b(this) {
                                    final /* synthetic */ C1445b f2229c;

                                    public void mo2303a(boolean z) {
                                        if (z) {
                                            this.f2229c.a.mo2219b(c1487j.m3158j(), c1487j.m3159k());
                                            this.f2229c.m2976a(c1487j, h);
                                        }
                                    }

                                    public void mo2302a(Throwable th, String str) {
                                        C1402i.m2898a(C1445b.f2230f, th, th.getMessage());
                                    }
                                });
                            }
                        }
                    }
                }
            }
        } catch (Throwable e) {
            C1402i.m2898a(f2230f, e, e.getMessage());
        }
        try {
            jSONObject.put("a", this.f2231g);
            jSONObject.put("b", this.f2231g.length());
            jSONObject.put("c", System.currentTimeMillis());
        } catch (JSONException e2) {
        }
        return jSONObject;
    }

    private void m2976a(C1487j c1487j, long j) {
        if (c1487j.m3152e() == 0) {
            Class a = ac.m2857a(c1487j, this.a, getClass().getClassLoader());
            if (a != null) {
                try {
                    Object newInstance = a.newInstance();
                    Method method = a.getMethod("getData", new Class[]{Context.class});
                    if (newInstance != null) {
                        Object invoke = method.invoke(newInstance, new Object[]{this.a.getContext()});
                        if (invoke != null) {
                            String a2 = ac.m2859a(invoke.toString());
                            if (!a2.equalsIgnoreCase(this.a.mo2268j(c1487j.m3158j()))) {
                                this.a.mo2224c(c1487j.m3158j(), a2);
                                newInstance = new JSONObject();
                                newInstance.put("a", c1487j.m3159k());
                                newInstance.put("b", c1487j.m3158j());
                                newInstance.put("c", c1487j.m3150d());
                                newInstance.put("d", invoke);
                                this.f2231g.put(newInstance);
                                if (j == 0) {
                                    this.a.mo2218b(c1487j.m3157i(), System.currentTimeMillis());
                                }
                            }
                        }
                    }
                } catch (Throwable e) {
                    C1402i.m2898a(f2230f, e, e.getMessage());
                } catch (Throwable e2) {
                    C1402i.m2898a(f2230f, e2, e2.getMessage());
                } catch (Throwable e22) {
                    C1402i.m2898a(f2230f, e22, e22.getMessage());
                } catch (Throwable e222) {
                    C1402i.m2898a(f2230f, e222, e222.getMessage());
                } catch (Throwable e2222) {
                    C1402i.m2898a(f2230f, e2222, e2222.getMessage());
                } catch (Throwable e22222) {
                    C1402i.m2898a(f2230f, e22222, e22222.getMessage());
                }
            }
        }
    }
}
