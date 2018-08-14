package com.google.android.gms.internal.ads;

import android.util.Base64OutputStream;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@VisibleForTesting
final class zzgt {
    @VisibleForTesting
    private ByteArrayOutputStream zzajc = new ByteArrayOutputStream(4096);
    @VisibleForTesting
    private Base64OutputStream zzajd = new Base64OutputStream(this.zzajc, 10);

    public final String toString() {
        String byteArrayOutputStream;
        try {
            this.zzajd.close();
        } catch (Throwable e) {
            zzakb.zzb("HashManager: Unable to convert to Base64.", e);
        }
        try {
            this.zzajc.close();
            byteArrayOutputStream = this.zzajc.toString();
        } catch (Throwable e2) {
            zzakb.zzb("HashManager: Unable to convert to Base64.", e2);
            byteArrayOutputStream = "";
        } finally {
            this.zzajc = null;
            this.zzajd = null;
        }
        return byteArrayOutputStream;
    }

    public final void write(byte[] bArr) throws IOException {
        this.zzajd.write(bArr);
    }
}
