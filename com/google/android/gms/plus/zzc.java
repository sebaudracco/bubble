package com.google.android.gms.plus;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.util.ScopeUtil;
import com.google.android.gms.plus.Plus.PlusOptions;
import com.google.android.gms.plus.internal.PlusCommonExtras;
import com.google.android.gms.plus.internal.zzh;
import com.google.android.gms.plus.internal.zzn;

final class zzc extends AbstractClientBuilder<zzh, PlusOptions> {
    zzc() {
    }

    public final /* synthetic */ Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        PlusOptions plusOptions = (PlusOptions) obj;
        if (plusOptions == null) {
            plusOptions = new PlusOptions(null);
        }
        return new zzh(context, looper, clientSettings, new zzn(clientSettings.getAccountOrDefault().name, ScopeUtil.toScopeString(clientSettings.getAllRequestedScopes()), (String[]) plusOptions.zzh.toArray(new String[0]), new String[0], context.getPackageName(), context.getPackageName(), null, new PlusCommonExtras()), connectionCallbacks, onConnectionFailedListener);
    }

    public final int getPriority() {
        return 2;
    }
}
