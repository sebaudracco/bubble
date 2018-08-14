package com.appsgeyser.sdk.ads.sdk;

import android.content.Context;
import com.appnext.base.Appnext;

public class SdkController {
    public static void initSdk(Context context) {
        Appnext.init(context);
    }
}
