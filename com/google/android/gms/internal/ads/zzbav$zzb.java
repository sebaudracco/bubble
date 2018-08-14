package com.google.android.gms.internal.ads;

import java.io.IOException;

public class zzbav$zzb extends IOException {
    zzbav$zzb() {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.");
    }

    zzbav$zzb(String str, Throwable th) {
        String valueOf = String.valueOf("CodedOutputStream was writing to a flat byte array and ran out of space.: ");
        String valueOf2 = String.valueOf(str);
        super(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), th);
    }

    zzbav$zzb(Throwable th) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
    }
}
