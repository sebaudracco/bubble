package com.appnext.banners;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.Pair;
import com.appnext.core.Ad;
import com.appnext.core.AppnextAd;
import com.appnext.core.AppnextError;
import com.appnext.core.C0908i;
import com.appnext.core.C0919c;
import com.appnext.core.C0919c.C0914a;
import com.appnext.core.C0921q;
import com.appnext.core.C1104a;
import com.appnext.core.C1128g;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import org.json.JSONArray;

class C1007b extends C0919c {
    private static C1007b fE;
    private final int dL = 50;

    public static synchronized C1007b aE() {
        C1007b c1007b;
        synchronized (C1007b.class) {
            if (fE == null) {
                fE = new C1007b();
            }
            c1007b = fE;
        }
        return c1007b;
    }

    private C1007b() {
    }

    protected String mo1973a(Context context, Ad ad, String str, ArrayList<Pair<String, String>> arrayList) {
        return "https://global.appnext.com" + "/offerWallApi.aspx?ext=t&pimp=1&igroup=sdk&m=1&osid=100&auid=" + (ad != null ? ad.getAUID() : "1000") + "&type=json&id=" + str + "&cnt=" + 50 + "&tid=" + (ad != null ? ad.getTID() : "301") + "&vid=" + (ad != null ? ad.getVID() : "2.2.5.468") + "&cat=" + (ad != null ? ad.getCategories() : "") + "&pbk=" + (ad != null ? ad.getPostback() : "") + "&did=" + C1128g.m2358u(context) + "&devn=" + C1128g.cV() + "&dosv=" + VERSION.SDK_INT + "&dct=" + C1128g.aP(C1128g.m2361x(context)) + "&lang=" + C1128g.cv() + "&dcc=" + C1128g.m2362y(context) + "&dds=" + ((int) C1128g.cX()) + "&packageId=" + context.getPackageName() + "&rnd=" + new Random().nextInt();
    }

    public void m2044a(Context context, Ad ad, String str, C0914a c0914a, BannerAdRequest bannerAdRequest) {
        ((BannerAd) ad).setAdRequest(new BannerAdRequest(bannerAdRequest));
        super.m1868a(context, ad, str, c0914a);
    }

    protected void mo1974a(Context context, Ad ad, C1104a c1104a) throws Exception {
        AppnextAd a = m2040a(context, ad, ((BannerAdRequest) ((BannerAd) ad).getAdRequest()).getCreativeType());
        if (a == null) {
            throw new Exception(AppnextError.NO_ADS);
        }
        C1128g.aO(a.getImageURL());
        if (ad instanceof MediumRectangleAd) {
            C1128g.aO(a.getWideImageURL());
        }
    }

    protected boolean mo1979a(Context context, C0908i c0908i) {
        BannerAdData bannerAdData = new BannerAdData((AppnextAd) c0908i);
        if (!m2037a(context, bannerAdData)) {
            return false;
        }
        if (bannerAdData.getCampaignGoal().equals("new") && C1128g.m2356h(context, bannerAdData.getAdPackage())) {
            return false;
        }
        if (!bannerAdData.getCampaignGoal().equals("existing") || C1128g.m2356h(context, bannerAdData.getAdPackage())) {
            return C1007b.m2039c((AppnextAd) c0908i);
        }
        return false;
    }

    protected void mo1975a(Ad ad, String str, String str2) {
        C1128g.m2333W("error " + str);
    }

    protected <T> void mo1977a(String str, Ad ad, T t) {
    }

    protected C0921q mo1981d(Ad ad) {
        return C1008c.aF();
    }

    protected boolean mo1978a(Context context, Ad ad, ArrayList<?> arrayList) {
        return m2041a(context, ad, (ArrayList) arrayList, ((BannerAdRequest) ((BannerAd) ad).getAdRequest()).getCreativeType()) != null;
    }

    protected AppnextAd m2041a(Context context, Ad ad, ArrayList<?> arrayList, String str) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            AppnextAd appnextAd = (AppnextAd) it.next();
            if (m2038a(appnextAd, str) && !m1883c(appnextAd.getBannerID(), ad.getPlacementID())) {
                return appnextAd;
            }
        }
        return null;
    }

    protected AppnextAd m2040a(Context context, Ad ad, String str) {
        if (m1890j(ad) == null) {
            return null;
        }
        ArrayList cL = m1890j(ad).cL();
        if (cL != null) {
            return m2041a(context, ad, cL, str);
        }
        return null;
    }

    private boolean m2038a(AppnextAd appnextAd, String str) {
        boolean z = true;
        switch (str.hashCode()) {
            case -892481938:
                if (str.equals("static")) {
                    z = true;
                    break;
                }
                break;
            case 96673:
                if (str.equals(BannerAdRequest.TYPE_ALL)) {
                    z = false;
                    break;
                }
                break;
            case 112202875:
                if (str.equals("video")) {
                    z = true;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                if (C1007b.m2039c(appnextAd) || C1007b.hasVideo(appnextAd)) {
                    return true;
                }
                return false;
            case true:
                return C1007b.m2039c(appnextAd);
            case true:
                return C1007b.hasVideo(appnextAd);
            default:
                return false;
        }
    }

    private boolean m2037a(Context context, BannerAdData bannerAdData) {
        if (bannerAdData.getCptList().equals("") || bannerAdData.getCptList().equals("[]")) {
            return true;
        }
        try {
            JSONArray jSONArray = new JSONArray(bannerAdData.getCptList());
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

    static boolean hasVideo(AppnextAd appnextAd) {
        return (appnextAd.getVideoUrl().equals("") && appnextAd.getVideoUrlHigh().equals("") && appnextAd.getVideoUrl30Sec().equals("") && appnextAd.getVideoUrlHigh30Sec().equals("")) ? false : true;
    }

    static boolean m2039c(AppnextAd appnextAd) {
        return !appnextAd.getWideImageURL().equals("");
    }
}
