package com.appsgeyser.sdk.server.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import com.appsgeyser.sdk.InternalEntryPoint;
import java.util.ArrayList;

public class NetworkAvailableReceiver extends BroadcastReceiver {
    private final ArrayList<OnNetworkStateChangedListener> listeners = new ArrayList();

    public static synchronized NetworkAvailableReceiver createAndRegisterNetworkReceiver(@NonNull Context context) {
        NetworkAvailableReceiver networkAvailableReceiver;
        synchronized (NetworkAvailableReceiver.class) {
            networkAvailableReceiver = new NetworkAvailableReceiver();
            context.registerReceiver(networkAvailableReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
        return networkAvailableReceiver;
    }

    public void onReceive(Context context, Intent intent) {
        notifyAllListeners(context);
    }

    private void notifyAllListeners(@NonNull Context context) {
        int listenersSize;
        int i;
        if (NetworkManager.isOnline(context)) {
            listenersSize = this.listeners.size();
            for (i = 0; i < listenersSize; i++) {
                ((OnNetworkStateChangedListener) this.listeners.get(i)).networkIsUp();
            }
            return;
        }
        listenersSize = this.listeners.size();
        for (i = 0; i < listenersSize; i++) {
            ((OnNetworkStateChangedListener) this.listeners.get(i)).networkIsDown();
        }
    }

    public void addListener(@NonNull OnNetworkStateChangedListener listener) {
        if (!this.listeners.contains(listener)) {
            this.listeners.add(listener);
        }
    }

    public void removeListener(@NonNull OnNetworkStateChangedListener listener) {
        if (this.listeners.contains(listener)) {
            this.listeners.remove(listener);
        }
    }

    public OnNetworkStateChangedListener createNetworkAvailableListener(final Context context) {
        return new OnNetworkStateChangedListener(getClass().getSimpleName()) {
            public void networkIsUp() {
                InternalEntryPoint.getInstance().retryParsers(context);
            }

            public void networkIsDown() {
            }
        };
    }
}
