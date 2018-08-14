package com.google.android.gms.wallet;

import android.accounts.Account;
import android.support.annotation.VisibleForTesting;
import com.google.android.gms.common.api.Api.ApiOptions.HasAccountOptions;
import com.google.android.gms.common.internal.Objects;

public final class Wallet$WalletOptions implements HasAccountOptions {
    private final Account account;
    public final int environment;
    public final int theme;
    @VisibleForTesting
    final boolean zzer;

    private Wallet$WalletOptions() {
        this(new Builder());
    }

    private Wallet$WalletOptions(Builder builder) {
        this.environment = Builder.zza(builder);
        this.theme = Builder.zzb(builder);
        this.zzer = Builder.zzc(builder);
        this.account = null;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Wallet$WalletOptions)) {
            return false;
        }
        Wallet$WalletOptions wallet$WalletOptions = (Wallet$WalletOptions) obj;
        return Objects.equal(Integer.valueOf(this.environment), Integer.valueOf(wallet$WalletOptions.environment)) && Objects.equal(Integer.valueOf(this.theme), Integer.valueOf(wallet$WalletOptions.theme)) && Objects.equal(null, null) && Objects.equal(Boolean.valueOf(this.zzer), Boolean.valueOf(wallet$WalletOptions.zzer));
    }

    public final Account getAccount() {
        return null;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{Integer.valueOf(this.environment), Integer.valueOf(this.theme), null, Boolean.valueOf(this.zzer)});
    }
}
