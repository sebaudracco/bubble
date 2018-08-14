package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.net.Uri;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.wearable.CapabilityApi;
import com.google.android.gms.wearable.CapabilityApi.AddLocalCapabilityResult;
import com.google.android.gms.wearable.CapabilityApi.CapabilityListener;
import com.google.android.gms.wearable.CapabilityApi.GetAllCapabilitiesResult;
import com.google.android.gms.wearable.CapabilityApi.GetCapabilityResult;
import com.google.android.gms.wearable.CapabilityApi.RemoveLocalCapabilityResult;

public final class zzo implements CapabilityApi {
    private static PendingResult<Status> zza(GoogleApiClient googleApiClient, CapabilityListener capabilityListener, IntentFilter[] intentFilterArr) {
        return zzb.zza(googleApiClient, new zzt(intentFilterArr), capabilityListener);
    }

    public final PendingResult<Status> addCapabilityListener(GoogleApiClient googleApiClient, CapabilityListener capabilityListener, String str) {
        String str2;
        Asserts.checkNotNull(str, "capability must not be null");
        CapabilityListener com_google_android_gms_wearable_internal_zzv = new zzv(capabilityListener, str);
        IntentFilter zzc = zzgj.zzc("com.google.android.gms.wearable.CAPABILITY_CHANGED");
        if (str.startsWith(BridgeUtil.SPLIT_MARK)) {
            str2 = str;
        } else {
            String str3 = BridgeUtil.SPLIT_MARK;
            str2 = String.valueOf(str);
            str2 = str2.length() != 0 ? str3.concat(str2) : new String(str3);
        }
        zzc.addDataPath(str2, 0);
        return zza(googleApiClient, com_google_android_gms_wearable_internal_zzv, new IntentFilter[]{zzc});
    }

    public final PendingResult<Status> addListener(GoogleApiClient googleApiClient, CapabilityListener capabilityListener, Uri uri, int i) {
        Asserts.checkNotNull(uri, "uri must not be null");
        boolean z = i == 0 || i == 1;
        Preconditions.checkArgument(z, "invalid filter type");
        return zza(googleApiClient, capabilityListener, new IntentFilter[]{zzgj.zza("com.google.android.gms.wearable.CAPABILITY_CHANGED", uri, i)});
    }

    public final PendingResult<AddLocalCapabilityResult> addLocalCapability(GoogleApiClient googleApiClient, String str) {
        return googleApiClient.enqueue(new zzr(this, googleApiClient, str));
    }

    public final PendingResult<GetAllCapabilitiesResult> getAllCapabilities(GoogleApiClient googleApiClient, int i) {
        boolean z = true;
        if (!(i == 0 || i == 1)) {
            z = false;
        }
        Preconditions.checkArgument(z);
        return googleApiClient.enqueue(new zzq(this, googleApiClient, i));
    }

    public final PendingResult<GetCapabilityResult> getCapability(GoogleApiClient googleApiClient, String str, int i) {
        boolean z = true;
        if (!(i == 0 || i == 1)) {
            z = false;
        }
        Preconditions.checkArgument(z);
        return googleApiClient.enqueue(new zzp(this, googleApiClient, str, i));
    }

    public final PendingResult<Status> removeCapabilityListener(GoogleApiClient googleApiClient, CapabilityListener capabilityListener, String str) {
        return googleApiClient.enqueue(new zzz(googleApiClient, new zzv(capabilityListener, str), null));
    }

    public final PendingResult<Status> removeListener(GoogleApiClient googleApiClient, CapabilityListener capabilityListener) {
        return googleApiClient.enqueue(new zzz(googleApiClient, capabilityListener, null));
    }

    public final PendingResult<RemoveLocalCapabilityResult> removeLocalCapability(GoogleApiClient googleApiClient, String str) {
        return googleApiClient.enqueue(new zzs(this, googleApiClient, str));
    }
}
