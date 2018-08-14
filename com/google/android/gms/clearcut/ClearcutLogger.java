package com.google.android.gms.clearcut;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.clearcut.zze;
import com.google.android.gms.internal.clearcut.zzge.zzv.zzb;
import com.google.android.gms.internal.clearcut.zzj;
import com.google.android.gms.internal.clearcut.zzp;
import com.google.android.gms.phenotype.ExperimentTokens;
import java.util.ArrayList;
import javax.annotation.Nullable;

@KeepForSdk
public final class ClearcutLogger {
    @Deprecated
    public static final Api<NoOptions> API = new Api("ClearcutLogger.API", CLIENT_BUILDER, CLIENT_KEY);
    private static final AbstractClientBuilder<zzj, NoOptions> CLIENT_BUILDER = new zza();
    private static final ClientKey<zzj> CLIENT_KEY = new ClientKey();
    private static final ExperimentTokens[] zze = new ExperimentTokens[0];
    private static final String[] zzf = new String[0];
    private static final byte[][] zzg = new byte[0][];
    private final String packageName;
    private final Context zzh;
    private final int zzi;
    private String zzj;
    private int zzk;
    private String zzl;
    private String zzm;
    private final boolean zzn;
    private zzb zzo;
    private final zzb zzp;
    private final Clock zzq;
    private zzc zzr;
    private final zza zzs;

    @VisibleForTesting
    private ClearcutLogger(Context context, int i, String str, String str2, String str3, boolean z, zzb com_google_android_gms_clearcut_zzb, Clock clock, zzc com_google_android_gms_clearcut_ClearcutLogger_zzc, zza com_google_android_gms_clearcut_ClearcutLogger_zza) {
        this.zzk = -1;
        this.zzo = zzb.zzbhk;
        this.zzh = context;
        this.packageName = context.getPackageName();
        this.zzi = zza(context);
        this.zzk = -1;
        this.zzj = str;
        this.zzl = str2;
        this.zzm = null;
        this.zzn = z;
        this.zzp = com_google_android_gms_clearcut_zzb;
        this.zzq = clock;
        this.zzr = new zzc();
        this.zzo = zzb.zzbhk;
        this.zzs = com_google_android_gms_clearcut_ClearcutLogger_zza;
        if (z) {
            Preconditions.checkArgument(str2 == null, "can't be anonymous with an upload account");
        }
    }

    @KeepForSdk
    public ClearcutLogger(Context context, String str, @Nullable String str2) {
        this(context, -1, str, str2, null, false, zze.zzb(context), DefaultClock.getInstance(), null, new zzp(context));
    }

    @KeepForSdk
    public static ClearcutLogger anonymousLogger(Context context, String str) {
        return new ClearcutLogger(context, -1, str, null, null, true, zze.zzb(context), DefaultClock.getInstance(), null, new zzp(context));
    }

    private static int zza(Context context) {
        int i = 0;
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Throwable e) {
            Log.wtf("ClearcutLogger", "This can't happen.", e);
            return i;
        }
    }

    private static int[] zza(ArrayList<Integer> arrayList) {
        if (arrayList == null) {
            return null;
        }
        int[] iArr = new int[arrayList.size()];
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        int i2 = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            int intValue = ((Integer) obj).intValue();
            int i3 = i2 + 1;
            iArr[i2] = intValue;
            i2 = i3;
        }
        return iArr;
    }

    @KeepForSdk
    public final LogEventBuilder newEvent(@Nullable byte[] bArr) {
        return new LogEventBuilder(this, bArr, null);
    }
}
