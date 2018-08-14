package com.facebook.ads;

import android.content.Context;
import com.facebook.ads.internal.p066i.C1995c;

public final class BidderTokenProvider {
    public static String getBidderToken(Context context) {
        return new C1995c(context, true).m6314a();
    }
}
