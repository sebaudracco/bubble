package com.google.android.gms.internal.ads;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECField;
import java.security.spec.ECFieldFp;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.EllipticCurve;
import javax.crypto.KeyAgreement;

public final class zzayt {
    private static BigInteger zza(EllipticCurve ellipticCurve) throws GeneralSecurityException {
        ECField field = ellipticCurve.getField();
        if (field instanceof ECFieldFp) {
            return ((ECFieldFp) field).getP();
        }
        throw new GeneralSecurityException("Only curves over prime order fields are supported");
    }

    public static KeyPair zza(ECParameterSpec eCParameterSpec) throws GeneralSecurityException {
        KeyPairGenerator keyPairGenerator = (KeyPairGenerator) zzayy.zzdoe.zzek("EC");
        keyPairGenerator.initialize(eCParameterSpec);
        return keyPairGenerator.generateKeyPair();
    }

    public static ECPublicKey zza(zzayv com_google_android_gms_internal_ads_zzayv, byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        ECParameterSpec zza = zza(com_google_android_gms_internal_ads_zzayv);
        ECPoint eCPoint = new ECPoint(new BigInteger(1, bArr), new BigInteger(1, bArr2));
        zza(eCPoint, zza.getCurve());
        return (ECPublicKey) ((KeyFactory) zzayy.zzdof.zzek("EC")).generatePublic(new ECPublicKeySpec(eCPoint, zza));
    }

    public static ECParameterSpec zza(zzayv com_google_android_gms_internal_ads_zzayv) throws NoSuchAlgorithmException {
        switch (zzayu.zzdnn[com_google_android_gms_internal_ads_zzayv.ordinal()]) {
            case 1:
                return zza("115792089210356248762697446949407573530086143415290314195533631308867097853951", "115792089210356248762697446949407573529996955224135760342422259061068512044369", "5ac635d8aa3a93e7b3ebbd55769886bc651d06b0cc53b0f63bce3c3e27d2604b", "6b17d1f2e12c4247f8bce6e563a440f277037d812deb33a0f4a13945d898c296", "4fe342e2fe1a7f9b8ee7eb4a7c0f9e162bce33576b315ececbb6406837bf51f5");
            case 2:
                return zza("39402006196394479212279040100143613805079739270465446667948293404245721771496870329047266088258938001861606973112319", "39402006196394479212279040100143613805079739270465446667946905279627659399113263569398956308152294913554433653942643", "b3312fa7e23ee7e4988e056be3f82d19181d9c6efe8141120314088f5013875ac656398d8a2ed19d2a85c8edd3ec2aef", "aa87ca22be8b05378eb1c71ef320ad746e1d3b628ba79b9859f741e082542a385502f25dbf55296c3a545e3872760ab7", "3617de4a96262c6f5d9e98bf9292dc29f8f41dbd289a147ce9da3113b5f0b8c00a60b1ce1d7e819d7a431d7c90ea0e5f");
            case 3:
                return zza("6864797660130609714981900799081393217269435300143305409394463459185543183397656052122559640661454554977296311391480858037121987999716643812574028291115057151", "6864797660130609714981900799081393217269435300143305409394463459185543183397655394245057746333217197532963996371363321113864768612440380340372808892707005449", "051953eb9618e1c9a1f929a21a0b68540eea2da725b99b315f3b8b489918ef109e156193951ec7e937b1652c0bd3bb1bf073573df883d2c34f1ef451fd46b503f00", "c6858e06b70404e9cd9e3ecb662395b4429c648139053fb521f828af606b4d3dbaa14b5e77efe75928fe1dc127a2ffa8de3348b3c1856a429bf97e7e31c2e5bd66", "11839296a789a3bc0045c8a5fb42c7d1bd998f54449579b446817afbd17273e662c97ee72995ef42640c550b9013fad0761353c7086a272c24088be94769fd16650");
            default:
                String valueOf = String.valueOf(com_google_android_gms_internal_ads_zzayv);
                throw new NoSuchAlgorithmException(new StringBuilder(String.valueOf(valueOf).length() + 22).append("curve not implemented:").append(valueOf).toString());
        }
    }

    private static ECParameterSpec zza(String str, String str2, String str3, String str4, String str5) {
        BigInteger bigInteger = new BigInteger(str);
        return new ECParameterSpec(new EllipticCurve(new ECFieldFp(bigInteger), bigInteger.subtract(new BigInteger("3")), new BigInteger(str3, 16)), new ECPoint(new BigInteger(str4, 16), new BigInteger(str5, 16)), new BigInteger(str2), 1);
    }

    static void zza(ECPoint eCPoint, EllipticCurve ellipticCurve) throws GeneralSecurityException {
        BigInteger zza = zza(ellipticCurve);
        BigInteger affineX = eCPoint.getAffineX();
        BigInteger affineY = eCPoint.getAffineY();
        if (affineX == null || affineY == null) {
            throw new GeneralSecurityException("point is at infinity");
        } else if (affineX.signum() == -1 || affineX.compareTo(zza) != -1) {
            throw new GeneralSecurityException("x is out of range");
        } else if (affineY.signum() == -1 || affineY.compareTo(zza) != -1) {
            throw new GeneralSecurityException("y is out of range");
        } else if (!affineY.multiply(affineY).mod(zza).equals(affineX.multiply(affineX).add(ellipticCurve.getA()).multiply(affineX).add(ellipticCurve.getB()).mod(zza))) {
            throw new GeneralSecurityException("Point is not on curve");
        }
    }

    public static byte[] zza(ECPrivateKey eCPrivateKey, ECPoint eCPoint) throws GeneralSecurityException {
        zza(eCPoint, eCPrivateKey.getParams().getCurve());
        ECParameterSpec params = eCPrivateKey.getParams();
        params.getCurve();
        Key generatePublic = KeyFactory.getInstance("EC").generatePublic(new ECPublicKeySpec(eCPoint, params));
        KeyAgreement keyAgreement = (KeyAgreement) zzayy.zzdod.zzek("ECDH");
        keyAgreement.init(eCPrivateKey);
        keyAgreement.doPhase(generatePublic, true);
        byte[] generateSecret = keyAgreement.generateSecret();
        EllipticCurve curve = eCPrivateKey.getParams().getCurve();
        BigInteger bigInteger = new BigInteger(1, generateSecret);
        if (bigInteger.signum() == -1 || bigInteger.compareTo(zza(curve)) != -1) {
            throw new GeneralSecurityException("shared secret is out of range");
        }
        BigInteger zza = zza(curve);
        BigInteger a = curve.getA();
        BigInteger mod = bigInteger.multiply(bigInteger).add(a).multiply(bigInteger).add(curve.getB()).mod(zza);
        if (zza.signum() != 1) {
            throw new InvalidAlgorithmParameterException("p must be positive");
        }
        BigInteger mod2 = mod.mod(zza);
        bigInteger = null;
        if (mod2.equals(BigInteger.ZERO)) {
            bigInteger = BigInteger.ZERO;
        } else {
            if (zza.testBit(0) && zza.testBit(1)) {
                bigInteger = mod2.modPow(zza.add(BigInteger.ONE).shiftRight(2), zza);
            } else if (zza.testBit(0) && !zza.testBit(1)) {
                BigInteger mod3;
                BigInteger modPow;
                mod = BigInteger.ONE;
                a = zza.subtract(BigInteger.ONE).shiftRight(1);
                bigInteger = mod;
                int i = 0;
                while (true) {
                    mod3 = bigInteger.multiply(bigInteger).subtract(mod2).mod(zza);
                    if (!mod3.equals(BigInteger.ZERO)) {
                        modPow = mod3.modPow(a, zza);
                        if (modPow.add(BigInteger.ONE).equals(zza)) {
                            break;
                        } else if (modPow.equals(BigInteger.ONE)) {
                            bigInteger = bigInteger.add(BigInteger.ONE);
                            i++;
                            if (i == 128 && !zza.isProbablePrime(80)) {
                                throw new InvalidAlgorithmParameterException("p is not prime");
                            }
                        } else {
                            throw new InvalidAlgorithmParameterException("p is not prime");
                        }
                    }
                    break;
                }
                BigInteger shiftRight = zza.add(BigInteger.ONE).shiftRight(1);
                a = BigInteger.ONE;
                int bitLength = shiftRight.bitLength() - 2;
                mod = bigInteger;
                while (bitLength >= 0) {
                    BigInteger multiply = mod.multiply(a);
                    modPow = mod.multiply(mod).add(a.multiply(a).mod(zza).multiply(mod3)).mod(zza);
                    mod = multiply.add(multiply).mod(zza);
                    if (shiftRight.testBit(bitLength)) {
                        a = modPow.multiply(bigInteger).add(mod.multiply(mod3)).mod(zza);
                        mod = bigInteger.multiply(mod).add(modPow).mod(zza);
                    } else {
                        a = modPow;
                    }
                    bitLength--;
                    BigInteger bigInteger2 = mod;
                    mod = a;
                    a = bigInteger2;
                }
                bigInteger = mod;
            }
            if (bigInteger != null && bigInteger.multiply(bigInteger).mod(zza).compareTo(mod2) != 0) {
                throw new GeneralSecurityException("Could not find a modular square root");
            }
        }
        if (true != bigInteger.testBit(0)) {
            zza.subtract(bigInteger).mod(zza);
        }
        return generateSecret;
    }

    public static int zzb(EllipticCurve ellipticCurve) throws GeneralSecurityException {
        return (zza(ellipticCurve).subtract(BigInteger.ONE).bitLength() + 7) / 8;
    }
}
