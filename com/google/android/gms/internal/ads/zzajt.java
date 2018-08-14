package com.google.android.gms.internal.ads;

import java.math.BigInteger;
import javax.annotation.concurrent.GuardedBy;

@zzadh
public final class zzajt {
    @GuardedBy("this")
    private BigInteger zzcqk = BigInteger.ONE;

    public final synchronized String zzql() {
        String bigInteger;
        bigInteger = this.zzcqk.toString();
        this.zzcqk = this.zzcqk.add(BigInteger.ONE);
        return bigInteger;
    }
}
