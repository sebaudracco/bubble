package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.List;

public final class zzals {
    private final List<String> zzctc = new ArrayList();
    private final List<Double> zzctd = new ArrayList();
    private final List<Double> zzcte = new ArrayList();

    public final zzals zza(String str, double d, double d2) {
        int i = 0;
        while (i < this.zzctc.size()) {
            double doubleValue = ((Double) this.zzcte.get(i)).doubleValue();
            double doubleValue2 = ((Double) this.zzctd.get(i)).doubleValue();
            if (d < doubleValue || (doubleValue == d && d2 < doubleValue2)) {
                break;
            }
            i++;
        }
        this.zzctc.add(i, str);
        this.zzcte.add(i, Double.valueOf(d));
        this.zzctd.add(i, Double.valueOf(d2));
        return this;
    }

    public final zzalp zzrz() {
        return new zzalp(this, null);
    }
}
