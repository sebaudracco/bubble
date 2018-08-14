package org.telegram.messenger.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.config.Config.ProxySettings;
import org.telegram.tgnet.ConnectionsManager;

public class ProxyManager {
    private static volatile ProxyManager Instance = null;

    public static ProxyManager getInstance() {
        ProxyManager localInstance = Instance;
        if (localInstance == null) {
            synchronized (ProxyManager.class) {
                try {
                    localInstance = Instance;
                    if (localInstance == null) {
                        ProxyManager localInstance2 = new ProxyManager();
                        try {
                            Instance = localInstance2;
                            localInstance = localInstance2;
                        } catch (Throwable th) {
                            Throwable th2 = th;
                            localInstance = localInstance2;
                            throw th2;
                        }
                    }
                } catch (Throwable th3) {
                    th2 = th3;
                    throw th2;
                }
            }
        }
        return localInstance;
    }

    public void checkProxy() {
        Log.d("ProxyService", "check proxy");
        if (ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0).getBoolean("proxy_enable_automatically", true) && ConnectionsManager.isNetworkOnline()) {
            String[] strArr = ProxySettings.TELEGRAM_HOSTS;
            int length = strArr.length;
            int i = 0;
            while (i < length) {
                String host = strArr[i];
                if (isServerReachable(ApplicationLoader.applicationContext, host)) {
                    Log.d("ProxyService", "available host: " + host);
                    i++;
                } else {
                    Log.d("ProxyService", "Unavailable host: " + host);
                    setProxyEnabled(true);
                    return;
                }
            }
            setProxyEnabled(false);
        }
    }

    private boolean isServerReachable(Context context, String url) {
        NetworkInfo netInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (netInfo == null || !netInfo.isConnected()) {
            return false;
        }
        try {
            HttpURLConnection urlConn = (HttpURLConnection) new URL(url).openConnection();
            urlConn.setConnectTimeout(1000);
            urlConn.connect();
            return true;
        } catch (MalformedURLException e1) {
            Log.d("ProxyService", "exception host: " + url + e1.getMessage());
            return false;
        } catch (IOException e) {
            Log.d("ProxyService", "exception host: " + url + e.getMessage());
            return false;
        }
    }

    public void setProxyEnabled(boolean enabled) {
        Log.d("ProxyService", "set proxy enabled: " + enabled);
        SharedPreferences sharedPreferences = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0);
        Editor editor = sharedPreferences.edit();
        editor.putBoolean("proxy_enabled", enabled);
        editor.putBoolean("proxy_enabled_calls", enabled);
        editor.commit();
        if (enabled) {
            ConnectionsManager.native_setProxySettings(sharedPreferences.getString("proxy_ip", ""), sharedPreferences.getInt("proxy_port", 0), sharedPreferences.getString("proxy_user", ""), sharedPreferences.getString("proxy_pass", ""));
        } else {
            ConnectionsManager.native_setProxySettings("", 0, "", "");
        }
    }
}
