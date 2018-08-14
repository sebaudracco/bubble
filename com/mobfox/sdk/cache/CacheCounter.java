package com.mobfox.sdk.cache;

import android.content.Context;
import com.mobfox.sdk.networking.AsyncCallback;
import com.mobfox.sdk.networking.MobFoxRequest;
import java.util.List;
import java.util.Map;

public class CacheCounter {
    Context context;
    private int runningTasks;
    List<String> urls;

    public CacheCounter(List<String> cacheURLs, Context c) {
        this.urls = cacheURLs;
        this.context = c;
    }

    public void cache(final CacheCounterCB cb) {
        if (this.urls.size() == 0) {
            cb.onDone();
            return;
        }
        this.runningTasks = this.urls.size();
        for (String cacheURL : this.urls) {
            MobFoxRequest req = new MobFoxRequest(this.context, cacheURL);
            req.setTimeout(7000);
            req.get(new AsyncCallback() {
                public void onComplete(int code, Object response, Map<String, List<String>> map) {
                    CacheCounter.this.cacheURLFinished(cb);
                }

                public void onError(Exception e) {
                    CacheCounter.this.cacheURLFinished(cb);
                }
            });
        }
    }

    public void cacheURLFinished(CacheCounterCB cb) {
        int i = this.runningTasks - 1;
        this.runningTasks = i;
        if (i == 0) {
            cb.onDone();
        }
    }
}
