package com.google.android.gms.wearable;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.util.Preconditions;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.common.api.GoogleApi.Settings;
import com.google.android.gms.common.api.GoogleApi.Settings.Builder;
import com.google.android.gms.wearable.internal.zzaa;
import com.google.android.gms.wearable.internal.zzaj;
import com.google.android.gms.wearable.internal.zzao;
import com.google.android.gms.wearable.internal.zzbv;
import com.google.android.gms.wearable.internal.zzbw;
import com.google.android.gms.wearable.internal.zzcj;
import com.google.android.gms.wearable.internal.zzeu;
import com.google.android.gms.wearable.internal.zzez;
import com.google.android.gms.wearable.internal.zzfg;
import com.google.android.gms.wearable.internal.zzfl;
import com.google.android.gms.wearable.internal.zzgi;
import com.google.android.gms.wearable.internal.zzh;
import com.google.android.gms.wearable.internal.zzhg;
import com.google.android.gms.wearable.internal.zzhq;
import com.google.android.gms.wearable.internal.zzk;
import com.google.android.gms.wearable.internal.zzo;

public class Wearable {
    @Deprecated
    public static final Api<WearableOptions> API = new Api("Wearable.API", CLIENT_BUILDER, CLIENT_KEY);
    private static final AbstractClientBuilder<zzhg, WearableOptions> CLIENT_BUILDER = new zzj();
    private static final ClientKey<zzhg> CLIENT_KEY = new ClientKey();
    @Deprecated
    public static final CapabilityApi CapabilityApi = new zzo();
    @Deprecated
    public static final ChannelApi ChannelApi = new zzaj();
    @Deprecated
    public static final DataApi DataApi = new zzbw();
    @Deprecated
    public static final MessageApi MessageApi = new zzeu();
    @Deprecated
    public static final NodeApi NodeApi = new zzfg();
    @Deprecated
    private static final zzi zzaa = new zzgi();
    @Deprecated
    private static final zzu zzab = new zzhq();
    @Deprecated
    private static final zzc zzx = new zzk();
    @Deprecated
    private static final zza zzy = new zzh();
    @Deprecated
    private static final zzf zzz = new zzbv();

    public static final class WearableOptions implements Optional {
        private final Looper zzac;

        private WearableOptions(Builder builder) {
            this.zzac = Builder.zza(builder);
        }

        private final Settings zza() {
            return this.zzac != null ? new Builder().setLooper(this.zzac).build() : Settings.DEFAULT_SETTINGS;
        }
    }

    private Wearable() {
    }

    public static CapabilityClient getCapabilityClient(@NonNull Activity activity) {
        return new zzaa(activity, Settings.DEFAULT_SETTINGS);
    }

    public static CapabilityClient getCapabilityClient(@NonNull Activity activity, @NonNull WearableOptions wearableOptions) {
        Preconditions.checkNotNull(wearableOptions, "options must not be null");
        return new zzaa(activity, wearableOptions.zza());
    }

    public static CapabilityClient getCapabilityClient(@NonNull Context context) {
        return new zzaa(context, Settings.DEFAULT_SETTINGS);
    }

    public static CapabilityClient getCapabilityClient(@NonNull Context context, @NonNull WearableOptions wearableOptions) {
        Preconditions.checkNotNull(wearableOptions, "options must not be null");
        return new zzaa(context, wearableOptions.zza());
    }

    public static ChannelClient getChannelClient(@NonNull Activity activity) {
        return new zzao(activity, Settings.DEFAULT_SETTINGS);
    }

    public static ChannelClient getChannelClient(@NonNull Activity activity, @NonNull WearableOptions wearableOptions) {
        Preconditions.checkNotNull(wearableOptions, "options must not be null");
        return new zzao(activity, wearableOptions.zza());
    }

    public static ChannelClient getChannelClient(@NonNull Context context) {
        return new zzao(context, Settings.DEFAULT_SETTINGS);
    }

    public static ChannelClient getChannelClient(@NonNull Context context, @NonNull WearableOptions wearableOptions) {
        Preconditions.checkNotNull(wearableOptions, "options must not be null");
        return new zzao(context, wearableOptions.zza());
    }

    public static DataClient getDataClient(@NonNull Activity activity) {
        return new zzcj(activity, Settings.DEFAULT_SETTINGS);
    }

    public static DataClient getDataClient(@NonNull Activity activity, @NonNull WearableOptions wearableOptions) {
        Preconditions.checkNotNull(wearableOptions, "options must not be null");
        return new zzcj(activity, wearableOptions.zza());
    }

    public static DataClient getDataClient(@NonNull Context context) {
        return new zzcj(context, Settings.DEFAULT_SETTINGS);
    }

    public static DataClient getDataClient(@NonNull Context context, @NonNull WearableOptions wearableOptions) {
        Preconditions.checkNotNull(wearableOptions, "options must not be null");
        return new zzcj(context, wearableOptions.zza());
    }

    public static MessageClient getMessageClient(@NonNull Activity activity) {
        return new zzez(activity, Settings.DEFAULT_SETTINGS);
    }

    public static MessageClient getMessageClient(@NonNull Activity activity, @NonNull WearableOptions wearableOptions) {
        Preconditions.checkNotNull(wearableOptions, "options must not be null");
        return new zzez(activity, wearableOptions.zza());
    }

    public static MessageClient getMessageClient(@NonNull Context context) {
        return new zzez(context, Settings.DEFAULT_SETTINGS);
    }

    public static MessageClient getMessageClient(@NonNull Context context, @NonNull WearableOptions wearableOptions) {
        Preconditions.checkNotNull(wearableOptions, "options must not be null");
        return new zzez(context, wearableOptions.zza());
    }

    public static NodeClient getNodeClient(@NonNull Activity activity) {
        return new zzfl(activity, Settings.DEFAULT_SETTINGS);
    }

    public static NodeClient getNodeClient(@NonNull Activity activity, @NonNull WearableOptions wearableOptions) {
        Preconditions.checkNotNull(wearableOptions, "options must not be null");
        return new zzfl(activity, wearableOptions.zza());
    }

    public static NodeClient getNodeClient(@NonNull Context context) {
        return new zzfl(context, Settings.DEFAULT_SETTINGS);
    }

    public static NodeClient getNodeClient(@NonNull Context context, @NonNull WearableOptions wearableOptions) {
        Preconditions.checkNotNull(wearableOptions, "options must not be null");
        return new zzfl(context, wearableOptions.zza());
    }
}
