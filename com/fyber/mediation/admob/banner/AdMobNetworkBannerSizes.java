package com.fyber.mediation.admob.banner;

import com.fyber.ads.banners.BannerSize;
import com.fyber.ads.banners.BannerSize.Builder;
import com.fyber.ads.banners.NetworkBannerSize;
import com.fyber.mediation.annotations.MediationNetworkBannerSize;
import cz.msebera.android.httpclient.HttpStatus;

public class AdMobNetworkBannerSizes {
    @MediationNetworkBannerSize("AdMob")
    public static final NetworkBannerSize BANNER = new NetworkBannerSize("AdMob", BannerSize.FIXED_SIZE_320_50);
    @MediationNetworkBannerSize("AdMob")
    public static final NetworkBannerSize FULL_BANNER = new NetworkBannerSize("AdMob", Builder.newBuilder().withWidth(468).withHeight(60).build());
    @MediationNetworkBannerSize("AdMob")
    public static final NetworkBannerSize LARGE_BANNER = new NetworkBannerSize("AdMob", Builder.newBuilder().withWidth(320).withHeight(100).build());
    @MediationNetworkBannerSize("AdMob")
    public static final NetworkBannerSize LEADERBOARD = new NetworkBannerSize("AdMob", Builder.newBuilder().withWidth(728).withHeight(90).build());
    @MediationNetworkBannerSize("AdMob")
    public static final NetworkBannerSize MEDIUM_RECTANGLE = new NetworkBannerSize("AdMob", Builder.newBuilder().withWidth(HttpStatus.SC_MULTIPLE_CHOICES).withHeight(250).build());
    @MediationNetworkBannerSize("AdMob")
    public static final NetworkBannerSize SMART_BANNER = new NetworkBannerSize("AdMob", BannerSize.SMART_SIZE);
}
