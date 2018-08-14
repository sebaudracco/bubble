package org.altbeacon.beacon;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Messenger;
import java.util.Map.Entry;
import org.altbeacon.beacon.logging.LogManager;

class BeaconManager$BeaconServiceConnection implements ServiceConnection {
    final /* synthetic */ BeaconManager this$0;

    private BeaconManager$BeaconServiceConnection(BeaconManager beaconManager) {
        this.this$0 = beaconManager;
    }

    public void onServiceConnected(ComponentName className, IBinder service) {
        LogManager.m16371d("BeaconManager", "we have a connection to the service now", new Object[0]);
        if (BeaconManager.access$100(this.this$0) == null) {
            BeaconManager.access$102(this.this$0, Boolean.valueOf(false));
        }
        BeaconManager.access$202(this.this$0, new Messenger(service));
        this.this$0.applySettings();
        synchronized (BeaconManager.access$300(this.this$0)) {
            for (Entry<BeaconConsumer, BeaconManager$ConsumerInfo> entry : BeaconManager.access$300(this.this$0).entrySet()) {
                if (!((BeaconManager$ConsumerInfo) entry.getValue()).isConnected) {
                    ((BeaconConsumer) entry.getKey()).onBeaconServiceConnect();
                    ((BeaconManager$ConsumerInfo) entry.getValue()).isConnected = true;
                }
            }
        }
    }

    public void onServiceDisconnected(ComponentName className) {
        LogManager.m16373e("BeaconManager", "onServiceDisconnected", new Object[0]);
        BeaconManager.access$202(this.this$0, null);
    }
}
