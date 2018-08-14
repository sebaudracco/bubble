package com.google.android.gms.internal.ads;

final class zzauq implements zzaua<zzatz> {
    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.ads.zzaug<com.google.android.gms.internal.ads.zzatz> zzb(java.lang.String r7, java.lang.String r8, int r9) throws java.security.GeneralSecurityException {
        /*
        r6 = this;
        r3 = 2;
        r0 = -1;
        r2 = 1;
        r1 = 0;
        r4 = r8.toLowerCase();
        r5 = r4.hashCode();
        switch(r5) {
            case 2989895: goto L_0x0024;
            default: goto L_0x000f;
        };
    L_0x000f:
        r4 = r0;
    L_0x0010:
        switch(r4) {
            case 0: goto L_0x002f;
            default: goto L_0x0013;
        };
    L_0x0013:
        r0 = new java.security.GeneralSecurityException;
        r3 = "No support for primitive '%s'.";
        r2 = new java.lang.Object[r2];
        r2[r1] = r8;
        r1 = java.lang.String.format(r3, r2);
        r0.<init>(r1);
        throw r0;
    L_0x0024:
        r5 = "aead";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x000f;
    L_0x002d:
        r4 = r1;
        goto L_0x0010;
    L_0x002f:
        r4 = r7.hashCode();
        switch(r4) {
            case 360753376: goto L_0x006b;
            case 1215885937: goto L_0x004a;
            case 1469984853: goto L_0x0076;
            case 1797113348: goto L_0x0055;
            case 1855890991: goto L_0x0060;
            case 2079211877: goto L_0x0081;
            default: goto L_0x0036;
        };
    L_0x0036:
        switch(r0) {
            case 0: goto L_0x008c;
            case 1: goto L_0x00ae;
            case 2: goto L_0x00b4;
            case 3: goto L_0x00ba;
            case 4: goto L_0x00c0;
            case 5: goto L_0x00c6;
            default: goto L_0x0039;
        };
    L_0x0039:
        r0 = new java.security.GeneralSecurityException;
        r3 = "No support for primitive 'Aead' with key type '%s'.";
        r2 = new java.lang.Object[r2];
        r2[r1] = r7;
        r1 = java.lang.String.format(r3, r2);
        r0.<init>(r1);
        throw r0;
    L_0x004a:
        r4 = "type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey";
        r4 = r7.equals(r4);
        if (r4 == 0) goto L_0x0036;
    L_0x0053:
        r0 = r1;
        goto L_0x0036;
    L_0x0055:
        r4 = "type.googleapis.com/google.crypto.tink.AesEaxKey";
        r4 = r7.equals(r4);
        if (r4 == 0) goto L_0x0036;
    L_0x005e:
        r0 = r2;
        goto L_0x0036;
    L_0x0060:
        r4 = "type.googleapis.com/google.crypto.tink.AesGcmKey";
        r4 = r7.equals(r4);
        if (r4 == 0) goto L_0x0036;
    L_0x0069:
        r0 = r3;
        goto L_0x0036;
    L_0x006b:
        r4 = "type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key";
        r4 = r7.equals(r4);
        if (r4 == 0) goto L_0x0036;
    L_0x0074:
        r0 = 3;
        goto L_0x0036;
    L_0x0076:
        r4 = "type.googleapis.com/google.crypto.tink.KmsAeadKey";
        r4 = r7.equals(r4);
        if (r4 == 0) goto L_0x0036;
    L_0x007f:
        r0 = 4;
        goto L_0x0036;
    L_0x0081:
        r4 = "type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey";
        r4 = r7.equals(r4);
        if (r4 == 0) goto L_0x0036;
    L_0x008a:
        r0 = 5;
        goto L_0x0036;
    L_0x008c:
        r0 = new com.google.android.gms.internal.ads.zzaus;
        r0.<init>();
    L_0x0091:
        r4 = r0.getVersion();
        if (r4 >= r9) goto L_0x00cc;
    L_0x0097:
        r0 = new java.security.GeneralSecurityException;
        r4 = "No key manager for key type '%s' with version at least %d.";
        r3 = new java.lang.Object[r3];
        r3[r1] = r7;
        r1 = java.lang.Integer.valueOf(r9);
        r3[r2] = r1;
        r1 = java.lang.String.format(r4, r3);
        r0.<init>(r1);
        throw r0;
    L_0x00ae:
        r0 = new com.google.android.gms.internal.ads.zzauu;
        r0.<init>();
        goto L_0x0091;
    L_0x00b4:
        r0 = new com.google.android.gms.internal.ads.zzauv;
        r0.<init>();
        goto L_0x0091;
    L_0x00ba:
        r0 = new com.google.android.gms.internal.ads.zzauw;
        r0.<init>();
        goto L_0x0091;
    L_0x00c0:
        r0 = new com.google.android.gms.internal.ads.zzaux;
        r0.<init>();
        goto L_0x0091;
    L_0x00c6:
        r0 = new com.google.android.gms.internal.ads.zzauz;
        r0.<init>();
        goto L_0x0091;
    L_0x00cc:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzauq.zzb(java.lang.String, java.lang.String, int):com.google.android.gms.internal.ads.zzaug<com.google.android.gms.internal.ads.zzatz>");
    }
}
