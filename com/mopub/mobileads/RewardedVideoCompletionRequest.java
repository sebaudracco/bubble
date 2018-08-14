package com.mopub.mobileads;

import android.support.annotation.NonNull;
import com.mopub.volley.NetworkResponse;
import com.mopub.volley.Request;
import com.mopub.volley.Response;
import com.mopub.volley.Response.ErrorListener;
import com.mopub.volley.RetryPolicy;
import com.mopub.volley.toolbox.HttpHeaderParser;

public class RewardedVideoCompletionRequest extends Request<Integer> {
    @NonNull
    final RewardedVideoCompletionRequestListener mListener;

    public interface RewardedVideoCompletionRequestListener extends ErrorListener {
        void onResponse(Integer num);
    }

    public RewardedVideoCompletionRequest(@NonNull String url, @NonNull RetryPolicy retryPolicy, @NonNull RewardedVideoCompletionRequestListener listener) {
        super(0, url, listener);
        setShouldCache(false);
        setRetryPolicy(retryPolicy);
        this.mListener = listener;
    }

    protected Response<Integer> parseNetworkResponse(NetworkResponse networkResponse) {
        return Response.success(Integer.valueOf(networkResponse.statusCode), HttpHeaderParser.parseCacheHeaders(networkResponse));
    }

    protected void deliverResponse(Integer response) {
        this.mListener.onResponse(response);
    }
}
