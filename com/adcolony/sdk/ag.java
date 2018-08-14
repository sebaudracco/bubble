package com.adcolony.sdk;

import com.adcolony.sdk.aa.C0595a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class ag {
    private ArrayList<ai> f534a = new ArrayList();
    private HashMap<Integer, ai> f535b = new HashMap();
    private int f536c = 2;
    private HashMap<String, ArrayList<ah>> f537d = new HashMap();
    private JSONArray f538e = C0802y.m1469b();
    private int f539f = 1;

    ag() {
    }

    void m703a(String str, ah ahVar) {
        ArrayList arrayList = (ArrayList) this.f537d.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList();
            this.f537d.put(str, arrayList);
        }
        arrayList.add(ahVar);
    }

    void m707b(String str, ah ahVar) {
        synchronized (this.f537d) {
            ArrayList arrayList = (ArrayList) this.f537d.get(str);
            if (arrayList != null) {
                arrayList.remove(ahVar);
            }
        }
    }

    void m702a() {
        C0740l a = C0594a.m605a();
        if (!a.m1277g() && !a.m1278h() && C0594a.m614d()) {
            final ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
            newSingleThreadExecutor.submit(new Runnable(this) {
                final /* synthetic */ ag f530b;

                public void run() {
                    JSONObject d = C0594a.m605a().m1271d().m554d();
                    C0802y.m1462a(d, "os_name", "android");
                    this.f530b.m701a(new ADCVMModule(C0594a.m613c(), 1, C0594a.m605a().m1285o().m787g() + "7bf3a1e7bbd31e612eda3310c2cdb8075c43c6b5", d, newSingleThreadExecutor));
                }
            });
        }
    }

    ai m701a(ai aiVar) {
        synchronized (this.f534a) {
            this.f534a.add(aiVar);
            this.f535b.put(Integer.valueOf(aiVar.mo1841a()), aiVar);
        }
        return aiVar;
    }

    ai m700a(int i) {
        ai aiVar;
        synchronized (this.f534a) {
            aiVar = (ai) this.f535b.get(Integer.valueOf(i));
            if (aiVar == null) {
                aiVar = null;
            } else {
                this.f534a.remove(aiVar);
                this.f535b.remove(Integer.valueOf(i));
                aiVar.mo1843b();
            }
        }
        return aiVar;
    }

    synchronized void m706b() {
        int size;
        JSONArray jSONArray;
        synchronized (this.f534a) {
            for (size = this.f534a.size() - 1; size >= 0; size--) {
                ((ai) this.f534a.get(size)).mo1844c();
            }
        }
        if (this.f538e.length() > 0) {
            JSONArray jSONArray2 = this.f538e;
            this.f538e = C0802y.m1469b();
            jSONArray = jSONArray2;
        } else {
            jSONArray = null;
        }
        if (jSONArray != null) {
            int length = jSONArray.length();
            for (size = 0; size < length; size++) {
                try {
                    final JSONObject jSONObject = jSONArray.getJSONObject(size);
                    final String string = jSONObject.getString("m_type");
                    if (jSONObject.getInt("m_origin") < 2) {
                        m704a(string, jSONObject);
                    } else if (C0594a.m614d()) {
                        az.m880a(new Runnable(this) {
                            final /* synthetic */ ag f533c;

                            public void run() {
                                this.f533c.m704a(string, jSONObject);
                            }
                        });
                    }
                } catch (JSONException e) {
                    new C0595a().m622a("JSON error from message dispatcher's update_modules(): ").m622a(e.toString()).m624a(aa.f484h);
                }
            }
        }
    }

    void m704a(String str, JSONObject jSONObject) {
        synchronized (this.f537d) {
            ArrayList arrayList = (ArrayList) this.f537d.get(str);
            if (arrayList != null) {
                af afVar = new af(jSONObject);
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    try {
                        ((ah) arrayList.get(i)).mo1863a(afVar);
                        i++;
                    } catch (Object e) {
                        new C0595a().m621a(e).m624a(aa.f484h);
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    void m705a(JSONObject jSONObject) {
        try {
            if (!jSONObject.has("m_id")) {
                int i = this.f539f;
                this.f539f = i + 1;
                jSONObject.put("m_id", i);
            }
            if (!jSONObject.has("m_origin")) {
                jSONObject.put("m_origin", 0);
            }
            int i2 = jSONObject.getInt("m_target");
            if (i2 == 0) {
                synchronized (this) {
                    this.f538e.put(jSONObject);
                }
                return;
            }
            ai aiVar = (ai) this.f535b.get(Integer.valueOf(i2));
            if (aiVar != null) {
                aiVar.mo1842a(jSONObject);
            }
        } catch (JSONException e) {
            new C0595a().m622a("JSON error in ADCMessageDispatcher's send_message(): ").m622a(e.toString()).m624a(aa.f484h);
        }
    }

    ArrayList<ai> m708c() {
        return this.f534a;
    }

    int m709d() {
        int i = this.f536c;
        this.f536c = i + 1;
        return i;
    }

    HashMap<Integer, ai> m710e() {
        return this.f535b;
    }
}
