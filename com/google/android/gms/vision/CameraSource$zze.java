package com.google.android.gms.vision;

import android.hardware.Camera;
import com.google.android.gms.common.images.Size;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.Nullable;

@VisibleForTesting
class CameraSource$zze {
    private Size zzaa;
    private Size zzab;

    public CameraSource$zze(Camera.Size size, @Nullable Camera.Size size2) {
        this.zzaa = new Size(size.width, size.height);
        if (size2 != null) {
            this.zzab = new Size(size2.width, size2.height);
        }
    }

    public final Size zzb() {
        return this.zzaa;
    }

    @Nullable
    public final Size zzc() {
        return this.zzab;
    }
}
