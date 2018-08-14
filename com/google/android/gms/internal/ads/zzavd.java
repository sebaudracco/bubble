package com.google.android.gms.internal.ads;

final class zzavd implements zzaua<zzaue> {
    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.ads.zzaug<com.google.android.gms.internal.ads.zzaue> zzb(java.lang.String r6, java.lang.String r7, int r8) throws java.security.GeneralSecurityException {
        /*
        r5 = this;
        r0 = -1;
        r4 = 1;
        r1 = 0;
        r2 = r7.toLowerCase();
        r3 = r2.hashCode();
        switch(r3) {
            case 275448849: goto L_0x0023;
            default: goto L_0x000e;
        };
    L_0x000e:
        r2 = r0;
    L_0x000f:
        switch(r2) {
            case 0: goto L_0x002e;
            default: goto L_0x0012;
        };
    L_0x0012:
        r0 = new java.security.GeneralSecurityException;
        r2 = "No support for primitive '%s'.";
        r3 = new java.lang.Object[r4];
        r3[r1] = r7;
        r1 = java.lang.String.format(r2, r3);
        r0.<init>(r1);
        throw r0;
    L_0x0023:
        r3 = "hybriddecrypt";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x000e;
    L_0x002c:
        r2 = r1;
        goto L_0x000f;
    L_0x002e:
        r2 = r6.hashCode();
        switch(r2) {
            case -80133005: goto L_0x0049;
            default: goto L_0x0035;
        };
    L_0x0035:
        switch(r0) {
            case 0: goto L_0x0054;
            default: goto L_0x0038;
        };
    L_0x0038:
        r0 = new java.security.GeneralSecurityException;
        r2 = "No support for primitive 'HybridEncrypt' with key type '%s'.";
        r3 = new java.lang.Object[r4];
        r3[r1] = r6;
        r1 = java.lang.String.format(r2, r3);
        r0.<init>(r1);
        throw r0;
    L_0x0049:
        r2 = "type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey";
        r2 = r6.equals(r2);
        if (r2 == 0) goto L_0x0035;
    L_0x0052:
        r0 = r1;
        goto L_0x0035;
    L_0x0054:
        r0 = new com.google.android.gms.internal.ads.zzava;
        r0.<init>();
        if (r8 <= 0) goto L_0x0073;
    L_0x005b:
        r0 = new java.security.GeneralSecurityException;
        r2 = "No key manager for key type '%s' with version at least %d.";
        r3 = 2;
        r3 = new java.lang.Object[r3];
        r3[r1] = r6;
        r1 = java.lang.Integer.valueOf(r8);
        r3[r4] = r1;
        r1 = java.lang.String.format(r2, r3);
        r0.<init>(r1);
        throw r0;
    L_0x0073:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzavd.zzb(java.lang.String, java.lang.String, int):com.google.android.gms.internal.ads.zzaug<com.google.android.gms.internal.ads.zzaue>");
    }
}
