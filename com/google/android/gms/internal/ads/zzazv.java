package com.google.android.gms.internal.ads;

import java.io.PrintWriter;
import java.util.List;

final class zzazv extends zzazs {
    private final zzazt zzdpb = new zzazt();

    zzazv() {
    }

    public final void zza(Throwable th, PrintWriter printWriter) {
        th.printStackTrace(printWriter);
        List<Throwable> zza = this.zzdpb.zza(th, false);
        if (zza != null) {
            synchronized (zza) {
                for (Throwable th2 : zza) {
                    printWriter.print("Suppressed: ");
                    th2.printStackTrace(printWriter);
                }
            }
        }
    }
}
