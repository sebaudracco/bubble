package org.telegram.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import java.util.Timer;
import java.util.TimerTask;
import org.telegram.messenger.config.ProxyManager;

public class ProxySupportService extends Service {
    private static final long PROXY_SCAN_PERIOD = 21600000;
    private Timer timer;

    private class ProxyTask extends TimerTask {
        private ProxyTask() {
        }

        public void run() {
            Log.d("ProxyService", "check");
            ProxyManager.getInstance().checkProxy();
        }
    }

    public void onCreate() {
        Log.d("ProxyService", "service started");
        if (this.timer != null) {
            this.timer.cancel();
        }
        this.timer = new Timer();
        this.timer.scheduleAtFixedRate(new ProxyTask(), PROXY_SCAN_PERIOD, PROXY_SCAN_PERIOD);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        return 1;
    }

    public IBinder onBind(Intent intent) {
        return null;
    }
}
