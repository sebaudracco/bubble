package com.google.android.gms.internal.ads;

import java.util.ListIterator;

final class zzbei implements ListIterator<String> {
    private ListIterator<String> zzdza = this.zzdzc.zzdyz.listIterator(this.zzdzb);
    private final /* synthetic */ int zzdzb;
    private final /* synthetic */ zzbeh zzdzc;

    zzbei(zzbeh com_google_android_gms_internal_ads_zzbeh, int i) {
        this.zzdzc = com_google_android_gms_internal_ads_zzbeh;
        this.zzdzb = i;
    }

    public final /* synthetic */ void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final boolean hasNext() {
        return this.zzdza.hasNext();
    }

    public final boolean hasPrevious() {
        return this.zzdza.hasPrevious();
    }

    public final /* synthetic */ Object next() {
        return (String) this.zzdza.next();
    }

    public final int nextIndex() {
        return this.zzdza.nextIndex();
    }

    public final /* synthetic */ Object previous() {
        return (String) this.zzdza.previous();
    }

    public final int previousIndex() {
        return this.zzdza.previousIndex();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public final /* synthetic */ void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}
