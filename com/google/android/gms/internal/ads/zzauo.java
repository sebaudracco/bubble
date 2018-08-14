package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzaxr.zzb;
import java.security.GeneralSecurityException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class zzauo {
    private static final Logger logger = Logger.getLogger(zzauo.class.getName());
    private static final ConcurrentMap<String, zzaug> zzdhq = new ConcurrentHashMap();
    private static final ConcurrentMap<String, Boolean> zzdhr = new ConcurrentHashMap();
    private static final ConcurrentMap<String, zzaua> zzdhs = new ConcurrentHashMap();

    public static <P> zzaum<P> zza(zzauh com_google_android_gms_internal_ads_zzauh, zzaug<P> com_google_android_gms_internal_ads_zzaug_P) throws GeneralSecurityException {
        zzaup.zzc(com_google_android_gms_internal_ads_zzauh.zzwg());
        zzaum<P> com_google_android_gms_internal_ads_zzaum = new zzaum();
        for (zzb com_google_android_gms_internal_ads_zzaxr_zzb : com_google_android_gms_internal_ads_zzauh.zzwg().zzzl()) {
            if (com_google_android_gms_internal_ads_zzaxr_zzb.zzzq() == zzaxl.ENABLED) {
                zzaun zza = com_google_android_gms_internal_ads_zzaum.zza(zza(com_google_android_gms_internal_ads_zzaxr_zzb.zzzp().zzyw(), com_google_android_gms_internal_ads_zzaxr_zzb.zzzp().zzyx()), com_google_android_gms_internal_ads_zzaxr_zzb);
                if (com_google_android_gms_internal_ads_zzaxr_zzb.zzzr() == com_google_android_gms_internal_ads_zzauh.zzwg().zzzk()) {
                    com_google_android_gms_internal_ads_zzaum.zza(zza);
                }
            }
        }
        return com_google_android_gms_internal_ads_zzaum;
    }

    public static <P> zzaxi zza(zzaxn com_google_android_gms_internal_ads_zzaxn) throws GeneralSecurityException {
        zzaug zzdz = zzdz(com_google_android_gms_internal_ads_zzaxn.zzyw());
        if (((Boolean) zzdhr.get(com_google_android_gms_internal_ads_zzaxn.zzyw())).booleanValue()) {
            return zzdz.zzc(com_google_android_gms_internal_ads_zzaxn.zzyx());
        }
        String str = "newKey-operation not permitted for key type ";
        String valueOf = String.valueOf(com_google_android_gms_internal_ads_zzaxn.zzyw());
        throw new GeneralSecurityException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }

    public static <P> zzbcu zza(String str, zzbcu com_google_android_gms_internal_ads_zzbcu) throws GeneralSecurityException {
        zzaug zzdz = zzdz(str);
        if (((Boolean) zzdhr.get(str)).booleanValue()) {
            return zzdz.zzb(com_google_android_gms_internal_ads_zzbcu);
        }
        String str2 = "newKey-operation not permitted for key type ";
        String valueOf = String.valueOf(str);
        throw new GeneralSecurityException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
    }

    private static <P> P zza(String str, zzbah com_google_android_gms_internal_ads_zzbah) throws GeneralSecurityException {
        return zzdz(str).zza(com_google_android_gms_internal_ads_zzbah);
    }

    public static <P> P zza(String str, byte[] bArr) throws GeneralSecurityException {
        return zza(str, zzbah.zzo(bArr));
    }

    public static synchronized <P> void zza(String str, zzaua<P> com_google_android_gms_internal_ads_zzaua_P) throws GeneralSecurityException {
        synchronized (zzauo.class) {
            if (zzdhs.containsKey(str.toLowerCase())) {
                if (!com_google_android_gms_internal_ads_zzaua_P.getClass().equals(((zzaua) zzdhs.get(str.toLowerCase())).getClass())) {
                    Logger logger = logger;
                    Level level = Level.WARNING;
                    String str2 = "com.google.crypto.tink.Registry";
                    String str3 = "addCatalogue";
                    String str4 = "Attempted overwrite of a catalogueName catalogue for name ";
                    String valueOf = String.valueOf(str);
                    logger.logp(level, str2, str3, valueOf.length() != 0 ? str4.concat(valueOf) : new String(str4));
                    throw new GeneralSecurityException(new StringBuilder(String.valueOf(str).length() + 47).append("catalogue for name ").append(str).append(" has been already registered").toString());
                }
            }
            zzdhs.put(str.toLowerCase(), com_google_android_gms_internal_ads_zzaua_P);
        }
    }

    public static <P> void zza(String str, zzaug<P> com_google_android_gms_internal_ads_zzaug_P) throws GeneralSecurityException {
        zza(str, com_google_android_gms_internal_ads_zzaug_P, true);
    }

    public static synchronized <P> void zza(String str, zzaug<P> com_google_android_gms_internal_ads_zzaug_P, boolean z) throws GeneralSecurityException {
        synchronized (zzauo.class) {
            if (com_google_android_gms_internal_ads_zzaug_P == null) {
                throw new IllegalArgumentException("key manager must be non-null.");
            }
            if (zzdhq.containsKey(str)) {
                zzaug zzdz = zzdz(str);
                boolean booleanValue = ((Boolean) zzdhr.get(str)).booleanValue();
                if (!com_google_android_gms_internal_ads_zzaug_P.getClass().equals(zzdz.getClass()) || (!booleanValue && z)) {
                    Logger logger = logger;
                    Level level = Level.WARNING;
                    String str2 = "com.google.crypto.tink.Registry";
                    String str3 = "registerKeyManager";
                    String str4 = "Attempted overwrite of a registered key manager for key type ";
                    String valueOf = String.valueOf(str);
                    logger.logp(level, str2, str3, valueOf.length() != 0 ? str4.concat(valueOf) : new String(str4));
                    throw new GeneralSecurityException(String.format("typeUrl (%s) is already registered with %s, cannot be re-registered with %s", new Object[]{str, zzdz.getClass().getName(), com_google_android_gms_internal_ads_zzaug_P.getClass().getName()}));
                }
            }
            zzdhq.put(str, com_google_android_gms_internal_ads_zzaug_P);
            zzdhr.put(str, Boolean.valueOf(z));
        }
    }

    public static <P> zzbcu zzb(zzaxn com_google_android_gms_internal_ads_zzaxn) throws GeneralSecurityException {
        zzaug zzdz = zzdz(com_google_android_gms_internal_ads_zzaxn.zzyw());
        if (((Boolean) zzdhr.get(com_google_android_gms_internal_ads_zzaxn.zzyw())).booleanValue()) {
            return zzdz.zzb(com_google_android_gms_internal_ads_zzaxn.zzyx());
        }
        String str = "newKey-operation not permitted for key type ";
        String valueOf = String.valueOf(com_google_android_gms_internal_ads_zzaxn.zzyw());
        throw new GeneralSecurityException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }

    public static <P> P zzb(String str, zzbcu com_google_android_gms_internal_ads_zzbcu) throws GeneralSecurityException {
        return zzdz(str).zza(com_google_android_gms_internal_ads_zzbcu);
    }

    public static <P> zzaua<P> zzdy(String str) throws GeneralSecurityException {
        if (str == null) {
            throw new IllegalArgumentException("catalogueName must be non-null.");
        }
        zzaua<P> com_google_android_gms_internal_ads_zzaua_P = (zzaua) zzdhs.get(str.toLowerCase());
        if (com_google_android_gms_internal_ads_zzaua_P != null) {
            return com_google_android_gms_internal_ads_zzaua_P;
        }
        String format = String.format("no catalogue found for %s. ", new Object[]{str});
        if (str.toLowerCase().startsWith("tinkaead")) {
            format = String.valueOf(format).concat("Maybe call AeadConfig.init().");
        }
        if (str.toLowerCase().startsWith("tinkdeterministicaead")) {
            format = String.valueOf(format).concat("Maybe call DeterministicAeadConfig.init().");
        } else if (str.toLowerCase().startsWith("tinkstreamingaead")) {
            format = String.valueOf(format).concat("Maybe call StreamingAeadConfig.init().");
        } else if (str.toLowerCase().startsWith("tinkhybriddecrypt") || str.toLowerCase().startsWith("tinkhybridencrypt")) {
            format = String.valueOf(format).concat("Maybe call HybridConfig.init().");
        } else if (str.toLowerCase().startsWith("tinkmac")) {
            format = String.valueOf(format).concat("Maybe call MacConfig.init().");
        } else if (str.toLowerCase().startsWith("tinkpublickeysign") || str.toLowerCase().startsWith("tinkpublickeyverify")) {
            format = String.valueOf(format).concat("Maybe call SignatureConfig.init().");
        } else if (str.toLowerCase().startsWith("tink")) {
            format = String.valueOf(format).concat("Maybe call TinkConfig.init().");
        }
        throw new GeneralSecurityException(format);
    }

    private static <P> zzaug<P> zzdz(String str) throws GeneralSecurityException {
        zzaug<P> com_google_android_gms_internal_ads_zzaug_P = (zzaug) zzdhq.get(str);
        if (com_google_android_gms_internal_ads_zzaug_P != null) {
            return com_google_android_gms_internal_ads_zzaug_P;
        }
        throw new GeneralSecurityException(new StringBuilder(String.valueOf(str).length() + 78).append("No key manager found for key type: ").append(str).append(".  Check the configuration of the registry.").toString());
    }
}
