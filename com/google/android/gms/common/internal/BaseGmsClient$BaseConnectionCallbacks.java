package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.support.annotation.Nullable;

public interface BaseGmsClient$BaseConnectionCallbacks {
    public static final int CAUSE_NETWORK_LOST = 2;
    public static final int CAUSE_SERVICE_DISCONNECTED = 1;

    void onConnected(@Nullable Bundle bundle);

    void onConnectionSuspended(int i);
}
