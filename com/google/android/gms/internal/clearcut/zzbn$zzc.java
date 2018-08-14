package com.google.android.gms.internal.clearcut;

import java.io.IOException;

public class zzbn$zzc extends IOException {
    zzbn$zzc() {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.");
    }

    zzbn$zzc(String str) {
        String valueOf = String.valueOf("CodedOutputStream was writing to a flat byte array and ran out of space.: ");
        String valueOf2 = String.valueOf(str);
        super(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
    }

    zzbn$zzc(String str, Throwable th) {
        String valueOf = String.valueOf("CodedOutputStream was writing to a flat byte array and ran out of space.: ");
        String valueOf2 = String.valueOf(str);
        super(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), th);
    }

    zzbn$zzc(Throwable th) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
    }
}
