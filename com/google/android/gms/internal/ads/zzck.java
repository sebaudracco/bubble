package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class zzck {
    private static Cipher zzrf = null;
    private static final Object zzrg = new Object();
    private static final Object zzrh = new Object();
    private final SecureRandom zzre = null;

    public zzck(SecureRandom secureRandom) {
    }

    private static Cipher getCipher() throws NoSuchAlgorithmException, NoSuchPaddingException {
        Cipher cipher;
        synchronized (zzrh) {
            if (zzrf == null) {
                zzrf = Cipher.getInstance("AES/CBC/PKCS5Padding");
            }
            cipher = zzrf;
        }
        return cipher;
    }

    public final byte[] zza(byte[] bArr, String str) throws zzcl {
        if (bArr.length != 16) {
            throw new zzcl(this);
        }
        try {
            byte[] zza = zzbi.zza(str, false);
            if (zza.length <= 16) {
                throw new zzcl(this);
            }
            ByteBuffer allocate = ByteBuffer.allocate(zza.length);
            allocate.put(zza);
            allocate.flip();
            byte[] bArr2 = new byte[16];
            zza = new byte[(zza.length - 16)];
            allocate.get(bArr2);
            allocate.get(zza);
            Key secretKeySpec = new SecretKeySpec(bArr, "AES");
            synchronized (zzrg) {
                getCipher().init(2, secretKeySpec, new IvParameterSpec(bArr2));
                zza = getCipher().doFinal(zza);
            }
            return zza;
        } catch (Throwable e) {
            throw new zzcl(this, e);
        } catch (Throwable e2) {
            throw new zzcl(this, e2);
        } catch (Throwable e22) {
            throw new zzcl(this, e22);
        } catch (Throwable e222) {
            throw new zzcl(this, e222);
        } catch (Throwable e2222) {
            throw new zzcl(this, e2222);
        } catch (Throwable e22222) {
            throw new zzcl(this, e22222);
        } catch (Throwable e222222) {
            throw new zzcl(this, e222222);
        }
    }

    public final String zzb(byte[] bArr, byte[] bArr2) throws zzcl {
        if (bArr.length != 16) {
            throw new zzcl(this);
        }
        try {
            byte[] doFinal;
            byte[] iv;
            Key secretKeySpec = new SecretKeySpec(bArr, "AES");
            synchronized (zzrg) {
                getCipher().init(1, secretKeySpec, null);
                doFinal = getCipher().doFinal(bArr2);
                iv = getCipher().getIV();
            }
            int length = doFinal.length + iv.length;
            ByteBuffer allocate = ByteBuffer.allocate(length);
            allocate.put(iv).put(doFinal);
            allocate.flip();
            doFinal = new byte[length];
            allocate.get(doFinal);
            return zzbi.zza(doFinal, false);
        } catch (Throwable e) {
            throw new zzcl(this, e);
        } catch (Throwable e2) {
            throw new zzcl(this, e2);
        } catch (Throwable e22) {
            throw new zzcl(this, e22);
        } catch (Throwable e222) {
            throw new zzcl(this, e222);
        } catch (Throwable e2222) {
            throw new zzcl(this, e2222);
        }
    }

    public final byte[] zzl(String str) throws zzcl {
        try {
            byte[] zza = zzbi.zza(str, false);
            if (zza.length != 32) {
                throw new zzcl(this);
            }
            byte[] bArr = new byte[16];
            ByteBuffer.wrap(zza, 4, 16).get(bArr);
            for (int i = 0; i < 16; i++) {
                bArr[i] = (byte) (bArr[i] ^ 68);
            }
            return bArr;
        } catch (Throwable e) {
            throw new zzcl(this, e);
        }
    }
}
