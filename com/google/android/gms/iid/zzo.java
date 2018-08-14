package com.google.android.gms.iid;

import android.util.Base64;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.util.VisibleForTesting;
import java.security.KeyPair;

final class zzo {
    private final KeyPair zzbw;
    private final long zzbx;

    @VisibleForTesting
    zzo(KeyPair keyPair, long j) {
        this.zzbw = keyPair;
        this.zzbx = j;
    }

    private final String zzo() {
        return Base64.encodeToString(this.zzbw.getPublic().getEncoded(), 11);
    }

    private final String zzp() {
        return Base64.encodeToString(this.zzbw.getPrivate().getEncoded(), 11);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzo)) {
            return false;
        }
        zzo com_google_android_gms_iid_zzo = (zzo) obj;
        return this.zzbx == com_google_android_gms_iid_zzo.zzbx && this.zzbw.getPublic().equals(com_google_android_gms_iid_zzo.zzbw.getPublic()) && this.zzbw.getPrivate().equals(com_google_android_gms_iid_zzo.zzbw.getPrivate());
    }

    final long getCreationTime() {
        return this.zzbx;
    }

    final KeyPair getKeyPair() {
        return this.zzbw;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzbw.getPublic(), this.zzbw.getPrivate(), Long.valueOf(this.zzbx)});
    }
}
