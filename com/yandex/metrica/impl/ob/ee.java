package com.yandex.metrica.impl.ob;

import android.telephony.SubscriptionInfo;

public final class ee {
    private final Integer f12317a;
    private final Integer f12318b;
    private final boolean f12319c;
    private final String f12320d;
    private final String f12321e;

    public ee(Integer num, Integer num2, boolean z, String str, String str2) {
        this.f12317a = num;
        this.f12318b = num2;
        this.f12319c = z;
        this.f12320d = str;
        this.f12321e = str2;
    }

    public ee(SubscriptionInfo subscriptionInfo) {
        boolean z = true;
        this.f12317a = Integer.valueOf(subscriptionInfo.getMcc());
        this.f12318b = Integer.valueOf(subscriptionInfo.getMnc());
        if (subscriptionInfo.getDataRoaming() != 1) {
            z = false;
        }
        this.f12319c = z;
        this.f12320d = subscriptionInfo.getCarrierName().toString();
        this.f12321e = subscriptionInfo.getIccId();
    }

    public Integer m15891a() {
        return this.f12317a;
    }

    public Integer m15892b() {
        return this.f12318b;
    }

    public boolean m15893c() {
        return this.f12319c;
    }

    public String m15894d() {
        return this.f12320d;
    }

    public String m15895e() {
        return this.f12321e;
    }
}
