package com.google.android.gms.common.api.internal;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api$AnyClientKey;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.gms.common.api.zzc;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public final class zzck {
    public static final Status zzmm = new Status(8, "The connection to Google Play services was lost");
    private static final BasePendingResult<?>[] zzmn = new BasePendingResult[0];
    private final Map<Api$AnyClientKey<?>, Client> zzil;
    @VisibleForTesting
    final Set<BasePendingResult<?>> zzmo = Collections.synchronizedSet(Collections.newSetFromMap(new WeakHashMap()));
    private final zzcn zzmp = new zzcl(this);

    public zzck(Map<Api$AnyClientKey<?>, Client> map) {
        this.zzil = map;
    }

    public final void release() {
        zzc com_google_android_gms_common_api_zzc = null;
        for (PendingResult pendingResult : (BasePendingResult[]) this.zzmo.toArray(zzmn)) {
            pendingResult.zza(com_google_android_gms_common_api_zzc);
            if (pendingResult.zzo() != null) {
                pendingResult.setResultCallback(com_google_android_gms_common_api_zzc);
                IBinder serviceBrokerBinder = ((Client) this.zzil.get(((ApiMethodImpl) pendingResult).getClientKey())).getServiceBrokerBinder();
                if (pendingResult.isReady()) {
                    pendingResult.zza(new zzcm(pendingResult, com_google_android_gms_common_api_zzc, serviceBrokerBinder, com_google_android_gms_common_api_zzc));
                } else if (serviceBrokerBinder == null || !serviceBrokerBinder.isBinderAlive()) {
                    pendingResult.zza(com_google_android_gms_common_api_zzc);
                    pendingResult.cancel();
                    com_google_android_gms_common_api_zzc.remove(pendingResult.zzo().intValue());
                } else {
                    Object com_google_android_gms_common_api_internal_zzcm = new zzcm(pendingResult, com_google_android_gms_common_api_zzc, serviceBrokerBinder, com_google_android_gms_common_api_zzc);
                    pendingResult.zza(com_google_android_gms_common_api_internal_zzcm);
                    try {
                        serviceBrokerBinder.linkToDeath(com_google_android_gms_common_api_internal_zzcm, 0);
                    } catch (RemoteException e) {
                        pendingResult.cancel();
                        com_google_android_gms_common_api_zzc.remove(pendingResult.zzo().intValue());
                    }
                }
                this.zzmo.remove(pendingResult);
            } else if (pendingResult.zzw()) {
                this.zzmo.remove(pendingResult);
            }
        }
    }

    final void zzb(BasePendingResult<? extends Result> basePendingResult) {
        this.zzmo.add(basePendingResult);
        basePendingResult.zza(this.zzmp);
    }

    public final void zzce() {
        for (BasePendingResult zzb : (BasePendingResult[]) this.zzmo.toArray(zzmn)) {
            zzb.zzb(zzmm);
        }
    }
}
