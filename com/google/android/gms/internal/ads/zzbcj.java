package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo.zze;

final class zzbcj implements zzbdn {
    private static final zzbct zzdvw = new zzbck();
    private final zzbct zzdvv;

    public zzbcj() {
        this(new zzbcl(zzbbn.zzadc(), zzaea()));
    }

    private zzbcj(zzbct com_google_android_gms_internal_ads_zzbct) {
        this.zzdvv = (zzbct) zzbbq.zza((Object) com_google_android_gms_internal_ads_zzbct, "messageInfoFactory");
    }

    private static boolean zza(zzbcs com_google_android_gms_internal_ads_zzbcs) {
        return com_google_android_gms_internal_ads_zzbcs.zzaeh() == zze.zzdui;
    }

    private static zzbct zzaea() {
        try {
            return (zzbct) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e) {
            return zzdvw;
        }
    }

    public final <T> zzbdm<T> zzd(Class<T> cls) {
        zzbdo.zzf(cls);
        zzbcs zzb = this.zzdvv.zzb(cls);
        if (zzb.zzaei()) {
            return zzbbo.class.isAssignableFrom(cls) ? zzbda.zza(zzbdo.zzafp(), zzbbf.zzact(), zzb.zzaej()) : zzbda.zza(zzbdo.zzafn(), zzbbf.zzacu(), zzb.zzaej());
        } else {
            if (zzbbo.class.isAssignableFrom(cls)) {
                if (zza(zzb)) {
                    return zzbcy.zza((Class) cls, zzb, zzbde.zzaem(), zzbce.zzadz(), zzbdo.zzafp(), zzbbf.zzact(), zzbcr.zzaef());
                }
                return zzbcy.zza((Class) cls, zzb, zzbde.zzaem(), zzbce.zzadz(), zzbdo.zzafp(), null, zzbcr.zzaef());
            } else if (zza(zzb)) {
                return zzbcy.zza((Class) cls, zzb, zzbde.zzael(), zzbce.zzady(), zzbdo.zzafn(), zzbbf.zzacu(), zzbcr.zzaee());
            } else {
                return zzbcy.zza((Class) cls, zzb, zzbde.zzael(), zzbce.zzady(), zzbdo.zzafo(), null, zzbcr.zzaee());
            }
        }
    }
}
