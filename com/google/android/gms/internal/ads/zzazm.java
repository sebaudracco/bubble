package com.google.android.gms.internal.ads;

import java.security.SecureRandom;

final class zzazm extends ThreadLocal<SecureRandom> {
    zzazm() {
    }

    protected final /* synthetic */ Object initialValue() {
        return zzazl.zzaar();
    }
}
