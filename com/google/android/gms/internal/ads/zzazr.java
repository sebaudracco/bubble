package com.google.android.gms.internal.ads;

import java.io.PrintStream;
import java.io.PrintWriter;

public final class zzazr {
    private static final zzazs zzdov;
    private static final int zzdow;

    static final class zza extends zzazs {
        zza() {
        }

        public final void zza(Throwable th, PrintWriter printWriter) {
            th.printStackTrace(printWriter);
        }
    }

    static {
        Integer zzaau;
        zzazs com_google_android_gms_internal_ads_zzazw;
        Throwable th;
        PrintStream printStream;
        String name;
        try {
            zzaau = zzaau();
            if (zzaau != null) {
                try {
                    if (zzaau.intValue() >= 19) {
                        com_google_android_gms_internal_ads_zzazw = new zzazw();
                        zzdov = com_google_android_gms_internal_ads_zzazw;
                        zzdow = zzaau != null ? 1 : zzaau.intValue();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    printStream = System.err;
                    name = zza.class.getName();
                    printStream.println(new StringBuilder(String.valueOf(name).length() + 132).append("An error has occured when initializing the try-with-resources desuguring strategy. The default strategy ").append(name).append("will be used. The error is: ").toString());
                    th.printStackTrace(System.err);
                    com_google_android_gms_internal_ads_zzazw = new zza();
                    zzdov = com_google_android_gms_internal_ads_zzazw;
                    if (zzaau != null) {
                    }
                    zzdow = zzaau != null ? 1 : zzaau.intValue();
                }
            }
            com_google_android_gms_internal_ads_zzazw = (!Boolean.getBoolean("com.google.devtools.build.android.desugar.runtime.twr_disable_mimic") ? 1 : null) != null ? new zzazv() : new zza();
        } catch (Throwable th3) {
            Throwable th4 = th3;
            zzaau = null;
            th = th4;
            printStream = System.err;
            name = zza.class.getName();
            printStream.println(new StringBuilder(String.valueOf(name).length() + 132).append("An error has occured when initializing the try-with-resources desuguring strategy. The default strategy ").append(name).append("will be used. The error is: ").toString());
            th.printStackTrace(System.err);
            com_google_android_gms_internal_ads_zzazw = new zza();
            zzdov = com_google_android_gms_internal_ads_zzazw;
            if (zzaau != null) {
            }
            zzdow = zzaau != null ? 1 : zzaau.intValue();
        }
        zzdov = com_google_android_gms_internal_ads_zzazw;
        if (zzaau != null) {
        }
        zzdow = zzaau != null ? 1 : zzaau.intValue();
    }

    public static void zza(Throwable th, PrintWriter printWriter) {
        zzdov.zza(th, printWriter);
    }

    private static Integer zzaau() {
        try {
            return (Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
        } catch (Exception e) {
            System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
            e.printStackTrace(System.err);
            return null;
        }
    }
}
