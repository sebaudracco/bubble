package com.google.android.gms.common.internal.service;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api$ApiOptions$NoOptions;
import com.google.android.gms.common.api.Api$ClientKey;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;

public final class Common {
    public static final Api<Api$ApiOptions$NoOptions> API = new Api("Common.API", CLIENT_BUILDER, CLIENT_KEY);
    private static final AbstractClientBuilder<CommonClient, Api$ApiOptions$NoOptions> CLIENT_BUILDER = new zza();
    public static final Api$ClientKey<CommonClient> CLIENT_KEY = new Api$ClientKey();
    public static final CommonApi CommonApi = new CommonApiImpl();
}
