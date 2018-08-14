package com.google.android.gms.internal.ads;

import android.support.v4.internal.view.SupportMenu;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;

@zzadh
public final class zzgv extends zzgq {
    private MessageDigest zzaje;

    public final byte[] zzx(String str) {
        byte[] array;
        int i = 0;
        String[] split = str.split(" ");
        int zzz;
        if (split.length == 1) {
            zzz = zzgu.zzz(split[0]);
            ByteBuffer allocate = ByteBuffer.allocate(4);
            allocate.order(ByteOrder.LITTLE_ENDIAN);
            allocate.putInt(zzz);
            array = allocate.array();
        } else if (split.length < 5) {
            byte[] bArr = new byte[(split.length << 1)];
            for (zzz = 0; zzz < split.length; zzz++) {
                int zzz2 = zzgu.zzz(split[zzz]);
                zzz2 = (zzz2 >> 16) ^ (SupportMenu.USER_MASK & zzz2);
                byte[] bArr2 = new byte[]{(byte) zzz2, (byte) (zzz2 >> 8)};
                bArr[zzz << 1] = bArr2[0];
                bArr[(zzz << 1) + 1] = bArr2[1];
            }
            array = bArr;
        } else {
            array = new byte[split.length];
            while (i < split.length) {
                int zzz3 = zzgu.zzz(split[i]);
                array[i] = (byte) ((zzz3 >> 24) ^ (((zzz3 & 255) ^ ((zzz3 >> 8) & 255)) ^ ((zzz3 >> 16) & 255)));
                i++;
            }
        }
        this.zzaje = zzhg();
        synchronized (this.mLock) {
            if (this.zzaje == null) {
                array = new byte[0];
            } else {
                this.zzaje.reset();
                this.zzaje.update(array);
                Object digest = this.zzaje.digest();
                array = new byte[(digest.length > 4 ? 4 : digest.length)];
                System.arraycopy(digest, 0, array, 0, array.length);
            }
        }
        return array;
    }
}
