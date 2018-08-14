package com.vungle.publisher.inject;

import android.content.Context;
import android.net.wifi.WifiManager;
import com.vungle.publisher.env.AndroidDevice.DeviceIdStrategy;
import com.vungle.publisher.env.C1610a;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
/* compiled from: vungle */
public class C1639t {
    @Singleton
    @Provides
    DeviceIdStrategy m4197a(C1610a c1610a) {
        return c1610a;
    }

    @Provides
    WifiManager m4196a(Context context) {
        return (WifiManager) context.getSystemService("wifi");
    }
}
