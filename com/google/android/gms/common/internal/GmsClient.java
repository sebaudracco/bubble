package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Handler;
import android.os.IInterface;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.GmsClientEventManager.GmsClientEventState;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Set;

public abstract class GmsClient<T extends IInterface> extends BaseGmsClient<T> implements Client, GmsClientEventState {
    private final Set<Scope> mScopes;
    private final ClientSettings zzgf;
    private final Account zzs;

    @VisibleForTesting
    protected GmsClient(Context context, Handler handler, int i, ClientSettings clientSettings) {
        this(context, handler, GmsClientSupervisor.getInstance(context), GoogleApiAvailability.getInstance(), i, clientSettings, null, null);
    }

    @VisibleForTesting
    protected GmsClient(Context context, Handler handler, GmsClientSupervisor gmsClientSupervisor, GoogleApiAvailability googleApiAvailability, int i, ClientSettings clientSettings, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, handler, gmsClientSupervisor, googleApiAvailability, i, zza(connectionCallbacks), zza(onConnectionFailedListener));
        this.zzgf = (ClientSettings) Preconditions.checkNotNull(clientSettings);
        this.zzs = clientSettings.getAccount();
        this.mScopes = zza(clientSettings.getAllRequestedScopes());
    }

    protected GmsClient(Context context, Looper looper, int i, ClientSettings clientSettings) {
        this(context, looper, GmsClientSupervisor.getInstance(context), GoogleApiAvailability.getInstance(), i, clientSettings, null, null);
    }

    protected GmsClient(Context context, Looper looper, int i, ClientSettings clientSettings, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        this(context, looper, GmsClientSupervisor.getInstance(context), GoogleApiAvailability.getInstance(), i, clientSettings, (ConnectionCallbacks) Preconditions.checkNotNull(connectionCallbacks), (OnConnectionFailedListener) Preconditions.checkNotNull(onConnectionFailedListener));
    }

    @VisibleForTesting
    protected GmsClient(Context context, Looper looper, GmsClientSupervisor gmsClientSupervisor, GoogleApiAvailability googleApiAvailability, int i, ClientSettings clientSettings, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, gmsClientSupervisor, googleApiAvailability, i, zza(connectionCallbacks), zza(onConnectionFailedListener), clientSettings.getRealClientClassName());
        this.zzgf = clientSettings;
        this.zzs = clientSettings.getAccount();
        this.mScopes = zza(clientSettings.getAllRequestedScopes());
    }

    @Nullable
    private static BaseGmsClient$BaseConnectionCallbacks zza(ConnectionCallbacks connectionCallbacks) {
        return connectionCallbacks == null ? null : new zzf(connectionCallbacks);
    }

    @Nullable
    private static BaseGmsClient$BaseOnConnectionFailedListener zza(OnConnectionFailedListener onConnectionFailedListener) {
        return onConnectionFailedListener == null ? null : new zzg(onConnectionFailedListener);
    }

    private final Set<Scope> zza(@NonNull Set<Scope> set) {
        Set<Scope> validateScopes = validateScopes(set);
        for (Scope contains : validateScopes) {
            if (!set.contains(contains)) {
                throw new IllegalStateException("Expanding scopes is not permitted, use implied scopes instead");
            }
        }
        return validateScopes;
    }

    public final Account getAccount() {
        return this.zzs;
    }

    protected final ClientSettings getClientSettings() {
        return this.zzgf;
    }

    public int getMinApkVersion() {
        return super.getMinApkVersion();
    }

    public Feature[] getRequiredFeatures() {
        return new Feature[0];
    }

    protected final Set<Scope> getScopes() {
        return this.mScopes;
    }

    @NonNull
    protected Set<Scope> validateScopes(@NonNull Set<Scope> set) {
        return set;
    }
}
