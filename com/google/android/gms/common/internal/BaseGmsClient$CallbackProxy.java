package com.google.android.gms.common.internal;

import android.util.Log;

protected abstract class BaseGmsClient$CallbackProxy<TListener> {
    private TListener zzli;
    private final /* synthetic */ BaseGmsClient zzru;
    private boolean zzrv = false;

    public BaseGmsClient$CallbackProxy(BaseGmsClient baseGmsClient, TListener tListener) {
        this.zzru = baseGmsClient;
        this.zzli = tListener;
    }

    public void deliverCallback() {
        synchronized (this) {
            Object obj = this.zzli;
            if (this.zzrv) {
                String valueOf = String.valueOf(this);
                Log.w("GmsClient", new StringBuilder(String.valueOf(valueOf).length() + 47).append("Callback proxy ").append(valueOf).append(" being reused. This is not safe.").toString());
            }
        }
        if (obj != null) {
            try {
                deliverCallback(obj);
            } catch (RuntimeException e) {
                onDeliverCallbackFailed();
                throw e;
            }
        }
        onDeliverCallbackFailed();
        synchronized (this) {
            this.zzrv = true;
        }
        unregister();
    }

    protected abstract void deliverCallback(TListener tListener);

    protected abstract void onDeliverCallbackFailed();

    public void removeListener() {
        synchronized (this) {
            this.zzli = null;
        }
    }

    public void unregister() {
        removeListener();
        synchronized (BaseGmsClient.zzf(this.zzru)) {
            BaseGmsClient.zzf(this.zzru).remove(this);
        }
    }
}
