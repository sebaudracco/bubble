package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map.Entry;

final class zzabe implements Iterator<Entry<K, V>> {
    private int pos;
    private final /* synthetic */ zzaay zzbuf;
    private boolean zzbug;
    private Iterator<Entry<K, V>> zzbuh;

    private zzabe(zzaay com_google_android_gms_internal_measurement_zzaay) {
        this.zzbuf = com_google_android_gms_internal_measurement_zzaay;
        this.pos = -1;
    }

    private final Iterator<Entry<K, V>> zzup() {
        if (this.zzbuh == null) {
            this.zzbuh = this.zzbuf.zzbtz.entrySet().iterator();
        }
        return this.zzbuh;
    }

    public final boolean hasNext() {
        return this.pos + 1 < this.zzbuf.zzbty.size() || (!this.zzbuf.zzbtz.isEmpty() && zzup().hasNext());
    }

    public final /* synthetic */ Object next() {
        this.zzbug = true;
        int i = this.pos + 1;
        this.pos = i;
        return i < this.zzbuf.zzbty.size() ? (Entry) this.zzbuf.zzbty.get(this.pos) : (Entry) zzup().next();
    }

    public final void remove() {
        if (this.zzbug) {
            this.zzbug = false;
            this.zzbuf.zzul();
            if (this.pos < this.zzbuf.zzbty.size()) {
                zzaay com_google_android_gms_internal_measurement_zzaay = this.zzbuf;
                int i = this.pos;
                this.pos = i - 1;
                com_google_android_gms_internal_measurement_zzaay.zzai(i);
                return;
            }
            zzup().remove();
            return;
        }
        throw new IllegalStateException("remove() was called before next()");
    }
}
