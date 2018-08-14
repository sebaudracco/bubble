package com.google.android.gms.ads;

import android.location.Location;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzlx;
import java.util.Date;

@VisibleForTesting
public final class AdRequest$Builder {
    private final zzlx zzuo = new zzlx();

    public AdRequest$Builder() {
        this.zzuo.zzad("B3EEABB8EE11C2BE770B684D95219ECB");
    }

    public final AdRequest$Builder addCustomEventExtrasBundle(Class<? extends CustomEvent> cls, Bundle bundle) {
        this.zzuo.zzb(cls, bundle);
        return this;
    }

    public final AdRequest$Builder addKeyword(String str) {
        this.zzuo.zzac(str);
        return this;
    }

    public final AdRequest$Builder addNetworkExtras(NetworkExtras networkExtras) {
        this.zzuo.zza(networkExtras);
        return this;
    }

    public final AdRequest$Builder addNetworkExtrasBundle(Class<? extends MediationAdapter> cls, Bundle bundle) {
        this.zzuo.zza(cls, bundle);
        if (cls.equals(AdMobAdapter.class) && bundle.getBoolean("_emulatorLiveAds")) {
            this.zzuo.zzae("B3EEABB8EE11C2BE770B684D95219ECB");
        }
        return this;
    }

    public final AdRequest$Builder addTestDevice(String str) {
        this.zzuo.zzad(str);
        return this;
    }

    public final AdRequest build() {
        return new AdRequest(this, null);
    }

    public final AdRequest$Builder setBirthday(Date date) {
        this.zzuo.zza(date);
        return this;
    }

    public final AdRequest$Builder setContentUrl(String str) {
        Preconditions.checkNotNull(str, "Content URL must be non-null.");
        Preconditions.checkNotEmpty(str, "Content URL must be non-empty.");
        Preconditions.checkArgument(str.length() <= 512, "Content URL must not exceed %d in length.  Provided length was %d.", new Object[]{Integer.valueOf(512), Integer.valueOf(str.length())});
        this.zzuo.zzaf(str);
        return this;
    }

    public final AdRequest$Builder setGender(int i) {
        this.zzuo.zzt(i);
        return this;
    }

    public final AdRequest$Builder setIsDesignedForFamilies(boolean z) {
        this.zzuo.zzk(z);
        return this;
    }

    public final AdRequest$Builder setLocation(Location location) {
        this.zzuo.zzb(location);
        return this;
    }

    public final AdRequest$Builder setRequestAgent(String str) {
        this.zzuo.zzah(str);
        return this;
    }

    public final AdRequest$Builder tagForChildDirectedTreatment(boolean z) {
        this.zzuo.zzj(z);
        return this;
    }
}
