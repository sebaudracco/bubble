package com.google.android.gms.wearable;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.EntityBuffer;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.wearable.internal.zzdf;
import cz.msebera.android.httpclient.cookie.ClientCookie;

@VisibleForTesting
public class DataItemBuffer extends EntityBuffer<DataItem> implements Result {
    private final Status zzp;

    public DataItemBuffer(DataHolder dataHolder) {
        super(dataHolder);
        this.zzp = new Status(dataHolder.getStatusCode());
    }

    protected /* synthetic */ Object getEntry(int i, int i2) {
        return new zzdf(this.mDataHolder, i, i2);
    }

    protected String getPrimaryDataMarkerColumn() {
        return ClientCookie.PATH_ATTR;
    }

    public Status getStatus() {
        return this.zzp;
    }
}
