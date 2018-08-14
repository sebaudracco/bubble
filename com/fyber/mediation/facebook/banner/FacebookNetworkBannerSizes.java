package com.fyber.mediation.facebook.banner;

import com.fyber.ads.banners.NetworkBannerSize;
import com.fyber.mediation.annotations.MediationNetworkBannerSize;

public class FacebookNetworkBannerSizes {
    @MediationNetworkBannerSize("FacebookAudienceNetwork")
    public static final NetworkBannerSize BANNER_50 = new NetworkBannerSize("FacebookAudienceNetwork", FacebookBannerSize.HEIGHT_50);
    @MediationNetworkBannerSize("FacebookAudienceNetwork")
    public static final NetworkBannerSize BANNER_90 = new NetworkBannerSize("FacebookAudienceNetwork", FacebookBannerSize.HEIGHT_90);
    @MediationNetworkBannerSize("FacebookAudienceNetwork")
    public static final NetworkBannerSize RECTANGLE_HEIGHT_250 = new NetworkBannerSize("FacebookAudienceNetwork", FacebookBannerSize.RECTANGLE_300_250);
}
