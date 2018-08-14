package com.mopub.common;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import com.mopub.common.ExternalViewabilitySession.VideoEvent;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.VastVideoConfig;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class ExternalViewabilitySessionManager {
    @NonNull
    private final Set<ExternalViewabilitySession> mViewabilitySessions = new HashSet();

    public enum ViewabilityVendor {
        AVID,
        MOAT,
        ALL;

        public void disable() {
            switch (this) {
                case AVID:
                    AvidViewabilitySession.disable();
                    break;
                case MOAT:
                    MoatViewabilitySession.disable();
                    break;
                case ALL:
                    AvidViewabilitySession.disable();
                    MoatViewabilitySession.disable();
                    break;
                default:
                    MoPubLog.m12061d("Attempted to disable an invalid viewability vendor: " + this);
                    return;
            }
            MoPubLog.m12061d("Disabled viewability for " + this);
        }

        @NonNull
        public static String getEnabledVendorKey() {
            boolean avidEnabled = AvidViewabilitySession.isEnabled();
            boolean moatEnabled = MoatViewabilitySession.isEnabled();
            String vendorKey = SchemaSymbols.ATTVAL_FALSE_0;
            if (avidEnabled && moatEnabled) {
                return "3";
            }
            if (avidEnabled) {
                return SchemaSymbols.ATTVAL_TRUE_1;
            }
            if (moatEnabled) {
                return "2";
            }
            return vendorKey;
        }

        @Nullable
        public static ViewabilityVendor fromKey(@NonNull String key) {
            Preconditions.checkNotNull(key);
            Object obj = -1;
            switch (key.hashCode()) {
                case 49:
                    if (key.equals(SchemaSymbols.ATTVAL_TRUE_1)) {
                        obj = null;
                        break;
                    }
                    break;
                case 50:
                    if (key.equals("2")) {
                        obj = 1;
                        break;
                    }
                    break;
                case 51:
                    if (key.equals("3")) {
                        obj = 2;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    return AVID;
                case 1:
                    return MOAT;
                case 2:
                    return ALL;
                default:
                    return null;
            }
        }
    }

    public ExternalViewabilitySessionManager(@NonNull Context context) {
        Preconditions.checkNotNull(context);
        this.mViewabilitySessions.add(new AvidViewabilitySession());
        this.mViewabilitySessions.add(new MoatViewabilitySession());
        initialize(context);
    }

    private void initialize(@NonNull Context context) {
        Preconditions.checkNotNull(context);
        for (ExternalViewabilitySession session : this.mViewabilitySessions) {
            logEvent(session, "initialize", session.initialize(context), false);
        }
    }

    public void invalidate() {
        for (ExternalViewabilitySession session : this.mViewabilitySessions) {
            logEvent(session, "invalidate", session.invalidate(), false);
        }
    }

    public void createDisplaySession(@NonNull Context context, @NonNull WebView webView, boolean isDeferred) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(webView);
        for (ExternalViewabilitySession session : this.mViewabilitySessions) {
            logEvent(session, "start display session", session.createDisplaySession(context, webView, isDeferred), true);
        }
    }

    public void createDisplaySession(@NonNull Context context, @NonNull WebView webview) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(webview);
        createDisplaySession(context, webview, false);
    }

    public void startDeferredDisplaySession(@NonNull Activity activity) {
        for (ExternalViewabilitySession session : this.mViewabilitySessions) {
            logEvent(session, "record deferred session", session.startDeferredDisplaySession(activity), true);
        }
    }

    public void endDisplaySession() {
        for (ExternalViewabilitySession session : this.mViewabilitySessions) {
            logEvent(session, "end display session", session.endDisplaySession(), true);
        }
    }

    public void createVideoSession(@NonNull Activity activity, @NonNull View view, @NonNull VastVideoConfig vastVideoConfig) {
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(view);
        Preconditions.checkNotNull(vastVideoConfig);
        for (ExternalViewabilitySession session : this.mViewabilitySessions) {
            Set<String> buyerResources = new HashSet();
            if (session instanceof AvidViewabilitySession) {
                buyerResources.addAll(vastVideoConfig.getAvidJavascriptResources());
            } else if (session instanceof MoatViewabilitySession) {
                buyerResources.addAll(vastVideoConfig.getMoatImpressionPixels());
            }
            logEvent(session, "start video session", session.createVideoSession(activity, view, buyerResources, vastVideoConfig.getExternalViewabilityTrackers()), true);
        }
    }

    public void registerVideoObstruction(@NonNull View view) {
        Preconditions.checkNotNull(view);
        for (ExternalViewabilitySession session : this.mViewabilitySessions) {
            logEvent(session, "register friendly obstruction", session.registerVideoObstruction(view), true);
        }
    }

    public void onVideoPrepared(@NonNull View playerView, int duration) {
        Preconditions.checkNotNull(playerView);
        for (ExternalViewabilitySession session : this.mViewabilitySessions) {
            logEvent(session, "on video prepared", session.onVideoPrepared(playerView, duration), true);
        }
    }

    public void recordVideoEvent(@NonNull VideoEvent event, int playheadMillis) {
        Preconditions.checkNotNull(event);
        for (ExternalViewabilitySession session : this.mViewabilitySessions) {
            logEvent(session, "record video event (" + event.name() + ")", session.recordVideoEvent(event, playheadMillis), true);
        }
    }

    public void endVideoSession() {
        for (ExternalViewabilitySession session : this.mViewabilitySessions) {
            logEvent(session, "end video session", session.endVideoSession(), true);
        }
    }

    private void logEvent(@NonNull ExternalViewabilitySession session, @NonNull String event, @Nullable Boolean successful, boolean isVerbose) {
        Preconditions.checkNotNull(session);
        Preconditions.checkNotNull(event);
        if (successful != null) {
            String failureString = successful.booleanValue() ? "" : "failed to ";
            String message = String.format(Locale.US, "%s viewability event: %s%s.", new Object[]{session.getName(), failureString, event});
            if (isVerbose) {
                MoPubLog.m12067v(message);
            } else {
                MoPubLog.m12061d(message);
            }
        }
    }
}
