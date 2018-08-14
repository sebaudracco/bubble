package com.google.android.gms.internal.plus;

import android.annotation.SuppressLint;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.People.LoadPeopleResult;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import java.util.Collection;

public final class zzj implements People {
    public final Person getCurrentPerson(GoogleApiClient googleApiClient) {
        return Plus.zza(googleApiClient, true).zzb();
    }

    @SuppressLint({"MissingRemoteException"})
    @VisibleForTesting
    public final PendingResult<LoadPeopleResult> load(GoogleApiClient googleApiClient, Collection<String> collection) {
        return googleApiClient.enqueue(new zzn(this, googleApiClient, collection));
    }

    @SuppressLint({"MissingRemoteException"})
    @VisibleForTesting
    public final PendingResult<LoadPeopleResult> load(GoogleApiClient googleApiClient, String... strArr) {
        return googleApiClient.enqueue(new zzo(this, googleApiClient, strArr));
    }

    @SuppressLint({"MissingRemoteException"})
    @VisibleForTesting
    public final PendingResult<LoadPeopleResult> loadConnected(GoogleApiClient googleApiClient) {
        return googleApiClient.enqueue(new zzm(this, googleApiClient));
    }

    @SuppressLint({"MissingRemoteException"})
    @VisibleForTesting
    public final PendingResult<LoadPeopleResult> loadVisible(GoogleApiClient googleApiClient, int i, String str) {
        return googleApiClient.enqueue(new zzk(this, googleApiClient, i, str));
    }

    @SuppressLint({"MissingRemoteException"})
    @VisibleForTesting
    public final PendingResult<LoadPeopleResult> loadVisible(GoogleApiClient googleApiClient, String str) {
        return googleApiClient.enqueue(new zzl(this, googleApiClient, str));
    }
}
