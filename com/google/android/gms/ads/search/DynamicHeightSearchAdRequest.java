package com.google.android.gms.ads.search;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.internal.ads.zzlw;

public final class DynamicHeightSearchAdRequest {
    private final SearchAdRequest zzdgw;

    private DynamicHeightSearchAdRequest(Builder builder) {
        this.zzdgw = Builder.zza(builder).build();
    }

    public final <T extends CustomEvent> Bundle getCustomEventExtrasBundle(Class<T> cls) {
        return this.zzdgw.getCustomEventExtrasBundle(cls);
    }

    @Deprecated
    public final <T extends NetworkExtras> T getNetworkExtras(Class<T> cls) {
        return this.zzdgw.getNetworkExtras(cls);
    }

    public final <T extends MediationAdapter> Bundle getNetworkExtrasBundle(Class<T> cls) {
        return this.zzdgw.getNetworkExtrasBundle(cls);
    }

    public final String getQuery() {
        return this.zzdgw.getQuery();
    }

    public final boolean isTestDevice(Context context) {
        return this.zzdgw.isTestDevice(context);
    }

    final zzlw zzay() {
        return this.zzdgw.zzay();
    }
}
