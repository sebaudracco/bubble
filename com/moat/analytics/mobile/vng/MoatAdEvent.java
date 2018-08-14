package com.moat.analytics.mobile.vng;

import com.mopub.mobileads.dfp.adapters.MoPubAdapter;
import java.util.HashMap;
import java.util.Map;

public class MoatAdEvent {
    public static final Double VOLUME_MUTED = Double.valueOf(0.0d);
    public static final Double VOLUME_UNMUTED = Double.valueOf(MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE);
    static final Integer f8852a = Integer.valueOf(Integer.MIN_VALUE);
    private static final Double f8853e = Double.valueOf(Double.NaN);
    Integer f8854b;
    Double f8855c;
    MoatAdEventType f8856d;
    private final Long f8857f;

    public MoatAdEvent(MoatAdEventType moatAdEventType) {
        this(moatAdEventType, f8852a, f8853e);
    }

    public MoatAdEvent(MoatAdEventType moatAdEventType, Integer num) {
        this(moatAdEventType, num, f8853e);
    }

    public MoatAdEvent(MoatAdEventType moatAdEventType, Integer num, Double d) {
        this.f8857f = Long.valueOf(System.currentTimeMillis());
        this.f8856d = moatAdEventType;
        this.f8855c = d;
        this.f8854b = num;
    }

    Map<String, Object> m11828a() {
        Map<String, Object> hashMap = new HashMap();
        hashMap.put(com.moat.analytics.mobile.inm.MoatAdEvent.EVENT_AD_VOLUME, this.f8855c);
        hashMap.put(com.moat.analytics.mobile.inm.MoatAdEvent.EVENT_PLAY_HEAD, this.f8854b);
        hashMap.put(com.moat.analytics.mobile.inm.MoatAdEvent.EVENT_TS, this.f8857f);
        hashMap.put("type", this.f8856d.toString());
        return hashMap;
    }
}
