package com.google.android.gms.internal.ads;

import java.util.HashMap;

public final class zzcy extends zzbh<Integer, Long> {
    public Long zzrr;
    public Long zzrs;

    public zzcy(String str) {
        zzj(str);
    }

    protected final void zzj(String str) {
        HashMap zzk = zzbh.zzk(str);
        if (zzk != null) {
            this.zzrr = (Long) zzk.get(Integer.valueOf(0));
            this.zzrs = (Long) zzk.get(Integer.valueOf(1));
        }
    }

    protected final HashMap<Integer, Long> zzu() {
        HashMap<Integer, Long> hashMap = new HashMap();
        hashMap.put(Integer.valueOf(0), this.zzrr);
        hashMap.put(Integer.valueOf(1), this.zzrs);
        return hashMap;
    }
}
