package com.google.android.gms.internal.clearcut;

public abstract class zzat<MessageType extends zzas<MessageType, BuilderType>, BuilderType extends zzat<MessageType, BuilderType>> implements zzdp {
    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return zzt();
    }

    protected abstract BuilderType zza(MessageType messageType);

    public final /* synthetic */ zzdp zza(zzdo com_google_android_gms_internal_clearcut_zzdo) {
        if (zzbe().getClass().isInstance(com_google_android_gms_internal_clearcut_zzdo)) {
            return zza((zzas) com_google_android_gms_internal_clearcut_zzdo);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }

    public abstract BuilderType zzt();
}
