package com.google.android.gms.internal.ads;

public abstract class zzazz<MessageType extends zzazy<MessageType, BuilderType>, BuilderType extends zzazz<MessageType, BuilderType>> implements zzbcv {
    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return zzaax();
    }

    protected abstract BuilderType zza(MessageType messageType);

    public abstract BuilderType zzaax();

    public final /* synthetic */ zzbcv zzd(zzbcu com_google_android_gms_internal_ads_zzbcu) {
        if (zzadg().getClass().isInstance(com_google_android_gms_internal_ads_zzbcu)) {
            return zza((zzazy) com_google_android_gms_internal_ads_zzbcu);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }
}
