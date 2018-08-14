package com.fyber.requesters.p097a.p098a;

import com.fyber.requesters.p097a.C2579k;
import com.fyber.utils.FyberLogger;
import cz.msebera.android.httpclient.HttpStatus;
import java.util.Arrays;
import java.util.Locale;

/* compiled from: TtlCacheValidator */
public final class C2618q implements C2595e {
    private static final int[] f6524a = new int[]{1, 5, 25, 125, HttpStatus.SC_MULTIPLE_CHOICES};

    public final boolean mo3995a(C2602f c2602f, C2579k c2579k) {
        boolean z;
        FyberLogger.m8448d("TtlCacheValidator", "Checking cached response ttl...");
        long currentTimeMillis = System.currentTimeMillis() - c2602f.m8342b();
        C2600c c2600c;
        switch (c2602f.m8345d()) {
            case 0:
                int a;
                boolean z2;
                FyberLogger.m8448d("TtlCacheValidator", "Checking for response type - SUCCESS");
                c2600c = (C2600c) c2602f.m8344c().mo3970a("CACHE_CONFIG");
                if (c2600c != null) {
                    a = c2600c.m8336a();
                    if (a >= 0) {
                        a = C2618q.m8391a(a);
                        FyberLogger.m8448d("TtlCacheValidator", "Current value - " + a);
                        z2 = currentTimeMillis >= ((long) (a * 1000));
                        if (z2) {
                            c2602f.m8344c().mo3973b("CACHE_TTL", Integer.valueOf(a));
                            z = z2;
                            break;
                        }
                        z = z2;
                        break;
                    }
                }
                a = HttpStatus.SC_MULTIPLE_CHOICES;
                FyberLogger.m8448d("TtlCacheValidator", "Current value - " + a);
                if (currentTimeMillis >= ((long) (a * 1000))) {
                }
                if (z2) {
                    c2602f.m8344c().mo3973b("CACHE_TTL", Integer.valueOf(a));
                    z = z2;
                } else {
                    z = z2;
                }
            case 1:
                int[] iArr;
                int length;
                boolean z3;
                int g = c2602f.m8348g();
                int[] iArr2 = f6524a;
                c2600c = (C2600c) c2602f.m8344c().mo3970a("CACHE_CONFIG");
                if (c2600c == null || c2600c.m8337b() == null || c2600c.m8337b().length <= 0) {
                    iArr = iArr2;
                } else {
                    iArr = c2600c.m8337b();
                }
                FyberLogger.m8448d("TtlCacheValidator", String.format(Locale.ENGLISH, "Checking for response type - FAILURE\n\tRequest nº %d\n\twith the following values %s", new Object[]{Integer.valueOf(g), Arrays.toString(iArr)}));
                if (g >= iArr.length) {
                    length = iArr.length - 1;
                } else {
                    length = g;
                }
                if (currentTimeMillis < ((long) (C2618q.m8391a(iArr[length]) * 1000))) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (!z3) {
                    z = z3;
                    break;
                }
                c2602f.m8344c().mo3973b("CACHE_TTL", Integer.valueOf(iArr[length]));
                z = z3;
                break;
                break;
            default:
                FyberLogger.m8448d("TtlCacheValidator", "The cached response type is unsupported");
                z = false;
                break;
        }
        String str = "TtlCacheValidator";
        Locale locale = Locale.ENGLISH;
        String str2 = "The cached response is %dms old - %s valid";
        Object[] objArr = new Object[2];
        objArr[0] = Long.valueOf(currentTimeMillis);
        objArr[1] = z ? "still" : "not";
        FyberLogger.m8448d(str, String.format(locale, str2, objArr));
        return z;
    }

    private static int m8391a(int i) {
        if (i > 3600) {
            return 3600;
        }
        return i;
    }
}
