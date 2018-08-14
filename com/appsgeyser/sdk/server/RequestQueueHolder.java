package com.appsgeyser.sdk.server;

import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.ArrayList;

public class RequestQueueHolder {
    private static ArrayList<String> deferredUrls = null;
    private static RequestQueueHolder instance;
    private static volatile boolean ready = false;
    private RequestQueue queue = null;

    private RequestQueueHolder(Context context) {
        this.queue = Volley.newRequestQueue(context);
        ready = true;
    }

    public static RequestQueueHolder getInstance(Context context) {
        if (instance == null) {
            instance = new RequestQueueHolder(context);
        }
        return instance;
    }

    static void addUrl(String url) {
        if (instance == null) {
            if (deferredUrls == null) {
                deferredUrls = new ArrayList();
            }
            deferredUrls.add(url);
            return;
        }
        instance.getQueue().add(new StringRequest(url, null, null));
    }

    public RequestQueue getQueue() {
        return this.queue;
    }

    public static boolean ready() {
        return ready;
    }
}
