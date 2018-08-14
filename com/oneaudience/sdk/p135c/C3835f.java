package com.oneaudience.sdk.p135c;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import java.io.ByteArrayInputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class C3835f {
    private static final String f9191a = C3835f.class.getSimpleName();

    public static PackageInfo m12263a(PackageManager packageManager, String str) {
        PackageInfo packageInfo = null;
        if (!(str == null || packageManager == null)) {
            try {
                packageInfo = packageManager.getPackageInfo(str, 0);
            } catch (Exception e) {
                C3833d.m12253c(f9191a, "Package %s was not found", str);
            }
        }
        return packageInfo;
    }

    public static boolean m12264a(Context context) {
        try {
            Signature[] signatureArr = new Signature[0];
            Signature[] signatureArr2 = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures;
            int length = signatureArr2.length;
            int i = 0;
            while (i < length) {
                try {
                    String name = ((X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(new ByteArrayInputStream(signatureArr2[i].toByteArray()))).getSubjectDN().getName();
                    C3833d.m12253c(f9191a, "Certificate: %s", name);
                    String str = f9191a;
                    String str2 = "Certificate subject: %s";
                    Object[] objArr = new Object[1];
                    boolean z = name.contains("Android Debug") || name.contains("common_name");
                    objArr[0] = Boolean.valueOf(z);
                    C3833d.m12253c(str, str2, objArr);
                    return name.contains("Android Debug") || name.contains("common_name");
                } catch (CertificateException e) {
                    C3833d.m12252c("Certificate Exception checking for debug keystore: %s", e.getLocalizedMessage());
                    i++;
                }
            }
        } catch (Exception e2) {
            C3833d.m12252c("General Exception checking for debug keystore: %s", e2.getLocalizedMessage());
        }
        return false;
    }
}
