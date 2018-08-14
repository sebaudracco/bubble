package com.google.android.gms.internal.ads;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.ads.search.SearchAdRequest;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@zzadh
public final class zzlw {
    private final int zzaqn;
    private final int zzaqq;
    private final String zzaqr;
    private final String zzaqt;
    private final Bundle zzaqv;
    private final String zzaqx;
    private final boolean zzaqz;
    private final Bundle zzask;
    private final Map<Class<? extends NetworkExtras>, NetworkExtras> zzasl;
    private final SearchAdRequest zzasm;
    private final Set<String> zzasn;
    private final Set<String> zzaso;
    private final Date zzhl;
    private final Set<String> zzhn;
    private final Location zzhp;
    private final boolean zzvm;

    public zzlw(zzlx com_google_android_gms_internal_ads_zzlx) {
        this(com_google_android_gms_internal_ads_zzlx, null);
    }

    public zzlw(zzlx com_google_android_gms_internal_ads_zzlx, SearchAdRequest searchAdRequest) {
        this.zzhl = com_google_android_gms_internal_ads_zzlx.zzhl;
        this.zzaqt = com_google_android_gms_internal_ads_zzlx.zzaqt;
        this.zzaqn = com_google_android_gms_internal_ads_zzlx.zzaqn;
        this.zzhn = Collections.unmodifiableSet(com_google_android_gms_internal_ads_zzlx.zzasp);
        this.zzhp = com_google_android_gms_internal_ads_zzlx.zzhp;
        this.zzvm = com_google_android_gms_internal_ads_zzlx.zzvm;
        this.zzask = com_google_android_gms_internal_ads_zzlx.zzask;
        this.zzasl = Collections.unmodifiableMap(com_google_android_gms_internal_ads_zzlx.zzasq);
        this.zzaqr = com_google_android_gms_internal_ads_zzlx.zzaqr;
        this.zzaqx = com_google_android_gms_internal_ads_zzlx.zzaqx;
        this.zzasm = searchAdRequest;
        this.zzaqq = com_google_android_gms_internal_ads_zzlx.zzaqq;
        this.zzasn = Collections.unmodifiableSet(com_google_android_gms_internal_ads_zzlx.zzasr);
        this.zzaqv = com_google_android_gms_internal_ads_zzlx.zzaqv;
        this.zzaso = Collections.unmodifiableSet(com_google_android_gms_internal_ads_zzlx.zzass);
        this.zzaqz = com_google_android_gms_internal_ads_zzlx.zzaqz;
    }

    public final Date getBirthday() {
        return this.zzhl;
    }

    public final String getContentUrl() {
        return this.zzaqt;
    }

    public final Bundle getCustomEventExtrasBundle(Class<? extends CustomEvent> cls) {
        Bundle bundle = this.zzask.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter");
        return bundle != null ? bundle.getBundle(cls.getName()) : null;
    }

    public final Bundle getCustomTargeting() {
        return this.zzaqv;
    }

    public final int getGender() {
        return this.zzaqn;
    }

    public final Set<String> getKeywords() {
        return this.zzhn;
    }

    public final Location getLocation() {
        return this.zzhp;
    }

    public final boolean getManualImpressionsEnabled() {
        return this.zzvm;
    }

    @Deprecated
    public final <T extends NetworkExtras> T getNetworkExtras(Class<T> cls) {
        return (NetworkExtras) this.zzasl.get(cls);
    }

    public final Bundle getNetworkExtrasBundle(Class<? extends MediationAdapter> cls) {
        return this.zzask.getBundle(cls.getName());
    }

    public final String getPublisherProvidedId() {
        return this.zzaqr;
    }

    public final boolean isDesignedForFamilies() {
        return this.zzaqz;
    }

    public final boolean isTestDevice(Context context) {
        Set set = this.zzasn;
        zzkb.zzif();
        return set.contains(zzamu.zzbc(context));
    }

    public final String zzip() {
        return this.zzaqx;
    }

    public final SearchAdRequest zziq() {
        return this.zzasm;
    }

    public final Map<Class<? extends NetworkExtras>, NetworkExtras> zzir() {
        return this.zzasl;
    }

    public final Bundle zzis() {
        return this.zzask;
    }

    public final int zzit() {
        return this.zzaqq;
    }

    public final Set<String> zziu() {
        return this.zzaso;
    }
}
