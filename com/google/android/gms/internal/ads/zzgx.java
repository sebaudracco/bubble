package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.VisibleForTesting;
import java.util.PriorityQueue;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzgx {
    @VisibleForTesting
    private static long zza(long j, int i) {
        return i == 0 ? 1 : i != 1 ? i % 2 == 0 ? zza((j * j) % 1073807359, i / 2) % 1073807359 : ((zza((j * j) % 1073807359, i / 2) % 1073807359) * j) % 1073807359 : j;
    }

    @VisibleForTesting
    private static String zza(String[] strArr, int i, int i2) {
        if (strArr.length < i + i2) {
            zzane.m3427e("Unable to construct shingle");
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i3 = i; i3 < (i + i2) - 1; i3++) {
            stringBuilder.append(strArr[i3]);
            stringBuilder.append(' ');
        }
        stringBuilder.append(strArr[(i + i2) - 1]);
        return stringBuilder.toString();
    }

    @VisibleForTesting
    private static void zza(int i, long j, String str, int i2, PriorityQueue<zzgy> priorityQueue) {
        zzgy com_google_android_gms_internal_ads_zzgy = new zzgy(j, str, i2);
        if ((priorityQueue.size() != i || (((zzgy) priorityQueue.peek()).zzajg <= com_google_android_gms_internal_ads_zzgy.zzajg && ((zzgy) priorityQueue.peek()).value <= com_google_android_gms_internal_ads_zzgy.value)) && !priorityQueue.contains(com_google_android_gms_internal_ads_zzgy)) {
            priorityQueue.add(com_google_android_gms_internal_ads_zzgy);
            if (priorityQueue.size() > i) {
                priorityQueue.poll();
            }
        }
    }

    public static void zza(String[] strArr, int i, int i2, PriorityQueue<zzgy> priorityQueue) {
        if (strArr.length < i2) {
            zza(i, zzb(strArr, 0, strArr.length), zza(strArr, 0, strArr.length), strArr.length, priorityQueue);
            return;
        }
        long zzb = zzb(strArr, 0, i2);
        zza(i, zzb, zza(strArr, 0, i2), i2, priorityQueue);
        long zza = zza(16785407, i2 - 1);
        for (int i3 = 1; i3 < (strArr.length - i2) + 1; i3++) {
            zzb += 1073807359;
            zzb = (((((zzb - ((((((long) zzgu.zzz(strArr[i3 - 1])) + 2147483647L) % 1073807359) * zza) % 1073807359)) % 1073807359) * 16785407) % 1073807359) + ((((long) zzgu.zzz(strArr[(i3 + i2) - 1])) + 2147483647L) % 1073807359)) % 1073807359;
            zza(i, zzb, zza(strArr, i3, i2), strArr.length, priorityQueue);
        }
    }

    private static long zzb(String[] strArr, int i, int i2) {
        long zzz = (((long) zzgu.zzz(strArr[0])) + 2147483647L) % 1073807359;
        for (int i3 = 1; i3 < i2; i3++) {
            zzz = (((zzz * 16785407) % 1073807359) + ((((long) zzgu.zzz(strArr[i3])) + 2147483647L) % 1073807359)) % 1073807359;
        }
        return zzz;
    }
}
