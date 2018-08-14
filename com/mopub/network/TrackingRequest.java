package com.mopub.network;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.Preconditions;
import com.mopub.common.event.BaseEvent.Name;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.VastErrorCode;
import com.mopub.mobileads.VastMacroHelper;
import com.mopub.mobileads.VastTracker;
import com.mopub.network.MoPubNetworkError.Reason;
import com.mopub.volley.DefaultRetryPolicy;
import com.mopub.volley.NetworkResponse;
import com.mopub.volley.Request;
import com.mopub.volley.RequestQueue;
import com.mopub.volley.Response;
import com.mopub.volley.Response.ErrorListener;
import com.mopub.volley.VolleyError;
import com.mopub.volley.toolbox.HttpHeaderParser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrackingRequest extends Request<Void> {
    private static final int ZERO_RETRIES = 0;
    @Nullable
    private final Listener mListener;

    public interface Listener extends ErrorListener {
        void onResponse(@NonNull String str);
    }

    private TrackingRequest(@NonNull String url, @Nullable Listener listener) {
        super(0, url, listener);
        this.mListener = listener;
        setShouldCache(false);
        setRetryPolicy(new DefaultRetryPolicy(2500, 0, 1.0f));
    }

    protected Response<Void> parseNetworkResponse(NetworkResponse networkResponse) {
        if (networkResponse.statusCode != 200) {
            return Response.error(new MoPubNetworkError("Failed to log tracking request. Response code: " + networkResponse.statusCode + " for url: " + getUrl(), Reason.TRACKING_FAILURE));
        }
        return Response.success(null, HttpHeaderParser.parseCacheHeaders(networkResponse));
    }

    public void deliverResponse(Void aVoid) {
        if (this.mListener != null) {
            this.mListener.onResponse(getUrl());
        }
    }

    public static void makeVastTrackingHttpRequest(@NonNull List<VastTracker> vastTrackers, @Nullable VastErrorCode vastErrorCode, @Nullable Integer contentPlayHead, @Nullable String assetUri, @Nullable Context context) {
        Preconditions.checkNotNull(vastTrackers);
        List<String> trackers = new ArrayList(vastTrackers.size());
        for (VastTracker vastTracker : vastTrackers) {
            if (vastTracker != null && (!vastTracker.isTracked() || vastTracker.isRepeatable())) {
                trackers.add(vastTracker.getContent());
                vastTracker.setTracked();
            }
        }
        makeTrackingHttpRequest(new VastMacroHelper(trackers).withErrorCode(vastErrorCode).withContentPlayHead(contentPlayHead).withAssetUri(assetUri).getUris(), context);
    }

    public static void makeTrackingHttpRequest(@Nullable Iterable<String> urls, @Nullable Context context, @Nullable final Listener listener, Name name) {
        if (urls != null && context != null) {
            RequestQueue requestQueue = Networking.getRequestQueue(context);
            for (final String url : urls) {
                if (!TextUtils.isEmpty(url)) {
                    requestQueue.add(new TrackingRequest(url, new Listener() {
                        public void onResponse(@NonNull String url) {
                            MoPubLog.m12061d("Successfully hit tracking endpoint: " + url);
                            if (listener != null) {
                                listener.onResponse(url);
                            }
                        }

                        public void onErrorResponse(VolleyError volleyError) {
                            MoPubLog.m12061d("Failed to hit tracking endpoint: " + url);
                            if (listener != null) {
                                listener.onErrorResponse(volleyError);
                            }
                        }
                    }));
                }
            }
        }
    }

    public static void makeTrackingHttpRequest(@Nullable String url, @Nullable Context context) {
        makeTrackingHttpRequest(url, context, null, null);
    }

    public static void makeTrackingHttpRequest(@Nullable String url, @Nullable Context context, @Nullable Listener listener) {
        makeTrackingHttpRequest(url, context, listener, null);
    }

    public static void makeTrackingHttpRequest(@Nullable String url, @Nullable Context context, Name name) {
        makeTrackingHttpRequest(url, context, null, name);
    }

    public static void makeTrackingHttpRequest(@Nullable String url, @Nullable Context context, @Nullable Listener listener, Name name) {
        if (url != null) {
            makeTrackingHttpRequest(Arrays.asList(new String[]{url}), context, listener, name);
        }
    }

    public static void makeTrackingHttpRequest(@Nullable Iterable<String> urls, @Nullable Context context) {
        makeTrackingHttpRequest((Iterable) urls, context, null, null);
    }

    public static void makeTrackingHttpRequest(@Nullable Iterable<String> urls, @Nullable Context context, Name name) {
        makeTrackingHttpRequest((Iterable) urls, context, null, name);
    }
}
