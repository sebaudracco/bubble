package com.mopub.network;

import android.os.Looper;
import android.support.annotation.NonNull;
import com.mopub.common.logging.MoPubLog;
import com.mopub.volley.Request;
import com.mopub.volley.VolleyError;

public class ScribeRequestManager extends RequestManager<ScribeRequest$ScribeRequestFactory> implements ScribeRequest$Listener {

    class C37581 implements Runnable {
        C37581() {
        }

        public void run() {
            ScribeRequestManager.this.clearRequest();
        }
    }

    public ScribeRequestManager(Looper looper) {
        super(looper);
    }

    @NonNull
    Request<?> createRequest() {
        return ((ScribeRequest$ScribeRequestFactory) this.mRequestFactory).createRequest(this);
    }

    public void onResponse() {
        MoPubLog.m12061d("Successfully scribed events");
        this.mHandler.post(new C37581());
    }

    public void onErrorResponse(final VolleyError volleyError) {
        this.mHandler.post(new Runnable() {
            public void run() {
                try {
                    ScribeRequestManager.this.mBackoffPolicy.backoff(volleyError);
                    ScribeRequestManager.this.makeRequestInternal();
                } catch (VolleyError e) {
                    MoPubLog.m12061d("Failed to Scribe events: " + volleyError);
                    ScribeRequestManager.this.clearRequest();
                }
            }
        });
    }
}
