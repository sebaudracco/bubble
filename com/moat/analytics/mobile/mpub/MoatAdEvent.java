package com.moat.analytics.mobile.mpub;

import com.mopub.mobileads.dfp.adapters.MoPubAdapter;
import java.util.HashMap;
import java.util.Map;

public class MoatAdEvent {
    public static final Double VOLUME_MUTED = Double.valueOf(0.0d);
    public static final Double VOLUME_UNMUTED = Double.valueOf(MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE);
    static final Integer f8638 = Integer.valueOf(Integer.MIN_VALUE);
    private static final Double f8639 = Double.valueOf(Double.NaN);
    private final Long f8640;
    Double f8641;
    Integer f8642;
    MoatAdEventType f8643;
    private final Double f8644;

    public MoatAdEvent(MoatAdEventType moatAdEventType, Integer num, Double d) {
        this.f8640 = Long.valueOf(System.currentTimeMillis());
        this.f8643 = moatAdEventType;
        this.f8641 = d;
        this.f8642 = num;
        this.f8644 = Double.valueOf(C3450r.m11783());
    }

    public MoatAdEvent(MoatAdEventType moatAdEventType, Integer num) {
        this(moatAdEventType, num, f8639);
    }

    public MoatAdEvent(MoatAdEventType moatAdEventType) {
        this(moatAdEventType, f8638, f8639);
    }

    final Map<String, Object> m11635() {
        Map<String, Object> hashMap = new HashMap();
        hashMap.put(com.moat.analytics.mobile.inm.MoatAdEvent.EVENT_AD_VOLUME, this.f8641);
        hashMap.put(com.moat.analytics.mobile.inm.MoatAdEvent.EVENT_PLAY_HEAD, this.f8642);
        hashMap.put(com.moat.analytics.mobile.inm.MoatAdEvent.EVENT_TS, this.f8640);
        hashMap.put("type", this.f8643.toString());
        hashMap.put("deviceVolume", this.f8644);
        return hashMap;
    }
}
