package com.google.android.gms.internal.clearcut;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public final class zzfa extends AbstractList<String> implements zzcx, RandomAccess {
    private final zzcx zzpb;

    public zzfa(zzcx com_google_android_gms_internal_clearcut_zzcx) {
        this.zzpb = com_google_android_gms_internal_clearcut_zzcx;
    }

    public final /* synthetic */ Object get(int i) {
        return (String) this.zzpb.get(i);
    }

    public final Object getRaw(int i) {
        return this.zzpb.getRaw(i);
    }

    public final Iterator<String> iterator() {
        return new zzfc(this);
    }

    public final ListIterator<String> listIterator(int i) {
        return new zzfb(this, i);
    }

    public final int size() {
        return this.zzpb.size();
    }

    public final List<?> zzbt() {
        return this.zzpb.zzbt();
    }

    public final zzcx zzbu() {
        return this;
    }
}
