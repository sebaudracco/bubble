package com.mopub.network;

import android.support.annotation.NonNull;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.event.BaseEvent;
import com.mopub.common.event.EventSerializer;
import com.mopub.volley.DefaultRetryPolicy;
import com.mopub.volley.NetworkResponse;
import com.mopub.volley.Request;
import com.mopub.volley.Response;
import com.mopub.volley.toolbox.HttpHeaderParser;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;

public class ScribeRequest extends Request<Void> {
    @NonNull
    private final EventSerializer mEventSerializer;
    @NonNull
    private final List<BaseEvent> mEvents;
    @NonNull
    private final Listener mListener;

    public ScribeRequest(@NonNull String url, @NonNull List<BaseEvent> events, @NonNull EventSerializer eventSerializer, @NonNull Listener listener) {
        super(1, url, listener);
        this.mEvents = events;
        this.mEventSerializer = eventSerializer;
        this.mListener = listener;
        setShouldCache(false);
        setRetryPolicy(new DefaultRetryPolicy());
    }

    protected Map<String, String> getParams() {
        JSONArray jsonArray = this.mEventSerializer.serializeAsJson(this.mEvents);
        Map<String, String> params = new HashMap();
        params.put("log", jsonArray.toString());
        return params;
    }

    protected Response<Void> parseNetworkResponse(NetworkResponse networkResponse) {
        return Response.success(null, HttpHeaderParser.parseCacheHeaders(networkResponse));
    }

    protected void deliverResponse(Void aVoid) {
        this.mListener.onResponse();
    }

    @NonNull
    @Deprecated
    @VisibleForTesting
    public List<BaseEvent> getEvents() {
        return this.mEvents;
    }
}
