package org.telegram.tgnet;

import android.content.SharedPreferences;
import android.util.Log;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.TimerTask;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.config.ProxyManager;

class ConnectionsManager$7 extends TimerTask {
    ConnectionsManager$7() {
    }

    public void run() {
        if (!ConnectionsManager.getInstance().isAppPaused()) {
            Log.w("proxy", "timer task");
            SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0);
            try {
                new Socket().connect(new InetSocketAddress(preferences.getString("proxy_ip", ""), preferences.getInt("proxy_port", 1080)), 2000);
                if (preferences.getBoolean("proxy_enable_automatically", true)) {
                    ProxyManager.getInstance().setProxyEnabled(true);
                }
            } catch (IOException e) {
                Log.d("Proxy", "Proxy is not available");
            }
        }
    }
}
