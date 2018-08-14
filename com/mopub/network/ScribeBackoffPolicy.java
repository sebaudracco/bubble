package com.mopub.network;

import com.mopub.common.VisibleForTesting;
import com.mopub.volley.NetworkResponse;
import com.mopub.volley.NoConnectionError;
import com.mopub.volley.VolleyError;
import cz.msebera.android.httpclient.HttpStatus;

public class ScribeBackoffPolicy extends BackoffPolicy {
    private static final int BACKOFF_MULTIPLIER = 2;
    private static final int DEFAULT_BACKOFF_TIME_MS = 60000;
    private static final int MAX_RETRIES = 5;

    public ScribeBackoffPolicy() {
        this(DEFAULT_BACKOFF_TIME_MS, 5, 2);
    }

    @VisibleForTesting
    ScribeBackoffPolicy(int defaultBackoffTimeMs, int maxRetries, int backoffMultiplier) {
        this.mDefaultBackoffTimeMs = defaultBackoffTimeMs;
        this.mMaxRetries = maxRetries;
        this.mBackoffMultiplier = backoffMultiplier;
    }

    public void backoff(VolleyError volleyError) throws VolleyError {
        if (!hasAttemptRemaining()) {
            throw volleyError;
        } else if (volleyError instanceof NoConnectionError) {
            updateBackoffTime();
        } else {
            NetworkResponse networkResponse = volleyError.networkResponse;
            if (networkResponse == null || !(networkResponse.statusCode == HttpStatus.SC_SERVICE_UNAVAILABLE || networkResponse.statusCode == HttpStatus.SC_GATEWAY_TIMEOUT)) {
                throw volleyError;
            }
            updateBackoffTime();
        }
    }

    private void updateBackoffTime() {
        this.mBackoffMs = (int) (((double) this.mDefaultBackoffTimeMs) * Math.pow((double) this.mBackoffMultiplier, (double) this.mRetryCount));
        this.mRetryCount++;
    }
}
