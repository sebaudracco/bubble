package com.mopub.common;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import com.mopub.common.ExternalViewabilitySession.VideoEvent;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Reflection;
import com.mopub.common.util.Reflection.MethodBuilder;
import com.mopub.mobileads.VastExtensionXmlManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

class MoatViewabilitySession implements ExternalViewabilitySession {
    private static final String DEFAULT_PARTNER_CODE = "mopubinapphtmvideo468906546585";
    private static final String MOAT_AD_EVENT_PATH = "com.moat.analytics.mobile.mpub.MoatAdEvent";
    private static final String MOAT_AD_EVENT_TYPE_PATH = "com.moat.analytics.mobile.mpub.MoatAdEventType";
    private static final String MOAT_ANALYTICS_PATH = "com.moat.analytics.mobile.mpub.MoatAnalytics";
    private static final String MOAT_FACTORY_PATH = "com.moat.analytics.mobile.mpub.MoatFactory";
    private static final String MOAT_KEY = "moat";
    private static final String MOAT_OPTIONS_PATH = "com.moat.analytics.mobile.mpub.MoatOptions";
    private static final String MOAT_PLUGIN_PATH = "com.moat.analytics.mobile.mpub.MoatPlugin";
    private static final String MOAT_REACTIVE_VIDEO_TRACKER_PLUGIN_PATH = "com.moat.analytics.mobile.mpub.ReactiveVideoTrackerPlugin";
    private static final String MOAT_VAST_IDS_KEY = "zMoatVASTIDs";
    private static final String PARTNER_CODE_KEY = "partnerCode";
    private static final Map<String, String> QUERY_PARAM_MAPPING = new HashMap();
    private static boolean sIsVendorDisabled;
    private static Boolean sIsViewabilityEnabledViaReflection;
    private static boolean sWasInitialized = false;
    @NonNull
    private Map<String, String> mAdIds = new HashMap();
    @Nullable
    private Object mMoatVideoTracker;
    @Nullable
    private Object mMoatWebAdTracker;
    private boolean mWasVideoPrepared;

    MoatViewabilitySession() {
    }

    static {
        QUERY_PARAM_MAPPING.put("moatClientLevel1", "level1");
        QUERY_PARAM_MAPPING.put("moatClientLevel2", "level2");
        QUERY_PARAM_MAPPING.put("moatClientLevel3", "level3");
        QUERY_PARAM_MAPPING.put("moatClientLevel4", "level4");
        QUERY_PARAM_MAPPING.put("moatClientSlicer1", "slicer1");
        QUERY_PARAM_MAPPING.put("moatClientSlicer2", "slicer2");
    }

    static boolean isEnabled() {
        return !sIsVendorDisabled && isViewabilityEnabledViaReflection();
    }

    static void disable() {
        sIsVendorDisabled = true;
    }

    private static boolean isViewabilityEnabledViaReflection() {
        if (sIsViewabilityEnabledViaReflection == null) {
            sIsViewabilityEnabledViaReflection = Boolean.valueOf(Reflection.classFound(MOAT_FACTORY_PATH));
            MoPubLog.m12061d("Moat is " + (sIsViewabilityEnabledViaReflection.booleanValue() ? "" : "un") + "available via reflection.");
        }
        return sIsViewabilityEnabledViaReflection.booleanValue();
    }

    @NonNull
    public String getName() {
        return VastExtensionXmlManager.MOAT;
    }

    @Nullable
    public Boolean initialize(@NonNull Context context) {
        Preconditions.checkNotNull(context);
        if (!isEnabled()) {
            return null;
        }
        if (sWasInitialized) {
            return Boolean.valueOf(true);
        }
        if (context instanceof Activity) {
            Object application = ((Activity) context).getApplication();
        } else {
            try {
                Application application2 = (Application) context.getApplicationContext();
            } catch (ClassCastException e) {
                MoPubLog.m12061d("Unable to initialize Moat, error obtaining application context.");
                return Boolean.valueOf(false);
            }
        }
        try {
            Object moatOptions = Reflection.instantiateClassWithEmptyConstructor(MOAT_OPTIONS_PATH, Object.class);
            moatOptions.getClass().getField("disableAdIdCollection").setBoolean(moatOptions, true);
            moatOptions.getClass().getField("disableLocationServices").setBoolean(moatOptions, true);
            new MethodBuilder(new MethodBuilder(null, "getInstance").setStatic(MOAT_ANALYTICS_PATH).execute(), "start").addParam(MOAT_OPTIONS_PATH, moatOptions).addParam(Application.class, application).execute();
            sWasInitialized = true;
            return Boolean.valueOf(true);
        } catch (Exception e2) {
            MoPubLog.m12061d("Unable to initialize Moat: " + e2.getMessage());
            return Boolean.valueOf(false);
        }
    }

    @Nullable
    public Boolean invalidate() {
        if (!isEnabled()) {
            return null;
        }
        this.mMoatWebAdTracker = null;
        this.mMoatVideoTracker = null;
        this.mAdIds.clear();
        return Boolean.valueOf(true);
    }

    @Nullable
    public Boolean createDisplaySession(@NonNull Context context, @NonNull WebView webView, boolean isDeferred) {
        Preconditions.checkNotNull(context);
        if (!isEnabled()) {
            return null;
        }
        try {
            this.mMoatWebAdTracker = new MethodBuilder(new MethodBuilder(null, "create").setStatic(MOAT_FACTORY_PATH).execute(), "createWebAdTracker").addParam(WebView.class, (Object) webView).execute();
            if (!isDeferred) {
                new MethodBuilder(this.mMoatWebAdTracker, "startTracking").execute();
            }
            return Boolean.valueOf(true);
        } catch (Exception e) {
            MoPubLog.m12061d("Unable to execute Moat start display session: " + e.getMessage());
            return Boolean.valueOf(false);
        }
    }

    @Nullable
    public Boolean startDeferredDisplaySession(@NonNull Activity activity) {
        if (!isEnabled()) {
            return null;
        }
        if (this.mMoatWebAdTracker == null) {
            MoPubLog.m12061d("MoatWebAdTracker unexpectedly null.");
            return Boolean.valueOf(false);
        }
        try {
            new MethodBuilder(this.mMoatWebAdTracker, "startTracking").execute();
            return Boolean.valueOf(true);
        } catch (Exception e) {
            MoPubLog.m12061d("Unable to record deferred display session for Moat: " + e.getMessage());
            return Boolean.valueOf(false);
        }
    }

    @Nullable
    public Boolean endDisplaySession() {
        if (!isEnabled()) {
            return null;
        }
        if (this.mMoatWebAdTracker == null) {
            MoPubLog.m12061d("Moat WebAdTracker unexpectedly null.");
            return Boolean.valueOf(false);
        }
        try {
            new MethodBuilder(this.mMoatWebAdTracker, "stopTracking").execute();
            return Boolean.valueOf(true);
        } catch (Exception e) {
            MoPubLog.m12061d("Unable to execute Moat end session: " + e.getMessage());
            return Boolean.valueOf(false);
        }
    }

    @Nullable
    public Boolean createVideoSession(@NonNull Activity activity, @NonNull View view, @NonNull Set<String> buyerResources, @NonNull Map<String, String> videoViewabilityTrackers) {
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(view);
        Preconditions.checkNotNull(buyerResources);
        Preconditions.checkNotNull(videoViewabilityTrackers);
        if (!isEnabled()) {
            return null;
        }
        updateAdIdsFromUrlStringAndBuyerResources((String) videoViewabilityTrackers.get(MOAT_KEY), buyerResources);
        if (TextUtils.isEmpty((String) this.mAdIds.get(PARTNER_CODE_KEY))) {
            MoPubLog.m12061d("partnerCode was empty when starting Moat video session");
            return Boolean.valueOf(false);
        }
        try {
            this.mMoatVideoTracker = new MethodBuilder(new MethodBuilder(null, "create").setStatic(MOAT_FACTORY_PATH).execute(), "createCustomTracker").addParam(MOAT_PLUGIN_PATH, Reflection.instantiateClassWithConstructor(MOAT_REACTIVE_VIDEO_TRACKER_PLUGIN_PATH, Object.class, new Class[]{String.class}, new Object[]{(String) this.mAdIds.get(PARTNER_CODE_KEY)})).execute();
            return Boolean.valueOf(true);
        } catch (Exception e) {
            MoPubLog.m12061d("Unable to execute Moat start video session: " + e.getMessage());
            return Boolean.valueOf(false);
        }
    }

    @Nullable
    public Boolean registerVideoObstruction(@NonNull View view) {
        Preconditions.checkNotNull(view);
        if (isEnabled()) {
            return Boolean.valueOf(true);
        }
        return null;
    }

    @Nullable
    public Boolean onVideoPrepared(@NonNull View playerView, int duration) {
        Preconditions.checkNotNull(playerView);
        if (!isEnabled()) {
            return null;
        }
        if (this.mMoatVideoTracker == null) {
            MoPubLog.m12061d("Moat VideoAdTracker unexpectedly null.");
            return Boolean.valueOf(false);
        } else if (this.mWasVideoPrepared) {
            return Boolean.valueOf(false);
        } else {
            try {
                new MethodBuilder(this.mMoatVideoTracker, "trackVideoAd").addParam(Map.class, this.mAdIds).addParam(Integer.class, Integer.valueOf(duration)).addParam(View.class, (Object) playerView).execute();
                this.mWasVideoPrepared = true;
                return Boolean.valueOf(true);
            } catch (Exception e) {
                MoPubLog.m12061d("Unable to execute Moat onVideoPrepared: " + e.getMessage());
                return Boolean.valueOf(false);
            }
        }
    }

    @Nullable
    public Boolean recordVideoEvent(@NonNull VideoEvent event, int playheadMillis) {
        Preconditions.checkNotNull(event);
        if (!isEnabled()) {
            return null;
        }
        if (this.mMoatVideoTracker == null) {
            MoPubLog.m12061d("Moat VideoAdTracker unexpectedly null.");
            return Boolean.valueOf(false);
        }
        try {
            switch (event) {
                case AD_STARTED:
                case AD_STOPPED:
                case AD_PAUSED:
                case AD_PLAYING:
                case AD_SKIPPED:
                case AD_VIDEO_FIRST_QUARTILE:
                case AD_VIDEO_MIDPOINT:
                case AD_VIDEO_THIRD_QUARTILE:
                case AD_COMPLETE:
                    handleVideoEventReflection(event, playheadMillis);
                    return Boolean.valueOf(true);
                case AD_LOADED:
                case AD_IMPRESSED:
                case AD_CLICK_THRU:
                case RECORD_AD_ERROR:
                    return null;
                default:
                    MoPubLog.m12061d("Unexpected video event: " + event.getMoatEnumName());
                    return Boolean.valueOf(false);
            }
        } catch (Exception e) {
            MoPubLog.m12061d("Video event " + event.getMoatEnumName() + " failed. " + e.getMessage());
            return Boolean.valueOf(false);
        }
    }

    @Nullable
    public Boolean endVideoSession() {
        if (!isEnabled()) {
            return null;
        }
        if (this.mMoatVideoTracker == null) {
            MoPubLog.m12061d("Moat VideoAdTracker unexpectedly null.");
            return Boolean.valueOf(false);
        }
        try {
            new MethodBuilder(this.mMoatVideoTracker, "stopTracking").execute();
            return Boolean.valueOf(true);
        } catch (Exception e) {
            MoPubLog.m12061d("Unable to execute Moat end video session: " + e.getMessage());
            return Boolean.valueOf(false);
        }
    }

    private void updateAdIdsFromUrlStringAndBuyerResources(@Nullable String urlString, @Nullable Set<String> buyerResources) {
        this.mAdIds.clear();
        this.mAdIds.put(PARTNER_CODE_KEY, DEFAULT_PARTNER_CODE);
        this.mAdIds.put(MOAT_VAST_IDS_KEY, TextUtils.join(";", buyerResources));
        if (!TextUtils.isEmpty(urlString)) {
            Uri uri = Uri.parse(urlString);
            List<String> pathSegments = uri.getPathSegments();
            if (pathSegments.size() > 0 && !TextUtils.isEmpty((CharSequence) pathSegments.get(0))) {
                this.mAdIds.put(PARTNER_CODE_KEY, pathSegments.get(0));
            }
            String fragment = uri.getFragment();
            if (!TextUtils.isEmpty(fragment)) {
                for (String fragmentPairs : fragment.split("&")) {
                    String[] fragmentPair = fragmentPairs.split("=");
                    if (fragmentPair.length >= 2) {
                        String fragmentKey = fragmentPair[0];
                        String fragmentValue = fragmentPair[1];
                        if (!(TextUtils.isEmpty(fragmentKey) || TextUtils.isEmpty(fragmentValue) || !QUERY_PARAM_MAPPING.containsKey(fragmentKey))) {
                            this.mAdIds.put(QUERY_PARAM_MAPPING.get(fragmentKey), fragmentValue);
                        }
                    }
                }
            }
        }
    }

    private boolean handleVideoEventReflection(@NonNull VideoEvent videoEvent, int playheadMillis) throws Exception {
        if (videoEvent.getMoatEnumName() == null) {
            return false;
        }
        Enum<?> adEventTypeEnum = Enum.valueOf(Class.forName(MOAT_AD_EVENT_TYPE_PATH).asSubclass(Enum.class), videoEvent.getMoatEnumName());
        new MethodBuilder(this.mMoatVideoTracker, "dispatchEvent").addParam(MOAT_AD_EVENT_PATH, Reflection.instantiateClassWithConstructor(MOAT_AD_EVENT_PATH, Object.class, new Class[]{clazz, Integer.class}, new Object[]{adEventTypeEnum, Integer.valueOf(playheadMillis)})).execute();
        return true;
    }
}
