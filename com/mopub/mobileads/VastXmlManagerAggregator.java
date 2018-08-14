package com.mopub.mobileads;

import android.content.Context;
import android.graphics.Point;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;
import com.mopub.common.MoPubHttpUrlConnection;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Dips;
import com.mopub.common.util.Streams;
import com.mopub.common.util.Strings;
import com.mopub.mobileads.VastResource.Type;
import com.mopub.network.Networking;
import com.mopub.network.TrackingRequest;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class VastXmlManagerAggregator extends AsyncTask<String, Void, VastVideoConfig> {
    public static final String ADS_BY_AD_SLOT_ID = "adsBy";
    private static final double AREA_WEIGHT = 30.0d;
    private static final double ASPECT_RATIO_WEIGHT = 70.0d;
    static final int MAX_TIMES_TO_FOLLOW_VAST_REDIRECT = 10;
    private static final int MINIMUM_COMPANION_AD_HEIGHT = 250;
    private static final int MINIMUM_COMPANION_AD_WIDTH = 300;
    private static final String MOPUB = "MoPub";
    public static final String SOCIAL_ACTIONS_AD_SLOT_ID = "socialActions";
    private static final List<String> VIDEO_MIME_TYPES = Arrays.asList(new String[]{"video/mp4", "video/3gpp"});
    @NonNull
    private final Context mContext;
    private final int mScreenAreaDp;
    private final double mScreenAspectRatio;
    private int mTimesFollowedVastRedirect;
    @NonNull
    private final WeakReference<VastXmlManagerAggregatorListener> mVastXmlManagerAggregatorListener;

    VastXmlManagerAggregator(@NonNull VastXmlManagerAggregatorListener vastXmlManagerAggregatorListener, double screenAspectRatio, int screenAreaDp, @NonNull Context context) {
        Preconditions.checkNotNull(vastXmlManagerAggregatorListener);
        Preconditions.checkNotNull(context);
        this.mVastXmlManagerAggregatorListener = new WeakReference(vastXmlManagerAggregatorListener);
        this.mScreenAspectRatio = screenAspectRatio;
        this.mScreenAreaDp = screenAreaDp;
        this.mContext = context.getApplicationContext();
    }

    protected void onPreExecute() {
        Networking.getUserAgent(this.mContext);
    }

    protected VastVideoConfig doInBackground(@Nullable String... strings) {
        VastVideoConfig vastVideoConfig = null;
        if (!(strings == null || strings.length == 0 || strings[0] == null)) {
            try {
                vastVideoConfig = evaluateVastXmlManager(strings[0], new ArrayList());
            } catch (Exception e) {
                MoPubLog.d("Unable to generate VastVideoConfig.", e);
            }
        }
        return vastVideoConfig;
    }

    protected void onPostExecute(@Nullable VastVideoConfig vastVideoConfig) {
        VastXmlManagerAggregatorListener listener = (VastXmlManagerAggregatorListener) this.mVastXmlManagerAggregatorListener.get();
        if (listener != null) {
            listener.onAggregationComplete(vastVideoConfig);
        }
    }

    protected void onCancelled() {
        VastXmlManagerAggregatorListener listener = (VastXmlManagerAggregatorListener) this.mVastXmlManagerAggregatorListener.get();
        if (listener != null) {
            listener.onAggregationComplete(null);
        }
    }

    @Nullable
    @VisibleForTesting
    VastVideoConfig evaluateVastXmlManager(@NonNull String vastXml, @NonNull List<VastTracker> errorTrackers) {
        Preconditions.checkNotNull(vastXml, "vastXml cannot be null");
        Preconditions.checkNotNull(errorTrackers, "errorTrackers cannot be null");
        VastXmlManager xmlManager = new VastXmlManager();
        try {
            xmlManager.parseVastXml(vastXml);
            List<VastAdXmlManager> vastAdXmlManagers = xmlManager.getAdXmlManagers();
            if (fireErrorTrackerIfNoAds(vastAdXmlManagers, xmlManager, this.mContext)) {
                return null;
            }
            for (VastAdXmlManager vastAdXmlManager : vastAdXmlManagers) {
                if (isValidSequenceNumber(vastAdXmlManager.getSequence())) {
                    VastVideoConfig vastVideoConfig;
                    VastInLineXmlManager vastInLineXmlManager = vastAdXmlManager.getInLineXmlManager();
                    if (vastInLineXmlManager != null) {
                        vastVideoConfig = evaluateInLineXmlManager(vastInLineXmlManager, errorTrackers);
                        if (vastVideoConfig != null) {
                            populateMoPubCustomElements(xmlManager, vastVideoConfig);
                            return vastVideoConfig;
                        }
                    }
                    VastBaseInLineWrapperXmlManager vastWrapperXmlManager = vastAdXmlManager.getWrapperXmlManager();
                    if (vastWrapperXmlManager != null) {
                        List<VastTracker> arrayList = new ArrayList(errorTrackers);
                        arrayList.addAll(vastWrapperXmlManager.getErrorTrackers());
                        String vastRedirectXml = evaluateWrapperRedirect(vastWrapperXmlManager, arrayList);
                        if (vastRedirectXml != null) {
                            vastVideoConfig = evaluateVastXmlManager(vastRedirectXml, arrayList);
                            if (vastVideoConfig != null) {
                                vastVideoConfig.addImpressionTrackers(vastWrapperXmlManager.getImpressionTrackers());
                                for (VastLinearXmlManager linearXmlManager : vastWrapperXmlManager.getLinearXmlManagers()) {
                                    populateLinearTrackersAndIcon(linearXmlManager, vastVideoConfig);
                                }
                                populateVideoViewabilityTracker(vastWrapperXmlManager, vastVideoConfig);
                                populateViewabilityMetadata(vastWrapperXmlManager, vastVideoConfig);
                                List<VastCompanionAdXmlManager> companionAdXmlManagers = vastWrapperXmlManager.getCompanionAdXmlManagers();
                                if (vastVideoConfig.hasCompanionAd()) {
                                    VastCompanionAdConfig landscapeCompanionAd = vastVideoConfig.getVastCompanionAd(2);
                                    VastCompanionAdConfig portraitCompanionAd = vastVideoConfig.getVastCompanionAd(1);
                                    if (!(landscapeCompanionAd == null || portraitCompanionAd == null)) {
                                        for (VastCompanionAdXmlManager companionAdXmlManager : companionAdXmlManagers) {
                                            if (!companionAdXmlManager.hasResources()) {
                                                landscapeCompanionAd.addClickTrackers(companionAdXmlManager.getClickTrackers());
                                                landscapeCompanionAd.addCreativeViewTrackers(companionAdXmlManager.getCompanionCreativeViewTrackers());
                                                portraitCompanionAd.addClickTrackers(companionAdXmlManager.getClickTrackers());
                                                portraitCompanionAd.addCreativeViewTrackers(companionAdXmlManager.getCompanionCreativeViewTrackers());
                                            }
                                        }
                                    }
                                } else {
                                    vastVideoConfig.setVastCompanionAd(getBestCompanionAd(companionAdXmlManagers, CompanionOrientation.LANDSCAPE), getBestCompanionAd(companionAdXmlManagers, CompanionOrientation.PORTRAIT));
                                }
                                if (vastVideoConfig.getSocialActionsCompanionAds().isEmpty()) {
                                    vastVideoConfig.setSocialActionsCompanionAds(getSocialActionsCompanionAds(companionAdXmlManagers));
                                }
                                populateMoPubCustomElements(xmlManager, vastVideoConfig);
                                return vastVideoConfig;
                            }
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }
                }
            }
            return null;
        } catch (Exception e) {
            MoPubLog.d("Failed to parse VAST XML", e);
            TrackingRequest.makeVastTrackingHttpRequest(errorTrackers, VastErrorCode.XML_PARSING_ERROR, null, null, this.mContext);
            return null;
        }
    }

    @Nullable
    private VastVideoConfig evaluateInLineXmlManager(@NonNull VastInLineXmlManager vastInLineXmlManager, @NonNull List<VastTracker> errorTrackers) {
        Preconditions.checkNotNull(vastInLineXmlManager);
        Preconditions.checkNotNull(errorTrackers);
        for (VastLinearXmlManager linearXmlManager : vastInLineXmlManager.getLinearXmlManagers()) {
            String bestMediaFileUrl = getBestMediaFileUrl(linearXmlManager.getMediaXmlManagers());
            if (bestMediaFileUrl != null) {
                VastVideoConfig vastVideoConfig = new VastVideoConfig();
                vastVideoConfig.addImpressionTrackers(vastInLineXmlManager.getImpressionTrackers());
                populateLinearTrackersAndIcon(linearXmlManager, vastVideoConfig);
                vastVideoConfig.setClickThroughUrl(linearXmlManager.getClickThroughUrl());
                vastVideoConfig.setNetworkMediaFileUrl(bestMediaFileUrl);
                List<VastCompanionAdXmlManager> companionAdXmlManagers = vastInLineXmlManager.getCompanionAdXmlManagers();
                vastVideoConfig.setVastCompanionAd(getBestCompanionAd(companionAdXmlManagers, CompanionOrientation.LANDSCAPE), getBestCompanionAd(companionAdXmlManagers, CompanionOrientation.PORTRAIT));
                vastVideoConfig.setSocialActionsCompanionAds(getSocialActionsCompanionAds(companionAdXmlManagers));
                errorTrackers.addAll(vastInLineXmlManager.getErrorTrackers());
                vastVideoConfig.addErrorTrackers(errorTrackers);
                populateVideoViewabilityTracker(vastInLineXmlManager, vastVideoConfig);
                populateViewabilityMetadata(vastInLineXmlManager, vastVideoConfig);
                return vastVideoConfig;
            }
        }
        return null;
    }

    private void populateVideoViewabilityTracker(@NonNull VastBaseInLineWrapperXmlManager vastInLineXmlManager, @NonNull VastVideoConfig vastVideoConfig) {
        Preconditions.checkNotNull(vastInLineXmlManager);
        Preconditions.checkNotNull(vastVideoConfig);
        if (vastVideoConfig.getVideoViewabilityTracker() == null) {
            VastExtensionParentXmlManager vastExtensionParentXmlManager = vastInLineXmlManager.getVastExtensionParentXmlManager();
            if (vastExtensionParentXmlManager != null) {
                for (VastExtensionXmlManager vastExtensionXmlManager : vastExtensionParentXmlManager.getVastExtensionXmlManagers()) {
                    if ("MoPub".equals(vastExtensionXmlManager.getType())) {
                        vastVideoConfig.setVideoViewabilityTracker(vastExtensionXmlManager.getVideoViewabilityTracker());
                        return;
                    }
                }
            }
        }
    }

    private void populateViewabilityMetadata(@NonNull VastBaseInLineWrapperXmlManager vastInLineXmlManager, @NonNull VastVideoConfig vastVideoConfig) {
        VastExtensionParentXmlManager vastExtensionParentXmlManager = vastInLineXmlManager.getVastExtensionParentXmlManager();
        if (vastExtensionParentXmlManager != null) {
            for (VastExtensionXmlManager vastExtensionXmlManager : vastExtensionParentXmlManager.getVastExtensionXmlManagers()) {
                if (vastExtensionXmlManager != null) {
                    vastVideoConfig.addAvidJavascriptResources(vastExtensionXmlManager.getAvidJavaScriptResources());
                    vastVideoConfig.addMoatImpressionPixels(vastExtensionXmlManager.getMoatImpressionPixels());
                }
            }
        }
    }

    @Nullable
    private String evaluateWrapperRedirect(@NonNull VastWrapperXmlManager vastWrapperXmlManager, @NonNull List<VastTracker> wrapperErrorTrackers) {
        String vastAdTagUri = vastWrapperXmlManager.getVastAdTagURI();
        if (vastAdTagUri == null) {
            return null;
        }
        String str = null;
        try {
            return followVastRedirect(vastAdTagUri);
        } catch (Exception e) {
            MoPubLog.d("Failed to follow VAST redirect", e);
            if (wrapperErrorTrackers.isEmpty()) {
                return str;
            }
            TrackingRequest.makeVastTrackingHttpRequest(wrapperErrorTrackers, VastErrorCode.WRAPPER_TIMEOUT, null, null, this.mContext);
            return str;
        }
    }

    private void populateLinearTrackersAndIcon(@NonNull VastLinearXmlManager linearXmlManager, @NonNull VastVideoConfig vastVideoConfig) {
        Preconditions.checkNotNull(linearXmlManager, "linearXmlManager cannot be null");
        Preconditions.checkNotNull(vastVideoConfig, "vastVideoConfig cannot be null");
        vastVideoConfig.addAbsoluteTrackers(linearXmlManager.getAbsoluteProgressTrackers());
        vastVideoConfig.addFractionalTrackers(linearXmlManager.getFractionalProgressTrackers());
        vastVideoConfig.addPauseTrackers(linearXmlManager.getPauseTrackers());
        vastVideoConfig.addResumeTrackers(linearXmlManager.getResumeTrackers());
        vastVideoConfig.addCompleteTrackers(linearXmlManager.getVideoCompleteTrackers());
        vastVideoConfig.addCloseTrackers(linearXmlManager.getVideoCloseTrackers());
        vastVideoConfig.addSkipTrackers(linearXmlManager.getVideoSkipTrackers());
        vastVideoConfig.addClickTrackers(linearXmlManager.getClickTrackers());
        if (vastVideoConfig.getSkipOffsetString() == null) {
            vastVideoConfig.setSkipOffset(linearXmlManager.getSkipOffset());
        }
        if (vastVideoConfig.getVastIconConfig() == null) {
            vastVideoConfig.setVastIconConfig(getBestIcon(linearXmlManager.getIconXmlManagers()));
        }
    }

    private void populateMoPubCustomElements(@NonNull VastXmlManager xmlManager, @NonNull VastVideoConfig vastVideoConfig) {
        Preconditions.checkNotNull(xmlManager, "xmlManager cannot be null");
        Preconditions.checkNotNull(vastVideoConfig, "vastVideoConfig cannot be null");
        vastVideoConfig.addImpressionTrackers(xmlManager.getMoPubImpressionTrackers());
        if (vastVideoConfig.getCustomCtaText() == null) {
            vastVideoConfig.setCustomCtaText(xmlManager.getCustomCtaText());
        }
        if (vastVideoConfig.getCustomSkipText() == null) {
            vastVideoConfig.setCustomSkipText(xmlManager.getCustomSkipText());
        }
        if (vastVideoConfig.getCustomCloseIconUrl() == null) {
            vastVideoConfig.setCustomCloseIconUrl(xmlManager.getCustomCloseIconUrl());
        }
        if (!vastVideoConfig.isCustomForceOrientationSet()) {
            vastVideoConfig.setCustomForceOrientation(xmlManager.getCustomForceOrientation());
        }
    }

    private boolean fireErrorTrackerIfNoAds(@NonNull List<VastAdXmlManager> vastAdXmlManagers, @NonNull VastXmlManager xmlManager, @NonNull Context context) {
        if (!vastAdXmlManagers.isEmpty() || xmlManager.getErrorTracker() == null) {
            return false;
        }
        TrackingRequest.makeVastTrackingHttpRequest(Collections.singletonList(xmlManager.getErrorTracker()), this.mTimesFollowedVastRedirect > 0 ? VastErrorCode.NO_ADS_VAST_RESPONSE : VastErrorCode.UNDEFINED_ERROR, null, null, context);
        return true;
    }

    @Nullable
    @VisibleForTesting
    String getBestMediaFileUrl(@NonNull List<VastMediaXmlManager> managers) {
        Preconditions.checkNotNull(managers, "managers cannot be null");
        double bestMediaFitness = Double.POSITIVE_INFINITY;
        String bestMediaFileUrl = null;
        Iterator<VastMediaXmlManager> xmlManagerIterator = new ArrayList(managers).iterator();
        while (xmlManagerIterator.hasNext()) {
            VastMediaXmlManager mediaXmlManager = (VastMediaXmlManager) xmlManagerIterator.next();
            String mediaType = mediaXmlManager.getType();
            String mediaUrl = mediaXmlManager.getMediaUrl();
            if (!VIDEO_MIME_TYPES.contains(mediaType) || mediaUrl == null) {
                xmlManagerIterator.remove();
            } else {
                Integer mediaWidth = mediaXmlManager.getWidth();
                Integer mediaHeight = mediaXmlManager.getHeight();
                if (mediaWidth != null && mediaWidth.intValue() > 0 && mediaHeight != null && mediaHeight.intValue() > 0) {
                    double mediaFitness = calculateFitness(mediaWidth.intValue(), mediaHeight.intValue());
                    if (mediaFitness < bestMediaFitness) {
                        bestMediaFitness = mediaFitness;
                        bestMediaFileUrl = mediaUrl;
                    }
                }
            }
        }
        return bestMediaFileUrl;
    }

    @Nullable
    @VisibleForTesting
    VastCompanionAdConfig getBestCompanionAd(@NonNull List<VastCompanionAdXmlManager> managers, @NonNull CompanionOrientation orientation) {
        Preconditions.checkNotNull(managers, "managers cannot be null");
        Preconditions.checkNotNull(orientation, "orientation cannot be null");
        List<VastCompanionAdXmlManager> arrayList = new ArrayList(managers);
        double bestCompanionFitness = Double.POSITIVE_INFINITY;
        VastCompanionAdXmlManager bestCompanionXmlManager = null;
        VastResource bestVastResource = null;
        Point bestVastScaledDimensions = null;
        for (Type type : Type.values()) {
            for (VastCompanionAdXmlManager companionXmlManager : arrayList) {
                Integer width = companionXmlManager.getWidth();
                Integer height = companionXmlManager.getHeight();
                if (width != null && width.intValue() >= 300 && height != null && height.intValue() >= 250) {
                    Point vastScaledDimensions = getScaledDimensions(width.intValue(), height.intValue(), type, orientation);
                    VastResource vastResource = VastResource.fromVastResourceXmlManager(companionXmlManager.getResourceXmlManager(), type, vastScaledDimensions.x, vastScaledDimensions.y);
                    if (vastResource != null) {
                        double companionFitness;
                        if (CompanionOrientation.PORTRAIT == orientation) {
                            companionFitness = calculateFitness(height.intValue(), width.intValue());
                        } else {
                            companionFitness = calculateFitness(width.intValue(), height.intValue());
                        }
                        if (companionFitness < bestCompanionFitness) {
                            bestCompanionFitness = companionFitness;
                            bestCompanionXmlManager = companionXmlManager;
                            bestVastResource = vastResource;
                            bestVastScaledDimensions = vastScaledDimensions;
                        }
                    }
                }
            }
            if (bestCompanionXmlManager != null) {
                break;
            }
        }
        if (bestCompanionXmlManager != null) {
            return new VastCompanionAdConfig(bestVastScaledDimensions.x, bestVastScaledDimensions.y, bestVastResource, bestCompanionXmlManager.getClickThroughUrl(), bestCompanionXmlManager.getClickTrackers(), bestCompanionXmlManager.getCompanionCreativeViewTrackers());
        }
        return null;
    }

    @NonNull
    @VisibleForTesting
    Map<String, VastCompanionAdConfig> getSocialActionsCompanionAds(@NonNull List<VastCompanionAdXmlManager> managers) {
        Preconditions.checkNotNull(managers, "managers cannot be null");
        Map<String, VastCompanionAdConfig> socialActionsCompanionAds = new HashMap();
        for (VastCompanionAdXmlManager companionXmlManager : managers) {
            Integer width = companionXmlManager.getWidth();
            Integer height = companionXmlManager.getHeight();
            if (!(width == null || height == null)) {
                String adSlotId = companionXmlManager.getAdSlotId();
                if (ADS_BY_AD_SLOT_ID.equals(adSlotId)) {
                    if (width.intValue() >= 25) {
                        if (width.intValue() <= 75) {
                            if (height.intValue() >= 10) {
                                if (height.intValue() > 50) {
                                }
                            }
                        }
                    }
                } else if (SOCIAL_ACTIONS_AD_SLOT_ID.equals(adSlotId) && width.intValue() >= 50 && width.intValue() <= 150 && height.intValue() >= 10) {
                    if (height.intValue() > 50) {
                    }
                }
                VastResource vastResource = VastResource.fromVastResourceXmlManager(companionXmlManager.getResourceXmlManager(), Type.HTML_RESOURCE, width.intValue(), height.intValue());
                if (vastResource != null) {
                    socialActionsCompanionAds.put(adSlotId, new VastCompanionAdConfig(width.intValue(), height.intValue(), vastResource, companionXmlManager.getClickThroughUrl(), companionXmlManager.getClickTrackers(), companionXmlManager.getCompanionCreativeViewTrackers()));
                }
            }
        }
        return socialActionsCompanionAds;
    }

    @NonNull
    @VisibleForTesting
    Point getScaledDimensions(int widthDp, int heightDp, Type type, CompanionOrientation orientation) {
        int screenWidthPx;
        int screenHeightPx;
        Point defaultPoint = new Point(widthDp, heightDp);
        Display display = ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay();
        int x = display.getWidth();
        int y = display.getHeight();
        int widthPx = Dips.dipsToIntPixels((float) widthDp, this.mContext);
        int heightPx = Dips.dipsToIntPixels((float) heightDp, this.mContext);
        if (CompanionOrientation.LANDSCAPE == orientation) {
            screenWidthPx = Math.max(x, y);
            screenHeightPx = Math.min(x, y);
        } else {
            screenWidthPx = Math.min(x, y);
            screenHeightPx = Math.max(x, y);
        }
        if (widthPx <= screenWidthPx - 16 && heightPx <= screenHeightPx - 16) {
            return defaultPoint;
        }
        Point point = new Point();
        if (Type.HTML_RESOURCE == type) {
            point.x = Math.min(screenWidthPx, widthPx);
            point.y = Math.min(screenHeightPx, heightPx);
        } else {
            float widthRatio = ((float) widthPx) / ((float) screenWidthPx);
            float heightRatio = ((float) heightPx) / ((float) screenHeightPx);
            if (widthRatio >= heightRatio) {
                point.x = screenWidthPx;
                point.y = (int) (((float) heightPx) / widthRatio);
            } else {
                point.x = (int) (((float) widthPx) / heightRatio);
                point.y = screenHeightPx;
            }
        }
        point.x -= 16;
        point.y -= 16;
        if (point.x < 0 || point.y < 0) {
            return defaultPoint;
        }
        point.x = Dips.pixelsToIntDips((float) point.x, this.mContext);
        point.y = Dips.pixelsToIntDips((float) point.y, this.mContext);
        return point;
    }

    @Nullable
    @VisibleForTesting
    VastIconConfig getBestIcon(@NonNull List<VastIconXmlManager> managers) {
        Preconditions.checkNotNull(managers, "managers cannot be null");
        List<VastIconXmlManager> iconXmlManagers = new ArrayList(managers);
        for (Type type : Type.values()) {
            for (VastIconXmlManager iconXmlManager : iconXmlManagers) {
                Integer width = iconXmlManager.getWidth();
                Integer height = iconXmlManager.getHeight();
                if (width != null && width.intValue() > 0 && width.intValue() <= 300 && height != null && height.intValue() > 0 && height.intValue() <= 300) {
                    VastResource vastResource = VastResource.fromVastResourceXmlManager(iconXmlManager.getResourceXmlManager(), type, width.intValue(), height.intValue());
                    if (vastResource != null) {
                        return new VastIconConfig(iconXmlManager.getWidth().intValue(), iconXmlManager.getHeight().intValue(), iconXmlManager.getOffsetMS(), iconXmlManager.getDurationMS(), vastResource, iconXmlManager.getClickTrackingUris(), iconXmlManager.getClickThroughUri(), iconXmlManager.getViewTrackingUris());
                    }
                }
            }
        }
        return null;
    }

    private double calculateFitness(int widthDp, int heightDp) {
        return (ASPECT_RATIO_WEIGHT * Math.abs(Math.log((((double) widthDp) / ((double) heightDp)) / this.mScreenAspectRatio))) + (AREA_WEIGHT * Math.abs(Math.log(((double) (widthDp * heightDp)) / ((double) this.mScreenAreaDp))));
    }

    static boolean isValidSequenceNumber(@Nullable String sequence) {
        if (TextUtils.isEmpty(sequence)) {
            return true;
        }
        try {
            if (Integer.parseInt(sequence) >= 2) {
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    @Nullable
    private String followVastRedirect(@NonNull String redirectUrl) throws IOException {
        Throwable th;
        Preconditions.checkNotNull(redirectUrl);
        if (this.mTimesFollowedVastRedirect >= 10) {
            return null;
        }
        this.mTimesFollowedVastRedirect++;
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        try {
            httpURLConnection = MoPubHttpUrlConnection.getHttpUrlConnection(redirectUrl);
            InputStream inputStream2 = new BufferedInputStream(httpURLConnection.getInputStream());
            try {
                String fromStream = Strings.fromStream(inputStream2);
                Streams.closeStream(inputStream2);
                if (httpURLConnection == null) {
                    return fromStream;
                }
                httpURLConnection.disconnect();
                return fromStream;
            } catch (Throwable th2) {
                th = th2;
                inputStream = inputStream2;
                Streams.closeStream(inputStream);
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            Streams.closeStream(inputStream);
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }

    @Deprecated
    @VisibleForTesting
    void setTimesFollowedVastRedirect(int timesFollowedVastRedirect) {
        this.mTimesFollowedVastRedirect = timesFollowedVastRedirect;
    }
}
