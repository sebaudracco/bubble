package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;

public final class zzazj implements zzauk {
    private Mac zzdoj;
    private final int zzdok;
    private final String zzdol;
    private final Key zzdom;

    public zzazj(String str, Key key, int i) throws GeneralSecurityException {
        if (i < 10) {
            throw new InvalidAlgorithmParameterException("tag size too small, need at least 10 bytes");
        }
        Object obj = -1;
        switch (str.hashCode()) {
            case -1823053428:
                if (str.equals("HMACSHA1")) {
                    obj = null;
                    break;
                }
                break;
            case 392315118:
                if (str.equals("HMACSHA256")) {
                    obj = 1;
                    break;
                }
                break;
            case 392317873:
                if (str.equals("HMACSHA512")) {
                    obj = 2;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                if (i > 20) {
                    throw new InvalidAlgorithmParameterException("tag size too big");
                }
                break;
            case 1:
                if (i > 32) {
                    throw new InvalidAlgorithmParameterException("tag size too big");
                }
                break;
            case 2:
                if (i > 64) {
                    throw new InvalidAlgorithmParameterException("tag size too big");
                }
                break;
            default:
                String str2 = "unknown Hmac algorithm: ";
                String valueOf = String.valueOf(str);
                throw new NoSuchAlgorithmException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        }
        this.zzdol = str;
        this.zzdok = i;
        this.zzdom = key;
        this.zzdoj = (Mac) zzayy.zzdoa.zzek(str);
        this.zzdoj.init(key);
    }

    public final byte[] zzg(byte[] bArr) throws GeneralSecurityException {
        Mac mac;
        try {
            mac = (Mac) this.zzdoj.clone();
        } catch (CloneNotSupportedException e) {
            mac = (Mac) zzayy.zzdoa.zzek(this.zzdol);
            mac.init(this.zzdom);
        }
        mac.update(bArr);
        Object obj = new byte[this.zzdok];
        System.arraycopy(mac.doFinal(), 0, obj, 0, this.zzdok);
        return obj;
    }
}
