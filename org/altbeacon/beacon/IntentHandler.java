package org.altbeacon.beacon;

import android.content.Context;
import android.content.Intent;
import java.util.Collection;
import java.util.Set;
import org.altbeacon.beacon.logging.LogManager;
import org.altbeacon.beacon.service.MonitoringData;
import org.altbeacon.beacon.service.MonitoringStatus;
import org.altbeacon.beacon.service.RangingData;

class IntentHandler {
    private static final String TAG = IntentHandler.class.getSimpleName();

    IntentHandler() {
    }

    public void convertIntentsToCallbacks(Context context, Intent intent) {
        MonitoringData monitoringData = null;
        RangingData rangingData = null;
        if (!(intent == null || intent.getExtras() == null)) {
            if (intent.getExtras().getBundle("monitoringData") != null) {
                monitoringData = MonitoringData.fromBundle(intent.getExtras().getBundle("monitoringData"));
            }
            if (intent.getExtras().getBundle("rangingData") != null) {
                rangingData = RangingData.fromBundle(intent.getExtras().getBundle("rangingData"));
            }
        }
        if (rangingData != null) {
            LogManager.m16371d(TAG, "got ranging data", new Object[0]);
            if (rangingData.getBeacons() == null) {
                LogManager.m16379w(TAG, "Ranging data has a null beacons collection", new Object[0]);
            }
            Set<RangeNotifier> notifiers = BeaconManager.getInstanceForApplication(context).getRangingNotifiers();
            Collection<Beacon> beacons = rangingData.getBeacons();
            if (notifiers != null) {
                for (RangeNotifier notifier : notifiers) {
                    notifier.didRangeBeaconsInRegion(beacons, rangingData.getRegion());
                }
            } else {
                LogManager.m16371d(TAG, "but ranging notifier is null, so we're dropping it.", new Object[0]);
            }
            RangeNotifier dataNotifier = BeaconManager.getInstanceForApplication(context).getDataRequestNotifier();
            if (dataNotifier != null) {
                dataNotifier.didRangeBeaconsInRegion(beacons, rangingData.getRegion());
            }
        }
        if (monitoringData != null) {
            LogManager.m16371d(TAG, "got monitoring data", new Object[0]);
            Set<MonitorNotifier> notifiers2 = BeaconManager.getInstanceForApplication(context).getMonitoringNotifiers();
            if (notifiers2 != null) {
                for (MonitorNotifier notifier2 : notifiers2) {
                    LogManager.m16371d(TAG, "Calling monitoring notifier: %s", notifier2);
                    Region region = monitoringData.getRegion();
                    Integer state = Integer.valueOf(monitoringData.isInside() ? 1 : 0);
                    notifier2.didDetermineStateForRegion(state.intValue(), region);
                    MonitoringStatus.getInstanceForApplication(context).updateLocalState(region, state);
                    if (monitoringData.isInside()) {
                        notifier2.didEnterRegion(monitoringData.getRegion());
                    } else {
                        notifier2.didExitRegion(monitoringData.getRegion());
                    }
                }
            }
        }
    }
}
