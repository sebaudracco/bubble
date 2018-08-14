package com.google.android.gms.internal.clearcut;

import java.util.Iterator;
import java.util.Map.Entry;

final class zzel extends zzer {
    private final /* synthetic */ zzei zzos;

    private zzel(zzei com_google_android_gms_internal_clearcut_zzei) {
        this.zzos = com_google_android_gms_internal_clearcut_zzei;
        super(com_google_android_gms_internal_clearcut_zzei);
    }

    public final Iterator<Entry<K, V>> iterator() {
        return new zzek(this.zzos);
    }
}
