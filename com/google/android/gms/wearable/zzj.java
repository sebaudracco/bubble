package com.google.android.gms.wearable;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.wearable.Wearable.WearableOptions;
import com.google.android.gms.wearable.internal.zzhg;

final class zzj extends AbstractClientBuilder<zzhg, WearableOptions> {
    zzj() {
    }

    public final /* synthetic */ Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        if (((WearableOptions) obj) == null) {
            WearableOptions wearableOptions = new WearableOptions(new Wearable$WearableOptions$Builder(), null);
        }
        return new zzhg(context, looper, connectionCallbacks, onConnectionFailedListener, clientSettings);
    }
}
