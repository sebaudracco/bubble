package com.appnext.sdk.adapters.mopub.banners;

import com.appnext.banners.BannerSize;
import java.util.Map;

public class Helper {
    protected static final String AppnextAutoPlayExtraKey = "AppnextAutoPlay";
    protected static final String AppnextBackButtonCanCloseExtraKey = "AppnextBackButtonCanClose";
    protected static final String AppnextBannerSizeExtraKey = "AppnextBannerSize";
    protected static final String AppnextButtonColorExtraKey = "AppnextButtonColor";
    protected static final String AppnextCategoriesExtraKey = "AppnextCategories";
    protected static final String AppnextClickEnabledExtraKey = "AppnextClickEnabled";
    protected static final String AppnextCreativeTypeExtraKey = "AppnextCreativeType";
    protected static final String AppnextMaxVideoLenExtraKey = "AppnextMaxVideoLen";
    protected static final String AppnextMinVideoLenExtraKey = "AppnextMinVideoLen";
    protected static final String AppnextMuteExtraKey = "AppnextMute";
    protected static final String AppnextOrientationExtraKey = "AppnextOrientation";
    protected static final String AppnextPlacementIdExtraKey = "AppnextPlacementId";
    protected static final String AppnextPostbackExtraKey = "AppnextPostback";
    protected static final String AppnextVideoLengthExtraKey = "AppnextVideoLength";

    protected static String getAppnextPlacementIdExtraKey(Map<String, String> map) {
        if (map != null) {
            return (String) map.get(AppnextPlacementIdExtraKey);
        }
        return null;
    }

    protected static BannerSize getBannerSize(Map<String, String> map) {
        if (map == null) {
            return BannerSize.BANNER;
        }
        String str = (String) map.get(AppnextBannerSizeExtraKey);
        if (str.equals("LARGE_BANNER")) {
            return BannerSize.LARGE_BANNER;
        }
        if (str.equals("MEDIUM_RECTANGLE")) {
            return BannerSize.MEDIUM_RECTANGLE;
        }
        if (str.equals("BANNER")) {
            return BannerSize.BANNER;
        }
        throw new IllegalArgumentException("Wrong size");
    }

    protected static boolean hasAdConfigServerExtras(Map<String, String> map) {
        if (map == null) {
            return false;
        }
        if (map.containsKey(AppnextButtonColorExtraKey) || map.containsKey(AppnextCategoriesExtraKey) || map.containsKey(AppnextPostbackExtraKey) || map.containsKey(AppnextOrientationExtraKey) || map.containsKey(AppnextMinVideoLenExtraKey) || map.containsKey(AppnextMaxVideoLenExtraKey) || map.containsKey(AppnextBackButtonCanCloseExtraKey) || map.containsKey(AppnextMuteExtraKey)) {
            return true;
        }
        return false;
    }
}
