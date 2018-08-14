package com.inmobi.signals.p119a;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.telephony.CellInfo;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import com.inmobi.commons.core.utilities.C3156e;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.p112a.C3105a;
import com.inmobi.signals.C3277o;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;

@TargetApi(17)
/* compiled from: CellularInfoUtil */
public class C3247c {
    private static final String f8169a = C3247c.class.getSimpleName();

    public static Map<String, String> m10848a() {
        Map<String, String> hashMap = new HashMap();
        Context b = C3105a.m10078b();
        if (b == null) {
            return hashMap;
        }
        if (!C3277o.m10989a().m10994e().m11074n()) {
            return hashMap;
        }
        int m = C3277o.m10989a().m10994e().m11073m();
        boolean a = C3247c.m10850a(m, 2);
        boolean a2 = C3247c.m10850a(m, 1);
        C3245a c3245a = new C3245a();
        TelephonyManager telephonyManager = (TelephonyManager) b.getSystemService("phone");
        if (!a) {
            int[] a3 = C3247c.m10851a(telephonyManager.getNetworkOperator());
            c3245a.m10835a(a3[0]);
            c3245a.m10838b(a3[1]);
            c3245a.m10836a(telephonyManager.getNetworkCountryIso());
        }
        if (!a2) {
            int[] a4 = C3247c.m10851a(telephonyManager.getSimOperator());
            c3245a.m10840c(a4[0]);
            c3245a.m10841d(a4[1]);
        }
        hashMap.put("s-ho", c3245a.m10837b());
        hashMap.put("s-co", c3245a.m10834a());
        hashMap.put("s-iso", c3245a.m10839c());
        return hashMap;
    }

    private static boolean m10850a(int i, int i2) {
        return (i & i2) == i2;
    }

    private static int[] m10851a(String str) {
        int[] iArr = new int[]{-1, -1};
        if (!(str == null || str.equals(""))) {
            try {
                int parseInt = Integer.parseInt(str.substring(0, 3));
                int parseInt2 = Integer.parseInt(str.substring(3));
                iArr[0] = parseInt;
                iArr[1] = parseInt2;
            } catch (Throwable e) {
                Logger.m10360a(InternalLogLevel.INTERNAL, f8169a, "Error while collecting cell info.", e);
            } catch (Throwable e2) {
                Logger.m10360a(InternalLogLevel.INTERNAL, f8169a, "Error while collecting cell info.", e2);
            }
        }
        return iArr;
    }

    public static C3246b m10852b() {
        if (C3277o.m10989a().m10994e().m11076p() && C3247c.m10856f()) {
            return C3247c.m10857g();
        }
        return null;
    }

    public static Map<String, String> m10853c() {
        C3246b b = C3247c.m10852b();
        Map hashMap = new HashMap();
        if (b != null) {
            hashMap.put("c-sc", b.m10844a().toString());
        }
        return hashMap;
    }

    private static boolean m10856f() {
        Context b = C3105a.m10078b();
        if (b == null) {
            return false;
        }
        boolean z;
        if (C3156e.m10410a(b, "signals", "android.permission.ACCESS_COARSE_LOCATION")) {
            z = true;
        } else {
            z = false;
        }
        boolean z2;
        if (C3156e.m10410a(b, "signals", "android.permission.ACCESS_FINE_LOCATION")) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z || r3) {
            return true;
        }
        return false;
    }

    private static C3246b m10857g() {
        Context b = C3105a.m10078b();
        if (b == null) {
            return null;
        }
        TelephonyManager telephonyManager = (TelephonyManager) b.getSystemService("phone");
        int[] a = C3247c.m10851a(telephonyManager.getNetworkOperator());
        String valueOf = String.valueOf(a[0]);
        String valueOf2 = String.valueOf(a[1]);
        if (VERSION.SDK_INT >= 17) {
            List allCellInfo = telephonyManager.getAllCellInfo();
            if (allCellInfo != null) {
                CellInfo cellInfo = null;
                for (int i = 0; i < allCellInfo.size(); i++) {
                    cellInfo = (CellInfo) allCellInfo.get(i);
                    if (cellInfo.isRegistered()) {
                        break;
                    }
                }
                if (cellInfo != null) {
                    return new C3246b(cellInfo, valueOf, valueOf2, telephonyManager.getNetworkType());
                }
            }
        }
        CellLocation cellLocation = telephonyManager.getCellLocation();
        if (cellLocation == null || a[0] == -1) {
            return null;
        }
        C3246b c3246b = new C3246b();
        if (cellLocation instanceof CdmaCellLocation) {
            CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
            c3246b.m10847b(Integer.MAX_VALUE);
            c3246b.m10845a(telephonyManager.getNetworkType());
            c3246b.m10846a(c3246b.m10842a(valueOf, cdmaCellLocation.getSystemId(), cdmaCellLocation.getNetworkId(), cdmaCellLocation.getBaseStationId()));
        } else {
            GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
            c3246b.m10847b(Integer.MAX_VALUE);
            c3246b.m10845a(telephonyManager.getNetworkType());
            c3246b.m10846a(c3246b.m10843a(valueOf, valueOf2, gsmCellLocation.getLac(), gsmCellLocation.getCid(), gsmCellLocation.getPsc(), Integer.MAX_VALUE));
        }
        return c3246b;
    }

    public static Map<String, String> m10854d() {
        List e = C3247c.m10855e();
        Map hashMap = new HashMap();
        if (!(e == null || e.isEmpty())) {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(((C3246b) e.get(e.size() - 1)).m10844a());
            hashMap.put("v-sc", jSONArray.toString());
        }
        return hashMap;
    }

    public static List<C3246b> m10855e() {
        if (!C3105a.m10076a() || !C3247c.m10858h() || !C3277o.m10989a().m10994e().m11075o()) {
            return new ArrayList();
        }
        Context b = C3105a.m10078b();
        if (b == null) {
            return new ArrayList();
        }
        TelephonyManager telephonyManager = (TelephonyManager) b.getSystemService("phone");
        List<C3246b> arrayList = new ArrayList();
        int[] a = C3247c.m10851a(telephonyManager.getNetworkOperator());
        String valueOf = String.valueOf(a[0]);
        String valueOf2 = String.valueOf(a[1]);
        if (VERSION.SDK_INT >= 17) {
            List<CellInfo> allCellInfo = telephonyManager.getAllCellInfo();
            if (allCellInfo != null) {
                for (CellInfo cellInfo : allCellInfo) {
                    if (!cellInfo.isRegistered()) {
                        arrayList.add(new C3246b(cellInfo, valueOf, valueOf2, telephonyManager.getNetworkType()));
                    }
                }
                return arrayList;
            }
        }
        List neighboringCellInfo = telephonyManager.getNeighboringCellInfo();
        if (neighboringCellInfo == null || neighboringCellInfo.isEmpty()) {
            return new ArrayList();
        }
        Iterator it = neighboringCellInfo.iterator();
        if (!it.hasNext()) {
            return new ArrayList();
        }
        NeighboringCellInfo neighboringCellInfo2 = (NeighboringCellInfo) it.next();
        C3246b c3246b = new C3246b();
        int networkType = neighboringCellInfo2.getNetworkType();
        c3246b.m10845a(networkType);
        if (neighboringCellInfo2.getRssi() == 99) {
            c3246b.m10847b(Integer.MAX_VALUE);
        } else if (C3247c.m10849a(networkType)) {
            c3246b.m10847b(neighboringCellInfo2.getRssi() - 116);
        } else {
            c3246b.m10847b((neighboringCellInfo2.getRssi() * 2) - 113);
        }
        c3246b.m10846a(c3246b.m10843a(valueOf, valueOf2, neighboringCellInfo2.getLac(), neighboringCellInfo2.getCid(), -1, Integer.MAX_VALUE));
        arrayList.add(c3246b);
        return arrayList;
    }

    private static boolean m10849a(int i) {
        switch (i) {
            case 3:
            case 8:
            case 9:
            case 10:
            case 15:
                return true;
            default:
                return false;
        }
    }

    private static boolean m10858h() {
        return C3105a.m10076a() && C3156e.m10410a(C3105a.m10078b(), "signals", "android.permission.ACCESS_COARSE_LOCATION");
    }
}
