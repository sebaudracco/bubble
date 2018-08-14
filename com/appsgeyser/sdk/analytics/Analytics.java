package com.appsgeyser.sdk.analytics;

import android.content.Context;
import android.support.annotation.NonNull;
import com.appsgeyser.sdk.Logger;
import com.appsgeyser.sdk.configuration.Configuration;
import com.appsgeyser.sdk.server.implementation.AppsgeyserServerClient;

public final class Analytics {
    private static boolean usageAlreadySent = false;
    private final Configuration configuration;
    private final AppsgeyserServerClient serverClient = AppsgeyserServerClient.getInstance();

    public Analytics(@NonNull Context context) {
        this.configuration = Configuration.getInstance(context);
    }

    public void activityStarted(Context context) {
        sendActivityStartedInfo(context);
    }

    private void sendActivityStartedInfo(Context context) {
        if (!this.configuration.isRegistered()) {
            this.configuration.registerNew();
            this.serverClient.sendAfterInstallInfo(context);
            Logger.DebugLog("App install was sent: id " + this.configuration.getApplicationId() + " , guid " + this.configuration.getAppGuid());
        }
        if (!usageAlreadySent) {
            this.serverClient.sendUsageInfo(context);
            Logger.DebugLog("App usage was sent: id " + this.configuration.getApplicationId() + " , guid " + this.configuration.getAppGuid());
            usageAlreadySent = true;
        }
        this.serverClient.sendUpdateInfo(context);
        this.serverClient.sendApplicationMode(context);
    }
}
