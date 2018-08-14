package com.facebook.ads.internal.p056q.p057a;

import android.os.Build.VERSION;
import android.view.View;

public enum C2118i {
    INTERNAL_NO_TAG(0),
    INTERNAL_NO_CLICK(1),
    INTERNAL_API_TOO_LOW(2),
    INTERNAL_WRONG_TAG_CLASS(3),
    INTERNAL_NULL_VIEW(4),
    INTERNAL_AD_ICON(5),
    INTERNAL_AD_TITLE(6),
    INTERNAL_AD_COVER_IMAGE(7),
    INTERNAL_AD_SUBTITLE(8),
    INTERNAL_AD_BODY(9),
    INTERNAL_AD_CALL_TO_ACTION(10),
    INTERNAL_AD_SOCIAL_CONTEXT(11),
    INTERNAL_AD_CHOICES_ICON(12),
    INTERNAL_AD_MEDIA(13);
    
    public static int f5042o;
    private final int f5044p;

    static {
        f5042o = -1593835521;
    }

    private C2118i(int i) {
        this.f5044p = i;
    }

    public static void m6796a(View view, C2118i c2118i) {
        if (view != null && c2118i != null && VERSION.SDK_INT > 4) {
            view.setTag(f5042o, c2118i);
        }
    }

    public int m6797a() {
        return this.f5044p;
    }
}
