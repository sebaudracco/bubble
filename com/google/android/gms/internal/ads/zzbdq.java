package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

final class zzbdq extends zzbdp<FieldDescriptorType, Object> {
    zzbdq(int i) {
        super(i);
    }

    public final void zzaaz() {
        if (!isImmutable()) {
            for (int i = 0; i < zzafs(); i++) {
                Entry zzcy = zzcy(i);
                if (((zzbbi) zzcy.getKey()).zzada()) {
                    zzcy.setValue(Collections.unmodifiableList((List) zzcy.getValue()));
                }
            }
            for (Entry entry : zzaft()) {
                if (((zzbbi) entry.getKey()).zzada()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zzaaz();
    }
}
