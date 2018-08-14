package com.google.android.gms.internal.measurement;

import java.io.PrintStream;

public final class zzxd {
    private static final zzxe zzbnx;
    private static final int zzbny;

    static final class zza extends zzxe {
        zza() {
        }

        public final void zza(Throwable th, PrintStream printStream) {
            th.printStackTrace(printStream);
        }
    }

    static {
        Integer zzsd;
        zzxe com_google_android_gms_internal_measurement_zzxi;
        Throwable th;
        PrintStream printStream;
        String name;
        try {
            zzsd = zzsd();
            if (zzsd != null) {
                try {
                    if (zzsd.intValue() >= 19) {
                        com_google_android_gms_internal_measurement_zzxi = new zzxi();
                        zzbnx = com_google_android_gms_internal_measurement_zzxi;
                        zzbny = zzsd != null ? 1 : zzsd.intValue();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    printStream = System.err;
                    name = zza.class.getName();
                    printStream.println(new StringBuilder(String.valueOf(name).length() + 132).append("An error has occured when initializing the try-with-resources desuguring strategy. The default strategy ").append(name).append("will be used. The error is: ").toString());
                    th.printStackTrace(System.err);
                    com_google_android_gms_internal_measurement_zzxi = new zza();
                    zzbnx = com_google_android_gms_internal_measurement_zzxi;
                    if (zzsd != null) {
                    }
                    zzbny = zzsd != null ? 1 : zzsd.intValue();
                }
            }
            com_google_android_gms_internal_measurement_zzxi = (!Boolean.getBoolean("com.google.devtools.build.android.desugar.runtime.twr_disable_mimic") ? 1 : null) != null ? new zzxh() : new zza();
        } catch (Throwable th3) {
            Throwable th4 = th3;
            zzsd = null;
            th = th4;
            printStream = System.err;
            name = zza.class.getName();
            printStream.println(new StringBuilder(String.valueOf(name).length() + 132).append("An error has occured when initializing the try-with-resources desuguring strategy. The default strategy ").append(name).append("will be used. The error is: ").toString());
            th.printStackTrace(System.err);
            com_google_android_gms_internal_measurement_zzxi = new zza();
            zzbnx = com_google_android_gms_internal_measurement_zzxi;
            if (zzsd != null) {
            }
            zzbny = zzsd != null ? 1 : zzsd.intValue();
        }
        zzbnx = com_google_android_gms_internal_measurement_zzxi;
        if (zzsd != null) {
        }
        zzbny = zzsd != null ? 1 : zzsd.intValue();
    }

    public static void zza(Throwable th, PrintStream printStream) {
        zzbnx.zza(th, printStream);
    }

    private static Integer zzsd() {
        try {
            return (Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
        } catch (Exception e) {
            System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
            e.printStackTrace(System.err);
            return null;
        }
    }
}
