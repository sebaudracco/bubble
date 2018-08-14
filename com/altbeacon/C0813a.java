package com.altbeacon;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import com.altbeacon.beacon.Beacon;
import com.altbeacon.beacon.C0819c;
import com.altbeacon.beacon.C0829b;
import com.altbeacon.beacon.C0844f;
import com.altbeacon.beacon.Region;
import com.altbeacon.beacon.p012b.C0822c;
import com.altbeacon.beacon.p012b.C0827e;
import com.altbeacon.beacon.p013c.C0835d;
import com.altbeacon.beacon.p014d.C0839a;
import com.altbeacon.beacon.service.C0862a;
import com.altbeacon.beacon.service.C0863b;
import com.altbeacon.beacon.service.C0864c;
import com.altbeacon.beacon.service.C0866e;
import com.altbeacon.beacon.service.C0867f;
import com.altbeacon.beacon.service.C0869h;
import com.altbeacon.beacon.service.C0876l;
import com.altbeacon.beacon.service.StartRMData;
import com.altbeacon.beacon.service.p009a.C0804a;
import com.altbeacon.beacon.service.p009a.C0847b;
import com.altbeacon.beacon.service.p009a.C0858e;
import com.altbeacon.beacon.service.p009a.C0859f;
import com.altbeacon.p010a.C0811b;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import org.altbeacon.beacon.BeaconParser;

public class C0813a {
    int f1539a;
    protected final C0804a f1540b = new C08051(this);
    private final String f1541c = C0813a.class.toString();
    private C0811b f1542d;
    private Context f1543e;
    private ExecutorService f1544f;
    private C0847b f1545g;
    private C0829b f1546h;
    private C0822c f1547i = null;
    private C0866e f1548j;
    private Set<C0819c> f1549k = new HashSet();
    private C0864c f1550l;
    private final C0858e f1551m = new C0858e();
    private final Map<Region, C0867f> f1552n = new HashMap();

    class C08051 implements C0804a {
        final /* synthetic */ C0813a f1511a;

        C08051(C0813a c0813a) {
            this.f1511a = c0813a;
        }

        public void mo1867a() {
            this.f1511a.f1551m.m1745a();
            this.f1511a.f1548j.m1775c();
            this.f1511a.m1523c();
        }

        @TargetApi(11)
        public void mo1868a(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
            try {
                new C0807b(this.f1511a, this.f1511a.f1546h.m1637l()).executeOnExecutor(this.f1511a.f1544f, new C0806a[]{new C0806a(bluetoothDevice, i, bArr)});
            } catch (RejectedExecutionException e) {
                C0835d.m1662c(this.f1511a.f1541c, "Ignoring scan result because we cannot keep up.", new Object[0]);
            }
        }
    }

    private static class C0806a {
        final int f1512a;
        final BluetoothDevice f1513b;
        final byte[] f1514c;

        C0806a(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
            this.f1513b = bluetoothDevice;
            this.f1512a = i;
            this.f1514c = bArr;
        }
    }

    private class C0807b extends AsyncTask<C0806a, Void, Void> {
        final C0863b f1515a = C0863b.m1752a();
        final /* synthetic */ C0813a f1516b;
        private final C0859f f1517c;

        public C0807b(C0813a c0813a, C0859f c0859f) {
            this.f1516b = c0813a;
            this.f1517c = c0859f;
        }

        protected Void m1489a(C0806a... c0806aArr) {
            C0806a c0806a = c0806aArr[0];
            Beacon beacon = null;
            for (C0819c a : this.f1516b.f1549k) {
                beacon = a.mo1871a(c0806a.f1514c, c0806a.f1512a, c0806a.f1513b);
                if (beacon != null) {
                    break;
                }
            }
            if (beacon != null) {
                if (C0835d.m1659a()) {
                    C0835d.m1657a(this.f1516b.f1541c, "Beacon packet detected for: " + beacon + " with rssi " + beacon.m1551g(), new Object[0]);
                }
                this.f1515a.m1754c();
                if (!(this.f1516b.f1545g.m1717c() || this.f1516b.f1551m.m1746a(c0806a.f1513b.getAddress(), c0806a.f1514c))) {
                    C0835d.m1660b(this.f1516b.f1541c, "Non-distinct packets detected in a single scan.  Restarting scans unnecessary.", new Object[0]);
                    this.f1516b.f1545g.m1715a(true);
                }
                C0813a c0813a = this.f1516b;
                c0813a.f1539a++;
                this.f1516b.m1519a(beacon);
            } else if (this.f1517c != null) {
                this.f1517c.m1747a(c0806a.f1513b, c0806a.f1512a, c0806a.f1514c);
            }
            return null;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m1489a((C0806a[]) objArr);
        }
    }

    public C0813a(Context context) {
        this.f1543e = context;
    }

    private List<Region> m1517a(Beacon beacon, Collection<Region> collection) {
        List<Region> arrayList = new ArrayList();
        for (Region region : collection) {
            if (region.m1563a(beacon)) {
                arrayList.add(region);
            } else {
                C0835d.m1657a(this.f1541c, "This region (%s) does not match beacon: %s", region, beacon);
            }
        }
        return arrayList;
    }

    private void m1519a(Beacon beacon) {
        if (C0876l.m1807a().m1813b()) {
            C0876l.m1807a().m1812a(beacon);
        }
        if (C0835d.m1659a()) {
            C0835d.m1657a(this.f1541c, "beacon detected : %s", beacon.toString());
        }
        Beacon a = this.f1550l.m1758a(beacon);
        if (a != null) {
            this.f1548j.m1771a(a);
            C0835d.m1657a(this.f1541c, "looking for ranging region matches for this beacon", new Object[0]);
            synchronized (this.f1552n) {
                for (Region region : m1517a(a, this.f1552n.keySet())) {
                    C0835d.m1657a(this.f1541c, "matches ranging region: %s", region);
                    C0867f c0867f = (C0867f) this.f1552n.get(region);
                    if (c0867f != null) {
                        c0867f.m1780a(a);
                    }
                }
            }
        } else if (C0835d.m1659a()) {
            C0835d.m1657a(this.f1541c, "not processing detections for GATT extra data beacon", new Object[0]);
        }
    }

    private void m1520a(Region region, C0862a c0862a) {
        synchronized (this.f1552n) {
            if (this.f1552n.containsKey(region)) {
                C0835d.m1660b(this.f1541c, "Already ranging that region -- will replace existing region.", new Object[0]);
                this.f1552n.remove(region);
            }
            this.f1552n.put(region, new C0867f(c0862a));
            C0835d.m1657a(this.f1541c, "Currently ranging %s regions.", Integer.valueOf(this.f1552n.size()));
        }
        this.f1545g.m1712a();
    }

    private void m1523c() {
        synchronized (this.f1552n) {
            for (Region region : this.f1552n.keySet()) {
                C0867f c0867f = (C0867f) this.f1552n.get(region);
                C0835d.m1657a(this.f1541c, "Calling ranging callback", new Object[0]);
                m1532a(new C0869h(c0867f.m1779a(), region).m1793c());
            }
        }
    }

    private StartRMData m1524d() {
        return this.f1546h.m1626a(new Region("all-beacons-region", null, null, null));
    }

    protected void m1530a() {
        boolean z;
        Set hashSet = new HashSet();
        if (this.f1546h.m1631d() != null) {
            hashSet.addAll(this.f1546h.m1631d());
            z = true;
            for (C0819c c0819c : this.f1546h.m1631d()) {
                if (c0819c.m1577a().size() > 0) {
                    z = false;
                    hashSet.addAll(c0819c.m1577a());
                }
                z = z;
            }
        } else {
            z = true;
        }
        this.f1549k = hashSet;
        this.f1550l = new C0864c(z);
    }

    public void m1531a(long j, long j2, boolean z) {
        this.f1545g.m1713a(j, j2, z);
    }

    protected void m1532a(Bundle bundle) {
        C0835d.m1657a(this.f1541c, "got an intent to process", new Object[0]);
        C0869h a = C0869h.m1790a(bundle);
        if (a != null) {
            C0835d.m1657a(this.f1541c, "got ranging data", new Object[0]);
            if (a.m1791a() == null) {
                C0835d.m1662c(this.f1541c, "Ranging data has a null beacons collection", new Object[0]);
            }
            Set<C0844f> h = C0829b.m1619a(this.f1543e).m1635h();
            Collection a2 = a.m1791a();
            for (C0844f a3 : h) {
                a3.m1698a(a2, a.m1792b());
            }
        }
    }

    public void m1533a(Region region) {
        synchronized (this.f1552n) {
            this.f1552n.remove(region);
            int size = this.f1552n.size();
            C0835d.m1657a(this.f1541c, "Currently ranging %s regions.", Integer.valueOf(this.f1552n.size()));
        }
        if (size == 0 && this.f1548j.m1773b() == 0) {
            this.f1545g.m1716b();
        }
    }

    public void m1534a(C0844f c0844f) {
        this.f1542d = new C0811b(this.f1543e);
        this.f1542d.m1507a();
        this.f1544f = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);
        this.f1545g = C0847b.m1706a(this.f1543e, 1100, 0, false, this.f1540b, this.f1542d);
        this.f1546h = C0829b.m1619a(this.f1543e.getApplicationContext());
        this.f1546h.m1631d().add(new C0819c().m1576a(BeaconParser.EDDYSTONE_URL_LAYOUT));
        this.f1546h.m1628a(true);
        if (this.f1546h.m1629b()) {
            C0835d.m1660b(this.f1541c, "beaconService version %s is starting up on the main process", "1.0");
        } else {
            C0835d.m1660b(this.f1541c, "beaconService version %s is starting up on a separate process", "1.0");
            C0839a c0839a = new C0839a(this.f1543e);
            C0835d.m1660b(this.f1541c, "beaconService PID is " + c0839a.m1679c() + " with process name " + c0839a.m1677a(), new Object[0]);
        }
        m1530a();
        this.f1547i = new C0827e(this.f1543e, C0829b.m1620i());
        Beacon.m1540a(this.f1547i);
        this.f1548j = C0866e.m1763a(this.f1543e.getApplicationContext());
        StartRMData d = m1524d();
        C0835d.m1660b(this.f1541c, "start ranging received", new Object[0]);
        m1520a(d.m1703c(), new C0862a(d.m1704d()));
        m1531a(d.m1701a(), d.m1702b(), d.m1705e());
        this.f1546h.m1627a(c0844f);
    }

    public void m1535b() {
        C0835d.m1660b(this.f1541c, "stop ranging received", new Object[0]);
        StartRMData d = m1524d();
        m1533a(d.m1703c());
        m1531a(d.m1701a(), d.m1702b(), d.m1705e());
        this.f1546h.m1633f();
    }
}
