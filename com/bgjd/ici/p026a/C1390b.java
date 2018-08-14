package com.bgjd.ici.p026a;

import com.bgjd.ici.p025b.C1395h;
import com.bgjd.ici.p025b.C1402i;
import com.bgjd.ici.p025b.C1410m;
import com.bgjd.ici.p028c.C1434d;
import com.bgjd.ici.p028c.C1435a;
import com.bgjd.ici.p028c.C1436e;
import com.bgjd.ici.p030e.C1476a;
import com.bgjd.ici.p030e.C1477b;
import com.bgjd.ici.p031f.C1488a;
import com.bgjd.ici.p031f.C1489b;
import java.util.Collection;
import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.utils.UrlBeaconUrlCompressor;

public class C1390b implements RangeNotifier {
    private static final String f2038b = "BCONEMGR";
    private C1395h f2039a;

    public C1390b(C1395h c1395h) {
        this.f2039a = c1395h;
    }

    public synchronized void didRangeBeaconsInRegion(Collection<Beacon> collection, Region region) {
        long longValue;
        long j;
        long j2;
        long j3;
        C1436e b;
        int i;
        int i2;
        int i3;
        if (collection != null) {
            if (!collection.isEmpty()) {
                C1434d c1435a = new C1435a(new C1410m(this.f2039a));
                c1435a.open();
                C1488a c1488a = (C1488a) c1435a.getMapper(C1488a.class, C1476a.class);
                C1489b c1489b = (C1489b) c1435a.getMapper(C1489b.class, C1477b.class);
                for (Beacon beacon : collection) {
                    if (beacon != null) {
                        C1477b c1477b;
                        String identifier = beacon.getId1().toString();
                        String identifier2 = beacon.getId2().toString();
                        String identifier3 = beacon.getId3().toString();
                        int rssi = beacon.getRssi();
                        int txPower = beacon.getTxPower();
                        String bluetoothAddress = beacon.getBluetoothAddress();
                        int beaconTypeCode = beacon.getBeaconTypeCode();
                        int serviceUuid = beacon.getServiceUuid();
                        int manufacturer = beacon.getManufacturer();
                        String bluetoothName = beacon.getBluetoothName();
                        if (bluetoothName == null) {
                            bluetoothName = "";
                        }
                        double distance = beacon.getDistance();
                        long j4 = 0;
                        long j5 = 0;
                        long j6 = 0;
                        String str = "";
                        if (beacon.getBeaconTypeCode() == 16) {
                            str = UrlBeaconUrlCompressor.uncompress(beacon.getId1().toByteArray());
                        }
                        if (beacon.getExtraDataFields().size() > 0) {
                            try {
                                j4 = ((Long) beacon.getExtraDataFields().get(0)).longValue();
                                j5 = ((Long) beacon.getExtraDataFields().get(1)).longValue();
                                j6 = ((Long) beacon.getExtraDataFields().get(3)).longValue();
                                longValue = ((Long) beacon.getExtraDataFields().get(4)).longValue();
                                try {
                                    C1402i.m2901b(f2038b, "The beacon is sending telemetry version " + j4 + ", has been up for : " + longValue + " seconds" + ", has a battery level of " + j5 + " mV" + ", and has transmitted " + j6 + " advertisements.");
                                    j = j5;
                                    j5 = longValue;
                                    longValue = j4;
                                } catch (IndexOutOfBoundsException e) {
                                    j = j6;
                                    j6 = j5;
                                    j5 = j4;
                                    j2 = longValue;
                                    longValue = j5;
                                    j5 = j2;
                                    j3 = j6;
                                    j6 = j;
                                    j = j3;
                                    if (!c1435a.IsConnected()) {
                                        b = c1489b.m3170b(identifier);
                                        i = 0;
                                        i2 = 0;
                                        i3 = 0;
                                        while (b.read()) {
                                            c1477b = (C1477b) b.fetch();
                                            i = c1477b.m3068b();
                                            i2 = c1477b.m3069c();
                                            i3 = c1477b.m3070d();
                                        }
                                        b.close();
                                        c1488a.m3165a(new C1476a(identifier, identifier2, identifier3, rssi, txPower, bluetoothAddress, beaconTypeCode, serviceUuid, manufacturer, bluetoothName, distance, longValue, j, j6, j5, str, i, i2, i3));
                                        C1402i.m2901b(f2038b, "DETECTED BEACON UUID : " + identifier + " MAJOR : " + identifier2 + " MINOR : " + identifier3 + " DISTANCE : " + distance);
                                    }
                                }
                            } catch (IndexOutOfBoundsException e2) {
                                longValue = 0;
                                j = j6;
                                j6 = j5;
                                j5 = j4;
                                j2 = longValue;
                                longValue = j5;
                                j5 = j2;
                                j3 = j6;
                                j6 = j;
                                j = j3;
                                if (!c1435a.IsConnected()) {
                                    b = c1489b.m3170b(identifier);
                                    i = 0;
                                    i2 = 0;
                                    i3 = 0;
                                    while (b.read()) {
                                        c1477b = (C1477b) b.fetch();
                                        i = c1477b.m3068b();
                                        i2 = c1477b.m3069c();
                                        i3 = c1477b.m3070d();
                                    }
                                    b.close();
                                    c1488a.m3165a(new C1476a(identifier, identifier2, identifier3, rssi, txPower, bluetoothAddress, beaconTypeCode, serviceUuid, manufacturer, bluetoothName, distance, longValue, j, j6, j5, str, i, i2, i3));
                                    C1402i.m2901b(f2038b, "DETECTED BEACON UUID : " + identifier + " MAJOR : " + identifier2 + " MINOR : " + identifier3 + " DISTANCE : " + distance);
                                }
                            }
                        } else {
                            longValue = 0;
                            j5 = 0;
                            j = 0;
                        }
                        if (!c1435a.IsConnected()) {
                            b = c1489b.m3170b(identifier);
                            i = 0;
                            i2 = 0;
                            i3 = 0;
                            while (b.read()) {
                                c1477b = (C1477b) b.fetch();
                                i = c1477b.m3068b();
                                i2 = c1477b.m3069c();
                                i3 = c1477b.m3070d();
                            }
                            b.close();
                            c1488a.m3165a(new C1476a(identifier, identifier2, identifier3, rssi, txPower, bluetoothAddress, beaconTypeCode, serviceUuid, manufacturer, bluetoothName, distance, longValue, j, j6, j5, str, i, i2, i3));
                            C1402i.m2901b(f2038b, "DETECTED BEACON UUID : " + identifier + " MAJOR : " + identifier2 + " MINOR : " + identifier3 + " DISTANCE : " + distance);
                        }
                    }
                }
                c1435a.close();
            }
        }
    }
}
