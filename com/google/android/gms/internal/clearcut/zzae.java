package com.google.android.gms.internal.clearcut;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.UserManager;
import android.support.v4.content.PermissionChecker;
import android.util.Log;
import javax.annotation.Nullable;

public abstract class zzae<T> {
    private static final Object zzdn = new Object();
    private static boolean zzdo = false;
    private static volatile Boolean zzdp = null;
    private static volatile Boolean zzdq = null;
    @SuppressLint({"StaticFieldLeak"})
    private static Context zzh = null;
    private final zzao zzdr;
    final String zzds;
    private final String zzdt;
    private final T zzdu;
    private T zzdv;
    private volatile zzab zzdw;
    private volatile SharedPreferences zzdx;

    private zzae(zzao com_google_android_gms_internal_clearcut_zzao, String str, T t) {
        this.zzdv = null;
        this.zzdw = null;
        this.zzdx = null;
        if (zzao.zza(com_google_android_gms_internal_clearcut_zzao) == null && zzao.zzb(com_google_android_gms_internal_clearcut_zzao) == null) {
            throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
        } else if (zzao.zza(com_google_android_gms_internal_clearcut_zzao) == null || zzao.zzb(com_google_android_gms_internal_clearcut_zzao) == null) {
            this.zzdr = com_google_android_gms_internal_clearcut_zzao;
            String valueOf = String.valueOf(zzao.zzc(com_google_android_gms_internal_clearcut_zzao));
            String valueOf2 = String.valueOf(str);
            this.zzdt = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
            valueOf = String.valueOf(zzao.zzd(com_google_android_gms_internal_clearcut_zzao));
            valueOf2 = String.valueOf(str);
            this.zzds = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
            this.zzdu = t;
        } else {
            throw new IllegalArgumentException("Must pass one of SharedPreferences file name or ContentProvider URI");
        }
    }

    public static void maybeInit(Context context) {
        if (zzh == null) {
            synchronized (zzdn) {
                if (VERSION.SDK_INT < 24 || !context.isDeviceProtectedStorage()) {
                    Context applicationContext = context.getApplicationContext();
                    if (applicationContext != null) {
                        context = applicationContext;
                    }
                }
                if (zzh != context) {
                    zzdp = null;
                }
                zzh = context;
            }
            zzdo = false;
        }
    }

    private static <T> zzae<T> zza(zzao com_google_android_gms_internal_clearcut_zzao, String str, T t, zzan<T> com_google_android_gms_internal_clearcut_zzan_T) {
        return new zzal(com_google_android_gms_internal_clearcut_zzao, str, t, com_google_android_gms_internal_clearcut_zzan_T);
    }

    private static zzae<String> zza(zzao com_google_android_gms_internal_clearcut_zzao, String str, String str2) {
        return new zzak(com_google_android_gms_internal_clearcut_zzao, str, str2);
    }

    private static zzae<Boolean> zza(zzao com_google_android_gms_internal_clearcut_zzao, String str, boolean z) {
        return new zzaj(com_google_android_gms_internal_clearcut_zzao, str, Boolean.valueOf(z));
    }

    private static <V> V zza(zzam<V> com_google_android_gms_internal_clearcut_zzam_V) {
        V zzp;
        long clearCallingIdentity;
        try {
            zzp = com_google_android_gms_internal_clearcut_zzam_V.zzp();
        } catch (SecurityException e) {
            clearCallingIdentity = Binder.clearCallingIdentity();
            zzp = com_google_android_gms_internal_clearcut_zzam_V.zzp();
        } finally {
            Binder.restoreCallingIdentity(clearCallingIdentity);
        }
        return zzp;
    }

    static boolean zza(String str, boolean z) {
        return zzn() ? ((Boolean) zza(new zzah(str, false))).booleanValue() : false;
    }

    @TargetApi(24)
    @Nullable
    private final T zzl() {
        String valueOf;
        if (zza("gms:phenotype:phenotype_flag:debug_bypass_phenotype", false)) {
            String str = "PhenotypeFlag";
            String str2 = "Bypass reading Phenotype values for flag: ";
            valueOf = String.valueOf(this.zzds);
            Log.w(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        } else if (zzao.zzb(this.zzdr) != null) {
            if (this.zzdw == null) {
                this.zzdw = zzab.zza(zzh.getContentResolver(), zzao.zzb(this.zzdr));
            }
            valueOf = (String) zza(new zzaf(this, this.zzdw));
            if (valueOf != null) {
                return zzb(valueOf);
            }
        } else if (zzao.zza(this.zzdr) != null) {
            boolean z;
            if (VERSION.SDK_INT < 24 || zzh.isDeviceProtectedStorage()) {
                z = true;
            } else {
                if (zzdq == null || !zzdq.booleanValue()) {
                    zzdq = Boolean.valueOf(((UserManager) zzh.getSystemService(UserManager.class)).isUserUnlocked());
                }
                z = zzdq.booleanValue();
            }
            if (!z) {
                return null;
            }
            if (this.zzdx == null) {
                this.zzdx = zzh.getSharedPreferences(zzao.zza(this.zzdr), 0);
            }
            SharedPreferences sharedPreferences = this.zzdx;
            if (sharedPreferences.contains(this.zzds)) {
                return zza(sharedPreferences);
            }
        }
        return null;
    }

    @Nullable
    private final T zzm() {
        if (!zzao.zzf(this.zzdr) && zzn()) {
            String str = (String) zza(new zzag(this));
            if (str != null) {
                return zzb(str);
            }
        }
        return null;
    }

    private static boolean zzn() {
        boolean z = false;
        if (zzdp == null) {
            if (zzh == null) {
                return false;
            }
            if (PermissionChecker.checkCallingOrSelfPermission(zzh, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0) {
                z = true;
            }
            zzdp = Boolean.valueOf(z);
        }
        return zzdp.booleanValue();
    }

    public final T get() {
        if (zzh == null) {
            throw new IllegalStateException("Must call PhenotypeFlag.init() first");
        }
        T zzm;
        if (zzao.zze(this.zzdr)) {
            zzm = zzm();
            if (zzm != null) {
                return zzm;
            }
            zzm = zzl();
            if (zzm != null) {
                return zzm;
            }
        }
        zzm = zzl();
        if (zzm != null) {
            return zzm;
        }
        zzm = zzm();
        if (zzm != null) {
            return zzm;
        }
        return this.zzdu;
    }

    protected abstract T zza(SharedPreferences sharedPreferences);

    protected abstract T zzb(String str);

    final /* synthetic */ String zzo() {
        return zzy.zza(zzh.getContentResolver(), this.zzdt, null);
    }
}
