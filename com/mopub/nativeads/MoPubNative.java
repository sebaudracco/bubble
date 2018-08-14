package com.mopub.nativeads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.cuebiq.cuebiqsdk.model.config.Settings;
import com.mopub.common.AdFormat;
import com.mopub.common.Constants;
import com.mopub.common.GpsHelper;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.DeviceUtils;
import com.mopub.common.util.ManifestUtils;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.network.AdRequest;
import com.mopub.network.AdRequest.Listener;
import com.mopub.network.AdResponse;
import com.mopub.network.MoPubNetworkError;
import com.mopub.network.Networking;
import com.mopub.volley.NetworkResponse;
import com.mopub.volley.VolleyError;
import cz.msebera.android.httpclient.HttpStatus;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.TreeMap;

public class MoPubNative {
    static final MoPubNativeNetworkListener EMPTY_NETWORK_LISTENER = new 1();
    @NonNull
    AdRendererRegistry mAdRendererRegistry;
    @NonNull
    private final String mAdUnitId;
    @NonNull
    private final WeakReference<Context> mContext;
    @NonNull
    private Map<String, Object> mLocalExtras;
    @NonNull
    private MoPubNativeNetworkListener mMoPubNativeNetworkListener;
    @Nullable
    private AdRequest mNativeRequest;
    @NonNull
    private final Listener mVolleyListener;

    public MoPubNative(@NonNull Context context, @NonNull String adUnitId, @NonNull MoPubNativeNetworkListener moPubNativeNetworkListener) {
        this(context, adUnitId, new AdRendererRegistry(), moPubNativeNetworkListener);
    }

    @VisibleForTesting
    public MoPubNative(@NonNull Context context, @NonNull String adUnitId, @NonNull AdRendererRegistry adRendererRegistry, @NonNull MoPubNativeNetworkListener moPubNativeNetworkListener) {
        this.mLocalExtras = new TreeMap();
        Preconditions.checkNotNull(context, "context may not be null.");
        Preconditions.checkNotNull(adUnitId, "AdUnitId may not be null.");
        Preconditions.checkNotNull(adRendererRegistry, "AdRendererRegistry may not be null.");
        Preconditions.checkNotNull(moPubNativeNetworkListener, "MoPubNativeNetworkListener may not be null.");
        ManifestUtils.checkNativeActivitiesDeclared(context);
        this.mContext = new WeakReference(context);
        this.mAdUnitId = adUnitId;
        this.mMoPubNativeNetworkListener = moPubNativeNetworkListener;
        this.mAdRendererRegistry = adRendererRegistry;
        this.mVolleyListener = new 2(this);
        GpsHelper.fetchAdvertisingInfoAsync(context, null);
    }

    public void registerAdRenderer(MoPubAdRenderer moPubAdRenderer) {
        this.mAdRendererRegistry.registerAdRenderer(moPubAdRenderer);
    }

    public void destroy() {
        this.mContext.clear();
        if (this.mNativeRequest != null) {
            this.mNativeRequest.cancel();
            this.mNativeRequest = null;
        }
        this.mMoPubNativeNetworkListener = EMPTY_NETWORK_LISTENER;
    }

    public void setLocalExtras(@Nullable Map<String, Object> localExtras) {
        if (localExtras == null) {
            this.mLocalExtras = new TreeMap();
        } else {
            this.mLocalExtras = new TreeMap(localExtras);
        }
    }

    public void makeRequest() {
        makeRequest((RequestParameters) null);
    }

    public void makeRequest(@Nullable RequestParameters requestParameters) {
        makeRequest(requestParameters, null);
    }

    public void makeRequest(@Nullable RequestParameters requestParameters, @Nullable Integer sequenceNumber) {
        Context context = getContextOrDestroy();
        if (context != null) {
            if (DeviceUtils.isNetworkAvailable(context)) {
                loadNativeAd(requestParameters, sequenceNumber);
            } else {
                this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.CONNECTION_ERROR);
            }
        }
    }

    private void loadNativeAd(@Nullable RequestParameters requestParameters, @Nullable Integer sequenceNumber) {
        Context context = getContextOrDestroy();
        if (context != null) {
            NativeUrlGenerator generator = new NativeUrlGenerator(context).withAdUnitId(this.mAdUnitId).withRequest(requestParameters);
            if (sequenceNumber != null) {
                generator.withSequenceNumber(sequenceNumber.intValue());
            }
            String endpointUrl = generator.generateUrlString(Constants.HOST);
            if (endpointUrl != null) {
                MoPubLog.d("Loading ad from: " + endpointUrl);
            }
            requestNativeAd(endpointUrl);
        }
    }

    void requestNativeAd(@Nullable String endpointUrl) {
        Context context = getContextOrDestroy();
        if (context != null) {
            if (endpointUrl == null) {
                this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.INVALID_REQUEST_URL);
                return;
            }
            this.mNativeRequest = new AdRequest(endpointUrl, AdFormat.NATIVE, this.mAdUnitId, context, this.mVolleyListener);
            Networking.getRequestQueue(context).add(this.mNativeRequest);
        }
    }

    private void onAdLoad(@NonNull AdResponse response) {
        Context context = getContextOrDestroy();
        if (context != null) {
            CustomEventNativeAdapter.loadNativeAd(context, this.mLocalExtras, response, new 3(this, response));
        }
    }

    @VisibleForTesting
    void onAdError(@NonNull VolleyError volleyError) {
        MoPubLog.d("Native ad request failed.", volleyError);
        if (volleyError instanceof MoPubNetworkError) {
            switch (4.$SwitchMap$com$mopub$network$MoPubNetworkError$Reason[((MoPubNetworkError) volleyError).getReason().ordinal()]) {
                case 1:
                    this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.INVALID_RESPONSE);
                    return;
                case 2:
                    this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.INVALID_RESPONSE);
                    return;
                case 3:
                    MoPubLog.c(MoPubErrorCode.WARMUP.toString());
                    this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.EMPTY_AD_RESPONSE);
                    return;
                case 4:
                    this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.EMPTY_AD_RESPONSE);
                    return;
                default:
                    this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.UNSPECIFIED);
                    return;
            }
        }
        NetworkResponse response = volleyError.networkResponse;
        if (response != null && response.statusCode >= HttpStatus.SC_INTERNAL_SERVER_ERROR && response.statusCode < Settings.MAX_DYNAMIC_ACQUISITION) {
            this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.SERVER_ERROR_RESPONSE_CODE);
        } else if (response != null || DeviceUtils.isNetworkAvailable((Context) this.mContext.get())) {
            this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.UNSPECIFIED);
        } else {
            MoPubLog.c(String.valueOf(MoPubErrorCode.NO_CONNECTION.toString()));
            this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.CONNECTION_ERROR);
        }
    }

    @Nullable
    @VisibleForTesting
    Context getContextOrDestroy() {
        Context context = (Context) this.mContext.get();
        if (context == null) {
            destroy();
            MoPubLog.d("Weak reference to Context in MoPubNative became null. This instance of MoPubNative is destroyed and No more requests will be processed.");
        }
        return context;
    }

    @Deprecated
    @NonNull
    @VisibleForTesting
    MoPubNativeNetworkListener getMoPubNativeNetworkListener() {
        return this.mMoPubNativeNetworkListener;
    }
}
