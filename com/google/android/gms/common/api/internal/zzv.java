package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.GoogleApiManager.zza;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.signin.SignInClient;
import com.google.android.gms.signin.SignInOptions;

public final class zzv<O extends ApiOptions> extends GoogleApi<O> {
    private final AbstractClientBuilder<? extends SignInClient, SignInOptions> zzdh;
    private final Client zzgd;
    private final zzp zzge;
    private final ClientSettings zzgf;

    public zzv(@NonNull Context context, Api<O> api, Looper looper, @NonNull Client client, @NonNull zzp com_google_android_gms_common_api_internal_zzp, ClientSettings clientSettings, AbstractClientBuilder<? extends SignInClient, SignInOptions> abstractClientBuilder) {
        super(context, api, looper);
        this.zzgd = client;
        this.zzge = com_google_android_gms_common_api_internal_zzp;
        this.zzgf = clientSettings;
        this.zzdh = abstractClientBuilder;
        this.zzcq.zza(this);
    }

    public final Client zza(Looper looper, zza<O> com_google_android_gms_common_api_internal_GoogleApiManager_zza_O) {
        this.zzge.zza(com_google_android_gms_common_api_internal_GoogleApiManager_zza_O);
        return this.zzgd;
    }

    public final zzby zza(Context context, Handler handler) {
        return new zzby(context, handler, this.zzgf, this.zzdh);
    }

    public final Client zzae() {
        return this.zzgd;
    }
}
