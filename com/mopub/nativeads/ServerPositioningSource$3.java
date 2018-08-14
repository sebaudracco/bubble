package com.mopub.nativeads;

import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.DeviceUtils;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.network.MoPubNetworkError;
import com.mopub.network.MoPubNetworkError.Reason;
import com.mopub.volley.Response.ErrorListener;
import com.mopub.volley.VolleyError;

class ServerPositioningSource$3 implements ErrorListener {
    final /* synthetic */ ServerPositioningSource this$0;

    ServerPositioningSource$3(ServerPositioningSource this$0) {
        this.this$0 = this$0;
    }

    public void onErrorResponse(VolleyError error) {
        if (!(error instanceof MoPubNetworkError) || ((MoPubNetworkError) error).getReason().equals(Reason.WARMING_UP)) {
            MoPubLog.m12064e("Failed to load positioning data", error);
            if (error.networkResponse == null && !DeviceUtils.isNetworkAvailable(ServerPositioningSource.access$200(this.this$0))) {
                MoPubLog.m12059c(String.valueOf(MoPubErrorCode.NO_CONNECTION.toString()));
            }
        }
        ServerPositioningSource.access$300(this.this$0);
    }
}
