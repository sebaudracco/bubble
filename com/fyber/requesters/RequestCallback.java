package com.fyber.requesters;

import android.content.Intent;
import com.fyber.ads.AdFormat;

public interface RequestCallback extends Callback {
    void onAdAvailable(Intent intent);

    void onAdNotAvailable(AdFormat adFormat);
}
