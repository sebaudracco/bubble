package com.appsgeyser.sdk.ads.behavior;

import com.appsgeyser.sdk.ads.behavior.bannerBehaviors.BannerHeightBehavior;
import com.appsgeyser.sdk.ads.behavior.bannerBehaviors.BannerWidthBehavior;
import com.appsgeyser.sdk.ads.behavior.loaderBehaviors.LoaderClickBehavior;
import com.appsgeyser.sdk.ads.behavior.loaderBehaviors.LoaderHideTimeoutBehavior;
import com.appsgeyser.sdk.ads.behavior.loaderBehaviors.LoaderRefreshTimeoutBehavior;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BehaviorFactory {
    private static final String BANNER_HEIGHT_HEADER = "APPAD-BannerHeight";
    private static final String BANNER_WIDTH_HEADER = "APPAD-BannerWidth";
    private static final String CLICK_BEHAVIOR_HEADER = "APPAD-ClickBehaviour";
    private static final String HIDE_TIMEOUT_HEADER = "APPAD-HideTimeout";
    private static final String REFRESH_TIMEOUT_HEADER = "APPAD-RefreshTimeout";
    private static final String REMAIN_ON_SCREEN_VALUE = "remainOnScreen";
    private final String[] POSTLOAD_BEHAVIORS = new String[]{HIDE_TIMEOUT_HEADER, BANNER_WIDTH_HEADER, BANNER_HEIGHT_HEADER, CLICK_BEHAVIOR_HEADER, REFRESH_TIMEOUT_HEADER};

    public enum ClickBehavior {
        HIDE,
        REMAIN_ON_SCREEN
    }

    public List<BehaviorVisitor> createPostloadBehaviors(Map<String, List<String>> responseHeaders) {
        return createBehaviors(this.POSTLOAD_BEHAVIORS, responseHeaders);
    }

    private List<BehaviorVisitor> createBehaviors(String[] types, Map<String, List<String>> responseHeaders) {
        ArrayList<BehaviorVisitor> resultSet = new ArrayList();
        if (responseHeaders != null) {
            for (Entry<String, List<String>> element : responseHeaders.entrySet()) {
                String key = (String) element.getKey();
                if (key != null && isInArray(types, key)) {
                    BehaviorVisitor visitor = createVisitor(element);
                    if (visitor != null) {
                        resultSet.add(visitor);
                    }
                }
            }
        }
        return resultSet;
    }

    private boolean isInArray(String[] haystack, String needle) {
        for (String s : haystack) {
            if (s.toLowerCase().equals(needle.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    private BehaviorVisitor createVisitor(Entry<String, List<String>> header) {
        try {
            String headerKey = ((String) header.getKey()).toLowerCase();
            String headerValue = ((String) ((List) header.getValue()).get(0)).toLowerCase();
            if (headerKey.equalsIgnoreCase(HIDE_TIMEOUT_HEADER)) {
                return new LoaderHideTimeoutBehavior(Float.parseFloat(headerValue));
            }
            if (headerKey.equalsIgnoreCase(BANNER_WIDTH_HEADER)) {
                return new BannerWidthBehavior(Integer.parseInt(headerValue));
            }
            if (headerKey.equalsIgnoreCase(BANNER_HEIGHT_HEADER)) {
                return new BannerHeightBehavior(Integer.parseInt(headerValue));
            }
            if (headerKey.equalsIgnoreCase(CLICK_BEHAVIOR_HEADER)) {
                ClickBehavior clickBehavior = ClickBehavior.HIDE;
                if (headerValue.equalsIgnoreCase(REMAIN_ON_SCREEN_VALUE)) {
                    clickBehavior = ClickBehavior.REMAIN_ON_SCREEN;
                }
                return new LoaderClickBehavior(clickBehavior);
            } else if (headerKey.equalsIgnoreCase(REFRESH_TIMEOUT_HEADER)) {
                return new LoaderRefreshTimeoutBehavior(Float.parseFloat(headerValue));
            } else {
                return null;
            }
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
