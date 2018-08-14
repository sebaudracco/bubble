package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbfh extends IOException {
    public zzbfh(String str) {
        super(str);
    }

    static zzbfh zzagq() {
        return new zzbfh("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
    }

    static zzbfh zzagr() {
        return new zzbfh("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    static zzbfh zzags() {
        return new zzbfh("CodedInputStream encountered a malformed varint.");
    }
}
