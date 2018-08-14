package com.mopub.common;

import android.app.Activity;
import android.content.Context;
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
import java.util.Map;
import java.util.Set;

class AvidViewabilitySession implements ExternalViewabilitySession {
    private static final String AVID_AD_SESSION_MANAGER_PATH = "com.integralads.avid.library.mopub.session.AvidAdSessionManager";
    private static final String AVID_KEY = "avid";
    private static final String AVID_MANAGER_PATH = "com.integralads.avid.library.mopub.AvidManager";
    private static final String EXTERNAL_AVID_AD_SESSION_CONTEXT_PATH = "com.integralads.avid.library.mopub.session.ExternalAvidAdSessionContext";
    private static Object sAvidAdSessionContextDeferred;
    private static Object sAvidAdSessionContextNonDeferred;
    private static boolean sIsVendorDisabled;
    private static Boolean sIsViewabilityEnabledViaReflection;
    @Nullable
    private Object mAvidDisplayAdSession;
    @Nullable
    private Object mAvidVideoAdSession;

    AvidViewabilitySession() {
    }

    static boolean isEnabled() {
        return !sIsVendorDisabled && isViewabilityEnabledViaReflection();
    }

    static void disable() {
        sIsVendorDisabled = true;
    }

    private static boolean isViewabilityEnabledViaReflection() {
        if (sIsViewabilityEnabledViaReflection == null) {
            sIsViewabilityEnabledViaReflection = Boolean.valueOf(Reflection.classFound(AVID_AD_SESSION_MANAGER_PATH));
            MoPubLog.m12061d("Avid is " + (sIsViewabilityEnabledViaReflection.booleanValue() ? "" : "un") + "available via reflection.");
        }
        return sIsViewabilityEnabledViaReflection.booleanValue();
    }

    @Nullable
    private static Object getAvidAdSessionContextDeferred() {
        if (sAvidAdSessionContextDeferred == null) {
            try {
                sAvidAdSessionContextDeferred = Reflection.instantiateClassWithConstructor(EXTERNAL_AVID_AD_SESSION_CONTEXT_PATH, Object.class, new Class[]{String.class, Boolean.TYPE}, new Object[]{"4.20.0", Boolean.valueOf(true)});
            } catch (Exception e) {
                MoPubLog.m12061d("Unable to generate Avid deferred ad session context: " + e.getMessage());
            }
        }
        return sAvidAdSessionContextDeferred;
    }

    @Nullable
    private static Object getAvidAdSessionContextNonDeferred() {
        if (sAvidAdSessionContextNonDeferred == null) {
            try {
                sAvidAdSessionContextNonDeferred = Reflection.instantiateClassWithConstructor(EXTERNAL_AVID_AD_SESSION_CONTEXT_PATH, Object.class, new Class[]{String.class}, new Object[]{"4.20.0"});
            } catch (Exception e) {
                MoPubLog.m12061d("Unable to generate Avid ad session context: " + e.getMessage());
            }
        }
        return sAvidAdSessionContextNonDeferred;
    }

    @NonNull
    public String getName() {
        return VastExtensionXmlManager.AVID;
    }

    @Nullable
    public Boolean initialize(@NonNull Context context) {
        Preconditions.checkNotNull(context);
        if (isEnabled()) {
            return Boolean.valueOf(true);
        }
        return null;
    }

    @Nullable
    public Boolean invalidate() {
        if (!isEnabled()) {
            return null;
        }
        this.mAvidDisplayAdSession = null;
        this.mAvidVideoAdSession = null;
        return Boolean.valueOf(true);
    }

    @Nullable
    public Boolean createDisplaySession(@NonNull Context context, @NonNull WebView webView, boolean isDeferred) {
        Object activity = null;
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(webView);
        if (!isEnabled()) {
            return null;
        }
        Object avidAdSessionContext;
        if (isDeferred) {
            avidAdSessionContext = getAvidAdSessionContextDeferred();
        } else {
            avidAdSessionContext = getAvidAdSessionContextNonDeferred();
        }
        if (context instanceof Activity) {
            activity = (Activity) context;
        }
        try {
            this.mAvidDisplayAdSession = new MethodBuilder(null, "startAvidDisplayAdSession").setStatic(AVID_AD_SESSION_MANAGER_PATH).addParam(Context.class, (Object) context).addParam(EXTERNAL_AVID_AD_SESSION_CONTEXT_PATH, avidAdSessionContext).execute();
            new MethodBuilder(this.mAvidDisplayAdSession, "registerAdView").addParam(View.class, (Object) webView).addParam(Activity.class, activity).execute();
            return Boolean.valueOf(true);
        } catch (Exception e) {
            MoPubLog.m12061d("Unable to execute Avid start display session: " + e.getMessage());
            return Boolean.valueOf(false);
        }
    }

    @Nullable
    public Boolean startDeferredDisplaySession(@NonNull Activity activity) {
        if (!isEnabled()) {
            return null;
        }
        if (this.mAvidDisplayAdSession == null) {
            MoPubLog.m12061d("Avid DisplayAdSession unexpectedly null.");
            return Boolean.valueOf(false);
        }
        try {
            new MethodBuilder(new MethodBuilder(null, "getInstance").setStatic(AVID_MANAGER_PATH).execute(), "registerActivity").addParam(Activity.class, (Object) activity).execute();
            Object deferredAdSessionListener = new MethodBuilder(this.mAvidDisplayAdSession, "getAvidDeferredAdSessionListener").execute();
            if (deferredAdSessionListener == null) {
                MoPubLog.m12061d("Avid AdSessionListener unexpectedly null.");
                return Boolean.valueOf(false);
            }
            new MethodBuilder(deferredAdSessionListener, "recordReadyEvent").execute();
            return Boolean.valueOf(true);
        } catch (Exception e) {
            MoPubLog.m12061d("Unable to execute Avid record deferred session: " + e.getMessage());
            return Boolean.valueOf(false);
        }
    }

    @Nullable
    public Boolean endDisplaySession() {
        if (!isEnabled()) {
            return null;
        }
        if (this.mAvidDisplayAdSession == null) {
            MoPubLog.m12061d("Avid DisplayAdSession unexpectedly null.");
            return Boolean.valueOf(false);
        }
        try {
            new MethodBuilder(this.mAvidDisplayAdSession, "endSession").execute();
            return Boolean.valueOf(true);
        } catch (Exception e) {
            MoPubLog.m12061d("Unable to execute Avid end session: " + e.getMessage());
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
        try {
            this.mAvidVideoAdSession = new MethodBuilder(null, "startAvidManagedVideoAdSession").setStatic(AVID_AD_SESSION_MANAGER_PATH).addParam(Context.class, (Object) activity).addParam(EXTERNAL_AVID_AD_SESSION_CONTEXT_PATH, getAvidAdSessionContextNonDeferred()).execute();
            new MethodBuilder(this.mAvidVideoAdSession, "registerAdView").addParam(View.class, (Object) view).addParam(Activity.class, (Object) activity).execute();
            if (!TextUtils.isEmpty((CharSequence) videoViewabilityTrackers.get("avid"))) {
                new MethodBuilder(this.mAvidVideoAdSession, "injectJavaScriptResource").addParam(String.class, videoViewabilityTrackers.get("avid")).execute();
            }
            for (Object buyerResource : buyerResources) {
                if (!TextUtils.isEmpty(buyerResource)) {
                    new MethodBuilder(this.mAvidVideoAdSession, "injectJavaScriptResource").addParam(String.class, buyerResource).execute();
                }
            }
            return Boolean.valueOf(true);
        } catch (Exception e) {
            MoPubLog.m12061d("Unable to execute Avid start video session: " + e.getMessage());
            return Boolean.valueOf(false);
        }
    }

    @Nullable
    public Boolean registerVideoObstruction(@NonNull View view) {
        Preconditions.checkNotNull(view);
        if (!isEnabled()) {
            return null;
        }
        if (this.mAvidVideoAdSession == null) {
            MoPubLog.m12061d("Avid VideoAdSession unexpectedly null.");
            return Boolean.valueOf(false);
        }
        try {
            new MethodBuilder(this.mAvidVideoAdSession, "registerFriendlyObstruction").addParam(View.class, (Object) view).execute();
            return Boolean.valueOf(true);
        } catch (Exception e) {
            MoPubLog.m12061d("Unable to register Avid video obstructions: " + e.getMessage());
            return Boolean.valueOf(false);
        }
    }

    @Nullable
    public Boolean onVideoPrepared(@NonNull View playerView, int duration) {
        Preconditions.checkNotNull(playerView);
        if (isEnabled()) {
            return Boolean.valueOf(true);
        }
        return null;
    }

    @Nullable
    public Boolean recordVideoEvent(@NonNull VideoEvent event, int playheadMillis) {
        Preconditions.checkNotNull(event);
        if (!isEnabled()) {
            return null;
        }
        if (this.mAvidVideoAdSession == null) {
            MoPubLog.m12061d("Avid VideoAdSession unexpectedly null.");
            return Boolean.valueOf(false);
        }
        try {
            switch (event) {
                case AD_LOADED:
                case AD_STARTED:
                case AD_STOPPED:
                case AD_PAUSED:
                case AD_PLAYING:
                case AD_SKIPPED:
                case AD_IMPRESSED:
                case AD_CLICK_THRU:
                case AD_VIDEO_FIRST_QUARTILE:
                case AD_VIDEO_MIDPOINT:
                case AD_VIDEO_THIRD_QUARTILE:
                case AD_COMPLETE:
                    handleVideoEventReflection(event);
                    return Boolean.valueOf(true);
                case RECORD_AD_ERROR:
                    handleVideoEventReflection(event, "error");
                    return Boolean.valueOf(true);
                default:
                    MoPubLog.m12061d("Unexpected video event type: " + event);
                    return Boolean.valueOf(false);
            }
        } catch (Exception e) {
            MoPubLog.m12061d("Unable to execute Avid video event for " + event.getAvidMethodName() + ": " + e.getMessage());
            return Boolean.valueOf(false);
        }
    }

    @Nullable
    public Boolean endVideoSession() {
        if (!isEnabled()) {
            return null;
        }
        if (this.mAvidVideoAdSession == null) {
            MoPubLog.m12061d("Avid VideoAdSession unexpectedly null.");
            return Boolean.valueOf(false);
        }
        try {
            new MethodBuilder(this.mAvidVideoAdSession, "endSession").execute();
            return Boolean.valueOf(true);
        } catch (Exception e) {
            MoPubLog.m12061d("Unable to execute Avid end video session: " + e.getMessage());
            return Boolean.valueOf(false);
        }
    }

    private void handleVideoEventReflection(@NonNull VideoEvent videoEvent) throws Exception {
        handleVideoEventReflection(videoEvent, null);
    }

    private void handleVideoEventReflection(@NonNull VideoEvent videoEvent, @Nullable String message) throws Exception {
        MethodBuilder methodBuilder = new MethodBuilder(new MethodBuilder(this.mAvidVideoAdSession, "getAvidVideoPlaybackListener").execute(), videoEvent.getAvidMethodName());
        if (!TextUtils.isEmpty(message)) {
            methodBuilder.addParam(String.class, (Object) message);
        }
        methodBuilder.execute();
    }
}
