package com.oneaudience.sdk.p133a;

import android.content.Context;
import android.os.Build.VERSION;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.CellSignalStrengthLte;
import android.telephony.CellSignalStrengthWcdma;
import android.telephony.TelephonyManager;
import com.oneaudience.sdk.C3843e;
import com.oneaudience.sdk.model.CellTowerInfo;
import com.oneaudience.sdk.p135c.C3833d;
import java.util.ArrayList;

public class C3794h extends C3784a {
    private static final String[] f9107f = new String[]{"android.permission.ACCESS_COARSE_LOCATION"};

    protected C3794h(Context context, String str, boolean z, boolean z2, long j) {
        super(context, str, z, z2, j, "cell_tower_data", "disableCellTowerCollector", true, true);
    }

    private ArrayList<CellTowerInfo> m12123i() {
        ArrayList<CellTowerInfo> arrayList = new ArrayList();
        TelephonyManager telephonyManager = (TelephonyManager) this.c.getSystemService("phone");
        if (VERSION.SDK_INT >= 17) {
            for (CellInfo cellInfo : telephonyManager.getAllCellInfo()) {
                if (cellInfo instanceof CellInfoGsm) {
                    CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
                    CellIdentityGsm cellIdentity = cellInfoGsm.getCellIdentity();
                    CellSignalStrengthGsm cellSignalStrength = cellInfoGsm.getCellSignalStrength();
                    arrayList.add(new CellTowerInfo("gsm", cellIdentity.getCid(), cellIdentity.getLac(), cellIdentity.getMcc(), cellIdentity.getMnc(), -1, -1, -1, -1, -1, cellSignalStrength.getLevel(), cellSignalStrength.getDbm(), cellInfo.isRegistered()));
                } else if (cellInfo instanceof CellInfoCdma) {
                    CellInfoCdma cellInfoCdma = (CellInfoCdma) cellInfo;
                    CellIdentityCdma cellIdentity2 = cellInfoCdma.getCellIdentity();
                    CellSignalStrengthCdma cellSignalStrength2 = cellInfoCdma.getCellSignalStrength();
                    arrayList.add(new CellTowerInfo("cdma", -1, -1, -1, -1, cellIdentity2.getBasestationId(), cellIdentity2.getLatitude(), cellIdentity2.getLongitude(), cellIdentity2.getNetworkId(), cellIdentity2.getSystemId(), cellSignalStrength2.getLevel(), cellSignalStrength2.getDbm(), cellInfo.isRegistered()));
                } else if (cellInfo instanceof CellInfoLte) {
                    CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
                    CellIdentityLte cellIdentity3 = cellInfoLte.getCellIdentity();
                    CellSignalStrengthLte cellSignalStrength3 = cellInfoLte.getCellSignalStrength();
                    arrayList.add(new CellTowerInfo("lte", cellIdentity3.getCi(), -1, cellIdentity3.getMcc(), cellIdentity3.getMnc(), -1, -1, -1, -1, -1, cellSignalStrength3.getLevel(), cellSignalStrength3.getDbm(), cellInfo.isRegistered()));
                } else if (VERSION.SDK_INT >= 18 && (cellInfo instanceof CellInfoWcdma)) {
                    CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
                    CellIdentityWcdma cellIdentity4 = cellInfoWcdma.getCellIdentity();
                    CellSignalStrengthWcdma cellSignalStrength4 = cellInfoWcdma.getCellSignalStrength();
                    arrayList.add(new CellTowerInfo("wcdma", cellIdentity4.getCid(), cellIdentity4.getLac(), cellIdentity4.getMcc(), cellIdentity4.getMnc(), -1, -1, -1, -1, -1, cellSignalStrength4.getLevel(), cellSignalStrength4.getDbm(), cellInfo.isRegistered()));
                }
            }
        }
        return arrayList;
    }

    public String mo6804a() {
        if (C3843e.m12285a(this.c, "android.permission.ACCESS_COARSE_LOCATION")) {
            return m12083a((Object) m12123i());
        }
        C3833d.m12246a(a, "Don't have permissions to collect cell tower");
        return "";
    }

    public String[] mo6805b() {
        return f9107f;
    }
}
