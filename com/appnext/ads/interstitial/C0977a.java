package com.appnext.ads.interstitial;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.Pair;
import com.appnext.core.Ad;
import com.appnext.core.AppnextAd;
import com.appnext.core.C0908i;
import com.appnext.core.C0919c;
import com.appnext.core.C0919c.C0914a;
import com.appnext.core.C0921q;
import com.appnext.core.C1104a;
import com.appnext.core.C1128g;
import com.appnext.core.webview.AppnextWebView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import org.json.JSONArray;

public class C0977a extends C0919c {
    private static final int dL = 30;
    private static C0977a fc;
    private HashMap<Ad, String> dM = new HashMap();
    private String eO;

    public static synchronized C0977a au() {
        C0977a c0977a;
        synchronized (C0977a.class) {
            if (fc == null) {
                fc = new C0977a();
            }
            c0977a = fc;
        }
        return c0977a;
    }

    private C0977a() {
    }

    protected String mo1973a(Context context, Ad ad, String str, ArrayList<Pair<String, String>> arrayList) {
        String str2;
        Object obj;
        StringBuilder append = new StringBuilder().append("https://global.appnext.com").append("/offerWallApi.aspx?ext=t&pimp=1&type=json&igroup=sdk&m=1&osid=100&auid=").append(ad != null ? ad.getAUID() : "600").append("&id=").append(str).append("&cnt=").append(30).append("&vid=").append(ad != null ? ad.getVID() : "2.2.5.468").append("&tid=").append(ad != null ? ad.getTID() : "301").append("&cat=");
        if (ad == null) {
            str2 = "";
        } else {
            str2 = ad.getCategories();
        }
        append = append.append(str2).append("&pbk=");
        if (ad == null) {
            str2 = "";
        } else {
            str2 = ad.getPostback();
        }
        append = append.append(str2).append("&vidmin=");
        if (ad == null) {
            obj = "";
        } else {
            obj = Integer.valueOf(ad.getMinVideoLength());
        }
        append = append.append(obj).append("&vidmax=");
        if (ad == null) {
            obj = "";
        } else {
            obj = Integer.valueOf(ad.getMaxVideoLength());
        }
        return append.append(obj).append("&did=").append(C1128g.m2358u(context)).append("&devn=").append(C1128g.cV()).append("&dosv=").append(VERSION.SDK_INT).append("&dct=").append(C1128g.aP(C1128g.m2361x(context))).append("&lang=").append(C1128g.cv()).append("&dcc=").append(C1128g.m2362y(context)).append("&dds=").append((int) C1128g.cX()).append(this.eO.equals("static") ? "&creative=0" : "").append("&packageId=").append(context.getPackageName()).append("&rnd=").append(new Random().nextInt()).toString();
    }

    public void m2020a(Context context, Ad ad, String str, C0914a c0914a, String str2) {
        this.eO = str2;
        super.m1868a(context, ad, str, c0914a);
    }

    protected void mo1974a(Context context, Ad ad, C1104a c1104a) throws Exception {
        AppnextWebView.m2400D(context).m2410a(((Interstitial) ad).getPageUrl(), null);
    }

    protected boolean mo1979a(Context context, C0908i c0908i) {
        return m2013a(context, (AppnextAd) c0908i) && m2015b(context, (AppnextAd) c0908i);
    }

    protected void mo1975a(Ad ad, String str, String str2) {
        C1128g.m2333W("error " + str);
    }

    protected <T> void mo1977a(String str, Ad ad, T t) {
    }

    protected boolean mo1978a(Context context, Ad ad, ArrayList<?> arrayList) {
        return m2017a(context, (ArrayList) arrayList, ((Interstitial) ad).getCreativeType(), ad) != null;
    }

    protected C0921q mo1981d(Ad ad) {
        return C0980c.ax();
    }

    protected boolean m2029e(Ad ad) {
        return m1880b(ad) && m1890j(ad).cL() != null && m1890j(ad).cL().size() > 0;
    }

    protected ArrayList<AppnextAd> m2026b(Context context, Ad ad, String str) {
        if (m1890j(ad) == null) {
            return null;
        }
        ArrayList cL = m1890j(ad).cL();
        if (cL == null) {
            return null;
        }
        AppnextAd a = m2017a(context, cL, str, ad);
        if (a != null) {
            return m2012a(cL, a);
        }
        return null;
    }

    private ArrayList<AppnextAd> m2012a(ArrayList<AppnextAd> arrayList, AppnextAd appnextAd) {
        arrayList.remove(appnextAd);
        arrayList.add(0, appnextAd);
        return arrayList;
    }

    protected String mo1998d(ArrayList<AppnextAd> arrayList) {
        return super.mo1998d((ArrayList) arrayList);
    }

    protected void mo1976a(String str, Ad ad) {
        super.mo1976a(str, ad);
        if (this.dM.containsKey(ad)) {
            this.dM.remove(ad);
        }
    }

    private boolean hasVideo(AppnextAd appnextAd) {
        return (appnextAd.getVideoUrl().equals("") && appnextAd.getVideoUrlHigh().equals("") && appnextAd.getVideoUrl30Sec().equals("") && appnextAd.getVideoUrlHigh30Sec().equals("")) ? false : true;
    }

    private boolean m2016c(AppnextAd appnextAd) {
        return !appnextAd.getWideImageURL().equals("");
    }

    protected AppnextAd m2017a(Context context, ArrayList<AppnextAd> arrayList, String str, Ad ad) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            AppnextAd appnextAd = (AppnextAd) it.next();
            if (m2014a(appnextAd, str, ad)) {
                return appnextAd;
            }
        }
        return null;
    }

    private boolean m2014a(AppnextAd appnextAd, String str, Ad ad) {
        boolean z = true;
        switch (str.hashCode()) {
            case -892481938:
                if (str.equals("static")) {
                    z = true;
                    break;
                }
                break;
            case 112202875:
                if (str.equals("video")) {
                    z = true;
                    break;
                }
                break;
            case 835260319:
                if (str.equals(Interstitial.TYPE_MANAGED)) {
                    z = false;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                if ((m2016c(appnextAd) || hasVideo(appnextAd)) && !m1883c(appnextAd.getBannerID(), ad.getPlacementID())) {
                    return true;
                }
            case true:
                if (hasVideo(appnextAd) && !m1883c(appnextAd.getBannerID(), ad.getPlacementID())) {
                    return true;
                }
            case true:
                if (m2016c(appnextAd) && !m1883c(appnextAd.getBannerID(), ad.getPlacementID())) {
                    return true;
                }
        }
        return false;
    }

    private boolean m2013a(Context context, AppnextAd appnextAd) {
        InterstitialAd interstitialAd = new InterstitialAd(appnextAd);
        if (interstitialAd.getCampaignGoal().equals("new") && C1128g.m2356h(context, interstitialAd.getAdPackage())) {
            return false;
        }
        if (!interstitialAd.getCampaignGoal().equals("existing") || C1128g.m2356h(context, interstitialAd.getAdPackage())) {
            return true;
        }
        return false;
    }

    private boolean m2015b(Context context, AppnextAd appnextAd) {
        InterstitialAd interstitialAd = new InterstitialAd(appnextAd);
        if (interstitialAd.getCptList().equals("") || interstitialAd.getCptList().equals("[]")) {
            return true;
        }
        try {
            JSONArray jSONArray = new JSONArray(interstitialAd.getCptList());
            for (int i = 0; i < jSONArray.length(); i++) {
                if (C1128g.m2356h(context, jSONArray.getString(i))) {
                    return true;
                }
            }
            return false;
        } catch (Throwable e) {
            C1128g.m2351c(e);
            return true;
        }
    }
}
