package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

final class zzaka extends zzajx {
    private Context mContext;

    zzaka(Context context) {
        this.mContext = context;
    }

    public final void onStop() {
    }

    public final void zzdn() {
        boolean isAdIdFakeForDebugLogging;
        Throwable e;
        try {
            isAdIdFakeForDebugLogging = AdvertisingIdClient.getIsAdIdFakeForDebugLogging(this.mContext);
        } catch (IOException e2) {
            e = e2;
            zzakb.zzb("Fail to get isAdIdFakeForDebugLogging", e);
            isAdIdFakeForDebugLogging = false;
            zzamy.zzaf(isAdIdFakeForDebugLogging);
            zzakb.zzdk("Update ad debug logging enablement as " + isAdIdFakeForDebugLogging);
        } catch (IllegalStateException e3) {
            e = e3;
            zzakb.zzb("Fail to get isAdIdFakeForDebugLogging", e);
            isAdIdFakeForDebugLogging = false;
            zzamy.zzaf(isAdIdFakeForDebugLogging);
            zzakb.zzdk("Update ad debug logging enablement as " + isAdIdFakeForDebugLogging);
        } catch (GooglePlayServicesNotAvailableException e4) {
            e = e4;
            zzakb.zzb("Fail to get isAdIdFakeForDebugLogging", e);
            isAdIdFakeForDebugLogging = false;
            zzamy.zzaf(isAdIdFakeForDebugLogging);
            zzakb.zzdk("Update ad debug logging enablement as " + isAdIdFakeForDebugLogging);
        } catch (GooglePlayServicesRepairableException e5) {
            e = e5;
            zzakb.zzb("Fail to get isAdIdFakeForDebugLogging", e);
            isAdIdFakeForDebugLogging = false;
            zzamy.zzaf(isAdIdFakeForDebugLogging);
            zzakb.zzdk("Update ad debug logging enablement as " + isAdIdFakeForDebugLogging);
        }
        zzamy.zzaf(isAdIdFakeForDebugLogging);
        zzakb.zzdk("Update ad debug logging enablement as " + isAdIdFakeForDebugLogging);
    }
}
