package com.bgjd.ici;

import android.app.IntentService;
import android.content.Intent;
import com.bgjd.ici.p025b.C1387g;
import com.bgjd.ici.p025b.C1395h;
import com.bgjd.ici.p025b.C1402i;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.bgjd.ici.p025b.C1411n;
import com.bgjd.ici.p025b.C1416p;
import com.bgjd.ici.p025b.C1425s;
import com.bgjd.ici.p025b.C1425s.C1424a;
import com.bgjd.ici.p025b.C1427u;
import com.bgjd.ici.p025b.C1430x;
import com.bgjd.ici.p025b.aa;
import com.bgjd.ici.p025b.ab;
import com.bgjd.ici.p025b.ac;
import com.bgjd.ici.p029d.C1451c;
import com.bgjd.ici.p029d.C1461l;

public class MarketService extends IntentService {
    private static final String f2036a = "MKTMSRVC";
    private static boolean f2037b = false;

    private class C1388a implements C1387g {
        final /* synthetic */ MarketService f2035a;

        private C1388a(MarketService marketService) {
            this.f2035a = marketService;
        }

        public void mo2168a(int i, String str) {
            String str2 = MarketService.f2036a;
            String[] strArr = new String[1];
            strArr[0] = String.format("Service Finished Executing With Status %d Message %s", new Object[]{Integer.valueOf(i), str});
            C1402i.m2901b(str2, strArr);
            MarketService.f2037b = false;
            this.f2035a.stopSelf();
        }
    }

    public MarketService() {
        super(null);
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        return 1;
    }

    protected void onHandleIntent(Intent intent) {
        try {
            String action = intent.getAction();
            C1395h aaVar = new aa(getBaseContext());
            if (action != null) {
                String a;
                boolean z;
                Thread.setDefaultUncaughtExceptionHandler(new C1416p(aaVar));
                boolean booleanExtra = intent.getBooleanExtra(C1404b.f2099C, false);
                if ((action.equalsIgnoreCase(C1404b.af) || action.equalsIgnoreCase(C1404b.ac)) && !f2037b) {
                    C1451c.m2986a(aaVar.getContext());
                    try {
                        a = C1451c.m2985a();
                        boolean b = C1451c.m2987b();
                        String r = aaVar.mo2283r();
                        if (r.length() > 0 && !r.equalsIgnoreCase(a) && action.equalsIgnoreCase(C1404b.af) && !b) {
                            action = C1404b.ac;
                            aaVar.mo2186M();
                            C1402i.m2901b(f2036a, "Advertising Id has been reset by end-user. Invoking new registration with action : " + action);
                        }
                        aaVar.mo2216b(a);
                        aaVar.mo2232e(b);
                        aaVar.mo2201a(System.currentTimeMillis() / 1000);
                        a = action;
                        z = b;
                    } catch (Exception e) {
                        a = action;
                        z = false;
                    }
                } else {
                    a = action;
                    z = false;
                }
                if (booleanExtra) {
                    C1402i.m2901b(f2036a, "SDK currently running on Sanbox Mode please make sure to disable before uploading to production.");
                }
                C1425s c1425s = null;
                if (a.equalsIgnoreCase(C1404b.ac) && !z) {
                    C1402i.m2901b(f2036a, "Registration Started..");
                    c1425s = new C1424a(this).m2941a(booleanExtra).m2940a(C1404b.aw).m2938a(new C1430x(new C1388a())).m2939a(new C1427u()).m2942a();
                    f2037b = true;
                } else if (!a.equalsIgnoreCase(C1404b.af) || z) {
                    if (a.length() > 0 && !z) {
                        c1425s = new C1424a(this).m2941a(booleanExtra).m2937a(intent).m2938a(new C1430x(new C1388a())).m2939a(new ab()).m2942a();
                    } else if (z) {
                        f2037b = false;
                        aaVar.mo2185L();
                        stopSelf();
                    }
                } else if (aaVar.mo2211a() && !aaVar.mo2226c()) {
                    C1461l d = ac.m2873d();
                    Object obj = 1;
                    if (d.f2306b > 0 && d.f2305a > 0) {
                        if ((((float) d.f2306b) / ((float) d.f2305a)) * 100.0f <= 8.0f) {
                            obj = null;
                            aaVar.mo2223c(300000);
                        }
                        C1402i.m2901b(f2036a, "Logger Started With Current Memory Percentage " + r2);
                    }
                    if (!(aaVar.mo2291v() || r1 == null)) {
                        aaVar.mo2214b(System.currentTimeMillis() / 1000);
                        c1425s = new C1424a(this).m2941a(booleanExtra).m2938a(new C1430x(new C1388a())).m2939a(new C1411n()).m2942a();
                        f2037b = true;
                    }
                }
                if (c1425s != null) {
                    c1425s.m2943a();
                    return;
                }
                return;
            }
            aaVar.mo2223c(300000);
            f2037b = false;
            stopSelf();
        } catch (Exception e2) {
            f2037b = false;
            stopSelf();
        }
    }
}
