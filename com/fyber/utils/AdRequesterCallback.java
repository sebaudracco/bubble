package com.fyber.utils;

import android.content.Intent;
import com.fyber.ads.AdFormat;
import com.fyber.requesters.RequestCallback;
import com.fyber.requesters.RequestError;
import com.fyber.requesters.Requester;

public abstract class AdRequesterCallback implements RequestCallback {
    protected Requester f6562a;

    public AdRequesterCallback(Requester requester) {
        this.f6562a = requester;
    }

    public void onAdAvailable(Intent intent) {
    }

    public void onAdNotAvailable(AdFormat adFormat) {
    }

    public void onRequestError(RequestError requestError) {
    }
}
