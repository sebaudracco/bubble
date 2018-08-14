package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

abstract class zzazo implements zzatz {
    private final byte[] key;
    private final zzazn zzdor;
    private final zzazn zzdos;

    zzazo(byte[] bArr) throws InvalidKeyException {
        this.key = (byte[]) bArr.clone();
        this.zzdor = zzc(bArr, 1);
        this.zzdos = zzc(bArr, 0);
    }

    abstract zzazn zzc(byte[] bArr, int i) throws InvalidKeyException;

    public byte[] zzc(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        int length = bArr.length;
        this.zzdor.zzaao();
        if (length > 2147483619) {
            throw new GeneralSecurityException("plaintext too long");
        }
        ByteBuffer allocate = ByteBuffer.allocate((bArr.length + this.zzdor.zzaao()) + 16);
        if (allocate.remaining() < (bArr.length + this.zzdor.zzaao()) + 16) {
            throw new IllegalArgumentException("Given ByteBuffer output is too small");
        }
        length = allocate.position();
        this.zzdor.zza(allocate, bArr);
        allocate.position(length);
        byte[] bArr3 = new byte[this.zzdor.zzaao()];
        allocate.get(bArr3);
        allocate.limit(allocate.limit() - 16);
        if (bArr2 == null) {
            bArr2 = new byte[0];
        }
        byte[] bArr4 = new byte[32];
        this.zzdos.zzb(bArr3, 0).get(bArr4);
        length = bArr2.length % 16 == 0 ? bArr2.length : (bArr2.length + 16) - (bArr2.length % 16);
        int remaining = allocate.remaining();
        int i = remaining % 16 == 0 ? remaining : (remaining + 16) - (remaining % 16);
        ByteBuffer order = ByteBuffer.allocate((length + i) + 16).order(ByteOrder.LITTLE_ENDIAN);
        order.put(bArr2);
        order.position(length);
        order.put(allocate);
        order.position(length + i);
        order.putLong((long) bArr2.length);
        order.putLong((long) remaining);
        bArr3 = zzazk.zze(bArr4, order.array());
        allocate.limit(allocate.limit() + 16);
        allocate.put(bArr3);
        return allocate.array();
    }
}
