package com.google.android.gms.internal.ads;

import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbv;
import java.math.BigInteger;
import java.util.Locale;
import javax.annotation.concurrent.GuardedBy;

@zzadh
public final class zzajw {
    private static final Object sLock = new Object();
    @GuardedBy("sLock")
    private static String zzcqq;

    public static String zzc(Context context, String str, String str2) {
        String str3;
        synchronized (sLock) {
            if (zzcqq == null && !TextUtils.isEmpty(str)) {
                try {
                    ClassLoader classLoader = context.createPackageContext(str2, 3).getClassLoader();
                    Class cls = Class.forName("com.google.ads.mediation.MediationAdapter", false, classLoader);
                    BigInteger bigInteger = new BigInteger(new byte[1]);
                    String[] split = str.split(",");
                    BigInteger bigInteger2 = bigInteger;
                    for (int i = 0; i < split.length; i++) {
                        zzbv.zzek();
                        if (zzakk.zza(classLoader, cls, split[i])) {
                            bigInteger2 = bigInteger2.setBit(i);
                        }
                    }
                    zzcqq = String.format(Locale.US, "%X", new Object[]{bigInteger2});
                } catch (Throwable th) {
                    zzcqq = NotificationCompat.CATEGORY_ERROR;
                }
            }
            str3 = zzcqq;
        }
        return str3;
    }

    public static String zzqn() {
        String str;
        synchronized (sLock) {
            str = zzcqq;
        }
        return str;
    }
}
