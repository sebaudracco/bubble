package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import android.view.View.OnClickListener;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.ads.internal.zzbv;
import java.lang.ref.WeakReference;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONObject;

@zzadh
@ParametersAreNonnullByDefault
public final class zzok implements OnClickListener {
    private final zzacm zzaad;
    @Nullable
    private zzro zzbhm;
    @Nullable
    private zzv zzbhn;
    @Nullable
    @VisibleForTesting
    String zzbho;
    @Nullable
    @VisibleForTesting
    Long zzbhp;
    @Nullable
    @VisibleForTesting
    WeakReference<View> zzbhq;

    public zzok(zzacm com_google_android_gms_internal_ads_zzacm) {
        this.zzaad = com_google_android_gms_internal_ads_zzacm;
    }

    private final void zzjx() {
        this.zzbho = null;
        this.zzbhp = null;
        if (this.zzbhq != null) {
            View view = (View) this.zzbhq.get();
            this.zzbhq = null;
            if (view != null) {
                view.setClickable(false);
                view.setOnClickListener(null);
            }
        }
    }

    public final void cancelUnconfirmedClick() {
        if (this.zzbhm != null && this.zzbhp != null) {
            zzjx();
            try {
                this.zzbhm.onUnconfirmedClickCancelled();
            } catch (Throwable e) {
                zzane.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    public final void onClick(View view) {
        if (this.zzbhq != null && this.zzbhq.get() == view) {
            if (!(this.zzbho == null || this.zzbhp == null)) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("id", this.zzbho);
                    jSONObject.put("time_interval", zzbv.zzer().currentTimeMillis() - this.zzbhp.longValue());
                    jSONObject.put("messageType", "onePointFiveClick");
                    this.zzaad.zza("sendMessageToNativeJs", jSONObject);
                } catch (Throwable e) {
                    zzane.zzb("Unable to dispatch sendMessageToNativeJs event", e);
                }
            }
            zzjx();
        }
    }

    public final void zza(zzro com_google_android_gms_internal_ads_zzro) {
        this.zzbhm = com_google_android_gms_internal_ads_zzro;
        if (this.zzbhn != null) {
            this.zzaad.zzb("/unconfirmedClick", this.zzbhn);
        }
        this.zzbhn = new zzol(this);
        this.zzaad.zza("/unconfirmedClick", this.zzbhn);
    }

    @Nullable
    public final zzro zzjw() {
        return this.zzbhm;
    }
}
