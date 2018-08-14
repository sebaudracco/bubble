package com.mopub.network;

import android.content.Context;
import android.location.Location;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.AdFormat;
import com.mopub.common.AdType;
import com.mopub.common.DataKeys;
import com.mopub.common.ExternalViewabilitySessionManager.ViewabilityVendor;
import com.mopub.common.FullAdType;
import com.mopub.common.LocationService;
import com.mopub.common.MoPub;
import com.mopub.common.MoPub$BrowserAgent;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.event.BaseEvent;
import com.mopub.common.event.BaseEvent.Category;
import com.mopub.common.event.BaseEvent.Name;
import com.mopub.common.event.BaseEvent.SamplingRate;
import com.mopub.common.event.Event;
import com.mopub.common.event.EventDetails;
import com.mopub.common.event.MoPubEvents;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Json;
import com.mopub.common.util.ResponseHeader;
import com.mopub.mobileads.AdTypeTranslator;
import com.mopub.network.AdResponse.Builder;
import com.mopub.network.MoPubNetworkError.Reason;
import com.mopub.volley.DefaultRetryPolicy;
import com.mopub.volley.NetworkResponse;
import com.mopub.volley.Request;
import com.mopub.volley.Response;
import com.mopub.volley.Response.ErrorListener;
import com.mopub.volley.toolbox.HttpHeaderParser;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONObject;

public class AdRequest extends Request<AdResponse> {
    @NonNull
    private final AdFormat mAdFormat;
    @Nullable
    private final String mAdUnitId;
    @NonNull
    private final Context mContext;
    @NonNull
    private final Listener mListener;

    public interface Listener extends ErrorListener {
        void onSuccess(AdResponse adResponse);
    }

    public AdRequest(@NonNull String url, @NonNull AdFormat adFormat, @Nullable String adUnitId, @NonNull Context context, @NonNull Listener listener) {
        super(0, url, listener);
        Preconditions.checkNotNull(adFormat);
        Preconditions.checkNotNull(listener);
        this.mAdUnitId = adUnitId;
        this.mListener = listener;
        this.mAdFormat = adFormat;
        this.mContext = context.getApplicationContext();
        setRetryPolicy(new DefaultRetryPolicy(2500, 1, 1.0f));
        setShouldCache(false);
    }

    @NonNull
    public Listener getListener() {
        return this.mListener;
    }

    public Map<String, String> getHeaders() {
        TreeMap<String, String> headers = new TreeMap();
        String languageCode = Locale.getDefault().getLanguage();
        Locale userLocale = this.mContext.getResources().getConfiguration().locale;
        if (!(userLocale == null || userLocale.getLanguage().trim().isEmpty())) {
            languageCode = userLocale.getLanguage().trim();
        }
        if (!languageCode.isEmpty()) {
            headers.put(ResponseHeader.ACCEPT_LANGUAGE.getKey(), languageCode);
        }
        return headers;
    }

    protected Response<AdResponse> parseNetworkResponse(NetworkResponse networkResponse) {
        Map<String, String> headers = networkResponse.headers;
        if (HeaderUtils.extractBooleanHeader((Map) headers, ResponseHeader.WARMUP, false)) {
            return Response.error(new MoPubNetworkError("Ad Unit is warming up.", Reason.WARMING_UP));
        }
        Integer refreshTimeMilliseconds;
        Location location = LocationService.getLastKnownLocation(this.mContext, MoPub.getLocationPrecision(), MoPub.getLocationAwareness());
        Builder builder = new Builder();
        builder.setAdUnitId(this.mAdUnitId);
        String adTypeString = HeaderUtils.extractHeader((Map) headers, ResponseHeader.AD_TYPE);
        String fullAdTypeString = HeaderUtils.extractHeader((Map) headers, ResponseHeader.FULL_AD_TYPE);
        builder.setAdType(adTypeString);
        builder.setFullAdType(fullAdTypeString);
        Integer refreshTimeSeconds = HeaderUtils.extractIntegerHeader((Map) headers, ResponseHeader.REFRESH_TIME);
        if (refreshTimeSeconds == null) {
            refreshTimeMilliseconds = null;
        } else {
            refreshTimeMilliseconds = Integer.valueOf(refreshTimeSeconds.intValue() * 1000);
        }
        builder.setRefreshTimeMilliseconds(refreshTimeMilliseconds);
        if (AdType.CLEAR.equals(adTypeString)) {
            logScribeEvent(builder.build(), networkResponse, location);
            return Response.error(new MoPubNetworkError("No ads found for ad unit.", Reason.NO_FILL, refreshTimeMilliseconds));
        }
        Integer num;
        String dspCreativeId = HeaderUtils.extractHeader((Map) headers, ResponseHeader.DSP_CREATIVE_ID);
        builder.setDspCreativeId(dspCreativeId);
        String networkType = HeaderUtils.extractHeader((Map) headers, ResponseHeader.NETWORK_TYPE);
        builder.setNetworkType(networkType);
        String redirectUrl = HeaderUtils.extractHeader((Map) headers, ResponseHeader.REDIRECT_URL);
        builder.setRedirectUrl(redirectUrl);
        String clickTrackingUrl = HeaderUtils.extractHeader((Map) headers, ResponseHeader.CLICK_TRACKING_URL);
        builder.setClickTrackingUrl(clickTrackingUrl);
        builder.setImpressionTrackingUrl(HeaderUtils.extractHeader((Map) headers, ResponseHeader.IMPRESSION_URL));
        String failUrl = HeaderUtils.extractHeader((Map) headers, ResponseHeader.FAIL_URL);
        builder.setFailoverUrl(failUrl);
        String requestId = getRequestId(failUrl);
        builder.setRequestId(requestId);
        boolean isScrollable = HeaderUtils.extractBooleanHeader((Map) headers, ResponseHeader.SCROLLABLE, false);
        builder.setScrollable(Boolean.valueOf(isScrollable));
        Integer width = HeaderUtils.extractIntegerHeader((Map) headers, ResponseHeader.WIDTH);
        Integer height = HeaderUtils.extractIntegerHeader((Map) headers, ResponseHeader.HEIGHT);
        builder.setDimensions(width, height);
        Integer adTimeoutDelaySeconds = HeaderUtils.extractIntegerHeader((Map) headers, ResponseHeader.AD_TIMEOUT);
        if (adTimeoutDelaySeconds == null) {
            num = null;
        } else {
            num = Integer.valueOf(adTimeoutDelaySeconds.intValue() * 1000);
        }
        builder.setAdTimeoutDelayMilliseconds(num);
        String responseBody = parseStringBody(networkResponse);
        builder.setResponseBody(responseBody);
        if ("json".equals(adTypeString) || AdType.VIDEO_NATIVE.equals(adTypeString)) {
            try {
                builder.setJsonBody(new JSONObject(responseBody));
            } catch (Throwable e) {
                return Response.error(new MoPubNetworkError("Failed to decode body JSON for native ad format", e, Reason.BAD_BODY));
            }
        }
        builder.setCustomEventClassName(AdTypeTranslator.getCustomEventName(this.mAdFormat, adTypeString, fullAdTypeString, headers));
        MoPub$BrowserAgent browserAgent = MoPub$BrowserAgent.fromHeader(HeaderUtils.extractIntegerHeader((Map) headers, ResponseHeader.BROWSER_AGENT));
        MoPub.setBrowserAgentFromAdServer(browserAgent);
        builder.setBrowserAgent(browserAgent);
        String customEventData = HeaderUtils.extractHeader((Map) headers, ResponseHeader.CUSTOM_EVENT_DATA);
        if (TextUtils.isEmpty(customEventData)) {
            customEventData = HeaderUtils.extractHeader((Map) headers, ResponseHeader.NATIVE_PARAMS);
        }
        try {
            Map<String, String> serverExtras = Json.jsonStringToMap(customEventData);
            if (redirectUrl != null) {
                serverExtras.put(DataKeys.REDIRECT_URL_KEY, redirectUrl);
            }
            if (clickTrackingUrl != null) {
                serverExtras.put(DataKeys.CLICKTHROUGH_URL_KEY, clickTrackingUrl);
            }
            if (eventDataIsInResponseBody(adTypeString, fullAdTypeString)) {
                serverExtras.put(DataKeys.HTML_RESPONSE_BODY_KEY, responseBody);
                serverExtras.put(DataKeys.SCROLLABLE_KEY, Boolean.toString(isScrollable));
                serverExtras.put(DataKeys.CREATIVE_ORIENTATION_KEY, HeaderUtils.extractHeader((Map) headers, ResponseHeader.ORIENTATION));
            }
            if ("json".equals(adTypeString) || AdType.VIDEO_NATIVE.equals(adTypeString)) {
                String impressionMinVisiblePercent = HeaderUtils.extractPercentHeaderString(headers, ResponseHeader.IMPRESSION_MIN_VISIBLE_PERCENT);
                String impressionVisibleMS = HeaderUtils.extractHeader((Map) headers, ResponseHeader.IMPRESSION_VISIBLE_MS);
                String impressionMinVisiblePx = HeaderUtils.extractHeader((Map) headers, ResponseHeader.IMPRESSION_MIN_VISIBLE_PX);
                if (!TextUtils.isEmpty(impressionMinVisiblePercent)) {
                    serverExtras.put(DataKeys.IMPRESSION_MIN_VISIBLE_PERCENT, impressionMinVisiblePercent);
                }
                if (!TextUtils.isEmpty(impressionVisibleMS)) {
                    serverExtras.put(DataKeys.IMPRESSION_VISIBLE_MS, impressionVisibleMS);
                }
                if (!TextUtils.isEmpty(impressionMinVisiblePx)) {
                    serverExtras.put(DataKeys.IMPRESSION_MIN_VISIBLE_PX, impressionMinVisiblePx);
                }
            }
            if (AdType.VIDEO_NATIVE.equals(adTypeString)) {
                Double d;
                Float f;
                serverExtras.put(DataKeys.PLAY_VISIBLE_PERCENT, HeaderUtils.extractPercentHeaderString(headers, ResponseHeader.PLAY_VISIBLE_PERCENT));
                serverExtras.put(DataKeys.PAUSE_VISIBLE_PERCENT, HeaderUtils.extractPercentHeaderString(headers, ResponseHeader.PAUSE_VISIBLE_PERCENT));
                serverExtras.put(DataKeys.MAX_BUFFER_MS, HeaderUtils.extractHeader((Map) headers, ResponseHeader.MAX_BUFFER_MS));
                EventDetails.Builder dspCreativeId2 = new EventDetails.Builder().adUnitId(this.mAdUnitId).adType(adTypeString).adNetworkType(networkType).adWidthPx(width).adHeightPx(height).dspCreativeId(dspCreativeId);
                if (location == null) {
                    d = null;
                } else {
                    d = Double.valueOf(location.getLatitude());
                }
                dspCreativeId2 = dspCreativeId2.geoLatitude(d);
                if (location == null) {
                    d = null;
                } else {
                    d = Double.valueOf(location.getLongitude());
                }
                dspCreativeId2 = dspCreativeId2.geoLongitude(d);
                if (location == null) {
                    f = null;
                } else {
                    f = Float.valueOf(location.getAccuracy());
                }
                builder.setEventDetails(dspCreativeId2.geoAccuracy(f).performanceDurationMs(Long.valueOf(networkResponse.networkTimeMs)).requestId(requestId).requestStatusCode(Integer.valueOf(networkResponse.statusCode)).requestUri(getUrl()).build());
            }
            String videoTrackers = HeaderUtils.extractHeader((Map) headers, ResponseHeader.VIDEO_TRACKERS);
            if (videoTrackers != null) {
                serverExtras.put(DataKeys.VIDEO_TRACKERS_KEY, videoTrackers);
            }
            if (AdType.REWARDED_VIDEO.equals(adTypeString) || ("interstitial".equals(adTypeString) && FullAdType.VAST.equals(fullAdTypeString))) {
                serverExtras.put(DataKeys.EXTERNAL_VIDEO_VIEWABILITY_TRACKERS_KEY, HeaderUtils.extractHeader((Map) headers, ResponseHeader.VIDEO_VIEWABILITY_TRACKERS));
            }
            if (AdFormat.BANNER.equals(this.mAdFormat)) {
                serverExtras.put(DataKeys.BANNER_IMPRESSION_MIN_VISIBLE_MS, HeaderUtils.extractHeader((Map) headers, ResponseHeader.BANNER_IMPRESSION_MIN_VISIBLE_MS));
                serverExtras.put(DataKeys.BANNER_IMPRESSION_MIN_VISIBLE_DIPS, HeaderUtils.extractHeader((Map) headers, ResponseHeader.BANNER_IMPRESSION_MIN_VISIBLE_DIPS));
            }
            String disabledViewabilityVendors = HeaderUtils.extractHeader((Map) headers, ResponseHeader.DISABLE_VIEWABILITY);
            if (!TextUtils.isEmpty(disabledViewabilityVendors)) {
                ViewabilityVendor disabledVendors = ViewabilityVendor.fromKey(disabledViewabilityVendors);
                if (disabledVendors != null) {
                    disabledVendors.disable();
                }
            }
            builder.setServerExtras(serverExtras);
            if (AdType.REWARDED_VIDEO.equals(adTypeString) || AdType.CUSTOM.equals(adTypeString) || AdType.REWARDED_PLAYABLE.equals(adTypeString)) {
                String rewardedVideoCurrencyName = HeaderUtils.extractHeader((Map) headers, ResponseHeader.REWARDED_VIDEO_CURRENCY_NAME);
                String rewardedVideoCurrencyAmount = HeaderUtils.extractHeader((Map) headers, ResponseHeader.REWARDED_VIDEO_CURRENCY_AMOUNT);
                String rewardedCurrencies = HeaderUtils.extractHeader((Map) headers, ResponseHeader.REWARDED_CURRENCIES);
                String rewardedVideoCompletionUrl = HeaderUtils.extractHeader((Map) headers, ResponseHeader.REWARDED_VIDEO_COMPLETION_URL);
                Integer rewardedDuration = HeaderUtils.extractIntegerHeader((Map) headers, ResponseHeader.REWARDED_DURATION);
                boolean shouldRewardOnClick = HeaderUtils.extractBooleanHeader((Map) headers, ResponseHeader.SHOULD_REWARD_ON_CLICK, false);
                builder.setRewardedVideoCurrencyName(rewardedVideoCurrencyName);
                builder.setRewardedVideoCurrencyAmount(rewardedVideoCurrencyAmount);
                builder.setRewardedCurrencies(rewardedCurrencies);
                builder.setRewardedVideoCompletionUrl(rewardedVideoCompletionUrl);
                builder.setRewardedDuration(rewardedDuration);
                builder.setShouldRewardOnClick(shouldRewardOnClick);
            }
            logScribeEvent(builder.build(), networkResponse, location);
            return Response.success(builder.build(), HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (Throwable e2) {
            return Response.error(new MoPubNetworkError("Failed to decode server extras for custom event data.", e2, Reason.BAD_HEADER_DATA));
        }
    }

    private boolean eventDataIsInResponseBody(@Nullable String adType, @Nullable String fullAdType) {
        return AdType.MRAID.equals(adType) || "html".equals(adType) || (("interstitial".equals(adType) && FullAdType.VAST.equals(fullAdType)) || ((AdType.REWARDED_VIDEO.equals(adType) && FullAdType.VAST.equals(fullAdType)) || AdType.REWARDED_PLAYABLE.equals(adType)));
    }

    protected String parseStringBody(NetworkResponse response) {
        try {
            return new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            return new String(response.data);
        }
    }

    protected void deliverResponse(AdResponse adResponse) {
        this.mListener.onSuccess(adResponse);
    }

    @Nullable
    @VisibleForTesting
    String getRequestId(@Nullable String failUrl) {
        if (failUrl == null) {
            return null;
        }
        String requestId = null;
        try {
            return Uri.parse(failUrl).getQueryParameter("request_id");
        } catch (UnsupportedOperationException e) {
            MoPubLog.m12061d("Unable to obtain request id from fail url.");
            return requestId;
        }
    }

    @VisibleForTesting
    void logScribeEvent(@NonNull AdResponse adResponse, @NonNull NetworkResponse networkResponse, @Nullable Location location) {
        Double valueOf;
        Double d = null;
        Preconditions.checkNotNull(adResponse);
        Preconditions.checkNotNull(networkResponse);
        BaseEvent.Builder withAdWidthPx = new Event.Builder(Name.AD_REQUEST, Category.REQUESTS, SamplingRate.AD_REQUEST.getSamplingRate()).withAdUnitId(this.mAdUnitId).withDspCreativeId(adResponse.getDspCreativeId()).withAdType(adResponse.getAdType()).withAdNetworkType(adResponse.getNetworkType()).withAdWidthPx(adResponse.getWidth() != null ? Double.valueOf(adResponse.getWidth().doubleValue()) : null);
        if (adResponse.getHeight() != null) {
            valueOf = Double.valueOf(adResponse.getHeight().doubleValue());
        } else {
            valueOf = null;
        }
        withAdWidthPx = withAdWidthPx.withAdHeightPx(valueOf);
        if (location != null) {
            valueOf = Double.valueOf(location.getLatitude());
        } else {
            valueOf = null;
        }
        withAdWidthPx = withAdWidthPx.withGeoLat(valueOf);
        if (location != null) {
            valueOf = Double.valueOf(location.getLongitude());
        } else {
            valueOf = null;
        }
        BaseEvent.Builder withGeoLon = withAdWidthPx.withGeoLon(valueOf);
        if (location != null) {
            d = Double.valueOf((double) location.getAccuracy());
        }
        MoPubEvents.log(withGeoLon.withGeoAccuracy(d).withPerformanceDurationMs(Double.valueOf((double) networkResponse.networkTimeMs)).withRequestId(adResponse.getRequestId()).withRequestStatusCode(Integer.valueOf(networkResponse.statusCode)).withRequestUri(getUrl()).build());
    }
}
