package com.google.android.gms.measurement;

import android.os.Bundle;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public interface AppMeasurement$EventInterceptor {
    @WorkerThread
    @KeepForSdk
    void interceptEvent(String str, String str2, Bundle bundle, long j);
}
