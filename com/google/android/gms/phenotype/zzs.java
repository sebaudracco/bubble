package com.google.android.gms.phenotype;

import android.content.SharedPreferences;
import android.util.Log;

final class zzs extends PhenotypeFlag<String> {
    zzs(PhenotypeFlag$Factory phenotypeFlag$Factory, String str, String str2) {
        super(phenotypeFlag$Factory, str, str2, null);
    }

    private final String zzb(SharedPreferences sharedPreferences) {
        try {
            return sharedPreferences.getString(this.zzap, null);
        } catch (Throwable e) {
            Throwable th = e;
            String str = "PhenotypeFlag";
            String str2 = "Invalid string value in SharedPreferences for ";
            String valueOf = String.valueOf(this.zzap);
            Log.e(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), th);
            return null;
        }
    }

    public final /* synthetic */ Object zza(SharedPreferences sharedPreferences) {
        return zzb(sharedPreferences);
    }

    public final /* synthetic */ Object zza(String str) {
        return str;
    }
}
