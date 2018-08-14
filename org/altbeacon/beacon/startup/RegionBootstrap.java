package org.altbeacon.beacon.startup;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.List;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.logging.LogManager;

public class RegionBootstrap {
    protected static final String TAG = "AppStarter";
    private BootstrapNotifier application;
    private BeaconConsumer beaconConsumer;
    private BeaconManager beaconManager;
    private boolean disabled = false;
    private List<Region> regions;
    private boolean serviceConnected = false;

    private class InternalBeaconConsumer implements BeaconConsumer {
        private Intent serviceIntent;

        private InternalBeaconConsumer() {
        }

        public void onBeaconServiceConnect() {
            LogManager.m16371d(RegionBootstrap.TAG, "Activating background region monitoring", new Object[0]);
            RegionBootstrap.this.beaconManager.addMonitorNotifier(RegionBootstrap.this.application);
            RegionBootstrap.this.serviceConnected = true;
            try {
                for (Region region : RegionBootstrap.this.regions) {
                    LogManager.m16371d(RegionBootstrap.TAG, "Background region monitoring activated for region %s", region);
                    RegionBootstrap.this.beaconManager.startMonitoringBeaconsInRegion(region);
                    if (RegionBootstrap.this.beaconManager.isBackgroundModeUninitialized()) {
                        RegionBootstrap.this.beaconManager.setBackgroundMode(true);
                    }
                }
            } catch (RemoteException e) {
                LogManager.m16374e(e, RegionBootstrap.TAG, "Can't set up bootstrap regions", new Object[0]);
            }
        }

        public boolean bindService(Intent intent, ServiceConnection conn, int arg2) {
            this.serviceIntent = intent;
            RegionBootstrap.this.application.getApplicationContext().startService(intent);
            return RegionBootstrap.this.application.getApplicationContext().bindService(intent, conn, arg2);
        }

        public Context getApplicationContext() {
            return RegionBootstrap.this.application.getApplicationContext();
        }

        public void unbindService(ServiceConnection conn) {
            RegionBootstrap.this.application.getApplicationContext().unbindService(conn);
            RegionBootstrap.this.application.getApplicationContext().stopService(this.serviceIntent);
            RegionBootstrap.this.serviceConnected = false;
        }
    }

    public RegionBootstrap(BootstrapNotifier application, Region region) {
        if (application.getApplicationContext() == null) {
            throw new NullPointerException("The BootstrapNotifier instance is returning null from its getApplicationContext() method.  Have you implemented this method?");
        }
        this.beaconManager = BeaconManager.getInstanceForApplication(application.getApplicationContext());
        this.application = application;
        this.regions = new ArrayList();
        this.regions.add(region);
        this.beaconConsumer = new InternalBeaconConsumer();
        this.beaconManager.bind(this.beaconConsumer);
        LogManager.m16371d(TAG, "Waiting for BeaconService connection", new Object[0]);
    }

    public RegionBootstrap(BootstrapNotifier application, List<Region> regions) {
        if (application.getApplicationContext() == null) {
            throw new NullPointerException("The BootstrapNotifier instance is returning null from its getApplicationContext() method.  Have you implemented this method?");
        }
        this.beaconManager = BeaconManager.getInstanceForApplication(application.getApplicationContext());
        this.application = application;
        this.regions = regions;
        this.beaconConsumer = new InternalBeaconConsumer();
        this.beaconManager.bind(this.beaconConsumer);
        LogManager.m16371d(TAG, "Waiting for BeaconService connection", new Object[0]);
    }

    public void disable() {
        if (!this.disabled) {
            this.disabled = true;
            try {
                for (Region region : this.regions) {
                    this.beaconManager.stopMonitoringBeaconsInRegion(region);
                }
            } catch (RemoteException e) {
                LogManager.m16374e(e, TAG, "Can't stop bootstrap regions", new Object[0]);
            }
            this.beaconManager.unbind(this.beaconConsumer);
        }
    }

    public void addRegion(Region region) {
        if (!this.regions.contains(region)) {
            if (this.serviceConnected) {
                try {
                    this.beaconManager.startMonitoringBeaconsInRegion(region);
                } catch (RemoteException e) {
                    LogManager.m16374e(e, TAG, "Can't add bootstrap region", new Object[0]);
                }
            } else {
                LogManager.m16379w(TAG, "Adding a region: service not yet Connected", new Object[0]);
            }
            this.regions.add(region);
        }
    }

    public void removeRegion(Region region) {
        if (this.regions.contains(region)) {
            if (this.serviceConnected) {
                try {
                    this.beaconManager.stopMonitoringBeaconsInRegion(region);
                } catch (RemoteException e) {
                    LogManager.m16374e(e, TAG, "Can't stop bootstrap region", new Object[0]);
                }
            } else {
                LogManager.m16379w(TAG, "Removing a region: service not yet Connected", new Object[0]);
            }
            this.regions.remove(region);
        }
    }
}
