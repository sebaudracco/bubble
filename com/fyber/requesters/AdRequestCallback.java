package com.fyber.requesters;

import com.fyber.ads.Ad;
import com.fyber.ads.AdFormat;

public interface AdRequestCallback extends Callback {
    void onAdAvailable(Ad ad);

    void onAdNotAvailable(AdFormat adFormat);
}
