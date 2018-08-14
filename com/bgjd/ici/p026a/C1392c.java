package com.bgjd.ici.p026a;

import android.location.Location;
import android.os.RemoteException;
import com.bgjd.ici.p025b.C1395h;
import com.bgjd.ici.p025b.C1410m;
import com.bgjd.ici.p028c.C1434d;
import com.bgjd.ici.p028c.C1435a;
import com.bgjd.ici.p028c.C1436e;
import com.bgjd.ici.p030e.C1477b;
import com.bgjd.ici.p030e.C1478c;
import com.bgjd.ici.p031f.C1489b;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.powersave.BackgroundPowerSaver;

public class C1392c implements C1389a, MonitorNotifier {
    private C1395h f2041a;
    private BeaconManager f2042b;
    private BeaconConsumer f2043c;
    private BackgroundPowerSaver f2044d;
    private C1390b f2045e;
    private Timer f2046f = new Timer("beaconTimer", true);
    private TimerTask f2047g = new C13911(this);

    class C13911 extends TimerTask {
        final /* synthetic */ C1392c f2040a;

        C13911(C1392c c1392c) {
            this.f2040a = c1392c;
        }

        public void run() {
            this.f2040a.mo2172d();
        }
    }

    public C1392c(C1395h c1395h) {
        this.f2041a = c1395h;
    }

    public void m2658a(BeaconConsumer beaconConsumer) {
        this.f2043c = beaconConsumer;
    }

    public void mo2169a() {
        this.f2042b = BeaconManager.getInstanceForApplication(this.f2041a.getContext());
        this.f2045e = new C1390b(this.f2041a);
        this.f2042b.bind(this.f2043c);
        this.f2044d = new BackgroundPowerSaver(this.f2041a.getContext());
    }

    public void mo2170b() {
        for (Region stopMonitoringBeaconsInRegion : this.f2042b.getMonitoredRegions()) {
            try {
                this.f2042b.stopMonitoringBeaconsInRegion(stopMonitoringBeaconsInRegion);
            } catch (RemoteException e) {
            }
        }
        this.f2042b.unbind(this.f2043c);
        this.f2044d = null;
    }

    public void didEnterRegion(Region region) {
        try {
            this.f2042b.startRangingBeaconsInRegion(region);
        } catch (RemoteException e) {
        }
    }

    public void didExitRegion(Region region) {
    }

    public void didDetermineStateForRegion(int i, Region region) {
    }

    public void mo2171c() {
        try {
            this.f2042b.setMonitorNotifier(this);
            this.f2042b.setBackgroundMode(true);
            this.f2042b.setForegroundBetweenScanPeriod(TimeUnit.SECONDS.toMillis(10));
            this.f2042b.setForegroundScanPeriod(TimeUnit.SECONDS.toMillis(2));
            this.f2042b.setBackgroundBetweenScanPeriod(TimeUnit.SECONDS.toMillis(10));
            this.f2042b.setBackgroundScanPeriod(TimeUnit.SECONDS.toMillis(2));
            this.f2042b.updateScanPeriods();
            this.f2042b.setRangeNotifier(this.f2045e);
            this.f2046f.schedule(this.f2047g, 5000, 300000);
        } catch (RemoteException e) {
        }
    }

    public void mo2172d() {
        try {
            if (this.f2042b != null) {
                C1434d c1435a = new C1435a(new C1410m(this.f2041a));
                c1435a.open();
                if (c1435a.IsConnected()) {
                    for (Region stopMonitoringBeaconsInRegion : this.f2042b.getMonitoredRegions()) {
                        try {
                            this.f2042b.stopMonitoringBeaconsInRegion(stopMonitoringBeaconsInRegion);
                        } catch (RemoteException e) {
                        }
                    }
                    this.f2042b.getBeaconParsers().clear();
                    this.f2042b.getMonitoredRegions().clear();
                    C1436e c = ((C1489b) c1435a.getMapper(C1489b.class, C1478c.class)).m3172c(10);
                    while (c.read()) {
                        this.f2042b.getBeaconParsers().add(new BeaconParser().setBeaconLayout(((C1478c) c.fetch()).m3076b()));
                    }
                    c.close();
                    c = ((C1489b) c1435a.getMapper(C1489b.class, C1477b.class)).m3167a(10);
                    double latitude = (double) this.f2041a.getLatitude();
                    double longitude = (double) this.f2041a.getLongitude();
                    Location location = new Location("current");
                    location.setLatitude(latitude);
                    location.setLongitude(longitude);
                    while (c.read()) {
                        C1477b c1477b = (C1477b) c.fetch();
                        String e2 = c1477b.m3071e();
                        Identifier parse = Identifier.parse(e2);
                        if (!(latitude == 0.0d || longitude == 0.0d)) {
                            Location location2 = new Location("center");
                            location2.setLatitude(c1477b.m3072f());
                            location2.setLongitude(c1477b.m3073g());
                            if (((double) location2.distanceTo(location)) <= c1477b.m3074h()) {
                                this.f2042b.startMonitoringBeaconsInRegion(new Region(e2, parse, null, null));
                            }
                        }
                    }
                    this.f2042b.startMonitoringBeaconsInRegion(new Region("mkt", null, null, null));
                    c.close();
                }
                c1435a.close();
            }
        } catch (RemoteException e3) {
        }
    }
}
