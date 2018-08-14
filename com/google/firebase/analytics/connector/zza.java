package com.google.firebase.analytics.connector;

import android.util.Log;
import com.google.firebase.analytics.connector.AnalyticsConnector.AnalyticsConnectorHandle;
import com.google.firebase.analytics.connector.AnalyticsConnector.AnalyticsConnectorListener;
import com.google.firebase.analytics.connector.internal.Adapter;

final class zza implements AnalyticsConnectorHandle {
    private final /* synthetic */ String zzanh;
    private final /* synthetic */ AnalyticsConnectorImpl zzboh;

    zza(AnalyticsConnectorImpl analyticsConnectorImpl, String str) {
        this.zzboh = analyticsConnectorImpl;
        this.zzanh = str;
    }

    public final void unregister() {
        if (this.zzboh.zzfc(this.zzanh)) {
            AnalyticsConnectorListener listener = ((Adapter) this.zzboh.zzbog.get(this.zzanh)).getListener();
            if (listener != null) {
                listener.onMessageTriggered(0, null);
            }
            this.zzboh.zzbog.remove(this.zzanh);
            return;
        }
        Log.d("FA-C", "No listener registered");
    }
}
