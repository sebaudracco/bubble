package com.stepleaderdigital.reveal;

import android.annotation.TargetApi;
import android.app.Service;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import com.stepleaderdigital.reveal.BeaconManager.BeaconLeScanCallback;
import com.stepleaderdigital.reveal.Reveal.RevealLogger;
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

public class BeaconService extends Service {
    public static final int MSG_SET_SCAN_PERIODS = 6;
    public static final int MSG_START_MONITORING = 2;
    public static final int MSG_STOP_MONITORING = 3;
    private boolean backgroundFlag = false;
    private BeaconManager beaconManager;
    BluetoothCrashResolver bluetoothCrashResolver;
    protected final CycledLeScanCallback cycledLeScanCallback = new C39861();
    private CycledLeScanner cycledScanner;
    private boolean disableAndroidL = false;
    private ExecutorService executor;
    private final Handler handler = new Handler();
    final Messenger messenger = new Messenger(new IncomingHandler(this));

    class C39861 implements CycledLeScanCallback {
        C39861() {
        }

        @TargetApi(11)
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
            try {
                new ScanProcessor(BeaconService.this.beaconManager.getBeaconCallback()).executeOnExecutor(BeaconService.this.executor, new ScanData[]{new ScanData(device, rssi, scanRecord)});
            } catch (RejectedExecutionException e) {
                RevealLogger.m12441w("Ignoring scan result because we cannot keep up.");
            }
        }

        public void onCycleEnd() {
            RevealLogger.m12430d("Scan ended");
        }
    }

    public class BeaconBinder extends Binder {
        public BeaconService getService() {
            RevealLogger.m12430d("getService of BeaconBinder called");
            return BeaconService.this;
        }
    }

    static class IncomingHandler extends Handler {
        private final WeakReference<BeaconService> mService;

        IncomingHandler(BeaconService service) {
            this.mService = new WeakReference(service);
        }

        public void handleMessage(Message msg) {
            BeaconService service = (BeaconService) this.mService.get();
            StartRMData startRMData = msg.obj;
            if (service != null) {
                RevealLogger.m12440v("Handle MSG_... " + msg.what + " from bindee");
                switch (msg.what) {
                    case 2:
                        RevealLogger.m12437i("MSG_START_MONITORING called");
                        service.start();
                        service.setScanPeriods(startRMData.getScanPeriod(), startRMData.getBetweenScanPeriod(), startRMData.getBackgroundFlag());
                        return;
                    case 3:
                        RevealLogger.m12437i("MSG_STOP_MONITORING called");
                        service.stop();
                        return;
                    case 6:
                        RevealLogger.m12437i("MSG_SET_SCAN_PERIODS called");
                        service.setScanPeriods(startRMData.getScanPeriod(), startRMData.getBetweenScanPeriod(), startRMData.getBackgroundFlag());
                        return;
                    default:
                        super.handleMessage(msg);
                        return;
                }
            }
        }
    }

    private class ScanData {
        BluetoothDevice device;
        int rssi;
        byte[] scanRecord;

        public ScanData(BluetoothDevice device, int rssi, byte[] scanRecord) {
            this.device = device;
            this.rssi = rssi;
            this.scanRecord = scanRecord;
        }
    }

    private class ScanProcessor extends AsyncTask<ScanData, Void, Void> {
        private BeaconLeScanCallback beaconLeScanCallback;

        public ScanProcessor(BeaconLeScanCallback beaconLeScanCallback) {
            this.beaconLeScanCallback = beaconLeScanCallback;
        }

        protected Void doInBackground(ScanData... params) {
            ScanData scanData = params[0];
            if (this.beaconLeScanCallback != null) {
                this.beaconLeScanCallback.onBeaconLeScan(scanData.device, scanData.rssi, scanData.scanRecord);
            }
            return null;
        }

        protected void onPostExecute(Void result) {
        }

        protected void onPreExecute() {
        }

        protected void onProgressUpdate(Void... values) {
        }
    }

    public void setScanPeriods(long scanPeriod, long betweenScanPeriod, boolean backgroundFlag) {
        this.cycledScanner.setScanPeriods(scanPeriod, betweenScanPeriod, backgroundFlag);
    }

    public void start() {
        RevealLogger.m12437i("beaconService beacon version: 1.4.21");
        this.bluetoothCrashResolver = new BluetoothCrashResolver(this);
        this.bluetoothCrashResolver.start();
        this.cycledScanner = CycledLeScanner.createScanner(getApplicationContext(), 1100, 20000, this.backgroundFlag, this.cycledLeScanCallback, this.bluetoothCrashResolver, this.disableAndroidL);
        if (this.cycledScanner != null) {
            this.cycledScanner.start();
        }
    }

    public void stop() {
        if (this.cycledScanner != null) {
            this.cycledScanner.stop();
        }
    }

    public void onCreate() {
        RevealLogger.m12438i("beaconService version %s is starting up", BuildConfig.VERSION_NAME);
        this.executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);
        this.bluetoothCrashResolver = new BluetoothCrashResolver(this);
        this.bluetoothCrashResolver.start();
        this.beaconManager = BeaconManager.getInstanceForApplication(getApplicationContext());
    }

    public IBinder onBind(Intent intent) {
        RevealLogger.m12437i("Binding service");
        return this.messenger.getBinder();
    }

    public boolean onUnbind(Intent intent) {
        RevealLogger.m12437i("Unbinding beacon service");
        stop();
        return false;
    }

    public void onDestroy() {
        RevealLogger.m12441w("destroying beacon service");
        if (VERSION.SDK_INT < 18) {
            RevealLogger.m12433e("Destruction prior API 18 is not permitted.");
            return;
        }
        if (this.bluetoothCrashResolver != null) {
            this.bluetoothCrashResolver.stop();
        }
        if (this.cycledScanner != null) {
            this.cycledScanner.stop();
            this.cycledScanner.destroy();
        }
        if (this.handler != null) {
            this.handler.removeCallbacksAndMessages(null);
        }
        RevealLogger.m12437i("onDestroy called. Stopping scans");
    }
}
