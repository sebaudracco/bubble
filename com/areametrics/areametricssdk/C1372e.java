package com.areametrics.areametricssdk;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import android.os.RemoteException;
import com.areametrics.areametricssdk.C1351c.C1350a;
import com.google.android.gms.wearable.WearableStatusCodes;
import com.mopub.mobileads.resource.DrawableConstants.CloseButton;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

class C1372e implements C1350a, BeaconConsumer {
    private static final String f1959a = ("AMS-" + C1372e.class.getSimpleName());
    private BeaconManager f1960b;
    private HashSet<String> f1961c;
    private ConcurrentHashMap<String, C1370a> f1962d;
    private ConcurrentLinkedQueue<Map<String, Object>> f1963e;
    private Map<String, Integer> f1964f;
    private Map<String, Long> f1965g;
    private C1367d f1966h;
    private C1351c f1967i;
    private C1378g f1968j;

    class C13681 implements RangeNotifier {
        final /* synthetic */ C1372e f1933a;

        C13681(C1372e c1372e) {
            this.f1933a = c1372e;
        }

        public final void didRangeBeaconsInRegion(Collection<Beacon> collection, Region region) {
            this.f1933a.m2557a();
            for (Beacon beacon : collection) {
                if (beacon.getIdentifiers().size() >= 3) {
                    try {
                        C1370a c1370a;
                        String format = String.format(Locale.US, "%s.%s.%s", new Object[]{beacon.getId1().toString(), beacon.getId2().toString(), beacon.getId3().toString()});
                        if (this.f1933a.f1962d.containsKey(format)) {
                            c1370a = (C1370a) this.f1933a.f1962d.get(format);
                        } else {
                            C1370a c1370a2 = new C1370a(new Region(format, beacon.getId1(), beacon.getId2(), beacon.getId3()), beacon);
                            this.f1933a.f1962d.put(format, c1370a2);
                            c1370a = c1370a2;
                        }
                        int rssi = beacon.getRssi();
                        switch (c1370a.f1949o) {
                            case START:
                                c1370a.f1949o = C1371b.ACTIVE;
                                c1370a.f1953s.m2544a(c1370a.f1935a.getUniqueId());
                                c1370a.f1936b = System.currentTimeMillis();
                                c1370a.f1940f++;
                                c1370a.f1937c = c1370a.f1936b;
                                c1370a.f1953s.m2545a(c1370a.f1935a, c1370a.f1936b, true, c1370a.f1947m);
                                c1370a.m2539a(rssi);
                                try {
                                    c1370a.f1953s.f1960b.startMonitoringBeaconsInRegion(c1370a.f1935a);
                                } catch (RemoteException e) {
                                }
                                c1370a.f1938d++;
                                break;
                            case INACTIVE:
                                c1370a.f1949o = C1371b.ACTIVE;
                                c1370a.f1953s.m2544a(c1370a.f1935a.getUniqueId());
                                c1370a.f1937c = System.currentTimeMillis();
                                c1370a.f1940f++;
                                c1370a.f1953s.m2545a(c1370a.f1935a, c1370a.f1937c, true, c1370a.f1947m);
                                try {
                                    c1370a.f1953s.f1960b.startMonitoringBeaconsInRegion(c1370a.f1935a);
                                } catch (RemoteException e2) {
                                }
                                c1370a.f1938d++;
                                c1370a.m2539a(rssi);
                                break;
                            case ACTIVE:
                                c1370a.f1937c = System.currentTimeMillis();
                                c1370a.f1940f++;
                                c1370a.m2539a(rssi);
                                break;
                        }
                        c1370a.f1940f++;
                    } catch (Exception e3) {
                    }
                }
            }
            this.f1933a.m2555f();
        }
    }

    class C1370a {
        Region f1935a;
        long f1936b;
        long f1937c;
        int f1938d;
        int f1939e;
        int f1940f;
        long f1941g;
        int f1942h;
        int f1943i;
        double f1944j;
        long f1945k;
        int f1946l;
        String f1947m;
        int f1948n;
        C1371b f1949o;
        Location f1950p;
        Location f1951q;
        ConcurrentLinkedQueue<Location> f1952r;
        final /* synthetic */ C1372e f1953s;

        C1370a(C1372e c1372e, Map<String, String> map) {
            String str;
            Identifier identifier = null;
            this.f1953s = c1372e;
            this.f1947m = null;
            this.f1948n = 20;
            this.f1950p = null;
            this.f1951q = null;
            this.f1952r = new ConcurrentLinkedQueue();
            this.f1941g = ((long) c1372e.m2551d().m2613d()) * 1000;
            this.f1949o = C1371b.valueOf((String) map.get("state"));
            this.f1946l = Integer.valueOf((String) map.get("power")).intValue();
            this.f1947m = (String) map.get("parserIdentifier");
            this.f1936b = Long.valueOf((String) map.get("enterTime")).longValue();
            this.f1937c = Long.valueOf((String) map.get("exitTime")).longValue();
            this.f1938d = Integer.valueOf((String) map.get("numEnters")).intValue();
            this.f1939e = Integer.valueOf((String) map.get("numExits")).intValue();
            this.f1940f = Integer.valueOf((String) map.get("numRanges")).intValue();
            this.f1942h = Integer.valueOf((String) map.get("maxRSSI")).intValue();
            this.f1943i = Integer.valueOf((String) map.get("minRSSI")).intValue();
            this.f1944j = (double) Float.valueOf((String) map.get("avgRSSI")).floatValue();
            this.f1945k = Long.valueOf((String) map.get("numRSSI")).longValue();
            Identifier parse = map.containsKey("regId1") ? Identifier.parse((String) map.get("regId1")) : null;
            Identifier parse2 = map.containsKey("regId2") ? Identifier.parse((String) map.get("regId2")) : null;
            if (map.containsKey("regId3")) {
                identifier = Identifier.parse((String) map.get("regId3"));
            }
            this.f1935a = new Region((String) map.get("regUniqueId"), parse, parse2, identifier);
            if (map.containsKey("lastLocationLatitude")) {
                str = (String) map.get("lastLocationProvider");
                if (str == null) {
                    str = "cache";
                }
                this.f1950p = new Location(str);
                this.f1950p.setTime(Long.valueOf((String) map.get("lastLocationTime")).longValue());
                this.f1950p.setLatitude(Double.valueOf((String) map.get("lastLocationLatitude")).doubleValue());
                this.f1950p.setLongitude(Double.valueOf((String) map.get("lastLocationLongitude")).doubleValue());
                if (map.containsKey("lastLocationAccuracy")) {
                    this.f1950p.setAccuracy(Float.valueOf((String) map.get("lastLocationAccuracy")).floatValue());
                }
                if (map.containsKey("lastLocationAltitude")) {
                    this.f1950p.setAltitude(Double.valueOf((String) map.get("lastLocationAltitude")).doubleValue());
                }
                if (map.containsKey("lastLocationBearing")) {
                    this.f1950p.setBearing(Float.valueOf((String) map.get("lastLocationBearing")).floatValue());
                }
                if (map.containsKey("lastLocationSpeed")) {
                    this.f1950p.setSpeed(Float.valueOf((String) map.get("lastLocationSpeed")).floatValue());
                }
            }
            if (map.containsKey("bestLocationLatitude")) {
                str = (String) map.get("bestLocationProvider");
                if (str == null) {
                    str = "cache";
                }
                this.f1951q = new Location(str);
                this.f1951q.setTime(Long.valueOf((String) map.get("bestLocationTime")).longValue());
                this.f1951q.setLatitude(Double.valueOf((String) map.get("bestLocationLatitude")).doubleValue());
                this.f1951q.setLongitude(Double.valueOf((String) map.get("bestLocationLongitude")).doubleValue());
                if (map.containsKey("bestLocationAccuracy")) {
                    this.f1951q.setAccuracy(Float.valueOf((String) map.get("bestLocationAccuracy")).floatValue());
                }
                if (map.containsKey("bestLocationAltitude")) {
                    this.f1951q.setAltitude(Double.valueOf((String) map.get("bestLocationAltitude")).doubleValue());
                }
                if (map.containsKey("bestLocationBearing")) {
                    this.f1951q.setBearing(Float.valueOf((String) map.get("bestLocationBearing")).floatValue());
                }
                if (map.containsKey("bestLocationSpeed")) {
                    this.f1951q.setSpeed(Float.valueOf((String) map.get("bestLocationSpeed")).floatValue());
                }
            }
            if (map.containsKey("avgLocationCount")) {
                int intValue = Integer.valueOf((String) map.get("avgLocationCount")).intValue();
                for (int i = 0; i < intValue; i++) {
                    Location location = new Location("avg");
                    location.setLatitude(Double.valueOf((String) map.get("avgLocationLatitude")).doubleValue());
                    location.setLongitude(Double.valueOf((String) map.get("avgLocationLongitude")).doubleValue());
                    if (map.containsKey("avgLocationAccuracy")) {
                        location.setAccuracy(Float.valueOf((String) map.get("avgLocationAccuracy")).floatValue());
                    }
                    this.f1952r.add(location);
                }
            }
        }

        private C1370a(C1372e c1372e, Region region, Beacon beacon) {
            this.f1953s = c1372e;
            this.f1947m = null;
            this.f1948n = 20;
            this.f1950p = null;
            this.f1951q = null;
            this.f1941g = ((long) c1372e.m2551d().m2613d()) * 1000;
            this.f1935a = region;
            this.f1946l = beacon.getTxPower();
            this.f1947m = beacon.getParserIdentifier();
            this.f1936b = 0;
            this.f1937c = 0;
            this.f1938d = 0;
            this.f1939e = 0;
            this.f1940f = 0;
            this.f1943i = Integer.MAX_VALUE;
            this.f1942h = Integer.MIN_VALUE;
            this.f1944j = 0.0d;
            this.f1945k = 0;
            this.f1949o = C1371b.START;
            this.f1952r = new ConcurrentLinkedQueue();
        }

        final Location m2538a() {
            if (this.f1952r.size() == 0) {
                return null;
            }
            Location location;
            Iterator it = this.f1952r.iterator();
            double d = 0.0d;
            double d2 = 0.0d;
            double d3 = 0.0d;
            double d4 = 0.0d;
            while (it.hasNext()) {
                location = (Location) it.next();
                double toRadians = Math.toRadians(location.getLatitude());
                double toRadians2 = Math.toRadians(location.getLongitude());
                d += Math.cos(toRadians) * Math.cos(toRadians2);
                d2 += Math.sin(toRadians2) * Math.cos(toRadians);
                d3 += Math.sin(toRadians);
                d4 = location.hasAccuracy() ? ((double) location.getAccuracy()) + d4 : d4;
            }
            double size = d / ((double) this.f1952r.size());
            d2 /= (double) this.f1952r.size();
            d3 /= (double) this.f1952r.size();
            d4 /= (double) this.f1952r.size();
            d = Math.atan2(d2, size);
            d3 = Math.atan2(d3, Math.sqrt((size * size) + (d2 * d2)));
            location = new Location("avg");
            location.setLatitude(Math.toDegrees(d3));
            location.setLongitude(Math.toDegrees(d));
            if (d4 <= 0.0d) {
                return location;
            }
            location.setAccuracy((float) d4);
            return location;
        }

        final void m2539a(int i) {
            if (i >= this.f1942h) {
                this.f1942h = i;
            }
            if (i < this.f1943i) {
                this.f1943i = i;
            }
            this.f1944j = ((this.f1944j * ((double) this.f1945k)) + ((double) i)) / ((double) (this.f1945k + 1));
            this.f1945k++;
        }

        final boolean m2540b() {
            if (System.currentTimeMillis() - this.f1937c <= this.f1941g) {
                return false;
            }
            this.f1949o = C1371b.END;
            if (this.f1953s.f1960b != null) {
                try {
                    this.f1953s.f1960b.stopMonitoringBeaconsInRegion(this.f1935a);
                } catch (RemoteException e) {
                }
            }
            if (this.f1953s.m2549c() != null) {
                this.f1953s.m2549c().m2524a(this);
            }
            this.f1950p = null;
            this.f1951q = null;
            if (!(this.f1952r == null || this.f1953s.f1962d == null)) {
                this.f1952r.clear();
            }
            return true;
        }
    }

    private enum C1371b {
        START,
        ACTIVE,
        INACTIVE,
        END
    }

    C1372e(Context context) {
        int i = WearableStatusCodes.TARGET_NODE_NOT_CONNECTED;
        this.f1966h = null;
        this.f1967i = null;
        this.f1968j = null;
        this.f1967i = new C1351c(this);
        this.f1961c = new HashSet();
        this.f1962d = new ConcurrentHashMap();
        m2556g();
        this.f1963e = new ConcurrentLinkedQueue();
        this.f1964f = new HashMap();
        this.f1965g = new HashMap();
        this.f1960b = BeaconManager.getInstanceForApplication(context);
        m2559a((long) m2551d().m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).getInt("fg_bscan_period", "release".equals("debug") ? 2000 : WearableStatusCodes.TARGET_NODE_NOT_CONNECTED));
        m2563b((long) m2551d().m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).getInt("fg_bscan_wait", "release".equals("debug") ? 10 : 120));
        C1378g d = m2551d();
        if (!"release".equals("debug")) {
            i = 8000;
        }
        m2565c((long) d.m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).getInt("bg_bscan_period", i));
        m2566d((long) m2551d().m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).getInt("bg_bscan_wait", "release".equals("debug") ? 20 : 240));
    }

    private void m2544a(String str) {
        int i = (str == null || this.f1967i.f1874a) ? 0 : ((!this.f1962d.containsKey(str) || ((C1370a) this.f1962d.get(str)).f1948n > 0) && !m2548b(str)) ? 1 : 0;
        if (i != 0) {
            Integer num = (Integer) this.f1964f.get(str);
            if (num == null) {
                num = Integer.valueOf(0);
            }
            num = Integer.valueOf(num.intValue() + 1);
            this.f1964f.put(str, num);
            this.f1967i.m2462a(50.0f);
            if (num.intValue() >= m2551d().m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).getInt("blt_start_counter", 3)) {
                this.f1964f.put(str, Integer.valueOf(0));
                this.f1965g.put(str, Long.valueOf(System.currentTimeMillis() + ((long) m2551d().m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).getInt("blt_block_unit", 600000))));
            }
        }
    }

    private void m2545a(Region region, long j, boolean z, String str) {
        if (m2548b(region.getUniqueId())) {
            m2549c().m2527a(region, j, null, z, str);
            return;
        }
        Map hashMap = new HashMap();
        hashMap.put("region", region);
        hashMap.put("time", Long.valueOf(j));
        hashMap.put("direction", Boolean.valueOf(z));
        hashMap.put("parser", str);
        this.f1963e.add(hashMap);
        m2544a(region.getUniqueId());
    }

    static void m2547b() {
    }

    private boolean m2548b(String str) {
        return this.f1965g.containsKey(str) && System.currentTimeMillis() < ((Long) this.f1965g.get(str)).longValue();
    }

    private C1367d m2549c() {
        return this.f1966h != null ? this.f1966h : AreaMetricsSDK.INSTANCE.getNetworkController();
    }

    private C1378g m2551d() {
        return this.f1968j != null ? this.f1968j : AreaMetricsSDK.INSTANCE.getUserData();
    }

    private void m2553e() {
        try {
            this.f1960b.updateScanPeriods();
        } catch (RemoteException e) {
        }
    }

    private void m2555f() {
        Editor clear = getApplicationContext().getSharedPreferences("AMS_BEACON_CACHE", 0).edit().clear();
        int i = 0;
        for (C1370a c1370a : this.f1962d.values()) {
            String provider;
            Map hashMap = new HashMap();
            hashMap.put("state", String.valueOf(c1370a.f1949o));
            hashMap.put("power", String.valueOf(c1370a.f1946l));
            hashMap.put("parserIdentifier", c1370a.f1947m);
            hashMap.put("enterTime", String.valueOf(c1370a.f1936b));
            hashMap.put("exitTime", String.valueOf(c1370a.f1937c));
            hashMap.put("numEnters", String.valueOf(c1370a.f1938d));
            hashMap.put("numExits", String.valueOf(c1370a.f1939e));
            hashMap.put("numRanges", String.valueOf(c1370a.f1940f));
            hashMap.put("maxRSSI", String.valueOf(c1370a.f1942h));
            hashMap.put("minRSSI", String.valueOf(c1370a.f1943i));
            hashMap.put("avgRSSI", String.valueOf(c1370a.f1944j));
            hashMap.put("numRSSI", String.valueOf(c1370a.f1945k));
            hashMap.put("regUniqueId", c1370a.f1935a.getUniqueId());
            if (c1370a.f1935a.getId1() != null) {
                hashMap.put("regId1", c1370a.f1935a.getId1().toString());
            }
            if (c1370a.f1935a.getId2() != null) {
                hashMap.put("regId2", c1370a.f1935a.getId2().toString());
            }
            if (c1370a.f1935a.getId3() != null) {
                hashMap.put("regId3", c1370a.f1935a.getId3().toString());
            }
            if (c1370a.f1950p != null) {
                hashMap.put("lastLocationTime", String.valueOf(c1370a.f1950p.getTime()));
                hashMap.put("lastLocationLatitude", String.valueOf(c1370a.f1950p.getLatitude()));
                hashMap.put("lastLocationLongitude", String.valueOf(c1370a.f1950p.getLongitude()));
                if (c1370a.f1950p.hasAccuracy()) {
                    hashMap.put("lastLocationAccuracy", String.valueOf(c1370a.f1950p.getAccuracy()));
                }
                if (c1370a.f1950p.hasAltitude()) {
                    hashMap.put("lastLocationAltitude", String.valueOf(c1370a.f1950p.getAltitude()));
                }
                if (c1370a.f1950p.hasBearing()) {
                    hashMap.put("lastLocationBearing", String.valueOf(c1370a.f1950p.getBearing()));
                }
                if (c1370a.f1950p.hasSpeed()) {
                    hashMap.put("lastLocationSpeed", String.valueOf(c1370a.f1950p.getSpeed()));
                }
                provider = c1370a.f1950p.getProvider();
                if (provider != null) {
                    hashMap.put("lastLocationProvider", provider);
                }
            }
            if (c1370a.f1951q != null) {
                hashMap.put("bestLocationTime", String.valueOf(c1370a.f1951q.getTime()));
                hashMap.put("bestLocationLatitude", String.valueOf(c1370a.f1951q.getLatitude()));
                hashMap.put("bestLocationLongitude", String.valueOf(c1370a.f1951q.getLongitude()));
                if (c1370a.f1951q.hasAccuracy()) {
                    hashMap.put("bestLocationAccuracy", String.valueOf(c1370a.f1951q.getAccuracy()));
                }
                if (c1370a.f1951q.hasAltitude()) {
                    hashMap.put("bestLocationAltitude", String.valueOf(c1370a.f1951q.getAltitude()));
                }
                if (c1370a.f1951q.hasBearing()) {
                    hashMap.put("bestLocationBearing", String.valueOf(c1370a.f1951q.getBearing()));
                }
                if (c1370a.f1951q.hasSpeed()) {
                    hashMap.put("bestLocationSpeed", String.valueOf(c1370a.f1951q.getSpeed()));
                }
                provider = c1370a.f1951q.getProvider();
                if (provider != null) {
                    hashMap.put("bestLocationProvider", provider);
                }
            }
            Location a = c1370a.m2538a();
            if (a != null) {
                hashMap.put("avgLocationCount", String.valueOf(c1370a.f1952r.size()));
                hashMap.put("avgLocationLatitude", String.valueOf(a.getLatitude()));
                hashMap.put("avgLocationLongitude", String.valueOf(a.getLongitude()));
                if (a.hasAccuracy()) {
                    hashMap.put("avgLocationAccuracy", String.valueOf(a.getAccuracy()));
                }
            }
            Editor editor = clear;
            for (Entry entry : hashMap.entrySet()) {
                editor = editor.putString(("BEACON" + i) + ((String) entry.getKey()), (String) entry.getValue());
            }
            i++;
            clear = editor;
        }
        clear.apply();
    }

    private void m2556g() {
        int i = 0;
        if (this.f1962d == null) {
            this.f1962d = new ConcurrentHashMap();
        }
        if (this.f1962d.isEmpty()) {
            Map all = getApplicationContext().getSharedPreferences("AMS_BEACON_CACHE", 0).getAll();
            int i2 = 0;
            while (i < all.size()) {
                String str = "BEACON" + i2;
                Map hashMap = new HashMap();
                int i3 = i;
                for (Entry entry : all.entrySet()) {
                    String str2 = (String) entry.getKey();
                    if (str2.startsWith(str)) {
                        hashMap.put(str2.replaceFirst(str, ""), entry.getValue());
                        i3++;
                    }
                }
                C1370a c1370a = new C1370a(this, hashMap);
                this.f1962d.put(c1370a.f1935a.getUniqueId(), c1370a);
                i2++;
                i = i3;
            }
        }
    }

    final void m2557a() {
        m2556g();
        List<String> arrayList = new ArrayList();
        for (Entry entry : this.f1962d.entrySet()) {
            if (((C1370a) entry.getValue()).m2540b()) {
                arrayList.add(entry.getKey());
            }
        }
        for (String str : arrayList) {
            if (str != null) {
                this.f1962d.remove(str);
            }
        }
        m2555f();
    }

    public final void mo2161a(int i) {
        Iterator it = this.f1963e.iterator();
        while (it.hasNext()) {
            Map map = (Map) it.next();
            String str = (String) map.get("parser");
            m2549c().m2527a((Region) map.get("region"), ((Long) map.get("time")).longValue(), null, ((Boolean) map.get("direction")).booleanValue(), str);
        }
        this.f1963e.clear();
    }

    final void m2559a(long j) {
        this.f1960b.setForegroundScanPeriod(j);
        m2553e();
    }

    public final void mo2162a(Location location) {
        if (location != null) {
            Boolean valueOf = Boolean.valueOf(false);
            Boolean bool = valueOf;
            for (C1370a c1370a : this.f1962d.values()) {
                if (c1370a.f1949o == C1371b.ACTIVE) {
                    c1370a.f1950p = location;
                    c1370a.f1952r.add(location);
                    if (c1370a.f1951q == null || location.getAccuracy() <= c1370a.f1951q.getAccuracy()) {
                        c1370a.f1951q = location;
                    }
                    if (location.getAccuracy() < CloseButton.TEXT_SIZE_SP) {
                        c1370a.f1948n -= 4;
                    } else {
                        c1370a.f1948n = c1370a.f1948n - 1;
                    }
                    if (c1370a.f1948n > 0) {
                        bool = Boolean.valueOf(true);
                    } else {
                        c1370a.f1948n = 0;
                    }
                }
            }
            if (this.f1963e.size() > 0) {
                Map map = (Map) this.f1963e.remove();
                Region region = (Region) map.get("region");
                Long l = (Long) map.get("time");
                Boolean bool2 = (Boolean) map.get("direction");
                String str = (String) map.get("parser");
                m2549c().m2527a(region, l.longValue(), location, bool2.booleanValue(), str);
                if (this.f1963e.size() > 0) {
                    bool = Boolean.valueOf(true);
                }
            }
            if (!bool.booleanValue()) {
                this.f1967i.m2461a();
            }
        }
    }

    public final void mo2163a(Location location, int i) {
        for (C1370a c1370a : this.f1962d.values()) {
            if (location != null && c1370a.f1949o == C1371b.ACTIVE) {
                c1370a.f1950p = location;
                c1370a.f1952r.add(location);
                if (c1370a.f1951q == null || location.getAccuracy() <= c1370a.f1951q.getAccuracy()) {
                    c1370a.f1951q = location;
                }
                if (location.getAccuracy() < CloseButton.TEXT_SIZE_SP) {
                    c1370a.f1948n -= 4;
                } else {
                    c1370a.f1948n = c1370a.f1948n - 1;
                }
                if (c1370a.f1948n < 0) {
                    c1370a.f1948n = 0;
                }
            }
        }
        Iterator it = this.f1963e.iterator();
        while (it.hasNext()) {
            Map map = (Map) it.next();
            Region region = (Region) map.get("region");
            Long l = (Long) map.get("time");
            Boolean bool = (Boolean) map.get("direction");
            String str = (String) map.get("parser");
            m2549c().m2527a(region, l.longValue(), location, bool.booleanValue(), str);
        }
        this.f1963e.clear();
    }

    final void m2562a(Region region) {
        m2557a();
        if (m2549c() != null) {
            m2549c().m2532f();
            m2549c().m2535i();
        }
        if (this.f1962d.containsKey(region.getUniqueId())) {
            C1370a c1370a = (C1370a) this.f1962d.get(region.getUniqueId());
            c1370a.f1937c = System.currentTimeMillis();
            c1370a.f1938d++;
            m2545a(c1370a.f1935a, System.currentTimeMillis(), true, c1370a.f1947m);
        } else if (!this.f1961c.contains(region.getUniqueId())) {
            this.f1961c.add(region.getUniqueId());
            try {
                this.f1960b.startRangingBeaconsInRegion(region);
            } catch (RemoteException e) {
            }
        }
        m2555f();
    }

    final void m2563b(long j) {
        this.f1960b.setForegroundBetweenScanPeriod(1000 * j);
        m2553e();
    }

    final void m2564b(Region region) {
        m2557a();
        if (m2549c() != null) {
            m2549c().m2532f();
            m2549c().m2535i();
        }
        if (this.f1962d.containsKey(region.getUniqueId())) {
            C1370a c1370a = (C1370a) this.f1962d.get(region.getUniqueId());
            c1370a.f1949o = C1371b.INACTIVE;
            c1370a.f1937c = System.currentTimeMillis();
            c1370a.f1939e++;
            try {
                c1370a.f1953s.f1960b.stopMonitoringBeaconsInRegion(c1370a.f1935a);
            } catch (RemoteException e) {
            }
            c1370a.f1953s.m2545a(c1370a.f1935a, c1370a.f1937c, false, c1370a.f1947m);
        } else {
            try {
                this.f1960b.stopRangingBeaconsInRegion(region);
            } catch (RemoteException e2) {
            }
            if (region.getUniqueId() != null) {
                this.f1961c.remove(region.getUniqueId());
            }
            if (this.f1961c.isEmpty()) {
                this.f1967i.m2461a();
            }
        }
        m2555f();
    }

    public boolean bindService(Intent intent, ServiceConnection serviceConnection, int i) {
        return getApplicationContext().bindService(intent, serviceConnection, i);
    }

    final void m2565c(long j) {
        this.f1960b.setBackgroundScanPeriod(j);
        m2553e();
    }

    final void m2566d(long j) {
        this.f1960b.setBackgroundBetweenScanPeriod(1000 * j);
        m2553e();
    }

    public Context getApplicationContext() {
        return AreaMetricsSDK.INSTANCE.getContext();
    }

    public void onBeaconServiceConnect() {
        this.f1960b.addRangeNotifier(new C13681(this));
        try {
            this.f1960b.startRangingBeaconsInRegion(new Region("AnyBeacon", null, null, null));
        } catch (RemoteException e) {
        }
    }

    public void unbindService(ServiceConnection serviceConnection) {
        getApplicationContext().unbindService(serviceConnection);
    }
}
