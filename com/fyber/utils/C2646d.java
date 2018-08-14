package com.fyber.utils;

import com.mopub.common.AdType;
import java.util.HashMap;
import java.util.Map;

/* compiled from: FyberBaseUrlProvider */
public final class C2646d {
    private static C2646d f6587a = new C2646d();
    private C2681u f6588b;
    private Map<String, String> f6589c = new C26451(this);

    /* compiled from: FyberBaseUrlProvider */
    class C26451 extends HashMap<String, String> {
        final /* synthetic */ C2646d f6586a;

        C26451(C2646d c2646d) {
            this.f6586a = c2646d;
            put("actions", "https://service.fyber.com/actions/v2");
            put("installs", "https://service.fyber.com/installs/v2");
            put("vcs", "https://api.fyber.com/vcs/v1/new_credit.json");
            put("videos", "https://video.fyber.com");
            put("rewarded_video_tracking", "https://rewarded-video.fyber.com/tracker");
            put("ofw", "https://offer.fyber.com/mobile");
            put("interstitial", "https://interstitial.fyber.com/interstitial");
            put("interstitial_tracking", "https://interstitial.fyber.com/tracker");
            put("config", "https://sdk-config.fyber.com");
            put("precaching", "https://engine.fyber.com/video-cache");
            put("banner", "https://banner.fyber.com/banner");
            put("banner_tracking", "https://banner.fyber.com/tracker");
            put("testsuite_tracking", "https://testsuite.fyber.com/tracker");
            put(AdType.REWARDED_VIDEO, "https://api-rewarded-video.fyber.com/video");
        }
    }

    private C2646d() {
    }

    public static String m8469a(String str) {
        C2646d c2646d = f6587a;
        String str2 = null;
        if (c2646d.f6588b != null) {
            str2 = c2646d.f6588b.m8581a();
        }
        return StringUtils.nullOrEmpty(str2) ? (String) c2646d.f6589c.get(str) : str2;
    }
}
