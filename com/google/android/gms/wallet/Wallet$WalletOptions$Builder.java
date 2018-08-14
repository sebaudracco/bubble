package com.google.android.gms.wallet;

import com.google.android.gms.wallet.Wallet.WalletOptions;
import java.util.Locale;

public final class Wallet$WalletOptions$Builder {
    private int environment = 3;
    private int theme = 0;
    private boolean zzer = true;

    public final WalletOptions build() {
        return new WalletOptions(this, null);
    }

    public final Wallet$WalletOptions$Builder setEnvironment(int i) {
        if (i == 0 || i == 0 || i == 2 || i == 1 || i == 3) {
            this.environment = i;
            return this;
        }
        throw new IllegalArgumentException(String.format(Locale.US, "Invalid environment value %d", new Object[]{Integer.valueOf(i)}));
    }

    public final Wallet$WalletOptions$Builder setTheme(int i) {
        if (i == 0 || i == 1) {
            this.theme = i;
            return this;
        }
        throw new IllegalArgumentException(String.format(Locale.US, "Invalid theme value %d", new Object[]{Integer.valueOf(i)}));
    }

    @Deprecated
    public final Wallet$WalletOptions$Builder useGoogleWallet() {
        this.zzer = false;
        return this;
    }
}
