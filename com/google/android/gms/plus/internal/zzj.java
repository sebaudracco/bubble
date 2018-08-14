package com.google.android.gms.plus.internal;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.plus.People.LoadPeopleResult;

@VisibleForTesting
final class zzj extends zza {
    private final ResultHolder<LoadPeopleResult> zzv;

    public zzj(ResultHolder<LoadPeopleResult> resultHolder) {
        this.zzv = resultHolder;
    }

    public final void zza(DataHolder dataHolder, String str) {
        Status status = new Status(dataHolder.getStatusCode(), null, dataHolder.getMetadata() != null ? (PendingIntent) dataHolder.getMetadata().getParcelable(BaseGmsClient.KEY_PENDING_INTENT) : null);
        if (!(status.isSuccess() || dataHolder == null)) {
            if (!dataHolder.isClosed()) {
                dataHolder.close();
            }
            dataHolder = null;
        }
        this.zzv.setResult(new zzi(status, dataHolder, str));
    }
}
