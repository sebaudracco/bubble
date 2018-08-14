package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

final class zzaaz extends zzaay<FieldDescriptorType, Object> {
    zzaaz(int i) {
        super(i);
    }

    public final void zzrg() {
        if (!isImmutable()) {
            for (int i = 0; i < zzuj(); i++) {
                Entry zzah = zzah(i);
                if (((zzzo) zzah.getKey()).zztk()) {
                    zzah.setValue(Collections.unmodifiableList((List) zzah.getValue()));
                }
            }
            for (Entry entry : zzuk()) {
                if (((zzzo) entry.getKey()).zztk()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zzrg();
    }
}
