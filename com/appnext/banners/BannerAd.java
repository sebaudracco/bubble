package com.appnext.banners;

import android.content.Context;
import com.appnext.core.Ad;
import com.appnext.core.C0987b;
import com.appnext.core.C1128g;
import com.appnext.core.callbacks.OnECPMLoaded;
import org.json.JSONObject;

class BannerAd extends Ad {
    protected static final String TID = "301";
    protected static final String VID = "2.2.5.468";
    boolean fq_status = false;

    class C09861 implements Runnable {
        final /* synthetic */ BannerAd fr;

        C09861(BannerAd bannerAd) {
            this.fr = bannerAd;
        }

        public void run() {
            try {
                String a = C1128g.m2336a("https://admin.appnext.com/AdminService.asmx/checkA?z=" + this.fr.getPlacementID() + "&callback=", null);
                BannerAd.fq = new JSONObject(a.substring(a.indexOf("(") + 1, a.lastIndexOf(")"))).getBoolean("status");
                this.fr.fq_status = BannerAd.fq;
                C1128g.m2333W("fq " + this.fr.fq_status);
                BannerAd.checked_fq = true;
            } catch (Throwable th) {
                C1128g.m2351c(th);
            }
        }
    }

    public BannerAd(Context context, String str) {
        super(context, str);
        if (checked_fq) {
            this.fq_status = Ad.fq;
        } else {
            new Thread(new C09861(this)).start();
        }
    }

    protected BannerAd(Ad ad) {
        super(ad);
    }

    public void showAd() {
    }

    public void loadAd() {
    }

    public boolean isAdLoaded() {
        return false;
    }

    public String getVID() {
        return "2.2.5.468";
    }

    public String getTID() {
        return TID;
    }

    public String getAUID() {
        return "1000";
    }

    public void getECPM(OnECPMLoaded onECPMLoaded) {
    }

    protected String getSessionId() {
        return super.getSessionId();
    }

    protected void setAdRequest(C0987b c0987b) {
        super.setAdRequest(c0987b);
    }

    protected C0987b getAdRequest() {
        return super.getAdRequest();
    }
}
