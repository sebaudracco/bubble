package com.google.android.gms.phenotype;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.UserManager;
import android.support.v4.content.PermissionChecker;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.phenotype.zzf;
import com.google.android.gms.internal.phenotype.zzh;
import javax.annotation.Nullable;

@KeepForSdk
@Deprecated
public abstract class PhenotypeFlag<T> {
    private static final Object zzak = new Object();
    @SuppressLint({"StaticFieldLeak"})
    private static Context zzal = null;
    private static boolean zzam = false;
    private static Boolean zzan = null;
    private final Factory zzao;
    final String zzap;
    private final String zzaq;
    private final T zzar;
    private T zzas;

    private PhenotypeFlag(Factory factory, String str, T t) {
        this.zzas = null;
        if (Factory.zza(factory) == null && Factory.zzb(factory) == null) {
            throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
        } else if (Factory.zza(factory) == null || Factory.zzb(factory) == null) {
            this.zzao = factory;
            String valueOf = String.valueOf(Factory.zzc(factory));
            String valueOf2 = String.valueOf(str);
            this.zzaq = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
            valueOf = String.valueOf(Factory.zzd(factory));
            valueOf2 = String.valueOf(str);
            this.zzap = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
            this.zzar = t;
        } else {
            throw new IllegalArgumentException("Must pass one of SharedPreferences file name or ContentProvider URI");
        }
    }

    @KeepForSdk
    public static void maybeInit(Context context) {
        zzh.maybeInit(context);
        if (zzal == null) {
            zzh.init(context);
            synchronized (zzak) {
                if (VERSION.SDK_INT < 24 || !context.isDeviceProtectedStorage()) {
                    Context applicationContext = context.getApplicationContext();
                    if (applicationContext != null) {
                        context = applicationContext;
                    }
                }
                if (zzal != context) {
                    zzan = null;
                }
                zzal = context;
            }
            zzam = false;
        }
    }

    private static PhenotypeFlag<String> zza(Factory factory, String str, String str2) {
        return new zzs(factory, str, str2);
    }

    private static <V> V zza(zza<V> com_google_android_gms_phenotype_PhenotypeFlag_zza_V) {
        V zzh;
        long clearCallingIdentity;
        try {
            zzh = com_google_android_gms_phenotype_PhenotypeFlag_zza_V.zzh();
        } catch (SecurityException e) {
            clearCallingIdentity = Binder.clearCallingIdentity();
            zzh = com_google_android_gms_phenotype_PhenotypeFlag_zza_V.zzh();
        } finally {
            Binder.restoreCallingIdentity(clearCallingIdentity);
        }
        return zzh;
    }

    static boolean zza(String str, boolean z) {
        return zzf() ? ((Boolean) zza(new zzq(str, false))).booleanValue() : false;
    }

    @TargetApi(24)
    @Nullable
    private final T zzd() {
        String valueOf;
        if (zza("gms:phenotype:phenotype_flag:debug_bypass_phenotype", false)) {
            String str = "PhenotypeFlag";
            String str2 = "Bypass reading Phenotype values for flag: ";
            valueOf = String.valueOf(this.zzap);
            Log.w(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        } else if (Factory.zzb(this.zzao) != null) {
            valueOf = (String) zza(new zzo(this, zza.zza(zzal.getContentResolver(), Factory.zzb(this.zzao))));
            if (valueOf != null) {
                return zza(valueOf);
            }
        } else if (Factory.zza(this.zzao) != null) {
            if (VERSION.SDK_INT >= 24 && !zzal.isDeviceProtectedStorage() && !((UserManager) zzal.getSystemService(UserManager.class)).isUserUnlocked()) {
                return null;
            }
            SharedPreferences sharedPreferences = zzal.getSharedPreferences(Factory.zza(this.zzao), 0);
            if (sharedPreferences.contains(this.zzap)) {
                return zza(sharedPreferences);
            }
        }
        return null;
    }

    @Nullable
    private final T zze() {
        if (!Factory.zzf(this.zzao) && zzf()) {
            String str = (String) zza(new zzp(this));
            if (str != null) {
                return zza(str);
            }
        }
        return null;
    }

    private static boolean zzf() {
        boolean z = false;
        if (zzan == null) {
            if (zzal == null) {
                return false;
            }
            if (PermissionChecker.checkCallingOrSelfPermission(zzal, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0) {
                z = true;
            }
            zzan = Boolean.valueOf(z);
        }
        return zzan.booleanValue();
    }

    @KeepForSdk
    public T get() {
        if (zzal == null) {
            throw new IllegalStateException("Must call PhenotypeFlag.init() first");
        }
        T zze;
        if (Factory.zze(this.zzao)) {
            zze = zze();
            if (zze != null) {
                return zze;
            }
            zze = zzd();
            if (zze != null) {
                return zze;
            }
        }
        zze = zzd();
        if (zze != null) {
            return zze;
        }
        zze = zze();
        if (zze != null) {
            return zze;
        }
        return this.zzar;
    }

    public abstract T zza(SharedPreferences sharedPreferences);

    public abstract T zza(String str);

    final /* synthetic */ String zzg() {
        return zzf.zza(zzal.getContentResolver(), this.zzaq, null);
    }
}
