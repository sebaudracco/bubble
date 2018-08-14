package com.google.android.gms.internal.ads;

import java.io.IOException;

public class zzbbu extends IOException {
    private zzbcu zzdut = null;

    public zzbbu(String str) {
        super(str);
    }

    static zzbbu zzadl() {
        return new zzbbu("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    static zzbbu zzadm() {
        return new zzbbu("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    static zzbbu zzadn() {
        return new zzbbu("CodedInputStream encountered a malformed varint.");
    }

    static zzbbu zzado() {
        return new zzbbu("Protocol message contained an invalid tag (zero).");
    }

    static zzbbu zzadp() {
        return new zzbbu("Protocol message end-group tag did not match expected tag.");
    }

    static zzbbv zzadq() {
        return new zzbbv("Protocol message tag had invalid wire type.");
    }

    static zzbbu zzadr() {
        return new zzbbu("Failed to parse the message.");
    }

    static zzbbu zzads() {
        return new zzbbu("Protocol message had invalid UTF-8.");
    }

    public final zzbbu zzj(zzbcu com_google_android_gms_internal_ads_zzbcu) {
        this.zzdut = com_google_android_gms_internal_ads_zzbcu;
        return this;
    }
}
