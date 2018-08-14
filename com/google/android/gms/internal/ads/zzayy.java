package com.google.android.gms.internal.ads;

import com.google.android.gms.security.ProviderInstaller;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.Provider;
import java.security.Security;
import java.security.Signature;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.Mac;

public final class zzayy<T_WRAPPER extends zzayz<T_ENGINE>, T_ENGINE> {
    private static final Logger logger = Logger.getLogger(zzayy.class.getName());
    private static final List<Provider> zzdny;
    public static final zzayy<zzaza, Cipher> zzdnz = new zzayy(new zzaza());
    public static final zzayy<zzaze, Mac> zzdoa = new zzayy(new zzaze());
    private static final zzayy<zzazg, Signature> zzdob = new zzayy(new zzazg());
    private static final zzayy<zzazf, MessageDigest> zzdoc = new zzayy(new zzazf());
    public static final zzayy<zzazb, KeyAgreement> zzdod = new zzayy(new zzazb());
    public static final zzayy<zzazd, KeyPairGenerator> zzdoe = new zzayy(new zzazd());
    public static final zzayy<zzazc, KeyFactory> zzdof = new zzayy(new zzazc());
    private T_WRAPPER zzdog;
    private List<Provider> zzdoh = zzdny;
    private boolean zzdoi = true;

    static {
        if (zzazp.zzaat()) {
            String[] strArr = new String[]{ProviderInstaller.PROVIDER_NAME, "AndroidOpenSSL"};
            List arrayList = new ArrayList();
            for (int i = 0; i < 2; i++) {
                Provider provider = Security.getProvider(strArr[i]);
                if (provider != null) {
                    arrayList.add(provider);
                } else {
                    logger.logp(Level.INFO, "com.google.crypto.tink.subtle.EngineFactory", "toProviderList", String.format("Provider %s not available", new Object[]{r4}));
                }
            }
            zzdny = arrayList;
        } else {
            zzdny = new ArrayList();
        }
    }

    private zzayy(T_WRAPPER t_wrapper) {
        this.zzdog = t_wrapper;
    }

    private final boolean zza(String str, Provider provider) {
        try {
            this.zzdog.zzb(str, provider);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public final T_ENGINE zzek(String str) throws GeneralSecurityException {
        for (Provider provider : this.zzdoh) {
            if (zza(str, provider)) {
                return this.zzdog.zzb(str, provider);
            }
        }
        if (this.zzdoi) {
            return this.zzdog.zzb(str, null);
        }
        throw new GeneralSecurityException("No good Provider found.");
    }
}
