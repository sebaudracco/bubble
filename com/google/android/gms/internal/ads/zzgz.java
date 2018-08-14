package com.google.android.gms.internal.ads;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzgz extends zzgq {
    private MessageDigest zzaje;
    private final int zzajh;
    private final int zzaji;

    public zzgz(int i) {
        int i2 = i / 8;
        if (i % 8 > 0) {
            i2++;
        }
        this.zzajh = i2;
        this.zzaji = i;
    }

    public final byte[] zzx(String str) {
        byte[] bArr;
        synchronized (this.mLock) {
            this.zzaje = zzhg();
            if (this.zzaje == null) {
                bArr = new byte[0];
            } else {
                this.zzaje.reset();
                this.zzaje.update(str.getBytes(Charset.forName("UTF-8")));
                Object digest = this.zzaje.digest();
                bArr = new byte[(digest.length > this.zzajh ? this.zzajh : digest.length)];
                System.arraycopy(digest, 0, bArr, 0, bArr.length);
                if (this.zzaji % 8 > 0) {
                    int i;
                    long j = 0;
                    for (i = 0; i < bArr.length; i++) {
                        if (i > 0) {
                            j <<= 8;
                        }
                        j += (long) (bArr[i] & 255);
                    }
                    j >>>= 8 - (this.zzaji % 8);
                    for (i = this.zzajh - 1; i >= 0; i--) {
                        bArr[i] = (byte) ((int) (255 & j));
                        j >>>= 8;
                    }
                }
            }
        }
        return bArr;
    }
}
