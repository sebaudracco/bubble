package com.google.android.gms.internal.ads;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public final class zzbeh extends AbstractList<String> implements zzbcd, RandomAccess {
    private final zzbcd zzdyz;

    public zzbeh(zzbcd com_google_android_gms_internal_ads_zzbcd) {
        this.zzdyz = com_google_android_gms_internal_ads_zzbcd;
    }

    public final /* synthetic */ Object get(int i) {
        return (String) this.zzdyz.get(i);
    }

    public final Iterator<String> iterator() {
        return new zzbej(this);
    }

    public final ListIterator<String> listIterator(int i) {
        return new zzbei(this, i);
    }

    public final int size() {
        return this.zzdyz.size();
    }

    public final List<?> zzadw() {
        return this.zzdyz.zzadw();
    }

    public final zzbcd zzadx() {
        return this;
    }

    public final void zzap(zzbah com_google_android_gms_internal_ads_zzbah) {
        throw new UnsupportedOperationException();
    }

    public final Object zzcp(int i) {
        return this.zzdyz.zzcp(i);
    }
}
