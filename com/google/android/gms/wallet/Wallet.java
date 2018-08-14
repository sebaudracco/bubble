package com.google.android.gms.wallet;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.internal.wallet.zzad;
import com.google.android.gms.internal.wallet.zzal;
import com.google.android.gms.internal.wallet.zzam;
import com.google.android.gms.internal.wallet.zze;
import com.google.android.gms.internal.wallet.zzw;
import com.google.android.gms.wallet.wobs.WalletObjects;

public final class Wallet {
    public static final Api<WalletOptions> API = new Api("Wallet.API", CLIENT_BUILDER, CLIENT_KEY);
    private static final AbstractClientBuilder<zzad, WalletOptions> CLIENT_BUILDER = new zzap();
    private static final ClientKey<zzad> CLIENT_KEY = new ClientKey();
    @Deprecated
    public static final Payments Payments = new zzw();
    private static final WalletObjects zzep = new zzam();
    private static final zze zzeq = new zzal();

    private Wallet() {
    }

    public static PaymentsClient getPaymentsClient(@NonNull Activity activity, @NonNull WalletOptions walletOptions) {
        return new PaymentsClient(activity, walletOptions);
    }

    public static PaymentsClient getPaymentsClient(@NonNull Context context, @NonNull WalletOptions walletOptions) {
        return new PaymentsClient(context, walletOptions);
    }

    public static WalletObjectsClient getWalletObjectsClient(@NonNull Activity activity, @Nullable WalletOptions walletOptions) {
        return new WalletObjectsClient(activity, walletOptions);
    }
}
