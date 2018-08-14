package com.mopub.network;

import android.support.annotation.NonNull;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.volley.Cache;
import com.mopub.volley.Network;
import com.mopub.volley.Request;
import com.mopub.volley.RequestQueue;
import com.mopub.volley.RequestQueue.RequestFilter;
import com.mopub.volley.ResponseDelivery;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class MoPubRequestQueue extends RequestQueue {
    private static final int CAPACITY = 10;
    @NonNull
    private final Map<Request<?>, DelayedRequestHelper> mDelayedRequests = new HashMap(10);

    MoPubRequestQueue(Cache cache, Network network, int threadPoolSize, ResponseDelivery delivery) {
        super(cache, network, threadPoolSize, delivery);
    }

    MoPubRequestQueue(Cache cache, Network network, int threadPoolSize) {
        super(cache, network, threadPoolSize);
    }

    MoPubRequestQueue(Cache cache, Network network) {
        super(cache, network);
    }

    public void addDelayedRequest(@NonNull Request<?> request, int delayMs) {
        Preconditions.checkNotNull(request);
        addDelayedRequest((Request) request, new DelayedRequestHelper(this, request, delayMs));
    }

    @VisibleForTesting
    void addDelayedRequest(@NonNull Request<?> request, @NonNull DelayedRequestHelper delayedRequestHelper) {
        Preconditions.checkNotNull(delayedRequestHelper);
        if (this.mDelayedRequests.containsKey(request)) {
            cancel(request);
        }
        delayedRequestHelper.start();
        this.mDelayedRequests.put(request, delayedRequestHelper);
    }

    public void cancelAll(@NonNull RequestFilter filter) {
        Preconditions.checkNotNull(filter);
        super.cancelAll(filter);
        Iterator<Entry<Request<?>, DelayedRequestHelper>> iterator = this.mDelayedRequests.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<Request<?>, DelayedRequestHelper> entry = (Entry) iterator.next();
            if (filter.apply((Request) entry.getKey())) {
                ((Request) entry.getKey()).cancel();
                ((DelayedRequestHelper) entry.getValue()).cancel();
                iterator.remove();
            }
        }
    }

    public void cancelAll(@NonNull Object tag) {
        Preconditions.checkNotNull(tag);
        super.cancelAll(tag);
        cancelAll(new 1(this, tag));
    }

    public void cancel(@NonNull Request<?> request) {
        Preconditions.checkNotNull(request);
        cancelAll(new 2(this, request));
    }

    @NonNull
    @Deprecated
    @VisibleForTesting
    Map<Request<?>, DelayedRequestHelper> getDelayedRequests() {
        return this.mDelayedRequests;
    }
}
