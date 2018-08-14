package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.InvalidKeyException;

final class zzayl extends zzazn {
    private static final byte[] zzdnb = new byte[16];

    zzayl(byte[] bArr, int i) throws InvalidKeyException {
        super(bArr, i);
    }

    private static void zza(int[] iArr, int i, int i2, int i3, int i4) {
        iArr[i] = iArr[i] + iArr[i2];
        iArr[i4] = zzazn.rotateLeft(iArr[i4] ^ iArr[i], 16);
        iArr[i3] = iArr[i3] + iArr[i4];
        iArr[i2] = zzazn.rotateLeft(iArr[i2] ^ iArr[i3], 12);
        iArr[i] = iArr[i] + iArr[i2];
        iArr[i4] = zzazn.rotateLeft(iArr[i4] ^ iArr[i], 8);
        iArr[i3] = iArr[i3] + iArr[i4];
        iArr[i2] = zzazn.rotateLeft(iArr[i2] ^ iArr[i3], 7);
    }

    final int zzaao() {
        return 12;
    }

    final ByteBuffer zzb(byte[] bArr, int i) {
        int i2;
        Object obj = new int[16];
        System.arraycopy(zzazn.zzdoo, 0, obj, 0, zzdoo.length);
        Object zza = zzazn.zza(ByteBuffer.wrap(this.zzdop.getBytes()));
        System.arraycopy(zza, 0, obj, 4, zza.length);
        obj[12] = i;
        System.arraycopy(zzazn.zza(ByteBuffer.wrap(bArr)), 0, obj, 13, 3);
        int[] iArr = (int[]) obj.clone();
        for (i2 = 0; i2 < 10; i2++) {
            zza(iArr, 0, 4, 8, 12);
            zza(iArr, 1, 5, 9, 13);
            zza(iArr, 2, 6, 10, 14);
            zza(iArr, 3, 7, 11, 15);
            zza(iArr, 0, 5, 10, 15);
            zza(iArr, 1, 6, 11, 12);
            zza(iArr, 2, 7, 8, 13);
            zza(iArr, 3, 4, 9, 14);
        }
        for (i2 = 0; i2 < 16; i2++) {
            obj[i2] = obj[i2] + iArr[i2];
        }
        ByteBuffer order = ByteBuffer.allocate(64).order(ByteOrder.LITTLE_ENDIAN);
        order.asIntBuffer().put(obj, 0, 16);
        return order;
    }
}
