package com.google.android.gms.internal.ads;

public abstract class zzazy<MessageType extends zzazy<MessageType, BuilderType>, BuilderType extends zzazz<MessageType, BuilderType>> implements zzbcu {
    private static boolean zzdpg = false;
    protected int zzdpf = 0;

    public final byte[] toByteArray() {
        try {
            byte[] bArr = new byte[zzacw()];
            zzbav zzq = zzbav.zzq(bArr);
            zzb(zzq);
            zzq.zzacl();
            return bArr;
        } catch (Throwable e) {
            String str = "byte array";
            String name = getClass().getName();
            throw new RuntimeException(new StringBuilder((String.valueOf(name).length() + 62) + String.valueOf(str).length()).append("Serializing ").append(name).append(" to a ").append(str).append(" threw an IOException (should never happen).").toString(), e);
        }
    }

    public final zzbah zzaav() {
        try {
            zzbam zzbo = zzbah.zzbo(zzacw());
            zzb(zzbo.zzabj());
            return zzbo.zzabi();
        } catch (Throwable e) {
            String str = "ByteString";
            String name = getClass().getName();
            throw new RuntimeException(new StringBuilder((String.valueOf(name).length() + 62) + String.valueOf(str).length()).append("Serializing ").append(name).append(" to a ").append(str).append(" threw an IOException (should never happen).").toString(), e);
        }
    }

    int zzaaw() {
        throw new UnsupportedOperationException();
    }

    void zzbj(int i) {
        throw new UnsupportedOperationException();
    }
}
