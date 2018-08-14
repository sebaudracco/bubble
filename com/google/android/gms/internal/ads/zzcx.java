package com.google.android.gms.internal.ads;

import java.util.HashMap;

public final class zzcx extends zzbh<Integer, Object> {
    public Long zzro;
    public Boolean zzrp;
    public Boolean zzrq;

    public zzcx(String str) {
        zzj(str);
    }

    protected final void zzj(String str) {
        HashMap zzk = zzbh.zzk(str);
        if (zzk != null) {
            this.zzro = (Long) zzk.get(Integer.valueOf(0));
            this.zzrp = (Boolean) zzk.get(Integer.valueOf(1));
            this.zzrq = (Boolean) zzk.get(Integer.valueOf(2));
        }
    }

    protected final HashMap<Integer, Object> zzu() {
        HashMap<Integer, Object> hashMap = new HashMap();
        hashMap.put(Integer.valueOf(0), this.zzro);
        hashMap.put(Integer.valueOf(1), this.zzrp);
        hashMap.put(Integer.valueOf(2), this.zzrq);
        return hashMap;
    }
}
