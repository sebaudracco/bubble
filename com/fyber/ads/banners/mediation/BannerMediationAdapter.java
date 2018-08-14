package com.fyber.ads.banners.mediation;

import android.content.Context;
import android.support.annotation.NonNull;
import com.fyber.ads.banners.BannerSize;
import com.fyber.mediation.AdFormatMediationAdapter;
import com.fyber.mediation.MediationAdapter;
import com.fyber.mediation.p108b.C2580a;
import com.fyber.utils.FyberLogger;
import java.util.List;

public abstract class BannerMediationAdapter<V extends MediationAdapter> extends AdFormatMediationAdapter<BannerWrapper, C2422a> {
    protected V mAdapter;

    protected abstract boolean checkForAds(Context context, List<BannerSize> list);

    public BannerMediationAdapter(V v) {
        this.mAdapter = v;
    }

    protected void setAdAvailable(BannerWrapper bannerWrapper) {
        if (this.providerRequesterListener != null) {
            this.providerRequesterListener.setAdAvailable(bannerWrapper, this.request);
        } else {
            FyberLogger.m8451i("BannerMediationAdapter", "No provider request listener");
        }
    }

    protected void setAdNotAvailable() {
        if (this.providerRequesterListener != null) {
            this.providerRequesterListener.setAdNotAvailable(this.request);
        } else {
            FyberLogger.m8451i("BannerMediationAdapter", "No provider request listener");
        }
    }

    protected void setAdError(String str) {
        if (this.providerRequesterListener != null) {
            this.providerRequesterListener.setAdError(new C2422a(str), this.request);
        } else {
            FyberLogger.m8451i("BannerMediationAdapter", "No provider request listener");
        }
    }

    public void isAdAvailable(@NonNull Context context, @NonNull C2580a c2580a) {
        this.request = c2580a;
        if (!checkForAds(context, (List) c2580a.mo3970a("BANNER_SIZES"))) {
            if (this.providerRequesterListener != null) {
                this.providerRequesterListener.setAdError(new C2422a("Unable to perform the request"), this.request);
            } else {
                FyberLogger.m8451i("BannerMediationAdapter", "No provider request listener");
            }
        }
    }
}
