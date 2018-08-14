package com.google.android.gms.wallet;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.internal.wallet.zzad;

final class zzap extends AbstractClientBuilder<zzad, Wallet$WalletOptions> {
    zzap() {
    }

    public final /* synthetic */ Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        Wallet$WalletOptions wallet$WalletOptions = (Wallet$WalletOptions) obj;
        if (wallet$WalletOptions == null) {
            wallet$WalletOptions = new Wallet$WalletOptions();
        }
        return new zzad(context, looper, clientSettings, connectionCallbacks, onConnectionFailedListener, wallet$WalletOptions.environment, wallet$WalletOptions.theme, wallet$WalletOptions.zzer);
    }
}
