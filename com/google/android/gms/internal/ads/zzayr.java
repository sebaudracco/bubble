package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public final class zzayr {
    private ECPublicKey zzdnj;

    public zzayr(ECPublicKey eCPublicKey) {
        this.zzdnj = eCPublicKey;
    }

    public final zzays zza(String str, byte[] bArr, byte[] bArr2, int i, zzayw com_google_android_gms_internal_ads_zzayw) throws GeneralSecurityException {
        KeyPair zza = zzayt.zza(this.zzdnj.getParams());
        ECPublicKey eCPublicKey = (ECPublicKey) zza.getPublic();
        ECPrivateKey eCPrivateKey = (ECPrivateKey) zza.getPrivate();
        ECPublicKey eCPublicKey2 = this.zzdnj;
        ECParameterSpec params = eCPublicKey2.getParams();
        ECParameterSpec params2 = eCPrivateKey.getParams();
        if (params.getCurve().equals(params2.getCurve()) && params.getGenerator().equals(params2.getGenerator()) && params.getOrder().equals(params2.getOrder()) && params.getCofactor() == params2.getCofactor()) {
            Object toByteArray;
            byte[] bArr3;
            byte[] zza2 = zzayt.zza(eCPrivateKey, eCPublicKey2.getW());
            EllipticCurve curve = eCPublicKey.getParams().getCurve();
            ECPoint w = eCPublicKey.getW();
            zzayt.zza(w, curve);
            int zzb = zzayt.zzb(curve);
            Object obj;
            switch (zzayu.zzdnm[com_google_android_gms_internal_ads_zzayw.ordinal()]) {
                case 1:
                    obj = new byte[((zzb * 2) + 1)];
                    Object toByteArray2 = w.getAffineX().toByteArray();
                    toByteArray = w.getAffineY().toByteArray();
                    System.arraycopy(toByteArray, 0, obj, ((zzb * 2) + 1) - toByteArray.length, toByteArray.length);
                    System.arraycopy(toByteArray2, 0, obj, (zzb + 1) - toByteArray2.length, toByteArray2.length);
                    obj[0] = (byte) 4;
                    bArr3 = obj;
                    break;
                case 2:
                    bArr3 = new byte[(zzb + 1)];
                    obj = w.getAffineX().toByteArray();
                    System.arraycopy(obj, 0, bArr3, (zzb + 1) - obj.length, obj.length);
                    bArr3[0] = (byte) (w.getAffineY().testBit(0) ? 3 : 2);
                    break;
                default:
                    String valueOf = String.valueOf(com_google_android_gms_internal_ads_zzayw);
                    throw new GeneralSecurityException(new StringBuilder(String.valueOf(valueOf).length() + 15).append("invalid format:").append(valueOf).toString());
            }
            byte[] zza3 = zzayk.zza(bArr3, zza2);
            Mac mac = (Mac) zzayy.zzdoa.zzek(str);
            if (i > mac.getMacLength() * 255) {
                throw new GeneralSecurityException("size too large");
            }
            if (bArr == null || bArr.length == 0) {
                mac.init(new SecretKeySpec(new byte[mac.getMacLength()], str));
            } else {
                mac.init(new SecretKeySpec(bArr, str));
            }
            toByteArray = new byte[i];
            mac.init(new SecretKeySpec(mac.doFinal(zza3), str));
            zza3 = new byte[0];
            int i2 = 1;
            int i3 = 0;
            while (true) {
                mac.update(zza3);
                mac.update(bArr2);
                mac.update((byte) i2);
                zza3 = mac.doFinal();
                if (zza3.length + i3 < i) {
                    System.arraycopy(zza3, 0, toByteArray, i3, zza3.length);
                    i3 += zza3.length;
                    i2++;
                } else {
                    System.arraycopy(zza3, 0, toByteArray, i3, i - i3);
                    return new zzays(bArr3, toByteArray);
                }
            }
        }
        throw new GeneralSecurityException("invalid public key spec");
    }
}
