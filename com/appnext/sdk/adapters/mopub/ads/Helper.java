package com.appnext.sdk.adapters.mopub.ads;

import android.util.Log;
import com.appnext.ads.fullscreen.FullscreenConfig;
import com.appnext.ads.fullscreen.RewardedConfig;
import com.appnext.ads.fullscreen.VideoConfig;
import com.appnext.banners.BannerSize;
import com.appnext.core.Configuration;
import java.util.Map;

public class Helper {
    protected static String getAppnextPlacementIdExtraKey(Map<String, String> map) {
        if (map != null) {
            return (String) map.get("AppnextPlacementId");
        }
        return null;
    }

    protected static BannerSize getBannerSize(Map<String, String> map) {
        if (map == null) {
            return BannerSize.BANNER;
        }
        String str = (String) map.get("AppnextBannerSize");
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
        if (map.containsKey("AppnextButtonColor") || map.containsKey("AppnextCategories") || map.containsKey("AppnextPostback") || map.containsKey("AppnextOrientation") || map.containsKey("AppnextMinVideoLen") || map.containsKey("AppnextMaxVideoLen") || map.containsKey("AppnextBackButtonCanClose") || map.containsKey("AppnextMute")) {
            return true;
        }
        return false;
    }

    protected static boolean hasVideoConfigServerExtras(Map<String, String> map) {
        if (map == null) {
            return false;
        }
        if (map.containsKey("AppnextProgressType") || map.containsKey("AppnextProgressColor") || map.containsKey("AppnextVideoLength") || map.containsKey("AppnextShowClose") || map.containsKey("AppnextCloseDelay")) {
            return true;
        }
        return false;
    }

    protected static void setConfigFromExtras(Configuration configuration, Map<String, String> map) {
        if (configuration != null && map != null) {
            if (map.containsKey("AppnextCategories")) {
                try {
                    configuration.setCategories((String) map.get("AppnextCategories"));
                } catch (Throwable th) {
                    Log.e("AppnextMoPub", "setCategories: " + th.getMessage());
                }
            }
            if (map.containsKey("AppnextPostback")) {
                try {
                    configuration.setPostback((String) map.get("AppnextPostback"));
                } catch (Throwable th2) {
                    Log.e("AppnextMoPub", "setPostback: " + th2.getMessage());
                }
            }
            if (map.containsKey("AppnextOrientation")) {
                try {
                    configuration.setOrientation((String) map.get("AppnextOrientation"));
                } catch (Throwable th22) {
                    Log.e("AppnextMoPub", "setOrientation: " + th22.getMessage());
                }
            }
            if (map.containsKey("AppnextMinVideoLen")) {
                try {
                    configuration.setMinVideoLength(Integer.parseInt((String) map.get("AppnextMinVideoLen")));
                } catch (Throwable th222) {
                    Log.e("AppnextMoPub", "setMinVideoLength: " + th222.getMessage());
                }
            }
            if (map.containsKey("AppnextMaxVideoLen")) {
                try {
                    configuration.setMaxVideoLength(Integer.parseInt((String) map.get("AppnextMaxVideoLen")));
                } catch (Throwable th2222) {
                    Log.e("AppnextMoPub", "setMaxVideoLength: " + th2222.getMessage());
                }
            }
            if (map.containsKey("AppnextMute")) {
                try {
                    configuration.setMute(Boolean.parseBoolean((String) map.get("AppnextMute")));
                } catch (Throwable th22222) {
                    Log.e("AppnextMoPub", "setMute: " + th22222.getMessage());
                }
            }
        }
    }

    protected static void setVideoConfigFromExtras(VideoConfig videoConfig, Map<String, String> map) {
        if (videoConfig != null && map != null && map.containsKey("AppnextVideoLength")) {
            try {
                videoConfig.setVideoLength((String) map.get("AppnextVideoLength"));
            } catch (Throwable th) {
                Log.e("AppnextMoPub", "setVideoLength: " + th.getMessage());
            }
        }
    }

    protected static void setRewardedVideoConfigFromExtras(RewardedConfig rewardedConfig, Map<String, String> map) {
        if (rewardedConfig != null && map != null) {
            if (map.containsKey("AppnextVideoMode")) {
                try {
                    rewardedConfig.setMode((String) map.get("AppnextVideoMode"));
                    if (rewardedConfig.getMode().equals("multi") && map.containsKey("AppnextMultiTimerLength")) {
                        rewardedConfig.setMultiTimerLength(Integer.parseInt((String) map.get("AppnextMultiTimerLength")));
                    }
                } catch (Throwable th) {
                    Log.e("AppnextMoPub", "setVideoMode: " + th.getMessage());
                }
            }
            if (map.containsKey("AppnextRollCapTime")) {
                try {
                    rewardedConfig.setRollCaptionTime(Integer.parseInt((String) map.get("AppnextRollCapTime")));
                } catch (Throwable th2) {
                    Log.e("AppnextMoPub", "setRollCaptionTime: " + th2.getMessage());
                }
            }
            if (map.containsKey("AppnextShowCta")) {
                try {
                    rewardedConfig.setShowCta(Boolean.parseBoolean((String) map.get("AppnextShowCta")));
                } catch (Throwable th22) {
                    Log.e("AppnextMoPub", "setShowCta: " + th22.getMessage());
                }
            }
        }
    }

    protected static void setFullscreenConfigFromExtras(FullscreenConfig fullscreenConfig, Map<String, String> map) {
        if (fullscreenConfig != null && map != null && map.containsKey("AppnextBackButtonCanClose")) {
            try {
                fullscreenConfig.setBackButtonCanClose(Boolean.parseBoolean((String) map.get("AppnextBackButtonCanClose")));
            } catch (Throwable th) {
                Log.e("AppnextMoPub", "setBackButtonCanClose: " + th.getMessage());
            }
        }
    }
}
