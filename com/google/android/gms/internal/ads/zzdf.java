package com.google.android.gms.internal.ads;

import java.util.HashMap;

public final class zzdf extends zzbh<Integer, Long> {
    public Long zzfp;
    public Long zzfr;
    public Long zzfv;
    public Long zzfw;
    public Long zzsp;
    public Long zzsq;
    public Long zzsr;
    public Long zzss;
    public Long zzst;
    public Long zzsu;
    public Long zzsv;

    public zzdf(String str) {
        zzj(str);
    }

    protected final void zzj(String str) {
        HashMap zzk = zzbh.zzk(str);
        if (zzk != null) {
            this.zzsp = (Long) zzk.get(Integer.valueOf(0));
            this.zzsq = (Long) zzk.get(Integer.valueOf(1));
            this.zzsr = (Long) zzk.get(Integer.valueOf(2));
            this.zzfr = (Long) zzk.get(Integer.valueOf(3));
            this.zzfp = (Long) zzk.get(Integer.valueOf(4));
            this.zzss = (Long) zzk.get(Integer.valueOf(5));
            this.zzst = (Long) zzk.get(Integer.valueOf(6));
            this.zzsu = (Long) zzk.get(Integer.valueOf(7));
            this.zzfw = (Long) zzk.get(Integer.valueOf(8));
            this.zzfv = (Long) zzk.get(Integer.valueOf(9));
            this.zzsv = (Long) zzk.get(Integer.valueOf(10));
        }
    }

    protected final HashMap<Integer, Long> zzu() {
        HashMap<Integer, Long> hashMap = new HashMap();
        hashMap.put(Integer.valueOf(0), this.zzsp);
        hashMap.put(Integer.valueOf(1), this.zzsq);
        hashMap.put(Integer.valueOf(2), this.zzsr);
        hashMap.put(Integer.valueOf(3), this.zzfr);
        hashMap.put(Integer.valueOf(4), this.zzfp);
        hashMap.put(Integer.valueOf(5), this.zzss);
        hashMap.put(Integer.valueOf(6), this.zzst);
        hashMap.put(Integer.valueOf(7), this.zzsu);
        hashMap.put(Integer.valueOf(8), this.zzfw);
        hashMap.put(Integer.valueOf(9), this.zzfv);
        hashMap.put(Integer.valueOf(10), this.zzsv);
        return hashMap;
    }
}
