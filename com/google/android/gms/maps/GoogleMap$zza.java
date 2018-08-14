package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzd;

final class GoogleMap$zza extends zzd {
    private final GoogleMap$CancelableCallback zzai;

    GoogleMap$zza(GoogleMap$CancelableCallback googleMap$CancelableCallback) {
        this.zzai = googleMap$CancelableCallback;
    }

    public final void onCancel() {
        this.zzai.onCancel();
    }

    public final void onFinish() {
        this.zzai.onFinish();
    }
}
