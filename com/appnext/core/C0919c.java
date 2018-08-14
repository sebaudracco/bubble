package com.appnext.core;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class C0919c {
    private static int kO = 200;
    private final HashMap<Ad, C1104a> kP = new HashMap();

    public interface C0914a {
        <T> void mo1971a(T t);

        void error(String str);
    }

    protected abstract String mo1973a(Context context, Ad ad, String str, ArrayList<Pair<String, String>> arrayList);

    protected abstract void mo1974a(Context context, Ad ad, C1104a c1104a) throws Exception;

    protected abstract void mo1975a(Ad ad, String str, String str2);

    protected abstract <T> void mo1977a(String str, Ad ad, T t);

    protected abstract boolean mo1978a(Context context, Ad ad, ArrayList<?> arrayList);

    protected abstract boolean mo1979a(Context context, C0908i c0908i);

    protected abstract C0921q mo1981d(Ad ad);

    public void m1868a(Context context, Ad ad, String str, C0914a c0914a) {
        m1869a(context, ad, str, c0914a, true);
    }

    public void m1869a(Context context, Ad ad, String str, C0914a c0914a, boolean z) {
        final Ad ad2 = ad;
        final Context context2 = context;
        final C0914a c0914a2 = c0914a;
        final String str2 = str;
        final boolean z2 = z;
        new Thread(new Runnable(this) {
            final /* synthetic */ C0919c kR;

            class C11051 implements Runnable {
                final /* synthetic */ C11061 kS;

                C11051(C11061 c11061) {
                    this.kS = c11061;
                }

                public void run() {
                    try {
                        if (this.kS.kR.mo1980a(ad2) || (this.kS.kR.m1880b(ad2) && this.kS.kR.m1882c(ad2))) {
                            ArrayList cL = ((C1104a) this.kS.kR.kP.get(ad2)).cL();
                            if (cL.size() == 0) {
                                c0914a2.error(AppnextError.NO_ADS);
                                return;
                            } else if (!this.kS.kR.mo1978a(context2, ad2, cL)) {
                                this.kS.kR.m1864U(str2);
                            } else if (c0914a2 != null) {
                                c0914a2.mo1971a(cL);
                                return;
                            } else {
                                return;
                            }
                        }
                    } catch (Throwable th) {
                        if (c0914a2 != null) {
                            c0914a2.error(AppnextError.NO_ADS);
                        }
                    }
                    if (C1128g.cU().equals("")) {
                        C1128g.m2359v(context2);
                        this.kS.kR.m1863b(context2, ad2, str2, c0914a2, z2);
                        return;
                    }
                    this.kS.kR.m1863b(context2, ad2, str2, c0914a2, z2);
                }
            }

            public void run() {
                try {
                    if (this.kR.mo1980a(ad2) || (this.kR.m1880b(ad2) && this.kR.m1882c(ad2))) {
                        this.kR.mo1974a(context2, ad2, this.kR.m1890j(ad2));
                    }
                } catch (Throwable th) {
                }
                new Handler(Looper.getMainLooper()).post(new C11051(this));
            }
        }).start();
    }

    private void m1863b(Context context, Ad ad, String str, C0914a c0914a, boolean z) {
        final Ad ad2 = ad;
        final Context context2 = context;
        final C0914a c0914a2 = c0914a;
        final String str2 = str;
        final boolean z2 = z;
        new Thread(this) {
            final /* synthetic */ C0919c kR;

            class C11071 implements Runnable {
                final /* synthetic */ C11082 kT;

                C11071(C11082 c11082) {
                    this.kT = c11082;
                }

                public void run() {
                    try {
                        ((C1104a) this.kT.kR.kP.get(ad2)).m2305i(((C1104a) this.kT.kR.kP.get(ad2)).cL());
                        this.kT.kR.mo1977a(str2, ad2, ((C1104a) this.kT.kR.kP.get(ad2)).cL());
                    } catch (Throwable th) {
                        this.kT.kR.m1879b(AppnextError.INTERNAL_ERROR, ad2);
                    }
                }
            }

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                r8 = this;
                r7 = 2;
                r4 = 1;
                r1 = 0;
                super.run();
                r0 = r2;	 Catch:{ Throwable -> 0x01ed }
                r2 = r3;	 Catch:{ Throwable -> 0x01ed }
                r2 = com.appnext.core.C1128g.m2330A(r2);	 Catch:{ Throwable -> 0x01ed }
                r0.setSessionId(r2);	 Catch:{ Throwable -> 0x01ed }
            L_0x0011:
                r0 = r8.kR;	 Catch:{ Throwable -> 0x00df }
                r2 = r0.kP;	 Catch:{ Throwable -> 0x00df }
                monitor-enter(r2);	 Catch:{ Throwable -> 0x00df }
                r0 = r8.kR;	 Catch:{ all -> 0x0108 }
                r0 = r0.kP;	 Catch:{ all -> 0x0108 }
                r3 = r2;	 Catch:{ all -> 0x0108 }
                r0 = r0.containsKey(r3);	 Catch:{ all -> 0x0108 }
                if (r0 == 0) goto L_0x0053;
            L_0x0026:
                r0 = r8.kR;	 Catch:{ all -> 0x0108 }
                r0 = r0.kP;	 Catch:{ all -> 0x0108 }
                r3 = r2;	 Catch:{ all -> 0x0108 }
                r0 = r0.get(r3);	 Catch:{ all -> 0x0108 }
                r0 = (com.appnext.core.C1104a) r0;	 Catch:{ all -> 0x0108 }
                r0 = r0.getState();	 Catch:{ all -> 0x0108 }
                if (r0 != r4) goto L_0x0053;
            L_0x003a:
                r0 = r4;	 Catch:{ all -> 0x0108 }
                if (r0 == 0) goto L_0x0051;
            L_0x003e:
                r0 = r8.kR;	 Catch:{ all -> 0x0108 }
                r0 = r0.kP;	 Catch:{ all -> 0x0108 }
                r1 = r2;	 Catch:{ all -> 0x0108 }
                r0 = r0.get(r1);	 Catch:{ all -> 0x0108 }
                r0 = (com.appnext.core.C1104a) r0;	 Catch:{ all -> 0x0108 }
                r1 = r4;	 Catch:{ all -> 0x0108 }
                r0.m2300a(r1);	 Catch:{ all -> 0x0108 }
            L_0x0051:
                monitor-exit(r2);	 Catch:{ all -> 0x0108 }
            L_0x0052:
                return;
            L_0x0053:
                r0 = "start loading ads";
                com.appnext.core.C1128g.m2333W(r0);	 Catch:{ all -> 0x0108 }
                r0 = new com.appnext.core.a;	 Catch:{ all -> 0x0108 }
                r0.<init>();	 Catch:{ all -> 0x0108 }
                r3 = r4;	 Catch:{ all -> 0x0108 }
                r0.m2300a(r3);	 Catch:{ all -> 0x0108 }
                r3 = r5;	 Catch:{ all -> 0x0108 }
                r0.setPlacementID(r3);	 Catch:{ all -> 0x0108 }
                r3 = 1;
                r0.setState(r3);	 Catch:{ all -> 0x0108 }
                r3 = r8.kR;	 Catch:{ all -> 0x0108 }
                r3 = r3.kP;	 Catch:{ all -> 0x0108 }
                r4 = r2;	 Catch:{ all -> 0x0108 }
                r3 = r3.containsKey(r4);	 Catch:{ all -> 0x0108 }
                if (r3 == 0) goto L_0x0085;
            L_0x007a:
                r3 = r8.kR;	 Catch:{ all -> 0x0108 }
                r3 = r3.kP;	 Catch:{ all -> 0x0108 }
                r4 = r2;	 Catch:{ all -> 0x0108 }
                r3.remove(r4);	 Catch:{ all -> 0x0108 }
            L_0x0085:
                r3 = r8.kR;	 Catch:{ all -> 0x0108 }
                r4 = r2;	 Catch:{ all -> 0x0108 }
                r3.m1870a(r4, r0);	 Catch:{ all -> 0x0108 }
                monitor-exit(r2);	 Catch:{ all -> 0x0108 }
                r0 = new java.util.ArrayList;	 Catch:{ SocketTimeoutException -> 0x010b, UnknownHostException -> 0x0120, Throwable -> 0x0135 }
                r0.<init>();	 Catch:{ SocketTimeoutException -> 0x010b, UnknownHostException -> 0x0120, Throwable -> 0x0135 }
                r2 = r8.kR;	 Catch:{ SocketTimeoutException -> 0x010b, UnknownHostException -> 0x0120, Throwable -> 0x0135 }
                r3 = r3;	 Catch:{ SocketTimeoutException -> 0x010b, UnknownHostException -> 0x0120, Throwable -> 0x0135 }
                r4 = r2;	 Catch:{ SocketTimeoutException -> 0x010b, UnknownHostException -> 0x0120, Throwable -> 0x0135 }
                r5 = r5;	 Catch:{ SocketTimeoutException -> 0x010b, UnknownHostException -> 0x0120, Throwable -> 0x0135 }
                r2 = r2.mo1973a(r3, r4, r5, r0);	 Catch:{ SocketTimeoutException -> 0x010b, UnknownHostException -> 0x0120, Throwable -> 0x0135 }
                r3 = new java.lang.StringBuilder;	 Catch:{ SocketTimeoutException -> 0x010b, UnknownHostException -> 0x0120, Throwable -> 0x0135 }
                r3.<init>();	 Catch:{ SocketTimeoutException -> 0x010b, UnknownHostException -> 0x0120, Throwable -> 0x0135 }
                r4 = "AdsManager request url: ";
                r3 = r3.append(r4);	 Catch:{ SocketTimeoutException -> 0x010b, UnknownHostException -> 0x0120, Throwable -> 0x0135 }
                r3 = r3.append(r2);	 Catch:{ SocketTimeoutException -> 0x010b, UnknownHostException -> 0x0120, Throwable -> 0x0135 }
                r3 = r3.toString();	 Catch:{ SocketTimeoutException -> 0x010b, UnknownHostException -> 0x0120, Throwable -> 0x0135 }
                com.appnext.core.C1128g.m2333W(r3);	 Catch:{ SocketTimeoutException -> 0x010b, UnknownHostException -> 0x0120, Throwable -> 0x0135 }
                r3 = r8.kR;	 Catch:{ SocketTimeoutException -> 0x010b, UnknownHostException -> 0x0120, Throwable -> 0x0135 }
                r3 = r3.m1891o();	 Catch:{ SocketTimeoutException -> 0x010b, UnknownHostException -> 0x0120, Throwable -> 0x0135 }
                r0 = com.appnext.core.C1128g.m2335a(r2, r0, r3);	 Catch:{ SocketTimeoutException -> 0x010b, UnknownHostException -> 0x0120, Throwable -> 0x0135 }
                com.appnext.core.C1128g.m2333W(r0);	 Catch:{ SocketTimeoutException -> 0x010b, UnknownHostException -> 0x0120, Throwable -> 0x0135 }
                r2 = "{}";
                r2 = r0.equals(r2);	 Catch:{ Throwable -> 0x00df }
                if (r2 != 0) goto L_0x00d3;
            L_0x00cb:
                r2 = r8.kR;	 Catch:{ Throwable -> 0x00df }
                r2 = r2.m1888h(r0);	 Catch:{ Throwable -> 0x00df }
                if (r2 == 0) goto L_0x0149;
            L_0x00d3:
                r1 = r8.kR;	 Catch:{ Throwable -> 0x00df }
                r2 = "No Ads";
                r3 = r2;	 Catch:{ Throwable -> 0x00df }
                r1.m1874a(r2, r0, r3);	 Catch:{ Throwable -> 0x00df }
                goto L_0x0052;
            L_0x00df:
                r0 = move-exception;
                r1 = new java.lang.StringBuilder;
                r1.<init>();
                r2 = "finished custom after load with error ";
                r1 = r1.append(r2);
                r2 = com.appnext.core.C1128g.m2346b(r0);
                r1 = r1.append(r2);
                r1 = r1.toString();
                com.appnext.core.C1128g.m2333W(r1);
                r1 = r8.kR;
                r0 = r0.getMessage();
                r2 = r2;
                r1.m1879b(r0, r2);
                goto L_0x0052;
            L_0x0108:
                r0 = move-exception;
                monitor-exit(r2);	 Catch:{ all -> 0x0108 }
                throw r0;	 Catch:{ Throwable -> 0x00df }
            L_0x010b:
                r0 = move-exception;
                com.appnext.core.C1128g.m2351c(r0);	 Catch:{ Throwable -> 0x00df }
                r1 = r8.kR;	 Catch:{ Throwable -> 0x00df }
                r2 = "Timeout";
                r0 = com.appnext.core.C1128g.m2346b(r0);	 Catch:{ Throwable -> 0x00df }
                r3 = r2;	 Catch:{ Throwable -> 0x00df }
                r4 = 0;
                r1.m1875a(r2, r0, r3, r4);	 Catch:{ Throwable -> 0x00df }
                goto L_0x0052;
            L_0x0120:
                r0 = move-exception;
                com.appnext.core.C1128g.m2351c(r0);	 Catch:{ Throwable -> 0x00df }
                r1 = r8.kR;	 Catch:{ Throwable -> 0x00df }
                r2 = "Connection Error";
                r0 = com.appnext.core.C1128g.m2346b(r0);	 Catch:{ Throwable -> 0x00df }
                r3 = r2;	 Catch:{ Throwable -> 0x00df }
                r4 = 0;
                r1.m1875a(r2, r0, r3, r4);	 Catch:{ Throwable -> 0x00df }
                goto L_0x0052;
            L_0x0135:
                r0 = move-exception;
                com.appnext.core.C1128g.m2351c(r0);	 Catch:{ Throwable -> 0x00df }
                r1 = r8.kR;	 Catch:{ Throwable -> 0x00df }
                r2 = "Internal error";
                r0 = com.appnext.core.C1128g.m2346b(r0);	 Catch:{ Throwable -> 0x00df }
                r3 = r2;	 Catch:{ Throwable -> 0x00df }
                r1.m1874a(r2, r0, r3);	 Catch:{ Throwable -> 0x00df }
                goto L_0x0052;
            L_0x0149:
                r2 = r8.kR;	 Catch:{ Throwable -> 0x0165 }
                r3 = r3;	 Catch:{ Throwable -> 0x0165 }
                r4 = r2;	 Catch:{ Throwable -> 0x0165 }
                r5 = com.appnext.core.C0919c.kO;	 Catch:{ Throwable -> 0x0165 }
                r2 = r2.m1866a(r3, r4, r0, r5);	 Catch:{ Throwable -> 0x0165 }
                if (r2 != 0) goto L_0x0179;
            L_0x0159:
                r1 = r8.kR;	 Catch:{ Throwable -> 0x00df }
                r2 = "No Ads";
                r3 = r2;	 Catch:{ Throwable -> 0x00df }
                r1.m1874a(r2, r0, r3);	 Catch:{ Throwable -> 0x00df }
                goto L_0x0052;
            L_0x0165:
                r0 = move-exception;
                com.appnext.core.C1128g.m2351c(r0);	 Catch:{ Throwable -> 0x00df }
                r1 = r8.kR;	 Catch:{ Throwable -> 0x00df }
                r2 = "Internal error";
                r0 = com.appnext.core.C1128g.m2346b(r0);	 Catch:{ Throwable -> 0x00df }
                r3 = r2;	 Catch:{ Throwable -> 0x00df }
                r1.m1874a(r2, r0, r3);	 Catch:{ Throwable -> 0x00df }
                goto L_0x0052;
            L_0x0179:
                r0 = r2.size();	 Catch:{ Throwable -> 0x00df }
                if (r0 != 0) goto L_0x018b;
            L_0x017f:
                r0 = r8.kR;	 Catch:{ Throwable -> 0x00df }
                r1 = "No Ads";
                r2 = r2;	 Catch:{ Throwable -> 0x00df }
                r0.m1879b(r1, r2);	 Catch:{ Throwable -> 0x00df }
                goto L_0x0052;
            L_0x018b:
                r0 = r8.kR;	 Catch:{ Throwable -> 0x00df }
                r0 = r0.kP;	 Catch:{ Throwable -> 0x00df }
                r3 = r2;	 Catch:{ Throwable -> 0x00df }
                r0 = r0.get(r3);	 Catch:{ Throwable -> 0x00df }
                r0 = (com.appnext.core.C1104a) r0;	 Catch:{ Throwable -> 0x00df }
                r0.m2304h(r2);	 Catch:{ Throwable -> 0x00df }
                r0 = r6;	 Catch:{ Throwable -> 0x00df }
                if (r0 == 0) goto L_0x01ba;
            L_0x01a0:
                r2 = 3;
            L_0x01a1:
                if (r1 >= r2) goto L_0x01ba;
            L_0x01a3:
                r3 = r8.kR;	 Catch:{ Throwable -> 0x01e5 }
                r4 = r3;	 Catch:{ Throwable -> 0x01e5 }
                r5 = r2;	 Catch:{ Throwable -> 0x01e5 }
                r0 = r8.kR;	 Catch:{ Throwable -> 0x01e5 }
                r0 = r0.kP;	 Catch:{ Throwable -> 0x01e5 }
                r6 = r2;	 Catch:{ Throwable -> 0x01e5 }
                r0 = r0.get(r6);	 Catch:{ Throwable -> 0x01e5 }
                r0 = (com.appnext.core.C1104a) r0;	 Catch:{ Throwable -> 0x01e5 }
                r3.mo1974a(r4, r5, r0);	 Catch:{ Throwable -> 0x01e5 }
            L_0x01ba:
                r0 = r8.kR;	 Catch:{ Throwable -> 0x00df }
                r0 = r0.kP;	 Catch:{ Throwable -> 0x00df }
                r1 = r2;	 Catch:{ Throwable -> 0x00df }
                r0 = r0.get(r1);	 Catch:{ Throwable -> 0x00df }
                r0 = (com.appnext.core.C1104a) r0;	 Catch:{ Throwable -> 0x00df }
                r1 = 2;
                r0.setState(r1);	 Catch:{ Throwable -> 0x00df }
                r0 = new android.os.Handler;	 Catch:{ Throwable -> 0x00df }
                r1 = android.os.Looper.getMainLooper();	 Catch:{ Throwable -> 0x00df }
                r0.<init>(r1);	 Catch:{ Throwable -> 0x00df }
                r1 = new com.appnext.core.c$2$1;	 Catch:{ Throwable -> 0x00df }
                r1.<init>(r8);	 Catch:{ Throwable -> 0x00df }
                r0.post(r1);	 Catch:{ Throwable -> 0x00df }
                r0 = "finished loading ads";
                com.appnext.core.C1128g.m2333W(r0);
                goto L_0x0052;
            L_0x01e5:
                r0 = move-exception;
                if (r1 != r7) goto L_0x01e9;
            L_0x01e8:
                throw r0;	 Catch:{ Throwable -> 0x00df }
            L_0x01e9:
                r0 = r1 + 1;
                r1 = r0;
                goto L_0x01a1;
            L_0x01ed:
                r0 = move-exception;
                goto L_0x0011;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.appnext.core.c.2.run():void");
            }
        }.start();
    }

    protected boolean mo1980a(Ad ad) {
        try {
            return m1880b(ad) && m1882c(ad);
        } catch (Throwable th) {
            C1128g.m2351c(th);
            return false;
        }
    }

    protected boolean m1880b(Ad ad) {
        return (this.kP == null || !this.kP.containsKey(ad) || this.kP.get(ad) == null || ((C1104a) this.kP.get(ad)).getState() != 2 || ((C1104a) this.kP.get(ad)).cL() == null) ? false : true;
    }

    protected boolean m1882c(Ad ad) {
        boolean z = true;
        if (m1889i(ad) != 0) {
            if (this.kP == null || !this.kP.containsKey(ad) || ((C1104a) this.kP.get(ad)).cK().longValue() + m1889i(ad) <= System.currentTimeMillis()) {
                z = false;
            }
            return z;
        } else if (this.kP != null && this.kP.containsKey(ad) && ((C1104a) this.kP.get(ad)).cL().size() == 0) {
            return ((C1104a) this.kP.get(ad)).cK().longValue() + 60000 > System.currentTimeMillis();
        } else {
            return false;
        }
    }

    protected long m1889i(Ad ad) {
        try {
            return mo1981d(ad).get("_cachingRequest") == null ? m1858a(ad, "ads_caching_time_minutes") * 60000 : 1000 * m1858a(ad, "_cachingRequest");
        } catch (Throwable th) {
            return m1858a(ad, "ads_caching_time_minutes") * 60000;
        }
    }

    protected int m1891o() {
        return 8000;
    }

    private long m1858a(Ad ad, String str) {
        return Long.valueOf(mo1981d(ad).get(str)).longValue();
    }

    public void m1881c(Context context, Ad ad, String str) {
        if (this.kP.containsKey(ad)) {
            this.kP.remove(ad);
        }
        m1863b(context, ad, str, null, true);
    }

    protected ArrayList<? extends C0908i> m1866a(final Context context, Ad ad, String str, int i) throws JSONException {
        ArrayList arrayList = new ArrayList();
        final StringBuilder stringBuilder = new StringBuilder();
        JSONArray jSONArray = new JSONObject(str).getJSONArray("apps");
        int i2 = 0;
        while (i2 < jSONArray.length()) {
            JSONObject jSONObject = jSONArray.getJSONObject(i2);
            C1128g.m2333W(jSONObject.toString());
            try {
                Object obj = (AppnextAd) m1887g(jSONObject.toString());
                obj.setAdID(arrayList.size());
                obj.setPlacementID(ad.getPlacementID());
                if (mo1979a(context, (C0908i) obj)) {
                    AppnextAd b = m1862b(arrayList, (AppnextAd) obj);
                    if (b != null) {
                        arrayList.remove(b);
                        obj = m1859a(b, (AppnextAd) obj);
                    }
                    arrayList.add(obj);
                } else {
                    stringBuilder.append(obj.getBannerID()).append(",");
                }
                if (arrayList.size() == i) {
                    break;
                }
                i2++;
            } catch (Throwable th) {
                C1128g.m2351c(th);
            }
        }
        new Thread(new Runnable(this) {
            final /* synthetic */ C0919c kR;

            public void run() {
                try {
                    String stringBuilder = stringBuilder.toString();
                    if (stringBuilder.length() != 0) {
                        HashMap hashMap = new HashMap();
                        String u = C1128g.m2358u(context);
                        if (!u.equals("")) {
                            hashMap.put("aid", u);
                            hashMap.put("bids", stringBuilder.substring(0, stringBuilder.length() - 1));
                            C1128g.m2336a("https://admin.appnext.com/AdminService.asmx/bns", hashMap);
                        }
                    }
                } catch (Throwable th) {
                    C1128g.m2351c(th);
                }
            }
        }).start();
        return arrayList;
    }

    private AppnextAd m1859a(AppnextAd appnextAd, AppnextAd appnextAd2) {
        if (appnextAd.getRevenueType().equals(appnextAd2.getRevenueType())) {
            if (Float.parseFloat(appnextAd.getRevenueRate()) < Float.parseFloat(appnextAd2.getRevenueRate())) {
                return appnextAd2;
            }
            return appnextAd;
        } else if (appnextAd.getRevenueType().equals("cpc")) {
            return appnextAd;
        } else {
            return appnextAd2;
        }
    }

    private AppnextAd m1862b(ArrayList<AppnextAd> arrayList, AppnextAd appnextAd) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            AppnextAd appnextAd2 = (AppnextAd) it.next();
            if (appnextAd2.getAdPackage().equals(appnextAd.getAdPackage())) {
                return appnextAd2;
            }
        }
        return null;
    }

    protected boolean m1888h(String str) {
        try {
            return new JSONObject(str).has("rnd");
        } catch (Throwable th) {
            return true;
        }
    }

    protected String mo1998d(ArrayList<AppnextAd> arrayList) {
        try {
            JSONArray jSONArray = new JSONArray();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                jSONArray.put(new JSONObject(((AppnextAd) it.next()).getAdJSON()));
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("apps", jSONArray);
            return jSONObject.toString().replace(" ", "\\u2028").replace(" ", "\\u2029");
        } catch (Throwable th) {
            return "";
        }
    }

    protected void m1879b(String str, Ad ad) {
        m1874a(str, "", ad);
    }

    protected void m1874a(String str, String str2, Ad ad) {
        m1875a(str, str2, ad, 2);
    }

    protected void m1875a(String str, String str2, Ad ad, int i) {
        final Ad ad2 = ad;
        final int i2 = i;
        final String str3 = str;
        final String str4 = str2;
        new Handler(Looper.getMainLooper()).post(new Runnable(this) {
            final /* synthetic */ C0919c kR;

            public void run() {
                C1104a c1104a = (C1104a) this.kR.kP.get(ad2);
                if (c1104a != null) {
                    if (c1104a.cL() == null) {
                        c1104a.m2304h(new ArrayList());
                    } else {
                        c1104a.m2304h(c1104a.cL());
                    }
                    c1104a.setState(i2);
                    c1104a.aI(str3);
                    this.kR.mo1975a(ad2, str3 + " " + str4, c1104a.getPlacementID());
                }
            }
        });
    }

    protected C1104a m1890j(Ad ad) {
        return (C1104a) this.kP.get(ad);
    }

    protected HashMap<Ad, C1104a> cM() {
        return this.kP;
    }

    protected void m1870a(Ad ad, C1104a c1104a) {
        this.kP.put(ad, c1104a);
    }

    public String m1885d(AppnextAd appnextAd) {
        return appnextAd.getAdJSON();
    }

    public C0908i m1887g(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            AppnextAd appnextAd = (AppnextAd) C1135m.m2376a(AppnextAd.class, jSONObject);
            if (appnextAd == null) {
                return appnextAd;
            }
            appnextAd.setAdJSON(jSONObject.toString());
            if (!appnextAd.getStoreRating().equals("")) {
                return appnextAd;
            }
            appnextAd.setStoreRating(SchemaSymbols.ATTVAL_FALSE_0);
            return appnextAd;
        } catch (Throwable th) {
            return null;
        }
    }

    protected boolean m1883c(String str, String str2) {
        return C1134l.db().m2374r(str, str2);
    }

    protected void m1864U(String str) {
        C1134l.db().aS(str);
    }

    protected void mo1976a(String str, Ad ad) {
        C1134l.db().m2373q(str, ad.getPlacementID());
    }
}
