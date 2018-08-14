package com.yandex.metrica.impl.ob;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.SparseArray;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.yandex.metrica.impl.C4364d;
import com.yandex.metrica.impl.C4364d.C4370a;
import com.yandex.metrica.impl.al;
import com.yandex.metrica.impl.bk;
import com.yandex.metrica.impl.ob.dz.C4456b;
import com.yandex.metrica.impl.ob.dz.C4457a;
import com.yandex.metrica.impl.ob.dz.C4458c;
import com.yandex.metrica.impl.ob.dz.C4459d;
import com.yandex.metrica.impl.ob.dz.C4460e;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

final class eb extends dy implements C4364d {
    private static final SparseArray<String> f12309a = new C44621();
    private final TelephonyManager f12310b;
    private PhoneStateListener f12311c;
    private boolean f12312d = false;
    private final C4370a<eg> f12313e = new C4370a();
    private final C4370a<dz[]> f12314f = new C4370a();
    private final Handler f12315g;
    private final Context f12316h;

    static class C44621 extends SparseArray<String> {
        C44621() {
            put(0, null);
            put(7, C1404b.f2127e);
            put(4, C1404b.f2128f);
            put(2, C1404b.f2129g);
            put(14, "eHRPD");
            put(5, "EVDO rev.0");
            put(6, "EVDO rev.A");
            put(12, "EVDO rev.B");
            put(1, C1404b.f2134l);
            put(8, C1404b.f2135m);
            put(10, C1404b.f2136n);
            put(15, "HSPA+");
            put(9, C1404b.f2138p);
            put(11, "iDen");
            put(3, C1404b.f2141s);
            put(12, "EVDO rev.B");
            if (bk.m14985a(11)) {
                put(14, "eHRPD");
                put(13, C1404b.f2140r);
                if (bk.m14985a(13)) {
                    put(15, "HSPA+");
                }
            }
        }
    }

    class C44632 implements Runnable {
        final /* synthetic */ eb f12305a;

        C44632(eb ebVar) {
            this.f12305a = ebVar;
        }

        public void run() {
            this.f12305a.f12311c = new C4466a();
        }
    }

    class C44643 implements Runnable {
        final /* synthetic */ eb f12306a;

        C44643(eb ebVar) {
            this.f12306a = ebVar;
        }

        public void run() {
            if (!this.f12306a.f12312d) {
                this.f12306a.f12312d = true;
                try {
                    if (this.f12306a.f12311c != null) {
                        this.f12306a.f12310b.listen(this.f12306a.f12311c, 256);
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    class C44654 implements Runnable {
        final /* synthetic */ eb f12307a;

        C44654(eb ebVar) {
            this.f12307a = ebVar;
        }

        public void run() {
            if (this.f12307a.f12312d) {
                this.f12307a.f12312d = false;
                try {
                    if (this.f12307a.f12311c != null) {
                        this.f12307a.f12310b.listen(this.f12307a.f12311c, 0);
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    private class C4466a extends PhoneStateListener {
        final /* synthetic */ eb f12308a;

        private C4466a(eb ebVar) {
            this.f12308a = ebVar;
        }

        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);
            this.f12308a.m15860a(signalStrength);
        }
    }

    protected eb(Context context) {
        this.f12316h = context;
        this.f12310b = (TelephonyManager) context.getSystemService("phone");
        HandlerThread handlerThread = new HandlerThread("TelephonyProviderThread");
        handlerThread.start();
        this.f12315g = new Handler(handlerThread.getLooper());
        this.f12315g.post(new C44632(this));
    }

    public synchronized void mo7084a() {
        this.f12315g.post(new C44643(this));
    }

    public synchronized void mo7087b() {
        this.f12315g.post(new C44654(this));
    }

    public synchronized void mo7086a(eh ehVar) {
        if (ehVar != null) {
            ehVar.mo7010a(m15883c());
        }
    }

    public synchronized void mo7085a(ea eaVar) {
        if (eaVar != null) {
            eaVar.mo7033a(m15866g());
        }
    }

    synchronized eg m15883c() {
        eg egVar;
        if (this.f12313e.m15045b() || this.f12313e.m15046c()) {
            eg egVar2 = new eg(m15884d(), m15885e(), m15886f());
            if (egVar2.m15902b().m15843a() == null && !this.f12313e.m15045b()) {
                egVar2.m15902b().m15844a(((eg) this.f12313e.m15043a()).m15902b().m15843a());
            }
            this.f12313e.m15044a(egVar2);
            egVar = egVar2;
        } else {
            egVar = (eg) this.f12313e.m15043a();
        }
        return egVar;
    }

    private synchronized dz[] m15866g() {
        dz[] dzVarArr;
        if (this.f12314f.m15045b() || this.f12314f.m15046c()) {
            List arrayList = new ArrayList();
            if (bk.m14985a(17) && al.m14592a(this.f12316h, "android.permission.ACCESS_COARSE_LOCATION")) {
                Collection allCellInfo = this.f12310b.getAllCellInfo();
                if (!bk.m14987a(allCellInfo)) {
                    for (int i = 0; i < allCellInfo.size(); i++) {
                        Object obj;
                        CellInfo cellInfo = (CellInfo) allCellInfo.get(i);
                        C4456b c4458c = cellInfo instanceof CellInfoGsm ? new C4458c() : cellInfo instanceof CellInfoCdma ? new C4457a() : cellInfo instanceof CellInfoLte ? new C4459d() : (bk.m14985a(18) && (cellInfo instanceof CellInfoWcdma)) ? new C4460e() : null;
                        if (c4458c == null) {
                            obj = null;
                        } else {
                            obj = c4458c.mo7083a(cellInfo);
                        }
                        if (obj != null) {
                            arrayList.add(obj);
                        }
                    }
                }
            }
            dzVarArr = arrayList.size() <= 0 ? new dz[]{m15883c().m15902b()} : (dz[]) arrayList.toArray(new dz[arrayList.size()]);
            this.f12314f.m15044a(dzVarArr);
        } else {
            dzVarArr = (dz[]) this.f12314f.m15043a();
        }
        return dzVarArr;
    }

    private synchronized void m15860a(SignalStrength signalStrength) {
        if (!(this.f12313e.m15045b() || this.f12313e.m15046c())) {
            int gsmSignalStrength;
            dz b = ((eg) this.f12313e.m15043a()).m15902b();
            if (signalStrength.isGsm()) {
                gsmSignalStrength = signalStrength.getGsmSignalStrength();
                gsmSignalStrength = 99 == gsmSignalStrength ? -1 : (gsmSignalStrength * 2) - 113;
            } else {
                gsmSignalStrength = signalStrength.getCdmaDbm();
                int evdoDbm = signalStrength.getEvdoDbm();
                if (-120 != evdoDbm) {
                    gsmSignalStrength = -120 == gsmSignalStrength ? evdoDbm : Math.min(gsmSignalStrength, evdoDbm);
                }
            }
            b.m15844a(Integer.valueOf(gsmSignalStrength));
        }
    }

    private Integer m15867h() {
        Integer num = null;
        try {
            Object substring = this.f12310b.getNetworkOperator().substring(0, 3);
            if (!TextUtils.isEmpty(substring)) {
                num = Integer.valueOf(Integer.parseInt(substring));
            }
        } catch (Exception e) {
        }
        return num;
    }

    private Integer m15868i() {
        Integer num = null;
        try {
            Object substring = this.f12310b.getNetworkOperator().substring(3);
            if (!TextUtils.isEmpty(substring)) {
                num = Integer.valueOf(Integer.parseInt(substring));
            }
        } catch (Exception e) {
        }
        return num;
    }

    private Integer m15869j() {
        Integer num = null;
        try {
            Object substring = this.f12310b.getSimOperator().substring(0, 3);
            if (!TextUtils.isEmpty(substring)) {
                num = Integer.valueOf(Integer.parseInt(substring));
            }
        } catch (Exception e) {
        }
        return num;
    }

    private Integer m15870k() {
        Integer num = null;
        try {
            Object substring = this.f12310b.getSimOperator().substring(3);
            if (!TextUtils.isEmpty(substring)) {
                num = Integer.valueOf(Integer.parseInt(substring));
            }
        } catch (Exception e) {
        }
        return num;
    }

    private Integer m15871l() {
        try {
            if (al.m14591a(this.f12316h)) {
                int cid = ((GsmCellLocation) this.f12310b.getCellLocation()).getCid();
                return -1 != cid ? Integer.valueOf(cid) : null;
            }
        } catch (Exception e) {
        }
        return null;
    }

    private Integer m15872m() {
        try {
            if (al.m14591a(this.f12316h)) {
                int lac = ((GsmCellLocation) this.f12310b.getCellLocation()).getLac();
                return -1 != lac ? Integer.valueOf(lac) : null;
            }
        } catch (Exception e) {
        }
        return null;
    }

    private String m15873n() {
        String str = "unknown";
        try {
            return (String) f12309a.get(this.f12310b.getNetworkType(), str);
        } catch (Exception e) {
            return str;
        }
    }

    private String m15874o() {
        String str = null;
        try {
            if (al.m14592a(this.f12316h, "android.permission.READ_PHONE_STATE")) {
                str = this.f12310b.getDeviceId();
            }
        } catch (Exception e) {
        }
        return str;
    }

    private List<String> m15875p() {
        Collection hashSet = new HashSet();
        try {
            if (al.m14592a(this.f12316h, "android.permission.READ_PHONE_STATE")) {
                for (int i = 0; i < 10; i++) {
                    String deviceId = this.f12310b.getDeviceId(i);
                    if (deviceId != null) {
                        hashSet.add(deviceId);
                    }
                }
            }
        } catch (Exception e) {
        }
        return new ArrayList(hashSet);
    }

    private boolean m15876q() {
        if (al.m14592a(this.f12316h, "android.permission.READ_PHONE_STATE")) {
            try {
                return this.f12310b.isNetworkRoaming();
            } catch (Exception e) {
            }
        }
        return false;
    }

    dz m15884d() {
        return new dz(m15867h(), m15868i(), m15872m(), m15871l(), this.f12310b.getNetworkOperatorName(), m15873n(), null, true, 0, null);
    }

    List<ee> m15885e() {
        List<ee> arrayList = new ArrayList();
        if (bk.m14985a(23)) {
            arrayList.addAll(m15878s());
            if (arrayList.size() == 0) {
                arrayList.add(m15877r());
            }
        } else {
            arrayList.add(m15877r());
        }
        return arrayList;
    }

    List<String> m15886f() {
        List<String> arrayList = new ArrayList();
        if (bk.m14985a(23)) {
            arrayList.addAll(m15875p());
        } else {
            arrayList.add(m15874o());
        }
        return arrayList;
    }

    private ee m15877r() {
        return new ee(m15869j(), m15870k(), m15876q(), this.f12310b.getSimOperatorName(), null);
    }

    private List<ee> m15878s() {
        List<ee> arrayList = new ArrayList();
        if (al.m14592a(this.f12316h, "android.permission.READ_PHONE_STATE")) {
            try {
                List<SubscriptionInfo> activeSubscriptionInfoList = SubscriptionManager.from(this.f12316h).getActiveSubscriptionInfoList();
                if (activeSubscriptionInfoList != null) {
                    for (SubscriptionInfo eeVar : activeSubscriptionInfoList) {
                        arrayList.add(new ee(eeVar));
                    }
                }
            } catch (Exception e) {
            }
        }
        return arrayList;
    }
}
