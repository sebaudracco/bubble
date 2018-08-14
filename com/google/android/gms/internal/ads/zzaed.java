package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.support.annotation.NonNull;
import com.appnext.base.p023b.C1042c;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks;
import com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener;
import com.google.android.gms.common.util.VisibleForTesting;

@zzadh
public final class zzaed extends zzadz implements BaseConnectionCallbacks, BaseOnConnectionFailedListener {
    private Context mContext;
    private final Object mLock = new Object();
    private zzaol<zzaef> zzccp;
    private final zzadx zzccq;
    @VisibleForTesting
    private zzaee zzcct;
    private zzang zzyf;

    public zzaed(Context context, zzang com_google_android_gms_internal_ads_zzang, zzaol<zzaef> com_google_android_gms_internal_ads_zzaol_com_google_android_gms_internal_ads_zzaef, zzadx com_google_android_gms_internal_ads_zzadx) {
        super(com_google_android_gms_internal_ads_zzaol_com_google_android_gms_internal_ads_zzaef, com_google_android_gms_internal_ads_zzadx);
        this.mContext = context;
        this.zzyf = com_google_android_gms_internal_ads_zzang;
        this.zzccp = com_google_android_gms_internal_ads_zzaol_com_google_android_gms_internal_ads_zzaef;
        this.zzccq = com_google_android_gms_internal_ads_zzadx;
        this.zzcct = new zzaee(context, ((Boolean) zzkb.zzik().zzd(zznk.zzavz)).booleanValue() ? zzbv.zzez().zzsa() : context.getMainLooper(), this, this);
        this.zzcct.checkAvailabilityAndConnect();
    }

    public final void onConnected(Bundle bundle) {
        zznt();
    }

    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        zzane.zzck("Cannot connect to remote service, fallback to local instance.");
        new zzaec(this.mContext, this.zzccp, this.zzccq).zznt();
        Bundle bundle = new Bundle();
        bundle.putString(C1042c.jL, "gms_connection_failed_fallback_to_local");
        zzbv.zzek().zzb(this.mContext, this.zzyf.zzcw, "gmob-apps", bundle, true);
    }

    public final void onConnectionSuspended(int i) {
        zzane.zzck("Disconnected from remote ad request service.");
    }

    public final void zznz() {
        synchronized (this.mLock) {
            if (this.zzcct.isConnected() || this.zzcct.isConnecting()) {
                this.zzcct.disconnect();
            }
            Binder.flushPendingCommands();
        }
    }

    public final zzaen zzoa() {
        zzaen zzob;
        synchronized (this.mLock) {
            try {
                zzob = this.zzcct.zzob();
            } catch (IllegalStateException e) {
                zzob = null;
                return zzob;
            } catch (DeadObjectException e2) {
                zzob = null;
                return zzob;
            }
        }
        return zzob;
    }
}
