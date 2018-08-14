package com.google.android.gms.ads;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzlw;
import java.util.Date;
import java.util.Set;

@VisibleForTesting
public final class AdRequest {
    public static final String DEVICE_ID_EMULATOR = "B3EEABB8EE11C2BE770B684D95219ECB";
    public static final int ERROR_CODE_INTERNAL_ERROR = 0;
    public static final int ERROR_CODE_INVALID_REQUEST = 1;
    public static final int ERROR_CODE_NETWORK_ERROR = 2;
    public static final int ERROR_CODE_NO_FILL = 3;
    public static final int GENDER_FEMALE = 2;
    public static final int GENDER_MALE = 1;
    public static final int GENDER_UNKNOWN = 0;
    public static final int MAX_CONTENT_URL_LENGTH = 512;
    private final zzlw zzun;

    private AdRequest(Builder builder) {
        this.zzun = new zzlw(Builder.zza(builder));
    }

    public final Date getBirthday() {
        return this.zzun.getBirthday();
    }

    public final String getContentUrl() {
        return this.zzun.getContentUrl();
    }

    public final <T extends CustomEvent> Bundle getCustomEventExtrasBundle(Class<T> cls) {
        return this.zzun.getCustomEventExtrasBundle(cls);
    }

    public final int getGender() {
        return this.zzun.getGender();
    }

    public final Set<String> getKeywords() {
        return this.zzun.getKeywords();
    }

    public final Location getLocation() {
        return this.zzun.getLocation();
    }

    @Deprecated
    public final <T extends NetworkExtras> T getNetworkExtras(Class<T> cls) {
        return this.zzun.getNetworkExtras(cls);
    }

    public final <T extends MediationAdapter> Bundle getNetworkExtrasBundle(Class<T> cls) {
        return this.zzun.getNetworkExtrasBundle(cls);
    }

    public final boolean isTestDevice(Context context) {
        return this.zzun.isTestDevice(context);
    }

    public final zzlw zzay() {
        return this.zzun;
    }
}
