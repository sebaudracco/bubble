package com.google.android.gms.phenotype;

import java.util.Comparator;

final class zzj implements Comparator<zzi> {
    zzj() {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzi com_google_android_gms_phenotype_zzi = (zzi) obj;
        zzi com_google_android_gms_phenotype_zzi2 = (zzi) obj2;
        return com_google_android_gms_phenotype_zzi.zzah == com_google_android_gms_phenotype_zzi2.zzah ? com_google_android_gms_phenotype_zzi.name.compareTo(com_google_android_gms_phenotype_zzi2.name) : com_google_android_gms_phenotype_zzi.zzah - com_google_android_gms_phenotype_zzi2.zzah;
    }
}
