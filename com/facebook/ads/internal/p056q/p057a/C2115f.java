package com.facebook.ads.internal.p056q.p057a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.Signature;
import android.os.Build;
import android.support.annotation.Nullable;
import com.google.android.gms.common.util.AndroidUtilsLight;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;

public class C2115f {
    private static final String f5027a = C2115f.class.getSimpleName();

    public enum C2114a {
        f5022a(0),
        UNROOTED(1),
        ROOTED(2);
        
        public final int f5026d;

        private C2114a(int i) {
            this.f5026d = i;
        }
    }

    public static C2114a m6782a() {
        try {
            Object obj = (C2115f.m6788c() || C2115f.m6787b() || C2115f.m6785a("su")) ? 1 : null;
            return obj != null ? C2114a.ROOTED : C2114a.UNROOTED;
        } catch (Throwable th) {
            return C2114a.f5022a;
        }
    }

    @Nullable
    public static String m6783a(Context context) {
        try {
            return C2115f.m6786b(context);
        } catch (Exception e) {
            return null;
        }
    }

    private static PublicKey m6784a(Signature signature) {
        return CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(signature.toByteArray())).getPublicKey();
    }

    private static boolean m6785a(String str) {
        for (String file : System.getenv("PATH").split(":")) {
            File file2 = new File(file);
            if (file2.exists() && file2.isDirectory()) {
                File[] listFiles = file2.listFiles();
                if (listFiles != null) {
                    for (File name : listFiles) {
                        if (name.getName().equals(str)) {
                            return true;
                        }
                    }
                    continue;
                } else {
                    continue;
                }
            }
        }
        return false;
    }

    @SuppressLint({"PackageManagerGetSignatures"})
    private static String m6786b(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Signature a : context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures) {
            stringBuilder.append(C2117h.m6794a(MessageDigest.getInstance(AndroidUtilsLight.DIGEST_ALGORITHM_SHA1).digest(C2115f.m6784a(a).getEncoded())));
            stringBuilder.append(";");
        }
        return stringBuilder.toString();
    }

    private static boolean m6787b() {
        String str = Build.TAGS;
        return str != null && str.contains("test-keys");
    }

    private static boolean m6788c() {
        return new File("/system/app/Superuser.apk").exists();
    }
}
