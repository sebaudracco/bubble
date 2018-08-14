package com.google.android.gms.plus.model.people;

import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataBufferSafeParcelable;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.plus.zzac;
import com.google.android.gms.internal.plus.zzr;

@Deprecated
@VisibleForTesting
public final class PersonBuffer extends AbstractDataBuffer<Person> {
    private final DataBufferSafeParcelable<zzr> zzcm;

    public PersonBuffer(DataHolder dataHolder) {
        super(dataHolder);
        if (dataHolder.getMetadata() == null || !dataHolder.getMetadata().getBoolean("com.google.android.gms.plus.IsSafeParcelable", false)) {
            this.zzcm = null;
        } else {
            this.zzcm = new DataBufferSafeParcelable(dataHolder, zzr.CREATOR);
        }
    }

    @Deprecated
    public final Person get(int i) {
        return this.zzcm != null ? (Person) this.zzcm.get(i) : new zzac(this.mDataHolder, i);
    }
}
