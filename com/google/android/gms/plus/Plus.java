package com.google.android.gms.plus;

import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.plus.zze;
import com.google.android.gms.internal.plus.zzi;
import com.google.android.gms.internal.plus.zzj;
import com.google.android.gms.plus.internal.zzh;
import java.util.HashSet;
import java.util.Set;

@Deprecated
public final class Plus {
    @Deprecated
    public static final Api<PlusOptions> API = new Api("Plus.API", CLIENT_BUILDER, CLIENT_KEY);
    @Deprecated
    public static final Account AccountApi = new zze();
    private static final AbstractClientBuilder<zzh, PlusOptions> CLIENT_BUILDER = new zzc();
    public static final ClientKey<zzh> CLIENT_KEY = new ClientKey();
    @Deprecated
    public static final People PeopleApi = new zzj();
    @Deprecated
    public static final Scope SCOPE_PLUS_LOGIN = new Scope(Scopes.PLUS_LOGIN);
    @Deprecated
    public static final Scope SCOPE_PLUS_PROFILE = new Scope(Scopes.PLUS_ME);
    @Deprecated
    private static final zzb zze = new zzi();
    private static final zza zzf = new com.google.android.gms.internal.plus.zzh();

    @Deprecated
    public static final class PlusOptions implements Optional {
        private final String zzg;
        final Set<String> zzh;

        @Deprecated
        public static final class Builder {
            String zzg;
            final Set<String> zzh = new HashSet();

            @Deprecated
            @VisibleForTesting
            public final Builder addActivityTypes(String... strArr) {
                Preconditions.checkNotNull(strArr, "activityTypes may not be null.");
                for (Object add : strArr) {
                    this.zzh.add(add);
                }
                return this;
            }

            @Deprecated
            @VisibleForTesting
            public final PlusOptions build() {
                return new PlusOptions();
            }

            @Deprecated
            public final Builder setServerClientId(String str) {
                this.zzg = str;
                return this;
            }
        }

        private PlusOptions() {
            this.zzg = null;
            this.zzh = new HashSet();
        }

        private PlusOptions(Builder builder) {
            this.zzg = builder.zzg;
            this.zzh = builder.zzh;
        }

        @Deprecated
        @VisibleForTesting
        public static Builder builder() {
            return new Builder();
        }
    }

    private Plus() {
    }

    public static zzh zza(GoogleApiClient googleApiClient, boolean z) {
        Preconditions.checkArgument(googleApiClient != null, "GoogleApiClient parameter is required.");
        Preconditions.checkState(googleApiClient.isConnected(), "GoogleApiClient must be connected.");
        Preconditions.checkState(googleApiClient.hasApi(API), "GoogleApiClient is not configured to use the Plus.API Api. Pass this into GoogleApiClient.Builder#addApi() to use this feature.");
        boolean hasConnectedApi = googleApiClient.hasConnectedApi(API);
        if (!z || hasConnectedApi) {
            return hasConnectedApi ? (zzh) googleApiClient.getClient(CLIENT_KEY) : null;
        } else {
            throw new IllegalStateException("GoogleApiClient has an optional Plus.API and is not connected to Plus. Use GoogleApiClient.hasConnectedApi(Plus.API) to guard this call.");
        }
    }
}
