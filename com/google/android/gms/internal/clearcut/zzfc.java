package com.google.android.gms.internal.clearcut;

import java.util.Iterator;

final class zzfc implements Iterator<String> {
    private final /* synthetic */ zzfa zzpe;
    private Iterator<String> zzpf = this.zzpe.zzpb.iterator();

    zzfc(zzfa com_google_android_gms_internal_clearcut_zzfa) {
        this.zzpe = com_google_android_gms_internal_clearcut_zzfa;
    }

    public final boolean hasNext() {
        return this.zzpf.hasNext();
    }

    public final /* synthetic */ Object next() {
        return (String) this.zzpf.next();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
