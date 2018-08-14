package com.google.android.gms.wearable;

import android.os.Looper;
import com.google.android.gms.wearable.Wearable.WearableOptions;

public class Wearable$WearableOptions$Builder {
    private Looper zzac;

    public WearableOptions build() {
        return new WearableOptions(this, null);
    }

    public Wearable$WearableOptions$Builder setLooper(Looper looper) {
        this.zzac = looper;
        return this;
    }
}
